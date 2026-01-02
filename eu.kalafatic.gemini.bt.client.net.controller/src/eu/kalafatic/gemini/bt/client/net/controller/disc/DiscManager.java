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
package eu.kalafatic.gemini.bt.client.net.controller.disc;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class DiscManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DiscManager extends ReentrantLock {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The instance. */
	private static DiscManager INSTANCE;

	/**
	 * Instantiates a new disc manager.
	 */
	public DiscManager() {
		super(true);
	}

	/**
	 * Gets the single instance of DiscManager.
	 * @return single instance of DiscManager
	 */
	public static DiscManager getInstance() {
		if (INSTANCE == null) {
			synchronized (DiscManager.class) {
				INSTANCE = new DiscManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the torrent structure on disc.
	 * @param extTorrent the ext torrent
	 * @return true, if successful
	 */
	public boolean createTorrentStructureOnDisc(ExtTorrent extTorrent) {
		lock();
		try {
			EList<DataFile> files = extTorrent.getInfo().getFiles();

			for (int i = 0; i < files.size(); i++) {

				DataFile dataFile = files.get(i);

				Path path = new Path(dataFile.getPath());

				String[] segments = path.segments();
				String destPath = path.getDevice();

				for (int j = 0; j < segments.length - 1; j++) {
					destPath += File.separator.concat(segments[j]);
					File file = new File(destPath);

					if (!file.exists()) {
						file.mkdirs();
					}
				}

				destPath += File.separator.concat(segments[segments.length - 1]);

				createLeafFile(dataFile, destPath);
			}
			return true;
		} catch (Exception e) {
			Log.log(MODULE, e);
			return false;
		} finally {
			unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Change dwnld locations.
	 * @param location the location
	 */
	public void changeDwnldLocations(String location) {
		// TODO - set download location ?
		Collection<ExtTorrent> torrents = NetworkModelManager.getInstance().getTorrents().getTorrentMap().values();

		for (ExtTorrent extTorrent : torrents) {
			File rootFile = new File(location.concat(File.separator).concat(extTorrent.getName()));
			EList<DataFile> files = extTorrent.getInfo().getFiles();

			for (DataFile dataFile : files) {
				dataFile.setPath(rootFile.getAbsolutePath().concat(File.separator).concat(dataFile.getName()));
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the leaf file.
	 * @param dataFile the data file
	 * @param destPath the dest path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void createLeafFile(DataFile dataFile, String destPath) throws IOException {
		File file = new File(destPath);
		if (!file.exists()) {
			file.createNewFile();
			file.setWritable(true, false);
			file.setExecutable(true, false);

			// RandomAccessFile raf = new RandomAccessFile(file, "rw"); // rws,
			// rwd
			// raf.setLength(dataFile.getLength());
			// raf.getFD().sync();
			// dataFile.setRaf(raf);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Flush piece buffers.
	 * @throws Exception the exception
	 */
	public void flushPieceBuffers() throws Exception {
		Collection<Session> values = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap().values();

		for (Session session : values) {
			DiscManager.getInstance().flushPieceBuffer((SwarmSession) session);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Flush piece buffer.
	 * @param swarmSession the swarm session
	 * @throws Exception the exception
	 */
	public void flushPieceBuffer(SwarmSession swarmSession) throws Exception {
		flushPieceBuffer(swarmSession.getPieceBuffer());

		Log.log(MODULE, LOG + "WRITE ON DISC " + swarmSession.getAnnounce());
	}

	// ---------------------------------------------------------------

	/**
	 * Flush piece buffer.
	 * @param pieceBuffer the piece buffer
	 * @throws Exception the exception
	 */
	public void flushPieceBuffer(WriteBuffer pieceBuffer) throws Exception {
		// sortBuffer(pieceBuffer);
		try {
			for (IOPiece piece : pieceBuffer.getPieces()) {
				DiscWriter.getInstance().writeRAFPieceOnDisc(piece);
				piece = null;
			}
		} finally {
			pieceBuffer.getPieces().clear();
			pieceBuffer.setOffset(0);
		}
	}

	// ---------------------------------------------------------------

	// private void sortBuffer(WriteBuffer pieceBuffer) {
	// Collections.sort(pieceBuffer.getPieces(), new Comparator<IOPiece>() {
	// @Override
	// public int compare(IOPiece p1, IOPiece p2) {
	// return p1.getPieceIndex() < p2.getPieceIndex() ? -1 : 1;
	// }
	// });
	// }
}
