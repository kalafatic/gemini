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
package eu.kalafatic.gemini.bt.tm.view.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * The Class class EditorContributor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("unused")
public class EditorContributor extends MultiPageEditorActionBarContributor {

	/** The active editor part. */
	private IEditorPart activeEditorPart;

	/** The delete action. */
	private IAction deleteAction;

	/** The undo action. */
	private IAction undoAction;

	/** The redo action. */
	private IAction redoAction;

	/** The copy action. */
	private IAction copyAction;

	/** The cut action. */
	private IAction cutAction;

	/** The paste action. */
	private IAction pasteAction;

	/** The select all action. */
	private IAction selectAllAction;

	/** The find action. */
	private IAction findAction;

	/** The save action. */
	private IAction saveAction;

	/** The save as action. */
	private IAction saveAsAction;

	/** The editor. */
	private ITextEditor editor;

	/**
	 * Instantiates a new editor contributor.
	 */
	public EditorContributor() {
		super();
		// createActions();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the action.
	 * @param editor the editor
	 * @param actionID the action id
	 * @return the action
	 */
	protected IAction getAction(ITextEditor editor, String actionID) {
		return (editor == null ? null : editor.getAction(actionID));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-JavaDoc) Method declared in AbstractMultiPageEditorActionBarContributor.
	 */

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorActionBarContributor#setActivePage (org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActivePage(IEditorPart part) {
		if (activeEditorPart == part) {
			return;
		}
		activeEditorPart = part;

		IActionBars actionBars = getActionBars();
		if (actionBars != null) {

			editor = (part instanceof ITextEditor) ? (ITextEditor) part : null;

			createActions(editor);
			setHandlers(actionBars);

			contributeToMenu(actionBars.getMenuManager());
			contributeToToolBar(actionBars.getToolBarManager());

			// IWorkbenchWindow window = part.getSite().getWorkbenchWindow();
			// ICoolBarManager coolBarManager = null;
			//
			// if (((WorkbenchWindow) window).getCoolBarVisible()) {
			// coolBarManager = ((WorkbenchWindow) window)
			// .getCoolBarManager2();
			// contributeToCoolBar(coolBarManager);
			// }

			actionBars.setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(), getAction(editor, IDEActionFactory.BOOKMARK.getId()));
			actionBars.updateActionBars();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the handlers.
	 * @param actionBars the new handlers
	 */
	private void setHandlers(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAllAction);
		actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), findAction);
		// actionBars.setGlobalActionHandler(ActionFactory.SAVE.getId(),
		// saveAction);
		// actionBars.setGlobalActionHandler(ActionFactory.SAVE_AS.getId(),
		// saveAsAction);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the actions.
	 * @param editor the editor
	 */
	private void createActions(ITextEditor editor) {
		deleteAction = getAction(editor, ITextEditorActionConstants.DELETE);

		undoAction = getAction(editor, ITextEditorActionConstants.UNDO);
		redoAction = getAction(editor, ITextEditorActionConstants.REDO);

		copyAction = getAction(editor, ITextEditorActionConstants.COPY);
		cutAction = getAction(editor, ITextEditorActionConstants.CUT);
		pasteAction = getAction(editor, ITextEditorActionConstants.PASTE);

		selectAllAction = getAction(editor, ITextEditorActionConstants.SELECT_ALL);
		findAction = getAction(editor, ITextEditorActionConstants.FIND);
		saveAction = getAction(editor, ActionFactory.SAVE.getId());
		saveAsAction = getAction(editor, ActionFactory.SAVE_ALL.getId());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse .jface.action.IMenuManager)
	 */
	@Override
	public void contributeToMenu(IMenuManager manager) {
		if (manager != null && editor != null) {
			// manager.add(new Separator());
			IMenuManager menu = new MenuManager("&Editor");
			manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
			menu.add(deleteAction);
			// menu.add(saveAction);
			// menu.add(saveAsAction);
			menu.add(redoAction);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org .eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void contributeToToolBar(IToolBarManager manager) {
		if (manager != null && editor != null) {
			IMenuManager menu = new MenuManager("&Editor");
			// manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS,
			// menu);
			menu.add(deleteAction);
			// menu.add(saveAction);
			// menu.add(saveAsAction);
			menu.add(redoAction);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToCoolBar(org .eclipse.jface.action.ICoolBarManager)
	 */
	@Override
	public void contributeToCoolBar(ICoolBarManager manager) {
		if (manager != null && editor != null) {
			// manager.add(new Separator());
			// IMenuManager menu = new MenuManager("&Editor");
			// manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS,
			// menu);

			IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
			manager.add(new ToolBarContributionItem(toolbar, "main"));

			toolbar.add(deleteAction);
			// toolbar.add(saveAction);
			// toolbar.add(saveAsAction);
			toolbar.add(redoAction);
		}
	}
}
