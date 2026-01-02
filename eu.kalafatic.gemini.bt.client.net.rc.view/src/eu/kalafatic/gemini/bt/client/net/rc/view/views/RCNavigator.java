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
package eu.kalafatic.gemini.bt.client.net.rc.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ADD_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ADD_X_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.COLLAPSE_ALL_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.DELETE_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.EXPAND_ALL_DESC;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TREE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ADD_FOLDER;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ADD_PAGE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.COLLAPSE_ALL;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EXPAND_ALL;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.client.net.rc.controller.model.RCModelManager;
import eu.kalafatic.gemini.bt.client.net.rc.controller.providers.RCNavigatorContentProvider;
import eu.kalafatic.gemini.bt.client.net.rc.controller.providers.RCNavigatorLabelProvider;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl.FolderImpl;
import eu.kalafatic.gemini.core.lib.EView;
import eu.kalafatic.gemini.core.utils.ValidationUtils;

/**
 * The Class class RCNavigator.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCNavigator extends ViewPart {

	/** The parent. */
	private Composite parent;

	/** The viewer. */
	private TreeViewer viewer;

	/** The drill down adapter. */
	private DrillDownAdapter drillDownAdapter;

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The remove action. */
	private Action expandAllAction, collapseAllAction, addFolderAction, addPageAction, removeAction;

	/** The tree. */
	private Tree tree;

	/**
	 * Instantiates a new rC navigator.
	 */
	public RCNavigator() {
		setTitleImage(TREE_IMG);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;

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

		viewer = new TreeViewer(parent, /* SWT.MULTI | */SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);

		EMap<String, Folder> inputMap = RCModelManager.getInstance().getRc().getTree();

		viewer.setContentProvider(new RCNavigatorContentProvider(inputMap));
		viewer.setLabelProvider(new RCNavigatorLabelProvider(inputMap));

		viewer.setUseHashlookup(true);
		tree = viewer.getTree();

		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer.setInput(inputMap);

		getSite().setSelectionProvider(viewer);
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
				RCNavigator.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/**
	 * Contribute to action bars.
	 */
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	// ---------------------------------------------------------------

	/**
	 * Fill local pull down.
	 * @param manager the manager
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(addFolderAction);
		manager.add(addPageAction);
		manager.add(new Separator());
		manager.add(removeAction);
		manager.add(new Separator());
	}

	// ---------------------------------------------------------------

	/**
	 * Fill context menu.
	 * @param manager the manager
	 */
	private void fillContextMenu(IMenuManager manager) {
		manager.add(addFolderAction);
		manager.add(addPageAction);
		manager.add(new Separator());
		manager.add(removeAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	// ---------------------------------------------------------------

	/**
	 * Fill local tool bar.
	 * @param manager the manager
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addFolderAction);
		manager.add(addPageAction);
		manager.add(removeAction);
		manager.add(new Separator());
		manager.add(expandAllAction);
		manager.add(collapseAllAction);
		drillDownAdapter.addNavigationActions(manager);
	}

	// ---------------------------------------------------------------

	/**
	 * Make actions.
	 */
	private void makeActions() {

		expandAllAction = new Action() {
			@Override
			public void run() {
				viewer.expandAll();
			}
		};
		expandAllAction.setToolTipText(EXPAND_ALL);
		expandAllAction.setImageDescriptor(EXPAND_ALL_DESC);

		collapseAllAction = new Action() {
			@Override
			public void run() {
				viewer.collapseAll();
			}
		};

		collapseAllAction.setToolTipText(COLLAPSE_ALL);
		collapseAllAction.setImageDescriptor(COLLAPSE_ALL_DESC);

		addFolderAction = new Action() {
			@Override
			public void run() {
				addFolder();
			}

		};

		addFolderAction.setText(ADD_FOLDER);
		addFolderAction.setToolTipText(ADD_FOLDER);
		addFolderAction.setImageDescriptor(ADD_DESC);

		addPageAction = new Action() {
			@Override
			public void run() {
				addPage();

			}

		};

		addPageAction.setText(ADD_PAGE);
		addPageAction.setToolTipText(ADD_PAGE);
		addPageAction.setImageDescriptor(ADD_X_DESC);

		removeAction = new Action() {
			@Override
			public void run() {
				remove();
			}

		};

		removeAction.setText(REMOVE);
		removeAction.setToolTipText(REMOVE);
		removeAction.setImageDescriptor(DELETE_DESC);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the folder.
	 */
	private void addFolder() {
		ISelection selection = viewer.getSelection();
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;

			InputDialog dialog = new InputDialog(Display.getCurrent().getActiveShell(), "New Folder", "Enter 1+ characters", "", ValidationUtils.INSTANCE.new LengthValidator(1, 50));

			if (dialog.open() == Window.OK) {
				String folderName = dialog.getValue();

				Folder folder = RcFactory.eINSTANCE.createFolder();
				folder.setAddress(folderName);

				if (treeSelection.isEmpty()) {
					RCModelManager.getInstance().getRc().getTree().put(folderName, folder);
					return;
				}

				if (treeSelection.getFirstElement() instanceof Folder) {
					Folder parentFolder = (Folder) treeSelection.getFirstElement();
					folder.setParent(parentFolder);
					parentFolder.getFolders().put(folderName, folder);
				}
				refresh();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the.
	 */
	private void remove() {
		ISelection selection = viewer.getSelection();
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;

			if (treeSelection.getFirstElement() instanceof Folder) {
				Folder folder = (Folder) treeSelection.getFirstElement();

				if (folder.getParent() == null) {
					RCModelManager.getInstance().getRc().getTree().removeKey(folder.getAddress());
				} else {
					folder.getParent().getFolders().removeKey(folder.getAddress());
				}

			} else if (treeSelection.getFirstElement() instanceof Page) {
				Page page = (Page) treeSelection.getFirstElement();
				page.getParent().getPages().removeKey(page.getAddress());
			}
			refresh();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the page.
	 */
	private void addPage() {
		RCView findView = (RCView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(EView.RC.ID);
		String address = findView.getLocation().getText();
		Page page = RcFactory.eINSTANCE.createPage();
		page.setAddress(address);

		ISelection selection = viewer.getSelection();
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			if (treeSelection.getFirstElement() instanceof FolderImpl) {
				FolderImpl folder = (FolderImpl) treeSelection.getFirstElement();
				folder.getPages().put(address, page);
				RCModelManager.getInstance().doSave();
				refresh();
			}
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
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}
