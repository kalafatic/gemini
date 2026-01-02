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
package eu.kalafatic.gemini.core.lib;

/**
 * The Enum enum EHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EHandler {

	/** The run rc. */
	RUN_RC("eu.kalafatic.gemini.bt.client.net.rc.controller.handlers.RunRCHandler"),

	/** The run torrents. */
	RUN_TORRENTS("eu.kalafatic.gemini.bt.client.controller.handlers.RunTorrentsHandler"),

	/** The run torrents net. */
	RUN_TORRENTS_NET("eu.kalafatic.gemini.bt.client.net.controller.handlers.RunTorrentsHandler"),

	/** The init torrents model. */
	INIT_TORRENTS_MODEL("eu.kalafatic.gemini.bt.client.controller.handlers.InitTorrentModelHandler"),

	/** The add torrent. */
	ADD_TORRENT("eu.kalafatic.gemini.bt.client.controller.handlers.AddTorrentHandler"),

	/** The create torrent. */
	CREATE_TORRENT("eu.kalafatic.gemini.bt.tm.controller.handlers.CreateTorrentHandler"),

	/** The open. */
	OPEN("eu.kalafatic.gemini.bt.client.controller.handlers.OpenHandler"),

	/** The run int comm. */
	RUN_INT_COMM("eu.kalafatic.gemini.core.handlers.RunInternalCommunicationHandler"),

	;

	/** The id. */
	public String ID;

	/**
	 * Instantiates a new e handler.
	 * @param index the index
	 * @param id the id
	 */
	private EHandler(String id) {
		this.ID = id;
	}
}
