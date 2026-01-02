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

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ATorrentActionHandler;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter.BTRun;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.lib.constants.FUIConstants;

/**
 * The Class class PauseTorrentsHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class PauseTorrentsHandler extends ATorrentActionHandler {

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
				TableViewer tableViewer = (TableViewer) ((IViewer) viewPart).getViewer();

				if (tableViewer != null) {
					List<ExtTorrent> selected = getSelectedTorrents(tableViewer, false, EViewsMessages.PAUSED);
					setCommandSourceForAdapter(tableViewer, selected.size() == tableViewer.getTable().getItemCount());
					NetworkManager.getInstance().pauseSwarms(selected);
				}
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the command source for adapter.
	 * @param tableViewer the table viewer
	 * @param all the all
	 */
	private void setCommandSourceForAdapter(TableViewer tableViewer, boolean all) {
		if (all) {
			if (tableViewer.getData(VIEWER_DATA_TYPE).equals("TorrentsTableView")) {
				SwarmsAdapter.run.flag &= ~BTRun.TORRENTS_UNF.flag;

			} else if (tableViewer.getData(VIEWER_DATA_TYPE).equals("FinishedTorrentsTableView")) {
				SwarmsAdapter.run.flag &= ~BTRun.TORRENTS_FIN.flag;
			}
			tableViewer.getTable().setBackground(FUIConstants.LIGHT_BLUE);
		} else {
			tableViewer.getTable().setBackground(FUIConstants.WHITE);
		}
	}

}
