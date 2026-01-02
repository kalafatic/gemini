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
package eu.kalafatic.gemini.core.application;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import eu.kalafatic.gemini.core.perspectives.CorePerspective;

/**
 * The Class class ApplicationWorkbenchAdvisor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor (org.eclipse.ui.application.IWorkbenchWindowConfigurer)
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui .application.IWorkbenchConfigurer)
	 */
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
	 */
	@Override
	public void preStartup() {
		super.preStartup();
		// Check for updates before starting up.
		// If an update is performed, restart.

		// final IProvisioningAgent agent = (IProvisioningAgent) ServiceHelper
		// .getService(Activator.bundleContext,
		// IProvisioningAgent.SERVICE_NAME);
		//
		// if (P2Util.checkForUpdates(agent,)) {
		// PlatformUI.getWorkbench().restart();
		// }

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#postStartup()
	 */
	@Override
	public void postStartup() {
		super.postStartup();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId ()
	 */
	@Override
	public String getInitialWindowPerspectiveId() {
		return CorePerspective.ID;
	}
}
