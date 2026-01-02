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
package eu.kalafatic.gemini.bt.tracker.controller.wizards;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BOLD_FONT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.ITrackerSyncWizard;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSelectPage;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSourcesPage;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSpecifyPage;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSummaryPage;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.FUIConstants;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DBUtils.EDBTorrents;
import eu.kalafatic.gemini.core.wizards.SyncWizard;

/**
 * The Class class ATrackerSyncWizard.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class ATrackerSyncWizard extends SyncWizard implements ITrackerSyncWizard {

	/** The torrents. */
	public Torrents torrents = TrackerModelManager.getInstance().getTorrents();

	/** The tracker swarm map. */
	public EMap<String, Session> trackerSwarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

	/** The tracker torrents. */
	public List<ExtTorrent> trackerTorrents = TrackerModelManager.getInstance().getTrackerTorrents();

	/** The selected tables. */
	public Button[] selectedTables = new Button[SRC_DEST = 3];

	/**
	 * Instantiates a new a tracker sync wizard.
	 * @param export the export
	 */
	protected ATrackerSyncWizard(boolean export) {
		super(export);

		SYNCHRONIZER = "Tracker";
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

	// --------------------------------------------------------------

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
		switchSyncType(syncObject, table, item);
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
			item.setBackground(FUIConstants.GRADIENT);
		}
		return item;
	}

	// ---------------------------------------------------------------

	/**
	 * Switch sync type.
	 * @param syncObject the sync object
	 * @param table the table
	 * @param item the item
	 */
	private void switchSyncType(SyncObject syncObject, Table table, TableItem item) {

		if (export && getContainer().getCurrentPage().equals(pages[3])) {
			String tableName = (String) table.getData(GUIFactory.NAME);
			switchSyncType(syncObject, item, tableName);
			// setExportDBEditItem(syncObject, item);
		} else {
			setItem(syncObject, item);
		}
	}

	// ---------------------------------------------------------------

	// summary page
	/**
	 * Switch sync type.
	 * @param syncObject the sync object
	 * @param item the item
	 * @param tableName the table name
	 */
	private void switchSyncType(SyncObject syncObject, TableItem item, String tableName) {

		// import
		if (tableName.equals(SYNCHRONIZER)) {
			setItem(syncObject, item);

			// exports
		} else if (tableName.equals(ECorePreferences.SYNC_FILES.getName())) {
			setItem(syncObject, item);

		} else if (tableName.equals(ECorePreferences.SYNC_CLIENT.getName())) {

			ExtTorrent extTorrent = getExtTorrent(syncObject);

			if (extTorrent == null) {
				System.err.println();
			} else {
				setItem(syncObject, item);
			}
		} else if (tableName.equals(ECorePreferences.SYNC_DB.getName())) {

			setExportDBEditItem(syncObject, item);
			syncObject.parameters.put(EDBTorrents.CATEGORY.columnName, DBUtils.DB_TORRENT_CATEGORIES[0]);
		}
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
				syncObject.parameters.put(EDBTorrents.CATEGORY.columnName, combo.getText());
			}
		});

		editor.grabHorizontal = true;
		editor.setEditor(combo, item, 0);

		item.setFont(2, BOLD_FONT);

		item.setText(1, syncObject.name);
		item.setText(2, syncObject.noteAsString());
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
	 * Gets the ext torrent.
	 * @param syncObject the sync object
	 * @return the ext torrent
	 */
	public ExtTorrent getExtTorrent(SyncObject syncObject) {
		ExtTorrent extTorrent = null;

		if ((syncObject.FLAGS & ASync.ORIGINAL) != 0) {
			for (ExtTorrent torrent : trackerTorrents) {
				if (syncObject.name.equals(torrent.getName())) {
					return torrent;
				}
			}
		} else {
			extTorrent = torrents.getTorrentMap().get(syncObject.name);
			if (extTorrent == null) {
				extTorrent = torrents.getTorrentMap().get(syncObject.name);
			}
			if (extTorrent == null) {
				extTorrent = torrents.getFinishedTorrentsMap().get(syncObject.name);
			}
		}
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tracker torrents.
	 * @return the tracker torrents
	 */
	protected List<ExtTorrent> getTrackerTorrents() {
		List<ExtTorrent> torrents = new ArrayList<ExtTorrent>();
		Collection<Session> values = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap().values();

		if (!values.isEmpty()) {
			for (Session session : values) {
				torrents.add((ExtTorrent) session.getTorrent());
			}
		}
		return torrents;
	}

}
