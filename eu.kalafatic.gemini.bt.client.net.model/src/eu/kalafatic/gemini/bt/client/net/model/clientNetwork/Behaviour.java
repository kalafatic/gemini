/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.gemini.bt.client.net.model.clientNetwork;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Behaviour</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getStrategy <em>Strategy</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getBehaviour()
 * @model
 * @generated
 */
public interface Behaviour extends EObject {
	/**
	 * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
	 * The default value is <code>"Random"</code>.
	 * The literals are from the enumeration {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Algorithm</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Algorithm</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy
	 * @see #setAlgorithm(EStrategy)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getBehaviour_Algorithm()
	 * @model default="Random"
	 * @generated
	 */
	EStrategy getAlgorithm();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getAlgorithm <em>Algorithm</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Algorithm</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy
	 * @see #getAlgorithm()
	 * @generated
	 */
	void setAlgorithm(EStrategy value);

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' attribute.
	 * The default value is <code>"Queuenning"</code>.
	 * The literals are from the enumeration {@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm
	 * @see #setStrategy(EAlgorithm)
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetworkPackage#getBehaviour_Strategy()
	 * @model default="Queuenning"
	 * @generated
	 */
	EAlgorithm getStrategy();

	/**
	 * Sets the value of the '{@link eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Behaviour#getStrategy <em>Strategy</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' attribute.
	 * @see eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(EAlgorithm value);

} // Behaviour
