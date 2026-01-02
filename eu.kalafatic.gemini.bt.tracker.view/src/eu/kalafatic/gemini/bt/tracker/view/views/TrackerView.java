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
package eu.kalafatic.gemini.bt.tracker.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.SERVER_IMG;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.tracker.controller.adapters.TrackerSwarmAdapter;
import eu.kalafatic.gemini.bt.tracker.controller.adapters.TrackerSwarmContentAdapter;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.listeners.TableListener;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.controller.providers.ColumnSorterProvider;
import eu.kalafatic.gemini.bt.tracker.controller.providers.TrackerTableContentProvider;
import eu.kalafatic.gemini.bt.tracker.controller.providers.TrackerTableLabelProvider;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;

/**
 * The Class class TrackerView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerView extends ViewPart implements IViewer, ISelectionListener {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.bt.tracker.view.views.TrackerView";

	/** The viewer. */
	private TableViewer viewer;

	/** The table. */
	private Table table;

	/** The date col. */
	private TableColumn dateCol;

	/** The message col. */
	private TableColumn messageCol;

	/** The column sorter. */
	private ColumnSorterProvider columnSorter;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The instance. */
	private static TrackerView INSTANCE;

	/**
	 * Gets the single instance of TrackerView.
	 * @return single instance of TrackerView
	 */
	public static TrackerView getInstance() {
		if (INSTANCE == null) {
			synchronized (TrackerView.class) {
				INSTANCE = new TrackerView();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		setTitleImage(SERVER_IMG);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);

		viewer.setLabelProvider(new TrackerTableLabelProvider());
		viewer.setContentProvider(new TrackerTableContentProvider());

		viewer.setUseHashlookup(true);

		table = viewer.getTable();

		initColumns();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Listener paintListener = new TableListener();

		table.addListener(SWT.MeasureItem, paintListener);
		table.addListener(SWT.PaintItem, paintListener);
		table.addListener(SWT.EraseItem, paintListener);

		EMap<String, Session> swarmMap = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

		viewer.setInput(swarmMap);

		columnSorter = new ColumnSorterProvider();
		viewer.setSorter(columnSorter);

		hookContextMenu();

		getSite().setSelectionProvider(viewer);
		getSite().getPage().addSelectionListener(this);

		initAdapters(swarmMap);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the adapters.
	 * @param map the map
	 */
	private void initAdapters(EMap<String, Session> map) {

		Iterator<Session> iterator = map.values().iterator();

		while (iterator.hasNext()) {
			TorrentSession session = (TorrentSession) iterator.next();

			for (int i = 0; i < session.eAdapters().size(); i++) {
				if (session.eAdapters().get(i) instanceof TrackerSwarmContentAdapter) {
					return;
				}
			}
			TrackerSwarmContentAdapter contentAdapter = new TrackerSwarmContentAdapter(viewer);
			session.eAdapters().add(contentAdapter);
		}
		TrackerSwarmAdapter torrentTableAdapter = new TrackerSwarmAdapter(viewer);
		TrackerModelManager.getInstance().getTrackerModel().eAdapters().add(torrentTableAdapter);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the columns.
	 */
	private void initColumns() {

		dateCol = new TableColumn(table, SWT.LEFT, 0);
		dateCol.setText("Date");
		dateCol.setWidth(80);
		// Add listener to column so tasks are sorted by description when
		// clicked
		dateCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				columnSorter.doSort(0);
				int dir = viewer.getTable().getSortDirection();
				if (viewer.getTable().getSortColumn() == dateCol) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {
					dir = SWT.DOWN;
				}
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(dateCol);

				refresh();
			}
		});

		messageCol = new TableColumn(table, SWT.LEFT, 1);
		messageCol.setText("Request / Response");
		messageCol.setWidth(400);
		// Add listener to column so tasks are sorted by description when
		// clicked
		messageCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				columnSorter.doSort(1);
				int dir = viewer.getTable().getSortDirection();
				if (viewer.getTable().getSortColumn() == messageCol) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {
					dir = SWT.DOWN;
				}
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(messageCol);
				refresh();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Hook context menu.
	 */
	private void hookContextMenu() {
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		try {
			if (selection instanceof StructuredSelection) {
				StructuredSelection structuredSelection = (StructuredSelection) selection;
				if (structuredSelection.getFirstElement() instanceof Entry<?, ?>) {
					Entry<?, ?> entry = (Entry<?, ?>) structuredSelection.getFirstElement();

					if (entry.getValue() instanceof Session) {
						Session session = (Session) entry.getValue();

						viewer.setInput(session);
						refresh();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				// e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	};

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer#getViewer ()
	 */
	@Override
	public Viewer getViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer#setTitleImg (org.eclipse.swt.graphics.Image)
	 */
	@Override
	public void setTitleImg(Image image) {
		setTitleImage(image);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}
}
