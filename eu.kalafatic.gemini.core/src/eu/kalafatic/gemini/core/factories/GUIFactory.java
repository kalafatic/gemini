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

import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.hack.ImageCombo;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EDateFormat;
import eu.kalafatic.gemini.core.models.ComboData;
import eu.kalafatic.gemini.core.utils.DateUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * A factory for creating GUI objects.
 */
public class GUIFactory {

	/** The Constant NAME. */
	public static final String NAME = "name";

	/** The Constant DATA. */
	public static final String DATA = "data";

	/** The Constant ORIGINAL. */
	public static final String ORIGINAL = "original";

	/** The shell. */
	private Shell shell;

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

	/** The section style. */
	public final int SECTION_STYLE = Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT;

	/** The Constant GUI_FACTORY. */
	public static final GUIFactory GUI_FACTORY = new GUIFactory();

	/**
	 * Instantiates a new GUI factory.
	 */
	public GUIFactory() {
		toolkit = new FormToolkit(Display.getDefault());
		init();
	}

	/**
	 * Instantiates a new GUI factory.
	 * @param parent the parent
	 */
	public GUIFactory(Composite parent) {
		this.shell = parent.getShell();
		toolkit = new FormToolkit(parent.getDisplay());
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		fontRegistry = new FontRegistry(Display.getDefault());
		fontRegistry.put("button-text", new FontData[] { new FontData("Arial", 8, SWT.NORMAL) });
		fontRegistry.put("label-text", new FontData[] { new FontData("Arial", 8, SWT.NORMAL) });
		fontRegistry.put("text-text", new FontData[] { new FontData("Arial", 8, SWT.BOLD) });

	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param name the name
	 * @param description the description
	 * @param style the style
	 * @return the section
	 */
	public Section createSection(String name, String description, int style) {

		final Section section = toolkit.createSection(form.getBody(), style);

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

		section.setLayoutData(new ColumnLayoutData());

		return section;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param section the section
	 * @param col the col
	 * @return the composite
	 */
	public Composite createSectionClient(Section section, int col) {

		Composite sectionClient = toolkit.createComposite(section);

		gridLayout = new GridLayout(col, true);
		gridLayout.verticalSpacing = 8;
		sectionClient.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;

		sectionClient.setLayoutData(gridData);
		return sectionClient;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param col the col
	 * @return the composite
	 */
	public Composite createComposite(Composite parent, int col) {
		return createComposite(parent, col, SWT.SHADOW_IN);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param col the col
	 * @param style the style
	 * @return the composite
	 */
	public Composite createComposite(Composite parent, int col, int style) {
		Composite composite = new Composite(parent, style);

		gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		composite.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);

		return composite;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param col the col
	 * @param style the style
	 * @param width the width
	 * @return the composite
	 */
	public Composite createComposite(Composite parent, int col, int style, int width) {
		Composite composite = new Composite(parent, style);

		gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		composite.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = width;
		composite.setLayoutData(gridData);

		return composite;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param col the col
	 * @return the group
	 */
	public Group createGroup(Composite parent, String name, int col) {
		return createGroup(parent, name, col, SWT.SHADOW_IN | SWT.WRAP);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param col the col
	 * @param orientation the orientation
	 * @return the sash form
	 */
	public SashForm createSashForm(Composite parent, int col, int orientation) {
		SashForm form = new SashForm(parent, SWT.BORDER | orientation);
		form.setLayout(new GridLayout(col, true));

		gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL | GridData.GRAB_HORIZONTAL);
		form.setLayoutData(gridData);
		return form;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param col the col
	 * @param orientation the orientation
	 * @param width the width
	 * @return the sash form
	 */
	public SashForm createSashForm(Composite parent, int col, int orientation, int width) {
		SashForm form = new SashForm(parent, SWT.BORDER | orientation);
		form.setLayout(new GridLayout(col, true));

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = width;
		parent.setLayoutData(gridData);

		form.setLayoutData(gridData);
		return form;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param col the col
	 * @param style the style
	 * @return the group
	 */
	public Group createGroup(Composite parent, String name, int col, int style) {
		Group group = new Group(parent, style);
		group.setText(name);

		gridLayout = new GridLayout();
		gridLayout.numColumns = col;
		group.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_HORIZONTAL);

		group.setLayoutData(gridData);

		return group;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param col the col
	 * @param style the style
	 * @param width the width
	 * @return the group
	 */
	public Group createGroup(Composite parent, String name, int col, int style, int width) {
		Group group = createGroup(parent, name, col);
		((GridData) group.getLayoutData()).widthHint = width;
		return group;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @return the label
	 */
	public Label createLabel(Composite composite) {
		return createLabel(composite, "", SWT.NONE);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param width the width
	 * @return the label
	 */
	public Label createLabel(Composite composite, int width) {
		return createLabel(composite, "", SWT.NONE, width);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name) {
		return createLabel(composite, name, SWT.SHADOW_IN);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param span the span
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name, byte span) {
		return createLabel(composite, name, SWT.SHADOW_IN, span);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param span the span
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name, int style, byte span) {
		Label label = createLabel(composite, name, style);
		if (span > 0) {
			((GridData) label.getLayoutData()).horizontalSpan = span;
		} else {
			((GridData) label.getLayoutData()).verticalSpan = -span;
		}
		return label;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name, int style) {
		Label label = new Label(composite, style);
		label.setText(name);
		label.setToolTipText(name);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
		gridData.widthHint = LABEL_WIDTH;
		label.setLayoutData(gridData);

		return label;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @return the label
	 */
	public Label createLabel(Composite composite, String name, int style, int width) {
		Label label = createLabel(composite, name, style);
		((GridData) label.getLayoutData()).widthHint = width;
		return label;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param href the href
	 * @return the hyperlink
	 */
	public Hyperlink createHyperlink(Composite composite, String name, final String href) {

		Hyperlink link = toolkit.createHyperlink(composite, name, SWT.NORMAL);
		link.setText(name);
		link.setHref(href);

		link.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkExited(HyperlinkEvent e) {

			}

			@Override
			public void linkEntered(HyperlinkEvent e) {

			}

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

		link.setForeground(new Color(Display.getDefault(), 0, 0, 0));
		link.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

		link.setFont(fontRegistry.get("label-text"));

		return link;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param editable the editable
	 * @return the text
	 */
	public Text createText(Composite composite, String name, boolean editable) {
		return createText(composite, name, editable, SWT.SINGLE | SWT.BORDER);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param editable the editable
	 * @param span the span
	 * @return the text
	 */
	public Text createText(Composite composite, String name, boolean editable, byte span) {
		Text text = createText(composite, name, editable, SWT.SINGLE | SWT.BORDER);
		if (span > 0) {
			((GridData) text.getLayoutData()).horizontalSpan = span;
		} else {
			((GridData) text.getLayoutData()).verticalSpan = span;
		}
		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param editable the editable
	 * @param style the style
	 * @return the text
	 */
	public Text createText(Composite composite, String name, boolean editable, int style) {
		Text text = new Text(composite, style);
		text.setEditable(editable);
		text.setToolTipText(name);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		// gridData.widthHint = TEXT_WIDTH;
		text.setLayoutData(gridData);

		text.setFont(fontRegistry.get("text-text"));

		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param editable the editable
	 * @param style the style
	 * @param width the width
	 * @return the text
	 */
	public Text createText(Composite composite, String name, boolean editable, int style, int width) {
		Text text = createText(composite, name, editable, style);
		((GridData) text.getLayoutData()).widthHint = width;
		text.pack(true);
		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style) {
		Button button = new Button(composite, style);
		button.setText(name);
		button.setToolTipText(name);

		gridData = new GridData(GridData.FILL);
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		button.setLayoutData(gridData);

		button.setForeground(new Color(Display.getDefault(), 50, 150, 50));
		button.setFont(fontRegistry.get("button-text"));

		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param listener the listener
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, Listener listener) {
		Button button = createButton(composite, name, style);
		button.addListener(SWT.Selection, listener);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width) {
		Button button = createButton(composite, name, style);
		((GridData) button.getLayoutData()).widthHint = width;
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param checked the checked
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, boolean checked) {
		return createButton(composite, name, style, BTN_WIDTH, checked);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @param checked the checked
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width, boolean checked) {
		Button button = createButton(composite, name, style, width);
		button.setSelection(checked);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param checked the checked
	 * @param listener the listener
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, boolean checked, Listener listener) {
		return createButton(composite, name, style, BTN_WIDTH, checked, listener);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @param checked the checked
	 * @param listener the listener
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width, boolean checked, Listener listener) {
		Button button = createButton(composite, name, style, width, checked);
		if (listener != null) {
			button.addListener(SWT.Selection, listener);
		}
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @param checked the checked
	 * @param enabled the enabled
	 * @param listener the listener
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width, boolean checked, boolean enabled, Listener listener) {
		Button button = createButton(composite, name, style, width, checked, listener);
		button.setEnabled(enabled);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @param backImage the back image
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width, Image backImage) {
		Button button = createButton(composite, name, style, width);
		button.setImage(backImage);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param width the width
	 * @param height the height
	 * @param backImage the back image
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, int width, int height, Image backImage) {
		Button button = createButton(composite, name, style, width);
		((GridData) button.getLayoutData()).heightHint = height;
		button.setImage(backImage);
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @param span the span
	 * @return the button
	 */
	public Button createButton(Composite composite, String name, int style, byte span) {
		Button button = createButton(composite, name, style);
		if (span > 0) {
			((GridData) button.getLayoutData()).horizontalSpan = span;
		} else {
			((GridData) button.getLayoutData()).verticalSpan = span;
		}
		return button;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param style the style
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, int style) {
		final Combo combo = new Combo(parent, style);
		combo.setToolTipText(name);

		gridData = new GridData();
		gridData.widthHint = BTN_WIDTH;
		combo.setLayoutData(gridData);

		combo.setForeground(new Color(Display.getDefault(), 50, 150, 50));
		combo.setFont(fontRegistry.get("button-text"));
		return combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param comboData the combo data
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, ComboData comboData) {
		return createCombo(parent, name, SWT.READ_ONLY, comboData);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param style the style
	 * @param comboData the combo data
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, int style, ComboData comboData) {
		Combo combo = createCombo(parent, name, style);
		combo.setData("comboData", comboData);
		combo.setItems(comboData.getItems());
		combo.select(comboData.defaultSelection);
		return combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param comboData the combo data
	 * @param listener the listener
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, ComboData comboData, Listener listener) {
		Combo combo = createCombo(parent, name, comboData);
		combo.addListener(SWT.Selection, listener);
		return combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param items the items
	 * @return the image combo
	 */
	public ImageCombo createImageCombo(Composite parent, String name, List<Object[]> items) {
		final ImageCombo combo = new ImageCombo(parent, SWT.READ_ONLY);
		combo.setToolTipText(name);

		gridData = new GridData();
		gridData.widthHint = BTN_WIDTH;
		combo.setLayoutData(gridData);

		combo.setForeground(new Color(Display.getDefault(), 50, 150, 50));
		combo.setFont(fontRegistry.get("button-text"));

		for (Object[] objects : items) {
			combo.add((String) objects[0], (Image) objects[1]);
		}
		return combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param items the items
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, String... items) {
		return createCombo(parent, name, SWT.READ_ONLY, items);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param name the name
	 * @param style the style
	 * @param items the items
	 * @return the combo
	 */
	public Combo createCombo(Composite parent, String name, int style, String... items) {
		Combo combo = createCombo(parent, name, style);
		for (int i = 0; i < items.length; i++) {
			combo.add(items[i]);
		}
		return combo;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @return the table
	 */
	public Table createTable(Composite composite, String name) {
		return createTable(composite, name, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	}

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param name the name
	 * @param style the style
	 * @return the table
	 */
	public Table createTable(Composite composite, String name, int style) {
		return createTable(composite, style, name, false, false);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param style the style
	 * @param name the name
	 * @param headerVisible the header visible
	 * @param linesVisible the lines visible
	 * @return the table
	 */
	public Table createTable(Composite composite, int style, String name, boolean headerVisible, boolean linesVisible) {
		return createTable(composite, style, name, name, headerVisible, linesVisible);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param style the style
	 * @param data the data
	 * @param name the name
	 * @param headerVisible the header visible
	 * @param linesVisible the lines visible
	 * @return the table
	 */
	public Table createTable(Composite composite, int style, Object data, String name, boolean headerVisible, boolean linesVisible) {
		Table table = new Table(composite, style);
		table.setData(data);
		table.setData(NAME, name);
		table.setToolTipText(name);

		gridData = new GridData(GridData.FILL_BOTH);
		// gridData.heightHint = BTN_WIDTH;
		table.setLayoutData(gridData);

		table.setHeaderVisible(headerVisible);
		table.setLinesVisible(linesVisible);
		// table.s

		table.setForeground(new Color(Display.getDefault(), 50, 150, 50));
		table.setFont(fontRegistry.get("button-text"));

		return table;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param table the table
	 * @param style the style
	 * @param name the name
	 * @return the table column
	 */
	public TableColumn createTableColumn(Table table, int style, String name) {
		return createTableColumn(table, style, name, 10);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param table the table
	 * @param style the style
	 * @param name the name
	 * @param width the width
	 * @return the table column
	 */
	public TableColumn createTableColumn(Table table, int style, String name, int width) {
		TableColumn tableColumn = new TableColumn(table, style);
		tableColumn.setToolTipText(name);
		tableColumn.setText(name);
		tableColumn.setWidth(width);
		tableColumn.setResizable(true);
		return tableColumn;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param table the table
	 * @param checked the checked
	 * @param values the values
	 * @return the table item
	 */
	public TableItem createTableItem(Table table, boolean checked, String... values) {
		return createTableItem(table, values[0], checked, values);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param table the table
	 * @param data the data
	 * @param checked the checked
	 * @param values the values
	 * @return the table item
	 */
	public TableItem createTableItem(Table table, Object data, boolean checked, String... values) {
		TableItem tableItem = new TableItem(table, SWT.LEFT);
		tableItem.setChecked(checked);
		tableItem.setData(data);

		for (int i = 0; i < values.length; i++) {
			tableItem.setText(i, values[i]);
		}
		return tableItem;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param composite the composite
	 * @param style the style
	 * @param name the name
	 * @param headerVisible the header visible
	 * @param linesVisible the lines visible
	 * @return the tree
	 */
	public Tree createTree(Composite composite, int style, String name, boolean headerVisible, boolean linesVisible) {
		Tree tree = new Tree(composite, style);
		tree.setData(NAME, name);
		tree.setToolTipText(name);

		gridData = new GridData(GridData.FILL_BOTH);
		// gridData.heightHint = BTN_WIDTH;
		tree.setLayoutData(gridData);

		tree.setHeaderVisible(headerVisible);
		tree.setLinesVisible(linesVisible);
		// table.s

		tree.setForeground(new Color(Display.getDefault(), 50, 150, 50));
		tree.setFont(fontRegistry.get("button-text"));

		return tree;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param tree the tree
	 * @param style the style
	 * @param name the name
	 * @param width the width
	 * @return the tree column
	 */
	public TreeColumn createTreeColumn(Tree tree, int style, String name, int width) {
		TreeColumn treeColumn = new TreeColumn(tree, style);
		treeColumn.setToolTipText(name);
		treeColumn.setText(name);
		treeColumn.setWidth(width);
		return treeColumn;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param tree the tree
	 * @param style the style
	 * @param data the data
	 * @param text the text
	 * @return the tree item
	 */
	public TreeItem createTreeItem(Tree tree, int style, Object data, String... text) {
		TreeItem treeItem = new TreeItem(tree, style);
		// treeItem.setToolTipText(data.);
		treeItem.setData(data);
		treeItem.setText(text);

		return treeItem;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param item the item
	 * @param style the style
	 * @param data the data
	 * @param text the text
	 * @return the tree item
	 */
	public TreeItem createTreeItem(TreeItem item, int style, Object data, String... text) {

		TreeItem treeItem = new TreeItem(item, style);
		// treeItem.setToolTipText(name);
		treeItem.setData(data);
		treeItem.setText(text);

		return treeItem;

	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param widgetFactory the widget factory
	 * @param parent the parent
	 * @param name the name
	 * @return the text
	 */
	public Text createPropertyText(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent, String name) {

		Composite composite = new Composite(parent, SWT.SHADOW_OUT);

		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginTop = 0;
		gridLayout.marginBottom = 0;
		gridLayout.marginHeight = 0;
		composite.setLayout(gridLayout);

		gridData = new GridData();
		gridData.verticalSpan = 0;
		gridData.verticalIndent = 0;

		composite.setLayoutData(gridData);

		gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);

		CLabel label = widgetFactory.createCLabel(composite, name);
		gridData = new GridData();
		gridData.widthHint = 70;
		gridData.verticalSpan = 0;
		gridData.verticalIndent = 0;
		label.setLayoutData(gridData);

		Text text = widgetFactory.createText(composite, name);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.verticalSpan = 0;
		gridData.verticalIndent = 0;
		text.setLayoutData(gridData);

		return text;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param rect the rect
	 * @param span the span
	 * @return the slider
	 */
	public Slider createSlider(Composite parent, Rectangle rect, byte span) {
		final Slider slider = new Slider(parent, SWT.HORIZONTAL);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		if (span > 0) {
			gridData.horizontalSpan = span;
		} else {
			gridData.verticalSpan = span;
		}
		slider.setLayoutData(gridData);

		slider.setMinimum(0);
		slider.setMaximum(100);
		slider.setIncrement(1);
		slider.setPageIncrement(5);

		slider.setBounds(rect.x, rect.y, rect.width, rect.height);
		return slider;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param parent the parent
	 * @param rect the rect
	 * @param span the span
	 * @return the scale
	 */
	public Scale createScale(Composite parent, Rectangle rect, byte span) {
		final Scale scale = new Scale(parent, SWT.HORIZONTAL);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		if (span > 0) {
			gridData.horizontalSpan = span;
		} else {
			gridData.verticalSpan = span;
		}
		scale.setLayoutData(gridData);

		scale.setMinimum(0);
		scale.setMaximum(100);
		scale.setIncrement(1);
		scale.setPageIncrement(5);

		scale.setBounds(rect.x, rect.y, rect.width, rect.height);

		scale.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				int perspectiveValue = scale.getMaximum() - scale.getSelection() + scale.getMinimum();
				System.err.println("Vol: " + perspectiveValue);

				System.err.println("Vol X : " + scale.getSelection());
			}
		});
		return scale;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param widget the widget
	 */
	public static void createCalendar(final Widget widget) {
		final Shell dialog = new Shell(Display.getDefault(), SWT.NO_TRIM | SWT.ON_TOP | SWT.APPLICATION_MODAL);
		final DateTime dateTime = new DateTime(dialog, SWT.CALENDAR | SWT.BORDER);
		dialog.setLayout(new FillLayout());

		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());

		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Long time = DateUtils.dateTimeToTime(dateTime);
				widget.setData("time", time);
				String formattedTime = DateUtils.decodeTime(time, EDateFormat.NICE_1);
				if (time != null) {
					if (widget instanceof Text) {
						((Text) widget).setText(formattedTime);
					} else if (widget instanceof Label) {
						((Label) widget).setText(formattedTime);
					} else if (widget instanceof TreeItem) {
						((TreeItem) widget).setText(1, formattedTime);
					}
				}
				dialog.dispose();
			}
		});

		Point location = Display.getCurrent().getCursorLocation();
		dialog.setLocation(location.x, location.y + 20);
		dialog.pack();
		dialog.open();
	}

	// ---------------------------------------------------------------

	/**
	 * Creates a new GUI object.
	 * @param coreImagePath the core image path
	 * @return the image
	 */
	public Image createImage(String coreImagePath) {
		return Activator.getImageDescriptor(coreImagePath).createImage();

	}

	// ---------------------------------------------------------------

	/**
	 * Gets the shell.
	 * @return the shell
	 */
	public Shell getShell() {
		if (shell == null) {
			return Display.getDefault().getActiveShell();
		}
		return shell;
	}

}
