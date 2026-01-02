/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;

import java.nio.ByteBuffer;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>IO Piece</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getPieceIndex <em>Piece Index</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getPayload <em>Payload</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#isFinished <em>Finished</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getBlocks <em>Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getLockedBlocks <em>Locked Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getExtTorrent <em>Ext Torrent</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getLastActivity <em>Last Activity</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getColor <em>Color</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.IOPieceImpl#getHash <em>Hash</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IOPieceImpl extends EObjectImpl implements IOPiece {
	/**
	 * The default value of the '{@link #getPieceIndex() <em>Piece Index</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPieceIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int PIECE_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPieceIndex() <em>Piece Index</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPieceIndex()
	 * @generated
	 * @ordered
	 */
	protected int pieceIndex = PIECE_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getPayload() <em>Payload</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPayload()
	 * @generated
	 * @ordered
	 */
	protected static final ByteBuffer PAYLOAD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPayload() <em>Payload</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPayload()
	 * @generated
	 * @ordered
	 */
	protected ByteBuffer payload = PAYLOAD_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinished() <em>Finished</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isFinished()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINISHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinished() <em>Finished</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isFinished()
	 * @generated
	 * @ordered
	 */
	protected boolean finished = FINISHED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int POSITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected int position = POSITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getBlocks() <em>Blocks</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBlocks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean[] BLOCKS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBlocks() <em>Blocks</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBlocks()
	 * @generated
	 * @ordered
	 */
	protected boolean[] blocks = BLOCKS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLockedBlocks() <em>Locked Blocks</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLockedBlocks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean[] LOCKED_BLOCKS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLockedBlocks() <em>Locked Blocks</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLockedBlocks()
	 * @generated
	 * @ordered
	 */
	protected boolean[] lockedBlocks = LOCKED_BLOCKS_EDEFAULT;

	/**
	 * The default value of the '{@link #getExtTorrent() <em>Ext Torrent</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getExtTorrent()
	 * @generated
	 * @ordered
	 */
	protected static final Object EXT_TORRENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtTorrent() <em>Ext Torrent</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getExtTorrent()
	 * @generated
	 * @ordered
	 */
	protected Object extTorrent = EXT_TORRENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastActivity() <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLastActivity()
	 * @generated
	 * @ordered
	 */
	protected static final long LAST_ACTIVITY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLastActivity() <em>Last Activity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLastActivity()
	 * @generated
	 * @ordered
	 */
	protected long lastActivity = LAST_ACTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected static final ReentrantReadWriteLock LOCK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected ReentrantReadWriteLock lock = LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final int COLOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected int color = COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] HASH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected byte[] hash = HASH_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IOPieceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.IO_PIECE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getPieceIndex() {
		return pieceIndex;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPieceIndex(int newPieceIndex) {
		int oldPieceIndex = pieceIndex;
		pieceIndex = newPieceIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__PIECE_INDEX, oldPieceIndex, pieceIndex));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ByteBuffer getPayload() {
		return payload;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPayload(ByteBuffer newPayload) {
		ByteBuffer oldPayload = payload;
		payload = newPayload;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__PAYLOAD, oldPayload, payload));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinished(boolean newFinished) {
		boolean oldFinished = finished;
		finished = newFinished;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__FINISHED, oldFinished, finished));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(int newPosition) {
		int oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean[] getBlocks() {
		return blocks;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlocks(boolean[] newBlocks) {
		boolean[] oldBlocks = blocks;
		blocks = newBlocks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__BLOCKS, oldBlocks, blocks));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean[] getLockedBlocks() {
		return lockedBlocks;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLockedBlocks(boolean[] newLockedBlocks) {
		boolean[] oldLockedBlocks = lockedBlocks;
		lockedBlocks = newLockedBlocks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__LOCKED_BLOCKS, oldLockedBlocks, lockedBlocks));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object getExtTorrent() {
		return extTorrent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtTorrent(Object newExtTorrent) {
		Object oldExtTorrent = extTorrent;
		extTorrent = newExtTorrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__EXT_TORRENT, oldExtTorrent, extTorrent));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getLastActivity() {
		return lastActivity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastActivity(long newLastActivity) {
		long oldLastActivity = lastActivity;
		lastActivity = newLastActivity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__LAST_ACTIVITY, oldLastActivity, lastActivity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ReentrantReadWriteLock getLock() {
		return lock;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLock(ReentrantReadWriteLock newLock) {
		ReentrantReadWriteLock oldLock = lock;
		lock = newLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(int newColor) {
		int oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__COLOR, oldColor, color));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getHash() {
		return hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHash(byte[] newHash) {
		byte[] oldHash = hash;
		hash = newHash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.IO_PIECE__HASH, oldHash, hash));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.IO_PIECE__PIECE_INDEX:
				return getPieceIndex();
			case ClientNetworkPackage.IO_PIECE__PAYLOAD:
				return getPayload();
			case ClientNetworkPackage.IO_PIECE__FINISHED:
				return isFinished();
			case ClientNetworkPackage.IO_PIECE__POSITION:
				return getPosition();
			case ClientNetworkPackage.IO_PIECE__BLOCKS:
				return getBlocks();
			case ClientNetworkPackage.IO_PIECE__LOCKED_BLOCKS:
				return getLockedBlocks();
			case ClientNetworkPackage.IO_PIECE__EXT_TORRENT:
				return getExtTorrent();
			case ClientNetworkPackage.IO_PIECE__LAST_ACTIVITY:
				return getLastActivity();
			case ClientNetworkPackage.IO_PIECE__LOCK:
				return getLock();
			case ClientNetworkPackage.IO_PIECE__COLOR:
				return getColor();
			case ClientNetworkPackage.IO_PIECE__HASH:
				return getHash();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClientNetworkPackage.IO_PIECE__PIECE_INDEX:
				setPieceIndex((Integer)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__PAYLOAD:
				setPayload((ByteBuffer)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__FINISHED:
				setFinished((Boolean)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__POSITION:
				setPosition((Integer)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__BLOCKS:
				setBlocks((boolean[])newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__LOCKED_BLOCKS:
				setLockedBlocks((boolean[])newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__EXT_TORRENT:
				setExtTorrent(newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__LAST_ACTIVITY:
				setLastActivity((Long)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__LOCK:
				setLock((ReentrantReadWriteLock)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__COLOR:
				setColor((Integer)newValue);
				return;
			case ClientNetworkPackage.IO_PIECE__HASH:
				setHash((byte[])newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.IO_PIECE__PIECE_INDEX:
				setPieceIndex(PIECE_INDEX_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__PAYLOAD:
				setPayload(PAYLOAD_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__FINISHED:
				setFinished(FINISHED_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__BLOCKS:
				setBlocks(BLOCKS_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__LOCKED_BLOCKS:
				setLockedBlocks(LOCKED_BLOCKS_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__EXT_TORRENT:
				setExtTorrent(EXT_TORRENT_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__LAST_ACTIVITY:
				setLastActivity(LAST_ACTIVITY_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__LOCK:
				setLock(LOCK_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case ClientNetworkPackage.IO_PIECE__HASH:
				setHash(HASH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClientNetworkPackage.IO_PIECE__PIECE_INDEX:
				return pieceIndex != PIECE_INDEX_EDEFAULT;
			case ClientNetworkPackage.IO_PIECE__PAYLOAD:
				return PAYLOAD_EDEFAULT == null ? payload != null : !PAYLOAD_EDEFAULT.equals(payload);
			case ClientNetworkPackage.IO_PIECE__FINISHED:
				return finished != FINISHED_EDEFAULT;
			case ClientNetworkPackage.IO_PIECE__POSITION:
				return position != POSITION_EDEFAULT;
			case ClientNetworkPackage.IO_PIECE__BLOCKS:
				return BLOCKS_EDEFAULT == null ? blocks != null : !BLOCKS_EDEFAULT.equals(blocks);
			case ClientNetworkPackage.IO_PIECE__LOCKED_BLOCKS:
				return LOCKED_BLOCKS_EDEFAULT == null ? lockedBlocks != null : !LOCKED_BLOCKS_EDEFAULT.equals(lockedBlocks);
			case ClientNetworkPackage.IO_PIECE__EXT_TORRENT:
				return EXT_TORRENT_EDEFAULT == null ? extTorrent != null : !EXT_TORRENT_EDEFAULT.equals(extTorrent);
			case ClientNetworkPackage.IO_PIECE__LAST_ACTIVITY:
				return lastActivity != LAST_ACTIVITY_EDEFAULT;
			case ClientNetworkPackage.IO_PIECE__LOCK:
				return LOCK_EDEFAULT == null ? lock != null : !LOCK_EDEFAULT.equals(lock);
			case ClientNetworkPackage.IO_PIECE__COLOR:
				return color != COLOR_EDEFAULT;
			case ClientNetworkPackage.IO_PIECE__HASH:
				return HASH_EDEFAULT == null ? hash != null : !HASH_EDEFAULT.equals(hash);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (pieceIndex: ");
		result.append(pieceIndex);
		result.append(", payload: ");
		result.append(payload);
		result.append(", finished: ");
		result.append(finished);
		result.append(", position: ");
		result.append(position);
		result.append(", blocks: ");
		result.append(blocks);
		result.append(", lockedBlocks: ");
		result.append(lockedBlocks);
		result.append(", extTorrent: ");
		result.append(extTorrent);
		result.append(", lastActivity: ");
		result.append(lastActivity);
		result.append(", lock: ");
		result.append(lock);
		result.append(", color: ");
		result.append(color);
		result.append(", hash: ");
		result.append(hash);
		result.append(')');
		return result.toString();
	}

} // IOPieceImpl
