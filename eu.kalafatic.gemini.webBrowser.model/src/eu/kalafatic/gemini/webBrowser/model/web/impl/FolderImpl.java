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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class FolderImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FolderImpl extends PageImpl implements Folder {

	/** The folders. */
	protected EMap<String, Folder> folders;

	/** The pages. */
	protected EMap<String, Page> pages;

	/**
	 * Instantiates a new folder impl.
	 */
	protected FolderImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.FOLDER;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Folder#getFolders()
	 */
	public EMap<String, Folder> getFolders() {
		if (folders == null) {
			folders = new EcoreEMap<String, Folder>(WebPackage.Literals.STRING_TO_FOLDER_MAP_ENTRY, StringToFolderMapEntryImpl.class, this, WebPackage.FOLDER__FOLDERS);
		}
		return folders;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.Folder#getPages()
	 */
	public EMap<String, Page> getPages() {
		if (pages == null) {
			pages = new EcoreEMap<String, Page>(WebPackage.Literals.STRING_TO_PAGE_MAP_ENTRY, StringToPageMapEntryImpl.class, this, WebPackage.FOLDER__PAGES);
		}
		return pages;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case WebPackage.FOLDER__FOLDERS:
			return ((InternalEList<?>) getFolders()).basicRemove(otherEnd, msgs);
		case WebPackage.FOLDER__PAGES:
			return ((InternalEList<?>) getPages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case WebPackage.FOLDER__FOLDERS:
			if (coreType)
				return getFolders();
			else
				return getFolders().map();
		case WebPackage.FOLDER__PAGES:
			if (coreType)
				return getPages();
			else
				return getPages().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl#eSet(int, java.lang.Object)
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case WebPackage.FOLDER__FOLDERS:
			((EStructuralFeature.Setting) getFolders()).set(newValue);
			return;
		case WebPackage.FOLDER__PAGES:
			((EStructuralFeature.Setting) getPages()).set(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl#eUnset(int)
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case WebPackage.FOLDER__FOLDERS:
			getFolders().clear();
			return;
		case WebPackage.FOLDER__PAGES:
			getPages().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl#eIsSet(int)
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case WebPackage.FOLDER__FOLDERS:
			return folders != null && !folders.isEmpty();
		case WebPackage.FOLDER__PAGES:
			return pages != null && !pages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // FolderImpl
