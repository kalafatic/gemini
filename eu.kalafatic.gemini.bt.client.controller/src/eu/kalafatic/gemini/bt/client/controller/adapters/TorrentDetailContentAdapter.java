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

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TorrentDetailContentAdapter.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentDetailContentAdapter extends EContentAdapter implements Adapter {

	/** The viewer. */
	private final Viewer viewer;

	/** The lock. */
	private AtomicBoolean lock = new AtomicBoolean(false);

	/**
	 * Instantiates a new torrent detail content adapter.
	 * @param viewer the viewer
	 */
	public TorrentDetailContentAdapter(Viewer viewer) {
		this.viewer = viewer;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse. emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);

		if (notification.isTouch()) {
			return;
		}
		if (notification.getFeature() instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute) notification.getFeature();

			if (eAttribute.getName().equals("color")) {
				// refreshPieeceState(notification);
				refresh();

			} else if (eAttribute.getName().equals("enabled")) {
				// System.err.println();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	private void refresh() {
		try {
			if (lock.compareAndSet(false, true)) {
				Display.getDefault().asyncExec(refresh);
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/** The refresh. */
	private final Runnable refresh = new Runnable() {
		@Override
		public void run() {
			try {
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() && viewer.getControl().isVisible()) {

					viewer.refresh();
				}
			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			} finally {
				lock.set(false);
			}
		}
	};
}