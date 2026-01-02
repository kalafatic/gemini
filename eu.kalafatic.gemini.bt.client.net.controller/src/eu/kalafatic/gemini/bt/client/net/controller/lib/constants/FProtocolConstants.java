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
package eu.kalafatic.gemini.bt.client.net.controller.lib.constants;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.core.runtime.Platform;

import eu.kalafatic.gemini.core.lib.ECorePreferences;

/**
 * The Class class FProtocolConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class FProtocolConstants {

	// handshake
	/** The Constant PROTOCOL_LEN. */
	public static final byte PROTOCOL_LEN = (byte) 19;

	/** The Constant PROTOCOL_NAME. */
	public static final String PROTOCOL_NAME = "BitTorrent protocol";

	/** The Constant PROTOCOL. */
	public static final byte[] PROTOCOL = PROTOCOL_NAME.getBytes();

	/** The Constant RESERVED. */
	public static final byte[] RESERVED = { 0, 0, 0, 0, 0, 0, 0, 0 };

	/** The Constant peerId. */
	public static final String peerId = PREFERENCES.get(ECorePreferences.APP_ID.getName(), (String) ECorePreferences.APP_ID.getDef());

	/** The Constant KEEP_ALIVE. */
	// public static final byte[] PEER_ID = peerId.getBytes();

	// messages
	/** The Constant KEEP_ALIVE. */
	public static final byte[] KEEP_ALIVE = { 0, 0, 0, 0 };

	/** The Constant CHOKE. */
	public static final byte[] CHOKE = { 0, 0, 0, 1, 0 };

	/** The Constant UNCHOKE. */
	public static final byte[] UNCHOKE = { 0, 0, 0, 1, 1 };

	/** The Constant INTERESTED. */
	public static final byte[] INTERESTED = { 0, 0, 0, 1, 2 };

	/** The Constant NOT_INTERESTED. */
	public static final byte[] NOT_INTERESTED = { 0, 0, 0, 1, 3 };

	/** The Constant HAVE. */
	public static final byte[] HAVE = { 0, 0, 0, 5, 4 };

	/** The Constant REQUEST. */
	public static final byte[] REQUEST = { 0, 0, 0, 13, 6 };

	/** The Constant CANCEL. */
	public static final byte[] CANCEL = { 0, 0, 0, 13, 8 };

	/** The Constant PORT. */
	public static final byte[] PORT = { 0, 0, 0, 3, 9 };

	/** The Constant BITFIELD_ID. */
	public static final byte[] BITFIELD_ID = { 5 };

	/** The Constant PIECE_ID. */
	public static final byte[] PIECE_ID = { 7 };

	/** The Constant PEER_ID. */
	public static final String PEER_ID = getClientID();

	// ---------------------------------------------------------------

	/**
	 * Gets the client id.
	 * @return the client id
	 */
	private static final String getClientID() {
		String version = Platform.getProduct().getProperty("version");
		version = version.replace(".", "");
		return "-GE" + version + "0-000000000000";
	}
}
