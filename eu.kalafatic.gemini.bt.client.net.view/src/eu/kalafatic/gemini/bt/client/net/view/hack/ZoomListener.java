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
package eu.kalafatic.gemini.bt.client.net.view.hack;

/**
 * The listener interface for receiving zoom events. The class that is
 * interested in processing a zoom event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addZoomListener<code> method. When
 * the zoom event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ZoomEvent
 */

/**
 * Listens to zoom level changes.
 * @author Eric Bordeau
 */
public interface ZoomListener {

	/**
	 * Zoom changed.
	 * @param zoom the zoom
	 */
	void zoomChanged(double zoom);

}
