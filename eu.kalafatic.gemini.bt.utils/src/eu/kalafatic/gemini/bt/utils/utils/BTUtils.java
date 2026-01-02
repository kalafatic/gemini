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
package eu.kalafatic.gemini.bt.utils.utils;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ANNOUNCE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HTTP;

/**
 * The Class class BTUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class BTUtils {

	/** The s announce. */
	private final String S_ANNOUNCE = "/" + ANNOUNCE;

	/** The s announce s. */
	private final String S_ANNOUNCE_S = S_ANNOUNCE + "/";

	/** The Constant INSTANCE. */
	public final static BTUtils INSTANCE = new BTUtils();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Parses the.
	 * @param announce the announce
	 * @return the string[]
	 */
	public String[] parse(String announce) {
		String[] request = new String[2];

		if (announce.startsWith(HTTP)) {
			String address = announce.substring(announce.indexOf(HTTP) + 7);
			request = address.split(":");
		}
		if (announce.endsWith(S_ANNOUNCE)) {
			request[1] = request[1].substring(0, request[1].indexOf(S_ANNOUNCE));

		} else if (announce.endsWith(S_ANNOUNCE_S)) {
			request[1] = request[1].substring(0, request[1].indexOf(S_ANNOUNCE_S));
		}
		return request;
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the.
	 * @param address the address
	 * @param port the port
	 * @return the string
	 */
	public String parse(String address, int port) {
		return parse(address, Integer.toString(port));
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the.
	 * @param address the address
	 * @param port the port
	 * @return the string
	 */
	public String parse(String address, String port) {
		if (!address.startsWith(HTTP)) {
			address = HTTP + address + ":" + port;
		}
		if (!address.endsWith(S_ANNOUNCE)) {
			address += S_ANNOUNCE;
		}
		return address;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the contaiment.
	 * @param bitfield the bitfield
	 * @param size the size
	 * @return the contaiment
	 */
	public float getContaiment(byte[] bitfield, float size) {
		float j = 0, k = 0;
		for (byte b : bitfield) {
			if (k < size) {
				for (int i = 0; i < 8; i++) {
					j += isTrue(b, i) ? 1 : 0;
				}
			}
		}
		return j / (size / 100f);
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is true.
	 * @param b the b
	 * @param pos the pos
	 * @return true, if is true
	 */
	public boolean isTrue(byte b, int pos) {
		int bitmask = 1 << (7 - pos);
		return (b & bitmask) != 0;
	}

}
