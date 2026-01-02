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
package eu.kalafatic.gemini.bt.client.controller.lib.interfaces;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLUE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.DARK_GREEN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import eu.kalafatic.gemini.bt.client.model.torrents.AdditionalInfo;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EViewsMessages;
import eu.kalafatic.gemini.core.utils.ImageUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ATorrentTableLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public abstract class ATorrentTableLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider, ITableFontProvider {

	/** The cache. */
	protected final Map<ExtTorrent, SwarmSession> cache = new HashMap<ExtTorrent, SwarmSession>(10);

	/** The gif. */
	protected Image gif;

	/** The gif index. */
	private int gifIndex = 0;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 * @param viewer the viewer
	 * @param delay the delay
	 */
	public void init(final Viewer viewer, int delay) {
		final Image[] animated = ImageUtils.loadGif("icons/actions/busy.gif");

		viewer.getControl().getDisplay().timerExec(1000, new Runnable() {
			@Override
			public void run() {
				try {
					gifIndex = (gifIndex < animated.length) ? gifIndex : 0;

					gif = animated[gifIndex++];

					if (viewer != null && !viewer.getControl().isDisposed()) {
						Display.getDefault().timerExec(200, this);
						viewer.refresh();
					}
				} catch (Exception e) {
					Log.log(EBTClientPreferences.MODULE, e);
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.lang .Object, int)
	 */
	@Override
	public Color getForeground(Object element, int columnIndex) {
		if (element instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (BasicEMap.Entry<?, ?>) element;

			if (entry.getValue() == null) {
				return null;
			}
			ExtTorrent extTorrent = (ExtTorrent) entry.getValue();

			boolean enabled = extTorrent.isEnabled();

			switch (extTorrent.getStatus().getValue()) {
			case EViewsMessages.READY_VALUE:
				return enabled ? DARK_GREEN : BLACK;
			case EViewsMessages.PAUSED_VALUE:
				return enabled ? BLUE : RED;
			case EViewsMessages.STOPPED_VALUE:
				return enabled ? BLACK : RED;
			}
		}
		return DARK_GREEN;
	}

	// ---------------------------------------------------------------

	/**
	 * Resolve downloaders.
	 * @param additionalInfo the additional info
	 * @param swarmSession the swarm session
	 * @return the string
	 */
	protected String resolveDownloaders(AdditionalInfo additionalInfo, SwarmSession swarmSession) {

		String result;
		synchronized (additionalInfo) {
			result = Integer.toString(additionalInfo.getDownloaders());
		}

		synchronized (swarmSession) {
			result += " / " + Integer.toString(swarmSession.getUploads().size());
		}
		return result;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang .Object, int)
	 */
	@Override
	public Color getBackground(Object element, int columnIndex) {
		// if (columnIndex == ETorrentTableColumns.COMPLETED_COL_VALUE) {
		// if (refresh.compareAndSet(false, true)) {
		// return LIGHT_BLUE;
		// } else {
		// refresh.set(false);
		// return WHITE;
		// }
		// }
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableFontProvider#getFont(java.lang.Object, int)
	 */
	@Override
	public Font getFont(Object element, int columnIndex) {
		return null;
	}
}
