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
package eu.kalafatic.gemini.webBrowser.model.web.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebFactory;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class WebPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebPackageImpl extends EPackageImpl implements WebPackage {

	/** The web e class. */
	private EClass webEClass = null;

	/** The page e class. */
	private EClass pageEClass = null;

	/** The folder e class. */
	private EClass folderEClass = null;

	/** The string to folder map entry e class. */
	private EClass stringToFolderMapEntryEClass = null;

	/** The string to page map entry e class. */
	private EClass stringToPageMapEntryEClass = null;

	/**
	 * Instantiates a new web package impl.
	 */
	private WebPackageImpl() {
		super(eNS_URI, WebFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the web package
	 */
	public static WebPackage init() {
		if (isInited)
			return (WebPackage) EPackage.Registry.INSTANCE.getEPackage(WebPackage.eNS_URI);

		// Obtain or create and register package
		WebPackageImpl theWebPackage = (WebPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WebPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WebPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theWebPackage.createPackageContents();

		// Initialize created meta-data
		theWebPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWebPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WebPackage.eNS_URI, theWebPackage);
		return theWebPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getWeb()
	 */
	public EClass getWeb() {
		return webEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getWeb_HomePage()
	 */
	public EAttribute getWeb_HomePage() {
		return (EAttribute) webEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getWeb_Folders()
	 */
	public EReference getWeb_Folders() {
		return (EReference) webEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getPage()
	 */
	public EClass getPage() {
		return pageEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getPage_Address()
	 */
	public EAttribute getPage_Address() {
		return (EAttribute) pageEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getPage_Parent()
	 */
	public EReference getPage_Parent() {
		return (EReference) pageEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getFolder()
	 */
	public EClass getFolder() {
		return folderEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getFolder_Folders()
	 */
	public EReference getFolder_Folders() {
		return (EReference) folderEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getFolder_Pages()
	 */
	public EReference getFolder_Pages() {
		return (EReference) folderEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToFolderMapEntry()
	 */
	public EClass getStringToFolderMapEntry() {
		return stringToFolderMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToFolderMapEntry_Key()
	 */
	public EAttribute getStringToFolderMapEntry_Key() {
		return (EAttribute) stringToFolderMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToFolderMapEntry_Value()
	 */
	public EReference getStringToFolderMapEntry_Value() {
		return (EReference) stringToFolderMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToPageMapEntry()
	 */
	public EClass getStringToPageMapEntry() {
		return stringToPageMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToPageMapEntry_Key()
	 */
	public EAttribute getStringToPageMapEntry_Key() {
		return (EAttribute) stringToPageMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getStringToPageMapEntry_Value()
	 */
	public EReference getStringToPageMapEntry_Value() {
		return (EReference) stringToPageMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebPackage#getWebFactory()
	 */
	public WebFactory getWebFactory() {
		return (WebFactory) getEFactoryInstance();
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
		webEClass = createEClass(WEB);
		createEAttribute(webEClass, WEB__HOME_PAGE);
		createEReference(webEClass, WEB__FOLDERS);

		pageEClass = createEClass(PAGE);
		createEAttribute(pageEClass, PAGE__ADDRESS);
		createEReference(pageEClass, PAGE__PARENT);

		folderEClass = createEClass(FOLDER);
		createEReference(folderEClass, FOLDER__FOLDERS);
		createEReference(folderEClass, FOLDER__PAGES);

		stringToFolderMapEntryEClass = createEClass(STRING_TO_FOLDER_MAP_ENTRY);
		createEAttribute(stringToFolderMapEntryEClass, STRING_TO_FOLDER_MAP_ENTRY__KEY);
		createEReference(stringToFolderMapEntryEClass, STRING_TO_FOLDER_MAP_ENTRY__VALUE);

		stringToPageMapEntryEClass = createEClass(STRING_TO_PAGE_MAP_ENTRY);
		createEAttribute(stringToPageMapEntryEClass, STRING_TO_PAGE_MAP_ENTRY__KEY);
		createEReference(stringToPageMapEntryEClass, STRING_TO_PAGE_MAP_ENTRY__VALUE);
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
		initEClass(webEClass, Web.class, "Web", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeb_HomePage(), ecorePackage.getEString(), "homePage", "http://www.kalafatic.eu/", 1, 1, Web.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeb_Folders(), this.getStringToFolderMapEntry(), null, "folders", null, 0, -1, Web.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPage_Address(), ecorePackage.getEString(), "address", "http://www.kalafatic.eu/", 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Parent(), this.getFolder(), null, "parent", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(folderEClass, Folder.class, "Folder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFolder_Folders(), this.getStringToFolderMapEntry(), null, "folders", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFolder_Pages(), this.getStringToPageMapEntry(), null, "pages", null, 0, -1, Folder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		// Create resource
		createResource(eNS_URI);
	}

} // WebPackageImpl
