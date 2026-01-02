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
package eu.kalafatic.gemini.bt.visual.editors;

import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * The Class GraphMultiPageEditor.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class GraphMultiPageEditor extends MultiPageEditorPart implements
		IResourceChangeListener {

	/** The editor. */
	private TextEditor editor;

	/** The font. */
	private Font font;

	/** The text. */
	private StyledText text;

	/** The solve page index. */
	private int plotPageIndex, solvePageIndex;

	
	/**
	 * Instantiates a new graph multi page editor.
	 */
	public GraphMultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the input page.
	 */
	void createInputPage() {
		try {
			editor = new TextEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Creates the model page.
	 * 
	 * @param editorInput
	 *            the editor input
	 */
	public void createModelPage(IEditorInput editorInput) {
		try {
			editor = new TextEditor();
			int index = addPage(editor, editorInput);
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Creates the output page.
	 * 
	 * @param editorInput
	 *            the editor input
	 */
	public void createOutputPage(IEditorInput editorInput) {
		try {
			editor = new TextEditor();
			int index = addPage(editor, editorInput);
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Creates the plot page.
	 * 
	 * @param editorInput
	 *            the editor input
	 * 
	 * @return the canvas
	 */
	public Canvas createPlotPage(IEditorInput editorInput) {
		
		Composite composite = getContainer();
		composite.setLayout(new FillLayout());
				
		Canvas canvas = new Canvas(composite, SWT.V_SCROLL | SWT.H_SCROLL);
		
		plotPageIndex = addPage(canvas);		
		
		setPageText(plotPageIndex, "Plotter");		
		
		return canvas;
	}
	
	// ---------------------------------------------------------------
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#getActivePage()
	 */
	@Override
	public int getActivePage() {		
		return super.getActivePage();
	}
	
	// ---------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#setActivePage(int)
	 */
	@Override
	public void setActivePage(int pageIndex) {
		super.setActivePage(pageIndex);
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	protected void createPages() {
		createInputPage();
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#dispose()
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	/**
	 * Goto marker.
	 * 
	 * @param marker
	 *            the marker
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		// if (!(editorInput instanceof IFileEditorInput))
		// throw new
		// PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
//		if (newPageIndex == 2) {
//			sortWords();
//		}
	}
	
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow()
							.getPages();
					for (int i = 0; i < pages.length; i++) {
						if (((FileEditorInput) editor.getEditorInput())
								.getFile().getProject().equals(
										event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(editor
									.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
					}
				}
			});
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Sets the font.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Sort words.
	 */
	@SuppressWarnings("unchecked")
	void sortWords() {

		String editorText = editor.getDocumentProvider().getDocument(
				editor.getEditorInput()).get();

		StringTokenizer tokenizer = new StringTokenizer(editorText,
				" \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the plot page index.
	 * 
	 * @return the plot page index
	 */
	public int getPlotPageIndex() {
		return plotPageIndex;
	}
	
	// ---------------------------------------------------------------

	/**
	 * Gets the solve page index.
	 * 
	 * @return the solve page index
	 */
	public int getSolvePageIndex() {
		return solvePageIndex;
	}
}
