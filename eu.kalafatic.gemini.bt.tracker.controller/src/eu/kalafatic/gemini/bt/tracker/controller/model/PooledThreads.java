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
package eu.kalafatic.gemini.bt.tracker.controller.model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;

/**
 * The Class class PooledThreads.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class PooledThreads {

	/** The upload executor. */
	private ThreadPoolExecutor clientExecutor, uploadExecutor;

	/**
	 * Instantiates a new pooled threads.
	 * @param keepAliveTime the keep alive time
	 */
	public PooledThreads(long keepAliveTime) {

		int maxClientThreads = Activator.getPreferences().getInt(ETrackerPreferences.MAX_CLIENT_THREADS.getName(), (Integer) ETrackerPreferences.MAX_CLIENT_THREADS.getDef());

		this.clientExecutor = new ThreadPoolExecutor(maxClientThreads, maxClientThreads, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		int maxUpldThreads = Activator.getPreferences().getInt(ETrackerPreferences.MAX_UPLD_THREADS.getName(), (Integer) ETrackerPreferences.MAX_UPLD_THREADS.getDef());

		this.uploadExecutor = new ThreadPoolExecutor(maxUpldThreads, maxUpldThreads, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the upload executor.
	 * @return the upload executor
	 */
	public ThreadPoolExecutor getUploadExecutor() {
		return uploadExecutor;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the client executor.
	 * @return the client executor
	 */
	public ThreadPoolExecutor getClientExecutor() {
		return clientExecutor;
	}
}
