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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Web.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Web extends EObject {

	/**
	 * Gets the home page.
	 * @return the home page
	 */
	String getHomePage();

	/**
	 * Sets the home page.
	 * @param value the new home page
	 */
	void setHomePage(String value);

	/**
	 * Gets the folders.
	 * @return the folders
	 */
	EMap<String, Folder> getFolders();

} // Web
