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

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.tracker.controller.factories.JobFactory;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSelectPage;
import eu.kalafatic.gemini.bt.tracker.controller.wizards.pages.SyncSummaryPage;
import eu.kalafatic.gemini.core.interfaces.ESyncType;
import eu.kalafatic.gemini.core.models.SyncObject;

/**
 * The Class class ExportSyncWizard.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ExportSyncWizard extends ATrackerSyncWizard {

	/**
	 * Instantiates a new export sync wizard.
	 */
	public ExportSyncWizard() {
		super(true);
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

		Progress progress = new Progress(worked, done);

		Collection<Table> values = destClones.values();
		for (Table table : values) {

			ESyncType toType = getSyncType(table);

			TableItem[] items = table.getItems();

			for (TableItem item : items) {
				SyncObject syncObject = (SyncObject) item.getData();

				if (toType.equals(ESyncType.DB)) {
					syncObject.to.put(ESyncType.DB, ((SyncSelectPage) pages[2]).dbList);

					tasks.add(JobFactory.getInstance().createSyncJob(this, syncObject, toType, progress));
					workToDo += 10;
				}
			}
		}
	}
}
