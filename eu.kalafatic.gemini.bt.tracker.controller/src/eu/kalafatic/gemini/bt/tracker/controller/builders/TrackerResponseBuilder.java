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
package eu.kalafatic.gemini.bt.tracker.controller.builders;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerResponseBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerResponseBuilder {

	/** The torrent session. */
	private TorrentSession torrentSession;

	/** The request map. */
	private Map<String, String> requestMap;

	/** The compact. */
	private int compact = 1;

	/** The numwant. */
	private int numwant = TrackerModelManager.getInstance().getTrackerModel().getDefaultNumwant();

	/**
	 * Instantiates a new tracker response builder.
	 * @param torrentSession the torrent session
	 * @param requestMap the request map
	 */
	public TrackerResponseBuilder(TorrentSession torrentSession, Map<String, String> requestMap) {

		this.torrentSession = torrentSession;
		this.requestMap = requestMap;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Builds the response.
	 * @return the map
	 */
	public synchronized Map<String, Object> buildResponse() {

		Map<String, Object> responseBuilder = new LinkedHashMap<String, Object>();

		responseBuilder.put(ETrackerResponseProtocol.INTERVAL.getLiteral(), TrackerModelManager.getInstance().getTrackerModel().getDefaultInterval());
		responseBuilder.put(ETrackerResponseProtocol.MIN_INTERVAL.getLiteral(), TrackerModelManager.getInstance().getTrackerModel().getDefaultInterval());

		// responseBuilder.put(ETrackerResponseProtocol.TRACKER_ID.getLiteral(),
		// ConfigModelManager.getInstance().getConfigModel().getAppID());

		responseBuilder.put(ETrackerResponseProtocol.COMPLETE.getLiteral(), torrentSession.getComplete());

		responseBuilder.put(ETrackerResponseProtocol.INCOMPLETE.getLiteral(), torrentSession.getIncomplete());

		responseBuilder.put(ETrackerRequestProtocol.DOWNLOADED.getLiteral(), torrentSession.getDownloaded());

		processPeers(responseBuilder);

		return responseBuilder;
	}

	// ---------------------------------------------------------------

	/**
	 * Process peers.
	 * @param responseBuilder the response builder
	 */
	private void processPeers(Map<String, Object> responseBuilder) {
		if (requestMap.containsKey(ETrackerRequestProtocol.COMPACT.getLiteral())) {

			compact = Integer.parseInt(requestMap.get(ETrackerRequestProtocol.COMPACT.getLiteral()));
		}
		if (requestMap.containsKey(ETrackerRequestProtocol.NUMWANT.getLiteral())) {

			numwant = Integer.parseInt(requestMap.get(ETrackerRequestProtocol.NUMWANT.getLiteral()));

			if (numwant == 0) {
				numwant = TrackerModelManager.getInstance().getTrackerModel().getDefaultNumwant();
			}
		}

		if (compact == 1) {
			createBinaryPeers(responseBuilder, numwant);
		} else {
			createDictionaryPeers(responseBuilder, numwant);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the binary peers.
	 * @param map the map
	 * @param numwant the numwant
	 */
	private void createBinaryPeers(Map<String, Object> map, int numwant) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int clients = 0;

			Iterator<Session> iterator = torrentSession.getClientMap().values().iterator();
			while (iterator.hasNext() && (clients++) <= numwant) {

				ClientSession clientSession = (ClientSession) iterator.next();

				String[] split = clientSession.getAddress().split(":");

				InetAddress inetAddress = InetAddress.getByName(split[0]);

				byte[] addressArray = inetAddress.getAddress();

				baos.write(addressArray);

				int intPort = Integer.parseInt(split[1]);
				byte[] portArray = new byte[] { (byte) (intPort >>> 8), (byte) intPort };

				baos.write(portArray);
			}
			byte[] result = baos.toByteArray();
			map.put(ETrackerResponseProtocol.PEERS.getLiteral(), result);
			baos.close();

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the dictionary peers.
	 * @param map the map
	 * @param numwant the numwant
	 */
	private void createDictionaryPeers(Map<String, Object> map, int numwant) {

		List<Object> dictionary = new ArrayList<Object>();
		int clients = 0;

		Iterator<Session> iterator = torrentSession.getClientMap().values().iterator();
		while (iterator.hasNext() && (clients++) <= numwant) {
			ClientSession clientSession = (ClientSession) iterator.next();

			Map<String, Object> peer = new HashMap<String, Object>();

			if (requestMap.containsKey(ETrackerRequestProtocol.NO_PEER_ID.getLiteral())) {

				if (compact == 0) {
					String peerId = clientSession.getRequestMap().get(ETrackerRequestProtocol.PEER_ID.getLiteral());
					peer.put("peer_id", peerId);
				} else {
					String noPeerId = requestMap.get(ETrackerRequestProtocol.NO_PEER_ID.getLiteral());

					if (Integer.parseInt(noPeerId) == 0) {
						String peerId = clientSession.getRequestMap().get(ETrackerRequestProtocol.PEER_ID.getLiteral());

						peer.put(ETrackerRequestProtocol.PEER_ID.getLiteral(), peerId);
					}
				}
			}

			peer.put(ETrackerRequestProtocol.IP.getLiteral(), clientSession.getAddress().split(":")[0]);

			peer.put(ETrackerRequestProtocol.PORT.getLiteral(), clientSession.getListenPort());

			dictionary.add(peer);
		}
		map.put(ETrackerResponseProtocol.PEERS.getLiteral(), dictionary);
	}
}
