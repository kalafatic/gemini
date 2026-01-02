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
package model;

import static protocols.FTP.ANNOUNCE;
import static protocols.FTP.ANNOUNCE_LIST;
import static protocols.FTP.ENCODING_TYPE;
import static protocols.FTP.FILES;
import static protocols.FTP.INFO;
import static protocols.FTP.LENGTH;
import static protocols.FTP.NAME;
import static protocols.FTP.PEER_ID;
import static protocols.FTP.PIECES;
import static protocols.FTP.PIECE_LENGTH;
import static protocols.FTP.PROTOCOL_NAME;
import static protocols.FTP.RESERVED;
import io.AIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.Main;
import protocols.IState;
import utils.TDecoder;
import utils.TEncoder;
import utils.Utils;

/**
 * The Class Torrent.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Torrent implements IState {

	/** The name. */
	public String name;

	/** The bf file. */
	public File file, bfFile;

	/** The STATE. */
	public int STATE = FINISHED;

	/** The handshake. */
	public byte[] bitfield, hash, pieces, handshake;

	/** The hash string. */
	public String hashString;

	/** The all length. */
	public long uploaded, downloaded, left, allLength;

	/** The piece length. */
	public int pieceLength;

	/** The info map. */
	public Map<?, ?> contentMap, infoMap;

	/** The raf. */
	public RandomAccessFile raf;

	/** The trackers. */
	public List<String> trackers = new ArrayList<String>();

	/** The data files. */
	public Map<String, DataFile> dataFiles = new LinkedHashMap<String, DataFile>();

	/**
	 * Instantiates a new torrent.
	 * 
	 * @param name
	 *            the name
	 * @throws Exception
	 *             the exception
	 */
	public Torrent(String name) throws Exception {
		this(new File(name));
	}

	/**
	 * Instantiates a new torrent.
	 * 
	 * @param file
	 *            the file
	 * @throws Exception
	 *             the exception
	 */
	public Torrent(File file) throws Exception {
		this.file = file;
		this.name = file.getName();
	}

	/**
	 * Inits the.
	 * 
	 * @return the torrent
	 * @throws Exception
	 *             the exception
	 */
	public Torrent init() throws Exception {
		contentMap = TDecoder.decode(file);

		infoMap = (Map<?, ?>) contentMap.get(INFO);
		byte[] info = TEncoder.encode(infoMap);

		hash = Utils.getHash(info);
		hashString = URLEncoder.encode(new String(hash, ENCODING_TYPE),
				ENCODING_TYPE).replaceAll("\\+", "%20");

		pieces = (byte[]) infoMap.get(PIECES);
		pieceLength = Utils.getInteger(infoMap.get(PIECE_LENGTH));

		setBitfield();
		setDataFiles();
		setAnnounce();
		setHandshake();
		return this;
	}

	/**
	 * Sets the data files.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void setDataFiles() throws IOException {
		if ((STATE & INITED) == 0) {
			String name = new String((byte[]) infoMap.get(NAME));
			File dataFile = new File(name);

			if (infoMap.containsKey(FILES)) {
				dataFile.mkdirs();

				List<Map> files = (List<Map>) infoMap.get(FILES);
				for (Map map : files) {
					dataFile = new File(name.concat(Utils.getPath((List) map
							.get("path"))));
					dataFile.getParentFile().mkdirs();
					dataFile.createNewFile();
					long length = (Long) map.get(LENGTH);

					dataFiles.put(dataFile.getAbsolutePath(), new DataFile(
							dataFile, allLength, length));
					allLength += length;
				}
			} else {
				dataFile.createNewFile();
				long length = (Long) infoMap.get(LENGTH);
				dataFiles.put(dataFile.getAbsolutePath(), new DataFile(
						dataFile, allLength, length));
				allLength += length;
			}
		}
	}

	/**
	 * Sets the announce.
	 */
	private void setAnnounce() {
		if (contentMap.containsKey(ANNOUNCE_LIST)) {
			ArrayList list = (ArrayList) contentMap.get(ANNOUNCE_LIST);
			if (list.get(0) instanceof ArrayList) {
				list = (ArrayList) list.get(0);
			}
			for (Object object : list) {
				trackers.add(new String((byte[]) object));
			}
		} else if (contentMap.containsKey(ANNOUNCE)) {
			byte[] announce = (byte[]) contentMap.get(ANNOUNCE);
			trackers.add(new String(announce));
		}
	}

	/**
	 * Sets the bitfield.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void setBitfield() throws Exception {
		bfFile = new File(name + ".bitfield");
		if (bfFile.exists()) {
			STATE |= INITED;
			bitfield = AIO.readBitfield(bfFile);
		} else {
			bitfield = getBitfield();
			bfFile.createNewFile();
			AIO.writeBitfield(bfFile, bitfield);
		}
		raf = new RandomAccessFile(bfFile, "rw");
	}

	/**
	 * Gets the bitfield.
	 * 
	 * @return the bitfield
	 */
	private byte[] getBitfield() {
		int piecesCount = pieces.length / 20;
		int len = (piecesCount / 8);
		len = (piecesCount % 8) > 0 ? len + 1 : len;
		return new byte[len];
	}

	/**
	 * Sets the handshake.
	 */
	private void setHandshake() {
		ByteBuffer handshakeBuffer = ByteBuffer.allocate(68);
		handshakeBuffer.put((byte) 19);
		handshakeBuffer.put(PROTOCOL_NAME.getBytes());
		handshakeBuffer.put(RESERVED);
		handshakeBuffer.put(hash);
		handshakeBuffer.put(PEER_ID.getBytes());
		handshakeBuffer.hasArray();

		handshake = handshakeBuffer.array();
	}

	/**
	 * Sets the finished.
	 * 
	 * @param finished
	 *            the new finished
	 */
	public void setFinished(boolean finished) {
		if (finished) {
			STATE &= FINISHED;
			synchronized (Main.main) {
				Main.main.notify();
			}
		} else {
			STATE &= ~FINISHED;
		}
	}
}
