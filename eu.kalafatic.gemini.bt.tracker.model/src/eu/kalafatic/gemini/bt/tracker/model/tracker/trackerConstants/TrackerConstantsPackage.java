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

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * The Interface interface TrackerConstantsPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface TrackerConstantsPackage extends EPackage {

	/** The e name. */
	String eNAME = "trackerConstants";

	/** The e n s_ uri. */
	String eNS_URI = "http:///trackerConstants.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "trackerConstants";

	/** The e instance. */
	TrackerConstantsPackage eINSTANCE = eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.impl.TrackerConstantsPackageImpl.init();

	/** The EVIE w_ messages. */
	int EVIEW_MESSAGES = 0;

	/** The ETRACKE r_ reques t_ protocol. */
	int ETRACKER_REQUEST_PROTOCOL = 1;

	/** The ETRACKE r_ respons e_ protocol. */
	int ETRACKER_RESPONSE_PROTOCOL = 2;

	/** The ETRACKE r_ respons e_ code. */
	int ETRACKER_RESPONSE_CODE = 3;

	/**
	 * Gets the e view messages.
	 * @return the e view messages
	 */
	EEnum getEViewMessages();

	/**
	 * Gets the e tracker request protocol.
	 * @return the e tracker request protocol
	 */
	EEnum getETrackerRequestProtocol();

	/**
	 * Gets the e tracker response protocol.
	 * @return the e tracker response protocol
	 */
	EEnum getETrackerResponseProtocol();

	/**
	 * Gets the e tracker response code.
	 * @return the e tracker response code
	 */
	EEnum getETrackerResponseCode();

	/**
	 * Gets the tracker constants factory.
	 * @return the tracker constants factory
	 */
	TrackerConstantsFactory getTrackerConstantsFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The EVIE w_ messages. */
		EEnum EVIEW_MESSAGES = eINSTANCE.getEViewMessages();

		/** The ETRACKE r_ reques t_ protocol. */
		EEnum ETRACKER_REQUEST_PROTOCOL = eINSTANCE.getETrackerRequestProtocol();

		/** The ETRACKE r_ respons e_ protocol. */
		EEnum ETRACKER_RESPONSE_PROTOCOL = eINSTANCE.getETrackerResponseProtocol();

		/** The ETRACKE r_ respons e_ code. */
		EEnum ETRACKER_RESPONSE_CODE = eINSTANCE.getETrackerResponseCode();

	}

} // TrackerConstantsPackage
