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
package eu.kalafatic.gemini.bt.tracker.controller.adapters;

import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.actions.TrackerModelActions;
import eu.kalafatic.gemini.bt.tracker.controller.utils.TrackerUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TorrentsAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TorrentsAdapter extends AdapterImpl implements Adapter {

	/** The viewer. */
	private final TreeViewer viewer;

	/** The lock. */
	public final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new torrents adapter.
	 * @param treeViewer the tree viewer
	 */
	public TorrentsAdapter(TreeViewer treeViewer) {
		this.viewer = treeViewer;
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

			if (feature.getName().equals("torrentMap")) {

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
			TrackerModelActions.INSTANCE.addTorrentToModel(extTorrent, TrackerUtils.INSTANCE.getTrackerAnnounce(), TrackerUtils.INSTANCE.getClientAddress());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	private void refresh() {
		if (lock.tryLock()) {
			try {
				Display.getDefault().asyncExec(refresh);
			} catch (Exception e) {
				Log.log(ETrackerPreferences.MODULE, e);
			} finally {
				lock.unlock();
			}
		}
	}

	// ---------------------------------------------------------------

	/** The refresh. */
	private final Runnable refresh = new Runnable() {
		@Override
		public void run() {
			lock.lock();
			try {
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {

					viewer.refresh();
				}
			} finally {
				lock.unlock();
			}
		}
	};
}
