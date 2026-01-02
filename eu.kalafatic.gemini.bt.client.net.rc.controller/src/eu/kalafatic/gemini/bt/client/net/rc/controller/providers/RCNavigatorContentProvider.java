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
package eu.kalafatic.gemini.bt.client.net.rc.controller.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Page;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl.FolderImpl;

/**
 * The Class class RCNavigatorContentProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCNavigatorContentProvider implements ITreeContentProvider {

	/** The input map. */
	@SuppressWarnings("unused")
	private EMap<String, Folder> inputMap;

	/**
	 * Instantiates a new rC navigator content provider.
	 * @param inputMap the input map
	 */
	public RCNavigatorContentProvider(EMap<String, Folder> inputMap) {
		this.inputMap = inputMap;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java .lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

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
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		System.err.println("inputChanged");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
	 */

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof EcoreEMap) {
			EcoreEMap<?, ?> map = (EcoreEMap<?, ?>) parentElement;
			return map.values().toArray();
		} else if (parentElement instanceof FolderImpl) {
			FolderImpl folder = (FolderImpl) parentElement;
			Collection<Folder> folders = folder.getFolders().values();
			Collection<Page> pages = folder.getPages().values();

			List<EObject> all = new ArrayList<EObject>();
			all.addAll(folders);
			all.addAll(pages);

			return all.toArray();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object )
	 */
	@Override
	public Object getParent(Object element) {
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
		// Return whether the parent has children
		return obj == null ? false : obj.length > 0;
	}
}
