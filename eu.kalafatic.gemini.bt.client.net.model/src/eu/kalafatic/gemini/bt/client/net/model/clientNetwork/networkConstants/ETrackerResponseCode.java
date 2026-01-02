/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>ETracker Response Code</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getETrackerResponseCode()
 * @model
 * @generated
 */
public enum ETrackerResponseCode implements Enumerator {
	/**
	 * The '<em><b>INVALID REQUEST TYPE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INVALID_REQUEST_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	INVALID_REQUEST_TYPE(100, "INVALID_REQUEST_TYPE", "Invalid request type: client request was not a HTTP GET"),

	/**
	 * The '<em><b>MISSING INFO HASH</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #MISSING_INFO_HASH_VALUE
	 * @generated
	 * @ordered
	 */
	MISSING_INFO_HASH(101, "MISSING_INFO_HASH", "Missing info_hash"),

	/**
	 * The '<em><b>MISSING PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #MISSING_PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	MISSING_PEER_ID(102, "MISSING_PEER_ID", "Missing peer_id"),

	/**
	 * The '<em><b>MISSING PORT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #MISSING_PORT_VALUE
	 * @generated
	 * @ordered
	 */
	MISSING_PORT(103, "MISSING_PORT", "Missing port"),

	/**
	 * The '<em><b>INVALID INFO HASH</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INVALID_INFO_HASH_VALUE
	 * @generated
	 * @ordered
	 */
	INVALID_INFO_HASH(150, "INVALID_INFO_HASH", "Invalid infohash: infohash is not 20 bytes long"),

	/**
	 * The '<em><b>INVALID PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INVALID_PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	INVALID_PEER_ID(151, "INVALID_PEER_ID", "Invalid peerid: peerid is not 20 bytes long"),

	/**
	 * The '<em><b>INVALID NUMWANT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INVALID_NUMWANT_VALUE
	 * @generated
	 * @ordered
	 */
	INVALID_NUMWANT(152, "INVALID_NUMWANT", "Invalid numwant. Client requested more peers than allowed by tracker"),

	/**
	 * The '<em><b>INFO HASH NOT FOUND</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INFO_HASH_NOT_FOUND_VALUE
	 * @generated
	 * @ordered
	 */
	INFO_HASH_NOT_FOUND(200, "INFO_HASH_NOT_FOUND", "info_hash not found in the database"),

	/**
	 * The '<em><b>FOUND</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #FOUND_VALUE
	 * @generated
	 * @ordered
	 */
	FOUND(302, "FOUND", "FOUND"),

	/**
	 * The '<em><b>BAD REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #BAD_REQUEST_VALUE
	 * @generated
	 * @ordered
	 */
	BAD_REQUEST(400, "BAD_REQUEST", "Bad Request"),

	/**
	 * The '<em><b>NOT FOUND</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NOT_FOUND_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_FOUND(404, "NOT_FOUND", "Not Found"),

	/**
	 * The '<em><b>EVENTLESS REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #EVENTLESS_REQUEST_VALUE
	 * @generated
	 * @ordered
	 */
	EVENTLESS_REQUEST(500, "EVENTLESS_REQUEST", "Client sent an eventless request before the specified time"),

	/**
	 * The '<em><b>GENERIC ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #GENERIC_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	GENERIC_ERROR(900, "GENERIC_ERROR", "Generic error");

	/**
	 * The '<em><b>INVALID REQUEST TYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INVALID REQUEST TYPE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INVALID_REQUEST_TYPE
	 * @model literal="Invalid request type: client request was not a HTTP GET"
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_REQUEST_TYPE_VALUE = 100;

	/**
	 * The '<em><b>MISSING INFO HASH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MISSING INFO HASH</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MISSING_INFO_HASH
	 * @model literal="Missing info_hash"
	 * @generated
	 * @ordered
	 */
	public static final int MISSING_INFO_HASH_VALUE = 101;

	/**
	 * The '<em><b>MISSING PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MISSING PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MISSING_PEER_ID
	 * @model literal="Missing peer_id"
	 * @generated
	 * @ordered
	 */
	public static final int MISSING_PEER_ID_VALUE = 102;

	/**
	 * The '<em><b>MISSING PORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MISSING PORT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MISSING_PORT
	 * @model literal="Missing port"
	 * @generated
	 * @ordered
	 */
	public static final int MISSING_PORT_VALUE = 103;

	/**
	 * The '<em><b>INVALID INFO HASH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INVALID INFO HASH</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INVALID_INFO_HASH
	 * @model literal="Invalid infohash: infohash is not 20 bytes long"
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_INFO_HASH_VALUE = 150;

	/**
	 * The '<em><b>INVALID PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INVALID PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INVALID_PEER_ID
	 * @model literal="Invalid peerid: peerid is not 20 bytes long"
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_PEER_ID_VALUE = 151;

	/**
	 * The '<em><b>INVALID NUMWANT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INVALID NUMWANT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INVALID_NUMWANT
	 * @model literal="Invalid numwant. Client requested more peers than allowed by tracker"
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_NUMWANT_VALUE = 152;

	/**
	 * The '<em><b>INFO HASH NOT FOUND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INFO HASH NOT FOUND</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INFO_HASH_NOT_FOUND
	 * @model literal="info_hash not found in the database"
	 * @generated
	 * @ordered
	 */
	public static final int INFO_HASH_NOT_FOUND_VALUE = 200;

	/**
	 * The '<em><b>FOUND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FOUND</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FOUND
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FOUND_VALUE = 302;

	/**
	 * The '<em><b>BAD REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BAD REQUEST</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BAD_REQUEST
	 * @model literal="Bad Request"
	 * @generated
	 * @ordered
	 */
	public static final int BAD_REQUEST_VALUE = 400;

	/**
	 * The '<em><b>NOT FOUND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT FOUND</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_FOUND
	 * @model literal="Not Found"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_FOUND_VALUE = 404;

	/**
	 * The '<em><b>EVENTLESS REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EVENTLESS REQUEST</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVENTLESS_REQUEST
	 * @model literal="Client sent an eventless request before the specified time"
	 * @generated
	 * @ordered
	 */
	public static final int EVENTLESS_REQUEST_VALUE = 500;

	/**
	 * The '<em><b>GENERIC ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GENERIC ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERIC_ERROR
	 * @model literal="Generic error"
	 * @generated
	 * @ordered
	 */
	public static final int GENERIC_ERROR_VALUE = 900;

	/**
	 * An array of all the '<em><b>ETracker Response Code</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final ETrackerResponseCode[] VALUES_ARRAY = new ETrackerResponseCode[] {
			INVALID_REQUEST_TYPE,
			MISSING_INFO_HASH,
			MISSING_PEER_ID,
			MISSING_PORT,
			INVALID_INFO_HASH,
			INVALID_PEER_ID,
			INVALID_NUMWANT,
			INFO_HASH_NOT_FOUND,
			FOUND,
			BAD_REQUEST,
			NOT_FOUND,
			EVENTLESS_REQUEST,
			GENERIC_ERROR,
		};

	/**
	 * A public read-only list of all the '<em><b>ETracker Response Code</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ETrackerResponseCode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>ETracker Response Code</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerResponseCode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseCode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Response Code</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerResponseCode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseCode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Response Code</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerResponseCode get(int value) {
		switch (value) {
			case INVALID_REQUEST_TYPE_VALUE: return INVALID_REQUEST_TYPE;
			case MISSING_INFO_HASH_VALUE: return MISSING_INFO_HASH;
			case MISSING_PEER_ID_VALUE: return MISSING_PEER_ID;
			case MISSING_PORT_VALUE: return MISSING_PORT;
			case INVALID_INFO_HASH_VALUE: return INVALID_INFO_HASH;
			case INVALID_PEER_ID_VALUE: return INVALID_PEER_ID;
			case INVALID_NUMWANT_VALUE: return INVALID_NUMWANT;
			case INFO_HASH_NOT_FOUND_VALUE: return INFO_HASH_NOT_FOUND;
			case FOUND_VALUE: return FOUND;
			case BAD_REQUEST_VALUE: return BAD_REQUEST;
			case NOT_FOUND_VALUE: return NOT_FOUND;
			case EVENTLESS_REQUEST_VALUE: return EVENTLESS_REQUEST;
			case GENERIC_ERROR_VALUE: return GENERIC_ERROR;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private ETrackerResponseCode(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} // ETrackerResponseCode
