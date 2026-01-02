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
package eu.kalafatic.gemini.bt.tracker.controller.handlers;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;

/**
 * The Class class RemoveHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class RemoveHandler extends AbstractHandler {

	/** The viewer. */
	private TreeViewer viewer;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				TreeViewer viewer = (TreeViewer) ((IViewer) viewPart).getViewer();
				this.viewer = viewer;
				removeSelected(viewer.getSelection());
				refresh();
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the selected.
	 * @param selection the selection
	 */
	private void removeSelected(ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
				Object selectedObject = iterator.next();

				if (selectedObject instanceof Entry<?, ?>) {

					Entry<?, ?> entry = (Entry<?, ?>) selectedObject;

					if (entry.getValue() instanceof TorrentSession) {
						TorrentSession session = (TorrentSession) entry.getValue();

						synchronized (session) {
							EMap<String, Session> swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();
							swarmMap.removeKey(session.getInfoHash());
						}

					} else if (entry.getValue() instanceof ClientSession) {
						ClientSession session = (ClientSession) entry.getValue();

						synchronized (session) {
							EObject eContainer = session.eContainer().eContainer();
							if (eContainer instanceof TorrentSession) {
								TorrentSession torrentSession = (TorrentSession) eContainer;

								torrentSession.getClientMap().removeKey(session.getAddress());
							}
						}
					}
					TrackerModelManager.getInstance().doSave();
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	public void refresh() {
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
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() && viewer.getControl().isVisible()) {

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
