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

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating Web objects.
 */
public interface WebFactory extends EFactory {

	/** The e instance. */
	WebFactory eINSTANCE = eu.kalafatic.gemini.webBrowser.model.web.impl.WebFactoryImpl.init();

	/**
	 * Creates a new Web object.
	 * @return the web
	 */
	Web createWeb();

	/**
	 * Creates a new Web object.
	 * @return the page
	 */
	Page createPage();

	/**
	 * Creates a new Web object.
	 * @return the folder
	 */
	Folder createFolder();

	/**
	 * Gets the web package.
	 * @return the web package
	 */
	WebPackage getWebPackage();

} // WebFactory
