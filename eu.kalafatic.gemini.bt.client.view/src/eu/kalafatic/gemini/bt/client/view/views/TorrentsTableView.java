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

import static eu.kalafatic.gemini.core.lib.constants.FTableConstants.VIEWER_DATA_TYPE;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;

import eu.kalafatic.gemini.bt.client.controller.adapters.TorrentTableAdapter;
import eu.kalafatic.gemini.bt.client.controller.adapters.TorrentTableContentAdapter;
import eu.kalafatic.gemini.bt.client.controller.dnd.TorrentViewerDropListener;
import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.controller.providers.ColumnSorterProvider;
import eu.kalafatic.gemini.bt.client.controller.providers.TorrentTableContentProvider;
import eu.kalafatic.gemini.bt.client.controller.providers.TorrentTableLabelProvider;
import eu.kalafatic.gemini.bt.client.controller.utils.TorrentTableUtil;
import eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.view.listeners.TorrentTableListener;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class TorrentsTableView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TorrentsTableView extends AViewer {

	/** The viewer. */
	private TableViewer viewer;

	/** The table. */
	private Table table;

	/** The column sorter. */
	private ColumnSorterProvider columnSorter;

	/** The lock. */
	private Lock lock = new ReentrantLock(true);

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(final Composite parent) {
		viewer = new TableViewer(parent, SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		viewer.setData(VIEWER_DATA_TYPE, "TorrentsTableView");
		table = viewer.getTable();

		viewer.setContentProvider(new TorrentTableContentProvider());
		viewer.setLabelProvider(new TorrentTableLabelProvider(viewer));
		viewer.setUseHashlookup(true);

		initColumns();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		EMap<String, ExtTorrent> inputMap = ClientModelManager.getInstance().getTorrents().getTorrentMap();

		// init
		NetworkModelManager.getInstance();

		viewer.setInput(inputMap);

		// DefaultToolTip toolTip = new DefaultToolTip(viewer.getControl(),
		// ToolTip.RECREATE, false);
		// toolTip.setText("Hello World\nHello World");
		// toolTip.setBackgroundColor(viewer.getTable().getDisplay()
		// .getSystemColor(SWT.COLOR_RED));
		// toolTip.setShift(new Point(10, 5));

		// CustomizedControlTooltip torrentToolTip = new
		// CustomizedControlTooltip(
		// viewer.getControl().getShell());

		initListeners();
		initAdapters(inputMap.values());

		TorrentTableUtil.getInstance().setCheckedTorrents(viewer);
		hookContextMenu();

		columnSorter = new ColumnSorterProvider(false);
		viewer.setSorter(columnSorter);

		getSite().setSelectionProvider(viewer);

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initListeners()
	 */
	@Override
	public void initListeners() {
		Listener tableListener = new TorrentTableListener(table);
		table.addListener(SWT.MouseHover, tableListener);
		table.addListener(SWT.MouseDown, tableListener);
		table.addListener(SWT.MouseMove, tableListener);
		table.addListener(SWT.PaintItem, tableListener);

		// tableViewer
		// .addDoubleClickListener(new TorrentsTableViewDoubleClickAction());

		new TorrentViewerDropListener(viewer, false);

		table.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}
		});

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.CHECK:
					if (event.item instanceof TableItem) {
						TableItem item = (TableItem) event.item;
						ExtTorrent extTorrent = (ExtTorrent) ((Entry<?, ?>) item.getData()).getValue();

						extTorrent.setEnabled(item.getChecked());
						ModelUtils.doSave(extTorrent);
					}
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initAdapters(java.util.Collection )
	 */
	@Override
	public void initAdapters(Collection<?> collection) {
		for (Object object : collection) {
			ExtTorrent extTorrent = (ExtTorrent) object;

			for (int i = 0; i < extTorrent.eAdapters().size(); i++) {
				if (extTorrent.eAdapters().get(i) instanceof TorrentTableContentAdapter) {
					return;
				}
			}
			extTorrent.eContainer().eAdapters().add(new TorrentTableContentAdapter());
		}
		ClientModelManager.getInstance().getTorrents().eAdapters().add(new TorrentTableAdapter(viewer));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		final TableColumn enabledCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.ENABLED_COL_VALUE);
		enabledCol.setText(ETorrentTableColumns.ENABLED_COL.getLiteral());

		enabledCol.setWidth(50);
		enabledCol.setToolTipText("1. Enabled - auto manage (Ready, Paused)\n2. Disabled - can't auto manage (Stopped)");
		// Add listener to column so tasks are sorted by description when
		// clicked
		enabledCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(enabledCol, ETorrentTableColumns.ENABLED_COL_VALUE);
			}
		});

		final TableColumn nameCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.NAME_COL_VALUE);
		nameCol.setText(ETorrentTableColumns.NAME_COL.getLiteral());
		nameCol.setWidth(200);
		// Add listener to column so tasks are sorted by description when
		// clicked
		nameCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(nameCol, ETorrentTableColumns.NAME_COL_VALUE);
			}
		});

		final TableColumn statCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.STATUS_COL_VALUE);
		statCol.setText(ETorrentTableColumns.STATUS_COL.getLiteral());
		statCol.setWidth(100);

		final TableColumn seedsCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.SEEDS_COL_VALUE);
		seedsCol.setText(ETorrentTableColumns.SEEDS_COL.getLiteral());
		seedsCol.setToolTipText("A seeder is a peer that has a complete copy of the torrent and still offers it for upload.");
		seedsCol.setWidth(25);
		// Add listener to column so tasks are sorted by owner when clicked
		seedsCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(seedsCol, ETorrentTableColumns.SEEDS_COL_VALUE);
			}
		});

		final TableColumn peersCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.PEERS_COL_VALUE);
		peersCol.setText(ETorrentTableColumns.PEERS_COL.getLiteral());
		peersCol.setToolTipText("A peer does not have the complete file, but only parts of it.\nActive / Available / All.");

		peersCol.setWidth(60);
		// Add listener to column so tasks are sorted by owner when clicked
		peersCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(peersCol, ETorrentTableColumns.PEERS_COL_VALUE);
			}
		});

		final TableColumn uplodersCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.DOWNLOADERS_COL_VALUE);
		uplodersCol.setText(ETorrentTableColumns.DOWNLOADERS_COL.getLiteral());
		uplodersCol.setToolTipText("A downloader is any peer that does not have the entire file and is downloading the file.\nActive / Available.");

		uplodersCol.setWidth(50);
		// Add listener to column so tasks are sorted by owner when clicked
		uplodersCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(uplodersCol, ETorrentTableColumns.DOWNLOADERS_COL_VALUE);
			}
		});

		final TableColumn sizeCol = new TableColumn(table, SWT.CHECK, ETorrentTableColumns.SIZE_COL_VALUE);
		sizeCol.setText(ETorrentTableColumns.SIZE_COL.getLiteral());

		sizeCol.setWidth(55);
		// Add listener to column so tasks are sorted by owner when clicked
		sizeCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(sizeCol, ETorrentTableColumns.SIZE_COL_VALUE);
			}
		});

		final TableColumn piecesCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.PIECES_COL_VALUE);
		piecesCol.setText(ETorrentTableColumns.PIECES_COL.getLiteral());
		piecesCol.setWidth(55);
		// Add listener to column so tasks are sorted by percent when clicked
		piecesCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(piecesCol, ETorrentTableColumns.PIECES_COL_VALUE);
			}
		});

		final TableColumn dwnldCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.DOWNLOADED_COL_VALUE);
		dwnldCol.setText(ETorrentTableColumns.DOWNLOADED_COL.getLiteral());
		dwnldCol.setWidth(60);
		// Add listener to column so tasks are sorted by percent when clicked
		dwnldCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(dwnldCol, ETorrentTableColumns.DOWNLOADED_COL_VALUE);
			}
		});

		final TableColumn complCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.COMPLETED_COL_VALUE);
		complCol.setText(ETorrentTableColumns.COMPLETED_COL.getLiteral());
		complCol.setWidth(100);
		complCol.setResizable(false);
		// Add listener to column so tasks are sorted by percent when clicked
		complCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(complCol, ETorrentTableColumns.COMPLETED_COL_VALUE);
			}
		});

		final TableColumn upldlCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.UPLOADED_COL_VALUE);
		upldlCol.setText(ETorrentTableColumns.UPLOADED_COL.getLiteral());
		upldlCol.setWidth(50);
		// Add listener to column so tasks are sorted by percent when clicked
		upldlCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(upldlCol, ETorrentTableColumns.UPLOADED_COL_VALUE);
			}
		});

		final TableColumn dwnldSpeedCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.DWNLD_SPEED_COL_VALUE);
		dwnldSpeedCol.setText(ETorrentTableColumns.DWNLD_SPEED_COL.getLiteral());
		dwnldSpeedCol.setWidth(60);
		// Add listener to column so tasks are sorted by percent when clicked
		dwnldSpeedCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(dwnldSpeedCol, ETorrentTableColumns.DWNLD_SPEED_COL_VALUE);
			}
		});

		final TableColumn upldSpeedCol = new TableColumn(table, SWT.LEFT, ETorrentTableColumns.UPLD_SPEED_COL_VALUE);
		upldSpeedCol.setText(ETorrentTableColumns.UPLD_SPEED_COL.getLiteral());
		upldSpeedCol.setWidth(60);
		// Add listener to column so tasks are sorted by percent when clicked
		upldSpeedCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(upldSpeedCol, ETorrentTableColumns.UPLD_SPEED_COL_VALUE);
			}
		});
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
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	public void refresh() {
		if (lock.tryLock()) {
			try {
				Display.getDefault().asyncExec(refresh);
			} finally {
				lock.unlock();
			}
		}
	}

	// ---------------------------------------------------------------

	/** The refresh. */
	private final Runnable refresh = new Runnable() {
		@Override
		public void run() {
			lock.lock();
			try {
				if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed() && viewer.getControl().isVisible()) {

					viewer.refresh();
				}
			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			} finally {
				lock.unlock();
			}
		}
	};

	// ---------------------------------------------------------------

	/**
	 * Do sort.
	 * @param tableColumn the table column
	 * @param columnIndex the column index
	 */
	private void doSort(TableColumn tableColumn, int columnIndex) {
		columnSorter.doSort(columnIndex);
		int dir = viewer.getTable().getSortDirection();
		if (viewer.getTable().getSortColumn() == tableColumn) {
			dir = (dir == SWT.UP) ? SWT.UP : SWT.DOWN;
		} else {
			dir = SWT.UP;
		}
		viewer.getTable().setSortDirection(dir);
		viewer.getTable().setSortColumn(tableColumn);
		refresh();
		TorrentTableUtil.getInstance().setCheckedTorrents(viewer);
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
	 * @see eu.kalafatic.gemini.bt.client.controller.lib.interfaces.IViewer#getViewer ()
	 */
	@Override
	public Viewer getViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse .jface.action.IToolBarManager)
	 */
	@Override
	protected void fillLocalToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse .jface.action.IMenuManager)
	 */
	@Override
	protected void fillLocalPullDown(IMenuManager menuManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse .jface.action.IMenuManager)
	 */
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		// TODO Auto-generated method stub

	}

}
