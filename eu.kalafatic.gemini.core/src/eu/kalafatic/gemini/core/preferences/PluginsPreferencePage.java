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
package eu.kalafatic.gemini.core.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;

import java.util.Map;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.osgi.framework.Bundle;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.BundleUtils;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class PluginsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

public class PluginsPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new plugins preference page.
	 */
	public PluginsPreferencePage() {
		super.noDefaultAndApplyButton();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		try {
			factory = new GUIFactory(parent);
			Composite container = factory.createComposite(parent, 1);

			Group group = factory.createGroup(container, "Bundles", 1);
			createEclipsePluginsBox(group);
			createGeminiPluginsBox(group);

			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the eclipse plugins box.
	 * @param composite the composite
	 * @throws Exception the exception
	 */
	private void createEclipsePluginsBox(Composite composite) throws Exception {
		Table table = new Table(composite, SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setSize(LONG_TEXT_WIDTH, 50);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = LONG_TEXT_WIDTH;
		table.setLayoutData(gridData);

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Row");
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Bundle name");

		Map<Long, IBundleGroup> installedBundles = BundleUtils.getInstance().getInstalledBundles();

		int row = 1;

		for (IBundleGroup iBundleGroup : installedBundles.values()) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { Integer.toString(row++), iBundleGroup.getName() });
		}
		column1.pack();
		column2.pack();
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the gemini plugins box.
	 * @param composite the composite
	 * @throws Exception the exception
	 */
	private void createGeminiPluginsBox(Composite composite) throws Exception {
		Table table = new Table(composite, SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setSize(LONG_TEXT_WIDTH, 50);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = LABEL_WIDTH;
		gridData.widthHint = LABEL_WIDTH;
		table.setLayoutData(gridData);

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Row");
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Bundle name");
		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Bundle state");

		Map<Long, Bundle> geminiPlugins = BundleUtils.getInstance().getGeminiPlugins();

		int row = 1;

		for (Bundle bundle : geminiPlugins.values()) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { Integer.toString(row++), bundle.getSymbolicName(), getBundleState(bundle.getState()) });
		}
		column1.pack();
		column2.pack();
		column3.pack();
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the bundle state.
	 * @param key the key
	 * @return the bundle state
	 */
	private String getBundleState(int key) {
		switch (key) {
		case Bundle.INSTALLED:
			return "INSTALLED";
		case Bundle.UNINSTALLED:
			return "UNINSTALLED";
		case Bundle.RESOLVED:
			return "RESOLVED";
		case Bundle.ACTIVE:
			return "ACTIVE";
		case Bundle.STARTING:
			return "STARTING";
		case Bundle.STOPPING:
			return "STOPPING";

		default:
			break;
		}
		return "";
	}
}
