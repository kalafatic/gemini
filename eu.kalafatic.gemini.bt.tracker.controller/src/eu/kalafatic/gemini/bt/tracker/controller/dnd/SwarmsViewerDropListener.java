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
package eu.kalafatic.gemini.bt.tracker.controller.dnd;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.tracker.controller.model.actions.TrackerModelActions;
import eu.kalafatic.gemini.core.lib.EExt;

/**
 * The listener interface for receiving swarmsViewerDrop events. The class that is interested in processing a swarmsViewerDrop event implements this
 * interface, and the object created with that class is registered with a component using the component's
 * <code>addSwarmsViewerDropListener<code> method. When
 * the swarmsViewerDrop event occurs, that object's appropriate
 * method is invoked.
 * @see SwarmsViewerDropEvent
 */
public class SwarmsViewerDropListener {

	/** The viewer. */
	private final Viewer viewer;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new swarms viewer drop listener.
	 * @param treeViewer the tree viewer
	 */
	public SwarmsViewerDropListener(TreeViewer treeViewer) {
		this.viewer = treeViewer;
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
					if (file.exists() && file.getPath().endsWith(EExt.TORRENT.ext)) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			@Override
			public synchronized void dropAccept(DropTargetEvent event) {

				String[] data = (String[]) FileTransfer.getInstance().nativeToJava(event.dataTypes[0]);

				for (int i = 0; i < data.length; i++) {

					try {
						TrackerModelActions.INSTANCE.addTorrentToModel(data[i]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				refresh();
				super.dropAccept(event);
			}
		});
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
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
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() && viewer.getControl().isVisible()) {

					viewer.refresh();
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	};
}
