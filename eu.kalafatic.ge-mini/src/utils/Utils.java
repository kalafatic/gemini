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

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.List;

/**
 * The Class Utils.
 */
public class Utils {

	/**
	 * Encode four byte number.
	 * 
	 * @param i
	 *            the i
	 * @return the byte[]
	 */
	public static byte[] encodeFourByteNumber(int i) {
		return new byte[] { (byte) (i >>> 24), (byte) (i >>> 16),
				(byte) (i >>> 8), (byte) i };
	}

	/**
	 * Decode four byte number.
	 * 
	 * @param b
	 *            the b
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static int decodeFourByteNumber(byte[] b) throws IOException {
		return (b[0] & 0xff) << 24 | (b[1] & 0xff) << 16 | (b[2] & 0xff) << 8
				| (b[3] & 0xff);
	}

	/**
	 * Gets the hash.
	 * 
	 * @param input
	 *            the input
	 * @return the hash
	 */
	public static byte[] getHash(byte[] input) {
		MessageDigest md;
		byte[] sha1Hash = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			sha1Hash = new byte[20];

			md.reset();
			md.update(input, 0, input.length);
			sha1Hash = md.digest();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha1Hash;
	}

	/**
	 * Gets the socket address.
	 * 
	 * @param announce
	 *            the announce
	 * @return the socket address
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws UnknownHostException
	 *             the unknown host exception
	 */
	public static SocketAddress getSocketAddress(String announce)
			throws MalformedURLException, UnknownHostException {

		URL url = new URL(announce);
		InetAddress address = InetAddress.getByName(url.getHost());
		return new InetSocketAddress(address, url.getPort());
	}

	/**
	 * Gets the integer.
	 * 
	 * @param object
	 *            the object
	 * @return the integer
	 */
	public static Integer getInteger(Object object) {
		Integer integer = null;
		if (object instanceof Long) {
			Long l = (Long) object;
			integer = new Integer(l.intValue());
		} else {
			integer = (Integer) object;
		}
		return integer;
	}

	/**
	 * Gets the path.
	 * 
	 * @param paths
	 *            the paths
	 * @return the path
	 */
	public static String getPath(List<byte[]> paths) {
		String path = "";
		for (byte[] segment : paths) {
			path += File.separator + (new String(segment));
		}
		return path;
	}

	/**
	 * Contains.
	 * 
	 * @param array
	 *            the array
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean contains(boolean[] array, boolean value) {
		for (boolean b : array) {
			if (b == value) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is true.
	 * 
	 * @param b
	 *            the b
	 * @param pos
	 *            the pos
	 * @return true, if is true
	 */
	public static boolean isTrue(byte b, int pos) {
		int bitmask = 1 << (7 - pos);
		return (b & bitmask) != 0;
	}
}
