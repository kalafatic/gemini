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

import java.nio.channels.SocketChannel;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages;

/**
 * The Class class SessionImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class SessionImpl extends EObjectImpl implements Session {

	/** The Constant ADDRESS_EDEFAULT. */
	protected static final String ADDRESS_EDEFAULT = null;

	/** The address. */
	protected String address = ADDRESS_EDEFAULT;

	/** The Constant CHANNEL_EDEFAULT. */
	protected static final SocketChannel CHANNEL_EDEFAULT = null;

	/** The channel. */
	protected SocketChannel channel = CHANNEL_EDEFAULT;

	/** The request map. */
	protected EMap<String, String> requestMap;

	/** The communication. */
	protected EList<Communication> communication;

	/** The Constant SESSION_STATE_EDEFAULT. */
	protected static final EViewMessages SESSION_STATE_EDEFAULT = EViewMessages.READY;

	/** The session state. */
	protected EViewMessages sessionState = SESSION_STATE_EDEFAULT;

	/** The Constant PEER_ID_EDEFAULT. */
	protected static final String PEER_ID_EDEFAULT = null;

	/** The peer id. */
	protected String peerId = PEER_ID_EDEFAULT;

	/** The Constant NOTE_EDEFAULT. */
	protected static final String NOTE_EDEFAULT = null;

	/** The note. */
	protected String note = NOTE_EDEFAULT;

	/** The Constant INFO_HASH_EDEFAULT. */
	protected static final String INFO_HASH_EDEFAULT = null;

	/** The info hash. */
	protected String infoHash = INFO_HASH_EDEFAULT;

	/** The Constant LISTEN_PORT_EDEFAULT. */
	protected static final int LISTEN_PORT_EDEFAULT = 0;

	/** The listen port. */
	protected int listenPort = LISTEN_PORT_EDEFAULT;

	/** The Constant TORRENT_EDEFAULT. */
	protected static final Object TORRENT_EDEFAULT = null;

	/** The torrent. */
	protected Object torrent = TORRENT_EDEFAULT;

	/**
	 * Instantiates a new session impl.
	 */
	protected SessionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.SESSION;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getAddress()
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setAddress(java.lang.String)
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__ADDRESS, oldAddress, address));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getChannel()
	 */
	public SocketChannel getChannel() {
		return channel;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setChannel(java.nio.channels.SocketChannel)
	 */
	public void setChannel(SocketChannel newChannel) {
		SocketChannel oldChannel = channel;
		channel = newChannel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__CHANNEL, oldChannel, channel));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getRequestMap()
	 */
	public EMap<String, String> getRequestMap() {
		if (requestMap == null) {
			requestMap = new EcoreEMap<String, String>(TrackerPackage.Literals.STRING_TO_STRING_MAP_ENTRY, StringToStringMapEntryImpl.class, this, TrackerPackage.SESSION__REQUEST_MAP);
		}
		return requestMap;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getCommunication()
	 */
	public EList<Communication> getCommunication() {
		if (communication == null) {
			communication = new EObjectContainmentEList<Communication>(Communication.class, this, TrackerPackage.SESSION__COMMUNICATION);
		}
		return communication;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getSessionState()
	 */
	public EViewMessages getSessionState() {
		return sessionState;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setSessionState(eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages
	 * )
	 */
	public void setSessionState(EViewMessages newSessionState) {
		EViewMessages oldSessionState = sessionState;
		sessionState = newSessionState == null ? SESSION_STATE_EDEFAULT : newSessionState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__SESSION_STATE, oldSessionState, sessionState));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getPeerId()
	 */
	public String getPeerId() {
		return peerId;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setPeerId(java.lang.String)
	 */
	public void setPeerId(String newPeerId) {
		String oldPeerId = peerId;
		peerId = newPeerId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__PEER_ID, oldPeerId, peerId));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getNote()
	 */
	public String getNote() {
		return note;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setNote(java.lang.String)
	 */
	public void setNote(String newNote) {
		String oldNote = note;
		note = newNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__NOTE, oldNote, note));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getInfoHash()
	 */
	public String getInfoHash() {
		return infoHash;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setInfoHash(java.lang.String)
	 */
	public void setInfoHash(String newInfoHash) {
		String oldInfoHash = infoHash;
		infoHash = newInfoHash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__INFO_HASH, oldInfoHash, infoHash));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getListenPort()
	 */
	public int getListenPort() {
		return listenPort;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setListenPort(int)
	 */
	public void setListenPort(int newListenPort) {
		int oldListenPort = listenPort;
		listenPort = newListenPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__LISTEN_PORT, oldListenPort, listenPort));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#getTorrent()
	 */
	public Object getTorrent() {
		return torrent;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Session#setTorrent(java.lang.Object)
	 */
	public void setTorrent(Object newTorrent) {
		Object oldTorrent = torrent;
		torrent = newTorrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.SESSION__TORRENT, oldTorrent, torrent));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case TrackerPackage.SESSION__REQUEST_MAP:
			return ((InternalEList<?>) getRequestMap()).basicRemove(otherEnd, msgs);
		case TrackerPackage.SESSION__COMMUNICATION:
			return ((InternalEList<?>) getCommunication()).basicRemove(otherEnd, msgs);
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
		case TrackerPackage.SESSION__ADDRESS:
			return getAddress();
		case TrackerPackage.SESSION__CHANNEL:
			return getChannel();
		case TrackerPackage.SESSION__REQUEST_MAP:
			if (coreType)
				return getRequestMap();
			else
				return getRequestMap().map();
		case TrackerPackage.SESSION__COMMUNICATION:
			return getCommunication();
		case TrackerPackage.SESSION__SESSION_STATE:
			return getSessionState();
		case TrackerPackage.SESSION__PEER_ID:
			return getPeerId();
		case TrackerPackage.SESSION__NOTE:
			return getNote();
		case TrackerPackage.SESSION__INFO_HASH:
			return getInfoHash();
		case TrackerPackage.SESSION__LISTEN_PORT:
			return getListenPort();
		case TrackerPackage.SESSION__TORRENT:
			return getTorrent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case TrackerPackage.SESSION__ADDRESS:
			setAddress((String) newValue);
			return;
		case TrackerPackage.SESSION__CHANNEL:
			setChannel((SocketChannel) newValue);
			return;
		case TrackerPackage.SESSION__REQUEST_MAP:
			((EStructuralFeature.Setting) getRequestMap()).set(newValue);
			return;
		case TrackerPackage.SESSION__COMMUNICATION:
			getCommunication().clear();
			getCommunication().addAll((Collection<? extends Communication>) newValue);
			return;
		case TrackerPackage.SESSION__SESSION_STATE:
			setSessionState((EViewMessages) newValue);
			return;
		case TrackerPackage.SESSION__PEER_ID:
			setPeerId((String) newValue);
			return;
		case TrackerPackage.SESSION__NOTE:
			setNote((String) newValue);
			return;
		case TrackerPackage.SESSION__INFO_HASH:
			setInfoHash((String) newValue);
			return;
		case TrackerPackage.SESSION__LISTEN_PORT:
			setListenPort((Integer) newValue);
			return;
		case TrackerPackage.SESSION__TORRENT:
			setTorrent(newValue);
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
		case TrackerPackage.SESSION__ADDRESS:
			setAddress(ADDRESS_EDEFAULT);
			return;
		case TrackerPackage.SESSION__CHANNEL:
			setChannel(CHANNEL_EDEFAULT);
			return;
		case TrackerPackage.SESSION__REQUEST_MAP:
			getRequestMap().clear();
			return;
		case TrackerPackage.SESSION__COMMUNICATION:
			getCommunication().clear();
			return;
		case TrackerPackage.SESSION__SESSION_STATE:
			setSessionState(SESSION_STATE_EDEFAULT);
			return;
		case TrackerPackage.SESSION__PEER_ID:
			setPeerId(PEER_ID_EDEFAULT);
			return;
		case TrackerPackage.SESSION__NOTE:
			setNote(NOTE_EDEFAULT);
			return;
		case TrackerPackage.SESSION__INFO_HASH:
			setInfoHash(INFO_HASH_EDEFAULT);
			return;
		case TrackerPackage.SESSION__LISTEN_PORT:
			setListenPort(LISTEN_PORT_EDEFAULT);
			return;
		case TrackerPackage.SESSION__TORRENT:
			setTorrent(TORRENT_EDEFAULT);
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
		case TrackerPackage.SESSION__ADDRESS:
			return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
		case TrackerPackage.SESSION__CHANNEL:
			return CHANNEL_EDEFAULT == null ? channel != null : !CHANNEL_EDEFAULT.equals(channel);
		case TrackerPackage.SESSION__REQUEST_MAP:
			return requestMap != null && !requestMap.isEmpty();
		case TrackerPackage.SESSION__COMMUNICATION:
			return communication != null && !communication.isEmpty();
		case TrackerPackage.SESSION__SESSION_STATE:
			return sessionState != SESSION_STATE_EDEFAULT;
		case TrackerPackage.SESSION__PEER_ID:
			return PEER_ID_EDEFAULT == null ? peerId != null : !PEER_ID_EDEFAULT.equals(peerId);
		case TrackerPackage.SESSION__NOTE:
			return NOTE_EDEFAULT == null ? note != null : !NOTE_EDEFAULT.equals(note);
		case TrackerPackage.SESSION__INFO_HASH:
			return INFO_HASH_EDEFAULT == null ? infoHash != null : !INFO_HASH_EDEFAULT.equals(infoHash);
		case TrackerPackage.SESSION__LISTEN_PORT:
			return listenPort != LISTEN_PORT_EDEFAULT;
		case TrackerPackage.SESSION__TORRENT:
			return TORRENT_EDEFAULT == null ? torrent != null : !TORRENT_EDEFAULT.equals(torrent);
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
		result.append(" (address: ");
		result.append(address);
		result.append(", channel: ");
		result.append(channel);
		result.append(", sessionState: ");
		result.append(sessionState);
		result.append(", peerId: ");
		result.append(peerId);
		result.append(", note: ");
		result.append(note);
		result.append(", infoHash: ");
		result.append(infoHash);
		result.append(", listenPort: ");
		result.append(listenPort);
		result.append(", torrent: ");
		result.append(torrent);
		result.append(')');
		return result.toString();
	}

} // SessionImpl
