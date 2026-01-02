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
package eu.kalafatic.gemini.bt.visual.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.bt.visual.interfaces.IAlgoritm;
import eu.kalafatic.gemini.bt.visual.interfaces.IModel;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;
import eu.kalafatic.gemini.bt.visual.views.DrawComposite;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;



/**
 * The Class GraphicsDijkstraAlgorithm.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class GraphicsDijkstraAlgorithm implements IAlgoritm, IUIConstants {

	/** The model. */
	@SuppressWarnings("unused")
	private IModel model;

	/** The draw composite. */
	private DrawComposite drawComposite;
	
	/** The draw. */
	private boolean draw;

	/** The node map. */
	private Map<String, Node> nodeMap;
	
	/** The shortest list. */
	private List<Node> actualList, potentialList, shortestList;
	
	/** The start node. */
	private Node startNode;
	
	/** The end node. */
	private Node endNode;
	
	/** The min price node. */
	private Node minPriceNode;
	
	/** The best price. */
	private int bestPrice;

	/**
	 * Instantiates a new graphics dijkstra algorithm.
	 * 
	 * @param model
	 *            the model
	 */
	public GraphicsDijkstraAlgorithm(TreeModel model) {
		super();
		this.model = model;
		nodeMap = model.getNodeMap();
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#init()
	 */
	@Override
	public void init() {
		actualList = new ArrayList<Node>();
		shortestList = new ArrayList<Node>();
		potentialList = new ArrayList<Node>();

		bestPrice = Integer.MAX_VALUE;
		

		startNode = nodeMap.get(Integer.toString(Config.startNode));
		endNode = nodeMap.get(Integer.toString(Config.endNode));

		actualList.add(startNode);
		startNode.setPrice(0);
		startNode.setPreviousNode(null);
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#loadInput()
	 */
	@Override
	public void loadInput() {

	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#solve()
	 */
	@Override
	public synchronized void solve() {
		init();

		if (startNode.equals(endNode)) {
			Config.showMessage("Start node equals end node ");
			return;
		}
		drawComposite.drawModel();
		drawComposite.drawStartEndNode(startNode, endNode);

		Config.stopped = false;

		processNode(startNode);

		if (potentialList.isEmpty()) {
			Config.showMessage("Path not found");
		} else {
			drawComposite.getCanvas().redraw();

			shortestList = getWinnersMinNodes(potentialList);

			for (int i = 0; i < shortestList.size(); i++) {

				Node node = shortestList.get(i);
				Node actual = null;

				String res = endNode.getName() + " > ";

				while ((actual = node.getPreviousNode()) != null) {
					res = actual.getName() + " > ";
				}
				res += "/n/n";
			}
			Config.showMessage("Paths :/n");
		}
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#getResult()
	 */
	@Override
	public Object getResult() {
		return null;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Process node.
	 * 
	 * @param node
	 *            the node
	 */
	private void processNode(final Node node) {

		if (Config.stopped) {
			Config.showMessage("Stopped");
			return;
		}
		if (!startNode.equals(node)) {
			drawComposite.drawSearchNode(node);
		}
		
		if (node.isProcessed()) {
			return;
		} else {
			node.setProcessed(true);
			actualList.remove(node);
		}

		Iterator<Node> iterator = node.children.values().iterator();		

		while (iterator.hasNext()) {

			Node childNode = iterator.next();

			if (startNode.equals(childNode)) {
				continue;
			}
			childNode.setPrice(node.getPrice() + childNode.getDistance());
			childNode.setPreviousNode(node);

			drawComposite.drawChildSearchNode(node, childNode, null);

			if (childNode.equals(endNode)) {
				potentialList.add(node);

				if (childNode.getPrice() < bestPrice) {
					bestPrice = childNode.getPrice();
				}
			}else{
				if (! childNode.isProcessed()) {
					actualList.add(childNode);
				}				
			}			
		}		
		minPriceNode = getMinPriceNode();
		
		if (minPriceNode == null) {
			return;
		}
		processNode(minPriceNode);
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the min price node.
	 * 
	 * @return the min price node
	 */
	private Node getMinPriceNode() {
		
		if (actualList.isEmpty()) {
			return null;
		} 

		Node minPriceNode = actualList.get(0);		
		
		if (actualList.size()==1) {
			return minPriceNode;
		}
		
		try {
			for (int j = 1; j < actualList.size(); j++) {
				Node check = actualList.get(j);
				if (minPriceNode.getPrice() > check.getPrice()) {
					minPriceNode = check;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
//		if (bestPrice < minPriceNode.getPrice()) {
//			return null;
//		}
		drawComposite.drawMinPriceNode(minPriceNode);

		return minPriceNode;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the winners min nodes.
	 * 
	 * @param potentialList
	 *            the potential list
	 * 
	 * @return the winners min nodes
	 */
	private List<Node> getWinnersMinNodes(List<Node> potentialList) {
		List<Node> res = new ArrayList<Node>();
		res.add(potentialList.get(0));
		Node minPriceNode = potentialList.get(0);
		try {
			if (potentialList.size() > 1) {
				for (int j = 1; j < potentialList.size(); j++) {
					Node check = potentialList.get(j);
					if (minPriceNode.getPrice() > check.getPrice()) {
						res.clear();
						res.add(check);
						minPriceNode = check;
					} else if (minPriceNode.getPrice() == check.getPrice()) {
						res.add(check);
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		drawComposite.drawWinnerPath(endNode, res);

		return res; // for console
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

	// ---------------------------------------------------------------

	/**
	 * Checks if is draw.
	 * 
	 * @return true, if is draw
	 */
	public boolean isDraw() {
		return draw;
	}

	// ---------------------------------------------------------------
	
	/**
	 * Sets the draw.
	 * 
	 * @param draw
	 *            the new draw
	 */
	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	// ---------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#setDrawComposite(eu.kalafatic.gemini.bt.client.net.graph.views.DrawComposite)
	 */
	@Override
	public void setDrawComposite(DrawComposite drawComposite) {
		this.drawComposite = drawComposite;
	}

	// ---------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#setEditor(eu.kalafatic.gemini.bt.client.net.graph.editors.GraphMultiPageEditor)
	 */
	@Override
	public void setEditor(GraphMultiPageEditor editor) {
		// TODO Auto-generated method stub
		
	}

}

