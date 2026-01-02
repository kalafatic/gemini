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
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.BROWSE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.DIR;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;

/**
 * The Class class LocationsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LocationsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The cert loc text. */
	private Text workspaceLocText, modelsLocText, logsLocText, certLocText;

	/** The cert loc btn. */
	private Button workspaceLocBtn, modelsLocBtn, logsLocBtn, certLocBtn;

	/** The cert loc value. */
	private String workspaceLocValue, modelsLocValue, logsLocValue, certLocValue;

	/**
	 * Instantiates a new locations preference page.
	 */
	public LocationsPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#init(org.eclipse .ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		workspaceLocValue = PREFERENCES.get(ECorePreferences.WORKSPACE_LOC.getName(), (String) ECorePreferences.WORKSPACE_LOC.getDef());
		modelsLocValue = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());
		logsLocValue = PREFERENCES.get(ECorePreferences.LOGS_LOC.getName(), (String) ECorePreferences.LOGS_LOC.getDef());
		certLocValue = PREFERENCES.get(ECorePreferences.CERT_LOC.getName(), (String) ECorePreferences.CERT_LOC.getDef());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		try {
			String path = null;
			if (event.widget == workspaceLocBtn) {
				if ((path = FileUtils.getInstance().openFile(false, workspaceLocValue)) != null) {
					workspaceLocText.setText(path);
				}
			} else if (event.widget == modelsLocBtn) {
				if ((path = FileUtils.getInstance().openFile(false, modelsLocValue)) != null) {
					modelsLocText.setText(path);
				}
			} else if (event.widget == logsLocBtn) {
				if ((path = FileUtils.getInstance().openFile(false, logsLocValue)) != null) {
					logsLocText.setText(path);
				}
			} else if (event.widget == certLocBtn) {
				if ((path = FileUtils.getInstance().openFile(false, certLocValue)) != null) {
					certLocText.setText(path);
				}
			}
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

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

			Group group = factory.createGroup(container, DIR, 2);
			createWorkspaceBox(group);
			createModelsBox(group);
			createLogsBox(group);
			createCertBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the workspace box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createWorkspaceBox(final Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.WORKSPACE_LOC.getName(), (byte) 2);

		workspaceLocText = factory.createText(group, ECorePreferences.WORKSPACE_LOC.getName(), false);
		workspaceLocText.setText(workspaceLocValue);

		workspaceLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the models box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createModelsBox(final Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.MODELS_LOC.getName(), (byte) 2);

		modelsLocText = factory.createText(group, ECorePreferences.MODELS_LOC.getName(), false);
		modelsLocText.setText(modelsLocValue);

		modelsLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the logs box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createLogsBox(final Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.LOGS_LOC.getName(), (byte) 2);

		logsLocText = factory.createText(group, ECorePreferences.LOGS_LOC.getName(), false);
		logsLocText.setText(logsLocValue);

		logsLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cert box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createCertBox(final Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.CERT_LOC.getName(), (byte) 2);

		certLocText = factory.createText(group, ECorePreferences.CERT_LOC.getName(), false);
		certLocText.setText(certLocValue);

		certLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		try {
			workspaceLocText.setText((String) ECorePreferences.WORKSPACE_LOC.getDef());
			PREFERENCES.put(ECorePreferences.WORKSPACE_LOC.getName(), (String) ECorePreferences.WORKSPACE_LOC.getDef());

			modelsLocText.setText((String) ECorePreferences.MODELS_LOC.getDef());
			PREFERENCES.put(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			logsLocText.setText((String) ECorePreferences.LOGS_LOC.getDef());
			PREFERENCES.put(ECorePreferences.LOGS_LOC.getName(), (String) ECorePreferences.LOGS_LOC.getDef());

			certLocText.setText((String) ECorePreferences.CERT_LOC.getDef());
			PREFERENCES.put(ECorePreferences.CERT_LOC.getName(), (String) ECorePreferences.CERT_LOC.getDef());

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
			PREFERENCES.put(ECorePreferences.WORKSPACE_LOC.getName(), workspaceLocText.getText());
			PREFERENCES.put(ECorePreferences.MODELS_LOC.getName(), modelsLocText.getText());
			PREFERENCES.put(ECorePreferences.LOGS_LOC.getName(), logsLocText.getText());
			PREFERENCES.put(ECorePreferences.CERT_LOC.getName(), certLocText.getText());
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
