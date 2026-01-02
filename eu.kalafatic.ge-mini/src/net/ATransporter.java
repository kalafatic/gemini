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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import main.Main;

/**
 * The Class ATransporter.
 */
public abstract class ATransporter {

	/** The channel. */
	protected SocketChannel channel;

	/** The bis. */
	protected BufferedInputStream bis;
	
	/** The bos. */
	protected BufferedOutputStream bos;

	/** The buffer. */
	protected byte[] buffer;

	/** The byte buffer rest. */
	protected ByteBuffer byteBufferRest = ByteBuffer.allocate(0);
	
	/** The baos. */
	protected final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	/**
	 * Instantiates a new a transporter.
	 * 
	 * @param channel
	 *            the channel
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ATransporter(SocketChannel channel) throws IOException {
		this.channel = channel;

		bis = new BufferedInputStream(channel.socket().getInputStream());
		bos = new BufferedOutputStream(channel.socket().getOutputStream());

		buffer = new byte[Main.BLOCKS_IN_REQUEST
				* (13 + Main.TRANSPORT_BLOCK_SIZE)];
	}

	/**
	 * Receive.
	 * 
	 * @param receiveLength
	 *            the receive length
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public byte[] receive(int receiveLength) throws Exception {
		baos.reset();
		int readed = byteBufferRest.remaining(), ret;

		if (readed > 0) {
			byte[] array = new byte[readed];
			byteBufferRest.get(array);
			baos.write(array);
		}
		do {
			ret = bis.read(buffer);
			if (ret == -1) {
				break;
			}
			baos.write(buffer, 0, ret);

		} while ((readed += ret) < receiveLength);

		if (readed > receiveLength) {
			return truncateStream(receiveLength, readed);

		} else if (readed < receiveLength) {

			if (ret == -1) {
				return new byte[0];
			} else {
				return null;
			}
		} else {
			return baos.toByteArray();
		}
	}

	/**
	 * Truncate stream.
	 * 
	 * @param receiveLength
	 *            the receive length
	 * @param readed
	 *            the readed
	 * @return the byte[]
	 */
	private byte[] truncateStream(int receiveLength, int readed) {
		byte[] readedBytes = baos.toByteArray();
		byte[] receiveBuffer = new byte[receiveLength];

		System.arraycopy(readedBytes, 0, receiveBuffer, 0, receiveLength);

		byteBufferRest = ByteBuffer.wrap(readedBytes, receiveLength, readed
				- receiveLength);
		byteBufferRest.compact();
		byteBufferRest.flip();
		byteBufferRest.hasArray();

		return receiveBuffer;
	}
}
