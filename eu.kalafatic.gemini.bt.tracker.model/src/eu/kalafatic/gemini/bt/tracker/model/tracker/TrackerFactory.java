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

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating Tracker objects.
 */
public interface TrackerFactory extends EFactory {

	/** The e instance. */
	TrackerFactory eINSTANCE = eu.kalafatic.gemini.bt.tracker.model.tracker.impl.TrackerFactoryImpl.init();

	/**
	 * Creates a new Tracker object.
	 * @return the tracker model
	 */
	TrackerModel createTrackerModel();

	/**
	 * Creates a new Tracker object.
	 * @return the torrent session
	 */
	TorrentSession createTorrentSession();

	/**
	 * Creates a new Tracker object.
	 * @return the client session
	 */
	ClientSession createClientSession();

	/**
	 * Creates a new Tracker object.
	 * @return the session
	 */
	Session createSession();

	/**
	 * Creates a new Tracker object.
	 * @return the communication
	 */
	Communication createCommunication();

	/**
	 * Gets the tracker package.
	 * @return the tracker package
	 */
	TrackerPackage getTrackerPackage();

} // TrackerFactory
