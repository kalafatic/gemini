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

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Communication.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Communication extends EObject {

	/**
	 * Gets the date.
	 * @return the date
	 */
	Date getDate();

	/**
	 * Sets the date.
	 * @param value the new date
	 */
	void setDate(Date value);

	/**
	 * Gets the request.
	 * @return the request
	 */
	String getRequest();

	/**
	 * Sets the request.
	 * @param value the new request
	 */
	void setRequest(String value);

	/**
	 * Gets the response.
	 * @return the response
	 */
	String getResponse();

	/**
	 * Sets the response.
	 * @param value the new response
	 */
	void setResponse(String value);

} // Communication
