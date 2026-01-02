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
package eu.kalafatic.gemini.webBrowser.model.web.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class PageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class PageImpl extends EObjectImpl implements Page {

	/** The Constant ADDRESS_EDEFAULT. */
	protected static final String ADDRESS_EDEFAULT = "http://www.kalafatic.eu/";

	/** The address. */
	protected String address = ADDRESS_EDEFAULT;

	/** The parent. */
	protected Folder parent;

	/**
	 * Instantiates a new page impl.
	 */
	protected PageImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.PAGE;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Page#getAddress()
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Page#setAddress(java.lang.String)
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.PAGE__ADDRESS, oldAddress, address));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Page#getParent()
	 */
	public Folder getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject) parent;
			parent = (Folder) eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebPackage.PAGE__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * Basic get parent.
	 * @return the folder
	 */
	public Folder basicGetParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Page#setParent(eu.kalafatic.gemini.webBrowser.model.web.Folder)
	 */
	public void setParent(Folder newParent) {
		Folder oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.PAGE__PARENT, oldParent, parent));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case WebPackage.PAGE__ADDRESS:
			return getAddress();
		case WebPackage.PAGE__PARENT:
			if (resolve)
				return getParent();
			return basicGetParent();
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
		case WebPackage.PAGE__ADDRESS:
			setAddress((String) newValue);
			return;
		case WebPackage.PAGE__PARENT:
			setParent((Folder) newValue);
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
		case WebPackage.PAGE__ADDRESS:
			setAddress(ADDRESS_EDEFAULT);
			return;
		case WebPackage.PAGE__PARENT:
			setParent((Folder) null);
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
		case WebPackage.PAGE__ADDRESS:
			return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
		case WebPackage.PAGE__PARENT:
			return parent != null;
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
		result.append(')');
		return result.toString();
	}

} // PageImpl
