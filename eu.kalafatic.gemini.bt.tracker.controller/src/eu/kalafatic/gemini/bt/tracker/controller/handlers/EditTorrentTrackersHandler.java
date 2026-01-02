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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.utils.dialogs.TrackersTree;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class EditTorrentTrackersHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("rawtypes")
public class EditTorrentTrackersHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();

			if (selection instanceof IStructuredSelection) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;

				if (iStructuredSelection.isEmpty()) {
					DialogUtils.INSTANCE.warn("Empty selection");
				} else {
					List<ExtTorrent> selectedTorrents = getSelectedTorrents(iStructuredSelection);
					new TrackersTree(selectedTorrents).open();
				}
			}
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected torrents.
	 * @param selection the selection
	 * @return the selected torrents
	 */
	private List<ExtTorrent> getSelectedTorrents(IStructuredSelection selection) {
		List<ExtTorrent> list = new ArrayList<ExtTorrent>();

		Iterator iterator = selection.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();

			if (object instanceof Entry) {
				Entry<?, ?> entry = (Entry<?, ?>) object;

				if (entry.getValue() instanceof TorrentSession) {
					TorrentSession torrentSession = (TorrentSession) entry.getValue();

					list.add((ExtTorrent) torrentSession.getTorrent());
				}
			}
		}
		return list;
	}
}