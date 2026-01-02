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
package eu.kalafatic.gemini.bt.client.net.controller.model;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.MAX_TRACKER_THREADS;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.SESSION_RATING_COMPARATOR;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AControl;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.NetworkUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackersManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("unchecked")
public class TrackersManager extends AControl {

	/** The swarm session. */
	private final SwarmSession swarmSession;

	/** The ext torrent. */
	private final ExtTorrent extTorrent;

	/** The pooled threads. */
	private final PooledThreads pooledThreads;

	/** The first call. */
	private static boolean firstCall = true;

	/**
	 * Instantiates a new trackers manager.
	 * @param swarmSession the swarm session
	 * @param pooledThreads the pooled threads
	 */
	public TrackersManager(SwarmSession swarmSession, PooledThreads pooledThreads) {
		this.swarmSession = swarmSession;
		this.pooledThreads = pooledThreads;

		extTorrent = (ExtTorrent) swarmSession.getTorrent();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run trackers.
	 */
	public void runTrackers() {
		if (canContinue(swarmSession)) {
			swarmSession.setObtainingClients(true);
			Log.log(EBTClientPreferences.MODULE, LOG + "OBTAIN CLIENTS");

			new Thread(new Runnable() {
				@Override
				public void run() {
					connectTrackers();
				}
			}).start();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Connect trackers.
	 */

	private void connectTrackers() {
		try {
			// persistent peers (saved in model)
			if (firstCall) {
				firstCall = false;
				swarmSession.getUploads().clear();
				swarmSession.getClients().addAll(swarmSession.getDownloads().keySet());
				swarmSession.getDownloads().clear();

				if (swarmSession.getClients().isEmpty()) {
					runTrackerHandlers();
				}
			} else {
				runTrackerHandlers();
			}
			searchForClients();

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		} finally {
			swarmSession.setObtainingClients(false);
			Log.log(EBTClientPreferences.MODULE, LOG + "UNLOCK TRACKERS");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run tracker for update.
	 */
	public void runTrackerForUpdate() {
		if (canContinue(swarmSession)) {
			swarmSession.setObtainingClients(true);
			new Thread() {
				@Override
				public void run() {
					runTrackerHandlers();
				}
			}.start();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run tracker handlers.
	 */
	public void runTrackerHandlers() {
		try {
			if (canContinue(extTorrent)) {
				NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.CONNECTING_TRACKERS);

				List<Session> trackerSessions = new ArrayList<Session>(swarmSession.getTrackers().values());
				Collections.sort(trackerSessions, SESSION_RATING_COMPARATOR);

				TrackerSession session = null;

				for (int i = 0, j = 0, r = -1; (i < trackerSessions.size()) && (j < MAX_TRACKER_THREADS); i++, r = -1) {
					try {
						session = (TrackerSession) trackerSessions.get(i);

						if (startTrackerThread(session)) {
							j++;
							r = 1;
						}
					} catch (Exception e) {
						Log.log(EBTClientPreferences.MODULE, e);
						NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.CAN_NOT_CONNECT_TRACKER);
					}
					NetworkUtils.changeRating(swarmSession, session, r);
				}
				NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.TRACKERS_FINISHED);
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Search for clients.
	 * @throws Exception the exception
	 */
	private void searchForClients() throws Exception {
		if (canContinue(extTorrent)) {

			if (swarmSession.getClients().isEmpty()) {
				swarmSession.setRating(swarmSession.getRating() - 1);

				if (swarmSession.getDownloads().isEmpty()) {
					NetworkModelManager.getInstance().removeFromActiveSwarms(swarmSession.getAnnounce(), -1);
				}
				return;
			}
			if (extTorrent.isFinished()) {
				swarmSession.getClients().clear();
				swarmSession.getDownloads().clear();
			} else {
				if (dwnldStarted) {
					pooledThreads.getSearchHandler().run();
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Start tracker thread.
	 * @param trackerSession the tracker session
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean startTrackerThread(TrackerSession trackerSession) throws Exception {
		if (trackerSession.getState().equals(EViewsMessages.SUSPENDED)) {
			trackerSession.setNextConnection(System.currentTimeMillis() + (300 * 1000));
			trackerSession.setState(EViewsMessages.READY);

		} else if (isTimeToConnectTracker(trackerSession)) {
			pooledThreads.getTrackerHandler().runTrackerHandler(trackerSession);
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is time to connect tracker.
	 * @param trackerSession the tracker session
	 * @return true, if is time to connect tracker
	 */
	private boolean isTimeToConnectTracker(TrackerSession trackerSession) {
		try {
			if (trackerSession.getResponse() == null) {
				return true;
			}
			long interval = trackerSession.getResponse().getInterval();
			long nextConnection = trackerSession.getNextConnection();
			long currentTime = System.currentTimeMillis();

			if (currentTime > nextConnection) {
				trackerSession.setNextConnection(currentTime + interval);
				return true;
			}
		} catch (NullPointerException e) {
			Log.log(EBTClientPreferences.MODULE, e);
			return true; // first time
		}
		return false;
	}
}
