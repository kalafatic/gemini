/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.*;

import java.net.URL;

import java.nio.ByteBuffer;

import java.nio.channels.SocketChannel;

import java.util.Map;
import java.util.Set;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class ClientNetworkFactoryImpl extends EFactoryImpl implements ClientNetworkFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ClientNetworkFactory init() {
		try {
			ClientNetworkFactory theClientNetworkFactory = (ClientNetworkFactory)EPackage.Registry.INSTANCE.getEFactory("http:///clientNetwork.ecore"); 
			if (theClientNetworkFactory != null) {
				return theClientNetworkFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClientNetworkFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetworkFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ClientNetworkPackage.CLIENT_NETWORK: return createClientNetwork();
			case ClientNetworkPackage.SEMAPHOR: return createSemaphor();
			case ClientNetworkPackage.SWARM_SESSION: return createSwarmSession();
			case ClientNetworkPackage.SESSION: return createSession();
			case ClientNetworkPackage.SWARM_INFO: return createSwarmInfo();
			case ClientNetworkPackage.SWARM_CONTENT: return createSwarmContent();
			case ClientNetworkPackage.TRACKER_SESSION: return createTrackerSession();
			case ClientNetworkPackage.DWNLD_SESSION: return createDwnldSession();
			case ClientNetworkPackage.UPLD_SESSION: return createUpldSession();
			case ClientNetworkPackage.CLIENT_SESSION: return createClientSession();
			case ClientNetworkPackage.CLIENT_INFO: return createClientInfo();
			case ClientNetworkPackage.TRACKER_RESPONSE: return createTrackerResponse();
			case ClientNetworkPackage.IO_PIECE: return createIOPiece();
			case ClientNetworkPackage.WRITE_BUFFER: return createWriteBuffer();
			case ClientNetworkPackage.SPEED_CONTAINER: return createSpeedContainer();
			case ClientNetworkPackage.STRING_TO_SESSION_MAP_ENTRY: return (EObject)createStringToSessionMapEntry();
			case ClientNetworkPackage.STRING_TO_CLIENT_SESSION_MAP_ENTRY: return (EObject)createStringToClientSessionMapEntry();
			case ClientNetworkPackage.INT_TO_PIECE_ENTRY: return (EObject)createIntToPieceEntry();
			case ClientNetworkPackage.BEHAVIOUR: return createBehaviour();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ClientNetworkPackage.BOOL_ARRAY:
				return createBoolArrayFromString(eDataType, initialValue);
			case ClientNetworkPackage.BYTE_BUFFER:
				return createByteBufferFromString(eDataType, initialValue);
			case ClientNetworkPackage.CHANNEL:
				return createChannelFromString(eDataType, initialValue);
			case ClientNetworkPackage.LOCK:
				return createLockFromString(eDataType, initialValue);
			case ClientNetworkPackage.SET:
				return createSetFromString(eDataType, initialValue);
			case ClientNetworkPackage.URL:
				return createURLFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ClientNetworkPackage.BOOL_ARRAY:
				return convertBoolArrayToString(eDataType, instanceValue);
			case ClientNetworkPackage.BYTE_BUFFER:
				return convertByteBufferToString(eDataType, instanceValue);
			case ClientNetworkPackage.CHANNEL:
				return convertChannelToString(eDataType, instanceValue);
			case ClientNetworkPackage.LOCK:
				return convertLockToString(eDataType, instanceValue);
			case ClientNetworkPackage.SET:
				return convertSetToString(eDataType, instanceValue);
			case ClientNetworkPackage.URL:
				return convertURLToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetwork createClientNetwork() {
		ClientNetworkImpl clientNetwork = new ClientNetworkImpl();
		return clientNetwork;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphor createSemaphor() {
		SemaphorImpl semaphor = new SemaphorImpl();
		return semaphor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SwarmSession createSwarmSession() {
		SwarmSessionImpl swarmSession = new SwarmSessionImpl();
		return swarmSession;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Session createSession() {
		SessionImpl session = new SessionImpl();
		return session;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SwarmInfo createSwarmInfo() {
		SwarmInfoImpl swarmInfo = new SwarmInfoImpl();
		return swarmInfo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SwarmContent createSwarmContent() {
		SwarmContentImpl swarmContent = new SwarmContentImpl();
		return swarmContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerSession createTrackerSession() {
		TrackerSessionImpl trackerSession = new TrackerSessionImpl();
		return trackerSession;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DwnldSession createDwnldSession() {
		DwnldSessionImpl dwnldSession = new DwnldSessionImpl();
		return dwnldSession;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public UpldSession createUpldSession() {
		UpldSessionImpl upldSession = new UpldSessionImpl();
		return upldSession;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientSession createClientSession() {
		ClientSessionImpl clientSession = new ClientSessionImpl();
		return clientSession;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientInfo createClientInfo() {
		ClientInfoImpl clientInfo = new ClientInfoImpl();
		return clientInfo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerResponse createTrackerResponse() {
		TrackerResponseImpl trackerResponse = new TrackerResponseImpl();
		return trackerResponse;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IOPiece createIOPiece() {
		IOPieceImpl ioPiece = new IOPieceImpl();
		return ioPiece;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WriteBuffer createWriteBuffer() {
		WriteBufferImpl writeBuffer = new WriteBufferImpl();
		return writeBuffer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedContainer createSpeedContainer() {
		SpeedContainerImpl speedContainer = new SpeedContainerImpl();
		return speedContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Session> createStringToSessionMapEntry() {
		StringToSessionMapEntryImpl stringToSessionMapEntry = new StringToSessionMapEntryImpl();
		return stringToSessionMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, ClientSession> createStringToClientSessionMapEntry() {
		StringToClientSessionMapEntryImpl stringToClientSessionMapEntry = new StringToClientSessionMapEntryImpl();
		return stringToClientSessionMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Integer, IOPiece> createIntToPieceEntry() {
		IntToPieceEntryImpl intToPieceEntry = new IntToPieceEntryImpl();
		return intToPieceEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Behaviour createBehaviour() {
		BehaviourImpl behaviour = new BehaviourImpl();
		return behaviour;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean[] createBoolArrayFromString(EDataType eDataType, String initialValue) {
		return (boolean[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBoolArrayToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ByteBuffer createByteBufferFromString(EDataType eDataType, String initialValue) {
		return (ByteBuffer)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertByteBufferToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SocketChannel createChannelFromString(EDataType eDataType, String initialValue) {
		return (SocketChannel)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertChannelToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ReentrantReadWriteLock createLockFromString(EDataType eDataType, String initialValue) {
		return (ReentrantReadWriteLock)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLockToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Set createSetFromString(EDataType eDataType, String initialValue) {
		return (Set)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSetToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URL createURLFromString(EDataType eDataType, String initialValue) {
		return (URL)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURLToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetworkPackage getClientNetworkPackage() {
		return (ClientNetworkPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ClientNetworkPackage getPackage() {
		return ClientNetworkPackage.eINSTANCE;
	}

} // ClientNetworkFactoryImpl
