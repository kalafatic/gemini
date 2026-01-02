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
package eu.kalafatic.gemini.bt.client.net.view.lib.constants;

import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.CompositeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalShift;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.VerticalLayoutAlgorithm;

/**
 * The Enum enum ELayouts.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ELayouts {

	/** The RADIAL. */
	RADIAL(0, "Radial", new RadialLayoutAlgorithm(LayoutStyles.NONE)),

	/** The SPRING. */
	SPRING(1, "Spring", new SpringLayoutAlgorithm(LayoutStyles.NONE)),

	/** The DIRECTED. */
	DIRECTED(2, "Directed", new DirectedGraphLayoutAlgorithm(LayoutStyles.NONE)),

	/** The HORIZ. */
	HORIZ(3, "Horizontal", new HorizontalLayoutAlgorithm(LayoutStyles.NONE)),

	/** The VERT. */
	VERT(4, "Vertical", new VerticalLayoutAlgorithm(LayoutStyles.NONE)),

	/** The TRE e_ vert. */
	TREE_VERT(5, "Tree-V", new TreeLayoutAlgorithm(LayoutStyles.NONE)),

	/** The TRE e_ horiz. */
	TREE_HORIZ(6, "Tree-H", new HorizontalTreeLayoutAlgorithm(LayoutStyles.NONE)),

	/** The COMPOSITE. */
	COMPOSITE(7, "Composite", new CompositeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING, new LayoutAlgorithm[] { new DirectedGraphLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING),
			new HorizontalShift(LayoutStyles.NO_LAYOUT_NODE_RESIZING) })),

	/** The GRID. */
	GRID(8, "Grid", new GridLayoutAlgorithm(LayoutStyles.NONE))

	// CONTINUOUS(
	// 1, "Continuous", new ContinuousLayoutAlgorithm(LayoutStyles.NONE)),

	/** The index. */
	;

	public int index;

	/** The name. */
	public String name;

	/** The algorithm. */
	public LayoutAlgorithm algorithm;

	// Constructor
	/**
	 * Instantiates a new e layouts.
	 * @param index the index
	 * @param name the name
	 * @param algorithm the algorithm
	 */
	ELayouts(int index, String name, LayoutAlgorithm algorithm) {
		this.index = index;
		this.name = name;
		this.algorithm = algorithm;

	}

	/**
	 * Gets the layouts as text.
	 * @return the layouts as text
	 */
	public static String[] getLayoutsAsText() {
		String[] values = new String[ELayouts.values().length];
		int i = 0;
		for (ELayouts layout : ELayouts.values()) {
			values[i++] = layout.name;
		}
		return values;
	}

	/**
	 * Gets the algorithm.
	 * @param name the name
	 * @return the algorithm
	 */
	public static LayoutAlgorithm getAlgorithm(String name) {
		for (ELayouts layout : ELayouts.values()) {
			if (layout.name.equals(name)) {
				return layout.algorithm;
			}
		}
		return SPRING.algorithm;
	}

}
