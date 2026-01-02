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
package eu.kalafatic.gemini.bt.tm.controller.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.actions.SendTorrentToTrackersAction;
import eu.kalafatic.gemini.bt.tm.controller.actions.SendTorrentToWebAction;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.EHandler;

/**
 * The Class class CMDUtils.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class CMDUtils {

	/** The Constant CLIENT. */
	public static final int CLIENT = 1;

	/** The Constant TRACKER. */
	public static final int TRACKER = 2;

	/** The Constant WEB. */
	public static final int WEB = 3;

	/** The INSTANCE. */
	private volatile static CMDUtils INSTANCE;

	/**
	 * Gets the single instance of CMDUtils.
	 * @return single instance of CMDUtils
	 */
	public static CMDUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (CMDUtils.class) {
				INSTANCE = new CMDUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Synchronize with.
	 * @param sync the sync
	 * @param extTorrent the ext torrent
	 * @param args the args
	 */
	public void synchronizeWith(int sync, final ExtTorrent extTorrent, Object... args) {
		StatusLineManager statusLineManager = (StatusLineManager) AppData.getInstance().getStatusLineManager();
		switch (sync) {
		case CLIENT:
			syncWithClient(statusLineManager, extTorrent.getPath());
			break;
		case TRACKER:
			syncWithTracker(statusLineManager, extTorrent);
			break;
		case WEB:
			syncWithWeb(statusLineManager, extTorrent, (String) args[0]);
			break;
		default:
			break;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sync with client.
	 * @param statusLineManager the status line manager
	 * @param path the path
	 */
	private void syncWithClient(final StatusLineManager statusLineManager, final String path) {
		Display.getDefault().asyncExec(new Runnable() {
			IProgressMonitor monitor = statusLineManager.getProgressMonitor();

			@Override
			public void run() {
				try {
					monitor.beginTask("Importing Torrent  ...", 100);
					statusLineManager.update(true);

					CommandFactory.INSTANCE.executeCommand(EHandler.ADD_TORRENT.ID, path, "true");

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					monitor.done();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Sync with tracker.
	 * @param statusLineManager the status line manager
	 * @param extTorrent the ext torrent
	 */
	private void syncWithTracker(final StatusLineManager statusLineManager, final ExtTorrent extTorrent) {
		Display.getDefault().asyncExec(new Runnable() {
			IProgressMonitor monitor = statusLineManager.getProgressMonitor();

			@Override
			public void run() {
				try {
					monitor.beginTask("Sending Torrent to trackers ...", 100);
					statusLineManager.update(true);
					new SendTorrentToTrackersAction().send(extTorrent);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					monitor.done();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Sync with web.
	 * @param statusLineManager the status line manager
	 * @param extTorrent the ext torrent
	 * @param torrentAsString the torrent as string
	 */
	private void syncWithWeb(final StatusLineManager statusLineManager, final ExtTorrent extTorrent, final String torrentAsString) {
		Display.getDefault().asyncExec(new Runnable() {
			IProgressMonitor monitor = statusLineManager.getProgressMonitor();

			@Override
			public void run() {
				try {
					monitor.beginTask("Sending Torrent to web ...", 100);
					statusLineManager.update(true);

					new SendTorrentToWebAction().send(extTorrent, torrentAsString);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					monitor.done();
				}
			}
		});
	}
}
