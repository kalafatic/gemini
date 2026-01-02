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
 * The Enum enum ERCSessionState.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ERCSessionState implements Enumerator {

	/** The NEW. */
	NEW(0, "NEW", "NEW"),

	/** The LOGIN. */
	LOGIN(1, "LOGIN", "LOGIN"),

	/** The LOGED. */
	LOGED(2, "LOGED", "LOGED"),

	/** The CERTIFIED. */
	CERTIFIED(3, "CERTIFIED", "CERTIFIED"),

	/** The LOGOUT. */
	LOGOUT(4, "LOGOUT", "LOGOUT");

	/** The Constant NEW_VALUE. */
	public static final int NEW_VALUE = 0;

	/** The Constant LOGIN_VALUE. */
	public static final int LOGIN_VALUE = 1;

	/** The Constant LOGED_VALUE. */
	public static final int LOGED_VALUE = 2;

	/** The Constant CERTIFIED_VALUE. */
	public static final int CERTIFIED_VALUE = 3;

	/** The Constant LOGOUT_VALUE. */
	public static final int LOGOUT_VALUE = 4;

	/** The Constant VALUES_ARRAY. */
	private static final ERCSessionState[] VALUES_ARRAY = new ERCSessionState[] { NEW, LOGIN, LOGED, CERTIFIED, LOGOUT, };

	/** The Constant VALUES. */
	public static final List<ERCSessionState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the eRC session state
	 */
	public static ERCSessionState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ERCSessionState result = VALUES_ARRAY[i];
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
	public static ERCSessionState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ERCSessionState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the eRC session state
	 */
	public static ERCSessionState get(int value) {
		switch (value) {
		case NEW_VALUE:
			return NEW;
		case LOGIN_VALUE:
			return LOGIN;
		case LOGED_VALUE:
			return LOGED;
		case CERTIFIED_VALUE:
			return CERTIFIED;
		case LOGOUT_VALUE:
			return LOGOUT;
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
	 * Instantiates a new eRC session state.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ERCSessionState(int value, String name, String literal) {
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

} // ERCSessionState
