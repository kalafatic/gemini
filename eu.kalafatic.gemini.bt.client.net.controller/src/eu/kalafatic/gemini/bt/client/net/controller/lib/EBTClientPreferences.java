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
package eu.kalafatic.gemini.bt.client.net.controller.lib;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy;
import eu.kalafatic.gemini.core.interfaces.IPreference;

/**
 * The Enum enum EBTClientPreferences.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EBTClientPreferences implements IPreference {

	/** The set. */
	SET(0, "Set", false, false),

	/** The module. */
	MODULE(1, "BT Client", "", ""),

	// THREADS
	/** The max swarm threads. */
	MAX_SWARM_THREADS(2, "Max swarm threads", 4, 4),

	/** The max tracker threads. */
	MAX_TRACKER_THREADS(3, "Max trackers/swarm threads", 8, 8),

	/** The max dwnld threads. */
	MAX_DWNLD_THREADS(4, "Max downloads/swarm threads", 8, 8),

	/** The max upld threads. */
	MAX_UPLD_THREADS(5, "Max uploads/swarm threads", 8, 8),

	/** The executor timeout. */
	EXECUTOR_TIMEOUT(6, "Max executor queue timeout", 32, 32),

	// ALGORITHMS
	/** The algorithm. */
	ALGORITHM(7, "Algorithm", EAlgorithm.QUEUENING, EAlgorithm.QUEUENING),

	/** The strategy. */
	STRATEGY(8, "Piece downloading strategy", EStrategy.RANDOM, EStrategy.RANDOM),

	/** The ratio. */
	RATIO(9, "D/U Ratio strategy", 0f, 0f),

	// NETWORK
	/** The listening peers. */
	LISTENING_PEERS(10, "Listen", true, true),

	/** The http timeout. */
	HTTP_TIMEOUT(11, "Connection timeout", 4000, 4000),
	// TIME
	/** The client timeout. */
	CLIENT_TIMEOUT(12, "Client timeout", 4000, 4000),

	/** The tracker timeout. */
	TRACKER_TIMEOUT(13, "Tracker timeout", 8000, 8000),

	/** The read timeout. */
	READ_TIMEOUT(14, "Read timeout", 2000, 2000),

	/** The next timeout. */
	NEXT_TIMEOUT(15, "Next task timeout", 10000, 10000),

	/** The pause timeout. */
	PAUSE_TIMEOUT(16, "Pause timeout", 1000, 1000),

	/** The speed delay. */
	SPEED_DELAY(17, "Speed calculation delay (s)", 4, 4),
	// SCHEDULERS
	/** The trackers delay. */
	TRACKERS_DELAY(18, "Trackers delay (s)", 4 * 60, 4 * 60),
	// / TRANSPORT
	/** The requested pieces. */
	REQUESTED_PIECES(19, "Requested pieces/client", 1, 1),

	/** The requests. */
	REQUESTS(20, "Blocks in request", 4, 4),

	/** The blocks in request. */
	BLOCKS_IN_REQUEST(21, "Blocks in request", 4, 4),

	/** The transport block size. */
	TRANSPORT_BLOCK_SIZE(22, "Transport block size", 1 << 14, 1 << 14),

	/** The tracker numwant. */
	TRACKER_NUMWANT(23, "Tracker numwant", 16, 16),

	/** The transport buffer. */
	TRANSPORT_BUFFER(24, "Transport buffer", 1024, 1024),

	/** The tracker scrape. */
	TRACKER_SCRAPE(25, "Scrape", false, false),

	/** The max dwnld speed. */
	MAX_DWNLD_SPEED(26, "Max download speed", 0, 0),

	/** The max upld speed. */
	MAX_UPLD_SPEED(27, "Max upload speed", 1024, 1024),

	;

	// ---------------------------------------------------------------

	/** The index. */
	private int index;

	/** The name. */
	private String name;

	/** The def. */
	private Object value, def;

	/**
	 * Instantiates a new EBT client preferences.
	 * @param index the index
	 * @param name the name
	 * @param value the value
	 * @param def the def
	 */
	private EBTClientPreferences(int index, String name, Object value, Object def) {
		this.index = index;
		this.name = name;
		this.value = value;
		this.def = def;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the index.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getValue()
	 */
	@Override
	public Object getValue() {
		return value;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getDef()
	 */
	@Override
	public Object getDef() {
		return def;
	}
}
