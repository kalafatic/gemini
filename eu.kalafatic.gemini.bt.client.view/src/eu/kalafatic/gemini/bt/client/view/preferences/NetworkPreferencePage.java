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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ERemote;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.models.ComboData;
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

	/** The listen btn. */
	private Button listenBtn;

	/** The watch dog state text. */
	private Text clientListenPortText, trackerPortText, pingAddressText, watchdogDelayText, watchDogStateText;

	/** The watch dog state combo. */
	private Combo watchdogDelayCombo, watchDogStateCombo;

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
			Group group = factory.createGroup(container, "Client Ports", 3);
			createListenPortBox(group);
			createTrackerPortBox(group);

			group = factory.createGroup(container, "WatchDog", 3);
			createWatchDogAddressBox(group);
			createWatchDogDelayBox(group);
			createWatchDogStateBox(group);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(listenBtn)) {
			Activator.getPreferences().putBoolean(EBTClientPreferences.LISTENING_PEERS.getName(), listenBtn.getSelection());

		} else if (event.widget.equals(watchdogDelayCombo)) {
			ComboData comboData = (ComboData) watchdogDelayCombo.getData("comboData");
			comboData.defaultSelection = watchdogDelayCombo.getSelectionIndex();
			watchdogDelayText.setText(comboData.getDefLiteral());

			PREFERENCES.putInt(ECorePreferences.WATCHDOG_DELAY.name(), (comboData.getElement().getIntValue()));

		} else if (event.widget.equals(watchdogDelayCombo)) {
			String item = watchdogDelayCombo.getItem(watchdogDelayCombo.getSelectionIndex());
			watchdogDelayText.setText(item);
			PREFERENCES.putInt(ECorePreferences.WATCHDOG_DELAY.name(), Integer.parseInt(item));

		} else if (event.widget.equals(watchDogStateCombo)) {
			String item = watchDogStateCombo.getItem(watchDogStateCombo.getSelectionIndex());

			ERemote eRemote = null;

			if (item.equals(ERemote.AUTO.literal)) {
				eRemote = ERemote.AUTO;
			} else if (item.equals(ERemote.ENABLED.literal)) {
				eRemote = ERemote.ENABLED;
			} else if (item.equals(ERemote.DISABLED.literal)) {
				eRemote = ERemote.DISABLED;
			}
			PREFERENCES.put(ECorePreferences.PING_ENABLED.name(), eRemote.literal);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the listen port box.
	 * @param group the group
	 */
	private void createListenPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.BT_CLIENT_PORT.getName());

		clientListenPortText = factory.createText(group, ECorePreferences.BT_CLIENT_PORT.getName(), false);
		int value = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());
		clientListenPortText.setText(Integer.toString(value));

		listenBtn = factory.createButton(group, EBTClientPreferences.LISTENING_PEERS.getName(), SWT.CHECK);
		listenBtn.setToolTipText("To listen server\njust run any torrent");
		boolean listeningPeers = Activator.getPreferences().getBoolean(EBTClientPreferences.LISTENING_PEERS.getName(), (Boolean) EBTClientPreferences.LISTENING_PEERS.getDef());

		listenBtn.setSelection(listeningPeers);
		listenBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker port box.
	 * @param group the group
	 */
	private void createTrackerPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.TRACKER_PORT.getName());

		trackerPortText = factory.createText(group, ECorePreferences.TRACKER_PORT.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());

		trackerPortText.setText(Integer.toString(value));

		// outputDirText1.addModifyListener(new ModifyListener() {
		//
		// @Override
		// public void modifyText(ModifyEvent e) {
		// Text text = (Text) e.getSource();
		//
		// }
		// });
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog address box.
	 * @param group the group
	 */
	private void createWatchDogAddressBox(final Group group) {
		factory.createLabel(group, ECorePreferences.PING_ADDRESS.getName());

		pingAddressText = factory.createText(group, ECorePreferences.PING_ADDRESS.getName(), false, (byte) 2);

		String value = PREFERENCES.get(ECorePreferences.PING_ADDRESS.getName(), (String) ECorePreferences.PING_ADDRESS.getDef());

		pingAddressText.setText(value);
		pingAddressText.setEditable(true);

		pingAddressText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.getSource();
				pingAddressText.setText(text.getText());
				PREFERENCES.put(ECorePreferences.PING_ADDRESS.getName(), text.getText());
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog delay box.
	 * @param group the group
	 */
	private void createWatchDogDelayBox(final Group group) {
		factory.createLabel(group, ECorePreferences.WATCHDOG_DELAY.getName());

		watchdogDelayText = factory.createText(group, ECorePreferences.WATCHDOG_DELAY.getName(), false);
		int defVal = PREFERENCES.getInt(ECorePreferences.WATCHDOG_DELAY.getName(), ((Integer) ECorePreferences.WATCHDOG_DELAY.getDef()));

		watchdogDelayText.setText(Integer.toString(defVal));

		watchdogDelayCombo = factory.createCombo(group, ECorePreferences.WATCHDOG_DELAY.getName(), new ComboData(defVal - 5, 5, 1, 30));
		watchdogDelayCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog state box.
	 * @param group the group
	 */
	private void createWatchDogStateBox(final Group group) {
		factory.createLabel(group, ECorePreferences.PING_ENABLED.getName());

		watchDogStateText = factory.createText(group, ECorePreferences.PING_ENABLED.getName(), false);

		String value = PREFERENCES.get(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getDef());
		watchDogStateText.setText(value);

		watchDogStateCombo = factory.createCombo(group, ECorePreferences.PING_ENABLED.getName(), ERemote.asStringArray());
		watchDogStateCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		clientListenPortText.setText((String) ECorePreferences.BT_CLIENT_PORT.getDef());
		PREFERENCES.put(ECorePreferences.BT_CLIENT_PORT.getName(), (String) ECorePreferences.BT_CLIENT_PORT.getDef());

		trackerPortText.setText((String) ECorePreferences.TRACKER_PORT.getDef());
		PREFERENCES.put(ECorePreferences.TRACKER_PORT.getName(), (String) ECorePreferences.TRACKER_PORT.getDef());

		pingAddressText.setText((String) ECorePreferences.PING_ADDRESS.getDef());
		PREFERENCES.put(ECorePreferences.PING_ADDRESS.getName(), (String) ECorePreferences.PING_ADDRESS.getDef());

		watchdogDelayText.setText((String) ECorePreferences.WATCHDOG_DELAY.getDef());
		PREFERENCES.put(ECorePreferences.WATCHDOG_DELAY.getName(), (String) ECorePreferences.WATCHDOG_DELAY.getDef());

		watchDogStateText.setText((String) ECorePreferences.PING_ENABLED.getDef());
		PREFERENCES.put(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getDef());

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
