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
package eu.kalafatic.gemini.bt.tracker.view.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class OptionsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OptionsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The sync db btn. */
	private Button syncClientBtn, syncFileBtn, syncDBBtn;

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
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub

	}

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
			Group group = factory.createGroup(container, "Synchronization", 2);
			createSynchronizationBox(group);

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the synchronization box.
	 * @param group the group
	 */
	private void createSynchronizationBox(Group group) {
		syncClientBtn = factory.createButton(group, ECorePreferences.SYNC_CLIENT.getName(), SWT.CHECK, LABEL_WIDTH);
		syncFileBtn = factory.createButton(group, ECorePreferences.SYNC_FILES.getName(), SWT.CHECK, LABEL_WIDTH);
		syncDBBtn = factory.createButton(group, ECorePreferences.SYNC_DB.getName(), SWT.CHECK, LABEL_WIDTH);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		syncClientBtn.setSelection((Boolean) ECorePreferences.SYNC_CLIENT.getDef());
		PREFERENCES.putBoolean(ECorePreferences.SYNC_CLIENT.getName(), (Boolean) ECorePreferences.SYNC_CLIENT.getDef());

		syncFileBtn.setSelection((Boolean) ECorePreferences.SYNC_FILES.getDef());
		PREFERENCES.putBoolean(ECorePreferences.SYNC_FILES.getName(), (Boolean) ECorePreferences.SYNC_FILES.getDef());

		syncDBBtn.setSelection((Boolean) ECorePreferences.SYNC_DB.getDef());
		PREFERENCES.putBoolean(ECorePreferences.SYNC_DB.getName(), (Boolean) ECorePreferences.SYNC_DB.getDef());
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
			PREFERENCES.putBoolean(ECorePreferences.SYNC_CLIENT.getName(), syncClientBtn.getSelection());

			PREFERENCES.putBoolean(ECorePreferences.SYNC_FILES.getName(), syncFileBtn.getSelection());

			PREFERENCES.putBoolean(ECorePreferences.SYNC_DB.getName(), syncDBBtn.getSelection());

			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
