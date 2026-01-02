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
package eu.kalafatic.gemini.bt.client.net.rc.controller.handlers;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.LOG;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SRV_ACC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import eu.kalafatic.gemini.bt.client.net.rc.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.rc.controller.lib.ERCPreferences;
import eu.kalafatic.gemini.bt.client.net.rc.controller.main.RCManager;
import eu.kalafatic.gemini.bt.client.net.rc.controller.model.RCModelManager;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class RCHttpHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCHttpHandler implements HttpHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange )
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		try {
			if (httpExchange.getRequestURI().toString().startsWith("/images/")) {
				sendImage(httpExchange);
				return;
			}
			String key = httpExchange.getRemoteAddress().toString();
			Log.log(ERCPreferences.MODULE, LOG + SRV_ACC + key);

			RCSession rcSession = RCModelManager.getInstance().getSession(key);
			rcSession.setHttpExchange(httpExchange);

			RCManager.getInstance().runSession(rcSession);

		} catch (Exception e) {
			Log.log(ERCPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Send image.
	 * @param httpExchange the http exchange
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendImage(HttpExchange httpExchange) throws IOException {
		String fileName = httpExchange.getRequestURI().toString();
		if (fileName.startsWith("/")) {
			fileName = fileName.substring(1);
		}
		InputStream inputStream = FileLocator.openStream(Activator.getDefault().getBundle(), new Path(fileName), false);

		byte[] bytesFromFile = getBytesFromFile(inputStream);

		Headers headers = httpExchange.getResponseHeaders();
		headers.set("Content-Type", FileUtils.getInstance().getMimeType(fileName).mime);
		// headers.set("Content-Length",
		// Integer.toString(bytesFromFile.length));
		// headers.set("Content-Disposition", "inline;filename=" + fileName);
		// headers.set("Cache-Control", "no-cache");
		// headers.set("Cache-Control", "no-cache");

		httpExchange.sendResponseHeaders(200, bytesFromFile.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(bytesFromFile);
		os.close();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the bytes from file.
	 * @param is the is
	 * @return the bytes from file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] getBytesFromFile(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int ret = 0;

		while ((ret = is.read(buffer)) >= 0) {
			baos.write(buffer, 0, ret);
		}
		is.close();
		return baos.toByteArray();
	}
}
