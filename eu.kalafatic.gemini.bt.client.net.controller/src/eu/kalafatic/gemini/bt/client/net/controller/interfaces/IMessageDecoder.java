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

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;

/**
 * The Interface interface IMessageDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
interface IMessageDecoder {

	/**
	 * Decode message.
	 * @return the e return
	 */
	EReturn decodeMessage();

	/**
	 * Proces have.
	 * @throws Exception the exception
	 */
	void procesHave() throws Exception;

	/**
	 * Process bitfield.
	 * @throws Exception the exception
	 */
	void processBitfield() throws Exception;

	/**
	 * Process request.
	 * @throws Exception the exception
	 */
	void processRequest() throws Exception;

	/**
	 * Process piece.
	 * @throws Exception the exception
	 */
	void processPiece() throws Exception;

	/**
	 * Process cancel.
	 * @throws Exception the exception
	 */
	void processCancel() throws Exception;

	/**
	 * Process dht port.
	 * @throws Exception the exception
	 */
	void processDHTPort() throws Exception;

	/**
	 * Process handshake.
	 * @throws Exception the exception
	 */
	void processHandshake() throws Exception;
}
