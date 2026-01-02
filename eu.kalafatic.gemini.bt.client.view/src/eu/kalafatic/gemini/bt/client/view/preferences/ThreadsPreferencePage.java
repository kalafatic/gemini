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
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.models.ComboData;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ThreadsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ThreadsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The max upld threads text. */
	private Text maxSwarmThreadsText, maxTrackerThreadsText, maxDwnldThreadsText, maxUpldThreadsText;

	/** The max upld threads combo. */
	private Combo maxSwarmThreadsCombo, maxTrackerThreadsCombo, maxDwnldThreadsCombo, maxUpldThreadsCombo;

	/**
	 * Instantiates a new threads preference page.
	 */
	public ThreadsPreferencePage() {
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
			Group group = factory.createGroup(container, "Threads constraints", 3);
			createMaxSwarmsThreadsCountBox(group);
			createMaxTrackersThreadsCountBox(group);
			createMaxDwnldThreadsCountBox(group);
			createMaxUpldThreadsCountBox(group);

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
		try {
			if (event.widget.equals(maxSwarmThreadsCombo)) {
				String item = maxSwarmThreadsCombo.getItem(maxSwarmThreadsCombo.getSelectionIndex());
				maxSwarmThreadsText.setText(item);

			} else if (event.widget.equals(maxTrackerThreadsCombo)) {
				String item = maxTrackerThreadsCombo.getItem(maxTrackerThreadsCombo.getSelectionIndex());
				maxTrackerThreadsText.setText(item);

			} else if (event.widget.equals(maxDwnldThreadsCombo)) {
				String item = maxDwnldThreadsCombo.getItem(maxDwnldThreadsCombo.getSelectionIndex());
				maxDwnldThreadsText.setText(item);

			} else if (event.widget.equals(maxUpldThreadsCombo)) {
				String item = maxUpldThreadsCombo.getItem(maxUpldThreadsCombo.getSelectionIndex());
				maxUpldThreadsText.setText(item);
			}
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max swarms threads count box.
	 * @param group the group
	 */
	private void createMaxSwarmsThreadsCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.MAX_SWARM_THREADS.getName(), SWT.NONE, LONG_TEXT_WIDTH);

		maxSwarmThreadsText = factory.createText(group, EBTClientPreferences.MAX_SWARM_THREADS.getName(), false);
		int defVal = Activator.getPreferences().getInt(EBTClientPreferences.MAX_SWARM_THREADS.getName(), (Integer) EBTClientPreferences.MAX_SWARM_THREADS.getDef());
		maxSwarmThreadsText.setText(Integer.toString(defVal));

		maxSwarmThreadsCombo = factory.createCombo(group, EBTClientPreferences.MAX_SWARM_THREADS.getName(), new ComboData(defVal - 1, 1, 1, 10), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max trackers threads count box.
	 * @param group the group
	 */
	private void createMaxTrackersThreadsCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.MAX_TRACKER_THREADS.getName(), SWT.NONE, LONG_TEXT_WIDTH);

		maxTrackerThreadsText = factory.createText(group, EBTClientPreferences.MAX_TRACKER_THREADS.getName(), false);

		int defVal = Activator.getPreferences().getInt(EBTClientPreferences.MAX_TRACKER_THREADS.getName(), (Integer) EBTClientPreferences.MAX_TRACKER_THREADS.getDef());
		maxTrackerThreadsText.setText(Integer.toString(defVal));

		maxTrackerThreadsCombo = factory.createCombo(group, EBTClientPreferences.MAX_TRACKER_THREADS.getName(), new ComboData(defVal - 1, 1, 1, 10), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max dwnld threads count box.
	 * @param group the group
	 */
	private void createMaxDwnldThreadsCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.MAX_DWNLD_THREADS.getName(), SWT.NONE, LONG_TEXT_WIDTH);

		maxDwnldThreadsText = factory.createText(group, EBTClientPreferences.MAX_DWNLD_THREADS.getName(), false);
		int defVal = Activator.getPreferences().getInt(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());
		maxDwnldThreadsText.setText(Integer.toString(defVal));

		maxDwnldThreadsCombo = factory.createCombo(group, EBTClientPreferences.MAX_DWNLD_THREADS.getName(), new ComboData(defVal - 1, 1, 1, 10), this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max upld threads count box.
	 * @param group the group
	 */
	private void createMaxUpldThreadsCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.MAX_UPLD_THREADS.getName(), SWT.NONE, LONG_TEXT_WIDTH);

		maxUpldThreadsText = factory.createText(group, EBTClientPreferences.MAX_UPLD_THREADS.getName(), false);
		int defVal = Activator.getPreferences().getInt(EBTClientPreferences.MAX_UPLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_UPLD_THREADS.getDef());
		maxUpldThreadsText.setText(Integer.toString(defVal));

		maxUpldThreadsCombo = factory.createCombo(group, EBTClientPreferences.MAX_UPLD_THREADS.getName(), new ComboData(defVal - 1, 1, 1, 10), this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		maxSwarmThreadsText.setText((String) EBTClientPreferences.MAX_SWARM_THREADS.getDef());
		Activator.getPreferences().put(EBTClientPreferences.MAX_SWARM_THREADS.getName(), (String) EBTClientPreferences.MAX_SWARM_THREADS.getDef());

		maxTrackerThreadsText.setText((String) EBTClientPreferences.MAX_TRACKER_THREADS.getDef());
		Activator.getPreferences().put(EBTClientPreferences.MAX_TRACKER_THREADS.getName(), (String) EBTClientPreferences.MAX_TRACKER_THREADS.getDef());

		maxDwnldThreadsText.setText((String) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());
		Activator.getPreferences().put(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), (String) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());

		maxUpldThreadsText.setText((String) EBTClientPreferences.MAX_UPLD_THREADS.getDef());
		Activator.getPreferences().put(EBTClientPreferences.MAX_UPLD_THREADS.getName(), (String) EBTClientPreferences.MAX_UPLD_THREADS.getDef());

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
			Activator.getPreferences().putInt(EBTClientPreferences.MAX_SWARM_THREADS.getName(), Integer.parseInt(maxSwarmThreadsText.getText()));

			Activator.getPreferences().putInt(EBTClientPreferences.MAX_TRACKER_THREADS.getName(), Integer.parseInt(maxTrackerThreadsText.getText()));

			Activator.getPreferences().putInt(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), Integer.parseInt(maxDwnldThreadsText.getText()));

			Activator.getPreferences().putInt(EBTClientPreferences.MAX_UPLD_THREADS.getName(), Integer.parseInt(maxUpldThreadsText.getText()));
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
