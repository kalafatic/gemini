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
package eu.kalafatic.gemini.bt.tm.controller.actions;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SendTorrentToWebAction.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class SendTorrentToWebAction {

	/** The charset. */
	private final Charset charset = Charset.forName(EEncoding.ISO_1.getLiteral());

	/** The decoder. */
	private final CharsetDecoder decoder = charset.newDecoder();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Send.
	 * @param extTorrent the ext torrent
	 * @param torrentString the torrent string
	 */
	public void send(ExtTorrent extTorrent, String torrentString) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		SocketChannel channel = null;
		try {
			String handshake = getHandshake(extTorrent);

			baos.write(handshake.getBytes(EEncoding.ISO_1.getLiteral()));
			baos.write(torrentString.getBytes());

			channel = getConnection();

			if (channel == null) {
				return;
			}
			channel.write(ByteBuffer.wrap(baos.toByteArray()));
			baos.close();

			channel.socket().shutdownOutput();

			byte[] responseBytes = getResponse(channel);

			String response = decoder.decode(ByteBuffer.wrap(responseBytes)).toString();

			if (response.equalsIgnoreCase("ok")) {
				Log.log(ETMPreferences.MODULE, "SEND-TORRENT-TO-WEB : OK");
			}
			channel.close();

		} catch (MalformedURLException e) {
			Log.log(ETMPreferences.MODULE, e);
		} catch (IOException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the response.
	 * @param channel the channel
	 * @return the response
	 */
	private byte[] getResponse(SocketChannel channel) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
		byte[] receiveBytes;

		int readed = 0;

		try {
			while (true) {
				readed = channel.read(receiveBuffer);
				if (readed == -1) {
					break;
				} else {
					receiveBuffer.flip();
					receiveBytes = new byte[readed];
					receiveBuffer.get(receiveBytes, 0, readed);
					baos.write(receiveBytes);
					receiveBuffer = ByteBuffer.allocate(1024);
				}
			}
		} catch (IOException e) {
			Log.log(ETMPreferences.MODULE, e);

		}
		return baos.toByteArray();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the connection.
	 * @return the connection
	 */
	private SocketChannel getConnection() {

		String webHost = PREFERENCES.get(ECorePreferences.HOME_SITE.getName(), (String) ECorePreferences.HOME_SITE.getDef());

		int webPort = PREFERENCES.getInt(ECorePreferences.WEB_UPLOAD_PORT.getName(), (Integer) ECorePreferences.WEB_UPLOAD_PORT.getDef());

		SocketChannel channel = null;
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(true);
			channel.connect(new InetSocketAddress(webHost, webPort));

		} catch (Exception e) {
			MessageDialog.openWarning(Display.getCurrent().getActiveShell(), "   Warning", "Address : " + webHost + ":" + webPort + "\n" + e.toString());
			return null;
		}
		return channel;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the handshake.
	 * @param extTorrent the ext torrent
	 * @return the handshake
	 */
	private String getHandshake(ExtTorrent extTorrent) {

		StringBuffer data = new StringBuffer();

		try {
			data.append("name=");
			data.append(extTorrent.getName());

			data.append("&size=");
			data.append(Long.toString(extTorrent.getAdditionalInfo().getFileSize()));

			data.append("&data=");

		} catch (Exception e) {
			Log.log(ETMPreferences.MODULE, e);
		}
		return data.toString();
	}
}
