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
package eu.kalafatic.gemini.bt.tracker.model.tracker;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface TrackerModel.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface TrackerModel extends EObject {

	/**
	 * Gets the swarm map.
	 * @return the swarm map
	 */
	EMap<String, Session> getSwarmMap();

	/**
	 * Gets the tracker id.
	 * @return the tracker id
	 */
	String getTrackerID();

	/**
	 * Sets the tracker id.
	 * @param value the new tracker id
	 */
	void setTrackerID(String value);

	/**
	 * Gets the default numwant.
	 * @return the default numwant
	 */
	int getDefaultNumwant();

	/**
	 * Sets the default numwant.
	 * @param value the new default numwant
	 */
	void setDefaultNumwant(int value);

	/**
	 * Gets the default interval.
	 * @return the default interval
	 */
	int getDefaultInterval();

	/**
	 * Sets the default interval.
	 * @param value the new default interval
	 */
	void setDefaultInterval(int value);

} // TrackerModel
