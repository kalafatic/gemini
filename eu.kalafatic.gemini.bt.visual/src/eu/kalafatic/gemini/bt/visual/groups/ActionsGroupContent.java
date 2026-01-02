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
package eu.kalafatic.gemini.bt.visual.groups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.main.AplicationManager;
import eu.kalafatic.gemini.core.factories.SectionFactory;

/**
 * The Class ActionsGroupContent.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class ActionsGroupContent {

	/** The factory. */
	private SectionFactory factory;
	
	/** The group. */
	private Group group;

	/**
	 * Instantiates a new actions group content.
	 * 
	 * @param factory
	 *            the factory
	 * @param group
	 *            the group
	 */
	public ActionsGroupContent(SectionFactory factory, Group group) {
		this.factory = factory;
		this.group = group;
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the content.
	 */
	public void createContent() {
		
		Button analyzeBtn = factory.createButton(group, "Analyze", SWT.NORMAL);

		analyzeBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				AplicationManager.getInstance().analyzeModel();
			}
		});
		
		// 3
		Button solveButton = factory.createButton(group, "Solve", SWT.NORMAL);

		solveButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				AplicationManager.getInstance().solveAlgorithm();
			}
		});

		// 4
		Button stopBtn = factory.createButton(group, "Stop", GridData.HORIZONTAL_ALIGN_END);
		
		stopBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Config.stopped=true;
			}
		});
		
		// 5
		Button resetBtn = factory.createButton(group, "Reset", GridData.HORIZONTAL_ALIGN_END);
		
		resetBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				AplicationManager.getInstance().resetModel();
			}
		});
	}
}
