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

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebFactory;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * The Class class WebFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebFactoryImpl extends EFactoryImpl implements WebFactory {

	/**
	 * Inits the.
	 * @return the web factory
	 */
	public static WebFactory init() {
		try {
			WebFactory theWebFactory = (WebFactory) EPackage.Registry.INSTANCE.getEFactory("http:///web.ecore");
			if (theWebFactory != null) {
				return theWebFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebFactoryImpl();
	}

	/**
	 * Instantiates a new web factory impl.
	 */
	public WebFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case WebPackage.WEB:
			return createWeb();
		case WebPackage.PAGE:
			return createPage();
		case WebPackage.FOLDER:
			return createFolder();
		case WebPackage.STRING_TO_FOLDER_MAP_ENTRY:
			return (EObject) createStringToFolderMapEntry();
		case WebPackage.STRING_TO_PAGE_MAP_ENTRY:
			return (EObject) createStringToPageMapEntry();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebFactory#createWeb()
	 */
	public Web createWeb() {
		WebImpl web = new WebImpl();
		return web;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebFactory#createPage()
	 */
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebFactory#createFolder()
	 */
	public Folder createFolder() {
		FolderImpl folder = new FolderImpl();
		return folder;
	}

	/**
	 * Creates the string to folder map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Folder> createStringToFolderMapEntry() {
		StringToFolderMapEntryImpl stringToFolderMapEntry = new StringToFolderMapEntryImpl();
		return stringToFolderMapEntry;
	}

	/**
	 * Creates the string to page map entry.
	 * @return the map. entry
	 */
	public Map.Entry<String, Page> createStringToPageMapEntry() {
		StringToPageMapEntryImpl stringToPageMapEntry = new StringToPageMapEntryImpl();
		return stringToPageMapEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.webBrowser.model.web.WebFactory#getWebPackage()
	 */
	public WebPackage getWebPackage() {
		return (WebPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static WebPackage getPackage() {
		return WebPackage.eINSTANCE;
	}

} // WebFactoryImpl
