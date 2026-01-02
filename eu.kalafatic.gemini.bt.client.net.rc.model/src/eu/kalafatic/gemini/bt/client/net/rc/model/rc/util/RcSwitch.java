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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc.util;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.*;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class RcSwitch.
 * @param <T> the generic type
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RcSwitch<T> extends Switch<T> {

	/** The model package. */
	protected static RcPackage modelPackage;

	/**
	 * Instantiates a new rc switch.
	 */
	public RcSwitch() {
		if (modelPackage == null) {
			modelPackage = RcPackage.eINSTANCE;
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
		case RcPackage.RC: {
			RC rc = (RC) theEObject;
			T result = caseRC(rc);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.RC_SESSION: {
			RCSession rcSession = (RCSession) theEObject;
			T result = caseRCSession(rcSession);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.STRING_TO_SESSION_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, RCSession> stringToSessionMapEntry = (Map.Entry<String, RCSession>) theEObject;
			T result = caseStringToSessionMapEntry(stringToSessionMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.STRING_TO_FOLDER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Folder> stringToFolderMapEntry = (Map.Entry<String, Folder>) theEObject;
			T result = caseStringToFolderMapEntry(stringToFolderMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Page> stringToPageMapEntry = (Map.Entry<String, Page>) theEObject;
			T result = caseStringToPageMapEntry(stringToPageMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.FOLDER: {
			Folder folder = (Folder) theEObject;
			T result = caseFolder(folder);
			if (result == null)
				result = casePage(folder);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case RcPackage.PAGE: {
			Page page = (Page) theEObject;
			T result = casePage(page);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Case rc.
	 * @param object the object
	 * @return the t
	 */
	public T caseRC(RC object) {
		return null;
	}

	/**
	 * Case rc session.
	 * @param object the object
	 * @return the t
	 */
	public T caseRCSession(RCSession object) {
		return null;
	}

	/**
	 * Case string to session map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToSessionMapEntry(Map.Entry<String, RCSession> object) {
		return null;
	}

	/**
	 * Case string to folder map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToFolderMapEntry(Map.Entry<String, Folder> object) {
		return null;
	}

	/**
	 * Case string to page map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToPageMapEntry(Map.Entry<String, Page> object) {
		return null;
	}

	/**
	 * Case folder.
	 * @param object the object
	 * @return the t
	 */
	public T caseFolder(Folder object) {
		return null;
	}

	/**
	 * Case page.
	 * @param object the object
	 * @return the t
	 */
	public T casePage(Page object) {
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

} // RcSwitch
