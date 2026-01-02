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
package eu.kalafatic.gemini.bt.client.net.controller.adapters;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CONN_ERR_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CONN_OK_IMG;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TrayItem;

import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers.WatchDogSheduler;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.ERemote;

/**
 * The Class class ConnectionAdapter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ConnectionAdapter extends EContentAdapter implements Adapter {

	/** The tray item. */
	private TrayItem trayItem = AppData.getInstance().getTrayItem();

	/** The instance. */
	private volatile static ConnectionAdapter INSTANCE;

	/**
	 * Gets the single instance of ConnectionAdapter.
	 * @return single instance of ConnectionAdapter
	 */
	public static ConnectionAdapter getInstance() {
		if (INSTANCE == null) {
			synchronized (ConnectionAdapter.class) {
				INSTANCE = new ConnectionAdapter();
			}
		}
		return INSTANCE;
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

		if (notification.getFeature() instanceof EAttributeImpl) {
			EAttributeImpl atribute = (EAttributeImpl) notification.getFeature();

			if (atribute.getName().equals("connected")) {

				trayItem = AppData.getInstance().getTrayItem();

				if (notification.getNewBooleanValue()) {
					connected();
				} else {
					disconnected();
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Disconnected.
	 */
	private void disconnected() {
		NetworkModelManager.getInstance().getClientNetwork().getSemaphor().setConnected(false);

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				trayItem.setImage(CONN_ERR_IMG);
				trayItem.setToolTipText("Connection - Error");

				String value = PREFERENCES.get(ECorePreferences.PING_ENABLED.getName(), (String) ECorePreferences.PING_ENABLED.getDef());

				int delay = PREFERENCES.getInt(ECorePreferences.WATCHDOG_DELAY.getName(), (Integer) ECorePreferences.WATCHDOG_DELAY.getDef());

				if (value.equals(ERemote.AUTO.literal)) {
					WatchDogSheduler.getInstance(ERemote.AUTO, delay).schedule();
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Connected.
	 */
	private void connected() {
		NetworkModelManager.getInstance().getClientNetwork().getSemaphor().setConnected(true);

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				trayItem.setImage(CONN_OK_IMG);
				trayItem.setToolTipText("Connection - OK");
			}
		});
	}
}
