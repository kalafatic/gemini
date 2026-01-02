/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage
 * @generated
 */
public interface TorrentsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TorrentsFactory eINSTANCE = eu.kalafatic.gemini.bt.client.model.torrents.impl.TorrentsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Torrents</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Torrents</em>'.
	 * @generated
	 */
	Torrents createTorrents();

	/**
	 * Returns a new object of class '<em>Torrent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Torrent</em>'.
	 * @generated
	 */
	Torrent createTorrent();

	/**
	 * Returns a new object of class '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Info</em>'.
	 * @generated
	 */
	Info createInfo();

	/**
	 * Returns a new object of class '<em>Ext Torrent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ext Torrent</em>'.
	 * @generated
	 */
	ExtTorrent createExtTorrent();

	/**
	 * Returns a new object of class '<em>Additional Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Additional Info</em>'.
	 * @generated
	 */
	AdditionalInfo createAdditionalInfo();

	/**
	 * Returns a new object of class '<em>Speed Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Speed Info</em>'.
	 * @generated
	 */
	SpeedInfo createSpeedInfo();

	/**
	 * Returns a new object of class '<em>Data File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data File</em>'.
	 * @generated
	 */
	DataFile createDataFile();

	/**
	 * Returns a new object of class '<em>Data File Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data File Info</em>'.
	 * @generated
	 */
	DataFileInfo createDataFileInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TorrentsPackage getTorrentsPackage();

} //TorrentsFactory
