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
package eu.kalafatic.gemini.bt.client.net.view.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;

/**
 * The Class class ZestNodeContentProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ZestNodeContentProvider extends ArrayContentProvider implements IGraphEntityContentProvider {

	/** The viewer. */
	private final GraphViewer viewer;

	/**
	 * Instantiates a new zest node content provider.
	 * @param viewer the viewer
	 */
	public ZestNodeContentProvider(GraphViewer viewer) {
		this.viewer = viewer;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider#getElements(java.lang. Object)
	 */
	@Override
	public Object[] getElements(Object element) {
		if (element instanceof HashMap<?, ?>) {
			Map<?, ?> map = (Map<?, ?>) element;
			return getAllChildren(map.values(), new ArrayList<Object>());
		} else if (element instanceof EMap<?, ?>) {
			EMap<?, ?> map = (EMap<?, ?>) element;
			return getAllChildren(map.values(), new ArrayList<Object>());
		} else if (element instanceof Session) {
			return addSession(element, new ArrayList<Object>()).toArray();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the all children.
	 * @param collection the collection
	 * @param allChildren the all children
	 * @return the all children
	 */
	private Object[] getAllChildren(Collection<?> collection, List<Object> allChildren) {
		for (Object object : collection) {
			if (object instanceof HashMap<?, ?>) {
				HashMap<?, ?> map = (HashMap<?, ?>) object;
				allChildren.add(map);
				getAllChildren(map.values(), allChildren);

			} else if (object instanceof EcoreEMap<?, ?>) {
				EcoreEMap<?, ?> eMap = (EcoreEMap<?, ?>) object;
				EStructuralFeature eStructuralFeature = eMap.getEStructuralFeature();
				allChildren.add(eMap);
				getAllChildren(eMap.values(), allChildren);
			}
			addSession(object, allChildren);
		}
		return allChildren.toArray();
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the session.
	 * @param object the object
	 * @param allChildren the all children
	 * @return the list
	 */
	private List<Object> addSession(Object object, List<Object> allChildren) {
		if (object instanceof Session) {
			Session session = (Session) object;

			if (session instanceof SwarmSession) {
				SwarmSession swarmSession = (SwarmSession) session;
				allChildren.add(swarmSession);
				allChildren.addAll(swarmSession.getDownloads().values());
				allChildren.addAll(swarmSession.getUploads().values());

				if ((viewer.getInput() instanceof SwarmSession)) {
					allChildren.addAll(swarmSession.getTrackers().values());
				}
			} else if (session instanceof TrackerSession) {
				TrackerSession trackerSession = (TrackerSession) session;
				SwarmSession swarmSession = (SwarmSession) trackerSession.eContainer().eContainer();

				allChildren.add(trackerSession);

				if ((viewer.getInput() instanceof SwarmSession)) {
					allChildren.addAll(swarmSession.getDownloads().values());
				}

				if ((trackerSession.getClients() != null) && (!trackerSession.getClients().isEmpty())) {
					allChildren.addAll(trackerSession.getClients());
				}

			} else if (session instanceof DwnldSession) {
				DwnldSession dwnldSession = (DwnldSession) session;
				allChildren.add(dwnldSession);

			} else if (session instanceof UpldSession) {
				UpldSession upldSession = (UpldSession) session;
				allChildren.add(upldSession);
			}
		}
		return allChildren;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IGraphEntityContentProvider#getConnectedTo (java.lang.Object)
	 */
	@Override
	public Object[] getConnectedTo(Object element) {
		List<Object> allChildren = new ArrayList<Object>();

		if (element instanceof SwarmSession) {
			SwarmSession swarmSession = (SwarmSession) element;

			// allChildren.addAll(swarmSession.getTrackers().values());
			allChildren.addAll(swarmSession.getDownloads().values());
			allChildren.addAll(swarmSession.getUploads().values());

			if ((viewer.getInput() instanceof SwarmSession)) {
				allChildren.addAll(swarmSession.getTrackers().values());
			}

			// } else if (element instanceof DwnldSession) {
			// DwnldSession session = (DwnldSession) element;
			// SwarmSession swarmSession = (SwarmSession) session.eContainer()
			// .eContainer();
			// allChildren.addAll(swarmSession.getTrackers().values());

			// } else if (element instanceof UpldSession) {
			// UpldSession session = (UpldSession) element;
			// SwarmSession swarmSession = (SwarmSession) session.eContainer()
			// .eContainer();
			// allChildren.add(swarmSession);

		} else if (element instanceof TrackerSession) {
			TrackerSession trackerSession = (TrackerSession) element;
			SwarmSession swarmSession = (SwarmSession) trackerSession.eContainer().eContainer();

			if ((viewer.getInput() instanceof SwarmSession) && (trackerSession.getClients() != null) && (!trackerSession.getClients().isEmpty())) {
				allChildren.addAll(trackerSession.getClients());
				// allChildren.addAll(swarmSession.getDownloads().values());
			} else if ((viewer.getInput() instanceof TrackerSession) && (trackerSession.getClients() != null) && (!trackerSession.getClients().isEmpty())) {

				allChildren.addAll(trackerSession.getClients());

			} else if (viewer.getInput() instanceof EcoreEMap<?, ?>) {
				EcoreEMap<?, ?> eMap = (EcoreEMap<?, ?>) viewer.getInput();
				allChildren.addAll(eMap.values());
			}
		} else if (element instanceof HashMap<?, ?>) {
			HashMap<?, ?> map = (HashMap<?, ?>) element;
			allChildren.addAll(map.values());

		} else if (element instanceof EcoreEMap<?, ?>) {
			EcoreEMap<?, ?> eMap = (EcoreEMap<?, ?>) element;
			allChildren.addAll(eMap.values());
		}
		return allChildren.toArray();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider#inputChanged(org.eclipse .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider#dispose()
	 */
	@Override
	public void dispose() {}

}
