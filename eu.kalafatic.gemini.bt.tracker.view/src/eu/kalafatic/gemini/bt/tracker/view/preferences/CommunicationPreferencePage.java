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

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
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
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;

/**
 * The Class class CommunicationPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CommunicationPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The message queue size text. */
	private Text messageQueueSizeText;

	/** The sync db btn. */
	private Button syncClientBtn, syncFileBtn, syncDBBtn;

	/**
	 * Instantiates a new communication preference page.
	 */
	public CommunicationPreferencePage() {
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
		if (event.widget.equals(syncClientBtn)) {
			Activator.getPreferences().putBoolean(ECorePreferences.SYNC_CLIENT.name(), ((Button) event.widget).getSelection());
		} else if (event.widget.equals(syncFileBtn)) {
			Activator.getPreferences().putBoolean(ECorePreferences.SYNC_FILES.name(), ((Button) event.widget).getSelection());
		} else if (event.widget.equals(syncDBBtn)) {
			Activator.getPreferences().putBoolean(ECorePreferences.SYNC_DB.name(), ((Button) event.widget).getSelection());
		}
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

		Group group = factory.createGroup(container, "Session", 3);
		createSessionBox(group);

		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the session box.
	 * @param group the group
	 */
	private void createSessionBox(final Group group) {
		factory.createLabel(group, ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), SWT.NONE);

		messageQueueSizeText = factory.createText(group, ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), false);

		int value = Activator.getPreferences().getInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), ((Integer) ETrackerPreferences.MESSAGE_QUEUE_SIZE.getDef()));

		messageQueueSizeText.setText(Integer.toString(value));

		final Combo combo = factory.createCombo(group, ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), SWT.NULL);

		String[] counts = NUMBERS;

		for (int i = 0; i < counts.length; i++) {
			combo.add(counts[i]);
		}

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String item = combo.getItem(combo.getSelectionIndex());

				messageQueueSizeText.setText(item);

				Activator.getPreferences().putInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.name(), Integer.parseInt(item));
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		int value = Activator.getPreferences().getInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), ((Integer) ETrackerPreferences.MESSAGE_QUEUE_SIZE.getDef()));

		messageQueueSizeText.setText(Integer.toString(value));
		Activator.getPreferences().putInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.name(), value);

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
			Activator.getPreferences().putInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.name(), Integer.parseInt(messageQueueSizeText.getText()));

			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
