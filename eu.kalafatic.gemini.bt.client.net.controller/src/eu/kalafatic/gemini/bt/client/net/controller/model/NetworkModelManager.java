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
package eu.kalafatic.gemini.bt.client.net.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.ConnectionAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmsAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.interfaces.IClientHandler;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.main.NetworkManager;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.SpeedScheduler;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.SwarmsScheduler;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler.GSHf;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.interfaces.ISplashUser;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EHandler;
import eu.kalafatic.gemini.core.schedulers.CPUScheduler;
import eu.kalafatic.gemini.core.threads.DeadlocksManagerThread;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class NetworkModelManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetworkModelManager implements ISplashUser {

	/** The network file. */
	private File networkFile;

	/** The network uri. */
	private URI networkURI;

	/** The client network. */
	private ClientNetwork clientNetwork;

	/** The torrents. */
	private Torrents torrents;

	/** The resource. */
	private Resource resource;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The model name. */
	private final String MODEL_NAME = "Model.clientNetwork";

	/** The instance. */
	private volatile static NetworkModelManager INSTANCE = null;

	/**
	 * Instantiates a new network model manager.
	 */
	public NetworkModelManager() {
		initNetworkModel();
	}

	/**
	 * Gets the single instance of NetworkModelManager.
	 * @return single instance of NetworkModelManager
	 */
	public static NetworkModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (NetworkModelManager.class) {
				INSTANCE = new NetworkModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the network model.
	 */
	private void initNetworkModel() {
		try {
			getTorrents();
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			networkFile = new File(models.concat(File.separator).concat(MODEL_NAME));
			networkURI = URI.createURI("file:/" + networkFile.getAbsolutePath());

			if (networkFile.exists()) {
				openModel();
			} else {
				createClientNetworkModel();
			}
			// because transient
			setUpSwarms();
			setUpCounters();
			setUpAdapters();
			initAppData();

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the app data.
	 */
	private void initAppData() {
		Assert.isNotNull(clientNetwork);

		AppData.getInstance().getSplashUsersUsers().add(this);
		AppData.getInstance().getSharedModels().put(MODEL_NAME, clientNetwork);
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 * @throws Exception the exception
	 */
	private void openModel() throws Exception {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(networkURI);

		resource = resourceSet.getResource(networkURI, true);
		resource.load(ModelUtils.SAVE_OPTIONS);
		clientNetwork = (ClientNetwork) resource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the client network model.
	 */
	private void createClientNetworkModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();

			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(ClientNetworkPackage.eNS_URI, ClientNetworkPackage.eINSTANCE);
			resource = resourceSet.createResource(networkURI);
			clientNetwork = ClientNetworkFactory.eINSTANCE.createClientNetwork();
			resource.getContents().add(clientNetwork);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up piece buffer.
	 * @param swarmSession the new up piece buffer
	 */
	private void setUpPieceBuffer(SwarmSession swarmSession) {
		WriteBuffer writeBuffer = ClientNetworkFactory.eINSTANCE.createWriteBuffer();

		int discBuffer = PREFERENCES.getInt(ECorePreferences.DISC_BUFFER.getName(), (Integer) ECorePreferences.DISC_BUFFER.getDef());

		writeBuffer.setSize(discBuffer * 1024 * 1024); // mb
		writeBuffer.setOffset(0);
		swarmSession.setPieceBuffer(writeBuffer);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up adapters.
	 */
	private void setUpAdapters() {
		Semaphor semaphor = ClientNetworkFactory.eINSTANCE.createSemaphor();
		clientNetwork.setSemaphor(semaphor);
		semaphor.eAdapters().add(SwarmsAdapter.getInstance());
		semaphor.eAdapters().add(ConnectionAdapter.getInstance());
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up counters.
	 */
	private void setUpCounters() {
		clientNetwork.getActiveSwarms().clear();

		Collection<ExtTorrent> values = getTorrents().getTorrentMap().values();
		for (ExtTorrent extTorrent : values) {
			// TODO - setCompleted ?
			int completedPieces = 0, length = extTorrent.getModelBitfield().length;
			for (int i = 0; i < length; i++) {
				completedPieces += extTorrent.getModelBitfield()[i] ? 1 : 0;
			}
			extTorrent.getAdditionalInfo().setCompletedPieces(completedPieces);
			int percent = (int) ((completedPieces > 0) ? (completedPieces / (length / 100f)) : 0);
			extTorrent.getAdditionalInfo().setCompleted(percent > 100 ? 100 : percent);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the swarm session.
	 * @param extTorrent the ext torrent
	 * @return the swarm session
	 */
	public SwarmSession getSwarmSession(ExtTorrent extTorrent) {
		SwarmSession swarmSession;
		final String key = extTorrent.getName();

		if (clientNetwork == null) {
			initNetworkModel();
		}

		EMap<String, Session> swarmMap = clientNetwork.getSwarmMap();

		if (swarmMap.containsKey(key)) {
			swarmSession = (SwarmSession) swarmMap.get(key);
		} else {
			// called by viewers on refresh (DND..)
			swarmSession = createSwarmSession(extTorrent);
		}
		// called by viewers on refresh (DND..)
		setUpSwarm(extTorrent, swarmSession);

		return swarmSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the upload swarm session.
	 * @param extTorrent the ext torrent
	 * @return the upload swarm session
	 */
	public SwarmSession getUploadSwarmSession(ExtTorrent extTorrent) {
		SwarmSession swarmSession;
		EMap<String, Session> swarmMap = clientNetwork.getSwarmMap();

		if (swarmMap.containsKey(extTorrent.getName())) {
			swarmSession = (SwarmSession) swarmMap.get(extTorrent.getName());
		} else {
			swarmSession = createSwarmSession(extTorrent);
		}
		if (!swarmSession.isSheduled()) {
			swarmSession.setSheduled(true);
		}
		return swarmSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the swarm session.
	 * @param extTorrent the ext torrent
	 * @return the swarm session
	 */
	private SwarmSession createSwarmSession(ExtTorrent extTorrent) {
		SwarmSession swarmSession;
		lock.lock();
		try {
			swarmSession = ClientNetworkFactoryImpl.eINSTANCE.createSwarmSession();

			swarmSession.setAnnounce(extTorrent.getName());
			swarmSession.setState(EViewsMessages.READY);

			setUpSwarm(extTorrent, swarmSession);
		} finally {
			lock.unlock();
		}
		return swarmSession;
	}

	// ---------------------------------------------------------------

	// called on application start
	/**
	 * Sets the up swarms.
	 */
	private void setUpSwarms() {
		Collection<Session> values = clientNetwork.getSwarmMap().values();

		for (Session session : values) {
			try {
				SwarmSession swarmSession = (SwarmSession) session;
				swarmSession.getUploads().clear();
				swarmSession.setRating(0);

				ExtTorrent extTorrent = getDwnldUpldExtTorrent(swarmSession.getAnnounce());

				if (extTorrent != null) {
					setUpSwarm(extTorrent, swarmSession);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	// lazy calls
	/**
	 * Sets the up swarm.
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 */
	private void setUpSwarm(ExtTorrent extTorrent, SwarmSession swarmSession) {
		swarmSession.setTorrent(extTorrent);

		if (swarmSession.getSpeedContainer() == null) {
			SpeedContainer speedContainer = ClientNetworkFactoryImpl.eINSTANCE.createSpeedContainer();
			speedContainer.setStartTime(System.currentTimeMillis());
			swarmSession.setSpeedContainer(speedContainer);
		}
		if (!SwarmThreadsManager.getInstance().getPooledThreadsMap().containsKey(extTorrent.getName())) {
			createPool(swarmSession);
		}
		if (swarmSession.getPieceBuffer() == null) {
			setUpPieceBuffer(swarmSession);
			swarmSession.getPieceBuffer().setLock(new ReentrantReadWriteLock(true));
		}
		if (swarmSession.getClients() == null) {
			swarmSession.setClients(new HashSet<String>());
		}

		if (extTorrent.isFinished()) {
			swarmSession.setUploadOnly(true);
		} else {
			SwarmThreadsManager.getInstance().loadSwarm(extTorrent, swarmSession);
		}
		clientNetwork.getSwarmMap().put(extTorrent.getName(), swarmSession);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the pool.
	 * @param swarmSession the swarm session
	 * @return the pooled threads
	 */
	public PooledThreads createPool(SwarmSession swarmSession) {
		PooledThreads pool = new PooledThreads(swarmSession);
		SwarmThreadsManager.getInstance().getPooledThreadsMap().put(swarmSession.getAnnounce(), pool);

		swarmSession.setTrackersManager(new TrackersManager(swarmSession, pool));
		return pool;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the client network.
	 * @return the client network
	 */
	public ClientNetwork getClientNetwork() {
		return clientNetwork;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the torrents.
	 * @return the torrents
	 */
	public Torrents getTorrents() {
		if (torrents == null) {
			// call client model manager to init model
			torrents = (Torrents) CommandFactory.INSTANCE.executeCommand(EHandler.INIT_TORRENTS_MODEL.ID);
		}
		return torrents;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the dwnld upld ext torrent.
	 * @param key the key
	 * @return the dwnld upld ext torrent
	 */
	// lazy starts
	public ExtTorrent getDwnldUpldExtTorrent(String key) {
		lock.lock();
		try {
			ExtTorrent extTorrent = torrents.getTorrentMap().get(key);
			if (extTorrent == null) {
				extTorrent = torrents.getFinishedTorrentsMap().get(key);
			}
			if (extTorrent == null) {
				torrents.getTorrentMap().removeKey(key);
				torrents.getFinishedTorrentsMap().removeKey(key);
			} else {
				checkExtTorrent(extTorrent);
			}
			return extTorrent;
		} finally {
			lock.unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check ext torrent.
	 * @param extTorrent the ext torrent
	 */
	public void checkExtTorrent(ExtTorrent extTorrent) {
		Assert.isNotNull(extTorrent);

		if (extTorrent.getLock() == null) {
			extTorrent.setLock(new ReentrantReadWriteLock(true));
		}
		if (extTorrent.getAdditionalInfo().getLock() == null) {
			extTorrent.getAdditionalInfo().setLock(new ReentrantReadWriteLock(true));
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISplashUser#endWithProgress(eu.kalafatic .gemini.core.dialogs.GeminiSplashHandler)
	 */
	@Override
	public synchronized void endWithProgress(final GeminiSplashHandler splashHandler) {
		try {
			splashHandler.startSubTask("Saving Network Configuration ", GSHf.VISIBLE);
			Thread.sleep(200);
			splashHandler.setText("Stopping Schedulers ... ");
			Thread.sleep(200);

			if (NetworkModelManager.getInstance().getClientNetwork().isSheduled()) {
				SwarmsScheduler.INSTANCE.stop();
				SpeedScheduler.INSTANCE.stop();
				DeadlocksManagerThread.INSTANCE.stop();
				CPUScheduler.INSTANCE.stop();
			}
			Collection<ExtTorrent> values = torrents.getTorrentMap().values();
			List<ExtTorrent> synchronizedList = Collections.synchronizedList(new ArrayList<ExtTorrent>(values));
			synchronizedList.addAll(torrents.getFinishedTorrentsMap().values());

			splashHandler.setText("Stopping Swarms ... ");
			Thread.sleep(200);
			shutDownSwarms(synchronizedList, splashHandler);

			splashHandler.setText("Saving Torrents Model ...");
			Thread.sleep(200);

			ModelUtils.doSave(torrents, null);

			splashHandler.setText("Saving Network Model ...");
			Thread.sleep(200);
			ModelUtils.doSave(clientNetwork, null);

			GSHf.FLAG &= ~GSHf.VISIBLE;
			Thread.sleep(200);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Shut down swarms.
	 * @param selected the selected
	 * @param splashHandler the splash handler
	 */
	private void shutDownSwarms(List<ExtTorrent> selected, GeminiSplashHandler splashHandler) {
		try {
			shutDownAllExecutors(selected, splashHandler);
			awaitTermination(selected, splashHandler);
		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Shut down all executors.
	 * @param selected the selected
	 * @param splashHandler the splash handler
	 * @throws InterruptedException the interrupted exception
	 */
	private void shutDownAllExecutors(List<ExtTorrent> selected, GeminiSplashHandler splashHandler) throws InterruptedException {

		EList<String> activeSwarms = clientNetwork.getActiveSwarms();

		for (ExtTorrent extTorrent : selected) {

			String key = extTorrent.getName();

			PooledThreads pooledThreads = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(key);

			// swarm not created yet
			if (pooledThreads != null) {
				splashHandler.startSubTask("Shutdown Torrent: " + key, GSHf.NONE);

				pooledThreads.getDownloadExecutor().purge();
				pooledThreads.getUploadExecutor().purge();

				List<Runnable> downloads = pooledThreads.getDownloadExecutor().shutdownNow();
				for (Runnable r : downloads) {
					try {
						ClientSession session = ((IClientHandler) r).getSession();
						session.getChannel().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				List<Runnable> uploads = pooledThreads.getUploadExecutor().shutdownNow();
				for (Runnable r : uploads) {
					try {
						ClientSession session = ((IClientHandler) r).getSession();
						session.getChannel().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				Thread.sleep(200);
			}
			if (activeSwarms.contains(key)) {
				activeSwarms.remove(key);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Await termination.
	 * @param selected the selected
	 * @param splashHandler the splash handler
	 * @throws InterruptedException the interrupted exception
	 */
	private void awaitTermination(List<ExtTorrent> selected, GeminiSplashHandler splashHandler) throws InterruptedException {
		for (ExtTorrent extTorrent : selected) {

			splashHandler.setText("Wait for termination .. ");
			Thread.sleep(200);

			PooledThreads pooledThreads = SwarmThreadsManager.getInstance().getPooledThreadsMap().get(extTorrent.getName());

			// swarm not created yet
			if (pooledThreads != null) {
				splashHandler.setText("Stopping download executor");
				awaitTermination(pooledThreads.getDownloadExecutor());

				splashHandler.setText("Stopping upload executor");
				awaitTermination(pooledThreads.getUploadExecutor());
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Await termination.
	 * @param executor the executor
	 */
	private/* synchronized */void awaitTermination(ThreadPoolExecutor executor) {
		try {
			executor.awaitTermination(500, TimeUnit.MILLISECONDS);

			if (!executor.isTerminated()) {
				Iterator<Runnable> iterator = executor.getQueue().iterator();
				while (iterator.hasNext()) {
					Thread runnable = (Thread) iterator.next();
					runnable.interrupt();
				}
			}
		} catch (Exception e) {
			executor = null;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the from active swarms.
	 * @param key the key
	 * @param rating the rating
	 */
	public void removeFromActiveSwarms(String key, int rating) {
		try {
			EMap<String, Session> swarmMap = clientNetwork.getSwarmMap();

			synchronized (swarmMap) {
				if (swarmMap.containsKey(key)) {

					Session swarmSession = swarmMap.get(key);

					swarmSession.setState(EViewsMessages.READY);
					swarmSession.setRating(swarmSession.getRating() + rating);

					EList<String> activeSwarms = clientNetwork.getActiveSwarms();
					synchronized (activeSwarms) {
						if (activeSwarms.contains(key)) {
							activeSwarms.remove(key);
						}
					}

					NetworkManager.getInstance().setViewMessage(getDwnldUpldExtTorrent(key), EViewsMessages.READY);
				}
			}
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
			if (clientNetwork != null) {
				ModelUtils.doSave(clientNetwork);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
