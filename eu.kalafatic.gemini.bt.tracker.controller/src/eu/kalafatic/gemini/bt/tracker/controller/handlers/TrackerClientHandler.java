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
package eu.kalafatic.gemini.bt.tracker.controller.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.builders.TrackerResponseBuilder;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.utils.decoders.BEncoder;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.utils.HexCodec;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerClientHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerClientHandler implements Runnable {

	/** The session. */
	private ClientSession session;

	/** The socket. */
	private Socket socket;

	/** The swarm map. */
	private EMap<String, Session> swarmMap;

	/** The request map. */
	private Map<String, String> requestMap = new HashMap<String, String>();

	/** The response. */
	private Map<String, Object> response;

	/** The communication. */
	private Communication communication;

	/** The request. */
	private String request;

	/**
	 * Instantiates a new tracker client handler.
	 * @param session the session
	 */
	public TrackerClientHandler(ClientSession session) {
		this.session = session;

		socket = session.getChannel().socket();
		swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();
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
			if (!init()) {
				return;
			}
			processTask();
			sendResponse();

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean init() throws Exception {
		request = readRequestIntoString();

		if (parseRequest()) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Process task.
	 * @throws Exception the exception
	 */
	private/* synchronized */void processTask() throws Exception {

		String stringHash = requestMap.get(ETrackerRequestProtocol.INFO_HASH.getLiteral());

		stringHash = URLDecoder.decode(stringHash, EEncoding.ISO_1.getLiteral());

		String hexStringHash = HexCodec.bytesToHexString(stringHash.getBytes(EEncoding.ISO_1.getLiteral()));

		TorrentSession torrentSession = (TorrentSession) swarmMap.get(hexStringHash);

		if (torrentSession != null) {

			EMap<String, Session> clientMap = torrentSession.getClientMap();

			resolveClientSession(clientMap, torrentSession);

			if (requestMap.containsKey(ETrackerRequestProtocol.DOWNLOADED.getLiteral())) {

				int downloaded = Integer.parseInt(requestMap.get(ETrackerRequestProtocol.DOWNLOADED.getLiteral()));

				long torrentLen = torrentSession.getTorrentLen();
				int complete = torrentSession.getComplete();
				int incomplete = torrentSession.getIncomplete();

				if (clientMap.containsKey(session.getAddress().toString())) {

					if (downloaded == torrentLen) {
						session.setSeed(true);
						torrentSession.setComplete(++complete);
						if (incomplete > 0) {
							torrentSession.setIncomplete(--incomplete);
						}
					}
				} else {
					if (downloaded == torrentLen) {
						session.setSeed(true);
						torrentSession.setComplete(++complete);
					} else {
						torrentSession.setIncomplete(++incomplete);
					}
				}
			}
			createResponse(torrentSession);

		} else {
			response = new HashMap<String, Object>();
			response.put(ETrackerResponseProtocol.FAILURE_REASON.getLiteral(), "Exception");
			Log.log(ETrackerPreferences.MODULE, "RESPONSE : EXCEPTION");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve client session.
	 * @param clientMap the client map
	 * @param torrentSession the torrent session
	 */
	private void resolveClientSession(EMap<String, Session> clientMap, TorrentSession torrentSession) {

		InetAddress inetAddress = session.getChannel().socket().getInetAddress();
		String address = inetAddress.getHostAddress();

		if (requestMap.containsKey("port")) {

			String port = requestMap.get("port");
			address += ":" + port;

			if (!clientMap.containsKey(address)) {
				clientMap.put(address, session);
			} else {
				Session session2 = clientMap.get(address);
				session2.setChannel(session.getChannel());
				EMap<String, String> requestMap2 = session2.getRequestMap();
				session = null;
				session = (ClientSession) session2;
				session.getRequestMap().putAll(requestMap2);
				session.getRequestMap().putAll(requestMap);
			}
			session.setListenPort(Integer.parseInt(port));
			session.setInfoHash(torrentSession.getInfoHash());

		} else {
			Log.log(ETrackerPreferences.MODULE, "CLIENT-SESSION : NO PORT");
		}
		if (requestMap.containsKey("peer_id")) {
			session.setPeerId(requestMap.get("peer_id"));
		}

		session.setAddress(address);
		session.setInfoHash(torrentSession.getInfoHash());

		createCommunication();
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the communication.
	 */
	private void createCommunication() {
		int messageQueueSize = Activator.getPreferences().getInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), (Integer) ETrackerPreferences.MESSAGE_QUEUE_SIZE.getDef());

		communication = TrackerFactory.eINSTANCE.createCommunication();
		communication.setDate(new Date());

		while (session.getCommunication().size() > messageQueueSize) {
			session.getCommunication().remove(0);
		}
		communication.setRequest(request);
		session.getCommunication().add(communication);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the response.
	 * @param torrentSession the torrent session
	 */
	private/* synchronized */void createResponse(TorrentSession torrentSession) {
		TrackerResponseBuilder responseBuilder = new TrackerResponseBuilder(torrentSession, requestMap);
		response = responseBuilder.buildResponse();
	}

	// ---------------------------------------------------------------

	/**
	 * Send response.
	 */
	private void sendResponse() {
		try {
			byte[] encodedResponse = BEncoder.encode(response);

			String stringResponse = new String(encodedResponse, EEncoding.ISO_1.getLiteral());

			String header = "HTTP/1.0 200 OK\r\n" + "Connection: close\r\n" + "Content-Type: text/plain\r\n" // ; Charset=ISO-8859-1
					+ "Content-length: " + encodedResponse.length + "\r\n" + "\r\n";

			socket.getOutputStream().write(header.getBytes());
			socket.getOutputStream().write(encodedResponse);
			socket.getOutputStream().flush();

			communication.setResponse(stringResponse);

			Log.log(ETrackerPreferences.MODULE, "CLIENT-RESPONSE : " + stringResponse);
			// SoundPlayer.getInstance().play("SendingMessage.wav");
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		} finally {
			dispose();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the request.
	 * @return true, if successful
	 */
	private boolean parseRequest() {
		try {
			request = request.substring(request.indexOf('?') + 1);
			request = request.substring(0, request.indexOf(' '));

			String[] dividedRequest = request.split("&");

			for (int i = 0; i < dividedRequest.length; i++) {

				String[] keyValue = dividedRequest[i].split("=");

				requestMap.put(keyValue[0], keyValue[1]);
			}
		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Read request into string.
	 * @return the string
	 */
	private String readRequestIntoString() {
		StringBuffer request = null;
		try {
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);

			request = new StringBuffer();
			String line = "";

			socket.setSoTimeout(500);

			while ((line = br.readLine()) != null) {
				request.append(line);
			}
		} catch (Exception e) {
			// do nothing
			// Log.log("", e);
		}
		return request.toString();
	}

	// ---------------------------------------------------------------

	/**
	 * Dispose.
	 */
	private void dispose() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					socket.shutdownOutput();
					socket.close();
					socket = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
