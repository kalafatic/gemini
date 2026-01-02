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
package eu.kalafatic.gemini.stat.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.ModelUtils;
import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatisticsModelManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsModelManager {

	/** The INSTANCE. */
	private static StatisticsModelManager INSTANCE = null;

	/** The stat file. */
	private File statFile;

	/** The stat uri. */
	private URI statURI;

	/** The statistics. */
	private Statistics statistics;

	/**
	 * Instantiates a new statistics model manager.
	 */
	public StatisticsModelManager() {
		initStatModel();
	}

	/**
	 * Gets the single instance of StatisticsModelManager.
	 * @return single instance of StatisticsModelManager
	 */
	public static StatisticsModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (StatisticsModelManager.class) {
				INSTANCE = new StatisticsModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the stat model.
	 */
	private void initStatModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			statFile = new File(models.concat(File.separator).concat("Model.statistics"));
			statURI = URI.createURI("file:/" + statFile.getAbsolutePath());

			if (statFile.exists()) {
				openModel();
			} else {
				createModel();
				initModel();
				doSave();
			}
		} catch (Exception e) {
			// Log.log(EWebPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 */
	private void openModel() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(statURI);
		Resource ecoreResource = resourceSet.getResource(statURI, true);
		statistics = (Statistics) ecoreResource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the model.
	 */
	private void createModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(StatisticsPackage.eNS_URI, StatisticsPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(statURI);
			statistics = StatisticsFactory.eINSTANCE.createStatistics();
			resource.getContents().add(statistics);

		} catch (Exception e) {
			// Log.log(EWebPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the model.
	 */
	private void initModel() {

	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		ModelUtils.doSave(statistics);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the test study.
	 * @return the test study
	 */
	public Study getTestStudy() {
		Study study = StatisticsFactory.eINSTANCE.createStudy();
		study.setName("Test");

		for (int i = 0, j = 0; i < 1; i++) {

			Graph graph = StatisticsFactory.eINSTANCE.createGraph();
			graph.setName("Graph");
			graph.setNameX("x");
			graph.setNameY("y");

			graph.getCatSeries().add("getCatSeries");
			graph.getCatSeries().add("getCatSeries");
			graph.getCatSeries().add("getCatSeries");

			graph.getSeries().add("getSeries");
			graph.getSeries().add("getSeries");
			graph.getSeries().add("getSeries");

			for (int k = 0; k < 10; k++) {
				graph.getCoordinates().add(j++);
			}
			study.getGraphs().put(Integer.toString(i), graph);
		}
		return study;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the statistics.
	 * @return the statistics
	 */
	public Statistics getStatistics() {
		return statistics;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the statistics.
	 * @param statistics the new statistics
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
}
