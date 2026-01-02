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
package eu.kalafatic.gemini.bt.client.net.controller.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.net.controller.factories.ExecutorThreadFactory;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ClientPoolExecutor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("unused")
public class ClientPoolExecutor extends ThreadPoolExecutor {

	/** The swarm session. */
	private SwarmSession swarmSession;

	/**
	 * Instantiates a new client pool executor.
	 * @param swarmSession the swarm session
	 * @param corePoolSize the core pool size
	 * @param maximumPoolSize the maximum pool size
	 * @param keepAliveTime the keep alive time
	 * @param unit the unit
	 * @param workQueue the work queue
	 * @param handler the handler
	 * @param executorThreadFactory the executor thread factory
	 */
	public ClientPoolExecutor(SwarmSession swarmSession, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler,
			ExecutorThreadFactory executorThreadFactory) {

		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, executorThreadFactory, handler);
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.ThreadPoolExecutor#beforeExecute(java.lang.Thread, java.lang.Runnable)
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable runnable) {
		super.beforeExecute(t, runnable);

		if (runnable instanceof IClientHandler) {
			IClientHandler handler = (IClientHandler) runnable;

			ClientSession session = handler.getSession();

			if (session.getState().equals(EViewsMessages.READY)) {
				session.setState(EViewsMessages.ACTIVATED);

				t.setPriority(Thread.MAX_PRIORITY);

				if (session instanceof UpldSession) {
					addDownloader(handler, 1);
				}
			} else if (session.getState().equals(EViewsMessages.FINISHED)) {

				if (session instanceof DwnldSession) {
					session.setState(EViewsMessages.READY_TO_DOWNLOAD);
				} else if (session instanceof UpldSession) {
					session.setState(EViewsMessages.READY_TO_UPLOAD);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.ThreadPoolExecutor#afterExecute(java.lang.Runnable, java.lang.Throwable)
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		try {
			if (r instanceof IClientHandler) {
				ClientSession session = ((IClientHandler) r).getSession();
				// reuse only if previous session is ok
				if (session.getState().equals(EViewsMessages.SUSPENDED) || !getQueue().add(r)) {
					throw new Exception(EViewsMessages.SUSPENDED.getLiteral());
				}
			}
		} catch (Exception e) {
			super.getRejectedExecutionHandler().rejectedExecution(r, this);
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the downloader.
	 * @param handler the handler
	 * @param increment the increment
	 */
	public void addDownloader(IClientHandler handler, int increment) {
		try {
			AdditionalInfo addInfo = handler.getExtTorrent().getAdditionalInfo();
			int downloaders = addInfo.getDownloaders();
			if ((increment < 0) && (downloaders <= 0)) {
				return;
			}
			addInfo.setDownloaders(downloaders + increment);
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}
}
