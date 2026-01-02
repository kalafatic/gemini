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
package eu.kalafatic.gemini.bt.client.net.controller.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.core.interfaces.IViewer;

/**
 * The Class class SetNodeAsInputHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SetNodeAsInputHandler extends AbstractHandler {

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

			if (((IViewer) viewPart).getViewer() instanceof GraphViewer) {
				GraphViewer viewer = (GraphViewer) ((IViewer) viewPart).getViewer();

				ISelection selection = viewer.getSelection();
				Object selected = ((IStructuredSelection) selection).getFirstElement();

				if (selected instanceof SwarmSession) {
					viewer.setInput(selected);

				} else if (selected instanceof TrackerSession) {
					viewer.setInput(selected);

				} else if (selected instanceof EMap<?, ?>) {
					EMap<?, ?> map = (EMap<?, ?>) selected;
					viewer.setInput(map);
				}
				viewer.refresh();
			}
		}
		return null;
	}
}
