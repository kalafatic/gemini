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
package eu.kalafatic.gemini.webbrowser.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;
import eu.kalafatic.gemini.webBrowser.model.web.Folder;
import eu.kalafatic.gemini.webBrowser.model.web.Page;
import eu.kalafatic.gemini.webBrowser.model.web.Web;
import eu.kalafatic.gemini.webBrowser.model.web.WebFactory;
import eu.kalafatic.gemini.webBrowser.model.web.WebPackage;
import eu.kalafatic.gemini.webbrowser.controller.lib.EWebPreferences;

/**
 * The Class class WebModelManager.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class WebModelManager {

	/** The INSTANCE. */
	private static WebModelManager INSTANCE = null;

	/** The web file. */
	private File webFile;

	/** The web uri. */
	private URI webURI;

	/** The web. */
	private Web web;

	/**
	 * Instantiates a new web model manager.
	 */
	public WebModelManager() {
		initWebModel();
	}

	/**
	 * Gets the single instance of WebModelManager.
	 * @return single instance of WebModelManager
	 */
	public static WebModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (WebModelManager.class) {
				INSTANCE = new WebModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the web model.
	 */
	private void initWebModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			webFile = new File(models.concat(File.separator).concat("Model.web"));
			webURI = URI.createURI("file:/" + webFile.getAbsolutePath());

			if (webFile.exists()) {
				openModel();
			} else {
				createModel();
				initModel();
				doSave();
			}
		} catch (Exception e) {
			Log.log(EWebPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 */
	private void openModel() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(webURI);
		Resource ecoreResource = resourceSet.getResource(webURI, true);
		web = (Web) ecoreResource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the model.
	 */
	private void createModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();
			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(WebPackage.eNS_URI, WebPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(webURI);
			web = WebFactory.eINSTANCE.createWeb();
			resource.getContents().add(web);

		} catch (Exception e) {
			Log.log(EWebPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the model.
	 */
	private void initModel() {
		Page page = WebFactory.eINSTANCE.createPage();
		page.setAddress(web.getHomePage());
		Folder folder = WebFactory.eINSTANCE.createFolder();
		folder.setAddress("Home");
		folder.getPages().put(web.getHomePage(), page);
		web.getFolders().put("Home", folder);
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		ModelUtils.doSave(web);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the web.
	 * @return the web
	 */
	public Web getWeb() {
		return web;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the web.
	 * @param web the new web
	 */
	public void setWeb(Web web) {
		this.web = web;
	}
}
