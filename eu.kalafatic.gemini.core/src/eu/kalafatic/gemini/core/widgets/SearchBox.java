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
package eu.kalafatic.gemini.core.widgets;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.kalafatic.gemini.core.interfaces.IViewer;

/**
 * The Class class SearchBox.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SearchBox extends Composite implements KeyListener, ModifyListener, DisposeListener {

	/** The case sensitive. */
	private boolean caseSensitive;

	/** The associated control. */
	private Control associatedControl;

	/** The viewer. */
	private Viewer viewer;

	/** The init content. */
	private List<String> initContent = new ArrayList<String>();

	/** The search content. */
	private List<String> searchContent = new ArrayList<String>();

	/** The saved content. */
	private List<String> savedContent = new ArrayList<String>();

	/** The search buffer. */
	private StringBuffer searchBuffer = new StringBuffer();

	/** The pressed image. */
	private Image inactiveImage, activeImage, pressedImage;

	/** The filter text. */
	private Text filterText;

	/** The clear button. */
	private Label clearButton;

	/**
	 * Instantiates a new search box.
	 * @param parent the parent
	 */
	public SearchBox(Composite parent) {
		this(parent, false);
	}

	/**
	 * Instantiates a new search box.
	 * @param parent the parent
	 * @param style the style
	 */
	public SearchBox(Composite parent, int style) {
		super(parent, style);
		this.caseSensitive = false;
	}

	/**
	 * Instantiates a new search box.
	 * @param parent the parent
	 * @param caseSensitive the case sensitive
	 */
	public SearchBox(Composite parent, boolean caseSensitive) {
		super(parent, SWT.NONE);
		this.caseSensitive = caseSensitive;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the control.
	 * @param viewer the viewer
	 * @param files the files
	 */
	public void createControl(IViewer viewer, File[] files) {
		this.viewer = (Viewer) viewer.getViewer();
		setContent(files);
		initContent.addAll(searchContent);
		savedContent.addAll(searchContent);
		createControl(this.viewer.getControl(), searchContent);

		this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					if (selection.getFirstElement() instanceof File) {
						File file = (File) selection.getFirstElement();
						if (file.isDirectory()) {
							setContent(file.listFiles());
						} else {
							addPathOrDevice(searchContent, file);
						}
					}
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the control.
	 * @param associatedControl the associated control
	 * @param searchContent the search content
	 */
	public void createControl(Control associatedControl, List<String> searchContent) {
		this.associatedControl = associatedControl;
		this.searchContent = searchContent;
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		createFilter(this);
		createClearLabel(this);
		filterText.addModifyListener(this);
		filterText.addKeyListener(this);
		associatedControl.addKeyListener(this);
		getParent().addDisposeListener(this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the filter.
	 * @param parent the parent
	 */
	private void createFilter(Composite parent) {
		filterText = new Text(parent, SWT.SINGLE);
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the clear label.
	 * @param parent the parent
	 */
	private void createClearLabel(Composite parent) {
		activeImage = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_CLEAR);
		inactiveImage = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_CLEAR_DISABLED);
		pressedImage = new Image(parent.getDisplay(), activeImage, SWT.IMAGE_GRAY);

		clearButton = new Label(parent, SWT.NONE);
		clearButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		clearButton.setImage(inactiveImage);
		clearButton.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		clearButton.setToolTipText("Clear");
		clearButton.addMouseListener(new MouseAdapter() {
			private MouseMoveListener fMoveListener;

			@Override
			public void mouseDown(MouseEvent e) {
				clearButton.setImage(pressedImage);
				fMoveListener = new MouseMoveListener() {
					private boolean fMouseInButton = true;

					@Override
					public void mouseMove(MouseEvent e) {
						boolean mouseInButton = isMouseInButton(e);
						if (mouseInButton != fMouseInButton) {
							fMouseInButton = mouseInButton;
							clearButton.setImage(mouseInButton ? pressedImage : inactiveImage);
						}
					}
				};
				clearButton.addMouseMoveListener(fMoveListener);
			}

			@Override
			public void mouseUp(MouseEvent e) {
				if (fMoveListener != null) {
					clearButton.removeMouseMoveListener(fMoveListener);
					fMoveListener = null;
					boolean mouseInButton = isMouseInButton(e);
					clearButton.setImage(mouseInButton ? activeImage : inactiveImage);
					if (mouseInButton) {
						filterText.setText("");
						filterText.setFocus();
					}
				}
			}

			private boolean isMouseInButton(MouseEvent e) {
				Point buttonSize = clearButton.getSize();
				return 0 <= e.x && e.x < buttonSize.x && 0 <= e.y && e.y < buttonSize.y;
			}
		});
		clearButton.addMouseTrackListener(new MouseTrackListener() {
			@Override
			public void mouseEnter(MouseEvent e) {
				clearButton.setImage(activeImage);
			}

			@Override
			public void mouseExit(MouseEvent e) {
				clearButton.setImage(inactiveImage);
			}

			@Override
			public void mouseHover(MouseEvent e) {}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the content.
	 * @param files the new content
	 */
	public void setContent(File[] files) {
		searchContent.clear();
		for (File file : files) {
			addPathOrDevice(searchContent, file);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events. KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// associated control event
		if (associatedControl.isFocusControl()) {
			if (e.keyCode >= 97 && e.keyCode <= 122) {
				filterText.setText(searchBuffer.append((char) e.keyCode).toString());
			}
		} else {
			// user pressed down arrow to move to associated control
			if (hasItems() && e.keyCode == SWT.ARROW_DOWN) {
				associatedControl.setFocus();
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events .KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events .ModifyEvent)
	 */
	@Override
	public void modifyText(ModifyEvent e) {
		if (filterText.getText().isEmpty()) {
			searchBuffer = new StringBuffer();
			searchContent.addAll(initContent);
			savedContent.addAll(initContent);

			setContent(initContent);
		} else {
			setContent(search());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Search.
	 * @return the list
	 */
	private List<String> search() {
		Collections.sort(searchContent);
		searchBuffer = new StringBuffer(filterText.getText());
		List<String> searchResult = search(searchContent, searchBuffer.toString());
		return searchResult;
	}

	// ---------------------------------------------------------------

	/**
	 * Search.
	 * @param list the list
	 * @param regex the regex
	 * @return the list
	 */
	public List<String> search(List<String> list, String regex) {
		List<String> results = new ArrayList<String>();
		for (String text : list) {
			if (caseSensitive) {
				if (text.matches(".*" + regex + ".*")) {
					results.add(text);
				}
			} else {
				if (new String(text).toLowerCase().matches(".*" + regex.toLowerCase() + ".*")) {
					results.add(text);
				}
			}
		}
		return results;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the content.
	 * @param newContent the new content
	 */
	private void setContent(List<String> newContent) {
		if (associatedControl instanceof org.eclipse.swt.widgets.List) {
			setContent(newContent, (org.eclipse.swt.widgets.List) associatedControl);
		} else if (associatedControl instanceof org.eclipse.swt.widgets.Tree) {
			if (newContent.containsAll(initContent)) {
				TreeItem[] items = ((org.eclipse.swt.widgets.Tree) associatedControl).getItems();
				for (TreeItem treeItem : items) {
					treeItem.setExpanded(false);
				}
			} else {
				setContent(newContent, (org.eclipse.swt.widgets.Tree) associatedControl);
			}
		}
		refresh(associatedControl);

	}

	// ---------------------------------------------------------------

	/**
	 * Sets the content.
	 * @param newContent the new content
	 * @param control the control
	 */
	private void setContent(List<String> newContent, org.eclipse.swt.widgets.List control) {
		control.removeAll();
		for (String result : newContent) {
			control.add(result);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the content.
	 * @param newContent the new content
	 * @param control the control
	 */
	private void setContent(List<String> newContent, org.eclipse.swt.widgets.Tree control) {
		TreeItem[] items = control.getItems();

		for (int i = 0; i < items.length; i++) {

			if (newContent.contains(items[i].getText())) {

				if (items[i].getData() instanceof File) {
					File file = (File) items[i].getData();
					// addPathOrDevice(searchContent, file);
					String[] array = file.list();
					if ((array != null) && (array.length > 0)) {
						searchContent.addAll(Arrays.asList(array));
					}
				}
				items[i].setExpanded(true);
			} else {
				items[i].setExpanded(false);
			}
			refresh(associatedControl);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the path or device.
	 * @param newSearchContent the new search content
	 * @param file the file
	 */
	private void addPathOrDevice(List<String> newSearchContent, File file) {
		if (file.getName().isEmpty()) {
			newSearchContent.add(file.getPath());
		} else {
			newSearchContent.add(file.getName());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh.
	 * @param control the control
	 */
	private void refresh(final Control control) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					if ((control != null) && (!control.isDisposed())) {
						control.getParent().layout(true, true);
					}
					if (viewer != null) {
						viewer.refresh();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Checks for items.
	 * @return true, if successful
	 */
	private boolean hasItems() {
		if (associatedControl instanceof org.eclipse.swt.widgets.List) {
			org.eclipse.swt.widgets.List listControl = (org.eclipse.swt.widgets.List) associatedControl;
			return listControl.getItemCount() > 0;
		} else if (associatedControl instanceof org.eclipse.swt.widgets.Tree) {
			org.eclipse.swt.widgets.Tree control = (org.eclipse.swt.widgets.Tree) associatedControl;
			return control.getItemCount() > 0;
		}
		return false;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt .events.DisposeEvent)
	 */
	@Override
	public void widgetDisposed(DisposeEvent e) {
		// disposes all images
		if (activeImage != null) {
			activeImage = null;
		}
		if (inactiveImage != null) {
			inactiveImage = null;
		}
		if (pressedImage != null) {
			pressedImage = null;
		}
	}
}
