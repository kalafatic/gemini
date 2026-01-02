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

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CS_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.DE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.EN_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ES_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.RU_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ZH_IMG;

import org.eclipse.swt.graphics.Image;

/**
 * The Enum enum ELang.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ELang {

	/** The cs. */
	CS(0, "CS", CS_IMG),

	/** The en. */
	EN(1, "EN", EN_IMG),

	/** The de. */
	DE(2, "DE", DE_IMG),

	/** The zh. */
	ZH(3, "ZH", ZH_IMG),

	/** The ru. */
	RU(4, "RU", RU_IMG),

	/** The es. */
	ES(5, "ES", ES_IMG);

	/** The index. */
	public int index;

	/** The literal. */
	public String literal;

	/** The image. */
	public Image image;

	/**
	 * Instantiates a new e lang.
	 * @param index the index
	 * @param literal the literal
	 * @param image the image
	 */
	ELang(int index, String literal, Image image) {
		this.index = index;
		this.literal = literal;
		this.image = image;
	}
}
