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
package eu.kalafatic.gemini.bt.client.controller.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.core.utils.AppUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;

/**
 * The Class class CopyToHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */

/**
 * @author petr
 */
public class CopyToHandler extends AbstractHandler {

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.client.controller.CopyToParameter1";

	/** The last open path. */
	private static String lastOpenPath = null;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection;
		try {
			String viewName = event.getParameter(PARAMETER_1);

			IViewPart view = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(viewName);
			selection = view.getSite().getSelectionProvider().getSelection();

			Viewer viewer = (Viewer) view.getSite().getSelectionProvider();
			List<Object> selected = getSelected(viewer);

			processSelection(selection, selected);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Process selection.
	 * @param selection the selection
	 * @param selected the selected
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processSelection(ISelection selection, List<Object> selected) throws IOException {

		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			String folder = getDestination(true);

			if (folder != null) {
				File destFolder = new File(folder);
				destFolder.mkdirs();

				if (structuredSelection.getFirstElement() instanceof Entry) {
					copyTorrents(destFolder, selected);
				} else if (structuredSelection.getFirstElement() instanceof DataFile) {
					copyDataFiles(destFolder, selected);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Copy torrents.
	 * @param destFolder the dest folder
	 * @param selected the selected
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void copyTorrents(File destFolder, List<Object> selected) throws IOException {
		List<Job> jobs = new ArrayList<Job>();

		for (Object object : selected) {
			if (((ExtTorrent) object).isFinished()) {
				copyTorrent(jobs, destFolder, (ExtTorrent) object);
			}
		}
		AppUtils.getInstance().scheduleJobs(jobs);
	}

	// ---------------------------------------------------------------

	/**
	 * Copy torrent.
	 * @param jobs the jobs
	 * @param destFolder the dest folder
	 * @param extTorrent the ext torrent
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void copyTorrent(List<Job> jobs, File destFolder, ExtTorrent extTorrent) throws IOException {

		destFolder = new File(destFolder.getPath() + File.separator + extTorrent.getName());
		destFolder.mkdir();

		for (DataFile datafile : extTorrent.getInfo().getFiles()) {
			addCopyJob(destFolder, jobs, datafile);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Copy data files.
	 * @param destFolder the dest folder
	 * @param selected the selected
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void copyDataFiles(File destFolder, List<Object> selected) throws IOException {
		List<Job> jobs = new ArrayList<Job>();

		for (Object object : selected) {
			addCopyJob(destFolder, jobs, (DataFile) object);
		}
		AppUtils.getInstance().scheduleJobs(jobs);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the copy job.
	 * @param destFolder the dest folder
	 * @param jobs the jobs
	 * @param datafile the datafile
	 */
	private void addCopyJob(File destFolder, List<Job> jobs, DataFile datafile) {
		String datafileName = new File(datafile.getPath()).getName();
		String destPath = destFolder.getAbsolutePath() + File.separator + datafileName;

		jobs.add(FileUtils.getInstance().copyFileJob(datafile.getPath(), destPath));
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the destination.
	 * @param folder the folder
	 * @return the destination
	 */
	private String getDestination(boolean folder) {
		if (folder) {
			DirectoryDialog directoryDialog = new DirectoryDialog(new Shell(Display.getDefault()));
			directoryDialog.setText("Select / Create Folder");

			if (lastOpenPath != null) {
				directoryDialog.setFilterPath(lastOpenPath);
			}
			return lastOpenPath = directoryDialog.open();
		} else {
			FileDialog fileDialog = new FileDialog(new Shell(Display.getDefault()));
			fileDialog.setText("Select / Create File");

			if (lastOpenPath != null) {
				fileDialog.setFilterPath(lastOpenPath);
			}
			return lastOpenPath = fileDialog.open();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected.
	 * @param viewer the viewer
	 * @return the selected
	 */
	public List<Object> getSelected(Viewer viewer) {
		List<Object> selected = new ArrayList<Object>();

		ISelection selection = viewer.getSelection();

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();

			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
				selected.add(entry.getValue());
			}
		}
		return selected;
	}
}
