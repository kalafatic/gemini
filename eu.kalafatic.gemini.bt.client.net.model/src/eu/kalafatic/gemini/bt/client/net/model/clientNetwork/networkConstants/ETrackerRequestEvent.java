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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>ETracker Request Event</b></em>', and utility methods for
 * working with them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getETrackerRequestEvent()
 * @model
 * @generated
 */
public enum ETrackerRequestEvent implements Enumerator {
	/**
	 * The '<em><b>STARTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #STARTED_VALUE
	 * @generated
	 * @ordered
	 */
	STARTED(0, "STARTED", "started"),

	/**
	 * The '<em><b>STOPPED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #STOPPED_VALUE
	 * @generated
	 * @ordered
	 */
	STOPPED(1, "STOPPED", "stopped"),

	/**
	 * The '<em><b>COMPLETED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #COMPLETED_VALUE
	 * @generated
	 * @ordered
	 */
	COMPLETED(2, "COMPLETED", "completed");

	/**
	 * The '<em><b>STARTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STARTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTED
	 * @model literal="started"
	 * @generated
	 * @ordered
	 */
	public static final int STARTED_VALUE = 0;

	/**
	 * The '<em><b>STOPPED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STOPPED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOPPED
	 * @model literal="stopped"
	 * @generated
	 * @ordered
	 */
	public static final int STOPPED_VALUE = 1;

	/**
	 * The '<em><b>COMPLETED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLETED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLETED
	 * @model literal="completed"
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETED_VALUE = 2;

	/**
	 * An array of all the '<em><b>ETracker Request Event</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final ETrackerRequestEvent[] VALUES_ARRAY = new ETrackerRequestEvent[] {
			STARTED,
			STOPPED,
			COMPLETED,
		};

	/**
	 * A public read-only list of all the '<em><b>ETracker Request Event</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ETrackerRequestEvent> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>ETracker Request Event</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestEvent get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestEvent result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Request Event</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestEvent getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETrackerRequestEvent result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETracker Request Event</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static ETrackerRequestEvent get(int value) {
		switch (value) {
			case STARTED_VALUE: return STARTED;
			case STOPPED_VALUE: return STOPPED;
			case COMPLETED_VALUE: return COMPLETED;
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
	private ETrackerRequestEvent(int value, String name, String literal) {
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

} // ETrackerRequestEvent
