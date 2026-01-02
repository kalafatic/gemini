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
package eu.kalafatic.gemini.bt.visual.interfaces;


/**
 * The Interface IModel.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public interface IModel {
	
	/** The PAT h_ no t_ exist. */
	public static int PATH_NOT_EXIST= 0;	
	
	/** The PAT h_ exist. */
	public static int PATH_EXIST=1;	
	
	/** The SEARCHING. */
	public static int SEARCHING=2;	
	
	/** The NOD e_ ha s_ n o_ children. */
	public static int NODE_HAS_NO_CHILDREN= -1;
	
}
