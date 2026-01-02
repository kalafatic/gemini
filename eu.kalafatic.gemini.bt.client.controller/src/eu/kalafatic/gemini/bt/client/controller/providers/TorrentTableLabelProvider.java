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
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.INDEXES_FONT;

import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ATorrentTableLabelProvider;
import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.ConvertUtils;

/**
 * The Class class TorrentTableLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentTableLabelProvider extends ATorrentTableLabelProvider {

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/**
	 * Instantiates a new torrent table label provider.
	 * @param viewer the viewer
	 */
	public TorrentTableLabelProvider(Viewer viewer) {
		init(viewer, 1000);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		try {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			Entry<?, ?> entry = (Entry<?, ?>) element;

			if (entry.getValue() == null) {
				return null;
			}

			extTorrent = (ExtTorrent) entry.getValue();
			AdditionalInfo additionalInfo = extTorrent.getAdditionalInfo();

			// trying make it faster
			if (cache.containsKey(extTorrent)) {
				swarmSession = cache.get(extTorrent);
			} else {
				swarmSession = NetworkModelManager.getInstance().getSwarmSession(extTorrent);
				cache.put(extTorrent, swarmSession);
			}

			switch (columnIndex) {

			case ETorrentTableColumns.ENABLED_COL_VALUE:
				return Integer.toString(extTorrent.getRow());

			case ETorrentTableColumns.NAME_COL_VALUE:
				return extTorrent.getName();

			case ETorrentTableColumns.STATUS_COL_VALUE:
				return extTorrent.getStatus().getLiteral();

			case ETorrentTableColumns.SEEDS_COL_VALUE:
				return Integer.toString(additionalInfo.getSeeds());

			case ETorrentTableColumns.PEERS_COL_VALUE:
				return resolveUploaders(additionalInfo, swarmSession);

			case ETorrentTableColumns.DOWNLOADERS_COL_VALUE:
				return resolveDownloaders(additionalInfo, swarmSession);

			case ETorrentTableColumns.SIZE_COL_VALUE:
				return ConvertUtils.getNumberAsMemorySize(additionalInfo.getFileSize());

			case ETorrentTableColumns.PIECES_COL_VALUE:
				String completedP = Integer.toString(additionalInfo.getCompletedPieces());
				int modelBitfieldLength = extTorrent.getInfo().getPieces().length / 20;
				String len = Integer.toString(modelBitfieldLength);
				return completedP + " / " + len;

			case ETorrentTableColumns.DOWNLOADED_COL_VALUE:
				return ConvertUtils.getNumberAsMemorySize(additionalInfo.getDownloaded());

			case ETorrentTableColumns.COMPLETED_COL_VALUE:
				// return resolveCompleted(extTorrent);
				return " ";

			case ETorrentTableColumns.UPLOADED_COL_VALUE:
				return ConvertUtils.getNumberAsMemorySize(additionalInfo.getUploaded());

			case ETorrentTableColumns.DWNLD_SPEED_COL_VALUE:
				return ConvertUtils.getNumberAsSpeed(additionalInfo.getDwnldSpeed());

			case ETorrentTableColumns.UPLD_SPEED_COL_VALUE:
				return ConvertUtils.getNumberAsSpeed(additionalInfo.getUpldSpeed());

			default:
				break;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve uploaders.
	 * @param additionalInfo the additional info
	 * @param swarmSession the swarm session
	 * @return the string
	 */
	private String resolveUploaders(AdditionalInfo additionalInfo, SwarmSession swarmSession) {

		String result = Integer.toString(additionalInfo.getPeers());
		result += " / " + Integer.toString(swarmSession.getDownloads().size());
		result += " / " + Integer.toString(swarmSession.getClients().size());
		return result;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableFontProvider#getFont(java.lang.Object, int)
	 */
	@Override
	public Font getFont(Object element, int columnIndex) {
		if (columnIndex == ETorrentTableColumns.COMPLETED_COL_VALUE) {
			return INDEXES_FONT;
		}
		return null;
	}

}
