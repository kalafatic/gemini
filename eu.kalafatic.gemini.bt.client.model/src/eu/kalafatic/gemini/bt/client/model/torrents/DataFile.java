/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getName <em>Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getMd5sum <em>Md5sum</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getPath <em>Path</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getLength <em>Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFile()
 * @model
 * @generated
 */
public interface DataFile extends DataFileInfo {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFile_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Md5sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Md5sum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Md5sum</em>' attribute.
	 * @see #setMd5sum(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFile_Md5sum()
	 * @model
	 * @generated
	 */
	String getMd5sum();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getMd5sum <em>Md5sum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Md5sum</em>' attribute.
	 * @see #getMd5sum()
	 * @generated
	 */
	void setMd5sum(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFile_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFile_Length()
	 * @model
	 * @generated
	 */
	long getLength();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(long value);

} // DataFile
