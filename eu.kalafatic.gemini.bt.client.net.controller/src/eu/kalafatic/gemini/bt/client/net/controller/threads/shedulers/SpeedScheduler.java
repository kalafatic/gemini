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
package eu.kalafatic.gemini.bt.client.net.controller.threads.shedulers;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FNetConstants.SPEED_DELAY;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.LOGS;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.KB;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.MB;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SpeedContainer;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.hack.StatusLineContributionItem;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class SpeedScheduler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SpeedScheduler {

	/** The lock. */
	private final Lock lock = new ReentrantLock(true);

	/** The timer. */
	private Timer timer;

	/** The all down speed. */
	private float allUpSpeed, allDownSpeed;

	/** The task. */
	private ComputeSpeedTask task;

	/** The non active swarms counter. */
	private int nonActiveSwarmsCounter = 0;

	/** The Constant INSTANCE. */
	public static final SpeedScheduler INSTANCE = new SpeedScheduler();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Schedule.
	 */
	public void schedule() {
		timer = new Timer(false);
		task = new ComputeSpeedTask();
		timer.scheduleAtFixedRate(task, SPEED_DELAY * 1000, SPEED_DELAY * 1000);
		// Log.log(EBTClientPreferences.MODULE, "SHEDULER-SPEED : SHEDULE");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class ComputeSpeedTask.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class ComputeSpeedTask extends TimerTask {

		/*
		 * (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			try {
				// Log.log(EBTClientPreferences.MODULE,
				// "SHEDULER-SPEED : WAKE UP");

				allDownSpeed = 0;
				allUpSpeed = 0;

				updateSpeed();
				updateNonActiveSwarms();

				checkLogSize();
				Display.getDefault().asyncExec(worker);

			} catch (Exception e) {
				Log.log(EBTClientPreferences.MODULE, e);
			}
		}
	}

	// ---------------------------------------------------------------

	/** The worker. */
	private final Runnable worker = new Runnable() {
		@Override
		public void run() {
			if (lock.tryLock()) {
				try {
					StatusLineContributionItem speedDownItem = AppData.getInstance().getSpeedDownItem();

					if (speedDownItem.getLock().tryLock()) {
						try {
							AppData.getInstance().setAllDownSpeed(allDownSpeed);
							AppData.getInstance().setAllUpSpeed(allUpSpeed);

							AppData.getInstance().getSpeedDownItem().setText(resolveSpeed(allDownSpeed));
							AppData.getInstance().getSpeedUpItem().setText(resolveSpeed(allUpSpeed));

							// AppDataUtil.getInstance().getStatusLineManager()
							// .markDirty();
						} finally {
							speedDownItem.getLock().unlock();
						}
					}
				} catch (Exception e) {
					Log.log(EBTClientPreferences.MODULE, e);
				} finally {
					lock.unlock();
				}
			}
		}
	};

	// ---------------------------------------------------------------

	/**
	 * Update speed.
	 * @throws Exception the exception
	 */
	public void updateSpeed() throws Exception {
		EMap<String, Session> swarmMap = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap();

		for (Session session : swarmMap.values()) {
			SpeedContainer speedContainer = ((SwarmSession) session).getSpeedContainer();
			// TODO something wrong in removing objects from model (move,
			// remove)
			if (speedContainer == null) {
				continue;
			}

			try {
				long diff = (System.currentTimeMillis() - speedContainer.getStartTime()) / 1000;
				Assert.isTrue(diff > 0);

				setUpSpeed(session.getAnnounce(), speedContainer, diff);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up speed.
	 * @param key the key
	 * @param speedContainer the speed container
	 * @param diff the diff
	 * @throws Exception the exception
	 */
	private void setUpSpeed(String key, SpeedContainer speedContainer, long diff) throws Exception {
		AdditionalInfo additionalInfo = NetworkModelManager.getInstance().getDwnldUpldExtTorrent(key).getAdditionalInfo();

		Assert.isNotNull(additionalInfo);

		if (speedContainer.getDwnldSize() > 0) {
			long speed = speedContainer.getDwnldSize() / diff;
			additionalInfo.setDwnldSpeed(speed);
			allDownSpeed += speed;
		} else {
			additionalInfo.setDwnldSpeed(0);
		}
		if (speedContainer.getUpldSize() > 0) {
			long speed = speedContainer.getUpldSize() / diff;
			additionalInfo.setUpldSpeed(speed);
			allUpSpeed += speed;
		} else {
			additionalInfo.setUpldSpeed(0);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Update non active swarms.
	 */
	public synchronized void updateNonActiveSwarms() {

		if ((nonActiveSwarmsCounter++) < 10) {
			return;
		}
		nonActiveSwarmsCounter = 0;

		Log.log(EBTClientPreferences.MODULE, "SHEDULER-SPEED : UPDATING NON ACTIVE SWARMS");

		EMap<String, Session> swarmMap = NetworkModelManager.getInstance().getClientNetwork().getSwarmMap();

		EList<String> activeSwarms = NetworkModelManager.getInstance().getClientNetwork().getActiveSwarms();

		for (Entry<String, Session> entry : swarmMap) {
			SwarmSession session = (SwarmSession) entry.getValue();

			if (!activeSwarms.contains(session.getAnnounce())) {
				ExtTorrent extTorrent = NetworkModelManager.getInstance().getTorrents().getTorrentMap().get(session.getAnnounce());
				// finished
				if (extTorrent == null) {
					continue;
				}
				extTorrent.getAdditionalInfo().setDwnldSpeed(0);
				extTorrent.getAdditionalInfo().setUpldSpeed(0);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Check log size.
	 */
	private void checkLogSize() {
		while (LOGS.size() > 100) {
			LOGS.remove(LOGS.size() - 1);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve speed.
	 * @param size the size
	 * @return the string
	 */
	private String resolveSpeed(float size) {
		float number = 0;

		if (size > MB) {
			number = size / MB;
			return formatNumber(number) + " MB/s";
		} else if (size > KB) {
			number = size / KB;
			return formatNumber(number) + " kB/s";
		}
		return formatNumber(size) + "  B/s";
	}

	// ---------------------------------------------------------------

	/**
	 * Format number.
	 * @param number the number
	 * @return the string
	 */
	public String formatNumber(float number) {
		NumberFormat form = new DecimalFormat("#,###,###,##0.00");
		return form.format(number);
	}

	// ---------------------------------------------------------------

	/**
	 * Stop.
	 */
	public void stop() {
		if (timer != null) {
			timer.cancel();
		}
	}
}
