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
 * The Enum enum EEncoding.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EEncoding {

	/** The IS o_1. */
	ISO_1("ISO-8859-1"),

	/** The WI n_1250. */
	WIN_1250("windows-1250"),

	/** The WI n_1251. */
	WIN_1251("windows-1251"),

	/** The us ascii. */
	US_ASCII("US-ASCII"),

	/** The UT f_8. */
	UTF_8("UTF-8"),

	/** The UT f_16. */
	UTF_16("UTF-16");

	/** The literal. */
	private String literal;

	/**
	 * Instantiates a new e encoding.
	 * @param index the index
	 * @param value the value
	 */
	private EEncoding(String value) {
		this.literal = value;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the literal.
	 * @return the literal
	 */
	public String getLiteral() {
		return literal;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the values.
	 * @return the values
	 */
	public static String[] getValues() {
		String[] values = new String[values().length];
		for (int i = 0; i < values.length; i++) {
			values[i] = values()[i].getLiteral();
		}
		return values;
	}
}
