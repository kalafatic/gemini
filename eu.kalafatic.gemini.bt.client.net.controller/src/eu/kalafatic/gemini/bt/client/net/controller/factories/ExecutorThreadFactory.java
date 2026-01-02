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
package eu.kalafatic.gemini.bt.client.net.controller.factories;

import java.util.concurrent.ThreadFactory;

/**
 * A factory for creating ExecutorThread objects.
 */
public class ExecutorThreadFactory implements ThreadFactory {

	// private String executorName;

	/**
	 * Instantiates a new executor thread factory.
	 * @param executorName the executor name
	 */
	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.d HH:mm:ss:S");

	public ExecutorThreadFactory(String executorName) {
		// this.executorName=executorName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable runnable) {
		// System.out.println(executorName + "   "
		// + sdf.format(System.currentTimeMillis()));

		return new Thread(runnable);
	}
}
