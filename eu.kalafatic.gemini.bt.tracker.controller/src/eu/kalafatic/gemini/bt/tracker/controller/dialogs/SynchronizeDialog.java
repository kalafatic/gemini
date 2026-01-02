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
package eu.kalafatic.gemini.bt.tracker.controller.dialogs;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BOLD_FONT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;

/**
 * The Class class SynchronizeDialog.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SynchronizeDialog extends TitleAreaDialog {

	/** The decision. */
	private final String DECISION = "Remove ?";

	/** The ready remove. */
	private final String READY_REMOVE = "Ready to removing";

	/** The tables. */
	private Map<String, SyncTable> tables = new HashMap<String, SyncTable>();

	/**
	 * The Class class SyncTable.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class SyncTable {

		/** The name. */
		public String name;

		/** The table. */
		public Table table;

		/** The elements. */
		public List<TableElement> elements = new ArrayList<TableElement>();

		/**
		 * Instantiates a new sync table.
		 * @param name the name
		 * @param table the table
		 */
		private SyncTable(String name, Table table) {
			this.name = name;
			this.table = table;
		}
	}

	/**
	 * The Class class TableElement.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class TableElement {

		/** The content. */
		public String name, content;

		/** The table. */
		public Table table;

		/** The ext torrent. */
		public ExtTorrent extTorrent;

		/**
		 * Instantiates a new table element.
		 * @param extTorrent the ext torrent
		 */
		private TableElement(ExtTorrent extTorrent) {
			this.extTorrent = extTorrent;
			this.name = extTorrent.getName();
		}

		/**
		 * Instantiates a new table element.
		 * @param name the name
		 * @param content the content
		 */
		private TableElement(String name, String content) {
			this.name = name;
			this.content = content;
		}
	}

	/** The form. */
	private SashForm form;

	/** The listener. */
	private Listener listener;

	/** The factory. */
	private GUIFactory factory;

	/** The tracker composite. */
	private Composite trackerComposite;

	/**
	 * Instantiates a new synchronize dialog.
	 * @param shell the shell
	 */
	public SynchronizeDialog(Shell shell) {
		super(shell);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse. swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		parent.getShell().setText("Custom synchronization wizard");
		setMessage("Select torrents to add to tracker?", IMessageProvider.INFORMATION);

		Rectangle bounds = parent.getDisplay().getBounds();
		parent.setBounds(bounds.width / 2 - 300, bounds.height / 2 - 200, 600, 400);
		return contents;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		factory = new GUIFactory(composite);

		createSelectionForm(composite);
		return composite;
	}

	/**
	 * Creates the selection form.
	 * @param composite the composite
	 */
	private void createSelectionForm(Composite composite) {
		SashForm sashForm = factory.createSashForm(composite, 3, SWT.HORIZONTAL);

		form = factory.createSashForm(sashForm, 2, SWT.VERTICAL);

		Composite btnComposite = factory.createComposite(sashForm, 1);

		btnComposite = factory.createComposite(btnComposite, 1);
		((GridData) btnComposite.getLayoutData()).horizontalAlignment = GridData.CENTER;
		((GridData) btnComposite.getLayoutData()).verticalAlignment = GridData.CENTER;

		factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_right.gif"));
		factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_left.gif"));
		factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_right_double.gif"));
		factory.createButton(btnComposite, "", SWT.PUSH, 25, 20, factory.createImage("icons/actions/arrow_left_double.gif"));

		trackerComposite = factory.createComposite(sashForm, 1);

		setSyncInput();
		initListeners();

		sashForm.setWeights(new int[] { 5, 1, 5 });
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the table.
	 * @param parent the parent
	 * @param name the name
	 * @return the table
	 */
	private Table createTable(Composite parent, String name) {
		Table table = factory.createTable(parent, SWT.CHECK | SWT.SINGLE | SWT.APPLICATION_MODAL | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, name, true, true);

		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table, SWT.LEFT);

		tc1.setText(name);
		tc2.setText("Action");

		tc1.setWidth(200);
		tc2.setWidth(200);
		return table;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the item.
	 * @param table the table
	 * @param tableElement the table element
	 */
	private void createItem(Table table, TableElement tableElement) {
		TableItem item = null;

		if (tableElement.extTorrent == null) {
			item = new TableItem(table, SWT.CHECK);
			item.setText(0, tableElement.name);
			item.setFont(1, BOLD_FONT);
			item.setText(1, DECISION);
		} else {
			if (tableElement.extTorrent.isEnabled()) {
				item = new TableItem(table, SWT.CHECK);
				item.setText(0, tableElement.extTorrent.getName());
				item.setText(1, READY_REMOVE);
				item.setFont(1, BOLD_FONT);
				item.setForeground(1, RED);
				item.setChecked(true);
				// removeMap.put(extTorrent.getName(), extTorrent);
			} else {
				item = new TableItem(table, SWT.CHECK);
				item.setText(0, tableElement.extTorrent.getName());
				item.setText(1, DECISION);
			}
			item.setData(tableElement.extTorrent);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		final Button allBtn = createButton(parent, IDialogConstants.YES_TO_ALL_ID, "Torrent+Data", false);

		final Button torrentBtn = createButton(parent, IDialogConstants.YES_ID, "Torrent", true);

		final Button cancelBtn = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == allBtn) {
					setReturnCode(IDialogConstants.YES_TO_ALL_ID);

				} else if (event.widget == torrentBtn) {
					setReturnCode(IDialogConstants.YES_ID);

				} else if (event.widget == cancelBtn) {
					setReturnCode(IDialogConstants.CANCEL_ID);

				}
				close();
			}
		};
		allBtn.addListener(SWT.Selection, listener);
		torrentBtn.addListener(SWT.Selection, listener);
		cancelBtn.addListener(SWT.Selection, listener);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the table.
	 * @param table the table
	 * @param torrents the torrents
	 */
	private void setTable(Table table, List<ExtTorrent> torrents) {
		TableItem item = null;

		for (ExtTorrent extTorrent : torrents) {
			if (extTorrent.isEnabled()) {
				item = new TableItem(table, SWT.CHECK);
				item.setText(0, extTorrent.getName());
				item.setText(1, READY_REMOVE);
				item.setFont(1, BOLD_FONT);
				item.setForeground(1, RED);
				item.setChecked(true);
				// removeMap.put(extTorrent.getName(), extTorrent);
			} else {
				item = new TableItem(table, SWT.CHECK);
				item.setText(0, extTorrent.getName());
				item.setText(1, DECISION);
			}
			item.setData(extTorrent);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.CHECK:
					TableItem item = (TableItem) event.item;
					boolean checked = item.getChecked();
					ExtTorrent extTorrent = (ExtTorrent) item.getData();

					item.setText(1, checked ? READY_REMOVE : DECISION);
					item.setFont(1, checked ? BOLD_FONT : null);
					item.setForeground(1, checked ? RED : BLACK);

					if (checked) {
						// removeMap.put(extTorrent.getName(), extTorrent);
					} else {
						// removeMap.remove(extTorrent.getName());
					}
					break;
				default:
					break;
				}
			}
		};
		// table1.addListener(SWT.Selection, listener);
		// table2.addListener(SWT.Selection, listener);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the sync input.
	 */
	private void setSyncInput() {
		if (Activator.getPreferences().getBoolean(ECorePreferences.SYNC_CLIENT.name(), (Boolean) ECorePreferences.SYNC_CLIENT.getDef())) {

			List<ExtTorrent> torrents = getTorrents("Model.torrents");
			if (torrents != null) {
				Table table = createTable(form, ECorePreferences.SYNC_CLIENT.getName());
				SyncTable syncTable = new SyncTable(ECorePreferences.SYNC_CLIENT.getName(), table);

				for (ExtTorrent extTorrent : torrents) {
					TableElement tableElement = new TableElement(extTorrent);
					syncTable.elements.add(tableElement);
					createItem(table, tableElement);
				}
				tables.put(ECorePreferences.SYNC_CLIENT.getName(), syncTable);
			}
		}

		if (Activator.getPreferences().getBoolean(ECorePreferences.SYNC_DB.name(), (Boolean) ECorePreferences.SYNC_DB.getDef())) {

			getTorrentsFromDB();
		}

		Table table = createTable(trackerComposite, ECorePreferences.SYNC_CLIENT.getName());
		SyncTable syncTable = new SyncTable(ECorePreferences.SYNC_CLIENT.getName(), table);
		tables.put(ECorePreferences.SYNC_CLIENT.getName(), syncTable);

		List<ExtTorrent> torrents = getTorrents("Model.tracker");

		if (torrents == null || torrents.isEmpty()) {

		} else {
			for (ExtTorrent extTorrent : torrents) {
				TableElement tableElement = new TableElement(extTorrent);
				syncTable.elements.add(tableElement);
				createItem(table, tableElement);
			}

		}

	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents.
	 * @param modelName the model name
	 * @return the torrents
	 */
	private List<ExtTorrent> getTorrents(String modelName) {
		try {
			Collection collection = (Collection) AppData.getInstance().getSharedModels().get(modelName);

			if (collection != null) {
				return new ArrayList<ExtTorrent>(collection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents from db.
	 * @return the torrents from db
	 */
	private void getTorrentsFromDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/torrents?user=reader");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT name, content FROM torrents.torrents");

			Table table = createTable(form, ECorePreferences.SYNC_DB.getName());
			SyncTable syncTable = new SyncTable(ECorePreferences.SYNC_DB.getName(), table);

			int count = 0;
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String content = resultSet.getString("content");
				// System.out.println("name = " + nameVal + ", category = "
				// + catVal);

				TableElement tableElement = new TableElement(name, content);
				syncTable.elements.add(tableElement);
				createItem(table, tableElement);

				++count;
			}
			tables.put(ECorePreferences.SYNC_DB.getName(), syncTable);

			resultSet.close();
			statement.close();
			System.out.println(count + " rows were retrieved");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TrayDialog#close()
	 */
	@Override
	public boolean close() {
		return super.close();
	}
}
