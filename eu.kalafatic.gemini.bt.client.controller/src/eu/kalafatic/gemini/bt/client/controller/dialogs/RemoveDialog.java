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
package eu.kalafatic.gemini.bt.client.controller.dialogs;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BOLD_FONT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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

/**
 * The Class class RemoveDialog.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RemoveDialog extends TitleAreaDialog {

	/** The remove map. */
	private Map<String, ExtTorrent> /* input, */
	removeMap = new HashMap<String, ExtTorrent>();

	/** The input. */
	private List<ExtTorrent> input;

	/** The DECISION. */
	private final String DECISION = "Remove ?";

	/** The READ y_ remove. */
	private final String READY_REMOVE = "Ready to removing";

	/** The table2. */
	private Table table1, table2;

	/** The form. */
	private SashForm form;

	/** The listener. */
	private Listener listener;

	/**
	 * Instantiates a new removes the dialog.
	 * @param shell the shell
	 * @param input the input
	 */
	public RemoveDialog(Shell shell, List<ExtTorrent> input) {
		super(shell);
		this.input = input;
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
		parent.getShell().setText("Remove dialog");
		setMessage("Do you want to delete torrents ?", IMessageProvider.WARNING);
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

		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		form = new SashForm(composite, SWT.BORDER | SWT.VERTICAL);
		form.setLayout(new GridLayout(1, false));
		form.setLayoutData(new GridData(GridData.FILL_BOTH));

		table1 = new Table(form, SWT.CHECK | SWT.SINGLE | SWT.NONE | SWT.APPLICATION_MODAL);
		table1.setLayoutData(new GridData(GridData.FILL_BOTH));
		table1.setHeaderVisible(true);
		table1.setLinesVisible(true);

		table2 = new Table(form, SWT.CHECK | SWT.SINGLE | SWT.NONE | SWT.APPLICATION_MODAL);
		table2.setLayoutData(new GridData(GridData.FILL_BOTH));
		table2.setHeaderVisible(true);
		table2.setLinesVisible(true);

		initColumns();
		setTable();
		initListeners();

		return composite;
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
	 */
	private void setTable() {
		TableItem item = null;

		for (ExtTorrent extTorrent : input) {
			if (extTorrent.isEnabled()) {
				item = new TableItem(table2, SWT.CHECK);
				item.setText(0, extTorrent.getName());
				item.setText(1, READY_REMOVE);
				item.setFont(1, BOLD_FONT);
				item.setForeground(1, RED);
				item.setChecked(true);
				removeMap.put(extTorrent.getName(), extTorrent);
			} else {
				item = new TableItem(table1, SWT.CHECK);
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
						removeMap.put(extTorrent.getName(), extTorrent);
					} else {
						removeMap.remove(extTorrent.getName());
					}
					break;
				default:
					break;
				}
			}
		};
		table1.addListener(SWT.Selection, listener);
		table2.addListener(SWT.Selection, listener);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the columns.
	 */
	private void initColumns() {
		TableColumn tc1 = new TableColumn(table1, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table1, SWT.LEFT);
		TableColumn tc3 = new TableColumn(table2, SWT.LEFT);
		TableColumn tc4 = new TableColumn(table2, SWT.LEFT);

		tc1.setText("Selected Torrents to Remove");
		tc2.setText("Action");
		tc3.setText("Checked Torrents to Remove");
		tc4.setText("Action");

		tc1.setWidth(200);
		tc2.setWidth(200);
		tc3.setWidth(200);
		tc4.setWidth(200);
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

	/**
	 * Gets the removes the map.
	 * @return the removes the map
	 */
	public Map<String, ExtTorrent> getRemoveMap() {
		return removeMap;
	}

}
