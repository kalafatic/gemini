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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.lib.EHandler;

/**
 * The Class class ImportTorrentHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ImportTorrentHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.NONE);
		fileDialog.setFilterExtensions(new String[] { "*.torrent" });
		String open = fileDialog.open();

		if (open != null) {
			CommandFactory.INSTANCE.executeCommand(EHandler.ADD_TORRENT.ID, open, "false");
		}
		return null;
	}
}
