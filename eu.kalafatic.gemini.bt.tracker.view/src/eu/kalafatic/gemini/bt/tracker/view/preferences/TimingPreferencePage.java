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

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TimingPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TimingPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The trackers delay text. */
	private Text connTimeoutText, readTimeoutText, nextTimeoutText, pauseTimeoutText, speedTimeoutText, trackersDelayText;

	/** The trackers delay combo. */
	private Combo connTimeoutCombo, readTimeoutCombo, nextTimeoutCombo, pauseTimeoutCombo, speedTimeoutCombo, trackersDelayCombo;

	/**
	 * Instantiates a new timing preference page.
	 */
	public TimingPreferencePage() {
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
			Group group = factory.createGroup(container, "Workers timing (ms)", 2);
			createConnectionTimeoutBox(group);
			// createPeerSearchTimeoutBox(group);
			// createWaitForNextTaskBox(group);
			// createPausedBox(group);

			// group = factory.createGroup(container, "Delays", 3);
			//
			// createSpeedComputeDelayBox(group);
			// createTrackerDelayBox(group);

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
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
		// if (event.widget.equals(connTimeoutCombo)) {
		// String item = connTimeoutCombo.getItem(connTimeoutCombo
		// .getSelectionIndex());
		// connTimeoutText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.CLIENT_TIMEOUT.getName(),
		// Integer.parseInt(item));
		//
		// } else if (event.widget.equals(readTimeoutCombo)) {
		// String item = readTimeoutCombo.getItem(readTimeoutCombo
		// .getSelectionIndex());
		// readTimeoutText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.READ_TIMEOUT.getName(),
		// Integer.parseInt(item));
		//
		// } else if (event.widget.equals(nextTimeoutCombo)) {
		// String item = nextTimeoutCombo.getItem(nextTimeoutCombo
		// .getSelectionIndex());
		// nextTimeoutText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.NEXT_TIMEOUT.getName(),
		// Integer.parseInt(item));
		//
		// } else if (event.widget.equals(pauseTimeoutCombo)) {
		// String item = pauseTimeoutCombo.getItem(pauseTimeoutCombo
		// .getSelectionIndex());
		// pauseTimeoutText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(),
		// Integer.parseInt(item));
		//
		// } else if (event.widget.equals(speedTimeoutCombo)) {
		// String item = speedTimeoutCombo.getItem(speedTimeoutCombo
		// .getSelectionIndex());
		// speedTimeoutText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.SPEED_DELAY.getName(),
		// Integer.parseInt(item));
		//
		// } else if (event.widget.equals(trackersDelayCombo)) {
		// String item = trackersDelayCombo.getItem(trackersDelayCombo
		// .getSelectionIndex());
		// trackersDelayText.setText(item);
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.TRACKERS_DELAY.getName(),
		// Integer.parseInt(item));
		// }
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the connection timeout box.
	 * @param group the group
	 */
	private void createConnectionTimeoutBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.CLIENT_TIMEOUT.getName());
		//
		// connTimeoutText = factory.createText(group,
		// EBTClientPreferences.CLIENT_TIMEOUT.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.CLIENT_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.CLIENT_TIMEOUT.getDef());
		// connTimeoutText.setText(Integer.toString(defVal));
		//
		// connTimeoutCombo = factory.createCombo(group,
		// ECorePreferences.WATCHDOG_DELAY.getName(), new ComboData(
		// (defVal / 100) - 1, 100, 100, 100), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the peer search timeout box.
	 * @param group the group
	 */
	private void createPeerSearchTimeoutBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.READ_TIMEOUT.getName());
		//
		// readTimeoutText = factory.createText(group,
		// EBTClientPreferences.READ_TIMEOUT.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.READ_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.READ_TIMEOUT.getDef());
		// readTimeoutText.setText(Integer.toString(defVal));
		//
		// readTimeoutCombo = factory.createCombo(group,
		// EBTClientPreferences.READ_TIMEOUT.getName(), new ComboData(
		// (defVal / 100) - 1, 100, 100, 100), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the wait for next task box.
	 * @param group the group
	 */
	private void createWaitForNextTaskBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.NEXT_TIMEOUT.getName());
		//
		// nextTimeoutText = factory.createText(group,
		// EBTClientPreferences.NEXT_TIMEOUT.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.NEXT_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.NEXT_TIMEOUT.getDef());
		// nextTimeoutText.setText(Integer.toString(defVal));
		//
		// nextTimeoutCombo = factory.createCombo(group,
		// EBTClientPreferences.NEXT_TIMEOUT.getName(), new ComboData(
		// (defVal / 100) - 1, 100, 100, 100), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the paused box.
	 * @param group the group
	 */
	private void createPausedBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.PAUSE_TIMEOUT.getName());
		//
		// pauseTimeoutText = factory.createText(group,
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.PAUSE_TIMEOUT.getDef());
		// pauseTimeoutText.setText(Integer.toString(defVal));
		//
		// pauseTimeoutCombo = factory.createCombo(group,
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(), new ComboData(
		// (defVal / 100) - 1, 100, 100, 100), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the speed compute delay box.
	 * @param group the group
	 */
	private void createSpeedComputeDelayBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.SPEED_DELAY.getName());
		//
		// speedTimeoutText = factory.createText(group,
		// EBTClientPreferences.SPEED_DELAY.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.SPEED_DELAY.getName(),
		// (Integer) EBTClientPreferences.SPEED_DELAY.getDef());
		// speedTimeoutText.setText(Integer.toString(defVal));
		//
		// speedTimeoutCombo = factory.createCombo(group,
		// EBTClientPreferences.SPEED_DELAY.getName(), new ComboData(
		// defVal - 1, 1, 1, 10), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker delay box.
	 * @param group the group
	 */
	private void createTrackerDelayBox(final Group group) {
		// factory.createLabel(group,
		// EBTClientPreferences.TRACKERS_DELAY.getName());
		//
		// trackersDelayText = factory.createText(group,
		// EBTClientPreferences.TRACKERS_DELAY.getName(), false);
		//
		// int defVal = Activator.getPreferences().getInt(
		// EBTClientPreferences.TRACKERS_DELAY.getName(),
		// (Integer) EBTClientPreferences.TRACKERS_DELAY.getDef());
		// trackersDelayText.setText(Integer.toString(defVal));
		//
		// trackersDelayCombo = factory.createCombo(group,
		// EBTClientPreferences.SPEED_DELAY.getName(), new ComboData(
		// (defVal / 10) - 1, 10, 10, 100), this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		// connTimeoutText.setText((String) EBTClientPreferences.CLIENT_TIMEOUT
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.CLIENT_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.CLIENT_TIMEOUT.getDef());
		//
		// readTimeoutText.setText((String) EBTClientPreferences.PAUSE_TIMEOUT
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.PAUSE_TIMEOUT.getDef());
		//
		// nextTimeoutText.setText((String) EBTClientPreferences.NEXT_TIMEOUT
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.NEXT_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.NEXT_TIMEOUT.getDef());
		//
		// pauseTimeoutText.setText((String) EBTClientPreferences.PAUSE_TIMEOUT
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.PAUSE_TIMEOUT.getName(),
		// (Integer) EBTClientPreferences.PAUSE_TIMEOUT.getDef());
		//
		// speedTimeoutText.setText((String) EBTClientPreferences.SPEED_DELAY
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.SPEED_DELAY.getName(),
		// (Integer) EBTClientPreferences.SPEED_DELAY.getDef());
		//
		// trackersDelayText.setText((String)
		// EBTClientPreferences.TRACKERS_DELAY
		// .getDef());
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.TRACKERS_DELAY.getName(),
		// (Integer) EBTClientPreferences.TRACKERS_DELAY.getDef());

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
			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
