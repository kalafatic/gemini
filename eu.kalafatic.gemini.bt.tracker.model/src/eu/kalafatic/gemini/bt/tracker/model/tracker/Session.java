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
package eu.kalafatic.gemini.bt.tracker.model.tracker;

import java.nio.channels.SocketChannel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages;

/**
 * The Interface interface Session.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Session extends EObject {

	/**
	 * Gets the address.
	 * @return the address
	 */
	String getAddress();

	/**
	 * Sets the address.
	 * @param value the new address
	 */
	void setAddress(String value);

	/**
	 * Gets the channel.
	 * @return the channel
	 */
	SocketChannel getChannel();

	/**
	 * Sets the channel.
	 * @param value the new channel
	 */
	void setChannel(SocketChannel value);

	/**
	 * Gets the request map.
	 * @return the request map
	 */
	EMap<String, String> getRequestMap();

	/**
	 * Gets the communication.
	 * @return the communication
	 */
	EList<Communication> getCommunication();

	/**
	 * Gets the session state.
	 * @return the session state
	 */
	EViewMessages getSessionState();

	/**
	 * Sets the session state.
	 * @param value the new session state
	 */
	void setSessionState(EViewMessages value);

	/**
	 * Gets the peer id.
	 * @return the peer id
	 */
	String getPeerId();

	/**
	 * Sets the peer id.
	 * @param value the new peer id
	 */
	void setPeerId(String value);

	/**
	 * Gets the note.
	 * @return the note
	 */
	String getNote();

	/**
	 * Sets the note.
	 * @param value the new note
	 */
	void setNote(String value);

	/**
	 * Gets the info hash.
	 * @return the info hash
	 */
	String getInfoHash();

	/**
	 * Sets the info hash.
	 * @param value the new info hash
	 */
	void setInfoHash(String value);

	/**
	 * Gets the listen port.
	 * @return the listen port
	 */
	int getListenPort();

	/**
	 * Sets the listen port.
	 * @param value the new listen port
	 */
	void setListenPort(int value);

	/**
	 * Gets the torrent.
	 * @return the torrent
	 */
	Object getTorrent();

	/**
	 * Sets the torrent.
	 * @param value the new torrent
	 */
	void setTorrent(Object value);

} // Session
