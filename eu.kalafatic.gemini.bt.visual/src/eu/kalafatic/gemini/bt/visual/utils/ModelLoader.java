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
package eu.kalafatic.gemini.bt.visual.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;

/**
 * The Class ModelLoader.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class ModelLoader {

	/** The buffered reader. */
	private BufferedReader bufferedReader;
	
	/** The model. */
	private TreeModel model;
	
	/** The node map. */
	private Map<String, Node> nodeMap;

	/**
	 * Instantiates a new model loader.
	 */
	public ModelLoader() {
		model = new TreeModel();
		nodeMap = model.getNodeMap();
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Load input into model.
	 * 
	 * @return the tree model
	 */
	public TreeModel loadInputIntoModel() {
		try {
			File inputFile = new File(Config.inputFileName);

			bufferedReader = new BufferedReader(new FileReader(inputFile));
			String line = null;

			// repeat until all lines is read
			while ((line = bufferedReader.readLine()) != null) {

				String[] split = line.split(" ");

				if (split.length == 0 || split.length == 1 || split.length > 2) {
					Config.showMessage("Bad input  " );					
				}
				setUpNode(split);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * Sets the up node.
	 * 
	 * @param split
	 *            the new up node
	 */
	private void setUpNode(String[] split) {
		Node parentNode, childNode = null;
		Random random = new Random();
		String key = split[0].trim();

		if (nodeMap.containsKey(key)) {
			parentNode = nodeMap.get(key);
		} else {
			parentNode = new Node(key);
			parentNode.setPrice(Integer.MAX_VALUE);
			nodeMap.put(key, parentNode);
		}
		if (split.length > 1) {

			key = split[1].trim();

			if (!parentNode.getChildren().containsKey(key)) {
				
				if (nodeMap.containsKey(key)) {
					childNode = nodeMap.get(key);
				} else {
					childNode = new Node(key);
					childNode.setPrice(Integer.MAX_VALUE);
					nodeMap.put(key, childNode);
				}
				if (Config.generate) {
					childNode.setDistance(Math.abs(random.nextInt()
							% Config.range));
				}
				parentNode.getChildren().put(key, childNode);
			}
		}
	}

}
