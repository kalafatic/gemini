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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * The Enum enum RCProtocol.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum RCProtocol implements Enumerator {
	;

	/** The Constant VALUES_ARRAY. */
	private static final RCProtocol[] VALUES_ARRAY = new RCProtocol[] {};

	/** The Constant VALUES. */
	public static final List<RCProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the rC protocol
	 */
	public static RCProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RCProtocol result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the by name.
	 * @param name the name
	 * @return the by name
	 */
	public static RCProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RCProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the rC protocol
	 */
	public static RCProtocol get(int value) {
		switch (value) {
		}
		return null;
	}

	/** The value. */
	private final int value;

	/** The name. */
	private final String name;

	/** The literal. */
	private final String literal;

	/**
	 * Instantiates a new rC protocol.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private RCProtocol(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getValue()
	 */
	public int getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getLiteral()
	 */
	public String getLiteral() {
		return literal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return literal;
	}

} // RCProtocol
