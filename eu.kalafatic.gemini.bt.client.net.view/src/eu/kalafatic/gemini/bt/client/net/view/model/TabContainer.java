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
package eu.kalafatic.gemini.bt.client.net.view.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

import eu.kalafatic.gemini.bt.client.net.view.views.BTSwarmActivityView;

/**
 * The Class class TabContainer.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TabContainer {

	/** The name. */
	public String name;

	/** The canvas. */
	public Canvas canvas;

	/** The position. */
	public Point position; // x-list index / y-table position height

	/** The list. */
	public List<Tab> list;

	/** The container font. */
	private Font containerFont;

	/** The background height. */
	private int backgroundHeight = 80;

	/** The composite. */
	public Composite parent, composite;

	/** The h bar. */
	private ScrollBar hBar;

	/** The listener. */
	private Listener listener;

	/**
	 * Instantiates a new tab container.
	 * @param name the name
	 * @param canvas the canvas
	 */
	public TabContainer(String name, Canvas canvas) {
		this.name = name;
		this.canvas = canvas;

		list = new ArrayList<Tab>();
		containerFont = new Font(canvas.getDisplay(), "Arial", 8, SWT.BOLD);

		if (name.equals(BTSwarmActivityView.S_CONTAINER)) {
			this.position = new Point(0, 5);
		} else if (name.equals(BTSwarmActivityView.A_CONTAINER)) {
			this.position = new Point(0, 127);
		} else if (name.equals(BTSwarmActivityView.D_CONTAINER)) {
			this.position = new Point(0, 250);
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	public void init() {
		parent = new Composite(canvas, SWT.BORDER | SWT.H_SCROLL);

		GridLayout gridLayout = new GridLayout(1, true);
		parent.setLayout(gridLayout);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 60;
		parent.setLayoutData(gridData);

		hBar = parent.getHorizontalBar();

		composite = new Composite(parent, SWT.NATIVE | SWT.BORDER);

		initCompositeWidth();

		for (Tab tab : list) {
			tab.init();
		}
		initscrolls();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the composite width.
	 */
	private void initCompositeWidth() {
		GridLayout gridLayout = new GridLayout(list.size(), false);
		composite.setLayout(gridLayout);

		GridData gridData = new GridData();
		gridData.widthHint = list.size() * (Tab.TAB_SIZE.x + 10);
		composite.setLayoutData(gridData);

		hBar.setValues(0, 1, composite.getSize().x, Tab.TAB_SIZE.x, Tab.TAB_SIZE.x, 1);

		composite.layout(true, true);
	}

	// ---------------------------------------------------------------

	/**
	 * Initscrolls.
	 */
	public void initscrolls() {

		if (listener != null) {
			hBar.removeListener(SWT.Selection, listener);
			listener = null;
		}

		listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					Point location = composite.getLocation();
					location.x = -hBar.getSelection();
					composite.setLocation(location);
					break;
				}
			}
		};
		hBar.addListener(SWT.Selection, listener);

		hBar.setValues(0, 1, 10, 5, 5, 1);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the background.
	 * @param gc the gc
	 */
	public void initBackground(GC gc) {
		gc.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		gc.setForeground(canvas.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		gc.setFont(containerFont);

		Rectangle bounds = canvas.getBounds();

		gc.fillGradientRectangle(0, position.y, bounds.width, backgroundHeight, false);
		gc.drawString(name, bounds.width - 110, position.y);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the.
	 * @param tab the tab
	 * @param init the init
	 */
	public void add(Tab tab, boolean init) {
		list.add(tab);

		if (!init) {
			initCompositeWidth();
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Removes the.
	 * @param tab the tab
	 * @param dispose the dispose
	 */
	public void remove(Tab tab, boolean dispose) {
		if (dispose) {
			tab.table.dispose();
		}
		list.remove(tab);
		recalculate();
	}

	// ---------------------------------------------------------------

	/**
	 * Recalculate.
	 */
	public void recalculate() {
		int i = 0;
		for (Tab tab : list) {
			int x = tab.table.getSize().x * (i++);
			tab.table.setLocation(x, position.y);
			tab.table.redraw();
		}
	}
}
