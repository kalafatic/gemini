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
package eu.kalafatic.gemini.bt.client.net.controller.threads;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LISTENING;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SRV_ACC;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;

import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.interfaces.AServerThread;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class ListenClientsThread.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ListenClientsThread extends AServerThread {

	/** The instance. */
	private volatile static ListenClientsThread INSTANCE;

	/**
	 * Instantiates a new listen clients thread.
	 */
	public ListenClientsThread() {
		init();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the single instance of ListenClientsThread.
	 * @return single instance of ListenClientsThread
	 */
	public static ListenClientsThread getInstance() {
		if (INSTANCE == null) {
			synchronized (ListenClientsThread.class) {
				INSTANCE = new ListenClientsThread();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IServerThread#init()
	 */
	@Override
	public void init() {
		try {
			port = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

			message = MessageFormat.format(LISTENING, EBTClientPreferences.MODULE.getName(), "for upload", port);

			InetAddress ia = InetAddress.getLocalHost();
			isa = new InetSocketAddress(ia, port);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			super.bind(EBTClientPreferences.MODULE);
			SoundPlayer.getInstance().play("audrey_bittorrent_client_is listenning_for_uploads.wav");

			while (true) {
				// catch one connection exception
				try {
					SocketChannel channel = serverSocketChannel.accept();

					UpldSession session = createClientSession(channel);
					SwarmThreadsManager.getInstance().runUpldClient(session);

					Log.log(EBTClientPreferences.MODULE, LOG + SRV_ACC + channel.socket().getInetAddress() + ":" + channel.socket().getPort());

				} catch (Exception e) {
					Log.log(EBTClientPreferences.MODULE, e);
				}
			}
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		} finally {
			super.processEndOfThread(EBTClientPreferences.MODULE, serverSocketChannel);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client session.
	 * @param channel the channel
	 * @return the upld session
	 */
	private UpldSession createClientSession(SocketChannel channel) {
		UpldSession upldSession = ClientNetworkFactory.eINSTANCE.createUpldSession();

		upldSession.setAnnounce(createAnnounce(channel));
		upldSession.setState(EViewsMessages.READY);
		upldSession.setChannel(channel);
		upldSession.setSpeedContainer(ClientNetworkFactoryImpl.eINSTANCE.createSpeedContainer());

		return upldSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the announce.
	 * @param channel the channel
	 * @return the string
	 */
	private String createAnnounce(SocketChannel channel) {
		String hostName = channel.socket().getInetAddress().getHostName();
		int port = channel.socket().getPort();

		return "http://" + hostName + ":" + port;
	}
}
