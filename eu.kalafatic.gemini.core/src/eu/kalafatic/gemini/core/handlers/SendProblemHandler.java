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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SendProblemHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SendProblemHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String bugsAddress = PREFERENCES.get(ECorePreferences.BUG_SITE.getName(), (String) ECorePreferences.BUG_SITE.getDef());

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		IWorkbenchBrowserSupport support = window.getWorkbench().getBrowserSupport();
		try {
			support.getExternalBrowser().openURL(new URL(bugsAddress));
		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		}
		return null;
	}
}
