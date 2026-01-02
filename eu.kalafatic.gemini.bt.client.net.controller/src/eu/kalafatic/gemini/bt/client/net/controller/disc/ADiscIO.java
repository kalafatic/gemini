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
package eu.kalafatic.gemini.bt.client.net.controller.disc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;

/**
 * The Class class ADiscIO.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
abstract class ADiscIO extends ReentrantLock {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new a disc io.
	 */
	public ADiscIO() {
		super(true);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the io.
	 * @param dataFiles the data files
	 * @param index the index
	 * @param position the position
	 * @param len the len
	 * @return the io
	 */
	List<Integer> getIO(EList<DataFile> dataFiles, int index, long position, int len) {
		List<Integer> ioLengts = new ArrayList<Integer>();
		setIO(dataFiles, index, position, len, ioLengts);
		return ioLengts;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the io.
	 * @param dataFiles the data files
	 * @param index the index
	 * @param position the position
	 * @param len the len
	 * @param ioLengts the io lengts
	 */
	private void setIO(EList<DataFile> dataFiles, int index, long position, int len, List<Integer> ioLengts) {

		DataFile dataFile = dataFiles.get(index);

		if ((position + len) <= dataFile.getEnd()) {
			ioLengts.add(len);
		} else {
			int size = (int) (dataFile.getEnd() - position);
			ioLengts.add(size);

			long nextPosition = dataFile.getEnd();
			int nextIndex = index + 1;
			int nextLen = len - size;

			setIO(dataFiles, nextIndex, nextPosition, nextLen, ioLengts);
		}
	}

}
