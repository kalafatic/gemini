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
package eu.kalafatic.gemini.bt.tracker.controller.handlers;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.bt.tracker.controller.model.actions.TrackerModelActions;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EExt;

/**
 * The Class class ImportTorrentHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ImportTorrentHandler extends AbstractHandler {

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.tracker.controller.ImportParameter1";

	/** The PARAMETE r_2. */
	private final String PARAMETER_2 = "eu.kalafatic.gemini.bt.tracker.controller.ImportParameter2";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			String path = event.getParameter(PARAMETER_1);
			String clients = event.getParameter(PARAMETER_2);

			if ((path == null) || path.isEmpty()) {
				String torrents = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

				FileDialog fileDialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.NONE);
				fileDialog.setFilterPath(torrents);
				fileDialog.setFilterExtensions(new String[] { "*" + EExt.TORRENT.ext });

				path = fileDialog.open();
			}
			if ((path != null) && (clients != null) && !clients.isEmpty()) {
				TrackerModelActions.INSTANCE.addTorrentToModel(path, clients.split(","));
			} else if (path != null) {
				TrackerModelActions.INSTANCE.addTorrentToModel(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
