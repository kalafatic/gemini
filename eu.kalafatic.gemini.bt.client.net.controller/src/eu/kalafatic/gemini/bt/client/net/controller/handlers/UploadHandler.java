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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.MAX_UPLOAD;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_HANDSHAKE_ERROR;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.CLIENT_HANDSHAKE_OK;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.FINISHED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.PEER_NOT_INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.RATING_REJECTION;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.RATIO_EXCEEDED_CHOKING;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.READY_TO_INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.READY_TO_UPLOAD;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.SUSPENDED;
import static eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages.UPLOAD_ERROR;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.PooledThreads;
import eu.kalafatic.gemini.bt.client.net.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.ConvertUtils;
import eu.kalafatic.gemini.bt.client.net.controller.utils.NetworkUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClientTransportProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class UploadHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class UploadHandler extends AClientHandler {

	/** The uploaded. */
	public int uploaded = 0;

	/** The first attempt. */
	private boolean firstAttempt = true;

	/**
	 * Instantiates a new upload handler.
	 * @param upldSession the upld session
	 * @throws Exception the exception
	 */
	public UploadHandler(UpldSession upldSession) throws Exception {
		super(upldSession);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @return true, if successful
	 */
	public boolean init() {
		try {
			if (!processHandshake()) {
				Log.log(MODULE, LOG + "UPLOAD REJECTED-TORRENT");
				return false;
			}
			setAlgorithm();
			Log.log(MODULE, LOG + "UPLOAD INCOMMING TORRENT");

		} catch (Exception e) {
			Log.log(MODULE, e);
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			uploaded = 0;
			while (true) {
				if (!canContinue(extTorrent)) {
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
			NetworkManager.getInstance().setViewMessage(extTorrent, UPLOAD_ERROR);
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
			if (super.processHandshake()) {
				session.setState(READY_TO_INTERESTED);
				Log.log(MODULE, CLIENT_HANDSHAKE_OK.getLiteral());
			} else {
				session.setState(SUSPENDED);
				Log.log(MODULE, CLIENT_HANDSHAKE_ERROR.getLiteral());
			}
			break;
		case READY_TO_INTERESTED:
			if (session.isInterested()) {
				if (!unchokeSent) {
					sendUnchoke();
				}
				// boostPerformance(session);

				session.setState(READY_TO_UPLOAD);
				Log.log(MODULE, EViewsMessages.READY_TO_UPLOAD.getLiteral());
			} else {
				NetworkManager.getInstance().setViewMessage(extTorrent, PEER_NOT_INTERESTED);

				if (firstAttempt) {
					firstAttempt = false;
					messageDecoder.decodeMessages(EClientTransportProtocol.INTERESTED_VALUE, true);
				} else {
					if (this.processDownload()) {
						session.setState(FINISHED);
					} else {
						session.setState(SUSPENDED);
						Log.log(MODULE, PEER_NOT_INTERESTED.getLiteral());
					}
				}
			}
			break;
		case READY_TO_UPLOAD:
			if (messageDecoder.decodeMessages(EClientTransportProtocol.REQUEST_VALUE, false)) {

				session.setRating(++rating);
				Log.log(MODULE, LOG + "TRANSPORT-UPLOAD");

				if (++uploaded >= MAX_UPLOAD) {
					uploaded = 0;
					if (isDwnldUpldRatioAcceptable()) {
						session.setState(FINISHED);
					} else {
						SoundPlayer.getInstance().play("audrey_upload_rejected.wav");
						session.setState(SUSPENDED);
					}
					if (dwnldStarted) {
						this.processDownload();
					}
				}
			} else {
				session.setState(SUSPENDED);
				Log.log(MODULE, SUSPENDED.getLiteral());
			}
			break;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #isDwnldUpldRatioAcceptable()
	 */
	@Override
	public boolean isDwnldUpldRatioAcceptable() {
		try {
			EMap<String, ExtTorrent> torrentMap = NetworkModelManager.getInstance().getTorrents().getTorrentMap();
			if (torrentMap.isEmpty()) {
				return canUpload = true;
			}

			if (!dwnldStarted) {
				return canUpload = true;
			}
			if (!isRatingOK(swarmSession)) {
				sendChokeAndFinish(RATING_REJECTION);
				return canUpload = false;
			}
			if (!isSpeedOK(extTorrent)) {
				sendChokeAndFinish(RATIO_EXCEEDED_CHOKING);
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
		Log.log(MODULE, LOG + "UPLOAD HANDSHAKE");

		// need to know peer
		if (!messageDecoder.decodeMessages()) {
			return false;
		}
		extTorrent = messageDecoder.getExtTorrent();
		swarmSession = messageDecoder.getSwarmSession();

		Assert.isNotNull(extTorrent, "Torrent not found.");

		// lazy set up finished torrent
		if (extTorrent.getModelBitfield() == null) {
			ConvertUtils.INSTANCE.setModelBitfield(extTorrent);
		}
		return true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.AHandler# processDownload()
	 */
	@Override
	public boolean processDownload() throws Exception {
		if (!extTorrent.isFinished() && session.getBitfield() != null && processInterested() && !session.isChoking()) {

			return super.processDownload();
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Finish with interest.
	 */
	public void finishWithInterest() {
		try {
			if (!extTorrent.isFinished() && session.getBitfield() != null && interested(extTorrent, session) && processInterested()) {

				DwnldSession dwnldSession = createDwnldSession();
				setUpDwnldSession(dwnldSession);
				runExecutor(dwnldSession);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the dwnld session.
	 * @return the dwnld session
	 * @throws Exception the exception
	 */
	private DwnldSession createDwnldSession() throws Exception {

		DwnldSession dwnldSession = ClientNetworkFactory.eINSTANCE.createDwnldSession();
		dwnldSession.setAnnounce(announce);
		dwnldSession.setChannel(session.getChannel());
		dwnldSession.getHave().addAll(session.getHave());
		dwnldSession.setBitfield(session.getBitfield());
		dwnldSession.setHandshakeOK(true);
		dwnldSession.setState(EViewsMessages.FINISHED);

		dwnldSession.setSpeedContainer(ClientNetworkFactoryImpl.eINSTANCE.createSpeedContainer());

		return dwnldSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up dwnld session.
	 * @param dwnldSession the new up dwnld session
	 */
	private void setUpDwnldSession(DwnldSession dwnldSession) {
		if (!swarmSession.getDownloads().containsKey(announce)) {
			swarmSession.getDownloads().put(announce, dwnldSession);
		}
		processPeersSeeds(extTorrent.getAdditionalInfo(), 1);
	}

	// ---------------------------------------------------------------

	/**
	 * Run executor.
	 * @param dwnldSession the dwnld session
	 * @throws Exception the exception
	 */
	private void runExecutor(DwnldSession dwnldSession) throws Exception {
		Map<String, PooledThreads> pooledThreadsMap = SwarmThreadsManager.getInstance().getPooledThreadsMap();

		if (pooledThreadsMap.containsKey(swarmSession.getAnnounce())) {

			ThreadPoolExecutor downloadExecutor = pooledThreadsMap.get(swarmSession.getAnnounce()).getDownloadExecutor();

			downloadExecutor.execute(new DownloadHandler(swarmSession, dwnldSession));
		}
	}
}
