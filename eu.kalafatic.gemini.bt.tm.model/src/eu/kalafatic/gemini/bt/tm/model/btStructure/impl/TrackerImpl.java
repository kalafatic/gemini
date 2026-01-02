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
package eu.kalafatic.gemini.bt.tm.model.btStructure.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;

/**
 * The Class class TrackerImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerImpl extends EObjectImpl implements Tracker {

	/** The Constant CHECKED_EDEFAULT. */
	protected static final boolean CHECKED_EDEFAULT = false;

	/** The checked. */
	protected boolean checked = CHECKED_EDEFAULT;

	/** The Constant ANNOUNCE_EDEFAULT. */
	protected static final String ANNOUNCE_EDEFAULT = null;

	/** The announce. */
	protected String announce = ANNOUNCE_EDEFAULT;

	/** The Constant COMMENT_EDEFAULT. */
	protected static final String COMMENT_EDEFAULT = null;

	/** The comment. */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * Instantiates a new tracker impl.
	 */
	protected TrackerImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return BtStructurePackage.Literals.TRACKER;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#isChecked()
	 */
	public boolean isChecked() {
		return checked;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#setChecked(boolean)
	 */
	public void setChecked(boolean newChecked) {
		boolean oldChecked = checked;
		checked = newChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.TRACKER__CHECKED, oldChecked, checked));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#getAnnounce()
	 */
	public String getAnnounce() {
		return announce;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#setAnnounce(java.lang.String)
	 */
	public void setAnnounce(String newAnnounce) {
		String oldAnnounce = announce;
		announce = newAnnounce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.TRACKER__ANNOUNCE, oldAnnounce, announce));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#getComment()
	 */
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker#setComment(java.lang.String)
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.TRACKER__COMMENT, oldComment, comment));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BtStructurePackage.TRACKER__CHECKED:
			return isChecked();
		case BtStructurePackage.TRACKER__ANNOUNCE:
			return getAnnounce();
		case BtStructurePackage.TRACKER__COMMENT:
			return getComment();
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
		case BtStructurePackage.TRACKER__CHECKED:
			setChecked((Boolean) newValue);
			return;
		case BtStructurePackage.TRACKER__ANNOUNCE:
			setAnnounce((String) newValue);
			return;
		case BtStructurePackage.TRACKER__COMMENT:
			setComment((String) newValue);
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
		case BtStructurePackage.TRACKER__CHECKED:
			setChecked(CHECKED_EDEFAULT);
			return;
		case BtStructurePackage.TRACKER__ANNOUNCE:
			setAnnounce(ANNOUNCE_EDEFAULT);
			return;
		case BtStructurePackage.TRACKER__COMMENT:
			setComment(COMMENT_EDEFAULT);
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
		case BtStructurePackage.TRACKER__CHECKED:
			return checked != CHECKED_EDEFAULT;
		case BtStructurePackage.TRACKER__ANNOUNCE:
			return ANNOUNCE_EDEFAULT == null ? announce != null : !ANNOUNCE_EDEFAULT.equals(announce);
		case BtStructurePackage.TRACKER__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
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
		result.append(" (checked: ");
		result.append(checked);
		result.append(", announce: ");
		result.append(announce);
		result.append(", comment: ");
		result.append(comment);
		result.append(')');
		return result.toString();
	}

} // TrackerImpl
