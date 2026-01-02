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

/**
 * The Class class SwarmThreadsManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmThreadsManager {

	/** The pooled threads. */
	private PooledThreads pooledThreads = new PooledThreads(30);

	/** The instance. */
	private static SwarmThreadsManager INSTANCE;

	/**
	 * Instantiates a new swarm threads manager.
	 */
	public SwarmThreadsManager() {
		init();
	}

	/**
	 * Gets the single instance of SwarmThreadsManager.
	 * @return single instance of SwarmThreadsManager
	 */
	public static SwarmThreadsManager getInstance() {
		if (INSTANCE == null) {
			synchronized (SwarmThreadsManager.class) {
				INSTANCE = new SwarmThreadsManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {}

	// ---------------------------------------------------------------

	/**
	 * Gets the pooled threads.
	 * @return the pooled threads
	 */
	public PooledThreads getPooledThreads() {
		return pooledThreads;
	}

}
