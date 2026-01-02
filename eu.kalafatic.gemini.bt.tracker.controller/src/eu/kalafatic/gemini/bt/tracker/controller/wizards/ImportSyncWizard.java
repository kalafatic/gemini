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
package eu.kalafatic.gemini.bt.tracker.controller.wizards;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.tracker.controller.factories.JobFactory;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSummaryPage;
import eu.kalafatic.gemini.core.interfaces.ASync;
import eu.kalafatic.gemini.core.models.SyncObject;

/**
 * The Class class ImportSyncWizard.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ImportSyncWizard extends ATrackerSyncWizard {

	/**
	 * Instantiates a new import sync wizard.
	 */
	public ImportSyncWizard() {
		super(false);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISWizard#createJobs()
	 */
	@Override
	public void createJobs() {
		total = workToDo = 0;
		worked = new AtomicInteger(0);
		done = new AtomicInteger(0);

		if (((SyncSummaryPage) pages[3]).parallel) {
			createExecutors(src.size() >= dest.size() ? src.size() : dest.size());
		} else {
			createExecutors(1);
		}
		// super.createJobs();

		Progress progress = new Progress(worked, done);

		TableItem[] items = dest.get(SYNCHRONIZER).getItems();

		for (TableItem item : items) {
			SyncObject syncObject = (SyncObject) item.getData();

			if ((syncObject.FLAGS & ASync.ORIGINAL) == 0) {
				syncObject.parameters.put("swarmClients", ((SyncSummaryPage) pages[3]).swarmClients);
				tasks.add(JobFactory.getInstance().createSyncJob(this, syncObject, null, progress));
				workToDo += 10;
			}
		}
	}
}
