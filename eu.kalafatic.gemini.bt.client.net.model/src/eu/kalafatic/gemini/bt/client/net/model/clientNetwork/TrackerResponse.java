/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Tracker Response</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getFailureReason <em>Failure Reason</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getInterval <em>Interval</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getMinInterval <em>Min Interval</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getTrackerId <em>Tracker Id</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getComplete <em>Complete</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getIncomplete <em>Incomplete</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getResponseMessage <em>Response Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse()
 * @model
 * @generated
 */
public interface TrackerResponse extends EObject {
	/**
	 * Returns the value of the '<em><b>Failure Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Failure Reason</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failure Reason</em>' attribute.
	 * @see #setFailureReason(String)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_FailureReason()
	 * @model
	 * @generated
	 */
	String getFailureReason();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getFailureReason <em>Failure Reason</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failure Reason</em>' attribute.
	 * @see #getFailureReason()
	 * @generated
	 */
	void setFailureReason(String value);

	/**
	 * Returns the value of the '<em><b>Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interval</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interval</em>' attribute.
	 * @see #setInterval(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_Interval()
	 * @model
	 * @generated
	 */
	long getInterval();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getInterval <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval</em>' attribute.
	 * @see #getInterval()
	 * @generated
	 */
	void setInterval(long value);

	/**
	 * Returns the value of the '<em><b>Min Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Interval</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Interval</em>' attribute.
	 * @see #setMinInterval(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_MinInterval()
	 * @model
	 * @generated
	 */
	long getMinInterval();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getMinInterval <em>Min Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Interval</em>' attribute.
	 * @see #getMinInterval()
	 * @generated
	 */
	void setMinInterval(long value);

	/**
	 * Returns the value of the '<em><b>Tracker Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tracker Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tracker Id</em>' attribute.
	 * @see #setTrackerId(String)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_TrackerId()
	 * @model
	 * @generated
	 */
	String getTrackerId();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getTrackerId <em>Tracker Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tracker Id</em>' attribute.
	 * @see #getTrackerId()
	 * @generated
	 */
	void setTrackerId(String value);

	/**
	 * Returns the value of the '<em><b>Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complete</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Complete</em>' attribute.
	 * @see #setComplete(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_Complete()
	 * @model
	 * @generated
	 */
	int getComplete();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getComplete <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Complete</em>' attribute.
	 * @see #getComplete()
	 * @generated
	 */
	void setComplete(int value);

	/**
	 * Returns the value of the '<em><b>Incomplete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incomplete</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incomplete</em>' attribute.
	 * @see #setIncomplete(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_Incomplete()
	 * @model
	 * @generated
	 */
	int getIncomplete();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getIncomplete <em>Incomplete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incomplete</em>' attribute.
	 * @see #getIncomplete()
	 * @generated
	 */
	void setIncomplete(int value);

	/**
	 * Returns the value of the '<em><b>Response Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Response Message</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Message</em>' attribute.
	 * @see #setResponseMessage(String)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getTrackerResponse_ResponseMessage()
	 * @model
	 * @generated
	 */
	String getResponseMessage();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse#getResponseMessage <em>Response Message</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Message</em>' attribute.
	 * @see #getResponseMessage()
	 * @generated
	 */
	void setResponseMessage(String value);

} // TrackerResponse
