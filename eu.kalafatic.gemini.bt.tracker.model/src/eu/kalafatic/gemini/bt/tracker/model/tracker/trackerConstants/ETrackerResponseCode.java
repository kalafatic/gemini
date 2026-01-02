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
 * The Enum enum ETrackerResponseCode.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ETrackerResponseCode implements Enumerator {

	/** The INVALI d_ request. */
	INVALID_REQUEST(100, "INVALID_REQUEST", "Invalid request type: client request was not a HTTP GET. "),

	/** The MISSIN g_ hash. */
	MISSING_HASH(101, "MISSING_HASH", "Missing info_hash. "),

	/** The MISSIN g_ pee r_ id. */
	MISSING_PEER_ID(102, "MISSING_PEER_ID", "Missing peer_id. "),

	/** The MISSIN g_ port. */
	MISSING_PORT(103, "MISSING_PORT", "Missing port."),

	/** The INVALI d_ hash. */
	INVALID_HASH(150, "INVALID_HASH", "Invalid infohash: infohash is not 20 bytes long. "),

	/** The INVALI d_ pee r_ id. */
	INVALID_PEER_ID(151, "INVALID_PEER_ID", "Invalid peerid: peerid is not 20 bytes long."),

	/** The INVALI d_ numwant. */
	INVALID_NUMWANT(152, "INVALID_NUMWANT", "Invalid numwant. Client requested more peers than allowed by tracker. "),

	/** The HAS h_ no t_ found. */
	HASH_NOT_FOUND(200, "HASH_NOT_FOUND", "info_hash not found in the database. Sent only by trackers that do not automatically include new hashes into the database. "),

	/** The EVENTLES s_ request. */
	EVENTLESS_REQUEST(500, "EVENTLESS_REQUEST", "Client sent an eventless request before the specified time. "),

	/** The GENERI c_ error. */
	GENERIC_ERROR(900, "GENERIC_ERROR", "Generic error.");

	/** The Constant INVALID_REQUEST_VALUE. */
	public static final int INVALID_REQUEST_VALUE = 100;

	/** The Constant MISSING_HASH_VALUE. */
	public static final int MISSING_HASH_VALUE = 101;

	/** The Constant MISSING_PEER_ID_VALUE. */
	public static final int MISSING_PEER_ID_VALUE = 102;

	/** The Constant MISSING_PORT_VALUE. */
	public static final int MISSING_PORT_VALUE = 103;

	/** The Constant INVALID_HASH_VALUE. */
	public static final int INVALID_HASH_VALUE = 150;

	/** The Constant INVALID_PEER_ID_VALUE. */
	public static final int INVALID_PEER_ID_VALUE = 151;

	/** The Constant INVALID_NUMWANT_VALUE. */
	public static final int INVALID_NUMWANT_VALUE = 152;

	/** The Constant HASH_NOT_FOUND_VALUE. */
	public static final int HASH_NOT_FOUND_VALUE = 200;

	/** The Constant EVENTLESS_REQUEST_VALUE. */
	public static final int EVENTLESS_REQUEST_VALUE = 500;

	/** The Constant GENERIC_ERROR_VALUE. */
	public static final int GENERIC_ERROR_VALUE = 900;

	/** The Constant VALUES_ARRAY. */
	private static final ETrackerResponseCode[] VALUES_ARRAY = new ETrackerResponseCode[] { INVALID_REQUEST, MISSING_HASH, MISSING_PEER_ID, MISSING_PORT, INVALID_HASH, INVALID_PEER_ID,
			INVALID_NUMWANT, HASH_NOT_FOUND, EVENTLESS_REQUEST, GENERIC_ERROR, };

	/** The Constant VALUES. */
	public static final List<ETrackerResponseCode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e tracker response code
	 */
	public static ETrackerResponseCode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseCode result = VALUES_ARRAY[i];
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
	public static ETrackerResponseCode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseCode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e tracker response code
	 */
	public static ETrackerResponseCode get(int value) {
		switch (value) {
		case INVALID_REQUEST_VALUE:
			return INVALID_REQUEST;
		case MISSING_HASH_VALUE:
			return MISSING_HASH;
		case MISSING_PEER_ID_VALUE:
			return MISSING_PEER_ID;
		case MISSING_PORT_VALUE:
			return MISSING_PORT;
		case INVALID_HASH_VALUE:
			return INVALID_HASH;
		case INVALID_PEER_ID_VALUE:
			return INVALID_PEER_ID;
		case INVALID_NUMWANT_VALUE:
			return INVALID_NUMWANT;
		case HASH_NOT_FOUND_VALUE:
			return HASH_NOT_FOUND;
		case EVENTLESS_REQUEST_VALUE:
			return EVENTLESS_REQUEST;
		case GENERIC_ERROR_VALUE:
			return GENERIC_ERROR;
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
	 * Instantiates a new e tracker response code.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ETrackerResponseCode(int value, String name, String literal) {
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

} // ETrackerResponseCode
