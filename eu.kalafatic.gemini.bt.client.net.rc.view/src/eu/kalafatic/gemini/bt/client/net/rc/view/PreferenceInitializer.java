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
package eu.kalafatic.gemini.bt.client.net.rc.view;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.rc.view.lib.EBTRCPreferences;

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
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up.
	 */
	public static void setUp() {
		Preferences preferences = Platform.getPreferencesService().getRootNode().node(Activator.PLUGIN_ID);

		boolean set = preferences.getBoolean(EBTRCPreferences.SET.getName(), (Boolean) EBTRCPreferences.SET.getDef());

		if (set) {
			return;
		}
		preferences.putBoolean(EBTRCPreferences.SET.getName(), true);

		try {
			// Forces the application to save the preferences
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
}
