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
package eu.kalafatic.gemini.bt.utils.decoders;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class BDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class BDecoder {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param data the data
	 * @return the map
	 */
	public static Map<?, ?> decode(byte[] data) {
		return BDecoder.decode(new ByteArrayInputStream(data));
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param data the data
	 * @return the map
	 */
	public static Map<?, ?> decode(ByteArrayInputStream data) {
		try {
			return (Map<?, ?>) BDecoder.decode((InputStream) data);
		} catch (IOException e) {
			Log.log(ECorePreferences.MODULE, e);
			return null;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param data the data
	 * @return the map
	 */
	public static Map<?, ?> decode(BufferedInputStream data) {
		try {
			return (Map<?, ?>) BDecoder.decode((InputStream) data);
		} catch (IOException e) {
			// Log.log("", e);
			return new HashMap<Object, Object>();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param bais the bais
	 * @return the object
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public synchronized static Object decode(InputStream bais) throws IOException {
		if (!bais.markSupported()) {
			throw new IOException("InputStream must support the mark() method");
		}

		// set a mark
		bais.mark(Integer.MAX_VALUE);

		// read a byte
		int tempByte = bais.read();

		// decide what to do
		switch (tempByte) {

		case 'd':
			// create a new dictionary object
			Map<String, Object> tempMap = new HashMap<String, Object>();

			// get the key
			byte[] tempByteArray = null;

			while ((tempByteArray = (byte[]) BDecoder.decode(bais)) != null) {
				// decode some more
				Object value = BDecoder.decode(bais);
				// add the value to the map
				tempMap.put(new String(tempByteArray, "ISO-8859-1"), value);
			}

			// return the map
			return tempMap;

		case 'l':
			// create the list
			List<Object> tempList = new ArrayList<Object>();

			// create the key
			Object tempElement = null;
			while ((tempElement = BDecoder.decode(bais)) != null) {
				// add the element
				tempList.add(tempElement);
			}
			// return the list
			return tempList;

		case 'e':
			return null;

		case 'i':
			return new Long(BDecoder.getNumberFromStream(bais, 'e'));

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
			return BDecoder.getByteArrayFromStream(bais);

		default:
			// throw new IOException("UNKNOWN COMMAND");
			return BDecoder.getByteArrayFromStream(bais);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the number from stream.
	 * @param bais the bais
	 * @param parseChar the parse char
	 * @return the number from stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static long getNumberFromStream(InputStream bais, char parseChar) throws IOException {
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
			Log.log(ECorePreferences.MODULE, e);
		}
		return parseLong;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the byte array from stream.
	 * @param bais the bais
	 * @return the byte array from stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static byte[] getByteArrayFromStream(InputStream bais) throws IOException {
		int length = (int) BDecoder.getNumberFromStream(bais, ':');

		if (length == -1) {
			return null;
		}
		byte[] tempArray = new byte[length];

		// get the string
		bais.read(tempArray, 0, length);

		return tempArray;
	}
}
