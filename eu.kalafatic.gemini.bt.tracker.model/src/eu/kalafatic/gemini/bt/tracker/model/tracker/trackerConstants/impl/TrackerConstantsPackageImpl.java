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
package eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.impl;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;
import eu.kalafatic.gemini.bt.tracker.model.tracker.impl.TrackerPackageImpl;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseCode;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage;

/**
 * The Class class TrackerConstantsPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerConstantsPackageImpl extends EPackageImpl implements TrackerConstantsPackage {

	/** The e view messages e enum. */
	private EEnum eViewMessagesEEnum = null;

	/** The e tracker request protocol e enum. */
	private EEnum eTrackerRequestProtocolEEnum = null;

	/** The e tracker response protocol e enum. */
	private EEnum eTrackerResponseProtocolEEnum = null;

	/** The e tracker response code e enum. */
	private EEnum eTrackerResponseCodeEEnum = null;

	/**
	 * Instantiates a new tracker constants package impl.
	 */
	private TrackerConstantsPackageImpl() {
		super(eNS_URI, TrackerConstantsFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the tracker constants package
	 */
	public static TrackerConstantsPackage init() {
		if (isInited)
			return (TrackerConstantsPackage) EPackage.Registry.INSTANCE.getEPackage(TrackerConstantsPackage.eNS_URI);

		// Obtain or create and register package
		TrackerConstantsPackageImpl theTrackerConstantsPackage = (TrackerConstantsPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TrackerConstantsPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new TrackerConstantsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		TrackerPackageImpl theTrackerPackage = (TrackerPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TrackerPackage.eNS_URI) instanceof TrackerPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(TrackerPackage.eNS_URI) : TrackerPackage.eINSTANCE);

		// Create package meta-data objects
		theTrackerConstantsPackage.createPackageContents();
		theTrackerPackage.createPackageContents();

		// Initialize created meta-data
		theTrackerConstantsPackage.initializePackageContents();
		theTrackerPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTrackerConstantsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TrackerConstantsPackage.eNS_URI, theTrackerConstantsPackage);
		return theTrackerConstantsPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage#getEViewMessages()
	 */
	public EEnum getEViewMessages() {
		return eViewMessagesEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage#getETrackerRequestProtocol()
	 */
	public EEnum getETrackerRequestProtocol() {
		return eTrackerRequestProtocolEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage#getETrackerResponseProtocol()
	 */
	public EEnum getETrackerResponseProtocol() {
		return eTrackerResponseProtocolEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage#getETrackerResponseCode()
	 */
	public EEnum getETrackerResponseCode() {
		return eTrackerResponseCodeEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage#getTrackerConstantsFactory()
	 */
	public TrackerConstantsFactory getTrackerConstantsFactory() {
		return (TrackerConstantsFactory) getEFactoryInstance();
	}

	/** The is created. */
	private boolean isCreated = false;

	/**
	 * Creates the package contents.
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create enums
		eViewMessagesEEnum = createEEnum(EVIEW_MESSAGES);
		eTrackerRequestProtocolEEnum = createEEnum(ETRACKER_REQUEST_PROTOCOL);
		eTrackerResponseProtocolEEnum = createEEnum(ETRACKER_RESPONSE_PROTOCOL);
		eTrackerResponseCodeEEnum = createEEnum(ETRACKER_RESPONSE_CODE);
	}

	/** The is initialized. */
	private boolean isInitialized = false;

	/**
	 * Initialize package contents.
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Initialize enums and add enum literals
		initEEnum(eViewMessagesEEnum, EViewMessages.class, "EViewMessages");
		addEEnumLiteral(eViewMessagesEEnum, EViewMessages.READY);
		addEEnumLiteral(eViewMessagesEEnum, EViewMessages.PAUSED);
		addEEnumLiteral(eViewMessagesEEnum, EViewMessages.STOPPED);
		addEEnumLiteral(eViewMessagesEEnum, EViewMessages.FINISHED);
		addEEnumLiteral(eViewMessagesEEnum, EViewMessages.WAITING);

		initEEnum(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.class, "ETrackerRequestProtocol");
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.ANNOUNCE);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.INFO_HASH);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.PEER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.PORT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.UPLOADED);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.DOWNLOADED);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.LEFT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.COMPACT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.NO_PEER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.IP);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.NUMWANT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.KEY);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.TRACKER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.EVENT);

		initEEnum(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.class, "ETrackerResponseProtocol");
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.FAILURE_REASON);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.WARNING_MESSAGE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.INTERVAL);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.MIN_INTERVAL);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.TRACKER_ID);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.COMPLETE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.INCOMPLETE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PEERS);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PEER_ID);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.IP);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PORT);

		initEEnum(eTrackerResponseCodeEEnum, ETrackerResponseCode.class, "ETrackerResponseCode");
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_REQUEST);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_HASH);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_PEER_ID);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_PORT);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_HASH);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_PEER_ID);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_NUMWANT);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.HASH_NOT_FOUND);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.EVENTLESS_REQUEST);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.GENERIC_ERROR);
	}

} // TrackerConstantsPackageImpl
