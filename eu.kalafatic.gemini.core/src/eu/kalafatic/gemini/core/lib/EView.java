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

/**
 * The Enum enum EView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public enum EView {

	/** The console. */
	CONSOLE("eu.kalafatic.gemini.core.views.ConsoleView"),

	/** The progress. */
	PROGRESS("org.eclipse.ui.views.ProgressView"),

	/** The errors. */
	ERRORS("org.eclipse.pde.runtime.LogView"),

	/** The workspace. */
	WORKSPACE("eu.kalafatic.gemini.core.views.WorkspaceView"),

	/** The file navigator. */
	FILE_NAVIGATOR("eu.kalafatic.gemini.core.views.FileNavigatorView"),

	/** The swarms. */
	SWARMS("eu.kalafatic.gemini.bt.tracker.view.views.SwarmsView"),

	/** The swarms graph. */
	SWARMS_GRAPH("eu.kalafatic.gemini.bt.client.net.view.views.BTGraphView"),

	/** The swarms activity. */
	SWARMS_ACTIVITY("eu.kalafatic.gemini.bt.client.net.view.views.BTSwarmActivityView"),

	/** The swarms tree. */
	SWARMS_TREE("eu.kalafatic.gemini.bt.client.net.view.views.SwarmTreeView"),

	/** The tracker. */
	TRACKER("eu.kalafatic.gemini.bt.tracker.view.views.TrackerView"),

	/** The torrents table. */
	TORRENTS_TABLE("eu.kalafatic.gemini.bt.client.view.views.TorrentsTableView"),

	/** The finished torrents table. */
	FINISHED_TORRENTS_TABLE("eu.kalafatic.gemini.bt.client.view.views.FinishedTorrentsTableView"),

	/** The torrent detail. */
	TORRENT_DETAIL("eu.kalafatic.gemini.bt.client.view.views.TorrentDetailView"),

	/** The session detail. */
	SESSION_DETAIL("eu.kalafatic.gemini.bt.client.net.view.views.SessionDetailView"),

	/** The session property. */
	SESSION_PROPERTY("eu.kalafatic.gemini.bt.tracker.view.views.SessionPropertyView"),

	/** The video player. */
	VIDEO_PLAYER("eu.kalafatic.gemini.media.view.views.VideoPlayerView"),

	/** The browser. */
	BROWSER("eu.kalafatic.gemini.webbrowser.view.views.WebBrowserView"),

	/** The browser navigator. */
	BROWSER_NAVIGATOR("eu.kalafatic.gemini.webbrowser.view.views.WebBrowserNavigator"),

	/** The pref algorithms. */
	PREF_ALGORITHMS("eu.kalafatic.gemini.bt.client.view.preferences.AlgorithmsPreferencePage"),

	/** The rc. */
	RC("eu.kalafatic.gemini.bt.client.net.rc.view.views.RCView"),

	/** The rc navigator. */
	RC_NAVIGATOR("eu.kalafatic.gemini.bt.client.net.rc.view.views.RCNavigator"),

	/** The statistics. */
	STATISTICS("eu.kalafatic.gemini.stat.view.views.StatisticsView"),

	/** The statistics navigator. */
	STATISTICS_NAVIGATOR("eu.kalafatic.gemini.stat.view.views.StatTreeView"),

	;

	/** The id. */
	public String ID;

	/**
	 * Instantiates a new e view.
	 * @param id the id
	 */
	private EView(String id) {
		this.ID = id;
	}
}
