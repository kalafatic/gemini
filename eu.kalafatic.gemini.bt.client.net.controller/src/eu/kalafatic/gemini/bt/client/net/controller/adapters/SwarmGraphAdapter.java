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

import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;

/**
 * The Class class SwarmGraphAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmGraphAdapter extends AdapterImpl implements Adapter {

	/** The viewer. */
	private GraphViewer viewer;

	/** The graph. */
	private Graph graph;

	/** The lock. */
	private ReentrantLock lock;

	/** The content adapter. */
	private SwarmGraphContentAdapter contentAdapter;

	/**
	 * Instantiates a new swarm graph adapter.
	 * @param viewer the viewer
	 * @param lock the lock
	 * @param contentAdapter the content adapter
	 */
	public SwarmGraphAdapter(GraphViewer viewer, ReentrantLock lock, SwarmGraphContentAdapter contentAdapter) {
		this.viewer = viewer;
		this.lock = lock;
		this.contentAdapter = contentAdapter;

		graph = viewer.getGraphControl();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		try {

			if (notification.getFeature() instanceof EReference) {
				EReference feature = (EReference) notification.getFeature();

				switch (notification.getEventType()) {

				case Notification.ADD:
					if (feature.getName().equals("swarmMap")) {
						addSwarmNode(notification);
					} else if (feature.getName().equals("trackers")) {
						addNodeToSwarm(notification);
					} else if (feature.getName().equals("downloads")) {
						// addNodeToSwarm(notification);
						addNodeToTrackers(notification);
					} else if (feature.getName().equals("uploads")) {
						addNodeToSwarm(notification);
					} else if (feature.getName().equals("clients")) {
						addNodeToTrackers(notification);
					}
					refresh();
					break;

				case Notification.REMOVE:
					refresh();
					break;
				}

			} else if (notification.getFeature() instanceof EAttribute) {
				EAttribute attribute = (EAttribute) notification.getFeature();

				if (attribute.eContainer() instanceof Session) {}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the swarm node.
	 * @param notification the notification
	 */
	private void addSwarmNode(Notification notification) {
		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();
		Session session = (Session) entry.getValue();

		session.eAdapters().add(contentAdapter);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the node to swarm.
	 * @param notification the notification
	 */
	private void addNodeToSwarm(Notification notification) {
		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();
		Object value = entry.getValue();

		if (value instanceof Session) {
			Session session = (Session) value;

			if (session.eContainer().eContainer() instanceof Session) {
				Session swarmSession = (Session) session.eContainer().eContainer();

				swarmSession.eAdapters().add(contentAdapter);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the node to trackers.
	 * @param notification the notification
	 */
	private void addNodeToTrackers(Notification notification) {
		Entry<?, ?> entry = (Entry<?, ?>) notification.getNewValue();

		Object value = entry.getValue();
		if (value instanceof Session) {
			Session session = (Session) value;

			if (session.eContainer().eContainer() instanceof Session) {
				Session swarmSession = (Session) session.eContainer().eContainer();

				swarmSession.eAdapters().add(contentAdapter);
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
			if (lock.tryLock()) {
				try {
					if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() && viewer.getControl().isVisible()) {

						viewer.refresh(false);
						graph.applyLayout();
						// viewer.refresh();
						Thread.sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	};

}
