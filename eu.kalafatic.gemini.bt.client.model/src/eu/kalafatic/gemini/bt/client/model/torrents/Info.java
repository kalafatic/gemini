/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieceLength <em>Piece Length</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#isPrivate <em>Private</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getHash <em>Hash</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getFiles <em>Files</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getMaxPiecesPerFile <em>Max Pieces Per File</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo()
 * @model
 * @generated
 */
public interface Info extends EObject {
	/**
	 * Returns the value of the '<em><b>Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pieces</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pieces</em>' attribute.
	 * @see #setPieces(byte[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_Pieces()
	 * @model
	 * @generated
	 */
	byte[] getPieces();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieces <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pieces</em>' attribute.
	 * @see #getPieces()
	 * @generated
	 */
	void setPieces(byte[] value);

	/**
	 * Returns the value of the '<em><b>Piece Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Piece Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Piece Length</em>' attribute.
	 * @see #setPieceLength(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_PieceLength()
	 * @model
	 * @generated
	 */
	int getPieceLength();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieceLength <em>Piece Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Piece Length</em>' attribute.
	 * @see #getPieceLength()
	 * @generated
	 */
	void setPieceLength(int value);

	/**
	 * Returns the value of the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Private</em>' attribute.
	 * @see #setPrivate(boolean)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_Private()
	 * @model
	 * @generated
	 */
	boolean isPrivate();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #isPrivate()
	 * @generated
	 */
	void setPrivate(boolean value);

	/**
	 * Returns the value of the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hash</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash</em>' attribute.
	 * @see #setHash(byte[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_Hash()
	 * @model
	 * @generated
	 */
	byte[] getHash();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getHash <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash</em>' attribute.
	 * @see #getHash()
	 * @generated
	 */
	void setHash(byte[] value);

	/**
	 * Returns the value of the '<em><b>Files</b></em>' containment reference list.
	 * The list contents are of type {@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Files</em>' containment reference list.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_Files()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataFile> getFiles();

	/**
	 * Returns the value of the '<em><b>Max Pieces Per File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Pieces Per File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Pieces Per File</em>' attribute.
	 * @see #setMaxPiecesPerFile(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getInfo_MaxPiecesPerFile()
	 * @model
	 * @generated
	 */
	int getMaxPiecesPerFile();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getMaxPiecesPerFile <em>Max Pieces Per File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Pieces Per File</em>' attribute.
	 * @see #getMaxPiecesPerFile()
	 * @generated
	 */
	void setMaxPiecesPerFile(int value);

} // Info
