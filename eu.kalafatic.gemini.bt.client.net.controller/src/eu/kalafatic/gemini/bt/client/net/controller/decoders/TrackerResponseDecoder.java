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
package eu.kalafatic.gemini.bt.client.net.controller.decoders;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences.MODULE;
import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.BT_CLIENT_PORT;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.BUFFER_SIZE;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.INTERFACES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.bt.utils.decoders.BDecoder;
import eu.kalafatic.gemini.core.models.NetInterface;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerResponseDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("unchecked")
public class TrackerResponseDecoder {

	/** The input stream. */
	private InputStream inputStream;

	/** The decode map. */
	private Map<String, Object> decodeMap;

	/** The denied. */
	private List<String> denied = new ArrayList<String>();

	/**
	 * Instantiates a new tracker response decoder.
	 */
	public TrackerResponseDecoder() {
		init();
	}

	// ---------------------------------------------------------------

	/**
	 * Instantiates a new tracker response decoder.
	 * @param inputStream the input stream
	 */
	public TrackerResponseDecoder(InputStream inputStream) {
		this.inputStream = inputStream;
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		for (NetInterface myInterface : INTERFACES) {

			for (String address : myInterface.getAddress()) {
				denied.add(HTTP + address + ":" + BT_CLIENT_PORT);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Read response.
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] readResponse() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
		try {
			while (true) {
				byte[] buf = new byte[BUFFER_SIZE];
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

	// ---------------------------------------------------------------

	/**
	 * Decode.
	 * @param extTorrent the ext torrent
	 * @param response the response
	 * @return the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Map<String, Object> decode(ExtTorrent extTorrent, byte[] response) throws IOException {
		decodeMap = (Map<String, Object>) BDecoder.decode(response);
		// wrong encoding
		if (decodeMap == null) {
			return null;
		}
		// If present, then no other keys may be present. The value is a
		// human-readable error message as to why the request failed (string).

		// d14:failure reason85:Torrent deleted or not in pool yet. Go to
		// Demonoid.com and read the FAQ for more infoe
		if (decodeMap.containsKey(ETrackerResponseProtocol.FAILURE_REASON.getLiteral())) {
			NetworkManager.getInstance().setViewMessage(extTorrent, EViewsMessages.TRACKER_SENT_FAILURE);
			Object object = decodeMap.get(ETrackerResponseProtocol.FAILURE_REASON.getLiteral());

			if (object instanceof byte[]) {
				byte[] res = (byte[]) object;
				String failureReason = new String(res);
				throw new IOException(failureReason);
			}
			return null;
		}
		decodePeers();
		return decodeMap;
	}

	// ---------------------------------------------------------------

	/**
	 * Decode peers.
	 */
	private void decodePeers() {
		Set<String> clients = null;

		Object object = decodeMap.get("peers");

		if (object instanceof byte[]) {
			byte[] bytes = (byte[]) object;
			clients = decodeBinaryPeers(bytes);

		} else if (object instanceof List<?>) {
			List<?> dictionary = (List<?>) object;
			clients = decodeDictionaryPeers(dictionary);
		}
		decodeMap.put("peers", clients);
	}

	// ---------------------------------------------------------------

	/**
	 * Decode dictionary peers.
	 * @param dictionary the dictionary
	 * @return the sets the
	 */
	private Set<String> decodeDictionaryPeers(List<?> dictionary) {

		Set<String> announces = new HashSet<String>();

		for (Iterator<?> iterator = dictionary.iterator(); iterator.hasNext();) {
			Object object = iterator.next();

			if (object instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) object;

				// byte[] idBytes = (byte[]) map.get("peer id");
				byte[] ipBytes = (byte[]) map.get("ip");
				Long port = (Long) map.get("port");

				try {
					InetAddress address = InetAddress.getByName(new String(ipBytes));

					String announce = "http:/" + address + ":" + port;

					if (denied.contains(announce)) {
						continue;
					}
					announces.add(announce);

				} catch (UnknownHostException e) {
					Log.log(MODULE, e);
				}
			}
		}
		return announces;

	}

	// ---------------------------------------------------------------

	/**
	 * Decode binary peers.
	 * @param bytes the bytes
	 * @return the sets the
	 */
	private Set<String> decodeBinaryPeers(byte[] bytes) {
		Set<String> announces = new HashSet<String>();
		try {
			ByteBuffer byteBuffer = (ByteBuffer) ByteBuffer.allocate(bytes.length).put(bytes, 0, bytes.length).rewind();

			while (byteBuffer.remaining() > 5) {
				bytes = new byte[4];
				byteBuffer.get(bytes, 0, 4);
				InetAddress address = InetAddress.getByAddress(bytes);

				int port = ((0xFF & byteBuffer.get()) << 8) | (0xFF & byteBuffer.get());

				String announce = "http:/" + address + ":" + port;

				if (denied.contains(announce)) {
					continue;
				}

				Log.log(EBTClientPreferences.MODULE, "TRACKER-RESPONSE-DECODER-PEER : " + announce);

				announces.add(announce);
			}
		} catch (UnknownHostException e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
		return announces;
	}

}
