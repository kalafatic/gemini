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
package utils;

import static main.Main.TRANSPORT_BLOCK_SIZE;
import io.AIO;
import io.TReader;
import io.TWriter;

import java.util.Arrays;

import main.Main;
import model.Piece;
import net.ASession;
import net.Transporter;
import protocols.ETP;

/**
 * The Class MDecoder.
 */
public class MDecoder {

	/** The session. */
	private ASession session;

	/** The transporter. */
	private Transporter transporter;

	/** The len. */
	private int len;

	/** The incoming blocks. */
	public int incomingBlocks = 0;

	/**
	 * Instantiates a new m decoder.
	 * 
	 * @param aSession
	 *            the a session
	 * @param transporter
	 *            the transporter
	 */
	public MDecoder(ASession aSession, Transporter transporter) {
		this.session = aSession;
		this.transporter = transporter;
	}

	/**
	 * Decode message.
	 * 
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean decodeMessage() throws Exception {
		byte[] receivedInt = transporter.receiveInt();

		if (receivedInt.length == 0) {
			throw new Exception();
		}
		int len = Utils.decodeFourByteNumber(receivedInt);

		if (len < 0) {
			throw new Exception();
		} else if (len == 0) {
			session.keepAlive = true;
			return false;
		} else {
			decodeMessage(transporter.receiveByte());
			return true;
		}
	}

	/**
	 * Decode message.
	 * 
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void decodeMessage(int id) throws Exception {
		id = (id == 84) ? 10 : id;
		switch (ETP.values()[id]) {
		case CHOKE:
			session.choking = true;
			break;
		case UNCHOKE:
			session.choking = false;
			break;
		case INTERESTED:
			session.interested = true;
			break;
		case NOT_INTERESTED:
			session.interested = false;
			break;
		case HAVE:
			session.have.add(Utils.decodeFourByteNumber(transporter
					.receiveInt()));
			break;
		case BITFIELD:
			session.bitfield = transporter.receive(len - 1);
			break;
		case REQUEST:
			processRequest();
			break;
		case PIECE:
			processPiece();
			break;
		case CANCEL:
			processCancel();
			break;
		case PORT:
			processPort();
			break;
		case HANDSHAKE:
			processHandshake();
			break;
		default:
			throw new Exception();
		}
	}

	/**
	 * Process request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void processRequest() throws Exception {
		byte[] indexArray = transporter.receiveInt();
		int index = Utils.decodeFourByteNumber(indexArray);

		byte[] beginArray = transporter.receiveInt();
		int begin = Utils.decodeFourByteNumber(beginArray);

		int length = Utils.decodeFourByteNumber(transporter.receiveInt());

		int pieceLength = Main.torrent.pieceLength;
		long position = (index * pieceLength) + begin;

		byte[] data = TReader.readRAFDataFromDisc(position, length);

		transporter.send(Utils.encodeFourByteNumber(length + 9),
				new byte[] { 7 }, indexArray, beginArray, data);
	}

	/**
	 * Process piece.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void processPiece() throws Exception {
		int index = Utils.decodeFourByteNumber(transporter.receiveInt());
		int begin = Utils.decodeFourByteNumber(transporter.receiveInt());

		byte[] data = transporter.receive(len - 9);

		processPiece(index, begin, data);
	}

	/**
	 * Process piece.
	 * 
	 * @param index
	 *            the index
	 * @param begin
	 *            the begin
	 * @param data
	 *            the data
	 * @throws Exception
	 *             the exception
	 */
	private void processPiece(int index, int begin, byte[] data)
			throws Exception {

		Piece piece = Main.processed.get(index);

		piece.payload.position(begin);
		piece.payload.put(data);
		piece.blocks[begin / TRANSPORT_BLOCK_SIZE] = true;

		if (!Utils.contains(piece.blocks, false)) {
			TWriter.writeRAFPieceOnDisc(piece);
			Main.torrent.bitfield[index / 8] |= (1 << (7 - (index % 8)));
			AIO.writeBitfield(Main.torrent.bfFile, index, '1');
		}
		++incomingBlocks;
	}

	/**
	 * Process handshake.
	 * 
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	private boolean processHandshake() throws Exception {
		transporter.receive(28);
		byte[] infoHashArray = transporter.receive(20);
		return session.handshake = Arrays.equals(Main.torrent.hash,
				infoHashArray);
	}

	/**
	 * Process port.
	 */
	private void processPort() {
		// TODO Auto-generated method stub

	}

	/**
	 * Process cancel.
	 */
	private void processCancel() {
		// TODO Auto-generated method stub

	}
}
