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
package eu.kalafatic.gemini.bt.tm.view.preferences;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.osgi.service.prefs.Preferences;

import eu.kalafatic.gemini.bt.tm.controller.PreferenceInitializer;
import eu.kalafatic.gemini.bt.tm.view.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class TMPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

public class TMPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The reset btn. */
	private Button setBtn, resetBtn;

	/** The preferences. */
	private Preferences preferences;

	/**
	 * Instantiates a new TM preference page.
	 */
	public TMPreferencePage() {
		super.noDefaultAndApplyButton();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Activator.getPluginProperties().getString("TMPreferencePageDescription"));
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		try {
			if (event.widget == setBtn) {
				boolean set = setBtn.getSelection();
				preferences.putBoolean(ECorePreferences.SET.getName(), set);
				preferences.flush();
				resetBtn.setEnabled(!set);
			}
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		try {
			factory = new GUIFactory(parent);
			Composite container = factory.createComposite(parent, 1);

			Group group = factory.createGroup(container, "Reset settings", 2);
			createReloadBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the reload box.
	 * @param group the group
	 */
	private void createReloadBox(final Group group) {
		preferences = Platform.getPreferencesService().getRootNode().node(Activator.PLUGIN_ID);
		boolean set = preferences.getBoolean(ECorePreferences.SET.getName(), (Boolean) ECorePreferences.SET.getDef());

		setBtn = factory.createButton(group, ECorePreferences.SET.getName(), SWT.CHECK, set, this);

		resetBtn = factory.createButton(group, "Reset", SWT.PUSH);
		resetBtn.setEnabled(!set);

		resetBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PreferenceInitializer.setUp();
				setBtn.setSelection(true);
				setBtn.notifyListeners(SWT.Selection, new Event());
			}
		});
	}
}