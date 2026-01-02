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

import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;
import java.util.List;

import main.Main;
import model.DataFile;

/**
 * The Class TReader.
 */
public class TReader extends AIO {

	/**
	 * Read raf data from disc.
	 * 
	 * @param position
	 *            the position
	 * @param len
	 *            the len
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public static byte[] readRAFDataFromDisc(long position, int len)
			throws Exception {

		List<DataFile> dataFiles = (List<DataFile>) Main.torrent.dataFiles
				.values();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(len);

		for (int i = 0; i < dataFiles.size(); i++) {

			if (dataFiles.get(i).end > position) {

				List<Integer> io = getIO(dataFiles, i, position, len);
				readRAFDataFromDisc(dataFiles, i, position, io, baos);
				break;
			}
		}
		return baos.toByteArray();
	}

	/**
	 * Read raf data from disc.
	 * 
	 * @param dataFiles
	 *            the data files
	 * @param index
	 *            the index
	 * @param position
	 *            the position
	 * @param io
	 *            the io
	 * @param baos
	 *            the baos
	 * @throws Exception
	 *             the exception
	 */
	private static void readRAFDataFromDisc(List<DataFile> dataFiles,
			int index, long position, List<Integer> io,
			ByteArrayOutputStream baos) throws Exception {

		for (int i = index, j = 0; (i < dataFiles.size()) && (j < io.size()); i++, j++) {

			DataFile dataFile = dataFiles.get(i);
			byte[] data = readRAFDataFromDatafile(dataFile, position, io.get(j));
			baos.write(data);

			position = dataFile.end;
		}
	}

	/**
	 * Read raf data from datafile.
	 * 
	 * @param dataFile
	 *            the data file
	 * @param position
	 *            the position
	 * @param length
	 *            the length
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	private static byte[] readRAFDataFromDatafile(DataFile dataFile,
			long position, int length) throws Exception {
		byte[] data = new byte[length];

		if (dataFile.raf == null) {
			dataFile.raf = new RandomAccessFile(dataFile.file, "rws");
		}
		int offset = (int) (position - dataFile.start);

		dataFile.raf.seek(offset);
		dataFile.raf.read(data);

		return data;
	}
}
