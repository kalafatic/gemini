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
package eu.kalafatic.gemini.core.utils;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EHandler;
import eu.kalafatic.gemini.core.schedulers.CPUScheduler;

/**
 * The Class class CMDUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CMDUtils {

	/** The instance. */
	private volatile static CMDUtils INSTANCE;

	/**
	 * Gets the single instance of CMDUtils.
	 * @return single instance of CMDUtils
	 */
	public static CMDUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (CMDUtils.class) {
				INSTANCE = new CMDUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run after start.
	 */
	public void runAfterStart() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				runInternal();
				runRC();
				runBT();
				runCPU();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Run internal.
	 */
	private void runInternal() {
		CommandFactory.INSTANCE.executeCommand(EHandler.RUN_INT_COMM.ID);
	}

	// ---------------------------------------------------------------

	/**
	 * Run rc.
	 */
	private void runRC() {
		boolean run = PREFERENCES.getBoolean(ECorePreferences.RC_ENABLED.getName(), ((Boolean) ECorePreferences.RC_ENABLED.getDef()));

		if (run) {
			CommandFactory.INSTANCE.executeCommand(EHandler.RUN_RC.ID);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run bt.
	 */
	private void runBT() {
		boolean run = PREFERENCES.getBoolean(ECorePreferences.RUN_BT_AFTER_START.getName(), ((Boolean) ECorePreferences.RUN_BT_AFTER_START.getDef()));

		if (run) {
			CommandFactory.INSTANCE.executeCommand(EHandler.RUN_TORRENTS.ID);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Run cpu.
	 */
	private void runCPU() {
		boolean run = PREFERENCES.getBoolean(ECorePreferences.CPU_MANAGEMENT.getName(), (Boolean) ECorePreferences.CPU_MANAGEMENT.getDef());

		if (run) {
			CPUScheduler.INSTANCE.start();
		}
	}
}
