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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc;

import java.net.Socket;

import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface RCSession.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface RCSession extends EObject {

	/**
	 * Gets the host.
	 * @return the host
	 */
	String getHost();

	/**
	 * Sets the host.
	 * @param value the new host
	 */
	void setHost(String value);

	/**
	 * Gets the announce.
	 * @return the announce
	 */
	String getAnnounce();

	/**
	 * Sets the announce.
	 * @param value the new announce
	 */
	void setAnnounce(String value);

	/**
	 * Gets the socket.
	 * @return the socket
	 */
	Socket getSocket();

	/**
	 * Sets the socket.
	 * @param value the new socket
	 */
	void setSocket(Socket value);

	/**
	 * Gets the state.
	 * @return the state
	 */
	ERCSessionState getState();

	/**
	 * Sets the state.
	 * @param value the new state
	 */
	void setState(ERCSessionState value);

	/**
	 * Gets the torrents.
	 * @return the torrents
	 */
	ERCCMD getTorrents();

	/**
	 * Sets the torrents.
	 * @param value the new torrents
	 */
	void setTorrents(ERCCMD value);

	/**
	 * Gets the http exchange.
	 * @return the http exchange
	 */
	Object getHttpExchange();

	/**
	 * Sets the http exchange.
	 * @param value the new http exchange
	 */
	void setHttpExchange(Object value);

} // RCSession
