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

import org.eclipse.swt.SWT;
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
 * The Class class SystemPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SystemPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new system preference page.
	 */
	public SystemPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePagecreateContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		try {
			factory = new GUIFactory(parent);
			Composite container = factory.createComposite(parent, 1);

			Group group = factory.createGroup(container, "System", 2);
			createCPUNameBox(group);
			createCPUSpeedBox(group);

			group = factory.createGroup(container, "OS", 2);
			createOSNameBox(group);
			createOSArchBox(group);

			group = factory.createGroup(container, "Java", 2);
			createJavaBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cpu name box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createCPUNameBox(Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.CPU_NAME.getName(), SWT.NONE);
		Text text = factory.createText(group, ECorePreferences.CPU_NAME.getName(), false);
		String value = PREFERENCES.get(ECorePreferences.CPU_NAME.getName(), (String) ECorePreferences.CPU_NAME.getDef());
		text.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cpu speed box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createCPUSpeedBox(Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.CPU_SPEED.getName(), SWT.NONE);
		Text text = factory.createText(group, ECorePreferences.CPU_SPEED.getName(), false);
		String value = PREFERENCES.get(ECorePreferences.CPU_SPEED.getName(), (String) ECorePreferences.CPU_SPEED.getDef());
		text.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the os name box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createOSNameBox(Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.OS_NAME.getName(), SWT.NONE);
		Text text = factory.createText(group, ECorePreferences.OS_NAME.getName(), false);
		String value = PREFERENCES.get(ECorePreferences.OS_NAME.getName(), (String) ECorePreferences.OS_NAME.getDef());
		text.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the os arch box.
	 * @param group the group
	 * @throws Exception the exception
	 */
	private void createOSArchBox(Group group) throws Exception {
		factory.createLabel(group, ECorePreferences.OS_ARCH.getName(), SWT.NONE);
		Text text = factory.createText(group, ECorePreferences.OS_ARCH.getName(), false);
		String value = PREFERENCES.get(ECorePreferences.OS_ARCH.getName(), (String) ECorePreferences.OS_ARCH.getDef());
		text.setText(value);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the java box.
	 * @param group the group
	 */
	private void createJavaBox(Group group) {
		factory.createLabel(group, ECorePreferences.JAVA_VERSION.getName(), SWT.NONE);
		Text text = factory.createText(group, ECorePreferences.JAVA_VERSION.getName(), false);
		String value = PREFERENCES.get(ECorePreferences.JAVA_VERSION.getName(), (String) ECorePreferences.JAVA_VERSION.getDef());
		text.setText(value);
	}
}
