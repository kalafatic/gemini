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
package model;

import java.nio.ByteBuffer;

import main.Main;

/**
 * The Class Piece.
 */
public class Piece {

	/** The index. */
	public int index;
	
	/** The payload. */
	public ByteBuffer payload;
	
	/** The blocks. */
	public boolean[] blocks;

	/**
	 * Instantiates a new piece.
	 * 
	 * @param index
	 *            the index
	 */
	public Piece(int index) {
		this.index = index;
		if (index == (Main.torrent.bitfield.length - 1)) {
			this.payload = ByteBuffer
					.allocate((int) (Main.torrent.allLength % Main.torrent.pieceLength));
		} else {
			this.payload = ByteBuffer.allocate(Main.torrent.pieceLength);
		}
		int size = Main.torrent.pieceLength / Main.TRANSPORT_BLOCK_SIZE;
		size = Main.torrent.pieceLength % Main.TRANSPORT_BLOCK_SIZE > 0 ? size + 1
				: size;
		this.blocks = new boolean[size];
	}
}
