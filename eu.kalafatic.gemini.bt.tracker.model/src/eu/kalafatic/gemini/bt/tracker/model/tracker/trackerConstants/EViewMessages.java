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
package eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * The Enum enum EViewMessages.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum EViewMessages implements Enumerator {

	/** The READY. */
	READY(10, "READY", "READY"),

	/** The PAUSED. */
	PAUSED(11, "PAUSED", "PAUSED"),

	/** The STOPPED. */
	STOPPED(12, "STOPPED", "STOPPED"),

	/** The FINISHED. */
	FINISHED(13, "FINISHED", "FINISHED"),

	/** The WAITING. */
	WAITING(14, "WAITING", "WAITING");

	/** The Constant READY_VALUE. */
	public static final int READY_VALUE = 10;

	/** The Constant PAUSED_VALUE. */
	public static final int PAUSED_VALUE = 11;

	/** The Constant STOPPED_VALUE. */
	public static final int STOPPED_VALUE = 12;

	/** The Constant FINISHED_VALUE. */
	public static final int FINISHED_VALUE = 13;

	/** The Constant WAITING_VALUE. */
	public static final int WAITING_VALUE = 14;

	/** The Constant VALUES_ARRAY. */
	private static final EViewMessages[] VALUES_ARRAY = new EViewMessages[] { READY, PAUSED, STOPPED, FINISHED, WAITING, };

	/** The Constant VALUES. */
	public static final List<EViewMessages> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e view messages
	 */
	public static EViewMessages get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EViewMessages result = VALUES_ARRAY[i];
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
	public static EViewMessages getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EViewMessages result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e view messages
	 */
	public static EViewMessages get(int value) {
		switch (value) {
		case READY_VALUE:
			return READY;
		case PAUSED_VALUE:
			return PAUSED;
		case STOPPED_VALUE:
			return STOPPED;
		case FINISHED_VALUE:
			return FINISHED;
		case WAITING_VALUE:
			return WAITING;
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
	 * Instantiates a new e view messages.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private EViewMessages(int value, String name, String literal) {
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

} // EViewMessages
