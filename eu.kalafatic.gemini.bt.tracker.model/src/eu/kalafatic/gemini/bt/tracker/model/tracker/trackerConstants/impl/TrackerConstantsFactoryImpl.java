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
package eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerRequestProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseCode;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.ETrackerResponseProtocol;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.EViewMessages;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsFactory;
import eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsPackage;

/**
 * The Class class TrackerConstantsFactoryImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackerConstantsFactoryImpl extends EFactoryImpl implements TrackerConstantsFactory {

	/**
	 * Inits the.
	 * @return the tracker constants factory
	 */
	public static TrackerConstantsFactory init() {
		try {
			TrackerConstantsFactory theTrackerConstantsFactory = (TrackerConstantsFactory) EPackage.Registry.INSTANCE.getEFactory("http:///trackerConstants.ecore");
			if (theTrackerConstantsFactory != null) {
				return theTrackerConstantsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TrackerConstantsFactoryImpl();
	}

	/**
	 * Instantiates a new tracker constants factory impl.
	 */
	public TrackerConstantsFactoryImpl() {
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
		case TrackerConstantsPackage.EVIEW_MESSAGES:
			return createEViewMessagesFromString(eDataType, initialValue);
		case TrackerConstantsPackage.ETRACKER_REQUEST_PROTOCOL:
			return createETrackerRequestProtocolFromString(eDataType, initialValue);
		case TrackerConstantsPackage.ETRACKER_RESPONSE_PROTOCOL:
			return createETrackerResponseProtocolFromString(eDataType, initialValue);
		case TrackerConstantsPackage.ETRACKER_RESPONSE_CODE:
			return createETrackerResponseCodeFromString(eDataType, initialValue);
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
		case TrackerConstantsPackage.EVIEW_MESSAGES:
			return convertEViewMessagesToString(eDataType, instanceValue);
		case TrackerConstantsPackage.ETRACKER_REQUEST_PROTOCOL:
			return convertETrackerRequestProtocolToString(eDataType, instanceValue);
		case TrackerConstantsPackage.ETRACKER_RESPONSE_PROTOCOL:
			return convertETrackerResponseProtocolToString(eDataType, instanceValue);
		case TrackerConstantsPackage.ETRACKER_RESPONSE_CODE:
			return convertETrackerResponseCodeToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * Creates the e view messages from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e view messages
	 */
	public EViewMessages createEViewMessagesFromString(EDataType eDataType, String initialValue) {
		EViewMessages result = EViewMessages.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e view messages to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertEViewMessagesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the e tracker request protocol from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e tracker request protocol
	 */
	public ETrackerRequestProtocol createETrackerRequestProtocolFromString(EDataType eDataType, String initialValue) {
		ETrackerRequestProtocol result = ETrackerRequestProtocol.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e tracker request protocol to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertETrackerRequestProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the e tracker response protocol from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e tracker response protocol
	 */
	public ETrackerResponseProtocol createETrackerResponseProtocolFromString(EDataType eDataType, String initialValue) {
		ETrackerResponseProtocol result = ETrackerResponseProtocol.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e tracker response protocol to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertETrackerResponseProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * Creates the e tracker response code from string.
	 * @param eDataType the e data type
	 * @param initialValue the initial value
	 * @return the e tracker response code
	 */
	public ETrackerResponseCode createETrackerResponseCodeFromString(EDataType eDataType, String initialValue) {
		ETrackerResponseCode result = ETrackerResponseCode.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * Convert e tracker response code to string.
	 * @param eDataType the e data type
	 * @param instanceValue the instance value
	 * @return the string
	 */
	public String convertETrackerResponseCodeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.model.tracker.trackerConstants.TrackerConstantsFactory#getTrackerConstantsPackage()
	 */
	public TrackerConstantsPackage getTrackerConstantsPackage() {
		return (TrackerConstantsPackage) getEPackage();
	}

	/**
	 * Gets the package.
	 * @return the package
	 */
	@Deprecated
	public static TrackerConstantsPackage getPackage() {
		return TrackerConstantsPackage.eINSTANCE;
	}

} // TrackerConstantsFactoryImpl
