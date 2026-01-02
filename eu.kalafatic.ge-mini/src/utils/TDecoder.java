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

import static protocols.FTP.ENCODING_TYPE;
import io.AIO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Class TDecoder.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TDecoder {

	/**
	 * Decode.
	 * 
	 * @param file
	 *            the file
	 * @return the map
	 * @throws Exception
	 *             the exception
	 */
	public static Map<?, ?> decode(File file) throws Exception {
		return decode(AIO.readBytes(file));
	}

	/**
	 * Decode.
	 * 
	 * @param data
	 *            the data
	 * @return the map
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Map decode(byte[] data) throws IOException {
		return (Map) decode(new ByteArrayInputStream(data));
	}

	/**
	 * Decode.
	 * 
	 * @param bais
	 *            the bais
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public synchronized static Object decode(ByteArrayInputStream bais)
			throws IOException {
		if (!bais.markSupported()) {
			throw new IOException("InputStream must support the mark() method");
		}

		// set a mark
		bais.mark(Integer.MAX_VALUE);

		// read a byte
		int tempByte = bais.read();

		// System.err.println((char)tempByte);

		// decide what to do
		switch (tempByte) {

		case 'd':
			// create a new dictionary object
			Map tempMap = new HashMap();

			// get the key
			byte[] tempByteArray = null;

			while ((tempByteArray = (byte[]) TDecoder.decode(bais)) != null) {
				// decode some more
				Object value = TDecoder.decode(bais);
				// add the value to the map
				tempMap.put(new String(tempByteArray, ENCODING_TYPE), value);
			}

			// return the map
			return tempMap;

		case 'l':
			// create the list
			List tempList = new ArrayList();

			// create the key
			Object tempElement = null;
			while ((tempElement = TDecoder.decode(bais)) != null) {
				// add the element
				tempList.add(tempElement);
			}
			// return the list
			return tempList;

		case 'e':
			return null;

		case 'i':
			return new Long(TDecoder.getNumberFromStream(bais, 'e'));

		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			// move back one
			bais.reset();
			// get the string
			return TDecoder.getByteArrayFromStream(bais);

		default:
			// System.err.println((char)tempByte);
			// System.err.println((char)bais.read());
			// throw new IOException("UNKNOWN COMMAND");
			return TDecoder.getByteArrayFromStream(bais);
		}
	}

	/**
	 * Gets the number from stream.
	 * 
	 * @param bais
	 *            the bais
	 * @param parseChar
	 *            the parse char
	 * @return the number from stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static long getNumberFromStream(InputStream bais, char parseChar)
			throws IOException {
		int length = 0;

		// place a mark
		bais.mark(Integer.MAX_VALUE);

		int tempByte = bais.read();
		while ((tempByte != parseChar) && (tempByte != -1)) {
			tempByte = bais.read();
			length++;
		}

		// are we at the end of the stream?
		if (tempByte == -1) {
			return -1;
		}

		// reset the mark
		bais.reset();

		// get the length
		byte[] tempArray = new byte[length];
		bais.read(tempArray, 0, length);

		// jump ahead in the stream to compensate for the :
		bais.skip(1);

		// return the value
		long parseLong = 0;
		try {
			parseLong = Long.parseLong(new String(tempArray));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return parseLong;
	}

	/**
	 * Gets the byte array from stream.
	 * 
	 * @param bais
	 *            the bais
	 * @return the byte array from stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static byte[] getByteArrayFromStream(InputStream bais)
			throws IOException {
		int length = (int) TDecoder.getNumberFromStream(bais, ':');

		if (length == -1) {
			return null;
		}

		byte[] tempArray = new byte[length];

		// get the string
		bais.read(tempArray, 0, length);

		return tempArray;
	}

	/**
	 * Decode dictionary peers.
	 * 
	 * @param dictionary
	 *            the dictionary
	 * @return the sets the
	 */
	public static Set<String> decodeDictionaryPeers(List<?> dictionary) {

		Set<String> announces = new HashSet<String>();

		for (Iterator<?> iterator = dictionary.iterator(); iterator.hasNext();) {
			Object object = iterator.next();

			if (object instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) object;

				// byte[] idBytes = (byte[]) map.get("peer id");
				byte[] ipBytes = (byte[]) map.get("ip");
				Long port = (Long) map.get("port");

				try {
					InetAddress address = InetAddress.getByName(new String(
							ipBytes));

					String announce = "http:/" + address + ":" + port;
					announces.add(announce);

				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		return announces;
	}

	/**
	 * Decode binary peers.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the sets the
	 */
	public static Set<String> decodeBinaryPeers(byte[] bytes) {
		Set<String> announces = new HashSet<String>();
		try {
			ByteBuffer byteBuffer = (ByteBuffer) ByteBuffer
					.allocate(bytes.length).put(bytes, 0, bytes.length)
					.rewind();

			while (byteBuffer.remaining() > 5) {
				bytes = new byte[4];
				byteBuffer.get(bytes, 0, 4);
				InetAddress address = InetAddress.getByAddress(bytes);

				int port = ((0xFF & byteBuffer.get()) << 8)
						| (0xFF & byteBuffer.get());

				String announce = "http:/" + address + ":" + port;
				announces.add(announce);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return announces;
	}
}
