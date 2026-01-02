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
package eu.kalafatic.gemini.bt.tracker.controller.factories;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.ITrackerSyncWizard;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.controller.utils.TrackerUtils;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.ATrackerSyncWizard;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.interfaces.ESyncType;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EDateFormat;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DBUtils.DB;
import eu.kalafatic.gemini.core.utils.DateUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.wizards.SyncWizard.Progress;

/**
 * A factory for creating Job objects.
 */
@SuppressWarnings("unchecked")
public class JobFactory {

	/** The instance. */
	private volatile static JobFactory INSTANCE;

	/**
	 * Gets the single instance of JobFactory.
	 * @return single instance of JobFactory
	 */
	public static JobFactory getInstance() {
		if (INSTANCE == null) {
			synchronized (JobFactory.class) {
				INSTANCE = new JobFactory();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates a new Job object.
	 * @param wizard the wizard
	 * @param syncObject the sync object
	 * @param toType the to type
	 * @param progress the progress
	 * @return the runnable
	 */
	public Runnable createSyncJob(ATrackerSyncWizard wizard, SyncObject syncObject, ESyncType toType, Progress progress) {
		if (wizard.export) {
			return createExportSyncJob(wizard, syncObject, toType, progress);
		} else {
			return createImportSyncJob(wizard, syncObject, progress);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Job object.
	 * @param wizard the wizard
	 * @param syncObject the sync object
	 * @param toType the to type
	 * @param progress the progress
	 * @return the runnable
	 */
	private Runnable createExportSyncJob(final ATrackerSyncWizard wizard, final SyncObject syncObject, final ESyncType toType, final Progress progress) {

		return new Runnable() {
			@Override
			public void run() {
				try {
					progress.worked.addAndGet(5);

					switch (toType) {
					case DEF:
						ExtTorrent extTorrent = wizard.getExtTorrent(syncObject);
						CommandFactory.INSTANCE.executeCommand(ITrackerSyncWizard.ADD_CLIENT_TORRENT_CMD, extTorrent.getPath(), Boolean.toString(extTorrent.isFinished()));
						break;
					case FILE:
						File file = (File) syncObject.data;
						file.createNewFile();
						break;
					case DB:
						exportToDB(syncObject);
						break;
					default:
						break;
					}

					progress.worked.addAndGet(5);
					progress.done.addAndGet(1);

					syncObject.FLAGS |= ASync.ORIGINAL;

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					progress.done.incrementAndGet();
				}
			}
		};
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Job object.
	 * @param wizard the wizard
	 * @param syncObject the sync object
	 * @param progress the progress
	 * @return the runnable
	 */
	public Runnable createImportSyncJob(final ATrackerSyncWizard wizard, final SyncObject syncObject, final Progress progress) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					progress.worked.addAndGet(5);
					File file = null;

					switch (syncObject.type) {
					case DEF:
						ExtTorrent extTorrent = wizard.getExtTorrent(syncObject);
						file = new File(extTorrent.getPath());
						break;
					case FILE:
						file = (File) syncObject.data;
						break;
					case DB:
						file = importFromDB(syncObject);
						break;
					default:
						break;
					}
					if (file != null) {
						executeImport(syncObject, file);
					}
					progress.worked.addAndGet(5);
					progress.done.addAndGet(1);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					progress.done.incrementAndGet();
				}
			}
		};
	}

	// ---------------------------------------------------------------

	/**
	 * Export to db.
	 * @param syncObject the sync object
	 * @throws Exception the exception
	 */
	private void exportToDB(SyncObject syncObject) throws Exception {

		List<DB> dbList = syncObject.to.get(ESyncType.DB);

		for (DB db : dbList) {
			try {
				Class.forName(db.driver);
				Connection connection = DriverManager.getConnection(db.url);

				PreparedStatement ps = prepareStatement(syncObject, connection);
				ps.executeUpdate();

			} catch (SQLException e) {
				DialogUtils.INSTANCE.showException(e, 15);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Prepare statement.
	 * @param syncObject the sync object
	 * @param connection the connection
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 * @throws FileNotFoundException the file not found exception
	 */
	private PreparedStatement prepareStatement(SyncObject syncObject, Connection connection) throws SQLException, FileNotFoundException {
		String query = new String("insert into torrents values (?,?,?,?,?,?,?)");

		TorrentSession torrentSession = (TorrentSession) TrackerModelManager.getInstance().getSessionByName(syncObject.name);

		File content = new File(torrentSession.getTorrentPath());

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, (String) syncObject.parameters.get(DBUtils.EDBTorrents.CATEGORY.columnName));
		ps.setString(2, syncObject.name);
		ps.setString(3, DateUtils.decodeTime(System.currentTimeMillis(), EDateFormat.BASIC));

		ps.setInt(4, (int) content.length());
		ps.setInt(5, TrackerUtils.INSTANCE.getClients(torrentSession.getClientMap(), true));
		ps.setInt(6, TrackerUtils.INSTANCE.getClients(torrentSession.getClientMap(), false));

		InputStream is = new FileInputStream(content);
		ps.setBinaryStream(7, is, (int) (content.length()));

		return ps;
	}

	// ---------------------------------------------------------------

	/**
	 * Import from db.
	 * @param syncObject the sync object
	 * @return the file
	 * @throws Exception the exception
	 */
	private File importFromDB(final SyncObject syncObject) throws Exception {

		DBUtils.DB db = (DBUtils.DB) syncObject.data;
		Class.forName(db.driver);
		Connection connection = DriverManager.getConnection(db.url);

		StringBuffer query = new StringBuffer("select content from torrents where name=\"");
		query.append(syncObject.name);
		query.append("\"");

		List<String> result = DBUtils.getInstance().execute(connection, query.toString(), 1);

		final String path = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());

		return FileUtils.getInstance().createFile(syncObject.name, path, result.get(0));
	}

	// ---------------------------------------------------------------

	/**
	 * Execute import.
	 * @param syncObject the sync object
	 * @param file the file
	 */
	private void executeImport(SyncObject syncObject, File file) {
		List<String> parameters = new ArrayList<String>();

		parameters.add(file.getAbsolutePath());

		addClientsParameter(syncObject, parameters);

		CommandFactory.INSTANCE.executeCommand(ITrackerSyncWizard.IMPORT_TRACKER_TORRENT_CMD, parameters.toArray(new String[parameters.size()]));
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the clients parameter.
	 * @param syncObject the sync object
	 * @param parameters the parameters
	 */
	private void addClientsParameter(SyncObject syncObject, List<String> parameters) {
		if (syncObject.parameters.containsKey("swarmClients")) {
			Set<String> swarmClients = (Set<String>) syncObject.parameters.get("swarmClients");

			StringBuffer ipPorts = new StringBuffer();
			for (String client : swarmClients) {
				ipPorts.append(client + ",");
			}
			parameters.add(ipPorts.deleteCharAt(ipPorts.lastIndexOf(",")).toString());
		}
	}
}
