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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ADD;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EDIT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.VALIDATE;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ESync;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DBUtils.DB;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class SyncSpecifyPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SyncSpecifyPage extends AUtilSyncPage {

	/** The subfolders btn. */
	public Button addFileBtn, delFileBtn, subfoldersBtn;

	/** The directory dialog. */
	private DirectoryDialog directoryDialog;

	/** The folder list. */
	public List folderList;

	/** The db list. */
	Table dbList;

	/** The del db btn. */
	private Button addDBBtn, editDBBtn, delDBBtn;

	/** The sync dbf. */
	public boolean syncFilesF = true, syncDBF;

	/** The sync dbt. */
	public boolean syncFilesT, syncDBT = true;

	/** The validate db btn. */
	private Button validateDBBtn;

	/**
	 * Instantiates a new sync specify page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSpecifyPage(GUIFactory factory, AUtilSyncWizard wizard) {
		super(1, factory, wizard, "Specify", "Specify synchronization properties");

		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */

	private void init() {
		directoryDialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
		changed = true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		SyncSelectPage syncSelectPage = ((SyncSelectPage) getNextPage());

		if (event.widget.equals(addFileBtn)) {
			directoryDialog.setText("Torrent's DirectoryDialog");
			String dir = directoryDialog.open();

			if (dir != null) {
				folderList.add(dir);
				directoryDialog.setFilterPath(dir);
			}
		} else if (event.widget.equals(delFileBtn)) {
			String[] selection = folderList.getSelection();
			for (String dir : selection) {
				folderList.remove(dir);
			}
		} else if (event.widget.equals(addDBBtn)) {
			DB db = DBUtils.getInstance().new DB();
			wizard.editDB(db);

			if (DBUtils.getInstance().contains(syncSelectPage.dbList, db)) {
				DialogUtils.INSTANCE.warn("Database already exists !");
			} else {
				TableItem item = new TableItem(dbList, SWT.NONE);
				item.setData(db);
				item.setText(db.parseURL());
				syncSelectPage.dbList.add(db);
			}
		} else if (event.widget.equals(editDBBtn)) {
			if (dbList.getSelection().length == 1) {
				TableItem item = dbList.getSelection()[0];
				DB db = getDB(item.getText());
				wizard.editDB(db);
				item.setText(db.parseURL());
			}
		} else if (event.widget.equals(validateDBBtn)) {
			if (dbList.getSelection().length == 1) {
				TableItem item = dbList.getSelection()[0];
				DB db = getDB(item.getText());
				DBUtils.getInstance().isAvailable(db);
			}
		} else if (event.widget.equals(delDBBtn)) {
			int[] selectionIndices = dbList.getSelectionIndices();
			for (int i = 0; i < selectionIndices.length; i++) {
				DB db = (DB) dbList.getItem(selectionIndices[i]).getData();
				dbList.remove(selectionIndices[i]);
				syncSelectPage.dbList.remove(db);
			}
		}
		syncSelectPage.changed = true;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the db.
	 * @param url the url
	 * @return the db
	 */
	private DBUtils.DB getDB(String url) {
		java.util.List<DBUtils.DB> list = ((SyncSelectPage) wizard.getPages()[2]).dbList;

		for (DBUtils.DB db : list) {
			if (db.url.equals(url)) {
				return db;
			}
		}
		return null;
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

			if (syncFilesF) {
				createSyncFromFilesGroup(true);
			} else if (syncDBF) {
				createSyncFromDBGroup(true);
			}

			if (syncFilesT) {
				createSyncFromFilesGroup(false);
			} else if (syncDBT) {
				createSyncFromDBGroup(false);
			}

			getControl().redraw();
			composite.layout(true, true);

		} finally {
			changed = false;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sync from files group.
	 * @param src the src
	 */
	private void createSyncFromFilesGroup(boolean src) {
		Group group = factory.createGroup(composite, ESync.SYNC_FILES.key, 2);

		folderList = new List(group, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		// gridData.heightHint = BTN_HEIGHT;
		gridData.verticalSpan = 3;
		folderList.setLayoutData(gridData);

		addFileBtn = factory.createButton(group, ADD, SWT.PUSH);
		addFileBtn.addListener(SWT.Selection, this);

		delFileBtn = factory.createButton(group, REMOVE, SWT.PUSH);
		delFileBtn.addListener(SWT.Selection, this);

		subfoldersBtn = factory.createButton(group, "Subfolders", SWT.CHECK);
		subfoldersBtn.setSelection(true);
		subfoldersBtn.addListener(SWT.Selection, this);

		String defLocation = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) (ECorePreferences.TORRENTS_LOC.getDef()));

		folderList.add(defLocation);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sync from db group.
	 * @param src the src
	 */
	private void createSyncFromDBGroup(boolean src) {
		Group group = factory.createGroup(composite, ESync.SYNC_DB.key, 2);
		dbList = new Table(group, SWT.BORDER | /* SWT.SINGLE | */SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);

		gridData.verticalSpan = 4;
		dbList.setLayoutData(gridData);

		final java.util.List<DBUtils.DB> dbTorrentsList = ((SyncSelectPage) wizard.getPages()[2]).dbList;

		addDBBtn = factory.createButton(group, ADD, SWT.PUSH);
		addDBBtn.addListener(SWT.Selection, this);

		editDBBtn = factory.createButton(group, EDIT, SWT.PUSH);
		editDBBtn.addListener(SWT.Selection, this);

		validateDBBtn = factory.createButton(group, VALIDATE, SWT.PUSH);
		validateDBBtn.addListener(SWT.Selection, this);

		delDBBtn = factory.createButton(group, REMOVE, SWT.PUSH);
		delDBBtn.addListener(SWT.Selection, this);

		if (dbTorrentsList.isEmpty()) {
			DB db = DBUtils.getInstance().new DB((String) ECorePreferences.DB_TORRENNTS_QUERY.getValue());
			db.settings.put(DBUtils.DB_URL_PARAMETERS[5], wizard.export ? "writer" : "reader");

			TableItem item = new TableItem(dbList, SWT.NONE);
			item.setData(db);
			item.setText(db.parseURL());
			dbTorrentsList.add(db);
		}
	}
}
