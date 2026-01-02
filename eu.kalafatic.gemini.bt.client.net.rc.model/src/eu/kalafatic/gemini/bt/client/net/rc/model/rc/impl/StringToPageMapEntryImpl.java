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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class StringToPageMapEntryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StringToPageMapEntryImpl extends EObjectImpl implements BasicEMap.Entry<String, Page> {

	/** The Constant KEY_EDEFAULT. */
	protected static final String KEY_EDEFAULT = null;

	/** The key. */
	protected String key = KEY_EDEFAULT;

	/** The value. */
	protected Page value;

	/**
	 * Instantiates a new string to page map entry impl.
	 */
	protected StringToPageMapEntryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return RcPackage.Literals.STRING_TO_PAGE_MAP_ENTRY;
	}

	/**
	 * Gets the typed key.
	 * @return the typed key
	 */
	public String getTypedKey() {
		return key;
	}

	/**
	 * Sets the typed key.
	 * @param newKey the new typed key
	 */
	public void setTypedKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.STRING_TO_PAGE_MAP_ENTRY__KEY, oldKey, key));
	}

	/**
	 * Gets the typed value.
	 * @return the typed value
	 */
	public Page getTypedValue() {
		return value;
	}

	/**
	 * Basic set typed value.
	 * @param newValue the new value
	 * @param msgs the msgs
	 * @return the notification chain
	 */
	public NotificationChain basicSetTypedValue(Page newValue, NotificationChain msgs) {
		Page oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE, oldValue, newValue);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the typed value.
	 * @param newValue the new typed value
	 */
	public void setTypedValue(Page newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject) value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject) newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE, null, msgs);
			msgs = basicSetTypedValue(newValue, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE, newValue, newValue));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE:
			return basicSetTypedValue(null, msgs);
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
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__KEY:
			return getTypedKey();
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE:
			return getTypedValue();
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
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__KEY:
			setTypedKey((String) newValue);
			return;
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE:
			setTypedValue((Page) newValue);
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
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__KEY:
			setTypedKey(KEY_EDEFAULT);
			return;
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE:
			setTypedValue((Page) null);
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
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__KEY:
			return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
		case RcPackage.STRING_TO_PAGE_MAP_ENTRY__VALUE:
			return value != null;
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
		result.append(" (key: ");
		result.append(key);
		result.append(')');
		return result.toString();
	}

	/** The hash. */
	protected int hash = -1;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.BasicEMap.Entry#getHash()
	 */
	public int getHash() {
		if (hash == -1) {
			Object theKey = getKey();
			hash = (theKey == null ? 0 : theKey.hashCode());
		}
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.BasicEMap.Entry#setHash(int)
	 */
	public void setHash(int hash) {
		this.hash = hash;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map.Entry#getKey()
	 */
	public String getKey() {
		return getTypedKey();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.util.BasicEMap.Entry#setKey(java.lang.Object)
	 */
	public void setKey(String key) {
		setTypedKey(key);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map.Entry#getValue()
	 */
	public Page getValue() {
		return getTypedValue();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 */
	public Page setValue(Page value) {
		Page oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * Gets the e map.
	 * @return the e map
	 */
	@SuppressWarnings("unchecked")
	public EMap<String, Page> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<String, Page>) container.eGet(eContainmentFeature());
	}

} // StringToPageMapEntryImpl
