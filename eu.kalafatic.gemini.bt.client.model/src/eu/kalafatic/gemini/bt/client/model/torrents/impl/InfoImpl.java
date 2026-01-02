/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.Collection;

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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#getPieces <em>Pieces</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#getPieceLength <em>Piece Length</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#isPrivate <em>Private</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#getHash <em>Hash</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#getFiles <em>Files</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl#getMaxPiecesPerFile <em>Max Pieces Per File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfoImpl extends EObjectImpl implements Info {
	/**
	 * The default value of the '{@link #getPieces() <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] PIECES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPieces() <em>Pieces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieces()
	 * @generated
	 * @ordered
	 */
	protected byte[] pieces = PIECES_EDEFAULT;

	/**
	 * The default value of the '{@link #getPieceLength() <em>Piece Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieceLength()
	 * @generated
	 * @ordered
	 */
	protected static final int PIECE_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPieceLength() <em>Piece Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPieceLength()
	 * @generated
	 * @ordered
	 */
	protected int pieceLength = PIECE_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIVATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] HASH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHash() <em>Hash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHash()
	 * @generated
	 * @ordered
	 */
	protected byte[] hash = HASH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFiles() <em>Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<DataFile> files;

	/**
	 * The default value of the '{@link #getMaxPiecesPerFile() <em>Max Pieces Per File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPiecesPerFile()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_PIECES_PER_FILE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxPiecesPerFile() <em>Max Pieces Per File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPiecesPerFile()
	 * @generated
	 * @ordered
	 */
	protected int maxPiecesPerFile = MAX_PIECES_PER_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TorrentsPackage.Literals.INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getPieces() {
		return pieces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPieces(byte[] newPieces) {
		byte[] oldPieces = pieces;
		pieces = newPieces;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.INFO__PIECES, oldPieces, pieces));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPieceLength() {
		return pieceLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPieceLength(int newPieceLength) {
		int oldPieceLength = pieceLength;
		pieceLength = newPieceLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.INFO__PIECE_LENGTH, oldPieceLength, pieceLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrivate() {
		return private_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivate(boolean newPrivate) {
		boolean oldPrivate = private_;
		private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.INFO__PRIVATE, oldPrivate, private_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getHash() {
		return hash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHash(byte[] newHash) {
		byte[] oldHash = hash;
		hash = newHash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.INFO__HASH, oldHash, hash));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataFile> getFiles() {
		if (files == null) {
			files = new EObjectContainmentEList<DataFile>(DataFile.class, this, TorrentsPackage.INFO__FILES);
		}
		return files;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxPiecesPerFile() {
		return maxPiecesPerFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxPiecesPerFile(int newMaxPiecesPerFile) {
		int oldMaxPiecesPerFile = maxPiecesPerFile;
		maxPiecesPerFile = newMaxPiecesPerFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TorrentsPackage.INFO__MAX_PIECES_PER_FILE, oldMaxPiecesPerFile, maxPiecesPerFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TorrentsPackage.INFO__FILES:
				return ((InternalEList<?>)getFiles()).basicRemove(otherEnd, msgs);
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
			case TorrentsPackage.INFO__PIECES:
				return getPieces();
			case TorrentsPackage.INFO__PIECE_LENGTH:
				return getPieceLength();
			case TorrentsPackage.INFO__PRIVATE:
				return isPrivate();
			case TorrentsPackage.INFO__HASH:
				return getHash();
			case TorrentsPackage.INFO__FILES:
				return getFiles();
			case TorrentsPackage.INFO__MAX_PIECES_PER_FILE:
				return getMaxPiecesPerFile();
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
			case TorrentsPackage.INFO__PIECES:
				setPieces((byte[])newValue);
				return;
			case TorrentsPackage.INFO__PIECE_LENGTH:
				setPieceLength((Integer)newValue);
				return;
			case TorrentsPackage.INFO__PRIVATE:
				setPrivate((Boolean)newValue);
				return;
			case TorrentsPackage.INFO__HASH:
				setHash((byte[])newValue);
				return;
			case TorrentsPackage.INFO__FILES:
				getFiles().clear();
				getFiles().addAll((Collection<? extends DataFile>)newValue);
				return;
			case TorrentsPackage.INFO__MAX_PIECES_PER_FILE:
				setMaxPiecesPerFile((Integer)newValue);
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
			case TorrentsPackage.INFO__PIECES:
				setPieces(PIECES_EDEFAULT);
				return;
			case TorrentsPackage.INFO__PIECE_LENGTH:
				setPieceLength(PIECE_LENGTH_EDEFAULT);
				return;
			case TorrentsPackage.INFO__PRIVATE:
				setPrivate(PRIVATE_EDEFAULT);
				return;
			case TorrentsPackage.INFO__HASH:
				setHash(HASH_EDEFAULT);
				return;
			case TorrentsPackage.INFO__FILES:
				getFiles().clear();
				return;
			case TorrentsPackage.INFO__MAX_PIECES_PER_FILE:
				setMaxPiecesPerFile(MAX_PIECES_PER_FILE_EDEFAULT);
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
			case TorrentsPackage.INFO__PIECES:
				return PIECES_EDEFAULT == null ? pieces != null : !PIECES_EDEFAULT.equals(pieces);
			case TorrentsPackage.INFO__PIECE_LENGTH:
				return pieceLength != PIECE_LENGTH_EDEFAULT;
			case TorrentsPackage.INFO__PRIVATE:
				return private_ != PRIVATE_EDEFAULT;
			case TorrentsPackage.INFO__HASH:
				return HASH_EDEFAULT == null ? hash != null : !HASH_EDEFAULT.equals(hash);
			case TorrentsPackage.INFO__FILES:
				return files != null && !files.isEmpty();
			case TorrentsPackage.INFO__MAX_PIECES_PER_FILE:
				return maxPiecesPerFile != MAX_PIECES_PER_FILE_EDEFAULT;
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
		result.append(" (pieces: ");
		result.append(pieces);
		result.append(", pieceLength: ");
		result.append(pieceLength);
		result.append(", private: ");
		result.append(private_);
		result.append(", hash: ");
		result.append(hash);
		result.append(", maxPiecesPerFile: ");
		result.append(maxPiecesPerFile);
		result.append(')');
		return result.toString();
	}

} //InfoImpl
