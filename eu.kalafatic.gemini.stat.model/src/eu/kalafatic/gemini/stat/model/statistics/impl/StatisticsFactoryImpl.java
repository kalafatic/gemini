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
package eu.kalafatic.gemini.stat.model.statistics.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatisticsFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsFactoryImpl extends EFactoryImpl implements StatisticsFactory {

	/**
	 * Inits the.
	 * @return the statistics factory
	 */
	public static StatisticsFactory init() {
		try {
			StatisticsFactory theStatisticsFactory = (StatisticsFactory) EPackage.Registry.INSTANCE.getEFactory("http:///statistics.ecore");
			if (theStatisticsFactory != null) {
				return theStatisticsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StatisticsFactoryImpl();
	}

	/**
	 * Instantiates a new statistics factory impl.
	 */
	public StatisticsFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case StatisticsPackage.STATISTICS:
			return createStatistics();
		case StatisticsPackage.STUDY:
			return createStudy();
		case StatisticsPackage.GRAPH:
			return createGraph();
		case StatisticsPackage.STRING_TO_STUDY_MAP_ENTRY:
			return (EObject) createStringToStudyMapEntry();
		case StatisticsPackage.STRING_TO_GRAPH_MAP_ENTRY:
			return (EObject) createStringToGraphMapEntry();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory#createStatistics()
	 */
	public Statistics createStatistics() {
		StatisticsImpl statistics = new StatisticsImpl();
		return statistics;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory#createStudy()
	 */
	public Study createStudy() {
		StudyImpl study = new StudyImpl();
		return study;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory#createGraph()
	 */
	public Graph createGraph() {
		GraphImpl graph = new GraphImpl();
		return graph;
	}

	/**
	 * Creates the string to study map entry.
	 * @return the map. entry
	 */
	public Map.Entry createStringToStudyMapEntry() {
		StringToStudyMapEntryImpl stringToStudyMapEntry = new StringToStudyMapEntryImpl();
		return stringToStudyMapEntry;
	}

	/**
	 * Creates the string to graph map entry.
	 * @return the map. entry
	 */
	public Map.Entry createStringToGraphMapEntry() {
		StringToGraphMapEntryImpl stringToGraphMapEntry = new StringToGraphMapEntryImpl();
		return stringToGraphMapEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory#getStatisticsPackage()
	 */
	public StatisticsPackage getStatisticsPackage() {
		return (StatisticsPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	public static StatisticsPackage getPackage() {
		return StatisticsPackage.eINSTANCE;
	}

} // StatisticsFactoryImpl
