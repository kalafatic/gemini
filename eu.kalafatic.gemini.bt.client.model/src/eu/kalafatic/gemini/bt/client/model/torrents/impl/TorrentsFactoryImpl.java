/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.*;

import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TorrentsFactoryImpl extends EFactoryImpl implements TorrentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TorrentsFactory init() {
		try {
			TorrentsFactory theTorrentsFactory = (TorrentsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///torrents.ecore"); 
			if (theTorrentsFactory != null) {
				return theTorrentsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TorrentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TorrentsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TorrentsPackage.TORRENTS: return createTorrents();
			case TorrentsPackage.STRING_TO_EXT_TORRENT_MAP_ENTRY: return (EObject)createStringToExtTorrentMapEntry();
			case TorrentsPackage.TORRENT: return createTorrent();
			case TorrentsPackage.INFO: return createInfo();
			case TorrentsPackage.EXT_TORRENT: return createExtTorrent();
			case TorrentsPackage.ADDITIONAL_INFO: return createAdditionalInfo();
			case TorrentsPackage.SPEED_INFO: return createSpeedInfo();
			case TorrentsPackage.DATA_FILE: return createDataFile();
			case TorrentsPackage.DATA_FILE_INFO: return createDataFileInfo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TorrentsPackage.ETORRENT_TABLE_COLUMNS:
				return createETorrentTableColumnsFromString(eDataType, initialValue);
			case TorrentsPackage.EFINISHED_TABLE_COLUMNS:
				return createEFinishedTableColumnsFromString(eDataType, initialValue);
			case TorrentsPackage.EBOOLEAN_ARRAY:
				return createEBooleanArrayFromString(eDataType, initialValue);
			case TorrentsPackage.EINTEGER_ARRAY:
				return createEIntegerArrayFromString(eDataType, initialValue);
			case TorrentsPackage.LOCK:
				return createLockFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TorrentsPackage.ETORRENT_TABLE_COLUMNS:
				return convertETorrentTableColumnsToString(eDataType, instanceValue);
			case TorrentsPackage.EFINISHED_TABLE_COLUMNS:
				return convertEFinishedTableColumnsToString(eDataType, instanceValue);
			case TorrentsPackage.EBOOLEAN_ARRAY:
				return convertEBooleanArrayToString(eDataType, instanceValue);
			case TorrentsPackage.EINTEGER_ARRAY:
				return convertEIntegerArrayToString(eDataType, instanceValue);
			case TorrentsPackage.LOCK:
				return convertLockToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Torrents createTorrents() {
		TorrentsImpl torrents = new TorrentsImpl();
		return torrents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, ExtTorrent> createStringToExtTorrentMapEntry() {
		StringToExtTorrentMapEntryImpl stringToExtTorrentMapEntry = new StringToExtTorrentMapEntryImpl();
		return stringToExtTorrentMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Torrent createTorrent() {
		TorrentImpl torrent = new TorrentImpl();
		return torrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Info createInfo() {
		InfoImpl info = new InfoImpl();
		return info;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtTorrent createExtTorrent() {
		ExtTorrentImpl extTorrent = new ExtTorrentImpl();
		return extTorrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdditionalInfo createAdditionalInfo() {
		AdditionalInfoImpl additionalInfo = new AdditionalInfoImpl();
		return additionalInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedInfo createSpeedInfo() {
		SpeedInfoImpl speedInfo = new SpeedInfoImpl();
		return speedInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFile createDataFile() {
		DataFileImpl dataFile = new DataFileImpl();
		return dataFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFileInfo createDataFileInfo() {
		DataFileInfoImpl dataFileInfo = new DataFileInfoImpl();
		return dataFileInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ETorrentTableColumns createETorrentTableColumnsFromString(EDataType eDataType, String initialValue) {
		ETorrentTableColumns result = ETorrentTableColumns.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertETorrentTableColumnsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EFinishedTableColumns createEFinishedTableColumnsFromString(EDataType eDataType, String initialValue) {
		EFinishedTableColumns result = EFinishedTableColumns.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEFinishedTableColumnsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean[] createEBooleanArrayFromString(EDataType eDataType, String initialValue) {
		return (boolean[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEBooleanArrayToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] createEIntegerArrayFromString(EDataType eDataType, String initialValue) {
		return (int[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEIntegerArrayToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReentrantReadWriteLock createLockFromString(EDataType eDataType, String initialValue) {
		return (ReentrantReadWriteLock)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLockToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TorrentsPackage getTorrentsPackage() {
		return (TorrentsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TorrentsPackage getPackage() {
		return TorrentsPackage.eINSTANCE;
	}

} //TorrentsFactoryImpl
