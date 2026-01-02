/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory
 * @model kind="package"
 * @generated
 */
public interface ClientNetworkPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "clientNetwork";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///clientNetwork.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "clientNetwork";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	ClientNetworkPackage eINSTANCE = eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl <em>Behaviour</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getBehaviour()
	 * @generated
	 */
	int BEHAVIOUR = 18;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__ALGORITHM = 0;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__STRATEGY = 1;

	/**
	 * The number of structural features of the '<em>Behaviour</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl <em>Client Network</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientNetwork()
	 * @generated
	 */
	int CLIENT_NETWORK = 0;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__ALGORITHM = BEHAVIOUR__ALGORITHM;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__STRATEGY = BEHAVIOUR__STRATEGY;

	/**
	 * The feature id for the '<em><b>Swarm Map</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__SWARM_MAP = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Semaphor</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__SEMAPHOR = BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Active Swarms</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__ACTIVE_SWARMS = BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Force Swarms Keys</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__FORCE_SWARMS_KEYS = BEHAVIOUR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK__SHEDULED = BEHAVIOUR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Client Network</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_NETWORK_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl <em>Semaphor</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSemaphor()
	 * @generated
	 */
	int SEMAPHOR = 1;

	/**
	 * The feature id for the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHOR__CONNECTED = 0;

	/**
	 * The feature id for the '<em><b>Opened</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHOR__OPENED = 1;

	/**
	 * The number of structural features of the '<em>Semaphor</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl <em>Session</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSession()
	 * @generated
	 */
	int SESSION = 3;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__ANNOUNCE = 0;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__CHANNEL = 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__STATE = 2;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__SHEDULED = 3;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__LAST_ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__RATING = 5;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__TIMEOUT = 6;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DURATION = 7;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__CLIENTS = 8;

	/**
	 * The number of structural features of the '<em>Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl <em>Swarm Session</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmSession()
	 * @generated
	 */
	int SWARM_SESSION = 2;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__ANNOUNCE = SESSION__ANNOUNCE;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__CHANNEL = SESSION__CHANNEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__STATE = SESSION__STATE;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__SHEDULED = SESSION__SHEDULED;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__LAST_ACTIVITY = SESSION__LAST_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__RATING = SESSION__RATING;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__TIMEOUT = SESSION__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__DURATION = SESSION__DURATION;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__CLIENTS = SESSION__CLIENTS;

	/**
	 * The feature id for the '<em><b>Dwnld Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__DWNLD_STRATEGY = SESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__ALGORITHM = SESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Created On Disc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__CREATED_ON_DISC = SESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Upload Only</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__UPLOAD_ONLY = SESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Trackers</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__TRACKERS = SESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Downloads</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__DOWNLOADS = SESSION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Uploads</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__UPLOADS = SESSION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Processed Pieces</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__PROCESSED_PIECES = SESSION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Uploaded Pieces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__UPLOADED_PIECES = SESSION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Piece Buffer</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__PIECE_BUFFER = SESSION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__SPEED_CONTAINER = SESSION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Obtaining Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__OBTAINING_CLIENTS = SESSION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Trackers Manager</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__TRACKERS_MANAGER = SESSION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Searchers Manager</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__SEARCHERS_MANAGER = SESSION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Torrent</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION__TORRENT = SESSION_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Swarm Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_SESSION_FEATURE_COUNT = SESSION_FEATURE_COUNT + 15;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl <em>Swarm Info</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmInfo()
	 * @generated
	 */
	int SWARM_INFO = 4;

	/**
	 * The feature id for the '<em><b>Dwnld Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_INFO__DWNLD_STRATEGY = 0;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_INFO__ALGORITHM = 1;

	/**
	 * The feature id for the '<em><b>Created On Disc</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_INFO__CREATED_ON_DISC = 2;

	/**
	 * The feature id for the '<em><b>Upload Only</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_INFO__UPLOAD_ONLY = 3;

	/**
	 * The number of structural features of the '<em>Swarm Info</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_INFO_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl <em>Swarm Content</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmContent()
	 * @generated
	 */
	int SWARM_CONTENT = 5;

	/**
	 * The feature id for the '<em><b>Trackers</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_CONTENT__TRACKERS = 0;

	/**
	 * The feature id for the '<em><b>Downloads</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_CONTENT__DOWNLOADS = 1;

	/**
	 * The feature id for the '<em><b>Uploads</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_CONTENT__UPLOADS = 2;

	/**
	 * The number of structural features of the '<em>Swarm Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWARM_CONTENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl <em>Tracker Session</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getTrackerSession()
	 * @generated
	 */
	int TRACKER_SESSION = 6;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__ANNOUNCE = SESSION__ANNOUNCE;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__CHANNEL = SESSION__CHANNEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__STATE = SESSION__STATE;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__SHEDULED = SESSION__SHEDULED;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__LAST_ACTIVITY = SESSION__LAST_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__RATING = SESSION__RATING;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__TIMEOUT = SESSION__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__DURATION = SESSION__DURATION;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__CLIENTS = SESSION__CLIENTS;

	/**
	 * The feature id for the '<em><b>Response</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__RESPONSE = SESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Next Connection</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__NEXT_CONNECTION = SESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__EVENT = SESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Scraped</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION__SCRAPED = SESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Tracker Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_SESSION_FEATURE_COUNT = SESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl <em>Client Session</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientSession()
	 * @generated
	 */
	int CLIENT_SESSION = 9;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__ANNOUNCE = SESSION__ANNOUNCE;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__CHANNEL = SESSION__CHANNEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__STATE = SESSION__STATE;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__SHEDULED = SESSION__SHEDULED;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__LAST_ACTIVITY = SESSION__LAST_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__RATING = SESSION__RATING;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__TIMEOUT = SESSION__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__DURATION = SESSION__DURATION;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__CLIENTS = SESSION__CLIENTS;

	/**
	 * The feature id for the '<em><b>Client Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__CLIENT_NAME = SESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Choking</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__CHOKING = SESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Handshake OK</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__HANDSHAKE_OK = SESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Interested</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__INTERESTED = SESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__BITFIELD = SESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__SEED = SESSION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Have</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__HAVE = SESSION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Reserved</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__RESERVED = SESSION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Canceled Blocks</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__CANCELED_BLOCKS = SESSION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Received Keep Alive</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__RECEIVED_KEEP_ALIVE = SESSION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION__SPEED_CONTAINER = SESSION_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Client Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_SESSION_FEATURE_COUNT = SESSION_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.DwnldSessionImpl <em>Dwnld Session</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.DwnldSessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getDwnldSession()
	 * @generated
	 */
	int DWNLD_SESSION = 7;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__ANNOUNCE = CLIENT_SESSION__ANNOUNCE;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__CHANNEL = CLIENT_SESSION__CHANNEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__STATE = CLIENT_SESSION__STATE;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__SHEDULED = CLIENT_SESSION__SHEDULED;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__LAST_ACTIVITY = CLIENT_SESSION__LAST_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__RATING = CLIENT_SESSION__RATING;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__TIMEOUT = CLIENT_SESSION__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__DURATION = CLIENT_SESSION__DURATION;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__CLIENTS = CLIENT_SESSION__CLIENTS;

	/**
	 * The feature id for the '<em><b>Client Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__CLIENT_NAME = CLIENT_SESSION__CLIENT_NAME;

	/**
	 * The feature id for the '<em><b>Choking</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__CHOKING = CLIENT_SESSION__CHOKING;

	/**
	 * The feature id for the '<em><b>Handshake OK</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__HANDSHAKE_OK = CLIENT_SESSION__HANDSHAKE_OK;

	/**
	 * The feature id for the '<em><b>Interested</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__INTERESTED = CLIENT_SESSION__INTERESTED;

	/**
	 * The feature id for the '<em><b>Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__BITFIELD = CLIENT_SESSION__BITFIELD;

	/**
	 * The feature id for the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__SEED = CLIENT_SESSION__SEED;

	/**
	 * The feature id for the '<em><b>Have</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__HAVE = CLIENT_SESSION__HAVE;

	/**
	 * The feature id for the '<em><b>Reserved</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__RESERVED = CLIENT_SESSION__RESERVED;

	/**
	 * The feature id for the '<em><b>Canceled Blocks</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__CANCELED_BLOCKS = CLIENT_SESSION__CANCELED_BLOCKS;

	/**
	 * The feature id for the '<em><b>Received Keep Alive</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__RECEIVED_KEEP_ALIVE = CLIENT_SESSION__RECEIVED_KEEP_ALIVE;

	/**
	 * The feature id for the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION__SPEED_CONTAINER = CLIENT_SESSION__SPEED_CONTAINER;

	/**
	 * The number of structural features of the '<em>Dwnld Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DWNLD_SESSION_FEATURE_COUNT = CLIENT_SESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.UpldSessionImpl <em>Upld Session</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.UpldSessionImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getUpldSession()
	 * @generated
	 */
	int UPLD_SESSION = 8;

	/**
	 * The feature id for the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__ANNOUNCE = CLIENT_SESSION__ANNOUNCE;

	/**
	 * The feature id for the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__CHANNEL = CLIENT_SESSION__CHANNEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__STATE = CLIENT_SESSION__STATE;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__SHEDULED = CLIENT_SESSION__SHEDULED;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__LAST_ACTIVITY = CLIENT_SESSION__LAST_ACTIVITY;

	/**
	 * The feature id for the '<em><b>Rating</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__RATING = CLIENT_SESSION__RATING;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__TIMEOUT = CLIENT_SESSION__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__DURATION = CLIENT_SESSION__DURATION;

	/**
	 * The feature id for the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__CLIENTS = CLIENT_SESSION__CLIENTS;

	/**
	 * The feature id for the '<em><b>Client Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__CLIENT_NAME = CLIENT_SESSION__CLIENT_NAME;

	/**
	 * The feature id for the '<em><b>Choking</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__CHOKING = CLIENT_SESSION__CHOKING;

	/**
	 * The feature id for the '<em><b>Handshake OK</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__HANDSHAKE_OK = CLIENT_SESSION__HANDSHAKE_OK;

	/**
	 * The feature id for the '<em><b>Interested</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__INTERESTED = CLIENT_SESSION__INTERESTED;

	/**
	 * The feature id for the '<em><b>Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__BITFIELD = CLIENT_SESSION__BITFIELD;

	/**
	 * The feature id for the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__SEED = CLIENT_SESSION__SEED;

	/**
	 * The feature id for the '<em><b>Have</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__HAVE = CLIENT_SESSION__HAVE;

	/**
	 * The feature id for the '<em><b>Reserved</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__RESERVED = CLIENT_SESSION__RESERVED;

	/**
	 * The feature id for the '<em><b>Canceled Blocks</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__CANCELED_BLOCKS = CLIENT_SESSION__CANCELED_BLOCKS;

	/**
	 * The feature id for the '<em><b>Received Keep Alive</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__RECEIVED_KEEP_ALIVE = CLIENT_SESSION__RECEIVED_KEEP_ALIVE;

	/**
	 * The feature id for the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION__SPEED_CONTAINER = CLIENT_SESSION__SPEED_CONTAINER;

	/**
	 * The number of structural features of the '<em>Upld Session</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPLD_SESSION_FEATURE_COUNT = CLIENT_SESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl <em>Client Info</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientInfo()
	 * @generated
	 */
	int CLIENT_INFO = 10;

	/**
	 * The feature id for the '<em><b>Client Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__CLIENT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Choking</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__CHOKING = 1;

	/**
	 * The feature id for the '<em><b>Handshake OK</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__HANDSHAKE_OK = 2;

	/**
	 * The feature id for the '<em><b>Interested</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__INTERESTED = 3;

	/**
	 * The feature id for the '<em><b>Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__BITFIELD = 4;

	/**
	 * The feature id for the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__SEED = 5;

	/**
	 * The feature id for the '<em><b>Have</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO__HAVE = 6;

	/**
	 * The number of structural features of the '<em>Client Info</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_INFO_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl <em>Tracker Response</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getTrackerResponse()
	 * @generated
	 */
	int TRACKER_RESPONSE = 11;

	/**
	 * The feature id for the '<em><b>Failure Reason</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__FAILURE_REASON = 0;

	/**
	 * The feature id for the '<em><b>Interval</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__INTERVAL = 1;

	/**
	 * The feature id for the '<em><b>Min Interval</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__MIN_INTERVAL = 2;

	/**
	 * The feature id for the '<em><b>Tracker Id</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__TRACKER_ID = 3;

	/**
	 * The feature id for the '<em><b>Complete</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__COMPLETE = 4;

	/**
	 * The feature id for the '<em><b>Incomplete</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__INCOMPLETE = 5;

	/**
	 * The feature id for the '<em><b>Response Message</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE__RESPONSE_MESSAGE = 6;

	/**
	 * The number of structural features of the '<em>Tracker Response</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKER_RESPONSE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl <em>IO Piece</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getIOPiece()
	 * @generated
	 */
	int IO_PIECE = 12;

	/**
	 * The feature id for the '<em><b>Piece Index</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__PIECE_INDEX = 0;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__PAYLOAD = 1;

	/**
	 * The feature id for the '<em><b>Finished</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__FINISHED = 2;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__POSITION = 3;

	/**
	 * The feature id for the '<em><b>Blocks</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__BLOCKS = 4;

	/**
	 * The feature id for the '<em><b>Locked Blocks</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__LOCKED_BLOCKS = 5;

	/**
	 * The feature id for the '<em><b>Ext Torrent</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__EXT_TORRENT = 6;

	/**
	 * The feature id for the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__LAST_ACTIVITY = 7;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__LOCK = 8;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__COLOR = 9;

	/**
	 * The feature id for the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE__HASH = 10;

	/**
	 * The number of structural features of the '<em>IO Piece</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IO_PIECE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl <em>Write Buffer</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getWriteBuffer()
	 * @generated
	 */
	int WRITE_BUFFER = 13;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER__OFFSET = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER__SIZE = 1;

	/**
	 * The feature id for the '<em><b>Pieces</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER__PIECES = 2;

	/**
	 * The feature id for the '<em><b>Sheduled</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER__SHEDULED = 3;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER__LOCK = 4;

	/**
	 * The number of structural features of the '<em>Write Buffer</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRITE_BUFFER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl <em>Speed Container</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSpeedContainer()
	 * @generated
	 */
	int SPEED_CONTAINER = 14;

	/**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_CONTAINER__START_TIME = 0;

	/**
	 * The feature id for the '<em><b>Dwnld Size</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_CONTAINER__DWNLD_SIZE = 1;

	/**
	 * The feature id for the '<em><b>Upld Size</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_CONTAINER__UPLD_SIZE = 2;

	/**
	 * The feature id for the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_CONTAINER__SPEED = 3;

	/**
	 * The number of structural features of the '<em>Speed Container</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_CONTAINER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToSessionMapEntryImpl <em>String To Session Map Entry</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToSessionMapEntryImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getStringToSessionMapEntry()
	 * @generated
	 */
	int STRING_TO_SESSION_MAP_ENTRY = 15;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SESSION_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SESSION_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Session Map Entry</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_SESSION_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToClientSessionMapEntryImpl <em>String To Client Session Map Entry</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToClientSessionMapEntryImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getStringToClientSessionMapEntry()
	 * @generated
	 */
	int STRING_TO_CLIENT_SESSION_MAP_ENTRY = 16;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_CLIENT_SESSION_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_CLIENT_SESSION_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Client Session Map Entry</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_CLIENT_SESSION_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IntToPieceEntryImpl <em>Int To Piece Entry</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IntToPieceEntryImpl
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getIntToPieceEntry()
	 * @generated
	 */
	int INT_TO_PIECE_ENTRY = 17;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_TO_PIECE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_TO_PIECE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Int To Piece Entry</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_TO_PIECE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>Bool Array</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getBoolArray()
	 * @generated
	 */
	int BOOL_ARRAY = 19;

	/**
	 * The meta object id for the '<em>Byte Buffer</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see java.nio.ByteBuffer
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getByteBuffer()
	 * @generated
	 */
	int BYTE_BUFFER = 20;

	/**
	 * The meta object id for the '<em>Channel</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see java.nio.channels.SocketChannel
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getChannel()
	 * @generated
	 */
	int CHANNEL = 21;

	/**
	 * The meta object id for the '<em>Lock</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see java.util.concurrent.locks.ReentrantReadWriteLock
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getLock()
	 * @generated
	 */
	int LOCK = 22;

	/**
	 * The meta object id for the '<em>Set</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see java.util.Set
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSet()
	 * @generated
	 */
	int SET = 23;

	/**
	 * The meta object id for the '<em>URL</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see java.net.URL
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getURL()
	 * @generated
	 */
	int URL = 24;

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork <em>Client Network</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Network</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork
	 * @generated
	 */
	EClass getClientNetwork();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSwarmMap <em>Swarm Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Swarm Map</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSwarmMap()
	 * @see #getClientNetwork()
	 * @generated
	 */
	EReference getClientNetwork_SwarmMap();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSemaphor <em>Semaphor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Semaphor</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSemaphor()
	 * @see #getClientNetwork()
	 * @generated
	 */
	EReference getClientNetwork_Semaphor();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getActiveSwarms <em>Active Swarms</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Active Swarms</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getActiveSwarms()
	 * @see #getClientNetwork()
	 * @generated
	 */
	EAttribute getClientNetwork_ActiveSwarms();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getForceSwarmsKeys <em>Force Swarms Keys</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Force Swarms Keys</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getForceSwarmsKeys()
	 * @see #getClientNetwork()
	 * @generated
	 */
	EAttribute getClientNetwork_ForceSwarmsKeys();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#isSheduled <em>Sheduled</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sheduled</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#isSheduled()
	 * @see #getClientNetwork()
	 * @generated
	 */
	EAttribute getClientNetwork_Sheduled();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor <em>Semaphor</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semaphor</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor
	 * @generated
	 */
	EClass getSemaphor();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor#isConnected <em>Connected</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connected</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor#isConnected()
	 * @see #getSemaphor()
	 * @generated
	 */
	EAttribute getSemaphor_Connected();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor#isOpened <em>Opened</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Opened</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor#isOpened()
	 * @see #getSemaphor()
	 * @generated
	 */
	EAttribute getSemaphor_Opened();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession <em>Swarm Session</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swarm Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession
	 * @generated
	 */
	EClass getSwarmSession();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getProcessedPieces <em>Processed Pieces</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Processed Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getProcessedPieces()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EReference getSwarmSession_ProcessedPieces();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getUploadedPieces <em>Uploaded Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Uploaded Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getUploadedPieces()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EReference getSwarmSession_UploadedPieces();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getPieceBuffer <em>Piece Buffer</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference '<em>Piece Buffer</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getPieceBuffer()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EReference getSwarmSession_PieceBuffer();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSpeedContainer <em>Speed Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Speed Container</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSpeedContainer()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EReference getSwarmSession_SpeedContainer();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#isObtainingClients <em>Obtaining Clients</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Obtaining Clients</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#isObtainingClients()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EAttribute getSwarmSession_ObtainingClients();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTrackersManager <em>Trackers Manager</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trackers Manager</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTrackersManager()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EAttribute getSwarmSession_TrackersManager();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSearchersManager <em>Searchers Manager</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Searchers Manager</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSearchersManager()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EAttribute getSwarmSession_SearchersManager();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTorrent <em>Torrent</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Torrent</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTorrent()
	 * @see #getSwarmSession()
	 * @generated
	 */
	EAttribute getSwarmSession_Torrent();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session <em>Session</em>}'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session
	 * @generated
	 */
	EClass getSession();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getAnnounce <em>Announce</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Announce</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getAnnounce()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Announce();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getChannel <em>Channel</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Channel</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getChannel()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Channel();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getState <em>State</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getState()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_State();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#isSheduled <em>Sheduled</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sheduled</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#isSheduled()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Sheduled();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getLastActivity <em>Last Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Activity</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getLastActivity()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_LastActivity();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getRating <em>Rating</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rating</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getRating()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Rating();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getTimeout()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getDuration()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Duration();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getClients <em>Clients</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clients</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getClients()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_Clients();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo <em>Swarm Info</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swarm Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo
	 * @generated
	 */
	EClass getSwarmInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getDwnldStrategy <em>Dwnld Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dwnld Strategy</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getDwnldStrategy()
	 * @see #getSwarmInfo()
	 * @generated
	 */
	EAttribute getSwarmInfo_DwnldStrategy();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getAlgorithm()
	 * @see #getSwarmInfo()
	 * @generated
	 */
	EAttribute getSwarmInfo_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isCreatedOnDisc <em>Created On Disc</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created On Disc</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isCreatedOnDisc()
	 * @see #getSwarmInfo()
	 * @generated
	 */
	EAttribute getSwarmInfo_CreatedOnDisc();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isUploadOnly <em>Upload Only</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upload Only</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isUploadOnly()
	 * @see #getSwarmInfo()
	 * @generated
	 */
	EAttribute getSwarmInfo_UploadOnly();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent <em>Swarm Content</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swarm Content</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent
	 * @generated
	 */
	EClass getSwarmContent();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getTrackers <em>Trackers</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Trackers</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getTrackers()
	 * @see #getSwarmContent()
	 * @generated
	 */
	EReference getSwarmContent_Trackers();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getDownloads <em>Downloads</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Downloads</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getDownloads()
	 * @see #getSwarmContent()
	 * @generated
	 */
	EReference getSwarmContent_Downloads();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getUploads <em>Uploads</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Uploads</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getUploads()
	 * @see #getSwarmContent()
	 * @generated
	 */
	EReference getSwarmContent_Uploads();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession <em>Tracker Session</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracker Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession
	 * @generated
	 */
	EClass getTrackerSession();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getResponse <em>Response</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Response</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getResponse()
	 * @see #getTrackerSession()
	 * @generated
	 */
	EReference getTrackerSession_Response();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getNextConnection <em>Next Connection</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Next Connection</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getNextConnection()
	 * @see #getTrackerSession()
	 * @generated
	 */
	EAttribute getTrackerSession_NextConnection();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getEvent()
	 * @see #getTrackerSession()
	 * @generated
	 */
	EAttribute getTrackerSession_Event();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#isScraped <em>Scraped</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scraped</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#isScraped()
	 * @see #getTrackerSession()
	 * @generated
	 */
	EAttribute getTrackerSession_Scraped();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession <em>Dwnld Session</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dwnld Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession
	 * @generated
	 */
	EClass getDwnldSession();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession <em>Upld Session</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Upld Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession
	 * @generated
	 */
	EClass getUpldSession();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession <em>Client Session</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Session</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession
	 * @generated
	 */
	EClass getClientSession();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getReserved <em>Reserved</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reserved</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getReserved()
	 * @see #getClientSession()
	 * @generated
	 */
	EAttribute getClientSession_Reserved();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getCanceledBlocks <em>Canceled Blocks</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Canceled Blocks</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getCanceledBlocks()
	 * @see #getClientSession()
	 * @generated
	 */
	EAttribute getClientSession_CanceledBlocks();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#isReceivedKeepAlive <em>Received Keep Alive</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Received Keep Alive</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#isReceivedKeepAlive()
	 * @see #getClientSession()
	 * @generated
	 */
	EAttribute getClientSession_ReceivedKeepAlive();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getSpeedContainer <em>Speed Container</em>}'.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Speed Container</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getSpeedContainer()
	 * @see #getClientSession()
	 * @generated
	 */
	EReference getClientSession_SpeedContainer();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo <em>Client Info</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo
	 * @generated
	 */
	EClass getClientInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getClientName <em>Client Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Client Name</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getClientName()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_ClientName();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isChoking <em>Choking</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Choking</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isChoking()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_Choking();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isHandshakeOK <em>Handshake OK</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Handshake OK</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isHandshakeOK()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_HandshakeOK();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isInterested <em>Interested</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interested</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isInterested()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_Interested();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getBitfield <em>Bitfield</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bitfield</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getBitfield()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_Bitfield();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isSeed <em>Seed</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seed</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isSeed()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_Seed();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getHave <em>Have</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Have</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getHave()
	 * @see #getClientInfo()
	 * @generated
	 */
	EAttribute getClientInfo_Have();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse <em>Tracker Response</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracker Response</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse
	 * @generated
	 */
	EClass getTrackerResponse();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getFailureReason <em>Failure Reason</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failure Reason</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getFailureReason()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_FailureReason();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getInterval <em>Interval</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interval</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getInterval()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_Interval();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getMinInterval <em>Min Interval</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Interval</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getMinInterval()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_MinInterval();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getTrackerId <em>Tracker Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tracker Id</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getTrackerId()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_TrackerId();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getComplete <em>Complete</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Complete</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getComplete()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_Complete();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getIncomplete <em>Incomplete</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Incomplete</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getIncomplete()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_Incomplete();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getResponseMessage <em>Response Message</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Response Message</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getResponseMessage()
	 * @see #getTrackerResponse()
	 * @generated
	 */
	EAttribute getTrackerResponse_ResponseMessage();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece <em>IO Piece</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>IO Piece</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece
	 * @generated
	 */
	EClass getIOPiece();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPieceIndex <em>Piece Index</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Piece Index</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPieceIndex()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_PieceIndex();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPayload <em>Payload</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Payload</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPayload()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Payload();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#isFinished <em>Finished</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finished</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#isFinished()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Finished();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPosition()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Position();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getBlocks <em>Blocks</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blocks</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getBlocks()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Blocks();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLockedBlocks <em>Locked Blocks</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locked Blocks</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLockedBlocks()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_LockedBlocks();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getExtTorrent <em>Ext Torrent</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ext Torrent</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getExtTorrent()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_ExtTorrent();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLastActivity <em>Last Activity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Activity</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLastActivity()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_LastActivity();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLock <em>Lock</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLock()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Lock();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getColor <em>Color</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getColor()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Color();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getHash <em>Hash</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getHash()
	 * @see #getIOPiece()
	 * @generated
	 */
	EAttribute getIOPiece_Hash();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer <em>Write Buffer</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Write Buffer</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer
	 * @generated
	 */
	EClass getWriteBuffer();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getOffset()
	 * @see #getWriteBuffer()
	 * @generated
	 */
	EAttribute getWriteBuffer_Offset();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getSize()
	 * @see #getWriteBuffer()
	 * @generated
	 */
	EAttribute getWriteBuffer_Size();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getPieces <em>Pieces</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getPieces()
	 * @see #getWriteBuffer()
	 * @generated
	 */
	EReference getWriteBuffer_Pieces();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#isSheduled <em>Sheduled</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sheduled</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#isSheduled()
	 * @see #getWriteBuffer()
	 * @generated
	 */
	EAttribute getWriteBuffer_Sheduled();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getLock <em>Lock</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getLock()
	 * @see #getWriteBuffer()
	 * @generated
	 */
	EAttribute getWriteBuffer_Lock();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer <em>Speed Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Speed Container</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer
	 * @generated
	 */
	EClass getSpeedContainer();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getStartTime()
	 * @see #getSpeedContainer()
	 * @generated
	 */
	EAttribute getSpeedContainer_StartTime();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getDwnldSize <em>Dwnld Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dwnld Size</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getDwnldSize()
	 * @see #getSpeedContainer()
	 * @generated
	 */
	EAttribute getSpeedContainer_DwnldSize();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getUpldSize <em>Upld Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upld Size</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getUpldSize()
	 * @see #getSpeedContainer()
	 * @generated
	 */
	EAttribute getSpeedContainer_UpldSize();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getSpeed <em>Speed</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speed</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer#getSpeed()
	 * @see #getSpeedContainer()
	 * @generated
	 */
	EAttribute getSpeedContainer_Speed();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Session Map Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @return the meta object for class '<em>String To Session Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" valueType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session"
	 *        valueContainment="true"
	 * @generated
	 */
	EClass getStringToSessionMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToSessionMapEntry()
	 * @generated
	 */
	EAttribute getStringToSessionMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToSessionMapEntry()
	 * @generated
	 */
	EReference getStringToSessionMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Client Session Map Entry</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>String To Client Session Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession" valueContainment="true"
	 * @generated
	 */
	EClass getStringToClientSessionMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToClientSessionMapEntry()
	 * @generated
	 */
	EAttribute getStringToClientSessionMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToClientSessionMapEntry()
	 * @generated
	 */
	EReference getStringToClientSessionMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Int To Piece Entry</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int To Piece Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EIntegerObject"
	 *        valueType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece" valueContainment="true"
	 * @generated
	 */
	EClass getIntToPieceEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntToPieceEntry()
	 * @generated
	 */
	EAttribute getIntToPieceEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntToPieceEntry()
	 * @generated
	 */
	EReference getIntToPieceEntry_Value();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour <em>Behaviour</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behaviour</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour
	 * @generated
	 */
	EClass getBehaviour();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getAlgorithm()
	 * @see #getBehaviour()
	 * @generated
	 */
	EAttribute getBehaviour_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy</em>'.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getStrategy()
	 * @see #getBehaviour()
	 * @generated
	 */
	EAttribute getBehaviour_Strategy();

	/**
	 * Returns the meta object for data type '<em>Bool Array</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Bool Array</em>'.
	 * @model instanceClass="boolean[]"
	 * @generated
	 */
	EDataType getBoolArray();

	/**
	 * Returns the meta object for data type '{@link java.nio.ByteBuffer <em>Byte Buffer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Byte Buffer</em>'.
	 * @see java.nio.ByteBuffer
	 * @model instanceClass="java.nio.ByteBuffer"
	 * @generated
	 */
	EDataType getByteBuffer();

	/**
	 * Returns the meta object for data type '{@link java.nio.channels.SocketChannel <em>Channel</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Channel</em>'.
	 * @see java.nio.channels.SocketChannel
	 * @model instanceClass="java.nio.channels.SocketChannel"
	 * @generated
	 */
	EDataType getChannel();

	/**
	 * Returns the meta object for data type '{@link java.util.concurrent.locks.ReentrantReadWriteLock <em>Lock</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for data type '<em>Lock</em>'.
	 * @see java.util.concurrent.locks.ReentrantReadWriteLock
	 * @model instanceClass="java.util.concurrent.locks.ReentrantReadWriteLock"
	 * @generated
	 */
	EDataType getLock();

	/**
	 * Returns the meta object for data type '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Set</em>'.
	 * @see java.util.Set
	 * @model instanceClass="java.util.Set"
	 * @generated
	 */
	EDataType getSet();

	/**
	 * Returns the meta object for data type '{@link java.net.URL <em>URL</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URL</em>'.
	 * @see java.net.URL
	 * @model instanceClass="java.net.URL"
	 * @generated
	 */
	EDataType getURL();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClientNetworkFactory getClientNetworkFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl <em>Client Network</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientNetwork()
		 * @generated
		 */
		EClass CLIENT_NETWORK = eINSTANCE.getClientNetwork();

		/**
		 * The meta object literal for the '<em><b>Swarm Map</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLIENT_NETWORK__SWARM_MAP = eINSTANCE.getClientNetwork_SwarmMap();

		/**
		 * The meta object literal for the '<em><b>Semaphor</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLIENT_NETWORK__SEMAPHOR = eINSTANCE.getClientNetwork_Semaphor();

		/**
		 * The meta object literal for the '<em><b>Active Swarms</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_NETWORK__ACTIVE_SWARMS = eINSTANCE.getClientNetwork_ActiveSwarms();

		/**
		 * The meta object literal for the '<em><b>Force Swarms Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_NETWORK__FORCE_SWARMS_KEYS = eINSTANCE.getClientNetwork_ForceSwarmsKeys();

		/**
		 * The meta object literal for the '<em><b>Sheduled</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_NETWORK__SHEDULED = eINSTANCE.getClientNetwork_Sheduled();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl <em>Semaphor</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSemaphor()
		 * @generated
		 */
		EClass SEMAPHOR = eINSTANCE.getSemaphor();

		/**
		 * The meta object literal for the '<em><b>Connected</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMAPHOR__CONNECTED = eINSTANCE.getSemaphor_Connected();

		/**
		 * The meta object literal for the '<em><b>Opened</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMAPHOR__OPENED = eINSTANCE.getSemaphor_Opened();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl <em>Swarm Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmSessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmSession()
		 * @generated
		 */
		EClass SWARM_SESSION = eINSTANCE.getSwarmSession();

		/**
		 * The meta object literal for the '<em><b>Processed Pieces</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWARM_SESSION__PROCESSED_PIECES = eINSTANCE.getSwarmSession_ProcessedPieces();

		/**
		 * The meta object literal for the '<em><b>Uploaded Pieces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference SWARM_SESSION__UPLOADED_PIECES = eINSTANCE.getSwarmSession_UploadedPieces();

		/**
		 * The meta object literal for the '<em><b>Piece Buffer</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWARM_SESSION__PIECE_BUFFER = eINSTANCE.getSwarmSession_PieceBuffer();

		/**
		 * The meta object literal for the '<em><b>Speed Container</b></em>' containment reference feature. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * @generated
		 */
		EReference SWARM_SESSION__SPEED_CONTAINER = eINSTANCE.getSwarmSession_SpeedContainer();

		/**
		 * The meta object literal for the '<em><b>Obtaining Clients</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_SESSION__OBTAINING_CLIENTS = eINSTANCE.getSwarmSession_ObtainingClients();

		/**
		 * The meta object literal for the '<em><b>Trackers Manager</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_SESSION__TRACKERS_MANAGER = eINSTANCE.getSwarmSession_TrackersManager();

		/**
		 * The meta object literal for the '<em><b>Searchers Manager</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_SESSION__SEARCHERS_MANAGER = eINSTANCE.getSwarmSession_SearchersManager();

		/**
		 * The meta object literal for the '<em><b>Torrent</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_SESSION__TORRENT = eINSTANCE.getSwarmSession_Torrent();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl <em>Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSession()
		 * @generated
		 */
		EClass SESSION = eINSTANCE.getSession();

		/**
		 * The meta object literal for the '<em><b>Announce</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__ANNOUNCE = eINSTANCE.getSession_Announce();

		/**
		 * The meta object literal for the '<em><b>Channel</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__CHANNEL = eINSTANCE.getSession_Channel();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__STATE = eINSTANCE.getSession_State();

		/**
		 * The meta object literal for the '<em><b>Sheduled</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__SHEDULED = eINSTANCE.getSession_Sheduled();

		/**
		 * The meta object literal for the '<em><b>Last Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__LAST_ACTIVITY = eINSTANCE.getSession_LastActivity();

		/**
		 * The meta object literal for the '<em><b>Rating</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__RATING = eINSTANCE.getSession_Rating();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__TIMEOUT = eINSTANCE.getSession_Timeout();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__DURATION = eINSTANCE.getSession_Duration();

		/**
		 * The meta object literal for the '<em><b>Clients</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__CLIENTS = eINSTANCE.getSession_Clients();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl <em>Swarm Info</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmInfo()
		 * @generated
		 */
		EClass SWARM_INFO = eINSTANCE.getSwarmInfo();

		/**
		 * The meta object literal for the '<em><b>Dwnld Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_INFO__DWNLD_STRATEGY = eINSTANCE.getSwarmInfo_DwnldStrategy();

		/**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_INFO__ALGORITHM = eINSTANCE.getSwarmInfo_Algorithm();

		/**
		 * The meta object literal for the '<em><b>Created On Disc</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_INFO__CREATED_ON_DISC = eINSTANCE.getSwarmInfo_CreatedOnDisc();

		/**
		 * The meta object literal for the '<em><b>Upload Only</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWARM_INFO__UPLOAD_ONLY = eINSTANCE.getSwarmInfo_UploadOnly();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl <em>Swarm Content</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSwarmContent()
		 * @generated
		 */
		EClass SWARM_CONTENT = eINSTANCE.getSwarmContent();

		/**
		 * The meta object literal for the '<em><b>Trackers</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWARM_CONTENT__TRACKERS = eINSTANCE.getSwarmContent_Trackers();

		/**
		 * The meta object literal for the '<em><b>Downloads</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWARM_CONTENT__DOWNLOADS = eINSTANCE.getSwarmContent_Downloads();

		/**
		 * The meta object literal for the '<em><b>Uploads</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWARM_CONTENT__UPLOADS = eINSTANCE.getSwarmContent_Uploads();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl <em>Tracker Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getTrackerSession()
		 * @generated
		 */
		EClass TRACKER_SESSION = eINSTANCE.getTrackerSession();

		/**
		 * The meta object literal for the '<em><b>Response</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKER_SESSION__RESPONSE = eINSTANCE.getTrackerSession_Response();

		/**
		 * The meta object literal for the '<em><b>Next Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_SESSION__NEXT_CONNECTION = eINSTANCE.getTrackerSession_NextConnection();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_SESSION__EVENT = eINSTANCE.getTrackerSession_Event();

		/**
		 * The meta object literal for the '<em><b>Scraped</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_SESSION__SCRAPED = eINSTANCE.getTrackerSession_Scraped();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.DwnldSessionImpl <em>Dwnld Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.DwnldSessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getDwnldSession()
		 * @generated
		 */
		EClass DWNLD_SESSION = eINSTANCE.getDwnldSession();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.UpldSessionImpl <em>Upld Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.UpldSessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getUpldSession()
		 * @generated
		 */
		EClass UPLD_SESSION = eINSTANCE.getUpldSession();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl <em>Client Session</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientSession()
		 * @generated
		 */
		EClass CLIENT_SESSION = eINSTANCE.getClientSession();

		/**
		 * The meta object literal for the '<em><b>Reserved</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_SESSION__RESERVED = eINSTANCE.getClientSession_Reserved();

		/**
		 * The meta object literal for the '<em><b>Canceled Blocks</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_SESSION__CANCELED_BLOCKS = eINSTANCE.getClientSession_CanceledBlocks();

		/**
		 * The meta object literal for the '<em><b>Received Keep Alive</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_SESSION__RECEIVED_KEEP_ALIVE = eINSTANCE.getClientSession_ReceivedKeepAlive();

		/**
		 * The meta object literal for the '<em><b>Speed Container</b></em>' containment reference feature. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * @generated
		 */
		EReference CLIENT_SESSION__SPEED_CONTAINER = eINSTANCE.getClientSession_SpeedContainer();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl <em>Client Info</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getClientInfo()
		 * @generated
		 */
		EClass CLIENT_INFO = eINSTANCE.getClientInfo();

		/**
		 * The meta object literal for the '<em><b>Client Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__CLIENT_NAME = eINSTANCE.getClientInfo_ClientName();

		/**
		 * The meta object literal for the '<em><b>Choking</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__CHOKING = eINSTANCE.getClientInfo_Choking();

		/**
		 * The meta object literal for the '<em><b>Handshake OK</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__HANDSHAKE_OK = eINSTANCE.getClientInfo_HandshakeOK();

		/**
		 * The meta object literal for the '<em><b>Interested</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__INTERESTED = eINSTANCE.getClientInfo_Interested();

		/**
		 * The meta object literal for the '<em><b>Bitfield</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__BITFIELD = eINSTANCE.getClientInfo_Bitfield();

		/**
		 * The meta object literal for the '<em><b>Seed</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__SEED = eINSTANCE.getClientInfo_Seed();

		/**
		 * The meta object literal for the '<em><b>Have</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_INFO__HAVE = eINSTANCE.getClientInfo_Have();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl <em>Tracker Response</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getTrackerResponse()
		 * @generated
		 */
		EClass TRACKER_RESPONSE = eINSTANCE.getTrackerResponse();

		/**
		 * The meta object literal for the '<em><b>Failure Reason</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__FAILURE_REASON = eINSTANCE.getTrackerResponse_FailureReason();

		/**
		 * The meta object literal for the '<em><b>Interval</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__INTERVAL = eINSTANCE.getTrackerResponse_Interval();

		/**
		 * The meta object literal for the '<em><b>Min Interval</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__MIN_INTERVAL = eINSTANCE.getTrackerResponse_MinInterval();

		/**
		 * The meta object literal for the '<em><b>Tracker Id</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__TRACKER_ID = eINSTANCE.getTrackerResponse_TrackerId();

		/**
		 * The meta object literal for the '<em><b>Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__COMPLETE = eINSTANCE.getTrackerResponse_Complete();

		/**
		 * The meta object literal for the '<em><b>Incomplete</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__INCOMPLETE = eINSTANCE.getTrackerResponse_Incomplete();

		/**
		 * The meta object literal for the '<em><b>Response Message</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKER_RESPONSE__RESPONSE_MESSAGE = eINSTANCE.getTrackerResponse_ResponseMessage();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl <em>IO Piece</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getIOPiece()
		 * @generated
		 */
		EClass IO_PIECE = eINSTANCE.getIOPiece();

		/**
		 * The meta object literal for the '<em><b>Piece Index</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__PIECE_INDEX = eINSTANCE.getIOPiece_PieceIndex();

		/**
		 * The meta object literal for the '<em><b>Payload</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__PAYLOAD = eINSTANCE.getIOPiece_Payload();

		/**
		 * The meta object literal for the '<em><b>Finished</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__FINISHED = eINSTANCE.getIOPiece_Finished();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__POSITION = eINSTANCE.getIOPiece_Position();

		/**
		 * The meta object literal for the '<em><b>Blocks</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__BLOCKS = eINSTANCE.getIOPiece_Blocks();

		/**
		 * The meta object literal for the '<em><b>Locked Blocks</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__LOCKED_BLOCKS = eINSTANCE.getIOPiece_LockedBlocks();

		/**
		 * The meta object literal for the '<em><b>Ext Torrent</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__EXT_TORRENT = eINSTANCE.getIOPiece_ExtTorrent();

		/**
		 * The meta object literal for the '<em><b>Last Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__LAST_ACTIVITY = eINSTANCE.getIOPiece_LastActivity();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__LOCK = eINSTANCE.getIOPiece_Lock();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__COLOR = eINSTANCE.getIOPiece_Color();

		/**
		 * The meta object literal for the '<em><b>Hash</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IO_PIECE__HASH = eINSTANCE.getIOPiece_Hash();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl <em>Write Buffer</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getWriteBuffer()
		 * @generated
		 */
		EClass WRITE_BUFFER = eINSTANCE.getWriteBuffer();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_BUFFER__OFFSET = eINSTANCE.getWriteBuffer_Offset();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_BUFFER__SIZE = eINSTANCE.getWriteBuffer_Size();

		/**
		 * The meta object literal for the '<em><b>Pieces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference WRITE_BUFFER__PIECES = eINSTANCE.getWriteBuffer_Pieces();

		/**
		 * The meta object literal for the '<em><b>Sheduled</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_BUFFER__SHEDULED = eINSTANCE.getWriteBuffer_Sheduled();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WRITE_BUFFER__LOCK = eINSTANCE.getWriteBuffer_Lock();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl <em>Speed Container</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSpeedContainer()
		 * @generated
		 */
		EClass SPEED_CONTAINER = eINSTANCE.getSpeedContainer();

		/**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_CONTAINER__START_TIME = eINSTANCE.getSpeedContainer_StartTime();

		/**
		 * The meta object literal for the '<em><b>Dwnld Size</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_CONTAINER__DWNLD_SIZE = eINSTANCE.getSpeedContainer_DwnldSize();

		/**
		 * The meta object literal for the '<em><b>Upld Size</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_CONTAINER__UPLD_SIZE = eINSTANCE.getSpeedContainer_UpldSize();

		/**
		 * The meta object literal for the '<em><b>Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_CONTAINER__SPEED = eINSTANCE.getSpeedContainer_Speed();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToSessionMapEntryImpl <em>String To Session Map Entry</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToSessionMapEntryImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getStringToSessionMapEntry()
		 * @generated
		 */
		EClass STRING_TO_SESSION_MAP_ENTRY = eINSTANCE.getStringToSessionMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_SESSION_MAP_ENTRY__KEY = eINSTANCE.getStringToSessionMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_SESSION_MAP_ENTRY__VALUE = eINSTANCE.getStringToSessionMapEntry_Value();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToClientSessionMapEntryImpl <em>String To Client Session Map Entry</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.StringToClientSessionMapEntryImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getStringToClientSessionMapEntry()
		 * @generated
		 */
		EClass STRING_TO_CLIENT_SESSION_MAP_ENTRY = eINSTANCE.getStringToClientSessionMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_CLIENT_SESSION_MAP_ENTRY__KEY = eINSTANCE.getStringToClientSessionMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_CLIENT_SESSION_MAP_ENTRY__VALUE = eINSTANCE.getStringToClientSessionMapEntry_Value();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IntToPieceEntryImpl <em>Int To Piece Entry</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IntToPieceEntryImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getIntToPieceEntry()
		 * @generated
		 */
		EClass INT_TO_PIECE_ENTRY = eINSTANCE.getIntToPieceEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_TO_PIECE_ENTRY__KEY = eINSTANCE.getIntToPieceEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference INT_TO_PIECE_ENTRY__VALUE = eINSTANCE.getIntToPieceEntry_Value();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl <em>Behaviour</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getBehaviour()
		 * @generated
		 */
		EClass BEHAVIOUR = eINSTANCE.getBehaviour();

		/**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOUR__ALGORITHM = eINSTANCE.getBehaviour_Algorithm();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOUR__STRATEGY = eINSTANCE.getBehaviour_Strategy();

		/**
		 * The meta object literal for the '<em>Bool Array</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getBoolArray()
		 * @generated
		 */
		EDataType BOOL_ARRAY = eINSTANCE.getBoolArray();

		/**
		 * The meta object literal for the '<em>Byte Buffer</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see java.nio.ByteBuffer
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getByteBuffer()
		 * @generated
		 */
		EDataType BYTE_BUFFER = eINSTANCE.getByteBuffer();

		/**
		 * The meta object literal for the '<em>Channel</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see java.nio.channels.SocketChannel
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getChannel()
		 * @generated
		 */
		EDataType CHANNEL = eINSTANCE.getChannel();

		/**
		 * The meta object literal for the '<em>Lock</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see java.util.concurrent.locks.ReentrantReadWriteLock
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getLock()
		 * @generated
		 */
		EDataType LOCK = eINSTANCE.getLock();

		/**
		 * The meta object literal for the '<em>Set</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see java.util.Set
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getSet()
		 * @generated
		 */
		EDataType SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '<em>URL</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see java.net.URL
		 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl#getURL()
		 * @generated
		 */
		EDataType URL = eINSTANCE.getURL();

	}

} // ClientNetworkPackage
