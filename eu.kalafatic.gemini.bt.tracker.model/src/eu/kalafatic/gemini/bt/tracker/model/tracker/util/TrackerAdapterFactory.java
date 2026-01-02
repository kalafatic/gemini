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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * A factory for creating TrackerAdapter objects.
 */
public class TrackerAdapterFactory extends AdapterFactoryImpl {

	/** The model package. */
	protected static TrackerPackage modelPackage;

	/**
	 * Instantiates a new tracker adapter factory.
	 */
	public TrackerAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TrackerPackage.eINSTANCE;
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
	protected TrackerSwitch<Adapter> modelSwitch = new TrackerSwitch<Adapter>() {
		@Override
		public Adapter caseTrackerModel(TrackerModel object) {
			return createTrackerModelAdapter();
		}

		@Override
		public Adapter caseTorrentSession(TorrentSession object) {
			return createTorrentSessionAdapter();
		}

		@Override
		public Adapter caseClientSession(ClientSession object) {
			return createClientSessionAdapter();
		}

		@Override
		public Adapter caseSession(Session object) {
			return createSessionAdapter();
		}

		@Override
		public Adapter caseStringToSessionMapEntry(Map.Entry<String, Session> object) {
			return createStringToSessionMapEntryAdapter();
		}

		@Override
		public Adapter caseStringToStringMapEntry(Map.Entry<String, String> object) {
			return createStringToStringMapEntryAdapter();
		}

		@Override
		public Adapter caseCommunication(Communication object) {
			return createCommunicationAdapter();
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
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createTrackerModelAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createTorrentSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createClientSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToSessionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToStringMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createCommunicationAdapter() {
		return null;
	}

	/**
	 * Creates a new TrackerAdapter object.
	 * @return the adapter
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // TrackerAdapterFactory
