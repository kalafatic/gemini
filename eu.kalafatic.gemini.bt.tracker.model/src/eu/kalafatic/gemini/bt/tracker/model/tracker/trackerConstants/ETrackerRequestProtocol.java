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
 * The Enum enum ETrackerRequestProtocol.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ETrackerRequestProtocol implements Enumerator {

	/** The ANNOUNCE. */
	ANNOUNCE(0, "ANNOUNCE", ""),

	/** The INF o_ hash. */
	INFO_HASH(1, "INFO_HASH", "info_hash"),

	/** The PEE r_ id. */
	PEER_ID(2, "PEER_ID", "peer_id"),

	/** The PORT. */
	PORT(3, "PORT", "port"),

	/** The UPLOADED. */
	UPLOADED(4, "UPLOADED", "uploaded"),

	/** The DOWNLOADED. */
	DOWNLOADED(5, "DOWNLOADED", "downloaded"),

	/** The LEFT. */
	LEFT(6, "LEFT", "left"),

	/** The COMPACT. */
	COMPACT(7, "COMPACT", "compact"),

	/** The N o_ pee r_ id. */
	NO_PEER_ID(8, "NO_PEER_ID", "no_peer_id"),

	/** The IP. */
	IP(9, "IP", "ip"),

	/** The NUMWANT. */
	NUMWANT(10, "NUMWANT", "numwant"),

	/** The KEY. */
	KEY(11, "KEY", "key"),

	/** The TRACKE r_ id. */
	TRACKER_ID(12, "TRACKER_ID", "tracker_id"),

	/** The EVENT. */
	EVENT(13, "EVENT", "event");

	/** The Constant ANNOUNCE_VALUE. */
	public static final int ANNOUNCE_VALUE = 0;

	/** The Constant INFO_HASH_VALUE. */
	public static final int INFO_HASH_VALUE = 1;

	/** The Constant PEER_ID_VALUE. */
	public static final int PEER_ID_VALUE = 2;

	/** The Constant PORT_VALUE. */
	public static final int PORT_VALUE = 3;

	/** The Constant UPLOADED_VALUE. */
	public static final int UPLOADED_VALUE = 4;

	/** The Constant DOWNLOADED_VALUE. */
	public static final int DOWNLOADED_VALUE = 5;

	/** The Constant LEFT_VALUE. */
	public static final int LEFT_VALUE = 6;

	/** The Constant COMPACT_VALUE. */
	public static final int COMPACT_VALUE = 7;

	/** The Constant NO_PEER_ID_VALUE. */
	public static final int NO_PEER_ID_VALUE = 8;

	/** The Constant IP_VALUE. */
	public static final int IP_VALUE = 9;

	/** The Constant NUMWANT_VALUE. */
	public static final int NUMWANT_VALUE = 10;

	/** The Constant KEY_VALUE. */
	public static final int KEY_VALUE = 11;

	/** The Constant TRACKER_ID_VALUE. */
	public static final int TRACKER_ID_VALUE = 12;

	/** The Constant EVENT_VALUE. */
	public static final int EVENT_VALUE = 13;

	/** The Constant VALUES_ARRAY. */
	private static final ETrackerRequestProtocol[] VALUES_ARRAY = new ETrackerRequestProtocol[] { ANNOUNCE, INFO_HASH, PEER_ID, PORT, UPLOADED, DOWNLOADED, LEFT, COMPACT, NO_PEER_ID, IP, NUMWANT,
			KEY, TRACKER_ID, EVENT, };

	/** The Constant VALUES. */
	public static final List<ETrackerRequestProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e tracker request protocol
	 */
	public static ETrackerRequestProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestProtocol result = VALUES_ARRAY[i];
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
	public static ETrackerRequestProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e tracker request protocol
	 */
	public static ETrackerRequestProtocol get(int value) {
		switch (value) {
		case ANNOUNCE_VALUE:
			return ANNOUNCE;
		case INFO_HASH_VALUE:
			return INFO_HASH;
		case PEER_ID_VALUE:
			return PEER_ID;
		case PORT_VALUE:
			return PORT;
		case UPLOADED_VALUE:
			return UPLOADED;
		case DOWNLOADED_VALUE:
			return DOWNLOADED;
		case LEFT_VALUE:
			return LEFT;
		case COMPACT_VALUE:
			return COMPACT;
		case NO_PEER_ID_VALUE:
			return NO_PEER_ID;
		case IP_VALUE:
			return IP;
		case NUMWANT_VALUE:
			return NUMWANT;
		case KEY_VALUE:
			return KEY;
		case TRACKER_ID_VALUE:
			return TRACKER_ID;
		case EVENT_VALUE:
			return EVENT;
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
	 * Instantiates a new e tracker request protocol.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ETrackerRequestProtocol(int value, String name, String literal) {
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

} // ETrackerRequestProtocol
