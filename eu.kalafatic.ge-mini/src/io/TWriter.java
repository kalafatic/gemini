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
package io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.List;

import main.Main;
import model.DataFile;
import model.Piece;

/**
 * The Class TWriter.
 */
public class TWriter extends AIO {

	/**
	 * Write raf piece on disc.
	 * 
	 * @param piece
	 *            the piece
	 * @throws Exception
	 *             the exception
	 */
	public static synchronized void writeRAFPieceOnDisc(Piece piece)
			throws Exception {

		List<DataFile> dataFiles = (List<DataFile>) Main.torrent.dataFiles
				.values();

		ByteBuffer payload = piece.payload;
		byte[] data = payload.array();

		int len = payload.array().length;
		long position = piece.index * Main.torrent.pieceLength;

		for (int i = 0; i < dataFiles.size(); i++) {

			if (dataFiles.get(i).end > position) {

				List<Integer> io = getIO(dataFiles, i, position, len);
				writeRAFPieceOnDisc(dataFiles, i, position, io, data);
				break;
			}
		}
	}

	/**
	 * Write raf piece on disc.
	 * 
	 * @param dataFiles
	 *            the data files
	 * @param index
	 *            the index
	 * @param position
	 *            the position
	 * @param io
	 *            the io
	 * @param data
	 *            the data
	 * @throws Exception
	 *             the exception
	 */
	public static void writeRAFPieceOnDisc(List<DataFile> dataFiles, int index,
			long position, List<Integer> io, byte[] data) throws Exception {

		for (int i = index, j = 0, offset = 0; (i < dataFiles.size())
				&& (j < io.size()); i++, j++) {

			int sizeToWrite = io.get(j);
			byte[] dataToWrite = new byte[sizeToWrite];

			System.arraycopy(data, offset, dataToWrite, 0, sizeToWrite);

			DataFile dataFile = dataFiles.get(i);

			writeRAFDataToDatafile(dataFile, position, dataToWrite);

			position = dataFile.end;
			offset += sizeToWrite;
		}
	}

	/**
	 * Write raf data to datafile.
	 * 
	 * @param dataFile
	 *            the data file
	 * @param position
	 *            the position
	 * @param data
	 *            the data
	 * @throws Exception
	 *             the exception
	 */
	private static void writeRAFDataToDatafile(DataFile dataFile,
			long position, byte[] data) throws Exception {
		try {
			if (dataFile.raf == null) {
				dataFile.raf = new RandomAccessFile(dataFile.file, "rws");
				dataFile.raf.setLength(dataFile.length);
			}
			int offset = (int) (position - dataFile.start);

			dataFile.raf.seek(offset);
			dataFile.raf.write(data);

			dataFile.recorded += data.length;
		} finally {
			if ((dataFile.recorded == dataFile.length)
					&& (dataFile.raf != null)) {
				dataFile.raf.getFD().sync();
				dataFile.raf.close();
				dataFile.raf = null;
			}
			data = null;
		}
	}
}
