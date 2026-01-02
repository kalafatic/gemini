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
package eu.kalafatic.gemini.core.wizards.pages;

import org.eclipse.swt.widgets.Table;

import eu.kalafatic.gemini.core.interfaces.ISWizardSettings;
import eu.kalafatic.gemini.core.models.SyncObject;

/**
 * The Interface interface ISWizardPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public interface ISWizardPage extends ISWizardSettings {

	/**
	 * Redraw.
	 */
	void redraw();

	/**
	 * Gets the page number.
	 * @return the page number
	 */
	int getPageNumber();

	/**
	 * Move object.
	 * @param targetTable the target table
	 * @param syncObject the sync object
	 * @param commit the commit
	 */
	void moveObject(Table targetTable, SyncObject syncObject, boolean commit);
}
