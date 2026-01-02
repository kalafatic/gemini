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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;

/**
 * The Class class BtStructureFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BtStructureFactoryImpl extends EFactoryImpl implements BtStructureFactory {

	/**
	 * Inits the.
	 * @return the bt structure factory
	 */
	public static BtStructureFactory init() {
		try {
			BtStructureFactory theBtStructureFactory = (BtStructureFactory) EPackage.Registry.INSTANCE.getEFactory("http:///btStructure.ecore");
			if (theBtStructureFactory != null) {
				return theBtStructureFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BtStructureFactoryImpl();
	}

	/**
	 * Instantiates a new bt structure factory impl.
	 */
	public BtStructureFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case BtStructurePackage.BT_STRUCTURE:
			return createBTStructure();
		case BtStructurePackage.TRACKER:
			return createTracker();
		case BtStructurePackage.FILE_TREE_OBJECT:
			return createFileTreeObject();
		case BtStructurePackage.STRING_TO_TRACKER_MAP_ENTRY:
			return (EObject) createStringToTrackerMapEntry();
		case BtStructurePackage.STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY:
			return (EObject) createStringToFileTreeObjectMapEntry();
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
		case BtStructurePackage.EINTEGER_ARRAY:
			return createEIntegerArrayFromString(eDataType, initialValue);
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
		case BtStructurePackage.EINTEGER_ARRAY:
			return convertEIntegerArrayToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory#createBTStructure()
	 */
	public BTStructure createBTStructure() {
		BTStructureImpl btStructure = new BTStructureImpl();
		return btStructure;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory#createTracker()
	 */
	public Tracker createTracker() {
		TrackerImpl tracker = new TrackerImpl();
		return tracker;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory#createFileTreeObject()
	 */
	public FileTreeObject createFileTreeObject() {
		FileTreeObjectImpl fileTreeObject = new FileTreeObjectImpl();
		return fileTreeObject;
	}

	/**
	 * Creates the string to tracker map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Tracker> createStringToTrackerMapEntry() {
		StringToTrackerMapEntryImpl stringToTrackerMapEntry = new StringToTrackerMapEntryImpl();
		return stringToTrackerMapEntry;
	}

	/**
	 * Creates the string to file tree object map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, FileTreeObject> createStringToFileTreeObjectMapEntry() {
		StringToFileTreeObjectMapEntryImpl stringToFileTreeObjectMapEntry = new StringToFileTreeObjectMapEntryImpl();
		return stringToFileTreeObjectMapEntry;
	}

	/**
	 * Creates the e integer array from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the int[]
	 */
	public int[] createEIntegerArrayFromString(EDataType eDataType, String initialValue) {
		return (int[]) super.createFromString(initialValue);
	}

	/**
	 * Convert e integer array to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertEIntegerArrayToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory#getBtStructurePackage()
	 */
	public BtStructurePackage getBtStructurePackage() {
		return (BtStructurePackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static BtStructurePackage getPackage() {
		return BtStructurePackage.eINSTANCE;
	}

} // BtStructureFactoryImpl
