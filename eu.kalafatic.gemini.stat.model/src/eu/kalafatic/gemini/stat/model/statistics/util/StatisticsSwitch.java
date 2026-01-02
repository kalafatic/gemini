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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatisticsSwitch.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsSwitch {

	/** The model package. */
	protected static StatisticsPackage modelPackage;

	/**
	 * Instantiates a new statistics switch.
	 */
	public StatisticsSwitch() {
		if (modelPackage == null) {
			modelPackage = StatisticsPackage.eINSTANCE;
		}
	}

	/**
	 * Do switch.
	 * @param theEObject the the e object
	 * @return the object
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Do switch.
	 * @param theEClass the the e class
	 * @param theEObject the the e object
	 * @return the object
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		} else {
			List eSuperTypes = theEClass.getESuperTypes();
			return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch((EClass) eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Do switch.
	 * @param classifierID the classifier id
	 * @param theEObject the the e object
	 * @return the object
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case StatisticsPackage.STATISTICS: {
			Statistics statistics = (Statistics) theEObject;
			Object result = caseStatistics(statistics);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case StatisticsPackage.STUDY: {
			Study study = (Study) theEObject;
			Object result = caseStudy(study);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case StatisticsPackage.GRAPH: {
			Graph graph = (Graph) theEObject;
			Object result = caseGraph(graph);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case StatisticsPackage.STRING_TO_STUDY_MAP_ENTRY: {
			Map.Entry stringToStudyMapEntry = (Map.Entry) theEObject;
			Object result = caseStringToStudyMapEntry(stringToStudyMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case StatisticsPackage.STRING_TO_GRAPH_MAP_ENTRY: {
			Map.Entry stringToGraphMapEntry = (Map.Entry) theEObject;
			Object result = caseStringToGraphMapEntry(stringToGraphMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Case statistics.
	 * @param object the object
	 * @return the object
	 */
	public Object caseStatistics(Statistics object) {
		return null;
	}

	/**
	 * Case study.
	 * @param object the object
	 * @return the object
	 */
	public Object caseStudy(Study object) {
		return null;
	}

	/**
	 * Case graph.
	 * @param object the object
	 * @return the object
	 */
	public Object caseGraph(Graph object) {
		return null;
	}

	/**
	 * Case string to study map entry.
	 * @param object the object
	 * @return the object
	 */
	public Object caseStringToStudyMapEntry(Map.Entry object) {
		return null;
	}

	/**
	 * Case string to graph map entry.
	 * @param object the object
	 * @return the object
	 */
	public Object caseStringToGraphMapEntry(Map.Entry object) {
		return null;
	}

	/**
	 * Default case.
	 * @param object the object
	 * @return the object
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} // StatisticsSwitch
