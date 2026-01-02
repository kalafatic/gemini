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
package eu.kalafatic.gemini.bt.client.net.rc.controller.main;

import static eu.kalafatic.gemini.core.lib.constants.FCoreConstants.REMOTE_PASS;
import static eu.kalafatic.gemini.core.lib.constants.FCoreConstants.REMOTE_USER;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ID;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PASS;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.net.ssl.SSLSocket;

import org.eclipse.ui.PlatformUI;

import com.sun.net.httpserver.HttpExchange;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.bt.client.net.rc.controller.builders.ClientTableBuilder;
import eu.kalafatic.gemini.bt.client.net.rc.controller.model.RCModelManager;
import eu.kalafatic.gemini.bt.client.net.rc.controller.model.Request;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;

/**
 * The Class class RCManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings("unchecked")
public class RCManager {

	/** The logged. */
	private boolean accCert, logged;

	/** The INSTANCE. */
	private volatile static RCManager INSTANCE;

	/**
	 * Gets the single instance of RCManager.
	 * @return single instance of RCManager
	 */
	public static RCManager getInstance() {
		if (INSTANCE == null) {
			synchronized (RCManager.class) {
				INSTANCE = new RCManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run session.
	 * @param rcSession the rc session
	 * @throws IOException Signals that an I/O exception has occurred.
	 */

	public void runSession(RCSession rcSession) throws IOException {
		try {
			HttpExchange httpExchange = (HttpExchange) rcSession.getHttpExchange();
			Map<String, Object> parameters = (Map<String, Object>) httpExchange.getAttribute("parameters");

			switch (rcSession.getState()) {

			case NEW:
				sendPage(rcSession, RCModelManager.getInstance().getLogin());
				rcSession.setState(ERCSessionState.LOGIN);
				break;
			case LOGIN:
				processLogin(parameters);
				sendPage(rcSession, null);
				rcSession.setState(ERCSessionState.LOGED);
				break;
			case LOGED:
				processCommand(rcSession, parameters);
				break;
			case CERTIFIED:

				break;
			default:
				break;
			}
		} catch (Exception e) {
			rcSession.setState(ERCSessionState.LOGIN);
			sendPage(rcSession, RCModelManager.getInstance().getLoginError());
			// e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	// http://localhost:6881/test?name=a&password=a
	/**
	 * Process login.
	 * @param parameters the parameters
	 * @throws Exception the exception
	 */
	private void processLogin(Map<String, Object> parameters) throws Exception {
		if (!(parameters.get(ID).equals(REMOTE_USER) && parameters.get(PASS).equals(REMOTE_PASS))) {
			throw new Exception("Login error");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process command.
	 * @param rcSession the rc session
	 * @param parameters the parameters
	 * @throws Exception the exception
	 */
	private void processCommand(RCSession rcSession, Map<String, Object> parameters) throws Exception {
		Object cmd = parameters.get("cmd");

		if (cmd == null) {
			return;
		}

		if (cmd.equals(ERCCMD.RC_LOGOUT.getLiteral())) {
			rcSession.setState(ERCSessionState.LOGIN);
			sendPage(rcSession, RCModelManager.getInstance().getLogin());

		} else if (cmd.equals(ERCCMD.APP_RESTART.getLiteral())) {
			RCModelManager.getInstance().doSave();

			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					PlatformUI.getWorkbench().restart();
				}
			});

		} else if (cmd.equals(ERCCMD.SEL_ALL.getLiteral())) {
			rcSession.setTorrents(ERCCMD.SEL_ALL);
			sendPage(rcSession, null);

		} else if (cmd.equals(ERCCMD.SEL_UNF.getLiteral())) {
			rcSession.setTorrents(ERCCMD.SEL_UNF);
			sendPage(rcSession, null);

		} else if (cmd.equals(ERCCMD.SEL_FIN.getLiteral())) {
			rcSession.setTorrents(ERCCMD.SEL_FIN);
			sendPage(rcSession, null);

		} else if (cmd.equals(ERCCMD.TSTART.getLiteral())) {
			startTorrent(parameters.get("torrent"), true);
			sendPage(rcSession, null);

		} else if (cmd.equals(ERCCMD.TSTOP.getLiteral())) {
			startTorrent(parameters.get("torrent"), false);
			sendPage(rcSession, null);

		} else if (cmd.equals(ERCCMD.TADD.getLiteral())) {
			System.err.println();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Start torrent.
	 * @param key the key
	 * @param start the start
	 */
	private void startTorrent(Object key, boolean start) {
		ExtTorrent extTorrent = NetworkModelManager.getInstance().getTorrents().getTorrentMap().get(key);
		if (extTorrent == null) {
			extTorrent = NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().get(key);
		}
		if (extTorrent == null) {
			return;
		}

		extTorrent.setEnabled(start);

		if (start) {
			extTorrent.setStatus(EViewsMessages.READY);
			NetworkManager.getInstance().runSwarms(extTorrent);
		} else {
			extTorrent.setStatus(EViewsMessages.STOPPED);
			NetworkManager.getInstance().stopSwarms(extTorrent);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run session.
	 * @param socket the socket
	 * @throws Exception the exception
	 */
	public void runSession(SSLSocket socket) throws Exception {
		try {
			while (!accCert) {
				// try {
				// // server.parseRequest(socket);
				//
				// // server.sendResponse(socket, RCModelManager.getInstance()
				// // .getLogin());
				//
				// accCert = true;
				//
				// } catch (SSLHandshakeException e) {
				// e.printStackTrace();
				//
				// Thread.sleep(5000);
				//
				// } catch (SSLException e) {
				// e.printStackTrace();
				// return;
				// }
			}

			if (!logged) {

				for (int i = 0; i < 3; i++) {
					if (processLogin(socket)) {
						logged = true;
						// sendTorrentsTable(socket);
						return;

					} else {
						// String loginError = RCModelManager.getInstance()
						// .getLoginError();
						// server.sendResponse(socket, loginError);

						if (i == 2) {
							return;
						}
					}
				}
			}

			// Request request = readRequestCommandString(socket);
			//
			// processRequest(socket, request);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket.close();
			// synchronized (RCServerThread.getInstance()) {
			// RCServerThread.getInstance().notify();
			// }
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process request.
	 * @param socket the socket
	 * @param request the request
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processRequest(SSLSocket socket, Request request) throws IOException {
		switch (request.getKeyVal()) {
		case 0:
			// server.sendResponse(socket,
			// RCModelManager.getInstance().getLogin());
			logged = false;
			break;

		default:
			break;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process login.
	 * @param socket the socket
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean processLogin(SSLSocket socket) throws Exception {
		// readRequestIntoString(socket);
		// if ((getMap.get("username").equals(REMOTE_USER))
		// && (getMap.get("password").equals(REMOTE_PASS))) {
		// return true;
		// }
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Send page.
	 * @param rcSession the rc session
	 * @param page the page
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendPage(RCSession rcSession, String page) throws IOException {
		if (page == null) {
			String clientHeader = RCModelManager.getInstance().getClientHeader();

			String torrentForms = ClientTableBuilder.getInstance().buildClientForms(rcSession.getTorrents());

			String clientEnd = RCModelManager.getInstance().getClientEnd();

			page = clientHeader + torrentForms + clientEnd;
		}
		HttpExchange httpExchange = (HttpExchange) rcSession.getHttpExchange();
		httpExchange.sendResponseHeaders(200, page.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(page.getBytes());
		os.close();
	}
}
