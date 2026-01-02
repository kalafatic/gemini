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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ERemote;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class WatchDogSheduler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class WatchDogSheduler {

	/** The ping remote. */
	private ERemote pingRemote;

	/** The delay. */
	private int delay;

	/** The timer. */
	private Timer timer;

	/** The counter. */
	@SuppressWarnings("unused")
	private int counter = 0;

	/** The running. */
	private boolean running = false;

	/** The instance. */
	private static WatchDogSheduler INSTANCE;

	/** The task. */
	private WatchDogTask task;

	/**
	 * Instantiates a new watch dog sheduler.
	 * @param pingRemote the ping remote
	 * @param delay the delay
	 */
	public WatchDogSheduler(ERemote pingRemote, int delay) {
		this.pingRemote = pingRemote;
		this.delay = delay;
	}

	/**
	 * Gets the single instance of WatchDogSheduler.
	 * @param pingRemote the ping remote
	 * @param delay the delay
	 * @return single instance of WatchDogSheduler
	 */
	public static WatchDogSheduler getInstance(ERemote pingRemote, int delay) {
		if (INSTANCE == null) {
			synchronized (WatchDogSheduler.class) {
				INSTANCE = new WatchDogSheduler(pingRemote, delay);
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Schedule.
	 */
	public void schedule() {
		if (!running) {
			running = true;
			timer = new Timer(false);

			String pingAddress = PREFERENCES.get(ECorePreferences.PING_ADDRESS.getName(), (String) ECorePreferences.PING_ADDRESS.getDef());

			task = new WatchDogTask(pingAddress, pingRemote);

			timer.scheduleAtFixedRate(task, delay * 1000, delay * 1000);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Dispose.
	 */
	public void dispose() {
		timer.cancel(); // Terminate the thread
		running = false;
	}

	// ---------------------------------------------------------------

	/**
	 * The Class class WatchDogTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class WatchDogTask extends TimerTask {

		/** The ping address. */
		private String pingAddress;

		/** The ping remote. */
		private ERemote pingRemote;

		/**
		 * Instantiates a new watch dog task.
		 * @param pingAddress the ping address
		 * @param pingRemote the ping remote
		 */
		public WatchDogTask(String pingAddress, ERemote pingRemote) {
			this.pingAddress = pingAddress;
			this.pingRemote = pingRemote;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			try {
				counter++;
				URL url = new URL(pingAddress);
				url.openConnection();

				Log.log(EBTClientPreferences.MODULE, "SHEDULER-WATCH DOG : WAKE-UP");

				NetworkModelManager.getInstance().getClientNetwork().getSemaphor().setConnected(true);

				if (pingRemote.equals(ERemote.AUTO)) {
					dispose();
				}

			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
				NetworkModelManager.getInstance().getClientNetwork().getSemaphor().setConnected(false);
			}
		}
	}
}
