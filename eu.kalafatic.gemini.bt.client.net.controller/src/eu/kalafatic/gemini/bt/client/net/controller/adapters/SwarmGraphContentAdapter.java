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

import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Class class SwarmGraphContentAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmGraphContentAdapter extends EContentAdapter implements Adapter {

	/** The viewer. */
	private GraphViewer viewer;

	/** The lock. */
	private ReentrantLock lock;

	/** The graph. */
	private Graph graph;

	/**
	 * Instantiates a new swarm graph content adapter.
	 * @param viewer the viewer
	 * @param lock the lock
	 */
	public SwarmGraphContentAdapter(GraphViewer viewer, ReentrantLock lock) {
		this.viewer = viewer;
		this.lock = lock;

		graph = viewer.getGraphControl();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse. emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getFeature() instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute) notification.getFeature();

			String attributeName = eAttribute.getName();

			if (attributeName.equals("color")) {
				IOPiece piece = (IOPiece) notification.getNotifier();

				EObject eContainer = piece.eContainer().eContainer();
				if (eContainer instanceof SwarmSession) {

				}

			} else if (attributeName.equals("enabled")) {
				System.err.println();
			} else if (attributeName.equals("status")) {
				updateState();
			}

		} else if (notification.getFeature() instanceof EReference) {
			refresh();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Update state.
	 */
	private void updateState() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {

				// Object[] nodeElements = viewer.getNodeElements();
				//
				// for (int i = 0; i < nodeElements.length; i++) {
				// if (nodeElements[i] instanceof GraphItem) {
				// System.err.println();
				// } else if (nodeElements[i] instanceof GraphNode) {
				// System.err.println();
				// }else{
				// System.err.println();
				// }
				//
				// }
			}
		});
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
