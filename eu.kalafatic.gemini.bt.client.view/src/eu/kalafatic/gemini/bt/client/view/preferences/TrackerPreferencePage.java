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
package eu.kalafatic.gemini.bt.client.view.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The scrape enabled btn. */
	private Button scrapeEnabledBtn;

	/**
	 * Instantiates a new tracker preference page.
	 */
	public TrackerPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		factory = new GUIFactory(parent);
		Composite container = factory.createComposite(parent, 1);
		try {
			Group group = factory.createGroup(container, "Tracker", 3);
			createScrapeBox(group);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the scrape box.
	 * @param group the group
	 */
	private void createScrapeBox(final Group group) {
		scrapeEnabledBtn = factory.createButton(group, EBTClientPreferences.TRACKER_SCRAPE.getName(), SWT.CHECK, (byte) 2);
		boolean value = Activator.getPreferences().getBoolean(EBTClientPreferences.TRACKER_SCRAPE.getName(), ((Boolean) EBTClientPreferences.TRACKER_SCRAPE.getDef()));
		scrapeEnabledBtn.setSelection(value);
		scrapeEnabledBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		Activator.getPreferences().putBoolean(EBTClientPreferences.TRACKER_SCRAPE.getName(), (Boolean) EBTClientPreferences.TRACKER_SCRAPE.getDef());

		super.performDefaults();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		try {
			Activator.getPreferences().putBoolean(EBTClientPreferences.TRACKER_SCRAPE.getName(), scrapeEnabledBtn.getSelection());

			// Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		try {
			// Forces the application to save the CORE preferences
			Activator.getPreferences().flush();
			// PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
