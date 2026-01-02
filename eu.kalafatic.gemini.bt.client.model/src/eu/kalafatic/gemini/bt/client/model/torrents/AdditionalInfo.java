/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Additional Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getSeeds <em>Seeds</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getPeers <em>Peers</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaders <em>Downloaders</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaded <em>Downloaded</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getUploaded <em>Uploaded</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompleted <em>Completed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompletedPieces <em>Completed Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getFileSize <em>File Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getVerifyList <em>Verify List</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo()
 * @model
 * @generated
 */
public interface AdditionalInfo extends SpeedInfo {
	/**
	 * Returns the value of the '<em><b>Seeds</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seeds</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seeds</em>' attribute.
	 * @see #setSeeds(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Seeds()
	 * @model default="0" transient="true"
	 * @generated
	 */
	int getSeeds();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getSeeds <em>Seeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seeds</em>' attribute.
	 * @see #getSeeds()
	 * @generated
	 */
	void setSeeds(int value);

	/**
	 * Returns the value of the '<em><b>Peers</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Peers</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peers</em>' attribute.
	 * @see #setPeers(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Peers()
	 * @model default="0" transient="true"
	 * @generated
	 */
	int getPeers();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getPeers <em>Peers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peers</em>' attribute.
	 * @see #getPeers()
	 * @generated
	 */
	void setPeers(int value);

	/**
	 * Returns the value of the '<em><b>Downloaders</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downloaders</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downloaders</em>' attribute.
	 * @see #setDownloaders(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Downloaders()
	 * @model default="0" transient="true"
	 * @generated
	 */
	int getDownloaders();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaders <em>Downloaders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Downloaders</em>' attribute.
	 * @see #getDownloaders()
	 * @generated
	 */
	void setDownloaders(int value);

	/**
	 * Returns the value of the '<em><b>Downloaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Downloaded</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Downloaded</em>' attribute.
	 * @see #setDownloaded(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Downloaded()
	 * @model
	 * @generated
	 */
	long getDownloaded();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaded <em>Downloaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Downloaded</em>' attribute.
	 * @see #getDownloaded()
	 * @generated
	 */
	void setDownloaded(long value);

	/**
	 * Returns the value of the '<em><b>Uploaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uploaded</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uploaded</em>' attribute.
	 * @see #setUploaded(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Uploaded()
	 * @model
	 * @generated
	 */
	long getUploaded();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getUploaded <em>Uploaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uploaded</em>' attribute.
	 * @see #getUploaded()
	 * @generated
	 */
	void setUploaded(long value);

	/**
	 * Returns the value of the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completed</em>' attribute.
	 * @see #setCompleted(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Completed()
	 * @model
	 * @generated
	 */
	int getCompleted();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompleted <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed</em>' attribute.
	 * @see #getCompleted()
	 * @generated
	 */
	void setCompleted(int value);

	/**
	 * Returns the value of the '<em><b>Completed Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completed Pieces</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completed Pieces</em>' attribute.
	 * @see #setCompletedPieces(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_CompletedPieces()
	 * @model
	 * @generated
	 */
	int getCompletedPieces();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompletedPieces <em>Completed Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed Pieces</em>' attribute.
	 * @see #getCompletedPieces()
	 * @generated
	 */
	void setCompletedPieces(int value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>File Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Size</em>' attribute.
	 * @see #setFileSize(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_FileSize()
	 * @model
	 * @generated
	 */
	long getFileSize();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getFileSize <em>File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Size</em>' attribute.
	 * @see #getFileSize()
	 * @generated
	 */
	void setFileSize(long value);

	/**
	 * Returns the value of the '<em><b>Verify List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verify List</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verify List</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getAdditionalInfo_VerifyList()
	 * @model transient="true"
	 * @generated
	 */
	EList<Integer> getVerifyList();

} // AdditionalInfo
