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
package eu.kalafatic.gemini.bt.client.net.rc.controller;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import eu.kalafatic.gemini.bt.client.net.rc.controller.lib.ERCPreferences;

/**
 * The Class class PreferenceInitializer.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

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
			boolean set = Activator.getPreferences().getBoolean(ERCPreferences.SET.getName(), (Boolean) ERCPreferences.SET.getDef());

			if (set) {
				return;
			}
			Activator.getPreferences().putBoolean(ERCPreferences.SET.getName(), true);
			// Forces the application to save the Activator.getPreferences()
			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
