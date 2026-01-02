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
import java.io.PushbackReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.text.TextPresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

/**
 * The Class class HTML2TextReader.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HTML2TextReader extends SubstitutionTextReader {

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/** The Constant fgEntityLookup. */
	private static final Map fgEntityLookup;

	/** The Constant fgTags. */
	private static final Set fgTags;

	static {
		fgTags = new HashSet();
		fgTags.add("b"); //$NON-NLS-1$
		fgTags.add("br"); //$NON-NLS-1$
		fgTags.add("br/"); //$NON-NLS-1$
		fgTags.add("div"); //$NON-NLS-1$
		fgTags.add("h1"); //$NON-NLS-1$
		fgTags.add("h2"); //$NON-NLS-1$
		fgTags.add("h3"); //$NON-NLS-1$
		fgTags.add("h4"); //$NON-NLS-1$
		fgTags.add("h5"); //$NON-NLS-1$
		fgTags.add("p"); //$NON-NLS-1$
		fgTags.add("dl"); //$NON-NLS-1$
		fgTags.add("dt"); //$NON-NLS-1$
		fgTags.add("dd"); //$NON-NLS-1$
		fgTags.add("li"); //$NON-NLS-1$
		fgTags.add("ul"); //$NON-NLS-1$
		fgTags.add("pre"); //$NON-NLS-1$
		fgTags.add("head"); //$NON-NLS-1$

		fgEntityLookup = new HashMap(7);
		fgEntityLookup.put("lt", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		fgEntityLookup.put("gt", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		fgEntityLookup.put("nbsp", " "); //$NON-NLS-1$ //$NON-NLS-2$
		fgEntityLookup.put("amp", "&"); //$NON-NLS-1$ //$NON-NLS-2$
		fgEntityLookup.put("circ", "^"); //$NON-NLS-1$ //$NON-NLS-2$
		fgEntityLookup.put("tilde", "~"); //$NON-NLS-2$ //$NON-NLS-1$
		fgEntityLookup.put("quot", "\""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** The counter. */
	private int fCounter = 0;

	/** The text presentation. */
	private TextPresentation fTextPresentation;

	/** The bold. */
	private int fBold = 0;

	/** The start offset. */
	private int fStartOffset = -1;

	/** The in paragraph. */
	private boolean fInParagraph = false;

	/** The is preformatted text. */
	private boolean fIsPreformattedText = false;

	/** The ignore. */
	private boolean fIgnore = false;

	/** The header detected. */
	private boolean fHeaderDetected = false;

	/**
	 * Instantiates a new HTM l2 text reader.
	 * @param reader the reader
	 * @param presentation the presentation
	 */
	public HTML2TextReader(Reader reader, TextPresentation presentation) {
		super(new PushbackReader(reader));
		fTextPresentation = presentation;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.SubstitutionTextReader#read()
	 */
	@Override
	public int read() throws IOException {
		int c = super.read();
		if (c != -1) {
			++fCounter;
		}
		return c;
	}

	/**
	 * Start bold.
	 */
	protected void startBold() {
		if (fBold == 0) {
			fStartOffset = fCounter;
		}
		++fBold;
	}

	/**
	 * Start preformatted text.
	 */
	protected void startPreformattedText() {
		fIsPreformattedText = true;
		setSkipWhitespace(false);
	}

	/**
	 * Stop preformatted text.
	 */
	protected void stopPreformattedText() {
		fIsPreformattedText = false;
		setSkipWhitespace(true);
	}

	/**
	 * Stop bold.
	 */
	protected void stopBold() {
		--fBold;
		if (fBold == 0) {
			if (fTextPresentation != null) {
				fTextPresentation.addStyleRange(new StyleRange(fStartOffset, fCounter - fStartOffset, null, null, SWT.BOLD));
			}
			fStartOffset = -1;
		}
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.text.SubstitutionTextReader#computeSubstitution (int)
	 */
	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.SubstitutionTextReader#computeSubstitution(int)
	 */
	@Override
	protected String computeSubstitution(int c) throws IOException {

		if (c == '<') {
			return processHTMLTag();
		} else if (fIgnore) {
			return EMPTY_STRING;
		} else if (c == '&') {
			return processEntity();
		} else if (fIsPreformattedText) {
			return processPreformattedText(c);
		}

		return null;
	}

	/**
	 * Html2 text.
	 * @param html the html
	 * @return the string
	 */
	private String html2Text(String html) {

		if (html == null || html.length() == 0) {
			return EMPTY_STRING;
		}

		html = html.toLowerCase();

		String tag = html;
		if ('/' == tag.charAt(0)) {
			tag = tag.substring(1);
		}

		if (!fgTags.contains(tag)) {
			return EMPTY_STRING;
		}

		if ("pre".equals(html)) { //$NON-NLS-1$
			startPreformattedText();
			return EMPTY_STRING;
		}

		if ("/pre".equals(html)) { //$NON-NLS-1$
			stopPreformattedText();
			return EMPTY_STRING;
		}

		if (fIsPreformattedText) {
			return EMPTY_STRING;
		}

		if ("b".equals(html)) { //$NON-NLS-1$
			startBold();
			return EMPTY_STRING;
		}

		if ((html.length() > 1 && html.charAt(0) == 'h' && Character.isDigit(html.charAt(1))) || "dt".equals(html)) { //$NON-NLS-1$
			startBold();
			return EMPTY_STRING;
		}

		if ("dl".equals(html)) {
			return LINE_DELIM;
		}

		if ("dd".equals(html)) {
			return "\t"; //$NON-NLS-1$
		}

		if ("li".equals(html)) {
			// FIXME: this hard-coded prefix does not work for RTL languages,
			// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=91682
			return LINE_DELIM;
		}

		if ("/b".equals(html)) { //$NON-NLS-1$
			stopBold();
			return EMPTY_STRING;
		}

		if ("p".equals(html)) { //$NON-NLS-1$
			fInParagraph = true;
			return LINE_DELIM;
		}

		if ("br".equals(html) || "br/".equals(html) || "div".equals(html)) {
			return LINE_DELIM;
		}

		if ("/p".equals(html)) { //$NON-NLS-1$
			boolean inParagraph = fInParagraph;
			fInParagraph = false;
			return inParagraph ? EMPTY_STRING : LINE_DELIM;
		}

		if ((html.startsWith("/h") && html.length() > 2 && Character.isDigit(html.charAt(2))) || "/dt".equals(html)) { //$NON-NLS-1$ //$NON-NLS-2$
			stopBold();
			return LINE_DELIM;
		}

		if ("/dd".equals(html)) {
			return LINE_DELIM;
		}

		if ("head".equals(html) && !fHeaderDetected) { //$NON-NLS-1$
			fHeaderDetected = true;
			fIgnore = true;
			return EMPTY_STRING;
		}

		if ("/head".equals(html) && fHeaderDetected && fIgnore) { //$NON-NLS-1$
			fIgnore = false;
			return EMPTY_STRING;
		}

		return EMPTY_STRING;
	}

	/*
	 * A '<' has been read. Process a html tag
	 */
	/**
	 * Process html tag.
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String processHTMLTag() throws IOException {

		StringBuffer buf = new StringBuffer();
		int ch;
		do {

			ch = nextChar();

			while (ch != -1 && ch != '>') {
				buf.append(Character.toLowerCase((char) ch));
				ch = nextChar();
				if (ch == '"') {
					buf.append(Character.toLowerCase((char) ch));
					ch = nextChar();
					while (ch != -1 && ch != '"') {
						buf.append(Character.toLowerCase((char) ch));
						ch = nextChar();
					}
				}
				if (ch == '<' && !isInComment(buf)) {
					unread(ch);
					return '<' + buf.toString();
				}
			}

			if (ch == -1) {
				return null;
			}

			if (!isInComment(buf) || isCommentEnd(buf)) {
				break;
			}
			// unfinished comment
			buf.append((char) ch);
		} while (true);

		return html2Text(buf.toString());
	}

	/**
	 * Checks if is in comment.
	 * @param buf the buf
	 * @return true, if is in comment
	 */
	private static boolean isInComment(StringBuffer buf) {
		return buf.length() >= 3 && "!--".equals(buf.substring(0, 3)); //$NON-NLS-1$
	}

	/**
	 * Checks if is comment end.
	 * @param buf the buf
	 * @return true, if is comment end
	 */
	private static boolean isCommentEnd(StringBuffer buf) {
		int tagLen = buf.length();
		return tagLen >= 5 && "--".equals(buf.substring(tagLen - 2)); //$NON-NLS-1$
	}

	/**
	 * Process preformatted text.
	 * @param c the c
	 * @return the string
	 */
	private String processPreformattedText(int c) {
		if (c == '\r' || c == '\n') {
			fCounter++;
		}
		return null;
	}

	/**
	 * Unread.
	 * @param ch the ch
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void unread(int ch) throws IOException {
		((PushbackReader) getReader()).unread(ch);
	}

	/**
	 * Entity2 text.
	 * @param symbol the symbol
	 * @return the string
	 */
	protected String entity2Text(String symbol) {
		if (symbol.length() > 1 && symbol.charAt(0) == '#') {
			int ch;
			try {
				if (symbol.charAt(1) == 'x') {
					ch = Integer.parseInt(symbol.substring(2), 16);
				} else {
					ch = Integer.parseInt(symbol.substring(1), 10);
				}
				return EMPTY_STRING + (char) ch;
			} catch (NumberFormatException e) {}
		} else {
			String str = (String) fgEntityLookup.get(symbol);
			if (str != null) {
				return str;
			}
		}
		return "&" + symbol; // not found //$NON-NLS-1$
	}

	/*
	 * A '&' has been read. Process a entity
	 */
	/**
	 * Process entity.
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String processEntity() throws IOException {
		StringBuffer buf = new StringBuffer();
		int ch = nextChar();
		while (Character.isLetterOrDigit((char) ch) || ch == '#') {
			buf.append((char) ch);
			ch = nextChar();
		}

		if (ch == ';') {
			return entity2Text(buf.toString());
		}

		buf.insert(0, '&');
		if (ch != -1) {
			buf.append((char) ch);
		}
		return buf.toString();
	}
}