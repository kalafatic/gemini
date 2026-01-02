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
 * A representation of the model object '<em><b>Torrent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getName <em>Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationBy <em>Creation By</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getModifiedBy <em>Modified By</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getInfo <em>Info</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getAnnounceList <em>Announce List</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent()
 * @model
 * @generated
 */
public interface Torrent extends EObject {
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
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_CreationDate()
	 * @model
	 * @generated
	 */
	long getCreationDate();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(long value);

	/**
	 * Returns the value of the '<em><b>Creation By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation By</em>' attribute.
	 * @see #setCreationBy(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_CreationBy()
	 * @model
	 * @generated
	 */
	String getCreationBy();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationBy <em>Creation By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation By</em>' attribute.
	 * @see #getCreationBy()
	 * @generated
	 */
	void setCreationBy(String value);

	/**
	 * Returns the value of the '<em><b>Modified By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified By</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified By</em>' attribute.
	 * @see #setModifiedBy(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_ModifiedBy()
	 * @model
	 * @generated
	 */
	String getModifiedBy();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getModifiedBy <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified By</em>' attribute.
	 * @see #getModifiedBy()
	 * @generated
	 */
	void setModifiedBy(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding</em>' attribute.
	 * @see #setEncoding(String)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_Encoding()
	 * @model
	 * @generated
	 */
	String getEncoding();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(String value);

	/**
	 * Returns the value of the '<em><b>Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Info</em>' containment reference.
	 * @see #setInfo(Info)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_Info()
	 * @model containment="true" derived="true"
	 * @generated
	 */
	Info getInfo();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getInfo <em>Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Info</em>' containment reference.
	 * @see #getInfo()
	 * @generated
	 */
	void setInfo(Info value);

	/**
	 * Returns the value of the '<em><b>Announce List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Announce List</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Announce List</em>' attribute list.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrent_AnnounceList()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getAnnounceList();

} // Torrent
