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
 * The Class ConsoleGroupContent.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class ConsoleGroupContent {

	/** The factory. */
	private SectionFactory factory;
	
	/** The group. */
	private Group group;

	/**
	 * Instantiates a new console group content.
	 * 
	 * @param factory
	 *            the factory
	 * @param group
	 *            the group
	 */
	public ConsoleGroupContent(SectionFactory factory, Group group) {
		this.factory = factory;
		this.group = group;
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the content.
	 */
	public void createContent() {

		// 1
		factory.createLabel(group, "Use settings :        ", SWT.NORMAL);

		// 2
		final Button outputBtn = new Button(group, SWT.CHECK);
		outputBtn.setText("Generate output");
		outputBtn.setSelection(true);
		outputBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (outputBtn.getSelection()) {
					Config.generateOutput = true;
				} else {
					Config.generateOutput = false;
				}
			}
		});

		// 3 		
		final Button resultBtn = new Button(group, SWT.CHECK);
		resultBtn.setText("Show result        ");
		resultBtn.setSelection(true);
		resultBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (resultBtn.getSelection()) {
					Config.showResult = true;
				} else {
					Config.showResult = false;
				}
			}
		});
		// 4 - 5
		factory.createLabel(group, " ", SWT.NORMAL);
		factory.createLabel(group, " ", SWT.NORMAL);

		// ---

		// 1
		factory.createLabel(group, " ", SWT.NORMAL);

		// 2
		final Button drawBtn = new Button(group, SWT.CHECK);
		drawBtn.setText("Draw solution    ");
		drawBtn.setSelection(Config.drawSolution);
		
		drawBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (drawBtn.getSelection()) {
					Config.drawSolution = true;
				} else {
					Config.drawSolution = false;
				}
			}
		});

		// 3 - 5
		factory.createLabel(group, " ", SWT.NORMAL);
		factory.createLabel(group, " ", SWT.NORMAL);
		factory.createLabel(group, " ", SWT.NORMAL);
		
		
		
		// ---

		// 1
		factory.createLabel(group, "Select action :        ", SWT.NORMAL);

		// 2
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
