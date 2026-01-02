/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;

import java.nio.channels.SocketChannel;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Session</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getAnnounce <em>Announce</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getChannel <em>Channel</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getState <em>State</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#isSheduled <em>Sheduled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getLastActivity <em>Last Activity</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getRating <em>Rating</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SessionImpl#getClients <em>Clients</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionImpl extends EObjectImpl implements Session {
	/**
	 * The default value of the '{@link #getAnnounce() <em>Announce</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAnnounce()
	 * @generated
	 * @ordered
	 */
	protected static final String ANNOUNCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAnnounce() <em>Announce</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAnnounce()
	 * @generated
	 * @ordered
	 */
	protected String announce = ANNOUNCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getChannel() <em>Channel</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getChannel()
	 * @generated
	 * @ordered
	 */
	protected static final SocketChannel CHANNEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChannel() <em>Channel</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getChannel()
	 * @generated
	 * @ordered
	 */
	protected SocketChannel channel = CHANNEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final EViewsMessages STATE_EDEFAULT = EViewsMessages.READY;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected EViewsMessages state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSheduled() <em>Sheduled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSheduled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHEDULED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSheduled() <em>Sheduled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSheduled()
	 * @generated
	 * @ordered
	 */
	protected boolean sheduled = SHEDULED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastActivity() <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLastActivity()
	 * @generated
	 * @ordered
	 */
	protected static final long LAST_ACTIVITY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLastActivity() <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLastActivity()
	 * @generated
	 * @ordered
	 */
	protected long lastActivity = LAST_ACTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getRating() <em>Rating</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRating()
	 * @generated
	 * @ordered
	 */
	protected static final int RATING_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRating() <em>Rating</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRating()
	 * @generated
	 * @ordered
	 */
	protected int rating = RATING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final long TIMEOUT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected long timeout = TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final long DURATION_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected long duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getClients() <em>Clients</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getClients()
	 * @generated
	 * @ordered
	 */
	protected static final Set CLIENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClients() <em>Clients</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getClients()
	 * @generated
	 * @ordered
	 */
	protected Set clients = CLIENTS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SESSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getAnnounce() {
		return announce;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnnounce(String newAnnounce) {
		String oldAnnounce = announce;
		announce = newAnnounce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__ANNOUNCE, oldAnnounce, announce));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SocketChannel getChannel() {
		return channel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setChannel(SocketChannel newChannel) {
		SocketChannel oldChannel = channel;
		channel = newChannel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__CHANNEL, oldChannel, channel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EViewsMessages getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(EViewsMessages newState) {
		EViewsMessages oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSheduled() {
		return sheduled;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSheduled(boolean newSheduled) {
		boolean oldSheduled = sheduled;
		sheduled = newSheduled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__SHEDULED, oldSheduled, sheduled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getLastActivity() {
		return lastActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastActivity(long newLastActivity) {
		long oldLastActivity = lastActivity;
		lastActivity = newLastActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__LAST_ACTIVITY, oldLastActivity, lastActivity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRating(int newRating) {
		int oldRating = rating;
		rating = newRating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__RATING, oldRating, rating));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(long newTimeout) {
		long oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(long newDuration) {
		long oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Set getClients() {
		return clients;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setClients(Set newClients) {
		Set oldClients = clients;
		clients = newClients;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SESSION__CLIENTS, oldClients, clients));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.SESSION__ANNOUNCE:
				return getAnnounce();
			case ClientNetworkPackage.SESSION__CHANNEL:
				return getChannel();
			case ClientNetworkPackage.SESSION__STATE:
				return getState();
			case ClientNetworkPackage.SESSION__SHEDULED:
				return isSheduled();
			case ClientNetworkPackage.SESSION__LAST_ACTIVITY:
				return getLastActivity();
			case ClientNetworkPackage.SESSION__RATING:
				return getRating();
			case ClientNetworkPackage.SESSION__TIMEOUT:
				return getTimeout();
			case ClientNetworkPackage.SESSION__DURATION:
				return getDuration();
			case ClientNetworkPackage.SESSION__CLIENTS:
				return getClients();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClientNetworkPackage.SESSION__ANNOUNCE:
				setAnnounce((String)newValue);
				return;
			case ClientNetworkPackage.SESSION__CHANNEL:
				setChannel((SocketChannel)newValue);
				return;
			case ClientNetworkPackage.SESSION__STATE:
				setState((EViewsMessages)newValue);
				return;
			case ClientNetworkPackage.SESSION__SHEDULED:
				setSheduled((Boolean)newValue);
				return;
			case ClientNetworkPackage.SESSION__LAST_ACTIVITY:
				setLastActivity((Long)newValue);
				return;
			case ClientNetworkPackage.SESSION__RATING:
				setRating((Integer)newValue);
				return;
			case ClientNetworkPackage.SESSION__TIMEOUT:
				setTimeout((Long)newValue);
				return;
			case ClientNetworkPackage.SESSION__DURATION:
				setDuration((Long)newValue);
				return;
			case ClientNetworkPackage.SESSION__CLIENTS:
				setClients((Set)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.SESSION__ANNOUNCE:
				setAnnounce(ANNOUNCE_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__CHANNEL:
				setChannel(CHANNEL_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__STATE:
				setState(STATE_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__SHEDULED:
				setSheduled(SHEDULED_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__LAST_ACTIVITY:
				setLastActivity(LAST_ACTIVITY_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__RATING:
				setRating(RATING_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case ClientNetworkPackage.SESSION__CLIENTS:
				setClients(CLIENTS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.SESSION__ANNOUNCE:
				return ANNOUNCE_EDEFAULT == null ? announce != null : !ANNOUNCE_EDEFAULT.equals(announce);
			case ClientNetworkPackage.SESSION__CHANNEL:
				return CHANNEL_EDEFAULT == null ? channel != null : !CHANNEL_EDEFAULT.equals(channel);
			case ClientNetworkPackage.SESSION__STATE:
				return state != STATE_EDEFAULT;
			case ClientNetworkPackage.SESSION__SHEDULED:
				return sheduled != SHEDULED_EDEFAULT;
			case ClientNetworkPackage.SESSION__LAST_ACTIVITY:
				return lastActivity != LAST_ACTIVITY_EDEFAULT;
			case ClientNetworkPackage.SESSION__RATING:
				return rating != RATING_EDEFAULT;
			case ClientNetworkPackage.SESSION__TIMEOUT:
				return timeout != TIMEOUT_EDEFAULT;
			case ClientNetworkPackage.SESSION__DURATION:
				return duration != DURATION_EDEFAULT;
			case ClientNetworkPackage.SESSION__CLIENTS:
				return CLIENTS_EDEFAULT == null ? clients != null : !CLIENTS_EDEFAULT.equals(clients);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (announce: ");
		result.append(announce);
		result.append(", channel: ");
		result.append(channel);
		result.append(", state: ");
		result.append(state);
		result.append(", sheduled: ");
		result.append(sheduled);
		result.append(", lastActivity: ");
		result.append(lastActivity);
		result.append(", rating: ");
		result.append(rating);
		result.append(", timeout: ");
		result.append(timeout);
		result.append(", duration: ");
		result.append(duration);
		result.append(", clients: ");
		result.append(clients);
		result.append(')');
		return result.toString();
	}

} // SessionImpl
