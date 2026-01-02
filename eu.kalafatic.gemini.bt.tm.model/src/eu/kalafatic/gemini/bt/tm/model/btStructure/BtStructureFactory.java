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
package eu.kalafatic.gemini.bt.tm.model.btStructure;

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating BtStructure objects.
 */
public interface BtStructureFactory extends EFactory {

	/** The e instance. */
	BtStructureFactory eINSTANCE = eu.kalafatic.gemini.bt.tm.model.btStructure.impl.BtStructureFactoryImpl.init();

	/**
	 * Creates a new BtStructure object.
	 * @return the BT structure
	 */
	BTStructure createBTStructure();

	/**
	 * Creates a new BtStructure object.
	 * @return the tracker
	 */
	Tracker createTracker();

	/**
	 * Creates a new BtStructure object.
	 * @return the file tree object
	 */
	FileTreeObject createFileTreeObject();

	/**
	 * Gets the bt structure package.
	 * @return the bt structure package
	 */
	BtStructurePackage getBtStructurePackage();

} // BtStructureFactory
