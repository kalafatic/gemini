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
package eu.kalafatic.gemini.bt.visual.config;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class Config.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class Config {

	/** The input file name. */
	public static String inputFileName;
	
	/** The output file name. */
	public static String outputFileName;

	/** The task. */
	public static int task;
	
	/** The algorithm. */
	public static String algorithm;

	/** The generate output. */
	public static boolean generateOutput = true;
	
	/** The show result. */
	public static boolean showResult = true;
	
	/** The draw solution. */
	public static boolean drawSolution = false;
	
	/** The stopped. */
	public static boolean stopped = true;

	/** The start node. */
	public static int startNode = 0;
	
	/** The end node. */
	public static int endNode = 0;

	/** The range. */
	public static int range = 8;
	
	/** The generate. */
	public static boolean generate = true;
	
	/** The radius. */
	public static int radius = 200;
	
	/** The radius changed. */
	public static boolean radiusChanged = false;
	
	/** The delay. */
	public static int delay = 250;

	/** The model file. */
	public static File modelFile;

	/** The slow node drawing. */
	public static long slowNodeDrawing = 50;
	
	/** The slow bridge drawing. */
	public static long slowBridgeDrawing = 100;

	/** The arial font. */
	public Font arialFont = new Font(Display.getDefault(), "Arial", 12,
			SWT.BOLD | SWT.ITALIC);

	/**
	 * Show message.
	 * 
	 * @param message
	 *            the message
	 */
	public static void showMessage(String message) {

		Shell parent = Display.getCurrent().getActiveShell();
		Shell dialog = new Shell(parent, SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		
		dialog.setText("Results");
		dialog.setSize(300, 150);
		dialog.setLocation(new Point(0,0));
		createContents(dialog, message);

		dialog.open();
		Display display = parent.getDisplay();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	// ---------------------------------------------------------------

	/**
	 * Creates the contents.
	 * 
	 * @param dialog
	 *            the dialog
	 * @param message
	 *            the message
	 */
	private static void createContents(final Shell dialog, String message) {
		
		GridLayout childLayout = new GridLayout(1, false);
		childLayout.marginHeight = 10;
		childLayout.marginWidth = 10;
		childLayout.verticalSpacing = 10;
		dialog.setLayout(childLayout);
		
		Text commentsText = new Text(dialog, SWT.NONE | SWT.WRAP | SWT.BORDER
				| SWT.MULTI | SWT.V_SCROLL);
		commentsText.setText(message);
		commentsText.setEditable(false);

		GridData gridData = new GridData(GridData.FILL_BOTH);
////		gridData.grabExcessHorizontalSpace = true;
////		gridData.heightHint = 50;
////		gridData.minimumWidth = 190;
		commentsText.setLayoutData(gridData);		
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw shell background.
	 * 
	 * @param shell
	 *            the shell
	 */
	public static void drawShellBackground(Shell shell) {
		Display display = Display.getCurrent();
		GC gc = new GC(shell);
		Rectangle bounds = shell.getBounds();
		shell.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

		gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		gc.fillGradientRectangle(bounds.x, bounds.y, bounds.width,
				bounds.height, false);

		shell.layout(true, true);
		gc.dispose();
	}
	
	// ---------------------------------------------------------------

	/**
	 * Draw children shells background.
	 * 
	 * @param shell
	 *            the shell
	 */
	public static void drawChildrenShellsBackground(Shell shell) {
		drawShellBackground(shell);
		Shell[] shells = shell.getShells();

		for (int i = 0; i < shells.length; i++) {
			drawShellBackground(shells[i]);
		}
	}
}
