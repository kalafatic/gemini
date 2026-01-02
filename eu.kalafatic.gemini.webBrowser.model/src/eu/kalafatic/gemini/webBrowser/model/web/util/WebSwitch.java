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
package eu.kalafatic.gemini.webBrowser.model.web.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class WebSwitch.
 * @param <T> the generic type
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebSwitch<T> extends Switch<T> {

	/** The model package. */
	protected static WebPackage modelPackage;

	/**
	 * Instantiates a new web switch.
	 */
	public WebSwitch() {
		if (modelPackage == null) {
			modelPackage = WebPackage.eINSTANCE;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#isSwitchFor(org.eclipse.emf.ecore.EPackage)
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#doSwitch(int, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case WebPackage.WEB: {
			Web web = (Web) theEObject;
			T result = caseWeb(web);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case WebPackage.PAGE: {
			Page page = (Page) theEObject;
			T result = casePage(page);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case WebPackage.FOLDER: {
			Folder folder = (Folder) theEObject;
			T result = caseFolder(folder);
			if (result == null)
				result = casePage(folder);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case WebPackage.STRING_TO_FOLDER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Folder> stringToFolderMapEntry = (Map.Entry<String, Folder>) theEObject;
			T result = caseStringToFolderMapEntry(stringToFolderMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case WebPackage.STRING_TO_PAGE_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Page> stringToPageMapEntry = (Map.Entry<String, Page>) theEObject;
			T result = caseStringToPageMapEntry(stringToPageMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Case web.
	 * @param object the object
	 * @return the t
	 */
	public T caseWeb(Web object) {
		return null;
	}

	/**
	 * Case page.
	 * @param object the object
	 * @return the t
	 */
	public T casePage(Page object) {
		return null;
	}

	/**
	 * Case folder.
	 * @param object the object
	 * @return the t
	 */
	public T caseFolder(Folder object) {
		return null;
	}

	/**
	 * Case string to folder map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToFolderMapEntry(Map.Entry<String, Folder> object) {
		return null;
	}

	/**
	 * Case string to page map entry.
	 * @param object the object
	 * @return the t
	 */
	public T caseStringToPageMapEntry(Map.Entry<String, Page> object) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.Switch#defaultCase(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // WebSwitch
