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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tm.controller.Activator;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class NetworkPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetworkPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The web upload port text. */
	private Text clientListenPortText, trackerListenPortText, trackerUploadPortText, webUploadPortText;

	/**
	 * Instantiates a new network preference page.
	 */
	public NetworkPreferencePage() {
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
			Group group = factory.createGroup(container, "Torrent Maker Ports", 2);
			createWebPortBox(group);
			createListenPortBox(group);
			createTrackerPortBox(group);
			createTrackerUploadPortBox(group);

		} catch (Exception e) {
			Log.log(ETMPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the web port box.
	 * @param group the group
	 */
	private void createWebPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.WEB_UPLOAD_PORT.getName(), SWT.NONE);

		webUploadPortText = factory.createText(group, ECorePreferences.WEB_UPLOAD_PORT.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.WEB_UPLOAD_PORT.getName(), (Integer) ECorePreferences.WEB_UPLOAD_PORT.getDef());

		webUploadPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the listen port box.
	 * @param group the group
	 */
	private void createListenPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.BT_CLIENT_PORT.getName(), SWT.NONE);

		clientListenPortText = factory.createText(group, ECorePreferences.BT_CLIENT_PORT.getName(), true);

		int value = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		clientListenPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker port box.
	 * @param group the group
	 */
	private void createTrackerPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.TRACKER_PORT.getName(), SWT.NONE);

		trackerListenPortText = factory.createText(group, ECorePreferences.TRACKER_PORT.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());

		trackerListenPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker upload port box.
	 * @param group the group
	 */
	private void createTrackerUploadPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.TRACKER_UPLOAD_PORT.getName(), SWT.NONE);

		trackerUploadPortText = factory.createText(group, ECorePreferences.TRACKER_UPLOAD_PORT.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef());

		trackerUploadPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		clientListenPortText.setText(Integer.toString((Integer) ECorePreferences.BT_CLIENT_PORT.getDef()));
		PREFERENCES.putInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		trackerListenPortText.setText(Integer.toString((Integer) ECorePreferences.TRACKER_PORT.getDef()));
		PREFERENCES.putInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());

		trackerUploadPortText.setText(Integer.toString((Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef()));
		PREFERENCES.putInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef());

		webUploadPortText.setText(Integer.toString((Integer) ECorePreferences.WEB_UPLOAD_PORT.getDef()));
		PREFERENCES.putInt(ECorePreferences.WEB_UPLOAD_PORT.getName(), (Integer) ECorePreferences.WEB_UPLOAD_PORT.getDef());

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
			PREFERENCES.putInt(ECorePreferences.BT_CLIENT_PORT.getName(), Integer.parseInt(clientListenPortText.getText()));

			PREFERENCES.putInt(ECorePreferences.TRACKER_PORT.getName(), Integer.parseInt(trackerListenPortText.getText()));

			PREFERENCES.putInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), Integer.parseInt(trackerUploadPortText.getText()));

			PREFERENCES.putInt(ECorePreferences.WEB_UPLOAD_PORT.getName(), Integer.parseInt(webUploadPortText.getText()));

			Activator.getPreferences().flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}
}
