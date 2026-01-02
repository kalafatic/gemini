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
package eu.kalafatic.gemini.bt.tracker.controller.components;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TEXT_WIDTH;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.tracker.controller.lib.ETrackerPreferences;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class EditClientDialog.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class EditClientDialog extends TitleAreaDialog {

	/** The host text. */
	private Text addressText, hostText;

	/** The port text. */
	private Text portText;

	/** The note text. */
	private Text noteText;

	/** The request. */
	private String[] request;

	/**
	 * Instantiates a new edits the client dialog.
	 * @param parentShell the parent shell
	 * @param request the request
	 */
	public EditClientDialog(Shell parentShell, String[] request) {
		super(parentShell);
		this.request = request;
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
		shell.setSize(350, 260);
		shell.setText("Setup client");
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
		setTitle("Setup client");

		setMessage("Host OR Address must be filled.\n" + " Port must be filled.\n" + " Note is optional ", IMessageProvider.INFORMATION);
		return contents;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the controls.
	 * @param parent the parent
	 * @return the composite
	 */
	public Composite createControls(Composite parent) {
		Composite composite = null;
		try {
			composite = new Composite(parent, SWT.NATIVE | SWT.FILL);
			GridLayout gridLayout = new GridLayout();
			composite.setLayout(gridLayout);
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.verticalAlignment = GridData.BEGINNING;

			composite.setLayoutData(gridData);

			Composite output = new Composite(composite, SWT.FILL);

			gridLayout = new GridLayout();
			gridLayout.numColumns = 4;
			gridData = new GridData(GridData.FILL_HORIZONTAL);

			output.setLayout(gridLayout);
			output.setLayoutData(gridData);

			Label hostLabel = new Label(output, SWT.FILL);
			hostLabel.setText("Host :  ");

			gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.grabExcessHorizontalSpace = false;
			gridData.minimumWidth = LABEL_WIDTH;
			hostLabel.setLayoutData(gridData);

			hostText = new Text(output, SWT.BORDER);

			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.grabExcessHorizontalSpace = true;
			gridData.minimumWidth = TEXT_WIDTH;
			hostText.setEditable(true);
			hostText.setLayoutData(gridData);

			hostText.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					if ((addressText.getText() != null) && (portText.getText() != null)) {
						getButton(OK).setEnabled(true);
					} else {
						getButton(OK).setEnabled(false);
					}
				}
			});

			Label portLabel = new Label(output, SWT.FILL);
			portLabel.setText("Port :    ");

			gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.grabExcessHorizontalSpace = false;
			gridData.minimumWidth = LABEL_WIDTH;
			portLabel.setLayoutData(gridData);

			portText = new Text(output, SWT.BORDER);

			int clientPort = PREFERENCES.getInt(ECorePreferences.BT_CLIENT_PORT.getName(), (Integer) ECorePreferences.BT_CLIENT_PORT.getDef());

			portText.setText(Integer.toString(clientPort));

			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.grabExcessHorizontalSpace = true;
			gridData.minimumWidth = BTN_WIDTH;
			portText.setEditable(true);
			portText.setLayoutData(gridData);

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

			Label addressLabel = new Label(output, SWT.FILL);
			addressLabel.setText("Address :  ");

			addressText = new Text(output, SWT.BORDER);

			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.grabExcessHorizontalSpace = true;
			gridData.minimumWidth = TEXT_WIDTH;
			addressText.setEditable(true);
			addressText.setLayoutData(gridData);

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

			Composite comments = new Composite(composite, SWT.FILL);

			gridLayout = new GridLayout();
			gridLayout.numColumns = 2;
			gridData = new GridData(GridData.FILL_HORIZONTAL);

			comments.setLayout(gridLayout);
			comments.setLayoutData(gridData);

			Label noteLabel = new Label(comments, SWT.FILL);
			noteLabel.setText("Note :       ");

			gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.grabExcessHorizontalSpace = false;
			gridData.minimumWidth = LABEL_WIDTH;
			noteLabel.setLayoutData(gridData);

			noteText = new Text(comments, SWT.NONE | SWT.WRAP | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.grabExcessHorizontalSpace = true;
			gridData.heightHint = 40;
			gridData.minimumWidth = BTN_WIDTH;

			noteText.setLayoutData(gridData);
			noteText.addVerifyListener(new VerifyListener() {

				@Override
				public void verifyText(VerifyEvent e) {}
			});

		} catch (Exception e) {
			Log.log(ETrackerPreferences.MODULE, e);
		}
		return composite;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		if (hostText.getText().length() > 0) {
			request[0] = hostText.getText();
		} else {
			request[0] = addressText.getText();
		}
		request[1] = portText.getText();
		request[2] = noteText.getText();

		try {
			if (request[0].length() > 0) {
				InetAddress.getByName(request[0]);
				super.okPressed();
				return;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		MessageDialog.openInformation(getShell(), "Client", "Unknown Host");
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

	// ---------------------------------------------------------------

	/**
	 * Gets the comments text.
	 * @return the comments text
	 */
	public Text getCommentsText() {
		return noteText;
	}
}
