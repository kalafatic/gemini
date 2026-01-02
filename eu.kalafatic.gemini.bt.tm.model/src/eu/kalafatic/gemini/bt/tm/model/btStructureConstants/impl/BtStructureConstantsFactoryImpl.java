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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsFactory;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsPackage;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.EBlockSize;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.ETMMessages;
import eu.kalafatic.gemini.bt.tm.model.btStructureConstants.ETorrentStructure;

/**
 * The Class class BtStructureConstantsFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BtStructureConstantsFactoryImpl extends EFactoryImpl implements BtStructureConstantsFactory {

	/**
	 * Inits the.
	 * @return the bt structure constants factory
	 */
	public static BtStructureConstantsFactory init() {
		try {
			BtStructureConstantsFactory theBtStructureConstantsFactory = (BtStructureConstantsFactory) EPackage.Registry.INSTANCE.getEFactory("http:///btStructureConstants.ecore");
			if (theBtStructureConstantsFactory != null) {
				return theBtStructureConstantsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BtStructureConstantsFactoryImpl();
	}

	/**
	 * Instantiates a new bt structure constants factory impl.
	 */
	public BtStructureConstantsFactoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#create(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#createFromString(org.eclipse.emf.ecore.EDataType, java.lang.String)
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case BtStructureConstantsPackage.ETORRENT_STRUCTURE:
			return createETorrentStructureFromString(eDataType, initialValue);
		case BtStructureConstantsPackage.EBLOCK_SIZE:
			return createEBlockSizeFromString(eDataType, initialValue);
		case BtStructureConstantsPackage.ETM_MESSAGES:
			return createETMMessagesFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EFactoryImpl#convertToString(org.eclipse.emf.ecore.EDataType, java.lang.Object)
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case BtStructureConstantsPackage.ETORRENT_STRUCTURE:
			return convertETorrentStructureToString(eDataType, instanceValue);
		case BtStructureConstantsPackage.EBLOCK_SIZE:
			return convertEBlockSizeToString(eDataType, instanceValue);
		case BtStructureConstantsPackage.ETM_MESSAGES:
			return convertETMMessagesToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * Creates the e torrent structure from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e torrent structure
	 */
	public ETorrentStructure createETorrentStructureFromString(EDataType eDataType, String initialValue) {
		ETorrentStructure result = ETorrentStructure.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e torrent structure to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertETorrentStructureToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the e block size from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e block size
	 */
	public EBlockSize createEBlockSizeFromString(EDataType eDataType, String initialValue) {
		EBlockSize result = EBlockSize.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e block size to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertEBlockSizeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the etm messages from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the eTM messages
	 */
	public ETMMessages createETMMessagesFromString(EDataType eDataType, String initialValue) {
		ETMMessages result = ETMMessages.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert etm messages to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertETMMessagesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructureConstants.BtStructureConstantsFactory#getBtStructureConstantsPackage()
	 */
	public BtStructureConstantsPackage getBtStructureConstantsPackage() {
		return (BtStructureConstantsPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static BtStructureConstantsPackage getPackage() {
		return BtStructureConstantsPackage.eINSTANCE;
	}

} // BtStructureConstantsFactoryImpl
