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
package eu.kalafatic.gemini.bt.client.net.rc.controller.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.lib.EEncoding;

/**
 * The Class class TorrentForm.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentForm {

	/** The gradient. */
	String gradient = "color: #669;border-top: 1px solid #fff;background: #e8edff url(\''images/gradback.png\'') repeat-x; \"";

	/** The hoover. */
	String hoover = "color: #339; background: #d0dadf url(\''images/gradhover.png\'') repeat-x;\"";

	/** The onclick start. */
	String onclickStart = "onclick=\"location=''http://localhost:6881/?cmd=T_START&torrent=";

	/** The onclick stop. */
	String onclickStop = "onclick=\"location=''http://localhost:6881/?cmd=T_STOP&torrent=";

	/** The tr1. */
	String tr, tr1 = "<tr style=\"color: #339; background:#e8edff;\">";

	/** The tr2. */
	String tr2 = "<tr style=\"color: #339; background:#55ff55;\">";

	/** The td1. */
	String td1 = "<td style=\"width: 75px;\">";

	/** The sel. */
	String sel = "<select name=\"action\" size=\"1\">";

	/** The op1. */
	String op1 = "<option " + onclickStart + "{0}''\">Start";

	/** The op2. */
	String op2 = "<option " + onclickStop + "{0}''\">Stop";

	/** The td2. */
	String td2 = "<td style=\"width:auto;colspan=2 " + gradient + "> {1} </td>";

	/** The td3. */
	String td3 = "<td style=\"width: 70px;\" > {2} </td>";

	/** The td4. */
	String td4 = "<td style=\"width: 60px;\" > {3} / {4} </td></tr>";

	/** The torrent form started. */
	private String torrentFormStarted = td1 + sel + op2 + op1 + "</select> </td>" + td2 + td3 + td4;

	/** The torrent form stopped. */
	private String torrentFormStopped = td1 + sel + op1 + op2 + "</select> </td>" + td2 + td3 + td4;

	/** The Constant INSTANCE. */
	public static final TorrentForm INSTANCE = new TorrentForm();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the torrent form.
	 * @param extTorrent the ext torrent
	 * @return the torrent form
	 */
	public String getTorrentForm(ExtTorrent extTorrent) {
		String torrentName = extTorrent.getName();
		String torrentState = extTorrent.getStatus().getLiteral();
		String downloaders = Integer.toString(extTorrent.getAdditionalInfo().getPeers());
		String uploaders = Integer.toString(extTorrent.getAdditionalInfo().getDownloaders());

		String element = "";
		try {
			element = URLEncoder.encode(torrentName,
			/* System.getProperty("file.encoding") */
			EEncoding.ISO_1.getLiteral()).replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		tr = extTorrent.isFinished() ? tr2 : tr1;

		boolean stopped = extTorrent.getStatus().equals(EViewsMessages.STOPPED) || extTorrent.getStatus().equals(EViewsMessages.PAUSED);

		return getTorrentForm(stopped, element, torrentName, torrentState, downloaders, uploaders);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrent form.
	 * @param stopped the stopped
	 * @param args the args
	 * @return the torrent form
	 */
	public String getTorrentForm(boolean stopped, Object... args) {
		if (stopped) {
			return MessageFormat.format(tr + torrentFormStopped, args);
		}
		return MessageFormat.format(tr + torrentFormStarted, args);
	}
}
