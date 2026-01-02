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
package eu.kalafatic.gemini.bt.client.net.controller.decoders;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.MSG_INC;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AMessageDecoder;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClientTransportProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class MessageDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MessageDecoder extends AMessageDecoder {

	/**
	 * Instantiates a new message decoder.
	 * @param handler the handler
	 */
	public MessageDecoder(AClientHandler handler) {
		super(handler);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	// don't know what's coming
	/**
	 * Decode messages.
	 * @return true, if successful
	 */
	public boolean decodeMessages() {
		EReturn returnCode;
		do {
			returnCode = decodeMessage();
		} while ((returnCode == EReturn.OK) && (transporter.byteBufferRest.remaining() > 0));

		return returnCode != EReturn.ERROR;
	}

	// ---------------------------------------------------------------

	// know number of requested blocks
	/**
	 * Decode messages.
	 * @param expectedBlocks the expected blocks
	 * @return true, if successful
	 */
	public boolean decodeMessages(int expectedBlocks) {
		EReturn returnCode;
		incomingBlocks = 0;
		do {
			returnCode = decodeMessage();
		} while ((returnCode == EReturn.OK) && (expectedBlocks > incomingBlocks));

		return returnCode != EReturn.ERROR;
	}

	// ---------------------------------------------------------------

	// expect one specific response with force
	/**
	 * Decode messages.
	 * @param expectedID the expected id
	 * @param force the force
	 * @return true, if successful
	 */
	public boolean decodeMessages(int expectedID, boolean force) {
		if (force) {
			// response MUST BE expectedID
			return ((decodeMessage() == EReturn.OK) && (expectedID == id));
		}
		EReturn returnCode;
		boolean satisfied = false;
		do {
			returnCode = decodeMessage();

			if (expectedID == id) {
				satisfied = true;
			}
		} while ((returnCode == EReturn.OK) && (transporter.byteBufferRest.remaining() > 0));

		return satisfied && (returnCode != EReturn.ERROR);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #decodeMessage()
	 */
	@Override
	public EReturn decodeMessage() {
		try {
			byte[] receivedInt = transporter.receiveInt();

			if (receivedInt.length == 0) {
				return EReturn.EXIT;
			}
			len = decodeFourByteNumber(receivedInt);

			if (len < -1) {
				return EReturn.ERROR;

			} else if (len == -1) {
				return EReturn.EXIT;

			} else if (len == 0) {
				Log.log(MODULE, MSG_INC + "KEEP ALIVE");
				session.setReceivedKeepAlive(true);
				return EReturn.OK;
			} else {
				id = transporter.receiveByte();
				EClientTransportProtocol protocol = EClientTransportProtocol.get(id);
				if (protocol == null) {
					return EReturn.ERROR;
				} else {
					return decodeMessage(protocol);
				}
			}
		} catch (Exception e) {
			return EReturn.ERROR;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Decode message.
	 * @param protocol the protocol
	 * @return the e return
	 * @throws Exception the exception
	 */
	private EReturn decodeMessage(EClientTransportProtocol protocol) throws Exception {

		switch (protocol) {
		case CHOKE:
			session.setChoking(true);
			Log.log(MODULE, MSG_INC + "CHOKE");
			break;
		case UNCHOKE:
			session.setChoking(false);
			Log.log(MODULE, MSG_INC + "UNCHOKE");
			break;
		case INTERESTED:
			session.setInterested(true);
			Log.log(MODULE, MSG_INC + "INTERESTED");
			break;
		case NOT_INTERESTED:
			session.setInterested(false);
			Log.log(MODULE, MSG_INC + "NOT_INTERESTED");
			break;
		case HAVE:
			procesHave();
			break;
		case BITFIELD:
			processBitfield();
			Log.log(MODULE, MSG_INC + "BITFIELD");
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
		case DHT_PORT:
			processDHTPort();
			break;
		case HANDSHAKE:
			processHandshake();
			Log.log(MODULE, MSG_INC + "HANDSHAKE");
			break;
		default:
			Log.log(MODULE, MSG_INC + "WRONG ID " + protocol);
			return EReturn.ERROR;
		}
		return EReturn.OK;
	}
}
