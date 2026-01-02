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
package eu.kalafatic.gemini.stat.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.COLLAPSE_ALL_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.EXPAND_ALL_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TREE_STYLE_1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.DrillDownAdapter;

import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.stat.controller.model.StatisticsModelManager;
import eu.kalafatic.gemini.stat.controller.providers.StatTreeContentProvider;
import eu.kalafatic.gemini.stat.controller.providers.StatTreeLabelProvider;
import eu.kalafatic.gemini.stat.controller.providers.StatTreeNameSorter;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatTreeView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings("unchecked")
public class StatTreeView extends AViewer {

	/** The viewer. */
	private TreeViewer viewer;

	/** The drill down adapter. */
	private DrillDownAdapter drillDownAdapter;

	/** The collapse all. */
	private Action expandAll, collapseAll;

	/** The tree. */
	private Tree tree;

	/** The found item. */
	private TreeItem foundItem;

	/** The input map. */
	private Map<String, EMap<?, ?>> inputMap;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The ROO t_ node. */
	private final String ROOT_NODE = "Statistics";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(final Composite parent) {
		createContents(parent);
		makeActions();
		hookContextMenu();
		contributeToActionBars();
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the contents.
	 * @param parent the parent
	 */

	private void createContents(Composite parent) {
		viewer = new TreeViewer(parent, TREE_STYLE_1);
		drillDownAdapter = new DrillDownAdapter(viewer);

		viewer.setContentProvider(new StatTreeContentProvider());
		viewer.setLabelProvider(new StatTreeLabelProvider(viewer));
		viewer.setSorter(new StatTreeNameSorter());

		viewer.setUseHashlookup(true);
		// new TorrentViewerDropListener(treeViewer);
		// new SwarmMapTreeDoubleClickListener(this);

		tree = viewer.getTree();

		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		EMap<String, Study> statMap = StatisticsModelManager.getInstance().getStatistics().getStatMap();

		inputMap = new HashMap<String, EMap<?, ?>>();
		inputMap.put(ROOT_NODE, statMap);

		viewer.setInput(statMap);
		viewer.setAutoExpandLevel(3);

		// initAdapters(swarmMap);

		getSite().setSelectionProvider(viewer);
		getSite().getPage().addSelectionListener(this);

		expandChilds(viewer.getTree().getItems());
		updateSwarms(viewer.getTree().getItems());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initAdapters(java.util.Collection )
	 */
	@Override
	public void initAdapters(Collection<?> collection) {

		// Adapter swarmTreeAdapter = new SwarmTreeAdapter(viewer);
		//
		// ClientNetwork clientNetwork = NetworkModelManager.getInstance()
		// .getClientNetwork();
		// clientNetwork.eAdapters().add(swarmTreeAdapter);
		//
		// for (Session session : clientNetwork.getSwarmMap().values()) {
		// session.eAdapters().add(swarmTreeAdapter);
		// }
		//
		// Torrents torrents = NetworkModelManager.getInstance().getTorrents();
		// torrents.eAdapters().add(swarmTreeAdapter);
		//
		// for (ExtTorrent extTorrent : torrents.getTorrentMap().values()) {
		// extTorrent.eAdapters().add(swarmTreeAdapter);
		// }
		// for (ExtTorrent extTorrent :
		// torrents.getFinishedTorrentsMap().values()) {
		// extTorrent.eAdapters().add(swarmTreeAdapter);
		// }
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
			public void menuAboutToShow(IMenuManager manager) {
				StatTreeView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#contributeToActionBars()
	 */
	@Override
	public void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillLocalPullDown(IMenuManager manager) {
		manager.add(expandAll);
		manager.add(collapseAll);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu(IMenuManager manager) {
		manager.add(expandAll);
		manager.add(collapseAll);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void fillLocalToolBar(IToolBarManager manager) {
		manager.add(expandAll);
		manager.add(collapseAll);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	// ---------------------------------------------------------------

	/**
	 * Make actions.
	 */
	private void makeActions() {
		expandAll = new Action() {
			@Override
			public void run() {
				viewer.expandAll();
				updateSwarms(viewer.getTree().getItems());
			}
		};
		expandAll.setText("Expand All");
		expandAll.setToolTipText("Expand All");
		expandAll.setImageDescriptor(EXPAND_ALL_DESC);

		collapseAll = new Action() {
			@Override
			public void run() {
				viewer.collapseAll();
				updateSwarms(viewer.getTree().getItems());
			}
		};
		collapseAll.setText("Collapse All");
		collapseAll.setToolTipText("Collapse All");
		collapseAll.setImageDescriptor(COLLAPSE_ALL_DESC);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// try {
		// if (selection instanceof StructuredSelection) {
		// StructuredSelection structuredSelection = (StructuredSelection)
		// selection;
		// if (structuredSelection.getFirstElement() instanceof Entry<?, ?>) {
		// selectionChanged((Entry<?, ?>) structuredSelection
		// .getFirstElement());
		// } else if (structuredSelection.getFirstElement() instanceof Session)
		// {
		// selectionChanged((Session) structuredSelection
		// .getFirstElement());
		// }
		// updateSwarms();
		// }
		// } catch (Exception e) {
		// Log.log(EBTClientPreferences.MODULE, e);
		// }
	}

	// ---------------------------------------------------------------

	/**
	 * Selection changed.
	 * @param entry the entry
	 */
	// private void selectionChanged(Session session) {
	// showItem(session.getAnnounce());
	// }

	// ---------------------------------------------------------------

	/**
	 * Selection changed.
	 * @param entry the entry
	 */
	private void selectionChanged(final Entry<?, ?> entry) {
		// if (entry.getValue() instanceof ExtTorrent) {
		// showItem((String) entry.getKey());
		// }
	}

	// ---------------------------------------------------------------

	/**
	 * Update swarms.
	 */
	private void updateSwarms() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (viewer.getTree().isDisposed()) {
					return;
				}
				updateSwarms(viewer.getTree().getItems());
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Update swarms.
	 * @param items the items
	 */
	private void updateSwarms(TreeItem[] items) {
		for (int i = 0; i < items.length; i++) {

			if (items[i].getData() instanceof Entry) {
				Entry<?, ?> entry = (Entry<?, ?>) items[i].getData();

				// if (entry.getValue() instanceof SwarmSession) {
				// SwarmSession session = (SwarmSession) entry.getValue();
				// ExtTorrent extTorrent = NetworkModelManager.getInstance()
				// .getDwnldUpldExtTorrent(session.getAnnounce());
				//
				// // TODO-should not occur (move)
				// if (extTorrent == null) {
				// // NetworkModelManager.getInstance().getClientNetwork()
				// // .getSwarmMap().removeKey(session.getAnnounce());
				// items[i].dispose();
				// continue;
				// }
				// if (extTorrent.isFinished()) {
				// items[i].setBackground(SAND_COLOR);
				// } else {
				// items[i].setBackground(GRASS_COLOR);
				// }
				// }
			}
			if (items[i].getItems().length > 0) {
				updateSwarms(items[i].getItems());
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Expand childs.
	 * @param items the items
	 */
	private void expandChilds(TreeItem[] items) {
		for (int i = 0; i < items.length; i++) {
			items[i].setExpanded(true);

			if (items[i].getText().startsWith(ROOT_NODE)) {
				expandChilds(items[i].getItems());
			}
		}
		refresh();
	}

	// ---------------------------------------------------------------

	/**
	 * Show item.
	 * @param key the key
	 */
	private void showItem(final String key) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (viewer == null || viewer.getTree().isDisposed()) {
					return;
				}
				TreeItem root = tree.getItem(0);
				foundItem = null;
				findTreeItem(root, key);

				expandChildItems(root, false);

				foundItem = foundItem == null ? root : foundItem;

				expandChildItems(foundItem, true);

				viewer.getTree().setSelection(foundItem);
				viewer.getTree().showItem(foundItem);

				updateSwarms();
				viewer.refresh();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Expand child items.
	 * @param item the item
	 * @param expand the expand
	 */
	private void expandChildItems(TreeItem item, boolean expand) {
		if (item.isDisposed()) {
			return;
		}
		item.setExpanded(expand);
		for (int i = 0; i < item.getItems().length; i++) {
			expandChildItems(item.getItems()[i], expand);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Find tree item.
	 * @param root the root
	 * @param key the key
	 */
	private void findTreeItem(TreeItem root, String key) {
		root.setExpanded(true);
		TreeItem[] items = root.getItems();

		for (int i = 0; i < items.length; i++) {
			if (items[i].getText().startsWith(key)) {
				foundItem = items[i];
				// foundItem.setForeground(RED);
				return;
			}
		}
		root.setExpanded(false);
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

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initListeners()
	 */
	@Override
	public void initListeners() {}

	// -----------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {}

	// -----------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#getViewer()
	 */
	@Override
	public Viewer getViewer() {
		return viewer;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#perspectiveActivated(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor)
	 */
	@Override
	public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#perspectiveChanged(org.eclipse.ui.IWorkbenchPage, org.eclipse.ui.IPerspectiveDescriptor,
	 * java.lang.String)
	 */
	@Override
	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#initDragAndDrop()
	 */
	@Override
	public void initDragAndDrop() {
		// TODO Auto-generated method stub

	}
}
