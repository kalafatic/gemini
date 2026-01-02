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
package eu.kalafatic.gemini.bt.tm.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.INTERFACES;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ANNOUNCE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HTTP;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ROOT;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsFactoryImpl;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructureFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.models.NetInterface;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class BTStructureModelManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings("unused")
public class BTStructureModelManager {

	/** The INSTANCE. */
	private volatile static BTStructureModelManager INSTANCE = null;

	/** The bt structure file. */
	private File btStructureFile;

	/** The bt structure uri. */
	private URI btStructureURI;

	/** The bt structure. */
	private BTStructure btStructure;

	/**
	 * Instantiates a new bT structure model manager.
	 */
	public BTStructureModelManager() {
		initBTStructureModel();
	}

	/**
	 * Gets the single instance of BTStructureModelManager.
	 * @return single instance of BTStructureModelManager
	 */
	public static BTStructureModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (BTStructureModelManager.class) {
				INSTANCE = new BTStructureModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the bt structure model.
	 */
	private void initBTStructureModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			btStructureFile = new File(models.concat(File.separator).concat("Model.btStructure"));
			btStructureURI = URI.createURI("file:/" + btStructureFile.getAbsolutePath());

			if (btStructureFile.exists()) {
				openModel();
			} else {
				createModel();
			}
			setUpTrackers();
			doSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 */
	private void openModel() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(btStructureURI);
		Resource ecoreResource = resourceSet.getResource(btStructureURI, true);
		btStructure = (BTStructure) ecoreResource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the model.
	 */
	private void createModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(BtStructurePackage.eNS_URI, BtStructureFactory.eINSTANCE);
			Resource resource = resourceSet.createResource(btStructureURI);
			btStructure = BtStructureFactory.eINSTANCE.createBTStructure();
			resource.getContents().add(btStructure);

			doSave();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the new torrent.
	 * @return the ext torrent
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ExtTorrent createNewTorrent() throws IOException {
		String name = getTempName(btStructure.getNewTorrents().size());
		return createNewTorrent(name, true);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the new torrent.
	 * @param namePath the name path
	 * @param random the random
	 * @return the ext torrent
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ExtTorrent createNewTorrent(String namePath, boolean random) throws IOException {
		String path;
		String torrentsFolderDir = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

		File directory = new File(torrentsFolderDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		if (!namePath.endsWith(EExt.TORRENT.ext)) {
			namePath += EExt.TORRENT.ext;
		}

		if (random) {
			path = torrentsFolderDir.concat(File.separator).concat(new File(namePath).getName());
			File newFile = new File(path);
			newFile.createNewFile();
			return createNewTorrent(newFile.getName(), path);

		} else {
			File newFile = new File(namePath);
			return createNewTorrent(newFile.getName(), newFile.getAbsolutePath());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the new torrent.
	 * @param name the name
	 * @param path the path
	 * @return the ext torrent
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ExtTorrent createNewTorrent(String name, String path) throws IOException {
		ExtTorrent newTorrent = TorrentsFactoryImpl.eINSTANCE.createExtTorrent();
		newTorrent.setName(name);
		newTorrent.setInfo(TorrentsFactoryImpl.eINSTANCE.createInfo());
		newTorrent.setAdditionalInfo(TorrentsFactoryImpl.eINSTANCE.createAdditionalInfo());
		newTorrent.setPath(path);
		newTorrent.setLock(new ReentrantReadWriteLock(true));
		newTorrent.getAdditionalInfo().setLock(new ReentrantReadWriteLock(true));

		setTreeObject(newTorrent);

		ModelUtils.doSave(btStructure);
		return newTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the tree object.
	 * @param extTorrent the new tree object
	 */
	public void setTreeObject(ExtTorrent extTorrent) {
		FileTreeObject rootTreeObject = BtStructureFactory.eINSTANCE.createFileTreeObject();
		rootTreeObject.setName(ROOT);

		FileTreeObject fileTreeObject = BtStructureFactory.eINSTANCE.createFileTreeObject();
		fileTreeObject.setName(extTorrent.getName());
		fileTreeObject.setPath(extTorrent.getPath());
		fileTreeObject.setFile(false);
		fileTreeObject.setParent(rootTreeObject);

		btStructure.getTreeObjects().put(extTorrent.getName(), rootTreeObject);
		btStructure.getNewTorrents().put(extTorrent.getName(), extTorrent);

		rootTreeObject.getChildMap().put(ROOT, fileTreeObject);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the temp name.
	 * @param size the size
	 * @return the temp name
	 */
	private String getTempName(int size) {
		String key = Integer.toString(size);

		if (btStructure.getNewTorrents().containsKey(key)) {
			getTempName(size + 1);
		}
		return key;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up trackers.
	 */
	private void setUpTrackers() {
		int listenPort = PREFERENCES.getInt(ECorePreferences.TRACKER_PORT.getName(), (Integer) ECorePreferences.TRACKER_PORT.getDef());

		List<NetInterface> interfaces = INTERFACES;

		for (NetInterface netInterface : interfaces) {
			try {

				for (String ipString : netInterface.getAddress()) {

					String ip = resolveAddress(ipString);

					if (ip == null) {
						continue;
					}
					Tracker tracker = BtStructureFactory.eINSTANCE.createTracker();
					tracker.setChecked(true);

					String announce = HTTP + ip + ":" + Integer.toString(listenPort) + "/" + ANNOUNCE;

					tracker.setAnnounce(announce);

					btStructure.getTrackersMap().put(announce, tracker);
				}
			} catch (Exception e) {
				// interface problem
				Log.log(ETMPreferences.MODULE, e);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve address.
	 * @param ip the ip
	 * @return the string
	 */
	public String resolveAddress(String ip) {
		boolean isIpv4 = true;
		InetAddress address = null;

		try {
			InetAddress[] ia = InetAddress.getAllByName(ip);

			if (ia[0] instanceof Inet4Address) {
				address = ia[0];

			} else if (ia[0] instanceof Inet6Address) {
				address = ia[0];
				isIpv4 = false;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		int protocolValue = PREFERENCES.getInt(ECorePreferences.IP_PROTOCOL.getName(), ((Integer) ECorePreferences.IP_PROTOCOL.getDef()));

		if (isIpv4) {
			if (protocolValue == 4 || protocolValue == 46) {
				return ip;
			}
		} else {
			if (protocolValue == 6 || protocolValue == 46) {
				return ip;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		ModelUtils.doSave(btStructure);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the bt structure.
	 * @return the bt structure
	 */
	public BTStructure getBtStructure() {
		return btStructure;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the bt structure.
	 * @param btStructure the new bt structure
	 */
	public void setBtStructure(BTStructure btStructure) {
		this.btStructure = btStructure;
	}

}
