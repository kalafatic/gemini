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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.components.EditClientDialog;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;

/**
 * The Class class AddClientHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class AddClientHandler extends AbstractHandler {

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
				TreeViewer viewer = (TreeViewer) ((IViewer) viewPart).getViewer();
				checkSelection(viewer);
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Check selection.
	 * @param viewer the viewer
	 */
	private void checkSelection(TreeViewer viewer) {
		ISelection selection = viewer.getSelection();

		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
				Object selectedObject = iterator.next();

				if (selectedObject instanceof Entry<?, ?>) {
					Entry<?, ?> entry = (Entry<?, ?>) selectedObject;

					if (entry.getValue() instanceof TorrentSession) {
						TorrentSession session = (TorrentSession) entry.getValue();
						createClient(viewer, session);
					}
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client.
	 * @param viewer the viewer
	 * @param torrentSession the torrent session
	 */
	private void createClient(TreeViewer viewer, TorrentSession torrentSession) {

		try {
			String[] request = new String[3];
			EditClientDialog editClientDialog = new EditClientDialog(viewer.getControl().getShell(), request);

			if (editClientDialog.open() == Window.OK) {

				String host = request[0];
				String port = request[1];
				String note = request[2];

				if (host.length() == 0 || port.length() == 0) {
					showMessage(viewer, "Can not read address");
					return;
				}
				// check address
				InetAddress.getByName(host);

				String address = host + ":" + port;

				ClientSession session = TrackerFactory.eINSTANCE.createClientSession();

				session.setAddress(address);
				session.setInfoHash(torrentSession.getInfoHash());

				if (note.length() > 0) {
					session.setNote(note);
				}

				torrentSession.getClientMap().put(address, session);
				TrackerModelManager.getInstance().doSave();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			showMessage(viewer, "Unknown host");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Show message.
	 * @param viewer the viewer
	 * @param message the message
	 */
	private void showMessage(TreeViewer viewer, String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(), "Client", message);
	}

}
