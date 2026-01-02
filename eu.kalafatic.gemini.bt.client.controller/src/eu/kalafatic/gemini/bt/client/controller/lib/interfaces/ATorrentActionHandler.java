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

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.jface.viewers.TableViewer;

import eu.kalafatic.gemini.bt.client.controller.utils.TorrentTableUtil;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class ATorrentActionHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public abstract class ATorrentActionHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the selected torrents.
	 * @param tableViewer the table viewer
	 * @param enabled the enabled
	 * @param msg the msg
	 * @return the selected torrents
	 */
	protected List<ExtTorrent> getSelectedTorrents(TableViewer tableViewer, boolean enabled, EViewsMessages msg) {

		List<ExtTorrent> selected = TorrentTableUtil.getInstance().getSelected(tableViewer);

		for (ExtTorrent extTorrent : selected) {
			extTorrent.setEnabled(enabled);
			extTorrent.setStatus(msg);
			ModelUtils.doSave(extTorrent);
		}
		return selected;
	}

}
