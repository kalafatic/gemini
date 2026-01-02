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
package eu.kalafatic.gemini.bt.visual.lib;

/**
 * The Interface IAlgorithmsConstants.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public interface IAlgorithmsConstants {

	/** The SHORTES t_ path. */
	public static int SHORTEST_PATH = 0;
	
	/** The LONGES t_ path. */
	public static int LONGEST_PATH = 1;

	/** The SHORTES t_ pat h_ alg. */
	public static String[] SHORTEST_PATH_ALG = { "Dijkstra", "Bellman-Ford",
			"A* search", "Floyd-Warshall", "Johnson" };

	/** The LONGES t_ pat h_ alg. */
	public static String[] LONGEST_PATH_ALG = { "" };

	/** The NOD e_ ha s_ n o_ children. */
	public static int NODE_HAS_NO_CHILDREN = -1;
	
	/** The PAT h_ no t_ found. */
	public static int PATH_NOT_FOUND = 0;
	
	/** The PAT h_ found. */
	public static int PATH_FOUND = 1;
	
	/** The SEARCHING. */
	public static int SEARCHING = 2;
	
	
	
	
}
