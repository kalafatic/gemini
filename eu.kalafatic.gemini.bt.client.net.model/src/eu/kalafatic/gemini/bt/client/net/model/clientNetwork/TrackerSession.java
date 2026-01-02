/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Tracker Session</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getResponse <em>Response</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getNextConnection <em>Next Connection</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getEvent <em>Event</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#isScraped <em>Scraped</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerSession()
 * @model
 * @generated
 */
public interface TrackerSession extends Session {
	/**
	 * Returns the value of the '<em><b>Response</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Response</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response</em>' containment reference.
	 * @see #setResponse(TrackerResponse)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerSession_Response()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	TrackerResponse getResponse();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getResponse <em>Response</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response</em>' containment reference.
	 * @see #getResponse()
	 * @generated
	 */
	void setResponse(TrackerResponse value);

	/**
	 * Returns the value of the '<em><b>Next Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Connection</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Connection</em>' attribute.
	 * @see #setNextConnection(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerSession_NextConnection()
	 * @model transient="true"
	 * @generated
	 */
	long getNextConnection();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getNextConnection <em>Next Connection</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Connection</em>' attribute.
	 * @see #getNextConnection()
	 * @generated
	 */
	void setNextConnection(long value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent
	 * @see #setEvent(ETrackerRequestEvent)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerSession_Event()
	 * @model transient="true"
	 * @generated
	 */
	ETrackerRequestEvent getEvent();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#getEvent <em>Event</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(ETrackerRequestEvent value);

	/**
	 * Returns the value of the '<em><b>Scraped</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scraped</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scraped</em>' attribute.
	 * @see #setScraped(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerSession_Scraped()
	 * @model default="false"
	 * @generated
	 */
	boolean isScraped();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession#isScraped <em>Scraped</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scraped</em>' attribute.
	 * @see #isScraped()
	 * @generated
	 */
	void setScraped(boolean value);

} // TrackerSession
