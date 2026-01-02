/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmContent;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Swarm Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl#getTrackers <em>Trackers</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl#getDownloads <em>Downloads</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.SwarmContentImpl#getUploads <em>Uploads</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwarmContentImpl extends EObjectImpl implements SwarmContent {
	/**
	 * The cached value of the '{@link #getTrackers() <em>Trackers</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrackers()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Session> trackers;

	/**
	 * The cached value of the '{@link #getDownloads() <em>Downloads</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDownloads()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ClientSession> downloads;

	/**
	 * The cached value of the '{@link #getUploads() <em>Uploads</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUploads()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ClientSession> uploads;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SwarmContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.SWARM_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Session> getTrackers() {
		if (trackers == null) {
			trackers = new EcoreEMap<String,Session>(ClientNetworkPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_CONTENT__TRACKERS);
		}
		return trackers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ClientSession> getDownloads() {
		if (downloads == null) {
			downloads = new EcoreEMap<String,ClientSession>(ClientNetworkPackage.Literals.STRING_TO_CLIENT_SESSION_MAP_ENTRY, StringToClientSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS);
		}
		return downloads;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ClientSession> getUploads() {
		if (uploads == null) {
			uploads = new EcoreEMap<String,ClientSession>(ClientNetworkPackage.Literals.STRING_TO_CLIENT_SESSION_MAP_ENTRY, StringToClientSessionMapEntryImpl.class, this, ClientNetworkPackage.SWARM_CONTENT__UPLOADS);
		}
		return uploads;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.SWARM_CONTENT__TRACKERS:
				return ((InternalEList<?>)getTrackers()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS:
				return ((InternalEList<?>)getDownloads()).basicRemove(otherEnd, msgs);
			case ClientNetworkPackage.SWARM_CONTENT__UPLOADS:
				return ((InternalEList<?>)getUploads()).basicRemove(otherEnd, msgs);
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
			case ClientNetworkPackage.SWARM_CONTENT__TRACKERS:
				if (coreType) return getTrackers();
				else return getTrackers().map();
			case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS:
				if (coreType) return getDownloads();
				else return getDownloads().map();
			case ClientNetworkPackage.SWARM_CONTENT__UPLOADS:
				if (coreType) return getUploads();
				else return getUploads().map();
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
			case ClientNetworkPackage.SWARM_CONTENT__TRACKERS:
				((EStructuralFeature.Setting)getTrackers()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS:
				((EStructuralFeature.Setting)getDownloads()).set(newValue);
				return;
			case ClientNetworkPackage.SWARM_CONTENT__UPLOADS:
				((EStructuralFeature.Setting)getUploads()).set(newValue);
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
			case ClientNetworkPackage.SWARM_CONTENT__TRACKERS:
				getTrackers().clear();
				return;
			case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS:
				getDownloads().clear();
				return;
			case ClientNetworkPackage.SWARM_CONTENT__UPLOADS:
				getUploads().clear();
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
			case ClientNetworkPackage.SWARM_CONTENT__TRACKERS:
				return trackers != null && !trackers.isEmpty();
			case ClientNetworkPackage.SWARM_CONTENT__DOWNLOADS:
				return downloads != null && !downloads.isEmpty();
			case ClientNetworkPackage.SWARM_CONTENT__UPLOADS:
				return uploads != null && !uploads.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // SwarmContentImpl
