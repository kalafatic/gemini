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
package eu.kalafatic.gemini.bt.tm.model.btStructure.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;

/**
 * The Class class BtStructureSwitch.
 * @param <T> the generic type
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BtStructureSwitch<T> extends Switch<T> {

	/** The model package. */
	protected static BtStructurePackage modelPackage;

	/**
	 * Instantiates a new bt structure switch.
	 */
	public BtStructureSwitch() {
		if (modelPackage == null) {
			modelPackage = BtStructurePackage.eINSTANCE;
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
		case BtStructurePackage.BT_STRUCTURE: {
			BTStructure btStructure = (BTStructure) theEObject;
			T result = caseBTStructure(btStructure);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BtStructurePackage.TRACKER: {
			Tracker tracker = (Tracker) theEObject;
			T result = caseTracker(tracker);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BtStructurePackage.FILE_TREE_OBJECT: {
			FileTreeObject fileTreeObject = (FileTreeObject) theEObject;
			T result = caseFileTreeObject(fileTreeObject);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BtStructurePackage.STRING_TO_TRACKER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Tracker> stringToTrackerMapEntry = (Map.Entry<String, Tracker>) theEObject;
			T result = caseStringToTrackerMapEntry(stringToTrackerMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BtStructurePackage.STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, FileTreeObject> stringToFileTreeObjectMapEntry = (Map.Entry<String, FileTreeObject>) theEObject;
			T result = caseStringToFileTreeObjectMapEntry(stringToFileTreeObjectMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Case bt structure.
	 * @param object the object
	 * @return the t
	 */
	public T caseBTStructure(BTStructure object) {
		return null;
	}

	/**
	 * Case tracker.
	 * @param object the object
	 * @return the t
	 */
	public T caseTracker(Tracker object) {
		return null;
	}

	/**
	 * Case file tree object.
	 * @param object the object
	 * @return the t
	 */
	public T caseFileTreeObject(FileTreeObject object) {
		return null;
	}

	/**
	 * Case string to tracker map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToTrackerMapEntry(Map.Entry<String, Tracker> object) {
		return null;
	}

	/**
	 * Case string to file tree object map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToFileTreeObjectMapEntry(Map.Entry<String, FileTreeObject> object) {
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

} // BtStructureSwitch
