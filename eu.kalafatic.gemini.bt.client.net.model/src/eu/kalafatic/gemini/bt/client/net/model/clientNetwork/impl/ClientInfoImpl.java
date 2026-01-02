/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Client Info</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#getClientName <em>Client Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#isChoking <em>Choking</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#isHandshakeOK <em>Handshake OK</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#isInterested <em>Interested</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#getBitfield <em>Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#isSeed <em>Seed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientInfoImpl#getHave <em>Have</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClientInfoImpl extends EObjectImpl implements ClientInfo {
	/**
	 * The default value of the '{@link #getClientName() <em>Client Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getClientName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClientName() <em>Client Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getClientName()
	 * @generated
	 * @ordered
	 */
	protected String clientName = CLIENT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isChoking() <em>Choking</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isChoking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHOKING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isChoking() <em>Choking</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isChoking()
	 * @generated
	 * @ordered
	 */
	protected boolean choking = CHOKING_EDEFAULT;

	/**
	 * The default value of the '{@link #isHandshakeOK() <em>Handshake OK</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isHandshakeOK()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HANDSHAKE_OK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHandshakeOK() <em>Handshake OK</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isHandshakeOK()
	 * @generated
	 * @ordered
	 */
	protected boolean handshakeOK = HANDSHAKE_OK_EDEFAULT;

	/**
	 * The default value of the '{@link #isInterested() <em>Interested</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isInterested()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERESTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInterested() <em>Interested</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isInterested()
	 * @generated
	 * @ordered
	 */
	protected boolean interested = INTERESTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getBitfield() <em>Bitfield</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBitfield()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] BITFIELD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBitfield() <em>Bitfield</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBitfield()
	 * @generated
	 * @ordered
	 */
	protected byte[] bitfield = BITFIELD_EDEFAULT;

	/**
	 * The default value of the '{@link #isSeed() <em>Seed</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSeed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSeed() <em>Seed</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSeed()
	 * @generated
	 * @ordered
	 */
	protected boolean seed = SEED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHave() <em>Have</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHave()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> have;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ClientInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.CLIENT_INFO;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientName(String newClientName) {
		String oldClientName = clientName;
		clientName = newClientName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME, oldClientName, clientName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isChoking() {
		return choking;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setChoking(boolean newChoking) {
		boolean oldChoking = choking;
		choking = newChoking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__CHOKING, oldChoking, choking));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHandshakeOK() {
		return handshakeOK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandshakeOK(boolean newHandshakeOK) {
		boolean oldHandshakeOK = handshakeOK;
		handshakeOK = newHandshakeOK;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK, oldHandshakeOK, handshakeOK));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInterested() {
		return interested;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterested(boolean newInterested) {
		boolean oldInterested = interested;
		interested = newInterested;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__INTERESTED, oldInterested, interested));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getBitfield() {
		return bitfield;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBitfield(byte[] newBitfield) {
		byte[] oldBitfield = bitfield;
		bitfield = newBitfield;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__BITFIELD, oldBitfield, bitfield));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSeed() {
		return seed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeed(boolean newSeed) {
		boolean oldSeed = seed;
		seed = newSeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_INFO__SEED, oldSeed, seed));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getHave() {
		if (have == null) {
			have = new EDataTypeUniqueEList<Integer>(Integer.class, this, ClientNetworkPackage.CLIENT_INFO__HAVE);
		}
		return have;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME:
				return getClientName();
			case ClientNetworkPackage.CLIENT_INFO__CHOKING:
				return isChoking();
			case ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK:
				return isHandshakeOK();
			case ClientNetworkPackage.CLIENT_INFO__INTERESTED:
				return isInterested();
			case ClientNetworkPackage.CLIENT_INFO__BITFIELD:
				return getBitfield();
			case ClientNetworkPackage.CLIENT_INFO__SEED:
				return isSeed();
			case ClientNetworkPackage.CLIENT_INFO__HAVE:
				return getHave();
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
			case ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME:
				setClientName((String)newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__CHOKING:
				setChoking((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK:
				setHandshakeOK((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__INTERESTED:
				setInterested((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__BITFIELD:
				setBitfield((byte[])newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__SEED:
				setSeed((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_INFO__HAVE:
				getHave().clear();
				getHave().addAll((Collection<? extends Integer>)newValue);
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
			case ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME:
				setClientName(CLIENT_NAME_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__CHOKING:
				setChoking(CHOKING_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK:
				setHandshakeOK(HANDSHAKE_OK_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__INTERESTED:
				setInterested(INTERESTED_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__BITFIELD:
				setBitfield(BITFIELD_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__SEED:
				setSeed(SEED_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_INFO__HAVE:
				getHave().clear();
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
			case ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME:
				return CLIENT_NAME_EDEFAULT == null ? clientName != null : !CLIENT_NAME_EDEFAULT.equals(clientName);
			case ClientNetworkPackage.CLIENT_INFO__CHOKING:
				return choking != CHOKING_EDEFAULT;
			case ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK:
				return handshakeOK != HANDSHAKE_OK_EDEFAULT;
			case ClientNetworkPackage.CLIENT_INFO__INTERESTED:
				return interested != INTERESTED_EDEFAULT;
			case ClientNetworkPackage.CLIENT_INFO__BITFIELD:
				return BITFIELD_EDEFAULT == null ? bitfield != null : !BITFIELD_EDEFAULT.equals(bitfield);
			case ClientNetworkPackage.CLIENT_INFO__SEED:
				return seed != SEED_EDEFAULT;
			case ClientNetworkPackage.CLIENT_INFO__HAVE:
				return have != null && !have.isEmpty();
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
		result.append(" (clientName: ");
		result.append(clientName);
		result.append(", choking: ");
		result.append(choking);
		result.append(", handshakeOK: ");
		result.append(handshakeOK);
		result.append(", interested: ");
		result.append(interested);
		result.append(", bitfield: ");
		result.append(bitfield);
		result.append(", seed: ");
		result.append(seed);
		result.append(", have: ");
		result.append(have);
		result.append(')');
		return result.toString();
	}

} // ClientInfoImpl
