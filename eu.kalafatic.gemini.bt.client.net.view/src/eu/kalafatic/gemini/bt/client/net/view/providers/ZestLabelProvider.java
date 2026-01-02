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
package eu.kalafatic.gemini.bt.client.net.view.providers;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ARROW_DOWN_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ARROW_UP_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.GEMINI_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.INFO_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.SERVER_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TORRENT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLUE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.CYAN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.DARK_GREEN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.GRADIENT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.GRASS_COLOR;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.GREEN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.INDEXES_FONT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.ORANGE_COLOR;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.SAND_COLOR;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.WHITE;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.UpdateListener;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.zest.core.viewers.EntityConnectionData;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider;
import org.eclipse.zest.core.viewers.ISelfStyleProvider;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutEntity;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.ClientSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.DwnldSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.TrackerSession;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.UpldSession;
import eu.kalafatic.gemini.bt.utils.utils.BTUtils;

/**
 * The Class class ZestLabelProvider.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings("rawtypes")
public class ZestLabelProvider extends LabelProvider implements IFontProvider, ISelfStyleProvider, IEntityConnectionStyleProvider {

	/** The viewer. */
	@SuppressWarnings("unused")
	private final GraphViewer viewer;

	/** The graph. */
	private final Graph graph;

	/**
	 * Instantiates a new zest label provider.
	 * @param viewer the viewer
	 */
	public ZestLabelProvider(GraphViewer viewer) {
		this.viewer = viewer;

		graph = viewer.getGraphControl();

		// graph.getViewport().getUpdateManager()
		// .addUpdateListener(new UpdateListener() {
		//
		// @Override
		// public void notifyPainting(Rectangle location,
		// Map dirtyRegions) {
		// System.err.println("notifyPainting");
		//
		// if (dirtyRegions.values().contains(location)) {
		// return;
		// }
		//
		// Collection values = dirtyRegions.values();
		//
		// for (Object object : values) {
		//
		// final Rectangle dirty =
		// (org.eclipse.draw2d.geometry.Rectangle) object;
		//
		// IFigure figureAt = graph.getFigureAt(dirty.x,
		// dirty.y);
		//
		// if (figureAt instanceof GraphLabel) {
		// final GraphLabel label = (GraphLabel) figureAt;
		//
		// label.setText("sgfd");
		//
		// GC gc = new GC(graph);
		// gc.setBackground(SAND_COLOR);
		// gc.setForeground(CYAN);
		// gc.drawRectangle(location.x, location.y,
		// location.width, 10);
		//
		// gc.setBackground(BLUE);
		// gc.fillGradientRectangle(dirty.x, dirty.y - 50,
		// 50, 10, true);
		//
		// gc.dispose();
		// System.err.println();
		// }
		// }
		// }
		//
		// @Override
		// public void notifyValidating() {
		// }
		// });
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof Map<?, ?>) {
			Map<?, ?> map = (Map<?, ?>) element;
			return "Swarms".concat(" (" + map.size() + ")");
		} else if (element instanceof Session) {
			Session session = (Session) element;
			return session.getAnnounce();
		} else if (element instanceof String) {
			return (String) element;
		}
		return "";
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof EntityConnectionData) {
			// EntityConnectionData entityConnectionData =
			// (EntityConnectionData) element;
			// return INFO_IMG;
			return null;
		}
		if (element instanceof HashMap<?, ?>) {
			return SERVER_IMG;

		} else if (element instanceof EcoreEMap<?, ?>) {
			return GEMINI_IMG;
		} else if (element instanceof SwarmSession) {
			return TORRENT_IMG;
		} else if (element instanceof TrackerSession) {
			return SERVER_IMG;
		} else if (element instanceof DwnldSession) {
			return ARROW_DOWN_IMG;
		} else if (element instanceof UpldSession) {
			return ARROW_UP_IMG;

		} else if (element instanceof String) {
			return INFO_IMG;
		}
		return FILE_IMG;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.ISelfStyleProvider#selfStyleConnection( java.lang.Object, org.eclipse.zest.core.widgets.GraphConnection)
	 */
	@Override
	public void selfStyleConnection(Object element, final GraphConnection connection) {

		if (element instanceof EntityConnectionData) {
			EntityConnectionData entityConnectionData = (EntityConnectionData) element;

			if (entityConnectionData.dest instanceof Session) {
				Session session = (Session) entityConnectionData.dest;

				if (session instanceof SwarmSession) {
					connection.setLineColor(ORANGE_COLOR);
					connection.setLineWidth(3);

				} else if (session instanceof TrackerSession) {
					if (entityConnectionData.source instanceof SwarmSession) {
						SwarmSession ss = (SwarmSession) entityConnectionData.source;
						if (ss.isObtainingClients()) {
							connection.setLineWidth(2);
						} else {
							connection.setLineWidth(1);
						}
					}
					connection.setLineColor(SAND_COLOR);

				} else if (session instanceof DwnldSession) {
					if (entityConnectionData.source instanceof SwarmSession) {
						switch (session.getState()) {
						case READY_TO_DOWNLOAD:
							connection.setLineWidth(2);
							break;
						default:
							connection.setLineWidth(1);
						}
						connection.setLineColor(GREEN);
					}
				} else if (session instanceof UpldSession) {
					if (entityConnectionData.source instanceof SwarmSession) {
						switch (session.getState()) {
						case READY_TO_UPLOAD:
							connection.setLineWidth(2);
							break;
						default:
							connection.setLineWidth(1);
						}
						connection.setLineColor(BLUE);
					}
				}
			} else if (entityConnectionData.dest instanceof String) {
				connection.setLineColor(CYAN);
			}
		}
		connection.addListener(SWT.SELECTED, new Listener() {
			@Override
			public void handleEvent(Event event) {
				connection.setLineWidth(3);
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.ISelfStyleProvider#selfStyleNode(java.lang .Object, org.eclipse.zest.core.widgets.GraphNode)
	 */
	@Override
	public void selfStyleNode(final Object element, final GraphNode graphNode) {
		final LayoutEntity layoutEntity = graphNode.getLayoutEntity();

		Point size = new Point(30, 16);

		int style = ZestStyles.NODES_NO_LAYOUT_RESIZE | ZestStyles.NODES_NO_LAYOUT_ANIMATION | ZestStyles.NODES_CACHE_LABEL | ZestStyles.NODES_NO_ANIMATION;

		if (element instanceof HashMap<?, ?>) {
			size = new Point(60, 30);
			graphNode.setBackgroundColor(SAND_COLOR);
			graphNode.setNodeStyle(style);

		} else if (element instanceof EcoreEMap<?, ?>) {
			size = new Point(25, 25);
			graphNode.setBackgroundColor(ORANGE_COLOR);

			graphNode.setNodeStyle(style);

		} else if (element instanceof SwarmSession) {
			size = new Point(100, 20);
			graphNode.setBackgroundColor(GREEN);

			graphNode.setNodeStyle(style | ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE);

		} else if (element instanceof TrackerSession) {
			size = new Point(60, 20);
			graphNode.setBackgroundColor(SAND_COLOR);

			graphNode.setNodeStyle(style | ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE);

		} else if (element instanceof DwnldSession) {
			graphNode.setBackgroundColor(GRASS_COLOR);
			graphNode.setNodeStyle(style | ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE);

		} else if (element instanceof UpldSession) {
			graphNode.setBackgroundColor(GRADIENT);
			graphNode.setNodeStyle(style | ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE);

		} else {
			size = new Point(25, 10);
			graphNode.setBackgroundColor(WHITE);
			graphNode.setNodeStyle(style | ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE);
		}

		layoutEntity.setSizeInLayout(size.x, size.y);

		final Point size1 = size;

		IFigure figure = graphNode.getNodeFigure().getParent();

		figure.getUpdateManager().addUpdateListener(new UpdateListener() {
			// long lastUpdate = System.currentTimeMillis();

			@SuppressWarnings("unused")
			@Override
			public void notifyValidating() {
				try {
					if (size1 == null) {
						layoutEntity.setSizeInLayout(15, 15);
					} else {
						layoutEntity.setSizeInLayout(size1.x, size1.y);
					}
					// IFigure tooltip = graphNode.getTooltip();
					// if (tooltip == null) {
					// // Image image1 = Display.getDefault().getSystemImage(
					// // SWT.ICON_INFORMATION);
					// tooltip = new Label("Information to Warning",
					// SERVER_IMG);
					// graphNode.setTooltip(tooltip);
					// System.err.println();
					// } else {
					// tooltip.setVisible(true);
					// }

					// if (element instanceof ClientSession) {
					// if ((System.currentTimeMillis() - 100) > lastUpdate) {
					// lastUpdate = System.currentTimeMillis();
					// paintBitfield(graphNode, (ClientSession) element);
					// }
					// }

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void notifyPainting(Rectangle location, Map dirtyRegions) {}
		});
	}

	/**
	 * Gets the tool tip.
	 * @param graphNode the graph node
	 * @param session the session
	 * @return the tool tip
	 */
	private IFigure getToolTip(GraphNode graphNode, ClientSession session) {
		IFigure figure = graphNode.getTooltip();

		return figure;
	}

	// ---------------------------------------------------------------

	/**
	 * Paint bitfield.
	 * @param graphNode the graph node
	 * @param session the session
	 */
	private void paintBitfield(GraphNode graphNode, ClientSession session) {
		if (session.getBitfield() == null) {
			return;
		}
		if (session.eContainer().eContainer() instanceof SwarmSession) {
			SwarmSession swarmSession = (SwarmSession) session.eContainer().eContainer();
			ExtTorrent extTorrent = (ExtTorrent) swarmSession.getTorrent();

			float contaiment = BTUtils.INSTANCE.getContaiment(session.getBitfield(), extTorrent.getModelBitfield().length);

			org.eclipse.draw2d.geometry.Point location = graphNode.getLocation();
			GC gc = new GC(graph);
			gc.setForeground(WHITE);
			gc.setBackground(DARK_GREEN);

			// org.eclipse.draw2d.geometry.Rectangle rect = new
			// Rectangle(location.x, location.y + 20, 100, 10);
			gc.drawRectangle(location.x, location.y - 20, 100, 10);

			gc.fillGradientRectangle(location.x, location.y - 20, (int) contaiment, 10, true);

			gc.setForeground(BLACK);
			gc.setFont(INDEXES_FONT);
			gc.drawString("sss", location.x, location.y - 20);
			gc.dispose();
		}

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider# getConnectionStyle(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int getConnectionStyle(Object src, Object dest) {
		if (src instanceof EcoreEMap) {
			SwarmSession session = (SwarmSession) dest;
			EList<String> activeSwarms = NetworkModelManager.getInstance().getClientNetwork().getActiveSwarms();
			if (activeSwarms.contains(session.getAnnounce())) {
				return ZestStyles.CONNECTIONS_DASH;
			} else {
				return ZestStyles.CONNECTIONS_SOLID;
			}
		}
		if (dest instanceof TrackerSession) {
			if (src instanceof SwarmSession) {
				SwarmSession session = (SwarmSession) src;
				if (session.isObtainingClients()) {
					return ZestStyles.CONNECTIONS_DASH;
				} else {
					return ZestStyles.CONNECTIONS_SOLID;
				}
			}
		} else if (dest instanceof DwnldSession) {
			DwnldSession session = (DwnldSession) dest;
			switch (session.getState()) {
			case READY_TO_DOWNLOAD:
				return ZestStyles.CONNECTIONS_DASH;
			default:
				return ZestStyles.CONNECTIONS_SOLID;
			}
		} else if (dest instanceof UpldSession) {
			UpldSession session = (UpldSession) dest;
			switch (session.getState()) {
			case READY_TO_UPLOAD:
				return ZestStyles.CONNECTIONS_DASH
				/* | ZestStyles.CONNECTIONS_DIRECTED */;
			default:
				return ZestStyles.CONNECTIONS_SOLID;
			}
		} else if (dest instanceof String) {
			return ZestStyles.CONNECTIONS_DOT;
		}
		return 0;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider#getColor (java.lang.Object, java.lang.Object)
	 */
	@Override
	public Color getColor(Object src, Object dest) {
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider# getHighlightColor(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Color getHighlightColor(Object src, Object dest) {
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider#getLineWidth (java.lang.Object, java.lang.Object)
	 */
	@Override
	public int getLineWidth(Object src, Object dest) {
		return 1;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider#getTooltip (java.lang.Object)
	 */
	@Override
	public IFigure getTooltip(Object entity) {
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
	 */
	@Override
	public Font getFont(Object element) {
		return null;
	}
}
