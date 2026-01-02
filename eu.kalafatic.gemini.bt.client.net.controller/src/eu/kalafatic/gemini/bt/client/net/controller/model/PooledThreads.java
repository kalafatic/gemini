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
package eu.kalafatic.gemini.bt.client.net.controller.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.executors.ClientPoolExecutor;
import eu.kalafatic.gemini.bt.client.net.controller.factories.ExecutorThreadFactory;
import eu.kalafatic.gemini.bt.client.net.controller.handlers.RejectedTaskHandler;
import eu.kalafatic.gemini.bt.client.net.controller.handlers.SearchHandler;
import eu.kalafatic.gemini.bt.client.net.controller.handlers.TrackerHandler;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Class class PooledThreads.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class PooledThreads {

	/** The tracker handler. */
	private TrackerHandler trackerHandler;

	/** The search handler. */
	private SearchHandler searchHandler;

	/** The upload executor. */

	/** The upload executor. */
	private ThreadPoolExecutor downloadExecutor, uploadExecutor;

	/** The lock. */
	public final Lock lock = new ReentrantLock(true);

	/** The max dwnld threads. */
	private final int MAX_DWNLD_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());

	/** The max upld threads. */
	private final int MAX_UPLD_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_UPLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_UPLD_THREADS.getDef());

	/** The executor timeout. */
	private final int EXECUTOR_TIMEOUT = Activator.getPreferences().getInt(EBTClientPreferences.EXECUTOR_TIMEOUT.getName(), (Integer) EBTClientPreferences.EXECUTOR_TIMEOUT.getDef());

	/**
	 * Instantiates a new pooled threads.
	 * @param swarmSession the swarm session
	 */
	public PooledThreads(SwarmSession swarmSession) {
		RejectedTaskHandler rejectedTaskHandler = new RejectedTaskHandler(swarmSession);
		trackerHandler = new TrackerHandler(swarmSession);
		searchHandler = new SearchHandler(swarmSession);

		// core-max-timeout-size / 1*-2*-2*-4* (8)
		downloadExecutor = new ClientPoolExecutor(swarmSession, MAX_DWNLD_THREADS, 2 * MAX_DWNLD_THREADS, EXECUTOR_TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2 * MAX_DWNLD_THREADS),
				rejectedTaskHandler, new ExecutorThreadFactory(swarmSession + "-downloadExecutor"));

		uploadExecutor = new ClientPoolExecutor(swarmSession, MAX_UPLD_THREADS, 2 * MAX_UPLD_THREADS, EXECUTOR_TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2 * MAX_UPLD_THREADS),
				rejectedTaskHandler, new ExecutorThreadFactory(swarmSession + "-uploadExecutor"));

		// uploadExecutor = new ClientPoolExecutor(swarmSession,
		// MAX_UPLD_THREADS,
		// 2 * MAX_UPLD_THREADS, EXECUTOR_TIMEOUT, TimeUnit.SECONDS,
		// new SynchronousQueue<Runnable>(), rejectedTaskHandler,
		// new ExecutorThreadFactory(swarmSession + "-uploadExecutor"));
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the download executor.
	 * @return the download executor
	 */
	public ThreadPoolExecutor getDownloadExecutor() {
		return downloadExecutor;
	}

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
	 * Gets the tracker handler.
	 * @return the tracker handler
	 */
	public TrackerHandler getTrackerHandler() {
		return trackerHandler;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the search handler.
	 * @return the search handler
	 */
	public SearchHandler getSearchHandler() {
		return searchHandler;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the lock.
	 * @return the lock
	 */
	public Lock getLock() {
		return lock;
	}
}
