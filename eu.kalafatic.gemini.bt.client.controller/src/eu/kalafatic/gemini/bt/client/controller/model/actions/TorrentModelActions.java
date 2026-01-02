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
package eu.kalafatic.gemini.bt.client.controller.model.actions;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.io.File;
import java.util.Arrays;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.bt.utils.decoders.TDecoder;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class TorrentModelActions.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentModelActions {

	/** The Constant INSTANCE. */
	public static final TorrentModelActions INSTANCE = new TorrentModelActions();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Adds the torrent to model.
	 * @param torrentPath the torrent path
	 * @param finished the finished
	 * @return true, if successful
	 */

	public boolean addTorrentToModel(String torrentPath, boolean finished) {
		ExtTorrent extTorrent = null;
		try {
			EMap<String, ExtTorrent> torrentMap;

			extTorrent = TDecoder.INSTANCE.decode(new File(torrentPath), finished);
			extTorrent.setEnabled(true);

			if (finished) {
				torrentMap = ClientModelManager.getInstance().getTorrents().getFinishedTorrentsMap();
				setUpFinishedTorrent(extTorrent);

			} else {
				torrentMap = ClientModelManager.getInstance().getTorrents().getTorrentMap();
				extTorrent.setStatus(EViewsMessages.READY);
			}

			if (torrentMap.containsKey(extTorrent.getName())) {
				DialogUtils.INSTANCE.warn("Torrent already exists !");
				return false;
			}

			torrentMap.put(extTorrent.getName(), extTorrent);
			ModelUtils.doSave(extTorrent);

			Log.log(EBTClientPreferences.MODULE, LOG + "TORRENT ADDED " + extTorrent.getName());

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up finished torrent.
	 * @param extTorrent the new up finished torrent
	 */
	private void setUpFinishedTorrent(ExtTorrent extTorrent) {
		extTorrent.setFinished(true);
		extTorrent.getAdditionalInfo().setCompleted(100);
		extTorrent.setStatus(EViewsMessages.READY_TO_UPLOAD);
		Arrays.fill(extTorrent.getModelBitfield(), true);
	}
}
