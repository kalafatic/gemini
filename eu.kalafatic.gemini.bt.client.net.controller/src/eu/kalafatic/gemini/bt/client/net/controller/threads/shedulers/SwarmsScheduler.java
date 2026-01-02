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
package eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TRACKERS_DELAY;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.Timer;
import java.util.TimerTask;

import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.MemoryUtils;

/**
 * The Class class SwarmsScheduler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmsScheduler {

	/** The timer. */
	private Timer timer;

	/** The task. */
	private TrackersTask task;

	/** The Constant INSTANCE. */
	public static final SwarmsScheduler INSTANCE = new SwarmsScheduler();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Schedule.
	 */
	public void schedule() {
		timer = new Timer(false);
		task = new TrackersTask();
		timer.scheduleAtFixedRate(task, TRACKERS_DELAY * 1000, TRACKERS_DELAY * 2 * 1000);
		Log.log(EBTClientPreferences.MODULE, "SHEDULER-SWARMS : SHEDULE");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class TrackersTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class TrackersTask extends TimerTask {

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			try {
				Log.log(EBTClientPreferences.MODULE, "SHEDULER-SWARMS : WAKE UP");
				checkMemory();
				SwarmsAdapter.getInstance().processSheduler();

				// NetworkModelManager.getInstance().getClientNetwork()
				// .getSemaphor().setOpened(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check memory.
	 */

	private void checkMemory() {
		if (MemoryUtils.INSTANCE.getMemoryPerformance() > 70) {
			Runtime.getRuntime().gc();

			Log.log(EBTClientPreferences.MODULE, LOG + "MEMORY USAGE OVER 70% - GC STARTED");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Stop.
	 */
	public void stop() {
		if (timer != null) {
			timer.cancel();
		}
	}
}
