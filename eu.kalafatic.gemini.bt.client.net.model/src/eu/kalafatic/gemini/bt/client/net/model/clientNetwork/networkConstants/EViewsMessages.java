/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>EViews Messages</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.NetworkConstantsPackage#getEViewsMessages()
 * @model
 * @generated
 */
public enum EViewsMessages implements Enumerator {
	/**
	 * The '<em><b>READY</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_VALUE
	 * @generated
	 * @ordered
	 */
	READY(10, "READY", "Ready"),

	/**
	 * The '<em><b>ACTIVATED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #ACTIVATED_VALUE
	 * @generated
	 * @ordered
	 */
	ACTIVATED(11, "ACTIVATED", "Activated"),

	/**
	 * The '<em><b>RUNNING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #RUNNING_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNING(12, "RUNNING", "Running ... "),

	/**
	 * The '<em><b>PAUSED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PAUSED_VALUE
	 * @generated
	 * @ordered
	 */
	PAUSED(13, "PAUSED", "Paused"),

	/**
	 * The '<em><b>STOPPED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #STOPPED_VALUE
	 * @generated
	 * @ordered
	 */
	STOPPED(14, "STOPPED", "Stopped"),

	/**
	 * The '<em><b>WAITING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #WAITING_VALUE
	 * @generated
	 * @ordered
	 */
	WAITING(15, "WAITING", "Waiting"),

	/**
	 * The '<em><b>SUSPENDED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #SUSPENDED_VALUE
	 * @generated
	 * @ordered
	 */
	SUSPENDED(16, "SUSPENDED", "Suspended"),

	/**
	 * The '<em><b>DISABLED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DISABLED_VALUE
	 * @generated
	 * @ordered
	 */
	DISABLED(17, "DISABLED", "Disabled"),

	/**
	 * The '<em><b>REJECTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #REJECTED_VALUE
	 * @generated
	 * @ordered
	 */
	REJECTED(18, "REJECTED", "Rejected"),

	/**
	 * The '<em><b>FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	FINISHED(19, "FINISHED", "Finished"),

	/**
	 * The '<em><b>READY TO TRACKER HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_TRACKER_HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_TRACKER_HANDSHAKE(20, "READY_TO_TRACKER_HANDSHAKE", "Ready to tracker handshake"),

	/**
	 * The '<em><b>CAN NOT CONNECT TRACKER</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CAN_NOT_CONNECT_TRACKER_VALUE
	 * @generated
	 * @ordered
	 */
	CAN_NOT_CONNECT_TRACKER(21, "CAN_NOT_CONNECT_TRACKER", "Can not connect tracker"),

	/**
	 * The '<em><b>CONNECTING TRACKERS</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CONNECTING_TRACKERS_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTING_TRACKERS(22, "CONNECTING_TRACKERS", "Connecting trackers ..."),

	/**
	 * The '<em><b>TRACKERS CONNECTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKERS_CONNECTED_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKERS_CONNECTED(23, "TRACKERS_CONNECTED", "Trackers connected"),

	/**
	 * The '<em><b>TRACKERS FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKERS_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKERS_FINISHED(24, "TRACKERS_FINISHED", "Trackers finished"),

	/**
	 * The '<em><b>TRACKER SENT WARNING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKER_SENT_WARNING_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKER_SENT_WARNING(25, "TRACKER_SENT_WARNING", "Tracker sent warning"),

	/**
	 * The '<em><b>TRACKER SENT FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRACKER_SENT_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	TRACKER_SENT_FAILURE(26, "TRACKER_SENT_FAILURE", "Tracker sent failure"),

	/**
	 * The '<em><b>SEARCH FOR CLIENTS</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #SEARCH_FOR_CLIENTS_VALUE
	 * @generated
	 * @ordered
	 */
	SEARCH_FOR_CLIENTS(30, "SEARCH_FOR_CLIENTS", "Search for clients ..."),

	/**
	 * The '<em><b>SEARCH FOR CLIENTS FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #SEARCH_FOR_CLIENTS_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	SEARCH_FOR_CLIENTS_FINISHED(31, "SEARCH_FOR_CLIENTS_FINISHED", "Search for clients finished"),

	/**
	 * The '<em><b>CONNECTING CLIENTS</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CONNECTING_CLIENTS_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTING_CLIENTS(32, "CONNECTING_CLIENTS", "Connecting clients ..."),

	/**
	 * The '<em><b>CAN NOT CONNECT CLIENT</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CAN_NOT_CONNECT_CLIENT_VALUE
	 * @generated
	 * @ordered
	 */
	CAN_NOT_CONNECT_CLIENT(33, "CAN_NOT_CONNECT_CLIENT", "Can not connect client"),

	/**
	 * The '<em><b>CONNECTED TO CLIENTS</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CONNECTED_TO_CLIENTS_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTED_TO_CLIENTS(34, "CONNECTED_TO_CLIENTS", "Connected to clients"),

	/**
	 * The '<em><b>CLIENT CONNECTION ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CLIENT_CONNECTION_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	CLIENT_CONNECTION_ERROR(35, "CLIENT_CONNECTION_ERROR", "Client connection error"),

	/**
	 * The '<em><b>READY TO HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_HANDSHAKE(40, "READY_TO_HANDSHAKE", "Ready to  handshake"),

	/**
	 * The '<em><b>READY TO RECEIVE HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_RECEIVE_HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_RECEIVE_HANDSHAKE(41, "READY_TO_RECEIVE_HANDSHAKE", "Ready to receive handshake"),

	/**
	 * The '<em><b>READY TO SEND HANDSHAKE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_SEND_HANDSHAKE_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_SEND_HANDSHAKE(42, "READY_TO_SEND_HANDSHAKE", "Ready to send handshake"),

	/**
	 * The '<em><b>CLIENT HANDSHAKE ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CLIENT_HANDSHAKE_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	CLIENT_HANDSHAKE_ERROR(43, "CLIENT_HANDSHAKE_ERROR", "Client handshake error"),

	/**
	 * The '<em><b>CLIENT HANDSHAKE OK</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CLIENT_HANDSHAKE_OK_VALUE
	 * @generated
	 * @ordered
	 */
	CLIENT_HANDSHAKE_OK(44, "CLIENT_HANDSHAKE_OK", "Client handshake OK"),

	/**
	 * The '<em><b>CLIENT IS CHOKING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CLIENT_IS_CHOKING_VALUE
	 * @generated
	 * @ordered
	 */
	CLIENT_IS_CHOKING(45, "CLIENT_IS_CHOKING", "Client is choking ..."),

	/**
	 * The '<em><b>CLIENT NOT INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CLIENT_NOT_INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	CLIENT_NOT_INTERESTED(46, "CLIENT_NOT_INTERESTED", "Not interested ..."),

	/**
	 * The '<em><b>RATIO EXCEEDED CHOKING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #RATIO_EXCEEDED_CHOKING_VALUE
	 * @generated
	 * @ordered
	 */
	RATIO_EXCEEDED_CHOKING(51, "RATIO_EXCEEDED_CHOKING", "D/U ratio exceeded-sending choke"),

	/**
	 * The '<em><b>READY TO INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_INTERESTED(60, "READY_TO_INTERESTED", "Interested"),

	/**
	 * The '<em><b>PEER IS INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEER_IS_INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	PEER_IS_INTERESTED(61, "PEER_IS_INTERESTED", "Peer is interested"),

	/**
	 * The '<em><b>PEER NOT INTERESTED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PEER_NOT_INTERESTED_VALUE
	 * @generated
	 * @ordered
	 */
	PEER_NOT_INTERESTED(62, "PEER_NOT_INTERESTED", "Peer is not interested"),

	/**
	 * The '<em><b>RATING REJECTION</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #RATING_REJECTION_VALUE
	 * @generated
	 * @ordered
	 */
	RATING_REJECTION(63, "RATING_REJECTION", "Rejected due to poor rating.."),

	/**
	 * The '<em><b>READY TO DOWNLOAD</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_DOWNLOAD_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_DOWNLOAD(70, "READY_TO_DOWNLOAD", "Ready to download ..."),

	/**
	 * The '<em><b>DOWNLOADING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DOWNLOADING_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOADING(71, "DOWNLOADING", "Downloading ..."),

	/**
	 * The '<em><b>DOWNLOAD PIECE FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DOWNLOAD_PIECE_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOAD_PIECE_FINISHED(72, "DOWNLOAD_PIECE_FINISHED", "Piece downloaded"),

	/**
	 * The '<em><b>DOWNLOAD FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DOWNLOAD_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOAD_FINISHED(73, "DOWNLOAD_FINISHED", "Download finished"),

	/**
	 * The '<em><b>DOWNLOAD ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DOWNLOAD_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	DOWNLOAD_ERROR(74, "DOWNLOAD_ERROR", "File transfer error"),

	/**
	 * The '<em><b>READY TO UPLOAD</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READY_TO_UPLOAD_VALUE
	 * @generated
	 * @ordered
	 */
	READY_TO_UPLOAD(80, "READY_TO_UPLOAD", "Ready to upload"),

	/**
	 * The '<em><b>UPLOADING</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UPLOADING_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOADING(81, "UPLOADING", "Uploading ..."),

	/**
	 * The '<em><b>UPLOAD PICE FINISHED</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UPLOAD_PICE_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOAD_PICE_FINISHED(82, "UPLOAD_PICE_FINISHED", "Piece uploaded"),

	/**
	 * The '<em><b>UPLOAD ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UPLOAD_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	UPLOAD_ERROR(83, "UPLOAD_ERROR", "Upload error"),

	/**
	 * The '<em><b>READ ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #READ_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	READ_ERROR(90, "READ_ERROR", "READ_ERROR"),

	/**
	 * The '<em><b>WRITE ERROR</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #WRITE_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	WRITE_ERROR(95, "WRITE_ERROR", "WRITE_ERROR"),

	/**
	 * The '<em><b>STARTING SWARM</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #STARTING_SWARM_VALUE
	 * @generated
	 * @ordered
	 */
	STARTING_SWARM(100, "STARTING_SWARM", "Starting swarm ..."),

	/**
	 * The '<em><b>PAUSING SWARM</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #PAUSING_SWARM_VALUE
	 * @generated
	 * @ordered
	 */
	PAUSING_SWARM(101, "PAUSING_SWARM", "Pausing swarm ..."),

	/**
	 * The '<em><b>STOPPING SWARM</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #STOPPING_SWARM_VALUE
	 * @generated
	 * @ordered
	 */
	STOPPING_SWARM(102, "STOPPING_SWARM", "Stopping swarm ...");

	/**
	 * The '<em><b>READY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY
	 * @model literal="Ready"
	 * @generated
	 * @ordered
	 */
	public static final int READY_VALUE = 10;

	/**
	 * The '<em><b>ACTIVATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ACTIVATED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ACTIVATED
	 * @model literal="Activated"
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVATED_VALUE = 11;

	/**
	 * The '<em><b>RUNNING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RUNNING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RUNNING
	 * @model literal="Running ... "
	 * @generated
	 * @ordered
	 */
	public static final int RUNNING_VALUE = 12;

	/**
	 * The '<em><b>PAUSED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PAUSED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PAUSED
	 * @model literal="Paused"
	 * @generated
	 * @ordered
	 */
	public static final int PAUSED_VALUE = 13;

	/**
	 * The '<em><b>STOPPED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STOPPED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOPPED
	 * @model literal="Stopped"
	 * @generated
	 * @ordered
	 */
	public static final int STOPPED_VALUE = 14;

	/**
	 * The '<em><b>WAITING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WAITING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WAITING
	 * @model literal="Waiting"
	 * @generated
	 * @ordered
	 */
	public static final int WAITING_VALUE = 15;

	/**
	 * The '<em><b>SUSPENDED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUSPENDED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUSPENDED
	 * @model literal="Suspended"
	 * @generated
	 * @ordered
	 */
	public static final int SUSPENDED_VALUE = 16;

	/**
	 * The '<em><b>DISABLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DISABLED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISABLED
	 * @model literal="Disabled"
	 * @generated
	 * @ordered
	 */
	public static final int DISABLED_VALUE = 17;

	/**
	 * The '<em><b>REJECTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REJECTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REJECTED
	 * @model literal="Rejected"
	 * @generated
	 * @ordered
	 */
	public static final int REJECTED_VALUE = 18;

	/**
	 * The '<em><b>FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FINISHED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FINISHED
	 * @model literal="Finished"
	 * @generated
	 * @ordered
	 */
	public static final int FINISHED_VALUE = 19;

	/**
	 * The '<em><b>READY TO TRACKER HANDSHAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO TRACKER HANDSHAKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_TRACKER_HANDSHAKE
	 * @model literal="Ready to tracker handshake"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_TRACKER_HANDSHAKE_VALUE = 20;

	/**
	 * The '<em><b>CAN NOT CONNECT TRACKER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CAN NOT CONNECT TRACKER</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CAN_NOT_CONNECT_TRACKER
	 * @model literal="Can not connect tracker"
	 * @generated
	 * @ordered
	 */
	public static final int CAN_NOT_CONNECT_TRACKER_VALUE = 21;

	/**
	 * The '<em><b>CONNECTING TRACKERS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONNECTING TRACKERS</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTING_TRACKERS
	 * @model literal="Connecting trackers ..."
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTING_TRACKERS_VALUE = 22;

	/**
	 * The '<em><b>TRACKERS CONNECTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKERS CONNECTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKERS_CONNECTED
	 * @model literal="Trackers connected"
	 * @generated
	 * @ordered
	 */
	public static final int TRACKERS_CONNECTED_VALUE = 23;

	/**
	 * The '<em><b>TRACKERS FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKERS FINISHED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKERS_FINISHED
	 * @model literal="Trackers finished"
	 * @generated
	 * @ordered
	 */
	public static final int TRACKERS_FINISHED_VALUE = 24;

	/**
	 * The '<em><b>TRACKER SENT WARNING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKER SENT WARNING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKER_SENT_WARNING
	 * @model literal="Tracker sent warning"
	 * @generated
	 * @ordered
	 */
	public static final int TRACKER_SENT_WARNING_VALUE = 25;

	/**
	 * The '<em><b>TRACKER SENT FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRACKER SENT FAILURE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRACKER_SENT_FAILURE
	 * @model literal="Tracker sent failure"
	 * @generated
	 * @ordered
	 */
	public static final int TRACKER_SENT_FAILURE_VALUE = 26;

	/**
	 * The '<em><b>SEARCH FOR CLIENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEARCH FOR CLIENTS</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEARCH_FOR_CLIENTS
	 * @model literal="Search for clients ..."
	 * @generated
	 * @ordered
	 */
	public static final int SEARCH_FOR_CLIENTS_VALUE = 30;

	/**
	 * The '<em><b>SEARCH FOR CLIENTS FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEARCH FOR CLIENTS FINISHED</b></em>' literal object isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEARCH_FOR_CLIENTS_FINISHED
	 * @model literal="Search for clients finished"
	 * @generated
	 * @ordered
	 */
	public static final int SEARCH_FOR_CLIENTS_FINISHED_VALUE = 31;

	/**
	 * The '<em><b>CONNECTING CLIENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONNECTING CLIENTS</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTING_CLIENTS
	 * @model literal="Connecting clients ..."
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTING_CLIENTS_VALUE = 32;

	/**
	 * The '<em><b>CAN NOT CONNECT CLIENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CAN NOT CONNECT CLIENT</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CAN_NOT_CONNECT_CLIENT
	 * @model literal="Can not connect client"
	 * @generated
	 * @ordered
	 */
	public static final int CAN_NOT_CONNECT_CLIENT_VALUE = 33;

	/**
	 * The '<em><b>CONNECTED TO CLIENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONNECTED TO CLIENTS</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTED_TO_CLIENTS
	 * @model literal="Connected to clients"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTED_TO_CLIENTS_VALUE = 34;

	/**
	 * The '<em><b>CLIENT CONNECTION ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT CONNECTION ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_CONNECTION_ERROR
	 * @model literal="Client connection error"
	 * @generated
	 * @ordered
	 */
	public static final int CLIENT_CONNECTION_ERROR_VALUE = 35;

	/**
	 * The '<em><b>READY TO HANDSHAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO HANDSHAKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_HANDSHAKE
	 * @model literal="Ready to  handshake"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_HANDSHAKE_VALUE = 40;

	/**
	 * The '<em><b>READY TO RECEIVE HANDSHAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO RECEIVE HANDSHAKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_RECEIVE_HANDSHAKE
	 * @model literal="Ready to receive handshake"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_RECEIVE_HANDSHAKE_VALUE = 41;

	/**
	 * The '<em><b>READY TO SEND HANDSHAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO SEND HANDSHAKE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_SEND_HANDSHAKE
	 * @model literal="Ready to send handshake"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_SEND_HANDSHAKE_VALUE = 42;

	/**
	 * The '<em><b>CLIENT HANDSHAKE ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT HANDSHAKE ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_HANDSHAKE_ERROR
	 * @model literal="Client handshake error"
	 * @generated
	 * @ordered
	 */
	public static final int CLIENT_HANDSHAKE_ERROR_VALUE = 43;

	/**
	 * The '<em><b>CLIENT HANDSHAKE OK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT HANDSHAKE OK</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_HANDSHAKE_OK
	 * @model literal="Client handshake OK"
	 * @generated
	 * @ordered
	 */
	public static final int CLIENT_HANDSHAKE_OK_VALUE = 44;

	/**
	 * The '<em><b>CLIENT IS CHOKING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT IS CHOKING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_IS_CHOKING
	 * @model literal="Client is choking ..."
	 * @generated
	 * @ordered
	 */
	public static final int CLIENT_IS_CHOKING_VALUE = 45;

	/**
	 * The '<em><b>CLIENT NOT INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLIENT NOT INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLIENT_NOT_INTERESTED
	 * @model literal="Not interested ..."
	 * @generated
	 * @ordered
	 */
	public static final int CLIENT_NOT_INTERESTED_VALUE = 46;

	/**
	 * The '<em><b>RATIO EXCEEDED CHOKING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RATIO EXCEEDED CHOKING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RATIO_EXCEEDED_CHOKING
	 * @model literal="D/U ratio exceeded-sending choke"
	 * @generated
	 * @ordered
	 */
	public static final int RATIO_EXCEEDED_CHOKING_VALUE = 51;

	/**
	 * The '<em><b>READY TO INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_INTERESTED
	 * @model literal="Interested"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_INTERESTED_VALUE = 60;

	/**
	 * The '<em><b>PEER IS INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER IS INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_IS_INTERESTED
	 * @model literal="Peer is interested"
	 * @generated
	 * @ordered
	 */
	public static final int PEER_IS_INTERESTED_VALUE = 61;

	/**
	 * The '<em><b>PEER NOT INTERESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PEER NOT INTERESTED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PEER_NOT_INTERESTED
	 * @model literal="Peer is not interested"
	 * @generated
	 * @ordered
	 */
	public static final int PEER_NOT_INTERESTED_VALUE = 62;

	/**
	 * The '<em><b>RATING REJECTION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RATING REJECTION</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RATING_REJECTION
	 * @model literal="Rejected due to poor rating.."
	 * @generated
	 * @ordered
	 */
	public static final int RATING_REJECTION_VALUE = 63;

	/**
	 * The '<em><b>READY TO DOWNLOAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO DOWNLOAD</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_DOWNLOAD
	 * @model literal="Ready to download ..."
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_DOWNLOAD_VALUE = 70;

	/**
	 * The '<em><b>DOWNLOADING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOADING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOADING
	 * @model literal="Downloading ..."
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOADING_VALUE = 71;

	/**
	 * The '<em><b>DOWNLOAD PIECE FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOAD PIECE FINISHED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOAD_PIECE_FINISHED
	 * @model literal="Piece downloaded"
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOAD_PIECE_FINISHED_VALUE = 72;

	/**
	 * The '<em><b>DOWNLOAD FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOAD FINISHED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOAD_FINISHED
	 * @model literal="Download finished"
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOAD_FINISHED_VALUE = 73;

	/**
	 * The '<em><b>DOWNLOAD ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DOWNLOAD ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DOWNLOAD_ERROR
	 * @model literal="File transfer error"
	 * @generated
	 * @ordered
	 */
	public static final int DOWNLOAD_ERROR_VALUE = 74;

	/**
	 * The '<em><b>READY TO UPLOAD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READY TO UPLOAD</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READY_TO_UPLOAD
	 * @model literal="Ready to upload"
	 * @generated
	 * @ordered
	 */
	public static final int READY_TO_UPLOAD_VALUE = 80;

	/**
	 * The '<em><b>UPLOADING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLOADING</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLOADING
	 * @model literal="Uploading ..."
	 * @generated
	 * @ordered
	 */
	public static final int UPLOADING_VALUE = 81;

	/**
	 * The '<em><b>UPLOAD PICE FINISHED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLOAD PICE FINISHED</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLOAD_PICE_FINISHED
	 * @model literal="Piece uploaded"
	 * @generated
	 * @ordered
	 */
	public static final int UPLOAD_PICE_FINISHED_VALUE = 82;

	/**
	 * The '<em><b>UPLOAD ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPLOAD ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPLOAD_ERROR
	 * @model literal="Upload error"
	 * @generated
	 * @ordered
	 */
	public static final int UPLOAD_ERROR_VALUE = 83;

	/**
	 * The '<em><b>READ ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READ ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READ_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int READ_ERROR_VALUE = 90;

	/**
	 * The '<em><b>WRITE ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WRITE ERROR</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WRITE_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WRITE_ERROR_VALUE = 95;

	/**
	 * The '<em><b>STARTING SWARM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STARTING SWARM</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTING_SWARM
	 * @model literal="Starting swarm ..."
	 * @generated
	 * @ordered
	 */
	public static final int STARTING_SWARM_VALUE = 100;

	/**
	 * The '<em><b>PAUSING SWARM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PAUSING SWARM</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PAUSING_SWARM
	 * @model literal="Pausing swarm ..."
	 * @generated
	 * @ordered
	 */
	public static final int PAUSING_SWARM_VALUE = 101;

	/**
	 * The '<em><b>STOPPING SWARM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STOPPING SWARM</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOPPING_SWARM
	 * @model literal="Stopping swarm ..."
	 * @generated
	 * @ordered
	 */
	public static final int STOPPING_SWARM_VALUE = 102;

	/**
	 * An array of all the '<em><b>EViews Messages</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final EViewsMessages[] VALUES_ARRAY = new EViewsMessages[] {
			READY,
			ACTIVATED,
			RUNNING,
			PAUSED,
			STOPPED,
			WAITING,
			SUSPENDED,
			DISABLED,
			REJECTED,
			FINISHED,
			READY_TO_TRACKER_HANDSHAKE,
			CAN_NOT_CONNECT_TRACKER,
			CONNECTING_TRACKERS,
			TRACKERS_CONNECTED,
			TRACKERS_FINISHED,
			TRACKER_SENT_WARNING,
			TRACKER_SENT_FAILURE,
			SEARCH_FOR_CLIENTS,
			SEARCH_FOR_CLIENTS_FINISHED,
			CONNECTING_CLIENTS,
			CAN_NOT_CONNECT_CLIENT,
			CONNECTED_TO_CLIENTS,
			CLIENT_CONNECTION_ERROR,
			READY_TO_HANDSHAKE,
			READY_TO_RECEIVE_HANDSHAKE,
			READY_TO_SEND_HANDSHAKE,
			CLIENT_HANDSHAKE_ERROR,
			CLIENT_HANDSHAKE_OK,
			CLIENT_IS_CHOKING,
			CLIENT_NOT_INTERESTED,
			RATIO_EXCEEDED_CHOKING,
			READY_TO_INTERESTED,
			PEER_IS_INTERESTED,
			PEER_NOT_INTERESTED,
			RATING_REJECTION,
			READY_TO_DOWNLOAD,
			DOWNLOADING,
			DOWNLOAD_PIECE_FINISHED,
			DOWNLOAD_FINISHED,
			DOWNLOAD_ERROR,
			READY_TO_UPLOAD,
			UPLOADING,
			UPLOAD_PICE_FINISHED,
			UPLOAD_ERROR,
			READ_ERROR,
			WRITE_ERROR,
			STARTING_SWARM,
			PAUSING_SWARM,
			STOPPING_SWARM,
		};

	/**
	 * A public read-only list of all the '<em><b>EViews Messages</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EViewsMessages> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>EViews Messages</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static EViewsMessages get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EViewsMessages result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EViews Messages</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static EViewsMessages getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EViewsMessages result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EViews Messages</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static EViewsMessages get(int value) {
		switch (value) {
			case READY_VALUE: return READY;
			case ACTIVATED_VALUE: return ACTIVATED;
			case RUNNING_VALUE: return RUNNING;
			case PAUSED_VALUE: return PAUSED;
			case STOPPED_VALUE: return STOPPED;
			case WAITING_VALUE: return WAITING;
			case SUSPENDED_VALUE: return SUSPENDED;
			case DISABLED_VALUE: return DISABLED;
			case REJECTED_VALUE: return REJECTED;
			case FINISHED_VALUE: return FINISHED;
			case READY_TO_TRACKER_HANDSHAKE_VALUE: return READY_TO_TRACKER_HANDSHAKE;
			case CAN_NOT_CONNECT_TRACKER_VALUE: return CAN_NOT_CONNECT_TRACKER;
			case CONNECTING_TRACKERS_VALUE: return CONNECTING_TRACKERS;
			case TRACKERS_CONNECTED_VALUE: return TRACKERS_CONNECTED;
			case TRACKERS_FINISHED_VALUE: return TRACKERS_FINISHED;
			case TRACKER_SENT_WARNING_VALUE: return TRACKER_SENT_WARNING;
			case TRACKER_SENT_FAILURE_VALUE: return TRACKER_SENT_FAILURE;
			case SEARCH_FOR_CLIENTS_VALUE: return SEARCH_FOR_CLIENTS;
			case SEARCH_FOR_CLIENTS_FINISHED_VALUE: return SEARCH_FOR_CLIENTS_FINISHED;
			case CONNECTING_CLIENTS_VALUE: return CONNECTING_CLIENTS;
			case CAN_NOT_CONNECT_CLIENT_VALUE: return CAN_NOT_CONNECT_CLIENT;
			case CONNECTED_TO_CLIENTS_VALUE: return CONNECTED_TO_CLIENTS;
			case CLIENT_CONNECTION_ERROR_VALUE: return CLIENT_CONNECTION_ERROR;
			case READY_TO_HANDSHAKE_VALUE: return READY_TO_HANDSHAKE;
			case READY_TO_RECEIVE_HANDSHAKE_VALUE: return READY_TO_RECEIVE_HANDSHAKE;
			case READY_TO_SEND_HANDSHAKE_VALUE: return READY_TO_SEND_HANDSHAKE;
			case CLIENT_HANDSHAKE_ERROR_VALUE: return CLIENT_HANDSHAKE_ERROR;
			case CLIENT_HANDSHAKE_OK_VALUE: return CLIENT_HANDSHAKE_OK;
			case CLIENT_IS_CHOKING_VALUE: return CLIENT_IS_CHOKING;
			case CLIENT_NOT_INTERESTED_VALUE: return CLIENT_NOT_INTERESTED;
			case RATIO_EXCEEDED_CHOKING_VALUE: return RATIO_EXCEEDED_CHOKING;
			case READY_TO_INTERESTED_VALUE: return READY_TO_INTERESTED;
			case PEER_IS_INTERESTED_VALUE: return PEER_IS_INTERESTED;
			case PEER_NOT_INTERESTED_VALUE: return PEER_NOT_INTERESTED;
			case RATING_REJECTION_VALUE: return RATING_REJECTION;
			case READY_TO_DOWNLOAD_VALUE: return READY_TO_DOWNLOAD;
			case DOWNLOADING_VALUE: return DOWNLOADING;
			case DOWNLOAD_PIECE_FINISHED_VALUE: return DOWNLOAD_PIECE_FINISHED;
			case DOWNLOAD_FINISHED_VALUE: return DOWNLOAD_FINISHED;
			case DOWNLOAD_ERROR_VALUE: return DOWNLOAD_ERROR;
			case READY_TO_UPLOAD_VALUE: return READY_TO_UPLOAD;
			case UPLOADING_VALUE: return UPLOADING;
			case UPLOAD_PICE_FINISHED_VALUE: return UPLOAD_PICE_FINISHED;
			case UPLOAD_ERROR_VALUE: return UPLOAD_ERROR;
			case READ_ERROR_VALUE: return READ_ERROR;
			case WRITE_ERROR_VALUE: return WRITE_ERROR;
			case STARTING_SWARM_VALUE: return STARTING_SWARM;
			case PAUSING_SWARM_VALUE: return PAUSING_SWARM;
			case STOPPING_SWARM_VALUE: return STOPPING_SWARM;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EViewsMessages(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} // EViewsMessages
