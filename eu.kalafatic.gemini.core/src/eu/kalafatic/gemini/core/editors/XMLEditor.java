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
package eu.kalafatic.gemini.core.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * The Class class XMLEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "unused" })
public class XMLEditor extends FormEditor {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.core.editors.XMLEditor";

	/** The editor. */
	private FormEditor editor;

	/** The index. */
	private int index;

	/** The id. */
	private String id;

	/** The form. */
	private ScrolledForm form;

	/** The toolkit. */
	private FormToolkit toolkit;

	/**
	 * Instantiates a new XML editor.
	 */
	public XMLEditor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#createPages()
	 */
	@Override
	public void createPages() {
		try {

			Composite composite = new Composite(getContainer(), SWT.NONE);
			FillLayout layout = new FillLayout();
			composite.setLayout(layout);
			StyledText text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
			text.setEditable(false);

			int index = addPage(composite);
			setPageText(index, "hguk");

			TreesPage viewsPage = new TreesPage(this);
			index = addPage(viewsPage);
			setPageText(index, "Views");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	public void addPages() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * The Class class TreesPage.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class TreesPage extends FormPage {

		/** The form. */
		private ScrolledForm form;

		/** The toolkit. */
		private FormToolkit toolkit;

		/**
		 * Instantiates a new trees page.
		 * @param editor the editor
		 */
		public TreesPage(XMLEditor editor) {
			super(editor, "chn", "Views");
		}

		// ---------------------------------------------------------------
		// ---------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse .ui.forms.IManagedForm)
		 */
		@Override
		protected void createFormContent(final IManagedForm managedForm) {
			form = managedForm.getForm();
			toolkit = managedForm.getToolkit();

			form.setText("File Manager");
			form.getBody().setLayout(new GridLayout(2, true));

			// treeView = new TreeView(torrentEditor).createContent(form,
			// toolkit);
			// textEditorView = new
			// TextEditorView(torrentEditor).createContent(form,
			// toolkit);
		}

		// ---------------------------------------------------------------

		/**
		 * Sets the input.
		 */
		public void setInput() {
			// if (treeView == null) {
			// treeView = new TreeView(torrentEditor).createContent(form,
			// toolkit);
			// }
			// if (textEditorView == null) {
			// textEditorView = new TextEditorView(torrentEditor).createContent(
			// form, toolkit);
			// }
			// treeView.getTree().removeAll();
			// treeView.setTree();
			// treeView.setInput(extTorrent);
			// textEditorView.setInput(extTorrent);
		}
	}

}
