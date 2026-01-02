/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Behaviour</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.BehaviourImpl#getStrategy <em>Strategy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BehaviourImpl extends EObjectImpl implements Behaviour {
	/**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final EStrategy ALGORITHM_EDEFAULT = EStrategy.RANDOM;

	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected EStrategy algorithm = ALGORITHM_EDEFAULT;

	/**
	 * The default value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final EAlgorithm STRATEGY_EDEFAULT = EAlgorithm.QUEUENING;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected EAlgorithm strategy = STRATEGY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EStrategy getAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlgorithm(EStrategy newAlgorithm) {
		EStrategy oldAlgorithm = algorithm;
		algorithm = newAlgorithm == null ? ALGORITHM_EDEFAULT : newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.BEHAVIOUR__ALGORITHM, oldAlgorithm, algorithm));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAlgorithm getStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategy(EAlgorithm newStrategy) {
		EAlgorithm oldStrategy = strategy;
		strategy = newStrategy == null ? STRATEGY_EDEFAULT : newStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.BEHAVIOUR__STRATEGY, oldStrategy, strategy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.BEHAVIOUR__ALGORITHM:
				return getAlgorithm();
			case ClientNetworkPackage.BEHAVIOUR__STRATEGY:
				return getStrategy();
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
			case ClientNetworkPackage.BEHAVIOUR__ALGORITHM:
				setAlgorithm((EStrategy)newValue);
				return;
			case ClientNetworkPackage.BEHAVIOUR__STRATEGY:
				setStrategy((EAlgorithm)newValue);
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
			case ClientNetworkPackage.BEHAVIOUR__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
			case ClientNetworkPackage.BEHAVIOUR__STRATEGY:
				setStrategy(STRATEGY_EDEFAULT);
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
			case ClientNetworkPackage.BEHAVIOUR__ALGORITHM:
				return algorithm != ALGORITHM_EDEFAULT;
			case ClientNetworkPackage.BEHAVIOUR__STRATEGY:
				return strategy != STRATEGY_EDEFAULT;
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
		result.append(" (algorithm: ");
		result.append(algorithm);
		result.append(", strategy: ");
		result.append(strategy);
		result.append(')');
		return result.toString();
	}

} // BehaviourImpl
