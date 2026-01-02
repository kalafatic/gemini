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
package eu.kalafatic.gemini.bt.tracker.controller.wizards.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.tracker.controller.wizards.ATrackerSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.BundleUtils;

/**
 * The Class class SyncSourcesPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SyncSourcesPage extends ATrackerSyncPage {

	/** The sync db btn. */
	private Button syncFilesBtn, syncClientBtn, syncDBBtn;

	/**
	 * Instantiates a new sync sources page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSourcesPage(GUIFactory factory, ATrackerSyncWizard wizard) {
		super(0, factory, wizard, "Sources", "Select synchronization sources");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.wizards.pages.ISWizardPage#redraw()
	 */
	@Override
	public void redraw() {
		try {
			if (!changed) {
				return;
			}
			for (Control control : composite.getChildren()) {
				control.dispose();
			}

			Group group = factory.createGroup(composite, "Synchronize", 1);

			createTorrentsGroup(group);

			if (BundleUtils.getInstance().isBundleInstalled(null, CLIENT_BUNDLE_PATTERN)) {
				createClientGroup(group);
			}
			createDBGroup(group);

			getControl().redraw();
			composite.layout(true, true);

		} finally {
			changed = false;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		SyncSpecifyPage syncSpecifyPage = ((SyncSpecifyPage) getNextPage());

		if (event.widget.equals(syncFilesBtn)) {
			syncSpecifyPage.syncTorrents = syncFilesBtn.getSelection();

		} else if (event.widget.equals(syncClientBtn)) {
			syncSpecifyPage.syncClient = syncClientBtn.getSelection();

		} else if (event.widget.equals(syncDBBtn)) {
			syncSpecifyPage.syncDatabases = syncDBBtn.getSelection();
		}
		syncSpecifyPage.changed = true;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the torrents group.
	 * @param composite the composite
	 */
	private void createTorrentsGroup(Composite composite) {
		syncFilesBtn = factory.createButton(composite, ECorePreferences.SYNC_FILES.getName(), SWT.CHECK, 200, true, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client group.
	 * @param composite the composite
	 */
	private void createClientGroup(Composite composite) {
		syncClientBtn = factory.createButton(composite, ECorePreferences.SYNC_CLIENT.getName(), SWT.CHECK, 200, true, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the db group.
	 * @param composite the composite
	 */
	private void createDBGroup(Composite composite) {
		syncDBBtn = factory.createButton(composite, ECorePreferences.SYNC_DB.getName(), SWT.CHECK, 200, true, this);
	}
}
