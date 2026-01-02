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
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.BROWSE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.DIR;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscManager;
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

	/** The dwnld text. */
	private Text torrentsText, dwnldText;

	/** The torrent btn. */
	private Button dwnldBtn, torrentBtn;

	/** The dwnld location. */
	private String dwnldLocation = PREFERENCES.get(ECorePreferences.DOWNLOADS_LOC.getName(), (String) ECorePreferences.DOWNLOADS_LOC.getDef());

	/** The torrent location. */
	private String torrentLocation = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

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
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		factory = new GUIFactory(parent);
		Composite container = factory.createComposite(parent, 1);
		try {
			Group group = factory.createGroup(container, DIR, 2);
			createTorrentsBox(group);
			createDownloadsBox(group);
		} catch (Exception e) {
			e.printStackTrace();
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
			if (event.widget.equals(dwnldBtn)) {
				DirectoryDialog fileDialog = new DirectoryDialog(dwnldBtn.getShell());
				fileDialog.setFilterPath(dwnldLocation);
				String open = fileDialog.open();

				if (open != null && !open.isEmpty()) {
					dwnldText.setText(dwnldLocation = open);
				}

			} else if (event.widget.equals(torrentBtn)) {
				DirectoryDialog fileDialog = new DirectoryDialog(torrentBtn.getShell());
				fileDialog.setFilterPath(torrentLocation);
				String open = fileDialog.open();

				if (open != null && !open.isEmpty()) {
					torrentsText.setText(torrentLocation = open);
				}

			}
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the downloads box.
	 * @param group the group
	 */
	private void createDownloadsBox(final Group group) {
		factory.createLabel(group, ECorePreferences.DOWNLOADS_LOC.getName(), (byte) 2);

		dwnldText = factory.createText(group, ECorePreferences.DOWNLOADS_LOC.getName(), false);
		dwnldText.setText(dwnldLocation);

		dwnldBtn = factory.createButton(group, BROWSE, SWT.PUSH);
		dwnldBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the torrents box.
	 * @param group the group
	 */
	private void createTorrentsBox(final Group group) {
		factory.createLabel(group, ECorePreferences.TORRENTS_LOC.getName(), (byte) 2);

		torrentsText = factory.createText(group, ECorePreferences.TORRENTS_LOC.getName(), false);
		torrentsText.setText(torrentLocation);

		torrentBtn = factory.createButton(group, BROWSE, SWT.PUSH);
		torrentBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		try {
			torrentsText.setText((String) ECorePreferences.TORRENTS_LOC.getDef());
			PREFERENCES.put(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

			dwnldText.setText((String) ECorePreferences.DOWNLOADS_LOC.getDef());
			PREFERENCES.put(ECorePreferences.DOWNLOADS_LOC.getName(), (String) ECorePreferences.DOWNLOADS_LOC.getDef());

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
			PREFERENCES.put(ECorePreferences.TORRENTS_LOC.getName(), torrentsText.getText());

			PREFERENCES.put(ECorePreferences.DOWNLOADS_LOC.getName(), dwnldText.getText());
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
			DiscManager.getInstance().changeDwnldLocations(dwnldLocation);
			// Forces the application to save the CORE preferences
			PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
