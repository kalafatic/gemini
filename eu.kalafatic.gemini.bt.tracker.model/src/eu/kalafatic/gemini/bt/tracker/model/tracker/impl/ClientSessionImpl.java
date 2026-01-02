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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class ClientSessionImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class ClientSessionImpl extends SessionImpl implements ClientSession {

	/** The Constant SEED_EDEFAULT. */
	protected static final boolean SEED_EDEFAULT = false;

	/** The seed. */
	protected boolean seed = SEED_EDEFAULT;

	/** The Constant LAST_CONNECTION_EDEFAULT. */
	protected static final long LAST_CONNECTION_EDEFAULT = 0L;

	/** The last connection. */
	protected long lastConnection = LAST_CONNECTION_EDEFAULT;

	/**
	 * Instantiates a new client session impl.
	 */
	protected ClientSessionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.CLIENT_SESSION;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession#isSeed()
	 */
	public boolean isSeed() {
		return seed;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession#setSeed(boolean)
	 */
	public void setSeed(boolean newSeed) {
		boolean oldSeed = seed;
		seed = newSeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CLIENT_SESSION__SEED, oldSeed, seed));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession#getLastConnection()
	 */
	public long getLastConnection() {
		return lastConnection;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession#setLastConnection(long)
	 */
	public void setLastConnection(long newLastConnection) {
		long oldLastConnection = lastConnection;
		lastConnection = newLastConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CLIENT_SESSION__LAST_CONNECTION, oldLastConnection, lastConnection));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TrackerPackage.CLIENT_SESSION__SEED:
			return isSeed();
		case TrackerPackage.CLIENT_SESSION__LAST_CONNECTION:
			return getLastConnection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eSet(int, java.lang.Object)
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case TrackerPackage.CLIENT_SESSION__SEED:
			setSeed((Boolean) newValue);
			return;
		case TrackerPackage.CLIENT_SESSION__LAST_CONNECTION:
			setLastConnection((Long) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eUnset(int)
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case TrackerPackage.CLIENT_SESSION__SEED:
			setSeed(SEED_EDEFAULT);
			return;
		case TrackerPackage.CLIENT_SESSION__LAST_CONNECTION:
			setLastConnection(LAST_CONNECTION_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eIsSet(int)
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case TrackerPackage.CLIENT_SESSION__SEED:
			return seed != SEED_EDEFAULT;
		case TrackerPackage.CLIENT_SESSION__LAST_CONNECTION:
			return lastConnection != LAST_CONNECTION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#toString()
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (seed: ");
		result.append(seed);
		result.append(", lastConnection: ");
		result.append(lastConnection);
		result.append(')');
		return result.toString();
	}

} // ClientSessionImpl
