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
package eu.kalafatic.gemini.bt.tracker.controller.model.actions;

import java.io.File;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsFactoryImpl;
import eu.kalafatic.gemini.bt.tracker.controller.Activator;
import eu.kalafatic.gemini.bt.tracker.controller.adapters.TorrentsAdapter;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.utils.decoders.TDecoder;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.lib.EHandler;
import eu.kalafatic.gemini.core.lib.EView;
import eu.kalafatic.gemini.core.utils.HexCodec;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TrackerModelActions.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerModelActions {

	/** The instance. */
	public static TrackerModelActions INSTANCE = new TrackerModelActions();

	/** The torrents. */
	private Torrents torrents;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Adds the torrent to model.
	 * @param torrentPath the torrent path
	 * @return the torrent session
	 * @throws Exception the exception
	 */

	public TorrentSession addTorrentToModel(String torrentPath) throws Exception {
		TorrentSession torrentSession = TrackerFactory.eINSTANCE.createTorrentSession();
		decodeTorrent(torrentSession, torrentPath);

		EMap<String, Session> swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

		if (swarmMap.containsKey(torrentSession.getInfoHash())) {
			MessageDialog.openError(new Shell(Display.getDefault()), "   Error", "Torrent already exists !");
			return null;
		}

		String name = new File(torrentPath).getName();

		if (name.endsWith(EExt.TORRENT.ext)) {
			name = name.substring(0, name.lastIndexOf(EExt.TORRENT.ext));
		}

		torrentSession.setTorrentName(name);
		torrentSession.setTorrentPath(torrentPath);
		torrentSession.setComplete(1);
		torrentSession.setIncomplete(0);

		if (torrentSession.getTorrent() == null) {
			ExtTorrent extTorrent = TorrentsFactoryImpl.eINSTANCE.createExtTorrent();
			extTorrent.setName(name);
			torrentSession.setTorrent(extTorrent);
		}
		swarmMap.put(torrentSession.getInfoHash(), torrentSession);

		TrackerModelManager.getInstance().doSave();

		return torrentSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the torrent to model.
	 * @param torrentPath the torrent path
	 * @param clients the clients
	 * @throws Exception the exception
	 */
	public void addTorrentToModel(String torrentPath, String... clients) throws Exception {
		TorrentSession torrentSession = addTorrentToModel(torrentPath);

		for (int i = 0; i < clients.length; i++) {
			addClient(torrentSession, clients[i]);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the torrent to model.
	 * @param extTorrent the ext torrent
	 * @param trackerAnnounce the tracker announce
	 * @param clientAddress the client address
	 */
	public void addTorrentToModel(ExtTorrent extTorrent, String trackerAnnounce, String clientAddress) {
		try {
			TorrentSession torrentSession = TrackerFactory.eINSTANCE.createTorrentSession();
			torrentSession.setTorrent(extTorrent);

			decodeTorrent(torrentSession, extTorrent);

			EMap<String, Session> swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

			if (swarmMap.containsKey(torrentSession.getInfoHash())) {
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "   Error", "Torrent already exists !");
				return;
			}
			torrentSession.setTorrentName(extTorrent.getName());
			int complete = extTorrent.getAdditionalInfo().getSeeds() + (extTorrent.isFinished() ? 1 : 0);
			torrentSession.setComplete(complete);
			int incomplete = extTorrent.getAdditionalInfo().getPeers() + (extTorrent.isFinished() ? 0 : 1);
			torrentSession.setIncomplete(incomplete);

			if (clientAddress != null) {
				addClient(torrentSession, clientAddress);
			}
			if (trackerAnnounce != null) {
				extTorrent.getAnnounceList().add(trackerAnnounce);
			}
			swarmMap.put(torrentSession.getInfoHash(), torrentSession);

			TrackerModelManager.getInstance().doSave();

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the client.
	 * @param torrentSession the torrent session
	 * @param clientAddress the client address
	 */
	private void addClient(TorrentSession torrentSession, String clientAddress) {
		try {
			ClientSession session = TrackerFactory.eINSTANCE.createClientSession();

			torrentSession.setTorrent(torrentSession.getTorrent());

			session.setAddress(clientAddress);
			session.setInfoHash(torrentSession.getInfoHash());
			session.setNote("Synchronized with client application");

			torrentSession.getClientMap().put(clientAddress, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Decode torrent.
	 * @param session the session
	 * @param torrentPath the torrent path
	 * @throws Exception the exception
	 */
	private void decodeTorrent(TorrentSession session, String torrentPath) throws Exception {
		ExtTorrent extTorrent = TDecoder.INSTANCE.decode(new File(torrentPath), true);
		decodeTorrent(session, extTorrent);
	}

	// ---------------------------------------------------------------

	/**
	 * Decode torrent.
	 * @param session the session
	 * @param extTorrent the ext torrent
	 * @throws Exception the exception
	 */
	private void decodeTorrent(TorrentSession session, ExtTorrent extTorrent) throws Exception {
		byte[] hash = extTorrent.getInfo().getHash();
		session.setInfoHash(HexCodec.bytesToHexString(hash));
		session.setTorrentLen(extTorrent.getAdditionalInfo().getFileSize());
		session.setTorrent(extTorrent);
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the torrent from model.
	 * @param torrentName the torrent name
	 */
	public void removeTorrentFromModel(String torrentName) {
		EMap<String, Session> swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();
		swarmMap.removeKey(torrentName);
		TrackerModelManager.getInstance().doSave();
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the torrents adapter.
	 */
	public void addTorrentsAdapter() {
		Torrents torrents = getTorrents();

		if (torrents != null) {
			boolean synchronize = Activator.getPreferences().getBoolean(ECorePreferences.SYNC_CLIENT.getName(), (Boolean) ECorePreferences.SYNC_CLIENT.getDef());

			if (synchronize) {
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

				if (activePage == null) {
					return;
				}
				IViewPart view = activePage.findViewReference(EView.SWARMS.ID).getView(false);

				if (view != null) {
					torrents.eAdapters().add(new TorrentsAdapter((TreeViewer) ((IViewer) view).getViewer()));
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents.
	 * @return the torrents
	 */
	public Torrents getTorrents() {
		if (torrents == null) {
			try {
				torrents = (Torrents) CommandFactory.INSTANCE.executeCommand(EHandler.INIT_TORRENTS_MODEL.ID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return torrents;
	}
}
