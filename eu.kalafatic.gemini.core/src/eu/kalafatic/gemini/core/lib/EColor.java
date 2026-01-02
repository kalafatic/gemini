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
package eu.kalafatic.gemini.core.lib;

import org.eclipse.swt.graphics.Color;

import eu.kalafatic.gemini.core.lib.constants.FUIConstants;

/**
 * The Enum enum EColor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EColor {

	/** The empty. */
	EMPTY(0, FUIConstants.GRAY),

	/** The dwnld. */
	DWNLD(1, FUIConstants.ORANGE_COLOR),

	/** The fin. */
	FIN(2, FUIConstants.GREEN),

	/** The upld. */
	UPLD(3, FUIConstants.DARK_GREEN),

	/** The lock. */
	LOCK(4, FUIConstants.GRADIENT),

	/** The error. */
	ERROR(5, FUIConstants.LIGHT_RED), ;

	/** The value. */
	public int value;

	/** The color. */
	public Color color;

	/**
	 * Instantiates a new e color.
	 * @param value the value
	 * @param color the color
	 */
	private EColor(int value, Color color) {
		this.value = value;
		this.color = color;
	}
}
