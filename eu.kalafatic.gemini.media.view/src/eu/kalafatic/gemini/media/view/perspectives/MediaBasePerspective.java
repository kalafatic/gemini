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
package eu.kalafatic.gemini.media.view.perspectives;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.core.editors.MultiPageEditor;
import eu.kalafatic.gemini.core.lib.EFolder;
import eu.kalafatic.gemini.core.lib.EView;
import eu.kalafatic.gemini.core.utils.AppUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class MediaBasePerspective.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class MediaBasePerspective implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui .IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout topLeft = layout.createFolder(EFolder.TOP_LEFT.ID, IPageLayout.LEFT, 0.25f, editorArea);
		topLeft.addView(EView.FILE_NAVIGATOR.ID);
		topLeft.addView(EView.SWARMS_TREE.ID);

		IFolderLayout bottomLeft = layout.createFolder(EFolder.BOTTOM_LEFT.ID, IPageLayout.BOTTOM, 0.65f, EFolder.TOP_LEFT.ID);

		bottomLeft.addView(EView.CONSOLE.ID);

		IFolderLayout topRight = layout.createFolder(EFolder.TOP_RIGHT.ID, IPageLayout.TOP, 1.0f, editorArea);
		topRight.addView(EView.VIDEO_PLAYER.ID);
	}

	// ---------------------------------------------------------------

	/**
	 * Open editor.
	 */
	public void openEditor() {
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		if (AppUtils.isEditorOpen(page, MultiPageEditor.ID)) {
			int result = DialogUtils.INSTANCE.question("Open new editor ?");

			if (result == SWT.YES) {
				AppUtils.openEditor(page, MultiPageEditor.ID, "");
			} else {
				page.showEditor(AppUtils.iEditorReference);
			}
		} else {
			AppUtils.openEditor(page, MultiPageEditor.ID, "");
		}
		page.setEditorAreaVisible(true);
	}
}
