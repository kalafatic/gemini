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
package eu.kalafatic.gemini.bt.tm.controller.lib;

import eu.kalafatic.gemini.core.interfaces.IPreference;

/**
 * The Enum enum ETMPreferences.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ETMPreferences implements IPreference {

	/** The SET. */
	SET(0, "Set", false, false),

	/** The MODULE. */
	MODULE(1, "Torrent Maker", "", ""),

	/** The SYN c_ client. */
	SYNC_CLIENT(2, "Synchronize with client", true, true),

	/** The SYN c_ tracker. */
	SYNC_TRACKER(3, "Synchronize with tracker", true, true),

	/** The SYN c_ web. */
	SYNC_WEB(4, "Synchronize with web", true, true)

	;

	// ---------------------------------------------------------------

	/** The index. */
	private int index;

	/** The name. */
	private String name;

	/** The def. */
	private Object value, def;

	/**
	 * Instantiates a new eTM preferences.
	 * @param index the index
	 * @param name the name
	 * @param value the value
	 * @param def the def
	 */
	private ETMPreferences(int index, String name, Object value, Object def) {
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
