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
package eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants;

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating TrackerConstants objects.
 */
public interface TrackerConstantsFactory extends EFactory {

	/** The e instance. */
	TrackerConstantsFactory eINSTANCE = eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.impl.TrackerConstantsFactoryImpl.init();

	/**
	 * Gets the tracker constants package.
	 * @return the tracker constants package
	 */
	TrackerConstantsPackage getTrackerConstantsPackage();

} // TrackerConstantsFactory
