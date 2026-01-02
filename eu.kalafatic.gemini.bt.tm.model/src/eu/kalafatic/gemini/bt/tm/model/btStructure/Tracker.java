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
package eu.kalafatic.gemini.bt.tm.model.btStructure;

import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface Tracker.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface Tracker extends EObject {

	/**
	 * Checks if is checked.
	 * @return true, if is checked
	 */
	boolean isChecked();

	/**
	 * Sets the checked.
	 * @param value the new checked
	 */
	void setChecked(boolean value);

	/**
	 * Gets the announce.
	 * @return the announce
	 */
	String getAnnounce();

	/**
	 * Sets the announce.
	 * @param value the new announce
	 */
	void setAnnounce(String value);

	/**
	 * Gets the comment.
	 * @return the comment
	 */
	String getComment();

	/**
	 * Sets the comment.
	 * @param value the new comment
	 */
	void setComment(String value);

} // Tracker
