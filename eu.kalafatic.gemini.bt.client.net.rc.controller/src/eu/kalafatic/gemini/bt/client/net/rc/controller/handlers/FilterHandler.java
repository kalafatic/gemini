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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

/**
 * The Class class FilterHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FilterHandler extends Filter {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see com.sun.net.httpserver.Filter#doFilter(com.sun.net.httpserver.HttpExchange, com.sun.net.httpserver.Filter.Chain)
	 */
	@Override
	public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
		parseGetParameters(exchange);
		parsePostParameters(exchange);
		chain.doFilter(exchange);
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the get parameters.
	 * @param exchange the exchange
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	private void parseGetParameters(HttpExchange exchange) throws UnsupportedEncodingException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		URI requestedUri = exchange.getRequestURI();
		String query = requestedUri.getRawQuery();
		parseQuery(query, parameters);
		exchange.setAttribute("parameters", parameters);
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the post parameters.
	 * @param exchange the exchange
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void parsePostParameters(HttpExchange exchange) throws IOException {
		if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
			@SuppressWarnings("unchecked")
			Map<String, Object> parameters = (Map<String, Object>) exchange.getAttribute("parameters");
			InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
			parseQuery(query, parameters);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Parses the query.
	 * @param query the query
	 * @param parameters the parameters
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@SuppressWarnings("unchecked")
	private void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see com.sun.net.httpserver.Filter#description()
	 */
	@Override
	public String description() {
		return null;
	}
}
