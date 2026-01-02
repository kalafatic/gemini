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
package eu.kalafatic.gemini.stat.view.views;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;

import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.stat.controller.model.StatisticsModelManager;
import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class StatisticsView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class StatisticsView extends AViewer {

	/** The parent. */
	Composite parent;

	/** The study. */
	private Study study;

	/** The graph. */
	private Graph graph;

	/** The chart. */
	private Chart chart;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;

		createContents();
		createToolbar();
	}

	// ---------------------------------------------------------------
	/**
	 * Creates the contents.
	 */
	private void createContents() {
		getStudy(null);
		getGraph(null);

		// parent.setLayout(new GridLayout(getCols(), true));
		// parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		// Composite composite0 = new Composite(parent, SWT.V_SCROLL);

		Iterator iterator = study.getGraphs().values().iterator();
		while (iterator.hasNext()) {
			Graph graph = (Graph) iterator.next();

			// Composite composite = new Composite(parent, SWT.BORDER);
			// composite.setLayout(new GridLayout(1, true));
			// composite.setLayoutData(new GridData(GridData.FILL_BOTH));

			chart = new Chart(parent, SWT.BORDER);
			setGraphInput(graph);

			// chart.setLayout(new ChartLayout());
			// chart.setLayoutData(new ChartLayoutData(200, 200));

			// chart.layout(true, true);
			chart.pack(true);
		}
		// composite0.pack(true);
		// composite0.layout(true, true);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the cols.
	 * @return the cols
	 */
	private int getCols() {
		int size = study.getGraphs().size();

		for (int i = 2; i < 5; i++) {
			if ((i * i) > size) {
				return i - 1;
			}
		}
		return 1;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the graph input.
	 * @param graph the new graph input
	 */
	private void setGraphInput(Graph graph) {
		chart.getTitle().setText(study.getName());

		chart.getAxisSet().getXAxis(0).getTitle().setText(graph.getNameX());
		chart.getAxisSet().getYAxis(0).getTitle().setText(graph.getNameY());

		IAxisSet axisSet = chart.getAxisSet();
		IAxis xAxis = axisSet.getXAxis(0);
		xAxis.setCategorySeries((String[]) graph.getCatSeries().toArray());
		xAxis.enableCategory(true);

		IBarSeries series = (IBarSeries) chart.getSeriesSet().createSeries(SeriesType.BAR, "Data");
		series.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));

		double[] values = { 0.7 };
		series.setYSeries(values);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the toolbar.
	 */
	private void createToolbar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();

		IActionBars bars = getViewSite().getActionBars();

		// subtreeFilter = new ContributionItem();
		// filterItem = new FilterContributionItem(viewer, inputMap,
		// subtreeFilter);

		// layoutItem = new LayoutContributionItem(viewer.getGraphControl());
		// zoomItem = new ZoomContributionViewItem(this, zoomManager);

		// bars.getMenuManager().add(filterItem);
		// bars.getMenuManager().add(subtreeFilter);
		// bars.getMenuManager().add(layoutItem);
		// bars.getMenuManager().add(zoomItem);

		// toolBarManager.add(filterItem);
		// toolBarManager.add(subtreeFilter);
		// toolBarManager.add(layoutItem);
		// toolBarManager.add(zoomItem);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the study.
	 * @return the study
	 */
	public Study getStudy() {
		return study;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the study.
	 * @param key the key
	 * @return the study
	 */
	private Study getStudy(String key) {
		if (key == null) {
			return study = StatisticsModelManager.getInstance().getTestStudy();
		}
		return study = (Study) StatisticsModelManager.getInstance().getStatistics().getStatMap().get(key);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the graph.
	 * @return the graph
	 */
	public Graph getGraph() {
		return graph;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the graph.
	 * @param key the key
	 * @return the graph
	 */
	private Graph getGraph(String key) {
		if (key == null) {
			return graph = (Graph) study.getGraphs().values().iterator().next();
		}
		return graph = (Graph) study.getGraphs().get(key);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the study.
	 * @param study the new study
	 */
	public void setStudy(Study study) {
		this.study = study;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the graph.
	 * @param graph the new graph
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initListeners()
	 */
	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initAdapters(java.util.Collection )
	 */
	@Override
	public void initAdapters(Collection<?> collection) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#hookContextMenu()
	 */
	@Override
	public void hookContextMenu() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#getViewer()
	 */
	@Override
	public Object getViewer() {
		return chart;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	protected void fillLocalToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillLocalPullDown(IMenuManager menuManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		// TODO Auto-generated method stub

	}
}
