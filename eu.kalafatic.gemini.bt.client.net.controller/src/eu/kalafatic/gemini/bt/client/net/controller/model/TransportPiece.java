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
package eu.kalafatic.gemini.bt.client.net.controller.model;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.BLOCKS_IN_REQUEST;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.REQUESTS;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.ArrayList;
import java.util.List;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.core.lib.EColor;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TransportPiece.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TransportPiece {

	/** The lock. */
	public static int lock = REQUESTS * BLOCKS_IN_REQUEST;

	/** The piece. */
	public IOPiece piece;

	/** The processed. */
	public List<Integer> processed = new ArrayList<Integer>();

	/**
	 * Instantiates a new transport piece.
	 * @param piece the piece
	 */
	public TransportPiece(IOPiece piece) {
		this.piece = piece;

		piece.setLastActivity(System.currentTimeMillis());
		piece.setColor(EColor.DWNLD.value);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Lock.
	 * @return true, if successful
	 */
	public boolean lock() {
		processed.clear();

		if (piece.getLock().readLock().tryLock()) {
			try {
				boolean[] lockedBlocks = piece.getLockedBlocks();
				int i = 0, j = 0;

				for (; (i < lockedBlocks.length) && (j < lock); i++) {
					if (!piece.getBlocks()[i] && !lockedBlocks[i]) {
						lockedBlocks[i] = true;
						processed.add(i);
						j++;
					}
				}
				return j > 0;

			} finally {
				piece.getLock().readLock().unlock();
			}
		} else {
			return false;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Unlock.
	 */
	public void unlock() {
		Log.log(MODULE, LOG + "UNLOCK ALL " + piece.getPieceIndex());
		piece.getLock().readLock().tryLock();
		try {
			boolean[] lockedBlocks = piece.getLockedBlocks();

			for (Integer i : processed) {
				lockedBlocks[i] = false;
			}
		} finally {
			piece.getLock().readLock().unlock();
		}
	}
}
