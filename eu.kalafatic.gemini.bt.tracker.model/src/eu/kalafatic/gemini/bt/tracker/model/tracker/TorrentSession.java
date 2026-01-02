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

import org.eclipse.emf.common.util.EMap;

/**
 * The Interface interface TorrentSession.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface TorrentSession extends Session {

	/**
	 * Gets the torrent name.
	 * @return the torrent name
	 */
	String getTorrentName();

	/**
	 * Sets the torrent name.
	 * @param value the new torrent name
	 */
	void setTorrentName(String value);

	/**
	 * Gets the complete.
	 * @return the complete
	 */
	int getComplete();

	/**
	 * Sets the complete.
	 * @param value the new complete
	 */
	void setComplete(int value);

	/**
	 * Gets the incomplete.
	 * @return the incomplete
	 */
	int getIncomplete();

	/**
	 * Sets the incomplete.
	 * @param value the new incomplete
	 */
	void setIncomplete(int value);

	/**
	 * Gets the torrent len.
	 * @return the torrent len
	 */
	long getTorrentLen();

	/**
	 * Sets the torrent len.
	 * @param value the new torrent len
	 */
	void setTorrentLen(long value);

	/**
	 * Gets the downloaded.
	 * @return the downloaded
	 */
	int getDownloaded();

	/**
	 * Sets the downloaded.
	 * @param value the new downloaded
	 */
	void setDownloaded(int value);

	/**
	 * Gets the client map.
	 * @return the client map
	 */
	EMap<String, Session> getClientMap();

	/**
	 * Gets the torrent path.
	 * @return the torrent path
	 */
	String getTorrentPath();

	/**
	 * Sets the torrent path.
	 * @param value the new torrent path
	 */
	void setTorrentPath(String value);

} // TorrentSession
