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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * The Interface interface FileTreeObject.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface FileTreeObject extends EObject {

	/**
	 * Gets the name.
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name.
	 * @param value the new name
	 */
	void setName(String value);

	/**
	 * Gets the parent.
	 * @return the parent
	 */
	FileTreeObject getParent();

	/**
	 * Sets the parent.
	 * @param value the new parent
	 */
	void setParent(FileTreeObject value);

	/**
	 * Checks if is file.
	 * @return true, if is file
	 */
	boolean isFile();

	/**
	 * Sets the file.
	 * @param value the new file
	 */
	void setFile(boolean value);

	/**
	 * Gets the path.
	 * @return the path
	 */
	String getPath();

	/**
	 * Sets the path.
	 * @param value the new path
	 */
	void setPath(String value);

	/**
	 * Gets the md5 sum.
	 * @return the md5 sum
	 */
	String getMd5Sum();

	/**
	 * Sets the md5 sum.
	 * @param value the new md5 sum
	 */
	void setMd5Sum(String value);

	/**
	 * Gets the length.
	 * @return the length
	 */
	long getLength();

	/**
	 * Sets the length.
	 * @param value the new length
	 */
	void setLength(long value);

	/**
	 * Gets the child map.
	 * @return the child map
	 */
	EMap<String, FileTreeObject> getChildMap();

} // FileTreeObject
