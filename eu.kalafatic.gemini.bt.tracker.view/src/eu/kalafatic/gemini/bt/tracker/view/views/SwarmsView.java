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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import eu.kalafatic.gemini.bt.tracker.controller.adapters.TrackerSwarmAdapter;
import eu.kalafatic.gemini.bt.tracker.controller.adapters.TrackerSwarmContentAdapter;
import eu.kalafatic.gemini.bt.tracker.controller.dnd.SwarmsViewerDropListener;
import eu.kalafatic.gemini.bt.tracker.controller.lib.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tracker.controller.model.TrackerModelManager;
import eu.kalafatic.gemini.bt.tracker.controller.model.actions.TrackerModelActions;
import eu.kalafatic.gemini.bt.tracker.controller.providers.TrackerSwarmTreeContentProvider;
import eu.kalafatic.gemini.bt.tracker.controller.providers.TrackerSwarmTreeLabelProvider;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;

/**
 * The Class class SwarmsView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SwarmsView extends ViewPart implements IViewer, ITabbedPropertySheetPageContributor {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.bt.tracker.view.views.SwarmsView";

	/** The Constant SHEET_ID. */
	public static final String SHEET_ID = "org.eclipse.ui.views.PropertySheet";

	/** The viewer. */
	private TreeViewer viewer;

	/** The drill down adapter. */
	@SuppressWarnings("unused")
	private DrillDownAdapter drillDownAdapter;

	/** The property sheet page. */
	protected TabbedPropertySheetPage propertySheetPage;

	/** The tree. */
	private Tree tree;

	/** The note col. */
	private TreeColumn nameCol, clientCol, noteCol;

	/** The input map. */
	private Map<String, EMap<?, ?>> inputMap;

	/** The instance. */
	private volatile static SwarmsView INSTANCE;

	/**
	 * Gets the single instance of SwarmsView.
	 * @return single instance of SwarmsView
	 */
	public static SwarmsView getInstance() {
		if (INSTANCE == null) {
			synchronized (SwarmsView.class) {
				INSTANCE = new SwarmsView();
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
		viewer = new TreeViewer(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new TrackerSwarmTreeContentProvider());
		viewer.setLabelProvider(new TrackerSwarmTreeLabelProvider());
		// treeViewer.setSorter(new SwarmTreeNameSorter());
		viewer.setUseHashlookup(true);

		tree = viewer.getTree();

		initColumns();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer.setAutoExpandLevel(TreeViewer.ALL_LEVELS);

		EMap<String, Session> map = TrackerModelManager.getInstance().getTrackerModel().getSwarmMap();

		inputMap = new HashMap<String, EMap<?, ?>>();
		inputMap.put("Swarms", map);

		viewer.setInput(inputMap);

		initListeners();
		initAdapters(map);
		attachListeners();
		hookContextMenu();

		getSite().setSelectionProvider(viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(SHEET_ID);
							// propertySheetPage.setFocus();

						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

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

		TrackerModelActions.INSTANCE.addTorrentsAdapter();
	}

	// ---------------------------------------------------------------

	/**
	 * Attach listeners.
	 */
	private void attachListeners() {
		new SwarmsViewerDropListener(viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the columns.
	 */
	private void initColumns() {

		// 0rd column
		nameCol = new TreeColumn(tree, SWT.LEFT, 0);
		nameCol.setText("Torrent / Client");
		nameCol.setWidth(200);
		// Add listener to column so tasks are sorted by description when
		// clicked
		nameCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// tableViewer.setSorter(new ExampleTaskSorter(
				// ExampleTaskSorter.DESCRIPTION));
			}
		});

		// 1rd column
		clientCol = new TreeColumn(tree, SWT.CHECK, 1);
		clientCol.setText("Client");

		clientCol.setWidth(50);
		// Add listener to column so tasks are sorted by owner when clicked
		clientCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// tableViewer.setSorter(new
				// ExampleTaskSorter(ExampleTaskSorter.OWNER));
			}
		});

		// 2 column
		noteCol = new TreeColumn(tree, SWT.CHECK, 2);
		noteCol.setText("Note");

		noteCol.setWidth(200);
		// Add listener to column so tasks are sorted by owner when clicked
		noteCol.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// tableViewer.setSorter(new
				// ExampleTaskSorter(ExampleTaskSorter.OWNER));
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
			public void menuAboutToShow(IMenuManager manager) {
				// viewer.getControl().getParent().setFocus();

				setFocus();
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

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
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		try {
			if (adapter == IPropertySheetPage.class) {
				if (propertySheetPage == null || propertySheetPage.getControl().isDisposed()) {
					propertySheetPage = new TabbedPropertySheetPage(this);
					// TabbedPropertyComposite tabbedPropertyComposite =
					// (TabbedPropertyComposite) propertySheetPage
					// .getControl();
					// TabbedPropertyTitle title = tabbedPropertyComposite
					// .getTitle();
					// propertySheetPage.getSite().getShell()
					// .setImage(SESSION_IMG);
					// propertySheetPage.getSite().getShell()
					// .setText("Sessions properties");

					// title.setTitle("Sessions properties", SESSION_IMG);
				}
				// else {
				// // propertySheetPage = new TabbedPropertySheetPage(this);
				// IHandlerService handlerService = (IHandlerService) PlatformUI
				// .getWorkbench().getActiveWorkbenchWindow()
				// .getService(IHandlerService.class);
				//
				// propertySheetPage = (TabbedPropertySheetPage) handlerService
				// .executeCommand(
				// "org.eclipse.ui.views.properties.NewPropertySheetCommand",
				// null);
				//
				// }
				// propertySheetPage = new TabbedPropertySheetPage(this);
				return propertySheetPage;
			}
		} catch (Exception e) {}
		return super.getAdapter(adapter);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor #getContributorId()
	 */
	@Override
	public String getContributorId() {
		return getSite().getId();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// viewer.getControl().setFocus();
	}
}
