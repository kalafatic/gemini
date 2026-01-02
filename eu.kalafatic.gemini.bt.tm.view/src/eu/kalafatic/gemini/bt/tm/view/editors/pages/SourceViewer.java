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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.core.interfaces.ATreeViewer;
import eu.kalafatic.gemini.core.providers.FileTreeContentProvider;
import eu.kalafatic.gemini.core.providers.FileTreeLabelProvider;

/**
 * The Class class SourceViewer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SourceViewer extends ATreeViewer {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/**
	 * Instantiates a new source viewer.
	 * @param torrentEditor the torrent editor
	 */
	public SourceViewer(TorrentEditor torrentEditor) {
		this.torrentEditor = torrentEditor;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/* (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ATreeViewer#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		((TreeViewer) viewer).getTree().setLayoutData(new GridData(GridData.FILL_BOTH));

		((TreeViewer) viewer).setContentProvider(new FileTreeContentProvider());
		((TreeViewer) viewer).setLabelProvider(new FileTreeLabelProvider());

		viewer.setInput("root");

		init();
	}
}
