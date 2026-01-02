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
package eu.kalafatic.gemini.bt.client.net.controller.transporters;

import java.io.IOException;

import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.ATransporter;

/**
 * The Class class Transporter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class Transporter extends ATransporter {

	/**
	 * Instantiates a new transporter.
	 * @param handler the handler
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Transporter(AClientHandler handler) throws IOException {
		super(handler);

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# receiveByte()
	 */
	@Override
	public byte receiveByte() throws Exception {
		return receive(1)[0];
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# receiveInt()
	 */
	@Override
	public byte[] receiveInt() throws Exception {
		byte[] length = receive(4);
		if (length == null) {
			throw new IOException("Not enaugh data in stream !");
		}
		return length;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ATransporter# receive(int)
	 */
	@Override
	public byte[] receive(int receiveLength) throws Exception {
		// if (session instanceof UpldSession) {
		// System.err.println();
		// }

		// read from previous
		if (byteBufferRest.remaining() >= receiveLength) {
			byte[] receiveBuffer = new byte[receiveLength];
			byteBufferRest.get(receiveBuffer);
			return receiveBuffer;
		}
		return super.receive(receiveLength);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# send(byte[])
	 */
	@Override
	public void send(byte[] data) throws Exception {
		bos.write(data);
		bos.flush();
		// here is possible to wait
		resolveUpDownSize(data.length, true);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# send(byte[][])
	 */
	@Override
	public void send(byte[]... data) throws Exception {
		int sent = 0;
		for (int i = 0; i < data.length; i++) {
			bos.write(data[i]);
			sent += data[i].length;
		}
		bos.flush();
		// here is possible to wait
		resolveUpDownSize(sent, true);
	}
}
