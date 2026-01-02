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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.eclipse.swt.graphics.GC;

import com.ibm.icu.text.BreakIterator;

/*
 * Not a real reader. Could change if requested
 */
/**
 * The Class class LineBreakingReader.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class LineBreakingReader {

	/** The reader. */
	private BufferedReader fReader;

	/** The gc. */
	private GC fGC;

	/** The max width. */
	private int fMaxWidth;

	/** The line. */
	private String fLine;

	/** The offset. */
	private int fOffset;

	/** The line break iterator. */
	private BreakIterator fLineBreakIterator;

	/** The break words. */
	private boolean fBreakWords;

	/**
	 * Instantiates a new line breaking reader.
	 * @param reader the reader
	 * @param gc the gc
	 * @param maxLineWidth the max line width
	 */
	public LineBreakingReader(Reader reader, GC gc, int maxLineWidth) {
		fReader = new BufferedReader(reader);
		fGC = gc;
		fMaxWidth = maxLineWidth;
		// fMaxWidth = 1024;
		fOffset = 0;
		fLine = null;
		fLineBreakIterator = BreakIterator.getLineInstance();
		fBreakWords = true;
	}

	/**
	 * Checks if is formatted line.
	 * @return true, if is formatted line
	 */
	public boolean isFormattedLine() {
		return fLine != null;
	}

	/**
	 * Read line.
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String readLine() throws IOException {
		if (fLine == null) {
			String line = fReader.readLine();
			if (line == null) {
				return null;
			}

			int lineLen = fGC.textExtent(line).x;
			if (lineLen < fMaxWidth) {
				return line;
			}
			fLine = line;
			fLineBreakIterator.setText(line);
			fOffset = 0;
		}
		int breakOffset = findNextBreakOffset(fOffset);
		String res;
		if (breakOffset != BreakIterator.DONE) {
			res = fLine.substring(fOffset, breakOffset);
			fOffset = findWordBegin(breakOffset);
			if (fOffset == fLine.length()) {
				fLine = null;
			}
		} else {
			res = fLine.substring(fOffset);
			fLine = null;
		}
		return res;
	}

	/**
	 * Find next break offset.
	 * @param currOffset the curr offset
	 * @return the int
	 */
	private int findNextBreakOffset(int currOffset) {
		int currWidth = 0;
		int nextOffset = fLineBreakIterator.following(currOffset);
		while (nextOffset != BreakIterator.DONE) {
			String word = fLine.substring(currOffset, nextOffset);
			int wordWidth = fGC.textExtent(word).x;
			int nextWidth = wordWidth + currWidth;
			if (nextWidth > fMaxWidth) {
				if (currWidth > 0) {
					return currOffset;
				}

				if (!fBreakWords) {
					return nextOffset;
				}

				// need to fit into fMaxWidth
				int length = word.length();
				while (length >= 0) {
					length--;
					word = word.substring(0, length);
					wordWidth = fGC.textExtent(word).x;

					if (wordWidth + currWidth < fMaxWidth) {
						return currOffset + length;
					}
				}
				return nextOffset;
			}
			currWidth = nextWidth;
			currOffset = nextOffset;
			nextOffset = fLineBreakIterator.next();
		}
		return nextOffset;
	}

	/**
	 * Find word begin.
	 * @param idx the idx
	 * @return the int
	 */
	private int findWordBegin(int idx) {
		while (idx < fLine.length() && Character.isWhitespace(fLine.charAt(idx))) {
			idx++;
		}
		return idx;
	}
}
