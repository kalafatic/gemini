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
package eu.kalafatic.gemini.bt.client.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

import eu.kalafatic.gemini.bt.client.net.controller.PreferenceInitializer;

/**
 * The Class class Activator.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class Activator extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "eu.kalafatic.gemini.bt.client.controller";

	/** The plugin. */
	private static Activator plugin;

	/** The preferences. */
	@SuppressWarnings("unused")
	private Preferences preferences;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		preferences = Platform.getPreferencesService().getRootNode().node(PLUGIN_ID);
		PreferenceInitializer.setUp();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the default.
	 * @return the default
	 */
	public static Activator getDefault() {
		return plugin;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the image descriptor.
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the image file.
	 * @param imgName the img name
	 * @return the image file
	 */
	public File getImageFile(String imgName) {
		Bundle bundle = plugin.getBundle();
		Path path = new Path(imgName); //$NON-NLS-1$
		URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
		URL fileUrl = null;
		try {
			fileUrl = FileLocator.toFileURL(url);
		} catch (IOException e) {
			// Will happen if the file cannot be read for some reason
			e.printStackTrace();
		}
		return new File(fileUrl.getPath());
	}
}
