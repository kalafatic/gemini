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
package eu.kalafatic.gemini.core.factories;

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LONG_TEXT_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TEXT_WIDTH;

import java.net.URL;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.Log;

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

	/** The font registry. */
	private FontRegistry fontRegistry;

	/**
	 * Instantiates a new section factory.
	 */
	public SectionFactory() {}

	/**
	 * Instantiates a new section factory.
	 * @param composite the composite
	 */
	public SectionFactory(Composite composite) {
		this.parent = composite;

		fontRegistry = new FontRegistry(Display.getDefault());
		fontRegistry.put("button-text", new FontData[] { new FontData("Arial", 8, SWT.NORMAL) });
		fontRegistry.put("label-text", new FontData[] { new FontData("Arial", 8, SWT.NORMAL) });
		fontRegistry.put("text-text", new FontData[] { new FontData("Arial", 8, SWT.BOLD) });
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the grid.
	 * @param col the col
	 */
	public void initGrid(int col) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = col;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		// layout.makeColumnsEqualWidth=true;

		form.getBody().setLayout(layout);
		// // form.getBody().setLayoutData(new TableWrapData(0, 0, 0, 0));
		// form.getBody().setLayoutData(new TableWrapData(0,0));
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the col.
	 * @param col the col
	 */
	public void initCol(int col) {

		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);

		ColumnLayout layout = new ColumnLayout();
		layout.maxNumColumns = col;

		form.getBody().setLayout(layout);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param name the name
	 * @param description the description
	 * @param style the style
	 * @return the section
	 */
	public Section createSection(String name, String description, int style) {

		Composite composite = form.getBody().getParent();

		final Section section = toolkit.createSection(form.getBody(), style);
		section.setSize(composite.getSize().x, 100);
		composite.pack(true);
		// section.pack(true);

		section.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {

				form.setLayoutData(new GridData(GridData.FILL_BOTH));
				form.reflow(true);
			}
		});
		section.setText(name);
		if (description != null) {
			section.setDescription(description);
		}
		gridLayout = new GridLayout(1, true);
		gridLayout.verticalSpacing = 8;

		section.setLayout(gridLayout);

		// section.setLayoutData(new ColumnLayoutData());
		// section.setLayoutData(new GridData(GridData.FILL_BOTH));

		ColumnLayoutData columnLayoutData = new ColumnLayoutData(ColumnLayoutData.FILL);
		columnLayoutData.horizontalAlignment = ColumnLayoutData.FILL;
		section.setLayoutData(columnLayoutData);

		// section.setBackground(new Color(Display.getDefault(),0,240,255));

		section.pack(true);
		return section;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 * @param description the description
	 * @param expanded the expanded
	 * @return the section
	 */
	public Section createSection(final ScrolledForm form, FormToolkit toolkit, String title, String description, boolean expanded) {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION);
		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));

		section.setText(title);
		section.setDescription(description);

		toolkit.createCompositeSeparator(section);

		section.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		section.setExpanded(expanded);
		return section;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param toolkit the toolkit
	 * @param section the section
	 * @param col the col
	 * @param vSpan the v span
	 * @return the composite
	 */
	public Composite createClient(FormToolkit toolkit, Section section, int col, int vSpan) {
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		client.setLayout(gridLayout);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = vSpan;
		gridData.grabExcessVerticalSpace = true;
		section.setLayoutData(gridData);

		toolkit.paintBordersFor(client);
		section.setClient(client);
		return client;
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

		gridLayout = new GridLayout(col, false);
		gridLayout.verticalSpacing = 8;
		sectionClient.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;

		sectionClient.setLayoutData(gridData);

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

		final Group group = new Group(parent, SWT.SHADOW_IN | SWT.WRAP);
		group.setText(name);

		gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		group.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessVerticalSpace = true;

		group.setLayoutData(gridData);

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
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = LABEL_WIDTH;

		label.setLayoutData(gridData);

		label.setToolTipText(name);

		// label.setForeground(new Color(Display.getDefault(), 0, 0, 0));

		label.setFont(fontRegistry.get("label-text"));

		return label;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param composite the composite
	 * @param name the name
	 * @param href the href
	 * @param style the style
	 * @return the hyperlink
	 */
	public Hyperlink createHyperlink(Composite composite, String name, final String href, int style) {
		Hyperlink link = toolkit.createHyperlink(composite, name, style);
		link.setText(name);
		link.setHref(href);

		link.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkExited(HyperlinkEvent e) {}

			@Override
			public void linkEntered(HyperlinkEvent e) {}

			@Override
			public void linkActivated(HyperlinkEvent e) {
				IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
				try {
					support.getExternalBrowser().openURL(new URL(href));
				} catch (Exception ex) {
					Log.log(ECorePreferences.MODULE, ex);
				}
			}
		});
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = LABEL_WIDTH;

		link.setLayoutData(gridData);
		link.setToolTipText(href);
		// link.setForeground(new Color(Display.getDefault(), 0, 0, 0));
		link.setFont(fontRegistry.get("label-text"));

		return link;
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
		Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);

		// text.setBackground(new Color(Display.getDefault(),230,240,255));

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = TEXT_WIDTH;
		// gridData.heightHint = TEXT_HEIGHT;

		text.setLayoutData(gridData);

		text.setEditable(editable);

		// text.setFont(fontRegistry.get("text-text"));

		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param composite the composite
	 * @param editable the editable
	 * @return the text
	 */
	public Text createLongText(Composite composite, boolean editable) {
		Text text = new Text(composite, SWT.SINGLE | SWT.BORDER);
		// text.setBackground(new Color(Display.getDefault(),230,240,255));

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = LONG_TEXT_WIDTH;
		// gridData.heightHint = TEXT_HEIGHT;

		text.setLayoutData(gridData);

		text.setEditable(editable);

		// text.setFont(fontRegistry.get("text-text"));

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

		gridData = new GridData(GridData.FILL);
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;

		button.setLayoutData(gridData);

		button.setToolTipText(name);

		// button.setForeground(new Color(Display.getDefault(), 50, 150, 50));

		button.setFont(fontRegistry.get("button-text"));

		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new Section object.
	 * @param group the group
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @return the button
	 */
	public Button createButton(Composite group, String name, int style, int width) {
		Button button = new Button(group, style);
		button.setText(name);

		gridData = new GridData(GridData.FILL);
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		gridData.widthHint = width;
		gridData.heightHint = BTN_HEIGHT;

		button.setLayoutData(gridData);

		button.setToolTipText(name);

		// button.setForeground(new Color(Display.getDefault(), 50, 150, 50));

		button.setFont(fontRegistry.get("button-text"));

		return button;
	}

}
