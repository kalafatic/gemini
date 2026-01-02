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
package eu.kalafatic.gemini.bt.client.controller.handlers;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.client.controller.dialogs.RemoveDialog;
import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.controller.utils.TorrentTableUtil;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class RemoveTorrentsHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RemoveTorrentsHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

		if (activePart instanceof ViewPart) {
			ViewPart viewPart = (ViewPart) activePart;

			if (viewPart instanceof IViewer) {
				TableViewer tableViewer = (TableViewer) ((IViewer) viewPart).getViewer();

				remove(activePart.getSite().getShell(), getSelected(tableViewer));
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the.
	 * @param shell the shell
	 * @param input the input
	 */
	private void remove(Shell shell, List<ExtTorrent> input) {
		RemoveDialog rd = new RemoveDialog(shell, input);
		int result = rd.open();
		try {
			if (result == IDialogConstants.CANCEL_ID) {
				return;
			}
			ArrayList<ExtTorrent> selected = new ArrayList<ExtTorrent>(rd.getRemoveMap().values());

			for (ExtTorrent extTorrent : selected) {
				extTorrent.setEnabled(false);
			}
			NetworkManager.getInstance().stopSwarms(selected);

			if (result == IDialogConstants.YES_ID) {
				removeSwarms(selected);
			} else if (result == IDialogConstants.YES_TO_ALL_ID) {
				removeSwarms(selected);
				removeFromDisc(selected);
			}
			ClientModelManager.getInstance().doSave();
			NetworkModelManager.getInstance().doSave();

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		} finally {
			rd.close();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected.
	 * @param tableViewer the table viewer
	 * @return the selected
	 */
	private List<ExtTorrent> getSelected(TableViewer tableViewer) {
		List<ExtTorrent> input = new ArrayList<ExtTorrent>();

		List<ExtTorrent> selected = TorrentTableUtil.getInstance().getSelected(tableViewer);
		for (ExtTorrent extTorrent : selected) {
			input.add(extTorrent);
		}
		List<ExtTorrent> checked = TorrentTableUtil.getInstance().getChecked(tableViewer);
		for (ExtTorrent extTorrent : checked) {
			input.add(extTorrent);
		}
		return input;
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the from disc.
	 * @param selected the selected
	 */
	private synchronized void removeFromDisc(List<ExtTorrent> selected) {
		NetworkManager.getInstance().stopSwarms(selected);

		String downloads = PREFERENCES.get(ECorePreferences.DOWNLOADS_LOC.getName(), (String) ECorePreferences.DOWNLOADS_LOC.getDef());

		for (ExtTorrent extTorrent : selected) {
			try {
				File rootFile = new File(downloads.concat(File.separator).concat(extTorrent.getName()));

				if (rootFile.exists()) {
					deleteDirectory(rootFile);
				}
				File torrentFile = new File(extTorrent.getPath());
				if (torrentFile.exists()) {
					torrentFile.delete();
				}
				Log.log(EBTClientPreferences.MODULE, "TORRENT-DELETE-FROM-DISC-OK  :  " + extTorrent.getName());

			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the swarms.
	 * @param selected the selected
	 */
	private void removeSwarms(List<ExtTorrent> selected) {
		ClientNetwork clientNetwork = NetworkModelManager.getInstance().getClientNetwork();

		if (!clientNetwork.getSwarmMap().isEmpty()) {
			for (ExtTorrent extTorrent : selected) {

				try {

					final String key = extTorrent.getName();

					EMap<String, Session> swarmMap = clientNetwork.getSwarmMap();
					synchronized (swarmMap) {
						if (swarmMap.containsKey(key)) {
							swarmMap.removeKey(key);
						}
					}
					EList<String> activeSwarms = clientNetwork.getActiveSwarms();
					synchronized (activeSwarms) {
						if (clientNetwork.getActiveSwarms().contains(extTorrent.getName())) {
							clientNetwork.getActiveSwarms().remove(key);
						}
					}

					if (extTorrent.isFinished()) {
						ClientModelManager.getInstance().getTorrents().getFinishedTorrentsMap().removeKey(key);
					} else {
						ClientModelManager.getInstance().getTorrents().getTorrentMap().removeKey(key);
					}

					Log.log(EBTClientPreferences.MODULE, "TORRENT-REMOVE-OK  :  " + extTorrent.getName());

				} catch (Exception e) {
					Log.log(EBTClientPreferences.MODULE, e);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Delete directory.
	 * @param file the file
	 * @return true, if successful
	 */
	public boolean deleteDirectory(File file) {
		if (file.exists()) {
			File[] files = file.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (file.delete());
	}

	// ---------------------------------------------------------------

	/**
	 * New array.
	 * @param <T> the generic type
	 * @param type the type
	 * @param size the size
	 * @return the t[]
	 */
	public static <T> T[] newArray(Class<T[]> type, int size) {
		return type.cast(Array.newInstance(type.getComponentType(), size));
	}
}
