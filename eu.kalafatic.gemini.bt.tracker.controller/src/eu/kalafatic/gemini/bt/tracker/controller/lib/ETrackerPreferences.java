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
package eu.kalafatic.gemini.bt.tracker.controller.lib;

import eu.kalafatic.gemini.core.interfaces.IPreference;

/**
 * The Enum enum ETrackerPreferences.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ETrackerPreferences implements IPreference {

	/** The set. */
	SET(0, "Set", false, false),

	/** The module. */
	MODULE(1, "BT Client", "", ""),

	// THREADS
	/** The max client threads. */
	MAX_CLIENT_THREADS(2, "Max client threads", 5, 5),

	/** The max upld threads. */
	MAX_UPLD_THREADS(3, "Max upload threads", 5, 5),
	// SCHEDULERS
	/** The connection timeout. */
	CONNECTION_TIMEOUT(4, "Connection timeout", 3, 3),

	/** The trackers delay. */
	TRACKERS_DELAY(5, "Trackers delay", 3, 3),

	/** The keep alive check delay. */
	KEEP_ALIVE_CHECK_DELAY(6, "Keep alive check delay", 50, 50),

	/** The message queue size. */
	MESSAGE_QUEUE_SIZE(7, "Message queue size", 10, 10)

	;

	// ---------------------------------------------------------------

	/** The index. */
	private int index;

	/** The name. */
	private String name;

	/** The def. */
	private Object value, def;

	/**
	 * Instantiates a new e tracker preferences.
	 * @param index the index
	 * @param name the name
	 * @param value the value
	 * @param def the def
	 */
	private ETrackerPreferences(int index, String name, Object value, Object def) {
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
