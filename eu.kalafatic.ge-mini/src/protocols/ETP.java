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
 * The Enum ETP.
 */
public enum ETP {

	/** The CHOKE. */
	CHOKE(new byte[] { 0, 0, 0, 1, 0 }),

	/** The UNCHOKE. */
	UNCHOKE(new byte[] { 0, 0, 0, 1, 1 }),

	/** The INTERESTED. */
	INTERESTED(new byte[] { 0, 0, 0, 1, 2 }),

	/** The NO t_ interested. */
	NOT_INTERESTED(new byte[] { 0, 0, 0, 1, 3 }),

	/** The HAVE. */
	HAVE(new byte[] { 0, 0, 0, 1, 4 }),

	/** The BITFIELD. */
	BITFIELD(new byte[] { 0, 0, 0, 1, 5 }),

	/** The REQUEST. */
	REQUEST(new byte[] { 0, 0, 0, 13, 6 }),

	/** The PIECE. */
	PIECE(new byte[] { 0, 0, 0, 1, 7 }),

	/** The CANCEL. */
	CANCEL(new byte[] { 0, 0, 0, 1, 8 }),

	/** The PORT. */
	PORT(new byte[] { 0, 0, 0, 1, 9 }),

	/** The HANDSHAKE. */
	HANDSHAKE(new byte[] { 0, 0, 0, 1, 84 });

	/** The header. */
	public byte[] header;

	/**
	 * Instantiates a new eTP.
	 * 
	 * @param header
	 *            the header
	 */
	private ETP(byte[] header) {
		this.header = header;
	}
}
