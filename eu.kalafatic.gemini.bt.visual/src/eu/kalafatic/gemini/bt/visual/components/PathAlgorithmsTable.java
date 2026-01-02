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
package eu.kalafatic.gemini.bt.visual.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.visual.config.Config;
import eu.kalafatic.gemini.bt.visual.lib.IAlgorithmsConstants;

/**
 * The Class PathAlgorithmsTable.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class PathAlgorithmsTable extends Dialog implements
		IAlgorithmsConstants {

	/** The algorithm text. */
	private Text algorithmText;
	
	/** The algorithm. */
	private int algorithm;

	// The shared instance
	/** The INSTANCE. */
	private static PathAlgorithmsTable INSTANCE;

	/**
	 * Gets the single instance of PathAlgorithmsTable.
	 * 
	 * @param shell
	 *            the shell
	 * @param text
	 *            the text
	 * @param algorithm
	 *            the algorithm
	 * 
	 * @return single instance of PathAlgorithmsTable
	 */
	public static PathAlgorithmsTable getInstance(Shell shell,
			Text text, int algorithm) {
		if (INSTANCE == null) {
			INSTANCE = new PathAlgorithmsTable(shell, text, algorithm);
		}
		return INSTANCE;
	}
	

	/**
	 * Instantiates a new path algorithms table.
	 * 
	 * @param parent
	 *            the parent
	 * @param algorithmText
	 *            the algorithm text
	 * @param algorithm
	 *            the algorithm
	 */
	public PathAlgorithmsTable(Shell parent, Text algorithmText,
			int algorithm) {
		super(parent);
		this.algorithmText = algorithmText;
		this.algorithm = algorithm;
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Open.
	 */
	public void open() {
		Shell parent = getParent();
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		dialog.setSize(220, 100);
		dialog.setLocation(Display.getCurrent().getCursorLocation());
		dialog.setText("Algorithms");
		dialog.setLayout(new GridLayout(2, true));
		dialog.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		switch (algorithm) {
		case SHORTEST_PATH:
			setShortestPathAlgorithms(dialog);
			Config.task=SHORTEST_PATH;
			break;
		case LONGEST_PATH:
			setLongestPathAlgorithms(dialog);
			Config.task=LONGEST_PATH;
			break;

		default:
			break;
		}		
		dialog.open();
		dialog.setFocus();

		Display display = parent.getDisplay();
		while (!parent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the shortest path algorithms.
	 * 
	 * @param dialog
	 *            the new shortest path algorithms
	 */
	private void setShortestPathAlgorithms(final Shell dialog) {
		
		for (int i = 0; i < SHORTEST_PATH_ALG.length; i++) {

			final Button btn = new Button(dialog, SWT.PUSH);			
			GridData gridData = new GridData();
			gridData.widthHint = 100;
			gridData.heightHint = 18;
			btn.setLayoutData(gridData);

			String myString = SHORTEST_PATH_ALG[i];
			btn.setData(SHORTEST_PATH_ALG[i]);

			btn.setText(myString);
			btn.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					algorithmText.setText(btn.getText());
					
					Config.algorithm=btn.getText();

					dialog.close();
					dialog.dispose();
				}
			});
		}		
	}
	
	// ---------------------------------------------------------------	
	
	/**
	 * Sets the longest path algorithms.
	 * 
	 * @param dialog
	 *            the new longest path algorithms
	 */
	private void setLongestPathAlgorithms(final Shell dialog) {
		for (int i = 0; i < SHORTEST_PATH_ALG.length; i++) {

			final Button btn = new Button(dialog, SWT.PUSH);
			btn.setBackground(new Color(dialog.getDisplay(), 0, 150, 150));
			GridData gridData = new GridData();
			gridData.widthHint = 50;
			gridData.heightHint = 18;
			btn.setLayoutData(gridData);

			String myString = SHORTEST_PATH_ALG[i];
			btn.setData(SHORTEST_PATH_ALG[i]);

			btn.setText(myString);
			btn.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					algorithmText.setText(btn.getText());
					
					Config.algorithm=btn.getText();

					dialog.close();
					dialog.dispose();
				}
			});
		}		
	}
}
