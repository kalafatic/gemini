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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.lib.ECorePreferences;

/**
 * The Class class FHandlerConstants.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class FHandlerConstants {

	/** The Constant MAX_UPLOAD. */
	public static final int MAX_UPLOAD = 4;

	// ---------------------------------------------------------------

	/** The Constant DISC_BUFFER. */
	public static final int DISC_BUFFER = PREFERENCES.getInt(ECorePreferences.DISC_BUFFER.getName(), (Integer) ECorePreferences.DISC_BUFFER.getDef()) * 1024 * 1024;

	/** The Constant TRANSPORT_BLOCK_SIZE. */
	public static final int TRANSPORT_BLOCK_SIZE = Activator.getPreferences().getInt(EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), (Integer) EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getDef());

	/** The requested pieces. */
	public static int REQUESTED_PIECES = Activator.getPreferences().getInt(EBTClientPreferences.REQUESTED_PIECES.getName(), (Integer) EBTClientPreferences.REQUESTED_PIECES.getDef());

	/** The requests. */
	public static int REQUESTS = Activator.getPreferences().getInt(EBTClientPreferences.REQUESTS.getName(), (Integer) EBTClientPreferences.REQUESTS.getDef());

	/** The blocks in request. */
	public static int BLOCKS_IN_REQUEST = Activator.getPreferences().getInt(EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), (Integer) EBTClientPreferences.BLOCKS_IN_REQUEST.getDef());

	/** The Constant MAX_DWNLD_THREADS. */
	public static final int MAX_DWNLD_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_DWNLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_DWNLD_THREADS.getDef());

	/** The Constant MAX_UPLD_THREADS. */
	public static final int MAX_UPLD_THREADS = Activator.getPreferences().getInt(EBTClientPreferences.MAX_UPLD_THREADS.getName(), (Integer) EBTClientPreferences.MAX_UPLD_THREADS.getDef());

}
