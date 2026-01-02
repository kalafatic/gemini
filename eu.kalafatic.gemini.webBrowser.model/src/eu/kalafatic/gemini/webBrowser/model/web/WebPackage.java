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
package eu.kalafatic.gemini.webBrowser.model.web;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * The Interface interface WebPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface WebPackage extends EPackage {

	/** The e name. */
	String eNAME = "web";

	/** The e n s_ uri. */
	String eNS_URI = "http:///web.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "web";

	/** The e instance. */
	WebPackage eINSTANCE = eu.kalafatic.gemini.webBrowser.model.web.impl.WebPackageImpl.init();

	/** The WEB. */
	int WEB = 0;

	/** The WE b__ hom e_ page. */
	int WEB__HOME_PAGE = 0;

	/** The WE b__ folders. */
	int WEB__FOLDERS = 1;

	/** The WE b_ featur e_ count. */
	int WEB_FEATURE_COUNT = 2;

	/** The PAGE. */
	int PAGE = 1;

	/** The PAG e__ address. */
	int PAGE__ADDRESS = 0;

	/** The PAG e__ parent. */
	int PAGE__PARENT = 1;

	/** The PAG e_ featur e_ count. */
	int PAGE_FEATURE_COUNT = 2;

	/** The FOLDER. */
	int FOLDER = 2;

	/** The FOLDE r__ address. */
	int FOLDER__ADDRESS = PAGE__ADDRESS;

	/** The FOLDE r__ parent. */
	int FOLDER__PARENT = PAGE__PARENT;

	/** The FOLDE r__ folders. */
	int FOLDER__FOLDERS = PAGE_FEATURE_COUNT + 0;

	/** The FOLDE r__ pages. */
	int FOLDER__PAGES = PAGE_FEATURE_COUNT + 1;

	/** The FOLDE r_ featur e_ count. */
	int FOLDER_FEATURE_COUNT = PAGE_FEATURE_COUNT + 2;

	/** The STRIN g_ t o_ folde r_ ma p_ entry. */
	int STRING_TO_FOLDER_MAP_ENTRY = 3;

	/** The STRIN g_ t o_ folde r_ ma p_ entr y__ key. */
	int STRING_TO_FOLDER_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ folde r_ ma p_ entr y__ value. */
	int STRING_TO_FOLDER_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ folde r_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_FOLDER_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The STRIN g_ t o_ pag e_ ma p_ entry. */
	int STRING_TO_PAGE_MAP_ENTRY = 4;

	/** The STRIN g_ t o_ pag e_ ma p_ entr y__ key. */
	int STRING_TO_PAGE_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ pag e_ ma p_ entr y__ value. */
	int STRING_TO_PAGE_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ pag e_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_PAGE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * Gets the web.
	 * @return the web
	 */
	EClass getWeb();

	/**
	 * Gets the web_ home page.
	 * @return the web_ home page
	 */
	EAttribute getWeb_HomePage();

	/**
	 * Gets the web_ folders.
	 * @return the web_ folders
	 */
	EReference getWeb_Folders();

	/**
	 * Gets the page.
	 * @return the page
	 */
	EClass getPage();

	/**
	 * Gets the page_ address.
	 * @return the page_ address
	 */
	EAttribute getPage_Address();

	/**
	 * Gets the page_ parent.
	 * @return the page_ parent
	 */
	EReference getPage_Parent();

	/**
	 * Gets the folder.
	 * @return the folder
	 */
	EClass getFolder();

	/**
	 * Gets the folder_ folders.
	 * @return the folder_ folders
	 */
	EReference getFolder_Folders();

	/**
	 * Gets the folder_ pages.
	 * @return the folder_ pages
	 */
	EReference getFolder_Pages();

	/**
	 * Gets the string to folder map entry.
	 * @return the string to folder map entry
	 */
	EClass getStringToFolderMapEntry();

	/**
	 * Gets the string to folder map entry_ key.
	 * @return the string to folder map entry_ key
	 */
	EAttribute getStringToFolderMapEntry_Key();

	/**
	 * Gets the string to folder map entry_ value.
	 * @return the string to folder map entry_ value
	 */
	EReference getStringToFolderMapEntry_Value();

	/**
	 * Gets the string to page map entry.
	 * @return the string to page map entry
	 */
	EClass getStringToPageMapEntry();

	/**
	 * Gets the string to page map entry_ key.
	 * @return the string to page map entry_ key
	 */
	EAttribute getStringToPageMapEntry_Key();

	/**
	 * Gets the string to page map entry_ value.
	 * @return the string to page map entry_ value
	 */
	EReference getStringToPageMapEntry_Value();

	/**
	 * Gets the web factory.
	 * @return the web factory
	 */
	WebFactory getWebFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The WEB. */
		EClass WEB = eINSTANCE.getWeb();

		/** The WE b__ hom e_ page. */
		EAttribute WEB__HOME_PAGE = eINSTANCE.getWeb_HomePage();

		/** The WE b__ folders. */
		EReference WEB__FOLDERS = eINSTANCE.getWeb_Folders();

		/** The PAGE. */
		EClass PAGE = eINSTANCE.getPage();

		/** The PAG e__ address. */
		EAttribute PAGE__ADDRESS = eINSTANCE.getPage_Address();

		/** The PAG e__ parent. */
		EReference PAGE__PARENT = eINSTANCE.getPage_Parent();

		/** The FOLDER. */
		EClass FOLDER = eINSTANCE.getFolder();

		/** The FOLDE r__ folders. */
		EReference FOLDER__FOLDERS = eINSTANCE.getFolder_Folders();

		/** The FOLDE r__ pages. */
		EReference FOLDER__PAGES = eINSTANCE.getFolder_Pages();

		/** The STRIN g_ t o_ folde r_ ma p_ entry. */
		EClass STRING_TO_FOLDER_MAP_ENTRY = eINSTANCE.getStringToFolderMapEntry();

		/** The STRIN g_ t o_ folde r_ ma p_ entr y__ key. */
		EAttribute STRING_TO_FOLDER_MAP_ENTRY__KEY = eINSTANCE.getStringToFolderMapEntry_Key();

		/** The STRIN g_ t o_ folde r_ ma p_ entr y__ value. */
		EReference STRING_TO_FOLDER_MAP_ENTRY__VALUE = eINSTANCE.getStringToFolderMapEntry_Value();

		/** The STRIN g_ t o_ pag e_ ma p_ entry. */
		EClass STRING_TO_PAGE_MAP_ENTRY = eINSTANCE.getStringToPageMapEntry();

		/** The STRIN g_ t o_ pag e_ ma p_ entr y__ key. */
		EAttribute STRING_TO_PAGE_MAP_ENTRY__KEY = eINSTANCE.getStringToPageMapEntry_Key();

		/** The STRIN g_ t o_ pag e_ ma p_ entr y__ value. */
		EReference STRING_TO_PAGE_MAP_ENTRY__VALUE = eINSTANCE.getStringToPageMapEntry_Value();

	}

} // WebPackage
