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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.REQUESTED_PIECES;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.ATransportBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.model.TransportPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class RandomTransportBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class RandomTransportBuilder extends ATransportBuilder {

	/** The random. */
	private final Random random = new Random();

	/**
	 * Instantiates a new random transport builder.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 * @param clientSession the client session
	 */
	public RandomTransportBuilder(ExtTorrent extTorrent, SwarmSession swarmSession, ClientSession clientSession) {
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
			processUnfinishedPieces(false);
			getPiecesFromHave();
			getPiecesFromClientBitfield();

			if (piecesList.isEmpty() && !swarmSession.getProcessedPieces().isEmpty()) {
				processUnfinishedPieces(true);
			}
		} catch (InterruptedException e) {
			Log.log(MODULE, e);
			tryAgain();
		} catch (ConcurrentModificationException e) {
			Log.log(MODULE, e);
			tryAgain();
		}
		return piecesList;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the pieces from client bitfield.
	 * @return the pieces from client bitfield
	 * @throws Exception the exception
	 */
	private void getPiecesFromClientBitfield() throws Exception {
		selectRandomPieces();
		selectSequencePieces();
	}

	// ---------------------------------------------------------------

	/**
	 * Select random pieces.
	 * @throws Exception the exception
	 */
	private void selectRandomPieces() throws Exception {
		byte[] clientBitfield = clientSession.getBitfield();
		boolean[] modelBitfield = extTorrent.getModelBitfield();

		for (int i = 0; (i < clientBitfield.length) && (piecesList.size() < REQUESTED_PIECES); i++) {

			int pieceIndex = random.nextInt(modelBitfield.length - 1);

			if (!modelBitfield[pieceIndex]) {

				if (isTrue(clientBitfield[pieceIndex / 8], pieceIndex % 8)) {
					tryAddPiece(pieceIndex);
				}
			}
		}
	}
}
