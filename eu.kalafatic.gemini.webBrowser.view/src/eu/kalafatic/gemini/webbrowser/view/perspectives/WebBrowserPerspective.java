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
package eu.kalafatic.gemini.webbrowser.view.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import eu.kalafatic.gemini.core.lib.EFolder;
import eu.kalafatic.gemini.core.lib.EView;

/**
 * The Class class WebBrowserPerspective.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebBrowserPerspective implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui .IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout leftRight = layout.createFolder(EFolder.LEFT_RIGHT.ID, IPageLayout.LEFT, 0.20f, editorArea);
		leftRight.addView(EView.BROWSER_NAVIGATOR.ID);
		leftRight.addView(EView.CONSOLE.ID);

		IFolderLayout topLeft = layout.createFolder(EFolder.TOP_LEFT.ID, IPageLayout.TOP, 0.80f, editorArea);
		topLeft.addView(EView.BROWSER.ID);
	}
}
