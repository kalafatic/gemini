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
package eu.kalafatic.gemini.bt.client.controller.handlers;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.core.interfaces.IViewer;

/**
 * The Class class CheckAllHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class CheckAllHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands. ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				TableViewer tableViewer = (TableViewer) ((IViewer) viewPart).getViewer();
				checkAll(tableViewer.getTable());

			} else if (viewPart instanceof ITable) {
				ITable iTable = (ITable) viewPart;
				checkAll(iTable.getTable());
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Check all.
	 * @param table the table
	 */
	private void checkAll(final Table table) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				TableItem[] items = table.getItems();

				if (items.length > 0) {
					if (items[0].getChecked()) {
						checkAll(table, false);
					} else {
						checkAll(table, true);
					}
					NetworkModelManager.getInstance().doSave();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Check all.
	 * @param table the table
	 * @param check the check
	 */
	private void checkAll(final Table table, boolean check) {
		TableItem[] items = table.getItems();
		for (TableItem item : items) {

			item.setChecked(check);

			if (item.getData() instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) item.getData();

				ExtTorrent extTorrent = (ExtTorrent) entry.getValue();
				extTorrent.setEnabled(check);

			} else if (item.getData() instanceof DataFile) {
				DataFile dataFile = (DataFile) item.getData();

				dataFile.setEnabled(check);
				item.setForeground(check ? BLACK : RED);
			}
		}
	}
}
