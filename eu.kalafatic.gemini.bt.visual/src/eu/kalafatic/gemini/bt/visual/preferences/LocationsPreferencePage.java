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

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class LocationsPreferencePage.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class LocationsPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, IUIConstants {
	
	/** The factory. */
	private GUIFactory factory;

	/** The container. */
	private Composite container;
	
	/** The input file name. */
	private String inputFileName = "";

	/** The file name. */
	private String fileName = "";

	/**
	 * Instantiates a new locations preference page.
	 */
	public LocationsPreferencePage() {
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
		createDirectoriesBox(group);		

		return container;
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * Creates the directories box.
	 * 
	 * @param group
	 *            the group
	 */
	private void createDirectoriesBox(final Group group){
		
		factory.createLabel(group, "Input file", SWT.NONE);

		final Text inputDirText = factory.createText(group, "Input file", false);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 150;

		inputDirText.setLayoutData(gridData);

		Button inBtn = factory.createButton(group, "Browse",
				GridData.HORIZONTAL_ALIGN_END);
		inBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(group.getShell());

				// fileDialog.setFilterPath("c:");
				// fileDialog.setFilterPath(System.getProperty("user.home"));
				String path = getClass().getProtectionDomain().getCodeSource()
						.getLocation().getPath();
				fileDialog.setFilterPath(path);

				fileDialog.setFilterExtensions(new String[] { "*.txt" });

				String open = fileDialog.open();

				inputDirText.setText(open);

				inputFileName = open;

				File input = new File(inputFileName);
				fileName = input.getName();

				Config.inputFileName = inputFileName;

				final IFileStore fileStore = EFS.getLocalFileSystem().getStore(
						input.toURI());
				IEditorInput editorInput = new FileStoreEditorInput(fileStore);

				IWorkbenchPage activePage = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();

				String editorID = "eu.kalafatic.gemini.bt.client.draw.GraphMultiPageEditor";
				try {
					IDE.openEditor(activePage, editorInput, editorID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});

		// --
		factory.createLabel(group, "Output directory :", LABEL_WIDTH);

		final Text outputDirText = factory.createText(group, "", false);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 150;

		outputDirText.setLayoutData(gridData);

		outputDirText.setText("");

		Button outBtn = factory.createButton(group, "Browse",
				GridData.HORIZONTAL_ALIGN_END);

		outBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog fileDialog = new DirectoryDialog(group
						.getShell());

				fileDialog.setFilterPath(System.getProperty("user.dir"));

				String open = fileDialog.open();

				if (inputFileName != "") {

					outputDirText.setText(open);

					File output = new File(open + "/" + fileName
							+ "-RESULT.txt");

					Config.outputFileName = output.getAbsolutePath();

					final IFileStore fileStore = EFS.getLocalFileSystem()
							.getStore(output.toURI());
					IEditorInput editorInput = new FileStoreEditorInput(
							fileStore);

					GraphMultiPageEditor activeEditor = (GraphMultiPageEditor) PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().getActiveEditor();

					activeEditor.createOutputPage(editorInput);

				} else {
					showMessage("Must set input file first !");
				}
			}
		});
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
