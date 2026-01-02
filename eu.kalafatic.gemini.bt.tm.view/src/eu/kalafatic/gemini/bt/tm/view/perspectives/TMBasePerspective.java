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
package eu.kalafatic.gemini.bt.tm.view.perspectives;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.core.lib.EFolder;
import eu.kalafatic.gemini.core.perspectives.CorePerspective;
import eu.kalafatic.gemini.core.utils.AppUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class TMBasePerspective.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("static-access")
public class TMBasePerspective implements IPerspectiveFactory {

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

		IFolderLayout topLeft = layout.createFolder(EFolder.TOP_LEFT.ID, IPageLayout.LEFT, 0.20f, editorArea);

		topLeft.addView(CorePerspective.EView.CONSOLE.ID);
		topLeft.addView(IPageLayout.ID_PROP_SHEET);

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				openEditor();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Open editor.
	 */

	public void openEditor() {
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		if (AppUtils.getInstance().isEditorOpen(page, TorrentEditor.ID)) {
			int result = DialogUtils.INSTANCE.question("Open new editor ?");

			if (result == SWT.YES) {
				AppUtils.getInstance().openEditor(page, TorrentEditor.ID, null);
			} else {
				page.showEditor(AppUtils.getInstance().iEditorReference);
			}
		} else {
			AppUtils.getInstance().openEditor(page, TorrentEditor.ID, null);

		}
		page.setEditorAreaVisible(true);
	}
}
