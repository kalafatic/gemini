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
package eu.kalafatic.gemini.stat.model.statistics;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Statistics.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Statistics extends EObject {

	/**
	 * Gets the stat map.
	 * @return the stat map
	 */
	EMap getStatMap();

} // Statistics
