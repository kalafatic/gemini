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

/**
 * The Enum enum EMimeType.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EMimeType {

	/** The txt. */
	TXT(0, "text/plain", EExt.TXT),

	/** The xml. */
	XML(1, "text/xml", EExt.XML),

	/** The jnlp. */
	JNLP(1, "application/x-java-jnlp-file", EExt.JNLP),

	/** The htm. */
	HTM(2, "text/html", EExt.HTM, EExt.HTML),

	/** The torrent. */
	TORRENT(3, "application/x-bittorrent", EExt.TORRENT),

	/** The jpeg. */
	JPEG(2, "image/jpeg", EExt.JPEG, EExt.JPG),

	/** The png. */
	PNG(2, "image/png", EExt.PNG),

	/** The gif. */
	GIF(2, "image/gif", EExt.GIF),

	/** The pdf. */
	PDF(2, "application/pdf", EExt.PDF),

	;

	/** The index. */
	public int index;

	/** The mime. */
	public String mime;

	/** The extensions. */
	public EExt[] extensions;

	/**
	 * Instantiates a new e mime type.
	 * @param index the index
	 * @param mime the mime
	 * @param extensions the extensions
	 */
	EMimeType(int index, String mime, EExt... extensions) {
		this.index = index;
		this.mime = mime;
		this.extensions = extensions;
	}
}
