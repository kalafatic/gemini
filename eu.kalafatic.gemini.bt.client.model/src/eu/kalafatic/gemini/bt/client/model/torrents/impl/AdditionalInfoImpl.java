/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Additional Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getSeeds <em>Seeds</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getPeers <em>Peers</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getDownloaders <em>Downloaders</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getDownloaded <em>Downloaded</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getUploaded <em>Uploaded</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getCompleted <em>Completed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getCompletedPieces <em>Completed Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getFileSize <em>File Size</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl#getVerifyList <em>Verify List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdditionalInfoImpl extends SpeedInfoImpl implements AdditionalInfo {
	/**
	 * The default value of the '{@link #getSeeds() <em>Seeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeeds()
	 * @generated
	 * @ordered
	 */
	protected static final int SEEDS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSeeds() <em>Seeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeeds()
	 * @generated
	 * @ordered
	 */
	protected int seeds = SEEDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPeers() <em>Peers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeers()
	 * @generated
	 * @ordered
	 */
	protected static final int PEERS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPeers() <em>Peers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeers()
	 * @generated
	 * @ordered
	 */
	protected int peers = PEERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDownloaders() <em>Downloaders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloaders()
	 * @generated
	 * @ordered
	 */
	protected static final int DOWNLOADERS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDownloaders() <em>Downloaders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloaders()
	 * @generated
	 * @ordered
	 */
	protected int downloaders = DOWNLOADERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDownloaded() <em>Downloaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloaded()
	 * @generated
	 * @ordered
	 */
	protected static final long DOWNLOADED_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDownloaded() <em>Downloaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloaded()
	 * @generated
	 * @ordered
	 */
	protected long downloaded = DOWNLOADED_EDEFAULT;

	/**
	 * The default value of the '{@link #getUploaded() <em>Uploaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUploaded()
	 * @generated
	 * @ordered
	 */
	protected static final long UPLOADED_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getUploaded() <em>Uploaded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUploaded()
	 * @generated
	 * @ordered
	 */
	protected long uploaded = UPLOADED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompleted()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPLETED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompleted()
	 * @generated
	 * @ordered
	 */
	protected int completed = COMPLETED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompletedPieces() <em>Completed Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletedPieces()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPLETED_PIECES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCompletedPieces() <em>Completed Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletedPieces()
	 * @generated
	 * @ordered
	 */
	protected int completedPieces = COMPLETED_PIECES_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileSize() <em>File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSize()
	 * @generated
	 * @ordered
	 */
	protected static final long FILE_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getFileSize() <em>File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSize()
	 * @generated
	 * @ordered
	 */
	protected long fileSize = FILE_SIZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVerifyList() <em>Verify List</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerifyList()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> verifyList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdditionalInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.ADDITIONAL_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSeeds() {
		return seeds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeeds(int newSeeds) {
		int oldSeeds = seeds;
		seeds = newSeeds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__SEEDS, oldSeeds, seeds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPeers() {
		return peers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeers(int newPeers) {
		int oldPeers = peers;
		peers = newPeers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__PEERS, oldPeers, peers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDownloaders() {
		return downloaders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDownloaders(int newDownloaders) {
		int oldDownloaders = downloaders;
		downloaders = newDownloaders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__DOWNLOADERS, oldDownloaders, downloaders));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getDownloaded() {
		return downloaded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDownloaded(long newDownloaded) {
		long oldDownloaded = downloaded;
		downloaded = newDownloaded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__DOWNLOADED, oldDownloaded, downloaded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getUploaded() {
		return uploaded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUploaded(long newUploaded) {
		long oldUploaded = uploaded;
		uploaded = newUploaded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__UPLOADED, oldUploaded, uploaded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCompleted() {
		return completed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompleted(int newCompleted) {
		int oldCompleted = completed;
		completed = newCompleted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__COMPLETED, oldCompleted, completed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCompletedPieces() {
		return completedPieces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletedPieces(int newCompletedPieces) {
		int oldCompletedPieces = completedPieces;
		completedPieces = newCompletedPieces;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__COMPLETED_PIECES, oldCompletedPieces, completedPieces));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileSize(long newFileSize) {
		long oldFileSize = fileSize;
		fileSize = newFileSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.ADDITIONAL_INFO__FILE_SIZE, oldFileSize, fileSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getVerifyList() {
		if (verifyList == null) {
			verifyList = new EDataTypeUniqueEList<Integer>(Integer.class, this, TorrentsPackage.ADDITIONAL_INFO__VERIFY_LIST);
		}
		return verifyList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TorrentsPackage.ADDITIONAL_INFO__SEEDS:
				return getSeeds();
			case TorrentsPackage.ADDITIONAL_INFO__PEERS:
				return getPeers();
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADERS:
				return getDownloaders();
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADED:
				return getDownloaded();
			case TorrentsPackage.ADDITIONAL_INFO__UPLOADED:
				return getUploaded();
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED:
				return getCompleted();
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED_PIECES:
				return getCompletedPieces();
			case TorrentsPackage.ADDITIONAL_INFO__DESCRIPTION:
				return getDescription();
			case TorrentsPackage.ADDITIONAL_INFO__FILE_SIZE:
				return getFileSize();
			case TorrentsPackage.ADDITIONAL_INFO__VERIFY_LIST:
				return getVerifyList();
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
			case TorrentsPackage.ADDITIONAL_INFO__SEEDS:
				setSeeds((Integer)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__PEERS:
				setPeers((Integer)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADERS:
				setDownloaders((Integer)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADED:
				setDownloaded((Long)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__UPLOADED:
				setUploaded((Long)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED:
				setCompleted((Integer)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED_PIECES:
				setCompletedPieces((Integer)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__FILE_SIZE:
				setFileSize((Long)newValue);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__VERIFY_LIST:
				getVerifyList().clear();
				getVerifyList().addAll((Collection<? extends Integer>)newValue);
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
			case TorrentsPackage.ADDITIONAL_INFO__SEEDS:
				setSeeds(SEEDS_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__PEERS:
				setPeers(PEERS_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADERS:
				setDownloaders(DOWNLOADERS_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADED:
				setDownloaded(DOWNLOADED_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__UPLOADED:
				setUploaded(UPLOADED_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED:
				setCompleted(COMPLETED_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED_PIECES:
				setCompletedPieces(COMPLETED_PIECES_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__FILE_SIZE:
				setFileSize(FILE_SIZE_EDEFAULT);
				return;
			case TorrentsPackage.ADDITIONAL_INFO__VERIFY_LIST:
				getVerifyList().clear();
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
			case TorrentsPackage.ADDITIONAL_INFO__SEEDS:
				return seeds != SEEDS_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__PEERS:
				return peers != PEERS_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADERS:
				return downloaders != DOWNLOADERS_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__DOWNLOADED:
				return downloaded != DOWNLOADED_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__UPLOADED:
				return uploaded != UPLOADED_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED:
				return completed != COMPLETED_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__COMPLETED_PIECES:
				return completedPieces != COMPLETED_PIECES_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case TorrentsPackage.ADDITIONAL_INFO__FILE_SIZE:
				return fileSize != FILE_SIZE_EDEFAULT;
			case TorrentsPackage.ADDITIONAL_INFO__VERIFY_LIST:
				return verifyList != null && !verifyList.isEmpty();
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
		result.append(" (seeds: ");
		result.append(seeds);
		result.append(", peers: ");
		result.append(peers);
		result.append(", downloaders: ");
		result.append(downloaders);
		result.append(", downloaded: ");
		result.append(downloaded);
		result.append(", uploaded: ");
		result.append(uploaded);
		result.append(", completed: ");
		result.append(completed);
		result.append(", completedPieces: ");
		result.append(completedPieces);
		result.append(", description: ");
		result.append(description);
		result.append(", fileSize: ");
		result.append(fileSize);
		result.append(", verifyList: ");
		result.append(verifyList);
		result.append(')');
		return result.toString();
	}

} //AdditionalInfoImpl
