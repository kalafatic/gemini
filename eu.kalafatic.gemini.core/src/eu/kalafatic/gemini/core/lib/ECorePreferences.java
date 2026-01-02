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
package eu.kalafatic.gemini.core.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.core.interfaces.IPreference;
import eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants;

/**
 * The Enum enum ECorePreferences.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum ECorePreferences implements IPreference {

	/** The set. */
	SET("Set", false, false),

	/** The module. */
	MODULE("Core", "", ""),

	// APLICATION
	/** The app name. */
	APP_NAME("Application name", "Gemini", "Gemini"),

	/** The app id. */
	APP_ID("Application ID", "-GE2010-000000000000", "-GE2010-000000000000"),
	// APP_ID(3, "Application ID", "M2-0-1--000000000000",
	// "M2-0-1--000000000000"),

	// LOCATIONS
	/** The workspace loc. */
	WORKSPACE_LOC("Workspace location", "C:\\GE\\workspace", "C:\\GE\\workspace"),

	/** The models loc. */
	MODELS_LOC("Models location", "C:\\GE\\workspace\\models", "C:\\GE\\workspace\\models"),

	/** The logs loc. */
	LOGS_LOC("Logs location", "C:\\GE\\workspace\\logs", "C:\\GE\\workspace\\logs"),

	/** The torrents loc. */
	TORRENTS_LOC("Torrents location", "C:\\GE\\workspace\\torrents", "C:\\GE\\workspace\\torrents"),

	/** The downloads loc. */
	DOWNLOADS_LOC("Downloads location", "C:\\GE\\workspace\\downloads", "C:\\GE\\workspace\\downloads"),

	/** The site loc. */
	SITE_LOC("Site location", "C:\\GE\\site", "C:\\GE\\site"),

	/** The nl loc. */
	NL_LOC("Site location", "C:\\GE\\nl", "C:\\GE\\nl"),

	/** The cert loc. */
	CERT_LOC("App certificates location", "C:\\GE\\certificates", "C:\\GE\\certificates"),

	/** The jdk cert loc. */
	JDK_CERT_LOC("JDK cacerts certificates location", "", ""),

	/** The jre cert loc. */
	JRE_CERT_LOC("JRE cacerts certificates location", "", ""),

	/** The cert enabled. */
	CERT_ENABLED("Certificates loaded (lazy)", false, false),

	/** The javadoc loc. */
	JAVADOC_LOC("Javadoc location", "C:\\GE\\javadoc", "C:\\GE\\javadoc"),

	/** The updates loc. */
	UPDATES_LOC("Updates location", "C:\\GE\\updates", "C:\\GE\\updates"),

	/** The maintain loc. */
	MAINTAIN_LOC("Maintain location", "C:\\GE\\maintain", "C:\\GE\\maintain"),

	// SYSTEM
	/** The cpu name. */
	CPU_NAME("CPU Name", "", ""),

	/** The cpu speed. */
	CPU_SPEED("CPU Speed", "", ""),

	/** The os name. */
	OS_NAME("OS Name", "", ""),

	/** The os arch. */
	OS_ARCH("OS Architecture", "", ""),

	// MEMORY
	/** The max app memory. */
	MAX_APP_MEMORY("Max memory (MB)", 5, 5),

	/** The app memory. */
	APP_MEMORY("Application memory (MB)", 32, 32),

	/** The disc buffer. */
	DISC_BUFFER("Disc write buffer (MB)", 64, 64),

	// JAVA
	/** The java version. */
	JAVA_VERSION("Java version", "", ""),

	// NETWORK
	/** The ip protocol. */
	IP_PROTOCOL("IP version ", 4, 4),

	/** The singleton args port. */
	SINGLETON_ARGS_PORT("Singleton arguments port", 6880, 6880),

	/** The rc port. */
	RC_PORT("Remote control port", 6881, 6881),

	/** The bt client port. */
	BT_CLIENT_PORT("BT client port", 6882, 6882),

	/** The tracker port. */
	TRACKER_PORT("Tracker port", 6883, 6883),

	/** The tracker upload port. */
	TRACKER_UPLOAD_PORT("Tracker upload port", 6884, 6884),

	/** The update port. */
	UPDATE_PORT("Update port", 6885, 6885),

	/** The web upload port. */
	WEB_UPLOAD_PORT("Web upload port", 8000, 8000),

	/** The ping address. */
	PING_ADDRESS("Ping address", "http://www.google.com:80", "http://www.google.com:80"),

	/** The watchdog delay. */
	WATCHDOG_DELAY("Watchdog delay (s)", 10, 10),

	/** The ping enabled. */
	PING_ENABLED("Ping enabled", ERemote.DISABLED.literal, ERemote.DISABLED.literal),

	/** The proxy set. */
	PROXY_SET("Proxy", false, false),

	/** The proxy host. */
	PROXY_HOST("Host", "", ""),

	/** The proxy port. */
	PROXY_PORT("Port", 8080, 8080),

	/** The proxy user. */
	PROXY_USER("User", "", ""),

	/** The proxy pass. */
	PROXY_PASS("Password", "", ""),

	// OS INTEGRATION
	/** The os integration. */
	OS_INTEGRATION("OS Integration", "", ""),

	/** The extension. */
	EXTENSION("Extension", "", ""),

	/** The program. */
	PROGRAM("Program", "", ""),

	/** The associated. */
	ASSOCIATED("Associated", "", ""),

	// ADDRESS
	/** The home site. */
	HOME_SITE("Home", "http://kalafatic.eu", "http://kalafatic.eu"),

	/** The update site. */
	UPDATE_SITE("Update", "http://gemini.kalafatic.eu/Pages/Update/index.html", "http://gemini.kalafatic.eu/Pages/Update/index.html"),

	/** The bug site. */
	BUG_SITE("Bugs", "http://bugs.kalafatic.eu", "http://bugs.kalafatic.eu"),

	/** The mail site. */
	MAIL_SITE("Mail", "http://post.kalafatic.eu", "http://post.kalafatic.eu"),

	// CUSTOMIZE
	/** The log console. */
	LOG_CONSOLE("Enable console output", true, true),

	/** The log enabled. */
	LOG_ENABLED("Enable logging", false, false),

	/** The log type. */
	LOG_TYPE("Log type", ELogType.TXT.getValue(), ELogType.TXT.getValue()),

	/** The log event. */
	LOG_EVENT("Log event", ELogEvent.BOTH.getValue(), ELogEvent.BOTH.getValue()),

	/** The log size. */
	LOG_SIZE("Max size (kB)", 100, 100),

	/** The check after. */
	CHECK_AFTER("Check log size\n(after x inputs)", 30, 30),

	/** The max logs. */
	MAX_LOGS("Max logs", 64, 64),

	/** The thread management. */
	THREAD_MANAGEMENT("Deadlocks management enabled", false, false),

	/** The cpu management. */
	CPU_MANAGEMENT("CPU management enabled", true, true),

	/** The max cpu. */
	MAX_CPU("Max CPU usage (%)", 50, 50),

	/** The sound. */
	SOUND("Sound enabled", false, false),

	/** The run bt after start. */
	RUN_BT_AFTER_START("Run BT after application start", false, false),

	/** The rc enabled. */
	RC_ENABLED("Enable RC", true, true),

	/** The program loc. */
	PROGRAM_LOC("Program location", "C:\\GE\\Gemini\\Gemini.exe", "C:\\GE\\Gemini\\Gemini.exe"),

	/** The db torrennts query. */
	DB_TORRENNTS_QUERY("DB torrents query", "7-select name from torrents", "7-select name from torrents"),

	// SYNCHRONIZATION
	/** The sync client. */
	SYNC_CLIENT("Sync with client", true, true),

	/** The sync files. */
	SYNC_FILES("Sync with local files", true, true),

	/** The sync db. */
	SYNC_DB("Sync with DB", true, true),

	/** The subfolders. */
	SUBFOLDERS("Subfolders", true, true),

	/** The language. */
	LANGUAGE("Language", Locale.ENGLISH.getLanguage(), Locale.ENGLISH.getLanguage()),

	/** The languages. */
	LANGUAGES("Languages", new ArrayList<Object[]>(), new ArrayList<Object[]>())

	;

	/** The locales. */
	private static List<Object[]> locales;

	/** The langs. */
	private static String[] langs = { "cs", "en", "de", "zh", "ru", "ru" };

	/**
	 * Gets the locale.
	 * @return the locale
	 */
	private List<Object[]> getLocale() {
		if (locales == null) {
			locales = new ArrayList<Object[]>();
			int i = 0;
			locales.add(new Object[] { langs[i++], FCoreImageConstants.CS_IMG });
			locales.add(new Object[] { langs[i++], FCoreImageConstants.EN_IMG });
			locales.add(new Object[] { langs[i++], FCoreImageConstants.DE_IMG });
			locales.add(new Object[] { langs[i++], FCoreImageConstants.ZH_IMG });
			locales.add(new Object[] { langs[i++], FCoreImageConstants.RU_IMG });
			locales.add(new Object[] { langs[i++], FCoreImageConstants.ES_IMG });
		}
		return locales;
	}

	/**
	 * Gets the locale index.
	 * @param lang the lang
	 * @return the locale index
	 */
	public static int getLocaleIndex(String lang) {
		for (int i = 0; i < langs.length; i++) {
			if (langs[i].equals(lang)) {
				return i;
			}
		}
		return 0;
	}

	/** The name. */
	private String name;

	/** The def. */
	private Object value, def;

	/**
	 * Instantiates a new e core preferences.
	 * @param name the name
	 * @param value the value
	 * @param def the def
	 */
	private ECorePreferences(String name, final Object value, final Object def) {
		this.name = name;
		this.value = value;
		this.def = def;

		if (name.equals("Languages")) {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					ECorePreferences.LANGUAGES.value = getLocale();
					ECorePreferences.LANGUAGES.def = getLocale();
				}
			});
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the name.
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getValue()
	 */
	@Override
	public Object getValue() {
		return value;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the value.
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IPreference#getDef()
	 */
	@Override
	public Object getDef() {
		return def;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the def.
	 * @param def the new def
	 */
	public void setDef(Object def) {
		this.def = def;
	}
}
