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
package eu.kalafatic.gemini.bt.tracker.model.tracker.impl;

import java.nio.channels.SocketChannel;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.impl.TrackerConstantsPackageImpl;

/**
 * The Class class TrackerPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerPackageImpl extends EPackageImpl implements TrackerPackage {

	/** The tracker model e class. */
	private EClass trackerModelEClass = null;

	/** The torrent session e class. */
	private EClass torrentSessionEClass = null;

	/** The client session e class. */
	private EClass clientSessionEClass = null;

	/** The session e class. */
	private EClass sessionEClass = null;

	/** The string to session map entry e class. */
	private EClass stringToSessionMapEntryEClass = null;

	/** The string to string map entry e class. */
	private EClass stringToStringMapEntryEClass = null;

	/** The communication e class. */
	private EClass communicationEClass = null;

	/** The channel e data type. */
	private EDataType channelEDataType = null;

	/**
	 * Instantiates a new tracker package impl.
	 */
	private TrackerPackageImpl() {
		super(eNS_URI, TrackerFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the tracker package
	 */
	public static TrackerPackage init() {
		if (isInited)
			return (TrackerPackage) EPackage.Registry.INSTANCE.getEPackage(TrackerPackage.eNS_URI);

		// Obtain or create and register package
		TrackerPackageImpl theTrackerPackage = (TrackerPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TrackerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new TrackerPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		TrackerConstantsPackageImpl theTrackerConstantsPackage = (TrackerConstantsPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TrackerConstantsPackage.eNS_URI) instanceof TrackerConstantsPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(TrackerConstantsPackage.eNS_URI) : TrackerConstantsPackage.eINSTANCE);

		// Create package meta-data objects
		theTrackerPackage.createPackageContents();
		theTrackerConstantsPackage.createPackageContents();

		// Initialize created meta-data
		theTrackerPackage.initializePackageContents();
		theTrackerConstantsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTrackerPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TrackerPackage.eNS_URI, theTrackerPackage);
		return theTrackerPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerModel()
	 */
	public EClass getTrackerModel() {
		return trackerModelEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerModel_SwarmMap()
	 */
	public EReference getTrackerModel_SwarmMap() {
		return (EReference) trackerModelEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerModel_TrackerID()
	 */
	public EAttribute getTrackerModel_TrackerID() {
		return (EAttribute) trackerModelEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerModel_DefaultNumwant()
	 */
	public EAttribute getTrackerModel_DefaultNumwant() {
		return (EAttribute) trackerModelEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerModel_DefaultInterval()
	 */
	public EAttribute getTrackerModel_DefaultInterval() {
		return (EAttribute) trackerModelEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession()
	 */
	public EClass getTorrentSession() {
		return torrentSessionEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_TorrentName()
	 */
	public EAttribute getTorrentSession_TorrentName() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_Complete()
	 */
	public EAttribute getTorrentSession_Complete() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_Incomplete()
	 */
	public EAttribute getTorrentSession_Incomplete() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_TorrentLen()
	 */
	public EAttribute getTorrentSession_TorrentLen() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_Downloaded()
	 */
	public EAttribute getTorrentSession_Downloaded() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(4);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_ClientMap()
	 */
	public EReference getTorrentSession_ClientMap() {
		return (EReference) torrentSessionEClass.getEStructuralFeatures().get(5);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTorrentSession_TorrentPath()
	 */
	public EAttribute getTorrentSession_TorrentPath() {
		return (EAttribute) torrentSessionEClass.getEStructuralFeatures().get(6);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getClientSession()
	 */
	public EClass getClientSession() {
		return clientSessionEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getClientSession_Seed()
	 */
	public EAttribute getClientSession_Seed() {
		return (EAttribute) clientSessionEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getClientSession_LastConnection()
	 */
	public EAttribute getClientSession_LastConnection() {
		return (EAttribute) clientSessionEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession()
	 */
	public EClass getSession() {
		return sessionEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_Address()
	 */
	public EAttribute getSession_Address() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_Channel()
	 */
	public EAttribute getSession_Channel() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_RequestMap()
	 */
	public EReference getSession_RequestMap() {
		return (EReference) sessionEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_Communication()
	 */
	public EReference getSession_Communication() {
		return (EReference) sessionEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_SessionState()
	 */
	public EAttribute getSession_SessionState() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(4);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_PeerId()
	 */
	public EAttribute getSession_PeerId() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(5);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_Note()
	 */
	public EAttribute getSession_Note() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(6);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_InfoHash()
	 */
	public EAttribute getSession_InfoHash() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(7);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_ListenPort()
	 */
	public EAttribute getSession_ListenPort() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(8);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getSession_Torrent()
	 */
	public EAttribute getSession_Torrent() {
		return (EAttribute) sessionEClass.getEStructuralFeatures().get(9);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToSessionMapEntry()
	 */
	public EClass getStringToSessionMapEntry() {
		return stringToSessionMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToSessionMapEntry_Key()
	 */
	public EAttribute getStringToSessionMapEntry_Key() {
		return (EAttribute) stringToSessionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToSessionMapEntry_Value()
	 */
	public EReference getStringToSessionMapEntry_Value() {
		return (EReference) stringToSessionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToStringMapEntry()
	 */
	public EClass getStringToStringMapEntry() {
		return stringToStringMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToStringMapEntry_Key()
	 */
	public EAttribute getStringToStringMapEntry_Key() {
		return (EAttribute) stringToStringMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getStringToStringMapEntry_Value()
	 */
	public EAttribute getStringToStringMapEntry_Value() {
		return (EAttribute) stringToStringMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getCommunication()
	 */
	public EClass getCommunication() {
		return communicationEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getCommunication_Date()
	 */
	public EAttribute getCommunication_Date() {
		return (EAttribute) communicationEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getCommunication_Request()
	 */
	public EAttribute getCommunication_Request() {
		return (EAttribute) communicationEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getCommunication_Response()
	 */
	public EAttribute getCommunication_Response() {
		return (EAttribute) communicationEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getChannel()
	 */
	public EDataType getChannel() {
		return channelEDataType;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage#getTrackerFactory()
	 */
	public TrackerFactory getTrackerFactory() {
		return (TrackerFactory) getEFactoryInstance();
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

		// Create classes and their features
		trackerModelEClass = createEClass(TRACKER_MODEL);
		createEReference(trackerModelEClass, TRACKER_MODEL__SWARM_MAP);
		createEAttribute(trackerModelEClass, TRACKER_MODEL__TRACKER_ID);
		createEAttribute(trackerModelEClass, TRACKER_MODEL__DEFAULT_NUMWANT);
		createEAttribute(trackerModelEClass, TRACKER_MODEL__DEFAULT_INTERVAL);

		torrentSessionEClass = createEClass(TORRENT_SESSION);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__TORRENT_NAME);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__COMPLETE);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__INCOMPLETE);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__TORRENT_LEN);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__DOWNLOADED);
		createEReference(torrentSessionEClass, TORRENT_SESSION__CLIENT_MAP);
		createEAttribute(torrentSessionEClass, TORRENT_SESSION__TORRENT_PATH);

		clientSessionEClass = createEClass(CLIENT_SESSION);
		createEAttribute(clientSessionEClass, CLIENT_SESSION__SEED);
		createEAttribute(clientSessionEClass, CLIENT_SESSION__LAST_CONNECTION);

		sessionEClass = createEClass(SESSION);
		createEAttribute(sessionEClass, SESSION__ADDRESS);
		createEAttribute(sessionEClass, SESSION__CHANNEL);
		createEReference(sessionEClass, SESSION__REQUEST_MAP);
		createEReference(sessionEClass, SESSION__COMMUNICATION);
		createEAttribute(sessionEClass, SESSION__SESSION_STATE);
		createEAttribute(sessionEClass, SESSION__PEER_ID);
		createEAttribute(sessionEClass, SESSION__NOTE);
		createEAttribute(sessionEClass, SESSION__INFO_HASH);
		createEAttribute(sessionEClass, SESSION__LISTEN_PORT);
		createEAttribute(sessionEClass, SESSION__TORRENT);

		stringToSessionMapEntryEClass = createEClass(STRING_TO_SESSION_MAP_ENTRY);
		createEAttribute(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__KEY);
		createEReference(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__VALUE);

		stringToStringMapEntryEClass = createEClass(STRING_TO_STRING_MAP_ENTRY);
		createEAttribute(stringToStringMapEntryEClass, STRING_TO_STRING_MAP_ENTRY__KEY);
		createEAttribute(stringToStringMapEntryEClass, STRING_TO_STRING_MAP_ENTRY__VALUE);

		communicationEClass = createEClass(COMMUNICATION);
		createEAttribute(communicationEClass, COMMUNICATION__DATE);
		createEAttribute(communicationEClass, COMMUNICATION__REQUEST);
		createEAttribute(communicationEClass, COMMUNICATION__RESPONSE);

		// Create data types
		channelEDataType = createEDataType(CHANNEL);
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

		// Obtain other dependent packages
		TrackerConstantsPackage theTrackerConstantsPackage = (TrackerConstantsPackage) EPackage.Registry.INSTANCE.getEPackage(TrackerConstantsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theTrackerConstantsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		torrentSessionEClass.getESuperTypes().add(this.getSession());
		clientSessionEClass.getESuperTypes().add(this.getSession());

		// Initialize classes and features; add operations and parameters
		initEClass(trackerModelEClass, TrackerModel.class, "TrackerModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrackerModel_SwarmMap(), this.getStringToSessionMapEntry(), null, "swarmMap", null, 0, -1, TrackerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerModel_TrackerID(), ecorePackage.getEString(), "trackerID", "-GE2010-000000000000", 0, 1, TrackerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerModel_DefaultNumwant(), ecorePackage.getEInt(), "defaultNumwant", "30", 0, 1, TrackerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerModel_DefaultInterval(), ecorePackage.getEInt(), "defaultInterval", "10", 0, 1, TrackerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(torrentSessionEClass, TorrentSession.class, "TorrentSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTorrentSession_TorrentName(), ecorePackage.getEString(), "torrentName", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrentSession_Complete(), ecorePackage.getEInt(), "complete", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrentSession_Incomplete(), ecorePackage.getEInt(), "incomplete", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrentSession_TorrentLen(), ecorePackage.getELong(), "torrentLen", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrentSession_Downloaded(), ecorePackage.getEInt(), "downloaded", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTorrentSession_ClientMap(), this.getStringToSessionMapEntry(), null, "clientMap", null, 0, -1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrentSession_TorrentPath(), ecorePackage.getEString(), "torrentPath", null, 0, 1, TorrentSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(clientSessionEClass, ClientSession.class, "ClientSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClientSession_Seed(), ecorePackage.getEBoolean(), "seed", "false", 0, 1, ClientSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientSession_LastConnection(), ecorePackage.getELong(), "lastConnection", null, 0, 1, ClientSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sessionEClass, Session.class, "Session", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSession_Address(), ecorePackage.getEString(), "address", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Channel(), this.getChannel(), "channel", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getSession_RequestMap(), this.getStringToStringMapEntry(), null, "requestMap", null, 0, -1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSession_Communication(), this.getCommunication(), null, "communication", null, 0, -1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_SessionState(), theTrackerConstantsPackage.getEViewMessages(), "sessionState", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_PeerId(), ecorePackage.getEString(), "peerId", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getSession_Note(), ecorePackage.getEString(), "note", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getSession_InfoHash(), ecorePackage.getEString(), "infoHash", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_ListenPort(), ecorePackage.getEInt(), "listenPort", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Torrent(), ecorePackage.getEJavaObject(), "torrent", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(stringToSessionMapEntryEClass, Map.Entry.class, "StringToSessionMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToSessionMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToSessionMapEntry_Value(), this.getSession(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToStringMapEntryEClass, Map.Entry.class, "StringToStringMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStringMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToStringMapEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(communicationEClass, Communication.class, "Communication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCommunication_Date(), ecorePackage.getEDate(), "date", null, 0, 1, Communication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommunication_Request(), ecorePackage.getEString(), "request", null, 0, 1, Communication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommunication_Response(), ecorePackage.getEString(), "response", null, 0, 1, Communication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(channelEDataType, SocketChannel.class, "Channel", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // TrackerPackageImpl
