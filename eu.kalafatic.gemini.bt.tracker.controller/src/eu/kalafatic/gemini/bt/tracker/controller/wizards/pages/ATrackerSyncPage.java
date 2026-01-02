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

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.tracker.controller.wizards.ATrackerSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.wizards.pages.AWizardPage;

/**
 * The Class class ATrackerSyncPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class ATrackerSyncPage extends AWizardPage implements Listener {

	/** The wizard. */
	protected ATrackerSyncWizard wizard;

	/** The composite. */
	protected Composite composite;

	/** The client bundle pattern. */
	protected final String CLIENT_BUNDLE_PATTERN = "eu.kalafatic.gemini.bt.client";

	/** The client settings. */
	protected final String[] CLIENT_SETTINGS = { "Host", "Address", "Port", "Note" };

	/**
	 * Instantiates a new a tracker sync page.
	 * @param pageNumber the page number
	 * @param factory the factory
	 * @param wizard the wizard
	 * @param pageName the page name
	 * @param title the title
	 */
	protected ATrackerSyncPage(int pageNumber, GUIFactory factory, ATrackerSyncWizard wizard, String pageName, String title) {
		super(pageNumber, factory, pageName, title, null);

		this.wizard = wizard;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		wizard.createWizardStepsComposite(this, parent, PROGRESS);

		composite = factory.createComposite(wizard.getSyncSashForm(), 1);

		setControl(wizard.getSyncSashForm());
		setPageComplete(true);

		PROGRESS[pageNumber].setSelection(true);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.wizards.pages.ISWizardPage#moveObject(org.eclipse.swt.widgets.Table, eu.kalafatic.gemini.core.models.SyncObject,
	 * boolean)
	 */
	@Override
	public void moveObject(Table targetTable, SyncObject syncObject, boolean add) {
		if (add) {
			wizard.createItem(targetTable, syncObject, true);
			targetTable.redraw();
		} else {
			if ((syncObject.FLAGS & ASync.ORIGINAL) == 0) {
				disposeItem(syncObject);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Dispose item.
	 * @param syncObject the sync object
	 */
	private void disposeItem(final SyncObject syncObject) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				Collection<Table> tables = wizard.dest.values();

				for (Table table : tables) {
					TableItem[] items = table.getItems();

					for (TableItem item : items) {
						if (item.getData(GUIFactory.NAME).equals(syncObject.name)) {
							item.dispose();
							table.redraw();
							break;
						}
					}
				}
			}
		});
	}
}
