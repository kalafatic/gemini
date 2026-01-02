/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkPackageImpl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClientHandshakeProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClientTransportProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClients;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EReturn;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerResponseCode;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class NetworkConstantsPackageImpl extends EPackageImpl implements NetworkConstantsPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eTrackerResponseCodeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eTrackerRequestProtocolEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eTrackerResponseProtocolEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eClientHandshakeProtocolEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eClientTransportProtocolEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eTrackerRequestEventEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eViewsMessagesEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eClientsEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eStrategyEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eAlgorithmEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eReturnEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NetworkConstantsPackageImpl() {
		super(eNS_URI, NetworkConstantsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link NetworkConstantsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NetworkConstantsPackage init() {
		if (isInited) return (NetworkConstantsPackage)EPackage.Registry.INSTANCE.getEPackage(NetworkConstantsPackage.eNS_URI);

		// Obtain or create and register package
		NetworkConstantsPackageImpl theNetworkConstantsPackage = (NetworkConstantsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NetworkConstantsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NetworkConstantsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ClientNetworkPackageImpl theClientNetworkPackage = (ClientNetworkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientNetworkPackage.eNS_URI) instanceof ClientNetworkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientNetworkPackage.eNS_URI) : ClientNetworkPackage.eINSTANCE);

		// Create package meta-data objects
		theNetworkConstantsPackage.createPackageContents();
		theClientNetworkPackage.createPackageContents();

		// Initialize created meta-data
		theNetworkConstantsPackage.initializePackageContents();
		theClientNetworkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNetworkConstantsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NetworkConstantsPackage.eNS_URI, theNetworkConstantsPackage);
		return theNetworkConstantsPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getETrackerResponseCode() {
		return eTrackerResponseCodeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getETrackerRequestProtocol() {
		return eTrackerRequestProtocolEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getETrackerResponseProtocol() {
		return eTrackerResponseProtocolEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEClientHandshakeProtocol() {
		return eClientHandshakeProtocolEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEClientTransportProtocol() {
		return eClientTransportProtocolEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getETrackerRequestEvent() {
		return eTrackerRequestEventEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEViewsMessages() {
		return eViewsMessagesEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEClients() {
		return eClientsEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEStrategy() {
		return eStrategyEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEAlgorithm() {
		return eAlgorithmEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEReturn() {
		return eReturnEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkConstantsFactory getNetworkConstantsFactory() {
		return (NetworkConstantsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create enums
		eTrackerResponseCodeEEnum = createEEnum(ETRACKER_RESPONSE_CODE);
		eTrackerRequestProtocolEEnum = createEEnum(ETRACKER_REQUEST_PROTOCOL);
		eTrackerResponseProtocolEEnum = createEEnum(ETRACKER_RESPONSE_PROTOCOL);
		eClientHandshakeProtocolEEnum = createEEnum(ECLIENT_HANDSHAKE_PROTOCOL);
		eClientTransportProtocolEEnum = createEEnum(ECLIENT_TRANSPORT_PROTOCOL);
		eTrackerRequestEventEEnum = createEEnum(ETRACKER_REQUEST_EVENT);
		eViewsMessagesEEnum = createEEnum(EVIEWS_MESSAGES);
		eClientsEEnum = createEEnum(ECLIENTS);
		eStrategyEEnum = createEEnum(ESTRATEGY);
		eAlgorithmEEnum = createEEnum(EALGORITHM);
		eReturnEEnum = createEEnum(ERETURN);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Initialize enums and add enum literals
		initEEnum(eTrackerResponseCodeEEnum, ETrackerResponseCode.class, "ETrackerResponseCode");
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_REQUEST_TYPE);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_INFO_HASH);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_PEER_ID);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.MISSING_PORT);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_INFO_HASH);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_PEER_ID);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INVALID_NUMWANT);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.INFO_HASH_NOT_FOUND);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.FOUND);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.BAD_REQUEST);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.NOT_FOUND);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.EVENTLESS_REQUEST);
		addEEnumLiteral(eTrackerResponseCodeEEnum, ETrackerResponseCode.GENERIC_ERROR);

		initEEnum(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.class, "ETrackerRequestProtocol");
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.ANNOUNCE);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.INFO_HASH);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.PEER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.PORT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.UPLOADED);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.DOWNLOADED);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.LEFT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.COMPACT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.NO_PEER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.IP);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.NUMWANT);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.KEY);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.TRACKER_ID);
		addEEnumLiteral(eTrackerRequestProtocolEEnum, ETrackerRequestProtocol.EVENT);

		initEEnum(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.class, "ETrackerResponseProtocol");
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.FAILURE_REASON);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.WARNING_MESSAGE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.INTERVAL);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.MIN_INTERVAL);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.TRACKER_ID);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.COMPLETE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.INCOMPLETE);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PEERS);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PEER_ID);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.IP);
		addEEnumLiteral(eTrackerResponseProtocolEEnum, ETrackerResponseProtocol.PORT);

		initEEnum(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.class, "EClientHandshakeProtocol");
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.HANDSHAKE);
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.PROTOCOL_LEN);
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.PROTOCOL_NAME);
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.RESERVED);
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.INFO_HASH);
		addEEnumLiteral(eClientHandshakeProtocolEEnum, EClientHandshakeProtocol.PEER_ID);

		initEEnum(eClientTransportProtocolEEnum, EClientTransportProtocol.class, "EClientTransportProtocol");
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.CHOKE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.UNCHOKE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.INTERESTED);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.NOT_INTERESTED);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.HAVE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.BITFIELD);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.REQUEST);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.PIECE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.CANCEL);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.DHT_PORT);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.KEEP_ALIVE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.LT_EXT_MESSAGE);
		addEEnumLiteral(eClientTransportProtocolEEnum, EClientTransportProtocol.HANDSHAKE);

		initEEnum(eTrackerRequestEventEEnum, ETrackerRequestEvent.class, "ETrackerRequestEvent");
		addEEnumLiteral(eTrackerRequestEventEEnum, ETrackerRequestEvent.STARTED);
		addEEnumLiteral(eTrackerRequestEventEEnum, ETrackerRequestEvent.STOPPED);
		addEEnumLiteral(eTrackerRequestEventEEnum, ETrackerRequestEvent.COMPLETED);

		initEEnum(eViewsMessagesEEnum, EViewsMessages.class, "EViewsMessages");
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.ACTIVATED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.RUNNING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.PAUSED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.STOPPED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.WAITING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.SUSPENDED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.DISABLED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.REJECTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_TRACKER_HANDSHAKE);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CAN_NOT_CONNECT_TRACKER);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CONNECTING_TRACKERS);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.TRACKERS_CONNECTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.TRACKERS_FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.TRACKER_SENT_WARNING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.TRACKER_SENT_FAILURE);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.SEARCH_FOR_CLIENTS);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.SEARCH_FOR_CLIENTS_FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CONNECTING_CLIENTS);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CAN_NOT_CONNECT_CLIENT);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CONNECTED_TO_CLIENTS);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CLIENT_CONNECTION_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_HANDSHAKE);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_RECEIVE_HANDSHAKE);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_SEND_HANDSHAKE);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CLIENT_HANDSHAKE_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CLIENT_HANDSHAKE_OK);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CLIENT_IS_CHOKING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.CLIENT_NOT_INTERESTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.RATIO_EXCEEDED_CHOKING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_INTERESTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.PEER_IS_INTERESTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.PEER_NOT_INTERESTED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.RATING_REJECTION);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_DOWNLOAD);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.DOWNLOADING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.DOWNLOAD_PIECE_FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.DOWNLOAD_FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.DOWNLOAD_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READY_TO_UPLOAD);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.UPLOADING);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.UPLOAD_PICE_FINISHED);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.UPLOAD_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.READ_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.WRITE_ERROR);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.STARTING_SWARM);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.PAUSING_SWARM);
		addEEnumLiteral(eViewsMessagesEEnum, EViewsMessages.STOPPING_SWARM);

		initEEnum(eClientsEEnum, EClients.class, "EClients");
		addEEnumLiteral(eClientsEEnum, EClients.AG);
		addEEnumLiteral(eClientsEEnum, EClients.AR);
		addEEnumLiteral(eClientsEEnum, EClients.AT);
		addEEnumLiteral(eClientsEEnum, EClients.AX);
		addEEnumLiteral(eClientsEEnum, EClients.AZ);
		addEEnumLiteral(eClientsEEnum, EClients.BB);
		addEEnumLiteral(eClientsEEnum, EClients.BC);
		addEEnumLiteral(eClientsEEnum, EClients.BF);
		addEEnumLiteral(eClientsEEnum, EClients.BG);
		addEEnumLiteral(eClientsEEnum, EClients.BP);
		addEEnumLiteral(eClientsEEnum, EClients.BR);
		addEEnumLiteral(eClientsEEnum, EClients.BS);
		addEEnumLiteral(eClientsEEnum, EClients.BW);
		addEEnumLiteral(eClientsEEnum, EClients.BX);
		addEEnumLiteral(eClientsEEnum, EClients.CD);
		addEEnumLiteral(eClientsEEnum, EClients.CT);
		addEEnumLiteral(eClientsEEnum, EClients.DE);
		addEEnumLiteral(eClientsEEnum, EClients.DP);
		addEEnumLiteral(eClientsEEnum, EClients.EB);
		addEEnumLiteral(eClientsEEnum, EClients.ES);
		addEEnumLiteral(eClientsEEnum, EClients.FC);
		addEEnumLiteral(eClientsEEnum, EClients.FT);
		addEEnumLiteral(eClientsEEnum, EClients.GS);
		addEEnumLiteral(eClientsEEnum, EClients.HL);
		addEEnumLiteral(eClientsEEnum, EClients.HN);
		addEEnumLiteral(eClientsEEnum, EClients.KG);
		addEEnumLiteral(eClientsEEnum, EClients.KT);
		addEEnumLiteral(eClientsEEnum, EClients.LC);
		addEEnumLiteral(eClientsEEnum, EClients.LH);
		addEEnumLiteral(eClientsEEnum, EClients.LP);
		addEEnumLiteral(eClientsEEnum, EClients.LT);
		addEEnumLiteral(eClientsEEnum, EClients.MP);
		addEEnumLiteral(eClientsEEnum, EClients.MT);
		addEEnumLiteral(eClientsEEnum, EClients.QT);
		addEEnumLiteral(eClientsEEnum, EClients.RT);
		addEEnumLiteral(eClientsEEnum, EClients.SB);
		addEEnumLiteral(eClientsEEnum, EClients.SS);
		addEEnumLiteral(eClientsEEnum, EClients.SZ);
		addEEnumLiteral(eClientsEEnum, EClients.TN);
		addEEnumLiteral(eClientsEEnum, EClients.TR);
		addEEnumLiteral(eClientsEEnum, EClients.TS);
		addEEnumLiteral(eClientsEEnum, EClients.UT);
		addEEnumLiteral(eClientsEEnum, EClients.XT);
		addEEnumLiteral(eClientsEEnum, EClients.ZT);

		initEEnum(eStrategyEEnum, EStrategy.class, "EStrategy");
		addEEnumLiteral(eStrategyEEnum, EStrategy.RANDOM);
		addEEnumLiteral(eStrategyEEnum, EStrategy.SEQUENCE);
		addEEnumLiteral(eStrategyEEnum, EStrategy.EXACT);

		initEEnum(eAlgorithmEEnum, EAlgorithm.class, "EAlgorithm");
		addEEnumLiteral(eAlgorithmEEnum, EAlgorithm.QUEUENING);
		addEEnumLiteral(eAlgorithmEEnum, EAlgorithm.SUPERSEEDING);

		initEEnum(eReturnEEnum, EReturn.class, "EReturn");
		addEEnumLiteral(eReturnEEnum, EReturn.EXIT);
		addEEnumLiteral(eReturnEEnum, EReturn.ERROR);
		addEEnumLiteral(eReturnEEnum, EReturn.OK);
	}

} // NetworkConstantsPackageImpl
