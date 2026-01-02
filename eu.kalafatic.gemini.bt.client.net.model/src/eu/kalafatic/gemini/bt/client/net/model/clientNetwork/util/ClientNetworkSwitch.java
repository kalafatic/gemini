/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.util;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.*;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage
 * @generated
 */
public class ClientNetworkSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClientNetworkPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClientNetworkSwitch() {
		if (modelPackage == null) {
			modelPackage = ClientNetworkPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ClientNetworkPackage.CLIENT_NETWORK: {
				ClientNetwork clientNetwork = (ClientNetwork)theEObject;
				T result = caseClientNetwork(clientNetwork);
				if (result == null) result = caseBehaviour(clientNetwork);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SEMAPHOR: {
				Semaphor semaphor = (Semaphor)theEObject;
				T result = caseSemaphor(semaphor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SWARM_SESSION: {
				SwarmSession swarmSession = (SwarmSession)theEObject;
				T result = caseSwarmSession(swarmSession);
				if (result == null) result = caseSession(swarmSession);
				if (result == null) result = caseSwarmInfo(swarmSession);
				if (result == null) result = caseSwarmContent(swarmSession);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SESSION: {
				Session session = (Session)theEObject;
				T result = caseSession(session);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SWARM_INFO: {
				SwarmInfo swarmInfo = (SwarmInfo)theEObject;
				T result = caseSwarmInfo(swarmInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SWARM_CONTENT: {
				SwarmContent swarmContent = (SwarmContent)theEObject;
				T result = caseSwarmContent(swarmContent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.TRACKER_SESSION: {
				TrackerSession trackerSession = (TrackerSession)theEObject;
				T result = caseTrackerSession(trackerSession);
				if (result == null) result = caseSession(trackerSession);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.DWNLD_SESSION: {
				DwnldSession dwnldSession = (DwnldSession)theEObject;
				T result = caseDwnldSession(dwnldSession);
				if (result == null) result = caseClientSession(dwnldSession);
				if (result == null) result = caseSession(dwnldSession);
				if (result == null) result = caseClientInfo(dwnldSession);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.UPLD_SESSION: {
				UpldSession upldSession = (UpldSession)theEObject;
				T result = caseUpldSession(upldSession);
				if (result == null) result = caseClientSession(upldSession);
				if (result == null) result = caseSession(upldSession);
				if (result == null) result = caseClientInfo(upldSession);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.CLIENT_SESSION: {
				ClientSession clientSession = (ClientSession)theEObject;
				T result = caseClientSession(clientSession);
				if (result == null) result = caseSession(clientSession);
				if (result == null) result = caseClientInfo(clientSession);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.CLIENT_INFO: {
				ClientInfo clientInfo = (ClientInfo)theEObject;
				T result = caseClientInfo(clientInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.TRACKER_RESPONSE: {
				TrackerResponse trackerResponse = (TrackerResponse)theEObject;
				T result = caseTrackerResponse(trackerResponse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.IO_PIECE: {
				IOPiece ioPiece = (IOPiece)theEObject;
				T result = caseIOPiece(ioPiece);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.WRITE_BUFFER: {
				WriteBuffer writeBuffer = (WriteBuffer)theEObject;
				T result = caseWriteBuffer(writeBuffer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.SPEED_CONTAINER: {
				SpeedContainer speedContainer = (SpeedContainer)theEObject;
				T result = caseSpeedContainer(speedContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.STRING_TO_SESSION_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Session> stringToSessionMapEntry = (Map.Entry<String, Session>)theEObject;
				T result = caseStringToSessionMapEntry(stringToSessionMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.STRING_TO_CLIENT_SESSION_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, ClientSession> stringToClientSessionMapEntry = (Map.Entry<String, ClientSession>)theEObject;
				T result = caseStringToClientSessionMapEntry(stringToClientSessionMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.INT_TO_PIECE_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<Integer, IOPiece> intToPieceEntry = (Map.Entry<Integer, IOPiece>)theEObject;
				T result = caseIntToPieceEntry(intToPieceEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClientNetworkPackage.BEHAVIOUR: {
				Behaviour behaviour = (Behaviour)theEObject;
				T result = caseBehaviour(behaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Client Network</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Client Network</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClientNetwork(ClientNetwork object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semaphor</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semaphor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSemaphor(Semaphor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Swarm Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Swarm Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSwarmSession(SwarmSession object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSession(Session object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Swarm Info</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Swarm Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSwarmInfo(SwarmInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Swarm Content</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Swarm Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSwarmContent(SwarmContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracker Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracker Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrackerSession(TrackerSession object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dwnld Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dwnld Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDwnldSession(DwnldSession object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Upld Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Upld Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUpldSession(UpldSession object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Client Session</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Client Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClientSession(ClientSession object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Client Info</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Client Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClientInfo(ClientInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracker Response</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracker Response</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrackerResponse(TrackerResponse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IO Piece</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IO Piece</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIOPiece(IOPiece object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Write Buffer</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Write Buffer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWriteBuffer(WriteBuffer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Speed Container</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Speed Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpeedContainer(SpeedContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Session Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Session Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToSessionMapEntry(Map.Entry<String, Session> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Client Session Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Client Session Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToClientSessionMapEntry(Map.Entry<String, ClientSession> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int To Piece Entry</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int To Piece Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntToPieceEntry(Map.Entry<Integer, IOPiece> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behaviour</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviour(Behaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // ClientNetworkSwitch
