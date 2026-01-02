/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.WriteBuffer;

import java.util.Collection;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Write Buffer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl#getSize <em>Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl#isSheduled <em>Sheduled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.WriteBufferImpl#getLock <em>Lock</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WriteBufferImpl extends EObjectImpl implements WriteBuffer {
	/**
	 * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected static final long OFFSET_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected long offset = OFFSET_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final long SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected long size = SIZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPieces() <em>Pieces</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected EList<IOPiece> pieces;

	/**
	 * The default value of the '{@link #isSheduled() <em>Sheduled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSheduled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHEDULED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSheduled() <em>Sheduled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSheduled()
	 * @generated
	 * @ordered
	 */
	protected boolean sheduled = SHEDULED_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected WriteBufferImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.WRITE_BUFFER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getOffset() {
		return offset;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffset(long newOffset) {
		long oldOffset = offset;
		offset = newOffset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.WRITE_BUFFER__OFFSET, oldOffset, offset));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public long getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(long newSize) {
		long oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.WRITE_BUFFER__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IOPiece> getPieces() {
		if (pieces == null) {
			pieces = new EObjectContainmentEList<IOPiece>(IOPiece.class, this, ClientNetworkPackage.WRITE_BUFFER__PIECES);
		}
		return pieces;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSheduled() {
		return sheduled;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSheduled(boolean newSheduled) {
		boolean oldSheduled = sheduled;
		sheduled = newSheduled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.WRITE_BUFFER__SHEDULED, oldSheduled, sheduled));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.WRITE_BUFFER__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.WRITE_BUFFER__PIECES:
				return ((InternalEList<?>)getPieces()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.WRITE_BUFFER__OFFSET:
				return getOffset();
			case ClientNetworkPackage.WRITE_BUFFER__SIZE:
				return getSize();
			case ClientNetworkPackage.WRITE_BUFFER__PIECES:
				return getPieces();
			case ClientNetworkPackage.WRITE_BUFFER__SHEDULED:
				return isSheduled();
			case ClientNetworkPackage.WRITE_BUFFER__LOCK:
				return getLock();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClientNetworkPackage.WRITE_BUFFER__OFFSET:
				setOffset((Long)newValue);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__SIZE:
				setSize((Long)newValue);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__PIECES:
				getPieces().clear();
				getPieces().addAll((Collection<? extends IOPiece>)newValue);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__SHEDULED:
				setSheduled((Boolean)newValue);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__LOCK:
				setLock((ReentrantReadWriteLock)newValue);
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
			case ClientNetworkPackage.WRITE_BUFFER__OFFSET:
				setOffset(OFFSET_EDEFAULT);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__PIECES:
				getPieces().clear();
				return;
			case ClientNetworkPackage.WRITE_BUFFER__SHEDULED:
				setSheduled(SHEDULED_EDEFAULT);
				return;
			case ClientNetworkPackage.WRITE_BUFFER__LOCK:
				setLock(LOCK_EDEFAULT);
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
			case ClientNetworkPackage.WRITE_BUFFER__OFFSET:
				return offset != OFFSET_EDEFAULT;
			case ClientNetworkPackage.WRITE_BUFFER__SIZE:
				return size != SIZE_EDEFAULT;
			case ClientNetworkPackage.WRITE_BUFFER__PIECES:
				return pieces != null && !pieces.isEmpty();
			case ClientNetworkPackage.WRITE_BUFFER__SHEDULED:
				return sheduled != SHEDULED_EDEFAULT;
			case ClientNetworkPackage.WRITE_BUFFER__LOCK:
				return LOCK_EDEFAULT == null ? lock != null : !LOCK_EDEFAULT.equals(lock);
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
		result.append(" (offset: ");
		result.append(offset);
		result.append(", size: ");
		result.append(size);
		result.append(", sheduled: ");
		result.append(sheduled);
		result.append(", lock: ");
		result.append(lock);
		result.append(')');
		return result.toString();
	}

} // WriteBufferImpl
