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
package eu.kalafatic.gemini.bt.tm.controller;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.osgi.service.prefs.BackingStoreException;

import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.core.lib.ECorePreferences;

/**
 * The Class class PreferenceInitializer.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer# initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		setUp();
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up.
	 */
	public static void setUp() {
		try {
			boolean set = Activator.getPreferences().getBoolean(ETMPreferences.SET.getName(), (Boolean) ETMPreferences.SET.getDef());

			if (set) {
				return;
			}
			Activator.getPreferences().putBoolean(ECorePreferences.SET.getName(), true);

			// OPTIONS
			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_CLIENT.getName(), (Boolean) ETMPreferences.SYNC_CLIENT.getDef());
			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_TRACKER.getName(), (Boolean) ETMPreferences.SYNC_TRACKER.getDef());
			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_WEB.getName(), (Boolean) ETMPreferences.SYNC_WEB.getDef());

			// Forces the application to save the preferences
			Activator.getPreferences().flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

}
