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
package eu.kalafatic.gemini.bt.tracker.view.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import eu.kalafatic.gemini.core.lib.EFolder;
import eu.kalafatic.gemini.core.lib.EView;
import eu.kalafatic.gemini.core.perspectives.CorePerspective;

/**
 * The Class class TrackerBasePerspective.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerBasePerspective implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui .IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout topLeft = layout.createFolder(EFolder.TOP_LEFT.ID, IPageLayout.LEFT, 0.50f, editorArea);
		topLeft.addView(EView.SWARMS.ID);

		IFolderLayout topRight = layout.createFolder(EFolder.TOP_RIGHT.ID, IPageLayout.RIGHT, 0.30f, editorArea);
		topRight.addView(EView.TRACKER.ID);

		IFolderLayout bottomRight = layout.createFolder(EFolder.BOTTOM_RIGHT.ID, IPageLayout.BOTTOM, 0.70f, EFolder.TOP_RIGHT.ID);
		bottomRight.addView(EView.SESSION_PROPERTY.ID);
		bottomRight.addView(CorePerspective.EView.CONSOLE.ID);
	}
}
