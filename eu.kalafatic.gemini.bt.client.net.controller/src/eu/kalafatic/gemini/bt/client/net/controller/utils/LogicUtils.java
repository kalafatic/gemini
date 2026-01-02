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
package eu.kalafatic.gemini.bt.client.net.controller.utils;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.SWARM_RATING_COMPARATOR_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;

/**
 * The Class class LogicUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LogicUtils {

	/** The instance. */
	private volatile static LogicUtils INSTANCE;

	/**
	 * Gets the single instance of LogicUtils.
	 * @return single instance of LogicUtils
	 */
	public static LogicUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (LogicUtils.class) {
				INSTANCE = new LogicUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the rating position.
	 * @param swarmSession the swarm session
	 * @return the rating position
	 */
	@SuppressWarnings("unchecked")
	public int getRatingPosition(SwarmSession swarmSession) {
		List<SwarmSession> sessions = new ArrayList<SwarmSession>((Collection<? extends SwarmSession>) NetworkModelManager.getInstance().getClientNetwork().getSwarmMap().values());

		Collections.sort(sessions, SWARM_RATING_COMPARATOR_2);

		int position = 0;
		for (SwarmSession session : sessions) {
			if (session.equals(swarmSession)) {
				return position;
			}
			++position;
		}
		return EReturn.EXIT_VALUE;
	}
}
