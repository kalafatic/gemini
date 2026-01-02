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
package eu.kalafatic.gemini.bt.tracker.controller.providers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * The Class class ColumnSorterProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ColumnSorterProvider extends ViewerSorter {

	/** The Constant ASCENDING. */
	private static final int ASCENDING = 0;

	/** The Constant DESCENDING. */
	private static final int DESCENDING = 1;

	/** The column. */
	private int column;

	/** The direction. */
	private int direction = DESCENDING;

	/**
	 * Instantiates a new column sorter provider.
	 */
	public ColumnSorterProvider() {
		this.column = 0;
		direction = ASCENDING;

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Do sort.
	 * @param column the column
	 */
	public void doSort(int column) {
		if (column == this.column) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public int compare(Viewer viewer, Object o1, Object o2) {

		// ExtTorrent extTorrent1;
		// ExtTorrent extTorrent2;
		//
		int result = 0;
		//
		// if (o1 instanceof Entry) {
		// extTorrent1 = (ExtTorrent) ((Entry) o1).getValue();
		// } else {
		// return result;
		// }
		//
		// if (o2 instanceof Entry) {
		// extTorrent2 = (ExtTorrent) ((Entry) o2).getValue();
		// } else {
		// return result;
		// }
		//
		// switch (column) {
		// case 0:
		// result = extTorrent1.getRow() > extTorrent2.getRow() ? 1 : -1;
		// break;
		// case 1:
		// result = extTorrent1.getName().compareToIgnoreCase(
		// extTorrent2.getName());
		// break;
		//
		// default:
		// result = 0;
		// }
		// // If descending order, flip the direction
		// if (direction == DESCENDING) {
		// result = -result;
		// }
		return result;
	}

}
