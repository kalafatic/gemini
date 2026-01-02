/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Semaphor;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Client Network</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl#getSwarmMap <em>Swarm Map</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl#getSemaphor <em>Semaphor</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl#getActiveSwarms <em>Active Swarms</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl#getForceSwarmsKeys <em>Force Swarms Keys</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientNetworkImpl#isSheduled <em>Sheduled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClientNetworkImpl extends BehaviourImpl implements ClientNetwork {
	/**
	 * The cached value of the '{@link #getSwarmMap() <em>Swarm Map</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSwarmMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Session> swarmMap;

	/**
	 * The cached value of the '{@link #getSemaphor() <em>Semaphor</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSemaphor()
	 * @generated
	 * @ordered
	 */
	protected Semaphor semaphor;

	/**
	 * The cached value of the '{@link #getActiveSwarms() <em>Active Swarms</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getActiveSwarms()
	 * @generated
	 * @ordered
	 */
	protected EList<String> activeSwarms;

	/**
	 * The cached value of the '{@link #getForceSwarmsKeys() <em>Force Swarms Keys</em>}' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @see #getForceSwarmsKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<String> forceSwarmsKeys;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ClientNetworkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.CLIENT_NETWORK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Session> getSwarmMap() {
		if (swarmMap == null) {
			swarmMap = new EcoreEMap<String,Session>(ClientNetworkPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP);
		}
		return swarmMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphor getSemaphor() {
		return semaphor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSemaphor(Semaphor newSemaphor, NotificationChain msgs) {
		Semaphor oldSemaphor = semaphor;
		semaphor = newSemaphor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR, oldSemaphor, newSemaphor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSemaphor(Semaphor newSemaphor) {
		if (newSemaphor != semaphor) {
			NotificationChain msgs = null;
			if (semaphor != null)
				msgs = ((InternalEObject)semaphor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR, null, msgs);
			if (newSemaphor != null)
				msgs = ((InternalEObject)newSemaphor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR, null, msgs);
			msgs = basicSetSemaphor(newSemaphor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR, newSemaphor, newSemaphor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getActiveSwarms() {
		if (activeSwarms == null) {
			activeSwarms = new EDataTypeUniqueEList<String>(String.class, this, ClientNetworkPackage.CLIENT_NETWORK__ACTIVE_SWARMS);
		}
		return activeSwarms;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getForceSwarmsKeys() {
		if (forceSwarmsKeys == null) {
			forceSwarmsKeys = new EDataTypeUniqueEList<String>(String.class, this, ClientNetworkPackage.CLIENT_NETWORK__FORCE_SWARMS_KEYS);
		}
		return forceSwarmsKeys;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_NETWORK__SHEDULED, oldSheduled, sheduled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP:
				return ((InternalEList<?>)getSwarmMap()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR:
				return basicSetSemaphor(null, msgs);
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
			case ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP:
				if (coreType) return getSwarmMap();
				else return getSwarmMap().map();
			case ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR:
				return getSemaphor();
			case ClientNetworkPackage.CLIENT_NETWORK__ACTIVE_SWARMS:
				return getActiveSwarms();
			case ClientNetworkPackage.CLIENT_NETWORK__FORCE_SWARMS_KEYS:
				return getForceSwarmsKeys();
			case ClientNetworkPackage.CLIENT_NETWORK__SHEDULED:
				return isSheduled();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP:
				((EStructuralFeature.Setting)getSwarmMap()).set(newValue);
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR:
				setSemaphor((Semaphor)newValue);
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__ACTIVE_SWARMS:
				getActiveSwarms().clear();
				getActiveSwarms().addAll((Collection<? extends String>)newValue);
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__FORCE_SWARMS_KEYS:
				getForceSwarmsKeys().clear();
				getForceSwarmsKeys().addAll((Collection<? extends String>)newValue);
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__SHEDULED:
				setSheduled((Boolean)newValue);
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
			case ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP:
				getSwarmMap().clear();
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR:
				setSemaphor((Semaphor)null);
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__ACTIVE_SWARMS:
				getActiveSwarms().clear();
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__FORCE_SWARMS_KEYS:
				getForceSwarmsKeys().clear();
				return;
			case ClientNetworkPackage.CLIENT_NETWORK__SHEDULED:
				setSheduled(SHEDULED_EDEFAULT);
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
			case ClientNetworkPackage.CLIENT_NETWORK__SWARM_MAP:
				return swarmMap != null && !swarmMap.isEmpty();
			case ClientNetworkPackage.CLIENT_NETWORK__SEMAPHOR:
				return semaphor != null;
			case ClientNetworkPackage.CLIENT_NETWORK__ACTIVE_SWARMS:
				return activeSwarms != null && !activeSwarms.isEmpty();
			case ClientNetworkPackage.CLIENT_NETWORK__FORCE_SWARMS_KEYS:
				return forceSwarmsKeys != null && !forceSwarmsKeys.isEmpty();
			case ClientNetworkPackage.CLIENT_NETWORK__SHEDULED:
				return sheduled != SHEDULED_EDEFAULT;
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
		result.append(" (activeSwarms: ");
		result.append(activeSwarms);
		result.append(", forceSwarmsKeys: ");
		result.append(forceSwarmsKeys);
		result.append(", sheduled: ");
		result.append(sheduled);
		result.append(')');
		return result.toString();
	}

} // ClientNetworkImpl
