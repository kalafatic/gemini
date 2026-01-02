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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class LocationsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LocationsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The torrents text. */
	private Text torrentsText;

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
		try {
			Group group = factory.createGroup(container, "Names and directories", 2);
			createTorrentsBox(group);
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return container;
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

		torrentsText = factory.createText(group, ECorePreferences.TORRENTS_LOC.getName(), false);

		final String value = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

		torrentsText.setText(value);

		Button torrentBtn = factory.createButton(group, "Browse", SWT.PUSH);

		torrentBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog fileDialog = new DirectoryDialog(group.getShell());
				fileDialog.setFilterPath(value);
				String open = fileDialog.open();
				torrentsText.setText(open);
				PREFERENCES.put(ECorePreferences.TORRENTS_LOC.getName(), open);
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
		torrentsText.setText((String) ECorePreferences.TORRENTS_LOC.getDef());
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
			Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
