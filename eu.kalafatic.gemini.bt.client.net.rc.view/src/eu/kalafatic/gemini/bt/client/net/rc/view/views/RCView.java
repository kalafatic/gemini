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
package eu.kalafatic.gemini.bt.client.net.rc.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FORWARD_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.HOME_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.PLAY_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.RELOAD_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.REWIND_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.STOP_IMG;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.part.ViewPart;

/**
 * The Class class RCView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCView extends ViewPart {

	/** The location. */
	private Text location;

	/** The LOCALHOST. */
	private final String LOCALHOST = "localhost:6881";

	/**
	 * Instantiates a new rC view.
	 */
	public RCView() {

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		parent.setLayout(gridLayout);
		ToolBar toolbar = new ToolBar(parent, SWT.NONE);

		ToolItem itemHome = new ToolItem(toolbar, SWT.PUSH);
		itemHome.setText("Localhost");
		itemHome.setImage(HOME_IMG);

		ToolItem itemBack = new ToolItem(toolbar, SWT.PUSH);
		itemBack.setText("Back");
		itemBack.setImage(REWIND_IMG);

		ToolItem itemForward = new ToolItem(toolbar, SWT.PUSH);
		itemForward.setText("Forward");
		itemForward.setImage(FORWARD_IMG);

		ToolItem itemStop = new ToolItem(toolbar, SWT.PUSH);
		itemStop.setText("Stop");
		itemStop.setImage(STOP_IMG);

		ToolItem itemGo = new ToolItem(toolbar, SWT.PUSH);
		itemGo.setText("Go");
		itemGo.setImage(PLAY_IMG);

		ToolItem itemRefresh = new ToolItem(toolbar, SWT.PUSH);
		itemRefresh.setText("Refresh");
		itemRefresh.setImage(RELOAD_IMG);

		GridData data = new GridData();
		data.horizontalSpan = 3;
		toolbar.setLayoutData(data);

		Label labelAddress = new Label(parent, SWT.NONE);
		labelAddress.setText("Address");

		location = new Text(parent, SWT.BORDER);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		location.setLayoutData(data);

		final Browser browser = new Browser(parent, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);

		browser.setJavascriptEnabled(true);

		browser.setUrl(LOCALHOST);

		final Label status = new Label(parent, SWT.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		status.setLayoutData(data);

		final ProgressBar progressBar = new ProgressBar(parent, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.END;
		progressBar.setLayoutData(data);

		/* event handling */
		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				ToolItem item = (ToolItem) event.widget;
				String string = item.getText();

				if (string.equals("Localhost")) {
					browser.setUrl(LOCALHOST);
				} else if (string.equals("Back")) {
					browser.back();
				} else if (string.equals("Forward")) {
					browser.forward();
				} else if (string.equals("Stop")) {
					browser.stop();
				} else if (string.equals("Refresh")) {
					browser.refresh();
				} else if (string.equals("Go")) {
					browser.setUrl(location.getText());
				}
			}
		};
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void changed(ProgressEvent event) {
				if (event.total == 0) {
					return;
				}
				int ratio = event.current * 100 / event.total;
				progressBar.setSelection(ratio);
			}

			@Override
			public void completed(ProgressEvent event) {
				progressBar.setSelection(0);
			}
		});
		browser.addStatusTextListener(new StatusTextListener() {
			@Override
			public void changed(StatusTextEvent event) {
				status.setText(event.text);
			}
		});
		browser.addLocationListener(new LocationListener() {
			@Override
			public void changed(LocationEvent event) {
				if (event.top) {
					location.setText(event.location);
				}
			}

			@Override
			public void changing(LocationEvent event) {}
		});
		itemHome.addListener(SWT.Selection, listener);
		itemBack.addListener(SWT.Selection, listener);
		itemForward.addListener(SWT.Selection, listener);
		itemStop.addListener(SWT.Selection, listener);
		itemRefresh.addListener(SWT.Selection, listener);
		itemGo.addListener(SWT.Selection, listener);

		location.addListener(SWT.DefaultSelection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				browser.setUrl(location.getText());
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}

	// ---------------------------------------------------------------

	/**
	 * Gets the location.
	 * @return the location
	 */
	public Text getLocation() {
		return location;
	}
}
