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
package eu.kalafatic.gemini.webbrowser.view.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.webbrowser.controller.Activator;

/**
 * The Class class NetworkPreferencePage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class NetworkPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The container. */
	private Composite container;

	/** The remote port text. */
	private Text remotePortText;

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
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		factory = new GUIFactory(parent);
		container = factory.createComposite(parent, 1);

		Group group = factory.createGroup(container, "Ports", 2);
		createWebPortBox(group);

		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the web port box.
	 * @param group the group
	 */
	private void createWebPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.WEB_UPLOAD_PORT.getName(), SWT.NONE);
		remotePortText = factory.createText(group, ECorePreferences.RC_PORT.getName(), false);
		int value = PREFERENCES.getInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());
		remotePortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		remotePortText.setText(((Integer) ECorePreferences.RC_PORT.getDef()).toString());
		PREFERENCES.putInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());

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
			PREFERENCES.putInt(ECorePreferences.WEB_UPLOAD_PORT.getName(), Integer.parseInt(remotePortText.getText()));

			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}