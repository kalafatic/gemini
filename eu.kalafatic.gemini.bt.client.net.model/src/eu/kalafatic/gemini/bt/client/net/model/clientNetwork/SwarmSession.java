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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Swarm Session</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getProcessedPieces <em>Processed Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getUploadedPieces <em>Uploaded Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getPieceBuffer <em>Piece Buffer</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSpeedContainer <em>Speed Container</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#isObtainingClients <em>Obtaining Clients</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTrackersManager <em>Trackers Manager</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSearchersManager <em>Searchers Manager</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTorrent <em>Torrent</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession()
 * @model
 * @generated
 */
public interface SwarmSession extends Session, SwarmInfo, SwarmContent {
	/**
	 * Returns the value of the '<em><b>Processed Pieces</b></em>' map.
	 * The key is of type {@link java.lang.Integer},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processed Pieces</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processed Pieces</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_ProcessedPieces()
	 * @model mapType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IntToPieceEntry<org.eclipse.emf.ecore.EIntegerObject, eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece>" transient="true"
	 * @generated
	 */
	EMap<Integer, IOPiece> getProcessedPieces();

	/**
	 * Returns the value of the '<em><b>Uploaded Pieces</b></em>' containment reference list.
	 * The list contents are of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uploaded Pieces</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uploaded Pieces</em>' containment reference list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_UploadedPieces()
	 * @model containment="true"
	 * @generated
	 */
	EList<IOPiece> getUploadedPieces();

	/**
	 * Returns the value of the '<em><b>Piece Buffer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Piece Buffer</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Piece Buffer</em>' containment reference.
	 * @see #setPieceBuffer(WriteBuffer)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_PieceBuffer()
	 * @model containment="true" required="true" transient="true"
	 * @generated
	 */
	WriteBuffer getPieceBuffer();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getPieceBuffer <em>Piece Buffer</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Piece Buffer</em>' containment reference.
	 * @see #getPieceBuffer()
	 * @generated
	 */
	void setPieceBuffer(WriteBuffer value);

	/**
	 * Returns the value of the '<em><b>Speed Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed Container</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed Container</em>' containment reference.
	 * @see #setSpeedContainer(SpeedContainer)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_SpeedContainer()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	SpeedContainer getSpeedContainer();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSpeedContainer <em>Speed Container</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Speed Container</em>' containment reference.
	 * @see #getSpeedContainer()
	 * @generated
	 */
	void setSpeedContainer(SpeedContainer value);

	/**
	 * Returns the value of the '<em><b>Obtaining Clients</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Obtaining Clients</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Obtaining Clients</em>' attribute.
	 * @see #setObtainingClients(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_ObtainingClients()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isObtainingClients();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#isObtainingClients <em>Obtaining Clients</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Obtaining Clients</em>' attribute.
	 * @see #isObtainingClients()
	 * @generated
	 */
	void setObtainingClients(boolean value);

	/**
	 * Returns the value of the '<em><b>Trackers Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trackers Manager</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trackers Manager</em>' attribute.
	 * @see #setTrackersManager(Object)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_TrackersManager()
	 * @model required="true" transient="true"
	 * @generated
	 */
	Object getTrackersManager();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTrackersManager <em>Trackers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trackers Manager</em>' attribute.
	 * @see #getTrackersManager()
	 * @generated
	 */
	void setTrackersManager(Object value);

	/**
	 * Returns the value of the '<em><b>Searchers Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Searchers Manager</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Searchers Manager</em>' attribute.
	 * @see #setSearchersManager(Object)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_SearchersManager()
	 * @model required="true" transient="true"
	 * @generated
	 */
	Object getSearchersManager();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getSearchersManager <em>Searchers Manager</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Searchers Manager</em>' attribute.
	 * @see #getSearchersManager()
	 * @generated
	 */
	void setSearchersManager(Object value);

	/**
	 * Returns the value of the '<em><b>Torrent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Torrent</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Torrent</em>' attribute.
	 * @see #setTorrent(Object)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmSession_Torrent()
	 * @model transient="true"
	 * @generated
	 */
	Object getTorrent();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession#getTorrent <em>Torrent</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Torrent</em>' attribute.
	 * @see #getTorrent()
	 * @generated
	 */
	void setTorrent(Object value);

} // SwarmSession
