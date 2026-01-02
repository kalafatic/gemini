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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.INTERFACES;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.factories.SectionFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.models.NetInterface;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.NetUtils;

/**
 * The Class class InterfacesPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */

public class InterfacesPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The first section. */
	private boolean firstSection = true;

	/**
	 * Instantiates a new interfaces preference page.
	 */
	public InterfacesPreferencePage() {
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

			Group group = factory.createGroup(container, "Interfaces", 1, SWT.H_SCROLL | SWT.V_SCROLL);
			SectionFactory factory2 = new SectionFactory(group);
			factory2.initGrid(1);
			List<NetInterface> interfaces = INTERFACES;

			for (NetInterface netInterface : interfaces) {
				Composite sectionClient = createSectionClient(factory2, netInterface.getName());
				sectionClient.setLayout(new GridLayout(1, true));
				sectionClient.setLayoutData(new GridData(GridData.FILL_BOTH));

				createInterfaceBox(sectionClient, factory2, netInterface);
			}
			return container;
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return parent;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the section client.
	 * @param factory the factory
	 * @param name the name
	 * @return the composite
	 * @throws Exception the exception
	 */
	private Composite createSectionClient(SectionFactory factory, String name) throws Exception {

		final Section section = factory.createSection(name, null, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT | Section.CLIENT_INDENT);

		TableWrapData tableWrapData = new TableWrapData(TableWrapData.FILL);
		tableWrapData.colspan = 1;
		tableWrapData.grabHorizontal = true;
		tableWrapData.grabVertical = true;

		section.setLayoutData(tableWrapData);

		if (firstSection) {
			section.setExpanded(true);
			firstSection = false;
		}

		Composite sectionClient = factory.createSectionClient(section, 2);
		section.setClient(sectionClient);

		return sectionClient;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the interface box.
	 * @param sectionClient the section client
	 * @param factory the factory
	 * @param netInterface the net interface
	 * @throws Exception the exception
	 */
	private void createInterfaceBox(final Composite sectionClient, SectionFactory factory, NetInterface netInterface) throws Exception {

		createLabels(sectionClient, factory, "Display name", netInterface.getDisplayName());

		if (netInterface.getMac() != null) {
			createLabels(sectionClient, factory, "Mac address", netInterface.getMac());
		}

		createLabels(sectionClient, factory, "Running", netInterface.isUp() ? "true" : "false");
		createLabels(sectionClient, factory, "Virtual", netInterface.isVirtual() ? "true" : "false");
		createLabels(sectionClient, factory, "Multicast", netInterface.isMulticast() ? "true" : "false");

		List<String> address = netInterface.getAddress();

		for (String stringIp : address) {

			String version = "IP version ";

			if (stringIp.startsWith("/")) {
				stringIp = stringIp.substring(1);
			} else {
				version += NetUtils.getInstance().isIPv4(stringIp) ? "4" : "6";
			}
			createLabels(sectionClient, factory, version, stringIp);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the labels.
	 * @param sectionClient the section client
	 * @param factory the factory
	 * @param name the name
	 * @param value the value
	 * @throws Exception the exception
	 */
	private void createLabels(final Composite sectionClient, SectionFactory factory, String name, String value) throws Exception {

		GUIFactory guiFactory = new GUIFactory();
		Composite composite = guiFactory.createComposite(sectionClient, 2);

		guiFactory.createLabel(composite, name, SWT.NONE);

		final Text outputDirText1 = this.factory.createText(composite, "", true);

		outputDirText1.setText(value);
		outputDirText1.setEditable(false);
	}
}