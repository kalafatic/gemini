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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Graph.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Graph extends EObject {

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

	/**
	 * Gets the coordinates.
	 * @return the coordinates
	 */
	EList getCoordinates();

	/**
	 * Checks if is active.
	 * @return true, if is active
	 */
	boolean isActive();

	/**
	 * Sets the active.
	 * @param value the new active
	 */
	void setActive(boolean value);

	/**
	 * Gets the name x.
	 * @return the name x
	 */
	String getNameX();

	/**
	 * Sets the name x.
	 * @param value the new name x
	 */
	void setNameX(String value);

	/**
	 * Gets the name y.
	 * @return the name y
	 */
	String getNameY();

	/**
	 * Sets the name y.
	 * @param value the new name y
	 */
	void setNameY(String value);

	/**
	 * Gets the type.
	 * @return the type
	 */
	String getType();

	/**
	 * Sets the type.
	 * @param value the new type
	 */
	void setType(String value);

	/**
	 * Gets the data.
	 * @return the data
	 */
	String getData();

	/**
	 * Sets the data.
	 * @param value the new data
	 */
	void setData(String value);

	/**
	 * Gets the series.
	 * @return the series
	 */
	EList getSeries();

	/**
	 * Gets the cat series.
	 * @return the cat series
	 */
	EList getCatSeries();

} // Graph
