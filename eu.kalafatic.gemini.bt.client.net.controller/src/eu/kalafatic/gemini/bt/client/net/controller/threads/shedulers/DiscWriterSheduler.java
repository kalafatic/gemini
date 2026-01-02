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

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscManager;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class DiscWriterSheduler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@Deprecated
public class DiscWriterSheduler {

	/** The delay. */
	private final int delay = 10;

	/** The timer. */
	private Timer timer;

	/** The task. */
	private WriteOnDiscTask task;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Schedule.
	 */
	public void schedule() {
		timer = new Timer(false);
		task = new WriteOnDiscTask();
		timer.scheduleAtFixedRate(task, delay * 1000, delay * 1000);
		Log.log(EBTClientPreferences.MODULE, "SHEDULER-DISC : SHEDULE");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class WriteOnDiscTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class WriteOnDiscTask extends TimerTask {
		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			Log.log(EBTClientPreferences.MODULE, "SHEDULER-DISC : WAKE-UP");
			checkWriteBuffer();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check write buffer.
	 */
	private void checkWriteBuffer() {
		try {
			Collection<Session> values = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap().values();
			for (Session session : values) {
				DiscManager.getInstance().flushPieceBuffer((SwarmSession) session);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log(EBTClientPreferences.MODULE, "SHEDULER-DISC : FLUSH ERROR");
		}
	}
}
