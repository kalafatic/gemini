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
 * The Class FTP.
 */
public final class FTP {

	/** The Constant ENCODING_TYPE. */
	public static final String ENCODING_TYPE = "ISO-8859-1";

	public static final String HTTP = "http://";

	/** The Constant UDP. */
	public static final String UDP = "udp://";

	/** The Constant RESERVED. */
	public static final byte[] RESERVED = { 0, 0, 0, 0, 0, 0, 0, 0 };

	/** The Constant PROTOCOL_NAME. */
	public static final String PROTOCOL_NAME = "BitTorrent protocol";

	/** The Constant PEER_ID. */
	public static final String PEER_ID = "-GE2010-000000000000";

	/** The Constant ENCODING. */
	public static final String ENCODING = "encoding";

	/** The Constant CTEATION_DATE. */
	public static final String CTEATION_DATE = "creation date";

	/** The Constant CREATED_BY. */
	public static final String CREATED_BY = "created by";

	/** The Constant ANNOUNCE. */
	public static final String ANNOUNCE = "announce";

	/** The Constant ANNOUNCE_LIST. */
	public static final String ANNOUNCE_LIST = "announce-list";

	/** The Constant COMMENT. */
	public static final String COMMENT = "comment";

	/** The Constant INFO. */
	public static final String INFO = "info";

	/** The Constant FILES. */
	public static final String FILES = "files";

	/** The Constant NAME. */
	public static final String NAME = "name";

	/** The Constant LENGTH. */
	public static final String LENGTH = "length";

	/** The Constant PATH. */
	public static final String PATH = "path";

	/** The Constant PIECES. */
	public static final String PIECES = "pieces";

	/** The Constant PIECE_LENGTH. */
	public static final String PIECE_LENGTH = "piece length";

	public static final String PEERS = "peers";
}
