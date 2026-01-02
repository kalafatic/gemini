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

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.KEEP_ALIVE;

import java.util.Timer;
import java.util.TimerTask;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.transporters.Transporter;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class KeepAliveSheduler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@Deprecated
public class KeepAliveSheduler {

	/** The transporter. */
	private Transporter transporter;

	/** The delay. */
	private final int delay = 60;

	/** The timer. */
	private Timer timer;

	/** The task. */
	private KeepAliveTask task;

	/**
	 * Instantiates a new keep alive sheduler.
	 * @param transporter the transporter
	 */
	public KeepAliveSheduler(Transporter transporter) {
		this.transporter = transporter;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Schedule.
	 */
	public void schedule() {
		timer = new Timer(false);
		task = new KeepAliveTask();
		timer.scheduleAtFixedRate(task, delay * 1000, delay * 1000);
		Log.log(EBTClientPreferences.MODULE, "SHEDULER-KEEP ALIVE : SHEDULE");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class KeepAliveTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class KeepAliveTask extends TimerTask {

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			Log.log(EBTClientPreferences.MODULE, "SHEDULER-KEEP ALIVE : WAKE-UP");
			try {
				transporter.send(KEEP_ALIVE);
			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Dispose.
	 */
	public void dispose() {
		if (timer != null) {
			timer.cancel();
		}
	}
}
