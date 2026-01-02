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

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ANNOUNCE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ANNOUNCE_LIST;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.COMMENT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.CREATED_BY;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.CREATION_DATE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ENCODING;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.FILES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.INFO;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LENGTH;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.NAME;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PATH;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PIECES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PIECE_LENGTH;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PRIVATE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.lib.EHash;
import eu.kalafatic.gemini.core.utils.ConvertUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.HashUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TEncoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TEncoder extends ATEditor {

	/** The buffer. */
	private StringBuffer buffer;

	/** The hash buffer. */
	private ByteBuffer hashBuffer;

	/** The finished. */
	private boolean finished;

	/** The file length. */
	private long fileLength;

	/** The piece length. */
	private int pieceLength;

	/** The pieces count. */
	private int piecesCount;

	/** The Constant INSTANCE. */
	public final static TEncoder INSTANCE = new TEncoder();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @param extTorrent the ext torrent
	 */
	private void init(ExtTorrent extTorrent) {
		super.extTorrent = extTorrent;

		this.fileLength = extTorrent.getAdditionalInfo().getFileSize();
		this.pieceLength = extTorrent.getInfo().getPieceLength();

		if ((fileLength % pieceLength) > 0) {
			piecesCount = (int) ((fileLength / pieceLength) + 1);
		} else {
			piecesCount = (int) (fileLength / pieceLength);
			if (piecesCount == 0) {
				piecesCount = 1;
			}
		}
		this.hashBuffer = ByteBuffer.allocateDirect(piecesCount * 20);
	}

	// ---------------------------------------------------------------

	/**
	 * Encode.
	 * @param extTorrent the ext torrent
	 * @return the map
	 */
	public Map encode(ExtTorrent extTorrent) {
		init(extTorrent);
		return encode(extTorrent, new HashMap());
	}

	// ---------------------------------------------------------------

	/**
	 * Encode and write.
	 * @param extTorrent the ext torrent
	 * @return true, if successful
	 */
	public boolean encodeAndWrite(ExtTorrent extTorrent) {
		try {
			Map map = encode(extTorrent);
			byte[] encoded = BEncoder.encode(map);
			FileUtils.getInstance().writeFile(extTorrent.getPath(), encoded);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Encode.
	 * @param extTorrent the ext torrent
	 * @param map the map
	 * @return the map
	 */
	private Map encode(ExtTorrent extTorrent, Map map) {
		map.put(CREATED_BY, extTorrent.getCreationBy());
		map.put(ENCODING, extTorrent.getEncoding());
		// long (ms) to int (s)
		map.put(CREATION_DATE, extTorrent.getCreationDate() / 1000);
		map.put(ANNOUNCE, extTorrent.getAnnounceList().get(0).getBytes());

		List<byte[]> announceList = new ArrayList<byte[]>();
		for (String announce : extTorrent.getAnnounceList()) {
			announceList.add(announce.getBytes());
		}
		map.put(ANNOUNCE_LIST, announceList);
		map.put(COMMENT, extTorrent.getComment());

		loadInfo(map);

		extTorrent.setMap(map);
		return map;
	}

	// ---------------------------------------------------------------

	/**
	 * Load info.
	 * @param map the map
	 */

	private void loadInfo(Map map) {
		Map infoMap = new HashMap();
		map.put(INFO, infoMap);

		setBasicInfo(infoMap);
		setMultipleFileMode(infoMap);
		// set info hash
		byte[] encoded = BEncoder.encode(infoMap);
		extTorrent.getInfo().setHash(HashUtils.getInstance().getHash(encoded, EHash.SHA_1));
		// set all file size and pieces per file
		setFileSizeAndPieces(extTorrent);
		// set bitmap
		setBitmap(extTorrent);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the basic info.
	 * @param infoMap the new basic info
	 */
	private void setBasicInfo(Map infoMap) {
		Info info = extTorrent.getInfo();

		infoMap.put(PIECES, info.getPieces());
		infoMap.put(PIECE_LENGTH, info.getPieceLength());
		infoMap.put(PRIVATE, info.isPrivate());
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the multiple file mode.
	 * @param infoMap the new multiple file mode
	 */
	private void setMultipleFileMode(Map infoMap) {
		List files = new ArrayList();
		infoMap.put(FILES, files);

		for (DataFile dataFile : extTorrent.getInfo().getFiles()) {
			Map fileMap = new HashMap();
			fileMap.put(NAME, dataFile.getName().getBytes());
			fileMap.put(LENGTH, dataFile.getLength());
			fileMap.put(PATH, dataFile.getPath().getBytes());
			files.add(fileMap);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Generate hash.
	 * @param bytes the bytes
	 */
	private void generateHash(byte[] bytes) {
		byte[] hash = HashUtils.getInstance().getHash(bytes, EHash.SHA_1);
		hashBuffer.put(hash);
	}

	// ---------------------------------------------------------------

	/**
	 * Read until.
	 * @param file the file
	 * @param offset the offset
	 * @param read the read
	 * @return the byte[]
	 */
	private byte[] readUntil(File file, long offset, int read) {
		byte[] bytes = new byte[read];
		RandomAccessFile raf = null;
		try {
			file.setReadable(true);
			raf = new RandomAccessFile(file, "r");
			raf.seek(offset);
			raf.read(bytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the hash.
	 * @param fileIndex the file index
	 * @param rest the rest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void createHash(int fileIndex, ByteBuffer rest) throws IOException {
		byte[] bytes;

		if (fileIndex == extTorrent.getInfo().getFiles().size()) { // finished
			if (rest.position() > 0) {
				int size = rest.position();
				rest.flip();
				bytes = new byte[size];
				rest.get(bytes);
				generateHash(bytes);
			}
			finished = true;
			return;
		}
		DataFile dataFile = extTorrent.getInfo().getFiles().get(fileIndex);
		File file = new File(dataFile.getPath());
		long offset = 0;
		long len = file.length();

		if (rest.position() > 0) {
			int read = pieceLength - rest.position();

			if (len < read) {
				read = (int) len;
			}

			bytes = readUntil(file, 0, read);

			rest.put(bytes);

			if (rest.position() < pieceLength) {
				createHash(fileIndex + 1, rest);
			} else {
				rest.flip();
				bytes = new byte[pieceLength];
				rest.get(bytes);

				generateHash(bytes);
				offset = read;
			}
		}
		// stop recursion
		if (finished) {
			return;
		}

		bytes = new byte[pieceLength];

		// here is clear state
		while ((offset + pieceLength) <= len) {

			bytes = readUntil(file, offset, pieceLength);
			generateHash(bytes);
			offset += pieceLength;
		}

		// rest of the file ( less then pieceLength )
		rest = ByteBuffer.allocateDirect(pieceLength);

		bytes = readUntil(file, offset, (int) (len - offset));
		rest.put(bytes);
		createHash(fileIndex + 1, rest);
	}

	// ---------------------------------------------------------------

	/**
	 * Write torrent.
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private File writeTorrent() throws IOException {
		BufferedOutputStream bufferedOutput = null;
		File torrentFile = null;
		try {
			torrentFile = new File(extTorrent.getPath());

			if (!torrentFile.getParentFile().exists()) {
				torrentFile.getParentFile().mkdirs();
			}
			if (!torrentFile.exists()) {
				torrentFile.createNewFile();
			}

			torrentFile.setWritable(true);
			bufferedOutput = new BufferedOutputStream(new FileOutputStream(torrentFile));

			String string = buffer.toString();

			bufferedOutput.write(string.getBytes(EEncoding.ISO_1.getLiteral()), 0, string.length());

		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		} finally {
			if (bufferedOutput != null) {
				bufferedOutput.flush();
				bufferedOutput.close();
			}
		}
		return torrentFile;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up bitfield.
	 */
	private void setUpBitfield() {
		int piecesCount = extTorrent.getInfo().getPieces().length / 20;

		int len = piecesCount / 8;
		byte[] realBitfield;

		if ((piecesCount % 8) > 0) {
			realBitfield = new byte[++len];
		} else {
			realBitfield = new byte[len];
		}
		Arrays.fill(realBitfield, (byte) -1);
		realBitfield = ConvertUtils.trimmLastPiece(realBitfield, len - 1, piecesCount % 8);
		extTorrent.setRealBitfield(realBitfield);

		boolean[] modelBitfield = new boolean[piecesCount];
		Arrays.fill(modelBitfield, true);
		extTorrent.setModelBitfield(modelBitfield);
	}
}
