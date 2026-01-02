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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.IDocumentProvider;

import eu.kalafatic.gemini.core.editors.providers.ColorManager;
import eu.kalafatic.gemini.core.editors.providers.XMLFileDocumentProvider;
import eu.kalafatic.gemini.core.editors.providers.XMLTextDocumentProvider;
import eu.kalafatic.gemini.core.editors.xml.XMLConfiguration;
import eu.kalafatic.gemini.core.lib.EMimeType;

/**
 * The Class class Editor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
class Editor extends TextEditor {

	/** The color manager. */
	private ColorManager colorManager;

	/**
	 * Instantiates a new editor.
	 * @param protocol the protocol
	 */
	Editor(EMimeType protocol) {
		super();

		colorManager = new ColorManager();
		IDocumentProvider documentProvider = createDocumentProvider(getEditorInput());

		switch (protocol) {
		case TXT:
			setSourceViewerConfiguration(new TextSourceViewerConfiguration());
			setDocumentProvider(documentProvider);
			break;
		case XML:
			setSourceViewerConfiguration(new XMLConfiguration(colorManager));
			setDocumentProvider(documentProvider);
			break;

		default:
			setSourceViewerConfiguration(new TextSourceViewerConfiguration());
			setDocumentProvider(documentProvider);
			break;
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#doSetInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	protected final void doSetInput(IEditorInput input) throws CoreException {
		setDocumentProvider(createDocumentProvider(input));
		super.doSetInput(input);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the document provider.
	 * @param input the input
	 * @return the i document provider
	 */
	private IDocumentProvider createDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput) {
			return new XMLTextDocumentProvider();
		} else if (input instanceof IStorageEditorInput) {
			return new XMLFileDocumentProvider();
		} else {
			return new XMLTextDocumentProvider();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
}
