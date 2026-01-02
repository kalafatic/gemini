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
package eu.kalafatic.gemini.bt.tracker.model.tracker;

/**
 * The Interface interface ClientSession.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface ClientSession extends Session {

	/**
	 * Checks if is seed.
	 * @return true, if is seed
	 */
	boolean isSeed();

	/**
	 * Sets the seed.
	 * @param value the new seed
	 */
	void setSeed(boolean value);

	/**
	 * Gets the last connection.
	 * @return the last connection
	 */
	long getLastConnection();

	/**
	 * Sets the last connection.
	 * @param value the new last connection
	 */
	void setLastConnection(long value);

} // ClientSession
