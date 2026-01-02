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
package eu.kalafatic.gemini.bt.utils.wizards.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ESync;
import eu.kalafatic.gemini.core.utils.BundleUtils;

/**
 * The Class class SyncSourcesPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SyncSourcesPage extends AUtilSyncPage {

	/** The sync db btn f. */
	public Button syncFilesBtnF, syncDBBtnF;

	/** The sync db btn t. */
	public Button syncFilesBtnT, syncDBBtnT;

	/**
	 * Instantiates a new sync sources page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSourcesPage(GUIFactory factory, AUtilSyncWizard wizard) {
		super(0, factory, wizard, "Sources", "Select synchronization sources");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		SyncSpecifyPage syncSpecifyPage = ((SyncSpecifyPage) getNextPage());

		if (event.widget.equals(syncFilesBtnF)) {
			boolean selection = syncFilesBtnF.getSelection();

			syncSpecifyPage.syncFilesF = selection;
			syncSpecifyPage.syncDBF = !selection;

		} else if (event.widget.equals(syncDBBtnF)) {
			boolean selection = syncDBBtnF.getSelection();

			syncSpecifyPage.syncFilesF = !selection;
			syncSpecifyPage.syncDBF = selection;

		} else if (event.widget.equals(syncFilesBtnT)) {
			boolean selection = syncFilesBtnT.getSelection();

			syncSpecifyPage.syncFilesT = selection;
			syncSpecifyPage.syncDBT = !selection;

		} else if (event.widget.equals(syncDBBtnT)) {
			boolean selection = syncDBBtnT.getSelection();

			syncSpecifyPage.syncFilesT = !selection;
			syncSpecifyPage.syncDBT = selection;
		}
		syncSpecifyPage.changed = true;
	}

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

			SashForm sashForm = factory.createSashForm(composite, 2, SWT.HORIZONTAL);

			createFromGroup(sashForm);
			createToGroup(sashForm);

			if (BundleUtils.getInstance().isBundleInstalled(null, CLIENT_BUNDLE_PATTERN)) {
				// createClientGroup(composite);
			}
			getControl().redraw();
			composite.layout(true, true);

		} finally {
			changed = false;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the from group.
	 * @param parent the parent
	 */
	private void createFromGroup(Composite parent) {
		Group group = factory.createGroup(parent, "From", 1);

		syncFilesBtnF = factory.createButton(group, ESync.SYNC_FILES.key, SWT.RADIO, 100, true, this);
		syncDBBtnF = factory.createButton(group, ESync.SYNC_DB.key, SWT.RADIO, 100, false, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the to group.
	 * @param parent the parent
	 */
	private void createToGroup(Composite parent) {
		Group group = factory.createGroup(parent, "To", 1);

		syncDBBtnT = factory.createButton(group, ESync.SYNC_DB.key, SWT.RADIO, 100, true, this);
		syncFilesBtnT = factory.createButton(group, ESync.SYNC_FILES.key, SWT.RADIO, 100, false, this);
	}
}
