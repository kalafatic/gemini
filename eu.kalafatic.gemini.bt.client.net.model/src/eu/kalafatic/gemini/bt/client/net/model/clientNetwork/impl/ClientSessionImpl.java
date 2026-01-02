/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientInfo;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Client Session</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getClientName <em>Client Name</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#isChoking <em>Choking</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#isHandshakeOK <em>Handshake OK</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#isInterested <em>Interested</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getBitfield <em>Bitfield</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#isSeed <em>Seed</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getHave <em>Have</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getReserved <em>Reserved</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getCanceledBlocks <em>Canceled Blocks</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#isReceivedKeepAlive <em>Received Keep Alive</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.impl.ClientSessionImpl#getSpeedContainer <em>Speed Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClientSessionImpl extends SessionImpl implements ClientSession {
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
	 * The default value of the '{@link #getReserved() <em>Reserved</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReserved()
	 * @generated
	 * @ordered
	 */
	protected static final byte[] RESERVED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReserved() <em>Reserved</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReserved()
	 * @generated
	 * @ordered
	 */
	protected byte[] reserved = RESERVED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCanceledBlocks() <em>Canceled Blocks</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCanceledBlocks()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> canceledBlocks;

	/**
	 * The default value of the '{@link #isReceivedKeepAlive() <em>Received Keep Alive</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isReceivedKeepAlive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECEIVED_KEEP_ALIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReceivedKeepAlive() <em>Received Keep Alive</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isReceivedKeepAlive()
	 * @generated
	 * @ordered
	 */
	protected boolean receivedKeepAlive = RECEIVED_KEEP_ALIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSpeedContainer() <em>Speed Container</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getSpeedContainer()
	 * @generated
	 * @ordered
	 */
	protected SpeedContainer speedContainer;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ClientSessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClientNetworkPackage.Literals.CLIENT_SESSION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME, oldClientName, clientName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__CHOKING, oldChoking, choking));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK, oldHandshakeOK, handshakeOK));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__INTERESTED, oldInterested, interested));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__BITFIELD, oldBitfield, bitfield));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__SEED, oldSeed, seed));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getHave() {
		if (have == null) {
			have = new EDataTypeUniqueEList<Integer>(Integer.class, this, ClientNetworkPackage.CLIENT_SESSION__HAVE);
		}
		return have;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getReserved() {
		return reserved;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReserved(byte[] newReserved) {
		byte[] oldReserved = reserved;
		reserved = newReserved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__RESERVED, oldReserved, reserved));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getCanceledBlocks() {
		if (canceledBlocks == null) {
			canceledBlocks = new EDataTypeUniqueEList<Integer>(Integer.class, this, ClientNetworkPackage.CLIENT_SESSION__CANCELED_BLOCKS);
		}
		return canceledBlocks;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReceivedKeepAlive() {
		return receivedKeepAlive;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceivedKeepAlive(boolean newReceivedKeepAlive) {
		boolean oldReceivedKeepAlive = receivedKeepAlive;
		receivedKeepAlive = newReceivedKeepAlive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__RECEIVED_KEEP_ALIVE, oldReceivedKeepAlive, receivedKeepAlive));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedContainer getSpeedContainer() {
		return speedContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpeedContainer(SpeedContainer newSpeedContainer, NotificationChain msgs) {
		SpeedContainer oldSpeedContainer = speedContainer;
		speedContainer = newSpeedContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER, oldSpeedContainer, newSpeedContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeedContainer(SpeedContainer newSpeedContainer) {
		if (newSpeedContainer != speedContainer) {
			NotificationChain msgs = null;
			if (speedContainer != null)
				msgs = ((InternalEObject)speedContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER, null, msgs);
			if (newSpeedContainer != null)
				msgs = ((InternalEObject)newSpeedContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER, null, msgs);
			msgs = basicSetSpeedContainer(newSpeedContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER, newSpeedContainer, newSpeedContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER:
				return basicSetSpeedContainer(null, msgs);
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
			case ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME:
				return getClientName();
			case ClientNetworkPackage.CLIENT_SESSION__CHOKING:
				return isChoking();
			case ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK:
				return isHandshakeOK();
			case ClientNetworkPackage.CLIENT_SESSION__INTERESTED:
				return isInterested();
			case ClientNetworkPackage.CLIENT_SESSION__BITFIELD:
				return getBitfield();
			case ClientNetworkPackage.CLIENT_SESSION__SEED:
				return isSeed();
			case ClientNetworkPackage.CLIENT_SESSION__HAVE:
				return getHave();
			case ClientNetworkPackage.CLIENT_SESSION__RESERVED:
				return getReserved();
			case ClientNetworkPackage.CLIENT_SESSION__CANCELED_BLOCKS:
				return getCanceledBlocks();
			case ClientNetworkPackage.CLIENT_SESSION__RECEIVED_KEEP_ALIVE:
				return isReceivedKeepAlive();
			case ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER:
				return getSpeedContainer();
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
			case ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME:
				setClientName((String)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__CHOKING:
				setChoking((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK:
				setHandshakeOK((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__INTERESTED:
				setInterested((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__BITFIELD:
				setBitfield((byte[])newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__SEED:
				setSeed((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__HAVE:
				getHave().clear();
				getHave().addAll((Collection<? extends Integer>)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__RESERVED:
				setReserved((byte[])newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__CANCELED_BLOCKS:
				getCanceledBlocks().clear();
				getCanceledBlocks().addAll((Collection<? extends Integer>)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__RECEIVED_KEEP_ALIVE:
				setReceivedKeepAlive((Boolean)newValue);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER:
				setSpeedContainer((SpeedContainer)newValue);
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
			case ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME:
				setClientName(CLIENT_NAME_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__CHOKING:
				setChoking(CHOKING_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK:
				setHandshakeOK(HANDSHAKE_OK_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__INTERESTED:
				setInterested(INTERESTED_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__BITFIELD:
				setBitfield(BITFIELD_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__SEED:
				setSeed(SEED_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__HAVE:
				getHave().clear();
				return;
			case ClientNetworkPackage.CLIENT_SESSION__RESERVED:
				setReserved(RESERVED_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__CANCELED_BLOCKS:
				getCanceledBlocks().clear();
				return;
			case ClientNetworkPackage.CLIENT_SESSION__RECEIVED_KEEP_ALIVE:
				setReceivedKeepAlive(RECEIVED_KEEP_ALIVE_EDEFAULT);
				return;
			case ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER:
				setSpeedContainer((SpeedContainer)null);
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
			case ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME:
				return CLIENT_NAME_EDEFAULT == null ? clientName != null : !CLIENT_NAME_EDEFAULT.equals(clientName);
			case ClientNetworkPackage.CLIENT_SESSION__CHOKING:
				return choking != CHOKING_EDEFAULT;
			case ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK:
				return handshakeOK != HANDSHAKE_OK_EDEFAULT;
			case ClientNetworkPackage.CLIENT_SESSION__INTERESTED:
				return interested != INTERESTED_EDEFAULT;
			case ClientNetworkPackage.CLIENT_SESSION__BITFIELD:
				return BITFIELD_EDEFAULT == null ? bitfield != null : !BITFIELD_EDEFAULT.equals(bitfield);
			case ClientNetworkPackage.CLIENT_SESSION__SEED:
				return seed != SEED_EDEFAULT;
			case ClientNetworkPackage.CLIENT_SESSION__HAVE:
				return have != null && !have.isEmpty();
			case ClientNetworkPackage.CLIENT_SESSION__RESERVED:
				return RESERVED_EDEFAULT == null ? reserved != null : !RESERVED_EDEFAULT.equals(reserved);
			case ClientNetworkPackage.CLIENT_SESSION__CANCELED_BLOCKS:
				return canceledBlocks != null && !canceledBlocks.isEmpty();
			case ClientNetworkPackage.CLIENT_SESSION__RECEIVED_KEEP_ALIVE:
				return receivedKeepAlive != RECEIVED_KEEP_ALIVE_EDEFAULT;
			case ClientNetworkPackage.CLIENT_SESSION__SPEED_CONTAINER:
				return speedContainer != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ClientInfo.class) {
			switch (derivedFeatureID) {
				case ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME: return ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME;
				case ClientNetworkPackage.CLIENT_SESSION__CHOKING: return ClientNetworkPackage.CLIENT_INFO__CHOKING;
				case ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK: return ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK;
				case ClientNetworkPackage.CLIENT_SESSION__INTERESTED: return ClientNetworkPackage.CLIENT_INFO__INTERESTED;
				case ClientNetworkPackage.CLIENT_SESSION__BITFIELD: return ClientNetworkPackage.CLIENT_INFO__BITFIELD;
				case ClientNetworkPackage.CLIENT_SESSION__SEED: return ClientNetworkPackage.CLIENT_INFO__SEED;
				case ClientNetworkPackage.CLIENT_SESSION__HAVE: return ClientNetworkPackage.CLIENT_INFO__HAVE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ClientInfo.class) {
			switch (baseFeatureID) {
				case ClientNetworkPackage.CLIENT_INFO__CLIENT_NAME: return ClientNetworkPackage.CLIENT_SESSION__CLIENT_NAME;
				case ClientNetworkPackage.CLIENT_INFO__CHOKING: return ClientNetworkPackage.CLIENT_SESSION__CHOKING;
				case ClientNetworkPackage.CLIENT_INFO__HANDSHAKE_OK: return ClientNetworkPackage.CLIENT_SESSION__HANDSHAKE_OK;
				case ClientNetworkPackage.CLIENT_INFO__INTERESTED: return ClientNetworkPackage.CLIENT_SESSION__INTERESTED;
				case ClientNetworkPackage.CLIENT_INFO__BITFIELD: return ClientNetworkPackage.CLIENT_SESSION__BITFIELD;
				case ClientNetworkPackage.CLIENT_INFO__SEED: return ClientNetworkPackage.CLIENT_SESSION__SEED;
				case ClientNetworkPackage.CLIENT_INFO__HAVE: return ClientNetworkPackage.CLIENT_SESSION__HAVE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", reserved: ");
		result.append(reserved);
		result.append(", canceledBlocks: ");
		result.append(canceledBlocks);
		result.append(", receivedKeepAlive: ");
		result.append(receivedKeepAlive);
		result.append(')');
		return result.toString();
	}

} // ClientSessionImpl
