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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.CLIENT_TIMEOUT;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.MY_INTERFACES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.AControl;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.ASessionHandler;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.NetworkUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SearchHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("unchecked")
public class SearchHandler extends ASessionHandler {

	/** The swarm session. */
	private final SwarmSession swarmSession;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The download executor. */
	private ThreadPoolExecutor downloadExecutor;

	/** The erase counter. */
	int eraseCounter = 0;

	/** The offline. */
	Set<String> offline = new HashSet<String>();

	/**
	 * Instantiates a new search handler.
	 * @param swarmSession the swarm session
	 */
	public SearchHandler(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;

		extTorrent = (ExtTorrent) swarmSession.getTorrent();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			if (!AControl.canContinue(extTorrent)) {
				return;
			}
			if (++eraseCounter > 3) {
				offline.clear();
				eraseCounter = 0;
			}
			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.SEARCH_FOR_CLIENTS);

			downloadExecutor = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(swarmSession.getAnnounce()).getDownloadExecutor();

			final int MAX = downloadExecutor.getMaximumPoolSize();

			Iterator<String> iterator = swarmSession.getClients().iterator();
			while (iterator.hasNext()) {
				String announce = iterator.next();

				if (MY_INTERFACES.contains(announce) || swarmSession.getDownloads().containsKey(announce)) {
					continue;
				}
				if (offline.contains(announce)) {
					System.err.println("*** offline === " + eraseCounter + "-" + announce);
					continue;
				}
				// pingAtClient(announce);
				if (pingAtClient(announce) && swarmSession.getDownloads().size() > MAX) {
					break;
				}
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		} finally {
			if (extTorrent.getStatus().getValue() < EViewsMessages.CONNECTING_CLIENTS.getValue() && extTorrent.getStatus().getValue() > 19) {

				NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.SEARCH_FOR_CLIENTS_FINISHED);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Ping at client.
	 * @param announce the announce
	 * @return true, if successful
	 */
	private/* synchronized */boolean pingAtClient(String announce) {
		SocketChannel channel = null;
		try {
			SocketAddress socketAddress = NetworkUtils.getSocketAddress(announce);
			channel = SocketChannel.open();
			startSession();
			channel.socket().connect(socketAddress, CLIENT_TIMEOUT);

			// channel.socket().setKeepAlive(true);
			provideSuccessfullContact(announce, channel);

			Log.log(MODULE, LOG + "ACTIVE CLIENT SEARCH " + announce);

			return true;
		} catch (Exception e) {
			Log.log(MODULE, LOG + "NON ACTIVE CLIENT " + announce);
			dispose(channel);
		}
		offline.add(announce);
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Provide successfull contact.
	 * @param announce the announce
	 * @param channel the channel
	 * @throws Exception the exception
	 */
	private void provideSuccessfullContact(String announce, SocketChannel channel) throws Exception {

		DwnldSession dwnldSession = createDwnldSession(channel, announce);
		downloadExecutor.execute(new DownloadHandler(swarmSession, dwnldSession));

		swarmSession.getDownloads().put(announce, dwnldSession);
		NetworkUtils.changeRating(swarmSession, dwnldSession, 1);

	}

	// ---------------------------------------------------------------

	/**
	 * Creates the dwnld session.
	 * @param channel the channel
	 * @param announce the announce
	 * @return the dwnld session
	 */
	private DwnldSession createDwnldSession(SocketChannel channel, String announce) {
		DwnldSession dwnldSession = ClientNetworkFactory.eINSTANCE.createDwnldSession();
		dwnldSession.setAnnounce(announce);
		dwnldSession.setChannel(channel);
		dwnldSession.setState(EViewsMessages.READY);

		setSessionConnTimeout(dwnldSession);
		dwnldSession.setDuration(getConnStart());

		dwnldSession.setSpeedContainer(ClientNetworkFactoryImpl.eINSTANCE.createSpeedContainer());

		return dwnldSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Dispose.
	 * @param channel the channel
	 */
	private void dispose(SocketChannel channel) {
		try {
			if (channel != null) {
				channel.close();
				channel = null;
			}
		} catch (IOException e) {
			Log.log(MODULE, e);
		}
	}
}
