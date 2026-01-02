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
package eu.kalafatic.gemini.bt.client.net.controller.utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class NetworkUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetworkUtils {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the socket address.
	 * @param announce the announce
	 * @return the socket address
	 * @throws MalformedURLException the malformed url exception
	 * @throws UnknownHostException the unknown host exception
	 */
	public static SocketAddress getSocketAddress(String announce) throws MalformedURLException, UnknownHostException {

		URL url = new URL(announce);
		InetAddress address = InetAddress.getByName(url.getHost());
		return new InetSocketAddress(address, url.getPort());
	}

	// ---------------------------------------------------------------

	/**
	 * Change rating.
	 * @param swarmSession the swarm session
	 * @param session the session
	 * @param rating the rating
	 */
	public static void changeRating(Session swarmSession, Session session, int rating) {
		try {
			swarmSession.setRating(swarmSession.getRating() + rating);
			session.setRating(session.getRating() + rating);
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

}
