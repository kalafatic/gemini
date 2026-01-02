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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.emf.common.util.EMap;
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
import org.eclipse.zest.core.viewers.GraphViewer;

import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;

/**
 * The Class class FilterContributionItem.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FilterContributionItem extends ContributionItem {

	/** The items. */
	private final String[] items = new String[] { "Model", "Swarm", "Tracker" };

	/** The current filter. */
	private final String currentFilter = items[0];

	/** The viewer. */
	private final GraphViewer viewer;

	/** The input map. */
	private final Map<String, EMap<?, ?>> inputMap;

	/** The subtree filter. */
	private final SubtreeContributionItem subtreeFilter;

	/** The combo. */
	private Combo combo;

	/** The menu. */
	private Menu fMenu;

	/** The lock. */
	private final ReentrantLock lock = new ReentrantLock(true);

	/**
	 * Instantiates a new filter contribution item.
	 * @param viewer the viewer
	 * @param inputMap the input map
	 * @param subtreeFilter the subtree filter
	 */
	public FilterContributionItem(GraphViewer viewer, Map<String, EMap<?, ?>> inputMap, SubtreeContributionItem subtreeFilter) {
		this.viewer = viewer;
		this.inputMap = inputMap;
		this.subtreeFilter = subtreeFilter;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/** The menu adapter. */
	private final MenuAdapter menuAdapter = new MenuAdapter() {
		@Override
		public void menuShown(MenuEvent e) {
			refresh(true);
		}
	};

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

		this.combo.setItems(items);

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
		lock.lock();
		try {
			Display.getDefault().asyncExec(new Runnable() {
				@SuppressWarnings({ "unchecked", "unused" })
				@Override
				public void run() {
					if (!viewer.getGraphControl().isVisible()) {
						return;
					}

					EMap<String, Session> swarmMap = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap();

					if (layoutName.equals(items[0])) {
						subtreeFilter.setItems(new String[] { "" });
						subtreeFilter.setCurrentFilter(items[0]);
						viewer.setInput(inputMap);

					} else if (layoutName.equals(items[1])) {
						subtreeFilter.setCurrentFilter(items[1]);
						subtreeFilter.setItems(swarmMap.keySet().toArray(new String[swarmMap.keySet().size()]));

					} else if (layoutName.equals(items[2])) {
						subtreeFilter.setCurrentFilter(items[2]);
						List<String> trackers = new ArrayList<String>();
						subtreeFilter.getTrackers().clear();

						for (Session session : swarmMap.values()) {
							EMap<String, Session> trackersMap = ((SwarmSession) session).getTrackers();
							subtreeFilter.getTrackers().addAll((Collection<? extends TrackerSession>) trackersMap.values());

							String[] array = (trackersMap.keySet().toArray(new String[swarmMap.keySet().size()]));
							trackers.addAll(Arrays.asList(array));
						}
						List<String> copy = new ArrayList<String>(trackers);
						for (int i = trackers.size() - 1; i >= 0; i--) {
							if (trackers.get(i) == null) {
								trackers.remove(i);
							}
						}
						String[] array = trackers.toArray(new String[trackers.size()]);
						subtreeFilter.setItems(array);
					}
					subtreeFilter.refresh(true);
				}
			});
		} finally {
			lock.unlock();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 * @param rebuild the rebuild
	 */
	public void refresh(boolean rebuild) {
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

			MenuItem[] oldItems = fMenu.getItems();
			for (int i = 0; i < oldItems.length; i++) {
				if (oldItems[i].getData() == this) {
					oldItems[i].dispose();
				}
			}
			for (int i = 0; i < items.length; i++) {
				MenuItem item = new MenuItem(fMenu, SWT.RADIO);
				item.setText(items[i]);
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
				if (currentFilter.equalsIgnoreCase(item.getText())) {
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
	private void refreshCombo(final boolean rebuild) {
		lock.lock();
		try {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					combo.setEnabled(false);

					if (rebuild) {
						combo.setItems(items);
					}

					int index = combo.indexOf(currentFilter);
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
