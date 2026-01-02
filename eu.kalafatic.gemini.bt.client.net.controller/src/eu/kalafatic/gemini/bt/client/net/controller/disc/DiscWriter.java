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

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;

/**
 * The Class class DiscWriter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DiscWriter extends ADiscIO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The instance. */
	private static volatile DiscWriter INSTANCE;

	/**
	 * Gets the single instance of DiscWriter.
	 * @return single instance of DiscWriter
	 */
	public static DiscWriter getInstance() {
		if (INSTANCE == null) {
			synchronized (DiscWriter.class) {
				INSTANCE = new DiscWriter();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Write raf piece on disc.
	 * @param piece the piece
	 * @throws Exception the exception
	 */
	public void writeRAFPieceOnDisc(IOPiece piece) throws Exception {

		ExtTorrent extTorrent = (ExtTorrent) piece.getExtTorrent();

		// piece holds write lock
		EList<DataFile> dataFiles = extTorrent.getInfo().getFiles();
		int pieceLength = extTorrent.getInfo().getPieceLength();

		ByteBuffer payload = piece.getPayload();
		// boolean hasArray = payload.hasArray();
		byte[] data = payload.array();

		int len = payload.array().length;
		long position = piece.getPieceIndex() * pieceLength;

		for (int i = 0; i < dataFiles.size(); i++) {

			if (dataFiles.get(i).getEnd() > position) {

				List<Integer> io = getIO(dataFiles, i, position, len);
				writeRAFPieceOnDisc(dataFiles, i, position, io, data);
				break;
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Write raf piece on disc.
	 * @param dataFiles the data files
	 * @param index the index
	 * @param position the position
	 * @param io the io
	 * @param data the data
	 * @throws Exception the exception
	 */
	public void writeRAFPieceOnDisc(EList<DataFile> dataFiles, int index, long position, List<Integer> io, byte[] data) throws Exception {

		for (int i = index, j = 0, offset = 0; (i < dataFiles.size()) && (j < io.size()); i++, j++) {

			int sizeToWrite = io.get(j);
			byte[] dataToWrite = new byte[sizeToWrite];

			System.arraycopy(data, offset, dataToWrite, 0, sizeToWrite);

			DataFile dataFile = dataFiles.get(i);

			writeRAFDataToDatafile(dataFile, position, dataToWrite);

			position = dataFile.getEnd();
			offset += sizeToWrite;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Write raf data to datafile.
	 * @param dataFile the data file
	 * @param position the position
	 * @param data the data
	 * @throws Exception the exception
	 */
	private void writeRAFDataToDatafile(DataFile dataFile, long position, byte[] data) throws Exception {
		lock();
		RandomAccessFile raf = null;
		long recorded = dataFile.getRecorded();

		try {
			// set lazy raf to model datafile
			if ((raf = (RandomAccessFile) dataFile.getRaf()) == null) {
				File output = new File(dataFile.getPath());
				raf = new RandomAccessFile(output, "rws"); // rws, rwd ?
				raf.setLength(dataFile.getLength());
				dataFile.setRaf(raf);
			}
			int offset = (int) (position - dataFile.getBegin());

			raf.seek(offset);
			raf.write(data);

			dataFile.setRecorded(recorded += data.length);

		} finally {
			if ((recorded == dataFile.getLength()) && (raf != null)) {
				raf.getFD().sync();
				raf.close();
				raf = null;
			}
			data = null;
			unlock();
		}
	}
}
