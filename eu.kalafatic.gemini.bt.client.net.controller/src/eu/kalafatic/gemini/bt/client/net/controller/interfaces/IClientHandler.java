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
package eu.kalafatic.gemini.bt.client.net.controller.interfaces;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.transporters.Transporter;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Interface interface IClientHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface IClientHandler {

	/**
	 * Process task.
	 * @throws Exception the exception
	 */
	void processTask() throws Exception;

	/**
	 * Process handshake.
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean processHandshake() throws Exception;

	/**
	 * Interested.
	 * @param extTorrent the ext torrent
	 * @param session the session
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean interested(ExtTorrent extTorrent, ClientSession session) throws Exception;

	/**
	 * Process interested.
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean processInterested() throws Exception;

	/**
	 * Process download.
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean processDownload() throws Exception;

	/**
	 * Process peers seeds.
	 * @param swarmSession the swarm session
	 * @param inc the inc
	 */
	void processPeersSeeds(SwarmSession swarmSession, int inc);

	/**
	 * Process peers seeds.
	 * @param additionalInfo the additional info
	 * @param inc the inc
	 */
	void processPeersSeeds(AdditionalInfo additionalInfo, int inc);

	/**
	 * Checks if is dwnld upld ratio acceptable.
	 * @return true, if is dwnld upld ratio acceptable
	 */
	boolean isDwnldUpldRatioAcceptable();

	/**
	 * Send have.
	 * @throws Exception the exception
	 */
	void sendHave() throws Exception;

	/**
	 * Send choke.
	 * @throws Exception the exception
	 */
	void sendChoke() throws Exception;

	/**
	 * Send unchoke.
	 * @throws Exception the exception
	 */
	void sendUnchoke() throws Exception;

	/**
	 * Process exception.
	 */
	void processException();

	/**
	 * Release session.
	 * @param eMap the e map
	 */
	void releaseSession(EMap<String, ClientSession> eMap);

	/**
	 * Gets the ext torrent.
	 * @return the ext torrent
	 */
	ExtTorrent getExtTorrent();

	/**
	 * Gets the session.
	 * @return the session
	 */
	ClientSession getSession();

	/**
	 * Gets the swarm session.
	 * @return the swarm session
	 */
	SwarmSession getSwarmSession();

	/**
	 * Gets the transporter.
	 * @return the transporter
	 */
	Transporter getTransporter();

}
