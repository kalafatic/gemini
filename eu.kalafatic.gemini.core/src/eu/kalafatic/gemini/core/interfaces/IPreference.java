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
package eu.kalafatic.gemini.core.interfaces;

/**
 * The Interface interface IPreference.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface IPreference {

	/**
	 * Gets the name.
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the value.
	 * @return the value
	 */
	Object getValue();

	/**
	 * Gets the def.
	 * @return the def
	 */
	Object getDef();

}
