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
package eu.kalafatic.gemini.webBrowser.model.web.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;

/**
 * A factory for creating WebAdapter objects.
 */
public class WebAdapterFactory extends AdapterFactoryImpl {

	/** The model package. */
	protected static WebPackage modelPackage;

	/**
	 * Instantiates a new web adapter factory.
	 */
	public WebAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WebPackage.eINSTANCE;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#isFactoryForType(java.lang.Object)
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/** The model switch. */
	protected WebSwitch<Adapter> modelSwitch = new WebSwitch<Adapter>() {
		@Override
		public Adapter caseWeb(Web object) {
			return createWebAdapter();
		}

		@Override
		public Adapter casePage(Page object) {
			return createPageAdapter();
		}

		@Override
		public Adapter caseFolder(Folder object) {
			return createFolderAdapter();
		}

		@Override
		public Adapter caseStringToFolderMapEntry(Map.Entry<String, Folder> object) {
			return createStringToFolderMapEntryAdapter();
		}

		@Override
		public Adapter caseStringToPageMapEntry(Map.Entry<String, Page> object) {
			return createStringToPageMapEntryAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#createAdapter(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createWebAdapter() {
		return null;
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToFolderMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createStringToPageMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new WebAdapter object.
	 * @return the adapter
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // WebAdapterFactory
