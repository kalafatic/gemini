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
package eu.kalafatic.gemini.bt.tm.controller.actions;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsFactory;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;

/**
 * The Class class ModelActions.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ModelActions {

	/** The INSTANCE. */
	private volatile static ModelActions INSTANCE;

	/**
	 * Gets the single instance of ModelActions.
	 * @return single instance of ModelActions
	 */
	public static ModelActions getInstance() {
		if (INSTANCE == null) {
			synchronized (ModelActions.class) {
				INSTANCE = new ModelActions();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Adds the file to model.
	 * @param extTorrent the ext torrent
	 * @param viewer the viewer
	 * @param filePaths the file paths
	 */
	public void addFileToModel(ExtTorrent extTorrent, Viewer viewer, String... filePaths) {
		FileTreeObject parentTreeObject = null;
		ISelection selection = viewer.getSelection();

		if (selection == null || selection.isEmpty()) {
			FileTreeObject rootTreeObject = BTStructureModelManager.getInstance().getBtStructure().getTreeObjects().get(extTorrent.getName());
			if (rootTreeObject.getChildMap().containsKey("Root")) {
				parentTreeObject = rootTreeObject.getChildMap().get("Root");
			}
		} else if (selection instanceof IStructuredSelection) {
			IStructuredSelection iStructuredSelection = (IStructuredSelection) viewer.getSelection();

			parentTreeObject = (FileTreeObject) iStructuredSelection.getFirstElement();
		}
		for (int i = 0; i < filePaths.length; i++) {
			File file = new File(filePaths[i]);
			addFileToModel(parentTreeObject, new File(filePaths[i]));
			addDataFile(extTorrent, file);

		}
		refresh(viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the data file.
	 * @param extTorrent the ext torrent
	 * @param file the file
	 */
	public void addDataFile(ExtTorrent extTorrent, File file) {
		DataFile dataFile = TorrentsFactory.eINSTANCE.createDataFile();
		dataFile.setLength(file.length());
		dataFile.setPath(file.getAbsolutePath());
		dataFile.setName(file.getName());
		extTorrent.getInfo().getFiles().add(dataFile);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the file to model.
	 * @param parentTreeObject the parent tree object
	 * @param file the file
	 */
	public void addFileToModel(FileTreeObject parentTreeObject, File file) {
		if (parentTreeObject.isFile()) {
			return;
		}
		FileTreeObject treeChild = BtStructureFactory.eINSTANCE.createFileTreeObject();
		treeChild.setFile(file.isFile());
		treeChild.setName(file.getName());
		treeChild.setPath(file.getAbsolutePath());
		treeChild.setParent(parentTreeObject);

		parentTreeObject.getChildMap().put(file.getAbsolutePath(), treeChild);

		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				addFileToModel(treeChild, child);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the file from model.
	 * @param extTorrent the ext torrent
	 * @param viewer the viewer
	 * @param fileTreeObject the file tree object
	 */
	public void removeFileFromModel(ExtTorrent extTorrent, Viewer viewer, FileTreeObject fileTreeObject) {

		EList<DataFile> files = extTorrent.getInfo().getFiles();
		DataFile toRemove = null;
		for (DataFile dataFile : files) {
			if (dataFile.getName().equals(fileTreeObject.getName())) {
				toRemove = dataFile;
				break;
			}
		}

		if (toRemove != null) {
			files.remove(toRemove);
			FileTreeObject parent = fileTreeObject.getParent();
			parent.getChildMap().removeKey(fileTreeObject.getPath());

			refresh(viewer);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 * @param viewer the viewer
	 */
	public void refresh(final Viewer viewer) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {
						viewer.refresh();
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		});
	}
}
