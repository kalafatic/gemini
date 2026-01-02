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
package eu.kalafatic.gemini.bt.client.net.rc.controller.threads;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LISTENING;

import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.text.MessageFormat;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.rc.controller.handlers.FilterHandler;
import eu.kalafatic.gemini.bt.client.net.rc.controller.handlers.RCHttpHandler;
import eu.kalafatic.gemini.bt.client.net.rc.controller.lib.ERCPreferences;
import eu.kalafatic.gemini.core.interfaces.AServerThread;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SSLUtils;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class RCServerThread.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCServerThread extends AServerThread {

	/** The ssl enabled. */
	private boolean sslEnabled;

	/** The ssl server factory. */
	private SSLServerSocketFactory sslServerFactory;

	/** The INSTANCE. */
	private volatile static RCServerThread INSTANCE;

	/** The server. */
	private HttpServer server;

	/**
	 * Instantiates a new rC server thread.
	 */
	public RCServerThread() {
		init();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the single instance of RCServerThread.
	 * @return single instance of RCServerThread
	 */
	public static RCServerThread getInstance() {
		if (INSTANCE == null) {
			synchronized (RCServerThread.class) {
				INSTANCE = new RCServerThread();
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
			port = PREFERENCES.getInt(ECorePreferences.RC_PORT.getName(), (Integer) ECorePreferences.RC_PORT.getDef());
			sslEnabled = Activator.getPreferences().getBoolean(ERCPreferences.SSL_RC_ENABLED.getName(), (Boolean) ERCPreferences.SSL_RC_ENABLED.getDef());
			message = MessageFormat.format(LISTENING, ERCPreferences.MODULE.getName(), "to remote control", port);
		} catch (Exception e) {
			Log.log(ERCPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the ssl.
	 */
	private void initSSL() {
		try {
			KeyStore keyStore = SSLUtils.getInstance().loadKeystore();

			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(keyStore, "traged".toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(keyStore);

			SSLContext context = SSLContext.getInstance("SSL");
			context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			sslServerFactory = context.getServerSocketFactory();

		} catch (Exception e) {
			Log.log(ERCPreferences.MODULE, e);
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
			if (sslEnabled) {
				server = HttpsServer.create(new InetSocketAddress(port), 0);
			} else {
				server = HttpServer.create(new InetSocketAddress(port), 0);
			}
			super.processMessages(ERCPreferences.MODULE, message);
			SoundPlayer.getInstance().play("audrey_gemini_is_listenning_for_remote_control.wav");

			HttpContext context = server.createContext("/", new RCHttpHandler());
			context.getFilters().add(new FilterHandler());
			server.setExecutor(null);
			server.start();

		} catch (Exception e) {
			Log.log(ERCPreferences.MODULE, e);
		} finally {
			// super.processEndOfThread(ERCPreferences.MODULE, serverSocket);
		}
	}
}
