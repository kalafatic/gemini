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
package eu.kalafatic.gemini.bt.tm.model.btStructureConstants;

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating BtStructureConstants objects.
 */
public interface BtStructureConstantsFactory extends EFactory {

	/** The e instance. */
	BtStructureConstantsFactory eINSTANCE = eu.kalafatic.gemini.bt.tm.model.btStructureConstants.impl.BtStructureConstantsFactoryImpl.init();

	/**
	 * Gets the bt structure constants package.
	 * @return the bt structure constants package
	 */
	BtStructureConstantsPackage getBtStructureConstantsPackage();

} // BtStructureConstantsFactory
