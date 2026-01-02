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
package eu.kalafatic.gemini.core.threads;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LISTENING;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SRV_ACC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import eu.kalafatic.gemini.core.interfaces.AServerThread;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.SoundPlayer;

/**
 * The Class class InternalCommunicationThread.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class InternalCommunicationThread extends AServerThread {

	/** The args. */
	public static List<String> args = new ArrayList<String>();

	/** The instance. */
	private volatile static InternalCommunicationThread INSTANCE;

	/**
	 * Instantiates a new internal communication thread.
	 */
	public InternalCommunicationThread() {
		init();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the single instance of InternalCommunicationThread.
	 * @return single instance of InternalCommunicationThread
	 */
	public static InternalCommunicationThread getInstance() {
		if (INSTANCE == null) {
			synchronized (InternalCommunicationThread.class) {
				INSTANCE = new InternalCommunicationThread();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IServerThread#init()
	 */
	@Override
	public void init() {
		try {
			port = PREFERENCES.getInt(ECorePreferences.SINGLETON_ARGS_PORT.getName(), (Integer) ECorePreferences.SINGLETON_ARGS_PORT.getDef());
			message = MessageFormat.format(LISTENING, ECorePreferences.MODULE.getName(), "internal", port);
		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			String line;
			serverSocket = new ServerSocket(port);

			super.processMessages(ECorePreferences.MODULE, message);
			SoundPlayer.getInstance().play("audrey_gemini_is_listenning_on_internal_port.wav");

			while (true) {
				// catch one connection exception
				try {
					Socket socket = serverSocket.accept();

					Log.log(ECorePreferences.MODULE, LOG + SRV_ACC + socket.getInetAddress() + ":" + socket.getPort());

					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					while ((line = in.readLine()) != null) {
						Scanner scanner = new Scanner(line);

						while (scanner.hasNext()) {
							String cmd = scanner.next().toUpperCase();
							//
							// String[] split = cmd.split("=");
							//
							// if (commands.containsKey(split[0])) {
							// commands.get(split[0]).add(split[1]);
							// }else{
							// List<String>arg=new ArrayList<String>();
							// arg.add(split[1]);
							// commands.put(split[0],arg);
							// }
							// TODO
							// notify listeners
							args.add(cmd);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.log(ECorePreferences.MODULE, e);
				}
			}
		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		} finally {
			super.processEndOfThread(ECorePreferences.MODULE, serverSocket);
		}
	}

}
