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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class TrackerFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerFactoryImpl extends EFactoryImpl implements TrackerFactory {

	/**
	 * Inits the.
	 * @return the tracker factory
	 */
	public static TrackerFactory init() {
		try {
			TrackerFactory theTrackerFactory = (TrackerFactory) EPackage.Registry.INSTANCE.getEFactory("http:///tracker.ecore");
			if (theTrackerFactory != null) {
				return theTrackerFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TrackerFactoryImpl();
	}

	/**
	 * Instantiates a new tracker factory impl.
	 */
	public TrackerFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case TrackerPackage.TRACKER_MODEL:
			return createTrackerModel();
		case TrackerPackage.TORRENT_SESSION:
			return createTorrentSession();
		case TrackerPackage.CLIENT_SESSION:
			return createClientSession();
		case TrackerPackage.SESSION:
			return createSession();
		case TrackerPackage.STRING_TO_SESSION_MAP_ENTRY:
			return (EObject) createStringToSessionMapEntry();
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY:
			return (EObject) createStringToStringMapEntry();
		case TrackerPackage.COMMUNICATION:
			return createCommunication();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#createFromString(org.eclipse.emf.ecore.EDataType, java.lang.String)
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case TrackerPackage.CHANNEL:
			return createChannelFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#convertToString(org.eclipse.emf.ecore.EDataType, java.lang.Object)
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case TrackerPackage.CHANNEL:
			return convertChannelToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#createTrackerModel()
	 */
	public TrackerModel createTrackerModel() {
		TrackerModelImpl trackerModel = new TrackerModelImpl();
		return trackerModel;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#createTorrentSession()
	 */
	public TorrentSession createTorrentSession() {
		TorrentSessionImpl torrentSession = new TorrentSessionImpl();
		return torrentSession;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#createClientSession()
	 */
	public ClientSession createClientSession() {
		ClientSessionImpl clientSession = new ClientSessionImpl();
		return clientSession;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#createSession()
	 */
	public Session createSession() {
		SessionImpl session = new SessionImpl();
		return session;
	}

	/**
	 * Creates the string to session map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Session> createStringToSessionMapEntry() {
		StringToSessionMapEntryImpl stringToSessionMapEntry = new StringToSessionMapEntryImpl();
		return stringToSessionMapEntry;
	}

	/**
	 * Creates the string to string map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, String> createStringToStringMapEntry() {
		StringToStringMapEntryImpl stringToStringMapEntry = new StringToStringMapEntryImpl();
		return stringToStringMapEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#createCommunication()
	 */
	public Communication createCommunication() {
		CommunicationImpl communication = new CommunicationImpl();
		return communication;
	}

	/**
	 * Creates the channel from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the socket channel
	 */
	public SocketChannel createChannelFromString(EDataType eDataType, String initialValue) {
		return (SocketChannel) super.createFromString(eDataType, initialValue);
	}

	/**
	 * Convert channel to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertChannelToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory#getTrackerPackage()
	 */
	public TrackerPackage getTrackerPackage() {
		return (TrackerPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static TrackerPackage getPackage() {
		return TrackerPackage.eINSTANCE;
	}

} // TrackerFactoryImpl
