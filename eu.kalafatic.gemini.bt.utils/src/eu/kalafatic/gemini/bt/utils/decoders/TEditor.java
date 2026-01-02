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
package eu.kalafatic.gemini.bt.utils.decoders;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ANNOUNCE_LIST;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.core.utils.FileUtils;

/**
 * The Class class TEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TEditor extends ATEditor {

	/** The Constant INSTANCE. */
	public final static TEditor INSTANCE = new TEditor();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Edits the trackers.
	 * @param extTorrent the ext torrent
	 * @return true, if successful
	 */
	public boolean editTrackers(ExtTorrent extTorrent) {
		try {
			File file = new File(extTorrent.getPath());

			if (file.exists()) {
				Map map = getTorrentMap(extTorrent, file);

				if (map.containsKey(ANNOUNCE_LIST)) {

					List<byte[]> announceList = new ArrayList<byte[]>();

					for (String announce : extTorrent.getAnnounceList()) {
						announceList.add(announce.getBytes());
					}
					map.put(ANNOUNCE_LIST, announceList);

					byte[] encoded = BEncoder.encode(map);

					FileUtils.getInstance().writeFile(file, encoded);

					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
