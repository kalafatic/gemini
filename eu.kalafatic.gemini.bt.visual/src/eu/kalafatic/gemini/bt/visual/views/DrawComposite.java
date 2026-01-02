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
package eu.kalafatic.gemini.bt.visual.views;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.editors.GraphMultiPageEditor;
import eu.kalafatic.gemini.bt.visual.models.Node;
import eu.kalafatic.gemini.bt.visual.models.TreeModel;
import eu.kalafatic.gemini.core.lib.constants.IUIConstants;

/**
 * The Class DrawComposite.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class DrawComposite implements IUIConstants {

	/** The canvas. */
	private Canvas canvas;
	
	/** The model. */
	private TreeModel model;

	/** The display. */
	private Display display;
	
	/** The center. */
	private Point center;
	
	/** The scroll. */
	private Point scrollBar, scroll;
	
	/** The client area. */
	private Rectangle clientArea;

	/** The paint allowed. */
	private boolean paintAllowed = false;	
	
	/** The circle. */
	private Point circle;
	
	/** The font. */
	private Font font;

	/**
	 * Instantiates a new draw composite.
	 * 
	 * @param canvas
	 *            the canvas
	 * @param model
	 *            the model
	 * @param activeEditor
	 *            the active editor
	 */
	public DrawComposite(Canvas canvas, TreeModel model,
			GraphMultiPageEditor activeEditor) {

		this.canvas = canvas;
		this.model = model;

		display = canvas.getDisplay();
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the control.
	 */
	public void createControl() {

		canvas.addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent e) {

				clientArea = ((Canvas) e.widget).getClientArea();

				if (Config.radiusChanged) {
					circle = new Point(Config.radius, Config.radius);
//					System.err.println("Config.radius-"+Config.radius);
					Config.radiusChanged = false;
				}

				center = new Point(clientArea.width / 2, clientArea.height / 2);
				drawModel();
			}
		});
		clientArea = canvas.getShell().getBounds();
				
		circle = new Point(Config.radius, Config.radius);
		scrollBar = canvas.getSize();
		scroll = new Point(0, 0);
		setScrollbars(clientArea);
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw model.
	 */
	public void drawModel() {
		int size = model.getNodeMap().size();

		double angle = 360000 / size;
		angle = angle / 1000;
		int index = 0;

		Iterator<Node> nodesIterator = model.getNodeMap().values().iterator();

		while (nodesIterator.hasNext()) {
			Node node = (Node) nodesIterator.next();

			double angdeg = Math.toRadians((index++) * angle);

			node.setCoordinates(solveCirclePosition(angdeg, circle.x / 2));

//			System.err.println("circle.x"+circle.x);
			drawNode(node);
		}
		drawChildren();
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw node.
	 * 
	 * @param node
	 *            the node
	 */
	public void drawNode(Node node) {
		GC gc = new GC(canvas);
		gc.setLineCap(2);
		gc.setLineWidth(3);
		gc.setLineStyle(SWT.LINE_SOLID);
		gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
		gc.drawLine(node.getCoordinates().x - 5, node.getCoordinates().y, node
				.getCoordinates().x + 5, node.getCoordinates().y);
		gc.drawLine(node.getCoordinates().x, node.getCoordinates().y - 5, node
				.getCoordinates().x, node.getCoordinates().y + 5);
		gc.dispose();
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw children.
	 */
	private void drawChildren() {
		GC gc = new GC(canvas);
		gc.setLineStyle(SWT.LINE_DOT);
		gc.setBackground(display
				.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));

		font = new Font(display, "Helvetica", 7, SWT.BOLD);

		Iterator<Node> nodesIterator = model.getNodeMap().values().iterator();

		while (nodesIterator.hasNext()) {
			Node parent = (Node) nodesIterator.next();

			int x = parent.getCoordinates().x;
			int y = parent.getCoordinates().y;

			int size = parent.getChildren().size();

			TableItem tableItem;
			final Table table;

			if (parent.getTable() == null) {
				table = createTable(parent, x, y, size);
			} else {
				table = parent.getTable();
			}

			Iterator<Node> childrenIterator = parent.getChildren().values()
					.iterator();

			while (childrenIterator.hasNext()) {

				Node child = childrenIterator.next();

				gc.drawLine(x, y, child.getCoordinates().x, child
						.getCoordinates().y);

				tableItem = new TableItem(table, SWT.NO);

				tableItem.setText(new String[] { child.getName(),
						"(" + Integer.toString(child.getDistance()) + ")" });
			}
			table.redraw();
		}
		gc.dispose();
	}
	
	// ---------------------------------------------------------------

	/**
	 * Creates the table.
	 * 
	 * @param parent
	 *            the parent
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param size
	 *            the size
	 * 
	 * @return the table
	 */
	private Table createTable(Node parent, int x, int y, int size) {

		final Table table = new Table(canvas, SWT.NATIVE);
		parent.setTable(table);
		table.getVerticalBar().dispose();
		table.getHorizontalBar().dispose();

		Point tableSize = new Point(48, (size + 1) * 12);

		Point move = new Point(0, 0);

		if (x < center.x) {
			move.x = -tableSize.x;
		}
		if (y < center.y) {
			move.y = -tableSize.y;
		}

		table.setBounds(parent.getCoordinates().x + move.x, parent
				.getCoordinates().y
				+ move.y, tableSize.x, tableSize.y);

		table.setFont(font);
		TableColumn tc1 = new TableColumn(table, SWT.NATIVE);
		tc1.setWidth(20);
		TableColumn tc2 = new TableColumn(table, SWT.NATIVE);
		tc2.setWidth(28);

		TableItem tableItem = new TableItem(table, SWT.NATIVE);
		tableItem.setBackground(SAND_COLOR);
		tableItem.setText(0, parent.getName());
			
		return table;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw start end node.
	 * 
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public synchronized void drawStartEndNode(Node start, Node end) {
		start.getTable().getItem(0).setBackground(1,
				display.getSystemColor(SWT.COLOR_RED));
		end.getTable().getItem(0).setBackground(1,
				display.getSystemColor(SWT.COLOR_RED));

		start.getTable().getItem(0).setText(1,
				Integer.toString(start.getPrice()));
		end.getTable().getItem(0).setText(1,
				Integer.toString(Integer.MAX_VALUE));

		start.getTable().update();
		end.getTable().update();

		start.getTable().redraw();
		end.getTable().redraw();

		try {
			Thread.sleep(Config.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw search node.
	 * 
	 * @param searchNode
	 *            the search node
	 */
	public void drawSearchNode(Node searchNode) {

		searchNode.getTable().getItem(0).setBackground(1,
				new Color(display, 200, 230, 255));
		searchNode.getTable().getItem(0).setText(1,
				Integer.toString(searchNode.getPrice()));

		searchNode.getTable().update();
		searchNode.getTable().redraw();

		try {
			Thread.sleep(Config.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw child search node.
	 * 
	 * @param node
	 *            the node
	 * @param childNode
	 *            the child node
	 * @param color
	 *            the color
	 */
	public void drawChildSearchNode(Node node, Node childNode, Color color) {
		GC gc = new GC(canvas);
		gc.setLineCap(2);
		gc.setLineWidth(2);
		gc.setLineStyle(SWT.LINE_SOLID);

		if (color == null) {
			gc.setForeground(display.getSystemColor(SWT.COLOR_MAGENTA));
		} else {
			gc.setForeground(color);
		}

		gc.drawLine(node.getCoordinates().x, node.getCoordinates().y,
				childNode.getCoordinates().x, childNode.getCoordinates().y);
		gc.dispose();

		drawSearchNode(childNode);

		try {
			Thread.sleep(Config.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw min price node.
	 * 
	 * @param minPriceNode
	 *            the min price node
	 */
	public synchronized void drawMinPriceNode(Node minPriceNode) {
		minPriceNode.getTable().getItem(0).setBackground(1,
				new Color(display, 50, 200, 50));
		minPriceNode.getTable().getItem(0).setText(1,
				Integer.toString(minPriceNode.getPrice()));

		minPriceNode.getTable().update();
		minPriceNode.getTable().redraw();
		try {
			Thread.sleep(Config.delay * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw winner path.
	 * 
	 * @param endNode
	 *            the end node
	 * @param shortestList
	 *            the shortest list
	 */
	public void drawWinnerPath(Node endNode, List<Node> shortestList) {	
		
		Color color = display.getSystemColor(SWT.COLOR_RED);
		
		drawChildSearchNode(endNode, shortestList.get(0), color);
		
		for (int i = 0; i < shortestList.size(); i++) {			
			drawRecursion(shortestList.get(i), color);
		}
		try {
			Thread.sleep(Config.delay * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw recursion.
	 * 
	 * @param actualNode
	 *            the actual node
	 * @param color
	 *            the color
	 */
	private void drawRecursion(Node actualNode, Color color) {

		Node previousNode = actualNode.getPreviousNode();

		if (previousNode != null) { // root
			drawChildSearchNode(actualNode, previousNode, color);
			drawRecursion(previousNode, color);
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Solve circle position.
	 * 
	 * @param angle
	 *            the angle
	 * @param radius
	 *            the radius
	 * 
	 * @return the point
	 */
	private Point solveCirclePosition(double angle, int radius) {

		double sin = Math.sin(angle);
		double cos = Math.cos(angle);

		int x = (int) (radius * cos); // prilehla-x
		int y = (int) (radius * sin); // protilehla-y

		Point result = new Point(center.x + scroll.x + x, center.y + scroll.y
				+ y);

		return result;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Sets the scrollbars.
	 * 
	 * @param newSize
	 *            the new scrollbars
	 */
	private void setScrollbars(Rectangle newSize) {

		if (newSize != null) {
			clientArea = newSize;
		}
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.setMaximum(clientArea.width);
		hBar.setSelection(clientArea.width / 2);

		hBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				int hSelection = hBar.getSelection();
				int destX = hSelection - scrollBar.x;

				canvas.scroll(destX, 0, 0, 0, clientArea.width,
						clientArea.height, true);
				scrollBar.x = hSelection;
				scroll.x = -(center.x - scrollBar.x);
				canvas.redraw();
				System.err.println("scroll.x" + scroll.x);
				System.out.println("HBar listener origin.x =" + scrollBar.x);
			}
		});
		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.setMaximum(clientArea.height);
		vBar.setSelection(clientArea.height / 2);

		vBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();
				int destY = vSelection - scrollBar.y;
				canvas.scroll(0, destY, 0, 0, clientArea.width,
						clientArea.height, true);
				scrollBar.y = vSelection;
				scroll.y = -(center.y - scrollBar.y);
				canvas.redraw();
				System.err.println("scroll.y" + scroll.y);
				System.out.println("VBar listener origin.y =" + scrollBar.y);
			}
		});
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the display.
	 * 
	 * @return the display
	 */
	public Display getDisplay() {
		return display;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Checks if is paint allowed.
	 * 
	 * @return true, if is paint allowed
	 */
	public boolean isPaintAllowed() {
		return paintAllowed;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Sets the paint allowed.
	 * 
	 * @param paintAllowed
	 *            the new paint allowed
	 */
	public void setPaintAllowed(boolean paintAllowed) {
		this.paintAllowed = paintAllowed;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the canvas.
	 * 
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
}
