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
import org.eclipse.zest.core.widgets.Graph;

import eu.kalafatic.gemini.bt.client.net.view.lib.constants.ELayouts;

/**
 * The Class class LayoutContributionItem.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class LayoutContributionItem extends ContributionItem {

	/** The graph. */
	private Graph graph;

	/** The current layout. */
	private ELayouts currentLayout = ELayouts.RADIAL;

	/** The layout levels. */
	private String[] layoutLevels;

	/** The combo. */
	private Combo combo;

	/** The menu. */
	private Menu fMenu;

	/**
	 * Instantiates a new layout contribution item.
	 * @param graph the graph
	 */
	public LayoutContributionItem(Graph graph) {
		this.graph = graph;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
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

	/** The menu adapter. */
	private MenuAdapter menuAdapter = new MenuAdapter() {
		@Override
		public void menuShown(MenuEvent e) {
			refresh(true);
		}
	};

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.CoolBar, int)
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
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
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

		if (layoutLevels == null) {
			layoutLevels = ELayouts.getLayoutsAsText();
		}
		this.combo.setItems(layoutLevels);
		this.combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selection = combo.getSelectionIndex();
				if (selection > 0) {
					doLayout(combo.getItem(selection));
				} else {
					doLayout(combo.getItem(0));
				}
			}
		});
		this.combo.pack();
		return this.combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Do layout.
	 * @param layoutName the layout name
	 */
	private void doLayout(final String layoutName) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				graph.setLayoutAlgorithm(ELayouts.getAlgorithm(layoutName), true);
			}
		});
	}

	// ---------------------------------------------------------------

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

		if (rebuild) {
			layoutLevels = ELayouts.getLayoutsAsText();
			MenuItem[] oldItems = fMenu.getItems();
			for (int i = 0; i < oldItems.length; i++) {
				if (oldItems[i].getData() == this) {
					oldItems[i].dispose();
				}
			}
			for (int i = 0; i < layoutLevels.length; i++) {
				MenuItem item = new MenuItem(fMenu, SWT.RADIO);
				item.setText(layoutLevels[i]);
				item.setData(this);
				item.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						MenuItem source = (MenuItem) e.getSource();
						doLayout(source.getText());
					}
				});
			}
		}

		MenuItem[] items = fMenu.getItems();
		for (int i = 0; i < items.length; i++) {
			MenuItem item = items[i];
			if (item.getData() == this) {
				item.setSelection(false);
				if (currentLayout.name.equalsIgnoreCase(item.getText())) {
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
	private void refreshCombo(boolean rebuild) {
		combo.setEnabled(false);

		if (rebuild) {
			combo.setItems(ELayouts.getLayoutsAsText());
		}

		int index = combo.indexOf(currentLayout.name);
		if (index > 0) {
			combo.select(index);
		}
		combo.setEnabled(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Zoom changed.
	 * @param z the z
	 */
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
