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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface RC.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface RC extends EObject {

	/**
	 * Gets the session map.
	 * @return the session map
	 */
	EMap<String, RCSession> getSessionMap();

	/**
	 * Gets the tree.
	 * @return the tree
	 */
	EMap<String, Folder> getTree();

} // RC
