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
package eu.kalafatic.gemini.bt.tracker.controller.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;

/**
 * The listener interface for receiving table events. The class that is interested in processing a table event implements this interface, and the
 * object created with that class is registered with a component using the component's <code>addTableListener<code> method. When
 * the table event occurs, that object's appropriate
 * method is invoked.
 * @see TableEvent
 */
public class TableListener implements Listener {

	/** The blue. */
	private Color blue = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);

	/** The black. */
	private Color black = Display.getDefault().getSystemColor(SWT.COLOR_RED);

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		switch (event.type) {
		case SWT.MeasureItem: {
			TableItem item = (TableItem) event.item;
			String text = item.getText(event.index);
			Point size = event.gc.textExtent(text);
			event.width = size.x;
			event.height = Math.max(event.height, size.y);
			break;
		}
		case SWT.PaintItem: {
			TableItem item = (TableItem) event.item;
			String text = item.getText(event.index);
			Point size = event.gc.textExtent(text);
			int offset2 = event.index == 0 ? Math.max(0, (event.height - size.y) / 2) : 0;

			String[] text2 = item.getText(event.index).split("\n");

			if (text2.length > 1) {
				event.gc.setForeground(blue);
				event.gc.drawText(text2[0], event.x, event.y + offset2, true);

				event.gc.setForeground(black);
				event.gc.drawText(text2[1], event.x, event.y + size.y / 2, true);

			} else {

				event.gc.drawText(text, event.x, event.y + offset2, true);
			}

			break;
		}
		case SWT.EraseItem: {
			event.detail &= ~SWT.FOREGROUND;
			break;
		}
		}
	}
}
