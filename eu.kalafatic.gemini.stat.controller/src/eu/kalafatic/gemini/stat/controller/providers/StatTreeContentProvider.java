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
package eu.kalafatic.gemini.stat.controller.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The Class class StatTreeContentProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatTreeContentProvider implements ITreeContentProvider {

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
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang. Object)
	 */
	@Override
	public Object[] getChildren(Object element) {
		if (element instanceof HashMap<?, ?>) {
			HashMap<?, ?> map = (HashMap<?, ?>) element;
			return map.values().toArray();

		} else if (element instanceof EcoreEMap<?, ?>) {
			EcoreEMap<?, ?> eMap = (EcoreEMap<?, ?>) element;
			return eMap.toArray();

		} else if (element instanceof List<?>) {
			List<?> list = (List<?>) element;
			return list.toArray();

		} else if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>) element;

			// if (entry.getValue() instanceof SwarmSession) {
			// SwarmSession session = (SwarmSession) entry.getValue();
			//
			// Map<String, Map<String, Object>> map1 = new HashMap<String,
			// Map<String, Object>>();
			//
			// Map<String, Object> map2 = new HashMap<String, Object>();
			//
			// map2.put("Trackers", session.getTrackers());
			// map2.put("Downloads", session.getDownloads());
			// map2.put("Uploads", session.getUploads());
			//
			// map1.put("Sessions", map2);
			//
			// return map1.values().toArray();
			// }
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
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

}
