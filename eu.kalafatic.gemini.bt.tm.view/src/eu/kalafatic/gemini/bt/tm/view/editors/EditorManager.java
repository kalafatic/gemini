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

package eu.kalafatic.gemini.bt.tm.view.editors;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.core.editors.MultiPageEditor;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class EditorManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class EditorManager {

	/** The active editor. */
	private MultiPageEditor activeEditor;

	/** The path. */
	private String path;

	/** The root. */
	private File root;

	/** The instance. */
	private static EditorManager INSTANCE = null;

	/**
	 * Instantiates a new editor manager.
	 */
	public EditorManager() {
		try {
			root = File.createTempFile("Torrent", "");
			root.mkdirs();
			path = root.getAbsolutePath();
		} catch (IOException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}

	/**
	 * Gets the single instance of EditorManager.
	 * @return single instance of EditorManager
	 */
	public static EditorManager getInstance() {
		if (INSTANCE == null) {
			synchronized (EditorManager.class) {
				INSTANCE = new EditorManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the temporary editor input.
	 * @return the temporary editor input
	 */
	// public IEditorInput getTemporaryEditorInput() {
	// IPath iPath = new Path(path);
	// IFile iFile = project.getFile(iPath.lastSegment());
	// IEditorInput editorInput = new FileEditorInput(iFile);
	// return editorInput;
	//
	// IWorkspace ws = ResourcesPlugin.getWorkspace();
	// IProject project = ws.getRoot().getProject("External Files");
	// if (!project.exists())
	// project.create(null);
	// if (!project.isOpen())
	// project.open(null);
	// Shell shell = window.getShell();
	// String name = new FileDialog(shell, SWT.OPEN).open();
	// if (name == null)
	// return;
	// IPath location = new Path(name);
	// IFile file = project.getFile(location.lastSegment());
	// file.createLink(location, IResource.NONE, null);
	// IWorkbenchPage page = window.getActivePage();
	// if (page != null)
	// page.openEditor(file);
	// }

	// ---------------------------------------------------------------

	/**
	 * Gets the temporary editor input.
	 * @return the temporary editor input
	 */
	public FileStoreEditorInput getTemporaryEditorInput() {
		IPath iPath = new Path(path);
		IFileStore ifs = EFS.getLocalFileSystem().getStore(iPath);
		FileStoreEditorInput editorInput = new FileStoreEditorInput(ifs);
		return editorInput;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the input file.
	 */
	public void setInputFile() {
		try {
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			if (activePage != null) {
				IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

				if (editor instanceof MultiPageEditor) {
					activeEditor = (MultiPageEditor) editor;
				}
			}

			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {

					// FileStoreEditorInput fileStoreEditorInput =
					// getTemporaryEditorInput();
					IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(path));
					// IEditorInput editorInput = new
					// FileEditorInput(fileStore);

					if (activeEditor == null) {// IFileEditorInput
						try {
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

							IDE.openEditorOnFileStore(page, fileStore);

							// activeEditor = (MultiPageEditor) PlatformUI
							// .getWorkbench()
							// .getActiveWorkbenchWindow()
							// .getActivePage()
							// .openEditor(fileStore,
							// "gemini.core.editors.MultiPageTorrentEditor");
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
					// activeEditor.createTorrentPage(fileStore);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the path.
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the root.
	 * @return the root
	 */
	public File getRoot() {
		return root;
	}

}
