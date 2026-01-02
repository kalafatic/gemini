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
package eu.kalafatic.gemini.webbrowser.controller.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.impl.FolderImpl;
import eu.kalafatic.gemini.webBrowser.model.web.impl.PageImpl;

/**
 * The Class class WebNavigatorLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebNavigatorLabelProvider extends LabelProvider {

	/** The input map. */
	@SuppressWarnings("unused")
	private EMap<String, Folder> inputMap;

	/**
	 * Instantiates a new web navigator label provider.
	 * @param inputMap the input map
	 */
	public WebNavigatorLabelProvider(EMap<String, Folder> inputMap) {
		this.inputMap = inputMap;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof FolderImpl) {
			FolderImpl folder = (FolderImpl) element;
			return folder.getAddress();
		} else if (element instanceof PageImpl) {
			PageImpl page = (PageImpl) element;
			return page.getAddress();
		}
		return "";
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof FolderImpl) {
			return FOLDER_IMG;
		} else if (element instanceof PageImpl) {
			return FILE_IMG;
		}
		return null;
	}
}
