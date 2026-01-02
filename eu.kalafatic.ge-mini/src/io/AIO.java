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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import model.DataFile;

/**
 * The Class AIO.
 */
public abstract class AIO {

	/**
	 * Read bitfield.
	 * 
	 * @param file
	 *            the file
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] readBitfield(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int r;
			while ((r = br.read()) != -1) {
				byte b = (byte) (r == '1' ? 1 : 0);
				if (b == 0) {
					Main.torrent.setFinished(false);
				}
				baos.write(b);
			}
			return baos.toByteArray();
		} finally {
			baos.close();
			br.close();
		}
	}

	/**
	 * Write bitfield.
	 * 
	 * @param file
	 *            the file
	 * @param bitfield
	 *            the bitfield
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void writeBitfield(File file, byte[] bitfield)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
		try {
			for (int i = 0; i < bitfield.length; i++) {
				String binaryByte = String.format("%8s",
						Integer.toBinaryString(bitfield[i])).replace(" ", "0");
				bw.write(binaryByte);
			}
		} finally {
			bw.close();
		}
	}

	/**
	 * Write bitfield.
	 * 
	 * @param file
	 *            the file
	 * @param position
	 *            the position
	 * @param c
	 *            the c
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void writeBitfield(File file, int position, char c)
			throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		try {
			raf.seek(position);
			raf.write(c);
		} finally {
			raf.close();
		}
	}

	/**
	 * Gets the iO.
	 * 
	 * @param dataFiles
	 *            the data files
	 * @param index
	 *            the index
	 * @param position
	 *            the position
	 * @param len
	 *            the len
	 * @return the iO
	 */
	public static List<Integer> getIO(List<DataFile> dataFiles, int index,
			long position, int len) {
		List<Integer> ioLengts = new ArrayList<Integer>();
		setIO(dataFiles, index, position, len, ioLengts);
		return ioLengts;
	}

	/**
	 * Sets the io.
	 * 
	 * @param dataFiles
	 *            the data files
	 * @param index
	 *            the index
	 * @param position
	 *            the position
	 * @param len
	 *            the len
	 * @param ioLengts
	 *            the io lengts
	 */
	private static void setIO(List<DataFile> dataFiles, int index,
			long position, int len, List<Integer> ioLengts) {

		DataFile dataFile = dataFiles.get(index);

		if ((position + len) <= dataFile.end) {
			ioLengts.add(len);
		} else {
			int size = (int) (dataFile.end - position);
			ioLengts.add(size);

			setIO(dataFiles, index + 1, dataFile.end, len - size, ioLengts);
		}
	}

	/**
	 * Read bytes.
	 * 
	 * @param file
	 *            the file
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	public static byte[] readBytes(File file) throws Exception {
		return readBytes(new FileInputStream(file));
	}

	/**
	 * Read bytes.
	 * 
	 * @param inputStream
	 *            the input stream
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
		try {
			while (true) {
				byte[] buf = new byte[512];
				len = inputStream.read(buf);
				if (len == -1) {
					break;
				}
				baos.write(buf, 0, len);
			}
			return baos.toByteArray();

		} finally {
			baos.close();
			inputStream.close();
		}
	}
}
