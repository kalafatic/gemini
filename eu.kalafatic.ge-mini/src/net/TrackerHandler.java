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
package net;

import static protocols.FTP.PEER_ID;
import io.AIO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import main.Main;
import protocols.FTP;
import utils.TDecoder;

/**
 * The Class TrackerHandler.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TrackerHandler {

	/**
	 * Gets the peers from tracker.
	 * 
	 * @param announce
	 *            the announce
	 * @return the peers from tracker
	 * @throws Exception
	 *             the exception
	 */
	public static List<String> getPeersFromTracker(String announce)
			throws Exception {
		if (announce.startsWith(FTP.HTTP)) {
			return processHTTP(announce);

		} else if (announce.startsWith(FTP.UDP)) {

			if (announce.contains(FTP.HTTP)) {
				String preferHttp = getHTTP(announce);
				return processHTTP(preferHttp);
			} else {
				return processUDP(announce);
			}
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @param announce
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static List<String> processHTTP(String announce)
			throws MalformedURLException, IOException {
		URL url = new URL(announce + getRequest());

		URLConnection urlConnection = url.openConnection();
		urlConnection.setConnectTimeout(Main.TIMEOUT);
		urlConnection.setDoOutput(true);

		byte[] response = AIO.readBytes(urlConnection.getInputStream());
		return decodePeers(TDecoder.decode(response));
	}

	private static List<String> processUDP(String announce) throws Exception {
		announce = announce.replaceFirst("udp", "http");

		URL url = new URL(announce);

		ByteBuffer sendBuffer = ByteBuffer.wrap(getRequest().getBytes());

		ByteBuffer receiveBuffer = ByteBuffer
				.allocate(Main.TRANSPORT_BLOCK_SIZE);
		receiveBuffer.order(ByteOrder.BIG_ENDIAN);

		DatagramChannel channel = DatagramChannel.open();
		SocketAddress socketAddress = new InetSocketAddress(url.getHost(),
				url.getPort());
		channel.send(sendBuffer, socketAddress);

		byte[] receiveData = new byte[Main.TRANSPORT_BLOCK_SIZE];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);

		try {
			channel.socket().setSoTimeout(Main.TIMEOUT);
			channel.socket().receive(receivePacket);

			receiveBuffer.put(receivePacket.getData());
		} finally {
			channel.close();
		}
		int position = receiveBuffer.position();
		byte[] response = new byte[position];
		receiveBuffer.flip();

		receiveBuffer.get(response);

		return decodePeers(TDecoder.decode(response));
	}

	private static String getHTTP(String announce) {
		String[] split = announce.split("(?=http://)");
		String result = split[1];
		if (result.contains("?")) {
			result = result.substring(0, result.indexOf('?'));
		}
		return result;
	}

	/**
	 * Decode peers.
	 * 
	 * @param decodedMap
	 *            the decoded map
	 * @return the list
	 */
	private static List<String> decodePeers(Map decodedMap) {
		List<String> clients = null;

		Object object = decodedMap.get("peers");

		if (object instanceof byte[]) {
			clients = new ArrayList(TDecoder.decodeBinaryPeers((byte[]) object));
		} else if (object instanceof List<?>) {
			clients = new ArrayList(
					TDecoder.decodeDictionaryPeers((List<?>) object));
		}
		return clients;
	}

	/**
	 * Gets the request.
	 * 
	 * @return the request
	 */
	private static String getRequest() {
		String request = "?info_hash=" + Main.torrent.hashString;
		request += "&peer_id=" + PEER_ID;
		request += "&port=" + Integer.toString(Main.PORT);
		request += "&uploaded=" + Long.toString(Main.torrent.uploaded);
		request += "&downloaded=" + Long.toString(Main.torrent.downloaded);
		request += "&left=" + Long.toString(Main.torrent.left);
		request += "&compact=1";
		return request;
	}
}
