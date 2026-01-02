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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.core.interfaces.IViewer;

/**
 * The Class class SetParentAsInputHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

/**
 * @author kalafaticp
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SetParentAsInputHandler extends AbstractHandler {

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
				GraphViewer viewer = (GraphViewer) ((IViewer) viewPart).getViewer();

				ISelection selection = viewer.getSelection();
				Object selected = ((IStructuredSelection) selection).getFirstElement();

				if (selected instanceof Session) {
					Session session = (Session) selected;
					if (session instanceof SwarmSession) {
						ClientNetwork clientNetwork = (ClientNetwork) session.eContainer().eContainer();

						Map inputMap = new HashMap<String, EMap<?, ?>>();
						inputMap.put("Swarms", clientNetwork.getSwarmMap());
						viewer.setInput(inputMap);

					} else if (session instanceof TrackerSession) {
						SwarmSession swarmSession = (SwarmSession) session.eContainer().eContainer();
						viewer.setInput(swarmSession);
					}

				} else if (selected instanceof EcoreEMap<?, ?>) {
					EcoreEMap<?, ?> map = (EcoreEMap<?, ?>) selected;
					viewer.setInput(map);
				}
				viewer.refresh();
			}
		}
		return null;
	}
}
