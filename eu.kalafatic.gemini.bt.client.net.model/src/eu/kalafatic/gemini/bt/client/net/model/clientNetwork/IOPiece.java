/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import java.nio.ByteBuffer;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>IO Piece</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPieceIndex <em>Piece Index</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPayload <em>Payload</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#isFinished <em>Finished</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPosition <em>Position</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getBlocks <em>Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLockedBlocks <em>Locked Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getExtTorrent <em>Ext Torrent</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLastActivity <em>Last Activity</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getColor <em>Color</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getHash <em>Hash</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece()
 * @model
 * @generated
 */
public interface IOPiece extends EObject {
	/**
	 * Returns the value of the '<em><b>Piece Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Piece Index</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Piece Index</em>' attribute.
	 * @see #setPieceIndex(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_PieceIndex()
	 * @model
	 * @generated
	 */
	int getPieceIndex();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPieceIndex <em>Piece Index</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Piece Index</em>' attribute.
	 * @see #getPieceIndex()
	 * @generated
	 */
	void setPieceIndex(int value);

	/**
	 * Returns the value of the '<em><b>Payload</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Payload</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Payload</em>' attribute.
	 * @see #setPayload(ByteBuffer)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Payload()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ByteBuffer" transient="true"
	 * @generated
	 */
	ByteBuffer getPayload();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPayload <em>Payload</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Payload</em>' attribute.
	 * @see #getPayload()
	 * @generated
	 */
	void setPayload(ByteBuffer value);

	/**
	 * Returns the value of the '<em><b>Finished</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finished</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finished</em>' attribute.
	 * @see #setFinished(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Finished()
	 * @model default="false"
	 * @generated
	 */
	boolean isFinished();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#isFinished <em>Finished</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finished</em>' attribute.
	 * @see #isFinished()
	 * @generated
	 */
	void setFinished(boolean value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Position()
	 * @model
	 * @generated
	 */
	int getPosition();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getPosition <em>Position</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(int value);

	/**
	 * Returns the value of the '<em><b>Blocks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Blocks</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blocks</em>' attribute.
	 * @see #setBlocks(boolean[])
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Blocks()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.BoolArray" transient="true"
	 * @generated
	 */
	boolean[] getBlocks();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getBlocks <em>Blocks</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blocks</em>' attribute.
	 * @see #getBlocks()
	 * @generated
	 */
	void setBlocks(boolean[] value);

	/**
	 * Returns the value of the '<em><b>Locked Blocks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locked Blocks</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locked Blocks</em>' attribute.
	 * @see #setLockedBlocks(boolean[])
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_LockedBlocks()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.BoolArray"
	 * @generated
	 */
	boolean[] getLockedBlocks();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLockedBlocks <em>Locked Blocks</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locked Blocks</em>' attribute.
	 * @see #getLockedBlocks()
	 * @generated
	 */
	void setLockedBlocks(boolean[] value);

	/**
	 * Returns the value of the '<em><b>Ext Torrent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ext Torrent</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ext Torrent</em>' attribute.
	 * @see #setExtTorrent(Object)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_ExtTorrent()
	 * @model transient="true"
	 * @generated
	 */
	Object getExtTorrent();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getExtTorrent <em>Ext Torrent</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ext Torrent</em>' attribute.
	 * @see #getExtTorrent()
	 * @generated
	 */
	void setExtTorrent(Object value);

	/**
	 * Returns the value of the '<em><b>Last Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Activity</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Activity</em>' attribute.
	 * @see #setLastActivity(long)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_LastActivity()
	 * @model transient="true"
	 * @generated
	 */
	long getLastActivity();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLastActivity <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Activity</em>' attribute.
	 * @see #getLastActivity()
	 * @generated
	 */
	void setLastActivity(long value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see #setLock(ReentrantReadWriteLock)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Lock()
	 * @model dataType="eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Lock" required="true" transient="true"
	 * @generated
	 */
	ReentrantReadWriteLock getLock();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getLock <em>Lock</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #getLock()
	 * @generated
	 */
	void setLock(ReentrantReadWriteLock value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Color()
	 * @model transient="true"
	 * @generated
	 */
	int getColor();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getColor <em>Color</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(int value);

	/**
	 * Returns the value of the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hash</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hash</em>' attribute.
	 * @see #setHash(byte[])
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getIOPiece_Hash()
	 * @model
	 * @generated
	 */
	byte[] getHash();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece#getHash <em>Hash</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hash</em>' attribute.
	 * @see #getHash()
	 * @generated
	 */
	void setHash(byte[] value);

} // IOPiece
