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
package eu.kalafatic.gemini.bt.client.net.view.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.client.net.view.model.Tab;
import eu.kalafatic.gemini.bt.client.net.view.model.TabContainer;

/**
 * The Class class BTSwarmActivityView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class BTSwarmActivityView extends ViewPart {

	/** The canvas. */
	private Canvas canvas;

	/** The downloads. */
	private TabContainer searchers, clients, downloads;

	/** The Constant S_CONTAINER. */
	public static final String S_CONTAINER = "Active Search";

	/** The Constant A_CONTAINER. */
	public static final String A_CONTAINER = "Available Clients";

	/** The Constant D_CONTAINER. */
	public static final String D_CONTAINER = "Active Clients";

	/**
	 * Instantiates a new bT swarm activity view.
	 */
	public BTSwarmActivityView() {
		// display = Display.getCurrent();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER /* | SWT.V_SCROLL | SWT.H_SCROLL */);

		GridLayout gridLayout = new GridLayout();
		canvas.setLayout(gridLayout);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		canvas.setLayoutData(gridData);

		searchers = new TabContainer(S_CONTAINER, canvas);
		clients = new TabContainer(A_CONTAINER, canvas);
		downloads = new TabContainer(D_CONTAINER, canvas);

		initContainers();

		setLabel(10);
		searchers.init();

		setLabel(30);
		clients.init();

		setLabel(30);
		downloads.init();

		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Resize:

					break;
				case SWT.Paint:
					System.err.println("Paint");

					searchers.initBackground(event.gc);
					clients.initBackground(event.gc);
					downloads.initBackground(event.gc);
					// for(Tab tab:downloads.list){
					// tab.table.setVisible(true);
					// tab.table.pack();
					// }

					break;
				}
			}
		};
		btn();

		canvas.redraw();

		canvas.addListener(SWT.Resize, listener);
		canvas.addListener(SWT.Paint, listener);
	}

	/**
	 * Sets the label.
	 * @param height the new label
	 */
	private void setLabel(int height) {
		Label label = new Label(canvas, SWT.BORDER);

		GridData gridData = new GridData();
		gridData.heightHint = height;
		label.setLayoutData(gridData);
	}

	// ---------------------------------------------------------------

	/**
	 * Btn.
	 */
	private void btn() {
		Button testBtn = new Button(canvas, SWT.PUSH);
		testBtn.setText("Test 1");
		testBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				animate(1);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		testBtn = new Button(canvas, SWT.PUSH);
		testBtn.setText("Test2");
		testBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				animate(2);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		testBtn = new Button(canvas, SWT.PUSH);
		testBtn.setText("Test3");
		testBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				animate(3);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Animate.
	 * @param i the i
	 */
	private void animate(int i) {
		if (i == 1) {
			Tab tab = searchers.list.get(0);
			tab.animateResult(clients);

		} else if (i == 2) {
			Tab tab = clients.list.get(0);
			tab.animateResult(downloads);
		} else if (i == 3) {
			Tab tab = downloads.list.get(0);
			tab.animateResult(downloads);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the containers.
	 */
	private void initContainers() {
		if (searchers.list.isEmpty()) {
			for (int i = 0; i < 5; i++) {
				searchers.add(new Tab("x", searchers, 2), true);
			}
		}
		if (clients.list.isEmpty()) {
			for (int i = 0; i < 20; i++) {
				clients.add(new Tab("y", clients, 2), true);
			}
		}
		if (downloads.list.isEmpty()) {
			downloads.add(new Tab("z", downloads, 2), true);
			downloads.add(new Tab("zz", downloads, 2), true);
			downloads.add(new Tab("zzz", downloads, 2), true);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}
}
