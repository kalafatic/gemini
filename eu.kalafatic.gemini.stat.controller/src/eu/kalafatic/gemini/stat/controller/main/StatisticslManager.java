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
package eu.kalafatic.gemini.stat.controller.main;

/**
 * The Class class StatisticslManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticslManager {

	/** The INSTANCE. */
	private static StatisticslManager INSTANCE = null;

	/**
	 * Gets the single instance of StatisticslManager.
	 * @return single instance of StatisticslManager
	 */
	public static StatisticslManager getInstance() {
		if (INSTANCE == null) {
			synchronized (StatisticslManager.class) {
				INSTANCE = new StatisticslManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

}
