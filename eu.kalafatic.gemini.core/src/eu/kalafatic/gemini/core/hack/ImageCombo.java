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
package eu.kalafatic.gemini.core.hack;

/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Tom Seidel      - enhancements for image-handling
 *******************************************************************************/

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleTextAdapter;
import org.eclipse.swt.accessibility.AccessibleTextEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;

/**
 * The Class class ImageCombo.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public final class ImageCombo extends Composite {

	/** The text. */
	Text text;

	/** The table. */
	Table table;

	/** The visible item count. */
	int visibleItemCount = 5;

	/** The popup. */
	Shell popup;

	/** The arrow. */
	Button arrow;

	/** The has focus. */
	boolean hasFocus;

	/** The filter. */
	Listener listener, filter;

	/** The background. */
	Color foreground, background;

	/** The font. */
	Font font;

	/**
	 * Instantiates a new image combo.
	 * @param parent the parent
	 * @param style the style
	 */
	public ImageCombo(Composite parent, int style) {
		super(parent, style = checkStyle(style));

		int textStyle = SWT.SINGLE;
		if ((style & SWT.READ_ONLY) != 0) {
			textStyle |= SWT.READ_ONLY;
		}
		if ((style & SWT.FLAT) != 0) {
			textStyle |= SWT.FLAT;
		}
		text = new Text(this, SWT.NONE);
		int arrowStyle = SWT.ARROW | SWT.DOWN;
		if ((style & SWT.FLAT) != 0) {
			arrowStyle |= SWT.FLAT;
		}
		arrow = new Button(this, arrowStyle);

		listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (popup == event.widget) {
					popupEvent(event);
					return;
				}
				if (text == event.widget) {
					textEvent(event);
					return;
				}
				if (table == event.widget) {
					listEvent(event);
					return;
				}
				if (arrow == event.widget) {
					arrowEvent(event);
					return;
				}
				if (ImageCombo.this == event.widget) {
					comboEvent(event);
					return;
				}
				if (getShell() == event.widget) {
					handleFocus(SWT.FocusOut);
				}
			}
		};
		filter = new Listener() {
			@Override
			public void handleEvent(Event event) {
				Shell shell = ((Control) event.widget).getShell();
				if (shell == ImageCombo.this.getShell()) {
					handleFocus(SWT.FocusOut);
				}
			}
		};

		int[] comboEvents = { SWT.Dispose, SWT.Move, SWT.Resize };
		for (int i = 0; i < comboEvents.length; i++) {
			this.addListener(comboEvents[i], listener);
		}

		int[] textEvents = { SWT.KeyDown, SWT.KeyUp, SWT.Modify, SWT.MouseDown, SWT.MouseUp, SWT.Traverse, SWT.FocusIn };
		for (int i = 0; i < textEvents.length; i++) {
			text.addListener(textEvents[i], listener);
		}

		int[] arrowEvents = { SWT.Selection, SWT.FocusIn };
		for (int i = 0; i < arrowEvents.length; i++) {
			arrow.addListener(arrowEvents[i], listener);
		}

		createPopup(-1);
		initAccessible();
	}

	/**
	 * Check style.
	 * @param style the style
	 * @return the int
	 */
	static int checkStyle(int style) {
		int mask = SWT.BORDER | SWT.READ_ONLY | SWT.FLAT | SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
		return style & mask;
	}

	/**
	 * Adds the.
	 * @param string the string
	 * @param image the image
	 */
	public void add(String string, Image image) {
		checkWidget();
		if (string == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		TableItem newItem = new TableItem(this.table, SWT.NONE);
		newItem.setText(string);
		if (image != null) {
			newItem.setImage(image);
		}
	}

	/**
	 * Adds the.
	 * @param string the string
	 * @param image the image
	 * @param index the index
	 */
	public void add(String string, Image image, int index) {
		checkWidget();
		if (string == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		TableItem newItem = new TableItem(this.table, SWT.NONE, index);
		if (image != null) {
			newItem.setImage(image);
		}
	}

	/**
	 * Adds the modify listener.
	 * @param listener the listener
	 */
	public void addModifyListener(ModifyListener listener) {
		checkWidget();
		if (listener == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Modify, typedListener);
	}

	/**
	 * Adds the selection listener.
	 * @param listener the listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		if (listener == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Selection, typedListener);
		addListener(SWT.DefaultSelection, typedListener);
	}

	/**
	 * Arrow event.
	 * @param event the event
	 */
	void arrowEvent(Event event) {
		switch (event.type) {
		case SWT.FocusIn: {
			handleFocus(SWT.FocusIn);
			break;
		}
		case SWT.Selection: {
			dropDown(!isDropped());
			break;
		}
		}
	}

	/**
	 * Clear selection.
	 */
	public void clearSelection() {
		checkWidget();
		text.clearSelection();
		table.deselectAll();
	}

	/**
	 * Combo event.
	 * @param event the event
	 */
	void comboEvent(Event event) {
		switch (event.type) {
		case SWT.Dispose:
			if (popup != null && !popup.isDisposed()) {
				table.removeListener(SWT.Dispose, listener);
				popup.dispose();
			}
			Shell shell = getShell();
			shell.removeListener(SWT.Deactivate, listener);
			Display display = getDisplay();
			display.removeFilter(SWT.FocusIn, filter);
			popup = null;
			text = null;
			table = null;
			arrow = null;
			break;
		case SWT.Move:
			dropDown(false);
			break;
		case SWT.Resize:
			internalLayout(false);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#computeSize(int, int, boolean)
	 */
	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		checkWidget();
		int width = 0, height = 0;
		String[] items = getStringsFromTable();
		int textWidth = 0;
		GC gc = new GC(text);
		int spacer = gc.stringExtent(" ").x; //$NON-NLS-1$
		for (int i = 0; i < items.length; i++) {
			textWidth = Math.max(gc.stringExtent(items[i]).x, textWidth);
		}
		gc.dispose();
		Point textSize = text.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
		Point arrowSize = arrow.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
		Point listSize = table.computeSize(wHint, SWT.DEFAULT, changed);
		int borderWidth = getBorderWidth();

		height = Math.max(hHint, Math.max(textSize.y, arrowSize.y) + 2 * borderWidth);
		width = Math.max(wHint, Math.max(textWidth + 2 * spacer + arrowSize.x + 2 * borderWidth, listSize.x));
		return new Point(width, height);
	}

	/**
	 * Creates the popup.
	 * @param selectionIndex the selection index
	 */
	void createPopup(int selectionIndex) {
		// create shell and list
		popup = new Shell(getShell(), SWT.NO_TRIM | SWT.ON_TOP);
		int style = getStyle();
		int listStyle = SWT.SINGLE | SWT.V_SCROLL;
		if ((style & SWT.FLAT) != 0) {
			listStyle |= SWT.FLAT;
		}
		if ((style & SWT.RIGHT_TO_LEFT) != 0) {
			listStyle |= SWT.RIGHT_TO_LEFT;
		}
		if ((style & SWT.LEFT_TO_RIGHT) != 0) {
			listStyle |= SWT.LEFT_TO_RIGHT;
		}
		// create a table instead of a list.
		table = new Table(popup, listStyle);
		if (font != null) {
			table.setFont(font);
		}
		if (foreground != null) {
			table.setForeground(foreground);
		}
		if (background != null) {
			table.setBackground(background);
		}

		int[] popupEvents = { SWT.Close, SWT.Paint, SWT.Deactivate };
		for (int i = 0; i < popupEvents.length; i++) {
			popup.addListener(popupEvents[i], listener);
		}
		int[] listEvents = { SWT.MouseUp, SWT.Selection, SWT.Traverse, SWT.KeyDown, SWT.KeyUp, SWT.FocusIn, SWT.Dispose };
		for (int i = 0; i < listEvents.length; i++) {
			table.addListener(listEvents[i], listener);
		}

		if (selectionIndex != -1) {
			table.setSelection(selectionIndex);
		}
	}

	/**
	 * Deselect.
	 * @param index the index
	 */
	public void deselect(int index) {
		checkWidget();
		table.deselect(index);
	}

	/**
	 * Deselect all.
	 */
	public void deselectAll() {
		checkWidget();
		table.deselectAll();
	}

	/**
	 * Drop down.
	 * @param drop the drop
	 */
	void dropDown(boolean drop) {
		if (drop == isDropped()) {
			return;
		}
		if (!drop) {
			popup.setVisible(false);
			if (!isDisposed() && arrow.isFocusControl()) {
				text.setFocus();
			}
			return;
		}

		if (getShell() != popup.getParent()) {
			TableItem[] items = table.getItems();
			int selectionIndex = table.getSelectionIndex();
			table.removeListener(SWT.Dispose, listener);
			popup.dispose();
			popup = null;
			table = null;
			createPopup(selectionIndex);
		}

		Point size = getSize();
		int itemCount = table.getItemCount();
		itemCount = (itemCount == 0) ? visibleItemCount : Math.min(visibleItemCount, itemCount);
		int itemHeight = table.getItemHeight() * itemCount;
		Point listSize = table.computeSize(SWT.DEFAULT, itemHeight, false);
		table.setBounds(1, 1, Math.max(size.x - 2, listSize.x), listSize.y);

		int index = table.getSelectionIndex();
		if (index != -1) {
			table.setTopIndex(index);
		}
		Display display = getDisplay();
		Rectangle listRect = table.getBounds();
		Rectangle parentRect = display.map(getParent(), null, getBounds());
		Point comboSize = getSize();
		Rectangle displayRect = getMonitor().getClientArea();
		int width = Math.max(comboSize.x, listRect.width + 2);
		int height = listRect.height + 2;
		int x = parentRect.x;
		int y = parentRect.y + comboSize.y;
		if (y + height > displayRect.y + displayRect.height) {
			y = parentRect.y - height;
		}
		popup.setBounds(x, y, width, height);
		popup.setVisible(true);
		table.setFocus();
	}

	/*
	 * Return the Label immediately preceding the receiver in the z-order, or null if none.
	 */
	/**
	 * Gets the associated label.
	 * @return the associated label
	 */
	Label getAssociatedLabel() {
		Control[] siblings = getParent().getChildren();
		for (int i = 0; i < siblings.length; i++) {
			if (siblings[i] == ImageCombo.this) {
				if (i > 0 && siblings[i - 1] instanceof Label) {
					return (Label) siblings[i - 1];
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#getChildren()
	 */
	@Override
	public Control[] getChildren() {
		checkWidget();
		return new Control[0];
	}

	/**
	 * Gets the editable.
	 * @return the editable
	 */
	public boolean getEditable() {
		checkWidget();
		return text.getEditable();
	}

	/**
	 * Gets the item.
	 * @param index the index
	 * @return the item
	 */
	public TableItem getItem(int index) {
		checkWidget();
		return this.table.getItem(index);
	}

	/**
	 * Gets the item count.
	 * @return the item count
	 */
	public int getItemCount() {
		checkWidget();
		return table.getItemCount();
	}

	/**
	 * Gets the item height.
	 * @return the item height
	 */
	public int getItemHeight() {
		checkWidget();
		return table.getItemHeight();
	}

	/**
	 * Gets the items.
	 * @return the items
	 */
	public TableItem[] getItems() {
		checkWidget();
		return table.getItems();
	}

	/**
	 * Gets the mnemonic.
	 * @param string the string
	 * @return the mnemonic
	 */
	char getMnemonic(String string) {
		int index = 0;
		int length = string.length();
		do {
			while ((index < length) && (string.charAt(index) != '&')) {
				index++;
			}
			if (++index >= length) {
				return '\0';
			}
			if (string.charAt(index) != '&') {
				return string.charAt(index);
			}
			index++;
		} while (index < length);
		return '\0';
	}

	/**
	 * Gets the strings from table.
	 * @return the strings from table
	 */
	String[] getStringsFromTable() {
		String[] items = new String[this.table.getItems().length];
		for (int i = 0, n = items.length; i < n; i++) {
			items[i] = this.table.getItem(i).getText();
		}
		return items;
	}

	/**
	 * Gets the selection.
	 * @return the selection
	 */
	public Point getSelection() {
		checkWidget();
		return text.getSelection();
	}

	/**
	 * Gets the selection index.
	 * @return the selection index
	 */
	public int getSelectionIndex() {
		checkWidget();
		return table.getSelectionIndex();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#getStyle()
	 */
	@Override
	public int getStyle() {
		int style = super.getStyle();
		style &= ~SWT.READ_ONLY;
		if (!text.getEditable()) {
			style |= SWT.READ_ONLY;
		}
		return style;
	}

	/**
	 * Gets the text.
	 * @return the text
	 */
	public String getText() {
		checkWidget();
		return text.getText();
	}

	/**
	 * Gets the text height.
	 * @return the text height
	 */
	public int getTextHeight() {
		checkWidget();
		return text.getLineHeight();
	}

	/**
	 * Gets the text limit.
	 * @return the text limit
	 */
	public int getTextLimit() {
		checkWidget();
		return text.getTextLimit();
	}

	/**
	 * Gets the visible item count.
	 * @return the visible item count
	 */
	public int getVisibleItemCount() {
		checkWidget();
		return visibleItemCount;
	}

	/**
	 * Handle focus.
	 * @param type the type
	 */
	void handleFocus(int type) {
		if (isDisposed()) {
			return;
		}
		switch (type) {
		case SWT.FocusIn: {
			if (hasFocus) {
				return;
			}
			if (getEditable()) {
				text.selectAll();
			}
			hasFocus = true;
			Shell shell = getShell();
			shell.removeListener(SWT.Deactivate, listener);
			shell.addListener(SWT.Deactivate, listener);
			Display display = getDisplay();
			display.removeFilter(SWT.FocusIn, filter);
			display.addFilter(SWT.FocusIn, filter);
			Event e = new Event();
			notifyListeners(SWT.FocusIn, e);
			break;
		}
		case SWT.FocusOut: {
			if (!hasFocus) {
				return;
			}
			Control focusControl = getDisplay().getFocusControl();
			if (focusControl == arrow || focusControl == table || focusControl == text) {
				return;
			}
			hasFocus = false;
			Shell shell = getShell();
			shell.removeListener(SWT.Deactivate, listener);
			Display display = getDisplay();
			display.removeFilter(SWT.FocusIn, filter);
			Event e = new Event();
			notifyListeners(SWT.FocusOut, e);
			break;
		}
		}
	}

	/**
	 * Index of.
	 * @param string the string
	 * @return the int
	 */
	public int indexOf(String string) {
		checkWidget();
		if (string == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		return Arrays.asList(getStringsFromTable()).indexOf(string);
	}

	/**
	 * Inits the accessible.
	 */
	void initAccessible() {
		AccessibleAdapter accessibleAdapter = new AccessibleAdapter() {
			@Override
			public void getName(AccessibleEvent e) {
				String name = null;
				Label label = getAssociatedLabel();
				if (label != null) {
					name = stripMnemonic(label.getText());
				}
				e.result = name;
			}

			@Override
			public void getKeyboardShortcut(AccessibleEvent e) {
				String shortcut = null;
				Label label = getAssociatedLabel();
				if (label != null) {
					String text = label.getText();
					if (text != null) {
						char mnemonic = getMnemonic(text);
						if (mnemonic != '\0') {
							shortcut = "Alt+" + mnemonic; //$NON-NLS-1$
						}
					}
				}
				e.result = shortcut;
			}

			@Override
			public void getHelp(AccessibleEvent e) {
				e.result = getToolTipText();
			}
		};
		getAccessible().addAccessibleListener(accessibleAdapter);
		text.getAccessible().addAccessibleListener(accessibleAdapter);
		table.getAccessible().addAccessibleListener(accessibleAdapter);

		arrow.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			@Override
			public void getName(AccessibleEvent e) {
				e.result = isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			@Override
			public void getKeyboardShortcut(AccessibleEvent e) {
				e.result = "Alt+Down Arrow"; //$NON-NLS-1$
			}

			@Override
			public void getHelp(AccessibleEvent e) {
				e.result = getToolTipText();
			}
		});

		getAccessible().addAccessibleTextListener(new AccessibleTextAdapter() {
			@Override
			public void getCaretOffset(AccessibleTextEvent e) {
				e.offset = text.getCaretPosition();
			}
		});

		getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
			@Override
			public void getChildAtPoint(AccessibleControlEvent e) {
				Point testPoint = toControl(e.x, e.y);
				if (getBounds().contains(testPoint)) {
					e.childID = ACC.CHILDID_SELF;
				}
			}

			@Override
			public void getLocation(AccessibleControlEvent e) {
				Rectangle location = getBounds();
				Point pt = toDisplay(location.x, location.y);
				e.x = pt.x;
				e.y = pt.y;
				e.width = location.width;
				e.height = location.height;
			}

			@Override
			public void getChildCount(AccessibleControlEvent e) {
				e.detail = 0;
			}

			@Override
			public void getRole(AccessibleControlEvent e) {
				e.detail = ACC.ROLE_COMBOBOX;
			}

			@Override
			public void getState(AccessibleControlEvent e) {
				e.detail = ACC.STATE_NORMAL;
			}

			@Override
			public void getValue(AccessibleControlEvent e) {
				e.result = getText();
			}
		});

		text.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
			@Override
			public void getRole(AccessibleControlEvent e) {
				e.detail = text.getEditable() ? ACC.ROLE_TEXT : ACC.ROLE_LABEL;
			}
		});

		arrow.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
			@Override
			public void getDefaultAction(AccessibleControlEvent e) {
				e.result = isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		});
	}

	/**
	 * Checks if is dropped.
	 * @return true, if is dropped
	 */
	boolean isDropped() {
		return popup.getVisible();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#isFocusControl()
	 */
	@Override
	public boolean isFocusControl() {
		checkWidget();
		if (text.isFocusControl() || arrow.isFocusControl() || table.isFocusControl() || popup.isFocusControl()) {
			return true;
		}
		return super.isFocusControl();
	}

	/**
	 * Internal layout.
	 * @param changed the changed
	 */
	void internalLayout(boolean changed) {
		if (isDropped()) {
			dropDown(false);
		}
		Rectangle rect = getClientArea();
		int width = rect.width;
		int height = rect.height;
		Point arrowSize = arrow.computeSize(SWT.DEFAULT, height, changed);
		text.setBounds(0, 0, width - arrowSize.x, height);
		arrow.setBounds(width - arrowSize.x, 0, arrowSize.x, arrowSize.y);
	}

	/**
	 * List event.
	 * @param event the event
	 */
	void listEvent(Event event) {
		switch (event.type) {
		case SWT.Dispose:
			if (getShell() != popup.getParent()) {
				TableItem[] items = table.getItems();
				int selectionIndex = table.getSelectionIndex();
				popup = null;
				table = null;
				createPopup(selectionIndex);
			}
			break;
		case SWT.FocusIn: {
			handleFocus(SWT.FocusIn);
			break;
		}
		case SWT.MouseUp: {
			if (event.button != 1) {
				return;
			}
			dropDown(false);
			break;
		}
		case SWT.Selection: {
			int index = table.getSelectionIndex();
			if (index == -1) {
				return;
			}
			text.setText(table.getItem(index).getText());
			text.selectAll();
			table.setSelection(index);
			Event e = new Event();
			e.time = event.time;
			e.stateMask = event.stateMask;
			e.doit = event.doit;
			notifyListeners(SWT.Selection, e);
			event.doit = e.doit;
			break;
		}
		case SWT.Traverse: {
			switch (event.detail) {
			case SWT.TRAVERSE_RETURN:
			case SWT.TRAVERSE_ESCAPE:
			case SWT.TRAVERSE_ARROW_PREVIOUS:
			case SWT.TRAVERSE_ARROW_NEXT:
				event.doit = false;
				break;
			}
			Event e = new Event();
			e.time = event.time;
			e.detail = event.detail;
			e.doit = event.doit;
			e.character = event.character;
			e.keyCode = event.keyCode;
			notifyListeners(SWT.Traverse, e);
			event.doit = e.doit;
			event.detail = e.detail;
			break;
		}
		case SWT.KeyUp: {
			Event e = new Event();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners(SWT.KeyUp, e);
			break;
		}
		case SWT.KeyDown: {
			if (event.character == SWT.ESC) {
				// Escape key cancels popup list
				dropDown(false);
			}
			if ((event.stateMask & SWT.ALT) != 0 && (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN)) {
				dropDown(false);
			}
			if (event.character == SWT.CR) {
				// Enter causes default selection
				dropDown(false);
				Event e = new Event();
				e.time = event.time;
				e.stateMask = event.stateMask;
				notifyListeners(SWT.DefaultSelection, e);
			}
			// At this point the widget may have been disposed.
			// If so, do not continue.
			if (isDisposed()) {
				break;
			}
			Event e = new Event();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners(SWT.KeyDown, e);
			break;

		}
		}
	}

	/**
	 * Popup event.
	 * @param event the event
	 */
	void popupEvent(Event event) {
		switch (event.type) {
		case SWT.Paint:
			// draw black rectangle around list
			Rectangle listRect = table.getBounds();
			Color black = getDisplay().getSystemColor(SWT.COLOR_BLACK);
			event.gc.setForeground(black);
			event.gc.drawRectangle(0, 0, listRect.width + 1, listRect.height + 1);
			break;
		case SWT.Close:
			event.doit = false;
			dropDown(false);
			break;
		case SWT.Deactivate:
			dropDown(false);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#redraw()
	 */
	@Override
	public void redraw() {
		super.redraw();
		text.redraw();
		arrow.redraw();
		if (popup.isVisible()) {
			table.redraw();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#redraw(int, int, int, int, boolean)
	 */
	@Override
	public void redraw(int x, int y, int width, int height, boolean all) {
		super.redraw(x, y, width, height, true);
	}

	/**
	 * Removes the.
	 * @param index the index
	 */
	public void remove(int index) {
		checkWidget();
		table.remove(index);
	}

	/**
	 * Removes the.
	 * @param start the start
	 * @param end the end
	 */
	public void remove(int start, int end) {
		checkWidget();
		table.remove(start, end);
	}

	/**
	 * Removes the.
	 * @param string the string
	 */
	public void remove(String string) {
		checkWidget();
		if (string == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		int index = -1;
		for (int i = 0, n = table.getItemCount(); i < n; i++) {
			if (table.getItem(i).getText().equals(string)) {
				index = i;
				break;
			}
		}
		remove(index);
	}

	/**
	 * Removes the all.
	 */
	public void removeAll() {
		checkWidget();
		text.setText(""); //$NON-NLS-1$
		table.removeAll();
	}

	/**
	 * Removes the modify listener.
	 * @param listener the listener
	 */
	public void removeModifyListener(ModifyListener listener) {
		checkWidget();
		if (listener == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		removeListener(SWT.Modify, listener);
	}

	/**
	 * Removes the selection listener.
	 * @param listener the listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		if (listener == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		removeListener(SWT.Selection, listener);
		removeListener(SWT.DefaultSelection, listener);
	}

	/**
	 * Select.
	 * @param index the index
	 */
	public void select(int index) {
		checkWidget();
		if (index == -1) {
			table.deselectAll();
			text.setText(""); //$NON-NLS-1$
			return;
		}
		if (0 <= index && index < table.getItemCount()) {
			if (index != getSelectionIndex()) {
				text.setText(table.getItem(index).getText());
				text.selectAll();
				table.select(index);
				table.showSelection();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		background = color;
		if (text != null) {
			text.setBackground(color);
		}
		if (table != null) {
			table.setBackground(color);
		}
		if (arrow != null) {
			arrow.setBackground(color);
		}
	}

	/**
	 * Sets the editable.
	 * @param editable the new editable
	 */
	public void setEditable(boolean editable) {
		checkWidget();
		text.setEditable(editable);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (popup != null) {
			popup.setVisible(false);
		}
		if (text != null) {
			text.setEnabled(enabled);
		}
		if (arrow != null) {
			arrow.setEnabled(enabled);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#setFocus()
	 */
	@Override
	public boolean setFocus() {
		checkWidget();
		return text.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setFont(org.eclipse.swt.graphics.Font)
	 */
	@Override
	public void setFont(Font font) {
		super.setFont(font);
		this.font = font;
		text.setFont(font);
		table.setFont(font);
		internalLayout(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setForeground(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setForeground(Color color) {
		super.setForeground(color);
		foreground = color;
		if (text != null) {
			text.setForeground(color);
		}
		if (table != null) {
			table.setForeground(color);
		}
		if (arrow != null) {
			arrow.setForeground(color);
		}
	}

	/**
	 * Sets the item.
	 * @param index the index
	 * @param string the string
	 * @param image the image
	 */
	public void setItem(int index, String string, Image image) {
		checkWidget();
		remove(index);
		add(string, image, index);
	}

	/**
	 * Sets the items.
	 * @param items the new items
	 */
	public void setItems(String[] items) {
		checkWidget();
		this.table.removeAll();
		for (int i = 0, n = items.length; i < n; i++) {
			add(items[i], null);
		}
		if (!text.getEditable()) {
			text.setText(""); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#setLayout(org.eclipse.swt.widgets.Layout)
	 */
	@Override
	public void setLayout(Layout layout) {
		checkWidget();
		return;
	}

	/**
	 * Sets the selection.
	 * @param selection the new selection
	 */
	public void setSelection(Point selection) {
		checkWidget();
		if (selection == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		text.setSelection(selection.x, selection.y);
	}

	/**
	 * Sets the text.
	 * @param string the new text
	 */
	public void setText(String string) {
		checkWidget();
		if (string == null) {
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		}
		int index = -1;
		for (int i = 0, n = table.getItemCount(); i < n; i++) {
			if (table.getItem(i).getText().equals(string)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			table.deselectAll();
			text.setText(string);
			return;
		}
		text.setText(string);
		text.selectAll();
		table.setSelection(index);
		table.showSelection();
	}

	/**
	 * Sets the text limit.
	 * @param limit the new text limit
	 */
	public void setTextLimit(int limit) {
		checkWidget();
		text.setTextLimit(limit);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setToolTipText(java.lang.String)
	 */
	@Override
	public void setToolTipText(String string) {
		checkWidget();
		super.setToolTipText(string);
		arrow.setToolTipText(string);
		text.setToolTipText(string);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (!visible) {
			popup.setVisible(false);
		}
	}

	/**
	 * Sets the visible item count.
	 * @param count the new visible item count
	 */
	public void setVisibleItemCount(int count) {
		checkWidget();
		if (count < 0) {
			return;
		}
		visibleItemCount = count;
	}

	/**
	 * Strip mnemonic.
	 * @param string the string
	 * @return the string
	 */
	String stripMnemonic(String string) {
		int index = 0;
		int length = string.length();
		do {
			while ((index < length) && (string.charAt(index) != '&')) {
				index++;
			}
			if (++index >= length) {
				return string;
			}
			if (string.charAt(index) != '&') {
				return string.substring(0, index - 1) + string.substring(index, length);
			}
			index++;
		} while (index < length);
		return string;
	}

	/**
	 * Text event.
	 * @param event the event
	 */
	void textEvent(Event event) {
		switch (event.type) {
		case SWT.FocusIn: {
			handleFocus(SWT.FocusIn);
			break;
		}
		case SWT.KeyDown: {
			if (event.character == SWT.CR) {
				dropDown(false);
				Event e = new Event();
				e.time = event.time;
				e.stateMask = event.stateMask;
				notifyListeners(SWT.DefaultSelection, e);
			}
			// At this point the widget may have been disposed.
			// If so, do not continue.
			if (isDisposed()) {
				break;
			}

			if (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN) {
				event.doit = false;
				if ((event.stateMask & SWT.ALT) != 0) {
					boolean dropped = isDropped();
					text.selectAll();
					if (!dropped) {
						setFocus();
					}
					dropDown(!dropped);
					break;
				}

				int oldIndex = getSelectionIndex();
				if (event.keyCode == SWT.ARROW_UP) {
					select(Math.max(oldIndex - 1, 0));
				} else {
					select(Math.min(oldIndex + 1, getItemCount() - 1));
				}
				if (oldIndex != getSelectionIndex()) {
					Event e = new Event();
					e.time = event.time;
					e.stateMask = event.stateMask;
					notifyListeners(SWT.Selection, e);
				}
				// At this point the widget may have been disposed.
				// If so, do not continue.
				if (isDisposed()) {
					break;
				}
			}

			// Further work : Need to add support for incremental search in
			// pop up list as characters typed in text widget

			Event e = new Event();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners(SWT.KeyDown, e);
			break;
		}
		case SWT.KeyUp: {
			Event e = new Event();
			e.time = event.time;
			e.character = event.character;
			e.keyCode = event.keyCode;
			e.stateMask = event.stateMask;
			notifyListeners(SWT.KeyUp, e);
			break;
		}
		case SWT.Modify: {
			table.deselectAll();
			Event e = new Event();
			e.time = event.time;
			notifyListeners(SWT.Modify, e);
			break;
		}
		case SWT.MouseDown: {
			if (event.button != 1) {
				return;
			}
			if (text.getEditable()) {
				return;
			}
			boolean dropped = isDropped();
			text.selectAll();
			if (!dropped) {
				setFocus();
			}
			dropDown(!dropped);
			break;
		}
		case SWT.MouseUp: {
			if (event.button != 1) {
				return;
			}
			if (text.getEditable()) {
				return;
			}
			text.selectAll();
			break;
		}
		case SWT.Traverse: {
			switch (event.detail) {
			case SWT.TRAVERSE_RETURN:
			case SWT.TRAVERSE_ARROW_PREVIOUS:
			case SWT.TRAVERSE_ARROW_NEXT:
				// The enter causes default selection and
				// the arrow keys are used to manipulate the list contents so
				// do not use them for traversal.
				event.doit = false;
				break;
			}

			Event e = new Event();
			e.time = event.time;
			e.detail = event.detail;
			e.doit = event.doit;
			e.character = event.character;
			e.keyCode = event.keyCode;
			notifyListeners(SWT.Traverse, e);
			event.doit = e.doit;
			event.detail = e.detail;
			break;
		}
		}
	}
}
