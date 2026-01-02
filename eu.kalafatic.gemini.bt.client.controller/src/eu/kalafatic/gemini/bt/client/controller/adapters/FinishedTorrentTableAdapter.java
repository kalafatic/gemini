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

import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class FinishedTorrentTableAdapter.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FinishedTorrentTableAdapter extends AdapterImpl implements Adapter {

	/** The viewer. */
	private final TableViewer viewer;

	/** The content adapter. */
	private FinishedTorrentTableContentAdapter contentAdapter;

	/**
	 * Instantiates a new finished torrent table adapter.
	 * @param tableViewer the table viewer
	 */
	public FinishedTorrentTableAdapter(TableViewer tableViewer) {
		this.viewer = tableViewer;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse .emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getFeature() instanceof EReferenceImpl) {

			EReferenceImpl feature = (EReferenceImpl) notification.getFeature();

			if (feature.getName().equals("finishedTorrentsMap")) {

				switch (notification.getEventType()) {

				case Notification.ADD:
					addTorrentToTableModel(notification);
					refresh();
					break;

				case Notification.REMOVE:
					break;
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the torrent to table model.
	 * @param notification the notification
	 */
	private void addTorrentToTableModel(Notification notification) {

		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();

		Object value = entry.getValue();
		if (value instanceof ExtTorrent) {
			ExtTorrent extTorrent = (ExtTorrent) value;

			contentAdapter = new FinishedTorrentTableContentAdapter();

			extTorrent.eContainer().eAdapters().add(contentAdapter);
		}
	}

	/**
	 * Refresh.
	 */
	private void refresh() {
		try {
			if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {

				viewer.refresh();

				TableItem[] items = viewer.getTable().getItems();

				for (int i = 0; i < items.length; i++) {
					Object data = items[i].getData();
					if (data instanceof Entry<?, ?>) {
						Entry<?, ?> entry = (Entry<?, ?>) data;
						ExtTorrent extTorrent = (ExtTorrent) entry.getValue();
						extTorrent.setRow(i);

						if (!extTorrent.isEnabled()) {
							extTorrent.setStatus(EViewsMessages.DISABLED);
						}
						items[i].setChecked(extTorrent.isEnabled());
					}
				}
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		} finally {

		}
	}
}
