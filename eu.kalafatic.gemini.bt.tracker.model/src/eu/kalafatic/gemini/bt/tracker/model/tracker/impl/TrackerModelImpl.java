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
package eu.kalafatic.gemini.bt.tracker.model.tracker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class TrackerModelImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerModelImpl extends EObjectImpl implements TrackerModel {

	/** The swarm map. */
	protected EMap<String, Session> swarmMap;

	/** The Constant TRACKER_ID_EDEFAULT. */
	protected static final String TRACKER_ID_EDEFAULT = "-GE2010-000000000000";

	/** The tracker id. */
	protected String trackerID = TRACKER_ID_EDEFAULT;

	/** The Constant DEFAULT_NUMWANT_EDEFAULT. */
	protected static final int DEFAULT_NUMWANT_EDEFAULT = 30;

	/** The default numwant. */
	protected int defaultNumwant = DEFAULT_NUMWANT_EDEFAULT;

	/** The Constant DEFAULT_INTERVAL_EDEFAULT. */
	protected static final int DEFAULT_INTERVAL_EDEFAULT = 10;

	/** The default interval. */
	protected int defaultInterval = DEFAULT_INTERVAL_EDEFAULT;

	/**
	 * Instantiates a new tracker model impl.
	 */
	protected TrackerModelImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TRACKER_MODEL;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#getSwarmMap()
	 */
	public EMap<String, Session> getSwarmMap() {
		if (swarmMap == null) {
			swarmMap = new EcoreEMap<String, Session>(TrackerPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, TrackerPackage.TRACKER_MODEL__SWARM_MAP);
		}
		return swarmMap;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#getTrackerID()
	 */
	public String getTrackerID() {
		return trackerID;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#setTrackerID(java.lang.String)
	 */
	public void setTrackerID(String newTrackerID) {
		String oldTrackerID = trackerID;
		trackerID = newTrackerID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER_MODEL__TRACKER_ID, oldTrackerID, trackerID));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#getDefaultNumwant()
	 */
	public int getDefaultNumwant() {
		return defaultNumwant;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#setDefaultNumwant(int)
	 */
	public void setDefaultNumwant(int newDefaultNumwant) {
		int oldDefaultNumwant = defaultNumwant;
		defaultNumwant = newDefaultNumwant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER_MODEL__DEFAULT_NUMWANT, oldDefaultNumwant, defaultNumwant));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#getDefaultInterval()
	 */
	public int getDefaultInterval() {
		return defaultInterval;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerModel#setDefaultInterval(int)
	 */
	public void setDefaultInterval(int newDefaultInterval) {
		int oldDefaultInterval = defaultInterval;
		defaultInterval = newDefaultInterval;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER_MODEL__DEFAULT_INTERVAL, oldDefaultInterval, defaultInterval));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case TrackerPackage.TRACKER_MODEL__SWARM_MAP:
			return ((InternalEList<?>) getSwarmMap()).basicRemove(otherEnd, msgs);
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
		case TrackerPackage.TRACKER_MODEL__SWARM_MAP:
			if (coreType)
				return getSwarmMap();
			else
				return getSwarmMap().map();
		case TrackerPackage.TRACKER_MODEL__TRACKER_ID:
			return getTrackerID();
		case TrackerPackage.TRACKER_MODEL__DEFAULT_NUMWANT:
			return getDefaultNumwant();
		case TrackerPackage.TRACKER_MODEL__DEFAULT_INTERVAL:
			return getDefaultInterval();
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
		case TrackerPackage.TRACKER_MODEL__SWARM_MAP:
			((EStructuralFeature.Setting) getSwarmMap()).set(newValue);
			return;
		case TrackerPackage.TRACKER_MODEL__TRACKER_ID:
			setTrackerID((String) newValue);
			return;
		case TrackerPackage.TRACKER_MODEL__DEFAULT_NUMWANT:
			setDefaultNumwant((Integer) newValue);
			return;
		case TrackerPackage.TRACKER_MODEL__DEFAULT_INTERVAL:
			setDefaultInterval((Integer) newValue);
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
		case TrackerPackage.TRACKER_MODEL__SWARM_MAP:
			getSwarmMap().clear();
			return;
		case TrackerPackage.TRACKER_MODEL__TRACKER_ID:
			setTrackerID(TRACKER_ID_EDEFAULT);
			return;
		case TrackerPackage.TRACKER_MODEL__DEFAULT_NUMWANT:
			setDefaultNumwant(DEFAULT_NUMWANT_EDEFAULT);
			return;
		case TrackerPackage.TRACKER_MODEL__DEFAULT_INTERVAL:
			setDefaultInterval(DEFAULT_INTERVAL_EDEFAULT);
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
		case TrackerPackage.TRACKER_MODEL__SWARM_MAP:
			return swarmMap != null && !swarmMap.isEmpty();
		case TrackerPackage.TRACKER_MODEL__TRACKER_ID:
			return TRACKER_ID_EDEFAULT == null ? trackerID != null : !TRACKER_ID_EDEFAULT.equals(trackerID);
		case TrackerPackage.TRACKER_MODEL__DEFAULT_NUMWANT:
			return defaultNumwant != DEFAULT_NUMWANT_EDEFAULT;
		case TrackerPackage.TRACKER_MODEL__DEFAULT_INTERVAL:
			return defaultInterval != DEFAULT_INTERVAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (trackerID: ");
		result.append(trackerID);
		result.append(", defaultNumwant: ");
		result.append(defaultNumwant);
		result.append(", defaultInterval: ");
		result.append(defaultInterval);
		result.append(')');
		return result.toString();
	}

} // TrackerModelImpl
