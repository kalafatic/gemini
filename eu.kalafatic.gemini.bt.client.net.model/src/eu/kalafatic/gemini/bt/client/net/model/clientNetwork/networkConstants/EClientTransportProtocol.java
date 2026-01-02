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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>EClient Transport Protocol</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getEClientTransportProtocol()
 * @model
 * @generated
 */
public enum EClientTransportProtocol implements Enumerator {
	/**
	 * The '<em><b>CHOKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CHOKE_VALUE
	 * @generated
	 * @ordered
	 */
	CHOKE(0, "CHOKE", "0,0,0,1,0"),

	/**
	 * The '<em><b>UNCHOKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UNCHOKE_VALUE
	 * @generated
	 * @ordered
	 */
	UNCHOKE(1, "UNCHOKE", "0,0,0,1,1"),

	/**
	 * The '<em><b>INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	INTERESTED(2, "INTERESTED", "0,0,0,1,2"),

	/**
	 * The '<em><b>NOT INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NOT_INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_INTERESTED(3, "NOT_INTERESTED", "0,0,0,1,3"),

	/**
	 * The '<em><b>HAVE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #HAVE_VALUE
	 * @generated
	 * @ordered
	 */
	HAVE(4, "HAVE", "0,0,0,5,4"),

	/**
	 * The '<em><b>BITFIELD</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #BITFIELD_VALUE
	 * @generated
	 * @ordered
	 */
	BITFIELD(5, "BITFIELD", "0,0,0,1,5"),

	/**
	 * The '<em><b>REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #REQUEST_VALUE
	 * @generated
	 * @ordered
	 */
	REQUEST(6, "REQUEST", "0,0,0,13,6"),

	/**
	 * The '<em><b>PIECE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PIECE_VALUE
	 * @generated
	 * @ordered
	 */
	PIECE(7, "PIECE", "0,0,0,9,7"),

	/**
	 * The '<em><b>CANCEL</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CANCEL_VALUE
	 * @generated
	 * @ordered
	 */
	CANCEL(8, "CANCEL", "0,0,0,13,8"),

	/**
	 * The '<em><b>DHT PORT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DHT_PORT_VALUE
	 * @generated
	 * @ordered
	 */
	DHT_PORT(9, "DHT_PORT", "0,0,0,3,9"),

	/**
	 * The '<em><b>KEEP ALIVE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #KEEP_ALIVE_VALUE
	 * @generated
	 * @ordered
	 */
	KEEP_ALIVE(10, "KEEP_ALIVE", "0,0,0,0"),

	/**
	 * The '<em><b>LT EXT MESSAGE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #LT_EXT_MESSAGE_VALUE
	 * @generated
	 * @ordered
	 */
	LT_EXT_MESSAGE(11, "LT_EXT_MESSAGE", "LT_EXT_MESSAGE"),

	/**
	 * The '<em><b>HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	HANDSHAKE(84, "HANDSHAKE", "HANDSHAKE");

	/**
	 * The '<em><b>CHOKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CHOKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHOKE
	 * @model literal="0,0,0,1,0"
	 * @generated
	 * @ordered
	 */
	public static final int CHOKE_VALUE = 0;

	/**
	 * The '<em><b>UNCHOKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNCHOKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNCHOKE
	 * @model literal="0,0,0,1,1"
	 * @generated
	 * @ordered
	 */
	public static final int UNCHOKE_VALUE = 1;

	/**
	 * The '<em><b>INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERESTED
	 * @model literal="0,0,0,1,2"
	 * @generated
	 * @ordered
	 */
	public static final int INTERESTED_VALUE = 2;

	/**
	 * The '<em><b>NOT INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_INTERESTED
	 * @model literal="0,0,0,1,3"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_INTERESTED_VALUE = 3;

	/**
	 * The '<em><b>HAVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HAVE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HAVE
	 * @model literal="0,0,0,5,4"
	 * @generated
	 * @ordered
	 */
	public static final int HAVE_VALUE = 4;

	/**
	 * The '<em><b>BITFIELD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BITFIELD</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BITFIELD
	 * @model literal="0,0,0,1,5"
	 * @generated
	 * @ordered
	 */
	public static final int BITFIELD_VALUE = 5;

	/**
	 * The '<em><b>REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST
	 * @model literal="0,0,0,13,6"
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST_VALUE = 6;

	/**
	 * The '<em><b>PIECE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PIECE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PIECE
	 * @model literal="0,0,0,9,7"
	 * @generated
	 * @ordered
	 */
	public static final int PIECE_VALUE = 7;

	/**
	 * The '<em><b>CANCEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CANCEL</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CANCEL
	 * @model literal="0,0,0,13,8"
	 * @generated
	 * @ordered
	 */
	public static final int CANCEL_VALUE = 8;

	/**
	 * The '<em><b>DHT PORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DHT PORT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DHT_PORT
	 * @model literal="0,0,0,3,9"
	 * @generated
	 * @ordered
	 */
	public static final int DHT_PORT_VALUE = 9;

	/**
	 * The '<em><b>KEEP ALIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KEEP ALIVE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KEEP_ALIVE
	 * @model literal="0,0,0,0"
	 * @generated
	 * @ordered
	 */
	public static final int KEEP_ALIVE_VALUE = 10;

	/**
	 * The '<em><b>LT EXT MESSAGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LT EXT MESSAGE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LT_EXT_MESSAGE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LT_EXT_MESSAGE_VALUE = 11;

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
	public static final int HANDSHAKE_VALUE = 84;

	/**
	 * An array of all the '<em><b>EClient Transport Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final EClientTransportProtocol[] VALUES_ARRAY = new EClientTransportProtocol[] {
			CHOKE,
			UNCHOKE,
			INTERESTED,
			NOT_INTERESTED,
			HAVE,
			BITFIELD,
			REQUEST,
			PIECE,
			CANCEL,
			DHT_PORT,
			KEEP_ALIVE,
			LT_EXT_MESSAGE,
			HANDSHAKE,
		};

	/**
	 * A public read-only list of all the '<em><b>EClient Transport Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EClientTransportProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>EClient Transport Protocol</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static EClientTransportProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EClientTransportProtocol result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EClient Transport Protocol</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static EClientTransportProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EClientTransportProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EClient Transport Protocol</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static EClientTransportProtocol get(int value) {
		switch (value) {
			case CHOKE_VALUE: return CHOKE;
			case UNCHOKE_VALUE: return UNCHOKE;
			case INTERESTED_VALUE: return INTERESTED;
			case NOT_INTERESTED_VALUE: return NOT_INTERESTED;
			case HAVE_VALUE: return HAVE;
			case BITFIELD_VALUE: return BITFIELD;
			case REQUEST_VALUE: return REQUEST;
			case PIECE_VALUE: return PIECE;
			case CANCEL_VALUE: return CANCEL;
			case DHT_PORT_VALUE: return DHT_PORT;
			case KEEP_ALIVE_VALUE: return KEEP_ALIVE;
			case LT_EXT_MESSAGE_VALUE: return LT_EXT_MESSAGE;
			case HANDSHAKE_VALUE: return HANDSHAKE;
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
	private EClientTransportProtocol(int value, String name, String literal) {
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

} // EClientTransportProtocol
