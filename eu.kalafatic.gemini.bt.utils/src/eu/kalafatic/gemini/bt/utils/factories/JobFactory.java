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
package eu.kalafatic.gemini.bt.utils.factories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import eu.kalafatic.gemini.bt.utils.wizards.AUtilSyncWizard;
import eu.kalafatic.gemini.bt.utils.wizards.pages.SyncSelectPage;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.interfaces.ESyncType;
import eu.kalafatic.gemini.core.lib.EDateFormat;
import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.utils.DBUtils;
import eu.kalafatic.gemini.core.utils.DBUtils.DB;
import eu.kalafatic.gemini.core.utils.DBUtils.EDBTorrents;
import eu.kalafatic.gemini.core.utils.DateUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.wizards.SyncWizard;
import eu.kalafatic.gemini.core.wizards.SyncWizard.Progress;

/**
 * A factory for creating Job objects.
 */
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
	public Runnable createSyncJob(AUtilSyncWizard wizard, SyncObject syncObject, ESyncType toType, Progress progress) {
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
	private Runnable createExportSyncJob(final AUtilSyncWizard wizard, final SyncObject syncObject, final ESyncType toType, final Progress progress) {

		return new Runnable() {
			@Override
			public void run() {
				try {
					progress.worked.addAndGet(5);

					switch (syncObject.type) {
					case DEF:
						break;
					case FILE:
						if (toType.equals(ESyncType.DB)) {
							List<DBUtils.DB> dbList = ((SyncSelectPage) wizard.getPages()[2]).dbList;
							exportToDB(syncObject, dbList);
						}
						break;
					case DB:
						break;
					default:
						break;
					}
					progress.worked.addAndGet(5);

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
	private Runnable createImportSyncJob(SyncWizard wizard, SyncObject syncObject, Progress progress) {
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Export to db.
	 * @param syncObject the sync object
	 * @param dbList the db list
	 * @throws Exception the exception
	 */
	private void exportToDB(SyncObject syncObject, List<DB> dbList) throws Exception {
		SQLException sqlException = null;

		for (DB db : dbList) {
			try {
				if (!DBUtils.getInstance().isUnique(db, db.settings.get(DBUtils.DB_URL_PARAMETERS[4]), "name", syncObject.name)) {
					continue;
				}

				Class.forName(db.driver);
				Connection connection = DriverManager.getConnection(db.url);

				PreparedStatement ps = prepareStatement(syncObject, connection);
				ps.executeUpdate();

			} catch (SQLException e) {
				sqlException = e;
				// TODO
			}
		}
		if (sqlException != null) {
			DialogUtils.INSTANCE.showException(sqlException, 20);
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

		File content = (File) syncObject.data;

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, (String) syncObject.parameters.get(EDBTorrents.CATEGORY.columnName));
		ps.setString(2, syncObject.name);
		ps.setString(3, DateUtils.decodeTime(System.currentTimeMillis(), EDateFormat.BASIC));

		ps.setInt(4, (int) content.length());
		ps.setInt(5, 0);
		ps.setInt(6, 0);

		InputStream is = new FileInputStream(content);
		ps.setBinaryStream(7, is, (int) (content.length()));

		return ps;
	}
}
