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
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import eu.kalafatic.gemini.core.utils.SSLUtils;

/**
 * The Class class SecurityPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SecurityPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The jre cert loc text. */
	private Text certLocText, jdkCertLocText, jreCertLocText;

	/** The cert load now btn. */
	private Button certLocBtn, jdkCertLocBtn, jreCertLocBtn, certEnBtn, certLoadNowBtn;

	/** The jre cert loc value. */
	private String certLocValue, jdkCertLocValue, jreCertLocValue;

	/** The cert en value. */
	private boolean certEnValue;

	/**
	 * Instantiates a new security preference page.
	 */
	public SecurityPreferencePage() {
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
		certLocValue = PREFERENCES.get(ECorePreferences.CERT_LOC.getName(), (String) ECorePreferences.CERT_LOC.getDef());
		jdkCertLocValue = PREFERENCES.get(ECorePreferences.JDK_CERT_LOC.getName(), (String) ECorePreferences.JDK_CERT_LOC.getDef());
		jreCertLocValue = PREFERENCES.get(ECorePreferences.JRE_CERT_LOC.getName(), (String) ECorePreferences.JRE_CERT_LOC.getDef());

		certEnValue = PREFERENCES.getBoolean(ECorePreferences.CERT_ENABLED.getName(), (Boolean) ECorePreferences.CERT_ENABLED.getDef());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org .eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		String path = null;
		if (event.widget == certLocBtn) {
			if ((path = FileUtils.getInstance().openFile(false, certLocValue)) != null) {
				certLocText.setText(path);
			}
		} else if (event.widget == jdkCertLocBtn) {
			if ((path = FileUtils.getInstance().openFile(false, jdkCertLocValue)) != null) {
				jdkCertLocText.setText(path);
			}
		} else if (event.widget == jreCertLocBtn) {
			if ((path = FileUtils.getInstance().openFile(false, jreCertLocValue)) != null) {
				jreCertLocText.setText(path);
			}
		} else if (event.widget == certLoadNowBtn) {
			try {
				SSLUtils.getInstance().loadKeystore();
				certEnBtn.setSelection(true);
			} catch (Exception e) {
				DialogUtils.INSTANCE.showException(e);
			}
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

			Group group = factory.createGroup(container, "Certificates", 2);

			createCertBox(group);
			createJDKCertBox(group);
			createJRECertBox(group);

			group = factory.createGroup(container, "Security actions", 2);
			createCertLoadedBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cert box.
	 * @param group the group
	 */
	private void createCertBox(final Group group) {
		factory.createLabel(group, ECorePreferences.CERT_LOC.getName(), LONG_TEXT_WIDTH, (byte) 2);

		certLocText = factory.createText(group, ECorePreferences.CERT_LOC.getName(), false);
		certLocText.setText(certLocValue);

		certLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the jdk cert box.
	 * @param group the group
	 */
	private void createJDKCertBox(final Group group) {
		factory.createLabel(group, ECorePreferences.JDK_CERT_LOC.getName(), LONG_TEXT_WIDTH, (byte) 2);

		jdkCertLocText = factory.createText(group, ECorePreferences.JDK_CERT_LOC.getName(), false);

		if (jdkCertLocValue.equals("")) {
			jdkCertLocValue = System.getProperty("java.home") + "\\lib\\security";
			PREFERENCES.put(ECorePreferences.JDK_CERT_LOC.getName(), jdkCertLocValue);
		}
		jdkCertLocText.setText(jdkCertLocValue);

		jdkCertLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the jre cert box.
	 * @param group the group
	 */
	private void createJRECertBox(final Group group) {
		factory.createLabel(group, ECorePreferences.JRE_CERT_LOC.getName(), LONG_TEXT_WIDTH, (byte) 2);

		jreCertLocText = factory.createText(group, ECorePreferences.JRE_CERT_LOC.getName(), false);

		if (jreCertLocValue.equals("")) {
			jreCertLocValue = "c:\\Program Files\\Java\\jre6\\lib\\security";
			PREFERENCES.put(ECorePreferences.JRE_CERT_LOC.getName(), jreCertLocValue);
		}
		jreCertLocText.setText(jreCertLocValue);

		jreCertLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cert loaded box.
	 * @param group the group
	 */
	private void createCertLoadedBox(Group group) {
		certEnBtn = factory.createButton(group, ECorePreferences.CERT_ENABLED.getName(), SWT.CHECK, LONG_TEXT_WIDTH, true, false, this);

		certEnBtn.setSelection(certEnValue);

		certLoadNowBtn = factory.createButton(group, "Load now", SWT.PUSH, this);

		Rectangle bounds = group.getBounds();
		certLoadNowBtn.setLocation(new Point(bounds.width - 60, bounds.height + 20));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		try {
			certLocText.setText((String) ECorePreferences.CERT_LOC.getDef());
			PREFERENCES.put(ECorePreferences.CERT_LOC.getName(), (String) ECorePreferences.CERT_LOC.getDef());

			jdkCertLocText.setText((String) ECorePreferences.JDK_CERT_LOC.getDef());
			PREFERENCES.put(ECorePreferences.JDK_CERT_LOC.getName(), (String) ECorePreferences.JDK_CERT_LOC.getDef());

			jreCertLocText.setText((String) ECorePreferences.JRE_CERT_LOC.getDef());
			PREFERENCES.put(ECorePreferences.JRE_CERT_LOC.getName(), (String) ECorePreferences.JRE_CERT_LOC.getDef());

			certEnBtn.setSelection((Boolean) ECorePreferences.CERT_ENABLED.getDef());
			PREFERENCES.putBoolean(ECorePreferences.CERT_ENABLED.getName(), (Boolean) ECorePreferences.CERT_ENABLED.getDef());

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

			PREFERENCES.put(ECorePreferences.CERT_LOC.getName(), certLocText.getText());
			PREFERENCES.put(ECorePreferences.JDK_CERT_LOC.getName(), jdkCertLocText.getText());
			PREFERENCES.put(ECorePreferences.JRE_CERT_LOC.getName(), jreCertLocText.getText());
			PREFERENCES.putBoolean(ECorePreferences.CERT_ENABLED.getName(), certEnBtn.getSelection());

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
