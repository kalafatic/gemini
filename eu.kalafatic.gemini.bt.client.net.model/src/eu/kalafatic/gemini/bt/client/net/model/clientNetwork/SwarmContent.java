/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Swarm Content</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getTrackers <em>Trackers</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getDownloads <em>Downloads</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent#getUploads <em>Uploads</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmContent()
 * @model
 * @generated
 */
public interface SwarmContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Trackers</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trackers</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trackers</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmContent_Trackers()
	 * @model mapType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.StringToSessionMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session>"
	 * @generated
	 */
	EMap<String, Session> getTrackers();

	/**
	 * Returns the value of the '<em><b>Downloads</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downloads</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downloads</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmContent_Downloads()
	 * @model mapType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.StringToClientSessionMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession>"
	 * @generated
	 */
	EMap<String, ClientSession> getDownloads();

	/**
	 * Returns the value of the '<em><b>Uploads</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uploads</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uploads</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmContent_Uploads()
	 * @model mapType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.StringToClientSessionMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession>" transient="true"
	 * @generated
	 */
	EMap<String, ClientSession> getUploads();

} // SwarmContent
