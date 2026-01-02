/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Swarm Info</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl#getDwnldStrategy <em>Dwnld Strategy</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl#isCreatedOnDisc <em>Created On Disc</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmInfoImpl#isUploadOnly <em>Upload Only</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwarmInfoImpl extends EObjectImpl implements SwarmInfo {
	/**
	 * The default value of the '{@link #getDwnldStrategy() <em>Dwnld Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final int DWNLD_STRATEGY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDwnldStrategy() <em>Dwnld Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDwnldStrategy()
	 * @generated
	 * @ordered
	 */
	protected int dwnldStrategy = DWNLD_STRATEGY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final int ALGORITHM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected int algorithm = ALGORITHM_EDEFAULT;

	/**
	 * The default value of the '{@link #isCreatedOnDisc() <em>Created On Disc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCreatedOnDisc()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CREATED_ON_DISC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCreatedOnDisc() <em>Created On Disc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCreatedOnDisc()
	 * @generated
	 * @ordered
	 */
	protected boolean createdOnDisc = CREATED_ON_DISC_EDEFAULT;

	/**
	 * The default value of the '{@link #isUploadOnly() <em>Upload Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUploadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPLOAD_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUploadOnly() <em>Upload Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUploadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean uploadOnly = UPLOAD_ONLY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SwarmInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SWARM_INFO;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getDwnldStrategy() {
		return dwnldStrategy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDwnldStrategy(int newDwnldStrategy) {
		int oldDwnldStrategy = dwnldStrategy;
		dwnldStrategy = newDwnldStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY, oldDwnldStrategy, dwnldStrategy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlgorithm(int newAlgorithm) {
		int oldAlgorithm = algorithm;
		algorithm = newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_INFO__ALGORITHM, oldAlgorithm, algorithm));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCreatedOnDisc() {
		return createdOnDisc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreatedOnDisc(boolean newCreatedOnDisc) {
		boolean oldCreatedOnDisc = createdOnDisc;
		createdOnDisc = newCreatedOnDisc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC, oldCreatedOnDisc, createdOnDisc));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUploadOnly() {
		return uploadOnly;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUploadOnly(boolean newUploadOnly) {
		boolean oldUploadOnly = uploadOnly;
		uploadOnly = newUploadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY, oldUploadOnly, uploadOnly));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY:
				return getDwnldStrategy();
			case ClientNetworkPackage.SWARM_INFO__ALGORITHM:
				return getAlgorithm();
			case ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC:
				return isCreatedOnDisc();
			case ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY:
				return isUploadOnly();
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
			case ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY:
				setDwnldStrategy((Integer)newValue);
				return;
			case ClientNetworkPackage.SWARM_INFO__ALGORITHM:
				setAlgorithm((Integer)newValue);
				return;
			case ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC:
				setCreatedOnDisc((Boolean)newValue);
				return;
			case ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY:
				setUploadOnly((Boolean)newValue);
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
			case ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY:
				setDwnldStrategy(DWNLD_STRATEGY_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_INFO__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC:
				setCreatedOnDisc(CREATED_ON_DISC_EDEFAULT);
				return;
			case ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY:
				setUploadOnly(UPLOAD_ONLY_EDEFAULT);
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
			case ClientNetworkPackage.SWARM_INFO__DWNLD_STRATEGY:
				return dwnldStrategy != DWNLD_STRATEGY_EDEFAULT;
			case ClientNetworkPackage.SWARM_INFO__ALGORITHM:
				return algorithm != ALGORITHM_EDEFAULT;
			case ClientNetworkPackage.SWARM_INFO__CREATED_ON_DISC:
				return createdOnDisc != CREATED_ON_DISC_EDEFAULT;
			case ClientNetworkPackage.SWARM_INFO__UPLOAD_ONLY:
				return uploadOnly != UPLOAD_ONLY_EDEFAULT;
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
		result.append(" (dwnldStrategy: ");
		result.append(dwnldStrategy);
		result.append(", algorithm: ");
		result.append(algorithm);
		result.append(", createdOnDisc: ");
		result.append(createdOnDisc);
		result.append(", uploadOnly: ");
		result.append(uploadOnly);
		result.append(')');
		return result.toString();
	}

} // SwarmInfoImpl
