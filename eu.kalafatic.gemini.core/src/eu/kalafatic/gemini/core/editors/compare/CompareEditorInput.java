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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IModificationDate;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class class CompareEditorInput.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "unused" })
public class CompareEditorInput extends org.eclipse.compare.CompareEditorInput {

	/**
	 * Instantiates a new compare editor input.
	 * @param configuration the configuration
	 */
	public CompareEditorInput(CompareConfiguration configuration) {
		super(configuration);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.CompareEditorInput#prepareInput(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected Object prepareInput(IProgressMonitor pm) {
		Differencer d = new Differencer() {
			@Override
			protected Object visit(Object parent, int description, Object ancestor, Object left, Object right) {
				return new MyDiffNode((IDiffContainer) parent, description, (ITypedElement) ancestor, (ITypedElement) left, (ITypedElement) right);
			}
		};

		CompareItem ancestor = new CompareItem("Common", "contents");
		CompareItem left = new CompareItem("Left", "new contents");
		CompareItem right = new CompareItem("Right", "old contents");
		return new DiffNode(null, Differencer.CONFLICTING, ancestor, left, right);

		// Object fRoot = d.findDifferences(true, pm, null, ancestor, left,
		// right);
		// return fRoot;
	}

	// @Override
	// protected Object prepareInput(IProgressMonitor monitor)
	// throws InvocationTargetException, InterruptedException {
	// Differencer d = new Differencer() {
	// @Override
	// protected Object visit(Object parent, int description,
	// Object ancestor, Object left, Object right) {
	// return new MyDiffNode((IDiffContainer) parent, description,
	// (ITypedElement) ancestor, (ITypedElement) left,
	// (ITypedElement) right);
	// }
	// };
	// return null;
	// }

	/**
	 * Sets the selection.
	 * @param s the s
	 * @param shell the shell
	 * @param showSelectAncestorDialog the show select ancestor dialog
	 * @return true, if successful
	 */
	boolean setSelection(ISelection s, Shell shell, boolean showSelectAncestorDialog) {

		if (!showSelectAncestorDialog) {
			// return showCompareWithOtherResourceDialog(shell, s);
		}

		// IResource[] selection = Utilities.getResources(s);

		// boolean fThreeWay = selection.length == 3;

		// if (fThreeWay) {
		// SelectAncestorDialog dialog = new SelectAncestorDialog(shell,
		// selection);
		// int code = dialog.open();
		// if (code == Window.CANCEL) {
		// return false;
		// }
		//
		// fAncestorResource = dialog.ancestorResource;
		// fAncestor = getStructure(fAncestorResource);
		// fLeftResource = dialog.leftResource;
		// fRightResource = dialog.rightResource;
		// } else {
		// fAncestorResource = null;
		// fAncestor = null;
		// fLeftResource = selection[0];
		// fRightResource = selection[1];
		// }
		// fLeft = getStructure(fLeftResource);
		// fRight = getStructure(fRightResource);
		return true;
	}

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
			// if (fDiffViewer != null) {
			// fDiffViewer.refresh(this);
			// }
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
	 * The Class class CompareItem.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class CompareItem implements IStreamContentAccessor, ITypedElement, IModificationDate {

		/** The name. */
		private String contents, name;

		/** The time. */
		private long time;

		/**
		 * Instantiates a new compare item.
		 * @param name the name
		 * @param contents the contents
		 * @param time the time
		 */
		CompareItem(String name, String contents, long time) {
			this.name = name;
			this.contents = contents;
			this.time = time;
		}

		/**
		 * Instantiates a new compare item.
		 * @param name the name
		 * @param contents the contents
		 */
		CompareItem(String name, String contents) {
			this(name, contents, System.currentTimeMillis());
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.IStreamContentAccessor#getContents()
		 */
		@Override
		public InputStream getContents() throws CoreException {
			return new ByteArrayInputStream(contents.getBytes());
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.ITypedElement#getImage()
		 */
		@Override
		public Image getImage() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.IModificationDate#getModificationDate()
		 */
		@Override
		public long getModificationDate() {
			return time;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.ITypedElement#getName()
		 */
		@Override
		public String getName() {
			return name;
		}

		/**
		 * Gets the string.
		 * @return the string
		 */
		public String getString() {
			return contents;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.ITypedElement#getType()
		 */
		@Override
		public String getType() {
			return ITypedElement.TEXT_TYPE;
		}
	}

}
