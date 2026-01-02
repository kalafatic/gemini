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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Class TEncoder.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TEncoder {

	/**
	 * Encode.
	 * 
	 * @param object
	 *            the object
	 * @return the byte[]
	 */
	public static byte[] encode(Map<?, ?> object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TEncoder.encode(baos, object);
		return baos.toByteArray();
	}

	/**
	 * Encode.
	 * 
	 * @param baos
	 *            the baos
	 * @param object
	 *            the object
	 */
	private static void encode(ByteArrayOutputStream baos, Object object) {
		if (object instanceof String) {
			String tempString = (String) object;

			try {
				baos.write(((tempString.length()) + ":" + tempString)
						.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (object instanceof Map) {
			Map<?, ?> tempMap = (Map<?, ?>) object;

			SortedMap tempTree = null;

			// write the d
			baos.write('d');

			// are we sorted?
			if (tempMap instanceof TreeMap) {
				tempTree = (TreeMap<?, ?>) tempMap;
			} else {
				// do map sorting here
				tempTree = new TreeMap(tempMap);
			}

			// create a list to hold the alpha ordered keys
			ArrayList<String> keyList = new ArrayList<String>();

			// BUILD THE KEY LIST
			// I KNOW THIS IS NASTY, BUT SUN DIDN'T SEE FIT TO RETURN A NULL
			do {
				try {
					// get the key
					String tempKey = (String) tempTree.firstKey();
					// stuff it into the list
					keyList.add(tempKey);
					// get the rest of the tree
					tempTree = tempTree.tailMap(tempKey + "\0");
				} catch (NoSuchElementException e) {
					break;
				}
			} while (true);

			// encode all of the keys
			for (int i = 0; i < keyList.size(); i++) {
				// encode the key
				TEncoder.encode(baos, keyList.get(i));
				// encode the value
				TEncoder.encode(baos, tempMap.get(keyList.get(i)));
			}

			baos.write('e');

		} else if (object instanceof List) {
			List<?> tempList = (List<?>) object;
			// write out the l
			baos.write('l');

			for (int i = 0; i < tempList.size(); i++) {
				// encode the first element
				TEncoder.encode(baos, tempList.get(i));
			}
			baos.write('e');

		} else if (object instanceof Long) {
			Long tempLong = (Long) object;
			// write out the l
			try {
				baos.write(new String('i' + tempLong.toString() + 'e')
						.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (object instanceof Integer) {
			Integer tempInteger = (Integer) object;
			// write out the l
			try {
				baos.write(new String('i' + tempInteger.toString() + 'e')
						.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (object instanceof byte[]) {
			byte[] tempByteArray = (byte[]) object;
			try {
				baos.write((String.valueOf(tempByteArray.length)).getBytes());
				baos.write(":".getBytes());
				baos.write(tempByteArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
