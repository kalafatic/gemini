/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl#getMd5sum <em>Md5sum</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl#getPath <em>Path</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl#getLength <em>Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataFileImpl extends DataFileInfoImpl implements DataFile {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMd5sum() <em>Md5sum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMd5sum()
	 * @generated
	 * @ordered
	 */
	protected static final String MD5SUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMd5sum() <em>Md5sum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMd5sum()
	 * @generated
	 * @ordered
	 */
	protected String md5sum = MD5SUM_EDEFAULT;

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
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final long LENGTH_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected long length = LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.DATA_FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMd5sum() {
		return md5sum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMd5sum(String newMd5sum) {
		String oldMd5sum = md5sum;
		md5sum = newMd5sum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE__MD5SUM, oldMd5sum, md5sum));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(long newLength) {
		long oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.DATA_FILE__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.DATA_FILE__NAME:
				return getName();
			case TorrentsPackage.DATA_FILE__MD5SUM:
				return getMd5sum();
			case TorrentsPackage.DATA_FILE__PATH:
				return getPath();
			case TorrentsPackage.DATA_FILE__LENGTH:
				return getLength();
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
			case TorrentsPackage.DATA_FILE__NAME:
				setName((String)newValue);
				return;
			case TorrentsPackage.DATA_FILE__MD5SUM:
				setMd5sum((String)newValue);
				return;
			case TorrentsPackage.DATA_FILE__PATH:
				setPath((String)newValue);
				return;
			case TorrentsPackage.DATA_FILE__LENGTH:
				setLength((Long)newValue);
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
			case TorrentsPackage.DATA_FILE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE__MD5SUM:
				setMd5sum(MD5SUM_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case TorrentsPackage.DATA_FILE__LENGTH:
				setLength(LENGTH_EDEFAULT);
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
			case TorrentsPackage.DATA_FILE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TorrentsPackage.DATA_FILE__MD5SUM:
				return MD5SUM_EDEFAULT == null ? md5sum != null : !MD5SUM_EDEFAULT.equals(md5sum);
			case TorrentsPackage.DATA_FILE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case TorrentsPackage.DATA_FILE__LENGTH:
				return length != LENGTH_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", md5sum: ");
		result.append(md5sum);
		result.append(", path: ");
		result.append(path);
		result.append(", length: ");
		result.append(length);
		result.append(')');
		return result.toString();
	}

} //DataFileImpl
