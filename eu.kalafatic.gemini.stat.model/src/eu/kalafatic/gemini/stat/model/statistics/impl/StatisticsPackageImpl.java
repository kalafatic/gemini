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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatisticsPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsPackageImpl extends EPackageImpl implements StatisticsPackage {

	/** The statistics e class. */
	private EClass statisticsEClass = null;

	/** The study e class. */
	private EClass studyEClass = null;

	/** The graph e class. */
	private EClass graphEClass = null;

	/** The string to study map entry e class. */
	private EClass stringToStudyMapEntryEClass = null;

	/** The string to graph map entry e class. */
	private EClass stringToGraphMapEntryEClass = null;

	/**
	 * Instantiates a new statistics package impl.
	 */
	private StatisticsPackageImpl() {
		super(eNS_URI, StatisticsFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the statistics package
	 */
	public static StatisticsPackage init() {
		if (isInited)
			return (StatisticsPackage) EPackage.Registry.INSTANCE.getEPackage(StatisticsPackage.eNS_URI);

		// Obtain or create and register package
		StatisticsPackageImpl theStatisticsPackage = (StatisticsPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StatisticsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new StatisticsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theStatisticsPackage.createPackageContents();

		// Initialize created meta-data
		theStatisticsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatisticsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StatisticsPackage.eNS_URI, theStatisticsPackage);
		return theStatisticsPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStatistics()
	 */
	public EClass getStatistics() {
		return statisticsEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStatistics_StatMap()
	 */
	public EReference getStatistics_StatMap() {
		return (EReference) statisticsEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStudy()
	 */
	public EClass getStudy() {
		return studyEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStudy_Graphs()
	 */
	public EReference getStudy_Graphs() {
		return (EReference) studyEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStudy_Name()
	 */
	public EAttribute getStudy_Name() {
		return (EAttribute) studyEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph()
	 */
	public EClass getGraph() {
		return graphEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Name()
	 */
	public EAttribute getGraph_Name() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Coordinates()
	 */
	public EAttribute getGraph_Coordinates() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Active()
	 */
	public EAttribute getGraph_Active() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(2);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_NameX()
	 */
	public EAttribute getGraph_NameX() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(3);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_NameY()
	 */
	public EAttribute getGraph_NameY() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(4);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Type()
	 */
	public EAttribute getGraph_Type() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(5);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Data()
	 */
	public EAttribute getGraph_Data() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(6);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_Series()
	 */
	public EAttribute getGraph_Series() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(7);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getGraph_CatSeries()
	 */
	public EAttribute getGraph_CatSeries() {
		return (EAttribute) graphEClass.getEStructuralFeatures().get(8);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToStudyMapEntry()
	 */
	public EClass getStringToStudyMapEntry() {
		return stringToStudyMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToStudyMapEntry_Key()
	 */
	public EAttribute getStringToStudyMapEntry_Key() {
		return (EAttribute) stringToStudyMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToStudyMapEntry_Value()
	 */
	public EReference getStringToStudyMapEntry_Value() {
		return (EReference) stringToStudyMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToGraphMapEntry()
	 */
	public EClass getStringToGraphMapEntry() {
		return stringToGraphMapEntryEClass;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToGraphMapEntry_Key()
	 */
	public EAttribute getStringToGraphMapEntry_Key() {
		return (EAttribute) stringToGraphMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStringToGraphMapEntry_Value()
	 */
	public EReference getStringToGraphMapEntry_Value() {
		return (EReference) stringToGraphMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage#getStatisticsFactory()
	 */
	public StatisticsFactory getStatisticsFactory() {
		return (StatisticsFactory) getEFactoryInstance();
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
		statisticsEClass = createEClass(STATISTICS);
		createEReference(statisticsEClass, STATISTICS__STAT_MAP);

		studyEClass = createEClass(STUDY);
		createEReference(studyEClass, STUDY__GRAPHS);
		createEAttribute(studyEClass, STUDY__NAME);

		graphEClass = createEClass(GRAPH);
		createEAttribute(graphEClass, GRAPH__NAME);
		createEAttribute(graphEClass, GRAPH__COORDINATES);
		createEAttribute(graphEClass, GRAPH__ACTIVE);
		createEAttribute(graphEClass, GRAPH__NAME_X);
		createEAttribute(graphEClass, GRAPH__NAME_Y);
		createEAttribute(graphEClass, GRAPH__TYPE);
		createEAttribute(graphEClass, GRAPH__DATA);
		createEAttribute(graphEClass, GRAPH__SERIES);
		createEAttribute(graphEClass, GRAPH__CAT_SERIES);

		stringToStudyMapEntryEClass = createEClass(STRING_TO_STUDY_MAP_ENTRY);
		createEAttribute(stringToStudyMapEntryEClass, STRING_TO_STUDY_MAP_ENTRY__KEY);
		createEReference(stringToStudyMapEntryEClass, STRING_TO_STUDY_MAP_ENTRY__VALUE);

		stringToGraphMapEntryEClass = createEClass(STRING_TO_GRAPH_MAP_ENTRY);
		createEAttribute(stringToGraphMapEntryEClass, STRING_TO_GRAPH_MAP_ENTRY__KEY);
		createEReference(stringToGraphMapEntryEClass, STRING_TO_GRAPH_MAP_ENTRY__VALUE);
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

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(statisticsEClass, Statistics.class, "Statistics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatistics_StatMap(), this.getStringToStudyMapEntry(), null, "statMap", null, 0, -1, Statistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(studyEClass, Study.class, "Study", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStudy_Graphs(), this.getStringToGraphMapEntry(), null, "graphs", null, 0, -1, Study.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStudy_Name(), ecorePackage.getEString(), "name", null, 0, 1, Study.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(graphEClass, Graph.class, "Graph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraph_Name(), ecorePackage.getEString(), "name", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_Coordinates(), ecorePackage.getEInt(), "coordinates", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraph_Active(), ecorePackage.getEBoolean(), "active", "false", 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_NameX(), ecorePackage.getEString(), "nameX", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_NameY(), ecorePackage.getEString(), "nameY", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_Type(), ecorePackage.getEString(), "type", "BAR", 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_Data(), ecorePackage.getEString(), "data", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_Series(), ecorePackage.getEString(), "series", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getGraph_CatSeries(), ecorePackage.getEString(), "catSeries", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(stringToStudyMapEntryEClass, Map.Entry.class, "StringToStudyMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStudyMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToStudyMapEntry_Value(), this.getStudy(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToGraphMapEntryEClass, Map.Entry.class, "StringToGraphMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToGraphMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStringToGraphMapEntry_Value(), this.getGraph(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // StatisticsPackageImpl
