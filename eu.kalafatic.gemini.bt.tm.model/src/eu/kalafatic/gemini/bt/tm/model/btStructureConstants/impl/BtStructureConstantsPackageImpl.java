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
package eu.kalafatic.gemini.bt.tm.model.btStructureConstants.impl;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.impl.BtStructurePackageImpl;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.EBlockSize;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.ETMMessages;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.ETorrentStructure;

/**
 * The Class class BtStructureConstantsPackageImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BtStructureConstantsPackageImpl extends EPackageImpl implements BtStructureConstantsPackage {

	/** The e torrent structure e enum. */
	private EEnum eTorrentStructureEEnum = null;

	/** The e block size e enum. */
	private EEnum eBlockSizeEEnum = null;

	/** The etm messages e enum. */
	private EEnum etmMessagesEEnum = null;

	/**
	 * Instantiates a new bt structure constants package impl.
	 */
	private BtStructureConstantsPackageImpl() {
		super(eNS_URI, BtStructureConstantsFactory.eINSTANCE);
	}

	/** The is inited. */
	private static boolean isInited = false;

	/**
	 * Inits the.
	 * @return the bt structure constants package
	 */
	public static BtStructureConstantsPackage init() {
		if (isInited)
			return (BtStructureConstantsPackage) EPackage.Registry.INSTANCE.getEPackage(BtStructureConstantsPackage.eNS_URI);

		// Obtain or create and register package
		BtStructureConstantsPackageImpl theBtStructureConstantsPackage = (BtStructureConstantsPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BtStructureConstantsPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new BtStructureConstantsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TorrentsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BtStructurePackageImpl theBtStructurePackage = (BtStructurePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(BtStructurePackage.eNS_URI) instanceof BtStructurePackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(BtStructurePackage.eNS_URI) : BtStructurePackage.eINSTANCE);

		// Create package meta-data objects
		theBtStructureConstantsPackage.createPackageContents();
		theBtStructurePackage.createPackageContents();

		// Initialize created meta-data
		theBtStructureConstantsPackage.initializePackageContents();
		theBtStructurePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBtStructureConstantsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BtStructureConstantsPackage.eNS_URI, theBtStructureConstantsPackage);
		return theBtStructureConstantsPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage#getETorrentStructure()
	 */
	public EEnum getETorrentStructure() {
		return eTorrentStructureEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage#getEBlockSize()
	 */
	public EEnum getEBlockSize() {
		return eBlockSizeEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage#getETMMessages()
	 */
	public EEnum getETMMessages() {
		return etmMessagesEEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage#getBtStructureConstantsFactory()
	 */
	public BtStructureConstantsFactory getBtStructureConstantsFactory() {
		return (BtStructureConstantsFactory) getEFactoryInstance();
	}

	/** The is created. */
	private boolean isCreated = false;

	/**
	 * Creates the package contents.
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create enums
		eTorrentStructureEEnum = createEEnum(ETORRENT_STRUCTURE);
		eBlockSizeEEnum = createEEnum(EBLOCK_SIZE);
		etmMessagesEEnum = createEEnum(ETM_MESSAGES);
	}

	/** The is initialized. */
	private boolean isInitialized = false;

	/**
	 * Initialize package contents.
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Initialize enums and add enum literals
		initEEnum(eTorrentStructureEEnum, ETorrentStructure.class, "ETorrentStructure");
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.ANNOUNCE);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.ANNOUNCE_LIST);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.CREATED_BY);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.CREATION_DATE);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.ENCODING);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.INFO);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.NAME);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.FILES);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.LENGTH);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.PATH);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.PIECES);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.PIECE_LENGTH);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.PRIVATE);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.MD5SUM);
		addEEnumLiteral(eTorrentStructureEEnum, ETorrentStructure.COMMENT);

		initEEnum(eBlockSizeEEnum, EBlockSize.class, "EBlockSize");
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PFOUR);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PFIVE);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PSIX);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PSEVEN);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PEIGHT);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PNINE);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PTEN);
		addEEnumLiteral(eBlockSizeEEnum, EBlockSize.PELEVEN);

		initEEnum(etmMessagesEEnum, ETMMessages.class, "ETMMessages");
		addEEnumLiteral(etmMessagesEEnum, ETMMessages.GENERATED);
	}

} // BtStructureConstantsPackageImpl
