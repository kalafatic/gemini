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
package eu.kalafatic.gemini.core.dialogs;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.MESSAGE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SETTINGS;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.TITLE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.WIDTH;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.core.factories.GUIFactory;

/**
 * The Class class SetupDialog.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SetupDialog extends TitleAreaDialog {

	/** The setup. */
	public static Map setup = new HashMap();
	static {
		setup.put(TITLE, "");
		setup.put(MESSAGE, "");
		setup.put(WIDTH, 300);
		setup.put(HEIGHT, 400);
		setup.put(SETTINGS, new LinkedHashMap());
	}

	/** The old setup. */
	private static LinkedHashMap oldSetup = new LinkedHashMap();

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new setup dialog.
	 * @param factory the factory
	 * @param specificSetup the specific setup
	 */
	public SetupDialog(GUIFactory factory, Map specificSetup) {
		super(factory.getShell());
		this.factory = factory;

		setup.put(SETTINGS, specificSetup);
		oldSetup.putAll(specificSetup);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize((Integer) setup.get(WIDTH), (Integer) setup.get(HEIGHT));
		shell.setText("Setup Dialog");
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
		setTitle((String) setup.get("Title"));
		setMessage((String) setup.get("Message"), IMessageProvider.INFORMATION);
		return contents;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the controls.
	 * @param parent the parent
	 * @return the composite
	 */
	private Composite createControls(Composite parent) {
		Composite composite = new Composite(parent, SWT.NO);
		composite.setLayout(new GridLayout(2, true));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalAlignment = GridData.BEGINNING;
		composite.setLayoutData(gridData);

		LinkedHashMap map = (LinkedHashMap) setup.get(SETTINGS);
		Iterator iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Composite row = factory.createComposite(parent, 2);

			factory.createLabel(row, (String) entry.getKey());
			Text text = factory.createText(row, (String) entry.getKey(), true);

			if (entry.getValue() instanceof String) {
				String value = (String) entry.getValue();
				if (value != null) {
					text.setText(value);
				}
			}
			entry.setValue(text);
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
		LinkedHashMap map = (LinkedHashMap) setup.get(SETTINGS);
		Iterator iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			Text text = (Text) entry.getValue();

			entry.setValue(text.getText());
		}
		super.okPressed();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	@Override
	protected void cancelPressed() {
		Map map = (LinkedHashMap) setup.get(SETTINGS);
		map.putAll(oldSetup);
		super.cancelPressed();
	}
}
