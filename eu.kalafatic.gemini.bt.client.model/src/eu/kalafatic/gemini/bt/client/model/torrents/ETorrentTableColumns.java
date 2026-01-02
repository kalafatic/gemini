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
 * A representation of the literals of the enumeration '<em><b>ETorrent Table Columns</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getETorrentTableColumns()
 * @model
 * @generated
 */
public enum ETorrentTableColumns implements Enumerator {
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
	 * The '<em><b>SEEDS COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEEDS_COL_VALUE
	 * @generated
	 * @ordered
	 */
	SEEDS_COL(3, "SEEDS_COL", "Seeds"),

	/**
	 * The '<em><b>PEERS COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PEERS_COL_VALUE
	 * @generated
	 * @ordered
	 */
	PEERS_COL(4, "PEERS_COL", "Peers"),

	/**
	 * The '<em><b>DOWNLOADERS COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADERS_COL_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOADERS_COL(5, "DOWNLOADERS_COL", "Downloaders"),

	/**
	 * The '<em><b>SIZE COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIZE_COL_VALUE
	 * @generated
	 * @ordered
	 */
	SIZE_COL(6, "SIZE_COL", "Size"),

	/**
	 * The '<em><b>PIECES COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PIECES_COL_VALUE
	 * @generated
	 * @ordered
	 */
	PIECES_COL(7, "PIECES_COL", "Pieces"),

	/**
	 * The '<em><b>DOWNLOADED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOADED_COL(8, "DOWNLOADED_COL", "Downloaded"),

	/**
	 * The '<em><b>COMPLETED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPLETED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	COMPLETED_COL(9, "COMPLETED_COL", "Percent"),

	/**
	 * The '<em><b>UPLOADED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPLOADED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOADED_COL(10, "UPLOADED_COL", "Uploaded"),

	/**
	 * The '<em><b>DWNLD SPEED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DWNLD_SPEED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	DWNLD_SPEED_COL(11, "DWNLD_SPEED_COL", "Dwnld speed"),

	/**
	 * The '<em><b>UPLD SPEED COL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPLD_SPEED_COL_VALUE
	 * @generated
	 * @ordered
	 */
	UPLD_SPEED_COL(12, "UPLD_SPEED_COL", "Upld speed");

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
	 * The '<em><b>SEEDS COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEEDS COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEEDS_COL
	 * @model literal="Seeds"
	 * @generated
	 * @ordered
	 */
	public static final int SEEDS_COL_VALUE = 3;

	/**
	 * The '<em><b>PEERS COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEERS COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEERS_COL
	 * @model literal="Peers"
	 * @generated
	 * @ordered
	 */
	public static final int PEERS_COL_VALUE = 4;

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
	public static final int DOWNLOADERS_COL_VALUE = 5;

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
	public static final int SIZE_COL_VALUE = 6;

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
	public static final int PIECES_COL_VALUE = 7;

	/**
	 * The '<em><b>DOWNLOADED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOADED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADED_COL
	 * @model literal="Downloaded"
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOADED_COL_VALUE = 8;

	/**
	 * The '<em><b>COMPLETED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLETED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLETED_COL
	 * @model literal="Percent"
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETED_COL_VALUE = 9;

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
	public static final int UPLOADED_COL_VALUE = 10;

	/**
	 * The '<em><b>DWNLD SPEED COL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DWNLD SPEED COL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DWNLD_SPEED_COL
	 * @model literal="Dwnld speed"
	 * @generated
	 * @ordered
	 */
	public static final int DWNLD_SPEED_COL_VALUE = 11;

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
	public static final int UPLD_SPEED_COL_VALUE = 12;

	/**
	 * An array of all the '<em><b>ETorrent Table Columns</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ETorrentTableColumns[] VALUES_ARRAY =
		new ETorrentTableColumns[] {
			ENABLED_COL,
			NAME_COL,
			STATUS_COL,
			SEEDS_COL,
			PEERS_COL,
			DOWNLOADERS_COL,
			SIZE_COL,
			PIECES_COL,
			DOWNLOADED_COL,
			COMPLETED_COL,
			UPLOADED_COL,
			DWNLD_SPEED_COL,
			UPLD_SPEED_COL,
		};

	/**
	 * A public read-only list of all the '<em><b>ETorrent Table Columns</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ETorrentTableColumns> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>ETorrent Table Columns</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ETorrentTableColumns get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETorrentTableColumns result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETorrent Table Columns</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ETorrentTableColumns getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ETorrentTableColumns result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>ETorrent Table Columns</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ETorrentTableColumns get(int value) {
		switch (value) {
			case ENABLED_COL_VALUE: return ENABLED_COL;
			case NAME_COL_VALUE: return NAME_COL;
			case STATUS_COL_VALUE: return STATUS_COL;
			case SEEDS_COL_VALUE: return SEEDS_COL;
			case PEERS_COL_VALUE: return PEERS_COL;
			case DOWNLOADERS_COL_VALUE: return DOWNLOADERS_COL;
			case SIZE_COL_VALUE: return SIZE_COL;
			case PIECES_COL_VALUE: return PIECES_COL;
			case DOWNLOADED_COL_VALUE: return DOWNLOADED_COL;
			case COMPLETED_COL_VALUE: return COMPLETED_COL;
			case UPLOADED_COL_VALUE: return UPLOADED_COL;
			case DWNLD_SPEED_COL_VALUE: return DWNLD_SPEED_COL;
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
	private ETorrentTableColumns(int value, String name, String literal) {
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
	
} //ETorrentTableColumns
