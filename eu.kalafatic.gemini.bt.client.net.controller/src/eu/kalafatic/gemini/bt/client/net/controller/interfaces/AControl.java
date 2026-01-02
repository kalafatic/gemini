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
package eu.kalafatic.gemini.bt.client.net.controller.interfaces;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.MAX_UPLD_THREADS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.LogicUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class AControl.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AControl {

	/** The dwnld started. */
	public static boolean dwnldStarted = false;

	/** The can upload. */
	public static boolean canUpload = !dwnldStarted;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Check all finished.
	 */
	void checkAllFinished() {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(60 * 1000);

					if (NetworkModelManager.getInstance().getTorrents().getTorrentMap().isEmpty()) {
						dwnldStarted = false;
						canUpload = true;

						SoundPlayer.getInstance().play("audrey_all downloads_finished.wav");
					}
				} catch (InterruptedException e) {
					Log.log(MODULE, e);
				}
			};
		}.start();
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is rating ok.
	 * @param session the session
	 * @return true, if is rating ok
	 */
	public boolean isRatingOK(SwarmSession session) {
		int ratingPosition = LogicUtils.getInstance().getRatingPosition(session);

		if ((ratingPosition != EReturn.EXIT_VALUE) && (ratingPosition > MAX_UPLD_THREADS)) {
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is speed ok.
	 * @param extTorrent the ext torrent
	 * @return true, if is speed ok
	 */
	public boolean isSpeedOK(ExtTorrent extTorrent) {
		float ratioValue = Activator.getPreferences().getFloat(EBTClientPreferences.RATIO.getName(), (Float) EBTClientPreferences.RATIO.getDef());

		float allDownSpeed = AppData.getInstance().getAllDownSpeed();
		float allUpSpeed = AppData.getInstance().getAllUpSpeed();

		float dwnldSpeed = extTorrent.getAdditionalInfo().getDwnldSpeed();
		float upldSpeed = extTorrent.getAdditionalInfo().getUpldSpeed();

		if (ratioValue == 0) {
			// prefer all download first
			if (allDownSpeed < allUpSpeed) {
				return false;
			}
			// TODO - prefer swarm download ?
			if (!extTorrent.isFinished() && (dwnldSpeed < upldSpeed)) {
				return false;
			}
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param extTorrent the ext torrent
	 * @param session the session
	 * @return true, if successful
	 */
	public static boolean canContinue(ExtTorrent extTorrent, Session session) {
		return (canContinue(extTorrent) && canContinue(session));
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 * @return true, if successful
	 */
	public static boolean canContinue(ExtTorrent extTorrent, SwarmSession swarmSession) {
		return !(canContinue(extTorrent, (Session) swarmSession) && canContinue(swarmSession));
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param extTorrent the ext torrent
	 * @return true, if successful
	 */
	public static boolean canContinue(ExtTorrent extTorrent) {
		if ((extTorrent != null) && (extTorrent.isEnabled())) {
			return canContinue((EViewsMessages) extTorrent.getStatus());
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param session the session
	 * @return true, if successful
	 */
	private static boolean canContinue(Session session) {
		if (session != null) {
			return canContinue(session.getState());
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param swarmSession the swarm session
	 * @return true, if successful
	 */
	public static boolean canContinue(SwarmSession swarmSession) {
		return !swarmSession.isObtainingClients();
	}

	// ---------------------------------------------------------------

	/**
	 * Can continue.
	 * @param msg the msg
	 * @return true, if successful
	 */
	private static boolean canContinue(EViewsMessages msg) {
		switch (msg) {
		case PAUSED:
		case STOPPED:
		case SUSPENDED:
			return false;
		default:
			return true;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the average rating.
	 * @param collection the collection
	 * @return the average rating
	 */
	public static float getAverageRating(Collection<ClientSession> collection) throws Exception {
		List<?> sessions = new ArrayList<Object>(collection);
		float all = 0;
		for (Object object : sessions) {
			all += ((Session) object).getRating();
		}
		return all / sessions.size();
	}
}
