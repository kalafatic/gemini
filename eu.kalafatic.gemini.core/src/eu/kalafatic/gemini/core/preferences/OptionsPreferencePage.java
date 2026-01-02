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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.HUNDRED_NUMBERS;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.TEN_NUMBERS;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ELogEvent;
import eu.kalafatic.gemini.core.lib.ELogType;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.perspectives.CorePerspective;
import eu.kalafatic.gemini.core.threads.DeadlocksManagerThread;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.LoggerUtils;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class OptionsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OptionsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The check log size after text. */
	private Text logMaxSizeText, checkLogSizeAfterText;

	/** The sound enabled btn. */
	private Button threadManagementBtn, cpuManagementBtn, enStartBtn, enRCBtn, soundEnabledBtn;

	/**
	 * Instantiates a new options preference page.
	 */
	public OptionsPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		try {
			factory = new GUIFactory(parent);
			Composite container = factory.createComposite(parent, 1);

			Group group = factory.createGroup(container, "Start settings", 1);
			createStartBox(group);

			group = factory.createGroup(container, "Log settings", 3);
			createLoggingBox(group);

			group = factory.createGroup(container, "Monitoring", 1);
			createThreadMonitorBox(group);
			createCPUMonitorBox(group);

			group = factory.createGroup(container, "Sounds", 2);
			createSoundBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the start box.
	 * @param group the group
	 */
	private void createStartBox(Group group) {
		enStartBtn = factory.createButton(group, ECorePreferences.RUN_BT_AFTER_START.getName(), SWT.CHECK, LONG_TEXT_WIDTH);
		boolean enStartValue = PREFERENCES.getBoolean(ECorePreferences.RUN_BT_AFTER_START.getName(), ((Boolean) ECorePreferences.RUN_BT_AFTER_START.getDef()));
		enStartBtn.setSelection(enStartValue);

		enRCBtn = factory.createButton(group, ECorePreferences.RUN_BT_AFTER_START.getName(), SWT.CHECK, LONG_TEXT_WIDTH);
		boolean enRCValue = PREFERENCES.getBoolean(ECorePreferences.RC_ENABLED.getName(), ((Boolean) ECorePreferences.RC_ENABLED.getDef()));
		enRCBtn.setSelection(enRCValue);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the logging box.
	 * @param group the group
	 */
	private void createLoggingBox(final Group group) {
		Composite composite = factory.createComposite(group, 1);

		final Button enLogBtn = factory.createButton(composite, ECorePreferences.LOG_ENABLED.getName(), SWT.CHECK, LABEL_WIDTH);

		boolean enLogValue = PREFERENCES.getBoolean(ECorePreferences.LOG_ENABLED.getName(), ((Boolean) ECorePreferences.LOG_ENABLED.getDef()));

		enLogBtn.setSelection(enLogValue);

		final Button enConsoleBtn = factory.createButton(composite, ECorePreferences.LOG_CONSOLE.getName(), SWT.CHECK, LABEL_WIDTH);

		boolean enConsoleValue = PREFERENCES.getBoolean(ECorePreferences.LOG_CONSOLE.getName(), ((Boolean) ECorePreferences.LOG_CONSOLE.getDef()));

		enConsoleBtn.setSelection(enConsoleValue);

		final Group logTypegroup = createLogTypeGroup(group);
		logTypegroup.setVisible(enLogValue);

		final Group logEventGroup = createLogEventGroup(group);
		logEventGroup.setVisible(enLogValue);

		enLogBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = enLogBtn.getSelection();
				PREFERENCES.putBoolean(ECorePreferences.LOG_ENABLED.getName(), selection);

				logTypegroup.setVisible(selection);
				logEventGroup.setVisible(selection);
				Log.LOG_ENABLED = selection;
			}
		});

		enConsoleBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean selection = enConsoleBtn.getSelection();

				PREFERENCES.putBoolean(ECorePreferences.LOG_CONSOLE.getName(), selection);

				try {
					if (selection) {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(CorePerspective.EView.CONSOLE.ID);
					} else {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(CorePerspective.EView.CONSOLE.ID).dispose();
					}

				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		createActionGroup(group);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the action group.
	 * @param group the group
	 */
	private void createActionGroup(final Group group) {
		factory.createLabel(group, ECorePreferences.LOG_SIZE.getName(), SWT.NONE);

		logMaxSizeText = factory.createText(group, ECorePreferences.LOG_SIZE.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.LOG_SIZE.getName(), ((Integer) ECorePreferences.LOG_SIZE.getDef()).intValue());

		logMaxSizeText.setText(Integer.toString(value));

		final Combo combo = factory.createCombo(group, ECorePreferences.LOG_SIZE.getName(), SWT.NULL);

		String[] counts = HUNDRED_NUMBERS;

		for (int i = 0; i < counts.length; i++) {
			combo.add(counts[i]);
		}

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String item = combo.getItem(combo.getSelectionIndex());
				logMaxSizeText.setText(item);

				PREFERENCES.putInt(ECorePreferences.LOG_SIZE.getName(), Integer.parseInt(item));
			}
		});

		createCheckSizeGroup(group);

		factory.createLabel(group, "", SWT.NORMAL);
		factory.createLabel(group, "", SWT.NORMAL);

		final Button clearButton = factory.createButton(group, "Clear", SWT.PUSH);

		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearLogs();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the check size group.
	 * @param group the group
	 */
	private void createCheckSizeGroup(Group group) {
		factory.createLabel(group, ECorePreferences.CHECK_AFTER.getName(), SWT.NONE);

		checkLogSizeAfterText = factory.createText(group, ECorePreferences.CHECK_AFTER.getName(), false);

		int value = PREFERENCES.getInt(ECorePreferences.CHECK_AFTER.getName(), (Integer) ECorePreferences.CHECK_AFTER.getDef());

		checkLogSizeAfterText.setText(Integer.toString(value));

		final Combo combo = factory.createCombo(group, ECorePreferences.CHECK_AFTER.getName(), SWT.NULL);

		String[] counts = TEN_NUMBERS;

		for (int i = 0; i < counts.length; i++) {
			combo.add(counts[i]);
		}

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String item = combo.getItem(combo.getSelectionIndex());

				checkLogSizeAfterText.setText(item);
				PREFERENCES.putInt(ECorePreferences.CHECK_AFTER.getName(), Integer.parseInt(item));
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Clear logs.
	 */
	private synchronized void clearLogs() {

		LoggerUtils.getInstance().closeBeforeDelete();

		String logs = PREFERENCES.get(ECorePreferences.LOGS_LOC.getName(), (String) ECorePreferences.LOGS_LOC.getDef());

		File[] listFiles = new File(logs).listFiles();

		for (int i = 0; i < listFiles.length; i++) {
			listFiles[i].delete();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the log type group.
	 * @param group the group
	 * @return the group
	 */
	private Group createLogTypeGroup(final Group group) {

		final Group g = new Group(group, SWT.NONE);

		GridData gridData = new GridData();
		gridData.widthHint = 85;
		gridData.heightHint = BTN_WIDTH;

		g.setLayoutData(gridData);

		// g.setText("Options Group");
		final Button txtBtn = new Button(g, SWT.RADIO);
		txtBtn.setBounds(10, 20, 75, 15);
		txtBtn.setText("  " + ELogType.TXT.getValue());

		String value = PREFERENCES.get(ECorePreferences.LOG_TYPE.getName(), (String) ECorePreferences.LOG_TYPE.getDef());

		boolean boolValue = value.equals(ELogType.TXT.getValue()) ? true : false;

		txtBtn.setSelection(boolValue);

		txtBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_TYPE.getName(), ELogType.TXT.getValue());
				}
			}
		});

		final Button htmlBtn = new Button(g, SWT.RADIO);
		htmlBtn.setBounds(10, 35, 75, 15);
		htmlBtn.setText("  " + ELogType.HTML.getValue());

		boolValue = value.equals(ELogType.HTML.getValue()) ? true : false;

		htmlBtn.setSelection(boolValue);

		htmlBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (htmlBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_TYPE.getName(), ELogType.HTML.getValue());
				}
			}
		});

		final Button xmlBtn = new Button(g, SWT.RADIO);
		xmlBtn.setBounds(10, 50, 80, 15);
		xmlBtn.setText("  " + ELogType.XML.getValue());

		boolValue = value.equals(ELogType.XML.getValue()) ? true : false;

		xmlBtn.setSelection(boolValue);

		xmlBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (xmlBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_TYPE.getName(), ELogType.XML.getValue());
				}
			}
		});
		return g;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the log event group.
	 * @param group the group
	 * @return the group
	 */
	private Group createLogEventGroup(final Group group) {

		final Group g = new Group(group, SWT.NONE);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		gridData.widthHint = 90;
		gridData.heightHint = BTN_WIDTH;
		gridData.horizontalIndent = 10;

		g.setLayoutData(gridData);

		// g.setText("Options Group");
		final Button bothBtn = new Button(g, SWT.RADIO);
		bothBtn.setBounds(10, 20, 75, 15);
		bothBtn.setText("  " + ELogEvent.BOTH.getValue());

		String value = PREFERENCES.get(ECorePreferences.LOG_EVENT.getName(), (String) ECorePreferences.LOG_EVENT.getDef());

		boolean boolValue = value.equals(ELogEvent.BOTH.getValue()) ? true : false;

		bothBtn.setSelection(boolValue);

		bothBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bothBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_EVENT.getName(), ELogEvent.BOTH.getValue());
				}
			}
		});

		final Button exBtn = new Button(g, SWT.RADIO);
		exBtn.setBounds(10, 35, 75, 15);
		exBtn.setText("  " + ELogEvent.EXCEPTIONS.getValue());

		boolValue = value.equals(ELogEvent.EXCEPTIONS.getValue()) ? true : false;
		exBtn.setSelection(boolValue);

		exBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (exBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_EVENT.getName(), ELogEvent.EXCEPTIONS.getValue());
				}
			}
		});

		final Button evBtn = new Button(g, SWT.RADIO);
		evBtn.setBounds(10, 50, 80, 15);
		evBtn.setText("  " + ELogEvent.EVENTS.getValue());

		boolValue = value.equals(ELogEvent.EVENTS.getValue()) ? true : false;
		evBtn.setSelection(boolValue);

		evBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (evBtn.getSelection()) {
					PREFERENCES.put(ECorePreferences.LOG_EVENT.getName(), ELogEvent.EVENTS.getValue());
				}
			}
		});
		return g;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the thread monitor box.
	 * @param group the group
	 */
	private void createThreadMonitorBox(final Group group) {
		threadManagementBtn = factory.createButton(group, ECorePreferences.THREAD_MANAGEMENT.getName(), SWT.CHECK, LONG_TEXT_WIDTH);

		boolean boolValue = PREFERENCES.getBoolean(ECorePreferences.THREAD_MANAGEMENT.getName(), (Boolean) ECorePreferences.THREAD_MANAGEMENT.getDef());

		threadManagementBtn.setSelection(boolValue);

		threadManagementBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PREFERENCES.putBoolean(ECorePreferences.THREAD_MANAGEMENT.getName(), threadManagementBtn.getSelection());

				if (threadManagementBtn.getSelection()) {
					DeadlocksManagerThread.INSTANCE.start();
				} else {
					DeadlocksManagerThread.INSTANCE.stop();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cpu monitor box.
	 * @param group the group
	 */
	private void createCPUMonitorBox(Group group) {
		cpuManagementBtn = factory.createButton(group, ECorePreferences.CPU_MANAGEMENT.getName(), SWT.CHECK, LONG_TEXT_WIDTH);

		boolean boolValue = PREFERENCES.getBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), (Boolean) ECorePreferences.CPU_MANAGEMENT.getDef());

		cpuManagementBtn.setSelection(boolValue);

		cpuManagementBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PREFERENCES.putBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), cpuManagementBtn.getSelection());

				if (cpuManagementBtn.getSelection()) {
					DeadlocksManagerThread.INSTANCE.start();
				} else {
					DeadlocksManagerThread.INSTANCE.stop();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sound box.
	 * @param group the group
	 */
	private void createSoundBox(final Group group) {
		soundEnabledBtn = factory.createButton(group, ECorePreferences.SOUND.getName(), SWT.CHECK, LABEL_WIDTH);

		boolean value = PREFERENCES.getBoolean(ECorePreferences.SOUND.getName(), ((Boolean) ECorePreferences.SOUND.getDef()));

		soundEnabledBtn.setSelection(value);

		Button button = factory.createButton(group, "Check", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean enabled = PREFERENCES.getBoolean(ECorePreferences.SOUND.getName(), (Boolean) ECorePreferences.SOUND.getDef());

				if (enabled) {
					SoundPlayer.getInstance().play("audrey_whats_up.wav");
				} else {
					DialogUtils.INSTANCE.showMessage(SWT.ICON_INFORMATION, "Sound disabled", "Enable sound and press apply");
				}

			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		try {
			enRCBtn.setSelection(((Boolean) ECorePreferences.RC_ENABLED.getDef()));
			PREFERENCES.putBoolean(ECorePreferences.RC_ENABLED.getName(), ((Boolean) ECorePreferences.RC_ENABLED.getDef()));

			enStartBtn.setSelection((Boolean) ECorePreferences.RUN_BT_AFTER_START.getDef());
			PREFERENCES.putBoolean(ECorePreferences.RUN_BT_AFTER_START.getName(), (Boolean) ECorePreferences.RUN_BT_AFTER_START.getDef());

			threadManagementBtn.setSelection((Boolean) ECorePreferences.THREAD_MANAGEMENT.getDef());
			PREFERENCES.putBoolean(ECorePreferences.THREAD_MANAGEMENT.getName(), (Boolean) ECorePreferences.THREAD_MANAGEMENT.getDef());

			cpuManagementBtn.setSelection((Boolean) ECorePreferences.CPU_MANAGEMENT.getDef());
			PREFERENCES.putBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), (Boolean) ECorePreferences.CPU_MANAGEMENT.getDef());

			logMaxSizeText.setText(Integer.toString((Integer) ECorePreferences.LOG_SIZE.getDef()));
			PREFERENCES.putInt(ECorePreferences.LOG_SIZE.getName(), (Integer) ECorePreferences.LOG_SIZE.getDef());

			checkLogSizeAfterText.setText(Integer.toString((Integer) ECorePreferences.CHECK_AFTER.getDef()));
			PREFERENCES.putInt(ECorePreferences.CHECK_AFTER.getName(), (Integer) ECorePreferences.CHECK_AFTER.getDef());

			soundEnabledBtn.setSelection((Boolean) ECorePreferences.SOUND.getDef());
			PREFERENCES.putBoolean(ECorePreferences.SOUND.getName(), (Boolean) ECorePreferences.SOUND.getDef());

			super.performDefaults();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		try {
			PREFERENCES.putBoolean(ECorePreferences.RUN_BT_AFTER_START.getName(), enStartBtn.getSelection());
			PREFERENCES.putBoolean(ECorePreferences.RC_ENABLED.getName(), enRCBtn.getSelection());
			PREFERENCES.putBoolean(ECorePreferences.THREAD_MANAGEMENT.getName(), threadManagementBtn.getSelection());
			PREFERENCES.putBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), cpuManagementBtn.getSelection());
			PREFERENCES.putInt(ECorePreferences.LOG_SIZE.getName(), (Integer) ECorePreferences.LOG_SIZE.getDef());
			PREFERENCES.putInt(ECorePreferences.CHECK_AFTER.getName(), (Integer) ECorePreferences.CHECK_AFTER.getDef());
			PREFERENCES.putBoolean(ECorePreferences.SOUND.getName(), soundEnabledBtn.getSelection());

		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		try {
			// Forces the application to save the CORE preferences
			PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
