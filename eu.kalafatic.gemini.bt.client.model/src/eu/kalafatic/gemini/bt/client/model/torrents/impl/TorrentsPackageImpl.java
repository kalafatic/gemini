/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.impl;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Torrents;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsFactory;
import eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage;

import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TorrentsPackageImpl extends EPackageImpl implements TorrentsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass torrentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToExtTorrentMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass torrentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extTorrentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass additionalInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass speedInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataFileInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eTorrentTableColumnsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eFinishedTableColumnsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eBooleanArrayEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eIntegerArrayEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType lockEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TorrentsPackageImpl() {
		super(eNS_URI, TorrentsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TorrentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TorrentsPackage init() {
		if (isInited) return (TorrentsPackage)EPackage.Registry.INSTANCE.getEPackage(TorrentsPackage.eNS_URI);

		// Obtain or create and register package
		TorrentsPackageImpl theTorrentsPackage = (TorrentsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TorrentsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TorrentsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTorrentsPackage.createPackageContents();

		// Initialize created meta-data
		theTorrentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTorrentsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TorrentsPackage.eNS_URI, theTorrentsPackage);
		return theTorrentsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTorrents() {
		return torrentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTorrents_TorrentMap() {
		return (EReference)torrentsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTorrents_FinishedTorrentsMap() {
		return (EReference)torrentsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToExtTorrentMapEntry() {
		return stringToExtTorrentMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToExtTorrentMapEntry_Key() {
		return (EAttribute)stringToExtTorrentMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToExtTorrentMapEntry_Value() {
		return (EReference)stringToExtTorrentMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTorrent() {
		return torrentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_Name() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_CreationDate() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_CreationBy() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_ModifiedBy() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_Comment() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_Encoding() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTorrent_Info() {
		return (EReference)torrentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTorrent_AnnounceList() {
		return (EAttribute)torrentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfo() {
		return infoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Pieces() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_PieceLength() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Private() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Hash() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfo_Files() {
		return (EReference)infoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_MaxPiecesPerFile() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtTorrent() {
		return extTorrentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Row() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Enabled() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Finished() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Status() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_RealBitfield() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_ModelBitfield() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Handshake() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Lock() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtTorrent_AdditionalInfo() {
		return (EReference)extTorrentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Path() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtTorrent_Map() {
		return (EAttribute)extTorrentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdditionalInfo() {
		return additionalInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Seeds() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Peers() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Downloaders() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Downloaded() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Uploaded() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Completed() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_CompletedPieces() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_Description() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_FileSize() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdditionalInfo_VerifyList() {
		return (EAttribute)additionalInfoEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpeedInfo() {
		return speedInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedInfo_DwnldSpeed() {
		return (EAttribute)speedInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedInfo_UpldSpeed() {
		return (EAttribute)speedInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpeedInfo_Lock() {
		return (EAttribute)speedInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFile() {
		return dataFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFile_Name() {
		return (EAttribute)dataFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFile_Md5sum() {
		return (EAttribute)dataFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFile_Path() {
		return (EAttribute)dataFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFile_Length() {
		return (EAttribute)dataFileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataFileInfo() {
		return dataFileInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Index() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Enabled() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Pieces() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Begin() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_End() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Lock() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Raf() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataFileInfo_Recorded() {
		return (EAttribute)dataFileInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getETorrentTableColumns() {
		return eTorrentTableColumnsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEFinishedTableColumns() {
		return eFinishedTableColumnsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEBooleanArray() {
		return eBooleanArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEIntegerArray() {
		return eIntegerArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLock() {
		return lockEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TorrentsFactory getTorrentsFactory() {
		return (TorrentsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		torrentsEClass = createEClass(TORRENTS);
		createEReference(torrentsEClass, TORRENTS__TORRENT_MAP);
		createEReference(torrentsEClass, TORRENTS__FINISHED_TORRENTS_MAP);

		stringToExtTorrentMapEntryEClass = createEClass(STRING_TO_EXT_TORRENT_MAP_ENTRY);
		createEAttribute(stringToExtTorrentMapEntryEClass, STRING_TO_EXT_TORRENT_MAP_ENTRY__KEY);
		createEReference(stringToExtTorrentMapEntryEClass, STRING_TO_EXT_TORRENT_MAP_ENTRY__VALUE);

		torrentEClass = createEClass(TORRENT);
		createEAttribute(torrentEClass, TORRENT__NAME);
		createEAttribute(torrentEClass, TORRENT__CREATION_DATE);
		createEAttribute(torrentEClass, TORRENT__CREATION_BY);
		createEAttribute(torrentEClass, TORRENT__MODIFIED_BY);
		createEAttribute(torrentEClass, TORRENT__COMMENT);
		createEAttribute(torrentEClass, TORRENT__ENCODING);
		createEReference(torrentEClass, TORRENT__INFO);
		createEAttribute(torrentEClass, TORRENT__ANNOUNCE_LIST);

		infoEClass = createEClass(INFO);
		createEAttribute(infoEClass, INFO__PIECES);
		createEAttribute(infoEClass, INFO__PIECE_LENGTH);
		createEAttribute(infoEClass, INFO__PRIVATE);
		createEAttribute(infoEClass, INFO__HASH);
		createEReference(infoEClass, INFO__FILES);
		createEAttribute(infoEClass, INFO__MAX_PIECES_PER_FILE);

		extTorrentEClass = createEClass(EXT_TORRENT);
		createEAttribute(extTorrentEClass, EXT_TORRENT__ROW);
		createEAttribute(extTorrentEClass, EXT_TORRENT__ENABLED);
		createEAttribute(extTorrentEClass, EXT_TORRENT__FINISHED);
		createEAttribute(extTorrentEClass, EXT_TORRENT__STATUS);
		createEAttribute(extTorrentEClass, EXT_TORRENT__REAL_BITFIELD);
		createEAttribute(extTorrentEClass, EXT_TORRENT__MODEL_BITFIELD);
		createEAttribute(extTorrentEClass, EXT_TORRENT__HANDSHAKE);
		createEAttribute(extTorrentEClass, EXT_TORRENT__LOCK);
		createEReference(extTorrentEClass, EXT_TORRENT__ADDITIONAL_INFO);
		createEAttribute(extTorrentEClass, EXT_TORRENT__PATH);
		createEAttribute(extTorrentEClass, EXT_TORRENT__MAP);

		additionalInfoEClass = createEClass(ADDITIONAL_INFO);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__SEEDS);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__PEERS);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__DOWNLOADERS);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__DOWNLOADED);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__UPLOADED);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__COMPLETED);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__COMPLETED_PIECES);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__DESCRIPTION);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__FILE_SIZE);
		createEAttribute(additionalInfoEClass, ADDITIONAL_INFO__VERIFY_LIST);

		speedInfoEClass = createEClass(SPEED_INFO);
		createEAttribute(speedInfoEClass, SPEED_INFO__DWNLD_SPEED);
		createEAttribute(speedInfoEClass, SPEED_INFO__UPLD_SPEED);
		createEAttribute(speedInfoEClass, SPEED_INFO__LOCK);

		dataFileEClass = createEClass(DATA_FILE);
		createEAttribute(dataFileEClass, DATA_FILE__NAME);
		createEAttribute(dataFileEClass, DATA_FILE__MD5SUM);
		createEAttribute(dataFileEClass, DATA_FILE__PATH);
		createEAttribute(dataFileEClass, DATA_FILE__LENGTH);

		dataFileInfoEClass = createEClass(DATA_FILE_INFO);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__INDEX);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__ENABLED);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__PIECES);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__BEGIN);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__END);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__LOCK);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__RAF);
		createEAttribute(dataFileInfoEClass, DATA_FILE_INFO__RECORDED);

		// Create enums
		eTorrentTableColumnsEEnum = createEEnum(ETORRENT_TABLE_COLUMNS);
		eFinishedTableColumnsEEnum = createEEnum(EFINISHED_TABLE_COLUMNS);

		// Create data types
		eBooleanArrayEDataType = createEDataType(EBOOLEAN_ARRAY);
		eIntegerArrayEDataType = createEDataType(EINTEGER_ARRAY);
		lockEDataType = createEDataType(LOCK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		extTorrentEClass.getESuperTypes().add(this.getTorrent());
		additionalInfoEClass.getESuperTypes().add(this.getSpeedInfo());
		dataFileEClass.getESuperTypes().add(this.getDataFileInfo());

		// Initialize classes and features; add operations and parameters
		initEClass(torrentsEClass, Torrents.class, "Torrents", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTorrents_TorrentMap(), this.getStringToExtTorrentMapEntry(), null, "torrentMap", null, 0, -1, Torrents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTorrents_FinishedTorrentsMap(), this.getStringToExtTorrentMapEntry(), null, "finishedTorrentsMap", null, 0, -1, Torrents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToExtTorrentMapEntryEClass, Map.Entry.class, "StringToExtTorrentMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToExtTorrentMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToExtTorrentMapEntry_Value(), this.getExtTorrent(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(torrentEClass, Torrent.class, "Torrent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTorrent_Name(), ecorePackage.getEString(), "name", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_CreationDate(), ecorePackage.getELong(), "creationDate", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_CreationBy(), ecorePackage.getEString(), "creationBy", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_ModifiedBy(), ecorePackage.getEString(), "modifiedBy", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_Encoding(), ecorePackage.getEString(), "encoding", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTorrent_Info(), this.getInfo(), null, "info", null, 0, 1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTorrent_AnnounceList(), ecorePackage.getEString(), "announceList", null, 1, -1, Torrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infoEClass, Info.class, "Info", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInfo_Pieces(), ecorePackage.getEByteArray(), "pieces", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_PieceLength(), ecorePackage.getEInt(), "pieceLength", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_Private(), ecorePackage.getEBoolean(), "private", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_Hash(), ecorePackage.getEByteArray(), "hash", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInfo_Files(), this.getDataFile(), null, "files", null, 0, -1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_MaxPiecesPerFile(), ecorePackage.getEInt(), "maxPiecesPerFile", null, 0, 1, Info.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extTorrentEClass, ExtTorrent.class, "ExtTorrent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtTorrent_Row(), ecorePackage.getEInt(), "row", null, 0, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Enabled(), ecorePackage.getEBoolean(), "enabled", "false", 0, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Finished(), ecorePackage.getEBoolean(), "finished", "false", 0, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Status(), ecorePackage.getEEnumerator(), "status", null, 0, 1, ExtTorrent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_RealBitfield(), ecorePackage.getEByteArray(), "realBitfield", null, 0, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_ModelBitfield(), this.getEBooleanArray(), "modelBitfield", null, 0, 1, ExtTorrent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Handshake(), ecorePackage.getEByteArray(), "handshake", null, 0, 1, ExtTorrent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Lock(), this.getLock(), "lock", null, 0, 1, ExtTorrent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtTorrent_AdditionalInfo(), this.getAdditionalInfo(), null, "additionalInfo", null, 1, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtTorrent_Path(), ecorePackage.getEString(), "path", null, 0, 1, ExtTorrent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getExtTorrent_Map(), g1, "map", null, 0, 1, ExtTorrent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(additionalInfoEClass, AdditionalInfo.class, "AdditionalInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdditionalInfo_Seeds(), ecorePackage.getEInt(), "seeds", "0", 0, 1, AdditionalInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Peers(), ecorePackage.getEInt(), "peers", "0", 0, 1, AdditionalInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Downloaders(), ecorePackage.getEInt(), "downloaders", "0", 0, 1, AdditionalInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Downloaded(), ecorePackage.getELong(), "downloaded", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Uploaded(), ecorePackage.getELong(), "uploaded", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Completed(), ecorePackage.getEInt(), "completed", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_CompletedPieces(), ecorePackage.getEInt(), "completedPieces", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_Description(), ecorePackage.getEString(), "description", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_FileSize(), ecorePackage.getELong(), "fileSize", null, 0, 1, AdditionalInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdditionalInfo_VerifyList(), ecorePackage.getEInt(), "verifyList", null, 0, -1, AdditionalInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(speedInfoEClass, SpeedInfo.class, "SpeedInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpeedInfo_DwnldSpeed(), ecorePackage.getEFloat(), "dwnldSpeed", null, 0, 1, SpeedInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpeedInfo_UpldSpeed(), ecorePackage.getEFloat(), "upldSpeed", null, 0, 1, SpeedInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpeedInfo_Lock(), this.getLock(), "lock", null, 0, 1, SpeedInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFileEClass, DataFile.class, "DataFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataFile_Name(), ecorePackage.getEString(), "name", null, 0, 1, DataFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFile_Md5sum(), ecorePackage.getEString(), "md5sum", null, 0, 1, DataFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFile_Path(), ecorePackage.getEString(), "path", null, 0, 1, DataFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFile_Length(), ecorePackage.getELong(), "length", null, 0, 1, DataFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataFileInfoEClass, DataFileInfo.class, "DataFileInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataFileInfo_Index(), ecorePackage.getEInt(), "index", null, 0, 1, DataFileInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, DataFileInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Pieces(), this.getEIntegerArray(), "pieces", null, 0, 1, DataFileInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Begin(), ecorePackage.getELong(), "begin", null, 0, 1, DataFileInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_End(), ecorePackage.getELong(), "end", null, 0, 1, DataFileInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Lock(), this.getLock(), "lock", null, 0, 1, DataFileInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Raf(), ecorePackage.getEJavaObject(), "raf", null, 0, 1, DataFileInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataFileInfo_Recorded(), ecorePackage.getELong(), "recorded", null, 0, 1, DataFileInfo.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eTorrentTableColumnsEEnum, ETorrentTableColumns.class, "ETorrentTableColumns");
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.ENABLED_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.NAME_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.STATUS_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.SEEDS_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.PEERS_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.DOWNLOADERS_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.SIZE_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.PIECES_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.DOWNLOADED_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.COMPLETED_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.UPLOADED_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.DWNLD_SPEED_COL);
		addEEnumLiteral(eTorrentTableColumnsEEnum, ETorrentTableColumns.UPLD_SPEED_COL);

		initEEnum(eFinishedTableColumnsEEnum, EFinishedTableColumns.class, "EFinishedTableColumns");
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.ENABLED_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.NAME_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.STATUS_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.DOWNLOADERS_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.SIZE_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.PIECES_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.UPLOADED_COL);
		addEEnumLiteral(eFinishedTableColumnsEEnum, EFinishedTableColumns.UPLD_SPEED_COL);

		// Initialize data types
		initEDataType(eBooleanArrayEDataType, boolean[].class, "EBooleanArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(eIntegerArrayEDataType, int[].class, "EIntegerArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(lockEDataType, ReentrantReadWriteLock.class, "Lock", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// null
		createNullAnnotations();
	}

	/**
	 * Initializes the annotations for <b>null</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createNullAnnotations() {
		String source = null;		
		addAnnotation
		  (getTorrents_TorrentMap(), 
		   source, 
		   new String[] {
		   });
	}

} //TorrentsPackageImpl
