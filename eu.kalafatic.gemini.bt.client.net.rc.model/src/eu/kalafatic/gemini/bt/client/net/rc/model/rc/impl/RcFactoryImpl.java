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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.*;
import java.net.Socket;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCProtocol;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class RcFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RcFactoryImpl extends EFactoryImpl implements RcFactory {

	/**
	 * Inits the.
	 * @return the rc factory
	 */
	public static RcFactory init() {
		try {
			RcFactory theRcFactory = (RcFactory) EPackage.Registry.INSTANCE.getEFactory("http:///rc.ecore");
			if (theRcFactory != null) {
				return theRcFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RcFactoryImpl();
	}

	/**
	 * Instantiates a new rc factory impl.
	 */
	public RcFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case RcPackage.RC:
			return createRC();
		case RcPackage.RC_SESSION:
			return createRCSession();
		case RcPackage.STRING_TO_SESSION_MAP_ENTRY:
			return (EObject) createStringToSessionMapEntry();
		case RcPackage.STRING_TO_FOLDER_MAP_ENTRY:
			return (EObject) createStringToFolderMapEntry();
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY:
			return (EObject) createStringToPageMapEntry();
		case RcPackage.FOLDER:
			return createFolder();
		case RcPackage.PAGE:
			return createPage();
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
		case RcPackage.ERC_SESSION_STATE:
			return createERCSessionStateFromString(eDataType, initialValue);
		case RcPackage.RC_PROTOCOL:
			return createRCProtocolFromString(eDataType, initialValue);
		case RcPackage.ERCCMD:
			return createERCCMDFromString(eDataType, initialValue);
		case RcPackage.SOCKET:
			return createSocketFromString(eDataType, initialValue);
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
		case RcPackage.ERC_SESSION_STATE:
			return convertERCSessionStateToString(eDataType, instanceValue);
		case RcPackage.RC_PROTOCOL:
			return convertRCProtocolToString(eDataType, instanceValue);
		case RcPackage.ERCCMD:
			return convertERCCMDToString(eDataType, instanceValue);
		case RcPackage.SOCKET:
			return convertSocketToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory#createRC()
	 */
	public RC createRC() {
		RCImpl rc = new RCImpl();
		return rc;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory#createRCSession()
	 */
	public RCSession createRCSession() {
		RCSessionImpl rcSession = new RCSessionImpl();
		return rcSession;
	}

	/**
	 * Creates the string to session map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, RCSession> createStringToSessionMapEntry() {
		StringToSessionMapEntryImpl stringToSessionMapEntry = new StringToSessionMapEntryImpl();
		return stringToSessionMapEntry;
	}

	/**
	 * Creates the string to folder map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Folder> createStringToFolderMapEntry() {
		StringToFolderMapEntryImpl stringToFolderMapEntry = new StringToFolderMapEntryImpl();
		return stringToFolderMapEntry;
	}

	/**
	 * Creates the string to page map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Page> createStringToPageMapEntry() {
		StringToPageMapEntryImpl stringToPageMapEntry = new StringToPageMapEntryImpl();
		return stringToPageMapEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory#createFolder()
	 */
	public Folder createFolder() {
		FolderImpl folder = new FolderImpl();
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory#createPage()
	 */
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * Creates the erc session state from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the eRC session state
	 */
	public ERCSessionState createERCSessionStateFromString(EDataType eDataType, String initialValue) {
		ERCSessionState result = ERCSessionState.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert erc session state to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertERCSessionStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the rc protocol from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the rC protocol
	 */
	public RCProtocol createRCProtocolFromString(EDataType eDataType, String initialValue) {
		RCProtocol result = RCProtocol.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert rc protocol to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertRCProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the erccmd from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the eRCCMD
	 */
	public ERCCMD createERCCMDFromString(EDataType eDataType, String initialValue) {
		ERCCMD result = ERCCMD.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert erccmd to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertERCCMDToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the socket from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the socket
	 */
	public Socket createSocketFromString(EDataType eDataType, String initialValue) {
		return (Socket) super.createFromString(eDataType, initialValue);
	}

	/**
	 * Convert socket to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertSocketToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory#getRcPackage()
	 */
	public RcPackage getRcPackage() {
		return (RcPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static RcPackage getPackage() {
		return RcPackage.eINSTANCE;
	}

} // RcFactoryImpl
