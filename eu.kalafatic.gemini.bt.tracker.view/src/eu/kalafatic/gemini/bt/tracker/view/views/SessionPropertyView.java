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
package eu.kalafatic.gemini.bt.tracker.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.INFO_IMG;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SessionPropertyView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SessionPropertyView extends ViewPart implements ISelectionListener {

	/** The table composite. */
	private Composite tableComposite;

	/** The table. */
	private Table table;

	/** The items. */
	// private static SessionPropertyView INSTANCE;

	/** The items. */
	private TableItem[] items;

	/**
	 * Instantiates a new session property view.
	 */
	public SessionPropertyView() {
		setTitleImage(INFO_IMG);
	}

	/**
	 * Gets the single instance of SessionPropertyView.
	 * @return single instance of SessionPropertyView
	 */
	// public static SessionPropertyView getInstance() {
	// if (INSTANCE == null) {
	// synchronized (SessionPropertyView.class) {
	// INSTANCE = new SessionPropertyView();
	// }
	// }
	// return INSTANCE;
	// }

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		tableComposite = new Composite(parent, SWT.FILL);
		GridLayout gridLayout = new GridLayout();
		tableComposite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.verticalAlignment = GridData.BEGINNING;

		tableComposite.setLayoutData(gridData);

		table = new Table(tableComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);

		final GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		table.setLayoutData(gd);

		initColumns();
		initRows();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		getSite().getPage().addSelectionListener(this);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the columns.
	 */
	private void initColumns() {
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table, SWT.LEFT);

		tc1.setText("Setting");
		tc2.setText("Value");

		tc1.setWidth(100);
		tc2.setWidth(200);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the rows.
	 */
	private void initRows() {
		items = new TableItem[10];
		for (int i = 0; i < items.length; i++) {
			items[i] = new TableItem(table, SWT.NONE);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		try {
			if (selection instanceof StructuredSelection) {
				StructuredSelection structuredSelection = (StructuredSelection) selection;
				if (structuredSelection.getFirstElement() instanceof Entry<?, ?>) {
					Entry<?, ?> entry = (Entry<?, ?>) structuredSelection.getFirstElement();

					if (entry.getValue() instanceof Session) {
						Session session = (Session) entry.getValue();
						table.clearAll();
						updateTable(session);
					}
				}
			}
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Update table.
	 * @param session the session
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	private void updateTable(Session session) throws UnsupportedEncodingException {
		int itemIndex = 0;

		items[itemIndex++].setText(new String[] { "Address", session.getAddress() });

		if (session.getInfoHash() != null) {
			items[itemIndex++].setText(new String[] { "Hash", new String(session.getInfoHash()) });
		}

		items[itemIndex++].setText(new String[] { "State", session.getSessionState().getLiteral() });

		if (session instanceof ClientSession) {
			ClientSession clientSession = (ClientSession) session;
			items[itemIndex++].setText(new String[] { "PeerID", clientSession.getRequestMap().get("peer_id") });
			items[itemIndex++].setText(new String[] { "Seed", clientSession.isSeed() ? "true" : "false" });

		} else if (session instanceof TorrentSession) {
			TorrentSession torrentSession = (TorrentSession) session;
			items[itemIndex++].setText(new String[] { "Torrent name", torrentSession.getTorrentName() });
			items[itemIndex++].setText(new String[] { "Size", Long.toString(torrentSession.getTorrentLen()) });
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}
}
