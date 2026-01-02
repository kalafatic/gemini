/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Client Info</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getClientName <em>Client Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isChoking <em>Choking</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isHandshakeOK <em>Handshake OK</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isInterested <em>Interested</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getBitfield <em>Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isSeed <em>Seed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getHave <em>Have</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo()
 * @model
 * @generated
 */
public interface ClientInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Client Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client Name</em>' attribute.
	 * @see #setClientName(String)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_ClientName()
	 * @model
	 * @generated
	 */
	String getClientName();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getClientName <em>Client Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client Name</em>' attribute.
	 * @see #getClientName()
	 * @generated
	 */
	void setClientName(String value);

	/**
	 * Returns the value of the '<em><b>Choking</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choking</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choking</em>' attribute.
	 * @see #setChoking(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_Choking()
	 * @model
	 * @generated
	 */
	boolean isChoking();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isChoking <em>Choking</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Choking</em>' attribute.
	 * @see #isChoking()
	 * @generated
	 */
	void setChoking(boolean value);

	/**
	 * Returns the value of the '<em><b>Handshake OK</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handshake OK</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handshake OK</em>' attribute.
	 * @see #setHandshakeOK(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_HandshakeOK()
	 * @model
	 * @generated
	 */
	boolean isHandshakeOK();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isHandshakeOK <em>Handshake OK</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handshake OK</em>' attribute.
	 * @see #isHandshakeOK()
	 * @generated
	 */
	void setHandshakeOK(boolean value);

	/**
	 * Returns the value of the '<em><b>Interested</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interested</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interested</em>' attribute.
	 * @see #setInterested(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_Interested()
	 * @model default="false"
	 * @generated
	 */
	boolean isInterested();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isInterested <em>Interested</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interested</em>' attribute.
	 * @see #isInterested()
	 * @generated
	 */
	void setInterested(boolean value);

	/**
	 * Returns the value of the '<em><b>Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bitfield</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bitfield</em>' attribute.
	 * @see #setBitfield(byte[])
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_Bitfield()
	 * @model transient="true"
	 * @generated
	 */
	byte[] getBitfield();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#getBitfield <em>Bitfield</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bitfield</em>' attribute.
	 * @see #getBitfield()
	 * @generated
	 */
	void setBitfield(byte[] value);

	/**
	 * Returns the value of the '<em><b>Seed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seed</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seed</em>' attribute.
	 * @see #setSeed(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_Seed()
	 * @model
	 * @generated
	 */
	boolean isSeed();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo#isSeed <em>Seed</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seed</em>' attribute.
	 * @see #isSeed()
	 * @generated
	 */
	void setSeed(boolean value);

	/**
	 * Returns the value of the '<em><b>Have</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Have</em>' attribute list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Have</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientInfo_Have()
	 * @model transient="true"
	 * @generated
	 */
	EList<Integer> getHave();

} // ClientInfo
