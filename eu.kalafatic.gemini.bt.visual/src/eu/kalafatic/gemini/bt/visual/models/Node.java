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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Table;

/**
 * The Class Node.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class Node {
	
	/** The name. */
	private String name;
	
	/** The previous node. */
	private Node previousNode;
	
	/** The price. */
	private int price;
	
	/** The distance. */
	private int distance;
	
	/** The processed. */
	private boolean processed=false;
	
	/** The coordinates. */
	private Point coordinates;
	
	/** The color. */
	private Color color;
	
	/** The table. */
	private Table table;

	/** The children. */
	public Map<String, Node> children;

	/**
	 * Instantiates a new node.
	 * 
	 * @param name
	 *            the name
	 */
	public Node(String name) {
		this.name = name;
		this.children = new HashMap<String, Node>();
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public Map<String, Node> getChildren() {
		return children;
	}

	/**
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price
	 *            the new price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Gets the distance.
	 * 
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance.
	 * 
	 * @param distance
	 *            the new distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Gets the coordinates.
	 * 
	 * @return the coordinates
	 */
	public Point getCoordinates() {
		return coordinates;
	}

	/**
	 * Sets the coordinates.
	 * 
	 * @param coordinates
	 *            the new coordinates
	 */
	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color
	 *            the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gets the table.
	 * 
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the table.
	 * 
	 * @param table
	 *            the new table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Checks if is processed.
	 * 
	 * @return true, if is processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * Sets the processed.
	 * 
	 * @param processed
	 *            the new processed
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/**
	 * Gets the previous node.
	 * 
	 * @return the previous node
	 */
	public Node getPreviousNode() {
		return previousNode;
	}

	/**
	 * Sets the previous node.
	 * 
	 * @param previousNode
	 *            the new previous node
	 */
	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

}
