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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class WebImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebImpl extends EObjectImpl implements Web {

	/** The Constant HOME_PAGE_EDEFAULT. */
	protected static final String HOME_PAGE_EDEFAULT = "http://www.kalafatic.eu/";

	/** The home page. */
	protected String homePage = HOME_PAGE_EDEFAULT;

	/** The folders. */
	protected EMap<String, Folder> folders;

	/**
	 * Instantiates a new web impl.
	 */
	protected WebImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.WEB;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Web#getHomePage()
	 */
	public String getHomePage() {
		return homePage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Web#setHomePage(java.lang.String)
	 */
	public void setHomePage(String newHomePage) {
		String oldHomePage = homePage;
		homePage = newHomePage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.WEB__HOME_PAGE, oldHomePage, homePage));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Web#getFolders()
	 */
	public EMap<String, Folder> getFolders() {
		if (folders == null) {
			folders = new EcoreEMap<String, Folder>(WebPackage.Literals.STRING_TO_FOLDER_MAP_ENTRY, StringToFolderMapEntryImpl.class, this, WebPackage.WEB__FOLDERS);
		}
		return folders;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case WebPackage.WEB__FOLDERS:
			return ((InternalEList<?>) getFolders()).basicRemove(otherEnd, msgs);
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
		case WebPackage.WEB__HOME_PAGE:
			return getHomePage();
		case WebPackage.WEB__FOLDERS:
			if (coreType)
				return getFolders();
			else
				return getFolders().map();
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
		case WebPackage.WEB__HOME_PAGE:
			setHomePage((String) newValue);
			return;
		case WebPackage.WEB__FOLDERS:
			((EStructuralFeature.Setting) getFolders()).set(newValue);
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
		case WebPackage.WEB__HOME_PAGE:
			setHomePage(HOME_PAGE_EDEFAULT);
			return;
		case WebPackage.WEB__FOLDERS:
			getFolders().clear();
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
		case WebPackage.WEB__HOME_PAGE:
			return HOME_PAGE_EDEFAULT == null ? homePage != null : !HOME_PAGE_EDEFAULT.equals(homePage);
		case WebPackage.WEB__FOLDERS:
			return folders != null && !folders.isEmpty();
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
		result.append(" (homePage: ");
		result.append(homePage);
		result.append(')');
		return result.toString();
	}

} // WebImpl
