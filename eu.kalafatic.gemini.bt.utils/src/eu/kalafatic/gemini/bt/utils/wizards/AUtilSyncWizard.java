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
package eu.kalafatic.gemini.bt.utils.wizards;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BOLD_FONT;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;

import eu.kalafatic.gemini.bt.utils.wizards.pages.SyncSelectPage;
import eu.kalafatic.gemini.bt.utils.wizards.pages.SyncSourcesPage;
import eu.kalafatic.gemini.bt.utils.wizards.pages.SyncSpecifyPage;
import eu.kalafatic.gemini.bt.utils.wizards.pages.SyncSummaryPage;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DBUtils.EDBTorrents;
import eu.kalafatic.gemini.core.wizards.SyncWizard;
import eu.kalafatic.gemini.core.wizards.pages.ISWizardPage;

/**
 * The Class class AUtilSyncWizard.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AUtilSyncWizard extends SyncWizard {

	/**
	 * Instantiates a new a util sync wizard.
	 * @param export the export
	 */
	public AUtilSyncWizard(boolean export) {
		super(export);
	}

	// --------------------------------------------------------------
	// --------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISWizard#createPages()
	 */
	@Override
	public void createPages() {
		pages[0] = new SyncSourcesPage(factory, this);
		pages[1] = new SyncSpecifyPage(factory, this);
		pages[2] = new SyncSelectPage(factory, this);
		pages[3] = new SyncSummaryPage(factory, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.wizards.SyncWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setTitleAndImage();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		return ((ISWizardPage) getContainer().getCurrentPage()).getPageNumber() == getPageCount() - 1;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			createJobs();

			IRunnableWithProgress progress = createProgress(workToDo);
			getContainer().run(true, true, progress);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the item.
	 * @param table the table
	 * @param syncObject the sync object
	 * @param checkDuplicity the check duplicity
	 */
	public void createItem(Table table, SyncObject syncObject, boolean checkDuplicity) {

		if (checkDuplicity && itemExists(table, syncObject.name)) {
			return;
		}
		TableItem item = createItem(table, syncObject);

		if (canFinish()) {
			setExportDBEditItem(syncObject, item);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the item.
	 * @param table the table
	 * @param syncObject the sync object
	 * @return the table item
	 */
	private TableItem createItem(Table table, SyncObject syncObject) {
		TableItem item = new TableItem(table, SWT.CHECK);
		item.setData(syncObject);
		item.setData(GUIFactory.NAME, syncObject.name);

		if ((syncObject.FLAGS & ASync.ORIGINAL) != 0) {
			syncObject.note.add(ORIGIN);
			// item.setBackground(FUIConstants.GRADIENT);
		}
		setItem(syncObject, item);
		return item;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the item.
	 * @param syncObject the sync object
	 * @param item the item
	 */
	private void setItem(SyncObject syncObject, TableItem item) {
		item.setFont(1, BOLD_FONT);
		item.setText(0, syncObject.name);
		item.setText(1, syncObject.noteAsString());
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the export db edit item.
	 * @param syncObject the sync object
	 * @param item the item
	 */
	private void setExportDBEditItem(final SyncObject syncObject, TableItem item) {
		TableEditor editor = new TableEditor(item.getParent());
		final CCombo combo = new CCombo(item.getParent(), SWT.NONE);

		combo.setItems(DBUtils.DB_TORRENT_CATEGORIES);
		combo.select(0);

		syncObject.parameters.put(EDBTorrents.CATEGORY.columnName, combo.getText());

		// syncObject.parameters.put(DB_COLS[1], syncObject.name);
		// syncObject.parameters.put(DB_COLS[2], DateUtils.decodeTime(
		// System.currentTimeMillis(), EDateFormat.NICE_1));
		// syncObject.parameters.put(DB_COLS[3], Long
		// .toString(((ExtTorrent) syncObject.data).getAdditionalInfo()
		// .getFileSize()));
		// syncObject.parameters.put(DB_COLS[4], "0");
		// syncObject.parameters.put(DB_COLS[5], "0");
		// syncObject.parameters.put(DB_COLS[6], new File(
		// ((ExtTorrent) syncObject.data).getPath()));

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				syncObject.parameters.put("category", combo.getText());
			}
		});

		editor.grabHorizontal = true;
		editor.setEditor(combo, item, 0);

		item.setFont(2, BOLD_FONT);

		item.setText(1, syncObject.name);
		item.setText(2, syncObject.noteAsString());
	}

}
