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
package eu.kalafatic.gemini.bt.client.net.controller.lib.constants;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.INTERFACES;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.models.NetInterface;

/**
 * The Class class FNetConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class FNetConstants {

	/** The Constant TRANSPORT_BUFFER. */
	public static final int TRANSPORT_BUFFER = Activator.getPreferences().getInt(EBTClientPreferences.TRANSPORT_BUFFER.getName(), (Integer) EBTClientPreferences.TRANSPORT_BUFFER.getDef());

	/** The Constant MAX_SWARM_THREADS. */
	public static final int MAX_SWARM_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_SWARM_THREADS.getName(), (Integer) EBTClientPreferences.MAX_SWARM_THREADS.getDef());

	/** The Constant MAX_TRACKER_THREADS. */
	public static final int MAX_TRACKER_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_TRACKER_THREADS.getName(), (Integer) EBTClientPreferences.MAX_TRACKER_THREADS.getDef());

	/** The Constant MAX_DWNLD_THREADS. */
	public static final int MAX_DWNLD_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());

	/** The Constant TRACKERS_DELAY. */
	public static final int TRACKERS_DELAY = Activator.getPreferences().getInt(EBTClientPreferences.TRACKERS_DELAY.getName(), (Integer) EBTClientPreferences.TRACKERS_DELAY.getDef());

	/** The Constant SPEED_DELAY. */
	public static final int SPEED_DELAY = Activator.getPreferences().getInt(EBTClientPreferences.SPEED_DELAY.getName(), (Integer) EBTClientPreferences.SPEED_DELAY.getDef());

	/** The Constant READ_TIMEOUT. */
	public static final int READ_TIMEOUT = Activator.getPreferences().getInt(EBTClientPreferences.READ_TIMEOUT.getName(), (Integer) EBTClientPreferences.READ_TIMEOUT.getDef());

	/** The Constant PAUSE_TIMEOUT. */
	public static final int PAUSE_TIMEOUT = Activator.getPreferences().getInt(EBTClientPreferences.PAUSE_TIMEOUT.getName(), (Integer) EBTClientPreferences.PAUSE_TIMEOUT.getDef());

	/** The Constant CLIENT_TIMEOUT. */
	public static final int CLIENT_TIMEOUT = Activator.getPreferences().getInt(EBTClientPreferences.CLIENT_TIMEOUT.getName(), (Integer) EBTClientPreferences.CLIENT_TIMEOUT.getDef());

	/** The Constant TRACKER_TIMEOUT. */
	public static final int TRACKER_TIMEOUT = Activator.getPreferences().getInt(EBTClientPreferences.TRACKER_TIMEOUT.getName(), (Integer) EBTClientPreferences.TRACKER_TIMEOUT.getDef());

	/** The Constant BT_CLIENT_PORT. */
	public static final int BT_CLIENT_PORT = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

	// ---------------------------------------------------------------

	/** The Constant MY_INTERFACES. */
	public static final List<String> MY_INTERFACES = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			for (NetInterface myInterface : INTERFACES) {

				for (String address : myInterface.getAddress()) {
					add("http://" + address + ":" + Integer.toString(BT_CLIENT_PORT));
				}
			}
		}
	};

	// ---------------------------------------------------------------

	/** The Constant TORRENT_PROGRESS_COMPARATOR. */
	public static final Comparator<ExtTorrent> TORRENT_PROGRESS_COMPARATOR = new Comparator<ExtTorrent>() {
		@Override
		public int compare(ExtTorrent t1, ExtTorrent t2) {
			long rating1 = t1.getAdditionalInfo().getDownloaded();
			long rating2 = t2.getAdditionalInfo().getDownloaded();
			return (rating1 < rating2 ? 1 : (rating1 == rating2 ? 0 : -1));
		}
	};

	// ---------------------------------------------------------------

	/** The Constant SESSION_RATING_COMPARATOR. */
	public static final Comparator<Session> SESSION_RATING_COMPARATOR = new Comparator<Session>() {
		@Override
		public int compare(Session s1, Session s2) {
			int rating1 = s1.getRating();
			int rating2 = s2.getRating();
			return (rating1 < rating2 ? 1 : (rating1 == rating2 ? 0 : -1));
		}
	};

	// ---------------------------------------------------------------

	/** The Constant SWARM_RATING_COMPARATOR_1. */
	public static final Comparator<SwarmSession> SWARM_RATING_COMPARATOR_1 = new Comparator<SwarmSession>() {
		@Override
		public int compare(SwarmSession ss1, SwarmSession ss2) {
			int rating1 = ss1.getRating();
			int rating2 = ss2.getRating();
			return (rating1 < rating2 ? 1 : (rating1 == rating2 ? 0 : -1));
		}
	};

	// ---------------------------------------------------------------

	/** The Constant SWARM_RATING_COMPARATOR_2. */
	public static final Comparator<SwarmSession> SWARM_RATING_COMPARATOR_2 = new Comparator<SwarmSession>() {
		@Override
		public int compare(SwarmSession ss1, SwarmSession ss2) {
			int rating1 = getAllSessionsRating(ss1);
			int rating2 = getAllSessionsRating(ss2);
			return (rating1 < rating2 ? 1 : (rating1 == rating2 ? 0 : -1));
		}

		private int getAllSessionsRating(SwarmSession session) {
			int rating = session.getRating();
			for (Session s : session.getTrackers().values()) {
				rating += s.getRating();
			}
			for (Session s : session.getDownloads().values()) {
				rating += s.getRating();
			}
			for (Session s : session.getUploads().values()) {
				rating += s.getRating();
			}
			return rating;
		}
	};

	// ---------------------------------------------------------------

	/** The Constant SWARM_PROGRESS_COMPARATOR. */
	public static final Comparator<SwarmSession> SWARM_PROGRESS_COMPARATOR = new Comparator<SwarmSession>() {
		@Override
		public int compare(SwarmSession ss1, SwarmSession ss2) {
			long rating1 = ss1.getSpeedContainer().getDwnldSize();
			long rating2 = ss2.getSpeedContainer().getDwnldSize();
			return (rating1 < rating2 ? 1 : (rating1 == rating2 ? 0 : -1));
		}
	};

}
