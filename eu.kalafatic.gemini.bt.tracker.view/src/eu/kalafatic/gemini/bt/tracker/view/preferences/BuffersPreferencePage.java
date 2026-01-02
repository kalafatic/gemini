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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;

/**
 * The Class class BuffersPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class BuffersPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The disc buffer text. */
	private Text discBufferText;

	/**
	 * Instantiates a new buffers preference page.
	 */
	public BuffersPreferencePage() {
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
		Composite container = factory.createComposite(parent, 1);

		Group group = factory.createGroup(container, "Sounds", 3);
		createDiscBufferBox(group);

		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the disc buffer box.
	 * @param group the group
	 */
	private void createDiscBufferBox(final Group group) {
		factory.createLabel(group, ECorePreferences.DISC_BUFFER.getName(), SWT.NONE);

		discBufferText = factory.createText(group, ECorePreferences.DISC_BUFFER.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.DISC_BUFFER.getName(), ((Integer) ECorePreferences.DISC_BUFFER.getDef()));

		discBufferText.setText(Integer.toString(value));

		final Combo combo = factory.createCombo(group, ECorePreferences.DISC_BUFFER.getName(), SWT.NULL);

		String[] counts = NUMBERS;

		for (int i = 0; i < counts.length; i++) {
			combo.add(counts[i]);
		}
		combo.select(value);

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String item = combo.getItem(combo.getSelectionIndex());

				discBufferText.setText(item);
				PREFERENCES.putInt(ECorePreferences.DISC_BUFFER.name(), Integer.parseInt(item));
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
		int value = PREFERENCES.getInt(ECorePreferences.DISC_BUFFER.getName(), ((Integer) ECorePreferences.DISC_BUFFER.getDef()));

		discBufferText.setText(Integer.toString(value));
		PREFERENCES.putInt(ECorePreferences.DISC_BUFFER.getName(), value);

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
