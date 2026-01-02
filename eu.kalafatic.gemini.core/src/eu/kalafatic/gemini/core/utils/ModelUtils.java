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
package eu.kalafatic.gemini.core.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.core.lib.EEncoding;

/**
 * The Class class ModelUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ModelUtils {

	/** The Constant SAVE_OPTIONS. */
	public static final Map<String, Object> SAVE_OPTIONS = new HashMap<String, Object>();
	static {
		SAVE_OPTIONS.put(XMLResource.OPTION_ENCODING, EEncoding.UTF_8.getLiteral());
		// SAVE_OPTIONS.put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
		// SAVE_OPTIONS.put(XMLResource.OPTION_ZIP, Boolean.TRUE);
		// SAVE_OPTIONS.put(XMLResource.OPTION_FORMATTED, Boolean.FALSE);
		// SAVE_OPTIONS.put(XMLResource.OPTION_DISABLE_NOTIFY, Boolean.TRUE);
		// SAVE_OPTIONS.put(XMLResource.OPTION_SAVE_ONLY_IF_CHANGED,
		// XMLResource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 * @param eObject the e object
	 */
	public static void doSave(final EObject eObject) {
		doSave(eObject, SAVE_OPTIONS);
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 * @param eObject the e object
	 * @param saveOptions the save options
	 */
	public static void doSave(final EObject eObject, final Map<?, ?> saveOptions) {
		if (saveOptions == null) {
			eObject.eResource().setModified(true);
			save(eObject, Collections.emptyMap());
		} else {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					save(eObject, saveOptions);
				}
			});
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Save.
	 * @param eObject the e object
	 * @param saveOptions the save options
	 */
	private static void save(final EObject eObject, final Map<?, ?> saveOptions) {
		try {
			if (eObject != null && eObject.eResource() != null) {
				eObject.eResource().save(saveOptions);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
