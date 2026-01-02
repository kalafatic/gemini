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
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EX;

import java.util.logging.Level;

import org.eclipse.emf.common.util.Enumerator;

import eu.kalafatic.gemini.core.interfaces.ALog;
import eu.kalafatic.gemini.core.interfaces.IPreference;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ELogEvent;
import eu.kalafatic.gemini.core.models.LogElement;

/**
 * The Class class Log.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class Log extends ALog {

	/** The Constant INSTANCE. */
	private static final Log INSTANCE = new Log();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Log.
	 * @param msg the msg
	 */
	public static void log(String msg) {
		Log.log(ECorePreferences.MODULE, msg);
	}

	// ---------------------------------------------------------------

	/**
	 * Log.
	 * @param iPreference the i preference
	 * @param msg the msg
	 */
	public static void log(IPreference iPreference, String msg) {
		INSTANCE.log(iPreference.getName(), msg);
	}

	// ---------------------------------------------------------------

	/**
	 * Log.
	 * @param iPreference the i preference
	 * @param enumerator the enumerator
	 */
	public static void log(IPreference iPreference, Enumerator enumerator) {
		INSTANCE.log(iPreference.getName(), enumerator.getLiteral());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ILog#log(java.lang.String, java.lang.String)
	 */
	@Override
	public void log(String logName, String msg) {
		LogElement logElement = LoggerUtils.getInstance().getLogElement("INF", logName, msg);

		System.out.println(logElement.getLineMsg());

		if (LOG_CONSOLE) {
			printConsole(logElement);
		}

		if (!LOG_ENABLED) {
			return;
		}
		String logEvent = PREFERENCES.get(ECorePreferences.LOG_EVENT.getName(), (String) ECorePreferences.LOG_EVENT.getDef());

		if (logEvent.equals(ELogEvent.BOTH.getValue()) || logEvent.equals(ELogEvent.EVENTS.getValue())) {

			logger = LoggerUtils.getInstance().getLogger(logName);

			logger.log(Level.INFO, msg);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Log.
	 * @param iPreference the i preference
	 * @param exception the exception
	 */
	public static void log(IPreference iPreference, Exception exception) {
		INSTANCE.log(iPreference.getName(), "", exception);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ILog#log(java.lang.String, java.lang.Exception)
	 */
	@Override
	public void log(String logName, Exception exception) {
		INSTANCE.log(logName, "", exception);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ILog#log(java.lang.String, java.lang.String, java.lang.Exception)
	 */
	@Override
	public void log(String logName, String msg, Exception exception) {
		LogElement logElement = LoggerUtils.getInstance().getLogElement("ERR", logName, EX + " : " + exception.getMessage());
		logElement.setException(exception);

		System.err.println(logElement.getLineMsg());

		if (LOG_CONSOLE) {
			printConsole(logElement);
		}

		if (!LOG_ENABLED) {
			return;
		}
		if (LOG_EVENT.equals(ELogEvent.BOTH.getValue()) || LOG_EVENT.equals(ELogEvent.EXCEPTIONS.getValue())) {

			logger = LoggerUtils.getInstance().getLogger(logName);

			logger.log(Level.WARNING, msg, exception);
		}
	}
}
