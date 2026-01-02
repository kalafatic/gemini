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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * A factory for creating RcAdapter objects.
 */
public class RcAdapterFactory extends AdapterFactoryImpl {

	/** The model package. */
	protected static RcPackage modelPackage;

	/**
	 * Instantiates a new rc adapter factory.
	 */
	public RcAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RcPackage.eINSTANCE;
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
	protected RcSwitch<Adapter> modelSwitch = new RcSwitch<Adapter>() {
		@Override
		public Adapter caseRC(RC object) {
			return createRCAdapter();
		}

		@Override
		public Adapter caseRCSession(RCSession object) {
			return createRCSessionAdapter();
		}

		@Override
		public Adapter caseStringToSessionMapEntry(Map.Entry<String, RCSession> object) {
			return createStringToSessionMapEntryAdapter();
		}

		@Override
		public Adapter caseStringToFolderMapEntry(Map.Entry<String, Folder> object) {
			return createStringToFolderMapEntryAdapter();
		}

		@Override
		public Adapter caseStringToPageMapEntry(Map.Entry<String, Page> object) {
			return createStringToPageMapEntryAdapter();
		}

		@Override
		public Adapter caseFolder(Folder object) {
			return createFolderAdapter();
		}

		@Override
		public Adapter casePage(Page object) {
			return createPageAdapter();
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
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createRCAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createRCSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToSessionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToFolderMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToPageMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new RcAdapter object.
	 * @return the adapter
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // RcAdapterFactory
