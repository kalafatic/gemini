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

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.COURIER_FONT;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.UndoEdit;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.bt.tm.view.interfaces.IView;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TextEditorView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TextEditorView implements IView {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The text. */
	private StyledText text;

	/** The has unsaved changes. */
	private boolean hasUnsavedChanges;

	/** The input. */
	private String input;

	/**
	 * Instantiates a new text editor view.
	 * @param torrentEditor the torrent editor
	 */
	public TextEditorView(TorrentEditor torrentEditor) {
		this.torrentEditor = torrentEditor;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the content.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @return the text editor view
	 */
	public TextEditorView createContent(ScrolledForm form, FormToolkit toolkit) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, "Torrent content editor", "Torrent content files", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 1, 1);

		createTextArea(client);

		return this;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the text area.
	 * @param client the client
	 */
	private void createTextArea(Composite client) {
		text = new StyledText(client, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		text.setFont(COURIER_FONT);

		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				hasUnsavedChanges = true;
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.view.interfaces.IView#setInput(eu.kalafatic .gemini.bt.client.model.torrents.ExtTorrent)
	 */
	@Override
	public void setInput(ExtTorrent extTorrent) {
		try {
			input = FileUtils.getInstance().getTextFromFile(extTorrent.getPath(), true);
			text.setText(input);

			IDocument document = new Document(input);
			System.out.println(document.get());
			MultiTextEdit edit = new MultiTextEdit();
			edit.addChild(new InsertEdit(0, "Java Entwicklung"));
			edit.addChild(new InsertEdit(0, " mit "));
			UndoEdit undo;

			undo = edit.apply(document);
			undo.apply(document);

		} catch (MalformedTreeException e) {
			Log.log(ETMPreferences.MODULE, e);
		} catch (BadLocationException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}
}
