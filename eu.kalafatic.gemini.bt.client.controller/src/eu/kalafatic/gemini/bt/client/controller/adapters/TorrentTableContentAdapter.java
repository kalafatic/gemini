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
package eu.kalafatic.gemini.bt.client.controller.adapters;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.EContentAdapter;

import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;

/**
 * The Class class TorrentTableContentAdapter.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentTableContentAdapter extends EContentAdapter implements Adapter {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse. emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getFeature() instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute) notification.getFeature();
			String atributeName = eAttribute.getName();

			if (notification.getNotifier() instanceof ExtTorrent) {
				ExtTorrent extTorrent = (ExtTorrent) notification.getNotifier();

				if (atributeName.equals("row")) {} else if (atributeName.equals("enabled")) {} else if (atributeName.equals("finished")) {
					updateFinished(extTorrent);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Update finished.
	 * @param extTorrent the ext torrent
	 */
	private void updateFinished(ExtTorrent extTorrent) {
		ClientModelManager.getInstance().getTorrents().getFinishedTorrentsMap().put(extTorrent.getName(), extTorrent);

		ClientModelManager.getInstance().getTorrents().getTorrentMap().removeKey(extTorrent.getName());

		extTorrent.setStatus(EViewsMessages.READY_TO_UPLOAD);
	}
}
