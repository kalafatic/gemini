/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkFactory;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.impl.NetworkConstantsPackageImpl;

import java.nio.ByteBuffer;

import java.nio.channels.SocketChannel;

import java.util.Map;
import java.util.Set;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class ClientNetworkPackageImpl extends EPackageImpl implements ClientNetworkPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clientNetworkEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semaphorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swarmSessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swarmInfoEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swarmContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackerSessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dwnldSessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass upldSessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clientSessionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clientInfoEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackerResponseEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ioPieceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass writeBufferEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass speedContainerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToSessionMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToClientSessionMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intToPieceEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviourEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType boolArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType byteBufferEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType channelEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType lockEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType urlEDataType = null;

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
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClientNetworkPackageImpl() {
		super(eNS_URI, ClientNetworkFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ClientNetworkPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClientNetworkPackage init() {
		if (isInited) return (ClientNetworkPackage)EPackage.Registry.INSTANCE.getEPackage(ClientNetworkPackage.eNS_URI);

		// Obtain or create and register package
		ClientNetworkPackageImpl theClientNetworkPackage = (ClientNetworkPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClientNetworkPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClientNetworkPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		NetworkConstantsPackageImpl theNetworkConstantsPackage = (NetworkConstantsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NetworkConstantsPackage.eNS_URI) instanceof NetworkConstantsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NetworkConstantsPackage.eNS_URI) : NetworkConstantsPackage.eINSTANCE);

		// Create package meta-data objects
		theClientNetworkPackage.createPackageContents();
		theNetworkConstantsPackage.createPackageContents();

		// Initialize created meta-data
		theClientNetworkPackage.initializePackageContents();
		theNetworkConstantsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClientNetworkPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClientNetworkPackage.eNS_URI, theClientNetworkPackage);
		return theClientNetworkPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClientNetwork() {
		return clientNetworkEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClientNetwork_SwarmMap() {
		return (EReference)clientNetworkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClientNetwork_Semaphor() {
		return (EReference)clientNetworkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientNetwork_ActiveSwarms() {
		return (EAttribute)clientNetworkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientNetwork_ForceSwarmsKeys() {
		return (EAttribute)clientNetworkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientNetwork_Sheduled() {
		return (EAttribute)clientNetworkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemaphor() {
		return semaphorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSemaphor_Connected() {
		return (EAttribute)semaphorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSemaphor_Opened() {
		return (EAttribute)semaphorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwarmSession() {
		return swarmSessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmSession_ProcessedPieces() {
		return (EReference)swarmSessionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmSession_UploadedPieces() {
		return (EReference)swarmSessionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmSession_PieceBuffer() {
		return (EReference)swarmSessionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmSession_SpeedContainer() {
		return (EReference)swarmSessionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmSession_ObtainingClients() {
		return (EAttribute)swarmSessionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmSession_TrackersManager() {
		return (EAttribute)swarmSessionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmSession_SearchersManager() {
		return (EAttribute)swarmSessionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmSession_Torrent() {
		return (EAttribute)swarmSessionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSession() {
		return sessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Announce() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Channel() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_State() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Sheduled() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_LastActivity() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Rating() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Timeout() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Duration() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSession_Clients() {
		return (EAttribute)sessionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwarmInfo() {
		return swarmInfoEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmInfo_DwnldStrategy() {
		return (EAttribute)swarmInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmInfo_Algorithm() {
		return (EAttribute)swarmInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmInfo_CreatedOnDisc() {
		return (EAttribute)swarmInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwarmInfo_UploadOnly() {
		return (EAttribute)swarmInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwarmContent() {
		return swarmContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmContent_Trackers() {
		return (EReference)swarmContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmContent_Downloads() {
		return (EReference)swarmContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwarmContent_Uploads() {
		return (EReference)swarmContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrackerSession() {
		return trackerSessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrackerSession_Response() {
		return (EReference)trackerSessionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerSession_NextConnection() {
		return (EAttribute)trackerSessionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerSession_Event() {
		return (EAttribute)trackerSessionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerSession_Scraped() {
		return (EAttribute)trackerSessionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDwnldSession() {
		return dwnldSessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUpldSession() {
		return upldSessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClientSession() {
		return clientSessionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientSession_Reserved() {
		return (EAttribute)clientSessionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientSession_CanceledBlocks() {
		return (EAttribute)clientSessionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientSession_ReceivedKeepAlive() {
		return (EAttribute)clientSessionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClientSession_SpeedContainer() {
		return (EReference)clientSessionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClientInfo() {
		return clientInfoEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_ClientName() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_Choking() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_HandshakeOK() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_Interested() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_Bitfield() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_Seed() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClientInfo_Have() {
		return (EAttribute)clientInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrackerResponse() {
		return trackerResponseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_FailureReason() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_Interval() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_MinInterval() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_TrackerId() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_Complete() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_Incomplete() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackerResponse_ResponseMessage() {
		return (EAttribute)trackerResponseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIOPiece() {
		return ioPieceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_PieceIndex() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Payload() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Finished() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Position() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Blocks() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_LockedBlocks() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_ExtTorrent() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_LastActivity() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Lock() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Color() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIOPiece_Hash() {
		return (EAttribute)ioPieceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWriteBuffer() {
		return writeBufferEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteBuffer_Offset() {
		return (EAttribute)writeBufferEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteBuffer_Size() {
		return (EAttribute)writeBufferEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWriteBuffer_Pieces() {
		return (EReference)writeBufferEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteBuffer_Sheduled() {
		return (EAttribute)writeBufferEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWriteBuffer_Lock() {
		return (EAttribute)writeBufferEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpeedContainer() {
		return speedContainerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedContainer_StartTime() {
		return (EAttribute)speedContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedContainer_DwnldSize() {
		return (EAttribute)speedContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedContainer_UpldSize() {
		return (EAttribute)speedContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedContainer_Speed() {
		return (EAttribute)speedContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToSessionMapEntry() {
		return stringToSessionMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToSessionMapEntry_Key() {
		return (EAttribute)stringToSessionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToSessionMapEntry_Value() {
		return (EReference)stringToSessionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToClientSessionMapEntry() {
		return stringToClientSessionMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToClientSessionMapEntry_Key() {
		return (EAttribute)stringToClientSessionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToClientSessionMapEntry_Value() {
		return (EReference)stringToClientSessionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntToPieceEntry() {
		return intToPieceEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntToPieceEntry_Key() {
		return (EAttribute)intToPieceEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntToPieceEntry_Value() {
		return (EReference)intToPieceEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehaviour() {
		return behaviourEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBehaviour_Algorithm() {
		return (EAttribute)behaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBehaviour_Strategy() {
		return (EAttribute)behaviourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBoolArray() {
		return boolArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getByteBuffer() {
		return byteBufferEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getChannel() {
		return channelEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLock() {
		return lockEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSet() {
		return setEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURL() {
		return urlEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetworkFactory getClientNetworkFactory() {
		return (ClientNetworkFactory)getEFactoryInstance();
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

		// Create classes and their features
		clientNetworkEClass = createEClass(CLIENT_NETWORK);
		createEReference(clientNetworkEClass, CLIENT_NETWORK__SWARM_MAP);
		createEReference(clientNetworkEClass, CLIENT_NETWORK__SEMAPHOR);
		createEAttribute(clientNetworkEClass, CLIENT_NETWORK__ACTIVE_SWARMS);
		createEAttribute(clientNetworkEClass, CLIENT_NETWORK__FORCE_SWARMS_KEYS);
		createEAttribute(clientNetworkEClass, CLIENT_NETWORK__SHEDULED);

		semaphorEClass = createEClass(SEMAPHOR);
		createEAttribute(semaphorEClass, SEMAPHOR__CONNECTED);
		createEAttribute(semaphorEClass, SEMAPHOR__OPENED);

		swarmSessionEClass = createEClass(SWARM_SESSION);
		createEReference(swarmSessionEClass, SWARM_SESSION__PROCESSED_PIECES);
		createEReference(swarmSessionEClass, SWARM_SESSION__UPLOADED_PIECES);
		createEReference(swarmSessionEClass, SWARM_SESSION__PIECE_BUFFER);
		createEReference(swarmSessionEClass, SWARM_SESSION__SPEED_CONTAINER);
		createEAttribute(swarmSessionEClass, SWARM_SESSION__OBTAINING_CLIENTS);
		createEAttribute(swarmSessionEClass, SWARM_SESSION__TRACKERS_MANAGER);
		createEAttribute(swarmSessionEClass, SWARM_SESSION__SEARCHERS_MANAGER);
		createEAttribute(swarmSessionEClass, SWARM_SESSION__TORRENT);

		sessionEClass = createEClass(SESSION);
		createEAttribute(sessionEClass, SESSION__ANNOUNCE);
		createEAttribute(sessionEClass, SESSION__CHANNEL);
		createEAttribute(sessionEClass, SESSION__STATE);
		createEAttribute(sessionEClass, SESSION__SHEDULED);
		createEAttribute(sessionEClass, SESSION__LAST_ACTIVITY);
		createEAttribute(sessionEClass, SESSION__RATING);
		createEAttribute(sessionEClass, SESSION__TIMEOUT);
		createEAttribute(sessionEClass, SESSION__DURATION);
		createEAttribute(sessionEClass, SESSION__CLIENTS);

		swarmInfoEClass = createEClass(SWARM_INFO);
		createEAttribute(swarmInfoEClass, SWARM_INFO__DWNLD_STRATEGY);
		createEAttribute(swarmInfoEClass, SWARM_INFO__ALGORITHM);
		createEAttribute(swarmInfoEClass, SWARM_INFO__CREATED_ON_DISC);
		createEAttribute(swarmInfoEClass, SWARM_INFO__UPLOAD_ONLY);

		swarmContentEClass = createEClass(SWARM_CONTENT);
		createEReference(swarmContentEClass, SWARM_CONTENT__TRACKERS);
		createEReference(swarmContentEClass, SWARM_CONTENT__DOWNLOADS);
		createEReference(swarmContentEClass, SWARM_CONTENT__UPLOADS);

		trackerSessionEClass = createEClass(TRACKER_SESSION);
		createEReference(trackerSessionEClass, TRACKER_SESSION__RESPONSE);
		createEAttribute(trackerSessionEClass, TRACKER_SESSION__NEXT_CONNECTION);
		createEAttribute(trackerSessionEClass, TRACKER_SESSION__EVENT);
		createEAttribute(trackerSessionEClass, TRACKER_SESSION__SCRAPED);

		dwnldSessionEClass = createEClass(DWNLD_SESSION);

		upldSessionEClass = createEClass(UPLD_SESSION);

		clientSessionEClass = createEClass(CLIENT_SESSION);
		createEAttribute(clientSessionEClass, CLIENT_SESSION__RESERVED);
		createEAttribute(clientSessionEClass, CLIENT_SESSION__CANCELED_BLOCKS);
		createEAttribute(clientSessionEClass, CLIENT_SESSION__RECEIVED_KEEP_ALIVE);
		createEReference(clientSessionEClass, CLIENT_SESSION__SPEED_CONTAINER);

		clientInfoEClass = createEClass(CLIENT_INFO);
		createEAttribute(clientInfoEClass, CLIENT_INFO__CLIENT_NAME);
		createEAttribute(clientInfoEClass, CLIENT_INFO__CHOKING);
		createEAttribute(clientInfoEClass, CLIENT_INFO__HANDSHAKE_OK);
		createEAttribute(clientInfoEClass, CLIENT_INFO__INTERESTED);
		createEAttribute(clientInfoEClass, CLIENT_INFO__BITFIELD);
		createEAttribute(clientInfoEClass, CLIENT_INFO__SEED);
		createEAttribute(clientInfoEClass, CLIENT_INFO__HAVE);

		trackerResponseEClass = createEClass(TRACKER_RESPONSE);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__FAILURE_REASON);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__INTERVAL);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__MIN_INTERVAL);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__TRACKER_ID);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__COMPLETE);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__INCOMPLETE);
		createEAttribute(trackerResponseEClass, TRACKER_RESPONSE__RESPONSE_MESSAGE);

		ioPieceEClass = createEClass(IO_PIECE);
		createEAttribute(ioPieceEClass, IO_PIECE__PIECE_INDEX);
		createEAttribute(ioPieceEClass, IO_PIECE__PAYLOAD);
		createEAttribute(ioPieceEClass, IO_PIECE__FINISHED);
		createEAttribute(ioPieceEClass, IO_PIECE__POSITION);
		createEAttribute(ioPieceEClass, IO_PIECE__BLOCKS);
		createEAttribute(ioPieceEClass, IO_PIECE__LOCKED_BLOCKS);
		createEAttribute(ioPieceEClass, IO_PIECE__EXT_TORRENT);
		createEAttribute(ioPieceEClass, IO_PIECE__LAST_ACTIVITY);
		createEAttribute(ioPieceEClass, IO_PIECE__LOCK);
		createEAttribute(ioPieceEClass, IO_PIECE__COLOR);
		createEAttribute(ioPieceEClass, IO_PIECE__HASH);

		writeBufferEClass = createEClass(WRITE_BUFFER);
		createEAttribute(writeBufferEClass, WRITE_BUFFER__OFFSET);
		createEAttribute(writeBufferEClass, WRITE_BUFFER__SIZE);
		createEReference(writeBufferEClass, WRITE_BUFFER__PIECES);
		createEAttribute(writeBufferEClass, WRITE_BUFFER__SHEDULED);
		createEAttribute(writeBufferEClass, WRITE_BUFFER__LOCK);

		speedContainerEClass = createEClass(SPEED_CONTAINER);
		createEAttribute(speedContainerEClass, SPEED_CONTAINER__START_TIME);
		createEAttribute(speedContainerEClass, SPEED_CONTAINER__DWNLD_SIZE);
		createEAttribute(speedContainerEClass, SPEED_CONTAINER__UPLD_SIZE);
		createEAttribute(speedContainerEClass, SPEED_CONTAINER__SPEED);

		stringToSessionMapEntryEClass = createEClass(STRING_TO_SESSION_MAP_ENTRY);
		createEAttribute(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__KEY);
		createEReference(stringToSessionMapEntryEClass, STRING_TO_SESSION_MAP_ENTRY__VALUE);

		stringToClientSessionMapEntryEClass = createEClass(STRING_TO_CLIENT_SESSION_MAP_ENTRY);
		createEAttribute(stringToClientSessionMapEntryEClass, STRING_TO_CLIENT_SESSION_MAP_ENTRY__KEY);
		createEReference(stringToClientSessionMapEntryEClass, STRING_TO_CLIENT_SESSION_MAP_ENTRY__VALUE);

		intToPieceEntryEClass = createEClass(INT_TO_PIECE_ENTRY);
		createEAttribute(intToPieceEntryEClass, INT_TO_PIECE_ENTRY__KEY);
		createEReference(intToPieceEntryEClass, INT_TO_PIECE_ENTRY__VALUE);

		behaviourEClass = createEClass(BEHAVIOUR);
		createEAttribute(behaviourEClass, BEHAVIOUR__ALGORITHM);
		createEAttribute(behaviourEClass, BEHAVIOUR__STRATEGY);

		// Create data types
		boolArrayEDataType = createEDataType(BOOL_ARRAY);
		byteBufferEDataType = createEDataType(BYTE_BUFFER);
		channelEDataType = createEDataType(CHANNEL);
		lockEDataType = createEDataType(LOCK);
		setEDataType = createEDataType(SET);
		urlEDataType = createEDataType(URL);
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

		// Obtain other dependent packages
		NetworkConstantsPackage theNetworkConstantsPackage = (NetworkConstantsPackage)EPackage.Registry.INSTANCE.getEPackage(NetworkConstantsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theNetworkConstantsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		clientNetworkEClass.getESuperTypes().add(this.getBehaviour());
		swarmSessionEClass.getESuperTypes().add(this.getSession());
		swarmSessionEClass.getESuperTypes().add(this.getSwarmInfo());
		swarmSessionEClass.getESuperTypes().add(this.getSwarmContent());
		trackerSessionEClass.getESuperTypes().add(this.getSession());
		dwnldSessionEClass.getESuperTypes().add(this.getClientSession());
		upldSessionEClass.getESuperTypes().add(this.getClientSession());
		clientSessionEClass.getESuperTypes().add(this.getSession());
		clientSessionEClass.getESuperTypes().add(this.getClientInfo());

		// Initialize classes and features; add operations and parameters
		initEClass(clientNetworkEClass, ClientNetwork.class, "ClientNetwork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClientNetwork_SwarmMap(), this.getStringToSessionMapEntry(), null, "swarmMap", null, 0, -1, ClientNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClientNetwork_Semaphor(), this.getSemaphor(), null, "semaphor", null, 0, 1, ClientNetwork.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientNetwork_ActiveSwarms(), ecorePackage.getEString(), "activeSwarms", "0", 0, -1, ClientNetwork.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientNetwork_ForceSwarmsKeys(), ecorePackage.getEString(), "forceSwarmsKeys", null, 0, -1, ClientNetwork.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientNetwork_Sheduled(), ecorePackage.getEBoolean(), "sheduled", "false", 0, 1, ClientNetwork.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(semaphorEClass, Semaphor.class, "Semaphor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSemaphor_Connected(), ecorePackage.getEBoolean(), "connected", null, 0, 1, Semaphor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSemaphor_Opened(), ecorePackage.getEBoolean(), "opened", null, 0, 1, Semaphor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swarmSessionEClass, SwarmSession.class, "SwarmSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwarmSession_ProcessedPieces(), this.getIntToPieceEntry(), null, "processedPieces", null, 0, -1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwarmSession_UploadedPieces(), this.getIOPiece(), null, "uploadedPieces", null, 0, -1, SwarmSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwarmSession_PieceBuffer(), this.getWriteBuffer(), null, "pieceBuffer", null, 1, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwarmSession_SpeedContainer(), this.getSpeedContainer(), null, "speedContainer", null, 0, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmSession_ObtainingClients(), ecorePackage.getEBoolean(), "obtainingClients", "false", 0, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmSession_TrackersManager(), ecorePackage.getEJavaObject(), "trackersManager", null, 1, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmSession_SearchersManager(), ecorePackage.getEJavaObject(), "searchersManager", null, 1, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmSession_Torrent(), ecorePackage.getEJavaObject(), "torrent", null, 0, 1, SwarmSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sessionEClass, Session.class, "Session", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSession_Announce(), ecorePackage.getEString(), "announce", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Channel(), this.getChannel(), "channel", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_State(), theNetworkConstantsPackage.getEViewsMessages(), "state", "Ready", 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Sheduled(), ecorePackage.getEBoolean(), "sheduled", "false", 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_LastActivity(), ecorePackage.getELong(), "lastActivity", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Rating(), ecorePackage.getEInt(), "rating", "0", 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Timeout(), ecorePackage.getELong(), "timeout", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Duration(), ecorePackage.getELong(), "duration", null, 0, 1, Session.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSession_Clients(), this.getSet(), "clients", null, 0, 1, Session.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swarmInfoEClass, SwarmInfo.class, "SwarmInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwarmInfo_DwnldStrategy(), ecorePackage.getEInt(), "dwnldStrategy", null, 0, 1, SwarmInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmInfo_Algorithm(), ecorePackage.getEInt(), "algorithm", "0", 0, 1, SwarmInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmInfo_CreatedOnDisc(), ecorePackage.getEBoolean(), "createdOnDisc", null, 0, 1, SwarmInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwarmInfo_UploadOnly(), ecorePackage.getEBoolean(), "uploadOnly", "false", 0, 1, SwarmInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swarmContentEClass, SwarmContent.class, "SwarmContent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwarmContent_Trackers(), this.getStringToSessionMapEntry(), null, "trackers", null, 0, -1, SwarmContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwarmContent_Downloads(), this.getStringToClientSessionMapEntry(), null, "downloads", null, 0, -1, SwarmContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwarmContent_Uploads(), this.getStringToClientSessionMapEntry(), null, "uploads", null, 0, -1, SwarmContent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackerSessionEClass, TrackerSession.class, "TrackerSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrackerSession_Response(), this.getTrackerResponse(), null, "response", null, 0, 1, TrackerSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerSession_NextConnection(), ecorePackage.getELong(), "nextConnection", null, 0, 1, TrackerSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerSession_Event(), theNetworkConstantsPackage.getETrackerRequestEvent(), "event", null, 0, 1, TrackerSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerSession_Scraped(), ecorePackage.getEBoolean(), "scraped", "false", 0, 1, TrackerSession.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dwnldSessionEClass, DwnldSession.class, "DwnldSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(upldSessionEClass, UpldSession.class, "UpldSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clientSessionEClass, ClientSession.class, "ClientSession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClientSession_Reserved(), ecorePackage.getEByteArray(), "reserved", null, 0, 1, ClientSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientSession_CanceledBlocks(), ecorePackage.getEInt(), "canceledBlocks", null, 0, -1, ClientSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientSession_ReceivedKeepAlive(), ecorePackage.getEBoolean(), "receivedKeepAlive", "false", 0, 1, ClientSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClientSession_SpeedContainer(), this.getSpeedContainer(), null, "speedContainer", null, 0, 1, ClientSession.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(clientInfoEClass, ClientInfo.class, "ClientInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClientInfo_ClientName(), ecorePackage.getEString(), "clientName", null, 0, 1, ClientInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_Choking(), ecorePackage.getEBoolean(), "choking", null, 0, 1, ClientInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_HandshakeOK(), ecorePackage.getEBoolean(), "handshakeOK", null, 0, 1, ClientInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_Interested(), ecorePackage.getEBoolean(), "interested", "false", 0, 1, ClientInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_Bitfield(), ecorePackage.getEByteArray(), "bitfield", null, 0, 1, ClientInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_Seed(), ecorePackage.getEBoolean(), "seed", null, 0, 1, ClientInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClientInfo_Have(), ecorePackage.getEInt(), "have", null, 0, -1, ClientInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackerResponseEClass, TrackerResponse.class, "TrackerResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTrackerResponse_FailureReason(), ecorePackage.getEString(), "failureReason", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_Interval(), ecorePackage.getELong(), "interval", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_MinInterval(), ecorePackage.getELong(), "minInterval", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_TrackerId(), ecorePackage.getEString(), "trackerId", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_Complete(), ecorePackage.getEInt(), "complete", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_Incomplete(), ecorePackage.getEInt(), "incomplete", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackerResponse_ResponseMessage(), ecorePackage.getEString(), "responseMessage", null, 0, 1, TrackerResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ioPieceEClass, IOPiece.class, "IOPiece", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIOPiece_PieceIndex(), ecorePackage.getEInt(), "pieceIndex", null, 0, 1, IOPiece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Payload(), this.getByteBuffer(), "payload", null, 0, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Finished(), ecorePackage.getEBoolean(), "finished", "false", 0, 1, IOPiece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Position(), ecorePackage.getEInt(), "position", null, 0, 1, IOPiece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Blocks(), this.getBoolArray(), "blocks", null, 0, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_LockedBlocks(), this.getBoolArray(), "lockedBlocks", null, 0, 1, IOPiece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_ExtTorrent(), ecorePackage.getEJavaObject(), "extTorrent", null, 0, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_LastActivity(), ecorePackage.getELong(), "lastActivity", null, 0, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Lock(), this.getLock(), "lock", null, 1, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Color(), ecorePackage.getEInt(), "color", null, 0, 1, IOPiece.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIOPiece_Hash(), ecorePackage.getEByteArray(), "hash", null, 0, 1, IOPiece.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(writeBufferEClass, WriteBuffer.class, "WriteBuffer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWriteBuffer_Offset(), ecorePackage.getELong(), "offset", null, 0, 1, WriteBuffer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWriteBuffer_Size(), ecorePackage.getELong(), "size", null, 0, 1, WriteBuffer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWriteBuffer_Pieces(), this.getIOPiece(), null, "pieces", null, 0, -1, WriteBuffer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWriteBuffer_Sheduled(), ecorePackage.getEBoolean(), "sheduled", "false", 0, 1, WriteBuffer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWriteBuffer_Lock(), this.getLock(), "lock", null, 1, 1, WriteBuffer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(speedContainerEClass, SpeedContainer.class, "SpeedContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpeedContainer_StartTime(), ecorePackage.getELong(), "startTime", null, 0, 1, SpeedContainer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpeedContainer_DwnldSize(), ecorePackage.getELong(), "dwnldSize", null, 0, 1, SpeedContainer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpeedContainer_UpldSize(), ecorePackage.getELong(), "upldSize", null, 0, 1, SpeedContainer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpeedContainer_Speed(), ecorePackage.getEFloat(), "speed", null, 0, 1, SpeedContainer.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToSessionMapEntryEClass, Map.Entry.class, "StringToSessionMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToSessionMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToSessionMapEntry_Value(), this.getSession(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToClientSessionMapEntryEClass, Map.Entry.class, "StringToClientSessionMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToClientSessionMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToClientSessionMapEntry_Value(), this.getClientSession(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intToPieceEntryEClass, Map.Entry.class, "IntToPieceEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntToPieceEntry_Key(), ecorePackage.getEIntegerObject(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntToPieceEntry_Value(), this.getIOPiece(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behaviourEClass, Behaviour.class, "Behaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBehaviour_Algorithm(), theNetworkConstantsPackage.getEStrategy(), "algorithm", "Random", 0, 1, Behaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBehaviour_Strategy(), theNetworkConstantsPackage.getEAlgorithm(), "strategy", "Queuenning", 0, 1, Behaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(boolArrayEDataType, boolean[].class, "BoolArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(byteBufferEDataType, ByteBuffer.class, "ByteBuffer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(channelEDataType, SocketChannel.class, "Channel", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(lockEDataType, ReentrantReadWriteLock.class, "Lock", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(setEDataType, Set.class, "Set", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(urlEDataType, java.net.URL.class, "URL", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // ClientNetworkPackageImpl
