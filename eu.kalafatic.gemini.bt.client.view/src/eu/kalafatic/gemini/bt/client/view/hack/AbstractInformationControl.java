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
package eu.kalafatic.gemini.bt.client.view.hack;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.text.revisions.Colors;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IInformationControlExtension;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.text.IInformationControlExtension3;
import org.eclipse.jface.text.IInformationControlExtension4;
import org.eclipse.jface.text.IInformationControlExtension5;
import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ToolBar;

/**
 * The Class class AbstractInformationControl.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public abstract class AbstractInformationControl implements IInformationControl, IInformationControlExtension, IInformationControlExtension2, IInformationControlExtension3,
		IInformationControlExtension4, IInformationControlExtension5 {

	/** The shell. */
	private final Shell fShell;

	/** The content composite. */
	private final Composite fContentComposite;

	/** The resizable. */
	private final boolean fResizable;

	/** The status composite. */
	private Composite fStatusComposite;

	/** The separator. */
	private Label fSeparator;

	/** The status label. */
	private Label fStatusLabel;

	/** The status label font. */
	private Font fStatusLabelFont;

	/** The status label foreground. */
	private Color fStatusLabelForeground;

	/** The tool bar manager. */
	private final ToolBarManager fToolBarManager;

	/** The tool bar. */
	private ToolBar fToolBar;

	/** The shell listener. */
	private Listener fShellListener;

	/** The focus listeners. */
	private final ListenerList fFocusListeners = new ListenerList(ListenerList.IDENTITY);

	/** The size constraints. */
	private Point fSizeConstraints;

	/** The resize handle size. */
	private int fResizeHandleSize;

	/**
	 * Instantiates a new abstract information control.
	 * @param parentShell the parent shell
	 * @param statusFieldText the status field text
	 */
	public AbstractInformationControl(Shell parentShell, String statusFieldText) {
		this(parentShell, SWT.TOOL | SWT.ON_TOP, statusFieldText, null);
	}

	/**
	 * Instantiates a new abstract information control.
	 * @param parentShell the parent shell
	 * @param toolBarManager the tool bar manager
	 */
	public AbstractInformationControl(Shell parentShell, ToolBarManager toolBarManager) {
		this(parentShell, SWT.TOOL | SWT.ON_TOP | SWT.RESIZE, null, toolBarManager);
	}

	/**
	 * Instantiates a new abstract information control.
	 * @param parentShell the parent shell
	 * @param isResizable the is resizable
	 */
	public AbstractInformationControl(Shell parentShell, boolean isResizable) {
		this(parentShell, SWT.TOOL | SWT.ON_TOP | (isResizable ? SWT.RESIZE : 0), null, null);
	}

	/**
	 * Instantiates a new abstract information control.
	 * @param parentShell the parent shell
	 * @param shellStyle the shell style
	 * @param statusFieldText the status field text
	 * @param toolBarManager the tool bar manager
	 */
	AbstractInformationControl(Shell parentShell, int shellStyle, final String statusFieldText, final ToolBarManager toolBarManager) {
		Assert.isTrue(statusFieldText == null || toolBarManager == null);
		fResizeHandleSize = -1;
		fToolBarManager = toolBarManager;

		if ((shellStyle & SWT.NO_TRIM) != 0) {
			shellStyle &= ~(SWT.NO_TRIM | SWT.SHELL_TRIM);
		}

		fResizable = (shellStyle & SWT.RESIZE) != 0;
		// fShell = new Shell(parentShell, shellStyle);
		// Display display = fShell.getDisplay();
		Color foreground = Display.getDefault().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
		Color background = Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
		// setColor(fShell, foreground, background);

		fShell = parentShell;

		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		fShell.setLayout(layout);

		fContentComposite = new Composite(fShell, SWT.NONE);
		fContentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		// fContentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		fContentComposite.setLayout(new FillLayout());
		setColor(fContentComposite, foreground, background);

		createStatusComposite(statusFieldText, toolBarManager, foreground, background);

		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				handleDispose();
			}
		});

	}

	/**
	 * Creates the status composite.
	 * @param statusFieldText the status field text
	 * @param toolBarManager the tool bar manager
	 * @param foreground the foreground
	 * @param background the background
	 */
	private void createStatusComposite(final String statusFieldText, final ToolBarManager toolBarManager, Color foreground, Color background) {
		if (toolBarManager == null && statusFieldText == null) {
			return;
		}

		fStatusComposite = new Composite(fShell, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
		fStatusComposite.setLayoutData(gridData);
		GridLayout statusLayout = new GridLayout(1, false);
		statusLayout.marginHeight = 0;
		statusLayout.marginWidth = 0;
		statusLayout.verticalSpacing = 1;
		fStatusComposite.setLayout(statusLayout);

		fSeparator = new Label(fStatusComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		fSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		if (statusFieldText != null) {
			createStatusLabel(statusFieldText, foreground, background);
		} else {
			createToolBar(toolBarManager);
		}
	}

	/**
	 * Creates the status label.
	 * @param statusFieldText the status field text
	 * @param foreground the foreground
	 * @param background the background
	 */
	private void createStatusLabel(final String statusFieldText, Color foreground, Color background) {
		fStatusLabel = new Label(fStatusComposite, SWT.RIGHT);
		fStatusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		fStatusLabel.setText(statusFieldText);

		FontData[] fontDatas = JFaceResources.getDialogFont().getFontData();
		for (int i = 0; i < fontDatas.length; i++) {
			fontDatas[i].setHeight(fontDatas[i].getHeight() * 9 / 10);
		}
		fStatusLabelFont = new Font(fStatusLabel.getDisplay(), fontDatas);
		fStatusLabel.setFont(fStatusLabelFont);

		fStatusLabelForeground = new Color(fStatusLabel.getDisplay(), Colors.blend(background.getRGB(), foreground.getRGB(), 0.56f));
		setColor(fStatusLabel, fStatusLabelForeground, background);
		setColor(fStatusComposite, foreground, background);
	}

	/**
	 * Creates the tool bar.
	 * @param toolBarManager the tool bar manager
	 */
	private void createToolBar(ToolBarManager toolBarManager) {
		final Composite bars = new Composite(fStatusComposite, SWT.NONE);
		bars.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		bars.setLayout(layout);

		fToolBar = toolBarManager.createControl(bars);
		GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		fToolBar.setLayoutData(gd);

		Composite spacer = new Composite(bars, SWT.NONE);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.widthHint = 0;
		gd.heightHint = 0;
		spacer.setLayoutData(gd);

		addMoveSupport(spacer);
		addResizeSupportIfNecessary(bars);
	}

	/**
	 * Adds the resize support if necessary.
	 * @param bars the bars
	 */
	private void addResizeSupportIfNecessary(final Composite bars) {
		// XXX: workarounds for
		// - https://bugs.eclipse.org/bugs/show_bug.cgi?id=219139 : API to add
		// resize grip / grow box in lower right corner of shell
		// - https://bugs.eclipse.org/bugs/show_bug.cgi?id=23980 : platform
		// specific shell resize behavior
		String platform = SWT.getPlatform();
		final boolean isWin = platform.equals("win32"); //$NON-NLS-1$
		if (!isWin && !platform.equals("gtk")) {
			return;
		}

		final Canvas resizer = new Canvas(bars, SWT.NONE);

		int size = getResizeHandleSize(bars);

		GridData data = new GridData(SWT.END, SWT.END, false, true);
		data.widthHint = size;
		data.heightHint = size;
		resizer.setLayoutData(data);
		resizer.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Point s = resizer.getSize();
				int x = s.x - 2;
				int y = s.y - 2;
				int min = Math.min(x, y);
				if (isWin) {
					// draw dots
					e.gc.setBackground(resizer.getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
					int end = min - 1;
					for (int i = 0; i <= 2; i++) {
						for (int j = 0; j <= 2 - i; j++) {
							e.gc.fillRectangle(end - 4 * i, end - 4 * j, 2, 2);
						}
					}
					end--;
					e.gc.setBackground(resizer.getDisplay().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
					for (int i = 0; i <= 2; i++) {
						for (int j = 0; j <= 2 - i; j++) {
							e.gc.fillRectangle(end - 4 * i, end - 4 * j, 2, 2);
						}
					}

				} else {
					// draw diagonal lines
					e.gc.setForeground(resizer.getDisplay().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
					for (int i = 1; i < min; i += 4) {
						e.gc.drawLine(i, y, x, i);
					}
					e.gc.setForeground(resizer.getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
					for (int i = 2; i < min; i += 4) {
						e.gc.drawLine(i, y, x, i);
					}
				}
			}
		});

		final boolean isRTL = (resizer.getShell().getStyle() & SWT.RIGHT_TO_LEFT) != 0;
		resizer.setCursor(resizer.getDisplay().getSystemCursor(isRTL ? SWT.CURSOR_SIZESW : SWT.CURSOR_SIZESE));
		MouseAdapter resizeSupport = new MouseAdapter() {
			private MouseMoveListener fResizeListener;

			@Override
			public void mouseDown(MouseEvent e) {
				Rectangle shellBounds = fShell.getBounds();
				final int shellX = shellBounds.x;
				final int shellY = shellBounds.y;
				final int shellWidth = shellBounds.width;
				final int shellHeight = shellBounds.height;
				Point mouseLoc = resizer.toDisplay(e.x, e.y);
				final int mouseX = mouseLoc.x;
				final int mouseY = mouseLoc.y;
				fResizeListener = new MouseMoveListener() {
					@Override
					public void mouseMove(MouseEvent e2) {
						Point mouseLoc2 = resizer.toDisplay(e2.x, e2.y);
						int dx = mouseLoc2.x - mouseX;
						int dy = mouseLoc2.y - mouseY;
						if (isRTL) {
							setLocation(new Point(shellX + dx, shellY));
							setSize(shellWidth - dx, shellHeight + dy);
						} else {
							setSize(shellWidth + dx, shellHeight + dy);
						}
					}
				};
				resizer.addMouseMoveListener(fResizeListener);
			}

			@Override
			public void mouseUp(MouseEvent e) {
				resizer.removeMouseMoveListener(fResizeListener);
				fResizeListener = null;
			}
		};
		resizer.addMouseListener(resizeSupport);
	}

	/**
	 * Gets the resize handle size.
	 * @param parent the parent
	 * @return the resize handle size
	 */
	private int getResizeHandleSize(Composite parent) {
		if (fResizeHandleSize == -1) {
			Slider sliderV = new Slider(parent, SWT.VERTICAL);
			Slider sliderH = new Slider(parent, SWT.HORIZONTAL);
			int width = sliderV.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
			int height = sliderH.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			sliderV.dispose();
			sliderH.dispose();
			fResizeHandleSize = Math.min(width, height);
		}

		return fResizeHandleSize;
	}

	/**
	 * Adds the move support.
	 * @param control the control
	 */
	private void addMoveSupport(final Control control) {
		MouseAdapter moveSupport = new MouseAdapter() {
			private MouseMoveListener fMoveListener;

			@Override
			public void mouseDown(MouseEvent e) {
				Point shellLoc = fShell.getLocation();
				final int shellX = shellLoc.x;
				final int shellY = shellLoc.y;
				Point mouseLoc = control.toDisplay(e.x, e.y);
				final int mouseX = mouseLoc.x;
				final int mouseY = mouseLoc.y;
				fMoveListener = new MouseMoveListener() {
					@Override
					public void mouseMove(MouseEvent e2) {
						Point mouseLoc2 = control.toDisplay(e2.x, e2.y);
						int dx = mouseLoc2.x - mouseX;
						int dy = mouseLoc2.y - mouseY;
						fShell.setLocation(shellX + dx, shellY + dy);
					}
				};
				control.addMouseMoveListener(fMoveListener);
			}

			@Override
			public void mouseUp(MouseEvent e) {
				control.removeMouseMoveListener(fMoveListener);
				fMoveListener = null;
			}
		};
		control.addMouseListener(moveSupport);
	}

	/**
	 * Sets the color.
	 * @param control the control
	 * @param foreground the foreground
	 * @param background the background
	 */
	private static void setColor(Control control, Color foreground, Color background) {
		control.setForeground(foreground);
		control.setBackground(background);
	}

	/**
	 * Gets the shell.
	 * @return the shell
	 */
	protected final Shell getShell() {
		return fShell;
	}

	/**
	 * Gets the tool bar manager.
	 * @return the tool bar manager
	 */
	protected final ToolBarManager getToolBarManager() {
		return fToolBarManager;
	}

	/**
	 * Creates the.
	 */
	protected final void create() {
		createContent(fContentComposite);
	}

	/**
	 * Creates the content.
	 * @param parent the parent
	 */
	protected abstract void createContent(Composite parent);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setInformation(java.lang.String)
	 */
	@Override
	public void setInformation(String information) {

	}

	/**
	 * Checks if is resizable.
	 * @return true, if is resizable
	 */
	public boolean isResizable() {
		return fResizable;
	}

	/*
	 * @see IInformationControl#setVisible(boolean)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		if (fShell.isVisible() == visible) {
			return;
		}

		fShell.setVisible(visible);
	}

	/*
	 * @see IInformationControl#dispose()
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#dispose()
	 */
	@Override
	public void dispose() {
		if (fShell != null && !fShell.isDisposed()) {
			fShell.dispose();
		}
	}

	/**
	 * Handle dispose.
	 */
	protected void handleDispose() {
		if (fStatusLabelFont != null) {
			fStatusLabelFont.dispose();
			fStatusLabelFont = null;
		}
		if (fStatusLabelForeground != null) {
			fStatusLabelForeground.dispose();
			fStatusLabelForeground = null;
		}
	}

	/*
	 * @see IInformationControl#setSize(int, int)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setSize(int, int)
	 */
	@Override
	public void setSize(int width, int height) {
		fShell.setSize(width, height);
	}

	/*
	 * @see IInformationControl#setLocation(Point)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setLocation(org.eclipse.swt.graphics.Point)
	 */
	@Override
	public void setLocation(Point location) {
		fShell.setLocation(location);
	}

	/*
	 * @see IInformationControl#setSizeConstraints(int, int)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setSizeConstraints(int, int)
	 */
	@Override
	public void setSizeConstraints(int maxWidth, int maxHeight) {
		fSizeConstraints = new Point(maxWidth, maxHeight);
	}

	/**
	 * Gets the size constraints.
	 * @return the size constraints
	 */
	protected final Point getSizeConstraints() {
		return fSizeConstraints != null ? Geometry.copy(fSizeConstraints) : null;
	}

	/*
	 * @see IInformationControl#computeSizeHint()
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#computeSizeHint()
	 */
	@Override
	public Point computeSizeHint() {
		// XXX: Verify whether this is a good default implementation. If yes,
		// document it.
		Point constrains = getSizeConstraints();
		if (constrains == null) {
			return fShell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		}

		return fShell.computeSize(constrains.x, constrains.y, true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension3#computeTrim()
	 */
	@Override
	public Rectangle computeTrim() {
		Rectangle trim = fShell.computeTrim(0, 0, 0, 0);

		if (fStatusComposite != null) {
			trim.height += fStatusComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		}

		return trim;
	}

	/*
	 * @see org.eclipse.jface.text.IInformationControlExtension3#getBounds()
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension3#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return fShell.getBounds();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension3#restoresLocation()
	 */
	@Override
	public boolean restoresLocation() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension3#restoresSize()
	 */
	@Override
	public boolean restoresSize() {
		return false;
	}

	/*
	 * @see IInformationControl#addDisposeListener(DisposeListener)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#addDisposeListener(org.eclipse.swt.events.DisposeListener)
	 */
	@Override
	public void addDisposeListener(DisposeListener listener) {
		fShell.addDisposeListener(listener);
	}

	/*
	 * @see IInformationControl#removeDisposeListener(DisposeListener)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#removeDisposeListener(org.eclipse.swt.events.DisposeListener)
	 */
	@Override
	public void removeDisposeListener(DisposeListener listener) {
		fShell.removeDisposeListener(listener);
	}

	/*
	 * @see IInformationControl#setForegroundColor(Color)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setForegroundColor(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setForegroundColor(Color foreground) {
		fContentComposite.setForeground(foreground);
	}

	/*
	 * @see IInformationControl#setBackgroundColor(Color)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setBackgroundColor(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setBackgroundColor(Color background) {
		fContentComposite.setBackground(background);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#isFocusControl()
	 */
	@Override
	public boolean isFocusControl() {
		return fShell.getDisplay().getActiveShell() == fShell;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#setFocus()
	 */
	@Override
	public void setFocus() {
		boolean focusTaken = fShell.setFocus();
		if (!focusTaken) {
			fShell.forceFocus();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#addFocusListener(org.eclipse.swt.events.FocusListener)
	 */
	@Override
	public void addFocusListener(final FocusListener listener) {
		if (fFocusListeners.isEmpty()) {
			fShellListener = new Listener() {

				@Override
				public void handleEvent(Event event) {
					Object[] listeners = fFocusListeners.getListeners();
					for (int i = 0; i < listeners.length; i++) {
						FocusListener focusListener = (FocusListener) listeners[i];
						if (event.type == SWT.Activate) {
							focusListener.focusGained(new FocusEvent(event));
						} else {
							focusListener.focusLost(new FocusEvent(event));
						}
					}
				}
			};
			fShell.addListener(SWT.Deactivate, fShellListener);
			fShell.addListener(SWT.Activate, fShellListener);
		}
		fFocusListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControl#removeFocusListener(org.eclipse.swt.events.FocusListener)
	 */
	@Override
	public void removeFocusListener(FocusListener listener) {
		fFocusListeners.remove(listener);
		if (fFocusListeners.isEmpty()) {
			fShell.removeListener(SWT.Activate, fShellListener);
			fShell.removeListener(SWT.Deactivate, fShellListener);
			fShellListener = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension4#setStatusText(java.lang.String)
	 */
	@Override
	public void setStatusText(String statusFieldText) {
		if (fStatusLabel != null && !getShell().isVisible()) {
			if (statusFieldText == null) {
				fStatusComposite.setVisible(false);
			} else {
				fStatusLabel.setText(statusFieldText);
				fStatusComposite.setVisible(true);
			}
		}
	}

	/*
	 * @see org.eclipse.jface.text.IInformationControlExtension5#containsControl( org.eclipse.swt.widgets.Control)
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension5#containsControl(org.eclipse.swt.widgets.Control)
	 */
	@Override
	public boolean containsControl(Control control) {
		do {
			if (control == fShell) {
				return true;
			}
			if (control instanceof Shell) {
				return false;
			}
			control = control.getParent();
		} while (control != null);
		return false;
	}

	/*
	 * @see org.eclipse.jface.text.IInformationControlExtension5#isVisible()
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension5#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return fShell != null && !fShell.isDisposed() && fShell.isVisible();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension5#getInformationPresenterControlCreator()
	 */
	@Override
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension5#computeSizeConstraints(int, int)
	 */
	@Override
	public Point computeSizeConstraints(int widthInChars, int heightInChars) {
		GC gc = new GC(fContentComposite);
		gc.setFont(JFaceResources.getDialogFont());
		int width = gc.getFontMetrics().getAverageCharWidth();
		int height = gc.getFontMetrics().getHeight();
		gc.dispose();

		return new Point(widthInChars * width, heightInChars * height);
	}

}
