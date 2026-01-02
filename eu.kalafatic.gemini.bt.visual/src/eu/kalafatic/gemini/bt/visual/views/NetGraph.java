/**
 * 
 */
package eu.kalafatic.gemini.bt.visual.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.EntityConnectionData;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
import org.eclipse.zest.core.viewers.internal.ZoomManager;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

import eu.kalafatic.gemini.bt.visual.hack.GraphViewer;

/**
 * @author kalafaticp
 * 
 */
public class NetGraph extends ViewPart implements IZoomableWorkbenchPart {

	public static final String ID = "eu.kalafatic.gemini.bt.visual.views.NetGraph";

	private GraphViewer viewer;

	private int zoom = 0;
	private static final double ZOOM_AMOUNT = 1.1;

	private ZoomContributionViewItem zoomItem;

	/**
	 * 
	 */
	public NetGraph() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {

		viewer = new GraphViewer(parent, SWT.BORDER);
		viewer.setContentProvider(new ZestNodeContentProvider());
		viewer.setLabelProvider(new ZestLabelProvider());

		viewer.setNodeStyle(PROP_TITLE);

		Listener listener = new Listener() {
			int zoomFactor = 50;

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MouseWheel:
					zoomFactor = Math.max(0, zoomFactor + event.count);
					sizeToZoom(zoomFactor);
					break;
				case SWT.Paint:
					event.gc.drawText("Zoom = " + zoomFactor, 10, 10);
					break;
				}
			}
		};

		viewer.getControl().addListener(SWT.MouseWheel, listener);
		viewer.getControl().addListener(SWT.Paint, listener);

		NodeModelContentProvider model = new NodeModelContentProvider();

		viewer.setInput(model.getNodes());

		LayoutAlgorithm layout = setLayout();
		viewer.setLayoutAlgorithm(layout, true);
		viewer.applyLayout();

		fillToolBar();
	}

	private void sizeToZoom(int zoom) {
		double factor = Math.pow(ZOOM_AMOUNT, zoom);
		zoomItem.zoomChanged(factor);

		ZoomManager zoomManager = new ZoomManager(viewer.getGraphControl()
				.getRootLayer(), viewer.getGraphControl().getViewport());
		zoomManager.setZoom(factor);

		System.err.println(zoom);

	}

	private LayoutAlgorithm setLayout() {
		LayoutAlgorithm layout;
		// layout = new
		// SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		// layout = new
		// HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		layout = new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		return layout;

	}

	class ZestNodeContentProvider extends ArrayContentProvider implements
			IGraphEntityContentProvider {

		@Override
		public Object[] getConnectedTo(Object entity) {
			if (entity instanceof MyNode) {
				MyNode node = (MyNode) entity;
				return node.getConnectedTo().toArray();
			}
			throw new RuntimeException("Type not supported");
		}
	}

	class ZestLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof MyNode) {
				MyNode myNode = (MyNode) element;
				return myNode.getName();
			}
			if (element instanceof MyConnection) {
				MyConnection myConnection = (MyConnection) element;
				return myConnection.getLabel();
			}

			if (element instanceof EntityConnectionData) {
				return "";
			}
			throw new RuntimeException("Wrong type: "
					+ element.getClass().toString());
		}
	}

	class MyNode {
		private final String id;
		private final String name;
		private List<MyNode> connections;

		public MyNode(String id, String name) {
			this.id = id;
			this.name = name;
			this.connections = new ArrayList<MyNode>();
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public List<MyNode> getConnectedTo() {
			return connections;
		}

	}

	class MyConnection {
		final String id;
		final String label;
		final MyNode source;
		final MyNode destination;

		public MyConnection(String id, String label, MyNode source,
				MyNode destination) {
			this.id = id;
			this.label = label;
			this.source = source;
			this.destination = destination;
		}

		public String getLabel() {
			return label;
		}

		public MyNode getSource() {
			return source;
		}

		public MyNode getDestination() {
			return destination;
		}

	}

	class NodeModelContentProvider {
		private List<MyConnection> connections;
		private List<MyNode> nodes;

		public NodeModelContentProvider() {
			// Image here a fancy DB access
			// Now create a few nodes
			nodes = new ArrayList<MyNode>();
			MyNode node = new MyNode("1", "Hamburg");
			nodes.add(node);
			node = new MyNode("2", "Frankfurt");
			nodes.add(node);
			node = new MyNode("3", "Berlin");
			nodes.add(node);
			node = new MyNode("4", "Munich");
			nodes.add(node);
			node = new MyNode("5", "Eppelheim");
			nodes.add(node);
			node = new MyNode("6", "Ahrensboek");
			nodes.add(node);

			connections = new ArrayList<MyConnection>();
			MyConnection connect = new MyConnection("1", "1", nodes.get(0),
					nodes.get(1));
			connections.add(connect);
			connect = new MyConnection("2", "2", nodes.get(0), nodes.get(1));
			connections.add(connect);
			connect = new MyConnection("3", "3", nodes.get(0), nodes.get(2));
			connections.add(connect);
			connect = new MyConnection("4", "3", nodes.get(0), nodes.get(3));
			connections.add(connect);
			connect = new MyConnection("4", "3", nodes.get(0), nodes.get(4));
			connections.add(connect);
			connect = new MyConnection("4", "3", nodes.get(1), nodes.get(5));
			connections.add(connect);

			// Because we are lasy we save the info about the connections in the
			// nodes

			for (MyConnection connection : connections) {
				connection.getSource().getConnectedTo().add(
						connection.getDestination());
			}
		}

		public List<MyNode> getNodes() {
			return nodes;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void fillToolBar() {
		zoomItem = new ZoomContributionViewItem(this);
		IActionBars bars = getViewSite().getActionBars();
		bars.getMenuManager().add(zoomItem);
	}

	@Override
	public org.eclipse.zest.core.viewers.AbstractZoomableViewer getZoomableViewer() {
		return viewer;
	}

}
