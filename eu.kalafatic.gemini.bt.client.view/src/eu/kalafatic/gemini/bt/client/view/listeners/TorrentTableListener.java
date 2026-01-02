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
package eu.kalafatic.gemini.bt.client.view.listeners;

import static eu.kalafatic.gemini.bt.client.net.controller.lib.constants.FHandlerConstants.TRANSPORT_BLOCK_SIZE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.APP_SIZE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.DARK_GREEN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.GRADIENT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.INDEXES_FONT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LIGHT_GREEN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.WHITE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ETorrentTableColumns;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.IOPiece;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.view.hack.DefaultInformationControl;
import eu.kalafatic.gemini.bt.client.view.hack.HTMLTextPresenter;

/**
 * The listener interface for receiving torrentTable events. The class that is interested in processing a torrentTable event implements this
 * interface, and the object created with that class is registered with a component using the component's
 * <code>addTorrentTableListener<code> method. When
 * the torrentTable event occurs, that object's appropriate
 * method is invoked.
 * @see TorrentTableEvent
 */
public class TorrentTableListener implements Listener {

	/** The table. */
	private Table table;

	/** The shell. */
	private Shell shell;

	/** The max upld view delay. */
	private final long MAX_UPLD_VIEW_DELAY = 30 * 1000;

	/** The progress tooltip. */
	public static DefaultInformationControl PROGRESS_TOOLTIP;

	/** The current content. */
	public static String CURRENT_CONTENT = "<b>Hello</b> <i>World</i>";

	/** The name. */
	static String NAME = "<b>%s</b><br>";

	/** The dl. */
	static String DL = "<br><i>Downloading Piece : %s</i><br>";

	/** The ul. */
	static String UL = "<br><i>Uploading Pieces : </i>";

	/**
	 * Instantiates a new torrent table listener.
	 * @param table the table
	 */
	public TorrentTableListener(Table table) {
		this.table = table;

		shell = new Shell(table.getShell(), SWT.TOOL | SWT.ON_TOP | SWT.NO_TRIM | SWT.RESIZE);
		shell.setAlpha(140);
		shell.setLayout(new GridLayout());
		shell.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void handleEvent(final Event event) {
		switch (event.type) {
		case SWT.Dispose:
		case SWT.KeyDown:
		case SWT.MouseHover:
			showHooverControl(event, true);
			PROGRESS_TOOLTIP.fText.setBackground(LIGHT_GREEN);
			// shell.setBackground(LIGHT_GREEN);
			shell.setAlpha(140);
			break;
		case SWT.MouseMove:
			showHooverControl(event, false);
			break;
		case SWT.PaintItem:
			if (event.index == 9) {
				paintProgress(event);
				// event.detail &= ~SWT.BACKGROUND;
				event.detail &= SWT.HOT;
				event.doit = false;
			}
			break;

		}
	}

	// ---------------------------------------------------------------

	/**
	 * Paint progress.
	 * @param event the event
	 */
	private void paintProgress(Event event) {
		final TableItem item = (TableItem) event.item;

		ExtTorrent extTorrent = (ExtTorrent) ((Entry<?, ?>) item.getData()).getValue();

		int completed = extTorrent.getAdditionalInfo().getCompleted();

		Rectangle bounds = item.getBounds(ETorrentTableColumns.COMPLETED_COL_VALUE);
		float onePercentWidth = (bounds.width + 1) / 100;
		float width = onePercentWidth * (completed >= 100 ? 100 : completed);
		int round = Math.round(width);

		String text = Math.round(completed) + " %";

		final GC gc = event.gc;
		if (gc == null) {
			return;
		}
		gc.setForeground(WHITE);
		gc.setBackground(DARK_GREEN);

		gc.fillGradientRectangle(event.x, event.y + 2, round, event.height - 6, true);

		Point size = event.gc.textExtent(text);
		int offset = Math.max(0, (event.height - size.y) / 2);

		gc.setForeground(BLACK);
		gc.setFont(INDEXES_FONT);
		gc.drawText(text, event.x + 5, event.y + offset, true);

		gc.dispose();
	}

	// ---------------------------------------------------------------

	/**
	 * Show hoover control.
	 * @param event the event
	 * @param visible the visible
	 */
	private void showHooverControl(Event event, boolean visible) {
		if (PROGRESS_TOOLTIP == null) {
			createHooverControl();
		}
		if (visible) {
			updateHooverControl(event);
		}
		PROGRESS_TOOLTIP.setVisible(visible);
	}

	// ---------------------------------------------------------------

	/**
	 * Update hoover control.
	 * @param event the event
	 */
	private void updateHooverControl(Event event) {
		TableItem item = table.getItem(new Point(event.x, event.y));

		if ((item != null) && item.getData() != null && item.getData() instanceof Entry<?, ?>) {
			Entry<?, ?> entry = (Entry<?, ?>) item.getData();

			if (entry.getValue() instanceof ExtTorrent) {
				ExtTorrent extTorrent = (ExtTorrent) entry.getValue();
				SwarmSession session = (SwarmSession) NetworkModelManager.getInstance().getClientNetwork().getSwarmMap().get(entry.getKey());

				if (session != null) {
					int processed = session.getProcessedPieces().size();

					Point cursorLocation = Display.getCurrent().getCursorLocation();
					Point newLocation = new Point(cursorLocation.x + 10, cursorLocation.y + 10);
					PROGRESS_TOOLTIP.setLocation(newLocation);

					CURRENT_CONTENT = getSessionContent(session);

					if (CURRENT_CONTENT.isEmpty() || session.getProcessedPieces().isEmpty() && session.getUploadedPieces().isEmpty()) {

						PROGRESS_TOOLTIP.setVisible(false);
					} else {
						int blocks = extTorrent.getInfo().getPieceLength() / TRANSPORT_BLOCK_SIZE;
						int blocksWidth = (int) (blocks * 3.2);
						int textWidth = (int) (PROGRESS_TOOLTIP.fText.getText().length() * 3.2);

						Point contentSize = PROGRESS_TOOLTIP.fText.computeSize((blocksWidth > textWidth) ? blocksWidth : textWidth, (int) (processed * 45.4) + 50);

						PROGRESS_TOOLTIP.setSize(contentSize.x, contentSize.y);
						PROGRESS_TOOLTIP.setInformation(CURRENT_CONTENT);
						// PROGRESS_TOOLTIP.setVisible(true);
					}
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the hoover control.
	 */
	private void createHooverControl() {
		PROGRESS_TOOLTIP = new DefaultInformationControl(shell, "Press F2 to focus", new HTMLTextPresenter(false));

		PROGRESS_TOOLTIP.setSize((APP_SIZE.x / 2) - 50, (APP_SIZE.y / 4));

		shell.getDisplay().addFilter(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.keyCode == SWT.F2) {
					showHooverControl(event, true);
					shell.setAlpha(250);
					PROGRESS_TOOLTIP.fText.setBackground(GRADIENT);
					// shell.setBackground(GRADIENT);
					shell.pack(true);
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the session content.
	 * @param swarmSession the swarm session
	 * @return the session content
	 */
	private String getSessionContent(SwarmSession swarmSession) {
		StringBuffer sb = new StringBuffer();

		sb.append(String.format(NAME, swarmSession.getAnnounce()));

		for (IOPiece piece : swarmSession.getProcessedPieces().values()) {
			sb.append(String.format(DL, piece.getPieceIndex()));

			for (int i = 0; i < piece.getBlocks().length; i++) {
				// not finished
				if (piece.getBlocks()[i]) {
					sb.append("|");
					// locked
				} else if (piece.getLockedBlocks()[i]) {
					sb.append("'");
				} else {
					sb.append(".");
				}
			}
			sb.append("<br>");
		}
		EList<IOPiece> uploadedPieces = swarmSession.getUploadedPieces();
		List<IOPiece> copyOfUploadedPieces = new ArrayList<IOPiece>(uploadedPieces);

		if (!copyOfUploadedPieces.isEmpty()) {
			sb.append(UL);

			for (IOPiece piece : copyOfUploadedPieces) {

				if ((piece.getLastActivity() + MAX_UPLD_VIEW_DELAY) > System.currentTimeMillis()) {
					sb.append(Integer.toString(piece.getPieceIndex()) + ",");
				} else {
					synchronized (uploadedPieces) {
						uploadedPieces.remove(piece);
					}
				}
			}
			sb.append("<br>");
		}
		return sb.toString();
	}
}
