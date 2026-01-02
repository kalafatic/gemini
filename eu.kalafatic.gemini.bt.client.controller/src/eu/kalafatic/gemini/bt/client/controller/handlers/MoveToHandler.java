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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.core.utils.AppUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class MoveToHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class MoveToHandler extends AbstractHandler {

	/** The lock. */
	public final Lock lock = new ReentrantLock(true);

	/** The viewer. */
	private Viewer viewer;

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.client.controller.MoveToParameter1";

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

			this.viewer = (Viewer) view.getSite().getSelectionProvider();

			List<Object> selected = getSelected(viewer);
			processSelection(selection, selected);
			refresh();

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
	 * @throws Exception the exception
	 */
	private void processSelection(ISelection selection, List<Object> selected) throws Exception {

		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			String folder = getDestination();

			if (folder != null) {
				File destFolder = new File(folder);
				destFolder.mkdirs();

				if (structuredSelection.getFirstElement() instanceof Entry) {
					moveTorrents(destFolder, selected);
				} else if (structuredSelection.getFirstElement() instanceof DataFile) {
					moveDataFiles(destFolder, selected);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Move torrents.
	 * @param destFolder the dest folder
	 * @param selected the selected
	 * @throws Exception the exception
	 */
	private void moveTorrents(File destFolder, List<Object> selected) throws Exception {
		List<Job> jobs = new ArrayList<Job>();

		for (Object object : selected) {
			ExtTorrent extTorrent = (ExtTorrent) object;
			if (extTorrent.isFinished()) {
				moveTorrent(jobs, destFolder, extTorrent);
			}
		}
		AppUtils.getInstance().scheduleJobs(jobs);
	}

	// ---------------------------------------------------------------

	/**
	 * Move torrent.
	 * @param jobs the jobs
	 * @param destFolder the dest folder
	 * @param extTorrent the ext torrent
	 * @throws Exception the exception
	 */
	private void moveTorrent(List<Job> jobs, File destFolder, ExtTorrent extTorrent) throws Exception {

		destFolder = new File(destFolder.getPath() + File.separator + extTorrent.getName());
		destFolder.mkdir();

		for (DataFile datafile : extTorrent.getInfo().getFiles()) {
			addMoveJob(destFolder, jobs, datafile);
		}
		jobs.add(removeTorrentJob(extTorrent.getName(), extTorrent.isFinished()));

		String path = extTorrent.getPath();
		if (path == null) {
			DataFile dataFile = extTorrent.getInfo().getFiles().get(0);
			if (dataFile != null) {
				path = new File(dataFile.getPath()).getParent();
			}
		}
		jobs.add(FileUtils.getInstance().removeFileJob(new File(path)));

	}

	// ---------------------------------------------------------------

	/**
	 * Move data files.
	 * @param destFolder the dest folder
	 * @param selected the selected
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void moveDataFiles(File destFolder, List<Object> selected) throws IOException {
		List<Job> jobs = new ArrayList<Job>();

		for (Object object : selected) {
			addMoveJob(destFolder, jobs, (DataFile) object);
		}
		AppUtils.getInstance().scheduleJobs(jobs);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the move job.
	 * @param destFolder the dest folder
	 * @param jobs the jobs
	 * @param datafile the datafile
	 */
	private void addMoveJob(File destFolder, List<Job> jobs, DataFile datafile) {
		jobs.add(FileUtils.getInstance().moveFileJob(datafile.getPath(), destFolder));
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the torrent job.
	 * @param key the key
	 * @param finished the finished
	 * @return the job
	 */
	public Job removeTorrentJob(final String key, final boolean finished) {
		String jobName = "Removing torrent from model : " + key;

		return new Job(jobName) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					EMap<String, Session> swarmMap = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap();
					swarmMap.removeKey(key);

					Torrents torrents = ClientModelManager.getInstance().getTorrents();

					if (finished) {
						torrents.getFinishedTorrentsMap().removeKey(key);
					} else {
						torrents.getTorrentMap().removeKey(key);
					}
					refresh();
					return Status.OK_STATUS;
				} catch (Exception e) {
					e.printStackTrace();
					return Status.CANCEL_STATUS;
				}
			}
		};
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

	// ---------------------------------------------------------------

	/**
	 * Gets the destination.
	 * @return the destination
	 */
	private String getDestination() {
		DirectoryDialog directoryDialog = new DirectoryDialog(new Shell(Display.getDefault()));
		directoryDialog.setText("Select / Create File");
		if (lastOpenPath != null) {
			directoryDialog.setFilterPath(lastOpenPath);
		}
		return lastOpenPath = directoryDialog.open();
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	private void refresh() {
		if (lock.tryLock()) {
			try {
				Display.getDefault().asyncExec(refresh);
			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			} finally {
				lock.unlock();
			}
		}
	}

	// ---------------------------------------------------------------

	/** The refresh. */
	private final Runnable refresh = new Runnable() {
		@Override
		public void run() {
			lock.lock();
			try {
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {

					viewer.refresh();
				}
			} finally {
				lock.unlock();
			}
		}
	};
}