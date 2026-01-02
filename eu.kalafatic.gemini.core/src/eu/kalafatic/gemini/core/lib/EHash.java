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
 * The Enum enum EHash.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EHash {

	/** The SH a_1. */
	SHA_1("SHA-1"),

	/** The M d_5. */
	MD_5("MD5")

	;

	/** The literal. */
	private String literal;

	/**
	 * Instantiates a new e hash.
	 * @param index the index
	 * @param literal the literal
	 */
	private EHash(String literal) {
		this.literal = literal;
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
}
