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

/**
 * The Class class SendImprovementHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SendImprovementHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		String mailAddress = PREFERENCES.get(ECorePreferences.MAIL_SITE.getName(), (String) ECorePreferences.MAIL_SITE.getDef());

		IWorkbenchBrowserSupport support = window.getWorkbench().getBrowserSupport();
		try {
			support.getExternalBrowser().openURL(new URL(mailAddress));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
