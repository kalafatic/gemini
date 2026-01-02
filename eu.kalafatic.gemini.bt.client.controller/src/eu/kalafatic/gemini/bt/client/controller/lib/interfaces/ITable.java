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
package eu.kalafatic.gemini.bt.client.controller.lib.interfaces;

import org.eclipse.swt.widgets.Table;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Interface interface ITable.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface ITable {

	/**
	 * Gets the table.
	 * @return the table
	 */
	public Table getTable();

	/**
	 * Gets the ext torrent.
	 * @return the ext torrent
	 */
	public ExtTorrent getExtTorrent();
}
