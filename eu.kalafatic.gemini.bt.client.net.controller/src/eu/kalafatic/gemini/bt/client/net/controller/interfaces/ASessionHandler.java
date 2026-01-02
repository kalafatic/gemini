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

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;

/**
 * The Class class ASessionHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class ASessionHandler extends AControl implements ISessionHandler {

	/** The conn start. */
	private long connStart = 0;

	/**
	 * Start session.
	 */
	protected void startSession() {
		this.connStart = System.currentTimeMillis();
	}

	/**
	 * Sets the session conn timeout.
	 * @param session the new session conn timeout
	 */
	protected void setSessionConnTimeout(Session session) {
		session.setTimeout(System.currentTimeMillis() - connStart);
	}

	/**
	 * Leave session.
	 * @param session the session
	 */
	protected void leaveSession(Session session) {
		session.setDuration(session.getDuration() + System.currentTimeMillis() - this.connStart);
	}

	/**
	 * Gets the conn start.
	 * @return the conn start
	 */
	protected long getConnStart() {
		return this.connStart;
	}

}
