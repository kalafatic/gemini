/*******************************************************************************
 * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL Version 3 
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.txt  
 * 
 * Contributors:
 *     Petr Kalafatic - initial API and implementation
 ******************************************************************************/
package eu.kalafatic.gemini.bt.tracker.model.tracker;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * The Interface interface TrackerPackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface TrackerPackage extends EPackage {

	/** The e name. */
	String eNAME = "tracker";

	/** The e n s_ uri. */
	String eNS_URI = "http:///tracker.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "tracker";

	/** The e instance. */
	TrackerPackage eINSTANCE = eu.kalafatic.gemini.bt.tracker.model.tracker.impl.TrackerPackageImpl.init();

	/** The TRACKE r_ model. */
	int TRACKER_MODEL = 0;

	/** The TRACKE r_ mode l__ swar m_ map. */
	int TRACKER_MODEL__SWARM_MAP = 0;

	/** The TRACKE r_ mode l__ tracke r_ id. */
	int TRACKER_MODEL__TRACKER_ID = 1;

	/** The TRACKE r_ mode l__ defaul t_ numwant. */
	int TRACKER_MODEL__DEFAULT_NUMWANT = 2;

	/** The TRACKE r_ mode l__ defaul t_ interval. */
	int TRACKER_MODEL__DEFAULT_INTERVAL = 3;

	/** The TRACKE r_ mode l_ featur e_ count. */
	int TRACKER_MODEL_FEATURE_COUNT = 4;

	/** The SESSION. */
	int SESSION = 3;

	/** The SESSIO n__ address. */
	int SESSION__ADDRESS = 0;

	/** The SESSIO n__ channel. */
	int SESSION__CHANNEL = 1;

	/** The SESSIO n__ reques t_ map. */
	int SESSION__REQUEST_MAP = 2;

	/** The SESSIO n__ communication. */
	int SESSION__COMMUNICATION = 3;

	/** The SESSIO n__ sessio n_ state. */
	int SESSION__SESSION_STATE = 4;

	/** The SESSIO n__ pee r_ id. */
	int SESSION__PEER_ID = 5;

	/** The SESSIO n__ note. */
	int SESSION__NOTE = 6;

	/** The SESSIO n__ inf o_ hash. */
	int SESSION__INFO_HASH = 7;

	/** The SESSIO n__ liste n_ port. */
	int SESSION__LISTEN_PORT = 8;

	/** The SESSIO n__ torrent. */
	int SESSION__TORRENT = 9;

	/** The SESSIO n_ featur e_ count. */
	int SESSION_FEATURE_COUNT = 10;

	/** The TORREN t_ session. */
	int TORRENT_SESSION = 1;

	/** The TORREN t_ sessio n__ address. */
	int TORRENT_SESSION__ADDRESS = SESSION__ADDRESS;

	/** The TORREN t_ sessio n__ channel. */
	int TORRENT_SESSION__CHANNEL = SESSION__CHANNEL;

	/** The TORREN t_ sessio n__ reques t_ map. */
	int TORRENT_SESSION__REQUEST_MAP = SESSION__REQUEST_MAP;

	/** The TORREN t_ sessio n__ communication. */
	int TORRENT_SESSION__COMMUNICATION = SESSION__COMMUNICATION;

	/** The TORREN t_ sessio n__ sessio n_ state. */
	int TORRENT_SESSION__SESSION_STATE = SESSION__SESSION_STATE;

	/** The TORREN t_ sessio n__ pee r_ id. */
	int TORRENT_SESSION__PEER_ID = SESSION__PEER_ID;

	/** The TORREN t_ sessio n__ note. */
	int TORRENT_SESSION__NOTE = SESSION__NOTE;

	/** The TORREN t_ sessio n__ inf o_ hash. */
	int TORRENT_SESSION__INFO_HASH = SESSION__INFO_HASH;

	/** The TORREN t_ sessio n__ liste n_ port. */
	int TORRENT_SESSION__LISTEN_PORT = SESSION__LISTEN_PORT;

	/** The TORREN t_ sessio n__ torrent. */
	int TORRENT_SESSION__TORRENT = SESSION__TORRENT;

	/** The TORREN t_ sessio n__ torren t_ name. */
	int TORRENT_SESSION__TORRENT_NAME = SESSION_FEATURE_COUNT + 0;

	/** The TORREN t_ sessio n__ complete. */
	int TORRENT_SESSION__COMPLETE = SESSION_FEATURE_COUNT + 1;

	/** The TORREN t_ sessio n__ incomplete. */
	int TORRENT_SESSION__INCOMPLETE = SESSION_FEATURE_COUNT + 2;

	/** The TORREN t_ sessio n__ torren t_ len. */
	int TORRENT_SESSION__TORRENT_LEN = SESSION_FEATURE_COUNT + 3;

	/** The TORREN t_ sessio n__ downloaded. */
	int TORRENT_SESSION__DOWNLOADED = SESSION_FEATURE_COUNT + 4;

	/** The TORREN t_ sessio n__ clien t_ map. */
	int TORRENT_SESSION__CLIENT_MAP = SESSION_FEATURE_COUNT + 5;

	/** The TORREN t_ sessio n__ torren t_ path. */
	int TORRENT_SESSION__TORRENT_PATH = SESSION_FEATURE_COUNT + 6;

	/** The TORREN t_ sessio n_ featur e_ count. */
	int TORRENT_SESSION_FEATURE_COUNT = SESSION_FEATURE_COUNT + 7;

	/** The CLIEN t_ session. */
	int CLIENT_SESSION = 2;

	/** The CLIEN t_ sessio n__ address. */
	int CLIENT_SESSION__ADDRESS = SESSION__ADDRESS;

	/** The CLIEN t_ sessio n__ channel. */
	int CLIENT_SESSION__CHANNEL = SESSION__CHANNEL;

	/** The CLIEN t_ sessio n__ reques t_ map. */
	int CLIENT_SESSION__REQUEST_MAP = SESSION__REQUEST_MAP;

	/** The CLIEN t_ sessio n__ communication. */
	int CLIENT_SESSION__COMMUNICATION = SESSION__COMMUNICATION;

	/** The CLIEN t_ sessio n__ sessio n_ state. */
	int CLIENT_SESSION__SESSION_STATE = SESSION__SESSION_STATE;

	/** The CLIEN t_ sessio n__ pee r_ id. */
	int CLIENT_SESSION__PEER_ID = SESSION__PEER_ID;

	/** The CLIEN t_ sessio n__ note. */
	int CLIENT_SESSION__NOTE = SESSION__NOTE;

	/** The CLIEN t_ sessio n__ inf o_ hash. */
	int CLIENT_SESSION__INFO_HASH = SESSION__INFO_HASH;

	/** The CLIEN t_ sessio n__ liste n_ port. */
	int CLIENT_SESSION__LISTEN_PORT = SESSION__LISTEN_PORT;

	/** The CLIEN t_ sessio n__ torrent. */
	int CLIENT_SESSION__TORRENT = SESSION__TORRENT;

	/** The CLIEN t_ sessio n__ seed. */
	int CLIENT_SESSION__SEED = SESSION_FEATURE_COUNT + 0;

	/** The CLIEN t_ sessio n__ las t_ connection. */
	int CLIENT_SESSION__LAST_CONNECTION = SESSION_FEATURE_COUNT + 1;

	/** The CLIEN t_ sessio n_ featur e_ count. */
	int CLIENT_SESSION_FEATURE_COUNT = SESSION_FEATURE_COUNT + 2;

	/** The STRIN g_ t o_ sessio n_ ma p_ entry. */
	int STRING_TO_SESSION_MAP_ENTRY = 4;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ key. */
	int STRING_TO_SESSION_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ value. */
	int STRING_TO_SESSION_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ sessio n_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_SESSION_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The STRIN g_ t o_ strin g_ ma p_ entry. */
	int STRING_TO_STRING_MAP_ENTRY = 5;

	/** The STRIN g_ t o_ strin g_ ma p_ entr y__ key. */
	int STRING_TO_STRING_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ strin g_ ma p_ entr y__ value. */
	int STRING_TO_STRING_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ strin g_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_STRING_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The COMMUNICATION. */
	int COMMUNICATION = 6;

	/** The COMMUNICATIO n__ date. */
	int COMMUNICATION__DATE = 0;

	/** The COMMUNICATIO n__ request. */
	int COMMUNICATION__REQUEST = 1;

	/** The COMMUNICATIO n__ response. */
	int COMMUNICATION__RESPONSE = 2;

	/** The COMMUNICATIO n_ featur e_ count. */
	int COMMUNICATION_FEATURE_COUNT = 3;

	/** The CHANNEL. */
	int CHANNEL = 7;

	/**
	 * Gets the tracker model.
	 * @return the tracker model
	 */
	EClass getTrackerModel();

	/**
	 * Gets the tracker model_ swarm map.
	 * @return the tracker model_ swarm map
	 */
	EReference getTrackerModel_SwarmMap();

	/**
	 * Gets the tracker model_ tracker id.
	 * @return the tracker model_ tracker id
	 */
	EAttribute getTrackerModel_TrackerID();

	/**
	 * Gets the tracker model_ default numwant.
	 * @return the tracker model_ default numwant
	 */
	EAttribute getTrackerModel_DefaultNumwant();

	/**
	 * Gets the tracker model_ default interval.
	 * @return the tracker model_ default interval
	 */
	EAttribute getTrackerModel_DefaultInterval();

	/**
	 * Gets the torrent session.
	 * @return the torrent session
	 */
	EClass getTorrentSession();

	/**
	 * Gets the torrent session_ torrent name.
	 * @return the torrent session_ torrent name
	 */
	EAttribute getTorrentSession_TorrentName();

	/**
	 * Gets the torrent session_ complete.
	 * @return the torrent session_ complete
	 */
	EAttribute getTorrentSession_Complete();

	/**
	 * Gets the torrent session_ incomplete.
	 * @return the torrent session_ incomplete
	 */
	EAttribute getTorrentSession_Incomplete();

	/**
	 * Gets the torrent session_ torrent len.
	 * @return the torrent session_ torrent len
	 */
	EAttribute getTorrentSession_TorrentLen();

	/**
	 * Gets the torrent session_ downloaded.
	 * @return the torrent session_ downloaded
	 */
	EAttribute getTorrentSession_Downloaded();

	/**
	 * Gets the torrent session_ client map.
	 * @return the torrent session_ client map
	 */
	EReference getTorrentSession_ClientMap();

	/**
	 * Gets the torrent session_ torrent path.
	 * @return the torrent session_ torrent path
	 */
	EAttribute getTorrentSession_TorrentPath();

	/**
	 * Gets the client session.
	 * @return the client session
	 */
	EClass getClientSession();

	/**
	 * Gets the client session_ seed.
	 * @return the client session_ seed
	 */
	EAttribute getClientSession_Seed();

	/**
	 * Gets the client session_ last connection.
	 * @return the client session_ last connection
	 */
	EAttribute getClientSession_LastConnection();

	/**
	 * Gets the session.
	 * @return the session
	 */
	EClass getSession();

	/**
	 * Gets the session_ address.
	 * @return the session_ address
	 */
	EAttribute getSession_Address();

	/**
	 * Gets the session_ channel.
	 * @return the session_ channel
	 */
	EAttribute getSession_Channel();

	/**
	 * Gets the session_ request map.
	 * @return the session_ request map
	 */
	EReference getSession_RequestMap();

	/**
	 * Gets the session_ communication.
	 * @return the session_ communication
	 */
	EReference getSession_Communication();

	/**
	 * Gets the session_ session state.
	 * @return the session_ session state
	 */
	EAttribute getSession_SessionState();

	/**
	 * Gets the session_ peer id.
	 * @return the session_ peer id
	 */
	EAttribute getSession_PeerId();

	/**
	 * Gets the session_ note.
	 * @return the session_ note
	 */
	EAttribute getSession_Note();

	/**
	 * Gets the session_ info hash.
	 * @return the session_ info hash
	 */
	EAttribute getSession_InfoHash();

	/**
	 * Gets the session_ listen port.
	 * @return the session_ listen port
	 */
	EAttribute getSession_ListenPort();

	/**
	 * Gets the session_ torrent.
	 * @return the session_ torrent
	 */
	EAttribute getSession_Torrent();

	/**
	 * Gets the string to session map entry.
	 * @return the string to session map entry
	 */
	EClass getStringToSessionMapEntry();

	/**
	 * Gets the string to session map entry_ key.
	 * @return the string to session map entry_ key
	 */
	EAttribute getStringToSessionMapEntry_Key();

	/**
	 * Gets the string to session map entry_ value.
	 * @return the string to session map entry_ value
	 */
	EReference getStringToSessionMapEntry_Value();

	/**
	 * Gets the string to string map entry.
	 * @return the string to string map entry
	 */
	EClass getStringToStringMapEntry();

	/**
	 * Gets the string to string map entry_ key.
	 * @return the string to string map entry_ key
	 */
	EAttribute getStringToStringMapEntry_Key();

	/**
	 * Gets the string to string map entry_ value.
	 * @return the string to string map entry_ value
	 */
	EAttribute getStringToStringMapEntry_Value();

	/**
	 * Gets the communication.
	 * @return the communication
	 */
	EClass getCommunication();

	/**
	 * Gets the communication_ date.
	 * @return the communication_ date
	 */
	EAttribute getCommunication_Date();

	/**
	 * Gets the communication_ request.
	 * @return the communication_ request
	 */
	EAttribute getCommunication_Request();

	/**
	 * Gets the communication_ response.
	 * @return the communication_ response
	 */
	EAttribute getCommunication_Response();

	/**
	 * Gets the channel.
	 * @return the channel
	 */
	EDataType getChannel();

	/**
	 * Gets the tracker factory.
	 * @return the tracker factory
	 */
	TrackerFactory getTrackerFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The TRACKE r_ model. */
		EClass TRACKER_MODEL = eINSTANCE.getTrackerModel();

		/** The TRACKE r_ mode l__ swar m_ map. */
		EReference TRACKER_MODEL__SWARM_MAP = eINSTANCE.getTrackerModel_SwarmMap();

		/** The TRACKE r_ mode l__ tracke r_ id. */
		EAttribute TRACKER_MODEL__TRACKER_ID = eINSTANCE.getTrackerModel_TrackerID();

		/** The TRACKE r_ mode l__ defaul t_ numwant. */
		EAttribute TRACKER_MODEL__DEFAULT_NUMWANT = eINSTANCE.getTrackerModel_DefaultNumwant();

		/** The TRACKE r_ mode l__ defaul t_ interval. */
		EAttribute TRACKER_MODEL__DEFAULT_INTERVAL = eINSTANCE.getTrackerModel_DefaultInterval();

		/** The TORREN t_ session. */
		EClass TORRENT_SESSION = eINSTANCE.getTorrentSession();

		/** The TORREN t_ sessio n__ torren t_ name. */
		EAttribute TORRENT_SESSION__TORRENT_NAME = eINSTANCE.getTorrentSession_TorrentName();

		/** The TORREN t_ sessio n__ complete. */
		EAttribute TORRENT_SESSION__COMPLETE = eINSTANCE.getTorrentSession_Complete();

		/** The TORREN t_ sessio n__ incomplete. */
		EAttribute TORRENT_SESSION__INCOMPLETE = eINSTANCE.getTorrentSession_Incomplete();

		/** The TORREN t_ sessio n__ torren t_ len. */
		EAttribute TORRENT_SESSION__TORRENT_LEN = eINSTANCE.getTorrentSession_TorrentLen();

		/** The TORREN t_ sessio n__ downloaded. */
		EAttribute TORRENT_SESSION__DOWNLOADED = eINSTANCE.getTorrentSession_Downloaded();

		/** The TORREN t_ sessio n__ clien t_ map. */
		EReference TORRENT_SESSION__CLIENT_MAP = eINSTANCE.getTorrentSession_ClientMap();

		/** The TORREN t_ sessio n__ torren t_ path. */
		EAttribute TORRENT_SESSION__TORRENT_PATH = eINSTANCE.getTorrentSession_TorrentPath();

		/** The CLIEN t_ session. */
		EClass CLIENT_SESSION = eINSTANCE.getClientSession();

		/** The CLIEN t_ sessio n__ seed. */
		EAttribute CLIENT_SESSION__SEED = eINSTANCE.getClientSession_Seed();

		/** The CLIEN t_ sessio n__ las t_ connection. */
		EAttribute CLIENT_SESSION__LAST_CONNECTION = eINSTANCE.getClientSession_LastConnection();

		/** The SESSION. */
		EClass SESSION = eINSTANCE.getSession();

		/** The SESSIO n__ address. */
		EAttribute SESSION__ADDRESS = eINSTANCE.getSession_Address();

		/** The SESSIO n__ channel. */
		EAttribute SESSION__CHANNEL = eINSTANCE.getSession_Channel();

		/** The SESSIO n__ reques t_ map. */
		EReference SESSION__REQUEST_MAP = eINSTANCE.getSession_RequestMap();

		/** The SESSIO n__ communication. */
		EReference SESSION__COMMUNICATION = eINSTANCE.getSession_Communication();

		/** The SESSIO n__ sessio n_ state. */
		EAttribute SESSION__SESSION_STATE = eINSTANCE.getSession_SessionState();

		/** The SESSIO n__ pee r_ id. */
		EAttribute SESSION__PEER_ID = eINSTANCE.getSession_PeerId();

		/** The SESSIO n__ note. */
		EAttribute SESSION__NOTE = eINSTANCE.getSession_Note();

		/** The SESSIO n__ inf o_ hash. */
		EAttribute SESSION__INFO_HASH = eINSTANCE.getSession_InfoHash();

		/** The SESSIO n__ liste n_ port. */
		EAttribute SESSION__LISTEN_PORT = eINSTANCE.getSession_ListenPort();

		/** The SESSIO n__ torrent. */
		EAttribute SESSION__TORRENT = eINSTANCE.getSession_Torrent();

		/** The STRIN g_ t o_ sessio n_ ma p_ entry. */
		EClass STRING_TO_SESSION_MAP_ENTRY = eINSTANCE.getStringToSessionMapEntry();

		/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ key. */
		EAttribute STRING_TO_SESSION_MAP_ENTRY__KEY = eINSTANCE.getStringToSessionMapEntry_Key();

		/** The STRIN g_ t o_ sessio n_ ma p_ entr y__ value. */
		EReference STRING_TO_SESSION_MAP_ENTRY__VALUE = eINSTANCE.getStringToSessionMapEntry_Value();

		/** The STRIN g_ t o_ strin g_ ma p_ entry. */
		EClass STRING_TO_STRING_MAP_ENTRY = eINSTANCE.getStringToStringMapEntry();

		/** The STRIN g_ t o_ strin g_ ma p_ entr y__ key. */
		EAttribute STRING_TO_STRING_MAP_ENTRY__KEY = eINSTANCE.getStringToStringMapEntry_Key();

		/** The STRIN g_ t o_ strin g_ ma p_ entr y__ value. */
		EAttribute STRING_TO_STRING_MAP_ENTRY__VALUE = eINSTANCE.getStringToStringMapEntry_Value();

		/** The COMMUNICATION. */
		EClass COMMUNICATION = eINSTANCE.getCommunication();

		/** The COMMUNICATIO n__ date. */
		EAttribute COMMUNICATION__DATE = eINSTANCE.getCommunication_Date();

		/** The COMMUNICATIO n__ request. */
		EAttribute COMMUNICATION__REQUEST = eINSTANCE.getCommunication_Request();

		/** The COMMUNICATIO n__ response. */
		EAttribute COMMUNICATION__RESPONSE = eINSTANCE.getCommunication_Response();

		/** The CHANNEL. */
		EDataType CHANNEL = eINSTANCE.getChannel();

	}

} // TrackerPackage
