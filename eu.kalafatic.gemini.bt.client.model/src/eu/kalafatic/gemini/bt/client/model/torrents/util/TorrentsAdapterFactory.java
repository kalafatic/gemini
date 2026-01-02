/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.util;

import eu.kalafatic.gemini.bt.client.model.torrents.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage
 * @generated
 */
public class TorrentsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TorrentsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TorrentsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TorrentsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TorrentsSwitch<Adapter> modelSwitch =
		new TorrentsSwitch<Adapter>() {
			@Override
			public Adapter caseTorrents(Torrents object) {
				return createTorrentsAdapter();
			}
			@Override
			public Adapter caseStringToExtTorrentMapEntry(Map.Entry<String, ExtTorrent> object) {
				return createStringToExtTorrentMapEntryAdapter();
			}
			@Override
			public Adapter caseTorrent(Torrent object) {
				return createTorrentAdapter();
			}
			@Override
			public Adapter caseInfo(Info object) {
				return createInfoAdapter();
			}
			@Override
			public Adapter caseExtTorrent(ExtTorrent object) {
				return createExtTorrentAdapter();
			}
			@Override
			public Adapter caseAdditionalInfo(AdditionalInfo object) {
				return createAdditionalInfoAdapter();
			}
			@Override
			public Adapter caseSpeedInfo(SpeedInfo object) {
				return createSpeedInfoAdapter();
			}
			@Override
			public Adapter caseDataFile(DataFile object) {
				return createDataFileAdapter();
			}
			@Override
			public Adapter caseDataFileInfo(DataFileInfo object) {
				return createDataFileInfoAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrents <em>Torrents</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrents
	 * @generated
	 */
	public Adapter createTorrentsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Ext Torrent Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToExtTorrentMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Torrent <em>Torrent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Torrent
	 * @generated
	 */
	public Adapter createTorrentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.Info <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.Info
	 * @generated
	 */
	public Adapter createInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent <em>Ext Torrent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent
	 * @generated
	 */
	public Adapter createExtTorrentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo <em>Additional Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo
	 * @generated
	 */
	public Adapter createAdditionalInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo <em>Speed Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.SpeedInfo
	 * @generated
	 */
	public Adapter createSpeedInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFile <em>Data File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFile
	 * @generated
	 */
	public Adapter createDataFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo <em>Data File Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.kalafatic.gemini.bt.client.model.torrents.DataFileInfo
	 * @generated
	 */
	public Adapter createDataFileInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TorrentsAdapterFactory
