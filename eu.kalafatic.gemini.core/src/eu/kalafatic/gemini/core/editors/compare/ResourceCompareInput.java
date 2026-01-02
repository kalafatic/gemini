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
package eu.kalafatic.gemini.core.editors.compare;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.ZipFileStructureCreator;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.DiffTreeViewer;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import eu.kalafatic.gemini.core.editors.providers.BufferedResourceNode;
import eu.kalafatic.gemini.core.editors.providers.Utilities;

/**
 * The Class class ResourceCompareInput.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
class ResourceCompareInput extends CompareEditorInput {

	/** The Constant NORMALIZE_CASE. */
	private static final boolean NORMALIZE_CASE = true;

	/** The three way. */
	private boolean fThreeWay = false;

	/** The root. */
	private Object fRoot;

	/** The ancestor. */
	private IStructureComparator fAncestor;

	/** The left. */
	private IStructureComparator fLeft;

	/** The right. */
	private IStructureComparator fRight;

	/** The ancestor resource. */
	private IResource fAncestorResource;

	/** The left resource. */
	private IResource fLeftResource;

	/** The right resource. */
	private IResource fRightResource;

	/** The diff viewer. */
	private DiffTreeViewer fDiffViewer;

	/** The open action. */
	private IAction fOpenAction;

	/**
	 * The Class class MyDiffNode.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class MyDiffNode extends DiffNode {

		/** The dirty. */
		private boolean fDirty = false;

		/** The last id. */
		private ITypedElement fLastId;

		/** The last name. */
		private String fLastName;

		/**
		 * Instantiates a new my diff node.
		 * @param parent the parent
		 * @param description the description
		 * @param ancestor the ancestor
		 * @param left the left
		 * @param right the right
		 */
		public MyDiffNode(IDiffContainer parent, int description, ITypedElement ancestor, ITypedElement left, ITypedElement right) {
			super(parent, description, ancestor, left, right);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.structuremergeviewer.DiffNode#fireChange()
		 */
		@Override
		public void fireChange() {
			super.fireChange();
			setDirty(true);
			fDirty = true;
			if (fDiffViewer != null) {
				fDiffViewer.refresh(this);
			}
		}

		/**
		 * Clear dirty.
		 */
		void clearDirty() {
			fDirty = false;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.structuremergeviewer.DiffNode#getName()
		 */
		@Override
		public String getName() {
			if (fLastName == null) {
				fLastName = super.getName();
			}
			if (fDirty) {
				return '<' + fLastName + '>';
			}
			return fLastName;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.structuremergeviewer.DiffNode#getId()
		 */
		@Override
		public ITypedElement getId() {
			ITypedElement id = super.getId();
			if (id == null) {
				return fLastId;
			}
			fLastId = id;
			return id;
		}
	}

	/**
	 * The Class class FilteredBufferedResourceNode.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	static class FilteredBufferedResourceNode extends BufferedResourceNode {

		/**
		 * Instantiates a new filtered buffered resource node.
		 * @param resource the resource
		 */
		FilteredBufferedResourceNode(IResource resource) {
			super(resource);
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.editors.providers.BufferedResourceNode#createChild(org.eclipse.core.resources.IResource)
		 */
		@Override
		protected IStructureComparator createChild(IResource child) {
			String name = child.getName();
			// if (CompareUIPlugin.getDefault().filter(name,
			// child instanceof IContainer, false)) {
			// return null;
			// }
			return new FilteredBufferedResourceNode(child);
		}
	}

	/*
	 * Creates an compare editor input for the given selection.
	 */
	/**
	 * Instantiates a new resource compare input.
	 * @param config the config
	 */
	ResourceCompareInput(CompareConfiguration config) {
		super(config);
	}

	// @Override
	// public Viewer createDiffViewer(Composite parent) {
	// fDiffViewer = new DiffTreeViewer(parent, getCompareConfiguration()) {
	// @Override
	// protected void fillContextMenu(IMenuManager manager) {
	//
	// if (fOpenAction == null) {
	// fOpenAction = new Action() {
	// @Override
	// public void run() {
	// handleOpen(null);
	// }
	// };
	// Utilities.initAction(fOpenAction, getBundle(),
	//							"action.CompareContents."); //$NON-NLS-1$
	// }
	//
	// boolean enable = false;
	// ISelection selection = getSelection();
	// if (selection instanceof IStructuredSelection) {
	// IStructuredSelection ss = (IStructuredSelection) selection;
	// if (ss.size() == 1) {
	// Object element = ss.getFirstElement();
	// if (element instanceof MyDiffNode) {
	// ITypedElement te = ((MyDiffNode) element).getId();
	// if (te != null) {
	// enable = !ITypedElement.FOLDER_TYPE.equals(te
	// .getType());
	// }
	// } else {
	// enable = true;
	// }
	// }
	// }
	// fOpenAction.setEnabled(enable);
	//
	// manager.add(fOpenAction);
	//
	// super.fillContextMenu(manager);
	// }
	// };
	// return fDiffViewer;
	// }

	/**
	 * The Class class SelectAncestorDialog.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class SelectAncestorDialog extends MessageDialog {

		/** The resources. */
		private IResource[] theResources;

		/** The ancestor resource. */
		IResource ancestorResource;

		/** The left resource. */
		IResource leftResource;

		/** The right resource. */
		IResource rightResource;

		/** The buttons. */
		private Button[] buttons;

		/**
		 * Instantiates a new select ancestor dialog.
		 * @param parentShell the parent shell
		 * @param theResources the the resources
		 */
		public SelectAncestorDialog(Shell parentShell, IResource[] theResources) {
			super(parentShell, "c", null, "c", MessageDialog.QUESTION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
			this.theResources = theResources;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected Control createCustomArea(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout());
			buttons = new Button[3];
			for (int i = 0; i < 3; i++) {
				buttons[i] = new Button(composite, SWT.RADIO);
				buttons[i].addSelectionListener(selectionListener);
				buttons[i].setText("c");
				buttons[i].setFont(parent.getFont());
				// set initial state
				buttons[i].setSelection(i == 0);
			}
			pickAncestor(0);
			return composite;
		}

		/**
		 * Pick ancestor.
		 * @param i the i
		 */
		private void pickAncestor(int i) {
			ancestorResource = theResources[i];
			leftResource = theResources[i == 0 ? 1 : 0];
			rightResource = theResources[i == 2 ? 1 : 2];
		}

		/** The selection listener. */
		private SelectionListener selectionListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button selectedButton = (Button) e.widget;
				if (!selectedButton.getSelection()) {
					return;
				}
				for (int i = 0; i < 3; i++) {
					if (selectedButton == buttons[i]) {
						pickAncestor(i);
					}
				}
			}
		};
	}

	// If the compare is three-way, this method asks the user which resource
	// to use as the ancestor. Depending on the value of
	// showSelectAncestorDialog flag it uses different dialogs to get the
	// feedback from the user. Returns false if the user cancels the prompt,
	// true otherwise.
	/**
	 * Sets the selection.
	 * @param s the s
	 * @param shell the shell
	 * @param showSelectAncestorDialog the show select ancestor dialog
	 * @return true, if successful
	 */
	boolean setSelection(ISelection s, Shell shell, boolean showSelectAncestorDialog) {

		if (!showSelectAncestorDialog) {
			return showCompareWithOtherResourceDialog(shell, s);
		}

		IResource[] selection = Utilities.getResources(s);

		fThreeWay = selection.length == 3;

		if (fThreeWay) {
			SelectAncestorDialog dialog = new SelectAncestorDialog(shell, selection);
			int code = dialog.open();
			if (code == Window.CANCEL) {
				return false;
			}

			fAncestorResource = dialog.ancestorResource;
			fAncestor = getStructure(fAncestorResource);
			fLeftResource = dialog.leftResource;
			fRightResource = dialog.rightResource;
		} else {
			fAncestorResource = null;
			fAncestor = null;
			fLeftResource = selection[0];
			fRightResource = selection[1];
		}
		fLeft = getStructure(fLeftResource);
		fRight = getStructure(fRightResource);
		return true;
	}

	/**
	 * Show compare with other resource dialog.
	 * @param shell the shell
	 * @param s the s
	 * @return true, if successful
	 */
	private boolean showCompareWithOtherResourceDialog(Shell shell, ISelection s) {
		CompareWithOtherResourceDialog dialog = new CompareWithOtherResourceDialog(shell, s);
		if (dialog.open() != IDialogConstants.OK_ID) {
			return false;
		}
		IResource[] selection = dialog.getResult();
		if (!checkSelection(selection)) {
			return false;
		}

		fThreeWay = selection.length == 3;
		if (fThreeWay) {
			fAncestorResource = selection[0];
			fAncestor = getStructure(fAncestorResource);
			fLeftResource = selection[1];
			fRightResource = selection[2];
		} else {
			fAncestorResource = null;
			fAncestor = null;
			fLeftResource = selection[0];
			fRightResource = selection[1];
		}
		fLeft = getStructure(fLeftResource);
		fRight = getStructure(fRightResource);
		return true;
	}

	/**
	 * Check selection.
	 * @param resources the resources
	 * @return true, if successful
	 */
	private boolean checkSelection(IResource[] resources) {
		for (int i = 0; i < resources.length; i++) {
			if (resources[i] == null) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Returns true if compare can be executed for the given selection.
	 */
	/**
	 * Checks if is enabled.
	 * @param s the s
	 * @return true, if is enabled
	 */
	public boolean isEnabled(ISelection s) {

		IResource[] selection = Utilities.getResources(s);
		if (selection.length < 2 || selection.length > 3) {
			return false;
		}

		boolean threeWay = selection.length == 3;

		if (threeWay) {
			// It only makes sense if they're all mutually comparable.
			// If not, the user should compare two of them.
			return comparable(selection[0], selection[1]) && comparable(selection[0], selection[2]) && comparable(selection[1], selection[2]);
		}

		return comparable(selection[0], selection[1]);
	}

	/**
	 * Initialize compare configuration.
	 */
	void initializeCompareConfiguration() {
		CompareConfiguration cc = getCompareConfiguration();
		if (fLeftResource != null) {
			cc.setLeftLabel(buildLabel(fLeftResource));
			// cc.setLeftImage(CompareUIPlugin.getImage(fLeftResource));
		}
		if (fRightResource != null) {
			cc.setRightLabel(buildLabel(fRightResource));
			// cc.setRightImage(CompareUIPlugin.getImage(fRightResource));
		}
		if (fThreeWay && fAncestorResource != null) {
			cc.setAncestorLabel(buildLabel(fAncestorResource));
			// cc.setAncestorImage(CompareUIPlugin.getImage(fAncestorResource));
		}
	}

	/*
	 * Returns true if both resources are either structured or unstructured.
	 */
	/**
	 * Comparable.
	 * @param c1 the c1
	 * @param c2 the c2
	 * @return true, if successful
	 */
	private boolean comparable(IResource c1, IResource c2) {
		return hasStructure(c1) == hasStructure(c2);
	}

	/*
	 * Returns true if the given argument has a structure.
	 */
	/**
	 * Checks for structure.
	 * @param input the input
	 * @return true, if successful
	 */
	private boolean hasStructure(IResource input) {

		if (input instanceof IContainer) {
			return true;
		}

		if (input instanceof IFile) {
			IFile file = (IFile) input;
			String type = file.getFileExtension();
			if (type != null) {
				type = normalizeCase(type);
				return "JAR".equals(type) || "ZIP".equals(type); //$NON-NLS-2$ //$NON-NLS-1$
			}
		}

		return false;
	}

	/*
	 * Creates a <code>IStructureComparator</code> for the given input. Returns <code>null</code> if no <code>IStructureComparator</code> can be found
	 * for the <code>IResource</code>.
	 */
	/**
	 * Gets the structure.
	 * @param input the input
	 * @return the structure
	 */
	private IStructureComparator getStructure(IResource input) {

		if (input instanceof IContainer) {
			return new FilteredBufferedResourceNode(input);
		}

		if (input instanceof IFile) {
			IStructureComparator rn = new FilteredBufferedResourceNode(input);
			IFile file = (IFile) input;
			String type = normalizeCase(file.getFileExtension());
			if ("JAR".equals(type) || "ZIP".equals(type)) {
				return new ZipFileStructureCreator().getStructure(rn);
			}
			return rn;
		}
		return null;
	}

	/*
	 * Performs a two-way or three-way diff on the current selection.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#prepareInput(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Object prepareInput(IProgressMonitor pm) throws InvocationTargetException {

		try {
			// fix for PR 1GFMLFB: ITPUI:WIN2000 - files that are out of sync
			// with the file system appear as empty
			fLeftResource.refreshLocal(IResource.DEPTH_INFINITE, pm);
			fRightResource.refreshLocal(IResource.DEPTH_INFINITE, pm);
			if (fThreeWay && fAncestorResource != null) {
				fAncestorResource.refreshLocal(IResource.DEPTH_INFINITE, pm);
				// end fix
			}

			pm.beginTask(Utilities.getString("ResourceCompare.taskName"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$

			String leftLabel = fLeftResource.getName();
			String rightLabel = fRightResource.getName();

			String title;
			if (fThreeWay) {
				String format = Utilities.getString("ResourceCompare.threeWay.title"); //$NON-NLS-1$
				String ancestorLabel = fAncestorResource.getName();
				title = MessageFormat.format(format, new String[] { ancestorLabel, leftLabel, rightLabel });
			} else {
				String format = Utilities.getString("ResourceCompare.twoWay.title"); //$NON-NLS-1$
				title = MessageFormat.format(format, new String[] { leftLabel, rightLabel });
			}
			// setTitle(title);
			setTitle("dfgs");

			Differencer d = new Differencer() {
				@Override
				protected Object visit(Object parent, int description, Object ancestor, Object left, Object right) {
					return new MyDiffNode((IDiffContainer) parent, description, (ITypedElement) ancestor, (ITypedElement) left, (ITypedElement) right);
				}
			};

			fRoot = d.findDifferences(/* fThreeWay */true, pm, null, fAncestor, fLeft, fRight);
			return fRoot;

		} catch (CoreException ex) {
			throw new InvocationTargetException(ex);
		} finally {
			pm.done();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		if (fLeftResource != null && fRightResource != null) {
			String leftLabel = fLeftResource.getFullPath().makeRelative().toString();
			String rightLabel = fRightResource.getFullPath().makeRelative().toString();
			if (fThreeWay) {
				String format = Utilities.getString("ResourceCompare.threeWay.tooltip"); //$NON-NLS-1$
				String ancestorLabel = fAncestorResource.getFullPath().makeRelative().toString();
				return MessageFormat.format(format, new String[] { ancestorLabel, leftLabel, rightLabel });
			}
			String format = Utilities.getString("ResourceCompare.twoWay.tooltip"); //$NON-NLS-1$
			return MessageFormat.format(format, new String[] { leftLabel, rightLabel });
		}
		// fall back
		return super.getToolTipText();
	}

	/**
	 * Builds the label.
	 * @param r the r
	 * @return the string
	 */
	private String buildLabel(IResource r) {
		// for a linked resource in a hidden project use its local file system
		// location
		if (r.isLinked() && r.getProject().isHidden()) {
			return r.getLocation().toString();
		}
		String n = r.getFullPath().toString();
		if (n.charAt(0) == IPath.SEPARATOR) {
			return n.substring(1);
		}
		return n;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#saveChanges(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void saveChanges(IProgressMonitor pm) throws CoreException {
		super.saveChanges(pm);
		if (fRoot instanceof DiffNode) {
			try {
				commit(pm, (DiffNode) fRoot);
			} finally {
				if (fDiffViewer != null) {
					fDiffViewer.refresh();
				}
				setDirty(false);
			}
		}
	}

	/*
	 * Recursively walks the diff tree and commits all changes.
	 */
	/**
	 * Commit.
	 * @param pm the pm
	 * @param node the node
	 * @throws CoreException the core exception
	 */
	private static void commit(IProgressMonitor pm, DiffNode node) throws CoreException {

		if (node instanceof MyDiffNode) {
			((MyDiffNode) node).clearDirty();
		}

		ITypedElement left = node.getLeft();
		if (left instanceof BufferedResourceNode) {
			((BufferedResourceNode) left).commit(pm);
		}

		ITypedElement right = node.getRight();
		if (right instanceof BufferedResourceNode) {
			((BufferedResourceNode) right).commit(pm);
		}

		IDiffElement[] children = node.getChildren();
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				IDiffElement element = children[i];
				if (element instanceof DiffNode) {
					commit(pm, (DiffNode) element);
				}
			}
		}
	}

	/*
	 * (non Javadoc) see IAdaptable.getAdapter
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class adapter) {
		if (IFile.class.equals(adapter)) {
			IProgressMonitor pm = new NullProgressMonitor();
			// flush changes in any dirty viewer
			flushViewers(pm);
			IFile[] files = (IFile[]) getAdapter(IFile[].class);
			if (files != null && files.length > 0) {
				return files[0]; // can only return one: limitation on
									// IDE.saveAllEditors; see #64617
			}
			return null;
		}
		if (IFile[].class.equals(adapter)) {
			HashSet collector = new HashSet();
			collectDirtyResources(fRoot, collector);
			return collector.toArray(new IFile[collector.size()]);
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Collect dirty resources.
	 * @param o the o
	 * @param collector the collector
	 */
	private void collectDirtyResources(Object o, Set collector) {
		if (o instanceof DiffNode) {
			DiffNode node = (DiffNode) o;

			ITypedElement left = node.getLeft();
			if (left instanceof BufferedResourceNode) {
				BufferedResourceNode bn = (BufferedResourceNode) left;
				if (bn.isDirty()) {
					IResource resource = bn.getResource();
					if (resource instanceof IFile) {
						collector.add(resource);
					}
				}
			}

			ITypedElement right = node.getRight();
			if (right instanceof BufferedResourceNode) {
				BufferedResourceNode bn = (BufferedResourceNode) right;
				if (bn.isDirty()) {
					IResource resource = bn.getResource();
					if (resource instanceof IFile) {
						collector.add(resource);
					}
				}
			}

			IDiffElement[] children = node.getChildren();
			if (children != null) {
				for (int i = 0; i < children.length; i++) {
					IDiffElement element = children[i];
					if (element instanceof DiffNode) {
						collectDirtyResources(element, collector);
					}
				}
			}
		}
	}

	/**
	 * Normalize case.
	 * @param s the s
	 * @return the string
	 */
	private static String normalizeCase(String s) {
		if (NORMALIZE_CASE && s != null) {
			return s.toUpperCase();
		}
		return s;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#canRunAsJob()
	 */
	@Override
	public boolean canRunAsJob() {
		return true;
	}
}
