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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.TRANSPORT_BLOCK_SIZE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.PIECE_ID;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.MSG_INC;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.decoders.ClientDecoder;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscManager;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscReader;
import eu.kalafatic.gemini.bt.client.net.controller.disc.DiscWriter;
import eu.kalafatic.gemini.bt.client.net.controller.factories.TransportFactory;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.transporters.Transporter;
import eu.kalafatic.gemini.bt.client.net.controller.utils.ConvertUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.lib.EColor;
import eu.kalafatic.gemini.core.utils.HashUtils;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class AMessageDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AMessageDecoder implements IMessageDecoder {

	/** The handler. */
	protected AClientHandler handler;

	/** The session. */
	protected ClientSession session;

	/** The transporter. */
	protected Transporter transporter;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The ext torrent. */
	protected ExtTorrent extTorrent;

	/** The length. */
	private int index, begin, length;

	/** The reserved array. */
	protected byte[] reservedArray;

	/** The info hash array. */
	protected byte[] infoHashArray;

	/** The peer id array. */
	private byte[] peerIDArray;

	/** The id. */
	protected int len, id;

	/** The incoming blocks. */
	protected int incomingBlocks = 0;

	/** The old upload index. */
	private int oldUploadIndex;

	/**
	 * Instantiates a new a message decoder.
	 * @param handler the handler
	 */
	public AMessageDecoder(AClientHandler handler) {
		this.handler = handler;
		this.session = handler.getSession();
		this.transporter = handler.getTransporter();

		if (session instanceof DwnldSession) {
			this.swarmSession = handler.getSwarmSession();
			this.extTorrent = (ExtTorrent) swarmSession.getTorrent();
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processHandshake()
	 */
	@Override
	public void processHandshake() throws Exception {
		// skip 20 bytes for protocol identification
		transporter.receive(15);

		reservedArray = transporter.receive(8);
		infoHashArray = transporter.receive(20);
		peerIDArray = transporter.receive(20);

		// upload
		if (extTorrent == null) {
			this.extTorrent = resolveTorrent(infoHashArray);

			Assert.isNotNull(extTorrent, "Torrent not found.");

			this.swarmSession = NetworkModelManager.getInstance().getUploadSwarmSession(extTorrent);
			swarmSession.getUploads().put(session.getAnnounce(), session);
			transporter.setSwarmSession(swarmSession);
		}
		transporter.resolveUpDownSize(68, false);

		NetworkModelManager.getInstance().checkExtTorrent(extTorrent);

		String decodedClientName = ClientDecoder.getInstance().decodeClientName(new String(peerIDArray));

		session.setHandshakeOK(true);
		session.setClientName(decodedClientName);
		session.setReserved(reservedArray);
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve torrent.
	 * @param clientHash the client hash
	 * @return the ext torrent
	 * @throws Exception the exception
	 */
	private ExtTorrent resolveTorrent(byte[] clientHash) throws Exception {
		Iterator<Entry<String, ExtTorrent>> iterator = NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().iterator();

		ExtTorrent extTorrent = resolveTorrent(clientHash, iterator);

		if (extTorrent == null) {
			iterator = NetworkModelManager.getInstance().getTorrents().getTorrentMap().iterator();
			return resolveTorrent(clientHash, iterator);
		}
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve torrent.
	 * @param clientHash the client hash
	 * @param iterator the iterator
	 * @return the ext torrent
	 */
	private ExtTorrent resolveTorrent(byte[] clientHash, Iterator<Entry<String, ExtTorrent>> iterator) {

		while (iterator.hasNext()) {
			Map.Entry<String, ExtTorrent> entry = iterator.next();

			byte[] hash = entry.getValue().getInfo().getHash();

			if (Arrays.equals(clientHash, hash)) {
				return entry.getValue();
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #procesHave()
	 */
	@Override
	public void procesHave() throws Exception {
		index = decodeFourByteNumber(transporter.receiveInt());
		Assert.isLegal(index < extTorrent.getModelBitfield().length, "Wrong piece index: " + index);

		if (session.getBitfield() != null) {
			if (!isTrue(session.getBitfield()[index / 8], index % 8)) {
				session.getBitfield()[index / 8] |= (1 << (7 - (index % 8)));
			}
		}
		session.getHave().add(index);

		transporter.resolveUpDownSize(9, false);
		Log.log(MODULE, MSG_INC + "HAVE " + index);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processBitfield()
	 */
	@Override
	public void processBitfield() throws Exception {
		Assert.isLegal((len - 1) <= extTorrent.getInfo().getPieces().length, "Wrong piece index: " + index);
		byte[] bitfield = transporter.receive(len - 1);

		session.setBitfield(bitfield);

		boolean[] modelBitfield = extTorrent.getModelBitfield();

		for (int i = 0; i < modelBitfield.length; i++) {

			if (!isTrue(bitfield[i / 8], i % 8)) {
				int peers = extTorrent.getAdditionalInfo().getPeers();
				extTorrent.getAdditionalInfo().setPeers(++peers);
				return;
			}
		}
		session.setSeed(true);
		int seeds = extTorrent.getAdditionalInfo().getSeeds();
		extTorrent.getAdditionalInfo().setSeeds(++seeds);
		transporter.resolveUpDownSize(len + 5, false);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processCancel()
	 */
	@Override
	public void processCancel() throws Exception {
		index = decodeFourByteNumber(transporter.receiveInt());
		begin = decodeFourByteNumber(transporter.receiveInt());
		length = decodeFourByteNumber(transporter.receiveInt());

		session.getCanceledBlocks().add(index);

		transporter.resolveUpDownSize(17, false);
		Log.log(MODULE, MSG_INC + "CANCEL " + index + "/" + begin);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processDHTPort()
	 */
	@Override
	public void processDHTPort() throws Exception {
		byte[] portArray = transporter.receive(2);

		int port = ConvertUtils.INSTANCE.decodeTwoByteNumber(portArray);

		TransportFactory.setUpPort(port);

		transporter.resolveUpDownSize(2, false);
		Log.log(MODULE, MSG_INC + "DHT_PORT " + port);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processRequest()
	 */
	@Override
	public void processRequest() throws Exception {
		byte[] indexArray = transporter.receiveInt();
		index = decodeFourByteNumber(indexArray);
		Assert.isLegal(index >= 0, "Number not allowed: " + index);

		if (session.getCanceledBlocks().contains(index)) {
			session.getCanceledBlocks().remove(new Integer(index));
			return;
		}
		byte[] beginArray = transporter.receiveInt();
		begin = decodeFourByteNumber(beginArray);
		Assert.isLegal(begin >= 0, "Number not allowed: " + begin);

		length = decodeFourByteNumber(transporter.receiveInt());
		Assert.isLegal(length <= (1 << 18), "Request length not allowed.");

		if (!AControl.canUpload) {
			SoundPlayer.getInstance().play("audrey_upload_rejected.wav");

			if (session instanceof UpldSession) {
				throw new Exception();
			} else if (session instanceof DwnldSession) {
				return;
			}
		}
		if (oldUploadIndex != index) {
			oldUploadIndex = index;
			SoundPlayer.getInstance().play("audrey_upload_accepted.wav");
		}

		int pieceLength = extTorrent.getInfo().getPieceLength();
		long position = (index * pieceLength) + begin;

		byte[] data = DiscReader.getInstance().readRAFDataFromDisc(extTorrent, position, length);

		transporter.send(encodeFourByteNumber(length + 9), PIECE_ID, indexArray, beginArray, data);

		extTorrent.getAdditionalInfo().setUploaded(extTorrent.getAdditionalInfo().getUploaded() + 13 + length);

		transporter.resolveUpDownSize(13 + length, true);

		while (swarmSession.getUploadedPieces().size() > 15) {
			swarmSession.getUploadedPieces().remove(0);
		}
		for (IOPiece piece : swarmSession.getUploadedPieces()) {
			if (piece.getPieceIndex() == index) {
				piece.setExtTorrent(extTorrent);
				piece.setColor(EColor.UPLD.value);
				piece.setLastActivity(System.currentTimeMillis());
				return;
			}
		}
		IOPiece piece = ClientNetworkFactory.eINSTANCE.createIOPiece();
		piece.setExtTorrent(extTorrent);
		piece.setPieceIndex(index);
		piece.setHash(Arrays.copyOfRange(extTorrent.getInfo().getPieces(), index * 20, (index * 20) + 20));
		piece.setColor(EColor.UPLD.value);
		piece.setLastActivity(System.currentTimeMillis());
		swarmSession.getUploadedPieces().add(piece);

		Log.log(MODULE, LOG + "REQUEST " + index + "/" + begin);
		NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.UPLOADING);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IMessageDecoder #processPiece()
	 */
	@Override
	public void processPiece() throws Exception {
		index = decodeFourByteNumber(transporter.receiveInt());
		begin = decodeFourByteNumber(transporter.receiveInt());

		byte[] data = transporter.receive(len - 9);

		Assert.isNotNull(data);

		processPieceData(index, begin, data);

		transporter.resolveUpDownSize(13 + len, false);
		Log.log(MODULE, MSG_INC + "PIECE " + index + "/" + begin);
	}

	// ---------------------------------------------------------------

	/**
	 * Process piece data.
	 * @param pieceIndex the piece index
	 * @param begin the begin
	 * @param data the data
	 * @throws Exception the exception
	 */
	private void processPieceData(int pieceIndex, int begin, byte[] data) throws Exception {

		if (swarmSession == null) {
			swarmSession = NetworkModelManager.getInstance().getSwarmSession(extTorrent);
		}
		IOPiece piece = swarmSession.getProcessedPieces().get((Integer) pieceIndex);

		if (piece == null || piece.isFinished()) {
			if (extTorrent.getModelBitfield()[pieceIndex]) {
				return;
			} else {
				throw new IOException(String.format("Piece %d is null.", pieceIndex));
			}
		}
		int downloadedBlock = begin / TRANSPORT_BLOCK_SIZE;

		// most important tasks first: set up piece payload / write the data
		setAndWriteData(begin, data, piece, downloadedBlock);

		// less important: compute percent (even piece not downloaded)
		computeDownloaded(extTorrent.getAdditionalInfo(), data);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the and write data.
	 * @param begin the begin
	 * @param data the data
	 * @param piece the piece
	 * @param downloadedBlock the downloaded block
	 */
	private void setAndWriteData(int begin, byte[] data, IOPiece piece, int downloadedBlock) {
		// here can't throw an exception
		try {
			// before-dispose piece
			piece.getLock().writeLock().lock();
			try {
				ByteBuffer payload = piece.getPayload();
				payload.position(begin);
				payload.put(data);

				piece.getBlocks()[downloadedBlock] = true;
				++incomingBlocks;

				if (!contains(piece.getBlocks(), false) && (HashUtils.getInstance().checkBlockHash(payload.array(), piece.getHash()))) {

					// write the piece/set bitfields
					writePiece(piece);

					piece.setFinished(true);
					piece.setColor(EColor.FIN.value);
					finishPiece(piece.getPieceIndex());
				} else {
					piece.setColor(EColor.DWNLD.value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				piece.setLastActivity(System.currentTimeMillis());
				piece.getLock().writeLock().unlock();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Finish piece.
	 * @param pieceIndex the piece index
	 */
	private void finishPiece(final int pieceIndex) throws Exception {
		// after successful writing remove from active
		swarmSession.getProcessedPieces().removeKey(pieceIndex);

		// and set torrent/check for finished
		computeCompletedPieces(pieceIndex);

		// Toolkit.getDefaultToolkit().beep();
	}

	// ---------------------------------------------------------------

	/**
	 * Write piece.
	 * @param piece the piece
	 * @throws Exception the exception
	 */
	private void writePiece(IOPiece piece) throws Exception {
		// piece holds write lock
		processWriteBuffer(piece, true/* discBuffer == 0 */);

		NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.DOWNLOAD_PIECE_FINISHED);
	}

	// ---------------------------------------------------------------

	/**
	 * Compute completed pieces.
	 * @param pieceIndex the piece index
	 */
	private void computeCompletedPieces(int pieceIndex) throws Exception {
		extTorrent.getLock().writeLock().lock();
		try {
			int length = extTorrent.getModelBitfield().length;

			AdditionalInfo additionalInfo = extTorrent.getAdditionalInfo();
			int completedPieces = additionalInfo.getCompletedPieces();

			// first soft check
			if ((completedPieces + 1) >= length) {
				// second check/not removed from active
				if (isPieceProcessed(pieceIndex)) {
					return;
				}
				// third exact
				if (allPiecesFinished()) {
					processFinishedTorrent(additionalInfo);
					ModelUtils.doSave(extTorrent);
				}
			} else {
				additionalInfo.setCompletedPieces(++completedPieces);

				int newCompleted = (int) (completedPieces / (length / 100f));
				additionalInfo.setCompleted(newCompleted);
			}
		} finally {
			ModelUtils.doSave(extTorrent);
			extTorrent.getLock().writeLock().unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is piece processed.
	 * @param pieceIndex the piece index
	 * @return true, if is piece processed
	 */
	private boolean isPieceProcessed(int pieceIndex) throws Exception {
		return swarmSession.getProcessedPieces().containsKey(pieceIndex);

	}

	// ---------------------------------------------------------------

	/**
	 * Compute downloaded.
	 * @param additionalInfo the additional info
	 * @param data the data
	 */
	private void computeDownloaded(AdditionalInfo additionalInfo, byte[] data) {
		long extTorrentDownloaded = additionalInfo.getDownloaded();
		additionalInfo.setDownloaded(extTorrentDownloaded + data.length);
	}

	// ---------------------------------------------------------------

	/**
	 * Process finished torrent.
	 * @param additionalInfo the additional info
	 */
	private void processFinishedTorrent(AdditionalInfo additionalInfo) throws Exception {
		// extTorrent holds lock
		extTorrent.setFinished(true);
		additionalInfo.setCompleted(100);
		additionalInfo.setCompletedPieces(extTorrent.getModelBitfield().length);

		flushWriteBuffer(swarmSession.getPieceBuffer());
		swarmSession.setState(EViewsMessages.DOWNLOAD_FINISHED);
		swarmSession.setUploadOnly(true);

		session.setState(EViewsMessages.DOWNLOAD_FINISHED);

		new Thread() {
			@Override
			public void run() {
				try {
					SoundPlayer.getInstance().play("audrey_file_transfer_completed.wav");
					sleep(1000);
					SoundPlayer.getInstance().play("audrey_ready_to_upload.wav");

					handler.checkAllFinished();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	// ---------------------------------------------------------------

	/**
	 * Process write buffer.
	 * @param piece the piece
	 * @param force the force
	 * @throws Exception the exception
	 */
	private void processWriteBuffer(IOPiece piece, boolean force) throws Exception {
		// piece holds write lock
		if (force) {
			DiscWriter.getInstance().writeRAFPieceOnDisc(piece);
			setBitfields(piece.getPieceIndex());
			return;
		}

		WriteBuffer pieceBuffer = swarmSession.getPieceBuffer();
		try {
			if (pieceBuffer.getLock().writeLock().tryLock(500, TimeUnit.MILLISECONDS)) {
				try {
					if ((pieceBuffer.getOffset() + piece.getPayload().capacity()) > pieceBuffer.getSize()) {

						flushWriteBuffer(pieceBuffer);
					}
					pieceBuffer.setOffset(pieceBuffer.getOffset() + piece.getPayload().capacity());

					pieceBuffer.getPieces().add(piece);

				} finally {
					pieceBuffer.getLock().writeLock().unlock();
				}
			} else {
				processWriteBuffer(piece, true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			// throw new LockException("Lock is not available.");
			processWriteBuffer(piece, true);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the bitfields.
	 * @param i the new bitfields
	 */
	private void setBitfields(int i) {
		extTorrent.getModelBitfield()[i] = true;
		extTorrent.getRealBitfield()[i / 8] |= (1 << (7 - (i % 8)));

		ModelUtils.doSave(extTorrent);
	}

	// ---------------------------------------------------------------

	/**
	 * Flush write buffer.
	 * @param pieceBuffer the piece buffer
	 */
	private void flushWriteBuffer(WriteBuffer pieceBuffer) {
		EList<IOPiece> pieces = pieceBuffer.getPieces();

		int[] bufferedIndexes = new int[pieces.size()];

		for (int i = 0; i < pieces.size(); i++) {
			bufferedIndexes[i] = pieces.get(i).getPieceIndex();
		}
		try {
			DiscManager.getInstance().flushPieceBuffer(pieceBuffer);

			commit(bufferedIndexes, true);

		} catch (Exception e) {
			e.printStackTrace();
			commit(bufferedIndexes, false);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Commit.
	 * @param bufferedIndexes the buffered indexes
	 * @param commit the commit
	 */
	private void commit(int[] bufferedIndexes, boolean commit) {
		extTorrent.getLock().writeLock().lock();
		try {
			if (commit) {
				for (int i = 0; i < bufferedIndexes.length; i++) {
					setBitfields(bufferedIndexes[i]);
				}
			} else {
				for (int i = 0; i < bufferedIndexes.length; i++) {
					extTorrent.getModelBitfield()[bufferedIndexes[i]] = false;
				}
			}
		} finally {
			extTorrent.getLock().writeLock().unlock();
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

	// ---------------------------------------------------------------

	/**
	 * Contains.
	 * @param array the array
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean contains(boolean[] array, boolean value) {
		for (boolean b : array) {
			if (b == value) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * All pieces finished.
	 * @return true, if successful
	 */
	private boolean allPiecesFinished() throws Exception {
		byte[] realBitfield = extTorrent.getRealBitfield();
		boolean[] modelBitfield = extTorrent.getModelBitfield();
		boolean clear = true;

		for (int i = 0; i < realBitfield.length - 1; i++) {

			if (realBitfield[i] != (byte) -1) {
				checkMissingPiecesInByte(realBitfield[i], i, 8);
				clear = false;
			}
		}
		// including zero (modelBitfield=8 / not 7) !
		int piecesInLastByte = (modelBitfield.length % 8);

		int missingPiecesInByte = checkMissingPiecesInByte(realBitfield[realBitfield.length - 1], realBitfield.length - 1, piecesInLastByte);

		if (missingPiecesInByte > 0) {
			clear = false;
		}
		return clear;
	}

	// ---------------------------------------------------------------

	/**
	 * Check missing pieces in byte.
	 * @param b the b
	 * @param positionInBitfield the position in bitfield
	 * @param piecesInLastByte the pieces in last byte
	 * @return the int
	 */
	private int checkMissingPiecesInByte(byte b, int positionInBitfield, int piecesInLastByte) throws Exception {

		int result = EReturn.EXIT_VALUE;

		for (int j = 0; j < piecesInLastByte; j++) {

			int bitmask = 1 << (7 - j);

			if ((b & bitmask) == 0) {
				int unfinishedPiece = (positionInBitfield * 8) + j;
				extTorrent.getModelBitfield()[unfinishedPiece] = false;
				++result;
			}
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Encode four byte number.
	 * @param i the i
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] encodeFourByteNumber(int i) throws IOException {
		return new byte[] { (byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i };
	}

	// ---------------------------------------------------------------

	/**
	 * Decode four byte number.
	 * @param b the b
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int decodeFourByteNumber(byte[] b) throws IOException {
		return (b[0] & 0xff) << 24 | (b[1] & 0xff) << 16 | (b[2] & 0xff) << 8 | (b[3] & 0xff);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the ext torrent.
	 * @return the ext torrent
	 */
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the swarm session.
	 * @return the swarm session
	 */
	public SwarmSession getSwarmSession() {
		return swarmSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the info hash array.
	 * @return the info hash array
	 */
	public byte[] getInfoHashArray() {
		return infoHashArray;
	}

}
