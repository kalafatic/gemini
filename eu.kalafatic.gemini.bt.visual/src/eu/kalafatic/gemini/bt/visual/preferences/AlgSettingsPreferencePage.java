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
package eu.kalafatic.gemini.bt.visual.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class AlgSettingsPreferencePage.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class AlgSettingsPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage ,IUIConstants{

	/** The factory. */
	private GUIFactory factory;

	/** The container. */
	private Composite container;

	/**
	 * Instantiates a new alg settings preference page.
	 */
	public AlgSettingsPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		factory = new GUIFactory(parent);

		container = factory.createComposite(parent, 1);

		Group group = factory
				.createGroup(container, "Setup", 3);
		createSetupsBox(group);

		return container;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the setups box.
	 * 
	 * @param group
	 *            the group
	 */
	private void createSetupsBox(final Group group) {
		factory.createLabel(group, "Start node", SWT.NONE);

		final Text startNodeText = factory.createText(group, "Start node", true);
		startNodeText.setText(Integer.toString(Config.startNode));

		startNodeText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Config.startNode = Integer.valueOf(startNodeText.getText());
			}
		});
		factory.createLabel(group, "", 1);

		factory.createLabel(group, "End node", LABEL_WIDTH);
		
		final Text endNodeText = factory.createText(group, "End node", true);		
		endNodeText.setText(Integer.toString(Config.endNode));

		endNodeText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Config.endNode = Integer.valueOf(endNodeText.getText());
			}
		});
		factory.createLabel(group, "", 1);

		factory.createLabel(group, "Random distance range", LABEL_WIDTH);

		final Text distanceText = factory.createText(group,  "Random distance range", true);
		distanceText.setText(Integer.toString(Config.range));

		distanceText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Config.range = Integer.valueOf(distanceText.getText());
			}
		});

		final Button randomDistanceBtn = new Button(group, SWT.CHECK);
		randomDistanceBtn.setText("Generate");
		randomDistanceBtn.setSelection(Config.generate);
		randomDistanceBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (randomDistanceBtn.getSelection()) {
					Config.generate = true;
				} else {
					Config.generate = false;
				}
			}
		});

		factory.createLabel(group, "Graph circle size ", LABEL_WIDTH);

		final Text circleText = factory.createText(group, "Graph circle size ", true);
		@SuppressWarnings("unused")
		Rectangle clientArea = Display.getDefault().getPrimaryMonitor()
				.getClientArea();
		// Config.radius=(clientArea.height*2)/3;

		circleText.setText(Integer.toString(Config.radius));
		
		circleText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {

				Config.radius = Integer.valueOf(circleText.getText());
				Config.radiusChanged = true;
			}
		});
		factory.createLabel(group, "", 1);

		factory.createLabel(group, "Animation delay", LABEL_WIDTH);

		final Text delayText = factory.createText(group, "Animation delay", true);
		
		delayText.setText(Integer.toString(Config.delay));

		delayText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Config.delay = Integer.valueOf(delayText.getText());
			}
		});
	}
}
