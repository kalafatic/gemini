/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.gemini.bt.client.net.controller.adapters;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.MAX_SWARM_THREADS;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TORRENT_PROGRESS_COMPARATOR;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TRACKERS_DELAY;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.EContentAdapter;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AControl;
import eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.PooledThreads;
import eu.kalafatic.gemini.bt.client.net.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.TrackersManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SwarmsAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmsAdapter extends EContentAdapter implements Adapter {

	/** The active swarms. */
	private EList<String> activeSwarms;

	/** The started. */
	private List<String> started = new ArrayList<String>();

	/** The removed. */
	private List<String> removed = new ArrayList<String>();

	/** The client network. */
	private ClientNetwork clientNetwork;

	/** The available. */
	private AtomicInteger available = new AtomicInteger();

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * The Enum enum BTRun.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public static enum BTRun {

		/** The none. */
		NONE(0),

		/** The force. */
		FORCE(1 << 0),

		/** The swarms. */
		SWARMS(1 << 1),

		/** The torrents unf. */
		TORRENTS_UNF(1 << 2),

		/** The torrents fin. */
		TORRENTS_FIN(1 << 3),

		/** The torrents both. */
		TORRENTS_BOTH((1 << 2) | (1 << 3));

		/** The flag. */
		public int flag;

		/**
		 * Instantiates a new BT run.
		 * @param flag the flag
		 */
		BTRun(int flag) {
			this.flag = flag;
		}
	}

	/** The run. */
	public static BTRun run = BTRun.TORRENTS_BOTH;

	/** The instance. */
	private volatile static SwarmsAdapter INSTANCE;

	/**
	 * Gets the single instance of SwarmsAdapter.
	 * @return single instance of SwarmsAdapter
	 */
	public static SwarmsAdapter getInstance() {
		if (INSTANCE == null) {
			synchronized (SwarmsAdapter.class) {
				INSTANCE = new SwarmsAdapter();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the and check.
	 * @throws Exception the exception
	 */
	private void initAndCheck() throws Exception {
		clientNetwork = NetworkModelManager.getInstance().getClientNetwork();
		activeSwarms = clientNetwork.getActiveSwarms();

		available.set((MAX_SWARM_THREADS + getAvailableFinished()) - activeSwarms.size());

		started.clear();
		removed.clear();
		// remove nonactive swarms
		checkActiveSwarms();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse. emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getFeature() instanceof EAttribute) {
			EAttribute atribute = (EAttribute) notification.getFeature();

			if (atribute.getName().equals("opened")) {
				processSheduler();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process sheduler.
	 */
	public void processSheduler() {
		if (lock.tryLock()) {
			try {
				initAndCheck();

				if (available.get() > 0) {
					processScheduler(0);
				} else {
					runActiveSwarms();
				}
			} catch (Exception e) {
				Log.log(MODULE, e);
			} finally {
				lock.unlock();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process scheduler.
	 * @param jump the jump
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private void processScheduler(int jump) throws Exception {
		switch (jump) {
		case 0:
			if (!clientNetwork.getForceSwarmsKeys().isEmpty()) {
				processSheduler(BTRun.FORCE, clientNetwork.getForceSwarmsKeys(), 0);
			}
			break;
		case 1:
			if (!AControl.dwnldStarted) {
				processScheduler(3);
			}
			if (!clientNetwork.getSwarmMap().isEmpty()) {
				List<SwarmSession> sessions = new ArrayList<SwarmSession>((Collection<? extends SwarmSession>) clientNetwork.getSwarmMap().values());

				sessions = filterDwnldSessions(sessions);

				Collections.sort(sessions, FNetConstants.SWARM_RATING_COMPARATOR_2);
				processSheduler(BTRun.SWARMS, sessions, 0);
			}
			break;
		case 2:
			if (!NetworkModelManager.getInstance().getTorrents().getTorrentMap().isEmpty()) {
				List<ExtTorrent> torrents = new ArrayList<ExtTorrent>(NetworkModelManager.getInstance().getTorrents().getTorrentMap().values());

				Collections.sort(torrents, TORRENT_PROGRESS_COMPARATOR);
				processSheduler(BTRun.TORRENTS_UNF, torrents, 0);
			}
			break;
		case 3:
			if (!NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().isEmpty()) {
				List<ExtTorrent> finTorrents = new ArrayList<ExtTorrent>(NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().values());

				Collections.shuffle(finTorrents);
				processSheduler(BTRun.TORRENTS_FIN, finTorrents, 0);
			}
			break;
		default:
			return;
		}

		if (available.get() > 0) {
			processScheduler(++jump);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Filter dwnld sessions.
	 * @param sessions the sessions
	 * @return the list
	 */
	private List<SwarmSession> filterDwnldSessions(List<SwarmSession> sessions) {
		List<SwarmSession> result = new ArrayList<SwarmSession>();

		for (SwarmSession swarmSession : sessions) {
			if (!((ExtTorrent) swarmSession.getTorrent()).isFinished()) {
				result.add(swarmSession);
			}
		}

		for (int i = 0; (i < sessions.size()) && (result.size() < MAX_SWARM_THREADS); i++) {
			if (!result.contains(sessions.get(i))) {
				result.add(sessions.get(i));
			}
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Process sheduler.
	 * @param jump the jump
	 * @param list the list
	 * @param index the index
	 */
	@SuppressWarnings({ "rawtypes" })
	private synchronized void processSheduler(BTRun jump, List list, int index) {
		boolean next = false;
		String key = null;
		try {
			switch (jump) {
			case FORCE:
				key = (String) list.remove(0);
				next = list.size() > 0;
				break;
			case SWARMS:
				key = ((SwarmSession) list.get(index)).getAnnounce();
				next = (index + 1) < list.size();
				break;
			case TORRENTS_UNF:
			case TORRENTS_FIN:
				key = ((ExtTorrent) list.get(index)).getName();
				next = (index + 1) < list.size();
				break;
			default:
				break;
			}
			procesDwnldUpld(key);

			if (next && (available.get() > 0)) {
				processSheduler(jump, list, ++index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run active swarms.
	 */
	private void runActiveSwarms() {
		for (int i = 0; (i < activeSwarms.size()) && (i < MAX_SWARM_THREADS); i++) {

			SwarmSession swarmSession = (SwarmSession) clientNetwork.getSwarmMap().get(activeSwarms.get(i));

			ExtTorrent extTorrent = (ExtTorrent) swarmSession.getTorrent();

			if (AControl.canContinue(extTorrent, swarmSession)) {
				if (AControl.dwnldStarted) {
					((TrackersManager) swarmSession.getTrackersManager()).runTrackers();
				} else {
					((TrackersManager) swarmSession.getTrackersManager()).runTrackerForUpdate();
				}
			}
		}
	}

	// ---------------------------------------------------------------
	/*
	 * Prevents freezed sessions
	 */
	/**
	 * Check active swarms.
	 */
	private void checkActiveSwarms() {
		List<String> copyOfActiveSwarms = new ArrayList<String>(activeSwarms);

		for (String key : copyOfActiveSwarms) {

			SwarmSession swarmSession = (SwarmSession) clientNetwork.getSwarmMap().get(key);

			if (swarmSession.getLastActivity() == 0) {
				swarmSession.setLastActivity(System.currentTimeMillis());
				continue;
			}

			ExtTorrent extTorrent = NetworkModelManager.getInstance().getDwnldUpldExtTorrent(key);

			if (extTorrent.isFinished()) {
				if (!isFinishedUploading(extTorrent.getAdditionalInfo())) {
					releaseSwarm(key);
				}
			} else {
				if (checkExecutorsQueueIsEmpty(key)) {
					releaseSwarm(key);

				} else if (checkSwarmSessionLastActivity(swarmSession.getLastActivity(), key)) {
					releaseSwarm(key);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Proces dwnld upld.
	 * @param key the key
	 * @throws Exception the exception
	 */
	private void procesDwnldUpld(String key) throws Exception {
		if (started.contains(key) || removed.contains(key)) {
			return;
		}
		ExtTorrent extTorrent = NetworkModelManager.getInstance().getDwnldUpldExtTorrent(key);

		if (AControl.canContinue(extTorrent)) {
			procesDwnldUpld(key, extTorrent);
		} else {
			// TODO can't occur (clear model!)
			clientNetwork.getSwarmMap().removeKey(key);
			releaseSwarm(key);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Proces dwnld upld.
	 * @param key the key
	 * @param extTorrent the ext torrent
	 * @throws Exception the exception
	 */
	private void procesDwnldUpld(String key, ExtTorrent extTorrent) throws Exception {
		SwarmSession swarmSession = NetworkModelManager.getInstance().getSwarmSession(extTorrent);
		TrackersManager trackersManager = (TrackersManager) swarmSession.getTrackersManager();

		if (trackersManager == null) {
			releaseSwarm(key);
		} else {
			if (extTorrent.isFinished()) {
				// must be enabled unfinished table
				if ((run.flag & BTRun.TORRENTS_FIN.flag) != 0) {
					trackersManager.runTrackerForUpdate();
				} else {
					return;
				}
			} else {
				if ((run.flag & BTRun.TORRENTS_UNF.flag) != 0) {
					trackersManager.runTrackers();
				} else {
					return;
				}
			}
			addSwarm(key, swarmSession);
			available.decrementAndGet();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check swarm session last activity.
	 * @param lastActivity the last activity
	 * @param key the key
	 * @return true, if successful
	 */
	private boolean checkSwarmSessionLastActivity(long lastActivity, String key) {
		long activityDelay = System.currentTimeMillis() - (TRACKERS_DELAY * 1000);

		if (activityDelay > lastActivity) {

			AdditionalInfo addInfo = NetworkModelManager.getInstance().getDwnldUpldExtTorrent(key).getAdditionalInfo();

			return (addInfo.getSeeds() == 0) && (addInfo.getPeers() == 0);
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is finished uploading.
	 * @param addInfo the add info
	 * @return true, if is finished uploading
	 */
	private boolean isFinishedUploading(AdditionalInfo addInfo) {
		return addInfo.getDownloaders() <= 0 ? false : true;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the available finished.
	 * @return the available finished
	 */
	private int getAvailableFinished() {
		Collection<ExtTorrent> values = NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().values();
		int counter = 0;
		for (ExtTorrent extTorrent : values) {
			counter += extTorrent.isEnabled() ? 1 : 0;
		}
		return counter;
	}

	// ---------------------------------------------------------------

	/**
	 * Check executors queue is empty.
	 * @param key the key
	 * @return true, if successful
	 */
	private boolean checkExecutorsQueueIsEmpty(String key) {
		PooledThreads pooledThreads = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(key);
		pooledThreads.getLock().lock();
		try {
			if ((pooledThreads.getDownloadExecutor().getActiveCount() == 0) && (pooledThreads.getUploadExecutor().getActiveCount() == 0)) {
				return true;
			} else {
				return false;
			}
		} finally {
			pooledThreads.getLock().unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the swarm.
	 * @param key the key
	 * @param swarmSession the swarm session
	 */
	private void addSwarm(String key, SwarmSession swarmSession) {
		try {
			swarmSession.setRating(swarmSession.getRating() + 1);
			// TrackersManager should remove
			synchronized (activeSwarms) {
				if (!activeSwarms.contains(key)) {
					activeSwarms.add(key);
				}
			}
			started.add(key);

			Log.log(MODULE, LOG + "ADDED ACTIVE SWARM " + key);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Release swarm.
	 * @param key the key
	 */
	private void releaseSwarm(String key) {
		try {
			// EList<String> activeSwarms = clientNetwork.getActiveSwarms();
			synchronized (activeSwarms) {
				if (activeSwarms.contains(key)) {
					activeSwarms.remove(key);
				}
			}
			removed.add(key);

			SwarmSession swarmSession = (SwarmSession) clientNetwork.getSwarmMap().get(key);

			if (swarmSession != null) {
				swarmSession.setState(EViewsMessages.READY);
				swarmSession.setRating(swarmSession.getRating() - 1);
			}
			Log.log(MODULE, LOG + "REMOVE NONACTIVE SWARM " + key);

			available.incrementAndGet();

		} catch (Exception e) {
			// TODO
			// e.printStackTrace();
		}
	}
}
