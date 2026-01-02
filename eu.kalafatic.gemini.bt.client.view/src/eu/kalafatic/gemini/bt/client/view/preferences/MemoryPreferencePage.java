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

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FText.BUFFERS;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FText.MEMORY;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FText.RUNTIME;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.MB;

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
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.models.ComboData;
import eu.kalafatic.gemini.core.utils.ConvertUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class MemoryPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class MemoryPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The disc buffer text. */
	private Text appMemoryText, discBufferText;

	/** The disc buffer combo. */
	private Combo appMemoryCombo, discBufferCombo;

	/**
	 * Instantiates a new memory preference page.
	 */
	public MemoryPreferencePage() {
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
			Group group = factory.createGroup(container, MEMORY, 3);
			createMaxAppMemoryBox(group);
			createAppMemoryBox(group);

			group = factory.createGroup(container, BUFFERS, 3);
			createDiscBufferBox(group);
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
		if (event.widget.equals(appMemoryCombo)) {
			ComboData comboData = (ComboData) appMemoryCombo.getData("comboData");
			comboData.defaultSelection = appMemoryCombo.getSelectionIndex();
			appMemoryText.setText(comboData.getDefLiteral());

			PREFERENCES.putInt(ECorePreferences.APP_MEMORY.getName(), (int) (comboData.getElement().getIntValue() / MB));

		} else if (event.widget.equals(discBufferCombo)) {
			ComboData comboData = (ComboData) discBufferCombo.getData("comboData");
			comboData.defaultSelection = discBufferCombo.getSelectionIndex();

			discBufferText.setText(comboData.getDefLiteral());
			PREFERENCES.putInt(ECorePreferences.DISC_BUFFER.getName(), (int) (comboData.getElement().getIntValue() / MB));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max app memory box.
	 * @param group the group
	 */
	private void createMaxAppMemoryBox(final Group group) {
		factory.createLabel(group, RUNTIME, SWT.NONE);

		Text text = factory.createText(group, RUNTIME, false);

		int maxAppMemory = (int) Runtime.getRuntime().maxMemory();
		text.setText(Integer.toString(maxAppMemory / (1024 * 1024)));

		factory.createLabel(group, BTN_WIDTH);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the app memory box.
	 * @param group the group
	 */
	private void createAppMemoryBox(final Group group) {
		factory.createLabel(group, ECorePreferences.APP_MEMORY.getName());
		appMemoryText = factory.createText(group, ECorePreferences.APP_MEMORY.getName(), false);
		// appMemoryCombo = factory.createCombo(group,
		// ECorePreferences.APP_MEMORY.getName(), SWT.NULL, MEMORY_SIZES);

		int defVal = PREFERENCES.getInt(ECorePreferences.APP_MEMORY.getName(), (Integer) ECorePreferences.APP_MEMORY.getDef());
		int pow = (int) ConvertUtils.log2(defVal);

		ComboData comboData = new ComboData(pow, 2, true, 0, 2 * pow, MB);

		appMemoryCombo = factory.createCombo(group, ECorePreferences.DISC_BUFFER.getName(), comboData);

		appMemoryText.setText(comboData.getDefLiteral());
		appMemoryCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the disc buffer box.
	 * @param group the group
	 */
	private void createDiscBufferBox(final Group group) {
		factory.createLabel(group, ECorePreferences.DISC_BUFFER.getName());
		discBufferText = factory.createText(group, ECorePreferences.DISC_BUFFER.getName(), false);

		int defVal = PREFERENCES.getInt(ECorePreferences.DISC_BUFFER.getName(), (Integer) ECorePreferences.DISC_BUFFER.getDef());
		int pow = (int) ConvertUtils.log2(defVal);

		ComboData comboData = new ComboData(pow, 2, true, 0, 2 * pow, MB);

		discBufferCombo = factory.createCombo(group, ECorePreferences.DISC_BUFFER.getName(), comboData);

		discBufferText.setText(comboData.getDefLiteral());
		discBufferCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		appMemoryText.setText((String) ECorePreferences.APP_MEMORY.getDef());
		PREFERENCES.put(ECorePreferences.APP_MEMORY.getName(), (String) ECorePreferences.APP_MEMORY.getDef());

		discBufferText.setText((String) ECorePreferences.DISC_BUFFER.getDef());
		PREFERENCES.put(ECorePreferences.DISC_BUFFER.getName(), (String) ECorePreferences.DISC_BUFFER.getDef());

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
