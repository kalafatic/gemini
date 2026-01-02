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
package eu.kalafatic.gemini.bt.tm.view.editors.pages;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TREE_STYLE_0;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import eu.kalafatic.gemini.bt.tm.controller.interfaces.IViewer;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.view.dnd.FileTreeViewerDropListener;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.bt.tm.view.providers.FileTreeContentProvider;
import eu.kalafatic.gemini.bt.tm.view.providers.FileTreeLabelProvider;
import eu.kalafatic.gemini.core.utils.ValidationUtils;

/**
 * The Class class TargetViewer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TargetViewer implements IViewer, ISelectionChangedListener {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The viewer. */
	private TreeViewer viewer;

	/** The tree. */
	private Tree tree;

	/**
	 * Instantiates a new target viewer.
	 * @param torrentEditor the torrent editor
	 */
	public TargetViewer(TorrentEditor torrentEditor) {
		this.torrentEditor = torrentEditor;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	/**
	 * Creates the part control.
	 * @param parent the parent
	 * @return the target viewer
	 */
	public TargetViewer createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, TREE_STYLE_0);
		tree = viewer.getTree();
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer.setContentProvider(new FileTreeContentProvider());
		viewer.setLabelProvider(new FileTreeLabelProvider());

		FileTreeObject fileTreeObject = BTStructureModelManager.getInstance().getBtStructure().getTreeObjects().get(torrentEditor.getExtTorrent().getName());
		setInput(fileTreeObject);

		makeActions();
		hookContextMenu();
		// hookDoubleClickAction();
		initListeners();
		contributeToActionBars();

		return this;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the input.
	 * @param fileTreeObject the new input
	 */
	public void setInput(FileTreeObject fileTreeObject) {
		viewer.setInput(fileTreeObject);
		refresh();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		viewer.addSelectionChangedListener(this);
		ValidationUtils.INSTANCE.createReqControlDecorator(tree).show();
		new FileTreeViewerDropListener(torrentEditor, viewer);
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

			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		// getSite().registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Contribute to action bars.
	 */
	private void contributeToActionBars() {
		// IActionBars bars = getViewSite().getActionBars();
		// fillLocalPullDown(bars.getMenuManager());
		// fillLocalToolBar(bars.getToolBarManager());
	}

	// ---------------------------------------------------------------

	/**
	 * Fill local pull down.
	 * @param manager the manager
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		// manager.add(removeAction);
		// manager.add(new Separator());
	}

	// ---------------------------------------------------------------

	/**
	 * Fill context menu.
	 * @param manager the manager
	 */
	private void fillContextMenu(IMenuManager manager) {
		// manager.add(removeAction);
		// manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
		// // Other plug-ins can contribute there actions here
		// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	// ---------------------------------------------------------------

	/**
	 * Fill local tool bar.
	 * @param manager the manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		// manager.add(removeAction);
		// manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
	}

	// ---------------------------------------------------------------

	/**
	 * Make actions.
	 */
	private void makeActions() {
		// removeAction = new RemoveFileTreeObjectAction(viewer);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.controller.interfaces.IViewer#getViewer()
	 */
	@Override
	public Viewer getViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the control.
	 * @return the control
	 */
	public Tree getControl() {
		return viewer.getTree();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tree.
	 * @return the tree
	 */
	public Tree getTree() {
		return tree;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		refresh();
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 */
	private void refresh() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {
						viewer.refresh();
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		});
	}
}
