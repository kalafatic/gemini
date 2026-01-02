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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.stat.model.statistics.StatisticsPackage;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StudyImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StudyImpl extends EObjectImpl implements Study {

	/** The graphs. */
	protected EMap graphs;

	/** The Constant NAME_EDEFAULT. */
	protected static final String NAME_EDEFAULT = null;

	/** The name. */
	protected String name = NAME_EDEFAULT;

	/**
	 * Instantiates a new study impl.
	 */
	protected StudyImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	protected EClass eStaticClass() {
		return StatisticsPackage.Literals.STUDY;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Study#getGraphs()
	 */
	public EMap getGraphs() {
		if (graphs == null) {
			graphs = new EcoreEMap(StatisticsPackage.Literals.STRING_TO_GRAPH_MAP_ENTRY, StringToGraphMapEntryImpl.class, this, StatisticsPackage.STUDY__GRAPHS);
		}
		return graphs;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Study#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.stat.model.statistics.Study#setName(java.lang.String)
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatisticsPackage.STUDY__NAME, oldName, name));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case StatisticsPackage.STUDY__GRAPHS:
			return ((InternalEList) getGraphs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case StatisticsPackage.STUDY__GRAPHS:
			if (coreType)
				return getGraphs();
			else
				return getGraphs().map();
		case StatisticsPackage.STUDY__NAME:
			return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case StatisticsPackage.STUDY__GRAPHS:
			((EStructuralFeature.Setting) getGraphs()).set(newValue);
			return;
		case StatisticsPackage.STUDY__NAME:
			setName((String) newValue);
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
		case StatisticsPackage.STUDY__GRAPHS:
			getGraphs().clear();
			return;
		case StatisticsPackage.STUDY__NAME:
			setName(NAME_EDEFAULT);
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
		case StatisticsPackage.STUDY__GRAPHS:
			return graphs != null && !graphs.isEmpty();
		case StatisticsPackage.STUDY__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(')');
		return result.toString();
	}

} // StudyImpl
