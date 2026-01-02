/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data File Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getIndex <em>Index</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getBegin <em>Begin</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getEnd <em>End</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRaf <em>Raf</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRecorded <em>Recorded</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo()
 * @model
 * @generated
 */
public interface DataFileInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Index()
	 * @model
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Enabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pieces</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pieces</em>' attribute.
	 * @see #setPieces(int[])
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Pieces()
	 * @model dataType="eu.kalafatic.gemini.bt.client.model.torrents.EIntegerArray"
	 * @generated
	 */
	int[] getPieces();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getPieces <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pieces</em>' attribute.
	 * @see #getPieces()
	 * @generated
	 */
	void setPieces(int[] value);

	/**
	 * Returns the value of the '<em><b>Begin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Begin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Begin</em>' attribute.
	 * @see #setBegin(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Begin()
	 * @model
	 * @generated
	 */
	long getBegin();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getBegin <em>Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Begin</em>' attribute.
	 * @see #getBegin()
	 * @generated
	 */
	void setBegin(long value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_End()
	 * @model
	 * @generated
	 */
	long getEnd();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(long value);

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
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Lock()
	 * @model dataType="eu.kalafatic.gemini.bt.client.model.torrents.Lock" transient="true"
	 * @generated
	 */
	ReentrantReadWriteLock getLock();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ReentrantReadWriteLock value);

	/**
	 * Returns the value of the '<em><b>Raf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Raf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Raf</em>' attribute.
	 * @see #setRaf(Object)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Raf()
	 * @model transient="true"
	 * @generated
	 */
	Object getRaf();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRaf <em>Raf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Raf</em>' attribute.
	 * @see #getRaf()
	 * @generated
	 */
	void setRaf(Object value);

	/**
	 * Returns the value of the '<em><b>Recorded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recorded</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recorded</em>' attribute.
	 * @see #setRecorded(long)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getDataFileInfo_Recorded()
	 * @model transient="true"
	 * @generated
	 */
	long getRecorded();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRecorded <em>Recorded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recorded</em>' attribute.
	 * @see #getRecorded()
	 * @generated
	 */
	void setRecorded(long value);

} // DataFileInfo
