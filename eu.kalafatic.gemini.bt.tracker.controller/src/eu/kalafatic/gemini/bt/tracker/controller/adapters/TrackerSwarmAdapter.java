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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerSwarmAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerSwarmAdapter extends AdapterImpl implements Adapter {

	/** The viewer. */
	private final Viewer viewer;

	/** The content adapter. */
	private TrackerSwarmContentAdapter contentAdapter;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new tracker swarm adapter.
	 * @param viewer the viewer
	 */
	public TrackerSwarmAdapter(Viewer viewer) {
		this.viewer = viewer;
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

		if (notification.getFeature() instanceof EReference) {

			EReference feature = (EReference) notification.getFeature();

			if (feature.getName().equals("swarmMap")) {

				switch (notification.getEventType()) {

				case Notification.ADD:
					Log.log(ETrackerPreferences.MODULE, "TORRENT : ADD");
					addTorrentToTableModel(notification);
					break;
				case Notification.REMOVE:
					Log.log(ETrackerPreferences.MODULE, "TORRENT : REMOVE");
					break;
				}
				refresh();
				TrackerModelManager.getInstance().doSave();
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
		if (entry.getValue() instanceof TorrentSession) {
			TorrentSession session = (TorrentSession) entry.getValue();

			contentAdapter = new TrackerSwarmContentAdapter(viewer);
			session.eAdapters().add(contentAdapter);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	public void refresh() {
		// if (lock.tryLock()) {
		try {
			Display.getDefault().asyncExec(refresh);
		} finally {
			// lock.unlock();
		}
		// }
	}

	// ---------------------------------------------------------------

	/** The refresh. */
	private final Runnable refresh = new Runnable() {
		@Override
		public void run() {
			// lock.lock();
			try {
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() /* && viewer.getControl().isVisible() */) {

					viewer.refresh();
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				// lock.unlock();
			}
		}
	};

}
