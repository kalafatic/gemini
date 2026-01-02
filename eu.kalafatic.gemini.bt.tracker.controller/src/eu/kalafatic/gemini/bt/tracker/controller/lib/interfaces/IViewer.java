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
package eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

/**
 * The Interface interface IViewer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface IViewer {

	/**
	 * Gets the viewer.
	 * @return the viewer
	 */
	public Viewer getViewer();

	/**
	 * Sets the title img.
	 * @param image the new title img
	 */
	public void setTitleImg(Image image);
}
