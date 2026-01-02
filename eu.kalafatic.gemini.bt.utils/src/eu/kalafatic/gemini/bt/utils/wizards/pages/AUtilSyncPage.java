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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.wizards.pages.AWizardPage;

/**
 * The Class class AUtilSyncPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AUtilSyncPage extends AWizardPage implements Listener {

	/** The wizard. */
	protected AUtilSyncWizard wizard;

	/** The composite. */
	protected Composite composite;

	/** The source. */
	protected Map<String, SyncObject> source = new HashMap<String, SyncObject>();

	/** The target. */
	protected Map<String, SyncObject> target = new HashMap<String, SyncObject>();

	/** The client bundle pattern. */
	protected final String CLIENT_BUNDLE_PATTERN = "eu.kalafatic.gemini.bt.client";

	/**
	 * Instantiates a new a util sync page.
	 * @param pageNumber the page number
	 * @param factory the factory
	 * @param wizard the wizard
	 * @param pageName the page name
	 * @param title the title
	 */
	protected AUtilSyncPage(int pageNumber, GUIFactory factory, AUtilSyncWizard wizard, String pageName, String title) {
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