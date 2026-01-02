/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class NetworkConstantsFactoryImpl extends EFactoryImpl implements NetworkConstantsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static NetworkConstantsFactory init() {
		try {
			NetworkConstantsFactory theNetworkConstantsFactory = (NetworkConstantsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///networkConstants.ecore"); 
			if (theNetworkConstantsFactory != null) {
				return theNetworkConstantsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NetworkConstantsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkConstantsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NetworkConstantsPackage.ETRACKER_RESPONSE_CODE:
				return createETrackerResponseCodeFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ETRACKER_REQUEST_PROTOCOL:
				return createETrackerRequestProtocolFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ETRACKER_RESPONSE_PROTOCOL:
				return createETrackerResponseProtocolFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ECLIENT_HANDSHAKE_PROTOCOL:
				return createEClientHandshakeProtocolFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ECLIENT_TRANSPORT_PROTOCOL:
				return createEClientTransportProtocolFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ETRACKER_REQUEST_EVENT:
				return createETrackerRequestEventFromString(eDataType, initialValue);
			case NetworkConstantsPackage.EVIEWS_MESSAGES:
				return createEViewsMessagesFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ECLIENTS:
				return createEClientsFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ESTRATEGY:
				return createEStrategyFromString(eDataType, initialValue);
			case NetworkConstantsPackage.EALGORITHM:
				return createEAlgorithmFromString(eDataType, initialValue);
			case NetworkConstantsPackage.ERETURN:
				return createEReturnFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NetworkConstantsPackage.ETRACKER_RESPONSE_CODE:
				return convertETrackerResponseCodeToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ETRACKER_REQUEST_PROTOCOL:
				return convertETrackerRequestProtocolToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ETRACKER_RESPONSE_PROTOCOL:
				return convertETrackerResponseProtocolToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ECLIENT_HANDSHAKE_PROTOCOL:
				return convertEClientHandshakeProtocolToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ECLIENT_TRANSPORT_PROTOCOL:
				return convertEClientTransportProtocolToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ETRACKER_REQUEST_EVENT:
				return convertETrackerRequestEventToString(eDataType, instanceValue);
			case NetworkConstantsPackage.EVIEWS_MESSAGES:
				return convertEViewsMessagesToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ECLIENTS:
				return convertEClientsToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ESTRATEGY:
				return convertEStrategyToString(eDataType, instanceValue);
			case NetworkConstantsPackage.EALGORITHM:
				return convertEAlgorithmToString(eDataType, instanceValue);
			case NetworkConstantsPackage.ERETURN:
				return convertEReturnToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ETrackerResponseCode createETrackerResponseCodeFromString(EDataType eDataType, String initialValue) {
		ETrackerResponseCode result = ETrackerResponseCode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertETrackerResponseCodeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ETrackerRequestProtocol createETrackerRequestProtocolFromString(EDataType eDataType, String initialValue) {
		ETrackerRequestProtocol result = ETrackerRequestProtocol.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertETrackerRequestProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ETrackerResponseProtocol createETrackerResponseProtocolFromString(EDataType eDataType, String initialValue) {
		ETrackerResponseProtocol result = ETrackerResponseProtocol.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertETrackerResponseProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClientHandshakeProtocol createEClientHandshakeProtocolFromString(EDataType eDataType, String initialValue) {
		EClientHandshakeProtocol result = EClientHandshakeProtocol.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEClientHandshakeProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClientTransportProtocol createEClientTransportProtocolFromString(EDataType eDataType, String initialValue) {
		EClientTransportProtocol result = EClientTransportProtocol.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEClientTransportProtocolToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ETrackerRequestEvent createETrackerRequestEventFromString(EDataType eDataType, String initialValue) {
		ETrackerRequestEvent result = ETrackerRequestEvent.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertETrackerRequestEventToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EViewsMessages createEViewsMessagesFromString(EDataType eDataType, String initialValue) {
		EViewsMessages result = EViewsMessages.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEViewsMessagesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClients createEClientsFromString(EDataType eDataType, String initialValue) {
		EClients result = EClients.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEClientsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EStrategy createEStrategyFromString(EDataType eDataType, String initialValue) {
		EStrategy result = EStrategy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEStrategyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAlgorithm createEAlgorithmFromString(EDataType eDataType, String initialValue) {
		EAlgorithm result = EAlgorithm.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEAlgorithmToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReturn createEReturnFromString(EDataType eDataType, String initialValue) {
		EReturn result = EReturn.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEReturnToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkConstantsPackage getNetworkConstantsPackage() {
		return (NetworkConstantsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NetworkConstantsPackage getPackage() {
		return NetworkConstantsPackage.eINSTANCE;
	}

} // NetworkConstantsFactoryImpl
