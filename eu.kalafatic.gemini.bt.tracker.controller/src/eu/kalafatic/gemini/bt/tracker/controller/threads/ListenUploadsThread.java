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
package eu.kalafatic.gemini.bt.tracker.controller.threads;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LISTENING;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SRV_ACC;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.util.concurrent.ThreadPoolExecutor;

import eu.kalafatic.gemini.bt.tracker.controller.handlers.TrackerUploadHandler;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.SwarmThreadsManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages;
import eu.kalafatic.gemini.core.interfaces.AServerThread;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class ListenUploadsThread.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ListenUploadsThread extends AServerThread {

	/**
	 * Instantiates a new listen uploads thread.
	 */
	public ListenUploadsThread() {
		init();
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
			port = PREFERENCES.getInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef());

			message = MessageFormat.format(LISTENING, ETrackerPreferences.MODULE.getName(), "for upload", port);

			InetAddress ia = InetAddress.getLocalHost();
			isa = new InetSocketAddress(ia, port);

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
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
			super.bind(ETrackerPreferences.MODULE);
			SoundPlayer.getInstance().play("audrey_tracker_is_listenning_for_uploads.wav");

			while (enabled) {
				// catch one connection exception
				try {
					SocketChannel channel = serverSocketChannel.accept();

					ClientSession session = createClientSession(channel);
					ThreadPoolExecutor uploadExecutor = SwarmThreadsManager.getInstance().getPooledThreads().getUploadExecutor();
					uploadExecutor.execute(new TrackerUploadHandler(session));

					Log.log(ETrackerPreferences.MODULE, LOG + SRV_ACC + channel.socket().getInetAddress() + ":" + channel.socket().getPort());

					// SoundPlayer.getInstance().play("IncomingURL.wav");
				} catch (Exception e) {
					Log.log(ETrackerPreferences.MODULE, e);
				}
			}
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		} finally {
			super.processEndOfThread(ETrackerPreferences.MODULE, serverSocketChannel);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client session.
	 * @param channel the channel
	 * @return the client session
	 */
	private ClientSession createClientSession(SocketChannel channel) {
		ClientSession session = TrackerFactory.eINSTANCE.createClientSession();
		session.setSessionState(EViewMessages.READY);
		session.setChannel(channel);
		return session;
	}
}
