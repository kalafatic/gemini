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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.HexCodec;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SendTorrentToTrackersAction.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class SendTorrentToTrackersAction {

	/** The client listen port. */
	private int trackrUpldPort, clientListenPort;

	/** The peer id. */
	private String peerId;

	/** The template. */
	private String template = "torrentName={0}&info_hash={1}&port={2}&peer_id={3}&torrentLen={4}";

	/**
	 * Instantiates a new send torrent to trackers action.
	 */
	public SendTorrentToTrackersAction() {
		trackrUpldPort = PREFERENCES.getInt(ECorePreferences.TRACKER_UPLOAD_PORT.getName(), (Integer) ECorePreferences.TRACKER_UPLOAD_PORT.getDef());
		clientListenPort = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());
		peerId = PREFERENCES.get(ECorePreferences.APP_ID.getName(), (String) ECorePreferences.APP_ID.getDef());
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Send.
	 * @param extTorrent the ext torrent
	 */
	public void send(ExtTorrent extTorrent) {
		Collection<Tracker> values = BTStructureModelManager.getInstance().getBtStructure().getTrackersMap().values();

		for (Tracker tracker : values) {
			if (!tracker.isChecked()) {
				continue;
			}
			Socket connection = null;
			try {
				String data = getData(extTorrent, tracker);

				URL trackerUrl = new URL(tracker.getAnnounce());
				String host = trackerUrl.getHost();

				connection = getConnection(host, trackrUpldPort);

				if (connection == null) {
					continue;
				}
				BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

				wr.write(data);
				wr.flush();
				connection.shutdownOutput();

				StringBuffer request = new StringBuffer();

				BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String str;
				while ((str = rd.readLine()) != null) {
					request.append(str);
				}
				wr.close();
				rd.close();

			} catch (MalformedURLException e) {
				Log.log(ETMPreferences.MODULE, e);
			} catch (IOException e) {
				Log.log(ETMPreferences.MODULE, e);
			}

		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the connection.
	 * @param host the host
	 * @param remotePort the remote port
	 * @return the connection
	 */
	private Socket getConnection(String host, int remotePort) {
		Socket connection = null;
		try {
			InetAddress remoteAddr = InetAddress.getByName(host);
			connection = new Socket(remoteAddr, remotePort);

		} catch (Exception e) {
			MessageDialog.openWarning(Display.getCurrent().getActiveShell(), "   Warning", "Address : " + host + ":" + remotePort + "\n" + e.toString());
		}
		return connection;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the data.
	 * @param extTorrent the ext torrent
	 * @param tracker the tracker
	 * @return the data
	 */
	private String getData(ExtTorrent extTorrent, Tracker tracker) {
		String result = null;
		try {
			byte[] infoHash = extTorrent.getInfo().getHash();

			Object[] args = new Object[] { extTorrent.getName(), HexCodec.bytesToHexString(infoHash), clientListenPort, peerId, extTorrent.getAdditionalInfo().getFileSize() };

			result = MessageFormat.format(template, args);

		} catch (Exception e) {
			Log.log(ETMPreferences.MODULE, e);
		}
		return result;
	}
}
