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
package eu.kalafatic.gemini.bt.client.net.view.views;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmGraphAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.adapters.SwarmGraphContentAdapter;
import eu.kalafatic.gemini.bt.client.net.controller.dnd.BTGraphDropListener;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientNetwork;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.view.hack.FilterContributionItem;
import eu.kalafatic.gemini.bt.client.net.view.hack.LayoutContributionItem;
import eu.kalafatic.gemini.bt.client.net.view.hack.SubtreeContributionItem;
import eu.kalafatic.gemini.bt.client.net.view.hack.ZoomContributionViewItem;
import eu.kalafatic.gemini.bt.client.net.view.hack.ZoomManager;
import eu.kalafatic.gemini.bt.client.net.view.lib.constants.ELayouts;
import eu.kalafatic.gemini.bt.client.net.view.providers.ZestLabelProvider;
import eu.kalafatic.gemini.bt.client.net.view.providers.ZestNodeContentProvider;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.lib.EView;

/**
 * The Class class BTGraphView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BTGraphView extends AViewer implements IPartListener2, IZoomableWorkbenchPart {

	/** The Constant ROOT_NODE. */
	public static final String ROOT_NODE = "Swarms";

	/** The viewer. */
	private GraphViewer viewer;

	/** The zoom item. */
	private ZoomContributionViewItem zoomItem;

	/** The layout item. */
	private LayoutContributionItem layoutItem;

	/** The filter item. */
	private FilterContributionItem filterItem;

	/** The subtree filter. */
	private SubtreeContributionItem subtreeFilter;

	/** The input map. */
	private Map<String, EMap<?, ?>> inputMap;

	/** The root node. */
	@SuppressWarnings("unused")
	private GraphNode rootNode;

	/** The graph. */
	private Graph graph;

	/** The zoom manager. */
	private ZoomManager zoomManager;

	/** The select all action. */
	@SuppressWarnings("unused")
	private Action addItemAction, deleteItemAction, selectAllAction;

	/** The lock. */
	private final ReentrantLock lock = new ReentrantLock(true);

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));

		viewer = new GraphViewer(parent, SWT.NONE);
		viewer.setContentProvider(new ZestNodeContentProvider(viewer));
		viewer.setLabelProvider(new ZestLabelProvider(viewer)); // GraphItemStyler

		graph = viewer.getGraphControl();

		zoomManager = new ZoomManager(graph.getRootLayer(), graph.getViewport());

		EMap<String, Session> swarmMap = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap();

		inputMap = new HashMap<String, EMap<?, ?>>();
		inputMap.put(ROOT_NODE, swarmMap);

		viewer.setInput(inputMap);

		createActions();
		createMenu();
		hookContextMenu();
		createToolbar();

		initContributionItems();

		getSite().setSelectionProvider(viewer);
		getSite().getWorkbenchWindow().getPartService().addPartListener(this);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the contribution items.
	 */
	private void initContributionItems() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				filterItem.getCombo().select(0);
				layoutItem.getCombo().select(0);
				zoomItem.getCombo().select(0);
				viewer.setLayoutAlgorithm(ELayouts.RADIAL.algorithm, true);

				initListeners();
				initAdapters(null);
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
		SwarmGraphContentAdapter contentAdapter = new SwarmGraphContentAdapter(viewer, lock);
		Adapter adapter = new SwarmGraphAdapter(viewer, lock, contentAdapter);

		ClientNetwork clientNetwork = NetworkModelManager.getInstance().getClientNetwork();
		clientNetwork.eAdapters().add(adapter);
		NetworkModelManager.getInstance().getTorrents().eAdapters().add(contentAdapter);

		for (Session session : clientNetwork.getSwarmMap().values()) {
			SwarmSession swarmSession = (SwarmSession) session;

			swarmSession.eAdapters().add(adapter);
			swarmSession.eAdapters().add(contentAdapter);

			for (Session trSession : swarmSession.getTrackers().values()) {
				TrackerSession trackerSession = (TrackerSession) trSession;

				trackerSession.eAdapters().add(adapter);
				trackerSession.eAdapters().add(contentAdapter);

				for (Session dwnldSession : swarmSession.getDownloads().values()) {

					dwnldSession.eAdapters().add(contentAdapter);
				}
			}
			for (Session upldSession : swarmSession.getUploads().values()) {
				upldSession.eAdapters().add(contentAdapter);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the menu.
	 */
	@SuppressWarnings("unused")
	private void createMenu() {
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		// mgr.add(selectAllAction);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the toolbar.
	 */
	private void createToolbar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();

		IActionBars bars = getViewSite().getActionBars();

		subtreeFilter = new SubtreeContributionItem(viewer, inputMap);
		filterItem = new FilterContributionItem(viewer, inputMap, subtreeFilter);

		layoutItem = new LayoutContributionItem(viewer.getGraphControl());
		zoomItem = new ZoomContributionViewItem(this, zoomManager);

		bars.getMenuManager().add(filterItem);
		bars.getMenuManager().add(subtreeFilter);
		bars.getMenuManager().add(layoutItem);
		bars.getMenuManager().add(zoomItem);

		toolBarManager.add(filterItem);
		toolBarManager.add(subtreeFilter);
		toolBarManager.add(layoutItem);
		toolBarManager.add(zoomItem);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unused")
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof Entry<?, ?>) {
				Entry<?, ?> entry = (Entry<?, ?>) structuredSelection.getFirstElement();

				// if (entry.getValue() instanceof Session) {
				// Session session = (Session) entry.getValue();

				// PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				// .getActivePage().bringToTop(this);

				// table.clearAll();
				// updateTable(session);
				// }
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the actions.
	 */
	public void createActions() {
		addItemAction = new Action("Add...") {
			@Override
			public void run() {
				System.err.println(" addItem();");

			}
		};
		// addItemAction.setImageDescriptor(getImageDescriptor("add.gif"));

		deleteItemAction = new Action("Delete") {
			@Override
			public void run() {
				System.err.println(" deleteItem();");

			}
		};
		// deleteItemAction.setImageDescriptor(getImageDescriptor("delete.gif"));

		selectAllAction = new Action("Select All") {
			@Override
			public void run() {
				System.err.println("  selectAll();");

			}
		};

		// Add selection listener.
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateActionEnablement();
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initListeners()
	 */

	@Override
	public void initListeners() {
		new BTGraphDropListener(viewer);

		// graph.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseDown(MouseEvent e) {
		// super.mouseDown(e);
		//
		// // if (e.button == 1) {
		// // ISelection selection = viewer.getSelection();
		// // Object selected = ((IStructuredSelection) selection)
		// // .getFirstElement();
		// // if (selected instanceof SwarmSession) {
		// // SwarmSession session = (SwarmSession) selected;
		// // StructuredSelection structuredSelection = (StructuredSelection)
		// selection;
		// //// viewer.setSelection(session);
		// // }
		// // }
		// }
		// });
		//
		// viewer.addDoubleClickListener(new IDoubleClickListener() {
		// @Override
		// public void doubleClick(DoubleClickEvent event) {
		// ISelection selection = viewer.getSelection();
		// Object selected = ((IStructuredSelection) selection)
		// .getFirstElement();
		// if (selected instanceof Node) {
		// Node node = (Node) selected;
		//
		// viewer.setInput(node);
		// }
		// }
		// });

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
	 * Update action enablement.
	 */
	private void updateActionEnablement() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		deleteItemAction.setEnabled(sel.size() > 0);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	// ---------------------------------------------------------------

	/**
	 * Fill tool bar.
	 */
	@SuppressWarnings("unused")
	private void fillToolBar() {
		// zoomItem = new ZoomContributionViewItem(this);
		// IActionBars bars = getViewSite().getActionBars();
		// bars.getMenuManager().add(zoomItem);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IZoomableWorkbenchPart#getZoomableViewer()
	 */
	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IViewer#getViewer ()
	 */
	@Override
	public GraphViewer getViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		if (partRef.getId().equals(EView.SWARMS_GRAPH.ID)) {
			Runtime.getRuntime().gc();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		if (partRef.getId().equals(EView.SWARMS_GRAPH.ID)) {
			Runtime.getRuntime().gc();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partVisible(IWorkbenchPartReference partRef) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui. IWorkbenchPartReference)
	 */
	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {}

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
