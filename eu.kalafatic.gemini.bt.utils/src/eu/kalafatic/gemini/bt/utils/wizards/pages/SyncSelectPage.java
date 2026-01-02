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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.core.dnd.SyncWizardDnD;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.interfaces.ESync;
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
public class SyncSelectPage extends AUtilSyncPage {

	/** The sash form. */
	private SashForm sashForm;

	/** The dest composite. */
	private Composite srcComposite, destComposite;

	/** The del all sel btn. */
	private Button addSelBtn, delSelBtn, addAllSelBtn, delAllSelBtn;

	/** The folder list. */
	public List<String> folderList = new ArrayList<String>();

	/** The db list. */
	public List<DBUtils.DB> dbList = new ArrayList<DBUtils.DB>();

	/**
	 * Instantiates a new sync select page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSelectPage(GUIFactory factory, AUtilSyncWizard wizard) {
		super(2, factory, wizard, "Select", "Select torrents to synchronize with tracker");
	}

	// ---------------------------------------------------------------
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

			SyncSpecifyPage syncSpecifyPage = (SyncSpecifyPage) getPreviousPage();

			if (syncSpecifyPage.syncFilesF) {
				createFilesContent(syncSpecifyPage, true);
				createDBContent(syncSpecifyPage, false);

			} else if (syncSpecifyPage.syncDBF) {
				createDBContent(syncSpecifyPage, true);
				createFilesContent(syncSpecifyPage, false);

			}
			initDnD(sashForm.getParent());

			getControl().redraw();
			composite.layout(true, true);

			sashForm.setWeights(new int[] { 5, 1, 5 });
		} finally {
			this.changed = false;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the db content.
	 * @param syncSpecifyPage the sync specify page
	 * @param src the src
	 */
	private void createDBContent(SyncSpecifyPage syncSpecifyPage, boolean src) {
		if (syncSpecifyPage.dbList.getItemCount() == 0) {
			return;
		}
		Composite parent = factory.createComposite(src ? srcComposite : destComposite, 2);

		Table table = wizard.createTable(parent, ECorePreferences.SYNC_DB.getName());
		wizard.addTable(src, table);

		if (src) {
			for (DBUtils.DB db : dbList) {

				String driver = db.driver;
				String url = db.url;
				String query = db.query;

				try {
					Class.forName(driver);
					Connection connection = DriverManager.getConnection(url);

					List<String> result = DBUtils.getInstance().execute(connection, query, 1);

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
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the files content.
	 * @param syncSpecifyPage the sync specify page
	 * @param src the src
	 */
	private void createFilesContent(SyncSpecifyPage syncSpecifyPage, boolean src) {
		if (syncSpecifyPage.folderList.getItemCount() == 0) {
			return;
		}
		Composite parent = factory.createComposite(src ? srcComposite : destComposite, 2);

		Table table = wizard.createTable(parent, ESync.SYNC_FILES.key);
		wizard.addTable(src, table);

		if (src) {
			boolean subfolders = syncSpecifyPage.subfoldersBtn.getSelection();

			for (String folderPath : syncSpecifyPage.folderList.getItems()) {
				File folder = new File(folderPath);
				setFileItems(table, folder, subfolders);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the selection form.
	 * @param composite the composite
	 */
	private void createSelectionForm(Composite composite) {
		sashForm = factory.createSashForm(composite, 3, SWT.HORIZONTAL);

		srcComposite = factory.createComposite(sashForm, 1);
		//
		Composite btnComposite = factory.createComposite(sashForm, 1);
		createButtons(btnComposite);
		//
		destComposite = factory.createComposite(sashForm, 1);
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
				// syncObject.FLAGS |= wizard.export ? ASync.ORIGINAL : 0;

				wizard.createItem(table, syncObject, !wizard.export);
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

		} else {
			Table targetTable = add ? wizard.dest.values().iterator().next() : null;
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
}
