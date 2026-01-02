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
package eu.kalafatic.gemini.bt.client.view.views;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.SESSION_RATING_COMPARATOR;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.COMMENT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CREATOR_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.DATE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ENCODING_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.INFO_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.LOCK_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.PIECES_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.SIZE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.URL_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;

import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.utils.ConvertUtils;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.utils.HexCodec;

/**
 * The Class class TorrentDetailView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TorrentDetailView extends AViewer implements ITable {

	/** The old ext torrent. */
	private ExtTorrent extTorrent, oldExtTorrent;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The properties table. */
	private Table propertiesTable;

	/** The Constant SHIFT. */
	private static final int SHIFT = 6;

	/** The old model bitfield. */
	private boolean[] oldModelBitfield;

	/** The processed pieces. */
	private EMap<Integer, IOPiece> processedPieces;

	/** The files view. */
	private TorrentDetailFilesView filesView;

	/** The form. */
	private SashForm form;

	/**
	 * Instantiates a new torrent detail view.
	 */
	public TorrentDetailView() {
		setTitleImage(INFO_IMG);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		// Create the table
		GridLayout gridLayout = new GridLayout();
		// gridLayout.numColumns = 3;
		gridLayout.numColumns = 2;
		GridData gridData = new GridData(GridData.FILL_BOTH);

		form = new SashForm(parent, SWT.FILL);
		form.setLayout(new FillLayout());

		propertiesTable = new Table(form, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		propertiesTable.setHeaderVisible(true);
		propertiesTable.setLinesVisible(true);

		gridData = new GridData();
		gridData.widthHint = 150;
		propertiesTable.setLayoutData(gridData);
		initColumns();
		hookContextMenu();

		getSite().getPage().addSelectionListener(this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		TableColumn tc1 = new TableColumn(propertiesTable, SWT.LEFT);
		TableColumn tc2 = new TableColumn(propertiesTable, SWT.LEFT);

		tc1.setText("Setting (Rating)");
		tc2.setText("Value");
		tc1.setWidth(100);
		tc2.setWidth(200);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#hookContextMenu()
	 */
	@Override
	public void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {}
		});

		// Menu menu = new Menu(fileTable.getShell(), SWT.POP_UP);
		// MenuItem item = new MenuItem(menu, SWT.PUSH);
		// item.setText("Open File");
		// fileTable.setMenu(menu);
		//
		// item.addSelectionListener(new OpenFileListener(getSite()));
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the tables.
	 */
	private void setTables() {
		if (propertiesTable.isDisposed()) {
			return;
		}
		propertiesTable.removeAll();

		String rating = " (0)";
		if (swarmSession != null) {
			rating = "  (" + Integer.toString(swarmSession.getRating()) + ")";
		}

		TableItem torrentItem = new TableItem(propertiesTable, SWT.NONE);
		torrentItem.setText(new String[] { "Name " + rating, extTorrent.getName() });
		torrentItem.setImage(FOLDER_IMG);

		final Info info = extTorrent.getInfo();

		TableItem privateItem = new TableItem(propertiesTable, SWT.NONE);

		privateItem.setText(new String[] { "Private", info.isPrivate() ? "Yes" : "No" });
		privateItem.setImage(LOCK_IMG);

		TableItem piecesItem = new TableItem(propertiesTable, SWT.NONE);
		piecesItem.setText(new String[] { "Pieces", HexCodec.bytesToHexString(info.getHash()) });
		piecesItem.setImage(PIECES_IMG);

		TableItem piecesLenItem = new TableItem(propertiesTable, SWT.NONE);
		piecesLenItem.setText(new String[] { "Piece-length", Long.toString(info.getPieceLength()) });
		piecesLenItem.setImage(SIZE_IMG);

		TableItem creationByItem = new TableItem(propertiesTable, SWT.NONE);
		creationByItem.setText(new String[] { "Creation By", extTorrent.getCreationBy() });
		creationByItem.setImage(CREATOR_IMG);

		long creationDate = extTorrent.getCreationDate();
		String date = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(creationDate);

		TableItem creationDateItem = new TableItem(propertiesTable, SWT.NONE);
		creationDateItem.setText(new String[] { "Creation Date", date });
		creationDateItem.setImage(DATE_IMG);

		TableItem encodingItem = new TableItem(propertiesTable, SWT.NONE);
		encodingItem.setText(new String[] { "Encoding", extTorrent.getEncoding() });
		encodingItem.setImage(ENCODING_IMG);

		TableItem commentItem = new TableItem(propertiesTable, SWT.NONE);
		commentItem.setText(new String[] { "Comment", extTorrent.getComment() });
		commentItem.setImage(COMMENT_IMG);

		createAnnounceItems(extTorrent.getAnnounceList());
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the announce items.
	 * @param list the list
	 */
	private void createAnnounceItems(EList<String> list) {
		String rating = " (0)";
		if (swarmSession != null) {
			rating = "  (" + Integer.toString(swarmSession.getRating()) + ")";
		}
		List<Session> trackers = new ArrayList<Session>(swarmSession.getTrackers().values());
		Collections.sort(trackers, SESSION_RATING_COMPARATOR);

		for (Session session : trackers) {
			rating = "  (" + Integer.toString(session.getRating()) + ")";

			TableItem announceItem = new TableItem(propertiesTable, SWT.NONE);
			announceItem.setText(new String[] { "Tracker" + rating, session.getAnnounce() });
			announceItem.setImage(URL_IMG);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the progress columns.
	 * @param info the info
	 */
	public void createProgressColumns(final Info info) {
		Table table = (Table) filesView.getViewer().getControl();

		if (table.getColumnCount() < (info.getMaxPiecesPerFile() + SHIFT)) {
			createProgressColumns(table, (info.getMaxPiecesPerFile() + SHIFT) - (table.getColumnCount()));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the progress columns.
	 * @param table the table
	 * @param size the size
	 */
	private void createProgressColumns(Table table, int size) {
		for (int i = 0; i < size; i++) {
			TableColumn tc = new TableColumn(table, SWT.LEFT);
			tc.setWidth(30);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			if (structuredSelection.getFirstElement() instanceof Entry) {
				Entry<?, ?> entry = (Entry<?, ?>) structuredSelection.getFirstElement();

				if (entry.getValue() instanceof ExtTorrent) {
					extTorrent = (ExtTorrent) entry.getValue();

					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							processSelection();
						}
					});
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process selection.
	 */
	private void processSelection() {
		swarmSession = (SwarmSession) NetworkModelManager.getInstance().getClientNetwork().getSwarmMap().get(extTorrent.getName());

		if (swarmSession == null) {
			return;
		}

		if (filesView == null) {
			setTables();

			filesView = new TorrentDetailFilesView(getSite(), extTorrent, swarmSession);
			filesView.createPartControl(form);
			createProgressColumns(extTorrent.getInfo());
			createCheckColumn(extTorrent.getInfo().getFiles());

			form.setWeights(new int[] { 150, 350 });
			form.layout(true);
		} else {
			if (isNecessary()) {
				setTables();

				filesView.setExtTorrent(extTorrent);
				filesView.setSwarmSession(swarmSession);
				filesView.getFilesTableLabelProvider().setExtTorrent(extTorrent);
				filesView.getFilesTableLabelProvider().setSwarmSession(swarmSession);
				filesView.getViewer().setInput(extTorrent.getInfo().getFiles());

				createProgressColumns(extTorrent.getInfo());
				createCheckColumn(extTorrent.getInfo().getFiles());

				// form.setWeights(new int[] { 150, 350 });
				form.layout(true);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the check column.
	 * @param files the files
	 */
	private void createCheckColumn(EList<DataFile> files) {
		Table table = ((TableViewer) filesView.getViewer()).getTable();

		for (int i = 0; (i < files.size()) && (i < table.getItems().length); i++) {
			table.getItem(i).setChecked(files.get(i).isEnabled());

			if (files.get(i).isEnabled()) {
				table.getItem(i).setForeground(BLACK);
			} else {
				table.getItem(i).setForeground(RED);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is necessary.
	 * @return true, if is necessary
	 */
	private boolean isNecessary() {
		if (extTorrent == null || swarmSession == null) {
			return false;
		}
		boolean[] modelBitfield = extTorrent.getModelBitfield();
		// lazy set up finished torrent
		if (modelBitfield == null) {
			ConvertUtils.INSTANCE.setModelBitfield(extTorrent);
			return true;
		}
		// first selection or another torrent selected
		if ((oldExtTorrent == null) || (!oldExtTorrent.equals(extTorrent))) {
			oldExtTorrent = extTorrent;
			oldModelBitfield = Arrays.copyOf(modelBitfield, modelBitfield.length);
			processedPieces = swarmSession.getProcessedPieces();
			return true;
		}
		// the same torrent with different bitfield
		if (!Arrays.equals(oldModelBitfield, modelBitfield)) {
			oldModelBitfield = Arrays.copyOf(modelBitfield, modelBitfield.length);
			return true;
		}

		if (!processedPieces.entrySet().equals(swarmSession.getProcessedPieces().entrySet())) {
			return true;
		}

		for (IOPiece piece1 : swarmSession.getProcessedPieces().values()) {
			String key = Integer.toString(piece1.getPieceIndex());
			IOPiece piece2 = processedPieces.get(key);

			if (piece2 == null) {
				return true;
			}
			if (!Arrays.equals(piece1.getBlocks(), piece2.getBlocks())) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Contains.
	 * @param array the array
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean contains(boolean[] array, boolean value) {
		for (boolean b : array) {
			if (b == value) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable#getTable()
	 */
	@Override
	public Table getTable() {
		return filesView.getTable();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable#getExtTorrent ()
	 */
	@Override
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IViewer#getViewer ()
	 */
	@Override
	public Viewer getViewer() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	protected void fillLocalToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillLocalPullDown(IMenuManager menuManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		// TODO Auto-generated method stub

	}
}