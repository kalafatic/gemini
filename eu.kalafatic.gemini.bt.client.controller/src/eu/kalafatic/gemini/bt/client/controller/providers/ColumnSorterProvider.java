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

import java.util.Map.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Class class ColumnSorterProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ColumnSorterProvider extends ViewerSorter {

	/** The finished. */
	private boolean finished;

	/** The Constant ASCENDING. */
	private static final int ASCENDING = 0;

	/** The Constant DESCENDING. */
	private static final int DESCENDING = 1;

	/** The column. */
	private int column;

	/** The direction. */
	private int direction = DESCENDING;

	/**
	 * Instantiates a new column sorter provider.
	 * @param finished the finished
	 */
	public ColumnSorterProvider(boolean finished) {
		this.finished = finished;
		this.column = 0;
		direction = ASCENDING;

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Do sort.
	 * @param column the column
	 */
	public void doSort(int column) {
		if (column == this.column) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface. viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Viewer viewer, Object o1, Object o2) {

		ExtTorrent extTorrent1;
		ExtTorrent extTorrent2;

		int result = 0;

		if (o1 instanceof Entry<?, ?>) {
			extTorrent1 = (ExtTorrent) ((Entry<?, ?>) o1).getValue();
			if (extTorrent1 == null) {
				return result;
			}
		} else {
			return result;
		}

		if (o2 instanceof Entry<?, ?>) {
			extTorrent2 = (ExtTorrent) ((Entry<?, ?>) o2).getValue();
			if (extTorrent2 == null) {
				return result;
			}
		} else {
			return result;
		}

		if (finished) {
			return compareFinishedTorrents(extTorrent1, extTorrent2);
		} else {
			return compareTorrents(extTorrent1, extTorrent2);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Compare torrents.
	 * @param extTorrent1 the ext torrent1
	 * @param extTorrent2 the ext torrent2
	 * @return the int
	 */
	private int compareTorrents(ExtTorrent extTorrent1, ExtTorrent extTorrent2) {

		int result = 0;

		switch (column) {
		case ETorrentTableColumns.ENABLED_COL_VALUE:
			result = extTorrent1.getRow() > extTorrent2.getRow() ? 1 : -1;
			break;
		case ETorrentTableColumns.NAME_COL_VALUE:
			result = extTorrent1.getName().compareToIgnoreCase(extTorrent2.getName());
			break;
		case ETorrentTableColumns.STATUS_COL_VALUE:

			break;
		case ETorrentTableColumns.SEEDS_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getSeeds() > extTorrent2.getAdditionalInfo().getSeeds() ? 1 : -1;
			break;
		case ETorrentTableColumns.PEERS_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getPeers() > extTorrent2.getAdditionalInfo().getPeers() ? 1 : -1;
			break;
		case ETorrentTableColumns.DOWNLOADERS_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getDownloaders() > extTorrent2.getAdditionalInfo().getDownloaders() ? 1 : -1;
			break;

		case ETorrentTableColumns.SIZE_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getFileSize() > extTorrent2.getAdditionalInfo().getFileSize() ? 1 : -1;
			break;

		case ETorrentTableColumns.PIECES_COL_VALUE:
			result = extTorrent1.getModelBitfield().length > extTorrent2.getModelBitfield().length ? 1 : -1;
			break;

		case ETorrentTableColumns.DOWNLOADED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getDownloaded() > extTorrent2.getAdditionalInfo().getDownloaded() ? 1 : -1;
			break;

		case ETorrentTableColumns.COMPLETED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getCompleted() > extTorrent2.getAdditionalInfo().getCompleted() ? 1 : -1;
			break;

		case ETorrentTableColumns.UPLOADED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getUploaded() > extTorrent2.getAdditionalInfo().getUploaded() ? 1 : -1;
			break;

		case ETorrentTableColumns.DWNLD_SPEED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getDwnldSpeed() > extTorrent2.getAdditionalInfo().getDwnldSpeed() ? 1 : -1;
			break;

		case ETorrentTableColumns.UPLD_SPEED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getUpldSpeed() > extTorrent2.getAdditionalInfo().getUpldSpeed() ? 1 : -1;
			break;
		default:
			result = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			result = -result;
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Compare finished torrents.
	 * @param extTorrent1 the ext torrent1
	 * @param extTorrent2 the ext torrent2
	 * @return the int
	 */
	private int compareFinishedTorrents(ExtTorrent extTorrent1, ExtTorrent extTorrent2) {

		int result = 0;

		switch (column) {
		case EFinishedTableColumns.ENABLED_COL_VALUE:
			result = extTorrent1.getRow() > extTorrent2.getRow() ? 1 : -1;
			break;
		case EFinishedTableColumns.NAME_COL_VALUE:
			result = extTorrent1.getName().compareToIgnoreCase(extTorrent2.getName());
			break;
		case EFinishedTableColumns.STATUS_COL_VALUE:

			break;
		case EFinishedTableColumns.DOWNLOADERS_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getDownloaders() > extTorrent2.getAdditionalInfo().getDownloaders() ? 1 : -1;
			break;
		case EFinishedTableColumns.SIZE_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getFileSize() > extTorrent2.getAdditionalInfo().getFileSize() ? 1 : -1;
			break;
		case EFinishedTableColumns.PIECES_COL_VALUE:
			result = extTorrent1.getModelBitfield().length > extTorrent2.getModelBitfield().length ? 1 : -1;
			break;
		case EFinishedTableColumns.UPLOADED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getUploaded() > extTorrent2.getAdditionalInfo().getUploaded() ? 1 : -1;
			break;

		case EFinishedTableColumns.UPLD_SPEED_COL_VALUE:
			result = extTorrent1.getAdditionalInfo().getUpldSpeed() > extTorrent2.getAdditionalInfo().getUpldSpeed() ? 1 : -1;
			break;

		default:
			result = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			result = -result;
		}
		return result;
	}

}
