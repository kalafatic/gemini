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
package eu.kalafatic.gemini.bt.client.controller.dnd;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.controller.model.actions.TorrentModelActions;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The listener interface for receiving torrentViewerDrop events. The class that is interested in processing a torrentViewerDrop event implements this
 * interface, and the object created with that class is registered with a component using the component's
 * <code>addTorrentViewerDropListener<code> method. When
 * the torrentViewerDrop event occurs, that object's appropriate
 * method is invoked.
 * @see TorrentViewerDropEvent
 */
public class TorrentViewerDropListener {

	/** The viewer. */
	private final Viewer viewer;

	/** The finished. */
	private final boolean finished;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new torrent viewer drop listener.
	 * @param tableViewer the table viewer
	 * @param finished the finished
	 */
	public TorrentViewerDropListener(TableViewer tableViewer, boolean finished) {
		this.viewer = tableViewer;
		this.finished = finished;
		initDropTarget();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the drop target.
	 */
	private void initDropTarget() {
		Transfer[] types = new Transfer[] { FileTransfer.getInstance() };

		DropTarget dropTarget = new DropTarget(viewer.getControl(), DND.DROP_COPY | DND.DROP_DEFAULT);
		dropTarget.setTransfer(types);

		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
				String[] data = (String[]) FileTransfer.getInstance().nativeToJava(event.dataTypes[0]);

				for (int i = 0; i < data.length; i++) {
					File file = new File(data[i]);
					if (file.exists() && file.getPath().endsWith(".torrent")) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				String[] data = (String[]) FileTransfer.getInstance().nativeToJava(event.dataTypes[0]);

				for (int i = 0; i < data.length; i++) {
					TorrentModelActions.INSTANCE.addTorrentToModel(data[i], finished);
				}
				refresh();
				super.dropAccept(event);
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	private void refresh() {
		if (lock.tryLock()) {
			try {
				Display.getDefault().asyncExec(refresh);
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
				if (viewer != null && !viewer.getControl().isDisposed()) {
					viewer.refresh();
				}
			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			} finally {
				lock.unlock();
			}
		}
	};
}
