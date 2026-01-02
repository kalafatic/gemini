/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.model.torrents.util;

import eu.kalafatic.gemini.bt.client.model.torrents.*;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eu.kalafatic.gemini.bt.client.model.torrents.TorrentsPackage
 * @generated
 */
public class TorrentsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TorrentsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TorrentsSwitch() {
		if (modelPackage == null) {
			modelPackage = TorrentsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TorrentsPackage.TORRENTS: {
				Torrents torrents = (Torrents)theEObject;
				T result = caseTorrents(torrents);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.STRING_TO_EXT_TORRENT_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, ExtTorrent> stringToExtTorrentMapEntry = (Map.Entry<String, ExtTorrent>)theEObject;
				T result = caseStringToExtTorrentMapEntry(stringToExtTorrentMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.TORRENT: {
				Torrent torrent = (Torrent)theEObject;
				T result = caseTorrent(torrent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.INFO: {
				Info info = (Info)theEObject;
				T result = caseInfo(info);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.EXT_TORRENT: {
				ExtTorrent extTorrent = (ExtTorrent)theEObject;
				T result = caseExtTorrent(extTorrent);
				if (result == null) result = caseTorrent(extTorrent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.ADDITIONAL_INFO: {
				AdditionalInfo additionalInfo = (AdditionalInfo)theEObject;
				T result = caseAdditionalInfo(additionalInfo);
				if (result == null) result = caseSpeedInfo(additionalInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.SPEED_INFO: {
				SpeedInfo speedInfo = (SpeedInfo)theEObject;
				T result = caseSpeedInfo(speedInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.DATA_FILE: {
				DataFile dataFile = (DataFile)theEObject;
				T result = caseDataFile(dataFile);
				if (result == null) result = caseDataFileInfo(dataFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TorrentsPackage.DATA_FILE_INFO: {
				DataFileInfo dataFileInfo = (DataFileInfo)theEObject;
				T result = caseDataFileInfo(dataFileInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Torrents</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Torrents</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTorrents(Torrents object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Ext Torrent Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Ext Torrent Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToExtTorrentMapEntry(Map.Entry<String, ExtTorrent> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Torrent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Torrent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTorrent(Torrent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfo(Info object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ext Torrent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ext Torrent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtTorrent(ExtTorrent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Additional Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Additional Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdditionalInfo(AdditionalInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Speed Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Speed Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpeedInfo(SpeedInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFile(DataFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data File Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data File Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFileInfo(DataFileInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TorrentsSwitch
