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
package eu.kalafatic.gemini.bt.visual.models;

import java.util.HashMap;
import java.util.Map;

import eu.kalafatic.gemini.bt.visual.interfaces.IModel;

/**
 * The Class TreeModel.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class TreeModel implements IModel{
	
	/** The node map. */
	private Map<String, Node> nodeMap;
	
	/**
	 * Instantiates a new tree model.
	 */
	public TreeModel() {		
		this.nodeMap = new HashMap<String, Node>();
	}
	

	/**
	 * Gets the node map.
	 * 
	 * @return the node map
	 */
	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}

}
