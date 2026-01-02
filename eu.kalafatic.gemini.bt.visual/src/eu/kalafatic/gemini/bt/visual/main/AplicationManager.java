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
package eu.kalafatic.gemini.bt.visual.main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

import eu.kalafatic.gemini.bt.visual.algorithms.DijkstraAlgorithm;
import eu.kalafatic.gemini.bt.visual.algorithms.GraphicsDijkstraAlgorithm;
import eu.kalafatic.gemini.bt.visual.analyzes.DijkstraAnalyzer;
import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.bt.visual.interfaces.IAlgoritm;
import eu.kalafatic.gemini.bt.visual.interfaces.IModel;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;
import eu.kalafatic.gemini.bt.visual.utils.ModelLoader;
import eu.kalafatic.gemini.bt.visual.views.DrawComposite;

/**
 * The Class AplicationManager.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class AplicationManager implements IModel {

	/** The draw composite. */
	private DrawComposite drawComposite;
	
	/** The model. */
	private TreeModel model;
	
	/** The active editor. */
	private GraphMultiPageEditor activeEditor;
	
	/** The dijkstra analyzer. */
	private DijkstraAnalyzer dijkstraAnalyzer;
	
	/** The INSTANCE. */
	private static AplicationManager INSTANCE;
	
	/** The algorithm. */
	private IAlgoritm algorithm;

	/**
	 * Gets the single instance of AplicationManager.
	 * 
	 * @return single instance of AplicationManager
	 */
	public static AplicationManager getInstance() {
		if (INSTANCE == null) {
			synchronized (AplicationManager.class) {
				INSTANCE = new AplicationManager();
			}
		}
		return INSTANCE;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Analyze model.
	 */
	public void analyzeModel() {

		model = loadModel();

		if (Config.drawSolution) {
			drawComposite = openPlotterInEditor(model);

			activeEditor.setActivePage(activeEditor.getPlotPageIndex());
			//drawComposite.getCanvas().getShell().setMaximized(true);
			drawComposite.getCanvas().redraw();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Solve algorithm.
	 */
	public void solveAlgorithm() {

		if (model == null) {
			model = loadModel();
		}
		if (Config.algorithm.equals("Dijkstra")) {
			if (Config.drawSolution) {
				algorithm = new GraphicsDijkstraAlgorithm(model);
				if (drawComposite == null) {
					openPlotterInEditor(model);
				}
				activeEditor.setActivePage(activeEditor.getPlotPageIndex());
				algorithm.setDrawComposite(drawComposite);

				//drawComposite.getCanvas().getShell().setMaximized(true);
			} else {
				algorithm = new DijkstraAlgorithm(model);
				algorithm.setEditor(activeEditor);
			}
		}
		algorithm.solve();
	}
	
	// ---------------------------------------------------------------

	/**
	 * Reset model.
	 */
	public void resetModel() {

		Iterator<Node> iterator = model.getNodeMap().values().iterator();
		while (iterator.hasNext()) {
			Node node = (Node) iterator.next();
			node.getTable().dispose();
		}

		if (model != null) {
			model = null;
		}
		drawComposite.getCanvas().redraw();
		drawComposite.getCanvas().layout(true, true);
	}
	
	// ---------------------------------------------------------------

	/**
	 * Load model.
	 * 
	 * @return the tree model
	 */
	public TreeModel loadModel() {
		ModelLoader loader = new ModelLoader();
		TreeModel model = loader.loadInputIntoModel();

		if (Config.generateOutput) {
			openModelInEditor(model);
		}
		return model;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Check settings.
	 * 
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private boolean checkSettings() {

		if (Config.inputFileName == null) {
			showMessage("Set up input");
			return false;
		}

		if (Config.algorithm == null) {
			showMessage("Set up algorithm");
			return false;
		}
		return true;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Open model in editor.
	 * 
	 * @param model
	 *            the model
	 */
	private void openModelInEditor(TreeModel model) {

		File modelFile = writeModelIntoFile(model);

		Config.modelFile = modelFile;

		final IFileStore fileStore = EFS.getLocalFileSystem().getStore(
				modelFile.toURI());
		IEditorInput editorInput = new FileStoreEditorInput(fileStore);

		activeEditor = (GraphMultiPageEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		activeEditor.createModelPage(editorInput);
	}
	
	// ---------------------------------------------------------------

	/**
	 * Open plotter in editor.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the draw composite
	 */
	private DrawComposite openPlotterInEditor(TreeModel model) {

		activeEditor = (GraphMultiPageEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		Canvas canvas = activeEditor.createPlotPage(null);

		drawComposite = new DrawComposite(canvas, model, activeEditor);
		drawComposite.createControl();

		return drawComposite;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Write model into file.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the file
	 */
	private File writeModelIntoFile(TreeModel model) {
		File modelFile = null;
		try {
			modelFile = new File(Config.outputFileName + "DIJKSTRA-MODEL.txt");

			modelFile = File.createTempFile("DIJKSTRA-MODEL", "txt");

			RandomAccessFile raf = new RandomAccessFile(modelFile, "rw");

			StringBuffer resultBuffer = new StringBuffer();

			dijkstraAnalyzer = new DijkstraAnalyzer(model);
			resultBuffer.append("\n");
			resultBuffer.append(dijkstraAnalyzer.getNodesAnalysis());
			resultBuffer.append("\n");

			raf.write(resultBuffer.toString().getBytes());
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelFile;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Show message.
	 * 
	 * @param message
	 *            the message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
				"Information", message);
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the draw composite.
	 * 
	 * @return the draw composite
	 */
	public DrawComposite getDrawComposite() {
		return drawComposite;
	}

}
