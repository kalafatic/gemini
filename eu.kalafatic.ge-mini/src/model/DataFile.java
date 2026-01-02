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
package model;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * The Class DataFile.
 */
public class DataFile {

	/** The file. */
	public File file;
	
	/** The recorded. */
	public long length, start, end, recorded;
	
	/** The raf. */
	public RandomAccessFile raf = null;

	/**
	 * Instantiates a new data file.
	 * 
	 * @param file
	 *            the file
	 * @param start
	 *            the start
	 * @param length
	 *            the length
	 */
	public DataFile(File file, long start, long length) {
		this.file = file;
		this.start = start;
		this.length = length;
		this.end = start + length;
	}
}
