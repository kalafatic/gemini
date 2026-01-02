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

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.APP_SIZE;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

/**
 * The Class class HTMLTextPresenter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class HTMLTextPresenter
/* extends FocusedInformationPresenter */implements DefaultInformationControl.IInformationPresenter, DefaultInformationControl.IInformationPresenterExtension {

	/** The size. */
	Point size;

	/** The Constant LINE_DELIM. */
	private static final String LINE_DELIM = System.getProperty("line.separator", "\n");

	/** The counter. */
	private int fCounter;

	/** The enforce upper line limit. */
	private boolean fEnforceUpperLineLimit;

	/**
	 * Instantiates a new HTML text presenter.
	 * @param enforceUpperLineLimit the enforce upper line limit
	 */
	public HTMLTextPresenter(boolean enforceUpperLineLimit) {
		super();
		fEnforceUpperLineLimit = enforceUpperLineLimit;
	}

	/**
	 * Instantiates a new HTML text presenter.
	 */
	public HTMLTextPresenter() {
		this(true);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the reader.
	 * @param hoverInfo the hover info
	 * @param presentation the presentation
	 * @return the reader
	 */
	protected Reader createReader(String hoverInfo, TextPresentation presentation) {
		return new HTML2TextReader(new StringReader(hoverInfo), presentation);
	}

	// ---------------------------------------------------------------

	/**
	 * Adapt text presentation.
	 * @param presentation the presentation
	 * @param offset the offset
	 * @param insertLength the insert length
	 */
	protected void adaptTextPresentation(TextPresentation presentation, int offset, int insertLength) {

		int yoursStart = offset;
		int yoursEnd = offset + insertLength - 1;
		yoursEnd = Math.max(yoursStart, yoursEnd);

		Iterator e = presentation.getAllStyleRangeIterator();
		while (e.hasNext()) {

			StyleRange range = (StyleRange) e.next();

			int myStart = range.start;
			int myEnd = range.start + range.length - 1;
			myEnd = Math.max(myStart, myEnd);

			if (myEnd < yoursStart) {
				continue;
			}
			if (myStart < yoursStart) {
				range.length += insertLength;
			} else {
				range.start += insertLength;
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Append.
	 * @param buffer the buffer
	 * @param string the string
	 * @param presentation the presentation
	 */
	private void append(StringBuffer buffer, String string, TextPresentation presentation) {

		int length = string.length();
		buffer.append(string);

		if (presentation != null) {
			adaptTextPresentation(presentation, fCounter, length);
		}
		fCounter += length;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the indent.
	 * @param line the line
	 * @return the indent
	 */
	private String getIndent(String line) {
		int length = line.length();

		int i = 0;
		while (i < length && Character.isWhitespace(line.charAt(i))) {
			++i;
		}
		return (i == length ? line : line.substring(0, i)) + " "; //$NON-NLS-1$
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.kalafatic.gemini.bt.client.view.hack.DefaultInformationControl.IInformationPresenter#updatePresentation(org.eclipse.swt.widgets.Display,
	 * java.lang.String, org.eclipse.jface.text.TextPresentation, int, int)
	 */
	@Override
	public String updatePresentation(Display display, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight) {

		return hoverInfo;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.kalafatic.gemini.bt.client.view.hack.DefaultInformationControl.IInformationPresenterExtension#updatePresentation(org.eclipse.swt.graphics
	 * .Drawable, java.lang.String, org.eclipse.jface.text.TextPresentation, int, int)
	 */
	@Override
	public String updatePresentation(Drawable drawable, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight) {

		maxWidth = APP_SIZE.x / 2;
		maxHeight = APP_SIZE.y / 3;

		if (hoverInfo == null) {
			return null;
		}
		GC gc = new GC(drawable);
		try {
			StringBuffer buffer = new StringBuffer();
			int maxNumberOfLines = Math.round(maxHeight / gc.getFontMetrics().getHeight());

			fCounter = 0;
			LineBreakingReader reader = new LineBreakingReader(createReader(hoverInfo, presentation), gc, maxWidth);

			boolean lastLineFormatted = false;
			String lastLineIndent = null;

			String line = reader.readLine();
			boolean lineFormatted = reader.isFormattedLine();
			boolean firstLineProcessed = false;

			while (line != null) {
				if (fEnforceUpperLineLimit && maxNumberOfLines <= 0) {
					break;
				}
				if (firstLineProcessed) {
					if (!lastLineFormatted) {
						append(buffer, LINE_DELIM, null);
					} else {
						append(buffer, LINE_DELIM, presentation);
						if (lastLineIndent != null) {
							append(buffer, lastLineIndent, presentation);
						}
					}
				}
				append(buffer, line, null);
				firstLineProcessed = true;

				lastLineFormatted = lineFormatted;
				if (!lineFormatted) {
					lastLineIndent = null;
				} else if (lastLineIndent == null) {
					lastLineIndent = getIndent(line);
				}
				line = reader.readLine();
				lineFormatted = reader.isFormattedLine();

				maxNumberOfLines--;
			}
			if (line != null) {
				append(buffer, LINE_DELIM, lineFormatted ? presentation : null);
			}
			return trim(buffer, presentation);
		} catch (IOException e) {
			return null;
		} finally {
			gc.dispose();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Trim.
	 * @param buffer the buffer
	 * @param presentation the presentation
	 * @return the string
	 */
	private String trim(StringBuffer buffer, TextPresentation presentation) {
		int length = buffer.length();

		int end = length - 1;
		while (end >= 0 && Character.isWhitespace(buffer.charAt(end))) {
			--end;
		}
		if (end == -1) {
			return "";
		}
		if (end < length - 1) {
			buffer.delete(end + 1, length);
		} else {
			end = length;
		}
		int start = 0;
		while (start < end && Character.isWhitespace(buffer.charAt(start))) {
			++start;
		}
		buffer.delete(0, start);
		presentation.setResultWindow(new Region(start, buffer.length()));
		return buffer.toString();
	}
}
