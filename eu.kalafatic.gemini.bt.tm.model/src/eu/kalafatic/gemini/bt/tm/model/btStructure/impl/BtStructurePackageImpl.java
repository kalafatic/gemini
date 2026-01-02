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
package eu.kalafatic.gemini.bt.tm.model.btStructure.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.impl.BtStructureConstantsPackageImpl;

/**
 * The Class class BtStructurePackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BtStructurePackageImpl extends EPackageImpl implements BtStructurePackage {

	/** The bt structure e class. */
	private EClass btStructureEClass = null;

	/** The tracker e class. */
	private EClass trackerEClass = null;

	/** The file tree object e class. */
	private EClass fileTreeObjectEClass = null;

	/** The string to tracker map entry e class. */
	private EClass stringToTrackerMapEntryEClass = null;

	/** The string to file tree object map entry e class. */
	private EClass stringToFileTreeObjectMapEntryEClass = null;

	/** The e integer array e data type. */
	private EDataType eIntegerArrayEDataType = null;

	/**
	 * Instantiates a new bt structure package impl.
	 */
	private BtStructurePackageImpl() {
		super(eNS_URI, BtStructureFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the bt structure package
	 */
	public static BtStructurePackage init() {
		if (isInited)
			return (BtStructurePackage) EPackage.Registry.INSTANCE.getEPackage(BtStructurePackage.eNS_URI);

		// Obtain or create and register package
		BtStructurePackageImpl theBtStructurePackage = (BtStructurePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BtStructurePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new BtStructurePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TorrentsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BtStructureConstantsPackageImpl theBtStructureConstantsPackage = (BtStructureConstantsPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(BtStructureConstantsPackage.eNS_URI) instanceof BtStructureConstantsPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(BtStructureConstantsPackage.eNS_URI) : BtStructureConstantsPackage.eINSTANCE);

		// Create package meta-data objects
		theBtStructurePackage.createPackageContents();
		theBtStructureConstantsPackage.createPackageContents();

		// Initialize created meta-data
		theBtStructurePackage.initializePackageContents();
		theBtStructureConstantsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBtStructurePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BtStructurePackage.eNS_URI, theBtStructurePackage);
		return theBtStructurePackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getBTStructure()
	 */
	public EClass getBTStructure() {
		return btStructureEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getBTStructure_NewTorrents()
	 */
	public EReference getBTStructure_NewTorrents() {
		return (EReference) btStructureEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getBTStructure_TrackersMap()
	 */
	public EReference getBTStructure_TrackersMap() {
		return (EReference) btStructureEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getBTStructure_TreeObjects()
	 */
	public EReference getBTStructure_TreeObjects() {
		return (EReference) btStructureEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getTracker()
	 */
	public EClass getTracker() {
		return trackerEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getTracker_Checked()
	 */
	public EAttribute getTracker_Checked() {
		return (EAttribute) trackerEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getTracker_Announce()
	 */
	public EAttribute getTracker_Announce() {
		return (EAttribute) trackerEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getTracker_Comment()
	 */
	public EAttribute getTracker_Comment() {
		return (EAttribute) trackerEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject()
	 */
	public EClass getFileTreeObject() {
		return fileTreeObjectEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_Name()
	 */
	public EAttribute getFileTreeObject_Name() {
		return (EAttribute) fileTreeObjectEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_Parent()
	 */
	public EReference getFileTreeObject_Parent() {
		return (EReference) fileTreeObjectEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_File()
	 */
	public EAttribute getFileTreeObject_File() {
		return (EAttribute) fileTreeObjectEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_Path()
	 */
	public EAttribute getFileTreeObject_Path() {
		return (EAttribute) fileTreeObjectEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_Md5Sum()
	 */
	public EAttribute getFileTreeObject_Md5Sum() {
		return (EAttribute) fileTreeObjectEClass.getEStructuralFeatures().get(4);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_Length()
	 */
	public EAttribute getFileTreeObject_Length() {
		return (EAttribute) fileTreeObjectEClass.getEStructuralFeatures().get(5);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getFileTreeObject_ChildMap()
	 */
	public EReference getFileTreeObject_ChildMap() {
		return (EReference) fileTreeObjectEClass.getEStructuralFeatures().get(6);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToTrackerMapEntry()
	 */
	public EClass getStringToTrackerMapEntry() {
		return stringToTrackerMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToTrackerMapEntry_Key()
	 */
	public EAttribute getStringToTrackerMapEntry_Key() {
		return (EAttribute) stringToTrackerMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToTrackerMapEntry_Value()
	 */
	public EReference getStringToTrackerMapEntry_Value() {
		return (EReference) stringToTrackerMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToFileTreeObjectMapEntry()
	 */
	public EClass getStringToFileTreeObjectMapEntry() {
		return stringToFileTreeObjectMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToFileTreeObjectMapEntry_Key()
	 */
	public EAttribute getStringToFileTreeObjectMapEntry_Key() {
		return (EAttribute) stringToFileTreeObjectMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getStringToFileTreeObjectMapEntry_Value()
	 */
	public EReference getStringToFileTreeObjectMapEntry_Value() {
		return (EReference) stringToFileTreeObjectMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getEIntegerArray()
	 */
	public EDataType getEIntegerArray() {
		return eIntegerArrayEDataType;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage#getBtStructureFactory()
	 */
	public BtStructureFactory getBtStructureFactory() {
		return (BtStructureFactory) getEFactoryInstance();
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
		btStructureEClass = createEClass(BT_STRUCTURE);
		createEReference(btStructureEClass, BT_STRUCTURE__NEW_TORRENTS);
		createEReference(btStructureEClass, BT_STRUCTURE__TRACKERS_MAP);
		createEReference(btStructureEClass, BT_STRUCTURE__TREE_OBJECTS);

		trackerEClass = createEClass(TRACKER);
		createEAttribute(trackerEClass, TRACKER__CHECKED);
		createEAttribute(trackerEClass, TRACKER__ANNOUNCE);
		createEAttribute(trackerEClass, TRACKER__COMMENT);

		fileTreeObjectEClass = createEClass(FILE_TREE_OBJECT);
		createEAttribute(fileTreeObjectEClass, FILE_TREE_OBJECT__NAME);
		createEReference(fileTreeObjectEClass, FILE_TREE_OBJECT__PARENT);
		createEAttribute(fileTreeObjectEClass, FILE_TREE_OBJECT__FILE);
		createEAttribute(fileTreeObjectEClass, FILE_TREE_OBJECT__PATH);
		createEAttribute(fileTreeObjectEClass, FILE_TREE_OBJECT__MD5_SUM);
		createEAttribute(fileTreeObjectEClass, FILE_TREE_OBJECT__LENGTH);
		createEReference(fileTreeObjectEClass, FILE_TREE_OBJECT__CHILD_MAP);

		stringToTrackerMapEntryEClass = createEClass(STRING_TO_TRACKER_MAP_ENTRY);
		createEAttribute(stringToTrackerMapEntryEClass, STRING_TO_TRACKER_MAP_ENTRY__KEY);
		createEReference(stringToTrackerMapEntryEClass, STRING_TO_TRACKER_MAP_ENTRY__VALUE);

		stringToFileTreeObjectMapEntryEClass = createEClass(STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY);
		createEAttribute(stringToFileTreeObjectMapEntryEClass, STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__KEY);
		createEReference(stringToFileTreeObjectMapEntryEClass, STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__VALUE);

		// Create data types
		eIntegerArrayEDataType = createEDataType(EINTEGER_ARRAY);
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
		BtStructureConstantsPackage theBtStructureConstantsPackage = (BtStructureConstantsPackage) EPackage.Registry.INSTANCE.getEPackage(BtStructureConstantsPackage.eNS_URI);
		TorrentsPackage theTorrentsPackage = (TorrentsPackage) EPackage.Registry.INSTANCE.getEPackage(TorrentsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theBtStructureConstantsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(btStructureEClass, BTStructure.class, "BTStructure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBTStructure_NewTorrents(), theTorrentsPackage.getStringToExtTorrentMapEntry(), null, "newTorrents", null, 0, -1, BTStructure.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBTStructure_TrackersMap(), this.getStringToTrackerMapEntry(), null, "trackersMap", null, 0, -1, BTStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBTStructure_TreeObjects(), this.getStringToFileTreeObjectMapEntry(), null, "treeObjects", null, 0, -1, BTStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackerEClass, Tracker.class, "Tracker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTracker_Checked(), ecorePackage.getEBoolean(), "checked", null, 0, 1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTracker_Announce(), ecorePackage.getEString(), "announce", null, 0, 1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTracker_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, Tracker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(fileTreeObjectEClass, FileTreeObject.class, "FileTreeObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTreeObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFileTreeObject_Parent(), this.getFileTreeObject(), null, "parent", null, 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTreeObject_File(), ecorePackage.getEBoolean(), "file", "false", 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTreeObject_Path(), ecorePackage.getEString(), "path", null, 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTreeObject_Md5Sum(), ecorePackage.getEString(), "md5Sum", null, 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTreeObject_Length(), ecorePackage.getELong(), "length", null, 0, 1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFileTreeObject_ChildMap(), this.getStringToFileTreeObjectMapEntry(), null, "childMap", null, 0, -1, FileTreeObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToTrackerMapEntryEClass, Map.Entry.class, "StringToTrackerMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToTrackerMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToTrackerMapEntry_Value(), this.getTracker(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToFileTreeObjectMapEntryEClass, Map.Entry.class, "StringToFileTreeObjectMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToFileTreeObjectMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToFileTreeObjectMapEntry_Value(), this.getFileTreeObject(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(eIntegerArrayEDataType, int[].class, "EIntegerArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // BtStructurePackageImpl
