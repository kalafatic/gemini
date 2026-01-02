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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.internal.framework.BundleContextImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import eu.kalafatic.gemini.core.Activator;

/**
 * The Class class BundleUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public class BundleUtils {

	/** The ge prefix. */
	private final String GE_PREFIX = "eu.kalafatic";

	/** The instance. */
	private volatile static BundleUtils INSTANCE;

	/**
	 * Gets the single instance of BundleUtils.
	 * @return single instance of BundleUtils
	 */
	public static BundleUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (BundleUtils.class) {
				INSTANCE = new BundleUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Checks if is bundle installed.
	 * @param installedBundles the installed bundles
	 * @param prefix the prefix
	 * @return true, if is bundle installed
	 */
	public boolean isBundleInstalled(Map<Long, Bundle> installedBundles, String prefix) {
		if (installedBundles == null) {
			installedBundles = getGeminiPlugins();
		}
		for (Bundle bundle : installedBundles.values()) {
			if (bundle.getSymbolicName().startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the gemini plugins.
	 * @return the gemini plugins
	 */
	public Map<Long, Bundle> getGeminiPlugins() {
		Map<Long, IBundleGroup> bundlesMap = getInstalledBundles();
		Map<Long, Bundle> geminiBundlesMap = new HashMap<Long, Bundle>();

		BundleContext bundleContext = Activator.getDefault().getBundle().getBundleContext();

		if (bundleContext instanceof BundleContextImpl) {

			Bundle[] bundles = ((BundleContextImpl) bundleContext).getBundles();

			for (int i = 0; i < bundles.length; i++) {
				if (!bundlesMap.containsKey(bundles[i].getBundleId())) {

					if (bundles[i].getSymbolicName().startsWith(GE_PREFIX)) {
						geminiBundlesMap.put(bundles[i].getBundleId(), bundles[i]);
					}
				}
			}
		}
		return geminiBundlesMap;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the installed bundles.
	 * @return the installed bundles
	 */
	public Map<Long, IBundleGroup> getInstalledBundles() {
		// get all the plugins that belong to features
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();

		Map<Long, IBundleGroup> bundlesMap = new HashMap<Long, IBundleGroup>();

		if (providers != null) {
			for (int i = 0; i < providers.length; i++) {
				IBundleGroup[] bundleGroups = providers[i].getBundleGroups();

				for (int j = 0; j < bundleGroups.length; j++) {
					Bundle[] bundles = bundleGroups[j] == null ? new Bundle[0] : bundleGroups[j].getBundles();

					for (int k = 0; k < bundles.length; k++) {
						bundlesMap.put(bundles[k].getBundleId(), bundleGroups[j]);
					}
				}
			}
		}
		return bundlesMap;
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is plugin installed.
	 * @param pluginId the plugin id
	 * @return true, if is plugin installed
	 */
	public boolean isPluginInstalled(String pluginId) {
		Map<Long, IBundleGroup> bundlesMap = getInstalledBundles();

		BundleContext bundleContext = Activator.getDefault().getBundle().getBundleContext();

		if (bundleContext instanceof BundleContextImpl) {
			Bundle[] bundles = ((BundleContextImpl) bundleContext).getBundles();

			for (int i = 0; i < bundles.length; i++) {
				if (!bundlesMap.containsKey(bundles[i].getBundleId())) {

					if (bundles[i].getSymbolicName().contains(pluginId)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
