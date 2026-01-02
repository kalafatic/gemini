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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TRACKER_TIMEOUT;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.TRANSPORT_BUFFER;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HTTP;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.UDP;

import java.net.DatagramPacket;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.builders.TrackerRequestBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.ASessionHandler;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.transporters.analyzers.TrackerResponseAnalyzer;
import eu.kalafatic.gemini.bt.client.net.controller.utils.NetworkUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerHandler extends ASessionHandler {

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The tracker session. */
	private TrackerSession trackerSession;

	/** The announce. */
	private String announce;

	/** The transport. */
	private StringBuffer transport;

	/** The url. */
	private URL url;

	/** The tracker response analyzer. */
	private TrackerResponseAnalyzer trackerResponseAnalyzer;

	/** The url connection. */
	private URLConnection urlConnection;

	/** The tracker request builder. */
	private TrackerRequestBuilder trackerRequestBuilder;

	/** The rating. */
	private int rating = 0;

	/**
	 * Instantiates a new tracker handler.
	 * @param swarmSession the swarm session
	 */
	public TrackerHandler(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
		this.extTorrent = (ExtTorrent) swarmSession.getTorrent();

		trackerRequestBuilder = new TrackerRequestBuilder(extTorrent);
		trackerResponseAnalyzer = new TrackerResponseAnalyzer(swarmSession);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run tracker handler.
	 * @param trackerSession the tracker session
	 */
	public void runTrackerHandler(TrackerSession trackerSession) {
		this.trackerSession = trackerSession;
		this.announce = trackerSession.getAnnounce();

		run();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			transport = trackerRequestBuilder.buildRequest();

			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.CONNECTING_TRACKERS);

			if (announce.startsWith(HTTP)) {
				processHTTPTask();
			} else if (announce.startsWith(UDP)) {

				if (announce.contains(HTTP)) {
					String preferHttp = getHTTP();
					processHTTPTask(preferHttp);
				} else {
					processUDPTask();
				}
			}
			rating++;
			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.TRACKERS_CONNECTED);

		} catch (Exception e) {
			rating--;
			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.CAN_NOT_CONNECT_TRACKER);
			Log.log(MODULE, e);
		} finally {
			NetworkUtils.changeRating(swarmSession, trackerSession, rating);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the http.
	 * @return the http
	 */
	private String getHTTP() {
		String[] split = announce.split("(?=http://)");
		String result = split[1];
		if (result.contains("?")) {
			result = result.substring(0, result.indexOf('?'));
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Process http task.
	 * @throws Exception the exception
	 */
	private void processHTTPTask() throws Exception {
		processHTTPTask(announce);
	}

	// ---------------------------------------------------------------

	/**
	 * Process http task.
	 * @param announce the announce
	 * @throws Exception the exception
	 */
	private void processHTTPTask(String announce) throws Exception {
		scrapeRequest();
		startSession();
		url = new URL(announce + transport);
		try {
			urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(TRACKER_TIMEOUT);
			urlConnection.setReadTimeout(TRACKER_TIMEOUT);
			urlConnection.setDoOutput(true);

			setSessionConnTimeout(trackerSession);

			trackerResponseAnalyzer.analyze(trackerSession, urlConnection);

			leaveSession(trackerSession);
		} finally {
			((HttpURLConnection) urlConnection).disconnect();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Scrape request.
	 */
	private void scrapeRequest() {

		// if (!trackerSession.isScraped()) {
		//
		// boolean scrape = Activator.getPreferences().getBoolean(
		// EBTClientPreferences.TRACKER_SCRAPE.getName(),
		// ((Boolean) EBTClientPreferences.TRACKER_SCRAPE.getDef()));
		//
		// if (scrape) {
		// int lastIndexOf = announce.lastIndexOf("/announce");
		// if (lastIndexOf > 0) {
		// String begin = announce.substring(0, lastIndexOf);
		// String end = announce.substring(lastIndexOf + 9);
		// announce = begin + "/scrape" + end;
		// }
		// trackerSession.setScraped(true);
		// }
		// }
	}

	// ---------------------------------------------------------------

	/**
	 * Process udp task.
	 * @throws Exception the exception
	 */
	private void processUDPTask() throws Exception {
		announce = announce.replaceFirst("udp", "http");

		url = new URL(announce);

		String host = url.getHost();
		int port = url.getPort();

		byte[] bytes = transport.toString().getBytes();

		ByteBuffer sendBuffer = ByteBuffer.wrap(bytes);

		ByteBuffer receiveBuffer = ByteBuffer.allocate(TRANSPORT_BUFFER);
		receiveBuffer.order(ByteOrder.BIG_ENDIAN);

		DatagramChannel channel = DatagramChannel.open();
		SocketAddress tracker = new InetSocketAddress(host, port);
		channel.send(sendBuffer, tracker);

		byte[] receiveData = new byte[TRANSPORT_BUFFER];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		try {
			channel.socket().setSoTimeout(TRACKER_TIMEOUT);
			channel.socket().receive(receivePacket);

			receiveBuffer.put(receivePacket.getData());
		} finally {
			channel.close();
		}
		int position = receiveBuffer.position();
		byte[] buffer = new byte[position];
		receiveBuffer.flip();

		receiveBuffer.get(buffer);

		trackerResponseAnalyzer.analyzeUDP(buffer);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tracker session.
	 * @return the tracker session
	 */
	public TrackerSession getTrackerSession() {
		return trackerSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the ext torrent.
	 * @return the ext torrent
	 */
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}
}
