/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ext Torrent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getRow <em>Row</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#isFinished <em>Finished</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getRealBitfield <em>Real Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getModelBitfield <em>Model Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getHandshake <em>Handshake</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getLock <em>Lock</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getAdditionalInfo <em>Additional Info</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getPath <em>Path</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtTorrentImpl extends TorrentImpl implements ExtTorrent {
	/**
	 * The default value of the '{@link #getRow() <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRow()
	 * @generated
	 * @ordered
	 */
	protected static final int ROW_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRow() <em>Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRow()
	 * @generated
	 * @ordered
	 */
	protected int row = ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = false;

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
	 * The default value of the '{@link #isFinished() <em>Finished</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinished()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINISHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinished() <em>Finished</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinished()
	 * @generated
	 * @ordered
	 */
	protected boolean finished = FINISHED_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final Enumerator STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected Enumerator status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRealBitfield() <em>Real Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealBitfield()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] REAL_BITFIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRealBitfield() <em>Real Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealBitfield()
	 * @generated
	 * @ordered
	 */
	protected byte[] realBitfield = REAL_BITFIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelBitfield() <em>Model Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelBitfield()
	 * @generated
	 * @ordered
	 */
	protected static final boolean[] MODEL_BITFIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelBitfield() <em>Model Bitfield</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelBitfield()
	 * @generated
	 * @ordered
	 */
	protected boolean[] modelBitfield = MODEL_BITFIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #getHandshake() <em>Handshake</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandshake()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] HANDSHAKE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHandshake() <em>Handshake</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandshake()
	 * @generated
	 * @ordered
	 */
	protected byte[] handshake = HANDSHAKE_EDEFAULT;

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
	 * The cached value of the '{@link #getAdditionalInfo() <em>Additional Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalInfo()
	 * @generated
	 * @ordered
	 */
	protected AdditionalInfo additionalInfo;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMap() <em>Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMap()
	 * @generated
	 * @ordered
	 */
	protected Map<?, ?> map;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtTorrentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.EXT_TORRENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRow() {
		return row;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRow(int newRow) {
		int oldRow = row;
		row = newRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__ROW, oldRow, row));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinished(boolean newFinished) {
		boolean oldFinished = finished;
		finished = newFinished;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__FINISHED, oldFinished, finished));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumerator getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(Enumerator newStatus) {
		Enumerator oldStatus = status;
		status = newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getRealBitfield() {
		return realBitfield;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealBitfield(byte[] newRealBitfield) {
		byte[] oldRealBitfield = realBitfield;
		realBitfield = newRealBitfield;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__REAL_BITFIELD, oldRealBitfield, realBitfield));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean[] getModelBitfield() {
		return modelBitfield;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelBitfield(boolean[] newModelBitfield) {
		boolean[] oldModelBitfield = modelBitfield;
		modelBitfield = newModelBitfield;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__MODEL_BITFIELD, oldModelBitfield, modelBitfield));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getHandshake() {
		return handshake;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandshake(byte[] newHandshake) {
		byte[] oldHandshake = handshake;
		handshake = newHandshake;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__HANDSHAKE, oldHandshake, handshake));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdditionalInfo(AdditionalInfo newAdditionalInfo, NotificationChain msgs) {
		AdditionalInfo oldAdditionalInfo = additionalInfo;
		additionalInfo = newAdditionalInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO, oldAdditionalInfo, newAdditionalInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdditionalInfo(AdditionalInfo newAdditionalInfo) {
		if (newAdditionalInfo != additionalInfo) {
			NotificationChain msgs = null;
			if (additionalInfo != null)
				msgs = ((InternalEObject)additionalInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO, null, msgs);
			if (newAdditionalInfo != null)
				msgs = ((InternalEObject)newAdditionalInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO, null, msgs);
			msgs = basicSetAdditionalInfo(newAdditionalInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO, newAdditionalInfo, newAdditionalInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<?, ?> getMap() {
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMap(Map<?, ?> newMap) {
		Map<?, ?> oldMap = map;
		map = newMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.EXT_TORRENT__MAP, oldMap, map));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO:
				return basicSetAdditionalInfo(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.EXT_TORRENT__ROW:
				return getRow();
			case TorrentsPackage.EXT_TORRENT__ENABLED:
				return isEnabled();
			case TorrentsPackage.EXT_TORRENT__FINISHED:
				return isFinished();
			case TorrentsPackage.EXT_TORRENT__STATUS:
				return getStatus();
			case TorrentsPackage.EXT_TORRENT__REAL_BITFIELD:
				return getRealBitfield();
			case TorrentsPackage.EXT_TORRENT__MODEL_BITFIELD:
				return getModelBitfield();
			case TorrentsPackage.EXT_TORRENT__HANDSHAKE:
				return getHandshake();
			case TorrentsPackage.EXT_TORRENT__LOCK:
				return getLock();
			case TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO:
				return getAdditionalInfo();
			case TorrentsPackage.EXT_TORRENT__PATH:
				return getPath();
			case TorrentsPackage.EXT_TORRENT__MAP:
				return getMap();
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
			case TorrentsPackage.EXT_TORRENT__ROW:
				setRow((Integer)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__FINISHED:
				setFinished((Boolean)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__STATUS:
				setStatus((Enumerator)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__REAL_BITFIELD:
				setRealBitfield((byte[])newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__MODEL_BITFIELD:
				setModelBitfield((boolean[])newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__HANDSHAKE:
				setHandshake((byte[])newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__LOCK:
				setLock((ReentrantReadWriteLock)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO:
				setAdditionalInfo((AdditionalInfo)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__PATH:
				setPath((String)newValue);
				return;
			case TorrentsPackage.EXT_TORRENT__MAP:
				setMap((Map<?, ?>)newValue);
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
			case TorrentsPackage.EXT_TORRENT__ROW:
				setRow(ROW_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__FINISHED:
				setFinished(FINISHED_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__REAL_BITFIELD:
				setRealBitfield(REAL_BITFIELD_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__MODEL_BITFIELD:
				setModelBitfield(MODEL_BITFIELD_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__HANDSHAKE:
				setHandshake(HANDSHAKE_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__LOCK:
				setLock(LOCK_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO:
				setAdditionalInfo((AdditionalInfo)null);
				return;
			case TorrentsPackage.EXT_TORRENT__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case TorrentsPackage.EXT_TORRENT__MAP:
				setMap((Map<?, ?>)null);
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
			case TorrentsPackage.EXT_TORRENT__ROW:
				return row != ROW_EDEFAULT;
			case TorrentsPackage.EXT_TORRENT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case TorrentsPackage.EXT_TORRENT__FINISHED:
				return finished != FINISHED_EDEFAULT;
			case TorrentsPackage.EXT_TORRENT__STATUS:
				return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
			case TorrentsPackage.EXT_TORRENT__REAL_BITFIELD:
				return REAL_BITFIELD_EDEFAULT == null ? realBitfield != null : !REAL_BITFIELD_EDEFAULT.equals(realBitfield);
			case TorrentsPackage.EXT_TORRENT__MODEL_BITFIELD:
				return MODEL_BITFIELD_EDEFAULT == null ? modelBitfield != null : !MODEL_BITFIELD_EDEFAULT.equals(modelBitfield);
			case TorrentsPackage.EXT_TORRENT__HANDSHAKE:
				return HANDSHAKE_EDEFAULT == null ? handshake != null : !HANDSHAKE_EDEFAULT.equals(handshake);
			case TorrentsPackage.EXT_TORRENT__LOCK:
				return LOCK_EDEFAULT == null ? lock != null : !LOCK_EDEFAULT.equals(lock);
			case TorrentsPackage.EXT_TORRENT__ADDITIONAL_INFO:
				return additionalInfo != null;
			case TorrentsPackage.EXT_TORRENT__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case TorrentsPackage.EXT_TORRENT__MAP:
				return map != null;
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
		result.append(" (row: ");
		result.append(row);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", finished: ");
		result.append(finished);
		result.append(", status: ");
		result.append(status);
		result.append(", realBitfield: ");
		result.append(realBitfield);
		result.append(", modelBitfield: ");
		result.append(modelBitfield);
		result.append(", handshake: ");
		result.append(handshake);
		result.append(", lock: ");
		result.append(lock);
		result.append(", path: ");
		result.append(path);
		result.append(", map: ");
		result.append(map);
		result.append(')');
		return result.toString();
	}

} //ExtTorrentImpl
