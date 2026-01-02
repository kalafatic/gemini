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
package eu.kalafatic.gemini.bt.client.net.rc.view.lib;

import eu.kalafatic.gemini.core.interfaces.IPreference;

/**
 * The Enum enum EBTRCPreferences.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum EBTRCPreferences implements IPreference {

	/** The SET. */
	SET(0, "Set", false, false),

	/** The MODULE. */
	MODULE(1, "BT Remote Control", "", ""),

	;

	// ---------------------------------------------------------------

	/** The index. */
	private int index;

	/** The name. */
	private String name;

	/** The def. */
	private Object value, def;

	/**
	 * Instantiates a new eBTRC preferences.
	 * @param index the index
	 * @param name the name
	 * @param value the value
	 * @param def the def
	 */
	private EBTRCPreferences(int index, String name, Object value, Object def) {
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
