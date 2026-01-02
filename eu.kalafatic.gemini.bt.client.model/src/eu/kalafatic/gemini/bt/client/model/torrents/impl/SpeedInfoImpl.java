/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Speed Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl#getDwnldSpeed <em>Dwnld Speed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl#getUpldSpeed <em>Upld Speed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl#getLock <em>Lock</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpeedInfoImpl extends EObjectImpl implements SpeedInfo {
	/**
	 * The default value of the '{@link #getDwnldSpeed() <em>Dwnld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDwnldSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final float DWNLD_SPEED_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getDwnldSpeed() <em>Dwnld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDwnldSpeed()
	 * @generated
	 * @ordered
	 */
	protected float dwnldSpeed = DWNLD_SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpldSpeed() <em>Upld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpldSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final float UPLD_SPEED_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getUpldSpeed() <em>Upld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpldSpeed()
	 * @generated
	 * @ordered
	 */
	protected float upldSpeed = UPLD_SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected static final ReentrantReadWriteLock LOCK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected ReentrantReadWriteLock lock = LOCK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpeedInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.SPEED_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getDwnldSpeed() {
		return dwnldSpeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDwnldSpeed(float newDwnldSpeed) {
		float oldDwnldSpeed = dwnldSpeed;
		dwnldSpeed = newDwnldSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.SPEED_INFO__DWNLD_SPEED, oldDwnldSpeed, dwnldSpeed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getUpldSpeed() {
		return upldSpeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpldSpeed(float newUpldSpeed) {
		float oldUpldSpeed = upldSpeed;
		upldSpeed = newUpldSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.SPEED_INFO__UPLD_SPEED, oldUpldSpeed, upldSpeed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReentrantReadWriteLock getLock() {
		return lock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLock(ReentrantReadWriteLock newLock) {
		ReentrantReadWriteLock oldLock = lock;
		lock = newLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.SPEED_INFO__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.SPEED_INFO__DWNLD_SPEED:
				return getDwnldSpeed();
			case TorrentsPackage.SPEED_INFO__UPLD_SPEED:
				return getUpldSpeed();
			case TorrentsPackage.SPEED_INFO__LOCK:
				return getLock();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TorrentsPackage.SPEED_INFO__DWNLD_SPEED:
				setDwnldSpeed((Float)newValue);
				return;
			case TorrentsPackage.SPEED_INFO__UPLD_SPEED:
				setUpldSpeed((Float)newValue);
				return;
			case TorrentsPackage.SPEED_INFO__LOCK:
				setLock((ReentrantReadWriteLock)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TorrentsPackage.SPEED_INFO__DWNLD_SPEED:
				setDwnldSpeed(DWNLD_SPEED_EDEFAULT);
				return;
			case TorrentsPackage.SPEED_INFO__UPLD_SPEED:
				setUpldSpeed(UPLD_SPEED_EDEFAULT);
				return;
			case TorrentsPackage.SPEED_INFO__LOCK:
				setLock(LOCK_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TorrentsPackage.SPEED_INFO__DWNLD_SPEED:
				return dwnldSpeed != DWNLD_SPEED_EDEFAULT;
			case TorrentsPackage.SPEED_INFO__UPLD_SPEED:
				return upldSpeed != UPLD_SPEED_EDEFAULT;
			case TorrentsPackage.SPEED_INFO__LOCK:
				return LOCK_EDEFAULT == null ? lock != null : !LOCK_EDEFAULT.equals(lock);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dwnldSpeed: ");
		result.append(dwnldSpeed);
		result.append(", upldSpeed: ");
		result.append(upldSpeed);
		result.append(", lock: ");
		result.append(lock);
		result.append(')');
		return result.toString();
	}

} //SpeedInfoImpl
