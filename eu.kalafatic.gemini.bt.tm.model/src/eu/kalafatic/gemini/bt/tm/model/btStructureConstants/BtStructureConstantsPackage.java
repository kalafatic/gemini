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
package eu.kalafatic.gemini.bt.tm.model.btStructureConstants;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * The Interface interface BtStructureConstantsPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface BtStructureConstantsPackage extends EPackage {

	/** The e name. */
	String eNAME = "btStructureConstants";

	/** The e n s_ uri. */
	String eNS_URI = "http:///btStructureConstants.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "btStructureConstants";

	/** The e instance. */
	BtStructureConstantsPackage eINSTANCE = eu.kalafatic.gemini.bt.tm.model.btStructureConstants.impl.BtStructureConstantsPackageImpl.init();

	/** The ETORREN t_ structure. */
	int ETORRENT_STRUCTURE = 0;

	/** The EBLOC k_ size. */
	int EBLOCK_SIZE = 1;

	/** The ET m_ messages. */
	int ETM_MESSAGES = 2;

	/**
	 * Gets the e torrent structure.
	 * @return the e torrent structure
	 */
	EEnum getETorrentStructure();

	/**
	 * Gets the e block size.
	 * @return the e block size
	 */
	EEnum getEBlockSize();

	/**
	 * Gets the eTM messages.
	 * @return the eTM messages
	 */
	EEnum getETMMessages();

	/**
	 * Gets the bt structure constants factory.
	 * @return the bt structure constants factory
	 */
	BtStructureConstantsFactory getBtStructureConstantsFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The ETORREN t_ structure. */
		EEnum ETORRENT_STRUCTURE = eINSTANCE.getETorrentStructure();

		/** The EBLOC k_ size. */
		EEnum EBLOCK_SIZE = eINSTANCE.getEBlockSize();

		/** The ET m_ messages. */
		EEnum ETM_MESSAGES = eINSTANCE.getETMMessages();

	}

} // BtStructureConstantsPackage
