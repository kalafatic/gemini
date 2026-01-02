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

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.GRASS_COLOR;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.ATrackerSyncWizard;
import eu.kalafatic.gemini.core.dnd.SyncWizardDnD;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.interfaces.ESyncType;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.wizards.pages.AWizardPage;

/**
 * The Class class SyncSelectPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
// @SuppressWarnings({ "rawtypes", "unchecked" })
public class SyncSelectPage extends ATrackerSyncPage {

	/** The fin torrents. */
	public boolean unfinTorrents = true, finTorrents = true;

	/** The tracker. */
	public List<ExtTorrent> syncClient, syncClientFin, tracker;

	/** The form. */
	private SashForm sashForm, form;

	/** The src dst composite. */
	private Composite srcDstComposite;

	/** The torrents folder list. */
	public List<String> torrentsFolderList = new ArrayList<String>();

	/** The db list. */
	public List<DBUtils.DB> dbList = new ArrayList<DBUtils.DB>();

	/** The del all sel btn. */
	private Button addSelBtn, delSelBtn, addAllSelBtn, delAllSelBtn;

	/**
	 * Instantiates a new sync select page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSelectPage(GUIFactory factory, ATrackerSyncWizard wizard) {
		super(2, factory, wizard, "Select", "Select torrents to synchronize with tracker");
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
			if (!this.changed) {
				return;
			}
			for (Control control : composite.getChildren()) {
				control.dispose();
			}
			createSelectionForm(composite);

			if (!torrentsFolderList.isEmpty()) {
				createTorrentsFolderContent();
			}
			if (unfinTorrents || finTorrents) {
				createClientTorrentsContent();
			}
			if (!dbList.isEmpty()) {
				createDBTorrentsContent();
			}

			initDnD(sashForm.getParent());

			for (final Button button : wizard.selectedTables) {
				if (button != null) {
					((GridData) button.getLayoutData()).verticalAlignment = GridData.BEGINNING;
					((GridData) button.getLayoutData()).horizontalAlignment = GridData.BEGINNING;
					button.setSelection(false);
					wizard.dest.get(button.getText()).setEnabled(button.getSelection());

					button.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							wizard.dest.get(button.getText()).setEnabled(button.getSelection());
						}
					});
				}
			}

			if (wizard.export) {
				createTrackerTorrentsContent();
			}

			getControl().redraw();
			composite.layout(true, true);

			sashForm.setWeights(new int[] { 5, 1, 5 });
		} finally {
			this.changed = false;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(addSelBtn)) {
			List<TableItem> selectedItems = wizard.getSelectedItems(true);
			for (TableItem tableItem : selectedItems) {
				moveObject(tableItem, true);
			}
		} else if (event.widget.equals(delSelBtn)) {
			List<TableItem> selectedItems = wizard.getSelectedItems(false);
			for (TableItem tableItem : selectedItems) {
				moveObject(tableItem, false);
			}
		} else if (event.widget.equals(addAllSelBtn)) {
			moveAllObjects(true);
		} else if (event.widget.equals(delAllSelBtn)) {
			moveAllObjects(false);
		}
		((AWizardPage) getNextPage()).changed = true;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the selection form.
	 * @param composite the composite
	 */
	private void createSelectionForm(Composite composite) {
		sashForm = factory.createSashForm(composite, 3, SWT.HORIZONTAL);

		if (wizard.export) {
			srcDstComposite = factory.createComposite(sashForm, 1);
			//
			Composite btnComposite = factory.createComposite(sashForm, 1);
			createButtons(btnComposite);
			//
			form = factory.createSashForm(sashForm, 3, SWT.VERTICAL);
		} else {
			form = factory.createSashForm(sashForm, 3, SWT.VERTICAL);
			//
			Composite btnComposite = factory.createComposite(sashForm, 1);
			createButtons(btnComposite);
			//
			srcDstComposite = factory.createComposite(sashForm, 1);
			createTrackerTorrentsContent();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the dn d.
	 * @param composite the composite
	 */
	private void initDnD(Composite composite) {
		for (Control control : composite.getChildren()) {
			if (control instanceof Table) {
				SyncWizardDnD.getInstance().initDnD(wizard, control);
			}
			if (control instanceof Composite) {
				initDnD((Composite) control);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the torrents folder content.
	 */
	private void createTorrentsFolderContent() {
		if (torrentsFolderList.isEmpty()) {
			return;
		}
		Composite parent = factory.createComposite(form, 2);

		if (wizard.export) {
			wizard.selectedTables[0] = factory.createButton(parent, ECorePreferences.SYNC_FILES.getName(), SWT.CHECK, 10);
		}
		Table table = wizard.createTable(parent, ECorePreferences.SYNC_FILES.getName());

		wizard.addTable(!wizard.export, table);

		boolean subfolders = Activator.getPreferences().getBoolean(ECorePreferences.SUBFOLDERS.getName(), (Boolean) ECorePreferences.SUBFOLDERS.getDef());

		for (String folderPath : torrentsFolderList) {
			File folder = new File(folderPath);

			if (wizard.export && folder.isDirectory()) {
				SyncObject syncObject = new SyncObject(folder.getAbsolutePath(), folder, ESyncType.FILE);
				syncObject.FLAGS |= wizard.export ? ASync.ORIGINAL : 0;
				wizard.createItem(table, syncObject, !wizard.export);
			} else {
				setFileItems(table, folder, subfolders);
			}
		}
		if (wizard.export) {
			table.setEnabled(wizard.selectedTables[0].getSelection());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the file items.
	 * @param table the table
	 * @param folder the folder
	 * @param subfolders the subfolders
	 */
	private void setFileItems(Table table, File folder, boolean subfolders) {
		for (File file : folder.listFiles()) {

			if (subfolders && file.isDirectory()) {
				setFileItems(table, file, subfolders);
			}
			String name = file.getName();

			if (name.endsWith(EExt.TORRENT.ext)) {
				name = name.substring(0, name.lastIndexOf("."));
				SyncObject syncObject = new SyncObject(name, file, ESyncType.FILE);
				syncObject.FLAGS |= wizard.export ? ASync.ORIGINAL : 0;
				wizard.createItem(table, syncObject, !wizard.export);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client torrents content.
	 */
	private void createClientTorrentsContent() {
		if (wizard.torrents == null) {
			return;
		}
		List<ExtTorrent> torrentsList = new ArrayList<ExtTorrent>(wizard.torrents.getTorrentMap().values());
		torrentsList.addAll(wizard.torrents.getFinishedTorrentsMap().values());

		if (torrentsList != null) {
			Composite parent = factory.createComposite(form, 2);

			if (wizard.export) {
				wizard.selectedTables[1] = factory.createButton(parent, ECorePreferences.SYNC_CLIENT.getName(), SWT.CHECK, 10);
			}

			Table table = wizard.createTable(parent, ECorePreferences.SYNC_CLIENT.getName());
			wizard.addTable(!wizard.export, table);

			for (ExtTorrent extTorrent : torrentsList) {
				SyncObject syncObject = new SyncObject(extTorrent.getName(), null, ESyncType.DEF);
				syncObject.FLAGS |= wizard.export ? ASync.ORIGINAL : 0;
				wizard.createItem(table, syncObject, !wizard.export);
			}
			if (wizard.export) {
				table.setEnabled(wizard.selectedTables[1].getSelection());
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the db torrents content.
	 */
	private void createDBTorrentsContent() {
		if (dbList.isEmpty()) {
			return;
		}
		Composite parent = factory.createComposite(form, 2);
		String tableName = ECorePreferences.SYNC_DB.getName();

		if (wizard.export) {
			wizard.selectedTables[2] = factory.createButton(parent, tableName, SWT.CHECK, 10);
		}
		Table table = wizard.createTable(parent, tableName);

		wizard.addTable(!wizard.export, table);

		if (wizard.export) {
			return;
		}

		for (DBUtils.DB db : dbList) {
			try {
				Class.forName(db.driver);
				Connection connection = DriverManager.getConnection(db.url);

				List<String> result = DBUtils.getInstance().execute(connection, db.query, 1);

				for (String name : result) {
					if (name.endsWith(EExt.TORRENT.ext)) {
						name = name.substring(0, name.lastIndexOf("."));
					}
					SyncObject syncObject = new SyncObject(name, db, ESyncType.DB);
					syncObject.FLAGS |= wizard.export ? ASync.ORIGINAL : 0;
					wizard.createItem(table, syncObject, !wizard.export);
				}
				connection.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (wizard.export) {
			table.setEnabled(wizard.selectedTables[2].getSelection());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker torrents content.
	 */
	private void createTrackerTorrentsContent() {
		Table table = wizard.createTable(srcDstComposite, wizard.SYNCHRONIZER);
		wizard.addTable(wizard.export, table);

		if (wizard.trackerTorrents != null && !wizard.trackerTorrents.isEmpty()) {

			for (ExtTorrent extTorrent : wizard.trackerTorrents) {
				SyncObject syncObject = new SyncObject(extTorrent.getName(), null, ESyncType.DEF);
				syncObject.FLAGS |= !wizard.export ? ASync.ORIGINAL : 0;
				wizard.createItem(table, syncObject, false);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the buttons.
	 * @param btnComposite the btn composite
	 */
	private void createButtons(Composite btnComposite) {
		((GridData) btnComposite.getLayoutData()).horizontalAlignment = GridData.CENTER;
		((GridData) btnComposite.getLayoutData()).verticalAlignment = GridData.CENTER;

		btnComposite = factory.createComposite(btnComposite, 1);
		((GridData) btnComposite.getLayoutData()).horizontalAlignment = GridData.CENTER;
		((GridData) btnComposite.getLayoutData()).verticalAlignment = GridData.CENTER;

		btnComposite.setBackground(GRASS_COLOR);

		addSelBtn = factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_right.gif"));
		delSelBtn = factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_left.gif"));
		addAllSelBtn = factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_right_double.gif"));
		delAllSelBtn = factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_left_double.gif"));

		addSelBtn.addListener(SWT.Selection, this);
		delSelBtn.addListener(SWT.Selection, this);
		addAllSelBtn.addListener(SWT.Selection, this);
		delAllSelBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Move object.
	 * @param selectedItem the selected item
	 * @param add the add
	 */
	private void moveObject(TableItem selectedItem, boolean add) {
		if (selectedItem == null) {
			DialogUtils.INSTANCE.warn("Select source!");

		} else if (wizard.export) {

			if (add && !exportTargetDefined()) {
				DialogUtils.INSTANCE.warn("Select target!");
			} else {

				if (add) {
					Collection<Table> values = wizard.dest.values();
					for (Table table : values) {
						if (table.isEnabled()) {
							SyncObject syncObject = (SyncObject) selectedItem.getData();
							moveObject(table, syncObject, add);
						}
					}
				} else {
					SyncObject syncObject = (SyncObject) selectedItem.getData();
					moveObject(selectedItem.getParent(), syncObject, add);
				}
			}
		} else {
			Table targetTable = wizard.dest.get(wizard.SYNCHRONIZER);
			SyncObject syncObject = (SyncObject) selectedItem.getData();
			moveObject(targetTable, syncObject, add);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Move all objects.
	 * @param add the add
	 */
	private void moveAllObjects(boolean add) {
		Collection<Table> values = add ? wizard.src.values() : wizard.dest.values();
		for (Table table : values) {
			if (table.isEnabled()) {
				TableItem[] items = table.getItems();

				for (int i = 0; i < items.length; i++) {
					moveObject(items[i], add);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Export target defined.
	 * @return true, if successful
	 */
	private boolean exportTargetDefined() {
		for (Button button : wizard.selectedTables) {
			if ((button != null) && button.getSelection()) {
				return true;
			}
		}
		return false;
	}
}
