/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerResponse;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tracker Response</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getFailureReason <em>Failure Reason</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getInterval <em>Interval</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getMinInterval <em>Min Interval</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getTrackerId <em>Tracker Id</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getComplete <em>Complete</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getIncomplete <em>Incomplete</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.TrackerResponseImpl#getResponseMessage <em>Response Message</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrackerResponseImpl extends EObjectImpl implements TrackerResponse {
	/**
	 * The default value of the '{@link #getFailureReason() <em>Failure Reason</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFailureReason()
	 * @generated
	 * @ordered
	 */
	protected static final String FAILURE_REASON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFailureReason() <em>Failure Reason</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFailureReason()
	 * @generated
	 * @ordered
	 */
	protected String failureReason = FAILURE_REASON_EDEFAULT;

	/**
	 * The default value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected static final long INTERVAL_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected long interval = INTERVAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinInterval() <em>Min Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMinInterval()
	 * @generated
	 * @ordered
	 */
	protected static final long MIN_INTERVAL_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMinInterval() <em>Min Interval</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMinInterval()
	 * @generated
	 * @ordered
	 */
	protected long minInterval = MIN_INTERVAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrackerId() <em>Tracker Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackerId()
	 * @generated
	 * @ordered
	 */
	protected static final String TRACKER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTrackerId() <em>Tracker Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackerId()
	 * @generated
	 * @ordered
	 */
	protected String trackerId = TRACKER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getComplete() <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getComplete()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPLETE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getComplete() <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getComplete()
	 * @generated
	 * @ordered
	 */
	protected int complete = COMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncomplete() <em>Incomplete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomplete()
	 * @generated
	 * @ordered
	 */
	protected static final int INCOMPLETE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIncomplete() <em>Incomplete</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomplete()
	 * @generated
	 * @ordered
	 */
	protected int incomplete = INCOMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResponseMessage() <em>Response Message</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResponseMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String RESPONSE_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResponseMessage() <em>Response Message</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResponseMessage()
	 * @generated
	 * @ordered
	 */
	protected String responseMessage = RESPONSE_MESSAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerResponseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.TRACKER_RESPONSE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getFailureReason() {
		return failureReason;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFailureReason(String newFailureReason) {
		String oldFailureReason = failureReason;
		failureReason = newFailureReason;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__FAILURE_REASON, oldFailureReason, failureReason));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterval(long newInterval) {
		long oldInterval = interval;
		interval = newInterval;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__INTERVAL, oldInterval, interval));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getMinInterval() {
		return minInterval;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinInterval(long newMinInterval) {
		long oldMinInterval = minInterval;
		minInterval = newMinInterval;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__MIN_INTERVAL, oldMinInterval, minInterval));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTrackerId() {
		return trackerId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrackerId(String newTrackerId) {
		String oldTrackerId = trackerId;
		trackerId = newTrackerId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__TRACKER_ID, oldTrackerId, trackerId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getComplete() {
		return complete;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setComplete(int newComplete) {
		int oldComplete = complete;
		complete = newComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__COMPLETE, oldComplete, complete));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getIncomplete() {
		return incomplete;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncomplete(int newIncomplete) {
		int oldIncomplete = incomplete;
		incomplete = newIncomplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__INCOMPLETE, oldIncomplete, incomplete));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponseMessage(String newResponseMessage) {
		String oldResponseMessage = responseMessage;
		responseMessage = newResponseMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.TRACKER_RESPONSE__RESPONSE_MESSAGE, oldResponseMessage, responseMessage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.TRACKER_RESPONSE__FAILURE_REASON:
				return getFailureReason();
			case ClientNetworkPackage.TRACKER_RESPONSE__INTERVAL:
				return getInterval();
			case ClientNetworkPackage.TRACKER_RESPONSE__MIN_INTERVAL:
				return getMinInterval();
			case ClientNetworkPackage.TRACKER_RESPONSE__TRACKER_ID:
				return getTrackerId();
			case ClientNetworkPackage.TRACKER_RESPONSE__COMPLETE:
				return getComplete();
			case ClientNetworkPackage.TRACKER_RESPONSE__INCOMPLETE:
				return getIncomplete();
			case ClientNetworkPackage.TRACKER_RESPONSE__RESPONSE_MESSAGE:
				return getResponseMessage();
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
			case ClientNetworkPackage.TRACKER_RESPONSE__FAILURE_REASON:
				setFailureReason((String)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__INTERVAL:
				setInterval((Long)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__MIN_INTERVAL:
				setMinInterval((Long)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__TRACKER_ID:
				setTrackerId((String)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__COMPLETE:
				setComplete((Integer)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__INCOMPLETE:
				setIncomplete((Integer)newValue);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__RESPONSE_MESSAGE:
				setResponseMessage((String)newValue);
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
			case ClientNetworkPackage.TRACKER_RESPONSE__FAILURE_REASON:
				setFailureReason(FAILURE_REASON_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__INTERVAL:
				setInterval(INTERVAL_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__MIN_INTERVAL:
				setMinInterval(MIN_INTERVAL_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__TRACKER_ID:
				setTrackerId(TRACKER_ID_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__COMPLETE:
				setComplete(COMPLETE_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__INCOMPLETE:
				setIncomplete(INCOMPLETE_EDEFAULT);
				return;
			case ClientNetworkPackage.TRACKER_RESPONSE__RESPONSE_MESSAGE:
				setResponseMessage(RESPONSE_MESSAGE_EDEFAULT);
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
			case ClientNetworkPackage.TRACKER_RESPONSE__FAILURE_REASON:
				return FAILURE_REASON_EDEFAULT == null ? failureReason != null : !FAILURE_REASON_EDEFAULT.equals(failureReason);
			case ClientNetworkPackage.TRACKER_RESPONSE__INTERVAL:
				return interval != INTERVAL_EDEFAULT;
			case ClientNetworkPackage.TRACKER_RESPONSE__MIN_INTERVAL:
				return minInterval != MIN_INTERVAL_EDEFAULT;
			case ClientNetworkPackage.TRACKER_RESPONSE__TRACKER_ID:
				return TRACKER_ID_EDEFAULT == null ? trackerId != null : !TRACKER_ID_EDEFAULT.equals(trackerId);
			case ClientNetworkPackage.TRACKER_RESPONSE__COMPLETE:
				return complete != COMPLETE_EDEFAULT;
			case ClientNetworkPackage.TRACKER_RESPONSE__INCOMPLETE:
				return incomplete != INCOMPLETE_EDEFAULT;
			case ClientNetworkPackage.TRACKER_RESPONSE__RESPONSE_MESSAGE:
				return RESPONSE_MESSAGE_EDEFAULT == null ? responseMessage != null : !RESPONSE_MESSAGE_EDEFAULT.equals(responseMessage);
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
		result.append(" (failureReason: ");
		result.append(failureReason);
		result.append(", interval: ");
		result.append(interval);
		result.append(", minInterval: ");
		result.append(minInterval);
		result.append(", trackerId: ");
		result.append(trackerId);
		result.append(", complete: ");
		result.append(complete);
		result.append(", incomplete: ");
		result.append(incomplete);
		result.append(", responseMessage: ");
		result.append(responseMessage);
		result.append(')');
		return result.toString();
	}

} // TrackerResponseImpl
