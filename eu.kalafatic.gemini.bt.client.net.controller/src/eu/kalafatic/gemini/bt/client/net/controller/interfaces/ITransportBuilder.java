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

import java.util.List;

import eu.kalafatic.gemini.bt.client.net.controller.model.TransportPiece;

/**
 * The Interface interface ITransportBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
interface ITransportBuilder {

	/**
	 * Gets the transport pieces.
	 * @return the transport pieces
	 * @throws Exception the exception
	 */
	public List<TransportPiece> getTransportPieces() throws Exception;

	/**
	 * Process unfinished pieces.
	 * @param force the force
	 * @throws Exception the exception
	 */
	void processUnfinishedPieces(boolean force) throws Exception;

	/**
	 * Gets the pieces from have.
	 * @return the pieces from have
	 * @throws Exception the exception
	 */
	void getPiecesFromHave() throws Exception;

	/**
	 * Select sequence pieces.
	 * @throws Exception the exception
	 */
	void selectSequencePieces() throws Exception;
}
