/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Client Network</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSwarmMap <em>Swarm Map</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSemaphor <em>Semaphor</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getActiveSwarms <em>Active Swarms</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getForceSwarmsKeys <em>Force Swarms Keys</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#isSheduled <em>Sheduled</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork()
 * @model
 * @generated
 */
public interface ClientNetwork extends Behaviour {
	/**
	 * Returns the value of the '<em><b>Swarm Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swarm Map</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swarm Map</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork_SwarmMap()
	 * @model mapType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.StringToSessionMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session>"
	 * @generated
	 */
	EMap<String, Session> getSwarmMap();

	/**
	 * Returns the value of the '<em><b>Semaphor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semaphor</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semaphor</em>' containment reference.
	 * @see #setSemaphor(Semaphor)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork_Semaphor()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	Semaphor getSemaphor();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#getSemaphor <em>Semaphor</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Semaphor</em>' containment reference.
	 * @see #getSemaphor()
	 * @generated
	 */
	void setSemaphor(Semaphor value);

	/**
	 * Returns the value of the '<em><b>Active Swarms</b></em>' attribute list. The list contents are of type {@link java.lang.String}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Swarms</em>' attribute list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Swarms</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork_ActiveSwarms()
	 * @model default="0" transient="true"
	 * @generated
	 */
	EList<String> getActiveSwarms();

	/**
	 * Returns the value of the '<em><b>Force Swarms Keys</b></em>' attribute list. The list contents are of type {@link java.lang.String}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Force Swarms Keys</em>' attribute list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Force Swarms Keys</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork_ForceSwarmsKeys()
	 * @model transient="true"
	 * @generated
	 */
	EList<String> getForceSwarmsKeys();

	/**
	 * Returns the value of the '<em><b>Sheduled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sheduled</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheduled</em>' attribute.
	 * @see #setSheduled(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getClientNetwork_Sheduled()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isSheduled();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork#isSheduled <em>Sheduled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheduled</em>' attribute.
	 * @see #isSheduled()
	 * @generated
	 */
	void setSheduled(boolean value);

} // ClientNetwork
