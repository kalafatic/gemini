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
package eu.kalafatic.gemini.bt.client.net.rc.controller.model;

/**
 * The Class class Request.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class Request {

	/** The value. */
	private String key, value;

	/** The key val. */
	private int keyVal;

	/**
	 * Instantiates a new request.
	 * @param key the key
	 * @param value the value
	 */
	public Request(String key, String value) {
		this.key = key;
		this.value = value;

		keyVal = Integer.parseInt(key);
	}

	/**
	 * Gets the key.
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the value.
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the key val.
	 * @return the key val
	 */
	public int getKeyVal() {
		return keyVal;
	}
}
