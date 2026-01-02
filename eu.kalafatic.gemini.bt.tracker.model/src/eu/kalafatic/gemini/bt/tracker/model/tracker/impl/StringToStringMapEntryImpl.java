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
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.bt.tracker.model.tracker.TrackerPackage;

/**
 * The Class class StringToStringMapEntryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StringToStringMapEntryImpl extends EObjectImpl implements BasicEMap.Entry<String, String> {

	/** The Constant KEY_EDEFAULT. */
	protected static final String KEY_EDEFAULT = null;

	/** The key. */
	protected String key = KEY_EDEFAULT;

	/** The Constant VALUE_EDEFAULT. */
	protected static final String VALUE_EDEFAULT = null;

	/** The value. */
	protected String value = VALUE_EDEFAULT;

	/**
	 * Instantiates a new string to string map entry impl.
	 */
	protected StringToStringMapEntryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.STRING_TO_STRING_MAP_ENTRY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.STRING_TO_STRING_MAP_ENTRY__KEY, oldKey, key));
	}

	/**
	 * Gets the typed value.
	 * @return the typed value
	 */
	public String getTypedValue() {
		return value;
	}

	/**
	 * Sets the typed value.
	 * @param newValue the new typed value
	 */
	public void setTypedValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.STRING_TO_STRING_MAP_ENTRY__VALUE, oldValue, value));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__KEY:
			return getTypedKey();
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__VALUE:
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
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__KEY:
			setTypedKey((String) newValue);
			return;
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__VALUE:
			setTypedValue((String) newValue);
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
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__KEY:
			setTypedKey(KEY_EDEFAULT);
			return;
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__VALUE:
			setTypedValue(VALUE_EDEFAULT);
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
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__KEY:
			return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
		case TrackerPackage.STRING_TO_STRING_MAP_ENTRY__VALUE:
			return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
		result.append(", value: ");
		result.append(value);
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
	public String getValue() {
		return getTypedValue();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 */
	public String setValue(String value) {
		String oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * Gets the e map.
	 * @return the e map
	 */
	@SuppressWarnings("unchecked")
	public EMap<String, String> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<String, String>) container.eGet(eContainmentFeature());
	}

} // StringToStringMapEntryImpl
