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
import org.eclipse.ui.dialogs.PreferencesUtil;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.core.factories.SectionFactory;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class SettingsGroupContent.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class SettingsGroupContent implements IUIConstants {

	/** The factory. */
	private SectionFactory factory;

	/** The group. */
	private Group group;

	/**
	 * Instantiates a new settings group content.
	 * 
	 * @param factory
	 *            the factory
	 * @param group
	 *            the group
	 */
	public SettingsGroupContent(SectionFactory factory, Group group) {
		this.factory = factory;
		this.group = group;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the content.
	 */
	public void createContent() {

		final Button prefBtn = factory.createButton(group, "Preferences",
				SWT.PUSH);

		GridData gridData = new GridData(GridData.FILL);		
		gridData.heightHint = BTN_HEIGHT;
		gridData.horizontalSpan = 2;
		prefBtn.setLayoutData(gridData);

		prefBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				PreferencesUtil
						.createPreferenceDialogOn(
								group.getShell(),
								"eu.kalafatic.gemini.bt.client.draw.preferences.DrawPreferencePage",
								new String[] {
										"eu.kalafatic.gemini.bt.client.draw.preferences.LocationsPreferencePage",
										"eu.kalafatic.gemini.bt.client.draw.preferences.AlgorithmsPreferencePage",
										"eu.kalafatic.gemini.bt.client.draw.preferences.AlgSetupPreferencePage" },
								null).open();
			}
		});

		final Button outputBtn = factory.createButton(group, "Generate output",
				SWT.CHECK, 100);

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
		final Button resultBtn = factory.createButton(group, "Show result",
				SWT.CHECK, 100);
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

		final Button drawBtn = factory.createButton(group, "Draw solution",
				SWT.CHECK, 100);
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
	}
}
