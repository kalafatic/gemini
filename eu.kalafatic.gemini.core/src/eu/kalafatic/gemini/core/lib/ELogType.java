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
package eu.kalafatic.gemini.core.lib;

/**
 * The Enum enum ELogType.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ELogType {

	/** The txt. */
	TXT(0, "txt"),

	/** The html. */
	HTML(1, "html"),

	/** The xml. */
	XML(2, "xml");

	/** The index. */
	private int index;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new e log type.
	 * @param index the index
	 * @param value the value
	 */
	private ELogType(int index, String value) {
		this.index = index;
		this.value = value;
	}

	/**
	 * Gets the index.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the value.
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
