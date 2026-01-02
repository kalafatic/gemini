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
package eu.kalafatic.gemini.bt.client.net.controller.factories;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.CANCEL;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.HAVE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.PEER_ID;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.PROTOCOL;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.PROTOCOL_LEN;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.REQUEST;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.RESERVED;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * A factory for creating Transport objects.
 */
public class TransportFactory {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	// swarm acceptable common bottleneck
	/**
	 * Sets the and get handshake.
	 * @param extTorrent the ext torrent
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] setAndGetHandshake(ExtTorrent extTorrent) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(68);
		baos.write(PROTOCOL_LEN);
		baos.write(PROTOCOL);
		baos.write(RESERVED);
		baos.write(extTorrent.getInfo().getHash());
		baos.write(PEER_ID.getBytes());

		byte[] handshake = baos.toByteArray();
		extTorrent.setHandshake(handshake);
		return handshake;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the have.
	 * @param pieceBuffer the piece buffer
	 * @return the byte[]
	 */
	public static byte[] setHave(WriteBuffer pieceBuffer) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			if (pieceBuffer.getLock().readLock().tryLock(500, TimeUnit.MILLISECONDS)) {
				try {
					for (IOPiece piece : pieceBuffer.getPieces()) {
						baos.write(HAVE);
						baos.write(encodeFourByteNumber(piece.getPieceIndex()));
					}
				} finally {
					pieceBuffer.getLock().readLock().unlock();
				}
				return baos.toByteArray();
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the have.
	 * @param extTorrent the ext torrent
	 * @return the have
	 */
	public static byte[] getHave(ExtTorrent extTorrent) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			boolean[] modelBitfield = extTorrent.getModelBitfield();
			int i = new Random().nextInt(modelBitfield.length - 1);

			for (int j = 0; (i < modelBitfield.length) && (j < 8); i++) {
				if (modelBitfield[i]) {
					baos.write(HAVE);
					baos.write(encodeFourByteNumber(i));
					j++;
				}
			}
		} catch (IOException e) {
			Log.log(MODULE, e);
		}
		return baos.toByteArray();
	}

	// ---------------------------------------------------------------

	// swarm acceptable common bottleneck
	/**
	 * Sets the request.
	 * @param pieceIndex the piece index
	 * @param begin the begin
	 * @param length the length
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] setRequest(int pieceIndex, int begin, int length) throws IOException {
		ByteBuffer requestBuffer = ByteBuffer.allocate(17);
		requestBuffer.put(REQUEST);
		requestBuffer.put(encodeFourByteNumber(pieceIndex));
		requestBuffer.put(encodeFourByteNumber(begin));
		requestBuffer.put(encodeFourByteNumber(length));
		requestBuffer.hasArray();
		return requestBuffer.array();
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the cancel.
	 * @param pieceIndex the piece index
	 * @param begin the begin
	 * @param length the length
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] setCancel(int pieceIndex, int begin, int length) throws IOException {
		ByteBuffer cancelBuffer = ByteBuffer.allocate(17);
		cancelBuffer.put(CANCEL);
		cancelBuffer.put(encodeFourByteNumber(pieceIndex));
		cancelBuffer.put(encodeFourByteNumber(begin));
		cancelBuffer.put(encodeFourByteNumber(length));
		cancelBuffer.hasArray();
		return cancelBuffer.array();
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up port.
	 * @param port the port
	 * @return the byte[]
	 */
	public static byte[] setUpPort(int port) {
		byte[] portArray = new byte[2];
		portArray[0] = port > 255 ? (byte) (port / 256) : 0;
		portArray[1] = (byte) (port % 256);

		return portArray;
	}

	// ---------------------------------------------------------------

	// unsigned right shift
	/**
	 * Encode four byte number.
	 * @param i the i
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] encodeFourByteNumber(int i) throws IOException {
		return new byte[] { (byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i };
	}
}
