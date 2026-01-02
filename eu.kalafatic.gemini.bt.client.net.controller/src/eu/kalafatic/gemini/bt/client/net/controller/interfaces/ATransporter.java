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
package eu.kalafatic.gemini.bt.client.net.controller.interfaces;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.BLOCKS_IN_REQUEST;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.TRANSPORT_BLOCK_SIZE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.eclipse.core.runtime.Assert;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;

/**
 * The Class class ATransporter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class ATransporter implements ITransporter {

	/** The session. */
	protected ClientSession session;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The bis. */
	private BufferedInputStream bis;

	/** The bos. */
	protected BufferedOutputStream bos;

	/** The byte buffer rest. */
	public ByteBuffer byteBufferRest = ByteBuffer.allocate(0);

	/** The buffer. */
	private byte[] buffer;

	/** The baos. */
	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	/**
	 * Instantiates a new a transporter.
	 * @param handler the handler
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ATransporter(AClientHandler handler) throws IOException {
		this.session = handler.getSession();

		if (session instanceof DwnldSession) {
			setSwarmSession(handler.getSwarmSession());
		}
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# init()
	 */
	@Override
	public void init() throws IOException {
		SocketChannel channel = session.getChannel();
		Assert.isNotNull(channel);

		// is = channel.socket().getInputStream();
		bis = new BufferedInputStream(channel.socket().getInputStream());
		bos = new BufferedOutputStream(channel.socket().getOutputStream());

		// 4len + 1id + 4index + 4begin + xblock
		buffer = new byte[BLOCKS_IN_REQUEST * (13 + TRANSPORT_BLOCK_SIZE)];
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# receive(int)
	 */
	@Override
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
			if (ret == EReturn.EXIT_VALUE) {
				break;
			}
			baos.write(buffer, 0, ret);

		} while ((readed += ret) < receiveLength);

		if (readed > receiveLength) {
			return truncateStream(receiveLength, readed);

		} else if (readed < receiveLength) {

			if (ret == EReturn.EXIT_VALUE) {
				return new byte[0];
			} else {
				return null;
			}
		} else {
			return baos.toByteArray();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Truncate stream.
	 * @param receiveLength the receive length
	 * @param readed the readed
	 * @return the byte[]
	 */
	private byte[] truncateStream(int receiveLength, int readed) {

		byte[] readedBytes = baos.toByteArray();

		byte[] receiveBuffer = new byte[receiveLength];

		System.arraycopy(readedBytes, 0, receiveBuffer, 0, receiveLength);

		byteBufferRest = ByteBuffer.wrap(readedBytes, receiveLength, readed - receiveLength);
		byteBufferRest.compact();
		byteBufferRest.flip();
		byteBufferRest.hasArray();

		return receiveBuffer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# resolveUpDownSize(int, boolean)
	 */
	@Override
	public void resolveUpDownSize(int size, boolean send) throws IOException {
		// initialize exttorrent || size == 0
		if ((swarmSession == null) || (size < 1)) {
			return;
		}
		SpeedContainer speedContainer = swarmSession.getSpeedContainer();
		if (send) {
			long upldSize = speedContainer.getUpldSize();
			speedContainer.setUpldSize(upldSize + size);
		} else {
			long dwnldSize = speedContainer.getDwnldSize();
			speedContainer.setDwnldSize(dwnldSize + size);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransporter# setSwarmSession
	 * (eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession)
	 */
	@Override
	public void setSwarmSession(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
	}
}
