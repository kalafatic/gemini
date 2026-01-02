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
package eu.kalafatic.gemini.bt.tracker.model.tracker.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class TrackerSwitch.
 * @param <T> the generic type
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerSwitch<T> extends Switch<T> {

	/** The model package. */
	protected static TrackerPackage modelPackage;

	/**
	 * Instantiates a new tracker switch.
	 */
	public TrackerSwitch() {
		if (modelPackage == null) {
			modelPackage = TrackerPackage.eINSTANCE;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#isSwitchFor(org.eclipse.emf.ecore.EPackage)
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#doSwitch(int, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case TrackerPackage.TRACKER_MODEL: {
			TrackerModel trackerModel = (TrackerModel) theEObject;
			T result = caseTrackerModel(trackerModel);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.TORRENT_SESSION: {
			TorrentSession torrentSession = (TorrentSession) theEObject;
			T result = caseTorrentSession(torrentSession);
			if (result == null)
				result = caseSession(torrentSession);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.CLIENT_SESSION: {
			ClientSession clientSession = (ClientSession) theEObject;
			T result = caseClientSession(clientSession);
			if (result == null)
				result = caseSession(clientSession);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.SESSION: {
			Session session = (Session) theEObject;
			T result = caseSession(session);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.STRING_TO_SESSION_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Session> stringToSessionMapEntry = (Map.Entry<String, Session>) theEObject;
			T result = caseStringToSessionMapEntry(stringToSessionMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> stringToStringMapEntry = (Map.Entry<String, String>) theEObject;
			T result = caseStringToStringMapEntry(stringToStringMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TrackerPackage.COMMUNICATION: {
			Communication communication = (Communication) theEObject;
			T result = caseCommunication(communication);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Case tracker model.
	 * @param object the object
	 * @return the t
	 */
	public T caseTrackerModel(TrackerModel object) {
		return null;
	}

	/**
	 * Case torrent session.
	 * @param object the object
	 * @return the t
	 */
	public T caseTorrentSession(TorrentSession object) {
		return null;
	}

	/**
	 * Case client session.
	 * @param object the object
	 * @return the t
	 */
	public T caseClientSession(ClientSession object) {
		return null;
	}

	/**
	 * Case session.
	 * @param object the object
	 * @return the t
	 */
	public T caseSession(Session object) {
		return null;
	}

	/**
	 * Case string to session map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToSessionMapEntry(Map.Entry<String, Session> object) {
		return null;
	}

	/**
	 * Case string to string map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToStringMapEntry(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Case communication.
	 * @param object the object
	 * @return the t
	 */
	public T caseCommunication(Communication object) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#defaultCase(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // TrackerSwitch
