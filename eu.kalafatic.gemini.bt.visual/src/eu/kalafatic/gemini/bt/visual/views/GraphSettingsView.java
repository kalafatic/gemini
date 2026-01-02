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
package eu.kalafatic.gemini.bt.visual.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import eu.kalafatic.gemini.bt.visual.groups.ActionsGroupContent;
import eu.kalafatic.gemini.bt.visual.groups.SettingsGroupContent;
import eu.kalafatic.gemini.core.factories.SectionFactory;

/**
 * The Class GraphSettingsView.
 * 
 * @author Petr Kalafatic (petr@kalafatic.eu)
 * @since 2010
 * @project Gemini v 2.0.2
 */
public class GraphSettingsView extends ViewPart {

	/**
	 * Instantiates a new graph settings view.
	 */
	public GraphSettingsView() {
		// TODO Auto-generated constructor stub
	}
	
	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {

		SectionFactory factory = new SectionFactory(parent);
		factory.initCol(1);

		Section section = factory.createSection("Actions", null,
				Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
		section.setExpanded(true);
		Composite sectionClient = factory.createSectionClient(section, 1);
		section.setClient(sectionClient);

		Group group = factory.createGroup(sectionClient, "Settings", 2);
		new SettingsGroupContent(factory, group).createContent();

		group = factory.createGroup(sectionClient, "Actions", 2);
		new ActionsGroupContent(factory, group).createContent();
	}
	
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
