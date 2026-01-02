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
package eu.kalafatic.gemini.stat.model.statistics;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * The Interface interface StatisticsPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface StatisticsPackage extends EPackage {

	/** The e name. */
	String eNAME = "statistics";

	/** The e n s_ uri. */
	String eNS_URI = "http:///statistics.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "statistics";

	/** The e instance. */
	StatisticsPackage eINSTANCE = eu.kalafatic.gemini.stat.model.statistics.impl.StatisticsPackageImpl.init();

	/** The STATISTICS. */
	int STATISTICS = 0;

	/** The STATISTIC s__ sta t_ map. */
	int STATISTICS__STAT_MAP = 0;

	/** The STATISTIC s_ featur e_ count. */
	int STATISTICS_FEATURE_COUNT = 1;

	/** The STUDY. */
	int STUDY = 1;

	/** The STUD y__ graphs. */
	int STUDY__GRAPHS = 0;

	/** The STUD y__ name. */
	int STUDY__NAME = 1;

	/** The STUD y_ featur e_ count. */
	int STUDY_FEATURE_COUNT = 2;

	/** The GRAPH. */
	int GRAPH = 2;

	/** The GRAP h__ name. */
	int GRAPH__NAME = 0;

	/** The GRAP h__ coordinates. */
	int GRAPH__COORDINATES = 1;

	/** The GRAP h__ active. */
	int GRAPH__ACTIVE = 2;

	/** The GRAP h__ nam e_ x. */
	int GRAPH__NAME_X = 3;

	/** The GRAP h__ nam e_ y. */
	int GRAPH__NAME_Y = 4;

	/** The GRAP h__ type. */
	int GRAPH__TYPE = 5;

	/** The GRAP h__ data. */
	int GRAPH__DATA = 6;

	/** The GRAP h__ series. */
	int GRAPH__SERIES = 7;

	/** The GRAP h__ ca t_ series. */
	int GRAPH__CAT_SERIES = 8;

	/** The GRAP h_ featur e_ count. */
	int GRAPH_FEATURE_COUNT = 9;

	/** The STRIN g_ t o_ stud y_ ma p_ entry. */
	int STRING_TO_STUDY_MAP_ENTRY = 3;

	/** The STRIN g_ t o_ stud y_ ma p_ entr y__ key. */
	int STRING_TO_STUDY_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ stud y_ ma p_ entr y__ value. */
	int STRING_TO_STUDY_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ stud y_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_STUDY_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The STRIN g_ t o_ grap h_ ma p_ entry. */
	int STRING_TO_GRAPH_MAP_ENTRY = 4;

	/** The STRIN g_ t o_ grap h_ ma p_ entr y__ key. */
	int STRING_TO_GRAPH_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ grap h_ ma p_ entr y__ value. */
	int STRING_TO_GRAPH_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ grap h_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_GRAPH_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * Gets the statistics.
	 * @return the statistics
	 */
	EClass getStatistics();

	/**
	 * Gets the statistics_ stat map.
	 * @return the statistics_ stat map
	 */
	EReference getStatistics_StatMap();

	/**
	 * Gets the study.
	 * @return the study
	 */
	EClass getStudy();

	/**
	 * Gets the study_ graphs.
	 * @return the study_ graphs
	 */
	EReference getStudy_Graphs();

	/**
	 * Gets the study_ name.
	 * @return the study_ name
	 */
	EAttribute getStudy_Name();

	/**
	 * Gets the graph.
	 * @return the graph
	 */
	EClass getGraph();

	/**
	 * Gets the graph_ name.
	 * @return the graph_ name
	 */
	EAttribute getGraph_Name();

	/**
	 * Gets the graph_ coordinates.
	 * @return the graph_ coordinates
	 */
	EAttribute getGraph_Coordinates();

	/**
	 * Gets the graph_ active.
	 * @return the graph_ active
	 */
	EAttribute getGraph_Active();

	/**
	 * Gets the graph_ name x.
	 * @return the graph_ name x
	 */
	EAttribute getGraph_NameX();

	/**
	 * Gets the graph_ name y.
	 * @return the graph_ name y
	 */
	EAttribute getGraph_NameY();

	/**
	 * Gets the graph_ type.
	 * @return the graph_ type
	 */
	EAttribute getGraph_Type();

	/**
	 * Gets the graph_ data.
	 * @return the graph_ data
	 */
	EAttribute getGraph_Data();

	/**
	 * Gets the graph_ series.
	 * @return the graph_ series
	 */
	EAttribute getGraph_Series();

	/**
	 * Gets the graph_ cat series.
	 * @return the graph_ cat series
	 */
	EAttribute getGraph_CatSeries();

	/**
	 * Gets the string to study map entry.
	 * @return the string to study map entry
	 */
	EClass getStringToStudyMapEntry();

	/**
	 * Gets the string to study map entry_ key.
	 * @return the string to study map entry_ key
	 */
	EAttribute getStringToStudyMapEntry_Key();

	/**
	 * Gets the string to study map entry_ value.
	 * @return the string to study map entry_ value
	 */
	EReference getStringToStudyMapEntry_Value();

	/**
	 * Gets the string to graph map entry.
	 * @return the string to graph map entry
	 */
	EClass getStringToGraphMapEntry();

	/**
	 * Gets the string to graph map entry_ key.
	 * @return the string to graph map entry_ key
	 */
	EAttribute getStringToGraphMapEntry_Key();

	/**
	 * Gets the string to graph map entry_ value.
	 * @return the string to graph map entry_ value
	 */
	EReference getStringToGraphMapEntry_Value();

	/**
	 * Gets the statistics factory.
	 * @return the statistics factory
	 */
	StatisticsFactory getStatisticsFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The STATISTICS. */
		EClass STATISTICS = eINSTANCE.getStatistics();

		/** The STATISTIC s__ sta t_ map. */
		EReference STATISTICS__STAT_MAP = eINSTANCE.getStatistics_StatMap();

		/** The STUDY. */
		EClass STUDY = eINSTANCE.getStudy();

		/** The STUD y__ graphs. */
		EReference STUDY__GRAPHS = eINSTANCE.getStudy_Graphs();

		/** The STUD y__ name. */
		EAttribute STUDY__NAME = eINSTANCE.getStudy_Name();

		/** The GRAPH. */
		EClass GRAPH = eINSTANCE.getGraph();

		/** The GRAP h__ name. */
		EAttribute GRAPH__NAME = eINSTANCE.getGraph_Name();

		/** The GRAP h__ coordinates. */
		EAttribute GRAPH__COORDINATES = eINSTANCE.getGraph_Coordinates();

		/** The GRAP h__ active. */
		EAttribute GRAPH__ACTIVE = eINSTANCE.getGraph_Active();

		/** The GRAP h__ nam e_ x. */
		EAttribute GRAPH__NAME_X = eINSTANCE.getGraph_NameX();

		/** The GRAP h__ nam e_ y. */
		EAttribute GRAPH__NAME_Y = eINSTANCE.getGraph_NameY();

		/** The GRAP h__ type. */
		EAttribute GRAPH__TYPE = eINSTANCE.getGraph_Type();

		/** The GRAP h__ data. */
		EAttribute GRAPH__DATA = eINSTANCE.getGraph_Data();

		/** The GRAP h__ series. */
		EAttribute GRAPH__SERIES = eINSTANCE.getGraph_Series();

		/** The GRAP h__ ca t_ series. */
		EAttribute GRAPH__CAT_SERIES = eINSTANCE.getGraph_CatSeries();

		/** The STRIN g_ t o_ stud y_ ma p_ entry. */
		EClass STRING_TO_STUDY_MAP_ENTRY = eINSTANCE.getStringToStudyMapEntry();

		/** The STRIN g_ t o_ stud y_ ma p_ entr y__ key. */
		EAttribute STRING_TO_STUDY_MAP_ENTRY__KEY = eINSTANCE.getStringToStudyMapEntry_Key();

		/** The STRIN g_ t o_ stud y_ ma p_ entr y__ value. */
		EReference STRING_TO_STUDY_MAP_ENTRY__VALUE = eINSTANCE.getStringToStudyMapEntry_Value();

		/** The STRIN g_ t o_ grap h_ ma p_ entry. */
		EClass STRING_TO_GRAPH_MAP_ENTRY = eINSTANCE.getStringToGraphMapEntry();

		/** The STRIN g_ t o_ grap h_ ma p_ entr y__ key. */
		EAttribute STRING_TO_GRAPH_MAP_ENTRY__KEY = eINSTANCE.getStringToGraphMapEntry_Key();

		/** The STRIN g_ t o_ grap h_ ma p_ entr y__ value. */
		EReference STRING_TO_GRAPH_MAP_ENTRY__VALUE = eINSTANCE.getStringToGraphMapEntry_Value();

	}

} // StatisticsPackage
