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
package eu.kalafatic.gemini.core.utils;

import java.util.Properties;

/**
 * The Class class OSUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OSUtils {

	/** The properties. */
	private Properties properties = System.getProperties();

	/** The Constant INSTANCE. */
	public static final OSUtils INSTANCE = new OSUtils();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the OS name.
	 * @return the OS name
	 */
	public String getOSName() {
		return properties.getProperty("os.name");
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the OS arch.
	 * @return the OS arch
	 */
	public String getOSArch() {
		return properties.getProperty("os.arch");
	}
}
