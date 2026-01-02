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
package eu.kalafatic.gemini.bt.client.net.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.INFO_IMG;

import java.util.Map.Entry;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.utils.ConvertUtils;

/**
 * The Class class SessionDetailView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class SessionDetailView extends AViewer {

	/** The table. */
	private Table table;

	/** The table composite. */
	private Composite tableComposite;

	/** The items. */
	private TableItem[] items;

	/**
	 * Instantiates a new session detail view.
	 */
	public SessionDetailView() {
		setTitleImage(INFO_IMG);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		tableComposite = new Composite(parent, SWT.NATIVE | SWT.FILL);
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

		// setTableContent();
		getSite().getPage().addSelectionListener(this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table, SWT.LEFT);
		tc1.setText("Setting");
		tc2.setText("Value");
		tc1.setWidth(100);
		tc2.setWidth(100);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the rows.
	 */
	private void initRows() {
		items = new TableItem[12];
		for (int i = 0; i < items.length; i++) {
			items[i] = new TableItem(table, SWT.NONE);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) structuredSelection.getFirstElement();

				if (entry.getValue() instanceof Session) {
					Session session = (Session) entry.getValue();

					// PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					// .getActivePage().bringToTop(this);

					//

					table.clearAll();
					updateTable(session);
				}
			}
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Update table.
	 * @param session the session
	 */
	private void updateTable(Session session) {
		int itemIndex = 0;

		if (session instanceof ClientSession) {
			ClientSession clientSession = (ClientSession) session;

			items[itemIndex++].setText(new String[] { "Client", clientSession.getClientName() });
			items[itemIndex++].setText(new String[] { "Seed", Boolean.toString(clientSession.isSeed()) });
			items[itemIndex++].setText(new String[] { "Handshake", Boolean.toString(clientSession.isHandshakeOK()) });
			items[itemIndex++].setText(new String[] { "Speed", ConvertUtils.getNumberAsSpeed(clientSession.getSpeedContainer().getSpeed()) });
		}

		items[itemIndex++].setText(new String[] { "Announce", session.getAnnounce() });
		items[itemIndex++].setText(new String[] { "State", session.getState().getLiteral() });
		items[itemIndex++].setText(new String[] { "Scheduled", Boolean.toString(session.isSheduled()) });
		items[itemIndex++].setText(new String[] { "Conn. timeout", ConvertUtils.getNumberAsTime(session.getTimeout()) });
		items[itemIndex++].setText(new String[] { "Last activity", ConvertUtils.getNumberAsTime(session.getLastActivity()) });

		if (session instanceof ClientSession) {
			String duration = ConvertUtils.getNumberAsTime(System.currentTimeMillis() - session.getDuration());
			items[itemIndex++].setText(new String[] { "Duration", duration });
		} else if (session instanceof TrackerSession) {
			items[itemIndex++].setText(new String[] { "Duration", ConvertUtils.getNumberAsTime(session.getDuration()) });
		}

		if (session instanceof DwnldSession) {
			DwnldSession dwnldSession = (DwnldSession) session;

			byte[] clientBitfield = dwnldSession.getBitfield();
			if (clientBitfield == null) {
				return;
			}

			char[] c = null;
			try {
				c = new char[clientBitfield.length * 8];

				for (int i = 0; i < clientBitfield.length; i++) {
					c[i] = isTrue(clientBitfield[i / 8], i % 8) == true ? '1' : '0';
				}
				String binaryBitfield = new String(c);

				items[itemIndex++].setText(new String[] { "Bitfield", binaryBitfield });

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is true.
	 * @param b the b
	 * @param pos the pos
	 * @return true, if is true
	 */
	public boolean isTrue(byte b, int pos) {
		int bitmask = 1 << (7 - pos);
		return (b & bitmask) != 0;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#hookContextMenu()
	 */
	@Override
	public void hookContextMenu() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#getViewer()
	 */
	@Override
	public Viewer getViewer() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	protected void fillLocalToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillLocalPullDown(IMenuManager menuManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		// TODO Auto-generated method stub

	}
}
