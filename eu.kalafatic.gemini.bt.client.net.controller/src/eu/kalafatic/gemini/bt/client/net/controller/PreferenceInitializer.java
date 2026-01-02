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
package eu.kalafatic.gemini.bt.client.net.controller;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;

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
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer# initializeDefaultPreferences()
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
			boolean set = Activator.getPreferences().getBoolean(EBTClientPreferences.SET.getName(), (Boolean) EBTClientPreferences.SET.getDef());

			if (set) {
				return;
			}
			Activator.getPreferences().putBoolean(EBTClientPreferences.SET.getName(), true);

			// Forces the application to save the preferences
			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
