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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * The Interface interface RcPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface RcPackage extends EPackage {

	/** The e name. */
	String eNAME = "rc";

	/** The e n s_ uri. */
	String eNS_URI = "http:///rc.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "rc";

	/** The e instance. */
	RcPackage eINSTANCE = eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl.RcPackageImpl.init();

	/** The RC. */
	int RC = 0;

	/** The R c__ sessio n_ map. */
	int RC__SESSION_MAP = 0;

	/** The R c__ tree. */
	int RC__TREE = 1;

	/** The R c_ featur e_ count. */
	int RC_FEATURE_COUNT = 2;

	/** The R c_ session. */
	int RC_SESSION = 1;

	/** The R c_ sessio n__ host. */
	int RC_SESSION__HOST = 0;

	/** The R c_ sessio n__ announce. */
	int RC_SESSION__ANNOUNCE = 1;

	/** The R c_ sessio n__ socket. */
	int RC_SESSION__SOCKET = 2;

	/** The R c_ sessio n__ state. */
	int RC_SESSION__STATE = 3;

	/** The R c_ sessio n__ torrents. */
	int RC_SESSION__TORRENTS = 4;

	/** The R c_ sessio n__ htt p_ exchange. */
	int RC_SESSION__HTTP_EXCHANGE = 2;

	/** The R c_ sessio n_ featur e_ count. */
	int RC_SESSION_FEATURE_COUNT = 5;

	/** The STRIN g_ t o_ sessio n_ ma p_ entry. */
	int STRING_TO_SESSION_MAP_ENTRY = 2;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ key. */
	int STRING_TO_SESSION_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ value. */
	int STRING_TO_SESSION_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_SESSION_MAP_ENTRY_FEATURE_COUNT = 2;

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

	/** The PAGE. */
	int PAGE = 6;

	/** The PAG e__ address. */
	int PAGE__ADDRESS = 0;

	/** The PAG e__ parent. */
	int PAGE__PARENT = 1;

	/** The PAG e_ featur e_ count. */
	int PAGE_FEATURE_COUNT = 2;

	/** The FOLDER. */
	int FOLDER = 5;

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

	/** The ER c_ sessio n_ state. */
	int ERC_SESSION_STATE = 7;

	/** The R c_ protocol. */
	int RC_PROTOCOL = 8;

	/** The ERCCMD. */
	int ERCCMD = 9;

	/** The SOCKET. */
	int SOCKET = 10;

	/**
	 * Gets the rC.
	 * @return the rC
	 */
	EClass getRC();

	/**
	 * Gets the r c_ session map.
	 * @return the r c_ session map
	 */
	EReference getRC_SessionMap();

	/**
	 * Gets the r c_ tree.
	 * @return the r c_ tree
	 */
	EReference getRC_Tree();

	/**
	 * Gets the rC session.
	 * @return the rC session
	 */
	EClass getRCSession();

	/**
	 * Gets the rC session_ host.
	 * @return the rC session_ host
	 */
	EAttribute getRCSession_Host();

	/**
	 * Gets the rC session_ announce.
	 * @return the rC session_ announce
	 */
	EAttribute getRCSession_Announce();

	/**
	 * Gets the rC session_ socket.
	 * @return the rC session_ socket
	 */
	EAttribute getRCSession_Socket();

	/**
	 * Gets the rC session_ state.
	 * @return the rC session_ state
	 */
	EAttribute getRCSession_State();

	/**
	 * Gets the rC session_ torrents.
	 * @return the rC session_ torrents
	 */
	EAttribute getRCSession_Torrents();

	/**
	 * Gets the rC session_ http exchange.
	 * @return the rC session_ http exchange
	 */
	EAttribute getRCSession_HttpExchange();

	/**
	 * Gets the string to session map entry.
	 * @return the string to session map entry
	 */
	EClass getStringToSessionMapEntry();

	/**
	 * Gets the string to session map entry_ key.
	 * @return the string to session map entry_ key
	 */
	EAttribute getStringToSessionMapEntry_Key();

	/**
	 * Gets the string to session map entry_ value.
	 * @return the string to session map entry_ value
	 */
	EReference getStringToSessionMapEntry_Value();

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
	 * Gets the eRC session state.
	 * @return the eRC session state
	 */
	EEnum getERCSessionState();

	/**
	 * Gets the rC protocol.
	 * @return the rC protocol
	 */
	EEnum getRCProtocol();

	/**
	 * Gets the eRCCMD.
	 * @return the eRCCMD
	 */
	EEnum getERCCMD();

	/**
	 * Gets the socket.
	 * @return the socket
	 */
	EDataType getSocket();

	/**
	 * Gets the rc factory.
	 * @return the rc factory
	 */
	RcFactory getRcFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The RC. */
		EClass RC = eINSTANCE.getRC();

		/** The R c__ sessio n_ map. */
		EReference RC__SESSION_MAP = eINSTANCE.getRC_SessionMap();

		/** The R c__ tree. */
		EReference RC__TREE = eINSTANCE.getRC_Tree();

		/** The R c_ session. */
		EClass RC_SESSION = eINSTANCE.getRCSession();

		/** The R c_ sessio n__ host. */
		EAttribute RC_SESSION__HOST = eINSTANCE.getRCSession_Host();

		/** The R c_ sessio n__ announce. */
		EAttribute RC_SESSION__ANNOUNCE = eINSTANCE.getRCSession_Announce();

		/** The R c_ sessio n__ socket. */
		EAttribute RC_SESSION__SOCKET = eINSTANCE.getRCSession_Socket();

		/** The R c_ sessio n__ state. */
		EAttribute RC_SESSION__STATE = eINSTANCE.getRCSession_State();

		/** The R c_ sessio n__ torrents. */
		EAttribute RC_SESSION__TORRENTS = eINSTANCE.getRCSession_Torrents();

		/** The R c_ sessio n__ htt p_ exchange. */
		EAttribute RC_SESSION__HTTP_EXCHANGE = eINSTANCE.getRCSession_HttpExchange();

		/** The STRIN g_ t o_ sessio n_ ma p_ entry. */
		EClass STRING_TO_SESSION_MAP_ENTRY = eINSTANCE.getStringToSessionMapEntry();

		/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ key. */
		EAttribute STRING_TO_SESSION_MAP_ENTRY__KEY = eINSTANCE.getStringToSessionMapEntry_Key();

		/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ value. */
		EReference STRING_TO_SESSION_MAP_ENTRY__VALUE = eINSTANCE.getStringToSessionMapEntry_Value();

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

		/** The FOLDER. */
		EClass FOLDER = eINSTANCE.getFolder();

		/** The FOLDE r__ folders. */
		EReference FOLDER__FOLDERS = eINSTANCE.getFolder_Folders();

		/** The FOLDE r__ pages. */
		EReference FOLDER__PAGES = eINSTANCE.getFolder_Pages();

		/** The PAGE. */
		EClass PAGE = eINSTANCE.getPage();

		/** The PAG e__ address. */
		EAttribute PAGE__ADDRESS = eINSTANCE.getPage_Address();

		/** The PAG e__ parent. */
		EReference PAGE__PARENT = eINSTANCE.getPage_Parent();

		/** The ER c_ sessio n_ state. */
		EEnum ERC_SESSION_STATE = eINSTANCE.getERCSessionState();

		/** The R c_ protocol. */
		EEnum RC_PROTOCOL = eINSTANCE.getRCProtocol();

		/** The ERCCMD. */
		EEnum ERCCMD = eINSTANCE.getERCCMD();

		/** The SOCKET. */
		EDataType SOCKET = eINSTANCE.getSocket();

	}

} // RcPackage
