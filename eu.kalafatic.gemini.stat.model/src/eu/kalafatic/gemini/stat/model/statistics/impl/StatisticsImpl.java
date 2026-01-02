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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.stat.model.statistics.Statistics;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;

/**
 * The Class class StatisticsImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsImpl extends EObjectImpl implements Statistics {

	/** The stat map. */
	protected EMap statMap;

	/**
	 * Instantiates a new statistics impl.
	 */
	protected StatisticsImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	protected EClass eStaticClass() {
		return StatisticsPackage.Literals.STATISTICS;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Statistics#getStatMap()
	 */
	public EMap getStatMap() {
		if (statMap == null) {
			statMap = new EcoreEMap(StatisticsPackage.Literals.STRING_TO_STUDY_MAP_ENTRY, StringToStudyMapEntryImpl.class, this, StatisticsPackage.STATISTICS__STAT_MAP);
		}
		return statMap;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StatisticsPackage.STATISTICS__STAT_MAP:
			return ((InternalEList) getStatMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case StatisticsPackage.STATISTICS__STAT_MAP:
			if (coreType)
				return getStatMap();
			else
				return getStatMap().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StatisticsPackage.STATISTICS__STAT_MAP:
			((EStructuralFeature.Setting) getStatMap()).set(newValue);
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
		case StatisticsPackage.STATISTICS__STAT_MAP:
			getStatMap().clear();
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
		case StatisticsPackage.STATISTICS__STAT_MAP:
			return statMap != null && !statMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // StatisticsImpl
