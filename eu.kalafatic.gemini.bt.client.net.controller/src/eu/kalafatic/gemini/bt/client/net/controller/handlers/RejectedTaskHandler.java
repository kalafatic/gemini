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
package eu.kalafatic.gemini.bt.client.net.controller.handlers;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import eu.kalafatic.gemini.bt.client.net.controller.executors.ClientPoolExecutor;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class RejectedTaskHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {

	/** The swarm session. */
	private SwarmSession swarmSession;

	/**
	 * Instantiates a new rejected task handler.
	 * @param swarmSession the swarm session
	 */
	public RejectedTaskHandler(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java. lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		try {
			if (runnable instanceof IClientHandler) {
				IClientHandler handler = (IClientHandler) runnable;

				ClientSession session = handler.getSession();

				Log.log(MODULE, LOG + "REJECTED EXECUTION - " + session.getAnnounce());

				if (session instanceof DwnldSession) {
					handler.releaseSession(swarmSession.getDownloads());

				} else if (session instanceof UpldSession) {
					handler.releaseSession(swarmSession.getUploads());
					((ClientPoolExecutor) executor).addDownloader(handler, -1);
				}
				handler.processPeersSeeds(swarmSession, -1);
				session.getChannel().close();
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		}
	}
}
