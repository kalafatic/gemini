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
package eu.kalafatic.gemini.bt.tm.view.factories;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A factory for creating Section objects.
 */
public class SectionFactory {

	/** The parent. */
	private Composite parent;

	/** The grid layout. */
	private GridLayout gridLayout;

	/** The grid data. */
	private GridData gridData;

	/** The toolkit. */
	private FormToolkit toolkit;

	/** The form. */
	private ScrolledForm form;

	/**
	 * Instantiates a new section factory.
	 * @param sectionClient the section client
	 */
	public SectionFactory(Composite sectionClient) {
		this.parent = sectionClient;
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		// form.setText("My Form");
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		form.getBody().setLayout(layout);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param name the name
	 * @param description the description
	 * @return the section
	 */
	public Section createSection(String name, String description) {
		Section section = toolkit.createSection(form.getBody(), Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 1;
		section.setLayoutData(td);
		section.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(name);
		if (description != null) {
			section.setDescription(description);
		}
		return section;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param section the section
	 * @param col the col
	 * @return the composite
	 */
	public Composite createSectionClient(Section section, int col) {

		Composite sectionClient = toolkit.createComposite(section);

		gridLayout = new GridLayout(col, true);
		gridLayout.verticalSpacing = 8;
		sectionClient.setLayout(gridLayout);
		sectionClient.setLayoutData(new GridData(GridData.FILL_BOTH));
		return sectionClient;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param parent the parent
	 * @param name the name
	 * @param col the col
	 * @return the group
	 */
	public Group createGroup(Composite parent, String name, int col) {

		final Group group = new Group(parent, SWT.SHADOW_IN);
		group.setText(name);
		gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		group.setLayout(gridLayout);

		group.setLayoutData(new GridData(GridData.FILL_BOTH));

		return group;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name, int style) {
		Label label = new Label(composite, style);
		label.setText(name);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		label.setLayoutData(gridData);
		return label;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param composite the composite
	 * @param style the style
	 * @param editable the editable
	 * @return the text
	 */
	public Text createText(Composite composite, int style, boolean editable) {
		Text text = new Text(composite, style);
		text.setEditable(editable);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param group the group
	 * @param name the name
	 * @param style the style
	 * @return the button
	 */
	public Button createButton(Composite group, String name, int style) {
		Button button = new Button(group, style);
		button.setText(name);
		gridData = new GridData();
		gridData.widthHint = 50;
		gridData.heightHint = 18;
		button.setLayoutData(gridData);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param group the group
	 * @return the composite
	 */
	public Composite createComposite(Composite group) {
		Composite composite = new Composite(group, SWT.NATIVE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		return composite;
	}

}
