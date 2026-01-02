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
package eu.kalafatic.gemini.bt.client.controller.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import eu.kalafatic.gemini.bt.client.controller.model.actions.TorrentModelActions;

/**
 * The Class class AddTorrentHandler.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class AddTorrentHandler extends AbstractHandler {

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.client.controller.addTorrentParameter1";

	/** The PARAMETE r_2. */
	private final String PARAMETER_2 = "eu.kalafatic.gemini.bt.client.controller.addTorrentParameter2";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String torrentPathParam = event.getParameter(PARAMETER_1);
		boolean finishedPathParam = Boolean.parseBoolean(event.getParameter(PARAMETER_2));

		TorrentModelActions.INSTANCE.addTorrentToModel(torrentPathParam, finishedPathParam);

		return null;
	}
}
