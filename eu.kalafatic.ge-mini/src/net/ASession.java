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

import java.io.ByteArrayOutputStream;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import protocols.IState;
import utils.MDecoder;
import utils.Utils;

/**
 * The Class ASession.
 */
public abstract class ASession implements Runnable, IState {

	/** The channel. */
	protected SocketChannel channel;

	/** The keep alive. */
	public boolean handshake, choking, interested, keepAlive;

	/** The STATE. */
	public int STATE = 0;

	/** The length array. */
	protected byte[] lengthArray;

	/** The bitfield. */
	public byte[] bitfield;

	/** The transporter. */
	protected Transporter transporter;

	/** The m decoder. */
	protected MDecoder mDecoder;

	/** The have. */
	public List<Integer> have = new ArrayList<Integer>();

	/** The baos. */
	public ByteArrayOutputStream baos = new ByteArrayOutputStream();

	/**
	 * Instantiates a new a session.
	 * 
	 * @param channel
	 *            the channel
	 * @param download
	 *            the download
	 * @throws Exception
	 *             the exception
	 */
	public ASession(SocketChannel channel, boolean download) throws Exception {
		this.channel = channel;

		transporter = new Transporter(channel);
		mDecoder = new MDecoder(this, transporter);

		if (!download) {
			mDecoder.decodeMessage();
			if (!handshake) {
				throw new Exception("Upload handshake error.");
			}
		}
	}

	/**
	 * Send handshake.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected void sendHandshake() throws Exception {
		lengthArray = Utils
				.encodeFourByteNumber(Main.torrent.bitfield.length + 1);
		transporter.send(Main.torrent.handshake, lengthArray, new byte[] { 5 },
				Main.torrent.bitfield);
		mDecoder.decodeMessage();
	}
}
