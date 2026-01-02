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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class OptionsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OptionsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new options preference page.
	 */
	// private Button soundEnabledBtn;

	/**
	 * Instantiates a new options preference page.
	 */
	public OptionsPreferencePage() {
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
			// Group group = factory.createGroup(container, "Sounds", 1);
			// createSoundBox(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sound box.
	 * @param group the group
	 */
	// private void createSoundBox(final Group group) {
	// soundEnabledBtn = factory.createButton(group,
	// ECorePreferences.SOUND.getName(), SWT.CHECK, LABEL_WIDTH);
	//
	// boolean value = PREFERENCES.getBoolean(
	// ECorePreferences.SOUND.getName(),
	// ((Boolean) ECorePreferences.SOUND.getDef()));
	//
	// soundEnabledBtn.setSelection(value);
	//
	// }

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		// soundEnabledBtn.setSelection((Boolean)
		// ECorePreferences.SOUND.getDef());
		// PREFERENCES.putBoolean(ECorePreferences.SOUND.getName(),
		// (Boolean) ECorePreferences.SOUND.getDef());

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
			// PREFERENCES.putBoolean(ECorePreferences.SOUND.getName(),
			// soundEnabledBtn.getSelection());

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
			PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
