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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.internal.keys.WorkbenchKeyboard;
import org.eclipse.ui.keys.IBindingService;

import eu.kalafatic.gemini.core.hack.KeyAssistDialog;
import eu.kalafatic.gemini.core.hack.KeyBindingState;

/**
 * The Class class ExportHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public class ExportHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbench workbench = PlatformUI.getWorkbench();

		BindingService bindingService = (BindingService) workbench.getAdapter(IBindingService.class);
		bindingService.readRegistryAndPreferences((ICommandService) PlatformUI.getWorkbench().getAdapter(ICommandService.class));

		WorkbenchKeyboard workbenchKeyboard = bindingService.getKeyboard();

		KeyBindingState keyBindingState = new KeyBindingState(workbench);

		new KeyAssistDialog(workbench, workbenchKeyboard, keyBindingState).open();
		return null;
	}
}
