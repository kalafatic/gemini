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
package eu.kalafatic.gemini.bt.tm.view.editors.pages;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.view.Activator;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;

/**
 * The Class class ViewsPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ViewsPage extends FormPage {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The Constant ID. */
	public static final String ID = "ViewsPage";

	/** The tree view. */
	private TreeView treeView;

	/** The text editor view. */
	private TextEditorView textEditorView;

	/** The form. */
	private ScrolledForm form;

	/** The toolkit. */
	private FormToolkit toolkit;

	/**
	 * Instantiates a new views page.
	 * @param torrentEditor the torrent editor
	 */
	public ViewsPage(TorrentEditor torrentEditor) {
		super(torrentEditor, ID, "Views");

		this.torrentEditor = torrentEditor;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		form = managedForm.getForm();
		toolkit = managedForm.getToolkit();

		form.setText("File Manager");
		form.setBackgroundImage(Activator.getDefault().getImage(Activator.IMG_FORM_BG));
		form.getBody().setLayout(new GridLayout(2, true));

		treeView = new TreeView(torrentEditor).createContent(form, toolkit);
		textEditorView = new TextEditorView(torrentEditor).createContent(form, toolkit);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the input.
	 * @param extTorrent the new input
	 */
	public void setInput(ExtTorrent extTorrent) {
		if (treeView == null) {
			treeView = new TreeView(torrentEditor).createContent(form, toolkit);
		}
		if (textEditorView == null) {
			textEditorView = new TextEditorView(torrentEditor).createContent(form, toolkit);
		}
		treeView.getTree().removeAll();
		treeView.setTree();
		treeView.setInput(extTorrent);
		textEditorView.setInput(extTorrent);
	}
}
