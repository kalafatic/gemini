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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;

/**
 * The Class class ClearHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ClearHandler extends AbstractHandler {

	/** The viewer. */
	private TableViewer viewer;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				TableViewer viewer = (TableViewer) ((IViewer) viewPart).getViewer();
				this.viewer = viewer;
				clearSelected(viewer.getSelection());
				refresh();
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Clear selected.
	 * @param selection the selection
	 */
	private void clearSelected(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;

			Iterator<?> iterator = iStructuredSelection.iterator();

			while (iterator.hasNext()) {

				Object selectedObject = iterator.next();

				if (selectedObject instanceof Communication) {
					Communication communication = (Communication) selectedObject;

					EObject eContainer = communication.eContainer();

					if (eContainer instanceof Session) {
						Session session = (Session) eContainer;

						session.getCommunication().remove(communication);
					}
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
