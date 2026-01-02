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
package eu.kalafatic.gemini.bt.client.controller.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.lib.EView;
import eu.kalafatic.gemini.stat.model.statistics.Graph;
import eu.kalafatic.gemini.stat.model.statistics.StatisticsFactory;
import eu.kalafatic.gemini.stat.model.statistics.Study;

/**
 * The Class class ShowStatistics.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
@SuppressWarnings({ "unchecked" })
public class ShowStatistics extends AbstractHandler {

	/** The PARAMETE r_1. */
	private final String PARAMETER_1 = "eu.kalafatic.gemini.bt.client.controller.ShowStatParameter1";

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			String viewName = event.getParameter(PARAMETER_1);

			IViewPart view = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(viewName);

			Viewer viewer = (Viewer) view.getSite().getSelectionProvider();
			List<Object> selected = getSelected(viewer);

			processSelection(view, selected);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Process selection.
	 * @param view the view
	 * @param selected the selected
	 * @throws Exception the exception
	 */
	private void processSelection(IViewPart view, List<Object> selected) throws Exception {

		if (!selected.isEmpty()) {
			if (selected.get(0) instanceof ExtTorrent) {
				ExtTorrent extTorrent = (ExtTorrent) selected.get(0);
				SwarmSession session = NetworkModelManager.getInstance().getSwarmSession(extTorrent);
				processSelection(view, session);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Process selection.
	 * @param view the view
	 * @param session the session
	 * @throws Exception the exception
	 */
	private void processSelection(IViewPart view, SwarmSession session) throws Exception {
		Study study = StatisticsFactory.eINSTANCE.createStudy();
		study.setName(session.toString());

		Graph graph = StatisticsFactory.eINSTANCE.createGraph();
		graph.setName(session.getAnnounce());
		graph.setNameX("x");
		graph.setNameY("y");

		graph.getCatSeries().add("getCatSeries");
		graph.getCatSeries().add("getCatSeries");
		graph.getCatSeries().add("getCatSeries");

		graph.getSeries().add("getSeries");
		graph.getSeries().add("getSeries");
		graph.getSeries().add("getSeries");

		study.getGraphs().put(Integer.toString(0), graph);

		IViewPart statView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(EView.STATISTICS.ID);

		if (statView instanceof IViewer) {
			Chart chart = (Chart) ((IViewer) statView).getViewer();

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

			chart.layout(true, true);
		}

	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected.
	 * @param viewer the viewer
	 * @return the selected
	 */
	public List<Object> getSelected(Viewer viewer) {
		List<Object> selected = new ArrayList<Object>();

		ISelection selection = viewer.getSelection();

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();

			while (iterator.hasNext()) {
				Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
				selected.add(entry.getValue());
			}
		}
		return selected;
	}
}
