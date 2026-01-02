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

import org.eclipse.emf.ecore.EFactory;

/**
 * A factory for creating Statistics objects.
 */
public interface StatisticsFactory extends EFactory {

	/** The e instance. */
	StatisticsFactory eINSTANCE = eu.kalafatic.gemini.stat.model.statistics.impl.StatisticsFactoryImpl.init();

	/**
	 * Creates a new Statistics object.
	 * @return the statistics
	 */
	Statistics createStatistics();

	/**
	 * Creates a new Statistics object.
	 * @return the study
	 */
	Study createStudy();

	/**
	 * Creates a new Statistics object.
	 * @return the graph
	 */
	Graph createGraph();

	/**
	 * Gets the statistics package.
	 * @return the statistics package
	 */
	StatisticsPackage getStatisticsPackage();

} // StatisticsFactory
