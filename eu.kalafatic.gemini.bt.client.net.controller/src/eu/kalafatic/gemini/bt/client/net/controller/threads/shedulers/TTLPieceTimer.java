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
package eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.core.lib.EColor;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TTLPieceTimer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TTLPieceTimer extends Timer {

	/** The processed. */
	EMap<Integer, IOPiece> processed;

	/**
	 * Instantiates a new TTL piece timer.
	 * @param processed the processed
	 * @param piece the piece
	 */
	public TTLPieceTimer(EMap<Integer, IOPiece> processed, IOPiece piece) {
		this.processed = processed;
		schedule(new ProgressTask(this, piece), 20000, 10000);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class ProgressTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public class ProgressTask extends TimerTask {

		/** The ttl piece timer. */
		TTLPieceTimer ttlPieceTimer;

		/** The piece. */
		IOPiece piece;

		/**
		 * Instantiates a new progress task.
		 * @param ttlPieceTimer the ttl piece timer
		 * @param piece the piece
		 */
		public ProgressTask(TTLPieceTimer ttlPieceTimer, IOPiece piece) {
			this.ttlPieceTimer = ttlPieceTimer;
			this.piece = piece;
			piece.setLastActivity(System.currentTimeMillis());
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			if ((piece == null) || piece.isFinished()) {
				ttlPieceTimer.cancel();
			} else {
				int delaySeconds = (int) ((System.currentTimeMillis() - piece.getLastActivity()) / 1000);

				// non active 120s reset
				if (delaySeconds > 120) {
					unlockAll();
					// non active 20s warning
				} else if (delaySeconds > 30) {
					piece.setColor(EColor.LOCK.value);
				}
			}
		}

		// ---------------------------------------------------------------

		/**
		 * Unlock all.
		 */
		private void unlockAll() {
			if (isWorthIt(piece)) {
				Log.log(MODULE, LOG + "UNLOCK ALL " + piece.getPieceIndex());
				Arrays.fill(piece.getLockedBlocks(), false);
				piece.setColor(EColor.ERROR.value);
				piece.setLastActivity(System.currentTimeMillis());
			} else {
				Log.log(MODULE, LOG + "REMOVE PIECE " + piece.getPieceIndex());
				synchronized (processed) {
					processed.remove(Integer.toString(piece.getPieceIndex()));
				}
				piece = null;
				ttlPieceTimer.cancel();
			}
		}

		// ---------------------------------------------------------------

		/**
		 * Checks if is worth it.
		 * @param piece the piece
		 * @return true, if is worth it
		 */
		private boolean isWorthIt(IOPiece piece) {
			piece.getLock().readLock().tryLock();
			try {
				boolean[] blocks = piece.getBlocks();
				for (int i = 0; i < blocks.length; i++) {
					if (blocks[i]) {
						return true;
					}
				}
				return false;
			} finally {
				piece.getLock().readLock().unlock();
			}
		}
	}
}
