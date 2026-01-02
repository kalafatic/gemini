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
package eu.kalafatic.gemini.bt.tm.controller.adapters;

import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.bt.tm.model.btStructure.impl.StringToTrackerMapEntryImpl;

/**
 * The Class class TrackersTableContentAdapter.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class TrackersTableContentAdapter extends EContentAdapter implements Adapter {

	/** The trackers table. */
	private Table trackersTable;

	/** The bt structure. */
	private BTStructure btStructure = BTStructureModelManager.getInstance().getBtStructure();

	/**
	 * Instantiates a new trackers table content adapter.
	 * @param sourceTable the source table
	 */
	public TrackersTableContentAdapter(Table sourceTable) {
		this.trackersTable = sourceTable;
		btStructure.eAdapters().add(this);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse. emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {

		super.notifyChanged(notification);

		if (notification.getNotifier() instanceof Tracker) {
			Tracker tracker = (Tracker) notification.getNotifier();
			if (tracker.isChecked()) {
				if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
					removeTrackerFromTable(tracker);
				} else {
					addTrackerToTable(tracker);
				}
			} else {
				removeTrackerFromTable(tracker);
			}
		} else if (notification.getNotifier() instanceof BTStructure) {

			Object object = notification.getFeature();

			if (object instanceof EReferenceImpl) {
				EReferenceImpl feature = (EReferenceImpl) object;

				if (!feature.getName().equals("trackersMap")) {
					return;
				}

				switch (notification.getEventType()) {

				case Notification.ADD:
					addTrackerToTable(notification);
					break;

				case Notification.REMOVE:
					removeTrackerFromTable(notification);
					break;
				}
			} else if (object instanceof EAttribute) {

			}

		} else if (notification.getNotifier() instanceof Entry) {

			Entry<?, ?> entry = (Entry<?, ?>) notification.getNotifier();

			switch (notification.getEventType()) {

			case Notification.REMOVING_ADAPTER:

				TableItem[] items = trackersTable.getItems();

				for (int i = 0; i < items.length; i++) {
					String text = items[i].getText(0);

					if (text.equalsIgnoreCase((String) entry.getKey())) {
						trackersTable.remove(i);
						break;
					}
				}
				break;
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the tracker to table.
	 * @param tracker the tracker
	 */
	private void addTrackerToTable(Tracker tracker) {
		TableItem[] items = trackersTable.getItems();

		for (int i = 0; i < items.length; i++) {
			if (items[i].getText(0).equalsIgnoreCase(tracker.getAnnounce())) {
				return;
			}
		}
		TableItem item = new TableItem(trackersTable, SWT.NORMAL);
		item.setText(new String[] { tracker.getAnnounce() });
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the tracker from table.
	 * @param tracker the tracker
	 */
	private void removeTrackerFromTable(Tracker tracker) {
		TableItem[] items = trackersTable.getItems();
		for (int i = 0; i < items.length; i++) {

			if (items[i].getText(0).equalsIgnoreCase(tracker.getAnnounce())) {
				trackersTable.remove(i);
				break;
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the tracker to table.
	 * @param notification the notification
	 */
	private void addTrackerToTable(Notification notification) {

		if (notification.getNewValue() instanceof StringToTrackerMapEntryImpl) {
			StringToTrackerMapEntryImpl entry = (StringToTrackerMapEntryImpl) notification.getNewValue();

			TableItem item = new TableItem(trackersTable, SWT.NORMAL);

			item.setText(new String[] { entry.getValue().getAnnounce() });
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the tracker from table.
	 * @param notification the notification
	 */
	private void removeTrackerFromTable(Notification notification) {

		// if (notification.getNewValue() instanceof
		// StringToTrackerMapEntryImpl) {
		//
		// TableItem[] items = trackersTable.getItems();
		//
		// }
	}

}
