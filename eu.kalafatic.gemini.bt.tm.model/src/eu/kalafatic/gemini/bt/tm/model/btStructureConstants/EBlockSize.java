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
package eu.kalafatic.gemini.bt.tm.model.btStructureConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * The Enum enum EBlockSize.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum EBlockSize implements Enumerator {

	/** The PFOUR. */
	PFOUR(16384, "P_FOUR", "16 KB"),

	/** The PFIVE. */
	PFIVE(32768, "P_FIVE", "32 Kb"),

	/** The PSIX. */
	PSIX(65536, "P_SIX", "64  KB"),

	/** The PSEVEN. */
	PSEVEN(131072, "P_SEVEN", "128  KB"),

	/** The PEIGHT. */
	PEIGHT(262144, "P_EIGHT", "256  KB"),

	/** The PNINE. */
	PNINE(524288, "P_NINE", "512  KB"),

	/** The PTEN. */
	PTEN(1048576, "P_TEN", "1 MB"),

	/** The PELEVEN. */
	PELEVEN(2097152, "P_ELEVEN", "2 MB");

	/** The Constant PFOUR_VALUE. */
	public static final int PFOUR_VALUE = 16384;

	/** The Constant PFIVE_VALUE. */
	public static final int PFIVE_VALUE = 32768;

	/** The Constant PSIX_VALUE. */
	public static final int PSIX_VALUE = 65536;

	/** The Constant PSEVEN_VALUE. */
	public static final int PSEVEN_VALUE = 131072;

	/** The Constant PEIGHT_VALUE. */
	public static final int PEIGHT_VALUE = 262144;

	/** The Constant PNINE_VALUE. */
	public static final int PNINE_VALUE = 524288;

	/** The Constant PTEN_VALUE. */
	public static final int PTEN_VALUE = 1048576;

	/** The Constant PELEVEN_VALUE. */
	public static final int PELEVEN_VALUE = 2097152;

	/** The Constant VALUES_ARRAY. */
	private static final EBlockSize[] VALUES_ARRAY = new EBlockSize[] { PFOUR, PFIVE, PSIX, PSEVEN, PEIGHT, PNINE, PTEN, PELEVEN, };

	/** The Constant VALUES. */
	public static final List<EBlockSize> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e block size
	 */
	public static EBlockSize get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EBlockSize result = VALUES_ARRAY[i];
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
	public static EBlockSize getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EBlockSize result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e block size
	 */
	public static EBlockSize get(int value) {
		switch (value) {
		case PFOUR_VALUE:
			return PFOUR;
		case PFIVE_VALUE:
			return PFIVE;
		case PSIX_VALUE:
			return PSIX;
		case PSEVEN_VALUE:
			return PSEVEN;
		case PEIGHT_VALUE:
			return PEIGHT;
		case PNINE_VALUE:
			return PNINE;
		case PTEN_VALUE:
			return PTEN;
		case PELEVEN_VALUE:
			return PELEVEN;
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
	 * Instantiates a new e block size.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private EBlockSize(int value, String name, String literal) {
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

} // EBlockSize
