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
package eu.kalafatic.gemini.bt.client.net.controller.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscManager;
import eu.kalafatic.gemini.bt.client.net.controller.handlers.UploadHandler;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;

/**
 * The Class class SwarmThreadsManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmThreadsManager {

	/** The pooled threads map. */
	private final Map<String, PooledThreads> pooledThreadsMap = new HashMap<String, PooledThreads>();

	/** The instance. */
	private volatile static SwarmThreadsManager INSTANCE;

	/**
	 * Gets the single instance of SwarmThreadsManager.
	 * @return single instance of SwarmThreadsManager
	 */
	public static SwarmThreadsManager getInstance() {
		if (INSTANCE == null) {
			synchronized (SwarmThreadsManager.class) {
				INSTANCE = new SwarmThreadsManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Load swarm.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 */
	public void loadSwarm(ExtTorrent extTorrent, SwarmSession swarmSession) {

		if (!swarmSession.isCreatedOnDisc()) {
			boolean isCreated = DiscManager.getInstance().createTorrentStructureOnDisc(extTorrent);
			swarmSession.setCreatedOnDisc(isCreated);
		}

		extTorrent.getAdditionalInfo().setDownloaders(0);

		if (swarmSession.getTrackers().isEmpty()) {
			for (String announce : extTorrent.getAnnounceList()) {

				TrackerSession trackerSession = createTrackerSession(announce);

				swarmSession.getTrackers().put(announce, trackerSession);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker session.
	 * @param announce the announce
	 * @return the tracker session
	 */
	public TrackerSession createTrackerSession(String announce) {
		TrackerSession trackerSession = ClientNetworkFactory.eINSTANCE.createTrackerSession();
		trackerSession.setAnnounce(announce);
		trackerSession.setState(EViewsMessages.READY);
		trackerSession.setClients(new HashSet<String>());
		return trackerSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Run upld client.
	 * @param session the session
	 * @throws Exception the exception
	 */
	public void runUpldClient(UpldSession session) throws Exception {
		UploadHandler uploadHandler = new UploadHandler(session);

		if (uploadHandler.init()) {

			final String key = uploadHandler.getExtTorrent().getName();

			if (!pooledThreadsMap.containsKey(key)) {
				NetworkModelManager.getInstance().createPool(uploadHandler.getSwarmSession());
			}
			pooledThreadsMap.get(key).getUploadExecutor().execute(uploadHandler);
		} else {
			session = null;
			uploadHandler = null;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the pooled threads map.
	 * @return the pooled threads map
	 */
	public Map<String, PooledThreads> getPooledThreadsMap() {
		return pooledThreadsMap;
	}
}
