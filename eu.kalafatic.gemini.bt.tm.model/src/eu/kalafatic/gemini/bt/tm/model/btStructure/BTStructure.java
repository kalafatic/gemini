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
package eu.kalafatic.gemini.bt.tm.model.btStructure;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Interface interface BTStructure.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface BTStructure extends EObject {

	/**
	 * Gets the new torrents.
	 * @return the new torrents
	 */
	EMap<String, ExtTorrent> getNewTorrents();

	/**
	 * Gets the trackers map.
	 * @return the trackers map
	 */
	EMap<String, Tracker> getTrackersMap();

	/**
	 * Gets the tree objects.
	 * @return the tree objects
	 */
	EMap<String, FileTreeObject> getTreeObjects();

} // BTStructure
