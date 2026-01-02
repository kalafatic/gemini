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
package eu.kalafatic.gemini.bt.client.net.rc.controller.model;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.osgi.storage.bundlefile.BundleFile;

import eu.kalafatic.gemini.bt.client.net.rc.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.rc.controller.lib.ERCPreferences;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.ERCSessionState;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcFactory;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class RCModelManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public class RCModelManager {

	/** The equinox bundle. */
	public EquinoxBundle EQUINOX_BUNDLE = (EquinoxBundle) Activator.getDefault().getBundle();

	/** The generation. */
	public Generation generation = (Generation) EQUINOX_BUNDLE.getModule().getCurrentRevision().getRevisionInfo();

	/** The bundle file. */
	public BundleFile BUNDLE_FILE = generation.getBundleFile();

	/** The templates loc. */
	public String TEMPLATES_LOC = BUNDLE_FILE.toString().concat(File.separator).concat("templates");

	/** The client end. */
	private String login, loginError, client, clientHeader, clientEnd;

	/** The rc. */
	private RC rc;

	/** The resource. */
	private Resource resource;

	/** The instance. */
	private volatile static RCModelManager INSTANCE = null;

	/** The rc file. */
	private File rcFile;

	/** The rc uri. */
	private URI rcURI;

	/**
	 * Instantiates a new RC model manager.
	 */
	public RCModelManager() {
		initRCModel();
		init();
	}

	/**
	 * Gets the single instance of RCModelManager.
	 * @return single instance of RCModelManager
	 */
	public static RCModelManager getInstance() {
		if (INSTANCE == null) {
			synchronized (RCModelManager.class) {
				INSTANCE = new RCModelManager();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the rc model.
	 */
	private void initRCModel() {
		try {
			String models = PREFERENCES.get(ECorePreferences.MODELS_LOC.getName(), (String) ECorePreferences.MODELS_LOC.getDef());

			rcFile = new File(models.concat(File.separator).concat("Model.rc"));
			rcURI = URI.createURI("file:/" + rcFile.getAbsolutePath());

			if (rcFile.exists()) {
				openModel();
			} else {
				createRCModel();
				doSave();
			}
			Assert.isNotNull(rc);

		} catch (Exception e) {
			e.printStackTrace();
			Log.log(ERCPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open model.
	 * @throws Exception the exception
	 */
	private void openModel() throws Exception {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getFactory(rcURI);
		resource = resourceSet.getResource(rcURI, true);
		rc = (RC) resource.getContents().get(0);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the rc model.
	 */
	private void createRCModel() {
		ResourceSetImpl resourceSet = null;
		try {
			resourceSet = new ResourceSetImpl();

			// Register the appropriate resource factory to handle all file
			// extensions.
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			// Register the package to ensure it is available during loading.
			resourceSet.getPackageRegistry().put(RcPackage.eNS_URI, RcPackage.eINSTANCE);
			resource = resourceSet.createResource(rcURI);
			rc = RcFactory.eINSTANCE.createRC();
			resource.getContents().add(rc);

		} catch (Exception e) {
			Log.log(ERCPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		try {
			File file = new File(TEMPLATES_LOC.concat(File.separator.concat("rc-login.html")));
			login = readFileAsString(file.getPath());
			file = new File(TEMPLATES_LOC.concat(File.separator.concat("rc-login-error.html")));
			loginError = readFileAsString(file.getPath());
			file = new File(TEMPLATES_LOC.concat(File.separator.concat("rc-header.html")));
			clientHeader = readFileAsString(file.getPath());
			file = new File(TEMPLATES_LOC.concat(File.separator.concat("rc-end.html")));
			clientEnd = readFileAsString(file.getPath());

		} catch (IOException e) {
			Log.log(ERCPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the session.
	 * @param remoteAddress the remote address
	 * @return the session
	 */
	public RCSession getSession(String remoteAddress) {

		RC rc = RCModelManager.getInstance().getRc();

		if (rc.getSessionMap().containsKey(remoteAddress)) {
			return rc.getSessionMap().get(remoteAddress);
		} else {
			return createRCSession(remoteAddress);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the rc session.
	 * @param remoteAddress the remote address
	 * @return the RC session
	 */
	private RCSession createRCSession(String remoteAddress) {
		RCSession rcSession = RcFactory.eINSTANCE.createRCSession();
		rcSession.setState(ERCSessionState.NEW);
		RCModelManager.getInstance().getRc().getSessionMap().put(remoteAddress, rcSession);
		return rcSession;
	}

	// ---------------------------------------------------------------

	/**
	 * Read file as string.
	 * @param filePath the file path
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String readFileAsString(String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the bytes from file.
	 * @param file the file
	 * @return the bytes from file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		byte[] bytes;
		InputStream is = new FileInputStream(file);
		try {
			long length = file.length();
			bytes = new byte[(int) length];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		} finally {
			is.close();
		}
		return bytes;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the post header.
	 * @return the post header
	 */
	public String getPostHeader() {
		Date now = new Date();

		String mimeType = "text/html";

		String header = "HTTP/1.1 200 OK\n\r" + "Date: " + now.toString() + "\n\rServer: kalafatic.eu\n\r" + "Last-Modified: " + now.toString() + "\n\rAccept-Ranges: bytes" // +
																																												// "\n\rConnection: close"
				+ "\n\rContent-Type: " + mimeType + "\n\n\r";

		return header;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the login.
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the client header.
	 * @return the client header
	 */
	public String getClientHeader() {
		return clientHeader;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the client end.
	 * @return the client end
	 */
	public String getClientEnd() {
		return clientEnd;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the client.
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the login error.
	 * @return the login error
	 */
	public String getLoginError() {
		return loginError;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the rc.
	 * @return the rc
	 */
	public RC getRc() {
		return rc;
	}

	// ---------------------------------------------------------------

	/**
	 * Do save.
	 */
	public void doSave() {
		ModelUtils.doSave(rc);
	}
}
