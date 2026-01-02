/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Write Buffer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getOffset <em>Offset</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getSize <em>Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#isSheduled <em>Sheduled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getLock <em>Lock</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer()
 * @model
 * @generated
 */
public interface WriteBuffer extends EObject {
	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer_Offset()
	 * @model
	 * @generated
	 */
	long getOffset();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getOffset <em>Offset</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(long value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer_Size()
	 * @model
	 * @generated
	 */
	long getSize();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getSize <em>Size</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(long value);

	/**
	 * Returns the value of the '<em><b>Pieces</b></em>' containment reference list.
	 * The list contents are of type {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pieces</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pieces</em>' containment reference list.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer_Pieces()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<IOPiece> getPieces();

	/**
	 * Returns the value of the '<em><b>Sheduled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sheduled</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheduled</em>' attribute.
	 * @see #setSheduled(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer_Sheduled()
	 * @model default="false" transient="true"
	 * @generated
	 */
	boolean isSheduled();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#isSheduled <em>Sheduled</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheduled</em>' attribute.
	 * @see #isSheduled()
	 * @generated
	 */
	void setSheduled(boolean value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see #setLock(ReentrantReadWriteLock)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getWriteBuffer_Lock()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Lock" required="true" transient="true"
	 * @generated
	 */
	ReentrantReadWriteLock getLock();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer#getLock <em>Lock</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ReentrantReadWriteLock value);

} // WriteBuffer
