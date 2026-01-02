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
package net;

import java.nio.channels.SocketChannel;

import main.Main;
import model.Piece;
import protocols.ETP;
import utils.Utils;

/**
 * The Class DSession.
 */
public class DSession extends ASession {

	/** The offset. */
	int offset = 0;

	/**
	 * Instantiates a new d session.
	 * 
	 * @param channel
	 *            the channel
	 * @throws Exception
	 *             the exception
	 */
	public DSession(SocketChannel channel) throws Exception {
		super(channel, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			sendHandshake();
			if (handshake && interested()) {
				while (interested && ((!have.isEmpty()) || (bitfield != null))) {
					processDownload();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * Interested.
	 * 
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	private boolean interested() throws Exception {
		try {
			for (; offset < Main.torrent.bitfield.length;) {
				byte b = Main.torrent.bitfield[offset];
				if (b == 0) {
					transporter.send(ETP.INTERESTED.header);
					return interested = true;
				}
				offset++;
			}
			transporter.send(ETP.NOT_INTERESTED.header);
			return interested = false;
		} finally {
			mDecoder.decodeMessage();
		}

	}

	/**
	 * Process download.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void processDownload() throws Exception {
		Piece piece = null;

		if (have.isEmpty()) {
			for (; offset < Main.torrent.bitfield.length; offset++) {
				byte mine = Main.torrent.bitfield[offset];
				byte peers = bitfield[offset];

				for (int i = 0; i < 8; i++) {
					if (!isTrue(mine, i % 8) && isTrue(peers, i % 8)) {
						int index = offset + (i % 8);
						if (!Main.processed.containsKey(index)) {
							piece = new Piece(index);
							break;
						}
					}
				}
			}
		} else {
			for (int i = 0; (i < have.size()); i++) {
				if (interested(have.get(i))
						&& Main.processed.containsKey(have.get(i))) {
					int index = have.remove(i--);
					if (!Main.processed.containsKey(index)) {
						piece = new Piece(index);
						break;
					}
				}
			}
		}
		if (piece == null) {
			interested = false;
		} else {
			processDownload(Main.processed.put(piece.index, piece));
		}
	}

	/**
	 * Process download.
	 * 
	 * @param piece
	 *            the piece
	 * @throws Exception
	 *             the exception
	 */
	private void processDownload(Piece piece) throws Exception {
		int requested = 0;
		baos.reset();

		for (int i = 0; i < piece.blocks.length; i++) {

			int begin = i * Main.TRANSPORT_BLOCK_SIZE;

			if ((begin + Main.TRANSPORT_BLOCK_SIZE) <= piece.payload.capacity()) {
				lengthArray = Main.DEF_LENGTH;
			} else {
				lengthArray = Utils.encodeFourByteNumber(piece.payload
						.capacity() - begin);
			}

			baos.write(ETP.REQUEST.header);
			baos.write(Utils.encodeFourByteNumber(piece.index));
			baos.write(Utils.encodeFourByteNumber(begin));
			baos.write(lengthArray);

			if ((++requested) >= Main.BLOCKS_IN_REQUEST) {
				processDownload(requested);
				baos.reset();
				requested = 0;
			}
		}
		if (baos.size() > 0) {
			processDownload(requested);
		}
	}

	/**
	 * Process download.
	 * 
	 * @param expectedBlocks
	 *            the expected blocks
	 * @throws Exception
	 *             the exception
	 */
	public void processDownload(int expectedBlocks) throws Exception {
		mDecoder.incomingBlocks = 0;
		transporter.send(baos.toByteArray());
		do {
			mDecoder.decodeMessage();
		} while (expectedBlocks > mDecoder.incomingBlocks);
	}

	/**
	 * Interested.
	 * 
	 * @param i
	 *            the i
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean interested(int i) throws Exception {
		return isTrue(Main.torrent.bitfield[i / 8], i % 8);
	}

	/**
	 * Checks if is true.
	 * 
	 * @param b
	 *            the b
	 * @param pos
	 *            the pos
	 * @return true, if is true
	 */
	public boolean isTrue(byte b, int pos) {
		int bitmask = 1 << (7 - pos);
		return (b & bitmask) != 0;
	}
}
