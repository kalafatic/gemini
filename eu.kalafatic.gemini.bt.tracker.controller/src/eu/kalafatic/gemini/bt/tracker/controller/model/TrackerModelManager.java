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
package eu.kalafatic.gemini.bt.tracker.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class TrackerModelManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerModelManager {

	/** The tracker file. */
	private File trackerFile;

	/** The tracker uri. */
	private URI trackerURI;

	/** The tracker model. */
	private TrackerModel trackerModel;

	/** The model name. */
	public final String MODEL_NAME = "Model.tracker";

	/** The client model name. */
	private final String CLIENT_MODEL_NAME = "Model.torrents";

	/** The torrents. */
	private Torrents torrents;

	/** The instance. */
	private static TrackerModelManager INSTANCE;

	/**
	 * Instantiates a new tracker model manager.
	 */
	public TrackerModelManager() {
		initTrackerModel();
	}

	/**
	 * Gets the single instance of TrackerModelManager.
	 * @return single instance of TrackerModelManager
	 */
	public static TrackerModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (TrackerModelManager.class) {
				INSTANCE = new TrackerModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the tracker model.
	 */
	private void initTrackerModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			trackerFile = new File(models.concat(File.separator).concat(MODEL_NAME));
			trackerURI = URI.createURI("file:/" + trackerFile.getAbsolutePath());

			if (trackerFile.exists()) {
				openModel();
			} else {
				createModel();
				doSave();
			}
			initAppData();

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the app data.
	 */
	private void initAppData() {
		Assert.isNotNull(trackerModel);
		AppData.getInstance().getSharedModels().put(MODEL_NAME, trackerModel);
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 */
	private void openModel() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(trackerURI);
		Resource ecoreResource = resourceSet.getResource(trackerURI, true);
		trackerModel = (TrackerModel) ecoreResource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the model.
	 */
	public void createModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(TrackerPackage.eNS_URI, TrackerPackage.eINSTANCE);

			Resource resource = resourceSet.createResource(trackerURI);
			trackerModel = TrackerFactory.eINSTANCE.createTrackerModel();

			resource.getContents().add(trackerModel);

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents.
	 * @return the torrents
	 */
	public Torrents getTorrents() {
		if (torrents == null) {
			// call client model manager to init model
			CommandFactory.INSTANCE.executeCommand("eu.kalafatic.gemini.bt.client.controller.handlers.InitTorrentModelHandler");
			torrents = (Torrents) AppData.getInstance().getSharedModels().get(CLIENT_MODEL_NAME);
		}
		return torrents;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tracker torrents.
	 * @return the tracker torrents
	 */
	public List<ExtTorrent> getTrackerTorrents() {
		List<ExtTorrent> values = new ArrayList<ExtTorrent>();
		for (Session session : trackerModel.getSwarmMap().values()) {
			ExtTorrent extTorrent = (ExtTorrent) session.getTorrent();
			if (extTorrent != null) {
				values.add((ExtTorrent) session.getTorrent());
			}
		}
		return values;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the session by name.
	 * @param torrentName the torrent name
	 * @return the session by name
	 */
	public Session getSessionByName(String torrentName) {
		for (Session session : trackerModel.getSwarmMap().values()) {
			ExtTorrent extTorrent = (ExtTorrent) session.getTorrent();
			if (extTorrent.getName().equals(torrentName)) {
				return session;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrent by name.
	 * @param torrentName the torrent name
	 * @return the torrent by name
	 */
	public ExtTorrent getTorrentByName(String torrentName) {
		return (ExtTorrent) getSessionByName(torrentName).getTorrent();
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		ModelUtils.doSave(trackerModel);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tracker model.
	 * @return the tracker model
	 */
	public TrackerModel getTrackerModel() {
		return trackerModel;
	}
}
