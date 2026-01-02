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

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.SERVER_DISABLED_IMG;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.main.TrackerManager;
import eu.kalafatic.gemini.core.lib.AppData;

/**
 * The Class class StopUploadServerHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class StopUploadServerHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		TrackerManager.getInstance().stopUploadTracker();

		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				((IViewer) viewPart).setTitleImg(SERVER_DISABLED_IMG);
			}
		}

		TrayItem trayItem = AppData.getInstance().getTrayItem();
		trayItem.getToolTip().dispose();

		ToolTip tip = new ToolTip(trayItem.getDisplay().getActiveShell(), SWT.BALLOON | SWT.ICON_ERROR);
		tip.setAutoHide(true);
		tip.setText("Info");
		trayItem.setToolTip(tip);
		tip.setMessage("Upload server stopped.");

		tip.setVisible(true);
		return null;
	}
}
