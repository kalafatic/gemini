/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Swarm Info</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getDwnldStrategy <em>Dwnld Strategy</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isCreatedOnDisc <em>Created On Disc</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isUploadOnly <em>Upload Only</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmInfo()
 * @model
 * @generated
 */
public interface SwarmInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Dwnld Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dwnld Strategy</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dwnld Strategy</em>' attribute.
	 * @see #setDwnldStrategy(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmInfo_DwnldStrategy()
	 * @model
	 * @generated
	 */
	int getDwnldStrategy();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getDwnldStrategy <em>Dwnld Strategy</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dwnld Strategy</em>' attribute.
	 * @see #getDwnldStrategy()
	 * @generated
	 */
	void setDwnldStrategy(int value);

	/**
	 * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Algorithm</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Algorithm</em>' attribute.
	 * @see #setAlgorithm(int)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmInfo_Algorithm()
	 * @model default="0"
	 * @generated
	 */
	int getAlgorithm();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#getAlgorithm <em>Algorithm</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Algorithm</em>' attribute.
	 * @see #getAlgorithm()
	 * @generated
	 */
	void setAlgorithm(int value);

	/**
	 * Returns the value of the '<em><b>Created On Disc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created On Disc</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created On Disc</em>' attribute.
	 * @see #setCreatedOnDisc(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmInfo_CreatedOnDisc()
	 * @model
	 * @generated
	 */
	boolean isCreatedOnDisc();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isCreatedOnDisc <em>Created On Disc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created On Disc</em>' attribute.
	 * @see #isCreatedOnDisc()
	 * @generated
	 */
	void setCreatedOnDisc(boolean value);

	/**
	 * Returns the value of the '<em><b>Upload Only</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upload Only</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upload Only</em>' attribute.
	 * @see #setUploadOnly(boolean)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getSwarmInfo_UploadOnly()
	 * @model default="false"
	 * @generated
	 */
	boolean isUploadOnly();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmInfo#isUploadOnly <em>Upload Only</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upload Only</em>' attribute.
	 * @see #isUploadOnly()
	 * @generated
	 */
	void setUploadOnly(boolean value);

} // SwarmInfo
