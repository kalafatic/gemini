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
package eu.kalafatic.gemini.core.models;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class class SharedModel.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SharedModel {

	/** The name. */
	public String name;

	/** The elements. */
	public Map<String, Element> elements = new HashMap<String, Element>();

	/**
	 * The Class class Element.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public class Element extends SharedModel {

		/** The path. */
		public String path;
	}

}
