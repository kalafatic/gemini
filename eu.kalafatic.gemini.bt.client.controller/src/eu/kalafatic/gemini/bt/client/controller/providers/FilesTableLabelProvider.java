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

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.INDEXES_FONT;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.lib.EColor;

/**
 * The Class class FilesTableLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FilesTableLabelProvider implements ITableLabelProvider, ITableColorProvider, ITableFontProvider {

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The Constant SHIFT. */
	private static final int SHIFT = 6;

	/**
	 * Instantiates a new files table label provider.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 */
	public FilesTableLabelProvider(ExtTorrent extTorrent, SwarmSession swarmSession) {
		this.extTorrent = extTorrent;
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse. jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		// if (ball != null)
		// ball.dispose();

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang .Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse .jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang .Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		DataFile dataFile = (DataFile) element;

		switch (columnIndex) {
		case 0:
			return Integer.toString(dataFile.getIndex());
		case 1:
			return dataFile.getName();
		case 2:
			return dataFile.getPath();
		case 3:
			return Long.toString(dataFile.getLength());
		case 4:
			return dataFile.getMd5sum();
		case 5:
			return Integer.toString(dataFile.getPieces().length);
		default:
			if ((dataFile.getPieces().length + SHIFT) > columnIndex) {
				int i = dataFile.getPieces()[columnIndex - SHIFT];
				return Integer.toString(i);
			} else {
				return "";
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.lang .Object, int)
	 */
	@Override
	public Color getForeground(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang .Object, int)
	 */
	@Override
	public Color getBackground(Object element, int columnIndex) {
		if (columnIndex < SHIFT) {
			return null;
		}
		DataFile datafile = ((DataFile) element);

		if ((datafile.getPieces().length + SHIFT) <= columnIndex) {
			return null;
		}
		int pieceIndex = datafile.getPieces()[columnIndex - SHIFT];

		if (swarmSession.getProcessedPieces().containsKey(pieceIndex)) {
			IOPiece ioPiece = swarmSession.getProcessedPieces().get((Integer) pieceIndex);
			return EColor.values()[ioPiece.getColor()].color;
		}
		// max 15 pieces
		for (IOPiece piece : swarmSession.getUploadedPieces()) {
			if (piece.getPieceIndex() == pieceIndex) {
				return EColor.UPLD.color;
			}
		}
		if (extTorrent.getModelBitfield()[pieceIndex]) {
			return EColor.FIN.color;
		}
		return EColor.EMPTY.color;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableFontProvider#getFont(java.lang.Object, int)
	 */
	@Override
	public Font getFont(Object element, int columnIndex) {
		if (columnIndex >= SHIFT) {
			return INDEXES_FONT;
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the swarm session.
	 * @param swarmSession the new swarm session
	 */
	public void setSwarmSession(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the ext torrent.
	 * @param extTorrent the new ext torrent
	 */
	public void setExtTorrent(ExtTorrent extTorrent) {
		this.extTorrent = extTorrent;
	}

}
