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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.kalafatic.gemini.bt.visual.components.PathAlgorithmsTable;
import eu.kalafatic.gemini.bt.visual.lib.IAlgorithmsConstants;
import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class AlgorithmsPreferencePage.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class AlgorithmsPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage,IAlgorithmsConstants,IUIConstants {
	
	/** The factory. */
	private GUIFactory factory;

	/** The container. */
	private Composite container;
	
	/** The shortest path alg text. */
	private Text shortestPathAlgText;
	
	/**
	 * Instantiates a new algorithms preference page.
	 */
	public AlgorithmsPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		
		factory = new GUIFactory(parent);

		container = factory.createComposite(parent, 1);

		Group group = factory
				.createGroup(container, "Names and directories", 3);
		createAlgorithmBox(group);		

		return container;
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * Creates the algorithm box.
	 * 
	 * @param group
	 *            the group
	 */
	private void createAlgorithmBox(final Group group){
		
		factory.createLabel(group, "Select task", LABEL_WIDTH);
		
		final Button shortestPathBtn=factory.createButton(group, "Shortest Path", SWT.CHECK);		
		
		shortestPathBtn.setSelection(false);
		shortestPathBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (shortestPathBtn.getSelection()) {					
					Shell shell = new Shell(Display.getDefault());
					
					PathAlgorithmsTable instance = PathAlgorithmsTable
							.getInstance(shell, shortestPathAlgText,
									SHORTEST_PATH);
					instance.open();
				}
			}
		});

		shortestPathAlgText = factory.createText(group, "Shortest Path", false);

		// ---
		factory.createLabel(group, " ",LABEL_WIDTH);

		final Button longestPathBtn =factory.createButton(group, "Longest Path", SWT.CHECK);	
		longestPathBtn.setSelection(false);
		longestPathBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				showMessage("Not implemented yet");
			}

		});

		factory.createText(group,"Longest Path", false);

	}
	
	// ---------------------------------------------------------------
	
	/**
	 * Show message.
	 * 
	 * @param message
	 *            the message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
				"Information", message);
	}
}
