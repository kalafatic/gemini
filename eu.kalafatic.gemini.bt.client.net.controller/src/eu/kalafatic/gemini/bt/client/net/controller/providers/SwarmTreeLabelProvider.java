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
package eu.kalafatic.gemini.bt.client.net.controller.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;

/**
 * The Class class SwarmTreeLabelProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmTreeLabelProvider extends LabelProvider {

	/**
	 * Instantiates a new swarm tree label provider.
	 * @param viewer the viewer
	 */
	public SwarmTreeLabelProvider(TreeViewer viewer) {
		// this.viewer = viewer;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {

		if (element instanceof EcoreEMap<?, ?>) {
			EcoreEMap<?, ?> map = (EcoreEMap<?, ?>) element;
			EStructuralFeature eStructuralFeature = map.getEStructuralFeature();

			String name = eStructuralFeature.getName();
			if (name.equals("swarmMap")) {
				return "Swarms".concat(" (" + map.size() + ")");
			} else if (name.equals("trackers")) {
				return "Trackers".concat(" (" + map.size() + ")");
			} else if (name.equals("downloads")) {
				return "Downloads".concat(" (" + map.size() + ")");
			} else if (name.equals("uploads")) {
				return "Uploads".concat(" (" + map.size() + ")");
			}
			return name;

		} else if (element instanceof HashMap<?, ?>) {
			// HashMap<?, ?> map = (HashMap<?, ?>) element;
			return "Sessions";

		} else if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>) element;

			if (entry.getValue() instanceof Session) {
				Session session = (Session) entry.getValue();
				return session.getAnnounce() + " (" + session.getRating() + ")";
			}
			return (String) entry.getKey();

		} else if (element instanceof ClientSession) {
			ClientSession session = (ClientSession) element;
			return session.getAnnounce() + " (" + session.getRating() + ")";

		} else if (element instanceof TrackerSession) {
			TrackerSession session = (TrackerSession) element;
			return session.getAnnounce();

		} else if (element instanceof SwarmSession) {
			SwarmSession session = (SwarmSession) element;
			return session.getAnnounce();

		} else if (element instanceof Integer) {
			Integer object = (Integer) element;
			return Integer.toString(object);

		} else if (element instanceof String) {
			return (String) element;

		} else if (element instanceof List<?>) {
			return "";
		}
		return "";
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {

		if (element instanceof HashMap<?, ?>) {
			return FOLDER_IMG;

		} else if (element instanceof EcoreEMap<?, ?>) {
			return FOLDER_IMG;

		} else if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>) element;

			if (entry.getValue() instanceof SwarmSession) {
				// updateSwarms(viewer.getTree().getItems());
				return FOLDER_IMG;

			} else if (entry.getValue() instanceof TrackerSession) {
				return FILE_IMG;

			} else if (entry.getValue() instanceof DwnldSession) {
				return FILE_IMG;

			} else if (entry.getValue() instanceof UpldSession) {
				return FILE_IMG;
			}
		} else if (element instanceof Queue<?>) {
			return FOLDER_IMG;
		}
		return FILE_IMG;
	}

	// ---------------------------------------------------------------

	/**
	 * Update swarms.
	 * @param items the items
	 */
	// private void updateSwarms(TreeItem[] items) {
	//
	// for (int i = 0; i < items.length; i++) {
	// if (items[i].getData() instanceof Entry) {
	// Entry<?, ?> entry = (Entry<?, ?>) items[i].getData();
	//
	// if (entry.getValue() instanceof SwarmSession) {
	// SwarmSession session = (SwarmSession) entry.getValue();
	// if (session.isUploadOnly()) {
	// items[i].setBackground(SAND_COLOR);
	// } else {
	// items[i].setBackground(GRASS_COLOR);
	// }
	// }
	// }
	// if (items[i].getItems().length > 0) {
	// updateSwarms(items[i].getItems());
	// }
	// }
	// refresh();
	// }
	//
	// // ---------------------------------------------------------------
	//
	// /**
	// * Refresh.
	// */
	// public void refresh() {
	// if (lock.tryLock()) {
	// try {
	// Display.getDefault().asyncExec(refresh);
	// } finally {
	// lock.unlock();
	// }
	// }
	// }
	//
	// // ---------------------------------------------------------------
	//
	// /** The refresh. */
	// private final Runnable refresh = new Runnable() {
	// @Override
	// public void run() {
	// lock.lock();
	// try {
	// if (viewer != null && viewer.getControl() != null
	// && !viewer.getControl().isDisposed() && viewer.getControl().isVisible())
	// {
	//
	// viewer.refresh();
	// }
	// } catch (Exception e) {
	// // e.printStackTrace();
	// } finally {
	// lock.unlock();
	// }
	// }
	// };

}
