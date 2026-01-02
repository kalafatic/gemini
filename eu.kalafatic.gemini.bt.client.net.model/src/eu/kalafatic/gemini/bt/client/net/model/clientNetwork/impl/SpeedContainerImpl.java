/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Speed Container</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl#getDwnldSize <em>Dwnld Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl#getUpldSize <em>Upld Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SpeedContainerImpl#getSpeed <em>Speed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpeedContainerImpl extends EObjectImpl implements SpeedContainer {
	/**
	 * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final long START_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected long startTime = START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDwnldSize() <em>Dwnld Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldSize()
	 * @generated
	 * @ordered
	 */
	protected static final long DWNLD_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDwnldSize() <em>Dwnld Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldSize()
	 * @generated
	 * @ordered
	 */
	protected long dwnldSize = DWNLD_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpldSize() <em>Upld Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUpldSize()
	 * @generated
	 * @ordered
	 */
	protected static final long UPLD_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getUpldSize() <em>Upld Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUpldSize()
	 * @generated
	 * @ordered
	 */
	protected long upldSize = UPLD_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final float SPEED_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected float speed = SPEED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SpeedContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SPEED_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTime(long newStartTime) {
		long oldStartTime = startTime;
		startTime = newStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SPEED_CONTAINER__START_TIME, oldStartTime, startTime));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getDwnldSize() {
		return dwnldSize;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDwnldSize(long newDwnldSize) {
		long oldDwnldSize = dwnldSize;
		dwnldSize = newDwnldSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SPEED_CONTAINER__DWNLD_SIZE, oldDwnldSize, dwnldSize));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getUpldSize() {
		return upldSize;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpldSize(long newUpldSize) {
		long oldUpldSize = upldSize;
		upldSize = newUpldSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SPEED_CONTAINER__UPLD_SIZE, oldUpldSize, upldSize));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeed(float newSpeed) {
		float oldSpeed = speed;
		speed = newSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SPEED_CONTAINER__SPEED, oldSpeed, speed));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.SPEED_CONTAINER__START_TIME:
				return getStartTime();
			case ClientNetworkPackage.SPEED_CONTAINER__DWNLD_SIZE:
				return getDwnldSize();
			case ClientNetworkPackage.SPEED_CONTAINER__UPLD_SIZE:
				return getUpldSize();
			case ClientNetworkPackage.SPEED_CONTAINER__SPEED:
				return getSpeed();
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
			case ClientNetworkPackage.SPEED_CONTAINER__START_TIME:
				setStartTime((Long)newValue);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__DWNLD_SIZE:
				setDwnldSize((Long)newValue);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__UPLD_SIZE:
				setUpldSize((Long)newValue);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__SPEED:
				setSpeed((Float)newValue);
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
			case ClientNetworkPackage.SPEED_CONTAINER__START_TIME:
				setStartTime(START_TIME_EDEFAULT);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__DWNLD_SIZE:
				setDwnldSize(DWNLD_SIZE_EDEFAULT);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__UPLD_SIZE:
				setUpldSize(UPLD_SIZE_EDEFAULT);
				return;
			case ClientNetworkPackage.SPEED_CONTAINER__SPEED:
				setSpeed(SPEED_EDEFAULT);
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
			case ClientNetworkPackage.SPEED_CONTAINER__START_TIME:
				return startTime != START_TIME_EDEFAULT;
			case ClientNetworkPackage.SPEED_CONTAINER__DWNLD_SIZE:
				return dwnldSize != DWNLD_SIZE_EDEFAULT;
			case ClientNetworkPackage.SPEED_CONTAINER__UPLD_SIZE:
				return upldSize != UPLD_SIZE_EDEFAULT;
			case ClientNetworkPackage.SPEED_CONTAINER__SPEED:
				return speed != SPEED_EDEFAULT;
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
		result.append(" (startTime: ");
		result.append(startTime);
		result.append(", dwnldSize: ");
		result.append(dwnldSize);
		result.append(", upldSize: ");
		result.append(upldSize);
		result.append(", speed: ");
		result.append(speed);
		result.append(')');
		return result.toString();
	}

} // SpeedContainerImpl
