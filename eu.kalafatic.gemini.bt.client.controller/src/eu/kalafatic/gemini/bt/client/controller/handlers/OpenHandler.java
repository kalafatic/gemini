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

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable;
import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITree;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.utils.OpenFileUtils;

/**
 * The Class class OpenHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class OpenHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof ITable) {
				ITable iTable = (ITable) viewPart;
				TableItem tableItem = iTable.getTable().getSelection()[0];
				Object data = tableItem.getData();

				if (data instanceof DataFile) {
					DataFile dataFile = (DataFile) data;

					final String path = dataFile.getPath();
					openFile(path);
				}
			} else if (viewPart instanceof ITree) {
				ITree iTree = (ITree) viewPart;
				TreeItem treeItem = iTree.getTree().getSelection()[0];

				if (treeItem.getData() instanceof File) {
					File file = (File) treeItem.getData();
					openFile(file.getAbsolutePath());
				}
			} else if (viewPart instanceof IViewer) {}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Open file.
	 * @param path the path
	 */
	private void openFile(final String path) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				OpenFileUtils.getInstance().openFile(path);
			}
		});
	}
}
