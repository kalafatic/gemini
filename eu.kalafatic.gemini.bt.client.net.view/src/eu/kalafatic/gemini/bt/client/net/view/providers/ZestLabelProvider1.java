///*******************************************************************************
// * Copyright (c) 2010, Petr Kalafatic (gemini@kalafatic.eu).
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the GNU GPL Version 3 
// * which accompanies this distribution, and is available at
// * http://www.gnu.org/licenses/gpl.txt  
// * 
// * Contributors:
// *     Petr Kalafatic - initial API and implementation
// ******************************************************************************/
//package eu.kalafatic.gemini.bt.client.net.view.providers;
//
//import org.eclipse.draw2d.IFigure;
//import org.eclipse.draw2d.Label;
//import org.eclipse.draw2d.geometry.Dimension;
//import org.eclipse.jface.viewers.IFontProvider;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.Font;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.zest.core.viewers.EntityConnectionData;
//import org.eclipse.zest.core.viewers.IEntityConnectionStyleProvider;
//import org.eclipse.zest.core.viewers.IEntityStyleProvider;
//import org.eclipse.zest.core.viewers.ISelfStyleProvider;
//import org.eclipse.zest.core.widgets.GraphConnection;
//import org.eclipse.zest.core.widgets.GraphNode;
//import org.eclipse.zest.core.widgets.ZestStyles;
//
//import eu.kalafatic.gemini.bt.client.net.controller.model.Node;
//import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.Session;
//import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
//import eu.kalafatic.gemini.core.lib.constants.IImageConstants;
//import eu.kalafatic.gemini.core.lib.constants.IUIConstants;
//
///**
// * The Class ZestLabelProvider.
// * 
// * @author Petr Kalafatic (petr@kalafatic.eu)
// * @since 2010
// * @project Gemini v 2.0.2
// */
//public class ZestLabelProvider1 extends LabelProvider implements
//		/*IFigureProvider,*/ IEntityStyleProvider, IFontProvider,
//		ISelfStyleProvider, IEntityConnectionStyleProvider, IUIConstants,
//		IImageConstants {
//
//	// ---------------------------------------------------------------
//	// ---------------------------------------------------------------
//
//	// @Override
//	// protected void fireLabelProviderChanged(LabelProviderChangedEvent event)
//	// {
//	// // 
//	// super.fireLabelProviderChanged(event);
//	// }
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
//	 */
//	@Override
//	public String getText(Object element) {
//		if (element instanceof Node) {
//			Node node = (Node) element;
//			// int length = node.getAnnounce().length();
//			// return node.getAnnounce().substring(0, length>10?10:length);
//			return node.getAnnounce();
//
//		} else if (element instanceof EntityConnectionData) {
//			EntityConnectionData entityConnection = (EntityConnectionData) element;
//
//			Node node1 = (Node) entityConnection.source;
//
//			Node node2 = (Node) entityConnection.dest;
//
//			// int length = node2.getAnnounce().length();
//			// return node2.getAnnounce().substring(0, length>5?5:length);
//			return node2.getAnnounce();
//
//		} else if (element instanceof Object[]) {
//			Node[] node = (Node[]) element;
//		}
//		return element.toString();
//	}
//
//	// ---------------------------------------------------------------
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
//	 */
//	@Override
//	public Image getImage(Object element) {
//		if (element instanceof Node) {
//			Node node = (Node) element;
//			return SERVER_IMG;
//		} else if (element instanceof EntityConnectionData) {
//			EntityConnectionData map = (EntityConnectionData) element;
//			System.err.println();
//			Node node1 = (Node) map.source;
//			Node node2 = (Node) map.dest;
//			// return GEMINI_IMG;
//			return null;
//		}
//		throw new RuntimeException("Type not supported");
//	}
//
//	// ---------------------------------------------------------------
//
////	@Override
////	public IFigure getFigure(Object element) {
////		IFigure result = null;
////		if (element instanceof Node) {
////			Node node = (Node) element;
////			result = new Label("I", SERVER_IMG);
////		} else if (element instanceof EntityConnectionData) {
////			EntityConnectionData map = (EntityConnectionData) element;
////			System.err.println();
////			Node node1 = (Node) map.source;
////			Node node2 = (Node) map.dest;
////			result = new Label("I", GEMINI_IMG);
////		}
////		result.setSize(-1, -1);
////		return result;
////	}
//
//	@Override
//	public Color getNodeHighlightColor(Object entity) {
//		return ORANGE_COLOR;
//	}
//
//	@Override
//	public Color getBorderColor(Object entity) {
//		return SAND_COLOR;
//	}
//
//	@Override
//	public Color getBorderHighlightColor(Object entity) {
//		return DARK_GRAY;
//	}
//
//	@Override
//	public int getBorderWidth(Object entity) {
//		return 1;
//	}
//
//	@Override
//	public Color getBackgroundColour(Object entity) {
//		return null;
//	}
//
//	@Override
//	public Color getForegroundColour(Object entity) {
//		return SAND_COLOR;
//	}
//
//	@Override
//	public IFigure getTooltip(Object entity) {
//		return new Label("I", SERVER_IMG);
//	}
//
//	@Override
//	public boolean fisheyeNode(Object entity) {
//		if (entity instanceof Node) {
//			Node node = (Node) entity;
//			Session session = node.getSession();
//
//			if (session == null) {
//				return true;
//			} else if (session instanceof SwarmSession) {
//				return false;
//
//			}
//
//			// Assert.isNotNull(session);
//		}
//		return true;
//	}
//
//	@Override
//	public Font getFont(Object element) {
//		// 
//		return null;
//	}
//
//	@Override
//	public void selfStyleConnection(Object element, GraphConnection connection) {
//		IFigure tooltip = new Label("Warning to Error", TORRENT_IMG);
//		connection.setTooltip(tooltip);
//
//		connection.changeLineColor(connection.getDisplay().getSystemColor(
//				SWT.COLOR_GREEN));
//		// Also set a text
//		// graphConnection.setText("This is a text");
//		connection.setHighlightColor(connection.getDisplay().getSystemColor(
//				SWT.COLOR_RED));
//		connection.setLineWidth(1);
//		connection.addListener(SWT.SELECTED, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				System.out.println("Selected");
//			}
//		});
//	}
//
//	@Override
//	public void selfStyleNode(Object element, GraphNode node) {
//		node.setNodeStyle(ZestStyles.NODES_CACHE_LABEL
//					 | ZestStyles.NODES_FISHEYE);
////		node.setNodeStyle(SWT.NONE);
////		node.setSize(20,20);
////		node.setVisible(false);
////		node.getNodeFigure().setSize(100,100);
//		node.getNodeFigure().setPreferredSize(new Dimension(50, 50));
//		node.getNodeFigure().revalidate();
//		
//	}
//
//	@Override
//	public int getConnectionStyle(Object src, Object dest) {
//		// 
//		return 0;
//	}
//
//	@Override
//	public Color getColor(Object src, Object dest) {
//		// 	
//		return GREEN;
//	}
//
//	@Override
//	public Color getHighlightColor(Object src, Object dest) {
//		// 
//		return SAND_COLOR;
//	}
//
//	@Override
//	public int getLineWidth(Object src, Object dest) {
//		// 
//		return 1;
//	}
// }
