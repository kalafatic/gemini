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
package eu.kalafatic.gemini.core;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.core.application.ApplicationWorkbenchAdvisor;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.threads.InternalCommunicationThread;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class Application.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class Application implements IApplication {

	/** The args port. */
	private int argsPort = PREFERENCES.getInt(ECorePreferences.SINGLETON_ARGS_PORT.getName(), (Integer) ECorePreferences.SINGLETON_ARGS_PORT.getDef());

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app. IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) {
		Display display = PlatformUI.createDisplay();
		try {
			if (anotherInstance()) {
				if (!openAnotherInstance()) {
					return IApplication.EXIT_OK;
				}
			}
			// System.setProperty("user.language", locale);
			// System.setProperty("osgi.nl", locale);

			// Runtime.getRuntime().addShutdownHook(new ShutdownHook());

			// String[] commandLineArgs = Platform.getCommandLineArgs();
			// Platform.getProduct().getDefiningBundle().update();

			// listenSingleInstance();

			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());

			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
				// return IApplication.EXIT_RELAUNCH;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open another instance.
	 * @return true, if successful
	 */
	private boolean openAnotherInstance() {
		int result = DialogUtils.INSTANCE.question("Open new Window ?");

		return result == SWT.YES ? true : false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null) {
			return;
		}
		final Display display = workbench.getDisplay();

		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!display.isDisposed()) {
					workbench.close();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Another instance.
	 * @return true, if successful
	 */
	private boolean anotherInstance() {
		try {
			ServerSocket serverSocket = new ServerSocket(argsPort);
			InternalCommunicationThread.args.addAll(Arrays.asList(Platform.getCommandLineArgs()));
			serverSocket.close();

		} catch (BindException e1) {
			sendParameters();
			return true;
		} catch (IOException e2) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Send parameters.
	 */
	private void sendParameters() {
		try {
			String[] applicationArgs = Platform.getApplicationArgs();

			Socket socket = new Socket("localhost", argsPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			for (int i = 0; i < applicationArgs.length; i++) {
				out.write(applicationArgs[i] + " ");
			}
			out.flush();
			out.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
