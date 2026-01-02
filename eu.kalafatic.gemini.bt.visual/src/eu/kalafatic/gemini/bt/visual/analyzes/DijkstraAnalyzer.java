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
package eu.kalafatic.gemini.bt.visual.analyzes;

import java.util.Iterator;
import java.util.Map;

import eu.kalafatic.gemini.bt.visual.interfaces.IAnalyzer;
import eu.kalafatic.gemini.bt.visual.interfaces.IModel;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class DijkstraAnalyzer.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class DijkstraAnalyzer implements  IAnalyzer, IUIConstants {

	
	/** The model. */
	@SuppressWarnings("unused")
	private IModel model;
	
	/** The result buffer. */
	private StringBuffer resultBuffer;
	
	/** The node map. */
	private Map<String, Node> nodeMap;

	/**
	 * Instantiates a new dijkstra analyzer.
	 * 
	 * @param model
	 *            the model
	 */
	public DijkstraAnalyzer(TreeModel model) {
		super();
		this.model = model;
		nodeMap = ((TreeModel) model).getNodeMap();
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAnalyzer#getNodesAnalysis()
	 */
	@Override
	public String getNodesAnalysis() {
		
		resultBuffer=new StringBuffer();
		
		resultBuffer.append("Nodes count : " + nodeMap.size()+"\n");		
		
		Iterator<Node> iterator = nodeMap.values().iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();

			resultBuffer.append("\n" + node.getName() + " : ");

			Iterator<Node> iterator2 = node.children.values().iterator();

			while (iterator2.hasNext()) {
				Node childNode = iterator2.next();
				resultBuffer.append("\n  --" + childNode.getDistance()
						+ "--   " + childNode.getName());					
			}
		}
		return resultBuffer.toString();
	}
}
