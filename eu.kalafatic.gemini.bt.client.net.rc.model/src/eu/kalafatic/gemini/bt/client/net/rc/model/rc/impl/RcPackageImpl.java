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

import java.net.Socket;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCProtocol;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class RcPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RcPackageImpl extends EPackageImpl implements RcPackage {

	/** The rc e class. */
	private EClass rcEClass = null;

	/** The rc session e class. */
	private EClass rcSessionEClass = null;

	/** The string to session map entry e class. */
	private EClass stringToSessionMapEntryEClass = null;

	/** The string to folder map entry e class. */
	private EClass stringToFolderMapEntryEClass = null;

	/** The string to page map entry e class. */
	private EClass stringToPageMapEntryEClass = null;

	/** The folder e class. */
	private EClass folderEClass = null;

	/** The page e class. */
	private EClass pageEClass = null;

	/** The erc session state e enum. */
	private EEnum ercSessionStateEEnum = null;

	/** The rc protocol e enum. */
	private EEnum rcProtocolEEnum = null;

	/** The erccmd e enum. */
	private EEnum erccmdEEnum = null;

	/** The socket e data type. */
	private EDataType socketEDataType = null;

	/**
	 * Instantiates a new rc package impl.
	 */
	private RcPackageImpl() {
		super(eNS_URI, RcFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the rc package
	 */
	public static RcPackage init() {
		if (isInited)
			return (RcPackage) EPackage.Registry.INSTANCE.getEPackage(RcPackage.eNS_URI);

		// Obtain or create and register package
		RcPackageImpl theRcPackage = (RcPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RcPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RcPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theRcPackage.createPackageContents();

		// Initialize created meta-data
		theRcPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRcPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RcPackage.eNS_URI, theRcPackage);
		return theRcPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRC()
	 */
	public EClass getRC() {
		return rcEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRC_SessionMap()
	 */
	public EReference getRC_SessionMap() {
		return (EReference) rcEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRC_Tree()
	 */
	public EReference getRC_Tree() {
		return (EReference) rcEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession()
	 */
	public EClass getRCSession() {
		return rcSessionEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_Host()
	 */
	public EAttribute getRCSession_Host() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_Announce()
	 */
	public EAttribute getRCSession_Announce() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_Socket()
	 */
	public EAttribute getRCSession_Socket() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_State()
	 */
	public EAttribute getRCSession_State() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_Torrents()
	 */
	public EAttribute getRCSession_Torrents() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(4);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCSession_HttpExchange()
	 */
	public EAttribute getRCSession_HttpExchange() {
		return (EAttribute) rcSessionEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToSessionMapEntry()
	 */
	public EClass getStringToSessionMapEntry() {
		return stringToSessionMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToSessionMapEntry_Key()
	 */
	public EAttribute getStringToSessionMapEntry_Key() {
		return (EAttribute) stringToSessionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToSessionMapEntry_Value()
	 */
	public EReference getStringToSessionMapEntry_Value() {
		return (EReference) stringToSessionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToFolderMapEntry()
	 */
	public EClass getStringToFolderMapEntry() {
		return stringToFolderMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToFolderMapEntry_Key()
	 */
	public EAttribute getStringToFolderMapEntry_Key() {
		return (EAttribute) stringToFolderMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToFolderMapEntry_Value()
	 */
	public EReference getStringToFolderMapEntry_Value() {
		return (EReference) stringToFolderMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToPageMapEntry()
	 */
	public EClass getStringToPageMapEntry() {
		return stringToPageMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToPageMapEntry_Key()
	 */
	public EAttribute getStringToPageMapEntry_Key() {
		return (EAttribute) stringToPageMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getStringToPageMapEntry_Value()
	 */
	public EReference getStringToPageMapEntry_Value() {
		return (EReference) stringToPageMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getFolder()
	 */
	public EClass getFolder() {
		return folderEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getFolder_Folders()
	 */
	public EReference getFolder_Folders() {
		return (EReference) folderEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getFolder_Pages()
	 */
	public EReference getFolder_Pages() {
		return (EReference) folderEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getPage()
	 */
	public EClass getPage() {
		return pageEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getPage_Address()
	 */
	public EAttribute getPage_Address() {
		return (EAttribute) pageEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getPage_Parent()
	 */
	public EReference getPage_Parent() {
		return (EReference) pageEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getERCSessionState()
	 */
	public EEnum getERCSessionState() {
		return ercSessionStateEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRCProtocol()
	 */
	public EEnum getRCProtocol() {
		return rcProtocolEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getERCCMD()
	 */
	public EEnum getERCCMD() {
		return erccmdEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getSocket()
	 */
	public EDataType getSocket() {
		return socketEDataType;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage#getRcFactory()
	 */
	public RcFactory getRcFactory() {
		return (RcFactory) getEFactoryInstance();
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
		rcEClass = createEClass(RC);
		createEReference(rcEClass, RC__SESSION_MAP);
		createEReference(rcEClass, RC__TREE);

		rcSessionEClass = createEClass(RC_SESSION);
		createEAttribute(rcSessionEClass, RC_SESSION__HOST);
		createEAttribute(rcSessionEClass, RC_SESSION__ANNOUNCE);
		createEAttribute(rcSessionEClass, RC_SESSION__SOCKET);
		createEAttribute(rcSessionEClass, RC_SESSION__STATE);
		createEAttribute(rcSessionEClass, RC_SESSION__TORRENTS);

		stringToSessionMapEntryEClass = createEClass(STRING_TO_SESSION_MAP_ENTRY);
		createEAttribute(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__KEY);
		createEReference(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__VALUE);

		stringToFolderMapEntryEClass = createEClass(STRING_TO_FOLDER_MAP_ENTRY);
		createEAttribute(stringToFolderMapEntryEClass, STRING_TO_FOLDER_MAP_ENTRY__KEY);
		createEReference(stringToFolderMapEntryEClass, STRING_TO_FOLDER_MAP_ENTRY__VALUE);

		stringToPageMapEntryEClass = createEClass(STRING_TO_PAGE_MAP_ENTRY);
		createEAttribute(stringToPageMapEntryEClass, STRING_TO_PAGE_MAP_ENTRY__KEY);
		createEReference(stringToPageMapEntryEClass, STRING_TO_PAGE_MAP_ENTRY__VALUE);

		folderEClass = createEClass(FOLDER);
		createEReference(folderEClass, FOLDER__FOLDERS);
		createEReference(folderEClass, FOLDER__PAGES);

		pageEClass = createEClass(PAGE);
		createEAttribute(pageEClass, PAGE__ADDRESS);
		createEReference(pageEClass, PAGE__PARENT);

		// Create enums
		ercSessionStateEEnum = createEEnum(ERC_SESSION_STATE);
		rcProtocolEEnum = createEEnum(RC_PROTOCOL);
		erccmdEEnum = createEEnum(ERCCMD);

		// Create data types
		socketEDataType = createEDataType(SOCKET);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		folderEClass.getESuperTypes().add(this.getPage());

		// Initialize classes and features; add operations and parameters
		initEClass(rcEClass, eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC.class, "RC", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRC_SessionMap(), this.getStringToSessionMapEntry(), null, "sessionMap", null, 0, -1, eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRC_Tree(), this.getStringToFolderMapEntry(), null, "tree", null, 0, -1, eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rcSessionEClass, RCSession.class, "RCSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRCSession_Host(), ecorePackage.getEString(), "host", null, 0, 1, RCSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getRCSession_Announce(), ecorePackage.getEString(), "announce", null, 0, 1, RCSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRCSession_Socket(), this.getSocket(), "socket", null, 0, 1, RCSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getRCSession_State(), this.getERCSessionState(), "state", null, 0, 1, RCSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRCSession_Torrents(), this.getERCCMD(), "torrents", "SEL_ALL", 0, 1, RCSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(stringToSessionMapEntryEClass, Map.Entry.class, "StringToSessionMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToSessionMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToSessionMapEntry_Value(), this.getRCSession(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToFolderMapEntryEClass, Map.Entry.class, "StringToFolderMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToFolderMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToFolderMapEntry_Value(), this.getFolder(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToPageMapEntryEClass, Map.Entry.class, "StringToPageMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToPageMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToPageMapEntry_Value(), this.getPage(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(folderEClass, Folder.class, "Folder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFolder_Folders(), this.getStringToFolderMapEntry(), null, "folders", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFolder_Pages(), this.getStringToPageMapEntry(), null, "pages", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPage_Address(), ecorePackage.getEString(), "address", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPage_Parent(), this.getFolder(), null, "parent", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(ercSessionStateEEnum, ERCSessionState.class, "ERCSessionState");
		addEEnumLiteral(ercSessionStateEEnum, ERCSessionState.NEW);
		addEEnumLiteral(ercSessionStateEEnum, ERCSessionState.LOGIN);
		addEEnumLiteral(ercSessionStateEEnum, ERCSessionState.LOGED);
		addEEnumLiteral(ercSessionStateEEnum, ERCSessionState.CERTIFIED);
		addEEnumLiteral(ercSessionStateEEnum, ERCSessionState.LOGOUT);

		initEEnum(rcProtocolEEnum, RCProtocol.class, "RCProtocol");

		initEEnum(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.class, "ERCCMD");
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.APP_RESTART);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.RC_LOGOUT);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.SEL_ALL);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.SEL_UNF);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.SEL_FIN);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.TSTART);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.TSTOP);
		addEEnumLiteral(erccmdEEnum, eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD.TADD);

		// Initialize data types
		initEDataType(socketEDataType, Socket.class, "Socket", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // RcPackageImpl
