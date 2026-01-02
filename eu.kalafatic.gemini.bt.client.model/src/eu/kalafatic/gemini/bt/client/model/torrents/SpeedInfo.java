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
 * A representation of the model object '<em><b>Speed Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getDwnldSpeed <em>Dwnld Speed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getUpldSpeed <em>Upld Speed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getLock <em>Lock</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getSpeedInfo()
 * @model
 * @generated
 */
public interface SpeedInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Dwnld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dwnld Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dwnld Speed</em>' attribute.
	 * @see #setDwnldSpeed(float)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getSpeedInfo_DwnldSpeed()
	 * @model transient="true"
	 * @generated
	 */
	float getDwnldSpeed();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getDwnldSpeed <em>Dwnld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dwnld Speed</em>' attribute.
	 * @see #getDwnldSpeed()
	 * @generated
	 */
	void setDwnldSpeed(float value);

	/**
	 * Returns the value of the '<em><b>Upld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upld Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upld Speed</em>' attribute.
	 * @see #setUpldSpeed(float)
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getSpeedInfo_UpldSpeed()
	 * @model transient="true"
	 * @generated
	 */
	float getUpldSpeed();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getUpldSpeed <em>Upld Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upld Speed</em>' attribute.
	 * @see #getUpldSpeed()
	 * @generated
	 */
	void setUpldSpeed(float value);

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
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getSpeedInfo_Lock()
	 * @model dataType="eu.kalafatic.gemini.bt.client.model.torrents.Lock" transient="true"
	 * @generated
	 */
	ReentrantReadWriteLock getLock();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ReentrantReadWriteLock value);

} // SpeedInfo
