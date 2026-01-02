/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.ETrackerRequestEvent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tracker Session</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl#getResponse <em>Response</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl#getNextConnection <em>Next Connection</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerSessionImpl#isScraped <em>Scraped</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackerSessionImpl extends SessionImpl implements TrackerSession {
	/**
	 * The cached value of the '{@link #getResponse() <em>Response</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResponse()
	 * @generated
	 * @ordered
	 */
	protected TrackerResponse response;

	/**
	 * The default value of the '{@link #getNextConnection() <em>Next Connection</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNextConnection()
	 * @generated
	 * @ordered
	 */
	protected static final long NEXT_CONNECTION_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNextConnection() <em>Next Connection</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNextConnection()
	 * @generated
	 * @ordered
	 */
	protected long nextConnection = NEXT_CONNECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEvent() <em>Event</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected static final ETrackerRequestEvent EVENT_EDEFAULT = ETrackerRequestEvent.STARTED;

	/**
	 * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEvent()
	 * @generated
	 * @ordered
	 */
	protected ETrackerRequestEvent event = EVENT_EDEFAULT;

	/**
	 * The default value of the '{@link #isScraped() <em>Scraped</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isScraped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCRAPED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScraped() <em>Scraped</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isScraped()
	 * @generated
	 * @ordered
	 */
	protected boolean scraped = SCRAPED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerSessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.TRACKER_SESSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TrackerResponse getResponse() {
		return response;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResponse(TrackerResponse newResponse, NotificationChain msgs) {
		TrackerResponse oldResponse = response;
		response = newResponse;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_SESSION__RESPONSE, oldResponse, newResponse);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponse(TrackerResponse newResponse) {
		if (newResponse != response) {
			NotificationChain msgs = null;
			if (response != null)
				msgs = ((InternalEObject)response).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.TRACKER_SESSION__RESPONSE, null, msgs);
			if (newResponse != null)
				msgs = ((InternalEObject)newResponse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.TRACKER_SESSION__RESPONSE, null, msgs);
			msgs = basicSetResponse(newResponse, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_SESSION__RESPONSE, newResponse, newResponse));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getNextConnection() {
		return nextConnection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextConnection(long newNextConnection) {
		long oldNextConnection = nextConnection;
		nextConnection = newNextConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_SESSION__NEXT_CONNECTION, oldNextConnection, nextConnection));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ETrackerRequestEvent getEvent() {
		return event;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(ETrackerRequestEvent newEvent) {
		ETrackerRequestEvent oldEvent = event;
		event = newEvent == null ? EVENT_EDEFAULT : newEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_SESSION__EVENT, oldEvent, event));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScraped() {
		return scraped;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setScraped(boolean newScraped) {
		boolean oldScraped = scraped;
		scraped = newScraped;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_SESSION__SCRAPED, oldScraped, scraped));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.TRACKER_SESSION__RESPONSE:
				return basicSetResponse(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.TRACKER_SESSION__RESPONSE:
				return getResponse();
			case ClientNetworkPackage.TRACKER_SESSION__NEXT_CONNECTION:
				return getNextConnection();
			case ClientNetworkPackage.TRACKER_SESSION__EVENT:
				return getEvent();
			case ClientNetworkPackage.TRACKER_SESSION__SCRAPED:
				return isScraped();
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
			case ClientNetworkPackage.TRACKER_SESSION__RESPONSE:
				setResponse((TrackerResponse)newValue);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__NEXT_CONNECTION:
				setNextConnection((Long)newValue);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__EVENT:
				setEvent((ETrackerRequestEvent)newValue);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__SCRAPED:
				setScraped((Boolean)newValue);
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
			case ClientNetworkPackage.TRACKER_SESSION__RESPONSE:
				setResponse((TrackerResponse)null);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__NEXT_CONNECTION:
				setNextConnection(NEXT_CONNECTION_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__EVENT:
				setEvent(EVENT_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_SESSION__SCRAPED:
				setScraped(SCRAPED_EDEFAULT);
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
			case ClientNetworkPackage.TRACKER_SESSION__RESPONSE:
				return response != null;
			case ClientNetworkPackage.TRACKER_SESSION__NEXT_CONNECTION:
				return nextConnection != NEXT_CONNECTION_EDEFAULT;
			case ClientNetworkPackage.TRACKER_SESSION__EVENT:
				return event != EVENT_EDEFAULT;
			case ClientNetworkPackage.TRACKER_SESSION__SCRAPED:
				return scraped != SCRAPED_EDEFAULT;
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
		result.append(" (nextConnection: ");
		result.append(nextConnection);
		result.append(", event: ");
		result.append(event);
		result.append(", scraped: ");
		result.append(scraped);
		result.append(')');
		return result.toString();
	}

} // TrackerSessionImpl
