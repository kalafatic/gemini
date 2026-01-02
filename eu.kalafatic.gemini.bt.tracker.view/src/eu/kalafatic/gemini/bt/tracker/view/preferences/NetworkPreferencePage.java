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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.NUMBERS;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ERemote;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
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

	/** The client listen port text. */
	private Text clientListenPortText;

	/** The tracker listen port text. */
	private Text trackerListenPortText;

	/** The tracker upload port text. */
	private Text trackerUploadPortText;

	/** The watch dog address text. */
	private Text watchDogAddressText;

	/** The watch dog delay text. */
	private Text watchDogDelayText;

	/** The watch dog state text. */
	private Text watchDogStateText;

	/** The watch dog state combo. */
	private Combo watchDogStateCombo;

	/**
	 * Instantiates a new network preference page.
	 */
	public NetworkPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
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
			Group group = factory.createGroup(container, "Tracker Ports", 2);
			createClientListenPortBox(group);
			createTrackerListenPortBox(group);
			createTrackerUploadPortBox(group);

			group = factory.createGroup(container, "WatchDog", 3);
			createWatchDogAddressBox(group);
			createWatchDogDelayBox(group);
			createWatchDogStateBox(group);
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(watchDogStateCombo)) {
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
	 * Creates the client listen port box.
	 * @param group the group
	 */
	private void createClientListenPortBox(final Group group) {
		factory.createLabel(group, ECorePreferences.BT_CLIENT_PORT.getName(), SWT.NONE);

		clientListenPortText = factory.createText(group, ECorePreferences.BT_CLIENT_PORT.getName(), true);

		int value = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		clientListenPortText.setText(Integer.toString(value));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker listen port box.
	 * @param group the group
	 */
	private void createTrackerListenPortBox(final Group group) {
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

		// trackerUploadPortText.addModifyListener(new ModifyListener() {
		//
		// @Override
		// public void modifyText(ModifyEvent e) {
		// Text text = (Text) e.getSource();
		// trackerUploadPortText.setText(text.getText());
		// corePreferences.putInt(ECorePreferences.TRACKER_UPLOAD_PORT
		// .getName(), Integer.parseInt(text.getText()));
		// }
		// });
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog address box.
	 * @param group the group
	 */
	private void createWatchDogAddressBox(final Group group) {
		factory.createLabel(group, ECorePreferences.PING_ADDRESS.getName(), SWT.NONE);

		watchDogAddressText = factory.createText(group, ECorePreferences.PING_ADDRESS.getName(), false);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		watchDogAddressText.setLayoutData(gridData);

		String value = PREFERENCES.get(ECorePreferences.PING_ADDRESS.getName(), (String) ECorePreferences.PING_ADDRESS.getDef());

		watchDogAddressText.setText(value);
		watchDogAddressText.setEditable(true);

		// watchDogAddressText.addModifyListener(new ModifyListener() {
		//
		// @Override
		// public void modifyText(ModifyEvent e) {
		// Text text = (Text) e.getSource();
		//
		// watchDogAddressText.setText(text.getText());
		// preferences.put(ETrackerPreferences.PING_ADDRESS.getName(),
		// text.getText());
		// }
		// });
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog delay box.
	 * @param group the group
	 */
	private void createWatchDogDelayBox(final Group group) {
		factory.createLabel(group, ECorePreferences.WATCHDOG_DELAY.getName(), SWT.NONE);

		watchDogDelayText = factory.createText(group, ECorePreferences.WATCHDOG_DELAY.getName(), false);

		int value = Activator.getPreferences().getInt(ECorePreferences.WATCHDOG_DELAY.getName(), (Integer) ECorePreferences.WATCHDOG_DELAY.getDef());

		watchDogDelayText.setText(Integer.toString(value));

		final Combo combo = factory.createCombo(group, ECorePreferences.WATCHDOG_DELAY.getName(), SWT.NULL);

		String[] counts = NUMBERS;

		for (int i = 0; i < counts.length; i++) {
			combo.add(counts[i]);
		}

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String item = combo.getItem(combo.getSelectionIndex());

				watchDogDelayText.setText(item);

				Activator.getPreferences().putInt(ECorePreferences.WATCHDOG_DELAY.getName(), Integer.parseInt(item));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

				String text = combo.getText();
				if (combo.indexOf(text) < 0) {
					combo.add(text);
					// Re-sort
					String[] items = combo.getItems();
					Arrays.sort(items);
					combo.setItems(items);
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the watch dog state box.
	 * @param group the group
	 */
	private void createWatchDogStateBox(Group group) {
		factory.createLabel(group, ECorePreferences.PING_ENABLED.getName(), SWT.NONE);

		watchDogStateText = factory.createText(group, ECorePreferences.PING_ENABLED.getName(), false);
		String value = Activator.getPreferences().get(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getValue());
		watchDogStateText.setText(value);

		watchDogStateCombo = factory.createCombo(group, ECorePreferences.PING_ENABLED.getName(), ERemote.asStringArray());
		watchDogStateCombo.addListener(SWT.Selection, this);

		factory.createLabel(group);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		int value = (Integer) ECorePreferences.BT_CLIENT_PORT.getDef();

		clientListenPortText.setText(Integer.toString(value));
		PREFERENCES.putInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		value = (Integer) ECorePreferences.TRACKER_PORT.getDef();

		trackerListenPortText.setText(Integer.toString(value));
		PREFERENCES.putInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());

		value = (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef();

		trackerUploadPortText.setText(Integer.toString(value));
		PREFERENCES.putInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef());

		String stringValue = (String) ECorePreferences.PING_ADDRESS.getDef();

		watchDogAddressText.setText(stringValue);
		Activator.getPreferences().put(ECorePreferences.PING_ADDRESS.getName(), (String) ECorePreferences.PING_ADDRESS.getDef());

		value = (Integer) ECorePreferences.WATCHDOG_DELAY.getDef();

		watchDogDelayText.setText(Integer.toString(value));
		PREFERENCES.putInt(ECorePreferences.WATCHDOG_DELAY.getName(), (Integer) ECorePreferences.WATCHDOG_DELAY.getDef());

		stringValue = (String) ECorePreferences.PING_ENABLED.getDef();

		watchDogStateText.setText(stringValue);
		Activator.getPreferences().put(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getDef());

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

			Activator.getPreferences().put(ECorePreferences.PING_ADDRESS.getName(), watchDogAddressText.getText());

			Activator.getPreferences().putInt(ECorePreferences.WATCHDOG_DELAY.getName(), Integer.parseInt(watchDogDelayText.getText()));

			Activator.getPreferences().put(ECorePreferences.PING_ENABLED.getName(), watchDogStateText.getText());

			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
