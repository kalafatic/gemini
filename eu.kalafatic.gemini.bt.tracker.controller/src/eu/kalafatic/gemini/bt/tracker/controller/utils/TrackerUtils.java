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
package eu.kalafatic.gemini.bt.tracker.controller.utils;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.utils.utils.BTUtils;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerUtils {

	/** The Constant INSTANCE. */
	public static final TrackerUtils INSTANCE = new TrackerUtils();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the client address.
	 * @return the client address
	 */
	public String getClientAddress() {
		String announce = "";
		try {
			int port = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

			String hostName = InetAddress.getLocalHost().getHostName();

			announce = hostName + ":" + Integer.toString(port);

		} catch (UnknownHostException e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return announce;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tracker announce.
	 * @return the tracker announce
	 */
	public String getTrackerAnnounce() {
		try {
			int port = PREFERENCES.getInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());
			String hostName = InetAddress.getLocalHost().getHostName();

			return BTUtils.INSTANCE.parse(hostName, port);

		} catch (UnknownHostException e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return "";
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the clients.
	 * @param eMap the e map
	 * @param seeds the seeds
	 * @return the clients
	 */
	public int getClients(EMap<String, Session> eMap, boolean seeds) {
		int count = 0;
		for (Session session : eMap.values()) {
			if (seeds && ((ClientSession) session).isSeed()) {
				count++;
			} else {
				count++;
			}
		}
		return count;
	}
}
