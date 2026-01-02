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

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.tm.controller.Activator;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.BundleUtils;
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

	/** The sync web btn. */
	private Button syncClientBtn, syncTrackerBtn, syncWebBtn;

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
			Group group = factory.createGroup(container, "Synchronization", 2);
			createSendCheckBox(group, factory);

		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the send check box.
	 * @param group the group
	 * @param factory the factory
	 */
	private void createSendCheckBox(final Group group, GUIFactory factory) {
		boolean value = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_CLIENT.getName(), (Boolean) ETMPreferences.SYNC_CLIENT.getDef());
		syncClientBtn = factory.createButton(group, ETMPreferences.SYNC_CLIENT.getName(), SWT.CHECK, LABEL_WIDTH, value);

		boolean clientInstalled = BundleUtils.getInstance().isPluginInstalled("eu.kalafatic.gemini.bt.client.view");

		syncClientBtn.setEnabled(clientInstalled);
		syncClientBtn.setGrayed(!clientInstalled);

		// --

		value = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_TRACKER.getName(), (Boolean) ETMPreferences.SYNC_TRACKER.getDef());
		syncTrackerBtn = factory.createButton(group, ETMPreferences.SYNC_TRACKER.getName(), SWT.CHECK, LABEL_WIDTH, value);

		boolean trackerInstalled = BundleUtils.getInstance().isPluginInstalled("eu.kalafatic.gemini.bt.tracker.view");

		syncTrackerBtn.setEnabled(trackerInstalled);
		syncTrackerBtn.setGrayed(!trackerInstalled);

		// --

		value = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_WEB.getName(), (Boolean) ETMPreferences.SYNC_WEB.getDef());
		syncWebBtn = factory.createButton(group, ETMPreferences.SYNC_WEB.getName(), SWT.CHECK, LABEL_WIDTH, value);
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is plugin installed.
	 * @param pluginId the plugin id
	 * @return true, if is plugin installed
	 */

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		Activator.getPreferences().putBoolean(ETMPreferences.SYNC_CLIENT.getName(), (Boolean) ETMPreferences.SYNC_CLIENT.getDef());
		syncClientBtn.setSelection((Boolean) ETMPreferences.SYNC_CLIENT.getDef());

		Activator.getPreferences().putBoolean(ETMPreferences.SYNC_TRACKER.getName(), (Boolean) ETMPreferences.SYNC_TRACKER.getDef());
		syncTrackerBtn.setSelection((Boolean) ETMPreferences.SYNC_TRACKER.getDef());

		Activator.getPreferences().putBoolean(ETMPreferences.SYNC_WEB.getName(), (Boolean) ETMPreferences.SYNC_WEB.getDef());
		syncWebBtn.setSelection((Boolean) ETMPreferences.SYNC_WEB.getDef());

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
			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_CLIENT.getName(), syncClientBtn.getSelection());

			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_TRACKER.getName(), syncTrackerBtn.getSelection());

			Activator.getPreferences().putBoolean(ETMPreferences.SYNC_WEB.getName(), syncWebBtn.getSelection());

			Activator.getPreferences().flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}
}
