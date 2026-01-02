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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>EClient Handshake Protocol</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getEClientHandshakeProtocol()
 * @model
 * @generated
 */
public enum EClientHandshakeProtocol implements Enumerator {
	/**
	 * The '<em><b>HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	HANDSHAKE(0, "HANDSHAKE", "HANDSHAKE"),

	/**
	 * The '<em><b>PROTOCOL LEN</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PROTOCOL_LEN_VALUE
	 * @generated
	 * @ordered
	 */
	PROTOCOL_LEN(1, "PROTOCOL_LEN", "PROTOCOL_LEN"),

	/**
	 * The '<em><b>PROTOCOL NAME</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PROTOCOL_NAME_VALUE
	 * @generated
	 * @ordered
	 */
	PROTOCOL_NAME(2, "PROTOCOL_NAME", "PROTOCOL_NAME"),

	/**
	 * The '<em><b>RESERVED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #RESERVED_VALUE
	 * @generated
	 * @ordered
	 */
	RESERVED(3, "RESERVED", "RESERVED"),

	/**
	 * The '<em><b>INFO HASH</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INFO_HASH_VALUE
	 * @generated
	 * @ordered
	 */
	INFO_HASH(4, "INFO_HASH", "INFO_HASH"),

	/**
	 * The '<em><b>PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	PEER_ID(5, "PEER_ID", "PEER_ID");

	/**
	 * The '<em><b>HANDSHAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HANDSHAKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HANDSHAKE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HANDSHAKE_VALUE = 0;

	/**
	 * The '<em><b>PROTOCOL LEN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROTOCOL LEN</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROTOCOL_LEN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROTOCOL_LEN_VALUE = 1;

	/**
	 * The '<em><b>PROTOCOL NAME</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROTOCOL NAME</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROTOCOL_NAME
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROTOCOL_NAME_VALUE = 2;

	/**
	 * The '<em><b>RESERVED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESERVED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESERVED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESERVED_VALUE = 3;

	/**
	 * The '<em><b>INFO HASH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INFO HASH</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INFO_HASH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INFO_HASH_VALUE = 4;

	/**
	 * The '<em><b>PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_ID
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PEER_ID_VALUE = 5;

	/**
	 * An array of all the '<em><b>EClient Handshake Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final EClientHandshakeProtocol[] VALUES_ARRAY = new EClientHandshakeProtocol[] {
			HANDSHAKE,
			PROTOCOL_LEN,
			PROTOCOL_NAME,
			RESERVED,
			INFO_HASH,
			PEER_ID,
		};

	/**
	 * A public read-only list of all the '<em><b>EClient Handshake Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EClientHandshakeProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>EClient Handshake Protocol</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static EClientHandshakeProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EClientHandshakeProtocol result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EClient Handshake Protocol</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static EClientHandshakeProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EClientHandshakeProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EClient Handshake Protocol</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static EClientHandshakeProtocol get(int value) {
		switch (value) {
			case HANDSHAKE_VALUE: return HANDSHAKE;
			case PROTOCOL_LEN_VALUE: return PROTOCOL_LEN;
			case PROTOCOL_NAME_VALUE: return PROTOCOL_NAME;
			case RESERVED_VALUE: return RESERVED;
			case INFO_HASH_VALUE: return INFO_HASH;
			case PEER_ID_VALUE: return PEER_ID;
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
	private EClientHandshakeProtocol(int value, String name, String literal) {
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

} // EClientHandshakeProtocol
