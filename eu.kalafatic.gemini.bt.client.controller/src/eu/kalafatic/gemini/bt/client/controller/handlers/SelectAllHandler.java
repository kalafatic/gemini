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
import eu.kalafatic.gemini.core.interfaces.IViewer;

/**
 * The Class class SelectAllHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class SelectAllHandler extends AbstractHandler {

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

			if (viewPart instanceof ITable) {
				ITable iTable = (ITable) viewPart;
				selectAll(iTable.getTable());
			} else if (viewPart instanceof IViewer) {
				TableViewer tableViewer = (TableViewer) ((IViewer) viewPart).getViewer();
				selectAll(tableViewer.getTable());
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Select all.
	 * @param table the table
	 */
	private void selectAll(final Table table) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				TableItem[] items = table.getItems();

				if (items.length > 0) {
					if (table.isSelected(0)) {
						table.deselectAll();
					} else {
						table.selectAll();
					}
				}
			}
		});
	}
}
