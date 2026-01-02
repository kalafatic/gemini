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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;

/**
 * A factory for creating BtStructureAdapter objects.
 */
public class BtStructureAdapterFactory extends AdapterFactoryImpl {

	/** The model package. */
	protected static BtStructurePackage modelPackage;

	/**
	 * Instantiates a new bt structure adapter factory.
	 */
	public BtStructureAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BtStructurePackage.eINSTANCE;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#isFactoryForType(java.lang.Object)
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/** The model switch. */
	protected BtStructureSwitch<Adapter> modelSwitch = new BtStructureSwitch<Adapter>() {
		@Override
		public Adapter caseBTStructure(BTStructure object) {
			return createBTStructureAdapter();
		}

		@Override
		public Adapter caseTracker(Tracker object) {
			return createTrackerAdapter();
		}

		@Override
		public Adapter caseFileTreeObject(FileTreeObject object) {
			return createFileTreeObjectAdapter();
		}

		@Override
		public Adapter caseStringToTrackerMapEntry(Map.Entry<String, Tracker> object) {
			return createStringToTrackerMapEntryAdapter();
		}

		@Override
		public Adapter caseStringToFileTreeObjectMapEntry(Map.Entry<String, FileTreeObject> object) {
			return createStringToFileTreeObjectMapEntryAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#createAdapter(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createBTStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createTrackerAdapter() {
		return null;
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createFileTreeObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToTrackerMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToFileTreeObjectMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new BtStructureAdapter object.
	 * @return the adapter
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // BtStructureAdapterFactory
