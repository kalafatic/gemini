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
package eu.kalafatic.gemini.bt.tracker.controller.main;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.threads.ListenPeersThread;
import eu.kalafatic.gemini.bt.tracker.controller.threads.ListenUploadsThread;
import eu.kalafatic.gemini.core.interfaces.AServerThread;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerManager {

	/** The instance. */
	private volatile static TrackerManager INSTANCE;

	/** The listen uploads thread. */
	private AServerThread listenPeersThread, listenUploadsThread;

	/**
	 * Gets the single instance of TrackerManager.
	 * @return single instance of TrackerManager
	 */
	public static TrackerManager getInstance() {
		if (INSTANCE == null) {
			synchronized (TrackerManager.class) {
				INSTANCE = new TrackerManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run tracker.
	 */
	public void runTracker() {
		listenPeersThread = new ListenPeersThread();
		listenPeersThread.start();
	}

	// ---------------------------------------------------------------

	/**
	 * Run upload tracker.
	 */
	public void runUploadTracker() {
		listenUploadsThread = new ListenUploadsThread();
		listenUploadsThread.start();
	}

	// ---------------------------------------------------------------

	/**
	 * Stop tracker.
	 */
	public void stopTracker() {
		try {
			listenPeersThread.setEnabled(false);
			listenPeersThread.interrupt();
			listenPeersThread = null;
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Stop upload tracker.
	 */
	public void stopUploadTracker() {
		try {
			listenUploadsThread.setEnabled(false);
			listenUploadsThread.interrupt();
			listenUploadsThread = null;
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}
}
