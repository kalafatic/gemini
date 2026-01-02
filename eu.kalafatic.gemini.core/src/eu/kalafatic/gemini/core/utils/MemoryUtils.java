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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import eu.kalafatic.gemini.core.lib.ECorePreferences;

/**
 * The Class class MemoryUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MemoryUtils {

	/** The heap size. */
	private long heapSize;

	/** The Constant INSTANCE. */
	public static final MemoryUtils INSTANCE = new MemoryUtils();

	/** The max app memory. */
	private int maxAppMemory;

	/** The heap size mb. */
	private long heapSizeMB;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the memory performance.
	 * @return the memory performance
	 */
	public float getMemoryPerformance() {
		heapSize = Runtime.getRuntime().totalMemory();

		maxAppMemory = PREFERENCES.getInt(ECorePreferences.MAX_APP_MEMORY.getName(), (Integer) ECorePreferences.MAX_APP_MEMORY.getDef());

		heapSizeMB = heapSize / (1024 * 1024);

		float onePercent = (maxAppMemory * 100) / 100;
		float performance = 100 * (heapSizeMB / onePercent);

		return performance;
	}
}
