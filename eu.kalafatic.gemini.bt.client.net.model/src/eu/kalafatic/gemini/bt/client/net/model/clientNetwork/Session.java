/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;

import java.nio.channels.SocketChannel;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Session</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getAnnounce <em>Announce</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getChannel <em>Channel</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getState <em>State</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#isSheduled <em>Sheduled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getLastActivity <em>Last Activity</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getRating <em>Rating</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getDuration <em>Duration</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getClients <em>Clients</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession()
 * @model
 * @generated
 */
public interface Session extends EObject {
	/**
	 * Returns the value of the '<em><b>Announce</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Announce</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Announce</em>' attribute.
	 * @see #setAnnounce(String)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Announce()
	 * @model
	 * @generated
	 */
	String getAnnounce();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getAnnounce <em>Announce</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Announce</em>' attribute.
	 * @see #getAnnounce()
	 * @generated
	 */
	void setAnnounce(String value);

	/**
	 * Returns the value of the '<em><b>Channel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Channel</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Channel</em>' attribute.
	 * @see #setChannel(SocketChannel)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Channel()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Channel" transient="true"
	 * @generated
	 */
	SocketChannel getChannel();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getChannel <em>Channel</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Channel</em>' attribute.
	 * @see #getChannel()
	 * @generated
	 */
	void setChannel(SocketChannel value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The default value is <code>"Ready"</code>.
	 * The literals are from the enumeration {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages
	 * @see #setState(EViewsMessages)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_State()
	 * @model default="Ready"
	 * @generated
	 */
	EViewsMessages getState();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getState <em>State</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages
	 * @see #getState()
	 * @generated
	 */
	void setState(EViewsMessages value);

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
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Sheduled()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isSheduled();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#isSheduled <em>Sheduled</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheduled</em>' attribute.
	 * @see #isSheduled()
	 * @generated
	 */
	void setSheduled(boolean value);

	/**
	 * Returns the value of the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Activity</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Activity</em>' attribute.
	 * @see #setLastActivity(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_LastActivity()
	 * @model transient="true"
	 * @generated
	 */
	long getLastActivity();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getLastActivity <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Activity</em>' attribute.
	 * @see #getLastActivity()
	 * @generated
	 */
	void setLastActivity(long value);

	/**
	 * Returns the value of the '<em><b>Rating</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rating</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rating</em>' attribute.
	 * @see #setRating(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Rating()
	 * @model default="0" transient="true"
	 * @generated
	 */
	int getRating();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getRating <em>Rating</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rating</em>' attribute.
	 * @see #getRating()
	 * @generated
	 */
	void setRating(int value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Timeout()
	 * @model transient="true"
	 * @generated
	 */
	long getTimeout();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getTimeout <em>Timeout</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(long value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Duration()
	 * @model
	 * @generated
	 */
	long getDuration();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getDuration <em>Duration</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(long value);

	/**
	 * Returns the value of the '<em><b>Clients</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clients</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clients</em>' attribute.
	 * @see #setClients(Set)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSession_Clients()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Set" transient="true"
	 * @generated
	 */
	Set getClients();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session#getClients <em>Clients</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clients</em>' attribute.
	 * @see #getClients()
	 * @generated
	 */
	void setClients(Set value);

} // Session
