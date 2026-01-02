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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;

/**
 * The Class class DiscReader.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DiscReader extends ADiscIO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The instance. */
	private static volatile DiscReader INSTANCE;

	/**
	 * Gets the single instance of DiscReader.
	 * @return single instance of DiscReader
	 */
	public static DiscReader getInstance() {
		if (INSTANCE == null) {
			synchronized (DiscReader.class) {
				INSTANCE = new DiscReader();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Read raf data from disc.
	 * @param extTorrent the ext torrent
	 * @param position the position
	 * @param len the len
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] readRAFDataFromDisc(ExtTorrent extTorrent, long position, int len) throws Exception {

		EList<DataFile> dataFiles = extTorrent.getInfo().getFiles();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(len);

		for (int i = 0; i < dataFiles.size(); i++) {

			if (dataFiles.get(i).getEnd() > position) {

				List<Integer> io = getIO(dataFiles, i, position, len);
				readRAFDataFromDisc(dataFiles, i, position, io, baos);
				break;
			}
		}
		return baos.toByteArray();
	}

	// ---------------------------------------------------------------

	/**
	 * Read raf data from disc.
	 * @param dataFiles the data files
	 * @param index the index
	 * @param position the position
	 * @param io the io
	 * @param baos the baos
	 * @throws Exception the exception
	 */
	private void readRAFDataFromDisc(EList<DataFile> dataFiles, int index, long position, List<Integer> io, ByteArrayOutputStream baos) throws Exception {

		for (int i = index, j = 0; (i < dataFiles.size()) && (j < io.size()); i++, j++) {

			DataFile dataFile = dataFiles.get(i);
			byte[] data = readRAFDataFromDatafile(dataFile, position, io.get(j));
			baos.write(data);

			position = dataFile.getEnd();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Read raf data from datafile.
	 * @param dataFile the data file
	 * @param position the position
	 * @param length the length
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	private byte[] readRAFDataFromDatafile(DataFile dataFile, long position, int length) throws Exception {
		lock();
		try {
			byte[] data = new byte[length];
			RandomAccessFile raf;

			if ((raf = (RandomAccessFile) dataFile.getRaf()) == null) {
				File input = new File(dataFile.getPath());
				raf = new RandomAccessFile(input, "rws");
				dataFile.setRaf(raf);
			}
			int offset = (int) (position - dataFile.getBegin());

			raf.seek(offset);
			raf.read(data);

			return data;
		} finally {
			// raf.close();
			unlock();
		}
	}
}
