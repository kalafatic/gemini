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
package eu.kalafatic.gemini.core.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;

import eu.kalafatic.gemini.core.hack.SelectPerspectiveDialog;
import eu.kalafatic.gemini.core.lib.constants.FTextConstants;

/**
 * The Class class OpenGeminiPerspectivesHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public class OpenGeminiPerspectivesHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IPerspectiveRegistry perspReg = WorkbenchPlugin.getDefault().getPerspectiveRegistry();

		List<IPerspectiveDescriptor> geminiPerspectives = new ArrayList<IPerspectiveDescriptor>();

		IPerspectiveDescriptor[] perspectives = perspReg.getPerspectives();

		for (int i = 0; i < perspectives.length; i++) {
			PerspectiveDescriptor descriptor = ((PerspectiveDescriptor) perspectives[i]);
			if (descriptor.getId().startsWith(FTextConstants.DOMAIN_FILTER)) {
				geminiPerspectives.add(descriptor);
			}
		}
		SelectPerspectiveDialog selectPerspectiveDialog = new SelectPerspectiveDialog(window.getShell(), geminiPerspectives);

		selectPerspectiveDialog.setBlockOnOpen(true);
		selectPerspectiveDialog.open();

		if (selectPerspectiveDialog.getReturnCode() == Window.CANCEL) {
			return null;
		}

		IPerspectiveDescriptor descriptor = selectPerspectiveDialog.getSelection();

		if (descriptor != null) {
			window.getActivePage().setPerspective(descriptor);
		}
		return null;
	}
}
