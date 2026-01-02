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
package eu.kalafatic.gemini.bt.tm.view.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.BROWSE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tm.controller.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class LocationsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LocationsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The torrents loc text. */
	private Text torrentsLocText;

	/** The torrents loc btn. */
	private Button torrentsLocBtn;

	/** The torrents loc. */
	private String TORRENTS_LOC = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

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
	 * @see eu.kalafatic.gemini.core.lib.constants.APreferencePage#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(torrentsLocBtn)) {
			DirectoryDialog fileDialog = new DirectoryDialog(torrentsLocBtn.getShell());
			fileDialog.setFilterPath(TORRENTS_LOC);
			String open = fileDialog.open();

			if (open != null) {
				torrentsLocText.setText(open);
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

			Group group = factory.createGroup(container, "Names and directories", 2);
			createTorrentsBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the torrents box.
	 * @param group the group
	 */
	private void createTorrentsBox(final Group group) {
		Label label = factory.createLabel(group, ECorePreferences.TORRENTS_LOC.getName(), SWT.NONE);

		GridData gridData = new GridData();
		gridData.widthHint = LONG_TEXT_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		gridData.horizontalSpan = 2;

		label.setLayoutData(gridData);

		torrentsLocText = factory.createText(group, ECorePreferences.TORRENTS_LOC.getName(), false);
		torrentsLocText.setText(TORRENTS_LOC);

		torrentsLocBtn = factory.createButton(group, BROWSE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		torrentsLocText.setText((String) ECorePreferences.TORRENTS_LOC.getDef());
		PREFERENCES.put(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

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
			PREFERENCES.put(ECorePreferences.TORRENTS_LOC.getName(), torrentsLocText.getText());

			Activator.getPreferences().flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}
}
