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
package eu.kalafatic.gemini.stat.model.statistics.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;

/**
 * The Class class GraphImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class GraphImpl extends EObjectImpl implements Graph {

	/** The Constant NAME_EDEFAULT. */
	protected static final String NAME_EDEFAULT = null;

	/** The name. */
	protected String name = NAME_EDEFAULT;

	/** The coordinates. */
	protected EList coordinates;

	/** The Constant ACTIVE_EDEFAULT. */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/** The active. */
	protected boolean active = ACTIVE_EDEFAULT;

	/** The Constant NAME_X_EDEFAULT. */
	protected static final String NAME_X_EDEFAULT = null;

	/** The name x. */
	protected String nameX = NAME_X_EDEFAULT;

	/** The Constant NAME_Y_EDEFAULT. */
	protected static final String NAME_Y_EDEFAULT = null;

	/** The name y. */
	protected String nameY = NAME_Y_EDEFAULT;

	/** The Constant TYPE_EDEFAULT. */
	protected static final String TYPE_EDEFAULT = "BAR";

	/** The type. */
	protected String type = TYPE_EDEFAULT;

	/** The Constant DATA_EDEFAULT. */
	protected static final String DATA_EDEFAULT = null;

	/** The data. */
	protected String data = DATA_EDEFAULT;

	/** The series. */
	protected EList series;

	/** The cat series. */
	protected EList catSeries;

	/**
	 * Instantiates a new graph impl.
	 */
	protected GraphImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	protected EClass eStaticClass() {
		return StatisticsPackage.Literals.GRAPH;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setName(java.lang.String)
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__NAME, oldName, name));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getCoordinates()
	 */
	public EList getCoordinates() {
		if (coordinates == null) {
			coordinates = new EDataTypeUniqueEList(Integer.class, this, StatisticsPackage.GRAPH__COORDINATES);
		}
		return coordinates;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#isActive()
	 */
	public boolean isActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setActive(boolean)
	 */
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__ACTIVE, oldActive, active));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getNameX()
	 */
	public String getNameX() {
		return nameX;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setNameX(java.lang.String)
	 */
	public void setNameX(String newNameX) {
		String oldNameX = nameX;
		nameX = newNameX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__NAME_X, oldNameX, nameX));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getNameY()
	 */
	public String getNameY() {
		return nameY;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setNameY(java.lang.String)
	 */
	public void setNameY(String newNameY) {
		String oldNameY = nameY;
		nameY = newNameY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__NAME_Y, oldNameY, nameY));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getType()
	 */
	public String getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setType(java.lang.String)
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__TYPE, oldType, type));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getData()
	 */
	public String getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#setData(java.lang.String)
	 */
	public void setData(String newData) {
		String oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.GRAPH__DATA, oldData, data));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getSeries()
	 */
	public EList getSeries() {
		if (series == null) {
			series = new EDataTypeUniqueEList(String.class, this, StatisticsPackage.GRAPH__SERIES);
		}
		return series;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Graph#getCatSeries()
	 */
	public EList getCatSeries() {
		if (catSeries == null) {
			catSeries = new EDataTypeUniqueEList(String.class, this, StatisticsPackage.GRAPH__CAT_SERIES);
		}
		return catSeries;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case StatisticsPackage.GRAPH__NAME:
			return getName();
		case StatisticsPackage.GRAPH__COORDINATES:
			return getCoordinates();
		case StatisticsPackage.GRAPH__ACTIVE:
			return isActive() ? Boolean.TRUE : Boolean.FALSE;
		case StatisticsPackage.GRAPH__NAME_X:
			return getNameX();
		case StatisticsPackage.GRAPH__NAME_Y:
			return getNameY();
		case StatisticsPackage.GRAPH__TYPE:
			return getType();
		case StatisticsPackage.GRAPH__DATA:
			return getData();
		case StatisticsPackage.GRAPH__SERIES:
			return getSeries();
		case StatisticsPackage.GRAPH__CAT_SERIES:
			return getCatSeries();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StatisticsPackage.GRAPH__NAME:
			setName((String) newValue);
			return;
		case StatisticsPackage.GRAPH__COORDINATES:
			getCoordinates().clear();
			getCoordinates().addAll((Collection) newValue);
			return;
		case StatisticsPackage.GRAPH__ACTIVE:
			setActive(((Boolean) newValue).booleanValue());
			return;
		case StatisticsPackage.GRAPH__NAME_X:
			setNameX((String) newValue);
			return;
		case StatisticsPackage.GRAPH__NAME_Y:
			setNameY((String) newValue);
			return;
		case StatisticsPackage.GRAPH__TYPE:
			setType((String) newValue);
			return;
		case StatisticsPackage.GRAPH__DATA:
			setData((String) newValue);
			return;
		case StatisticsPackage.GRAPH__SERIES:
			getSeries().clear();
			getSeries().addAll((Collection) newValue);
			return;
		case StatisticsPackage.GRAPH__CAT_SERIES:
			getCatSeries().clear();
			getCatSeries().addAll((Collection) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eUnset(int)
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
		case StatisticsPackage.GRAPH__NAME:
			setName(NAME_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__COORDINATES:
			getCoordinates().clear();
			return;
		case StatisticsPackage.GRAPH__ACTIVE:
			setActive(ACTIVE_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__NAME_X:
			setNameX(NAME_X_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__NAME_Y:
			setNameY(NAME_Y_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__DATA:
			setData(DATA_EDEFAULT);
			return;
		case StatisticsPackage.GRAPH__SERIES:
			getSeries().clear();
			return;
		case StatisticsPackage.GRAPH__CAT_SERIES:
			getCatSeries().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eIsSet(int)
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case StatisticsPackage.GRAPH__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case StatisticsPackage.GRAPH__COORDINATES:
			return coordinates != null && !coordinates.isEmpty();
		case StatisticsPackage.GRAPH__ACTIVE:
			return active != ACTIVE_EDEFAULT;
		case StatisticsPackage.GRAPH__NAME_X:
			return NAME_X_EDEFAULT == null ? nameX != null : !NAME_X_EDEFAULT.equals(nameX);
		case StatisticsPackage.GRAPH__NAME_Y:
			return NAME_Y_EDEFAULT == null ? nameY != null : !NAME_Y_EDEFAULT.equals(nameY);
		case StatisticsPackage.GRAPH__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case StatisticsPackage.GRAPH__DATA:
			return DATA_EDEFAULT == null ? data != null : !DATA_EDEFAULT.equals(data);
		case StatisticsPackage.GRAPH__SERIES:
			return series != null && !series.isEmpty();
		case StatisticsPackage.GRAPH__CAT_SERIES:
			return catSeries != null && !catSeries.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", coordinates: ");
		result.append(coordinates);
		result.append(", active: ");
		result.append(active);
		result.append(", nameX: ");
		result.append(nameX);
		result.append(", nameY: ");
		result.append(nameY);
		result.append(", type: ");
		result.append(type);
		result.append(", data: ");
		result.append(data);
		result.append(", series: ");
		result.append(series);
		result.append(", catSeries: ");
		result.append(catSeries);
		result.append(')');
		return result.toString();
	}

} // GraphImpl
