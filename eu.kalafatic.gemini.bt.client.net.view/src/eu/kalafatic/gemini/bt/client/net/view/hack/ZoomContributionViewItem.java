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
package eu.kalafatic.gemini.bt.client.net.view.hack;

/*******************************************************************************
 * Copyright 2005-2006, CHISEL Group, University of Victoria, Victoria, BC,
 * Canada. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: The Chisel Group, University of Victoria
 ******************************************************************************/

import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;

/**
 * The Class class ZoomContributionViewItem.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
// @tag zest.bug.156286-Zooming.fix : create a contribution item that can set
// zooming on Zest views.
public class ZoomContributionViewItem extends ContributionItem implements ZoomListener {

	/** The Constant FIT_WIDTH. */
	public static final String FIT_WIDTH = ZoomManager.FIT_WIDTH;

	/** The Constant FIT_HEIGHT. */
	public static final String FIT_HEIGHT = ZoomManager.FIT_HEIGHT;

	/** The Constant FIT_ALL. */
	public static final String FIT_ALL = ZoomManager.FIT_ALL;

	/** The zoom levels. */
	private String[] zoomLevels;

	/** The zoom manager. */
	private final ZoomManager zoomManager;

	/** The combo. */
	private Combo combo;

	/** The menu. */
	private Menu fMenu;

	/** The lock. */
	private final ReentrantLock lock = new ReentrantLock(true);

	/** The menu adapter. */
	private final MenuAdapter menuAdapter = new MenuAdapter() {
		@Override
		public void menuShown(MenuEvent e) {
			refresh(true);
		}
	};

	/**
	 * Instantiates a new zoom contribution view item.
	 * @param part the part
	 * @param zoomManager the zoom manager
	 */
	public ZoomContributionViewItem(IZoomableWorkbenchPart part, ZoomManager zoomManager) {
		this.zoomManager = zoomManager;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets .Menu, int)
	 */
	@Override
	public void fill(Menu menu, int index) {
		if (this.fMenu == null || this.fMenu != menu) {
			if (this.fMenu != null) {
				this.fMenu.removeMenuListener(menuAdapter);
				this.fMenu = null;
			}
			this.fMenu = menu;
			menu.addMenuListener(menuAdapter);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets .CoolBar, int)
	 */
	@Override
	public void fill(CoolBar parent, int index) {
		CoolItem item = new CoolItem(parent, SWT.DROP_DOWN);
		Combo combo = createCombo(parent);
		item.setControl(combo);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets .ToolBar, int)
	 */
	@Override
	public void fill(ToolBar parent, int index) {
		ToolItem item = new ToolItem(parent, SWT.SEPARATOR, index);
		Combo combo = createCombo(parent);
		item.setControl(combo);
		item.setWidth(combo.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the combo.
	 * @param parent the parent
	 * @return the combo
	 */
	private Combo createCombo(Composite parent) {
		this.combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 50;
		gridData.heightHint = 20;
		this.combo.setLayoutData(gridData);

		if (zoomLevels == null) {
			zoomLevels = zoomManager.getZoomLevelsAsText();
		}
		this.combo.setItems(zoomLevels);
		this.combo.addSelectionListener(new SelectionAdapter() {
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selection = combo.getSelectionIndex();
				if (selection > 0) {
					doZoom(combo.getItem(selection));
				} else {
					doZoom(combo.getItem(0));
				}
			}
		});
		this.combo.pack();
		return this.combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Do zoom.
	 * @param zoom the zoom
	 */
	private void doZoom(String zoom) {
		if (zoomManager != null) {
			zoomManager.setZoomAsText(zoom);
		}
	}

	/**
	 * Refresh.
	 * @param rebuild the rebuild
	 */
	public void refresh(boolean rebuild) {
		//
		if (combo != null && !combo.isDisposed()) {
			refreshCombo(rebuild);
		} else if (fMenu != null && !fMenu.isDisposed()) {
			refreshMenu(rebuild);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh menu.
	 * @param rebuild the rebuild
	 */
	private void refreshMenu(boolean rebuild) {
		fMenu.setEnabled(false);
		if (zoomManager == null) {
			return;
		}
		if (rebuild) {
			zoomLevels = zoomManager.getZoomLevelsAsText();
			MenuItem[] oldItems = fMenu.getItems();
			for (int i = 0; i < oldItems.length; i++) {
				if (oldItems[i].getData() == this) {
					oldItems[i].dispose();
				}
			}
			for (int i = 0; i < zoomLevels.length; i++) {
				MenuItem item = new MenuItem(fMenu, SWT.RADIO);
				item.setText(zoomLevels[i]);
				item.setData(this);
				item.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						MenuItem source = (MenuItem) e.getSource();
						doZoom(source.getText());
					}
				});
			}
		}
		String zoom = zoomManager.getZoomAsText();
		MenuItem[] items = fMenu.getItems();
		for (int i = 0; i < items.length; i++) {
			MenuItem item = items[i];
			if (item.getData() == this) {
				item.setSelection(false);
				if (zoom.equalsIgnoreCase(item.getText())) {
					item.setSelection(true);
				}
			}
		}
		fMenu.setEnabled(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh combo.
	 * @param rebuild the rebuild
	 */
	public void refreshCombo(final boolean rebuild) {
		lock.lock();
		try {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					combo.setEnabled(false);
					if (zoomManager == null) {
						return;
					}
					if (rebuild) {
						combo.setItems(zoomManager.getZoomLevelsAsText());
					}
					String zoom = zoomManager.getZoomAsText();
					int index = combo.indexOf(zoom);
					if (index > 0) {
						combo.select(index);
					}
					combo.setEnabled(true);
				}
			});
		} finally {
			lock.unlock();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.internal.ZoomListener#zoomChanged(double)
	 */
	@Override
	public void zoomChanged(double z) {
		refresh(false);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	@Override
	public void dispose() {
		if (combo != null) {
			combo = null;
		}
		if (fMenu != null) {
			fMenu = null;
		}
		super.dispose();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the combo.
	 * @return the combo
	 */
	public Combo getCombo() {
		return combo;
	}
}
