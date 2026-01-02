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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>ETracker Response Protocol</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getETrackerResponseProtocol()
 * @model
 * @generated
 */
public enum ETrackerResponseProtocol implements Enumerator {
	/**
	 * The '<em><b>FAILURE REASON</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #FAILURE_REASON_VALUE
	 * @generated
	 * @ordered
	 */
	FAILURE_REASON(0, "FAILURE_REASON", "failure reason"),

	/**
	 * The '<em><b>WARNING MESSAGE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #WARNING_MESSAGE_VALUE
	 * @generated
	 * @ordered
	 */
	WARNING_MESSAGE(1, "WARNING_MESSAGE", "warning message"),

	/**
	 * The '<em><b>INTERVAL</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INTERVAL_VALUE
	 * @generated
	 * @ordered
	 */
	INTERVAL(2, "INTERVAL", "interval"),

	/**
	 * The '<em><b>MIN INTERVAL</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #MIN_INTERVAL_VALUE
	 * @generated
	 * @ordered
	 */
	MIN_INTERVAL(3, "MIN_INTERVAL", "min interval"),

	/**
	 * The '<em><b>TRACKER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKER_ID(4, "TRACKER_ID", "tracker id"),

	/**
	 * The '<em><b>COMPLETE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #COMPLETE_VALUE
	 * @generated
	 * @ordered
	 */
	COMPLETE(5, "COMPLETE", "complete"),

	/**
	 * The '<em><b>INCOMPLETE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #INCOMPLETE_VALUE
	 * @generated
	 * @ordered
	 */
	INCOMPLETE(6, "INCOMPLETE", "incomplete"),

	/**
	 * The '<em><b>PEERS</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEERS_VALUE
	 * @generated
	 * @ordered
	 */
	PEERS(7, "PEERS", "peers"),

	/**
	 * The '<em><b>PEER ID</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEER_ID_VALUE
	 * @generated
	 * @ordered
	 */
	PEER_ID(8, "PEER_ID", "peer_id"),

	/**
	 * The '<em><b>IP</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #IP_VALUE
	 * @generated
	 * @ordered
	 */
	IP(9, "IP", "ip"),

	/**
	 * The '<em><b>PORT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PORT_VALUE
	 * @generated
	 * @ordered
	 */
	PORT(10, "PORT", "port");

	/**
	 * The '<em><b>FAILURE REASON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FAILURE REASON</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAILURE_REASON
	 * @model literal="failure reason"
	 * @generated
	 * @ordered
	 */
	public static final int FAILURE_REASON_VALUE = 0;

	/**
	 * The '<em><b>WARNING MESSAGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WARNING MESSAGE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WARNING_MESSAGE
	 * @model literal="warning message"
	 * @generated
	 * @ordered
	 */
	public static final int WARNING_MESSAGE_VALUE = 1;

	/**
	 * The '<em><b>INTERVAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTERVAL</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERVAL
	 * @model literal="interval"
	 * @generated
	 * @ordered
	 */
	public static final int INTERVAL_VALUE = 2;

	/**
	 * The '<em><b>MIN INTERVAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MIN INTERVAL</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIN_INTERVAL
	 * @model literal="min interval"
	 * @generated
	 * @ordered
	 */
	public static final int MIN_INTERVAL_VALUE = 3;

	/**
	 * The '<em><b>TRACKER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKER_ID
	 * @model literal="tracker id"
	 * @generated
	 * @ordered
	 */
	public static final int TRACKER_ID_VALUE = 4;

	/**
	 * The '<em><b>COMPLETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLETE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLETE
	 * @model literal="complete"
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_VALUE = 5;

	/**
	 * The '<em><b>INCOMPLETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INCOMPLETE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCOMPLETE
	 * @model literal="incomplete"
	 * @generated
	 * @ordered
	 */
	public static final int INCOMPLETE_VALUE = 6;

	/**
	 * The '<em><b>PEERS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEERS</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEERS
	 * @model literal="peers"
	 * @generated
	 * @ordered
	 */
	public static final int PEERS_VALUE = 7;

	/**
	 * The '<em><b>PEER ID</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER ID</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_ID
	 * @model literal="peer_id"
	 * @generated
	 * @ordered
	 */
	public static final int PEER_ID_VALUE = 8;

	/**
	 * The '<em><b>IP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IP</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IP
	 * @model literal="ip"
	 * @generated
	 * @ordered
	 */
	public static final int IP_VALUE = 9;

	/**
	 * The '<em><b>PORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PORT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PORT
	 * @model literal="port"
	 * @generated
	 * @ordered
	 */
	public static final int PORT_VALUE = 10;

	/**
	 * An array of all the '<em><b>ETracker Response Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final ETrackerResponseProtocol[] VALUES_ARRAY = new ETrackerResponseProtocol[] {
			FAILURE_REASON,
			WARNING_MESSAGE,
			INTERVAL,
			MIN_INTERVAL,
			TRACKER_ID,
			COMPLETE,
			INCOMPLETE,
			PEERS,
			PEER_ID,
			IP,
			PORT,
		};

	/**
	 * A public read-only list of all the '<em><b>ETracker Response Protocol</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ETrackerResponseProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>ETracker Response Protocol</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static ETrackerResponseProtocol get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseProtocol result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Response Protocol</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerResponseProtocol getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerResponseProtocol result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Response Protocol</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * @generated
	 */
	public static ETrackerResponseProtocol get(int value) {
		switch (value) {
			case FAILURE_REASON_VALUE: return FAILURE_REASON;
			case WARNING_MESSAGE_VALUE: return WARNING_MESSAGE;
			case INTERVAL_VALUE: return INTERVAL;
			case MIN_INTERVAL_VALUE: return MIN_INTERVAL;
			case TRACKER_ID_VALUE: return TRACKER_ID;
			case COMPLETE_VALUE: return COMPLETE;
			case INCOMPLETE_VALUE: return INCOMPLETE;
			case PEERS_VALUE: return PEERS;
			case PEER_ID_VALUE: return PEER_ID;
			case IP_VALUE: return IP;
			case PORT_VALUE: return PORT;
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
	private ETrackerResponseProtocol(int value, String name, String literal) {
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

} // ETrackerResponseProtocol
