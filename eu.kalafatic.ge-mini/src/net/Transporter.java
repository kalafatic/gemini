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

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * The Class Transporter.
 */
public class Transporter extends ATransporter {

	/**
	 * Instantiates a new transporter.
	 * 
	 * @param channel
	 *            the channel
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Transporter(SocketChannel channel) throws IOException {
		super(channel);
	}

	/**
	 * Send.
	 * 
	 * @param data
	 *            the data
	 * @throws Exception
	 *             the exception
	 */
	public void send(byte[]... data) throws Exception {
		for (int i = 0; i < data.length; i++) {
			bos.write(data[i]);
		}
		bos.flush();
	}

	/**
	 * Receive byte.
	 * 
	 * @return the byte
	 * @throws Exception
	 *             the exception
	 */
	public byte receiveByte() throws Exception {
		return receive(1)[0];
	}

	/**
	 * Receive int.
	 * 
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public byte[] receiveInt() throws Exception {
		byte[] length = receive(4);
		if (length == null) {
			throw new IOException("Not enaugh data in stream !");
		}
		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ATransporter#receive(int)
	 */
	@Override
	public byte[] receive(int receiveLength) throws Exception {
		// read from previous
		if (byteBufferRest.remaining() >= receiveLength) {
			byte[] receiveBuffer = new byte[receiveLength];
			byteBufferRest.get(receiveBuffer);
			return receiveBuffer;
		}
		return super.receive(receiveLength);
	}
}
