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
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class TorrentSessionImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TorrentSessionImpl extends SessionImpl implements TorrentSession {

	/** The Constant TORRENT_NAME_EDEFAULT. */
	protected static final String TORRENT_NAME_EDEFAULT = null;

	/** The torrent name. */
	protected String torrentName = TORRENT_NAME_EDEFAULT;

	/** The Constant COMPLETE_EDEFAULT. */
	protected static final int COMPLETE_EDEFAULT = 0;

	/** The complete. */
	protected int complete = COMPLETE_EDEFAULT;

	/** The Constant INCOMPLETE_EDEFAULT. */
	protected static final int INCOMPLETE_EDEFAULT = 0;

	/** The incomplete. */
	protected int incomplete = INCOMPLETE_EDEFAULT;

	/** The Constant TORRENT_LEN_EDEFAULT. */
	protected static final long TORRENT_LEN_EDEFAULT = 0L;

	/** The torrent len. */
	protected long torrentLen = TORRENT_LEN_EDEFAULT;

	/** The Constant DOWNLOADED_EDEFAULT. */
	protected static final int DOWNLOADED_EDEFAULT = 0;

	/** The downloaded. */
	protected int downloaded = DOWNLOADED_EDEFAULT;

	/** The client map. */
	protected EMap<String, Session> clientMap;

	/** The Constant TORRENT_PATH_EDEFAULT. */
	protected static final String TORRENT_PATH_EDEFAULT = null;

	/** The torrent path. */
	protected String torrentPath = TORRENT_PATH_EDEFAULT;

	/**
	 * Instantiates a new torrent session impl.
	 */
	protected TorrentSessionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TORRENT_SESSION;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getTorrentName()
	 */
	public String getTorrentName() {
		return torrentName;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setTorrentName(java.lang.String)
	 */
	public void setTorrentName(String newTorrentName) {
		String oldTorrentName = torrentName;
		torrentName = newTorrentName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__TORRENT_NAME, oldTorrentName, torrentName));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getComplete()
	 */
	public int getComplete() {
		return complete;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setComplete(int)
	 */
	public void setComplete(int newComplete) {
		int oldComplete = complete;
		complete = newComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__COMPLETE, oldComplete, complete));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getIncomplete()
	 */
	public int getIncomplete() {
		return incomplete;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setIncomplete(int)
	 */
	public void setIncomplete(int newIncomplete) {
		int oldIncomplete = incomplete;
		incomplete = newIncomplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__INCOMPLETE, oldIncomplete, incomplete));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getTorrentLen()
	 */
	public long getTorrentLen() {
		return torrentLen;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setTorrentLen(long)
	 */
	public void setTorrentLen(long newTorrentLen) {
		long oldTorrentLen = torrentLen;
		torrentLen = newTorrentLen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__TORRENT_LEN, oldTorrentLen, torrentLen));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getDownloaded()
	 */
	public int getDownloaded() {
		return downloaded;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setDownloaded(int)
	 */
	public void setDownloaded(int newDownloaded) {
		int oldDownloaded = downloaded;
		downloaded = newDownloaded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__DOWNLOADED, oldDownloaded, downloaded));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getClientMap()
	 */
	public EMap<String, Session> getClientMap() {
		if (clientMap == null) {
			clientMap = new EcoreEMap<String, Session>(TrackerPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, TrackerPackage.TORRENT_SESSION__CLIENT_MAP);
		}
		return clientMap;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#getTorrentPath()
	 */
	public String getTorrentPath() {
		return torrentPath;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession#setTorrentPath(java.lang.String)
	 */
	public void setTorrentPath(String newTorrentPath) {
		String oldTorrentPath = torrentPath;
		torrentPath = newTorrentPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TORRENT_SESSION__TORRENT_PATH, oldTorrentPath, torrentPath));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case TrackerPackage.TORRENT_SESSION__CLIENT_MAP:
			return ((InternalEList<?>) getClientMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.impl.SessionImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TrackerPackage.TORRENT_SESSION__TORRENT_NAME:
			return getTorrentName();
		case TrackerPackage.TORRENT_SESSION__COMPLETE:
			return getComplete();
		case TrackerPackage.TORRENT_SESSION__INCOMPLETE:
			return getIncomplete();
		case TrackerPackage.TORRENT_SESSION__TORRENT_LEN:
			return getTorrentLen();
		case TrackerPackage.TORRENT_SESSION__DOWNLOADED:
			return getDownloaded();
		case TrackerPackage.TORRENT_SESSION__CLIENT_MAP:
			if (coreType)
				return getClientMap();
			else
				return getClientMap().map();
		case TrackerPackage.TORRENT_SESSION__TORRENT_PATH:
			return getTorrentPath();
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
		case TrackerPackage.TORRENT_SESSION__TORRENT_NAME:
			setTorrentName((String) newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__COMPLETE:
			setComplete((Integer) newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__INCOMPLETE:
			setIncomplete((Integer) newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__TORRENT_LEN:
			setTorrentLen((Long) newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__DOWNLOADED:
			setDownloaded((Integer) newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__CLIENT_MAP:
			((EStructuralFeature.Setting) getClientMap()).set(newValue);
			return;
		case TrackerPackage.TORRENT_SESSION__TORRENT_PATH:
			setTorrentPath((String) newValue);
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
		case TrackerPackage.TORRENT_SESSION__TORRENT_NAME:
			setTorrentName(TORRENT_NAME_EDEFAULT);
			return;
		case TrackerPackage.TORRENT_SESSION__COMPLETE:
			setComplete(COMPLETE_EDEFAULT);
			return;
		case TrackerPackage.TORRENT_SESSION__INCOMPLETE:
			setIncomplete(INCOMPLETE_EDEFAULT);
			return;
		case TrackerPackage.TORRENT_SESSION__TORRENT_LEN:
			setTorrentLen(TORRENT_LEN_EDEFAULT);
			return;
		case TrackerPackage.TORRENT_SESSION__DOWNLOADED:
			setDownloaded(DOWNLOADED_EDEFAULT);
			return;
		case TrackerPackage.TORRENT_SESSION__CLIENT_MAP:
			getClientMap().clear();
			return;
		case TrackerPackage.TORRENT_SESSION__TORRENT_PATH:
			setTorrentPath(TORRENT_PATH_EDEFAULT);
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
		case TrackerPackage.TORRENT_SESSION__TORRENT_NAME:
			return TORRENT_NAME_EDEFAULT == null ? torrentName != null : !TORRENT_NAME_EDEFAULT.equals(torrentName);
		case TrackerPackage.TORRENT_SESSION__COMPLETE:
			return complete != COMPLETE_EDEFAULT;
		case TrackerPackage.TORRENT_SESSION__INCOMPLETE:
			return incomplete != INCOMPLETE_EDEFAULT;
		case TrackerPackage.TORRENT_SESSION__TORRENT_LEN:
			return torrentLen != TORRENT_LEN_EDEFAULT;
		case TrackerPackage.TORRENT_SESSION__DOWNLOADED:
			return downloaded != DOWNLOADED_EDEFAULT;
		case TrackerPackage.TORRENT_SESSION__CLIENT_MAP:
			return clientMap != null && !clientMap.isEmpty();
		case TrackerPackage.TORRENT_SESSION__TORRENT_PATH:
			return TORRENT_PATH_EDEFAULT == null ? torrentPath != null : !TORRENT_PATH_EDEFAULT.equals(torrentPath);
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
		result.append(" (torrentName: ");
		result.append(torrentName);
		result.append(", complete: ");
		result.append(complete);
		result.append(", incomplete: ");
		result.append(incomplete);
		result.append(", torrentLen: ");
		result.append(torrentLen);
		result.append(", downloaded: ");
		result.append(downloaded);
		result.append(", torrentPath: ");
		result.append(torrentPath);
		result.append(')');
		return result.toString();
	}

} // TorrentSessionImpl
