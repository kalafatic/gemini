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
 * The Enum enum ERemote.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ERemote {

	/** The disabled. */
	DISABLED(0, "Disabled"),

	/** The auto. */
	AUTO(1, "Auto"),

	/** The enabled. */
	ENABLED(2, "Enabled");

	/** The index. */
	public int index;

	/** The literal. */
	public String literal;

	/**
	 * Instantiates a new e remote.
	 * @param index the index
	 * @param literal the literal
	 */
	ERemote(int index, String literal) {
		this.index = index;
		this.literal = literal;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * As string array.
	 * @return the string[]
	 */
	public static String[] asStringArray() {
		String[] stringArray = new String[values().length];
		int i = 0;
		for (ERemote en : values()) {
			stringArray[i++] = en.literal;
		}
		return stringArray;
	}
}
