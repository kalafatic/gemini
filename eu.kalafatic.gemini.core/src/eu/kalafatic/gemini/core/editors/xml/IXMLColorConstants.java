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

import org.eclipse.swt.graphics.RGB;

/**
 * The Interface interface IXMLColorConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface IXMLColorConstants {

	/** The xml comment. */
	RGB XML_COMMENT = new RGB(128, 0, 0);

	/** The proc instr. */
	RGB PROC_INSTR = new RGB(128, 128, 128);

	/** The string. */
	RGB STRING = new RGB(0, 128, 0);

	/** The default. */
	RGB DEFAULT = new RGB(0, 0, 0);

	/** The tag. */
	RGB TAG = new RGB(0, 0, 128);
}
