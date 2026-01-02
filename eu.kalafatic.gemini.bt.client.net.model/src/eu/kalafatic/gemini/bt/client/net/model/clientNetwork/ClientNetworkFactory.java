/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the model. <!-- end-user-doc
 * -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage
 * @generated
 */
public interface ClientNetworkFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	ClientNetworkFactory eINSTANCE = eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Client Network</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Client Network</em>'.
	 * @generated
	 */
	ClientNetwork createClientNetwork();

	/**
	 * Returns a new object of class '<em>Semaphor</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Semaphor</em>'.
	 * @generated
	 */
	Semaphor createSemaphor();

	/**
	 * Returns a new object of class '<em>Swarm Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Swarm Session</em>'.
	 * @generated
	 */
	SwarmSession createSwarmSession();

	/**
	 * Returns a new object of class '<em>Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Session</em>'.
	 * @generated
	 */
	Session createSession();

	/**
	 * Returns a new object of class '<em>Swarm Info</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Swarm Info</em>'.
	 * @generated
	 */
	SwarmInfo createSwarmInfo();

	/**
	 * Returns a new object of class '<em>Swarm Content</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Swarm Content</em>'.
	 * @generated
	 */
	SwarmContent createSwarmContent();

	/**
	 * Returns a new object of class '<em>Tracker Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracker Session</em>'.
	 * @generated
	 */
	TrackerSession createTrackerSession();

	/**
	 * Returns a new object of class '<em>Dwnld Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Dwnld Session</em>'.
	 * @generated
	 */
	DwnldSession createDwnldSession();

	/**
	 * Returns a new object of class '<em>Upld Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Upld Session</em>'.
	 * @generated
	 */
	UpldSession createUpldSession();

	/**
	 * Returns a new object of class '<em>Client Session</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Client Session</em>'.
	 * @generated
	 */
	ClientSession createClientSession();

	/**
	 * Returns a new object of class '<em>Client Info</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Client Info</em>'.
	 * @generated
	 */
	ClientInfo createClientInfo();

	/**
	 * Returns a new object of class '<em>Tracker Response</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracker Response</em>'.
	 * @generated
	 */
	TrackerResponse createTrackerResponse();

	/**
	 * Returns a new object of class '<em>IO Piece</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>IO Piece</em>'.
	 * @generated
	 */
	IOPiece createIOPiece();

	/**
	 * Returns a new object of class '<em>Write Buffer</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Write Buffer</em>'.
	 * @generated
	 */
	WriteBuffer createWriteBuffer();

	/**
	 * Returns a new object of class '<em>Speed Container</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Speed Container</em>'.
	 * @generated
	 */
	SpeedContainer createSpeedContainer();

	/**
	 * Returns a new object of class '<em>Behaviour</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Behaviour</em>'.
	 * @generated
	 */
	Behaviour createBehaviour();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ClientNetworkPackage getClientNetworkPackage();

} // ClientNetworkFactory
