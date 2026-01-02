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
package eu.kalafatic.gemini.bt.client.view.hack;

import java.io.IOException;
import java.io.Reader;

/**
 * The Class class SingleCharReader.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class SingleCharReader extends Reader {

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#read()
	 */
	@Override
	public abstract int read() throws IOException;

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#read(char[], int, int)
	 */
	@Override
	public int read(char cbuf[], int off, int len) throws IOException {
		int end = off + len;
		for (int i = off; i < end; i++) {
			int ch = read();
			if (ch == -1) {
				if (i == off) {
					return -1;
				}
				return i - off;
			}
			cbuf[i] = (char) ch;
		}
		return len;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#ready()
	 */
	@Override
	public boolean ready() throws IOException {
		return true;
	}

	/**
	 * Gets the string.
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String getString() throws IOException {
		StringBuffer buf = new StringBuffer();
		int ch;
		while ((ch = read()) != -1) {
			buf.append((char) ch);
		}
		return buf.toString();
	}
}