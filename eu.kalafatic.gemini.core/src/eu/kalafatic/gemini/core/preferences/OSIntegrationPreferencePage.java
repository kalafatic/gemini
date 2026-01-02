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
package eu.kalafatic.gemini.core.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.GET_SYSTEM_ENV;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.GET_USER_ENV;
import static eu.kalafatic.gemini.core.lib.constants.FCMDConstants.REGSTREXP_TOKEN;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.osgi.service.prefs.BackingStoreException;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.AppPreferences;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.OSUtils;
import eu.kalafatic.gemini.core.utils.WinFileAssociationUtils;
import eu.kalafatic.gemini.core.utils.WinRegistryUtils;

/**
 * The Class class OSIntegrationPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

public class OSIntegrationPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The grid data. */
	private GridData gridData;

	/** The ft text. */
	private Text programText, pathText, ftText;

	/** The ext combo. */
	private Combo extCombo;

	/** The browse btn. */
	private Button regBtn, browseBtn;

	/** The file dialog. */
	private FileDialog fileDialog;

	/** The default directory. */
	private final String DEFAULT_DIRECTORY = "C:\\Program Files\\";

	/** The program path. */
	private String programPath;

	/** The program dir path. */
	private String programDirPath;

	/** The path. */
	private String ext, ftype, path;

	/** The contexts. */
	private boolean[] contexts = new boolean[] { false, true, true };

	/** The system enviroment. */
	private String userEnviroment, systemEnviroment;

	/**
	 * Instantiates a new OS integration preference page.
	 */
	public OSIntegrationPreferencePage() {
		super.noDefaultAndApplyButton();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Activator.getPluginProperties().getString("OSIntegrationPreferencePageDescription"));
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		programPath = PREFERENCES.get(ECorePreferences.PROGRAM_LOC.getName(), (String) ECorePreferences.PROGRAM_LOC.getDef());

		programDirPath = new File(programPath).getParent().concat(File.separator);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		try {
			factory = new GUIFactory(parent);

			gridData = new GridData(GridData.FILL_BOTH);
			gridData.widthHint = 600;
			gridData.heightHint = 400;
			parent.setLayoutData(gridData);

			Composite container = factory.createComposite(parent, 1);
			container.setLayoutData(gridData);

			Group group = factory.createGroup(container, "File Association", 3);
			createOSAssociationBox(group);
			group = factory.createGroup(container, "Context Menu", 3);
			createOSContectMenuBox(group);
			group = factory.createGroup(container, "Enviroment", 3);
			createOSEnviromentBox(group);
			group = factory.createGroup(container, "Registry Actions", 3);
			createRegistryActionsBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the os association box.
	 * @param group the group
	 * @throws BackingStoreException the backing store exception
	 */
	private void createOSAssociationBox(final Group group) throws BackingStoreException {

		String osName = PREFERENCES.get(ECorePreferences.OS_NAME.getName(), (String) ECorePreferences.OS_NAME.getDef());

		if (osName.length() == 0) {
			osName = OSUtils.INSTANCE.getOSName();
			PREFERENCES.put(ECorePreferences.OS_NAME.getName(), osName);
		}

		if (!osName.startsWith("Windows")) {
			return;
		}

		fileDialog = new FileDialog(group.getShell());

		factory.createLabel(group, "Extension", SWT.NONE);

		final Text extText = factory.createText(group, "", true);

		extCombo = factory.createCombo(group, "Extensions", SWT.READ_ONLY);
		fillExtCombo(extCombo);

		// ---

		factory.createLabel(group, "File Type", SWT.NONE);

		ftText = factory.createText(group, "", true);
		ftText.setEnabled(false);

		factory.createLabel(group, "", SWT.NONE, 1);

		// ---

		factory.createLabel(group, "Associated Program", SWT.NONE);

		programText = factory.createText(group, "", true);
		programText.setEnabled(false);

		factory.createLabel(group, "", SWT.NONE, 1);

		// ---

		factory.createLabel(group, "Path To Program", SWT.NONE);

		pathText = factory.createText(group, "", true);
		pathText.setEnabled(false);

		Composite btnComposite = factory.createComposite(group, 2);

		browseBtn = factory.createButton(btnComposite, "Browse", SWT.PUSH);
		browseBtn.setEnabled(false);

		regBtn = factory.createButton(btnComposite, "Register", SWT.PUSH);
		regBtn.setEnabled(false);

		regBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				ext = extText.getText();
				ftype = ftText.getText();
				path = pathText.getText();

				String assoc = ftype + "=" + path;

				AppPreferences.getInstance().getPreference("extensions").put(ext, assoc);

				fillExtCombo(extCombo);

				WinRegistryUtils.associateExtension(ext, ftype);
			}
		});

		browseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String open = fileDialog.open();

				if (open != null) {
					pathText.setText(open);
					regBtn.setEnabled(true);

					programPath = open;
					programDirPath = new File(programPath).getParent();

					programText.setText(new File(open).getName());
					programText.setEnabled(false);
				}
			}
		});

		extText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				browseBtn.setEnabled(true);
				regBtn.setEnabled(true);

				String extension = extText.getText();

				String assoc = WinFileAssociationUtils.getAssoc(extension);

				if (assoc != null && assoc.length() > 0 && assoc.contains("=")) {

					String[] prefValues = assoc.split("=");
					extension = prefValues[0];
					String fileType = prefValues[1];

					ftText.setText(fileType);
					ftText.setEnabled(false);

					String association = WinFileAssociationUtils.getFtype(fileType);

					if (association != null && association.length() > 0) {
						prefValues = association.split("=");

						String pathWithArgs = prefValues[1];

						setPathAndProgram(pathWithArgs);

						List<String> comboItems = Arrays.asList(extCombo.getItems());
						if (!comboItems.contains(extension)) {
							AppPreferences.getInstance().getPreference("extensions").put(extension, association);
							extCombo.add(extension);
						}
						extCombo.update();
					}

				} else {
					ftText.setText("");
					ftText.setEnabled(true);

					programText.setText("");
					programText.setEnabled(true);

					pathText.setText("");

					fileDialog.setFilterPath(DEFAULT_DIRECTORY);
				}
			}
		});

		extCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String extension = extCombo.getItem(extCombo.getSelectionIndex());
				extText.setText(extension);

				String association = AppPreferences.getInstance().getPreference("extensions").get(extension, null);

				if (association != null && association.length() > 0 && association.contains("=")) {

					String[] prefValues = association.split("=");

					String fileType = prefValues[0];
					String pathWithArgs = prefValues[1];

					ftText.setText(fileType);
					ftText.setEnabled(false);

					setPathAndProgram(pathWithArgs);

					regBtn.setEnabled(false);
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the os contect menu box.
	 * @param group the group
	 */
	private void createOSContectMenuBox(final Group group) {
		Composite btnComposite = factory.createComposite(group, 3);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		btnComposite.setLayoutData(gridData);

		final Button allBtn = factory.createButton(btnComposite, "All (*) !", SWT.CHECK, 50);
		allBtn.setSelection(contexts[0]);
		final Button openBtn = factory.createButton(btnComposite, "Open", SWT.CHECK, 50);
		openBtn.setSelection(contexts[1]);
		final Button openWithBtn = factory.createButton(btnComposite, "Open With List", SWT.CHECK, 100);
		openWithBtn.setSelection(contexts[2]);

		// ---

		final Text programPathText = factory.createText(group, "Program Path", false);

		Button workspaceBtn = factory.createButton(group, "Browse", SWT.PUSH);
		final Button regBtn = factory.createButton(group, "Register", SWT.PUSH);

		workspaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				FileDialog fileDialog = new FileDialog(group.getShell());
				fileDialog.setFilterPath(programDirPath);
				String open = fileDialog.open();
				programPathText.setText(open);

				// PREFERENCES.put(ECorePreferences.WORKSPACE_LOC.getName(),
				// open);
			}
		});

		if (programPath == null || programPath.length() == 0) {
			regBtn.setEnabled(false);
		} else {
			programPathText.setText(programPath);
		}

		Listener btnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == allBtn) {
					contexts[0] = allBtn.getSelection();
					openBtn.setEnabled(!contexts[0]);
					openWithBtn.setEnabled(!contexts[0]);
				} else if (event.widget == openBtn) {
					contexts[1] = openBtn.getSelection();
				} else if (event.widget == openWithBtn) {
					contexts[2] = openWithBtn.getSelection();
				} else if (event.widget == regBtn) {
					WinRegistryUtils.integrateContextMenu(ext, ftype, programPath, contexts);
				}
			}
		};
		allBtn.addListener(SWT.Selection, btnListener);
		openBtn.addListener(SWT.Selection, btnListener);
		openWithBtn.addListener(SWT.Selection, btnListener);
		regBtn.addListener(SWT.Selection, btnListener);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the os enviroment box.
	 * @param group the group
	 */
	private void createOSEnviromentBox(final Group group) {
		final Text programPathText = factory.createText(group, "Program Path", false);
		programPathText.setText(programPath);

		Button browseBtn = factory.createButton(group, "Browse", SWT.PUSH);
		final Button regBtn = factory.createButton(group, "Register", SWT.PUSH);

		browseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				FileDialog fileDialog = new FileDialog(group.getShell());
				fileDialog.setFilterPath(programDirPath);
				String open = fileDialog.open();

				if (!open.equalsIgnoreCase(programPath)) {
					programPathText.setText(open);
					PREFERENCES.put(ECorePreferences.PROGRAM_LOC.getName(), open);
					regBtn.setEnabled(true);
				}
			}
		});

		regBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				WinRegistryUtils.addProgramToPath(programDirPath, programPath);
			}
		});

		// ---

		Label label = factory.createLabel(group, "User Enviroment", SWT.NONE);

		GridData gridData = new GridData();
		gridData.widthHint = LABEL_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		gridData.horizontalSpan = 3;
		label.setLayoutData(gridData);

		final Table userEnvTable = factory.createTable(group, "User Paths");
		final TableColumn tc1 = new TableColumn(userEnvTable, SWT.BEGINNING);

		userEnviroment = WinRegistryUtils.getFromRegistry(GET_USER_ENV, REGSTREXP_TOKEN);
		setEnviromentTable(userEnvTable, tc1, userEnviroment);

		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;

		Button regUserBtn = factory.createButton(group, "Register", SWT.PUSH);
		regUserBtn.setLayoutData(gridData);
		if (userEnviroment != null) {
			regUserBtn.setEnabled(!userEnviroment.contains(programDirPath));
		}

		regUserBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				WinRegistryUtils.addProgramToUserEnviroment(programDirPath + ";" + userEnviroment);
				WinRegistryUtils.reloadRegistry();

				String userResult = userEnviroment = WinRegistryUtils.getFromRegistry(GET_USER_ENV, REGSTREXP_TOKEN);
				setEnviromentTable(userEnvTable, tc1, userResult);
			}
		});

		label = factory.createLabel(group, "System Enviroment", SWT.NONE);

		gridData = new GridData();
		gridData.widthHint = LABEL_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		gridData.horizontalSpan = 3;
		label.setLayoutData(gridData);

		final Table systemEnvTable = factory.createTable(group, "System Paths");
		final TableColumn tc2 = new TableColumn(systemEnvTable, SWT.BEGINNING);

		systemEnviroment = WinRegistryUtils.getFromRegistry(GET_SYSTEM_ENV, REGSTREXP_TOKEN);
		setEnviromentTable(systemEnvTable, tc2, systemEnviroment);

		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;

		Button regSystemBtn = factory.createButton(group, "Register", SWT.PUSH);
		regSystemBtn.setLayoutData(gridData);
		if (systemEnviroment != null) {
			regSystemBtn.setEnabled(!systemEnviroment.contains(programDirPath));
		}

		regSystemBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				WinRegistryUtils.addProgramToSystemEnviroment(programDirPath + ";" + systemEnviroment);
				WinRegistryUtils.reloadRegistry();

				String systemResult = systemEnviroment = WinRegistryUtils.getFromRegistry(GET_SYSTEM_ENV, REGSTREXP_TOKEN);
				setEnviromentTable(systemEnvTable, tc2, systemResult);
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the enviroment table.
	 * @param table the table
	 * @param tc1 the tc1
	 * @param userResult the user result
	 */
	private void setEnviromentTable(Table table, TableColumn tc1, String userResult) {
		table.removeAll();
		if (userResult != null) {
			String[] userValues = userResult.split(";");
			for (int i = 0; i < userValues.length; i++) {
				new TableItem(table, SWT.BEGINNING).setText(userValues[i]);
			}
			tc1.pack();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the registry actions box.
	 * @param group the group
	 */
	private void createRegistryActionsBox(final Group group) {
		Button backupBtn = factory.createButton(group, "Backup", SWT.PUSH);

		backupBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {

			}
		});

		Button loadBtn = factory.createButton(group, "Load", SWT.PUSH);

		loadBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				// DirectoryDialog fileDialog = new DirectoryDialog(group
				// .getShell());
				// fileDialog.setFilterPath(value);
				// String open = fileDialog.open();
				// osPathText.setText(open);
				//
				// PREFERENCES.put(ECorePreferences.WORKSPACE_LOC.getName(),
				// open);
			}
		});

		Button reloadBtn = factory.createButton(group, "Refresh", SWT.PUSH);

		reloadBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				WinRegistryUtils.reloadRegistry();
			}
		});

	}

	// ---------------------------------------------------------------

	/**
	 * Sets the path and program.
	 * @param pathWithArgs the new path and program
	 */
	private void setPathAndProgram(String pathWithArgs) {
		String[] trimmPath = pathWithArgs.split("\"");
		String pathToProgram = trimmPath[trimmPath.length > 1 ? 1 : 0];
		try {
			File file = new File(pathToProgram);
			if (file.exists()) {
				programText.setText(file.getName());
				programText.setEnabled(false);

				pathText.setText(pathToProgram);
				fileDialog.setFileName(file.getName());
				fileDialog.setFilterPath(file.getParent());

				programPath = pathToProgram;
				programDirPath = new File(programPath).getParent();
			} else {
				fileDialog.setFilterPath(programPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Fill ext combo.
	 * @param extCombo the ext combo
	 */
	private void fillExtCombo(final Combo extCombo) {
		try {
			extCombo.removeAll();
			String[] values = AppPreferences.getInstance().getPreference("extensions").keys();

			for (int i = 0; i < values.length; i++) {
				extCombo.add(values[i]);
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
}