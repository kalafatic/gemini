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

import static eu.kalafatic.gemini.core.lib.constants.FTableConstants.VIEWER_DATA_TYPE;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ATorrentActionHandler;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter.BTRun;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AControl;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.lib.constants.FUIConstants;

/**
 * The Class class RunTorrentsHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RunTorrentsHandler extends ATorrentActionHandler {

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
				Object viewer = ((IViewer) viewPart).getViewer();

				if (viewer instanceof TableViewer) {
					TableViewer tableViewer = (TableViewer) viewer;

					// SessionDetailView
					if (tableViewer != null) {
						List<ExtTorrent> selected = getSelectedTorrents(tableViewer, true, EViewsMessages.READY);
						setCommandSourceForAdapter(tableViewer);
						NetworkManager.getInstance().runSwarms(selected);
					}
				} else if (viewer instanceof Chart) {
					// Chart chart = (Chart) viewer;
					NetworkManager.getInstance().runSwarms();
					// PlatformUI
					// .getWorkbench()
					// .showPerspective(
					// "eu.kalafatic.gemini.bt.client.view.perspectives.ClientBasePerspective",
					// PlatformUI.getWorkbench()
					// .getActiveWorkbenchWindow());
				}
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the command source for adapter.
	 * @param tableViewer the new command source for adapter
	 */
	private void setCommandSourceForAdapter(TableViewer tableViewer) {
		try {
			if (tableViewer.getData(VIEWER_DATA_TYPE) == null) {
				SwarmsAdapter.run.flag |= BTRun.TORRENTS_UNF.flag;
				AControl.dwnldStarted = true;
				return;
			}
			if (tableViewer.getData(VIEWER_DATA_TYPE).equals("TorrentsTableView")) {
				SwarmsAdapter.run.flag |= BTRun.TORRENTS_UNF.flag;
				AControl.dwnldStarted = true;

			} else if (tableViewer.getData(VIEWER_DATA_TYPE).equals("FinishedTorrentsTableView")) {
				SwarmsAdapter.run.flag |= BTRun.TORRENTS_FIN.flag;
			}
			tableViewer.getTable().setBackground(FUIConstants.LIGHT_GREEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
