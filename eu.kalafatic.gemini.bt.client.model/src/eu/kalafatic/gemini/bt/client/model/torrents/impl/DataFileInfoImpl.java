/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data File Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getEnd <em>End</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getRaf <em>Raf</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl#getRecorded <em>Recorded</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataFileInfoImpl extends EObjectImpl implements DataFileInfo {
	/**
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected int index = INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPieces() <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected static final int[] PIECES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPieces() <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected int[] pieces = PIECES_EDEFAULT;

	/**
	 * The default value of the '{@link #getBegin() <em>Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBegin()
	 * @generated
	 * @ordered
	 */
	protected static final long BEGIN_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getBegin() <em>Begin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBegin()
	 * @generated
	 * @ordered
	 */
	protected long begin = BEGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final long END_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected long end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected static final ReentrantReadWriteLock LOCK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLock()
	 * @generated
	 * @ordered
	 */
	protected ReentrantReadWriteLock lock = LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #getRaf() <em>Raf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRaf()
	 * @generated
	 * @ordered
	 */
	protected static final Object RAF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRaf() <em>Raf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRaf()
	 * @generated
	 * @ordered
	 */
	protected Object raf = RAF_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecorded() <em>Recorded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecorded()
	 * @generated
	 * @ordered
	 */
	protected static final long RECORDED_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getRecorded() <em>Recorded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecorded()
	 * @generated
	 * @ordered
	 */
	protected long recorded = RECORDED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataFileInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.DATA_FILE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(int newIndex) {
		int oldIndex = index;
		index = newIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__INDEX, oldIndex, index));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] getPieces() {
		return pieces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPieces(int[] newPieces) {
		int[] oldPieces = pieces;
		pieces = newPieces;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__PIECES, oldPieces, pieces));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getBegin() {
		return begin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBegin(long newBegin) {
		long oldBegin = begin;
		begin = newBegin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__BEGIN, oldBegin, begin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(long newEnd) {
		long oldEnd = end;
		end = newEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__END, oldEnd, end));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReentrantReadWriteLock getLock() {
		return lock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLock(ReentrantReadWriteLock newLock) {
		ReentrantReadWriteLock oldLock = lock;
		lock = newLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRaf() {
		return raf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRaf(Object newRaf) {
		Object oldRaf = raf;
		raf = newRaf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__RAF, oldRaf, raf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getRecorded() {
		return recorded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecorded(long newRecorded) {
		long oldRecorded = recorded;
		recorded = newRecorded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE_INFO__RECORDED, oldRecorded, recorded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.DATA_FILE_INFO__INDEX:
				return getIndex();
			case TorrentsPackage.DATA_FILE_INFO__ENABLED:
				return isEnabled();
			case TorrentsPackage.DATA_FILE_INFO__PIECES:
				return getPieces();
			case TorrentsPackage.DATA_FILE_INFO__BEGIN:
				return getBegin();
			case TorrentsPackage.DATA_FILE_INFO__END:
				return getEnd();
			case TorrentsPackage.DATA_FILE_INFO__LOCK:
				return getLock();
			case TorrentsPackage.DATA_FILE_INFO__RAF:
				return getRaf();
			case TorrentsPackage.DATA_FILE_INFO__RECORDED:
				return getRecorded();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TorrentsPackage.DATA_FILE_INFO__INDEX:
				setIndex((Integer)newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__PIECES:
				setPieces((int[])newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__BEGIN:
				setBegin((Long)newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__END:
				setEnd((Long)newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__LOCK:
				setLock((ReentrantReadWriteLock)newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__RAF:
				setRaf(newValue);
				return;
			case TorrentsPackage.DATA_FILE_INFO__RECORDED:
				setRecorded((Long)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TorrentsPackage.DATA_FILE_INFO__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__PIECES:
				setPieces(PIECES_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__BEGIN:
				setBegin(BEGIN_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__END:
				setEnd(END_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__LOCK:
				setLock(LOCK_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__RAF:
				setRaf(RAF_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE_INFO__RECORDED:
				setRecorded(RECORDED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TorrentsPackage.DATA_FILE_INFO__INDEX:
				return index != INDEX_EDEFAULT;
			case TorrentsPackage.DATA_FILE_INFO__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case TorrentsPackage.DATA_FILE_INFO__PIECES:
				return PIECES_EDEFAULT == null ? pieces != null : !PIECES_EDEFAULT.equals(pieces);
			case TorrentsPackage.DATA_FILE_INFO__BEGIN:
				return begin != BEGIN_EDEFAULT;
			case TorrentsPackage.DATA_FILE_INFO__END:
				return end != END_EDEFAULT;
			case TorrentsPackage.DATA_FILE_INFO__LOCK:
				return LOCK_EDEFAULT == null ? lock != null : !LOCK_EDEFAULT.equals(lock);
			case TorrentsPackage.DATA_FILE_INFO__RAF:
				return RAF_EDEFAULT == null ? raf != null : !RAF_EDEFAULT.equals(raf);
			case TorrentsPackage.DATA_FILE_INFO__RECORDED:
				return recorded != RECORDED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (index: ");
		result.append(index);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", pieces: ");
		result.append(pieces);
		result.append(", begin: ");
		result.append(begin);
		result.append(", end: ");
		result.append(end);
		result.append(", lock: ");
		result.append(lock);
		result.append(", raf: ");
		result.append(raf);
		result.append(", recorded: ");
		result.append(recorded);
		result.append(')');
		return result.toString();
	}

} //DataFileInfoImpl
