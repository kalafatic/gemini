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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>ETracker Request Protocol</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getETrackerRequestProtocol()
 * @model
 * @generated
 */
public enum ETrackerRequestProtocol implements Enumerator {
	/**
	 * The '<em><b>ANNOUNCE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #ANNOUNCE_VALUE
	 * @generated
	 * @ordered
	 */
	ANNOUNCE(0, "ANNOUNCE", ""),

	/**
	 * The '<em><b>INFO HASH</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INFO_HASH_VALUE
	 * @generated
	 * @ordered
	 */
	INFO_HASH(1, "INFO_HASH", "?info_hash="),

	/**
	 * The '<em><b>PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	PEER_ID(2, "PEER_ID", "&peer_id="),

	/**
	 * The '<em><b>PORT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PORT_VALUE
	 * @generated
	 * @ordered
	 */
	PORT(3, "PORT", "&port="),

	/**
	 * The '<em><b>UPLOADED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UPLOADED_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOADED(4, "UPLOADED", "&uploaded="),

	/**
	 * The '<em><b>DOWNLOADED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DOWNLOADED_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOADED(5, "DOWNLOADED", "&downloaded="),

	/**
	 * The '<em><b>LEFT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT(6, "LEFT", "&left="),

	/**
	 * The '<em><b>COMPACT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #COMPACT_VALUE
	 * @generated
	 * @ordered
	 */
	COMPACT(7, "COMPACT", "&compact="),

	/**
	 * The '<em><b>NO PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NO_PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	NO_PEER_ID(8, "NO_PEER_ID", "&no_peer_id="),

	/**
	 * The '<em><b>IP</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #IP_VALUE
	 * @generated
	 * @ordered
	 */
	IP(9, "IP", "&ip="),

	/**
	 * The '<em><b>NUMWANT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NUMWANT_VALUE
	 * @generated
	 * @ordered
	 */
	NUMWANT(10, "NUMWANT", "&numwant="),

	/**
	 * The '<em><b>KEY</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #KEY_VALUE
	 * @generated
	 * @ordered
	 */
	KEY(11, "KEY", "&key="),

	/**
	 * The '<em><b>TRACKER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKER_ID(12, "TRACKER_ID", "&trackerid="),

	/**
	 * The '<em><b>EVENT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #EVENT_VALUE
	 * @generated
	 * @ordered
	 */
	EVENT(13, "EVENT", "&event=");

	/**
	 * The '<em><b>ANNOUNCE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ANNOUNCE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ANNOUNCE
	 * @model literal=""
	 * @generated
	 * @ordered
	 */
	public static final int ANNOUNCE_VALUE = 0;

	/**
	 * The '<em><b>INFO HASH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INFO HASH</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INFO_HASH
	 * @model literal="?info_hash="
	 * @generated
	 * @ordered
	 */
	public static final int INFO_HASH_VALUE = 1;

	/**
	 * The '<em><b>PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_ID
	 * @model literal="&peer_id="
	 * @generated
	 * @ordered
	 */
	public static final int PEER_ID_VALUE = 2;

	/**
	 * The '<em><b>PORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PORT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PORT
	 * @model literal="&port="
	 * @generated
	 * @ordered
	 */
	public static final int PORT_VALUE = 3;

	/**
	 * The '<em><b>UPLOADED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLOADED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLOADED
	 * @model literal="&uploaded="
	 * @generated
	 * @ordered
	 */
	public static final int UPLOADED_VALUE = 4;

	/**
	 * The '<em><b>DOWNLOADED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOADED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADED
	 * @model literal="&downloaded="
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOADED_VALUE = 5;

	/**
	 * The '<em><b>LEFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LEFT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT
	 * @model literal="&left="
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_VALUE = 6;

	/**
	 * The '<em><b>COMPACT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPACT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPACT
	 * @model literal="&compact="
	 * @generated
	 * @ordered
	 */
	public static final int COMPACT_VALUE = 7;

	/**
	 * The '<em><b>NO PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_PEER_ID
	 * @model literal="&no_peer_id="
	 * @generated
	 * @ordered
	 */
	public static final int NO_PEER_ID_VALUE = 8;

	/**
	 * The '<em><b>IP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IP</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IP
	 * @model literal="&ip="
	 * @generated
	 * @ordered
	 */
	public static final int IP_VALUE = 9;

	/**
	 * The '<em><b>NUMWANT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NUMWANT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NUMWANT
	 * @model literal="&numwant="
	 * @generated
	 * @ordered
	 */
	public static final int NUMWANT_VALUE = 10;

	/**
	 * The '<em><b>KEY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KEY</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KEY
	 * @model literal="&key="
	 * @generated
	 * @ordered
	 */
	public static final int KEY_VALUE = 11;

	/**
	 * The '<em><b>TRACKER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKER_ID
	 * @model literal="&trackerid="
	 * @generated
	 * @ordered
	 */
	public static final int TRACKER_ID_VALUE = 12;

	/**
	 * The '<em><b>EVENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EVENT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVENT
	 * @model literal="&event="
	 * @generated
	 * @ordered
	 */
	public static final int EVENT_VALUE = 13;

	/**
	 * An array of all the '<em><b>ETracker Request Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final ETrackerRequestProtocol[] VALUES_ARRAY = new ETrackerRequestProtocol[] {
			ANNOUNCE,
			INFO_HASH,
			PEER_ID,
			PORT,
			UPLOADED,
			DOWNLOADED,
			LEFT,
			COMPACT,
			NO_PEER_ID,
			IP,
			NUMWANT,
			KEY,
			TRACKER_ID,
			EVENT,
		};

	/**
	 * A public read-only list of all the '<em><b>ETracker Request Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ETrackerRequestProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>ETracker Request Protocol</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestProtocol result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Request Protocol</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Request Protocol</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestProtocol get(int value) {
		switch (value) {
			case ANNOUNCE_VALUE: return ANNOUNCE;
			case INFO_HASH_VALUE: return INFO_HASH;
			case PEER_ID_VALUE: return PEER_ID;
			case PORT_VALUE: return PORT;
			case UPLOADED_VALUE: return UPLOADED;
			case DOWNLOADED_VALUE: return DOWNLOADED;
			case LEFT_VALUE: return LEFT;
			case COMPACT_VALUE: return COMPACT;
			case NO_PEER_ID_VALUE: return NO_PEER_ID;
			case IP_VALUE: return IP;
			case NUMWANT_VALUE: return NUMWANT;
			case KEY_VALUE: return KEY;
			case TRACKER_ID_VALUE: return TRACKER_ID;
			case EVENT_VALUE: return EVENT;
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
	private ETrackerRequestProtocol(int value, String name, String literal) {
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

} // ETrackerRequestProtocol
