/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>EFinished Table Columns</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getEFinishedTableColumns()
 * @model
 * @generated
 */
public enum EFinishedTableColumns implements Enumerator {
	/**
	 * The '<em><b>ENABLED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENABLED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	ENABLED_COL(0, "ENABLED_COL", "Enabled"),

	/**
	 * The '<em><b>NAME COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAME_COL_VALUE
	 * @generated
	 * @ordered
	 */
	NAME_COL(1, "NAME_COL", "Name"),

	/**
	 * The '<em><b>STATUS COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATUS_COL_VALUE
	 * @generated
	 * @ordered
	 */
	STATUS_COL(2, "STATUS_COL", "Status"),

	/**
	 * The '<em><b>DOWNLOADERS COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADERS_COL_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOADERS_COL(3, "DOWNLOADERS_COL", "Downloaders"),

	/**
	 * The '<em><b>SIZE COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIZE_COL_VALUE
	 * @generated
	 * @ordered
	 */
	SIZE_COL(4, "SIZE_COL", "Size"),

	/**
	 * The '<em><b>PIECES COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PIECES_COL_VALUE
	 * @generated
	 * @ordered
	 */
	PIECES_COL(5, "PIECES_COL", "Pieces"),

	/**
	 * The '<em><b>UPLOADED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPLOADED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOADED_COL(6, "UPLOADED_COL", "Uploaded"),

	/**
	 * The '<em><b>UPLD SPEED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPLD_SPEED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	UPLD_SPEED_COL(7, "UPLD_SPEED_COL", "Upld speed");

	/**
	 * The '<em><b>ENABLED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ENABLED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENABLED_COL
	 * @model literal="Enabled"
	 * @generated
	 * @ordered
	 */
	public static final int ENABLED_COL_VALUE = 0;

	/**
	 * The '<em><b>NAME COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NAME COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NAME_COL
	 * @model literal="Name"
	 * @generated
	 * @ordered
	 */
	public static final int NAME_COL_VALUE = 1;

	/**
	 * The '<em><b>STATUS COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STATUS COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATUS_COL
	 * @model literal="Status"
	 * @generated
	 * @ordered
	 */
	public static final int STATUS_COL_VALUE = 2;

	/**
	 * The '<em><b>DOWNLOADERS COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOADERS COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADERS_COL
	 * @model literal="Downloaders"
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOADERS_COL_VALUE = 3;

	/**
	 * The '<em><b>SIZE COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SIZE COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIZE_COL
	 * @model literal="Size"
	 * @generated
	 * @ordered
	 */
	public static final int SIZE_COL_VALUE = 4;

	/**
	 * The '<em><b>PIECES COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PIECES COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PIECES_COL
	 * @model literal="Pieces"
	 * @generated
	 * @ordered
	 */
	public static final int PIECES_COL_VALUE = 5;

	/**
	 * The '<em><b>UPLOADED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLOADED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLOADED_COL
	 * @model literal="Uploaded"
	 * @generated
	 * @ordered
	 */
	public static final int UPLOADED_COL_VALUE = 6;

	/**
	 * The '<em><b>UPLD SPEED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLD SPEED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLD_SPEED_COL
	 * @model literal="Upld speed"
	 * @generated
	 * @ordered
	 */
	public static final int UPLD_SPEED_COL_VALUE = 7;

	/**
	 * An array of all the '<em><b>EFinished Table Columns</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EFinishedTableColumns[] VALUES_ARRAY =
		new EFinishedTableColumns[] {
			ENABLED_COL,
			NAME_COL,
			STATUS_COL,
			DOWNLOADERS_COL,
			SIZE_COL,
			PIECES_COL,
			UPLOADED_COL,
			UPLD_SPEED_COL,
		};

	/**
	 * A public read-only list of all the '<em><b>EFinished Table Columns</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EFinishedTableColumns> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>EFinished Table Columns</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EFinishedTableColumns get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EFinishedTableColumns result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EFinished Table Columns</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EFinishedTableColumns getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EFinishedTableColumns result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EFinished Table Columns</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EFinishedTableColumns get(int value) {
		switch (value) {
			case ENABLED_COL_VALUE: return ENABLED_COL;
			case NAME_COL_VALUE: return NAME_COL;
			case STATUS_COL_VALUE: return STATUS_COL;
			case DOWNLOADERS_COL_VALUE: return DOWNLOADERS_COL;
			case SIZE_COL_VALUE: return SIZE_COL;
			case PIECES_COL_VALUE: return PIECES_COL;
			case UPLOADED_COL_VALUE: return UPLOADED_COL;
			case UPLD_SPEED_COL_VALUE: return UPLD_SPEED_COL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EFinishedTableColumns(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //EFinishedTableColumns
