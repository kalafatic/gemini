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
package eu.kalafatic.gemini.bt.client.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsFactory;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;
import eu.kalafatic.gemini.bt.client.net.controller.utils.ConvertUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler.GSHf;
import eu.kalafatic.gemini.core.hack.StatusLineContributionItem;
import eu.kalafatic.gemini.core.interfaces.ISplashUser;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class ClientModelManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ClientModelManager implements ISplashUser {

	/** The torrents. */
	private Torrents torrents;

	/** The torrent file. */
	private File torrentFile;

	/** The torrent uri. */
	private URI torrentURI;

	/** The resource. */
	private Resource resource;

	/** The MODE l_ name. */
	private final String MODEL_NAME = "Model.torrents";

	/** The INSTANCE. */
	private volatile static ClientModelManager INSTANCE;

	/**
	 * Instantiates a new client model manager.
	 */
	public ClientModelManager() {
		initTorrentModel();
	}

	/**
	 * Gets the single instance of ClientModelManager.
	 * @return single instance of ClientModelManager
	 */
	public static ClientModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (ClientModelManager.class) {
				INSTANCE = new ClientModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the torrent model.
	 */
	private void initTorrentModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			torrentFile = new File(models.concat(File.separator).concat(MODEL_NAME));
			torrentURI = URI.createURI("file:/" + torrentFile.getAbsolutePath());

			if (torrentFile.exists()) {
				openModel();
				setUpTorrents();
			} else {
				createModel();
			}
			initAppData();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the app data.
	 */
	private void initAppData() {
		Assert.isNotNull(torrents);

		AppData.getInstance().getSplashUsersUsers().add(this);
		AppData.getInstance().getSharedModels().put(MODEL_NAME, torrents);

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {

				// float ratioValue = Activator.getPreferences().getFloat(
				// EBTClientPreferences.RATIO.getName(),
				// (Float) EBTClientPreferences.RATIO.getDef());

				try {
					StatusLineContributionItem speedDownItem = AppData.getInstance().getSpeedDownItem();
					StatusLineContributionItem speedUpItem = AppData.getInstance().getSpeedUpItem();

					speedDownItem.createSpeedPopUpMenu(true);
					speedUpItem.createSpeedPopUpMenu(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 * @throws Exception the exception
	 */
	private void openModel() throws Exception {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(torrentURI);
		Resource resource = resourceSet.getResource(torrentURI, true);
		resource.load(ModelUtils.SAVE_OPTIONS);
		torrents = (Torrents) resource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the model.
	 */
	private void createModel() {
		try {
			ResourceSetImpl resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(TorrentsPackage.eNS_URI, TorrentsPackage.eINSTANCE);

			resource = resourceSet.createResource(torrentURI);
			torrents = TorrentsFactory.eINSTANCE.createTorrents();
			resource.getContents().add(torrents);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up torrents.
	 */
	private void setUpTorrents() {
		List<ExtTorrent> allTorrents = new ArrayList<ExtTorrent>(torrents.getTorrentMap().values());
		allTorrents.addAll(torrents.getFinishedTorrentsMap().values());

		for (ExtTorrent extTorrent : allTorrents) {
			if (extTorrent.isEnabled()) {
				extTorrent.setStatus(EViewsMessages.READY);
			} else {
				extTorrent.setStatus(EViewsMessages.STOPPED);
			}
			extTorrent.setLock(new ReentrantReadWriteLock(true));
			extTorrent.getAdditionalInfo().setLock(new ReentrantReadWriteLock(true));

			ConvertUtils.INSTANCE.setModelBitfield(extTorrent);

			for (DataFile dataFile : extTorrent.getInfo().getFiles()) {
				dataFile.setLock(new ReentrantReadWriteLock(true));
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISplashUser#endWithProgress(eu.kalafatic .gemini.core.dialogs.GeminiSplashHandler)
	 */
	@Override
	public void endWithProgress(final GeminiSplashHandler splashHandler) {
		try {
			splashHandler.startSubTask("Saving Torrents Configuration ", GSHf.VISIBLE);
			Thread.sleep(200);
			splashHandler.setText("Saving Torrents Model ... ");
			ModelUtils.doSave(torrents, null);

			GSHf.FLAG &= ~GSHf.VISIBLE;
			Thread.sleep(200);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		try {
			if (torrents != null) {
				ModelUtils.doSave(torrents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents.
	 * @return the torrents
	 */
	public Torrents getTorrents() {
		return torrents;
	}
}
