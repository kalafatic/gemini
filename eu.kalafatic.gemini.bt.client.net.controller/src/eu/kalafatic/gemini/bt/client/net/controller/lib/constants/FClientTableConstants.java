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
package eu.kalafatic.gemini.bt.client.net.controller.lib.constants;

/**
 * The Class class FClientTableConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class FClientTableConstants {

	// torrent details table
	/** The Constant VALUE_COL. */
	public static final String VALUE_COL = "Value";

	/** The Constant DESCRIPTION_COL. */
	public static final String DESCRIPTION_COL = "Description";

	/** The Constant BITMAP_ROW. */
	public static final String BITMAP_ROW = "Bitmap";

	/** The Constant NAME_COL. */
	public static final String NAME_COL = "Name";

	/** The Constant SETTING_COL. */
	public static final String SETTING_COL = "Setting";

	/** The Constant PROTOCOL_ROW. */
	public static final String PROTOCOL_ROW = "Protocol";

	/** The Constant HOST_ROW. */
	public static final String HOST_ROW = "Host";

	/** The Constant PORT_ROW. */
	public static final String PORT_ROW = "Port";

	/** The Constant APP_ROW. */
	public static final String APP_ROW = "Application";

	/** The Constant DESCRIPTION_ROW. */
	public static final String DESCRIPTION_ROW = "Description";

	// ---------------------------------------------------------------

	// client detail table
	// Set column names
	/** The Constant CLIENT_DETAILS_COL_NAMES. */
	public static final String[] CLIENT_DETAILS_COL_NAMES = new String[] { NAME_COL, VALUE_COL, DESCRIPTION_COL };

	// ---------------------------------------------------------------

	// Set column names
	/** The Constant CLIENT_DETAILS_ROW_NAMES. */
	public static final String[] CLIENT_DETAILS_ROW_NAMES = new String[] { PROTOCOL_ROW, HOST_ROW, PORT_ROW, APP_ROW, BITMAP_ROW, DESCRIPTION_ROW };
}
