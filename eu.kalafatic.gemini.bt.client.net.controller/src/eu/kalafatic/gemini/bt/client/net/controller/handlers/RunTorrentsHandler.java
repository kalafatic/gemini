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
package eu.kalafatic.gemini.bt.client.net.controller.handlers;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.TrackersManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class RunTorrentsHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class RunTorrentsHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				GraphViewer viewer = (GraphViewer) ((IViewer) viewPart).getViewer();

				execute(viewer);
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Execute.
	 * @param viewer the viewer
	 */
	private void execute(GraphViewer viewer) {
		try {
			ISelection selection = viewer.getSelection();
			Object selected = ((IStructuredSelection) selection).getFirstElement();

			SwarmSession swarmSession;

			if (selected instanceof SwarmSession) {
				swarmSession = (SwarmSession) selected;
				NetworkManager.getInstance().runSwarms((ExtTorrent[]) swarmSession.getTorrent());

			} else if (selected instanceof TrackerSession) {
				TrackerSession session = (TrackerSession) selected;

				if (session.eContainer().eContainer() instanceof SwarmSession) {
					swarmSession = (SwarmSession) session.eContainer().eContainer();

					((TrackersManager) swarmSession.getTrackersManager()).runTrackers();
				}
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		}
	}
}
