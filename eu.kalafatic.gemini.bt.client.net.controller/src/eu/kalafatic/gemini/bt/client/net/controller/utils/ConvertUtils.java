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
package eu.kalafatic.gemini.bt.client.net.controller.utils;

import java.util.concurrent.locks.ReentrantLock;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Class class ConvertUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ConvertUtils extends ReentrantLock {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The hexes. */
	final String HEXES = "0123456789ABCDEF";

	/** The Constant INSTANCE. */
	public static final ConvertUtils INSTANCE = new ConvertUtils();

	/**
	 * Instantiates a new convert utils.
	 */
	public ConvertUtils() {
		super(true);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Sets the model bitfield.
	 * @param extTorrent the ext torrent
	 * @return the boolean[]
	 */
	public boolean[] setModelBitfield(ExtTorrent extTorrent) {
		extTorrent.getLock().writeLock().lock();
		try {
			int count = 0, completed = 0;
			byte[] realBitfield = extTorrent.getRealBitfield();
			boolean[] hasPiece = new boolean[realBitfield.length * 8];

			// TODO - DID NOT NOTICE OPPOSITE ORDER !!! (byte 1011.0001 /
			// 128,64,32...)
			for (int i = 0; i < realBitfield.length; i++) {
				int b = realBitfield[i] & 0xff;
				completed += (hasPiece[count++] = (b & 128) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 32) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 64) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 16) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 8) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 4) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 2) != 0) ? 1 : 0;
				completed += (hasPiece[count++] = (b & 1) != 0) ? 1 : 0;
			}
			boolean[] pieces = new boolean[extTorrent.getInfo().getPieces().length / 20];
			System.arraycopy(hasPiece, 0, pieces, 0, pieces.length);
			// transient
			extTorrent.setModelBitfield(pieces);
			extTorrent.getAdditionalInfo().setCompletedPieces(completed);
			return pieces;
		} finally {
			extTorrent.getLock().writeLock().unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Decode two byte number.
	 * @param portArray the port array
	 * @return the int
	 */
	public int decodeTwoByteNumber(byte[] portArray) {
		return (((portArray[0] & 0xff) << 8) | (portArray[1] & 0xff));
	}

	// ---------------------------------------------------------------

	/**
	 * Prints the binary.
	 * @param realByte the real byte
	 * @return the string
	 */
	public static String printBinary(int realByte) {
		String binaryString = Integer.toBinaryString(realByte);
		if (binaryString.length() > 8) {
			binaryString = binaryString.substring(binaryString.length() - 8, binaryString.length());
		} else {
			String bitmask = "00000000";
			bitmask += binaryString;
			binaryString = bitmask.substring(bitmask.length() - 8, bitmask.length());
		}
		System.err.print(binaryString);
		return binaryString;
	}
}
