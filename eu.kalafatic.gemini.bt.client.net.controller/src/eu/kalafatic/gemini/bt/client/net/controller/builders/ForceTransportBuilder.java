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
package eu.kalafatic.gemini.bt.client.net.controller.builders;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.ATransportBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.model.TransportPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ForceTransportBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ForceTransportBuilder extends ATransportBuilder {

	/**
	 * Instantiates a new force transport builder.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 * @param clientSession the client session
	 */
	public ForceTransportBuilder(ExtTorrent extTorrent, SwarmSession swarmSession, ClientSession clientSession) {
		super(extTorrent, swarmSession, clientSession);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransportBuilder #getTransportPieces()
	 */
	@Override
	public List<TransportPiece> getTransportPieces() throws Exception {
		piecesList = new ArrayList<TransportPiece>();
		try {
			List<Integer> list = extTorrent.getAdditionalInfo().getVerifyList();
			for (Integer index : list) {
				DataFile dataFile = getPieceDataFile(extTorrent, index);
				IOPiece piece = createProcessedPiece(extTorrent, swarmSession, dataFile, index);
				piecesList.add(new TransportPiece(piece));
			}
		} catch (ConcurrentModificationException e) {
			Log.log(MODULE, e);
			tryAgain();
		}
		return piecesList;
	}
}
