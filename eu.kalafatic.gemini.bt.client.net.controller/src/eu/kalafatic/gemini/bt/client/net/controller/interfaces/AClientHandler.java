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
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.BLOCKS_IN_REQUEST;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.DISC_BUFFER;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.TRANSPORT_BLOCK_SIZE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.BITFIELD_ID;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.CHOKE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.NOT_INTERESTED;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.REQUEST;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.UNCHOKE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EX;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;

import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EMap;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.builders.ForceTransportBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.builders.RandomTransportBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.builders.SequenceTransportBuilder;
import eu.kalafatic.gemini.bt.client.net.controller.decoders.MessageDecoder;
import eu.kalafatic.gemini.bt.client.net.controller.factories.TransportFactory;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.TransportPiece;
import eu.kalafatic.gemini.bt.client.net.controller.transporters.Transporter;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class AClientHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AClientHandler extends ASessionHandler implements IClientHandler {

	/** The rating. */
	protected int rating = 0;;

	/** The unchoke sent. */
	protected boolean unchokeSent = false;

	/** The ext torrent. */
	protected ExtTorrent extTorrent;

	/** The swarm session. */
	protected SwarmSession swarmSession;

	/** The session. */
	protected ClientSession session;

	/** The announce. */
	protected String announce;

	/** The transporter. */
	protected Transporter transporter;

	/** The message decoder. */
	protected MessageDecoder messageDecoder;

	/** The client transport builder. */
	private ITransportBuilder clientTransportBuilder;

	/** The transport pieces. */
	private List<TransportPiece> transportPieces = new ArrayList<TransportPiece>();

	/** The default length array. */
	private byte[] defaultLengthArray = encodeFourByteNumber(TRANSPORT_BLOCK_SIZE);

	/** The baos. */
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();

	/**
	 * Instantiates a new a client handler.
	 * @param session the session
	 * @throws Exception the exception
	 */
	protected AClientHandler(ClientSession session) throws Exception {
		this.session = session;

		announce = session.getAnnounce();
		transporter = new Transporter(this);
		messageDecoder = new MessageDecoder(this);
	}

	// ---------------------------------------------------------------

	/**
	 * Instantiates a new a client handler.
	 * @param swarmSession the swarm session
	 * @param session the session
	 * @throws Exception the exception
	 */
	protected AClientHandler(SwarmSession swarmSession, ClientSession session) throws Exception {
		this.swarmSession = swarmSession;
		this.session = session;

		announce = session.getAnnounce();
		extTorrent = (ExtTorrent) swarmSession.getTorrent();

		transporter = new Transporter(this);
		messageDecoder = new MessageDecoder(this);

		setAlgorithm();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Sets the algorithm.
	 */
	protected void setAlgorithm() {
		switch (NetworkModelManager.getInstance().getClientNetwork().getAlgorithm()) {
		case SEQUENCE:
			clientTransportBuilder = new SequenceTransportBuilder(extTorrent, swarmSession, session);
			break;
		case RANDOM:
			clientTransportBuilder = new RandomTransportBuilder(extTorrent, swarmSession, session);
			break;
		case EXACT:
			clientTransportBuilder = new ForceTransportBuilder(extTorrent, swarmSession, session);
			break;
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# processHandshake()
	 */
	@Override
	public boolean processHandshake() throws Exception {
		byte[] handshake = extTorrent.getHandshake();
		// lazy set up torrent handshake
		if (handshake == null) {
			handshake = TransportFactory.setAndGetHandshake(extTorrent);
		}

		byte[] bitfield = extTorrent.getRealBitfield();
		byte[] lengthArray = encodeFourByteNumber(bitfield.length + 1);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(handshake);
		baos.write(lengthArray);
		baos.write(BITFIELD_ID);
		baos.write(bitfield);

		if (session instanceof UpldSession) {
			baos.write(TransportFactory.getHave(extTorrent));
		}
		transporter.send(baos.toByteArray());

		return (messageDecoder.decodeMessages() && session.isHandshakeOK());
	}

	// ---------------------------------------------------------------

	/**
	 * Send bitfield.
	 * @throws Exception the exception
	 */
	public void sendBitfield() throws Exception {
		byte[] bitfield = extTorrent.getRealBitfield();
		byte[] lengthArray = encodeFourByteNumber(bitfield.length + 1);

		transporter.send(lengthArray, BITFIELD_ID, bitfield);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#sendHave ()
	 */
	@Override
	public void sendHave() throws Exception {
		byte[] have = null;

		if (DISC_BUFFER > 0) {
			have = TransportFactory.setHave(swarmSession.getPieceBuffer());

			if (have != null) {
				transporter.send(have);
			}
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			boolean[] modelBitfield = extTorrent.getModelBitfield();

			for (int i = 0, j = 0; (i < modelBitfield.length) && (j < 8); i++) {
				if (modelBitfield[i]) {
					baos.write(encodeFourByteNumber(i));
					j++;
				}
			}
			if (baos.size() > 0) {
				transporter.send(baos.toByteArray());
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#interested (eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent,
	 * eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession)
	 */
	@Override
	public boolean interested(ExtTorrent extTorrent, ClientSession session) throws Exception {
		boolean[] modelBitfield = extTorrent.getModelBitfield();
		byte[] bitfield = session.getBitfield();

		for (int i = 0; i < modelBitfield.length; i++) {

			if (!modelBitfield[i] && isTrue(bitfield[i / 8], i % 8)) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# processInterested()
	 */
	@Override
	public boolean processInterested() throws Exception {
		Log.log(MODULE, LOG + "INTERESTED");

		if (interested(extTorrent, session)) {
			transporter.send(INTERESTED);

			if (messageDecoder.decodeMessages()) {
				return true;
			}
		} else {
			transporter.send(NOT_INTERESTED);
		}
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# processDownload()
	 */
	@Override
	public boolean processDownload() throws Exception {
		Log.log(MODULE, LOG + "TRANSPORT-DOWNLOAD");

		transportPieces = clientTransportBuilder.getTransportPieces();

		// finished
		if (transportPieces.isEmpty()) {
			return false;
		}

		NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.DOWNLOADING);

		// sendBitfield();

		for (int i = 0; i < transportPieces.size(); i++) {

			session.getSpeedContainer().setStartTime(System.currentTimeMillis());
			TransportPiece transportPiece = transportPieces.get(i);

			if (!providePieceDownload(transportPiece)) {
				return false;
			}
			session.setRating(++rating);
			computeSessionSpeed(extTorrent.getInfo().getPieceLength(), System.currentTimeMillis());
		}
		return true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#sendUnchoke ()
	 */
	@Override
	public void sendUnchoke() throws Exception {
		transporter.send(UNCHOKE);
		unchokeSent = true;
		Log.log(MODULE, LOG + "UNCHOKE");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#sendChoke ()
	 */
	@Override
	public void sendChoke() throws Exception {
		transporter.send(CHOKE);
		Log.log(MODULE, LOG + "CHOKE");
	}

	// ---------------------------------------------------------------

	/**
	 * Send choke and finish.
	 * @param eViewsMessages the e views messages
	 * @throws Exception the exception
	 */
	public void sendChokeAndFinish(EViewsMessages eViewsMessages) throws Exception {
		transporter.send(CHOKE);
		session.setState(EViewsMessages.SUSPENDED);
		NetworkManager.getInstance().setViewMessage(extTorrent, eViewsMessages);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# processException
	 * (eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession)
	 */
	@Override
	public void processException() {

		try {
			SocketChannel channel = session.getChannel();

			if (channel == null || !channel.isConnected()) {
				Log.log(MODULE, EX + "CONTACT OFFLINE");
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		} finally {
			session.setState(EViewsMessages.SUSPENDED);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Provide piece download.
	 * @param transportPiece the transport piece
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean providePieceDownload(TransportPiece transportPiece) throws Exception {
		IOPiece piece = transportPiece.piece;
		List<Integer> processed = transportPiece.processed;
		int requestCounter = 0;
		try {
			byte[] indexArray = encodeFourByteNumber(piece.getPieceIndex());

			byte[] beginArray, lengthArray;

			int pieceCapacity = piece.getPayload().capacity();

			baos.reset();

			/** Cycle for sequence transport */
			for (Integer blockIndex : processed) {

				checkBlock(transportPiece, blockIndex);

				int begin = blockIndex * TRANSPORT_BLOCK_SIZE;

				beginArray = encodeFourByteNumber(begin);

				if ((begin + TRANSPORT_BLOCK_SIZE) <= pieceCapacity) {

					lengthArray = defaultLengthArray;
				} else {
					lengthArray = encodeFourByteNumber(pieceCapacity - begin);
				}

				baos.write(REQUEST);
				baos.write(indexArray);
				baos.write(beginArray);
				baos.write(lengthArray);

				if ((++requestCounter) >= BLOCKS_IN_REQUEST) {
					sendRequestsAndDecode(requestCounter);
					baos.reset();
					requestCounter = 0;
				}
			}
			if (baos.size() > 0) {
				sendRequestsAndDecode(requestCounter);
			}
			// unlock successful batch
			transportPiece.unlock();
			// try lock same piece batch
			if (transportPiece.lock()) {
				providePieceDownload(transportPiece);
			}
			return true;

		} catch (Exception e) {
			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.DOWNLOAD_ERROR);
			Log.log(MODULE, e);
			return false;
		} finally {
			transportPiece.unlock();
			transportPiece = null;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check block.
	 * @param transportPiece the transport piece
	 * @param blockIndex the block index
	 * @throws Exception the exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void checkBlock(TransportPiece transportPiece, Integer blockIndex) throws Exception, IOException {

		IOPiece piece = transportPiece.piece;

		if (piece.getBlocks()[blockIndex]) {
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();

			transportPiece.unlock();

			if (transportPiece.lock()) {
				providePieceDownload(transportPiece);
			} else {
				throw new IOException("Overlapping blocks-" + piece.getPieceIndex() + "/" + blockIndex);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Send requests and decode.
	 * @param requestCounter the request counter
	 * @throws Exception the exception
	 */
	private void sendRequestsAndDecode(int requestCounter) throws Exception {
		transporter.send(baos.toByteArray());

		if (!messageDecoder.decodeMessages(requestCounter)) {
			throw new IOException();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Load empty blocks.
	 * @param piece the piece
	 * @return the list
	 */
	// private List<Integer> loadEmptyBlocks(IOPiece piece) {
	// List<Integer> processed = new ArrayList<Integer>();
	// boolean[] blocks = piece.getBlocks();
	//
	// for (int i = 0; (i < blocks.length); i++) {
	// if (!blocks[i]) {
	// processed.add(i);
	// }
	// }
	// return processed;
	// }

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #releaseSession(org.eclipse.emf.common.util.EMap)
	 */
	@Override
	public void releaseSession(EMap<String, ClientSession> sessionMap) {
		try {
			synchronized (sessionMap) {
				if (sessionMap.containsKey(announce)) {
					sessionMap.removeKey(announce);
				}
			}
		} catch (Exception e) {
			Log.log(MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #processPeersSeeds
	 * (eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession, int)
	 */
	@Override
	public void processPeersSeeds(SwarmSession swarmSession, int inc) {
		AdditionalInfo additionalInfo = ((ExtTorrent) swarmSession.getTorrent()).getAdditionalInfo();
		processPeersSeeds(additionalInfo, inc);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #processPeersSeeds
	 * (eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo, int)
	 */
	@Override
	public void processPeersSeeds(AdditionalInfo additionalInfo, int inc) {
		try {
			if (session.isSeed()) {
				int seeds = additionalInfo.getSeeds();
				if (!((seeds <= 0) && (inc < 0))) {
					additionalInfo.setSeeds(seeds + inc);
				}
			} else {
				int peers = additionalInfo.getPeers();
				if (!((peers <= 0) && (inc < 0))) {
					additionalInfo.setPeers(peers + inc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Compute session speed.
	 * @param length the length
	 * @param endTime the end time
	 */
	private void computeSessionSpeed(final int length, final long endTime) {
		new Thread() {
			@Override
			public void run() {
				try {
					float diff = (endTime - session.getSpeedContainer().getStartTime()) / 1000f; // s
					if ((length > 0) && (diff > 0)) {
						session.getSpeedContainer().setSpeed(length / diff);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
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
	 * Encode four byte number.
	 * @param i the i
	 * @return the byte[]
	 */
	public byte[] encodeFourByteNumber(int i) {
		return new byte[] { (byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i };
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# getExtTorrent()
	 */
	@Override
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler#getSession ()
	 */
	@Override
	public ClientSession getSession() {
		return session;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IHandler# getSwarmSession()
	 */
	@Override
	public SwarmSession getSwarmSession() {
		return swarmSession;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler #getTransporter()
	 */
	@Override
	public Transporter getTransporter() {
		return transporter;
	}
}
