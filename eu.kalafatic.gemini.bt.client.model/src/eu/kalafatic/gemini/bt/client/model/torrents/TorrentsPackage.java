/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsFactory
 * @model kind="package"
 * @generated
 */
public interface TorrentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "torrents";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///torrents.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "torrents";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TorrentsPackage eINSTANCE = eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl <em>Torrents</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getTorrents()
	 * @generated
	 */
	int TORRENTS = 0;

	/**
	 * The feature id for the '<em><b>Torrent Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENTS__TORRENT_MAP = 0;

	/**
	 * The feature id for the '<em><b>Finished Torrents Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENTS__FINISHED_TORRENTS_MAP = 1;

	/**
	 * The number of structural features of the '<em>Torrents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENTS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.StringToExtTorrentMapEntryImpl <em>String To Ext Torrent Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.StringToExtTorrentMapEntryImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getStringToExtTorrentMapEntry()
	 * @generated
	 */
	int STRING_TO_EXT_TORRENT_MAP_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_EXT_TORRENT_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_EXT_TORRENT_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Ext Torrent Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_EXT_TORRENT_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl <em>Torrent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getTorrent()
	 * @generated
	 */
	int TORRENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__CREATION_DATE = 1;

	/**
	 * The feature id for the '<em><b>Creation By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__CREATION_BY = 2;

	/**
	 * The feature id for the '<em><b>Modified By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__MODIFIED_BY = 3;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__COMMENT = 4;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__ENCODING = 5;

	/**
	 * The feature id for the '<em><b>Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__INFO = 6;

	/**
	 * The feature id for the '<em><b>Announce List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT__ANNOUNCE_LIST = 7;

	/**
	 * The number of structural features of the '<em>Torrent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TORRENT_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getInfo()
	 * @generated
	 */
	int INFO = 3;

	/**
	 * The feature id for the '<em><b>Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__PIECES = 0;

	/**
	 * The feature id for the '<em><b>Piece Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__PIECE_LENGTH = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__PRIVATE = 2;

	/**
	 * The feature id for the '<em><b>Hash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__HASH = 3;

	/**
	 * The feature id for the '<em><b>Files</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__FILES = 4;

	/**
	 * The feature id for the '<em><b>Max Pieces Per File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__MAX_PIECES_PER_FILE = 5;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl <em>Ext Torrent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getExtTorrent()
	 * @generated
	 */
	int EXT_TORRENT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__NAME = TORRENT__NAME;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__CREATION_DATE = TORRENT__CREATION_DATE;

	/**
	 * The feature id for the '<em><b>Creation By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__CREATION_BY = TORRENT__CREATION_BY;

	/**
	 * The feature id for the '<em><b>Modified By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__MODIFIED_BY = TORRENT__MODIFIED_BY;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__COMMENT = TORRENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__ENCODING = TORRENT__ENCODING;

	/**
	 * The feature id for the '<em><b>Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__INFO = TORRENT__INFO;

	/**
	 * The feature id for the '<em><b>Announce List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__ANNOUNCE_LIST = TORRENT__ANNOUNCE_LIST;

	/**
	 * The feature id for the '<em><b>Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__ROW = TORRENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__ENABLED = TORRENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Finished</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__FINISHED = TORRENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__STATUS = TORRENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Real Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__REAL_BITFIELD = TORRENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Model Bitfield</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__MODEL_BITFIELD = TORRENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Handshake</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__HANDSHAKE = TORRENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__LOCK = TORRENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Additional Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__ADDITIONAL_INFO = TORRENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__PATH = TORRENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT__MAP = TORRENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Ext Torrent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXT_TORRENT_FEATURE_COUNT = TORRENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl <em>Speed Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getSpeedInfo()
	 * @generated
	 */
	int SPEED_INFO = 6;

	/**
	 * The feature id for the '<em><b>Dwnld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_INFO__DWNLD_SPEED = 0;

	/**
	 * The feature id for the '<em><b>Upld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_INFO__UPLD_SPEED = 1;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_INFO__LOCK = 2;

	/**
	 * The number of structural features of the '<em>Speed Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_INFO_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl <em>Additional Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getAdditionalInfo()
	 * @generated
	 */
	int ADDITIONAL_INFO = 5;

	/**
	 * The feature id for the '<em><b>Dwnld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__DWNLD_SPEED = SPEED_INFO__DWNLD_SPEED;

	/**
	 * The feature id for the '<em><b>Upld Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__UPLD_SPEED = SPEED_INFO__UPLD_SPEED;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__LOCK = SPEED_INFO__LOCK;

	/**
	 * The feature id for the '<em><b>Seeds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__SEEDS = SPEED_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Peers</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__PEERS = SPEED_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Downloaders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__DOWNLOADERS = SPEED_INFO_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Downloaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__DOWNLOADED = SPEED_INFO_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Uploaded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__UPLOADED = SPEED_INFO_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__COMPLETED = SPEED_INFO_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Completed Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__COMPLETED_PIECES = SPEED_INFO_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__DESCRIPTION = SPEED_INFO_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>File Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__FILE_SIZE = SPEED_INFO_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Verify List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO__VERIFY_LIST = SPEED_INFO_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Additional Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_INFO_FEATURE_COUNT = SPEED_INFO_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl <em>Data File Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getDataFileInfo()
	 * @generated
	 */
	int DATA_FILE_INFO = 8;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__INDEX = 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__ENABLED = 1;

	/**
	 * The feature id for the '<em><b>Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__PIECES = 2;

	/**
	 * The feature id for the '<em><b>Begin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__BEGIN = 3;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__END = 4;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__LOCK = 5;

	/**
	 * The feature id for the '<em><b>Raf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__RAF = 6;

	/**
	 * The feature id for the '<em><b>Recorded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO__RECORDED = 7;

	/**
	 * The number of structural features of the '<em>Data File Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_INFO_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl <em>Data File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getDataFile()
	 * @generated
	 */
	int DATA_FILE = 7;

	/**
	 * The feature id for the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__INDEX = DATA_FILE_INFO__INDEX;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__ENABLED = DATA_FILE_INFO__ENABLED;

	/**
	 * The feature id for the '<em><b>Pieces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__PIECES = DATA_FILE_INFO__PIECES;

	/**
	 * The feature id for the '<em><b>Begin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__BEGIN = DATA_FILE_INFO__BEGIN;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__END = DATA_FILE_INFO__END;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__LOCK = DATA_FILE_INFO__LOCK;

	/**
	 * The feature id for the '<em><b>Raf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__RAF = DATA_FILE_INFO__RAF;

	/**
	 * The feature id for the '<em><b>Recorded</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__RECORDED = DATA_FILE_INFO__RECORDED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__NAME = DATA_FILE_INFO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Md5sum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__MD5SUM = DATA_FILE_INFO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__PATH = DATA_FILE_INFO_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE__LENGTH = DATA_FILE_INFO_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Data File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FILE_FEATURE_COUNT = DATA_FILE_INFO_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns <em>ETorrent Table Columns</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getETorrentTableColumns()
	 * @generated
	 */
	int ETORRENT_TABLE_COLUMNS = 9;

	/**
	 * The meta object id for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns <em>EFinished Table Columns</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEFinishedTableColumns()
	 * @generated
	 */
	int EFINISHED_TABLE_COLUMNS = 10;

	/**
	 * The meta object id for the '<em>EBoolean Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEBooleanArray()
	 * @generated
	 */
	int EBOOLEAN_ARRAY = 11;

	/**
	 * The meta object id for the '<em>EInteger Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEIntegerArray()
	 * @generated
	 */
	int EINTEGER_ARRAY = 12;

	/**
	 * The meta object id for the '<em>Lock</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.concurrent.locks.ReentrantReadWriteLock
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getLock()
	 * @generated
	 */
	int LOCK = 13;


	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents <em>Torrents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Torrents</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrents
	 * @generated
	 */
	EClass getTorrents();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getTorrentMap <em>Torrent Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Torrent Map</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getTorrentMap()
	 * @see #getTorrents()
	 * @generated
	 */
	EReference getTorrents_TorrentMap();

	/**
	 * Returns the meta object for the map '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getFinishedTorrentsMap <em>Finished Torrents Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Finished Torrents Map</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getFinishedTorrentsMap()
	 * @see #getTorrents()
	 * @generated
	 */
	EReference getTorrents_FinishedTorrentsMap();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Ext Torrent Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Ext Torrent Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent" valueContainment="true"
	 * @generated
	 */
	EClass getStringToExtTorrentMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToExtTorrentMapEntry()
	 * @generated
	 */
	EAttribute getStringToExtTorrentMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToExtTorrentMapEntry()
	 * @generated
	 */
	EReference getStringToExtTorrentMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent <em>Torrent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Torrent</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent
	 * @generated
	 */
	EClass getTorrent();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getName()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationDate()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_CreationDate();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationBy <em>Creation By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation By</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getCreationBy()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_CreationBy();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getModifiedBy <em>Modified By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified By</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getModifiedBy()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_ModifiedBy();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getComment()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_Comment();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getEncoding()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_Encoding();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getInfo()
	 * @see #getTorrent()
	 * @generated
	 */
	EReference getTorrent_Info();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getAnnounceList <em>Announce List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Announce List</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent#getAnnounceList()
	 * @see #getTorrent()
	 * @generated
	 */
	EAttribute getTorrent_AnnounceList();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info
	 * @generated
	 */
	EClass getInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieces <em>Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieces()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Pieces();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieceLength <em>Piece Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Piece Length</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#getPieceLength()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_PieceLength();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#isPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#isPrivate()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Private();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getHash <em>Hash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hash</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#getHash()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Hash();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getFiles <em>Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Files</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#getFiles()
	 * @see #getInfo()
	 * @generated
	 */
	EReference getInfo_Files();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info#getMaxPiecesPerFile <em>Max Pieces Per File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Pieces Per File</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info#getMaxPiecesPerFile()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_MaxPiecesPerFile();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent <em>Ext Torrent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ext Torrent</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent
	 * @generated
	 */
	EClass getExtTorrent();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRow()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Row();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isEnabled()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isFinished <em>Finished</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finished</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#isFinished()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Finished();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getStatus()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Status();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRealBitfield <em>Real Bitfield</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Bitfield</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getRealBitfield()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_RealBitfield();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getModelBitfield <em>Model Bitfield</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Bitfield</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getModelBitfield()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_ModelBitfield();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getHandshake <em>Handshake</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Handshake</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getHandshake()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Handshake();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getLock()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Lock();

	/**
	 * Returns the meta object for the containment reference '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getAdditionalInfo <em>Additional Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Additional Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getAdditionalInfo()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EReference getExtTorrent_AdditionalInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getPath()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Path();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent#getMap()
	 * @see #getExtTorrent()
	 * @generated
	 */
	EAttribute getExtTorrent_Map();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo <em>Additional Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Additional Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo
	 * @generated
	 */
	EClass getAdditionalInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getSeeds <em>Seeds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seeds</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getSeeds()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Seeds();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getPeers <em>Peers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peers</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getPeers()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Peers();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaders <em>Downloaders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Downloaders</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaders()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Downloaders();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaded <em>Downloaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Downloaded</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDownloaded()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Downloaded();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getUploaded <em>Uploaded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uploaded</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getUploaded()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Uploaded();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompleted <em>Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completed</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompleted()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Completed();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompletedPieces <em>Completed Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completed Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getCompletedPieces()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_CompletedPieces();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getDescription()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_Description();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getFileSize <em>File Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Size</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getFileSize()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_FileSize();

	/**
	 * Returns the meta object for the attribute list '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getVerifyList <em>Verify List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Verify List</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo#getVerifyList()
	 * @see #getAdditionalInfo()
	 * @generated
	 */
	EAttribute getAdditionalInfo_VerifyList();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo <em>Speed Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Speed Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo
	 * @generated
	 */
	EClass getSpeedInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getDwnldSpeed <em>Dwnld Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dwnld Speed</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getDwnldSpeed()
	 * @see #getSpeedInfo()
	 * @generated
	 */
	EAttribute getSpeedInfo_DwnldSpeed();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getUpldSpeed <em>Upld Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upld Speed</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getUpldSpeed()
	 * @see #getSpeedInfo()
	 * @generated
	 */
	EAttribute getSpeedInfo_UpldSpeed();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo#getLock()
	 * @see #getSpeedInfo()
	 * @generated
	 */
	EAttribute getSpeedInfo_Lock();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile <em>Data File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data File</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile
	 * @generated
	 */
	EClass getDataFile();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getName()
	 * @see #getDataFile()
	 * @generated
	 */
	EAttribute getDataFile_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getMd5sum <em>Md5sum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Md5sum</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getMd5sum()
	 * @see #getDataFile()
	 * @generated
	 */
	EAttribute getDataFile_Md5sum();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getPath()
	 * @see #getDataFile()
	 * @generated
	 */
	EAttribute getDataFile_Path();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile#getLength()
	 * @see #getDataFile()
	 * @generated
	 */
	EAttribute getDataFile_Length();

	/**
	 * Returns the meta object for class '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo <em>Data File Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data File Info</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo
	 * @generated
	 */
	EClass getDataFileInfo();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getIndex()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Index();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#isEnabled()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getPieces <em>Pieces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pieces</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getPieces()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Pieces();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getBegin <em>Begin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Begin</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getBegin()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Begin();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getEnd()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_End();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getLock()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Lock();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRaf <em>Raf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Raf</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRaf()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Raf();

	/**
	 * Returns the meta object for the attribute '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRecorded <em>Recorded</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recorded</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo#getRecorded()
	 * @see #getDataFileInfo()
	 * @generated
	 */
	EAttribute getDataFileInfo_Recorded();

	/**
	 * Returns the meta object for enum '{@link eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns <em>ETorrent Table Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>ETorrent Table Columns</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns
	 * @generated
	 */
	EEnum getETorrentTableColumns();

	/**
	 * Returns the meta object for enum '{@link eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns <em>EFinished Table Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EFinished Table Columns</em>'.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns
	 * @generated
	 */
	EEnum getEFinishedTableColumns();

	/**
	 * Returns the meta object for data type '<em>EBoolean Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EBoolean Array</em>'.
	 * @model instanceClass="boolean[]"
	 * @generated
	 */
	EDataType getEBooleanArray();

	/**
	 * Returns the meta object for data type '<em>EInteger Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EInteger Array</em>'.
	 * @model instanceClass="int[]"
	 * @generated
	 */
	EDataType getEIntegerArray();

	/**
	 * Returns the meta object for data type '{@link java.util.concurrent.locks.ReentrantReadWriteLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Lock</em>'.
	 * @see java.util.concurrent.locks.ReentrantReadWriteLock
	 * @model instanceClass="java.util.concurrent.locks.ReentrantReadWriteLock"
	 * @generated
	 */
	EDataType getLock();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TorrentsFactory getTorrentsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl <em>Torrents</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getTorrents()
		 * @generated
		 */
		EClass TORRENTS = eINSTANCE.getTorrents();

		/**
		 * The meta object literal for the '<em><b>Torrent Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TORRENTS__TORRENT_MAP = eINSTANCE.getTorrents_TorrentMap();

		/**
		 * The meta object literal for the '<em><b>Finished Torrents Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TORRENTS__FINISHED_TORRENTS_MAP = eINSTANCE.getTorrents_FinishedTorrentsMap();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.StringToExtTorrentMapEntryImpl <em>String To Ext Torrent Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.StringToExtTorrentMapEntryImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getStringToExtTorrentMapEntry()
		 * @generated
		 */
		EClass STRING_TO_EXT_TORRENT_MAP_ENTRY = eINSTANCE.getStringToExtTorrentMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_EXT_TORRENT_MAP_ENTRY__KEY = eINSTANCE.getStringToExtTorrentMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_EXT_TORRENT_MAP_ENTRY__VALUE = eINSTANCE.getStringToExtTorrentMapEntry_Value();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl <em>Torrent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getTorrent()
		 * @generated
		 */
		EClass TORRENT = eINSTANCE.getTorrent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__NAME = eINSTANCE.getTorrent_Name();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__CREATION_DATE = eINSTANCE.getTorrent_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Creation By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__CREATION_BY = eINSTANCE.getTorrent_CreationBy();

		/**
		 * The meta object literal for the '<em><b>Modified By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__MODIFIED_BY = eINSTANCE.getTorrent_ModifiedBy();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__COMMENT = eINSTANCE.getTorrent_Comment();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__ENCODING = eINSTANCE.getTorrent_Encoding();

		/**
		 * The meta object literal for the '<em><b>Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TORRENT__INFO = eINSTANCE.getTorrent_Info();

		/**
		 * The meta object literal for the '<em><b>Announce List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TORRENT__ANNOUNCE_LIST = eINSTANCE.getTorrent_AnnounceList();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.InfoImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getInfo()
		 * @generated
		 */
		EClass INFO = eINSTANCE.getInfo();

		/**
		 * The meta object literal for the '<em><b>Pieces</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__PIECES = eINSTANCE.getInfo_Pieces();

		/**
		 * The meta object literal for the '<em><b>Piece Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__PIECE_LENGTH = eINSTANCE.getInfo_PieceLength();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__PRIVATE = eINSTANCE.getInfo_Private();

		/**
		 * The meta object literal for the '<em><b>Hash</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__HASH = eINSTANCE.getInfo_Hash();

		/**
		 * The meta object literal for the '<em><b>Files</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFO__FILES = eINSTANCE.getInfo_Files();

		/**
		 * The meta object literal for the '<em><b>Max Pieces Per File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__MAX_PIECES_PER_FILE = eINSTANCE.getInfo_MaxPiecesPerFile();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl <em>Ext Torrent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.ExtTorrentImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getExtTorrent()
		 * @generated
		 */
		EClass EXT_TORRENT = eINSTANCE.getExtTorrent();

		/**
		 * The meta object literal for the '<em><b>Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__ROW = eINSTANCE.getExtTorrent_Row();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__ENABLED = eINSTANCE.getExtTorrent_Enabled();

		/**
		 * The meta object literal for the '<em><b>Finished</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__FINISHED = eINSTANCE.getExtTorrent_Finished();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__STATUS = eINSTANCE.getExtTorrent_Status();

		/**
		 * The meta object literal for the '<em><b>Real Bitfield</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__REAL_BITFIELD = eINSTANCE.getExtTorrent_RealBitfield();

		/**
		 * The meta object literal for the '<em><b>Model Bitfield</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__MODEL_BITFIELD = eINSTANCE.getExtTorrent_ModelBitfield();

		/**
		 * The meta object literal for the '<em><b>Handshake</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__HANDSHAKE = eINSTANCE.getExtTorrent_Handshake();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__LOCK = eINSTANCE.getExtTorrent_Lock();

		/**
		 * The meta object literal for the '<em><b>Additional Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXT_TORRENT__ADDITIONAL_INFO = eINSTANCE.getExtTorrent_AdditionalInfo();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__PATH = eINSTANCE.getExtTorrent_Path();

		/**
		 * The meta object literal for the '<em><b>Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXT_TORRENT__MAP = eINSTANCE.getExtTorrent_Map();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl <em>Additional Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.AdditionalInfoImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getAdditionalInfo()
		 * @generated
		 */
		EClass ADDITIONAL_INFO = eINSTANCE.getAdditionalInfo();

		/**
		 * The meta object literal for the '<em><b>Seeds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__SEEDS = eINSTANCE.getAdditionalInfo_Seeds();

		/**
		 * The meta object literal for the '<em><b>Peers</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__PEERS = eINSTANCE.getAdditionalInfo_Peers();

		/**
		 * The meta object literal for the '<em><b>Downloaders</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__DOWNLOADERS = eINSTANCE.getAdditionalInfo_Downloaders();

		/**
		 * The meta object literal for the '<em><b>Downloaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__DOWNLOADED = eINSTANCE.getAdditionalInfo_Downloaded();

		/**
		 * The meta object literal for the '<em><b>Uploaded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__UPLOADED = eINSTANCE.getAdditionalInfo_Uploaded();

		/**
		 * The meta object literal for the '<em><b>Completed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__COMPLETED = eINSTANCE.getAdditionalInfo_Completed();

		/**
		 * The meta object literal for the '<em><b>Completed Pieces</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__COMPLETED_PIECES = eINSTANCE.getAdditionalInfo_CompletedPieces();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__DESCRIPTION = eINSTANCE.getAdditionalInfo_Description();

		/**
		 * The meta object literal for the '<em><b>File Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__FILE_SIZE = eINSTANCE.getAdditionalInfo_FileSize();

		/**
		 * The meta object literal for the '<em><b>Verify List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_INFO__VERIFY_LIST = eINSTANCE.getAdditionalInfo_VerifyList();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl <em>Speed Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.SpeedInfoImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getSpeedInfo()
		 * @generated
		 */
		EClass SPEED_INFO = eINSTANCE.getSpeedInfo();

		/**
		 * The meta object literal for the '<em><b>Dwnld Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_INFO__DWNLD_SPEED = eINSTANCE.getSpeedInfo_DwnldSpeed();

		/**
		 * The meta object literal for the '<em><b>Upld Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_INFO__UPLD_SPEED = eINSTANCE.getSpeedInfo_UpldSpeed();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_INFO__LOCK = eINSTANCE.getSpeedInfo_Lock();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl <em>Data File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getDataFile()
		 * @generated
		 */
		EClass DATA_FILE = eINSTANCE.getDataFile();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE__NAME = eINSTANCE.getDataFile_Name();

		/**
		 * The meta object literal for the '<em><b>Md5sum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE__MD5SUM = eINSTANCE.getDataFile_Md5sum();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE__PATH = eINSTANCE.getDataFile_Path();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE__LENGTH = eINSTANCE.getDataFile_Length();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl <em>Data File Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.DataFileInfoImpl
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getDataFileInfo()
		 * @generated
		 */
		EClass DATA_FILE_INFO = eINSTANCE.getDataFileInfo();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__INDEX = eINSTANCE.getDataFileInfo_Index();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__ENABLED = eINSTANCE.getDataFileInfo_Enabled();

		/**
		 * The meta object literal for the '<em><b>Pieces</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__PIECES = eINSTANCE.getDataFileInfo_Pieces();

		/**
		 * The meta object literal for the '<em><b>Begin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__BEGIN = eINSTANCE.getDataFileInfo_Begin();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__END = eINSTANCE.getDataFileInfo_End();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__LOCK = eINSTANCE.getDataFileInfo_Lock();

		/**
		 * The meta object literal for the '<em><b>Raf</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__RAF = eINSTANCE.getDataFileInfo_Raf();

		/**
		 * The meta object literal for the '<em><b>Recorded</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_FILE_INFO__RECORDED = eINSTANCE.getDataFileInfo_Recorded();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns <em>ETorrent Table Columns</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getETorrentTableColumns()
		 * @generated
		 */
		EEnum ETORRENT_TABLE_COLUMNS = eINSTANCE.getETorrentTableColumns();

		/**
		 * The meta object literal for the '{@link eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns <em>EFinished Table Columns</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEFinishedTableColumns()
		 * @generated
		 */
		EEnum EFINISHED_TABLE_COLUMNS = eINSTANCE.getEFinishedTableColumns();

		/**
		 * The meta object literal for the '<em>EBoolean Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEBooleanArray()
		 * @generated
		 */
		EDataType EBOOLEAN_ARRAY = eINSTANCE.getEBooleanArray();

		/**
		 * The meta object literal for the '<em>EInteger Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getEIntegerArray()
		 * @generated
		 */
		EDataType EINTEGER_ARRAY = eINSTANCE.getEIntegerArray();

		/**
		 * The meta object literal for the '<em>Lock</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.concurrent.locks.ReentrantReadWriteLock
		 * @see eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsPackageImpl#getLock()
		 * @generated
		 */
		EDataType LOCK = eINSTANCE.getLock();

	}

} //TorrentsPackage
