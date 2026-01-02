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
package eu.kalafatic.gemini.bt.client.net.controller.interfaces;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.REQUESTED_PIECES;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.TRANSPORT_BLOCK_SIZE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.TransportPiece;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.TTLPieceTimer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ATransportBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class ATransportBuilder implements ITransportBuilder {

	/** The ext torrent. */
	protected ExtTorrent extTorrent;

	/** The swarm session. */
	protected SwarmSession swarmSession;

	/** The client session. */
	protected ClientSession clientSession;

	/** The pieces list. */
	protected List<TransportPiece> piecesList;

	/**
	 * Instantiates a new a transport builder.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 * @param clientSession the client session
	 */
	public ATransportBuilder(ExtTorrent extTorrent, SwarmSession swarmSession, ClientSession clientSession) {
		this.extTorrent = extTorrent;
		this.swarmSession = swarmSession;
		this.clientSession = clientSession;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the piece data file.
	 * @param extTorrent the ext torrent
	 * @param pieceIndex the piece index
	 * @return the piece data file
	 */
	public DataFile getPieceDataFile(ExtTorrent extTorrent, int pieceIndex) {

		Collection<DataFile> synchronizedCollection = Collections.synchronizedCollection(extTorrent.getInfo().getFiles());

		for (DataFile dataFile : synchronizedCollection) {
			int[] pieces = dataFile.getPieces();

			if (pieces[0] <= pieceIndex && pieceIndex <= pieces[pieces.length - 1]) {
				return dataFile;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransportBuilder #processUnfinishedPieces()
	 */
	@Override
	public void processUnfinishedPieces(boolean force) throws Exception {
		byte[] clientBitfield = clientSession.getBitfield();

		Iterator<IOPiece> iterator = swarmSession.getProcessedPieces().values().iterator();

		while (iterator.hasNext() && (piecesList.size() < REQUESTED_PIECES)) {

			IOPiece piece = iterator.next();
			int i = piece.getPieceIndex();

			// peer has piece ?
			if (!isTrue(clientBitfield[i / 8], i % 8)) {
				continue;
			}
			if (force) {
				// force frozen piece
				addPiece(piece, "PIECE FORCE TAKING CONTROL ");
				continue;
			}
			addPiece(piece, "UNFINISHED PIECE ");
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the piece.
	 * @param piece the piece
	 */
	private void addPiece(IOPiece piece) {
		TransportPiece transportPiece = new TransportPiece(piece);
		if (transportPiece.lock()) {
			piecesList.add(transportPiece);
		} else {
			transportPiece = null;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the piece.
	 * @param piece the piece
	 * @param logMsg the log msg
	 */
	private void addPiece(IOPiece piece, String logMsg) {
		addPiece(piece);

		Log.log(MODULE, LOG + logMsg + piece.getPieceIndex());
	}

	// ---------------------------------------------------------------

	/**
	 * Contains.
	 * @param array the array
	 * @param value the value
	 * @return true, if successful
	 */
	protected boolean contains(boolean[] array, boolean value) {
		for (boolean b : array) {
			if (b == value) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransportBuilder #getPiecesFromHave()
	 */
	@Override
	public void getPiecesFromHave() throws Exception {
		final EList<Integer> have = clientSession.getHave();
		final boolean[] modelBitfield = extTorrent.getModelBitfield();

		for (int i = 0; i < have.size() && (piecesList.size() < REQUESTED_PIECES); i++) {

			if (!modelBitfield[i]) {
				tryAddPiece(have.get(i));
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.ITransportBuilder #selectSequencePieces()
	 */
	@Override
	public void selectSequencePieces() throws Exception {
		final byte[] clientBitfield = clientSession.getBitfield();
		final boolean[] modelBitfield = extTorrent.getModelBitfield();

		for (int i = 0; (i < modelBitfield.length) && (piecesList.size() < REQUESTED_PIECES); i++) {

			if (!modelBitfield[i]) {
				if (isTrue(clientBitfield[i / 8], i % 8)) {
					tryAddPiece(i);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the processed piece.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 * @param dataFile the data file
	 * @param pieceIndex the piece index
	 * @return the IO piece
	 */
	public IOPiece createProcessedPiece(ExtTorrent extTorrent, SwarmSession swarmSession, DataFile dataFile, int pieceIndex) {
		IOPiece piece = null;
		try {
			piece = ClientNetworkFactory.eINSTANCE.createIOPiece();
			piece.setExtTorrent(extTorrent);
			piece.setPieceIndex(pieceIndex);
			piece.setHash(Arrays.copyOfRange(extTorrent.getInfo().getPieces(), pieceIndex * 20, (pieceIndex * 20) + 20));
			piece.setLock(new ReentrantReadWriteLock(true));

			if (pieceIndex == (extTorrent.getModelBitfield().length - 1)) {

				setUpLastPiece(extTorrent, piece);

				Log.log(MODULE, LOG + "CREATED PIECE LAST : " + pieceIndex);
			} else {
				setUpPiece(extTorrent, piece);

				Log.log(MODULE, LOG + "CREATED PIECE : " + pieceIndex);
			}
			swarmSession.getProcessedPieces().put(pieceIndex, piece);
			new TTLPieceTimer(swarmSession.getProcessedPieces(), piece);

		} catch (Exception e) {
			Log.log(MODULE, e);
			// TODO
			// addOrRemoveProcessedPiece(swarmSession, pieceIndex, piece,
			// false);
		}
		return piece;
	}

	// ---------------------------------------------------------------

	/**
	 * Try add piece.
	 * @param pieceIndex the piece index
	 * @return true, if successful
	 */
	protected boolean tryAddPiece(int pieceIndex) {
		if (extTorrent.getModelBitfield()[pieceIndex]) {
			return false;
		}
		if (swarmSession.getProcessedPieces().containsKey(pieceIndex)) {
			return false;
		}
		DataFile dataFile = getPieceDataFile(extTorrent, pieceIndex);

		if (dataFile == null) {
			return false;
		}
		if (dataFile.isEnabled()) {
			IOPiece piece = createProcessedPiece(extTorrent, swarmSession, dataFile, pieceIndex);

			if (piece != null) {
				addPiece(piece);
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Try again.
	 * @throws Exception the exception
	 */
	protected void tryAgain() throws Exception {
		if (piecesList.isEmpty()) {
			getTransportPieces();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up piece.
	 * @param extTorrent the ext torrent
	 * @param piece the piece
	 */
	private void setUpPiece(ExtTorrent extTorrent, IOPiece piece) {
		int pieceSize = extTorrent.getInfo().getPieceLength();
		int blocks = pieceSize / TRANSPORT_BLOCK_SIZE;

		setUpPiece(piece, pieceSize, blocks);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up last piece.
	 * @param extTorrent the ext torrent
	 * @param piece the piece
	 */
	private void setUpLastPiece(ExtTorrent extTorrent, IOPiece piece) {
		int lastPieceSize = NetworkManager.getInstance().getLastPieceLength(extTorrent);

		int blocks = lastPieceSize / TRANSPORT_BLOCK_SIZE;
		blocks = lastPieceSize % TRANSPORT_BLOCK_SIZE == 0 ? blocks : blocks + 1;

		setUpPiece(piece, lastPieceSize, blocks);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up piece.
	 * @param piece the piece
	 * @param bufferSize the buffer size
	 * @param blocks the blocks
	 */
	private void setUpPiece(IOPiece piece, int bufferSize, int blocks) {
		piece.getLock().writeLock().lock();
		try {
			ByteBuffer payload = ByteBuffer.allocate(bufferSize);
			payload.hasArray();
			piece.setPayload(payload);
			piece.setBlocks(new boolean[blocks]);
			piece.setLockedBlocks(new boolean[blocks]);
			// Arrays.fill(piece.getLockedBlocks(), true);
		} finally {
			piece.getLock().writeLock().unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is true.
	 * @param b the b
	 * @param pos the pos
	 * @return true, if is true
	 */
	public boolean isTrue(byte b, int pos) {
		int bitmask = 1 << (7 - pos);
		return (b & bitmask) != 0;
	}
}
