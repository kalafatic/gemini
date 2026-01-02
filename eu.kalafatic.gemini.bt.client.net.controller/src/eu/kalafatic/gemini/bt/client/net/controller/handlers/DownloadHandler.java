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
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_HANDSHAKE_ERROR;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_HANDSHAKE_OK;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_IS_CHOKING;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_NOT_INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.DOWNLOAD_ERROR;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.FINISHED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.READY_TO_DOWNLOAD;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.READY_TO_INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.SUSPENDED;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.NetworkUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class DownloadHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DownloadHandler extends AClientHandler {

	/**
	 * Instantiates a new download handler.
	 * @param swarmSession the swarm session
	 * @param session the session
	 * @throws Exception the exception
	 */
	public DownloadHandler(SwarmSession swarmSession, DwnldSession session) throws Exception {
		super(swarmSession, session);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			// init loop upload
			isDwnldUpldRatioAcceptable();

			while (true) {
				if (!canContinue(extTorrent) || !dwnldStarted) {
					throw new Exception();
				}
				processTask();

				switch (session.getState()) {

				case SUSPENDED:
					--rating;
				case FINISHED:
					return;

				default:
					break;
				}
			}
		} catch (Exception e) {
			--rating;
			session.setState(SUSPENDED);
			NetworkManager.getInstance().setViewMessage(extTorrent, DOWNLOAD_ERROR);
			Log.log(MODULE, e);
		} finally {
			NetworkUtils.changeRating(swarmSession, session, rating);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#processTask ()
	 */
	@Override
	public void processTask() throws Exception {
		switch (session.getState()) {

		case READY:
		case ACTIVATED:
			if (processHandshake()) {
				session.setState(READY_TO_INTERESTED);
				NetworkManager.getInstance().setViewMessage(extTorrent, CLIENT_HANDSHAKE_OK);
			} else {
				session.setState(SUSPENDED);
				NetworkManager.getInstance().setViewMessage(extTorrent, CLIENT_HANDSHAKE_ERROR);
			}
			break;

		case READY_TO_INTERESTED:
			if (processInterested()) {

				if (session.isChoking()) {
					session.setState(SUSPENDED);
					NetworkManager.getInstance().setViewMessage(extTorrent, CLIENT_IS_CHOKING);
				} else {
					// boostPerformance(session);

					session.setState(READY_TO_DOWNLOAD);
					NetworkManager.getInstance().setViewMessage(extTorrent, READY_TO_DOWNLOAD);
				}
			} else {
				session.setState(SUSPENDED);
				NetworkManager.getInstance().setViewMessage(extTorrent, CLIENT_NOT_INTERESTED);
			}
			break;

		case READY_TO_DOWNLOAD:
			if (processDownload()) {
				// peer could ask for data
				if (session.isInterested()) {
					if (canUpload) {
						if (!unchokeSent) {
							sendUnchoke();
						}
					} else {
						sendChoke();
					}
				}
				// don't return handler if not necessary
				if (!shouldContinue()) {
					session.setState(FINISHED);
				}
			} else {
				session.setState(SUSPENDED);
				NetworkManager.getInstance().setViewMessage(extTorrent, DOWNLOAD_ERROR);
			}
			break;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Should continue.
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean shouldContinue() throws Exception {
		float average = getAverageRating(swarmSession.getDownloads().values());

		return session.getChannel().isConnected() && interested(extTorrent, session) && (session.getRating() > average);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #isDwnldUpldRatioAcceptable()
	 */
	@Override
	public boolean isDwnldUpldRatioAcceptable() {
		try {
			if (!isRatingOK(swarmSession)) {
				return canUpload = false;
			}
			if (!isSpeedOK(extTorrent)) {
				return canUpload = false;
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		}
		return canUpload = true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.AHandler# processHandshake()
	 */
	@Override
	public boolean processHandshake() throws Exception {
		Log.log(MODULE, LOG + "DOWNLOAD HANDSHAKE");

		if (super.processHandshake() && (session.getBitfield() != null)) {
			// sendHave();
			return true;
		}
		return false;
	}
}
