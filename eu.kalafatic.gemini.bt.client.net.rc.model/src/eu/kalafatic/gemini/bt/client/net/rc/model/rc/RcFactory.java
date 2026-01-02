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

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating Rc objects.
 */
public interface RcFactory extends EFactory {

	/** The e instance. */
	RcFactory eINSTANCE = eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl.RcFactoryImpl.init();

	/**
	 * Creates a new Rc object.
	 * @return the RC
	 */
	RC createRC();

	/**
	 * Creates a new Rc object.
	 * @return the RC session
	 */
	RCSession createRCSession();

	/**
	 * Creates a new Rc object.
	 * @return the folder
	 */
	Folder createFolder();

	/**
	 * Creates a new Rc object.
	 * @return the page
	 */
	Page createPage();

	/**
	 * Gets the rc package.
	 * @return the rc package
	 */
	RcPackage getRcPackage();

} // RcFactory
