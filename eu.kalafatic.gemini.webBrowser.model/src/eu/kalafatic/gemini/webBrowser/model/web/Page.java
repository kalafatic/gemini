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
package eu.kalafatic.gemini.webBrowser.model.web;

import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Page.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Page extends EObject {

	/**
	 * Gets the address.
	 * @return the address
	 */
	String getAddress();

	/**
	 * Sets the address.
	 * @param value the new address
	 */
	void setAddress(String value);

	/**
	 * Gets the parent.
	 * @return the parent
	 */
	Folder getParent();

	/**
	 * Sets the parent.
	 * @param value the new parent
	 */
	void setParent(Folder value);

} // Page
