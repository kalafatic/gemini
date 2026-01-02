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

import java.util.List;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.BackingStoreException;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.hack.ImageCombo;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.lib.constants.FUIConstants;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class LanguagePreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LanguagePreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The grid data. */
	private GridData gridData;

	/** The lang combo. */
	private ImageCombo langCombo;

	/** The lang text. */
	private Text langText;

	/** The current language. */
	private String currentLanguage;

	/** The locale. */
	private String locale;

	/**
	 * Instantiates a new language preference page.
	 */
	public LanguagePreferencePage() {
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
		try {
			factory = new GUIFactory(parent);

			gridData = new GridData(GridData.FILL_BOTH);
			gridData.widthHint = 600;
			gridData.heightHint = 400;
			parent.setLayoutData(gridData);

			Composite container = factory.createComposite(parent, 1);
			container.setLayoutData(gridData);

			Group group = factory.createGroup(container, "Application language", 3);
			createLanguageBox(group);
			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(langCombo)) {
			TableItem item = langCombo.getItem(langCombo.getSelectionIndex());
			String language = item.getText();

			langText.setText(language);
			PREFERENCES.put(ECorePreferences.LANGUAGE.getName(), language);

			System.setProperty("osgi.nl", language);
			System.getProperties().put("osgi.nl", language);
			Locale.setDefault(Locale.forLanguageTag(language));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the language box.
	 * @param group the group
	 * @throws BackingStoreException the backing store exception
	 */
	@SuppressWarnings("unchecked")
	private void createLanguageBox(final Group group) throws BackingStoreException {

		locale = PREFERENCES.get(ECorePreferences.LANGUAGE.getName(), (String) ECorePreferences.LANGUAGE.getDef());

		factory.createLabel(group, ECorePreferences.LANGUAGE.getName(), SWT.NONE);

		langText = factory.createText(group, ECorePreferences.LANGUAGE.getName(), false, SWT.SINGLE | SWT.BORDER, FUIConstants.LABEL_WIDTH);
		langText.setText(locale);

		langCombo = factory.createImageCombo(group, ECorePreferences.LANGUAGES.getName(), (List<Object[]>) ECorePreferences.LANGUAGES.getDef());
		langCombo.select(ECorePreferences.getLocaleIndex(locale));
		langCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		langText.setText((String) ECorePreferences.LANGUAGE.getDef());
		PREFERENCES.put(ECorePreferences.LANGUAGE.getName(), (String) ECorePreferences.LANGUAGE.getDef());
		System.setProperty("osgi.nl", (String) ECorePreferences.LANGUAGE.getDef());
		System.getProperties().put("osgi.nl", ECorePreferences.LANGUAGE.getDef());
		Locale.setDefault(Locale.forLanguageTag((String) ECorePreferences.LANGUAGE.getDef()));
		super.performDefaults();
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
			// Activator.getPreferences().flush();
			PREFERENCES.flush();

			if (!langText.getText().equals(locale)) {
				int answer = DialogUtils.INSTANCE.question("Changes needs restart\nDo you want to restart now?");
				if (answer == SWT.YES) {
					PlatformUI.getWorkbench().restart();
				}
			}

		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
