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
 * The Interface interface Study.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Study extends EObject {

	/**
	 * Gets the graphs.
	 * @return the graphs
	 */
	EMap getGraphs();

	/**
	 * Gets the name.
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name.
	 * @param value the new name
	 */
	void setName(String value);

} // Study
