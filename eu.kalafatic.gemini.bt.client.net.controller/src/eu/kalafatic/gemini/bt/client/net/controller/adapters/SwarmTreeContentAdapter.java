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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.TreeViewer;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Class class SwarmTreeContentAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
class SwarmTreeContentAdapter extends EContentAdapter implements Adapter {

	/**
	 * Instantiates a new swarm tree content adapter.
	 * @param treeViewer the tree viewer
	 * @param swarmSession the swarm session
	 */
	// private TreeViewer treeViewer;

	/**
	 * Instantiates a new swarm tree content adapter.
	 * @param treeViewer the tree viewer
	 * @param swarmSession the swarm session
	 */
	SwarmTreeContentAdapter(TreeViewer treeViewer, SwarmSession swarmSession) {
		// this.treeViewer = treeViewer;
		// this.swarmSession = swarmSession;

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
	}
}
