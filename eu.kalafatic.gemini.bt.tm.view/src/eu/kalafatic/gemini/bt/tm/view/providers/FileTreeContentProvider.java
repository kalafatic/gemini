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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;

/**
 * The Class class FileTreeContentProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class FileTreeContentProvider implements IStructuredContentProvider, ITreeContentProvider {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java .lang.Object)
	 */
	@Override
	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object )
	 */
	@Override
	public Object getParent(Object child) {
		if (child instanceof FileTreeObject) {
			return ((FileTreeObject) child).getParent();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
	 */
	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof FileTreeObject) {
			FileTreeObject fileTreeObject = (FileTreeObject) parent;
			if (fileTreeObject.getChildMap() != null) {
				return fileTreeObject.getChildMap().values().toArray();
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang. Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		Object[] obj = getChildren(element);
		return obj == null ? false : obj.length > 0;
	}
}
