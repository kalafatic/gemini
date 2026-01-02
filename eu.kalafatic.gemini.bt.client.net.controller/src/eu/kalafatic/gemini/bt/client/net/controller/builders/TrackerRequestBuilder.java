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
package eu.kalafatic.gemini.bt.client.net.controller.builders;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FProtocolConstants.PEER_ID;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.net.URLEncoder;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EEncoding;

/**
 * The Class class TrackerRequestBuilder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerRequestBuilder {

	/** The ext torrent. */
	private final ExtTorrent extTorrent;

	/** The client port. */
	// private final String peerId;

	/** The client port. */
	private final int clientPort;

	/** The numwant. */
	private int numwant;

	/** The finished string buffer. */
	private StringBuffer finishedStringBuffer = new StringBuffer();

	/** The uploaded. */
	private long uploaded;

	/** The downloaded. */
	private long downloaded;

	/** The file size. */
	private long fileSize;

	/**
	 * Instantiates a new tracker request builder.
	 * @param extTorrent the ext torrent
	 */
	public TrackerRequestBuilder(ExtTorrent extTorrent) {
		this.extTorrent = extTorrent;

		// peerId = PREFERENCES.get(ECorePreferences.APP_ID.getName(),
		// (String) ECorePreferences.APP_ID.getDef());

		// peerId = FProtocolConstants.getClientID();

		clientPort = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

		numwant = Activator.getPreferences().getInt(EBTClientPreferences.TRACKER_NUMWANT.getName(), (Integer) EBTClientPreferences.TRACKER_NUMWANT.getDef());

		fileSize = extTorrent.getAdditionalInfo().getFileSize();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Builds the request.
	 * @return the string buffer
	 * @throws Exception the exception
	 */
	public StringBuffer buildRequest() throws Exception {
		boolean finished = extTorrent.isFinished();

		if (finished && finishedStringBuffer.length() > 0) {
			return finishedStringBuffer;
		}
		uploaded = extTorrent.getAdditionalInfo().getUploaded();
		downloaded = extTorrent.getAdditionalInfo().getDownloaded();

		if (finished || (downloaded > fileSize)) {
			downloaded = fileSize;
		}
		return buildRequest(finished ? 0 : numwant, fileSize - downloaded);
	}

	// ---------------------------------------------------------------

	/**
	 * Builds the request.
	 * @param numwant the numwant
	 * @param left the left
	 * @return the string buffer
	 * @throws Exception the exception
	 */
	private StringBuffer buildRequest(int numwant, long left) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();

		byte[] infoHash = extTorrent.getInfo().getHash();

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.INFO_HASH.getLiteral());
		stringBuffer.append(URLEncoder.encode(new String(infoHash, EEncoding.ISO_1.getLiteral()), EEncoding.ISO_1.getLiteral()).replaceAll("\\+", "%20"));

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.PEER_ID.getLiteral());
		stringBuffer.append(PEER_ID);

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.PORT.getLiteral());
		stringBuffer.append(Integer.toString(clientPort));

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.UPLOADED.getLiteral());
		stringBuffer.append(Long.toString(uploaded));

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.DOWNLOADED.getLiteral());
		stringBuffer.append(Long.toString(downloaded));

		// REQUIRED
		stringBuffer.append(ETrackerRequestProtocol.LEFT.getLiteral());
		stringBuffer.append(Long.toString(left));

		// OPTIONAL
		stringBuffer.append(ETrackerRequestProtocol.COMPACT.getLiteral());
		stringBuffer.append("1");

		// optional
		// stringBuffer.append(ETrackerRequestProtocol.EVENT.getLiteral());
		// stringBuffer.append(trackerSession.getEvent().getLiteral());

		stringBuffer.append(ETrackerRequestProtocol.NUMWANT.getLiteral());
		stringBuffer.append(Integer.toString(numwant));

		if (extTorrent.isFinished()) {
			// finished
			finishedStringBuffer = stringBuffer;
		}
		return stringBuffer;
	}

}