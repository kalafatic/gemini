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
package eu.kalafatic.gemini.bt.tm.view.dialogs;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.core.factories.GUIFactory;

/**
 * The Class class EditTrackerDialog.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class EditTrackerDialog extends TitleAreaDialog {

	/** The port text. */
	private Text addressText, portText;

	/** The request. */
	private String[] request;

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new edits the tracker dialog.
	 * @param parentShell the parent shell
	 * @param request the request
	 */
	public EditTrackerDialog(Shell parentShell, String[] request) {
		super(parentShell);
		this.request = request;
		factory = new GUIFactory();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Run.
	 */
	public void run() {
		setBlockOnOpen(true);
		this.open();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets .Shell)
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(350, 180);
		shell.setText("Setup tracker");

		Point cursorLocation = Display.getCurrent().getCursorLocation();
		shell.setLocation(new Point(cursorLocation.x - 350, cursorLocation.y));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		return createControls(composite);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse. swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		// setTitle("Setup tracker");

		setMessage("Address and port must be filled", IMessageProvider.INFORMATION);
		return contents;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the controls.
	 * @param parent the parent
	 * @return the composite
	 */
	public Composite createControls(Composite parent) {
		Composite composite = factory.createComposite(parent, 4);

		factory.createLabel(composite, "Address :", SWT.SHADOW_IN, BTN_WIDTH);
		addressText = factory.createText(composite, "Address", true);

		addressText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if ((addressText.getText() != null) && (portText.getText() != null)) {
					getButton(OK).setEnabled(true);
				} else {
					getButton(OK).setEnabled(false);
				}
			}
		});

		factory.createLabel(composite, "Port :", SWT.SHADOW_IN, BTN_WIDTH);
		portText = factory.createText(composite, "Port", true);

		portText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if ((addressText.getText() != null) && (portText.getText() != null)) {
					getButton(OK).setEnabled(true);
				} else {
					getButton(OK).setEnabled(false);
				}
			}
		});
		return composite;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		request[0] = addressText.getText();
		request[1] = portText.getText();

		super.okPressed();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the address text.
	 * @return the address text
	 */
	public Text getAddressText() {
		return addressText;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the port text.
	 * @return the port text
	 */
	public Text getPortText() {
		return portText;
	}
}
