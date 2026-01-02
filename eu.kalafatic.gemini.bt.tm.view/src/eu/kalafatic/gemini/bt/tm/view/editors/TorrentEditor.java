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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.view.editors.pages.FileManagerPage;
import eu.kalafatic.gemini.bt.tm.view.editors.pages.SettingsPage;
import eu.kalafatic.gemini.bt.tm.view.editors.pages.ViewsPage;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.factories.SectionFactory;
import eu.kalafatic.gemini.core.interfaces.IFormInput;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class TorrentEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TorrentEditor extends FormEditor implements ISaveablePart, IResourceChangeListener, IFormInput {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor";

	/** The section factory. */
	private SectionFactory sectionFactory;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The views page index. */
	private final int viewsPageIndex = 2;

	/** The views page. */
	private ViewsPage viewsPage;

	/** The file manager page. */
	private FileManagerPage fileManagerPage;

	/** The settings page. */
	private SettingsPage settingsPage;

	/** The i form inputs. */
	public List<IFormInput> iFormInputs = new ArrayList<IFormInput>();

	/** The dirty. */
	private boolean dirty = false;

	/**
	 * Instantiates a new torrent editor.
	 */
	public TorrentEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);

		sectionFactory = new SectionFactory();
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		try {
			EMap<String, ExtTorrent> newTorrents = BTStructureModelManager.getInstance().getBtStructure().getNewTorrents();

			if (newTorrents.isEmpty()) {
				extTorrent = BTStructureModelManager.getInstance().createNewTorrent();
			} else {
				extTorrent = (ExtTorrent) newTorrents.values().toArray(new EObject[newTorrents.size()])[0];
			}
		} catch (IOException e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @param extTorrent the ext torrent
	 */
	public void init(ExtTorrent extTorrent) {
		this.extTorrent = extTorrent;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		createFileManagerPage();
		createSettingsPage();
		createViewsPage();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#pageChange(int)
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);

		if (newPageIndex == viewsPageIndex) {
			viewsPage.setInput(extTorrent);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the file manager page.
	 */
	public void createFileManagerPage() {
		try {
			fileManagerPage = new FileManagerPage(this);
			iFormInputs.add(fileManagerPage);
			int index = addPage(fileManagerPage);
			setPageText(index, "File Manager");
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the settings page.
	 */
	public void createSettingsPage() {
		try {
			settingsPage = new SettingsPage(this);
			iFormInputs.add(settingsPage);
			int index = addPage(settingsPage);
			setPageText(index, "Settings");
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the views page.
	 */
	private void createViewsPage() {
		try {
			viewsPage = new ViewsPage(this);
			int index = addPage(viewsPage);
			setPageText(index, "Views");
		} catch (PartInitException e) {
			DialogUtils.INSTANCE.showException(e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the dirty.
	 * @param dirty the new dirty
	 */
	public void setDirty(boolean dirty) {
		if (this.dirty == dirty) {
			return;
		}
		this.dirty = dirty;
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.view.interfaces.IFormInput#setInput()
	 */
	@Override
	public boolean setInput() {
		for (IFormInput iFormInput : iFormInputs) {
			if (!iFormInput.setInput()) {
				setActivePage(((FormPage) iFormInput).getId());
				return false;
			}
		}
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the ext torrent.
	 * @return the ext torrent
	 */
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the section factory.
	 * @return the section factory
	 */
	public SectionFactory getSectionFactory() {
		return sectionFactory;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org .eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime. IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		CommandFactory.INSTANCE.executeCommand("eu.kalafatic.gemini.bt.tm.controller.handlers.CreateTorrentHandler", extTorrent.getName());
		setDirty(false);
		// CommandUtils.getInstance().execute("eu.kalafatic.gemini.bt.tm.controller.handlers.CreateTorrentHandler");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the views page.
	 * @return the views page
	 */
	public ViewsPage getViewsPage() {
		return viewsPage;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the file manager page.
	 * @return the file manager page
	 */
	public FileManagerPage getFileManagerPage() {
		return fileManagerPage;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the settings page.
	 * @return the settings page
	 */
	public SettingsPage getSettingsPage() {
		return settingsPage;
	}
}
