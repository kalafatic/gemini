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
package eu.kalafatic.gemini.bt.utils.decoders;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.core.utils.FileUtils;

/**
 * The Class class ATEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes" })
public abstract class ATEditor {

	/** The ext torrent. */
	protected ExtTorrent extTorrent;

	/** The info. */
	protected Info info;

	/** The root file. */
	protected File rootFile;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Sets the file size and pieces.
	 * @param extTorrent the new file size and pieces
	 */
	protected void setFileSizeAndPieces(ExtTorrent extTorrent) {
		long size = 0, offset = 0, fileLength, rest = 0;
		int maxPiecesPerFile = 0, index = 0, len = 0;
		long pieceLength = extTorrent.getInfo().getPieceLength();

		EList<DataFile> files = extTorrent.getInfo().getFiles();
		for (DataFile dataFile : files) {

			dataFile.setBegin(size);

			fileLength = dataFile.getLength();
			size += fileLength;

			dataFile.setEnd(size);

			fileLength -= offset;

			len = (int) (fileLength / pieceLength);
			len = (fileLength % pieceLength) > 0 ? len + 1 : len;

			rest = (len * pieceLength) - fileLength;

			len = offset > 0 ? len + 1 : len;
			offset = rest;

			maxPiecesPerFile = maxPiecesPerFile < len ? len : maxPiecesPerFile;

			int[] piecesPerFile = new int[len];
			for (int i = 0; i < piecesPerFile.length; i++) {
				piecesPerFile[i] = index++;
			}
			index = rest > 0 ? --index : index;

			dataFile.setPieces(piecesPerFile);
		}
		extTorrent.getInfo().setMaxPiecesPerFile(maxPiecesPerFile);
		extTorrent.getAdditionalInfo().setFileSize(size);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the bitmap.
	 * @param extTorrent the new bitmap
	 */
	protected void setBitmap(ExtTorrent extTorrent) {
		int piecesCount = extTorrent.getInfo().getPieces().length / 20;
		extTorrent.setModelBitfield(new boolean[piecesCount]);

		int len = (piecesCount / 8);
		len = (piecesCount % 8) > 0 ? len + 1 : len;

		byte[] realBitfield = new byte[len];

		if (extTorrent.isFinished()) {
			Arrays.fill(realBitfield, (byte) -1);

			int rest = piecesCount % 8;

			if (rest > 0) {
				trimmLastByte(len, rest, realBitfield);
			}
		}
		extTorrent.setRealBitfield(realBitfield);
	}

	// ---------------------------------------------------------------

	/**
	 * Trimm last byte.
	 * @param len the len
	 * @param rest the rest
	 * @param realBitfield the real bitfield
	 */
	private void trimmLastByte(int len, int rest, byte[] realBitfield) {
		byte lastByte = 0;

		for (int i = 0; i < rest; i++) {
			lastByte |= (byte) (1 << (7 - i));
		}
		realBitfield[len - 1] = lastByte;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrent map.
	 * @param extTorrent the ext torrent
	 * @param file the file
	 * @return the torrent map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected Map getTorrentMap(ExtTorrent extTorrent, File file) throws IOException {
		Map map = extTorrent.getMap();

		if (map == null) {
			byte[] bytesFromFile = FileUtils.getInstance().getBytesFromFile(file);
			map = BDecoder.decode(bytesFromFile);
			extTorrent.setMap(map);
		}
		return map;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the file name.
	 * @param file the file
	 * @param ext the ext
	 * @return the file name
	 */
	protected String getFileName(File file, String ext) {
		int lastIndexOf = file.getName().lastIndexOf(ext);
		return file.getName().substring(0, lastIndexOf);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the integer.
	 * @param object the object
	 * @return the integer
	 */
	protected Integer getInteger(Object object) {
		Integer integer = null;
		if (object instanceof Long) {
			Long l = (Long) object;
			integer = new Integer(l.intValue());
		} else {
			integer = (Integer) object;
		}
		return integer;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the string.
	 * @param value the value
	 * @return the string
	 */
	protected String getString(Object value) {
		if (value instanceof byte[]) {
			byte[] val = (byte[]) value;
			return new String(val);

		} else if (value instanceof List) {
			StringBuffer sb = new StringBuffer();
			ListIterator<?> iterator = ((List<?>) value).listIterator();

			while (iterator.hasNext()) {
				sb.append(new String((byte[]) iterator.next()) + "/");
			}
			return sb.toString();
		}
		return "";
	}

}
