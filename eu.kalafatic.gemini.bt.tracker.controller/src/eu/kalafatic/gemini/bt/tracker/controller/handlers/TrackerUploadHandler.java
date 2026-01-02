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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerUploadHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerUploadHandler implements Runnable {

	/** The session. */
	private ClientSession session;

	/** The swarm map. */
	private EMap<String, Session> swarmMap;

	/** The communication. */
	private Communication communication;

	/**
	 * Instantiates a new tracker upload handler.
	 * @param session the session
	 */
	public TrackerUploadHandler(ClientSession session) {
		this.session = session;
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
			createCommunication();

			if (!init()) {
				return;
			}
			setUpSession();
			processTask();

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @return true, if successful
	 */
	private boolean init() {

		String request = readRequestIntoString();

		communication.setRequest(request);

		if (parseRequest(request)) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the communication.
	 */
	private void createCommunication() {
		int messageQueueSize = Activator.getPreferences().getInt(ETrackerPreferences.MESSAGE_QUEUE_SIZE.getName(), (Integer) ETrackerPreferences.MESSAGE_QUEUE_SIZE.getDef());

		communication = TrackerFactory.eINSTANCE.createCommunication();
		communication.setDate(new Date());

		if (session.getCommunication().size() >= messageQueueSize) {
			session.getCommunication().remove(0);
		}
		session.getCommunication().add(communication);
	}

	// ---------------------------------------------------------------

	/**
	 * Process task.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processTask() throws IOException {

		swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

		String hexStringHash = session.getRequestMap().get(ETrackerRequestProtocol.INFO_HASH.getLiteral());

		TorrentSession torrentSession = (TorrentSession) swarmMap.get(hexStringHash);

		if (torrentSession == null) {

			torrentSession = TrackerFactory.eINSTANCE.createTorrentSession();
			torrentSession.setTorrentName(session.getRequestMap().get("torrentName"));
			torrentSession.setTorrentLen(Long.parseLong(session.getRequestMap().get("torrentLen").replaceAll("[^\\d]", "")));

			torrentSession.setPeerId(session.getRequestMap().get("peer_id"));

			DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
			torrentSession.setNote("Uploaded : " + formatter.format(new Date()));
			torrentSession.setInfoHash(hexStringHash);

			swarmMap.put(hexStringHash, torrentSession);
		}

		EMap<String, Session> clientMap = torrentSession.getClientMap();

		if (!clientMap.containsKey(session.getAddress())) {

			clientMap.put(session.getAddress(), session);

			int complete = torrentSession.getComplete();
			torrentSession.setComplete(complete + 1);

			session.setInfoHash(hexStringHash);

			session.setPeerId(session.getRequestMap().get("peer_id"));
		}

		TrackerModelManager.getInstance().doSave();

		sendResponse();
	}

	// ---------------------------------------------------------------

	/**
	 * Send response.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendResponse() throws IOException {
		Socket socket = session.getChannel().socket();

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), EEncoding.ISO_1.getLiteral()));

		if (socket.isConnected() && !socket.isOutputShutdown()) {
			wr.write("ok");
			wr.flush();
			wr.close();
		}
		communication.setResponse("ok");
		// SoundPlayer.getInstance().play("SendingMessage.wav");
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up session.
	 */
	private void setUpSession() {
		// String stringHash = session.getRequestMap().get("info_hash");

		String port = session.getRequestMap().get("port").replaceAll("[^\\d]", "");

		InetAddress inetAddress = session.getChannel().socket().getInetAddress();
		session.setAddress(inetAddress.getHostAddress() + ":" + port);

		session.setSeed(true);
		session.setNote("Upload");

		session.setListenPort(Integer.parseInt(port));
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the request.
	 * @param request the request
	 * @return true, if successful
	 */
	private boolean parseRequest(String request) {
		try {
			String[] dividedRequest = request.split("&");

			for (int i = 0; i < dividedRequest.length; i++) {

				if (!dividedRequest[i].isEmpty()) {
					String[] keyValue = dividedRequest[i].split("=");

					session.getRequestMap().put(keyValue[0], keyValue[1]);
				}
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
		StringBuffer request = new StringBuffer();
		try {
			Socket socket = session.getChannel().socket();

			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line;
			while ((line = rd.readLine()) != null) {
				request.append(line);
			}
			socket.shutdownInput();

		} catch (IOException e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return request.toString();
	}
}
