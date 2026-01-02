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
package eu.kalafatic.gemini.core.perspectives;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.statushandlers.StatusManager;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.lib.EFolder;

/**
 * The Class class CorePerspective.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CorePerspective implements IPerspectiveFactory {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.core.perspectives.CorePerspective";

	/**
	 * The Enum enum EView.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public static enum EView {

		/** The console. */
		CONSOLE("eu.kalafatic.gemini.core.views.ConsoleView"),

		/** The workspace. */
		WORKSPACE("eu.kalafatic.gemini.core.views.WorkspaceView"),

		/** The file navigator. */
		FILE_NAVIGATOR("eu.kalafatic.gemini.core.views.FileNavigatorView");

		/** The id. */
		public String ID;

		/**
		 * Instantiates a new e view.
		 * @param id the id
		 */
		private EView(String id) {
			this.ID = id;
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui .IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);

		IFolderLayout topLeft = layout.createFolder(EFolder.TOP_LEFT.ID, IPageLayout.LEFT, 0.25f, editorArea);

		topLeft.addView(EView.FILE_NAVIGATOR.ID);
		topLeft.addView(EView.WORKSPACE.ID);

		IFolderLayout bottomLeft = layout.createFolder(EFolder.BOTTOM_LEFT.ID, IPageLayout.BOTTOM, 0.65f, EFolder.TOP_LEFT.ID);

		bottomLeft.addView(EView.CONSOLE.ID);

		Status status = new Status(IStatus.INFO, "Test", "Test string!");
		Status status2 = new Status(IStatus.ERROR, "Test", "Test string!");
		Activator.getDefault().getLog().log(status);
		StatusManager.getManager().handle(status2, StatusManager.SHOW);
		StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);

	}
}
