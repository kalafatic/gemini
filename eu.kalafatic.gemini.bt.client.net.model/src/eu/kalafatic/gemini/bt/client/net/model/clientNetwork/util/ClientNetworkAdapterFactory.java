/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.util;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage
 * @generated
 */
public class ClientNetworkAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClientNetworkPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetworkAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ClientNetworkPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the model. <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ClientNetworkSwitch<Adapter> modelSwitch = new ClientNetworkSwitch<Adapter>() {
			@Override
			public Adapter caseClientNetwork(ClientNetwork object) {
				return createClientNetworkAdapter();
			}
			@Override
			public Adapter caseSemaphor(Semaphor object) {
				return createSemaphorAdapter();
			}
			@Override
			public Adapter caseSwarmSession(SwarmSession object) {
				return createSwarmSessionAdapter();
			}
			@Override
			public Adapter caseSession(Session object) {
				return createSessionAdapter();
			}
			@Override
			public Adapter caseSwarmInfo(SwarmInfo object) {
				return createSwarmInfoAdapter();
			}
			@Override
			public Adapter caseSwarmContent(SwarmContent object) {
				return createSwarmContentAdapter();
			}
			@Override
			public Adapter caseTrackerSession(TrackerSession object) {
				return createTrackerSessionAdapter();
			}
			@Override
			public Adapter caseDwnldSession(DwnldSession object) {
				return createDwnldSessionAdapter();
			}
			@Override
			public Adapter caseUpldSession(UpldSession object) {
				return createUpldSessionAdapter();
			}
			@Override
			public Adapter caseClientSession(ClientSession object) {
				return createClientSessionAdapter();
			}
			@Override
			public Adapter caseClientInfo(ClientInfo object) {
				return createClientInfoAdapter();
			}
			@Override
			public Adapter caseTrackerResponse(TrackerResponse object) {
				return createTrackerResponseAdapter();
			}
			@Override
			public Adapter caseIOPiece(IOPiece object) {
				return createIOPieceAdapter();
			}
			@Override
			public Adapter caseWriteBuffer(WriteBuffer object) {
				return createWriteBufferAdapter();
			}
			@Override
			public Adapter caseSpeedContainer(SpeedContainer object) {
				return createSpeedContainerAdapter();
			}
			@Override
			public Adapter caseStringToSessionMapEntry(Map.Entry<String, Session> object) {
				return createStringToSessionMapEntryAdapter();
			}
			@Override
			public Adapter caseStringToClientSessionMapEntry(Map.Entry<String, ClientSession> object) {
				return createStringToClientSessionMapEntryAdapter();
			}
			@Override
			public Adapter caseIntToPieceEntry(Map.Entry<Integer, IOPiece> object) {
				return createIntToPieceEntryAdapter();
			}
			@Override
			public Adapter caseBehaviour(Behaviour object) {
				return createBehaviourAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork <em>Client Network</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork
	 * @generated
	 */
	public Adapter createClientNetworkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor <em>Semaphor</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor
	 * @generated
	 */
	public Adapter createSemaphorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession <em>Swarm Session</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession
	 * @generated
	 */
	public Adapter createSwarmSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session <em>Session</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session
	 * @generated
	 */
	public Adapter createSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo <em>Swarm Info</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo
	 * @generated
	 */
	public Adapter createSwarmInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent <em>Swarm Content</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent
	 * @generated
	 */
	public Adapter createSwarmContentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession <em>Tracker Session</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession
	 * @generated
	 */
	public Adapter createTrackerSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession <em>Dwnld Session</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession
	 * @generated
	 */
	public Adapter createDwnldSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession <em>Upld Session</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession
	 * @generated
	 */
	public Adapter createUpldSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession <em>Client Session</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession
	 * @generated
	 */
	public Adapter createClientSessionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo <em>Client Info</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo
	 * @generated
	 */
	public Adapter createClientInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse <em>Tracker Response</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse
	 * @generated
	 */
	public Adapter createTrackerResponseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece <em>IO Piece</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece
	 * @generated
	 */
	public Adapter createIOPieceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer <em>Write Buffer</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer
	 * @generated
	 */
	public Adapter createWriteBufferAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer <em>Speed Container</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer
	 * @generated
	 */
	public Adapter createSpeedContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Session Map Entry</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToSessionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Client Session Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToClientSessionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Int To Piece Entry</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createIntToPieceEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour <em>Behaviour</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour
	 * @generated
	 */
	public Adapter createBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ClientNetworkAdapterFactory
