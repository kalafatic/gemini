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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class CommunicationImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class CommunicationImpl extends EObjectImpl implements Communication {

	/** The Constant DATE_EDEFAULT. */
	protected static final Date DATE_EDEFAULT = null;

	/** The date. */
	protected Date date = DATE_EDEFAULT;

	/** The Constant REQUEST_EDEFAULT. */
	protected static final String REQUEST_EDEFAULT = null;

	/** The request. */
	protected String request = REQUEST_EDEFAULT;

	/** The Constant RESPONSE_EDEFAULT. */
	protected static final String RESPONSE_EDEFAULT = null;

	/** The response. */
	protected String response = RESPONSE_EDEFAULT;

	/**
	 * Instantiates a new communication impl.
	 */
	protected CommunicationImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.COMMUNICATION;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#getDate()
	 */
	public Date getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#setDate(java.util.Date)
	 */
	public void setDate(Date newDate) {
		Date oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.COMMUNICATION__DATE, oldDate, date));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#getRequest()
	 */
	public String getRequest() {
		return request;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#setRequest(java.lang.String)
	 */
	public void setRequest(String newRequest) {
		String oldRequest = request;
		request = newRequest;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.COMMUNICATION__REQUEST, oldRequest, request));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#getResponse()
	 */
	public String getResponse() {
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.Communication#setResponse(java.lang.String)
	 */
	public void setResponse(String newResponse) {
		String oldResponse = response;
		response = newResponse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.COMMUNICATION__RESPONSE, oldResponse, response));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TrackerPackage.COMMUNICATION__DATE:
			return getDate();
		case TrackerPackage.COMMUNICATION__REQUEST:
			return getRequest();
		case TrackerPackage.COMMUNICATION__RESPONSE:
			return getResponse();
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
		case TrackerPackage.COMMUNICATION__DATE:
			setDate((Date) newValue);
			return;
		case TrackerPackage.COMMUNICATION__REQUEST:
			setRequest((String) newValue);
			return;
		case TrackerPackage.COMMUNICATION__RESPONSE:
			setResponse((String) newValue);
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
		case TrackerPackage.COMMUNICATION__DATE:
			setDate(DATE_EDEFAULT);
			return;
		case TrackerPackage.COMMUNICATION__REQUEST:
			setRequest(REQUEST_EDEFAULT);
			return;
		case TrackerPackage.COMMUNICATION__RESPONSE:
			setResponse(RESPONSE_EDEFAULT);
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
		case TrackerPackage.COMMUNICATION__DATE:
			return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
		case TrackerPackage.COMMUNICATION__REQUEST:
			return REQUEST_EDEFAULT == null ? request != null : !REQUEST_EDEFAULT.equals(request);
		case TrackerPackage.COMMUNICATION__RESPONSE:
			return RESPONSE_EDEFAULT == null ? response != null : !RESPONSE_EDEFAULT.equals(response);
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
		result.append(" (date: ");
		result.append(date);
		result.append(", request: ");
		result.append(request);
		result.append(", response: ");
		result.append(response);
		result.append(')');
		return result.toString();
	}

} // CommunicationImpl
