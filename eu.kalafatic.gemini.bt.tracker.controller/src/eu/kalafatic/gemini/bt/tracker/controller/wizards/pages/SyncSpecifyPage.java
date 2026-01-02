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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ADD;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EDIT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.VALIDATE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

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

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.ATrackerSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SyncSpecifyPage extends ATrackerSyncPage {

	/** The sync databases. */
	public boolean syncTorrents = true, syncClient = true, syncDatabases = true;

	/** The del file btn. */
	private Button addFileBtn, delFileBtn;

	/** The finished btn. */
	private Button unfinishedBtn, finishedBtn;

	/** The del db btn. */
	private Button addDBBtn, editDBBtn, delDBBtn;

	/** The folder list. */
	public List folderList;

	/** The db list. */
	Table dbList;

	/** The subfolders btn. */
	private Button subfoldersBtn;

	/** The directory dialog. */
	private DirectoryDialog directoryDialog;

	/** The validate db btn. */
	private Button validateDBBtn;

	/**
	 * Instantiates a new sync specify page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSpecifyPage(GUIFactory factory, ATrackerSyncWizard wizard) {
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

			((SyncSelectPage) wizard.getPages()[2]).torrentsFolderList.clear();
			((SyncSelectPage) wizard.getPages()[2]).dbList.clear();
			// ((SyncSelectPage) wizard.getPages()[2]).finTorrents = true;
			// ((SyncSelectPage) wizard.getPages()[2]).unfinTorrents = true;
			((SyncSelectPage) wizard.getPages()[2]).changed = true;

			if (syncTorrents) {
				createSyncFilesGroup();
			}
			if (syncClient) {
				createSyncClientGroup();
			}
			if (syncDatabases) {
				createSyncDBGroup();
			}

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
		SyncSelectPage syncSelectPage = ((SyncSelectPage) getNextPage());
		final java.util.List<String> torrentsFolderList = syncSelectPage.torrentsFolderList;

		if (event.widget.equals(addFileBtn)) {
			directoryDialog.setText("Torrent's DirectoryDialog");
			String dir = directoryDialog.open();

			if (dir != null) {
				if (!torrentsFolderList.contains(dir)) {
					folderList.add(dir);
					torrentsFolderList.add(dir);
				}
				directoryDialog.setFilterPath(dir);
			}

		} else if (event.widget.equals(delFileBtn)) {
			String[] selection = folderList.getSelection();
			for (String dir : selection) {
				if (torrentsFolderList.contains(dir)) {
					folderList.remove(dir);
					torrentsFolderList.remove(dir);
				}
			}

		} else if (event.widget.equals(subfoldersBtn)) {
			Activator.getPreferences().putBoolean(ECorePreferences.SUBFOLDERS.getName(), subfoldersBtn.getSelection());

		} else if (event.widget.equals(unfinishedBtn)) {
			syncSelectPage.unfinTorrents = unfinishedBtn.getSelection();

		} else if (event.widget.equals(finishedBtn)) {
			syncSelectPage.finTorrents = finishedBtn.getSelection();

		} else if (event.widget.equals(addDBBtn)) {
			DB db = DBUtils.getInstance().new DB();
			wizard.editDB(db);

			if (DBUtils.getInstance().contains(syncSelectPage.dbList, db)) {
				DialogUtils.INSTANCE.warn("Database already exists !");
			} else {
				new TableItem(dbList, SWT.NONE).setText(db.parseURL());
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
				dbList.remove(selectionIndices[i]);
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

	/**
	 * Creates the sync files group.
	 */
	private void createSyncFilesGroup() {
		Group group = factory.createGroup(composite, ECorePreferences.SYNC_FILES.getName(), 2);
		folderList = new List(group, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = BTN_HEIGHT;
		gridData.verticalSpan = 3;
		folderList.setLayoutData(gridData);

		addFileBtn = factory.createButton(group, "Add", SWT.PUSH);
		addFileBtn.addListener(SWT.Selection, this);

		delFileBtn = factory.createButton(group, "Remove", SWT.PUSH);
		delFileBtn.addListener(SWT.Selection, this);

		if (!wizard.export) {
			boolean subfolders = Activator.getPreferences().getBoolean(ECorePreferences.SUBFOLDERS.getName(), (Boolean) ECorePreferences.SUBFOLDERS.getDef());

			subfoldersBtn = factory.createButton(group, ECorePreferences.SUBFOLDERS.getName(), SWT.CHECK);
			subfoldersBtn.setSelection(subfolders);
			subfoldersBtn.addListener(SWT.Selection, this);
		}

		String defLocation = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) (ECorePreferences.TORRENTS_LOC.getDef()));

		java.util.List<String> torrentsFolderList = ((SyncSelectPage) wizard.getPages()[2]).torrentsFolderList;

		if (!torrentsFolderList.contains(defLocation)) {
			folderList.add(defLocation);
			torrentsFolderList.add(defLocation);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sync client group.
	 */
	private void createSyncClientGroup() {
		Group group = factory.createGroup(composite, ECorePreferences.SYNC_CLIENT.getName(), 2);

		unfinishedBtn = factory.createButton(group, "Unfinished", SWT.CHECK, LABEL_WIDTH, true, this);

		finishedBtn = factory.createButton(group, "Finished", SWT.CHECK, LABEL_WIDTH, true, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sync db group.
	 */
	private void createSyncDBGroup() {
		Group group = factory.createGroup(composite, ECorePreferences.SYNC_DB.getName(), 2);
		dbList = new Table(group, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = BTN_HEIGHT;
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
