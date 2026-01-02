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
 * The Enum enum ETrackerResponseProtocol.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ETrackerResponseProtocol implements Enumerator {

	/** The FAILUR e_ reason. */
	FAILURE_REASON(0, "FAILURE_REASON", "failure reason"),

	/** The WARNIN g_ message. */
	WARNING_MESSAGE(1, "WARNING_MESSAGE", "warning message"),

	/** The INTERVAL. */
	INTERVAL(2, "INTERVAL", "interval"),

	/** The MI n_ interval. */
	MIN_INTERVAL(3, "MIN_INTERVAL", "min interval"),

	/** The TRACKE r_ id. */
	TRACKER_ID(4, "TRACKER_ID", "tracker_id"),

	/** The COMPLETE. */
	COMPLETE(5, "COMPLETE", "complete"),

	/** The INCOMPLETE. */
	INCOMPLETE(6, "INCOMPLETE", "incomplete"),

	/** The PEERS. */
	PEERS(7, "PEERS", "peers"),

	/** The PEE r_ id. */
	PEER_ID(8, "PEER_ID", "peer id"),

	/** The IP. */
	IP(9, "IP", "ip"),

	/** The PORT. */
	PORT(10, "PORT", "port");

	/** The Constant FAILURE_REASON_VALUE. */
	public static final int FAILURE_REASON_VALUE = 0;

	/** The Constant WARNING_MESSAGE_VALUE. */
	public static final int WARNING_MESSAGE_VALUE = 1;

	/** The Constant INTERVAL_VALUE. */
	public static final int INTERVAL_VALUE = 2;

	/** The Constant MIN_INTERVAL_VALUE. */
	public static final int MIN_INTERVAL_VALUE = 3;

	/** The Constant TRACKER_ID_VALUE. */
	public static final int TRACKER_ID_VALUE = 4;

	/** The Constant COMPLETE_VALUE. */
	public static final int COMPLETE_VALUE = 5;

	/** The Constant INCOMPLETE_VALUE. */
	public static final int INCOMPLETE_VALUE = 6;

	/** The Constant PEERS_VALUE. */
	public static final int PEERS_VALUE = 7;

	/** The Constant PEER_ID_VALUE. */
	public static final int PEER_ID_VALUE = 8;

	/** The Constant IP_VALUE. */
	public static final int IP_VALUE = 9;

	/** The Constant PORT_VALUE. */
	public static final int PORT_VALUE = 10;

	/** The Constant VALUES_ARRAY. */
	private static final ETrackerResponseProtocol[] VALUES_ARRAY = new ETrackerResponseProtocol[] { FAILURE_REASON, WARNING_MESSAGE, INTERVAL, MIN_INTERVAL, TRACKER_ID, COMPLETE, INCOMPLETE, PEERS,
			PEER_ID, IP, PORT, };

	/** The Constant VALUES. */
	public static final List<ETrackerResponseProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e tracker response protocol
	 */
	public static ETrackerResponseProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseProtocol result = VALUES_ARRAY[i];
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
	public static ETrackerResponseProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e tracker response protocol
	 */
	public static ETrackerResponseProtocol get(int value) {
		switch (value) {
		case FAILURE_REASON_VALUE:
			return FAILURE_REASON;
		case WARNING_MESSAGE_VALUE:
			return WARNING_MESSAGE;
		case INTERVAL_VALUE:
			return INTERVAL;
		case MIN_INTERVAL_VALUE:
			return MIN_INTERVAL;
		case TRACKER_ID_VALUE:
			return TRACKER_ID;
		case COMPLETE_VALUE:
			return COMPLETE;
		case INCOMPLETE_VALUE:
			return INCOMPLETE;
		case PEERS_VALUE:
			return PEERS;
		case PEER_ID_VALUE:
			return PEER_ID;
		case IP_VALUE:
			return IP;
		case PORT_VALUE:
			return PORT;
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
	 * Instantiates a new e tracker response protocol.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ETrackerResponseProtocol(int value, String name, String literal) {
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

} // ETrackerResponseProtocol
