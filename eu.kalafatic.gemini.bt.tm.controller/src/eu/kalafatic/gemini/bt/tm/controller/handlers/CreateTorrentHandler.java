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
package eu.kalafatic.gemini.bt.tm.controller.handlers;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.progress.IProgressConstants;
import org.osgi.framework.Version;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.Activator;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.controller.utils.CMDUtils;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.bt.utils.decoders.TEncoder;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.lib.EHash;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.HashUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class CreateTorrentHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings("restriction")
public class CreateTorrentHandler extends AbstractHandler {

	/** The add to session. */
	boolean addToSession;

	/** The window. */
	private IWorkbenchWindow window;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The hash buffer. */
	private ByteBuffer hashBuffer;

	/** The finished. */
	private boolean finished;

	/** The file length. */
	private long fileLength;

	/** The piece length. */
	private int pieceLength;

	/** The pieces count. */
	private int piecesCount;

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.tm.controller.torrentNameParameter";

	/** The PERSPECTIV e_ tm. */
	private final String PERSPECTIVE_TM = "eu.kalafatic.gemini.bt.tm.view.perspectives.TMBasePerspective";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			String nameParameter = event.getParameter(PARAMETER_1);

			window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			checkTMPerspectiveOpened();
			PlatformUI.getWorkbench().getProgressService().run(true, true, getWorker(nameParameter));
			executeOtherActions();

		} catch (InvocationTargetException e) {
			Log.log(ETMPreferences.MODULE, e);
		} catch (InterruptedException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the worker.
	 * @param nameParameter the name parameter
	 * @return the worker
	 */
	private IRunnableWithProgress getWorker(final String nameParameter) {
		return new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					monitor.beginTask("Generating Torrent ...", 100);
					String message = generateTorrent(monitor, nameParameter);
					DialogUtils.INSTANCE.info(message);

				} catch (Exception e) {
					Log.log(ETMPreferences.MODULE, e);
				} finally {
					monitor.done();
				}
			}
		};
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is modal.
	 * @param job the job
	 * @return true, if is modal
	 */
	public boolean isModal(Job job) {
		Boolean isModal = (Boolean) job.getProperty(IProgressConstants.PROPERTY_IN_DIALOG);
		if (isModal == null) {
			return false;
		}
		return isModal.booleanValue();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @param name the name
	 * @throws Exception the exception
	 */
	private void init(String name) throws Exception {
		BTStructure btStructure = BTStructureModelManager.getInstance().getBtStructure();

		EMap<String, FileTreeObject> fileMap = btStructure.getTreeObjects().get(name).getChildMap();

		if (fileMap.isEmpty()) {
			throw new Exception("No file !");
		}
		this.extTorrent = btStructure.getNewTorrents().get(name);
		if (extTorrent.getCreationDate() == 0) {
			extTorrent.setCreationDate(System.currentTimeMillis());
		}
		if (extTorrent.getCreationBy() == null) {
			String product = Platform.getProduct().getName();
			Version version = Version.parseVersion(Platform.getProduct().getDefiningBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION));

			extTorrent.setCreationBy(product + " v" + version);
		}
		if (extTorrent.getEncoding() == null) {
			extTorrent.setEncoding(EEncoding.ISO_1.getLiteral());
		}
		if (extTorrent.getComment() == null) {
			extTorrent.setComment("TM Gemini");
		}
		if (extTorrent.getInfo().getPieceLength() == 0) {
			extTorrent.getInfo().setPieceLength(2 << 14);
		}
		this.pieceLength = extTorrent.getInfo().getPieceLength();
		this.finished = false;

		computeAllFilesSize(fileMap);

		if (!extTorrent.getPath().endsWith(".torrent")) {
			String torrents = PREFERENCES.get(ECorePreferences.TORRENTS_LOC.getName(), (String) ECorePreferences.TORRENTS_LOC.getDef());
			extTorrent.setPath(torrents.concat(File.separator).concat(extTorrent.getName()));
		}
		File rootFile = new File(extTorrent.getPath());

		if (!rootFile.exists()) {
			rootFile.createNewFile();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Generate torrent.
	 * @param monitor the monitor
	 * @param name the name
	 * @return the string
	 * @throws Exception the exception
	 */
	private String generateTorrent(final IProgressMonitor monitor, String name) throws Exception {
		try {
			init(name);

			createPieces();
			addTrackers();

			// monitor.beginTask("Generating Torrent ...",
			// (int) ((fileLength / pieceLength) + 1));
			// statusLineManager.update(true);

			TEncoder.INSTANCE.encodeAndWrite(extTorrent);

			return "Torrent created";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Torrent created";
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the trackers.
	 * @throws Exception the exception
	 */
	private void addTrackers() throws Exception {
		Collection<Tracker> values = BTStructureModelManager.getInstance().getBtStructure().getTrackersMap().values();
		for (Tracker tracker : values) {
			if (tracker.isChecked()) {
				extTorrent.getAnnounceList().add(tracker.getAnnounce());
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Compute all files size.
	 * @param fileMap the file map
	 * @throws Exception the exception
	 */
	private void computeAllFilesSize(EMap<String, FileTreeObject> fileMap) throws Exception {
		this.fileLength = 0;
		Iterator<FileTreeObject> iterator = fileMap.values().iterator();

		while (iterator.hasNext()) {
			FileTreeObject fileTreeObject = iterator.next();

			if (fileTreeObject.isFile()) {
				File file = new File(fileTreeObject.getPath());
				fileLength += file.length();
			} else {
				computeAllFilesSize(fileTreeObject.getChildMap());
			}
		}
		extTorrent.getAdditionalInfo().setFileSize(fileLength);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the pieces.
	 * @throws Exception the exception
	 */
	private void createPieces() throws Exception {
		if ((fileLength % pieceLength) > 0) {
			piecesCount = (int) ((fileLength / pieceLength) + 1);
		} else {
			piecesCount = (int) (fileLength / pieceLength);
			if (piecesCount == 0) {
				piecesCount = 1;
			}
		}
		hashBuffer = ByteBuffer.allocateDirect(piecesCount * 20);

		createHash(0, ByteBuffer.allocateDirect(pieceLength));

		byte[] pieces = new byte[piecesCount * 20];

		hashBuffer.flip();
		hashBuffer.get(pieces);

		extTorrent.getInfo().setPieces(pieces);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the hash.
	 * @param fileIndex the file index
	 * @param rest the rest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void createHash(int fileIndex, ByteBuffer rest) throws IOException {
		byte[] bytes;

		if (fileIndex == extTorrent.getInfo().getFiles().size()) { // finished
			if (rest.position() > 0) {
				int size = rest.position();
				rest.flip();
				bytes = new byte[size];
				rest.get(bytes);
				generateHash(bytes);
			}
			finished = true;
			return;
		}
		DataFile dataFile = extTorrent.getInfo().getFiles().get(fileIndex);
		File file = new File(dataFile.getPath());
		long offset = 0;
		long len = file.length();

		if (rest.position() > 0) {
			int read = pieceLength - rest.position();

			if (len < read) {
				read = (int) len;
			}

			bytes = readUntil(file, 0, read);

			rest.put(bytes);

			if (rest.position() < pieceLength) {
				createHash(fileIndex + 1, rest);
			} else {
				rest.flip();
				bytes = new byte[pieceLength];
				rest.get(bytes);

				generateHash(bytes);
				offset = read;
			}
		}
		// stop recursion
		if (finished) {
			return;
		}

		bytes = new byte[pieceLength];

		// here is clear state
		while ((offset + pieceLength) <= len) {

			bytes = readUntil(file, offset, pieceLength);
			generateHash(bytes);
			offset += pieceLength;
		}

		// rest of the file ( less then pieceLength )
		rest = ByteBuffer.allocateDirect(pieceLength);

		bytes = readUntil(file, offset, (int) (len - offset));
		rest.put(bytes);
		createHash(fileIndex + 1, rest);
	}

	// ---------------------------------------------------------------

	/**
	 * Generate hash.
	 * @param bytes the bytes
	 */
	private void generateHash(byte[] bytes) {
		byte[] hash = HashUtils.getInstance().getHash(bytes, EHash.SHA_1);
		hashBuffer.put(hash);
	}

	// ---------------------------------------------------------------

	/**
	 * Execute other actions.
	 */
	private void executeOtherActions() {
		boolean addToClient = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_CLIENT.getName(), (Boolean) ETMPreferences.SYNC_CLIENT.getValue());

		if (addToClient) {
			CMDUtils.getInstance().synchronizeWith(CMDUtils.CLIENT, extTorrent);
		}

		boolean uploadToTrackers = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_TRACKER.getName(), (Boolean) ETMPreferences.SYNC_TRACKER.getValue());

		if (uploadToTrackers) {
			CMDUtils.getInstance().synchronizeWith(CMDUtils.TRACKER, extTorrent);
		}

		boolean uploadToWeb = Activator.getPreferences().getBoolean(ETMPreferences.SYNC_WEB.getName(), (Boolean) ETMPreferences.SYNC_WEB.getValue());

		if (uploadToWeb) {
			// CMDUtils.getInstance().synchronizeWith(CMDUtils.WEB, extTorrent,
			// buildTorrent.toString());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check tm perspective opened.
	 */
	private void checkTMPerspectiveOpened() {
		try {
			final IWorkbenchPage activePage = window.getActivePage();

			IPerspectiveDescriptor desc = window.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(PERSPECTIVE_TM);

			if (activePage != null) {
				activePage.setPerspective(desc);
			} else {
				IAdaptable input = ((Workbench) window.getWorkbench()).getDefaultPageInput();
				window.openPage(PERSPECTIVE_TM, input);
			}
		} catch (WorkbenchException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Read until.
	 * @param file the file
	 * @param offset the offset
	 * @param read the read
	 * @return the byte[]
	 */
	private byte[] readUntil(File file, long offset, int read) {
		byte[] bytes = new byte[read];
		RandomAccessFile raf = null;
		try {
			file.setReadable(true);
			raf = new RandomAccessFile(file, "r");
			raf.seek(offset);
			raf.read(bytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}
