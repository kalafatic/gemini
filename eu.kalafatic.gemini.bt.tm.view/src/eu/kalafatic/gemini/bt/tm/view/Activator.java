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
package eu.kalafatic.gemini.bt.tm.view;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Class class Activator.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class Activator extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "eu.kalafatic.gemini.bt.tm.view";

	/** The plugin. */
	private static Activator plugin;

	/** The plugin properties. */
	protected static String PLUGIN_PROPERTIES = "plugin.properties";

	/** The plugin properties. */
	protected static PropertyResourceBundle pluginProperties;

	/** The resource bundle. */
	private ResourceBundle resourceBundle;

	/** The form colors. */
	private FormColors formColors;

	/** The Constant IMG_FORM_BG. */
	public static final String IMG_FORM_BG = "formBg"; //$NON-NLS-1$

	/** The Constant IMG_LARGE. */
	public static final String IMG_LARGE = "large"; //$NON-NLS-1$

	/** The Constant IMG_HORIZONTAL. */
	public static final String IMG_HORIZONTAL = "horizontal"; //$NON-NLS-1$

	/** The Constant IMG_VERTICAL. */
	public static final String IMG_VERTICAL = "vertical"; //$NON-NLS-1$

	/** The Constant IMG_SAMPLE. */
	public static final String IMG_SAMPLE = "sample"; //$NON-NLS-1$

	/** The Constant IMG_WIZBAN. */
	public static final String IMG_WIZBAN = "wizban"; //$NON-NLS-1$

	/** The Constant IMG_LINKTO_HELP. */
	public static final String IMG_LINKTO_HELP = "linkto_help"; //$NON-NLS-1$

	/** The Constant IMG_HELP_TOPIC. */
	public static final String IMG_HELP_TOPIC = "help_topic"; //$NON-NLS-1$

	/** The Constant IMG_CLOSE. */
	public static final String IMG_CLOSE = "close"; //$NON-NLS-1$

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
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		try {
			if (formColors != null) {
				formColors.dispose();
				formColors = null;
			}
		} finally {
			super.stop(context);
		}
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
	 * Gets the resource bundle.
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the image.
	 * @param key the key
	 * @return the image
	 */
	public Image getImage(String key) {
		return getImageRegistry().get(key);
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
	 * Gets the workspace.
	 * @return the workspace
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
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

	// ---------------------------------------------------------------

	/**
	 * Gets the resource string.
	 * @param key the key
	 * @return the resource string
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = getDefault().getResourceBundle();
		try {
			return (bundle != null ? bundle.getString(key) : key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Register image.
	 * @param registry the registry
	 * @param key the key
	 * @param fileName the file name
	 */
	@SuppressWarnings("deprecation")
	private void registerImage(ImageRegistry registry, String key, String fileName) {
		try {
			IPath path = new Path("icons/" + fileName); //$NON-NLS-1$
			URL url = find(path);
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse .jface.resource.ImageRegistry)
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		registerImage(registry, IMG_FORM_BG, "form_banner.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_LARGE, "large_image.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_HORIZONTAL, "th_horizontal.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_VERTICAL, "th_vertical.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_SAMPLE, "sample.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_WIZBAN, "newprj_wiz.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_LINKTO_HELP, "linkto_help.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_HELP_TOPIC, "help_topic.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_CLOSE, "close_view.gif"); //$NON-NLS-1$
	}
}
