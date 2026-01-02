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
package eu.kalafatic.gemini.bt.tm.model.btStructureConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * The Enum enum ETorrentStructure.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public enum ETorrentStructure implements Enumerator {

	/** The ANNOUNCE. */
	ANNOUNCE(0, "ANNOUNCE", "announce"),

	/** The ANNOUNC e_ list. */
	ANNOUNCE_LIST(1, "ANNOUNCE_LIST", "announce list"),

	/** The CREATE d_ by. */
	CREATED_BY(2, "CREATED_BY", "created by"),

	/** The CREATIO n_ date. */
	CREATION_DATE(3, "CREATION_DATE", "creation date"),

	/** The ENCODING. */
	ENCODING(4, "ENCODING", "encoding"),

	/** The INFO. */
	INFO(5, "INFO", "info"),

	/** The NAME. */
	NAME(6, "NAME", "name"),

	/** The FILES. */
	FILES(7, "FILES", "files"),

	/** The LENGTH. */
	LENGTH(8, "LENGTH", "length"),

	/** The PATH. */
	PATH(9, "PATH", "path"),

	/** The PIECES. */
	PIECES(10, "PIECES", "pieces"),

	/** The PIEC e_ length. */
	PIECE_LENGTH(11, "PIECE_LENGTH", "piece length"),

	/** The PRIVATE. */
	PRIVATE(12, "PRIVATE", "private"),

	/** The M d5 sum. */
	MD5SUM(13, "MD5SUM", "md5sum"),

	/** The COMMENT. */
	COMMENT(14, "COMMENT", "comment");

	/** The Constant ANNOUNCE_VALUE. */
	public static final int ANNOUNCE_VALUE = 0;

	/** The Constant ANNOUNCE_LIST_VALUE. */
	public static final int ANNOUNCE_LIST_VALUE = 1;

	/** The Constant CREATED_BY_VALUE. */
	public static final int CREATED_BY_VALUE = 2;

	/** The Constant CREATION_DATE_VALUE. */
	public static final int CREATION_DATE_VALUE = 3;

	/** The Constant ENCODING_VALUE. */
	public static final int ENCODING_VALUE = 4;

	/** The Constant INFO_VALUE. */
	public static final int INFO_VALUE = 5;

	/** The Constant NAME_VALUE. */
	public static final int NAME_VALUE = 6;

	/** The Constant FILES_VALUE. */
	public static final int FILES_VALUE = 7;

	/** The Constant LENGTH_VALUE. */
	public static final int LENGTH_VALUE = 8;

	/** The Constant PATH_VALUE. */
	public static final int PATH_VALUE = 9;

	/** The Constant PIECES_VALUE. */
	public static final int PIECES_VALUE = 10;

	/** The Constant PIECE_LENGTH_VALUE. */
	public static final int PIECE_LENGTH_VALUE = 11;

	/** The Constant PRIVATE_VALUE. */
	public static final int PRIVATE_VALUE = 12;

	/** The Constant MD5SUM_VALUE. */
	public static final int MD5SUM_VALUE = 13;

	/** The Constant COMMENT_VALUE. */
	public static final int COMMENT_VALUE = 14;

	/** The Constant VALUES_ARRAY. */
	private static final ETorrentStructure[] VALUES_ARRAY = new ETorrentStructure[] { ANNOUNCE, ANNOUNCE_LIST, CREATED_BY, CREATION_DATE, ENCODING, INFO, NAME, FILES, LENGTH, PATH, PIECES,
			PIECE_LENGTH, PRIVATE, MD5SUM, COMMENT, };

	/** The Constant VALUES. */
	public static final List<ETorrentStructure> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Gets the.
	 * @param literal the literal
	 * @return the e torrent structure
	 */
	public static ETorrentStructure get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETorrentStructure result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the by name.
	 * @param name the name
	 * @return the by name
	 */
	public static ETorrentStructure getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETorrentStructure result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Gets the.
	 * @param value the value
	 * @return the e torrent structure
	 */
	public static ETorrentStructure get(int value) {
		switch (value) {
		case ANNOUNCE_VALUE:
			return ANNOUNCE;
		case ANNOUNCE_LIST_VALUE:
			return ANNOUNCE_LIST;
		case CREATED_BY_VALUE:
			return CREATED_BY;
		case CREATION_DATE_VALUE:
			return CREATION_DATE;
		case ENCODING_VALUE:
			return ENCODING;
		case INFO_VALUE:
			return INFO;
		case NAME_VALUE:
			return NAME;
		case FILES_VALUE:
			return FILES;
		case LENGTH_VALUE:
			return LENGTH;
		case PATH_VALUE:
			return PATH;
		case PIECES_VALUE:
			return PIECES;
		case PIECE_LENGTH_VALUE:
			return PIECE_LENGTH;
		case PRIVATE_VALUE:
			return PRIVATE;
		case MD5SUM_VALUE:
			return MD5SUM;
		case COMMENT_VALUE:
			return COMMENT;
		}
		return null;
	}

	/** The value. */
	private final int value;

	/** The name. */
	private final String name;

	/** The literal. */
	private final String literal;

	/**
	 * Instantiates a new e torrent structure.
	 * @param value the value
	 * @param name the name
	 * @param literal the literal
	 */
	private ETorrentStructure(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getValue()
	 */
	public int getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.Enumerator#getLiteral()
	 */
	public String getLiteral() {
		return literal;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return literal;
	}

} // ETorrentStructure
