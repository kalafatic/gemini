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

import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.DOC_ASSOC;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.DOC_COMMAND;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.FILE_COMMAND;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.IMG_ASSOC;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.IMG_COMMAND;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.PDF_ASSOC;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.PDF_COMMAND;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.TXT_ASSOC;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.TXT_COMMAND;

import java.util.List;

/**
 * The Enum enum ECmd.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ECmd {

	/** The open file. */
	OPEN_FILE(null, FILE_COMMAND),

	/** The open txt. */
	OPEN_TXT(TXT_ASSOC, TXT_COMMAND),

	/** The open doc. */
	OPEN_DOC(DOC_ASSOC, DOC_COMMAND),

	/** The open pdf. */
	OPEN_PDF(PDF_ASSOC, PDF_COMMAND),

	/** The open img. */
	OPEN_IMG(IMG_ASSOC, IMG_COMMAND)

	;

	/** The assoc. */
	public List<String> assoc;

	/** The command. */
	public String command;

	/**
	 * Instantiates a new e cmd.
	 * @param index the index
	 * @param assoc the assoc
	 * @param ecmd the ecmd
	 */
	ECmd(List<String> assoc, String ecmd) {
		this.assoc = assoc;
		this.command = ecmd;
	}
}
