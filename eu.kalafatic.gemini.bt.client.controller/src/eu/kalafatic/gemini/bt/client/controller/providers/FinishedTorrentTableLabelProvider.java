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
package eu.kalafatic.gemini.bt.client.controller.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.WAIT_IMG;

import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ATorrentTableLabelProvider;
import eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.ConvertUtils;

/**
 * The Class class FinishedTorrentTableLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FinishedTorrentTableLabelProvider extends ATorrentTableLabelProvider {

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/**
	 * Instantiates a new finished torrent table label provider.
	 * @param viewer the viewer
	 */
	public FinishedTorrentTableLabelProvider(TableViewer viewer) {
		init(viewer, 500);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {

		if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (BasicEMap.Entry<?, ?>) element;

			if (entry.getValue() == null) {
				return null;
			}

			extTorrent = (ExtTorrent) entry.getValue();

			final String key = extTorrent.getName();

			final EList<String> activeSwarms = NetworkModelManager.getInstance().getClientNetwork().getActiveSwarms();

			switch (columnIndex) {
			case 0:
				if (activeSwarms.contains(key) && extTorrent.isEnabled()) {
					return gif;
					// return PROGRESS_IMG;
				} else {
					return WAIT_IMG;
				}
			case 1:

				break;

			case 2:
				break;
			// return progress;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang .Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {

		if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (BasicEMap.Entry<?, ?>) element;

			if (entry.getValue() == null) {
				return null;
			}

			extTorrent = (ExtTorrent) entry.getValue();

			// trying make it faster
			if (cache.containsKey(extTorrent)) {
				swarmSession = cache.get(extTorrent);
			} else {
				swarmSession = NetworkModelManager.getInstance().getSwarmSession(extTorrent);
				cache.put(extTorrent, swarmSession);
			}

			switch (columnIndex) {

			case EFinishedTableColumns.ENABLED_COL_VALUE:
				return Integer.toString(extTorrent.getRow());

			case EFinishedTableColumns.NAME_COL_VALUE:
				return extTorrent.getName();

			case EFinishedTableColumns.STATUS_COL_VALUE:
				if (extTorrent.getStatus() == null) {
					extTorrent.setStatus(EViewsMessages.READY_TO_UPLOAD);
				}
				return extTorrent.getStatus().getLiteral();

			case EFinishedTableColumns.DOWNLOADERS_COL_VALUE:
				return resolveDownloaders(extTorrent.getAdditionalInfo(), swarmSession);

			case EFinishedTableColumns.SIZE_COL_VALUE:
				return ConvertUtils.getNumberAsMemorySize(extTorrent.getAdditionalInfo().getFileSize());

			case EFinishedTableColumns.PIECES_COL_VALUE:
				int modelBitfieldLength = extTorrent.getInfo().getPieces().length / 20;
				return Integer.toString(modelBitfieldLength);

			case EFinishedTableColumns.UPLOADED_COL_VALUE:
				return ConvertUtils.getNumberAsMemorySize(extTorrent.getAdditionalInfo().getUploaded());

			case EFinishedTableColumns.UPLD_SPEED_COL_VALUE:
				return ConvertUtils.getNumberAsSpeed(extTorrent.getAdditionalInfo().getUpldSpeed());
			}
		}
		return null;
	}
}
