/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Client Session</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getReserved <em>Reserved</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getCanceledBlocks <em>Canceled Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#isReceivedKeepAlive <em>Received Keep Alive</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getSpeedContainer <em>Speed Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientSession()
 * @model
 * @generated
 */
public interface ClientSession extends Session, ClientInfo {
	/**
	 * Returns the value of the '<em><b>Reserved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reserved</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reserved</em>' attribute.
	 * @see #setReserved(byte[])
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientSession_Reserved()
	 * @model transient="true"
	 * @generated
	 */
	byte[] getReserved();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getReserved <em>Reserved</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reserved</em>' attribute.
	 * @see #getReserved()
	 * @generated
	 */
	void setReserved(byte[] value);

	/**
	 * Returns the value of the '<em><b>Canceled Blocks</b></em>' attribute list. The list contents are of type {@link java.lang.Integer}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Canceled Blocks</em>' attribute list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Canceled Blocks</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientSession_CanceledBlocks()
	 * @model transient="true"
	 * @generated
	 */
	EList<Integer> getCanceledBlocks();

	/**
	 * Returns the value of the '<em><b>Received Keep Alive</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Received Keep Alive</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Received Keep Alive</em>' attribute.
	 * @see #setReceivedKeepAlive(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientSession_ReceivedKeepAlive()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isReceivedKeepAlive();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#isReceivedKeepAlive <em>Received Keep Alive</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Received Keep Alive</em>' attribute.
	 * @see #isReceivedKeepAlive()
	 * @generated
	 */
	void setReceivedKeepAlive(boolean value);

	/**
	 * Returns the value of the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed Container</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed Container</em>' containment reference.
	 * @see #setSpeedContainer(SpeedContainer)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientSession_SpeedContainer()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	SpeedContainer getSpeedContainer();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession#getSpeedContainer <em>Speed Container</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Speed Container</em>' containment reference.
	 * @see #getSpeedContainer()
	 * @generated
	 */
	void setSpeedContainer(SpeedContainer value);

} // ClientSession
