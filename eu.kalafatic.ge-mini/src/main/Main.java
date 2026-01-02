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
package main;

import io.AIO;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.Piece;
import model.Torrent;
import net.DSession;
import net.TrackerHandler;
import net.USession;
import protocols.IState;
import utils.Utils;

/**
 * The Class Main.
 */
public class Main implements IState {

	/** The Constant main. */
	public static final Main main = new Main();

	/** The Constant TIMEOUT. */
	public static final int TIMEOUT = 5000;

	/** The Constant PORT. */
	public static final int PORT = 6882;

	/** The Constant SESSIONS. */
	public static final int SESSIONS = 4;

	/** The Constant TRANSPORT_BLOCK_SIZE. */
	public static final int TRANSPORT_BLOCK_SIZE = 2 << 13;

	/** The DE f_ length. */
	public static byte[] DEF_LENGTH = Utils
			.encodeFourByteNumber(TRANSPORT_BLOCK_SIZE);

	/** The Constant BLOCKS_IN_REQUEST. */
	public static final int BLOCKS_IN_REQUEST = 2 << 3;

	/** The torrent. */
	public static Torrent torrent;

	/** The peers. */
	public static Set<String> peers = new HashSet<String>();

	/** The active. */
	public static Set<String> active = new HashSet<String>();

	/** The processed. */
	public static Map<Integer, Piece> processed = new HashMap<Integer, Piece>();

	/** The executor. */
	public static ExecutorService executor = new ThreadPoolExecutor(SESSIONS,
			2 * SESSIONS, 20, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(2 * SESSIONS, true),
			new ThreadPoolExecutor.CallerRunsPolicy());

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			switch (args.length) {
			case 0:
				main.runTorrents();
				break;
			case 1:
				torrent = new Torrent(args[0]).init();
				start();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			main.saveAfterAppEnd();
		}
	}

	/**
	 * Run torrents.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void runTorrents() throws Exception {
		// File[] files = new File(System.getProperty("user.dir")).listFiles();
		File[] files = new File("torrents").listFiles();

		for (File file : files) {
			if (file.getName().endsWith(".torrent")) {
				torrent = new Torrent(file);
				torrent.init();
				start();
				if ((torrent.STATE & FINISHED) != 0) {
					synchronized (main) {
						main.wait();
					}
				}
			}
		}
	}

	/**
	 * Start.
	 */
	private static void start() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					startTrackers();
					startDownload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 10, 128);
		startUpload();
	}

	/**
	 * Start trackers.
	 */
	private static void startTrackers() {
		for (String announce : torrent.trackers) {
			try {
				peers.addAll(TrackerHandler.getPeersFromTracker(announce));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Start download.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private static void startDownload() throws Exception {
		List<String> announces = new ArrayList<String>(peers);
		for (String announce : announces) {
			if (!active.contains(announce)) {
				startDownload(announce);
			}
		}
	}

	/**
	 * Start download.
	 * 
	 * @param announce
	 *            the announce
	 * @throws Exception
	 *             the exception
	 */
	private static void startDownload(final String announce) throws Exception {
		new Thread("Download-" + announce) {
			@Override
			public void run() {
				try {
					SocketChannel channel = SocketChannel.open();
					channel.socket().connect(Utils.getSocketAddress(announce),
							TIMEOUT);
					active.add(announce);
					executor.execute(new DSession(channel));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Start upload.
	 */
	private static void startUpload() {
		new Thread("Upload") {
			@Override
			public void run() {
				try {
					InetSocketAddress isa = new InetSocketAddress(
							InetAddress.getLocalHost(), PORT);
					ServerSocketChannel serverSocketChannel = ServerSocketChannel
							.open();
					serverSocketChannel.configureBlocking(true);
					serverSocketChannel.socket().bind(isa);

					while (true) {
						try {
							executor.execute(new USession(serverSocketChannel
									.accept()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Save after app end.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void saveAfterAppEnd() throws Exception {
		Thread save = new Thread("Save bitfield") {
			@Override
			public void run() {
				try {
					AIO.writeBitfield(Main.torrent.bfFile,
							Main.torrent.bitfield);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		save.setDaemon(true);
		save.start();
	}
}
