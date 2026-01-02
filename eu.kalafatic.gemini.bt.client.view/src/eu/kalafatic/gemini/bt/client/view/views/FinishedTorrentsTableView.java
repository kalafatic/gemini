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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.COMMAND_STACK;
import static eu.kalafatic.gemini.core.lib.constants.FTableConstants.VIEWER_DATA_TYPE;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
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

import eu.kalafatic.gemini.bt.client.controller.adapters.FinishedTorrentTableAdapter;
import eu.kalafatic.gemini.bt.client.controller.adapters.FinishedTorrentTableContentAdapter;
import eu.kalafatic.gemini.bt.client.controller.dnd.TorrentViewerDropListener;
import eu.kalafatic.gemini.bt.client.controller.listeners.TorrentsTableViewDoubleClickAction;
import eu.kalafatic.gemini.bt.client.controller.model.ClientModelManager;
import eu.kalafatic.gemini.bt.client.controller.model.actions.TorrentModelActions;
import eu.kalafatic.gemini.bt.client.controller.providers.ColumnSorterProvider;
import eu.kalafatic.gemini.bt.client.controller.providers.FinishedTorrentTableLabelProvider;
import eu.kalafatic.gemini.bt.client.controller.providers.TorrentTableContentProvider;
import eu.kalafatic.gemini.bt.client.controller.utils.TorrentTableUtil;
import eu.kalafatic.gemini.bt.client.model.torrents.EFinishedTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.view.listeners.TorrentTableListener;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class FinishedTorrentsTableView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class FinishedTorrentsTableView extends AViewer {

	/** The viewer. */
	private TableViewer viewer;

	/** The table. */
	private Table table;

	/** The column sorter. */
	private ColumnSorterProvider columnSorter;

	/** The lock. */
	private Lock lock = new ReentrantLock(true);

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(final Composite parent) {
		viewer = new TableViewer(parent, SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		viewer.setData(VIEWER_DATA_TYPE, "FinishedTorrentsTableView");

		viewer.setContentProvider(new TorrentTableContentProvider());
		viewer.setLabelProvider(new FinishedTorrentTableLabelProvider(viewer));

		viewer.setUseHashlookup(true);

		table = viewer.getTable();

		initColumns();

		EMap<String, ExtTorrent> inputMap = ClientModelManager.getInstance().getTorrents().getFinishedTorrentsMap();
		// init
		NetworkModelManager.getInstance();

		viewer.setInput(inputMap);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		initListeners();

		refreshFromCommandStack();

		initAdapters(inputMap.values());

		TorrentTableUtil.getInstance().setCheckedTorrents(viewer);
		hookContextMenu();

		columnSorter = new ColumnSorterProvider(true);
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
		// table.addListener(SWT.PaintItem, tableListener);

		viewer.addDoubleClickListener(new TorrentsTableViewDoubleClickAction());

		new TorrentViewerDropListener(viewer, true);

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
				if (extTorrent.eAdapters().get(i) instanceof FinishedTorrentTableContentAdapter) {
					return;
				}
			}
			extTorrent.eContainer().eAdapters().add(new FinishedTorrentTableContentAdapter());
		}
		ClientModelManager.getInstance().getTorrents().eAdapters().add(new FinishedTorrentTableAdapter(viewer));
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh from command stack.
	 */
	private void refreshFromCommandStack() {
		Map<String, List<Object>> commandStack = COMMAND_STACK;

		if (commandStack.containsKey("ImportTorrentAction")) {
			List<Object> parameters = commandStack.get("ImportTorrentAction");
			for (Object object : parameters) {
				TorrentModelActions.INSTANCE.addTorrentToModel((String) object, true);
			}
			// because adapter !
			if (!parameters.isEmpty()) {
				commandStack.put("ImportTorrentAction", new BasicEList<Object>());
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		final TableColumn enabledCol = new TableColumn(table, SWT.LEFT, EFinishedTableColumns.ENABLED_COL_VALUE);
		enabledCol.setText(EFinishedTableColumns.ENABLED_COL.getLiteral());
		enabledCol.setWidth(50);
		// Add listener to column so tasks are sorted by description when
		// clicked
		enabledCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(enabledCol, EFinishedTableColumns.ENABLED_COL_VALUE);
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
					break;
				}
			}
		});

		final TableColumn nameCol = new TableColumn(table, SWT.LEFT, EFinishedTableColumns.NAME_COL_VALUE);
		nameCol.setText(EFinishedTableColumns.NAME_COL.getLiteral());
		nameCol.setWidth(200);
		// Add listener to column so tasks are sorted by description when
		// clicked
		nameCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(nameCol, EFinishedTableColumns.NAME_COL_VALUE);
			}
		});

		final TableColumn statCol = new TableColumn(table, SWT.LEFT, EFinishedTableColumns.STATUS_COL_VALUE);
		statCol.setText(EFinishedTableColumns.STATUS_COL.getLiteral());
		statCol.setWidth(150);

		final TableColumn uplodersCol = new TableColumn(table, SWT.LEFT, EFinishedTableColumns.DOWNLOADERS_COL_VALUE);
		uplodersCol.setText(EFinishedTableColumns.DOWNLOADERS_COL.getLiteral());
		uplodersCol.setToolTipText("A downloader is any peer that does not have the entire file and is downloading the file.\nActive / Available.");

		uplodersCol.setWidth(45);
		// Add listener to column so tasks are sorted by owner when clicked
		uplodersCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(uplodersCol, EFinishedTableColumns.DOWNLOADERS_COL_VALUE);
			}
		});

		final TableColumn sizeCol = new TableColumn(table, SWT.CHECK, EFinishedTableColumns.SIZE_COL_VALUE);
		sizeCol.setText(EFinishedTableColumns.SIZE_COL.getLiteral());

		sizeCol.setWidth(60);
		// Add listener to column so tasks are sorted by owner when clicked
		sizeCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(sizeCol, EFinishedTableColumns.SIZE_COL_VALUE);
			}
		});

		final TableColumn piecesCol = new TableColumn(table, SWT.CHECK, EFinishedTableColumns.PIECES_COL_VALUE);
		piecesCol.setText(EFinishedTableColumns.PIECES_COL.getLiteral());

		piecesCol.setWidth(60);
		// Add listener to column so tasks are sorted by owner when clicked
		piecesCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(piecesCol, EFinishedTableColumns.PIECES_COL_VALUE);
			}
		});

		final TableColumn uploadedCol = new TableColumn(table, SWT.CHECK, EFinishedTableColumns.UPLOADED_COL_VALUE);
		uploadedCol.setText(EFinishedTableColumns.UPLOADED_COL.getLiteral());

		uploadedCol.setWidth(60);
		// Add listener to column so tasks are sorted by owner when clicked
		uploadedCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(uploadedCol, EFinishedTableColumns.UPLOADED_COL_VALUE);
			}
		});

		final TableColumn upldSpeedCol = new TableColumn(table, SWT.LEFT, EFinishedTableColumns.UPLD_SPEED_COL_VALUE);
		upldSpeedCol.setText(EFinishedTableColumns.UPLD_SPEED_COL.getLiteral());
		upldSpeedCol.setWidth(80);
		// Add listener to column so tasks are sorted by percent when clicked
		upldSpeedCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSort(upldSpeedCol, EFinishedTableColumns.UPLD_SPEED_COL_VALUE);
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
	 * Do sort.
	 * @param tableColumn the table column
	 * @param columnIndex the column index
	 */
	private void doSort(TableColumn tableColumn, int columnIndex) {
		columnSorter.doSort(columnIndex);
		int dir = viewer.getTable().getSortDirection();
		if (viewer.getTable().getSortColumn() == tableColumn) {
			dir = dir == SWT.UP ? SWT.UP : SWT.DOWN;
		} else {
			dir = SWT.DOWN;
		}
		viewer.getTable().setSortDirection(dir);
		viewer.getTable().setSortColumn(tableColumn);
		refresh();
		TorrentTableUtil.getInstance().setCheckedTorrents(viewer);
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
