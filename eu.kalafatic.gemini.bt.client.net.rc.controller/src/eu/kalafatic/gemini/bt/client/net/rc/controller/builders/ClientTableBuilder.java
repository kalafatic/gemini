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
package eu.kalafatic.gemini.bt.client.net.rc.controller.builders;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.Enumerator;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.rc.controller.model.TorrentForm;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD;

/**
 * The Class class ClientTableBuilder.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ClientTableBuilder {

	/** The INSTANCE. */
	private volatile static ClientTableBuilder INSTANCE;

	/**
	 * Gets the single instance of ClientTableBuilder.
	 * @return single instance of ClientTableBuilder
	 */
	public static ClientTableBuilder getInstance() {
		if (INSTANCE == null) {
			synchronized (ClientTableBuilder.class) {
				INSTANCE = new ClientTableBuilder();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Builds the client forms.
	 * @param selection the selection
	 * @return the string
	 */
	public String buildClientForms(Enumerator selection) {
		StringBuffer sb = new StringBuffer();
		Collection<ExtTorrent> values = new ArrayList<ExtTorrent>(NetworkModelManager.getInstance().getTorrents().getTorrentMap().values());

		switch (selection.getValue()) {
		case ERCCMD.SEL_ALL_VALUE:
			values.addAll(NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().values());
			break;
		case ERCCMD.SEL_UNF_VALUE:
			break;
		case ERCCMD.SEL_FIN_VALUE:
			values = NetworkModelManager.getInstance().getTorrents().getFinishedTorrentsMap().values();
			break;
		default:
			break;
		}

		for (ExtTorrent extTorrent : values) {
			sb.append(TorrentForm.INSTANCE.getTorrentForm(extTorrent));
		}
		return sb.toString();
	}
}
