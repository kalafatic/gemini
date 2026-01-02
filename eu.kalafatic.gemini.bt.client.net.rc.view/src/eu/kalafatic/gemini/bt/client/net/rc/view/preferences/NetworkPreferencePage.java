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
package eu.kalafatic.gemini.bt.client.net.rc.view.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.rc.view.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;

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

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org .eclipse.swt.widgets.Event)
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
		container = factory.createComposite(parent, 1);

		Group group = factory.createGroup(container, "Ports", 2);
		// createEnableRCBox(group);
		createRemotePortBox(group);

		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the remote port box.
	 * @param group the group
	 */
	private void createRemotePortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.RC_PORT.getName(), SWT.NONE);

		remotePortText = factory.createText(group, ECorePreferences.RC_PORT.getName(), true);
		int value = PREFERENCES.getInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());
		remotePortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		try {
			PREFERENCES.putInt(ECorePreferences.RC_PORT.getName(), Integer.parseInt(remotePortText.getText()));

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
