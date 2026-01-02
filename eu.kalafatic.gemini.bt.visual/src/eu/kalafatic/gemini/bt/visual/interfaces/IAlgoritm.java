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

import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.bt.visual.lib.IAlgorithmsConstants;
import eu.kalafatic.gemini.bt.visual.views.DrawComposite;

/**
 * The Interface IAlgoritm.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public interface IAlgoritm extends IAlgorithmsConstants {

	/**
	 * Inits the.
	 */
	public void init();

	/**
	 * Load input.
	 */
	public void loadInput();

	/**
	 * Sets the draw composite.
	 * 
	 * @param drawComposite
	 *            the new draw composite
	 */
	public void setDrawComposite(DrawComposite drawComposite);
	
	/**
	 * Sets the editor.
	 * 
	 * @param editor
	 *            the new editor
	 */
	public void setEditor(GraphMultiPageEditor editor);

	/**
	 * Solve.
	 */
	public void solve();

	/**
	 * Gets the result.
	 * 
	 * @return the result
	 */
	public Object getResult();

}
