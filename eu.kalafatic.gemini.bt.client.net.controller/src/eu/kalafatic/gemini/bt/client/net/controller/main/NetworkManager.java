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
package eu.kalafatic.gemini.bt.client.net.controller.main;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TORRENT_PROGRESS_COMPARATOR;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscReader;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.PooledThreads;
import eu.kalafatic.gemini.bt.client.net.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.client.net.controller.threads.ListenClientsThread;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.SpeedScheduler;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.SwarmsScheduler;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.WatchDogSheduler;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ERemote;
import eu.kalafatic.gemini.core.schedulers.CPUScheduler;
import eu.kalafatic.gemini.core.threads.DeadlocksManagerThread;
import eu.kalafatic.gemini.core.utils.HashUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class NetworkManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetworkManager {

	/** The listen clients. */
	private boolean listenClients;

	/** The instance. */
	private volatile static NetworkManager INSTANCE;

	/**
	 * Instantiates a new network manager.
	 */
	public NetworkManager() {
		init();
	}

	/**
	 * Gets the single instance of NetworkManager.
	 * @return single instance of NetworkManager
	 */
	public static NetworkManager getInstance() {
		if (INSTANCE == null) {
			synchronized (NetworkManager.class) {
				INSTANCE = new NetworkManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		String pingEnabled = PREFERENCES.get(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getDef());

		int delay = Activator.getPreferences().getInt(ECorePreferences.WATCHDOG_DELAY.getName(), (Integer) ECorePreferences.WATCHDOG_DELAY.getDef());

		if (pingEnabled.equals(ERemote.AUTO.literal)) {
			WatchDogSheduler.getInstance(ERemote.AUTO, delay).schedule();
		}

		listenClients = Activator.getPreferences().getBoolean(EBTClientPreferences.LISTENING_PEERS.getName(), (Boolean) EBTClientPreferences.LISTENING_PEERS.getDef());

		checkListenClients();
	}

	// ---------------------------------------------------------------

	/**
	 * Run swarms.
	 * @param selected the selected
	 */
	public void runSwarms(ExtTorrent... selected) {
		if (selected.length > 0) {
			runSwarms(Arrays.asList(selected));
		} else {
			Collection<ExtTorrent> torrents = NetworkModelManager.getInstance().getTorrents().getTorrentMap().values();
			runSwarms(new ArrayList<ExtTorrent>(torrents));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run swarms.
	 * @param selected the selected
	 */
	public void runSwarms(List<ExtTorrent> selected) {
		Collections.sort(selected, TORRENT_PROGRESS_COMPARATOR);

		Collection<String> forceSwarmsKeys = NetworkModelManager.getInstance().getClientNetwork().getForceSwarmsKeys();

		for (int i = 0; i < selected.size(); i++) {

			String key = selected.get(i).getName();

			if (!forceSwarmsKeys.contains(key)) {
				forceSwarmsKeys.add(key);
			}
		}
		runSwarmThreads();
	}

	// ---------------------------------------------------------------

	/**
	 * Run swarm threads.
	 */
	public void runSwarmThreads() {
		if (!NetworkModelManager.getInstance().getClientNetwork().isSheduled()) {
			initShedulers();
		}
		// run swarm immediately
		SwarmsAdapter.getInstance().processSheduler();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the shedulers.
	 */
	private void initShedulers() {
		// schedule 2min first 4min next
		SwarmsScheduler.INSTANCE.schedule();
		SpeedScheduler.INSTANCE.schedule();

		if (PREFERENCES.getBoolean(ECorePreferences.THREAD_MANAGEMENT.getName(), (Boolean) ECorePreferences.THREAD_MANAGEMENT.getDef())) {
			DeadlocksManagerThread.INSTANCE.start();
		} else {
			DeadlocksManagerThread.INSTANCE.stop();
		}

		if (PREFERENCES.getBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), (Boolean) ECorePreferences.CPU_MANAGEMENT.getDef())) {
			CPUScheduler.INSTANCE.start();
		} else {
			CPUScheduler.INSTANCE.stop();
		}
		NetworkModelManager.getInstance().getClientNetwork().setSheduled(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Pause swarms.
	 * @param selected the selected
	 */
	public void pauseSwarms(List<ExtTorrent> selected) {
		try {
			for (ExtTorrent extTorrent : selected) {

				PooledThreads pooledThreads = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(extTorrent.getName());

				// swarm not created yet
				if (pooledThreads != null) {
					pauseSwarmThreads(extTorrent, pooledThreads);
				}
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Stop swarms.
	 * @param selected the selected
	 */
	public void stopSwarms(ExtTorrent... selected) {
		stopSwarms(Arrays.asList(selected));
	}

	// ---------------------------------------------------------------

	/**
	 * Stop swarms.
	 * @param selected the selected
	 */
	public void stopSwarms(List<ExtTorrent> selected) {
		try {
			for (ExtTorrent extTorrent : selected) {
				PooledThreads pooledThreads = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(extTorrent.getName());

				// swarm not created yet
				if (pooledThreads != null) {
					pooledThreads.getDownloadExecutor().purge();
					pooledThreads.getUploadExecutor().purge();

					pooledThreads.getDownloadExecutor().shutdownNow();
					pooledThreads.getUploadExecutor().shutdownNow();
				}
				NetworkModelManager.getInstance().removeFromActiveSwarms(extTorrent.getName(), 0);
			}

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Pause swarm threads.
	 * @param extTorrent the ext torrent
	 * @param pooledThreads the pooled threads
	 */
	private void pauseSwarmThreads(ExtTorrent extTorrent, PooledThreads pooledThreads) {
		pooledThreads.lock.lock();
		try {
			setViewMessage(extTorrent, EViewsMessages.PAUSING_SWARM);
			// removes all future tasks
			pooledThreads.getDownloadExecutor().purge();
			pooledThreads.getUploadExecutor().purge();
		} finally {
			pooledThreads.lock.unlock();
			NetworkModelManager.getInstance().removeFromActiveSwarms(extTorrent.getName(), 0);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Verify torrents.
	 * @param selected the selected
	 */
	public void verifyTorrents(List<ExtTorrent> selected) {
		try {
			for (ExtTorrent extTorrent : selected) {
				extTorrent.getAdditionalInfo().getVerifyList().clear();
				verifyTorrent(extTorrent);
				extTorrent.setEnabled(true);
				extTorrent.setStatus(EViewsMessages.READY);
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Verify torrent.
	 * @param extTorrent the ext torrent
	 */
	public void verifyTorrent(ExtTorrent extTorrent) {
		try {
			int length = extTorrent.getModelBitfield().length;
			int pieceLength = extTorrent.getInfo().getPieceLength();
			int index = 0;
			long offset = 0;
			for (; index < (length - 1); index++) {
				extTorrent.getModelBitfield()[index] = verifyTorrent(extTorrent, index, offset, pieceLength);
				offset += pieceLength;
			}
			// last piece
			pieceLength = getLastPieceLength(extTorrent);
			extTorrent.getModelBitfield()[index] = verifyTorrent(extTorrent, index, offset, pieceLength);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Verify torrent.
	 * @param extTorrent the ext torrent
	 * @param index the index
	 * @param begin the begin
	 * @param pieceLength the piece length
	 * @return true, if successful
	 */
	public boolean verifyTorrent(ExtTorrent extTorrent, int index, long offset, int pieceLength) {
		try {
			byte[] hash = Arrays.copyOfRange(extTorrent.getInfo().getPieces(), index * 20, (index * 20) + 20);

			byte[] payload = DiscReader.getInstance().readRAFDataFromDisc(extTorrent, offset, pieceLength);
			return HashUtils.getInstance().checkBlockHash(payload, hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the last piece length.
	 * @param extTorrent the ext torrent
	 * @return the last piece length
	 */
	public int getLastPieceLength(ExtTorrent extTorrent) {
		long fileSize = extTorrent.getAdditionalInfo().getFileSize();
		int pieceLength = extTorrent.getInfo().getPieceLength();
		return (int) (fileSize % pieceLength);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the view message.
	 * @param extTorrent the ext torrent
	 * @param message the message
	 */
	public void setViewMessage(final ExtTorrent extTorrent, final Enumerator message) {

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				switch (extTorrent.getStatus().getValue()) {

				case EViewsMessages.PAUSED_VALUE:
				case EViewsMessages.STOPPED_VALUE:
				case EViewsMessages.DISABLED_VALUE:
					break;

				default:
					extTorrent.setStatus(message);
					break;
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Force ext torrent state.
	 * @param extTorrent the ext torrent
	 * @param state the state
	 */
	public void forceExtTorrentState(final ExtTorrent extTorrent, final EViewsMessages state) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				extTorrent.setStatus(state);
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Check listen clients.
	 */
	private void checkListenClients() {
		if (listenClients) {
			if (!ListenClientsThread.getInstance().isAlive()) {
				ListenClientsThread.getInstance().start();
			}
		}
	}
}
