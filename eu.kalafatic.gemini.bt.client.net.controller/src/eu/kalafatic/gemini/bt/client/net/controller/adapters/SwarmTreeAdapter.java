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
package eu.kalafatic.gemini.bt.client.net.controller.adapters;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Class class SwarmTreeAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmTreeAdapter extends AdapterImpl implements Adapter {

	/** The viewer. */
	private final TreeViewer viewer;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new swarm tree adapter.
	 * @param treeViewer the tree viewer
	 */
	public SwarmTreeAdapter(TreeViewer treeViewer) {
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

		if (notification.getFeature() instanceof EReference) {

			EReference feature = (EReference) notification.getFeature();

			if (feature instanceof Session) {
				// System.err.println();
			}

			if (feature.getName().equals("swarmMap")) {

				switch (notification.getEventType()) {

				case Notification.ADD:
					addSwarm(notification);
					break;

				case Notification.REMOVE:
					removeSwarm(notification);
					break;
				}

			} else if (feature.getName().equals("downloads")) {
				// System.err.println();
			} else if (feature.getName().equals("uploads")) {
				// System.err.println();
			} else if (feature.getName().equals("trackers")) {
				// System.err.println();

			}
		} else if (notification.getFeature() instanceof EAttribute) {
			EAttribute attribute = (EAttribute) notification.getFeature();

			if (attribute.eContainer() instanceof Session) {
				// System.err.println();
			}

			// System.err.println();
		}
		refresh();
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the swarm.
	 * @param notification the notification
	 */

	private/* synchronized */void addSwarm(Notification notification) {
		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();

		Object value = entry.getValue();
		if (value instanceof SwarmSession) {
			SwarmSession session = (SwarmSession) value;

			SwarmTreeContentAdapter contentAdapter = new SwarmTreeContentAdapter(viewer, session);
			session.eAdapters().add(contentAdapter);

			viewer.expandToLevel(session, 2);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the swarm.
	 * @param notification the notification
	 */
	private void removeSwarm(Notification notification) {
		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();

		if (entry == null) {
			return;
		}

		Object value = entry.getValue();
		if (value instanceof SwarmSession) {

			Object input = viewer.getInput();

			if (input instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) input;
				map.remove(entry);
			}
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
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	};

}
