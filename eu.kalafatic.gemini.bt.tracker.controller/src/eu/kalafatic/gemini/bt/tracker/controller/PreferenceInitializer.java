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
package eu.kalafatic.gemini.bt.tracker.controller;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;

/**
 * The Class class PreferenceInitializer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {}

	// ---------------------------------------------------------------

	/**
	 * Sets the up.
	 */
	public static void setUp() {
		try {
			boolean set = Activator.getPreferences().getBoolean(ETrackerPreferences.SET.getName(), (Boolean) ETrackerPreferences.SET.getDef());

			if (set) {
				return;
			}
			Activator.getPreferences().putBoolean(ETrackerPreferences.SET.getName(), (Boolean) ETrackerPreferences.SET.getDef());

			// THREADS
			Activator.getPreferences().putInt(ETrackerPreferences.MAX_CLIENT_THREADS.getName(), (Integer) ETrackerPreferences.MAX_CLIENT_THREADS.getDef());
			Activator.getPreferences().putInt(ETrackerPreferences.MAX_UPLD_THREADS.getName(), (Integer) ETrackerPreferences.MAX_UPLD_THREADS.getDef());

			// SCHEDULERS
			Activator.getPreferences().putInt(ETrackerPreferences.CONNECTION_TIMEOUT.getName(), (Integer) ETrackerPreferences.CONNECTION_TIMEOUT.getDef());
			Activator.getPreferences().putInt(ETrackerPreferences.TRACKERS_DELAY.getName(), (Integer) ETrackerPreferences.TRACKERS_DELAY.getDef());
			Activator.getPreferences().putInt(ETrackerPreferences.KEEP_ALIVE_CHECK_DELAY.getName(), (Integer) ETrackerPreferences.KEEP_ALIVE_CHECK_DELAY.getDef());

			// SYNCHRONIZATION
			// Activator.getPreferences().putBoolean(
			// ETrackerPreferences.SYNCHRONIZE_CLIENT.getName(),
			// (Boolean) ETrackerPreferences.SYNCHRONIZE_CLIENT.getDef());

			// TRANSPORT
			// Activator.getPreferences().putInt(
			// ETrackerPreferences.REQUESTED_PIECES_COUNT.getName(),
			// (Integer) ETrackerPreferences.REQUESTED_PIECES_COUNT.getDef());
			// Activator.getPreferences().putInt(ETrackerPreferences.TRANSPORT_BLOCK_SIZE.getName(),
			// (Integer) ETrackerPreferences.TRANSPORT_BLOCK_SIZE.getDef());
			// Activator.getPreferences().putInt(ETrackerPreferences.BLOCKS_IN_TRANSPORT_COUNT
			// .getName(),
			// (Integer) ETrackerPreferences.BLOCKS_IN_TRANSPORT_COUNT
			// .getDef());

			// COMMUNICATION
			Activator.getPreferences().putInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), (Integer) ETrackerPreferences.MESSAGE_QUEUE_SIZE.getDef());

			// Forces the application to save the Activator.getPreferences()
			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
