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
 * The Enum enum EDateFormat.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EDateFormat {

	/** The SIMPL e_1. */
	SIMPLE_1("dd MMMMM yyyy"),

	/** The SIMPL e_2. */
	SIMPLE_2("EEE, MMM d"),

	/** The basic. */
	BASIC("dd.MM.yy"),

	/** The NIC e_1. */
	NICE_1("yyyy.MM.dd 'at' hh:mm:ss z"),

	/** The NIC e_2. */
	NICE_2("yyyy.MM.dd G 'at' hh:mm:ss z"),

	/** The NIC e_3. */
	NICE_3("yyyy.MMMMM.dd GGG hh:mm aaa"),

	/** The TIM e_1. */
	TIME_1("H:mm:ss:SSS"),

	/** The TIM e_2. */
	TIME_2("K:mm a,z"),

	;

	/** The literal. */
	public String literal;

	/**
	 * Instantiates a new e date format.
	 * @param index the index
	 * @param literal the literal
	 */
	EDateFormat(String literal) {
		this.literal = literal;
	}

	/**
	 * Gets the literal.
	 * @return the literal
	 */
	public String getLiteral() {
		return literal;
	}
}
