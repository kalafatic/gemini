/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Swarm Session</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getDwnldStrategy <em>Dwnld Strategy</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#isCreatedOnDisc <em>Created On Disc</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#isUploadOnly <em>Upload Only</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getTrackers <em>Trackers</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getDownloads <em>Downloads</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getUploads <em>Uploads</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getProcessedPieces <em>Processed Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getUploadedPieces <em>Uploaded Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getPieceBuffer <em>Piece Buffer</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getSpeedContainer <em>Speed Container</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#isObtainingClients <em>Obtaining Clients</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getTrackersManager <em>Trackers Manager</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getSearchersManager <em>Searchers Manager</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl#getTorrent <em>Torrent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwarmSessionImpl extends SessionImpl implements SwarmSession {
	/**
	 * The default value of the '{@link #getDwnldStrategy() <em>Dwnld Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final int DWNLD_STRATEGY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDwnldStrategy() <em>Dwnld Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldStrategy()
	 * @generated
	 * @ordered
	 */
	protected int dwnldStrategy = DWNLD_STRATEGY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final int ALGORITHM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected int algorithm = ALGORITHM_EDEFAULT;

	/**
	 * The default value of the '{@link #isCreatedOnDisc() <em>Created On Disc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCreatedOnDisc()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CREATED_ON_DISC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCreatedOnDisc() <em>Created On Disc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCreatedOnDisc()
	 * @generated
	 * @ordered
	 */
	protected boolean createdOnDisc = CREATED_ON_DISC_EDEFAULT;

	/**
	 * The default value of the '{@link #isUploadOnly() <em>Upload Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUploadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPLOAD_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUploadOnly() <em>Upload Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUploadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean uploadOnly = UPLOAD_ONLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTrackers() <em>Trackers</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackers()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Session> trackers;

	/**
	 * The cached value of the '{@link #getDownloads() <em>Downloads</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDownloads()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ClientSession> downloads;

	/**
	 * The cached value of the '{@link #getUploads() <em>Uploads</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUploads()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ClientSession> uploads;

	/**
	 * The cached value of the '{@link #getProcessedPieces() <em>Processed Pieces</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProcessedPieces()
	 * @generated
	 * @ordered
	 */
	protected EMap<Integer, IOPiece> processedPieces;

	/**
	 * The cached value of the '{@link #getUploadedPieces() <em>Uploaded Pieces</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getUploadedPieces()
	 * @generated
	 * @ordered
	 */
	protected EList<IOPiece> uploadedPieces;

	/**
	 * The cached value of the '{@link #getPieceBuffer() <em>Piece Buffer</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPieceBuffer()
	 * @generated
	 * @ordered
	 */
	protected WriteBuffer pieceBuffer;

	/**
	 * The cached value of the '{@link #getSpeedContainer() <em>Speed Container</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getSpeedContainer()
	 * @generated
	 * @ordered
	 */
	protected SpeedContainer speedContainer;

	/**
	 * The default value of the '{@link #isObtainingClients() <em>Obtaining Clients</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isObtainingClients()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OBTAINING_CLIENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isObtainingClients() <em>Obtaining Clients</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isObtainingClients()
	 * @generated
	 * @ordered
	 */
	protected boolean obtainingClients = OBTAINING_CLIENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrackersManager() <em>Trackers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackersManager()
	 * @generated
	 * @ordered
	 */
	protected static final Object TRACKERS_MANAGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTrackersManager() <em>Trackers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackersManager()
	 * @generated
	 * @ordered
	 */
	protected Object trackersManager = TRACKERS_MANAGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSearchersManager() <em>Searchers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSearchersManager()
	 * @generated
	 * @ordered
	 */
	protected static final Object SEARCHERS_MANAGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSearchersManager() <em>Searchers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSearchersManager()
	 * @generated
	 * @ordered
	 */
	protected Object searchersManager = SEARCHERS_MANAGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTorrent() <em>Torrent</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTorrent()
	 * @generated
	 * @ordered
	 */
	protected static final Object TORRENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTorrent() <em>Torrent</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTorrent()
	 * @generated
	 * @ordered
	 */
	protected Object torrent = TORRENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SwarmSessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SWARM_SESSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getDwnldStrategy() {
		return dwnldStrategy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDwnldStrategy(int newDwnldStrategy) {
		int oldDwnldStrategy = dwnldStrategy;
		dwnldStrategy = newDwnldStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY, oldDwnldStrategy, dwnldStrategy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlgorithm(int newAlgorithm) {
		int oldAlgorithm = algorithm;
		algorithm = newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__ALGORITHM, oldAlgorithm, algorithm));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCreatedOnDisc() {
		return createdOnDisc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreatedOnDisc(boolean newCreatedOnDisc) {
		boolean oldCreatedOnDisc = createdOnDisc;
		createdOnDisc = newCreatedOnDisc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC, oldCreatedOnDisc, createdOnDisc));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUploadOnly() {
		return uploadOnly;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUploadOnly(boolean newUploadOnly) {
		boolean oldUploadOnly = uploadOnly;
		uploadOnly = newUploadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY, oldUploadOnly, uploadOnly));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Session> getTrackers() {
		if (trackers == null) {
			trackers = new EcoreEMap<String,Session>(ClientNetworkPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_SESSION__TRACKERS);
		}
		return trackers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ClientSession> getDownloads() {
		if (downloads == null) {
			downloads = new EcoreEMap<String,ClientSession>(ClientNetworkPackage.Literals.STRING_TO_CLIENT_SESSION_MAP_ENTRY, StringToClientSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_SESSION__DOWNLOADS);
		}
		return downloads;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ClientSession> getUploads() {
		if (uploads == null) {
			uploads = new EcoreEMap<String,ClientSession>(ClientNetworkPackage.Literals.STRING_TO_CLIENT_SESSION_MAP_ENTRY, StringToClientSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_SESSION__UPLOADS);
		}
		return uploads;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Integer, IOPiece> getProcessedPieces() {
		if (processedPieces == null) {
			processedPieces = new EcoreEMap<Integer,IOPiece>(ClientNetworkPackage.Literals.INT_TO_PIECE_ENTRY, IntToPieceEntryImpl.class, this, ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES);
		}
		return processedPieces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IOPiece> getUploadedPieces() {
		if (uploadedPieces == null) {
			uploadedPieces = new EObjectContainmentEList<IOPiece>(IOPiece.class, this, ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES);
		}
		return uploadedPieces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WriteBuffer getPieceBuffer() {
		return pieceBuffer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPieceBuffer(WriteBuffer newPieceBuffer, NotificationChain msgs) {
		WriteBuffer oldPieceBuffer = pieceBuffer;
		pieceBuffer = newPieceBuffer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER, oldPieceBuffer, newPieceBuffer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPieceBuffer(WriteBuffer newPieceBuffer) {
		if (newPieceBuffer != pieceBuffer) {
			NotificationChain msgs = null;
			if (pieceBuffer != null)
				msgs = ((InternalEObject)pieceBuffer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER, null, msgs);
			if (newPieceBuffer != null)
				msgs = ((InternalEObject)newPieceBuffer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER, null, msgs);
			msgs = basicSetPieceBuffer(newPieceBuffer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER, newPieceBuffer, newPieceBuffer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedContainer getSpeedContainer() {
		return speedContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpeedContainer(SpeedContainer newSpeedContainer, NotificationChain msgs) {
		SpeedContainer oldSpeedContainer = speedContainer;
		speedContainer = newSpeedContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER, oldSpeedContainer, newSpeedContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeedContainer(SpeedContainer newSpeedContainer) {
		if (newSpeedContainer != speedContainer) {
			NotificationChain msgs = null;
			if (speedContainer != null)
				msgs = ((InternalEObject)speedContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER, null, msgs);
			if (newSpeedContainer != null)
				msgs = ((InternalEObject)newSpeedContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER, null, msgs);
			msgs = basicSetSpeedContainer(newSpeedContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER, newSpeedContainer, newSpeedContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isObtainingClients() {
		return obtainingClients;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setObtainingClients(boolean newObtainingClients) {
		boolean oldObtainingClients = obtainingClients;
		obtainingClients = newObtainingClients;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__OBTAINING_CLIENTS, oldObtainingClients, obtainingClients));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTrackersManager() {
		return trackersManager;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrackersManager(Object newTrackersManager) {
		Object oldTrackersManager = trackersManager;
		trackersManager = newTrackersManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__TRACKERS_MANAGER, oldTrackersManager, trackersManager));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object getSearchersManager() {
		return searchersManager;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSearchersManager(Object newSearchersManager) {
		Object oldSearchersManager = searchersManager;
		searchersManager = newSearchersManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__SEARCHERS_MANAGER, oldSearchersManager, searchersManager));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object getTorrent() {
		return torrent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTorrent(Object newTorrent) {
		Object oldTorrent = torrent;
		torrent = newTorrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_SESSION__TORRENT, oldTorrent, torrent));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS:
				return ((InternalEList<?>)getTrackers()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS:
				return ((InternalEList<?>)getDownloads()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_SESSION__UPLOADS:
				return ((InternalEList<?>)getUploads()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES:
				return ((InternalEList<?>)getProcessedPieces()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES:
				return ((InternalEList<?>)getUploadedPieces()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER:
				return basicSetPieceBuffer(null, msgs);
			case ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER:
				return basicSetSpeedContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY:
				return getDwnldStrategy();
			case ClientNetworkPackage.SWARM_SESSION__ALGORITHM:
				return getAlgorithm();
			case ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC:
				return isCreatedOnDisc();
			case ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY:
				return isUploadOnly();
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS:
				if (coreType) return getTrackers();
				else return getTrackers().map();
			case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS:
				if (coreType) return getDownloads();
				else return getDownloads().map();
			case ClientNetworkPackage.SWARM_SESSION__UPLOADS:
				if (coreType) return getUploads();
				else return getUploads().map();
			case ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES:
				if (coreType) return getProcessedPieces();
				else return getProcessedPieces().map();
			case ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES:
				return getUploadedPieces();
			case ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER:
				return getPieceBuffer();
			case ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER:
				return getSpeedContainer();
			case ClientNetworkPackage.SWARM_SESSION__OBTAINING_CLIENTS:
				return isObtainingClients();
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS_MANAGER:
				return getTrackersManager();
			case ClientNetworkPackage.SWARM_SESSION__SEARCHERS_MANAGER:
				return getSearchersManager();
			case ClientNetworkPackage.SWARM_SESSION__TORRENT:
				return getTorrent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY:
				setDwnldStrategy((Integer)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__ALGORITHM:
				setAlgorithm((Integer)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC:
				setCreatedOnDisc((Boolean)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY:
				setUploadOnly((Boolean)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS:
				((EStructuralFeature.Setting)getTrackers()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS:
				((EStructuralFeature.Setting)getDownloads()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOADS:
				((EStructuralFeature.Setting)getUploads()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES:
				((EStructuralFeature.Setting)getProcessedPieces()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES:
				getUploadedPieces().clear();
				getUploadedPieces().addAll((Collection<? extends IOPiece>)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER:
				setPieceBuffer((WriteBuffer)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER:
				setSpeedContainer((SpeedContainer)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__OBTAINING_CLIENTS:
				setObtainingClients((Boolean)newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS_MANAGER:
				setTrackersManager(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__SEARCHERS_MANAGER:
				setSearchersManager(newValue);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TORRENT:
				setTorrent(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY:
				setDwnldStrategy(DWNLD_STRATEGY_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC:
				setCreatedOnDisc(CREATED_ON_DISC_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY:
				setUploadOnly(UPLOAD_ONLY_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS:
				getTrackers().clear();
				return;
			case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS:
				getDownloads().clear();
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOADS:
				getUploads().clear();
				return;
			case ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES:
				getProcessedPieces().clear();
				return;
			case ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES:
				getUploadedPieces().clear();
				return;
			case ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER:
				setPieceBuffer((WriteBuffer)null);
				return;
			case ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER:
				setSpeedContainer((SpeedContainer)null);
				return;
			case ClientNetworkPackage.SWARM_SESSION__OBTAINING_CLIENTS:
				setObtainingClients(OBTAINING_CLIENTS_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS_MANAGER:
				setTrackersManager(TRACKERS_MANAGER_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__SEARCHERS_MANAGER:
				setSearchersManager(SEARCHERS_MANAGER_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_SESSION__TORRENT:
				setTorrent(TORRENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY:
				return dwnldStrategy != DWNLD_STRATEGY_EDEFAULT;
			case ClientNetworkPackage.SWARM_SESSION__ALGORITHM:
				return algorithm != ALGORITHM_EDEFAULT;
			case ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC:
				return createdOnDisc != CREATED_ON_DISC_EDEFAULT;
			case ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY:
				return uploadOnly != UPLOAD_ONLY_EDEFAULT;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS:
				return trackers != null && !trackers.isEmpty();
			case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS:
				return downloads != null && !downloads.isEmpty();
			case ClientNetworkPackage.SWARM_SESSION__UPLOADS:
				return uploads != null && !uploads.isEmpty();
			case ClientNetworkPackage.SWARM_SESSION__PROCESSED_PIECES:
				return processedPieces != null && !processedPieces.isEmpty();
			case ClientNetworkPackage.SWARM_SESSION__UPLOADED_PIECES:
				return uploadedPieces != null && !uploadedPieces.isEmpty();
			case ClientNetworkPackage.SWARM_SESSION__PIECE_BUFFER:
				return pieceBuffer != null;
			case ClientNetworkPackage.SWARM_SESSION__SPEED_CONTAINER:
				return speedContainer != null;
			case ClientNetworkPackage.SWARM_SESSION__OBTAINING_CLIENTS:
				return obtainingClients != OBTAINING_CLIENTS_EDEFAULT;
			case ClientNetworkPackage.SWARM_SESSION__TRACKERS_MANAGER:
				return TRACKERS_MANAGER_EDEFAULT == null ? trackersManager != null : !TRACKERS_MANAGER_EDEFAULT.equals(trackersManager);
			case ClientNetworkPackage.SWARM_SESSION__SEARCHERS_MANAGER:
				return SEARCHERS_MANAGER_EDEFAULT == null ? searchersManager != null : !SEARCHERS_MANAGER_EDEFAULT.equals(searchersManager);
			case ClientNetworkPackage.SWARM_SESSION__TORRENT:
				return TORRENT_EDEFAULT == null ? torrent != null : !TORRENT_EDEFAULT.equals(torrent);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == SwarmInfo.class) {
			switch (derivedFeatureID) {
				case ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY: return ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY;
				case ClientNetworkPackage.SWARM_SESSION__ALGORITHM: return ClientNetworkPackage.SWARM_INFO__ALGORITHM;
				case ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC: return ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC;
				case ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY: return ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY;
				default: return -1;
			}
		}
		if (baseClass == SwarmContent.class) {
			switch (derivedFeatureID) {
				case ClientNetworkPackage.SWARM_SESSION__TRACKERS: return ClientNetworkPackage.SWARM_CONTENT__TRACKERS;
				case ClientNetworkPackage.SWARM_SESSION__DOWNLOADS: return ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS;
				case ClientNetworkPackage.SWARM_SESSION__UPLOADS: return ClientNetworkPackage.SWARM_CONTENT__UPLOADS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == SwarmInfo.class) {
			switch (baseFeatureID) {
				case ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY: return ClientNetworkPackage.SWARM_SESSION__DWNLD_STRATEGY;
				case ClientNetworkPackage.SWARM_INFO__ALGORITHM: return ClientNetworkPackage.SWARM_SESSION__ALGORITHM;
				case ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC: return ClientNetworkPackage.SWARM_SESSION__CREATED_ON_DISC;
				case ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY: return ClientNetworkPackage.SWARM_SESSION__UPLOAD_ONLY;
				default: return -1;
			}
		}
		if (baseClass == SwarmContent.class) {
			switch (baseFeatureID) {
				case ClientNetworkPackage.SWARM_CONTENT__TRACKERS: return ClientNetworkPackage.SWARM_SESSION__TRACKERS;
				case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS: return ClientNetworkPackage.SWARM_SESSION__DOWNLOADS;
				case ClientNetworkPackage.SWARM_CONTENT__UPLOADS: return ClientNetworkPackage.SWARM_SESSION__UPLOADS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dwnldStrategy: ");
		result.append(dwnldStrategy);
		result.append(", algorithm: ");
		result.append(algorithm);
		result.append(", createdOnDisc: ");
		result.append(createdOnDisc);
		result.append(", uploadOnly: ");
		result.append(uploadOnly);
		result.append(", obtainingClients: ");
		result.append(obtainingClients);
		result.append(", trackersManager: ");
		result.append(trackersManager);
		result.append(", searchersManager: ");
		result.append(searchersManager);
		result.append(", torrent: ");
		result.append(torrent);
		result.append(')');
		return result.toString();
	}

} // SwarmSessionImpl
