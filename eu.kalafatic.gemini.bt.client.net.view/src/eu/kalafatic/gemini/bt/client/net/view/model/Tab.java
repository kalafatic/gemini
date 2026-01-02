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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.net.view.views.BTSwarmActivityView;

/**
 * The Class class Tab.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class Tab {

	/** The name. */
	public String name;

	/** The container. */
	private TabContainer container;

	/** The rows. */
	private int rows;

	/** The Constant TAB_SIZE. */
	public static final Point TAB_SIZE = new Point(60, 40);

	/** The table. */
	public Table table;

	/** The animation delay. */
	private int animationDelay = 10;

	/**
	 * Instantiates a new tab.
	 * @param name the name
	 * @param container the container
	 * @param rows the rows
	 */
	public Tab(String name, TabContainer container, int rows) {
		this.name = name;
		this.container = container;
		this.rows = rows;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @return the tab
	 */
	public Tab init() {
		table = new Table(container.composite, SWT.BORDER | SWT.NO_SCROLL);
		GridData gridData = new GridData();
		gridData.widthHint = TAB_SIZE.x;
		gridData.heightHint = TAB_SIZE.y;
		table.setLayoutData(gridData);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setWidth(table.getSize().x);
		tc.setText(name);

		for (int i = 0; i < rows; i++) {
			new TableItem(table, SWT.BORDER);
		}
		return this;
	}

	// ---------------------------------------------------------------

	/**
	 * Animate result.
	 * @param destContainer the dest container
	 */
	public void animateResult(TabContainer destContainer) {
		final Color background = table.getBackground();

		table.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
		container.canvas.layout(true, true);

		Tab tab = null;

		if (destContainer.name.equals(BTSwarmActivityView.A_CONTAINER)) {
			tab = new Tab("a", container, 2);

		} else if (destContainer.name.equals(BTSwarmActivityView.D_CONTAINER)) {
			tab = new Tab("aa", container, 2);
		}

		if (container.equals(destContainer)) {
			tab = container.list.get(0);
			animateQueueResult(tab);
		} else {
			animateResult(destContainer, tab);
		}
		table.setBackground(background);

		destContainer.add(tab.init(), false);
		// destContainer.composite.layout(true, true);

		// container.composite.redraw();
		// container.composite.layout(true, true);
		// tab.table.layout(true, true);
		container.canvas.redraw();
		container.canvas.layout(true, true);

		// tab.table.redraw();
		// destContainer.composite.layout(true, true);

	}

	// ---------------------------------------------------------------

	/**
	 * Animate result.
	 * @param destContainer the dest container
	 * @param tab the tab
	 */
	public void animateResult(final TabContainer destContainer, final Tab tab) {

		Point oldLocation = new Point(container.position.x + table.getLocation().x, container.position.y + 10);

		Point newLocation = new Point(destContainer.list.size() * TAB_SIZE.x, destContainer.position.y);

		GC gc = new GC(container.canvas);

		Point step = getStep(oldLocation, newLocation, 100);

		for (int i = 0; i < animationDelay; i++) {
			try {
				gc.drawRectangle(oldLocation.x += step.x, oldLocation.y += step.y, TAB_SIZE.x, TAB_SIZE.y);

				Thread.sleep(animationDelay * 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		gc.dispose();
	}

	// ---------------------------------------------------------------

	/**
	 * Animate queue result.
	 * @param tab the tab
	 */
	private void animateQueueResult(Tab tab) {

		Point newLocation = new Point(((container.list.size() - 1) * TAB_SIZE.x) + 10, container.position.y);

		moveVertical(tab.table, 80, 3);
		tab.table.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		tab.table.redraw();

		container.remove(tab, false);
		tab.table.redraw();

		moveHorizontal(tab.table, newLocation, 6);
		tab.table.redraw();

		moveVertical(tab.table, -80, 3);
		tab.table.redraw();
	}

	// ---------------------------------------------------------------

	/**
	 * Move vertical.
	 * @param t the t
	 * @param y the y
	 * @param delay the delay
	 */
	private void moveVertical(Table t, float y, float delay) {

		float yy = y / delay;

		for (int i = 0; i < delay; i++) {
			try {
				t.setLocation(t.getLocation().x, (int) (t.getLocation().y + yy));
				t.redraw();
				Thread.sleep((long) (delay * 70));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Move horizontal.
	 * @param t the t
	 * @param newLocation the new location
	 * @param delay the delay
	 */
	private void moveHorizontal(Table t, Point newLocation, float delay) {

		float xx = (t.getLocation().x - newLocation.x) / delay;

		xx = xx < 0 ? -xx : xx;

		for (int i = 0; i < delay; i++) {
			try {
				t.setLocation((int) (t.getLocation().x + xx), t.getLocation().y);
				t.redraw();
				Thread.sleep((long) (delay * 40));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the step.
	 * @param oldLocation the old location
	 * @param newLocation the new location
	 * @param i the i
	 * @return the step
	 */
	private Point getStep(Point oldLocation, Point newLocation, int i) {

		int x = (oldLocation.x - newLocation.x) / animationDelay;
		x = (x < 0 ? -x : x);

		int y = (oldLocation.y - newLocation.y) / animationDelay;
		y = (y < 0 ? -y : y);

		return new Point(x, y);
	}
}
