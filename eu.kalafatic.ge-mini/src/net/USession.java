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
import protocols.ETP;

/**
 * The Class USession.
 */
public class USession extends ASession {

	/**
	 * Instantiates a new u session.
	 * 
	 * @param channel
	 *            the channel
	 * @throws Exception
	 *             the exception
	 */
	public USession(SocketChannel channel) throws Exception {
		super(channel, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			int uploaded = 0;
			sendHandshake();

			if (interested) {
				transporter.send(ETP.UNCHOKE.header);

				while (Main.SESSIONS > uploaded++) {
					mDecoder.decodeMessage();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
