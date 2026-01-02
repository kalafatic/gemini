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

/**
 * The Interface interface Folder.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Folder extends Page {

	/**
	 * Gets the folders.
	 * @return the folders
	 */
	EMap<String, Folder> getFolders();

	/**
	 * Gets the pages.
	 * @return the pages
	 */
	EMap<String, Page> getPages();

} // Folder
