/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrent;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Torrent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getCreationBy <em>Creation By</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getModifiedBy <em>Modified By</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getInfo <em>Info</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl#getAnnounceList <em>Announce List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TorrentImpl extends EObjectImpl implements Torrent {
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
	 * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final long CREATION_DATE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected long creationDate = CREATION_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationBy() <em>Creation By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationBy()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_BY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationBy() <em>Creation By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationBy()
	 * @generated
	 * @ordered
	 */
	protected String creationBy = CREATION_BY_EDEFAULT;

	/**
	 * The default value of the '{@link #getModifiedBy() <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedBy()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_BY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModifiedBy() <em>Modified By</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiedBy()
	 * @generated
	 * @ordered
	 */
	protected String modifiedBy = MODIFIED_BY_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected static final String ENCODING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected String encoding = ENCODING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInfo() <em>Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfo()
	 * @generated
	 * @ordered
	 */
	protected Info info;

	/**
	 * The cached value of the '{@link #getAnnounceList() <em>Announce List</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnounceList()
	 * @generated
	 * @ordered
	 */
	protected EList<String> announceList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TorrentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.TORRENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCreationDate() {
		return creationDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationDate(long newCreationDate) {
		long oldCreationDate = creationDate;
		creationDate = newCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationBy() {
		return creationBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationBy(String newCreationBy) {
		String oldCreationBy = creationBy;
		creationBy = newCreationBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__CREATION_BY, oldCreationBy, creationBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiedBy(String newModifiedBy) {
		String oldModifiedBy = modifiedBy;
		modifiedBy = newModifiedBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__MODIFIED_BY, oldModifiedBy, modifiedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncoding(String newEncoding) {
		String oldEncoding = encoding;
		encoding = newEncoding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__ENCODING, oldEncoding, encoding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Info getInfo() {
		return info;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInfo(Info newInfo, NotificationChain msgs) {
		Info oldInfo = info;
		info = newInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__INFO, oldInfo, newInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInfo(Info newInfo) {
		if (newInfo != info) {
			NotificationChain msgs = null;
			if (info != null)
				msgs = ((InternalEObject)info).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TorrentsPackage.TORRENT__INFO, null, msgs);
			if (newInfo != null)
				msgs = ((InternalEObject)newInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TorrentsPackage.TORRENT__INFO, null, msgs);
			msgs = basicSetInfo(newInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.TORRENT__INFO, newInfo, newInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAnnounceList() {
		if (announceList == null) {
			announceList = new EDataTypeUniqueEList<String>(String.class, this, TorrentsPackage.TORRENT__ANNOUNCE_LIST);
		}
		return announceList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TorrentsPackage.TORRENT__INFO:
				return basicSetInfo(null, msgs);
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
			case TorrentsPackage.TORRENT__NAME:
				return getName();
			case TorrentsPackage.TORRENT__CREATION_DATE:
				return getCreationDate();
			case TorrentsPackage.TORRENT__CREATION_BY:
				return getCreationBy();
			case TorrentsPackage.TORRENT__MODIFIED_BY:
				return getModifiedBy();
			case TorrentsPackage.TORRENT__COMMENT:
				return getComment();
			case TorrentsPackage.TORRENT__ENCODING:
				return getEncoding();
			case TorrentsPackage.TORRENT__INFO:
				return getInfo();
			case TorrentsPackage.TORRENT__ANNOUNCE_LIST:
				return getAnnounceList();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TorrentsPackage.TORRENT__NAME:
				setName((String)newValue);
				return;
			case TorrentsPackage.TORRENT__CREATION_DATE:
				setCreationDate((Long)newValue);
				return;
			case TorrentsPackage.TORRENT__CREATION_BY:
				setCreationBy((String)newValue);
				return;
			case TorrentsPackage.TORRENT__MODIFIED_BY:
				setModifiedBy((String)newValue);
				return;
			case TorrentsPackage.TORRENT__COMMENT:
				setComment((String)newValue);
				return;
			case TorrentsPackage.TORRENT__ENCODING:
				setEncoding((String)newValue);
				return;
			case TorrentsPackage.TORRENT__INFO:
				setInfo((Info)newValue);
				return;
			case TorrentsPackage.TORRENT__ANNOUNCE_LIST:
				getAnnounceList().clear();
				getAnnounceList().addAll((Collection<? extends String>)newValue);
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
			case TorrentsPackage.TORRENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__CREATION_BY:
				setCreationBy(CREATION_BY_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__MODIFIED_BY:
				setModifiedBy(MODIFIED_BY_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__ENCODING:
				setEncoding(ENCODING_EDEFAULT);
				return;
			case TorrentsPackage.TORRENT__INFO:
				setInfo((Info)null);
				return;
			case TorrentsPackage.TORRENT__ANNOUNCE_LIST:
				getAnnounceList().clear();
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
			case TorrentsPackage.TORRENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TorrentsPackage.TORRENT__CREATION_DATE:
				return creationDate != CREATION_DATE_EDEFAULT;
			case TorrentsPackage.TORRENT__CREATION_BY:
				return CREATION_BY_EDEFAULT == null ? creationBy != null : !CREATION_BY_EDEFAULT.equals(creationBy);
			case TorrentsPackage.TORRENT__MODIFIED_BY:
				return MODIFIED_BY_EDEFAULT == null ? modifiedBy != null : !MODIFIED_BY_EDEFAULT.equals(modifiedBy);
			case TorrentsPackage.TORRENT__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case TorrentsPackage.TORRENT__ENCODING:
				return ENCODING_EDEFAULT == null ? encoding != null : !ENCODING_EDEFAULT.equals(encoding);
			case TorrentsPackage.TORRENT__INFO:
				return info != null;
			case TorrentsPackage.TORRENT__ANNOUNCE_LIST:
				return announceList != null && !announceList.isEmpty();
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
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(", creationBy: ");
		result.append(creationBy);
		result.append(", modifiedBy: ");
		result.append(modifiedBy);
		result.append(", comment: ");
		result.append(comment);
		result.append(", encoding: ");
		result.append(encoding);
		result.append(", announceList: ");
		result.append(announceList);
		result.append(')');
		return result.toString();
	}

} //TorrentImpl
