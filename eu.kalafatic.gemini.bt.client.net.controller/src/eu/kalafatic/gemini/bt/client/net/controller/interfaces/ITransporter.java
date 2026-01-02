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

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;

/**
 * The Interface interface ITransporter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
interface ITransporter {

	/**
	 * Inits the.
	 * @throws Exception the exception
	 */
	void init() throws Exception;

	/**
	 * Receive int.
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	byte[] receiveInt() throws Exception;

	/**
	 * Receive byte.
	 * @return the byte
	 * @throws Exception the exception
	 */
	byte receiveByte() throws Exception;

	/**
	 * Send.
	 * @param data the data
	 * @throws Exception the exception
	 */
	void send(byte[] data) throws Exception;

	/**
	 * Send.
	 * @param data the data
	 * @throws Exception the exception
	 */
	void send(byte[]... data) throws Exception;

	/**
	 * Receive.
	 * @param receiveLength the receive length
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	byte[] receive(int receiveLength) throws Exception;

	/**
	 * Resolve up down size.
	 * @param size the size
	 * @param send the send
	 * @throws Exception the exception
	 */
	void resolveUpDownSize(int size, boolean send) throws Exception;

	/**
	 * Sets the swarm session.
	 * @param swarmSession the new swarm session
	 */
	void setSwarmSession(SwarmSession swarmSession);
}
