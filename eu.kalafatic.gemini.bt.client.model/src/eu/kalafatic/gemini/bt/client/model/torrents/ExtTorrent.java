/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ext Torrent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRow <em>Row</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isFinished <em>Finished</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getStatus <em>Status</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRealBitfield <em>Real Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getModelBitfield <em>Model Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getHandshake <em>Handshake</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getAdditionalInfo <em>Additional Info</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getPath <em>Path</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent()
 * @model
 * @generated
 */
public interface ExtTorrent extends Torrent {
	/**
	 * Returns the value of the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row</em>' attribute.
	 * @see #setRow(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Row()
	 * @model
	 * @generated
	 */
	int getRow();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRow <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row</em>' attribute.
	 * @see #getRow()
	 * @generated
	 */
	void setRow(int value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Enabled()
	 * @model default="false"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Finished</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finished</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finished</em>' attribute.
	 * @see #setFinished(boolean)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Finished()
	 * @model default="false"
	 * @generated
	 */
	boolean isFinished();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isFinished <em>Finished</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finished</em>' attribute.
	 * @see #isFinished()
	 * @generated
	 */
	void setFinished(boolean value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(Enumerator)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Status()
	 * @model transient="true"
	 * @generated
	 */
	Enumerator getStatus();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(Enumerator value);

	/**
	 * Returns the value of the '<em><b>Real Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real Bitfield</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real Bitfield</em>' attribute.
	 * @see #setRealBitfield(byte[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_RealBitfield()
	 * @model
	 * @generated
	 */
	byte[] getRealBitfield();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRealBitfield <em>Real Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real Bitfield</em>' attribute.
	 * @see #getRealBitfield()
	 * @generated
	 */
	void setRealBitfield(byte[] value);

	/**
	 * Returns the value of the '<em><b>Model Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Bitfield</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Bitfield</em>' attribute.
	 * @see #setModelBitfield(boolean[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_ModelBitfield()
	 * @model dataType="eu.kalafatic.gemini.bt.client.model.torrents.EBooleanArray" transient="true"
	 * @generated
	 */
	boolean[] getModelBitfield();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getModelBitfield <em>Model Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Bitfield</em>' attribute.
	 * @see #getModelBitfield()
	 * @generated
	 */
	void setModelBitfield(boolean[] value);

	/**
	 * Returns the value of the '<em><b>Handshake</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handshake</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handshake</em>' attribute.
	 * @see #setHandshake(byte[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Handshake()
	 * @model transient="true"
	 * @generated
	 */
	byte[] getHandshake();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getHandshake <em>Handshake</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handshake</em>' attribute.
	 * @see #getHandshake()
	 * @generated
	 */
	void setHandshake(byte[] value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see #setLock(ReentrantReadWriteLock)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Lock()
	 * @model dataType="eu.kalafatic.gemini.bt.client.model.torrents.Lock" transient="true"
	 * @generated
	 */
	ReentrantReadWriteLock getLock();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ReentrantReadWriteLock value);

	/**
	 * Returns the value of the '<em><b>Additional Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additional Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additional Info</em>' containment reference.
	 * @see #setAdditionalInfo(AdditionalInfo)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_AdditionalInfo()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AdditionalInfo getAdditionalInfo();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getAdditionalInfo <em>Additional Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Additional Info</em>' containment reference.
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	void setAdditionalInfo(AdditionalInfo value);

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
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map</em>' attribute.
	 * @see #setMap(Map)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getExtTorrent_Map()
	 * @model transient="true"
	 * @generated
	 */
	Map<?, ?> getMap();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getMap <em>Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map</em>' attribute.
	 * @see #getMap()
	 * @generated
	 */
	void setMap(Map<?, ?> value);

} // ExtTorrent
