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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl;

import java.net.Socket;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class RCSessionImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCSessionImpl extends EObjectImpl implements RCSession {

	/** The Constant HOST_EDEFAULT. */
	protected static final String HOST_EDEFAULT = null;

	/** The host. */
	protected String host = HOST_EDEFAULT;

	/** The Constant ANNOUNCE_EDEFAULT. */
	protected static final String ANNOUNCE_EDEFAULT = null;

	/** The announce. */
	protected String announce = ANNOUNCE_EDEFAULT;

	/** The Constant SOCKET_EDEFAULT. */
	protected static final Socket SOCKET_EDEFAULT = null;

	/** The socket. */
	protected Socket socket = SOCKET_EDEFAULT;

	/** The Constant STATE_EDEFAULT. */
	protected static final ERCSessionState STATE_EDEFAULT = ERCSessionState.NEW;

	/** The state. */
	protected ERCSessionState state = STATE_EDEFAULT;

	/** The Constant TORRENTS_EDEFAULT. */
	protected static final ERCCMD TORRENTS_EDEFAULT = ERCCMD.SEL_ALL;

	/** The torrents. */
	protected ERCCMD torrents = TORRENTS_EDEFAULT;

	/** The Constant HTTP_EXCHANGE_EDEFAULT. */
	protected static final Object HTTP_EXCHANGE_EDEFAULT = null;

	/** The http exchange. */
	protected Object httpExchange = HTTP_EXCHANGE_EDEFAULT;

	/**
	 * Instantiates a new rC session impl.
	 */
	protected RCSessionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return RcPackage.Literals.RC_SESSION;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getHost()
	 */
	public String getHost() {
		return host;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setHost(java.lang.String)
	 */
	public void setHost(String newHost) {
		String oldHost = host;
		host = newHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__HOST, oldHost, host));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getAnnounce()
	 */
	public String getAnnounce() {
		return announce;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setAnnounce(java.lang.String)
	 */
	public void setAnnounce(String newAnnounce) {
		String oldAnnounce = announce;
		announce = newAnnounce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__ANNOUNCE, oldAnnounce, announce));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getSocket()
	 */
	public Socket getSocket() {
		return socket;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setSocket(java.net.Socket)
	 */
	public void setSocket(Socket newSocket) {
		Socket oldSocket = socket;
		socket = newSocket;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__SOCKET, oldSocket, socket));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getState()
	 */
	public ERCSessionState getState() {
		return state;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setState(eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState)
	 */
	public void setState(ERCSessionState newState) {
		ERCSessionState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__STATE, oldState, state));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getTorrents()
	 */
	public ERCCMD getTorrents() {
		return torrents;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setTorrents(eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCCMD)
	 */
	public void setTorrents(ERCCMD newTorrents) {
		ERCCMD oldTorrents = torrents;
		torrents = newTorrents == null ? TORRENTS_EDEFAULT : newTorrents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__TORRENTS, oldTorrents, torrents));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#getHttpExchange()
	 */
	public Object getHttpExchange() {
		return httpExchange;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession#setHttpExchange(java.lang.Object)
	 */
	public void setHttpExchange(Object newHttpExchange) {
		Object oldHttpExchange = httpExchange;
		httpExchange = newHttpExchange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.RC_SESSION__HTTP_EXCHANGE, oldHttpExchange, httpExchange));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case RcPackage.RC_SESSION__HOST:
			return getHost();
		case RcPackage.RC_SESSION__ANNOUNCE:
			return getAnnounce();
		case RcPackage.RC_SESSION__SOCKET:
			return getSocket();
		case RcPackage.RC_SESSION__STATE:
			return getState();
		case RcPackage.RC_SESSION__TORRENTS:
			return getTorrents();
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
		case RcPackage.RC_SESSION__HOST:
			setHost((String) newValue);
			return;
		case RcPackage.RC_SESSION__ANNOUNCE:
			setAnnounce((String) newValue);
			return;
		case RcPackage.RC_SESSION__SOCKET:
			setSocket((Socket) newValue);
			return;
		case RcPackage.RC_SESSION__STATE:
			setState((ERCSessionState) newValue);
			return;
		case RcPackage.RC_SESSION__TORRENTS:
			setTorrents((ERCCMD) newValue);
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
		case RcPackage.RC_SESSION__HOST:
			setHost(HOST_EDEFAULT);
			return;
		case RcPackage.RC_SESSION__ANNOUNCE:
			setAnnounce(ANNOUNCE_EDEFAULT);
			return;
		case RcPackage.RC_SESSION__SOCKET:
			setSocket(SOCKET_EDEFAULT);
			return;
		case RcPackage.RC_SESSION__STATE:
			setState(STATE_EDEFAULT);
			return;
		case RcPackage.RC_SESSION__TORRENTS:
			setTorrents(TORRENTS_EDEFAULT);
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
		case RcPackage.RC_SESSION__HOST:
			return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
		case RcPackage.RC_SESSION__ANNOUNCE:
			return ANNOUNCE_EDEFAULT == null ? announce != null : !ANNOUNCE_EDEFAULT.equals(announce);
		case RcPackage.RC_SESSION__SOCKET:
			return SOCKET_EDEFAULT == null ? socket != null : !SOCKET_EDEFAULT.equals(socket);
		case RcPackage.RC_SESSION__STATE:
			return state != STATE_EDEFAULT;
		case RcPackage.RC_SESSION__TORRENTS:
			return torrents != TORRENTS_EDEFAULT;
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
		result.append(" (host: ");
		result.append(host);
		result.append(", announce: ");
		result.append(announce);
		result.append(", socket: ");
		result.append(socket);
		result.append(", state: ");
		result.append(state);
		result.append(", torrents: ");
		result.append(torrents);
		result.append(')');
		return result.toString();
	}

} // RCSessionImpl
