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

import org.eclipse.compare.BufferedContent;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;

/**
 * The Class class CompareEditorAction.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CompareEditorAction {

	/** The cc. */
	CompareConfiguration cc = new CompareConfiguration();

	/**
	 * Run.
	 */
	public void run() {
		CompareEditorInput input = new MyCompareInput(cc);
		CompareUI.openCompareEditor(input);
	}

	/**
	 * Run1.
	 * @throws CoreException the core exception
	 */
	public void run1() throws CoreException {
		// cc.setProperty(CompareConfiguration.USE_OUTLINE_VIEW, true);

		cc.setLeftEditable(true);
		cc.setRightEditable(false);
		// cc.setAncestorLabel("ancestor");
		IProject prj = CompareManager.getInstance().createProject("test1111");
		prj.open(new NullProgressMonitor());
		IFile leftF = prj.getFile("left");
		IFile rightF = prj.getFile("right");
		String left = "aaaaaaaaa\n\nee";
		String right = ";dfeadf\n";
		try {
			if (!leftF.exists()) {
				leftF.create(new ByteArrayInputStream(left.getBytes()), IFile.FORCE, null);
			} else {
				leftF.appendContents(new ByteArrayInputStream(left.getBytes()), IFile.FORCE, null);
			}

			if (!rightF.exists()) {
				rightF.create(new ByteArrayInputStream(right.getBytes()), IFile.FORCE, null);
			} else {
				rightF.appendContents(new ByteArrayInputStream(right.getBytes()), IFile.FORCE, null);
			}

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ResourceCompareInput input = new ResourceCompareInput(cc);
		CompareEditorInput input = new CompareEditorInput(cc);
		// CompareEditorInput input = new MyCompareInput(cc);

		StructuredSelection sel = new StructuredSelection(new IFile[] { leftF, rightF });
		input.setSelection(sel, null, false);
		CompareUI.openCompareEditor(input, true);
		// CompareUI.openCompareEditor(new MyCompareInput(cc));
	}

	/**
	 * The Class class MyCompareInput.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class MyCompareInput extends CompareEditorInput {

		/**
		 * Instantiates a new my compare input.
		 * @param configuration the configuration
		 */
		public MyCompareInput(CompareConfiguration configuration) {
			super(configuration);
			// TODO Auto-generated constructor stub
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.editors.compare.CompareEditorInput#prepareInput(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		protected Object prepareInput(IProgressMonitor monitor) {
			Differencer d = new Differencer() {
				@Override
				protected Object visit(Object parent, int description, Object ancestor, Object left, Object right) {
					return new MyDiffNode((IDiffContainer) parent, description, (ITypedElement) ancestor, (ITypedElement) left, (ITypedElement) right);
				}
			};
			MyCompareNode ancester = new MyCompareNode("aaaaaaaaa");
			MyCompareNode left = new MyCompareNode("aaaaaaaaa");
			MyCompareNode right = new MyCompareNode("aaaaaaaaa\nnd\neefaa");
			return d.findDifferences(true, null, null, ancester, left, right);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.CompareEditorInput#isSaveNeeded()
		 */
		@Override
		public boolean isSaveNeeded() {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.CompareEditorInput#saveChanges(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public void saveChanges(IProgressMonitor monitor) throws CoreException {
			// TODO Auto-generated method stub
			super.saveChanges(monitor);
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.compare.CompareEditorInput#isDirty()
		 */
		@Override
		public boolean isDirty() {
			// TODO Auto-generated method stub
			return super.isDirty();
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

			}

			/**
			 * Clear dirty.
			 */
			void clearDirty() {
				fDirty = false;
			}

			/**
			 * Checks if is f dirty.
			 * @return true, if is f dirty
			 */
			public boolean isfDirty() {
				getActionBars().clearGlobalActionHandlers();
				return fDirty;
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
		 * The Class class MyCompareNode.
		 * @author Petr Kalafatic
		 * @version 3.0.0
		 * @project Gemini
		 */
		class MyCompareNode extends BufferedContent implements ITypedElement {

			/** The resource. */
			private String fResource;

			/**
			 * Instantiates a new my compare node.
			 * @param resource the resource
			 */
			MyCompareNode(String resource) {
				fResource = resource;
			}

			/*
			 * (non-Javadoc)
			 * @see org.eclipse.compare.BufferedContent#createStream()
			 */
			@Override
			protected InputStream createStream() throws CoreException {
				InputStream is = null;
				is = new ByteArrayInputStream(fResource.getBytes());
				return is;
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
			 * @see org.eclipse.compare.ITypedElement#getName()
			 */
			@Override
			public String getName() {
				return null;
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
}
