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
package eu.kalafatic.gemini.bt.tm.view.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TORRENT_IMG;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;

/**
 * The Class class FileTreeLabelProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class FileTreeLabelProvider extends LabelProvider {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof FileTreeObject) {
			FileTreeObject fileTreeObject = (FileTreeObject) element;
			return fileTreeObject.getName();
		}
		return element.toString();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object obj) {
		if (obj instanceof FileTreeObject) {
			FileTreeObject fileTreeObject = (FileTreeObject) obj;
			if (fileTreeObject.getName().equals("root")) {
				return TORRENT_IMG;
			} else if (fileTreeObject.isFile()) {
				return FILE_IMG;
			} else {
				return FOLDER_IMG;
			}
		}
		return FILE_IMG;
	}
}
