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
package eu.kalafatic.gemini.bt.tracker.controller;

import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

/**
 * The Class class Activator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class Activator extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "eu.kalafatic.gemini.bt.tracker.controller";

	/** The plugin properties. */
	protected static String PLUGIN_PROPERTIES = "plugin.properties";

	/** The plugin properties. */
	protected static PropertyResourceBundle pluginProperties;

	/** The plugin. */
	private static Activator plugin;

	/** The preferences. */
	private static Preferences preferences;

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
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the preferences.
	 * @return the preferences
	 */
	public static Preferences getPreferences() {
		return preferences;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the plugin properties.
	 * @return the plugin properties
	 */
	public static PropertyResourceBundle getPluginProperties() {
		if (pluginProperties == null) {
			try {
				pluginProperties = new PropertyResourceBundle(FileLocator.openStream(getDefault().getBundle(), new Path(PLUGIN_PROPERTIES), false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pluginProperties;
	}
}
