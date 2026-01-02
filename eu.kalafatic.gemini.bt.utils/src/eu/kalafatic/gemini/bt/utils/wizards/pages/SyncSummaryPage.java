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
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TAB_STYLE_2;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.NetUtils;

/**
 * The Class class SyncSummaryPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SyncSummaryPage extends AUtilSyncPage {

	/** The parallel btn. */
	private Button sequenceBtn, parallelBtn;

	/** The db export. */
	public boolean parallel = true, dbExport;;

	/** The clients table. */
	private Table clientsTable;

	/** The remove client btn. */
	private Button addClientBtn, removeClientBtn;

	/** The form. */
	private SashForm form;

	/**
	 * Instantiates a new sync summary page.
	 * @param factory the factory
	 * @param wizard the wizard
	 */
	public SyncSummaryPage(GUIFactory factory, AUtilSyncWizard wizard) {
		super(3, factory, wizard, "Summary", "Process synchronization");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(sequenceBtn)) {
			parallel = sequenceBtn.getSelection();
		} else if (event.widget.equals(parallelBtn)) {
			parallel = parallelBtn.getSelection();
		} else if (event.widget.equals(addClientBtn)) {
			addClient();
		} else if (event.widget.equals(removeClientBtn)) {
			removeClient();
		}
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
			dbExport = false;

			createOptionButtonsGroup();

			if (wizard.export) {
				// createDBOptionGroup();
			} else {
				createOptionClientsGroup();
			}
			form = factory.createSashForm(composite, wizard.dest.size(), SWT.VERTICAL);

			createDestTables();

			getControl().redraw();
			composite.layout(true, true);

		} finally {
			this.changed = false;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the dest tables.
	 */
	private void createDestTables() {

		for (Table table : wizard.dest.values()) {

			if ((table.getItemCount() == 0) || (!table.isEnabled())) {
				continue;
			}
			String tableName = (String) table.getData(GUIFactory.NAME);

			Group group = factory.createGroup(form, tableName, 1);

			Table clonedTable = wizard.createTable(group, tableName, "Category", "Name", "Note");
			dbExport = true;

			wizard.destClones.put(tableName, clonedTable);

			TableItem[] items = table.getItems();

			for (TableItem item : items) {
				wizard.createItem(clonedTable, (SyncObject) item.getData(), true);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the option buttons group.
	 */
	private void createOptionButtonsGroup() {
		Group group = factory.createGroup(composite, "Process options", 2);

		sequenceBtn = factory.createButton(group, "Sequence", SWT.RADIO, 200, !parallel);
		parallelBtn = factory.createButton(group, "Paralllel", SWT.RADIO, 200, parallel);

		sequenceBtn.addListener(SWT.Selection, this);
		parallelBtn.addListener(SWT.Selection, this);

	}

	// ---------------------------------------------------------------

	/**
	 * Creates the option clients group.
	 */
	private void createOptionClientsGroup() {
		Group group = factory.createGroup(composite, "Default clients", 2);

		clientsTable = factory.createTable(group, TAB_STYLE_2, "Default clients", true, true);
		((GridData) clientsTable.getLayoutData()).heightHint = 50;
		((GridData) clientsTable.getLayoutData()).verticalSpan = 2;

		TableColumn[] tc = new TableColumn[4];

		for (int i = 0; i < tc.length; i++) {
			tc[i] = new TableColumn(clientsTable, SWT.LEFT);
			// tc[i].setText(CLIENT_SETTINGS[i]);
		}

		int clientPort = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		// 100 local address should be enough
		String[][] localHostIP = NetUtils.getInstance().getLocalHostIP(100, true);

		for (int i = 0; i < localHostIP.length; i++) {
			TableItem item = new TableItem(clientsTable, SWT.NONE);
			item.setText(new String[] { localHostIP[i][0], localHostIP[i][1], Integer.toString(clientPort), "" });

			// swarmClients.add(localHostIP[i][1] + ":" + clientPort);

			item.setChecked(true);
		}
		addClientBtn = factory.createButton(group, ADD, SWT.PUSH);
		removeClientBtn = factory.createButton(group, REMOVE, SWT.PUSH);

		for (int i = 0; i < tc.length; i++) {
			tc[i].pack();
		}

		addClientBtn.addListener(SWT.Selection, this);
		removeClientBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the client.
	 */
	private void addClient() {
		try {
			String[] request = new String[3];
			// EditClientDialog editClientDialog = new EditClientDialog(
			// getShell(), request);
			//
			// if (editClientDialog.open() == Window.OK) {
			// String host = request[0];
			// String port = request[1];
			// String note = request[2];
			//
			// if (host.length() == 0 || port.length() == 0) {
			// MessageDialog.openInformation(getShell(), "Client",
			// "Can not read address");
			// return;
			// }
			// InetAddress address = InetAddress.getByName(host);
			// String ip = address.getHostAddress();
			//
			// if (uniqueIP(ip)) {
			// TableItem item = new TableItem(clientsTable, SWT.NONE);
			// item.setText(new String[] { host, ip, port, note });
			// item.setChecked(true);
			//
			// swarmClients.add(ip + ":" + port);
			// } else {
			// MessageDialog.openInformation(getShell(), "Client",
			// "Duplicate host");
			// }
			// }
		} catch (Exception e) {
			MessageDialog.openInformation(getShell(), "Client", "Unknown host");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the client.
	 */
	private void removeClient() {
		try {
			int selectionIndex = clientsTable.getSelectionIndex();
			TableItem item = clientsTable.getItem(selectionIndex);

			// swarmClients.remove(item.getText(1) + ":" + item.getText(2));

			clientsTable.remove(selectionIndex);
			clientsTable.layout(true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Unique ip.
	 * @param ip the ip
	 * @return true, if successful
	 */
	private boolean uniqueIP(String ip) {
		TableItem[] items = clientsTable.getItems();
		for (int i = 0; i < items.length; i++) {
			if (items[i].getText(1).equalsIgnoreCase(ip)) {
				return false;
			}
		}
		return true;
	}

}
