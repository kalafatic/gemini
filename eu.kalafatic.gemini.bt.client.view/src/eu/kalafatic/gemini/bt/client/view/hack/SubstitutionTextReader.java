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
 * The Class class SubstitutionTextReader.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class SubstitutionTextReader extends SingleCharReader {

	/** The Constant LINE_DELIM. */
	protected static final String LINE_DELIM = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

	/** The reader. */
	private Reader fReader;

	/** The was white space. */
	protected boolean fWasWhiteSpace;

	/** The char after white space. */
	private int fCharAfterWhiteSpace;

	/** The skip white space. */
	private boolean fSkipWhiteSpace = true;

	/** The read from buffer. */
	private boolean fReadFromBuffer;

	/** The buffer. */
	private StringBuffer fBuffer;

	/** The index. */
	private int fIndex;

	/**
	 * Instantiates a new substitution text reader.
	 * @param reader the reader
	 */
	protected SubstitutionTextReader(Reader reader) {
		fReader = reader;
		fBuffer = new StringBuffer();
		fIndex = 0;
		fReadFromBuffer = false;
		fCharAfterWhiteSpace = -1;
		fWasWhiteSpace = true;
	}

	/**
	 * Compute substitution.
	 * @param c the c
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected abstract String computeSubstitution(int c) throws IOException;

	/**
	 * Gets the reader.
	 * @return the reader
	 */
	protected Reader getReader() {
		return fReader;
	}

	/**
	 * Next char.
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected int nextChar() throws IOException {
		fReadFromBuffer = (fBuffer.length() > 0);
		if (fReadFromBuffer) {
			char ch = fBuffer.charAt(fIndex++);
			if (fIndex >= fBuffer.length()) {
				fBuffer.setLength(0);
				fIndex = 0;
			}
			return ch;
		}

		int ch = fCharAfterWhiteSpace;
		if (ch == -1) {
			ch = fReader.read();
		}
		if (fSkipWhiteSpace && Character.isWhitespace((char) ch)) {
			do {
				ch = fReader.read();
			} while (Character.isWhitespace((char) ch));
			if (ch != -1) {
				fCharAfterWhiteSpace = ch;
				return ' ';
			}
		} else {
			fCharAfterWhiteSpace = -1;
		}
		return ch;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.SingleCharReader#read()
	 */
	@Override
	public int read() throws IOException {
		int c;
		do {

			c = nextChar();
			while (!fReadFromBuffer) {
				String s = computeSubstitution(c);
				if (s == null) {
					break;
				}
				if (s.length() > 0) {
					fBuffer.insert(0, s);
				}
				c = nextChar();
			}

		} while (fSkipWhiteSpace && fWasWhiteSpace && (c == ' '));
		fWasWhiteSpace = (c == ' ' || c == '\r' || c == '\n');
		return c;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.SingleCharReader#ready()
	 */
	@Override
	public boolean ready() throws IOException {
		return fReader.ready();
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#close()
	 */
	@Override
	public void close() throws IOException {
		fReader.close();
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#reset()
	 */
	@Override
	public void reset() throws IOException {
		fReader.reset();
		fWasWhiteSpace = true;
		fCharAfterWhiteSpace = -1;
		fBuffer.setLength(0);
		fIndex = 0;
	}

	/**
	 * Sets the skip whitespace.
	 * @param state the new skip whitespace
	 */
	protected final void setSkipWhitespace(boolean state) {
		fSkipWhiteSpace = state;
	}

	/**
	 * Checks if is skipping whitespace.
	 * @return true, if is skipping whitespace
	 */
	protected final boolean isSkippingWhitespace() {
		return fSkipWhiteSpace;
	}
}
