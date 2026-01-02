/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Semaphor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl#isConnected <em>Connected</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SemaphorImpl#isOpened <em>Opened</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SemaphorImpl extends EObjectImpl implements Semaphor {
	/**
	 * The default value of the '{@link #isConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isConnected()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONNECTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isConnected()
	 * @generated
	 * @ordered
	 */
	protected boolean connected = CONNECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isOpened() <em>Opened</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isOpened()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPENED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOpened() <em>Opened</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isOpened()
	 * @generated
	 * @ordered
	 */
	protected boolean opened = OPENED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SemaphorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SEMAPHOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnected(boolean newConnected) {
		boolean oldConnected = connected;
		connected = newConnected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SEMAPHOR__CONNECTED, oldConnected, connected));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpened(boolean newOpened) {
		boolean oldOpened = opened;
		opened = newOpened;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SEMAPHOR__OPENED, oldOpened, opened));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.SEMAPHOR__CONNECTED:
				return isConnected();
			case ClientNetworkPackage.SEMAPHOR__OPENED:
				return isOpened();
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
			case ClientNetworkPackage.SEMAPHOR__CONNECTED:
				setConnected((Boolean)newValue);
				return;
			case ClientNetworkPackage.SEMAPHOR__OPENED:
				setOpened((Boolean)newValue);
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
			case ClientNetworkPackage.SEMAPHOR__CONNECTED:
				setConnected(CONNECTED_EDEFAULT);
				return;
			case ClientNetworkPackage.SEMAPHOR__OPENED:
				setOpened(OPENED_EDEFAULT);
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
			case ClientNetworkPackage.SEMAPHOR__CONNECTED:
				return connected != CONNECTED_EDEFAULT;
			case ClientNetworkPackage.SEMAPHOR__OPENED:
				return opened != OPENED_EDEFAULT;
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
		result.append(" (connected: ");
		result.append(connected);
		result.append(", opened: ");
		result.append(opened);
		result.append(')');
		return result.toString();
	}

} // SemaphorImpl
