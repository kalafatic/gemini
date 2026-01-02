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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsFactoryImpl;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.lib.EHash;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.HashUtils;

/**
 * The Class class TDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TDecoder extends ATEditor {

	/** The Constant INSTANCE. */
	public final static TDecoder INSTANCE = new TDecoder();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @param file the file
	 * @param finished the finished
	 */
	private void init(File file, boolean finished) {
		extTorrent = TorrentsFactoryImpl.eINSTANCE.createExtTorrent();
		extTorrent.setName(getFileName(file, EExt.TORRENT.ext));
		extTorrent.setPath(file.getAbsolutePath());
		extTorrent.setLock(new ReentrantReadWriteLock(true));
		AdditionalInfo additionalInfo = TorrentsFactoryImpl.eINSTANCE.createAdditionalInfo();
		additionalInfo.setLock(new ReentrantReadWriteLock(true));
		extTorrent.setAdditionalInfo(additionalInfo);

		extTorrent.setEnabled(true);
		extTorrent.setFinished(finished);

		if (!extTorrent.getPath().endsWith(".torrent")) {
			String downloads = PREFERENCES.get(ECorePreferences.DOWNLOADS_LOC.getName(), (String) ECorePreferences.DOWNLOADS_LOC.getDef());
			rootFile = new File(downloads.concat(File.separator).concat(extTorrent.getName()));
		}

		rootFile = new File(extTorrent.getPath());
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param file the file
	 * @return the ext torrent
	 */
	public ExtTorrent decode(File file) {
		return decode(file, false);
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param file the file
	 * @param finished the finished
	 * @return the ext torrent
	 */
	public ExtTorrent decode(File file, boolean finished) {
		byte[] bytesFromFile;
		try {
			bytesFromFile = FileUtils.getInstance().getBytesFromFile(file);
			Map<?, ?> map = BDecoder.decode(bytesFromFile);
			return decode(file, finished, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param file the file
	 * @param finished the finished
	 * @param map the map
	 * @return the ext torrent
	 */
	public ExtTorrent decode(File file, boolean finished, Map<?, ?> map) {
		try {
			init(file, finished);
			return decode(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param map the map
	 * @return the ext torrent
	 * @throws Exception the exception
	 */
	private ExtTorrent decode(Map<?, ?> map) throws Exception {
		if (map.containsKey(CREATED_BY)) {
			extTorrent.setCreationBy(new String((byte[]) map.get(CREATED_BY)));
		}
		if (map.containsKey(ENCODING)) {
			extTorrent.setEncoding(new String((byte[]) map.get(ENCODING)));
		}
		if (map.containsKey(CREATION_DATE)) {
			extTorrent.setCreationDate(((Long) map.get(CREATION_DATE)));
		}
		if (map.containsKey(ANNOUNCE)) {
			String announce = new String((byte[]) map.get(ANNOUNCE));
			extTorrent.getAnnounceList().add(announce);
		}
		if (map.containsKey(COMMENT)) {
			extTorrent.setComment(new String((byte[]) map.get(COMMENT)));
		}
		if (map.containsKey(ANNOUNCE_LIST)) {
			ArrayList<?> array = (ArrayList<?>) map.get(ANNOUNCE_LIST);

			for (Object object : array) {
				String announce = getString(object);
				extTorrent.getAnnounceList().add(announce);
			}
		}
		loadInfo(map);

		extTorrent.setMap(map);

		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Load info.
	 * @param map the map
	 */
	private void loadInfo(Map<?, ?> map) {
		info = TorrentsFactoryImpl.eINSTANCE.createInfo();
		extTorrent.setInfo(info);

		Map<?, ?> infoMap = (Map<?, ?>) map.get(INFO);

		setBasicInfo(infoMap);

		if (infoMap.containsKey(FILES)) {
			setMultipleFileMode(infoMap);
		} else {
			setSingleFileMode(infoMap);
		}
		// set info hash
		byte[] encoded = BEncoder.encode(infoMap);
		info.setHash(HashUtils.getInstance().getHash(encoded, EHash.SHA_1));
		// set all file size and pieces per file
		setFileSizeAndPieces(extTorrent);
		// set bitmap
		setBitmap(extTorrent);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the single file mode.
	 * @param infoMap the info map
	 */
	private void setSingleFileMode(Map<?, ?> infoMap) {
		DataFile dataFile = TorrentsFactoryImpl.eINSTANCE.createDataFile();
		dataFile.setLock(new ReentrantReadWriteLock(true));

		if (infoMap.containsKey(NAME)) {
			String name = new String((byte[]) infoMap.get(NAME));
			dataFile.setName(name);
			dataFile.setPath(rootFile.getAbsolutePath().concat(File.separator).concat(name));
		}
		if (infoMap.containsKey(LENGTH)) {
			Object object = infoMap.get(LENGTH);
			if (object instanceof Long) {
				Long length = (Long) object;
				int intValue = length.intValue();
				dataFile.setLength(intValue);
			}
		}
		dataFile.setIndex(0);
		info.getFiles().add(dataFile);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the multiple file mode.
	 * @param infoMap the info map
	 */
	private void setMultipleFileMode(Map<?, ?> infoMap) {
		List<?> array = ((ArrayList<?>) infoMap.get(FILES));

		int index = 0;

		for (Iterator<?> iterator = array.iterator(); iterator.hasNext();) {
			Map<?, ?> fileMap = (Map<?, ?>) iterator.next();

			DataFile dataFile = TorrentsFactoryImpl.eINSTANCE.createDataFile();
			dataFile.setLock(new ReentrantReadWriteLock(true));
			dataFile.setIndex(index++);

			if (infoMap.containsKey(NAME)) {
				dataFile.setName(new String((byte[]) infoMap.get(NAME)));
			}

			if (fileMap.containsKey(LENGTH)) {
				if (fileMap.get(LENGTH) instanceof Long) {
					Long len = (Long) fileMap.get(LENGTH);
					dataFile.setLength(len);
				}
			}
			if (fileMap.containsKey(PATH)) {
				String path = getString(fileMap.get(PATH));
				if (path.endsWith("/")) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (extTorrent.isFinished()) {
					dataFile.setPath(path);
				} else {

					File file = new File(path);
					if (file.exists()) {
						dataFile.setPath(rootFile.getAbsolutePath().concat(File.separator).concat(file.getName()));
					} else {
						dataFile.setPath(rootFile.getAbsolutePath().concat(File.separator).concat(path));
					}
				}

			} else {
				dataFile.setPath(rootFile.getAbsolutePath().concat(File.separator).concat(dataFile.getName()));
			}
			info.getFiles().add(dataFile);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the basic info.
	 * @param infoMap the info map
	 */
	private void setBasicInfo(Map<?, ?> infoMap) {
		if (infoMap.containsKey(PIECES)) {
			info.setPieces((byte[]) infoMap.get(PIECES));
		}
		if (infoMap.containsKey(PIECE_LENGTH)) {
			Object object = infoMap.get(PIECE_LENGTH);
			info.setPieceLength(getInteger(object));
		}
		if (infoMap.containsKey(PRIVATE)) {
			Object object = infoMap.get(PRIVATE);
			if (object instanceof Boolean) {
				info.setPrivate((Boolean) object);
			} else if (object instanceof Long) {
				Long priv = (Long) object;
				info.setPrivate(priv == 1 ? true : false);
			}
		}
	}
}
