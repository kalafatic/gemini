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
package eu.kalafatic.gemini.bt.client.net.controller.transporters.analyzers;

import java.net.URLConnection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.decoders.TrackerResponseDecoder;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.ConvertUtils;

/**
 * The Class class TrackerResponseAnalyzer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerResponseAnalyzer {

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The tracker session. */
	private TrackerSession trackerSession;

	/** The tracker response. */
	private TrackerResponse trackerResponse;

	/** The response decoder. */
	private TrackerResponseDecoder responseDecoder;

	/**
	 * Instantiates a new tracker response analyzer.
	 * @param swarmSession the swarm session
	 */
	public TrackerResponseAnalyzer(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
		this.extTorrent = (ExtTorrent) swarmSession.getTorrent();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Analyze.
	 * @param trackerSession the tracker session
	 * @param urlConnection the url connection
	 * @throws Exception the exception
	 */
	public void analyze(TrackerSession trackerSession, URLConnection urlConnection) throws Exception {
		this.trackerSession = trackerSession;

		responseDecoder = new TrackerResponseDecoder(urlConnection.getInputStream());
		byte[] response = responseDecoder.readResponse();

		processDecoding(response);
	}

	// ---------------------------------------------------------------

	/**
	 * Analyze udp.
	 * @param buffer the buffer
	 * @throws Exception the exception
	 */
	public void analyzeUDP(byte[] buffer) throws Exception {
		responseDecoder = new TrackerResponseDecoder();

		processDecoding(buffer);
	}

	// ---------------------------------------------------------------

	/**
	 * Process decoding.
	 * @param response the response
	 * @throws Exception the exception
	 */
	private void processDecoding(byte[] response) throws Exception {
		Map<?, ?> responseMap = responseDecoder.decode(extTorrent, response);

		if (responseMap == null) {
			throw new Exception();
		}
		load(responseMap);
	}

	// ---------------------------------------------------------------

	/**
	 * Load.
	 * @param decodeMap the decode map
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public void load(Map<?, ?> decodeMap) throws Exception {

		trackerResponse = ClientNetworkFactoryImpl.eINSTANCE.createTrackerResponse();

		/**
		 * d14:failure reason136:Invalid PID (private announce): 98e376a7c2130b86a8d7fceb10766adb. Please sign up and redownload torrent from
		 * http://www.torrent-god.com.e
		 */

		String failureReason = (String) decodeMap.get(ETrackerResponseProtocol.FAILURE_REASON.getLiteral());
		trackerResponse.setFailureReason((failureReason == null) ? "" : failureReason);

		Long interval = (Long) decodeMap.get(ETrackerResponseProtocol.INTERVAL.getLiteral());
		trackerResponse.setInterval((interval == null) ? -1 : interval);

		Long minInterval = (Long) decodeMap.get(ETrackerResponseProtocol.MIN_INTERVAL.getLiteral());
		trackerResponse.setMinInterval((minInterval == null) ? -1 : minInterval);

		String trackerId = (String) decodeMap.get(ETrackerResponseProtocol.TRACKER_ID.getLiteral());
		trackerResponse.setTrackerId((trackerId == null) ? "" : trackerId);

		// problems with class cast - incoming Long
		Object object = decodeMap.get(ETrackerResponseProtocol.COMPLETE.getLiteral());
		Integer complete = ConvertUtils.getInteger(object);
		trackerResponse.setComplete((complete == null) ? -1 : complete);

		// problems with class cast - incoming Long
		object = decodeMap.get(ETrackerResponseProtocol.INCOMPLETE.getLiteral());
		Integer incomplete = ConvertUtils.getInteger(object);
		trackerResponse.setIncomplete((incomplete == null) ? -1 : incomplete);

		Set<String> clients = (Set<String>) decodeMap.get("peers");

		trackerSession.setResponse(trackerResponse);

		if (clients == null) {
			trackerSession.setState(EViewsMessages.SUSPENDED);
		} else {
			// TODO
			if (swarmSession.getClients() == null) {
				swarmSession.setClients(new HashSet<String>());
			}
			swarmSession.getClients().addAll(clients);

			if (trackerSession.getClients() == null) {
				trackerSession.setClients(new HashSet<String>());
			}
			trackerSession.getClients().addAll(clients);
		}
	}
}
