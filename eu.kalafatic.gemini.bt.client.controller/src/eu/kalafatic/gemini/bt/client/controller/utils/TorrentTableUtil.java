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
package eu.kalafatic.gemini.bt.client.controller.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Class class TorrentTableUtil.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentTableUtil {

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The cashe. */
	private Map<TableViewer, Worker> cashe = new HashMap<TableViewer, Worker>();

	/** The INSTANCE. */
	private static volatile TorrentTableUtil INSTANCE;

	/**
	 * Gets the single instance of TorrentTableUtil.
	 * @return single instance of TorrentTableUtil
	 */
	public static TorrentTableUtil getInstance() {
		if (INSTANCE == null) {
			synchronized (TorrentTableUtil.class) {
				INSTANCE = new TorrentTableUtil();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the selected.
	 * @param tableViewer the table viewer
	 * @return the selected
	 */
	public List<ExtTorrent> getSelected(TableViewer tableViewer) {
		List<ExtTorrent> selected = new ArrayList<ExtTorrent>();

		ISelection selection = tableViewer.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();

			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();

				if (entry.getValue() instanceof ExtTorrent) {
					ExtTorrent extTorrent = (ExtTorrent) entry.getValue();
					selected.add(extTorrent);
				}
			}
		}
		return selected;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the checked.
	 * @param tableViewer the table viewer
	 * @return the checked
	 */
	@SuppressWarnings("unchecked")
	public List<ExtTorrent> getChecked(TableViewer tableViewer) {

		List<ExtTorrent> checked = new ArrayList<ExtTorrent>();

		TableItem[] items = tableViewer.getTable().getItems();

		for (int i = 0; i < items.length; i++) {
			Object data = items[i].getData();

			if (data instanceof Entry<?, ?>) {
				Entry<String, ExtTorrent> entry = (Entry<String, ExtTorrent>) data;

				ExtTorrent extTorrent = entry.getValue();

				if (items[i].getChecked()) {
					checked.add(extTorrent);
				}
			}
		}
		return checked;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the checked torrents.
	 * @param viewer the new checked torrents
	 */

	public void setCheckedTorrents(final TableViewer viewer) {
		if (lock.tryLock()) {
			try {
				Worker worker = null;
				if (cashe.containsKey(viewer)) {
					worker = cashe.get(viewer);
				} else {
					worker = new Worker(viewer);
					cashe.put(viewer, worker);
				}
				Display.getDefault().asyncExec(worker);
			} finally {
				lock.unlock();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * The Class class Worker.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	class Worker implements Runnable {

		/** The viewer. */
		private final TableViewer viewer;

		/**
		 * Instantiates a new worker.
		 * @param viewer the viewer
		 */
		private Worker(TableViewer viewer) {
			this.viewer = viewer;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			lock.lock();
			try {
				if (viewer == null || viewer.getTable().isDisposed()) {
					return;
				}
				for (TableItem item : viewer.getTable().getItems()) {

					Object data = item.getData();

					if (data instanceof Entry) {
						Entry<?, ?> entry = (Entry<?, ?>) data;
						Object value = entry.getValue();

						if (value instanceof ExtTorrent) {
							ExtTorrent extTorrent = (ExtTorrent) value;

							// item.setForeground(extTorrent.isEnabled() ? BLACK
							// : RED);
							item.setChecked(extTorrent.isEnabled());
						}
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
