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
package eu.kalafatic.gemini.core.editors.xml;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * The Class class XMLWhitespaceDetector.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class XMLWhitespaceDetector implements IWhitespaceDetector {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
	 */
	@Override
	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}

}
