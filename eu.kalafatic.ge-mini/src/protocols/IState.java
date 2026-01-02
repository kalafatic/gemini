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
package protocols;

/**
 * The Interface IState.
 */
public interface IState {
	// torrent
	/** The INITED. */
	public int INITED = 1 << 21;

	/** The FINISHED. */
	public int FINISHED = 1 << 22;

	// // session
	// /** The CHOKING. */
	// public int CHOKING = 1 << ETP.CHOKE.header[4];
	//
	// /** The INTERESTED. */
	// public int INTERESTED = 1 << ETP.INTERESTED.header[4];
	//
	// /** The KEE p_ alive. */
	// public int KEEP_ALIVE = 1 << 29;
	//
	// /** The HANDSHAKED. */
	// public int HANDSHAKED = 1 << ETP.HANDSHAKE.header[4];

}
