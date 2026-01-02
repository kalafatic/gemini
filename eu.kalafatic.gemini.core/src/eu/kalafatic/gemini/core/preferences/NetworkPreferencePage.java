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
package eu.kalafatic.gemini.core.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class NetworkPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetworkPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The rc port text. */
	private Text homeSiteText, updateSiteText, bugSiteText, mailSiteText, updatePortText, internalPortText, rcPortText;

	/**
	 * Instantiates a new network preference page.
	 */
	public NetworkPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Core Preferences");
	}

	// ---------------------------------------------------------------
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

			Group group = factory.createGroup(container, "Protocols", 2);
			createProtocolsBox(group);

			group = factory.createGroup(container, "Address", 2);
			createHomeAddressBox(group);
			createUpdateAddressBox(group);
			createBugsAddressBox(group);
			createMailAddressBox(group);

			group = factory.createGroup(container, "Ports", 2);
			createListenPortBox(group);
			createUpdatePortBox(group);
			createRCPortBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the protocols box.
	 * @param group the group
	 */
	private void createProtocolsBox(Group group) {

		Composite composite = factory.createComposite(group, 2);

		final Button ipv4Btn = factory.createButton(composite, ECorePreferences.IP_PROTOCOL.getName() + "4", SWT.CHECK, LABEL_WIDTH);

		int protocolValue = PREFERENCES.getInt(ECorePreferences.IP_PROTOCOL.getName(), ((Integer) ECorePreferences.IP_PROTOCOL.getDef()));

		ipv4Btn.setSelection(protocolValue == 4 || protocolValue == 46);

		final Button ipv6Btn = factory.createButton(composite, ECorePreferences.IP_PROTOCOL.getName() + "6", SWT.CHECK, LABEL_WIDTH);

		ipv6Btn.setSelection(protocolValue == 6 || protocolValue == 46);

		SelectionListener ipSelectionListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int result = 6;

				if (ipv4Btn.getSelection()) {
					if (ipv6Btn.getSelection()) {
						result = 46;
					} else {
						result = 4;
					}
				}
				PREFERENCES.putInt(ECorePreferences.IP_PROTOCOL.getName(), result);
			}
		};
		ipv4Btn.addSelectionListener(ipSelectionListener);
		ipv6Btn.addSelectionListener(ipSelectionListener);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the home address box.
	 * @param group the group
	 */
	private void createHomeAddressBox(final Group group) {
		String value = PREFERENCES.get(ECorePreferences.HOME_SITE.getName(), (String) ECorePreferences.HOME_SITE.getDef());
		factory.createHyperlink(group, ECorePreferences.HOME_SITE.getName(), value);
		homeSiteText = factory.createText(group, ECorePreferences.HOME_SITE.getName(), true);
		homeSiteText.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the update address box.
	 * @param group the group
	 */
	private void createUpdateAddressBox(final Group group) {
		String value = PREFERENCES.get(ECorePreferences.UPDATE_SITE.getName(), (String) ECorePreferences.UPDATE_SITE.getDef());
		factory.createHyperlink(group, ECorePreferences.UPDATE_SITE.getName(), value);
		updateSiteText = factory.createText(group, ECorePreferences.UPDATE_SITE.getName(), false);
		updateSiteText.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the bugs address box.
	 * @param group the group
	 */
	private void createBugsAddressBox(final Group group) {
		String value = PREFERENCES.get(ECorePreferences.BUG_SITE.getName(), (String) ECorePreferences.UPDATE_SITE.getDef());
		factory.createHyperlink(group, ECorePreferences.BUG_SITE.getName(), value);
		bugSiteText = factory.createText(group, ECorePreferences.BUG_SITE.getName(), false);
		bugSiteText.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the mail address box.
	 * @param group the group
	 */
	private void createMailAddressBox(final Group group) {
		String value = PREFERENCES.get(ECorePreferences.MAIL_SITE.getName(), (String) ECorePreferences.UPDATE_SITE.getDef());
		factory.createHyperlink(group, ECorePreferences.MAIL_SITE.getName(), value);
		mailSiteText = factory.createText(group, ECorePreferences.MAIL_SITE.getName(), false);
		mailSiteText.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the listen port box.
	 * @param group the group
	 */
	private void createListenPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.SINGLETON_ARGS_PORT.getName(), SWT.NONE);
		internalPortText = factory.createText(group, ECorePreferences.SINGLETON_ARGS_PORT.getName(), false);
		int value = PREFERENCES.getInt(ECorePreferences.SINGLETON_ARGS_PORT.getName(), (Integer) ECorePreferences.SINGLETON_ARGS_PORT.getDef());
		internalPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the update port box.
	 * @param group the group
	 */
	private void createUpdatePortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.UPDATE_PORT.getName(), SWT.NONE);
		updatePortText = factory.createText(group, ECorePreferences.UPDATE_PORT.getName(), false);
		int value = PREFERENCES.getInt(ECorePreferences.UPDATE_PORT.getName(), (Integer) ECorePreferences.UPDATE_PORT.getDef());
		updatePortText.setText(Integer.toString(value));
		updatePortText.setEditable(false);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the rc port box.
	 * @param group the group
	 */
	private void createRCPortBox(Group group) {
		factory.createLabel(group, ECorePreferences.RC_PORT.getName(), SWT.NONE);
		rcPortText = factory.createText(group, ECorePreferences.RC_PORT.getName(), false);
		int value = PREFERENCES.getInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());
		rcPortText.setText(Integer.toString(value));
		rcPortText.setEditable(false);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		try {
			homeSiteText.setText((String) ECorePreferences.HOME_SITE.getDef());
			PREFERENCES.put(ECorePreferences.HOME_SITE.getName(), (String) ECorePreferences.HOME_SITE.getDef());

			updateSiteText.setText((String) ECorePreferences.UPDATE_SITE.getDef());
			PREFERENCES.put(ECorePreferences.UPDATE_SITE.getName(), (String) ECorePreferences.UPDATE_SITE.getDef());

			bugSiteText.setText((String) ECorePreferences.BUG_SITE.getDef());
			PREFERENCES.put(ECorePreferences.BUG_SITE.getName(), (String) ECorePreferences.BUG_SITE.getDef());

			mailSiteText.setText((String) ECorePreferences.MAIL_SITE.getDef());
			PREFERENCES.put(ECorePreferences.MAIL_SITE.getName(), (String) ECorePreferences.MAIL_SITE.getDef());

			internalPortText.setText((String) ECorePreferences.SINGLETON_ARGS_PORT.getDef());
			PREFERENCES.put(ECorePreferences.SINGLETON_ARGS_PORT.getName(), (String) ECorePreferences.SINGLETON_ARGS_PORT.getDef());

			updatePortText.setText((String) ECorePreferences.UPDATE_PORT.getDef());
			PREFERENCES.putInt(ECorePreferences.UPDATE_PORT.getName(), (Integer) ECorePreferences.UPDATE_PORT.getDef());

			rcPortText.setText((String) ECorePreferences.RC_PORT.getDef());
			PREFERENCES.putInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());

			super.performDefaults();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		try {
			PREFERENCES.put(ECorePreferences.HOME_SITE.getName(), homeSiteText.getText());

			PREFERENCES.put(ECorePreferences.UPDATE_SITE.getName(), updateSiteText.getText());

			PREFERENCES.put(ECorePreferences.BUG_SITE.getName(), bugSiteText.getText());

			PREFERENCES.put(ECorePreferences.MAIL_SITE.getName(), mailSiteText.getText());

			PREFERENCES.putInt(ECorePreferences.SINGLETON_ARGS_PORT.getName(), Integer.parseInt(internalPortText.getText()));

			PREFERENCES.putInt(ECorePreferences.UPDATE_PORT.getName(), Integer.parseInt(updatePortText.getText()));

			PREFERENCES.putInt(ECorePreferences.RC_PORT.getName(), Integer.parseInt(rcPortText.getText()));

		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
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
			PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
