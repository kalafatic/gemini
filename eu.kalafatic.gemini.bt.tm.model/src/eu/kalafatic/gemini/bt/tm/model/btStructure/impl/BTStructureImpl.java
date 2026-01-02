/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3 
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt  
 * 
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.gemini.bt.tm.model.btStructure.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;
import eu.kalafatic.gemini.bt.client.model.torrents.impl.StringToExtTorrentMapEntryImpl;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;

/**
 * The Class class BTStructureImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BTStructureImpl extends EObjectImpl implements BTStructure {

	/** The new torrents. */
	protected EMap<String, ExtTorrent> newTorrents;

	/** The trackers map. */
	protected EMap<String, Tracker> trackersMap;

	/** The tree objects. */
	protected EMap<String, FileTreeObject> treeObjects;

	/**
	 * Instantiates a new bT structure impl.
	 */
	protected BTStructureImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return BtStructurePackage.Literals.BT_STRUCTURE;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure#getNewTorrents()
	 */
	public EMap<String, ExtTorrent> getNewTorrents() {
		if (newTorrents == null) {
			newTorrents = new EcoreEMap<String, ExtTorrent>(TorrentsPackage.Literals.STRING_TO_EXT_TORRENT_MAP_ENTRY, StringToExtTorrentMapEntryImpl.class, this,
					BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS);
		}
		return newTorrents;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure#getTrackersMap()
	 */
	public EMap<String, Tracker> getTrackersMap() {
		if (trackersMap == null) {
			trackersMap = new EcoreEMap<String, Tracker>(BtStructurePackage.Literals.STRING_TO_TRACKER_MAP_ENTRY, StringToTrackerMapEntryImpl.class, this,
					BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP);
		}
		return trackersMap;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure#getTreeObjects()
	 */
	public EMap<String, FileTreeObject> getTreeObjects() {
		if (treeObjects == null) {
			treeObjects = new EcoreEMap<String, FileTreeObject>(BtStructurePackage.Literals.STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY, StringToFileTreeObjectMapEntryImpl.class, this,
					BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS);
		}
		return treeObjects;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS:
			return ((InternalEList<?>) getNewTorrents()).basicRemove(otherEnd, msgs);
		case BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP:
			return ((InternalEList<?>) getTrackersMap()).basicRemove(otherEnd, msgs);
		case BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS:
			return ((InternalEList<?>) getTreeObjects()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS:
			if (coreType)
				return getNewTorrents();
			else
				return getNewTorrents().map();
		case BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP:
			if (coreType)
				return getTrackersMap();
			else
				return getTrackersMap().map();
		case BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS:
			if (coreType)
				return getTreeObjects();
			else
				return getTreeObjects().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS:
			((EStructuralFeature.Setting) getNewTorrents()).set(newValue);
			return;
		case BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP:
			((EStructuralFeature.Setting) getTrackersMap()).set(newValue);
			return;
		case BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS:
			((EStructuralFeature.Setting) getTreeObjects()).set(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eUnset(int)
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS:
			getNewTorrents().clear();
			return;
		case BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP:
			getTrackersMap().clear();
			return;
		case BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS:
			getTreeObjects().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eIsSet(int)
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case BtStructurePackage.BT_STRUCTURE__NEW_TORRENTS:
			return newTorrents != null && !newTorrents.isEmpty();
		case BtStructurePackage.BT_STRUCTURE__TRACKERS_MAP:
			return trackersMap != null && !trackersMap.isEmpty();
		case BtStructurePackage.BT_STRUCTURE__TREE_OBJECTS:
			return treeObjects != null && !treeObjects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // BTStructureImpl
