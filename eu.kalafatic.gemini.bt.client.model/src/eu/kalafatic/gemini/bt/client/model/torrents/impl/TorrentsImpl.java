/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Torrents</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl#getTorrentMap <em>Torrent Map</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl#getFinishedTorrentsMap <em>Finished Torrents Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TorrentsImpl extends EObjectImpl implements Torrents {
	/**
	 * The cached value of the '{@link #getTorrentMap() <em>Torrent Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTorrentMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ExtTorrent> torrentMap;

	/**
	 * The cached value of the '{@link #getFinishedTorrentsMap() <em>Finished Torrents Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishedTorrentsMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ExtTorrent> finishedTorrentsMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TorrentsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.TORRENTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ExtTorrent> getTorrentMap() {
		if (torrentMap == null) {
			torrentMap = new EcoreEMap<String,ExtTorrent>(TorrentsPackage.Literals.STRING_TO_EXT_TORRENT_MAP_ENTRY, StringToExtTorrentMapEntryImpl.class, this, TorrentsPackage.TORRENTS__TORRENT_MAP);
		}
		return torrentMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, ExtTorrent> getFinishedTorrentsMap() {
		if (finishedTorrentsMap == null) {
			finishedTorrentsMap = new EcoreEMap<String,ExtTorrent>(TorrentsPackage.Literals.STRING_TO_EXT_TORRENT_MAP_ENTRY, StringToExtTorrentMapEntryImpl.class, this, TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP);
		}
		return finishedTorrentsMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TorrentsPackage.TORRENTS__TORRENT_MAP:
				return ((InternalEList<?>)getTorrentMap()).basicRemove(otherEnd, msgs);
			case TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP:
				return ((InternalEList<?>)getFinishedTorrentsMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.TORRENTS__TORRENT_MAP:
				if (coreType) return getTorrentMap();
				else return getTorrentMap().map();
			case TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP:
				if (coreType) return getFinishedTorrentsMap();
				else return getFinishedTorrentsMap().map();
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
			case TorrentsPackage.TORRENTS__TORRENT_MAP:
				((EStructuralFeature.Setting)getTorrentMap()).set(newValue);
				return;
			case TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP:
				((EStructuralFeature.Setting)getFinishedTorrentsMap()).set(newValue);
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
			case TorrentsPackage.TORRENTS__TORRENT_MAP:
				getTorrentMap().clear();
				return;
			case TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP:
				getFinishedTorrentsMap().clear();
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
			case TorrentsPackage.TORRENTS__TORRENT_MAP:
				return torrentMap != null && !torrentMap.isEmpty();
			case TorrentsPackage.TORRENTS__FINISHED_TORRENTS_MAP:
				return finishedTorrentsMap != null && !finishedTorrentsMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TorrentsImpl
