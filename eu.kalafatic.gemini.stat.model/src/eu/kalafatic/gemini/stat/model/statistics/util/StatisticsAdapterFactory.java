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
package eu.kalafatic.gemini.stat.model.statistics.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * A factory for creating StatisticsAdapter objects.
 */
public class StatisticsAdapterFactory extends AdapterFactoryImpl {

	/** The model package. */
	protected static StatisticsPackage modelPackage;

	/**
	 * Instantiates a new statistics adapter factory.
	 */
	public StatisticsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StatisticsPackage.eINSTANCE;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#isFactoryForType(java.lang.Object)
	 */
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
	protected StatisticsSwitch modelSwitch = new StatisticsSwitch() {
		public Object caseStatistics(Statistics object) {
			return createStatisticsAdapter();
		}

		public Object caseStudy(Study object) {
			return createStudyAdapter();
		}

		public Object caseGraph(Graph object) {
			return createGraphAdapter();
		}

		public Object caseStringToStudyMapEntry(Map.Entry object) {
			return createStringToStudyMapEntryAdapter();
		}

		public Object caseStringToGraphMapEntry(Map.Entry object) {
			return createStringToGraphMapEntryAdapter();
		}

		public Object defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#createAdapter(org.eclipse.emf.common.notify.Notifier)
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter) modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createStatisticsAdapter() {
		return null;
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createStudyAdapter() {
		return null;
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createGraphAdapter() {
		return null;
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToStudyMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToGraphMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new StatisticsAdapter object.
	 * @return the adapter
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // StatisticsAdapterFactory
