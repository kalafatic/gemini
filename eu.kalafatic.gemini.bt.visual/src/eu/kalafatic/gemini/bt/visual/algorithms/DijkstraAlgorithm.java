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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.bt.visual.interfaces.IAlgoritm;
import eu.kalafatic.gemini.bt.visual.interfaces.IModel;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;
import eu.kalafatic.gemini.bt.visual.views.DrawComposite;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;


/**
 * The Class DijkstraAlgorithm.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class DijkstraAlgorithm implements IAlgoritm, IUIConstants {

	/** The model. */
	@SuppressWarnings("unused")
	private IModel model;

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

	/** The active editor. */
	private GraphMultiPageEditor activeEditor;

	/**
	 * Instantiates a new dijkstra algorithm.
	 * 
	 * @param model
	 *            the model
	 */
	public DijkstraAlgorithm(TreeModel model) {
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

	/**
	 * Sets the start end node.
	 * 
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public void setStartEndNode(Node start, Node end) {
		startNode = start;
		endNode = end;
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#solve()
	 */
	@Override
	public synchronized void solve() {
		
		StringBuffer sb=new StringBuffer();

		Object[] array = nodeMap.values().toArray();

		for (int i = 0; i < array.length - 1; i++) {
			Node start = (Node) array[i];

			for (int j = i + 1; j < array.length; j++) {
				Node end = (Node) array[j];

				setStartEndNode(start, end);
				init();
				
				processNode(startNode);

				sb.append("Result for nodes : "+start.getName()+" - "+end.getName()+"\n");
				
				if (potentialList.isEmpty()) {					
					sb.append("Path not found. \n\n");					
				} else {
					shortestList = getWinnersMinNodes(potentialList);

					for (int k = 0; k < shortestList.size(); k++) {
						Node node = shortestList.get(k);
						
						int distance = node.getChildren().get(end.getName()).getDistance();	
						int price=node.getPrice()+distance;
						
						sb.append("Path length = "+price+"\n");				
					}
					sb.append("\n");
				}
			}
		}
		writeOutput(sb);
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

			if (childNode.equals(endNode)) {
				potentialList.add(node);

				if (childNode.getPrice() < bestPrice) {
					bestPrice = childNode.getPrice();
				}
			} else {
				if (!childNode.isProcessed()) {
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

		if (actualList.size() == 1) {
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

		// if (bestPrice < minPriceNode.getPrice()) {
		// return null;
		// }
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
		return res; // for console
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * Write output.
	 * 
	 * @param sb
	 *            the sb
	 */
	private void writeOutput(StringBuffer sb) {
		try {
			File file = new File("output.txt");
			FileWriter fw=new FileWriter(file);
			fw.write(sb.toString());
			fw.flush();
			
			final IFileStore fileStore = EFS.getLocalFileSystem().getStore(
					file.toURI());
			IEditorInput editorInput = new FileStoreEditorInput(fileStore);

			activeEditor = (GraphMultiPageEditor) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();

			activeEditor.createOutputPage(editorInput);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#setDrawComposite(eu.kalafatic.gemini.bt.client.net.graph.views.DrawComposite)
	 */
	@Override
	public void setDrawComposite(DrawComposite drawComposite) {
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.graph.interfaces.IAlgoritm#setEditor(eu.kalafatic.gemini.bt.client.net.graph.editors.GraphMultiPageEditor)
	 */
	@Override
	public void setEditor(GraphMultiPageEditor editor) {
		this.activeEditor=editor;
	}

}
