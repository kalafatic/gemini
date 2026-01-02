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
package eu.kalafatic.gemini.bt.client.net.controller.dnd;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.GraphViewer;

import eu.kalafatic.gemini.core.factories.CommandFactory;

/**
 * The listener interface for receiving BTGraphDrop events. The class that is interested in processing a BTGraphDrop event implements this interface,
 * and the object created with that class is registered with a component using the component's <code>addBTGraphDropListener<code> method. When
 * the BTGraphDrop event occurs, that object's appropriate
 * method is invoked.
 * @see BTGraphDropEvent
 */
public class BTGraphDropListener {

	/** The viewer. */
	private final GraphViewer viewer;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new BT graph drop listener.
	 * @param viewer the viewer
	 */
	public BTGraphDropListener(GraphViewer viewer) {
		this.viewer = viewer;

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
					CommandFactory.INSTANCE.executeCommand("eu.kalafatic.gemini.bt.client.controller.handlers.AddTorrentHandler", data[i], "false");

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
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {

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
