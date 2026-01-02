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
 * The Enum enum ERCCMD.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ERCCMD implements Enumerator {

	/** The AP p_ restart. */
	APP_RESTART(0, "APP_RESTART", "APP_RESTART"),

	/** The R c_ logout. */
	RC_LOGOUT(1, "RC_LOGOUT", "RC_LOGOUT"),

	/** The SE l_ all. */
	SEL_ALL(2, "SEL_ALL", "SEL_ALL"),

	/** The SE l_ unf. */
	SEL_UNF(3, "SEL_UNF", "SEL_UNF"),

	/** The SE l_ fin. */
	SEL_FIN(4, "SEL_FIN", "SEL_FIN"),

	/** The TSTART. */
	TSTART(5, "T_START", "T_START"),

	/** The TSTOP. */
	TSTOP(6, "T_STOP", "T_STOP"),

	/** The TADD. */
	TADD(7, "T_ADD", "T_ADD");

	/** The Constant APP_RESTART_VALUE. */
	public static final int APP_RESTART_VALUE = 0;

	/** The Constant RC_LOGOUT_VALUE. */
	public static final int RC_LOGOUT_VALUE = 1;

	/** The Constant SEL_ALL_VALUE. */
	public static final int SEL_ALL_VALUE = 2;

	/** The Constant SEL_UNF_VALUE. */
	public static final int SEL_UNF_VALUE = 3;

	/** The Constant SEL_FIN_VALUE. */
	public static final int SEL_FIN_VALUE = 4;

	/** The Constant TSTART_VALUE. */
	public static final int TSTART_VALUE = 5;

	/** The Constant TSTOP_VALUE. */
	public static final int TSTOP_VALUE = 6;

	/** The Constant TADD_VALUE. */
	public static final int TADD_VALUE = 7;

	/** The Constant VALUES_ARRAY. */
	private static final ERCCMD[] VALUES_ARRAY = new ERCCMD[] { APP_RESTART, RC_LOGOUT, SEL_ALL, SEL_UNF, SEL_FIN, TSTART, TSTOP, TADD, };

	/** The Constant VALUES. */
	public static final List<ERCCMD> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the eRCCMD
	 */
	public static ERCCMD get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ERCCMD result = VALUES_ARRAY[i];
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
	public static ERCCMD getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ERCCMD result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the eRCCMD
	 */
	public static ERCCMD get(int value) {
		switch (value) {
		case APP_RESTART_VALUE:
			return APP_RESTART;
		case RC_LOGOUT_VALUE:
			return RC_LOGOUT;
		case SEL_ALL_VALUE:
			return SEL_ALL;
		case SEL_UNF_VALUE:
			return SEL_UNF;
		case SEL_FIN_VALUE:
			return SEL_FIN;
		case TSTART_VALUE:
			return TSTART;
		case TSTOP_VALUE:
			return TSTOP;
		case TADD_VALUE:
			return TADD;
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
	 * Instantiates a new eRCCMD.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ERCCMD(int value, String name, String literal) {
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

} // ERCCMD
