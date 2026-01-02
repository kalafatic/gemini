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
package eu.kalafatic.gemini.bt.tracker.controller.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;

/**
 * The Class class TrackerSwarmTreeLabelProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

/**
 * @author Petr Kalafatic
 * @project Gemini 2.0
 */
public class TrackerSwarmTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

	/** The torrent. */
	private Image torrent = Activator.getImageDescriptor("icons/t.jpg").createImage();

	/** The bee. */
	private Image bee = Activator.getImageDescriptor("icons/bee-25-16.JPG").createImage();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			if (element instanceof EcoreEMap<?, ?>) {
				return FOLDER_IMG;

			} else if (element instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) element;

				if (entry.getValue() instanceof TorrentSession) {
					return torrent;
				} else if (entry.getValue() instanceof ClientSession) {
					return bee;
				}
			}

		default:
			break;
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

		switch (columnIndex) {
		case 0:
			if (element instanceof EcoreEMap<?, ?>) {
				return "Torrents";

			} else if (element instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) element;

				if (entry.getValue() instanceof TorrentSession) {
					TorrentSession torrentSession = (TorrentSession) entry.getValue();
					return torrentSession.getTorrentName();
				} else if (entry.getValue() instanceof ClientSession) {
					ClientSession session = (ClientSession) entry.getValue();
					return session.getAddress();
				}
			}
			break;

		case 1:
			if (element instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) element;

				if (entry.getValue() instanceof ClientSession) {
					Session session = (Session) entry.getValue();

					if (session.getPeerId() == null) {
						return "";
					}
					return session.getPeerId();
				}
			}
			break;

		case 2:
			if (element instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) element;

				if (entry.getValue() instanceof TorrentSession) {
					TorrentSession session = (TorrentSession) entry.getValue();
					try {
						if (session.getInfoHash() == null) {
							return "";
						}
						return "Hash : " + session.getInfoHash();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (entry.getValue() instanceof ClientSession) {
					ClientSession session = (ClientSession) entry.getValue();
					return session.getNote();
				}
			}
		default:
			break;
		}

		return "";
	}

}
