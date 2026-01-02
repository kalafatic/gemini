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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Button;

import eu.kalafatic.gemini.core.factories.GUIFactory;

/**
 * The Class class AWizardPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public abstract class AWizardPage extends WizardPage implements ISWizardPage {

	/** The page number. */
	protected int pageNumber;

	/** The factory. */
	protected GUIFactory factory;

	/** The progress. */
	protected Button[] PROGRESS = new Button[4];

	/** The monitor. */
	public IProgressMonitor monitor;

	/** The changed. */
	public boolean changed = true;

	/**
	 * Instantiates a new a wizard page.
	 * @param pageNumber the page number
	 * @param factory the factory
	 * @param pageName the page name
	 * @param title the title
	 * @param titleImage the title image
	 */
	protected AWizardPage(int pageNumber, GUIFactory factory, String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);

		this.pageNumber = pageNumber;
		this.factory = factory;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.wizards.pages.ISWizardPage#getPageNumber()
	 */
	@Override
	public int getPageNumber() {
		return pageNumber;
	}
}
