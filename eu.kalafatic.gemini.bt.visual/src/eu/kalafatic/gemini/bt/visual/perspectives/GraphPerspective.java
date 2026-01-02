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
package eu.kalafatic.gemini.bt.visual.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * The Class GraphPerspective.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class GraphPerspective implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui
	 * .IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {

		String editorArea = layout.getEditorArea();

		layout.setEditorAreaVisible(false);

		IFolderLayout topLeft = layout.createFolder("topLeft",
				IPageLayout.LEFT, 0.25f, editorArea);

		topLeft.addView("eu.kalafatic.gemini.core.views.FileNavigatorView");

		IFolderLayout bottomLeft = layout.createFolder("bottomLeft",
				IPageLayout.BOTTOM, 0.65f, "topLeft");

		bottomLeft.addView("eu.kalafatic.gemini.core.views.ConsoleView");

		IFolderLayout bottomRight = layout.createFolder("bottomRight",
				IPageLayout.BOTTOM, 0.40f, editorArea);

		bottomRight
				.addView("eu.kalafatic.gemini.bt.visual.views.NetGraph");
	}
}
