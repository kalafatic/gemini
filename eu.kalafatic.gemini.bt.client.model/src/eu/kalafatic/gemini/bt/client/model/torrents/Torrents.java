/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Torrents</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getTorrentMap <em>Torrent Map</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents#getFinishedTorrentsMap <em>Finished Torrents Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrents()
 * @model
 * @generated
 */
public interface Torrents extends EObject {
	/**
	 * Returns the value of the '<em><b>Torrent Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Torrent Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Torrent Map</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrents_TorrentMap()
	 * @model mapType="eu.kalafatic.gemini.bt.client.model.torrents.StringToExtTorrentMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent>"
	 * @generated
	 */
	EMap<String, ExtTorrent> getTorrentMap();

	/**
	 * Returns the value of the '<em><b>Finished Torrents Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finished Torrents Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finished Torrents Map</em>' map.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage#getTorrents_FinishedTorrentsMap()
	 * @model mapType="eu.kalafatic.gemini.bt.client.model.torrents.StringToExtTorrentMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent>"
	 * @generated
	 */
	EMap<String, ExtTorrent> getFinishedTorrentsMap();

} // Torrents
