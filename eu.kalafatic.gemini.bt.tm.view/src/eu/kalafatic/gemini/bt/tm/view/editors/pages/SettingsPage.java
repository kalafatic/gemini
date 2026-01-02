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
package eu.kalafatic.gemini.bt.tm.view.editors.pages;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PIECE_LENGTH;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.SETUP;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TABLE_STYLE_1;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.osgi.framework.Version;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.adapters.TrackersTableContentAdapter;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.Tracker;
import eu.kalafatic.gemini.bt.tm.view.Activator;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.bt.utils.dialogs.TrackersTree;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.IFormInput;
import eu.kalafatic.gemini.core.lib.EDateFormat;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.lib.EHash;
import eu.kalafatic.gemini.core.models.ComboData;
import eu.kalafatic.gemini.core.utils.DateUtils;
import eu.kalafatic.gemini.core.utils.ValidationUtils;

/**
 * The Class class SettingsPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SettingsPage extends FormPage implements Listener, IFormInput {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The Constant ID. */
	public static final String ID = "SettingsPage";

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The grid data. */
	private GridData gridData;

	/** The label. */
	private Label label;

	/** The created by text. */
	private Text creationDateText, commentText, createdByText;

	/** The md5 btn. */
	private Button calendarBtn, editTrackersBtn, sha1Btn, md5Btn;

	/** The size combo. */
	private Combo encodingCombo, sizeCombo;

	/** The bt structure. */
	private BTStructure btStructure;

	/** The trackers table. */
	private Table trackersTable;

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new settings page.
	 * @param torrentEditor the torrent editor
	 */
	public SettingsPage(TorrentEditor torrentEditor) {
		super(torrentEditor, ID, "Settings");
		this.torrentEditor = torrentEditor;
		factory = new GUIFactory();
		extTorrent = torrentEditor.getExtTorrent();
		btStructure = BTStructureModelManager.getInstance().getBtStructure();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget == trackersTable) {
			if (event.detail == SWT.CHECK) {
				TableItem item = (TableItem) event.item;

				if (item.getChecked()) {
					extTorrent.getAnnounceList().add((String) item.getData());
				} else {
					extTorrent.getAnnounceList().remove(item.getData());
				}
			}
		} else if (event.widget == editTrackersBtn) {
			new TrackersTree(extTorrent).open();

		} else if (event.widget == calendarBtn) {
			GUIFactory.createCalendar(creationDateText);
			Object data = creationDateText.getData("time");
			if ((data != null) && (data instanceof Long)) {
				extTorrent.setCreationDate((Long) data);
				commentText.setFocus();
			}

		} else if (event.widget == sizeCombo) {
			int selectionIndex = sizeCombo.getSelectionIndex();
			extTorrent.getInfo().setPieceLength(1 << (4 + selectionIndex));

		} else if (event.widget == encodingCombo) {
			extTorrent.setEncoding(encodingCombo.getText());

		} else if (event.widget == commentText) {
			torrentEditor.setDirty(true);
			if (!commentText.getText().isEmpty()) {
				ValidationUtils.INSTANCE.showDirtyDecoration(commentText);
			}
		} else if (event.widget == sha1Btn) {
			sha1Btn.setSelection(true);
			md5Btn.setSelection(false);
			extTorrent.setEncoding(EHash.SHA_1.getLiteral());

		} else if (event.widget == md5Btn) {
			sha1Btn.setSelection(false);
			md5Btn.setSelection(true);
			extTorrent.setEncoding(EHash.MD_5.getLiteral());
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui .forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();

		form.setText("File Manager");
		form.setBackgroundImage(Activator.getDefault().getImage(Activator.IMG_FORM_BG));
		form.getBody().setLayout(new GridLayout(2, true));

		createTrackersSection(form, toolkit, "Trackers");
		createInfoSection(form, toolkit, "Info");

		createHashEncodingSection(form, toolkit, "Hash / Encoding");
		createSizesSection(form, toolkit, "Sizes");

		initListeners();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		// Z orientation
		// editTrackersBtn.addListener(SWT.Selection, this);
		//
		// calendarBtn.addListener(SWT.Selection, this);
		// commentText.addListener(SWT.Modify, this);
		//
		//
		// encodingCombo.addListener(SWT.Selection, this);
		//
		// sizeCombo.addListener(SWT.Selection, this);
		//
		// trackersTable.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the sizes section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createSizesSection(ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Torrent content sizes", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 1);

		toolkit.createLabel(client, PIECE_LENGTH, SWT.NORMAL);

		sizeCombo = new Combo(client, SWT.NULL);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = BTN_WIDTH;
		sizeCombo.setLayoutData(gridData);

		ComboData comboData = new ComboData(4, 2, true, 13, 21);
		sizeCombo.setItems(comboData.getItems());
		sizeCombo.setData("comboData", comboData);

		// 2^4,2^5,2^6,2^7,2^8,2^9,2^10,2^11,
		sizeCombo.select(comboData.defaultSelection);
		extTorrent.getInfo().setPieceLength(comboData.data.get(comboData.defaultSelection).getIntValue());
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the hash encoding section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createHashEncodingSection(ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Torrent hash/encoding", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 1);

		createHashButtons(toolkit, client);
		createEncoding(toolkit, client);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the hash buttons.
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	private void createHashButtons(FormToolkit toolkit, Composite client) {
		toolkit.createLabel(client, "Use hash : ", SWT.NORMAL);

		Composite btnComposite = toolkit.createComposite(client, SWT.NONE);
		btnComposite.setLayout(new GridLayout(2, false));

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		btnComposite.setLayoutData(gridData);

		sha1Btn = factory.createButton(btnComposite, EHash.SHA_1.getLiteral(), SWT.CHECK, BTN_WIDTH, true, this);
		md5Btn = factory.createButton(btnComposite, EHash.MD_5.getLiteral(), SWT.CHECK, BTN_WIDTH, false, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the encoding.
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	private void createEncoding(FormToolkit toolkit, Composite client) {
		toolkit.createLabel(client, "Use encoding :", SWT.NORMAL);

		encodingCombo = new Combo(client, SWT.READ_ONLY);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = BTN_WIDTH;
		encodingCombo.setLayoutData(gridData);

		encodingCombo.setItems(EEncoding.getValues());
		encodingCombo.select(0);
		encodingCombo.addListener(SWT.Selection, this);

		extTorrent.setEncoding(EEncoding.ISO_1.getLiteral());
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the trackers section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createTrackersSection(ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Trackers", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 1);

		trackersTable = factory.createTable(client, TABLE_STYLE_1, extTorrent, "Trackers", false, true);
		factory.createTableColumn(trackersTable, SWT.LEFT, "Trackers", 200);

		for (String announce : extTorrent.getAnnounceList()) {
			factory.createTableItem(trackersTable, true, announce);
		}

		Map<String, Tracker> trackerMap = BTStructureModelManager.getInstance().getBtStructure().getTrackersMap().map();

		for (Tracker tracker : trackerMap.values()) {
			String announce = tracker.getAnnounce();

			if (!extTorrent.getAnnounceList().contains(announce) && tracker.isChecked()) {
				factory.createTableItem(trackersTable, false, announce);
			}
		}
		trackersTable.addListener(SWT.Selection, this);

		ValidationUtils.INSTANCE.createReqControlDecorator(trackersTable).show();
		new TrackersTableContentAdapter(trackersTable);

		editTrackersBtn = factory.createButton(client, SETUP, SWT.PUSH);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		editTrackersBtn.setLayoutData(gridData);
		editTrackersBtn.addListener(SWT.Selection, this);

		// editTrackersBtn = factory.createButton(client, SETUP, SWT.PUSH,
		// this);
		//
		// editTrackersBtn = toolkit.createButton(client, SETUP, SWT.PUSH);
		// gridData = new GridData(GridData.HORIZONTAL_ALIGN_END
		// | GridData.VERTICAL_ALIGN_BEGINNING);
		// gridData.widthHint = BTN_WIDTH;
		// editTrackersBtn.setLayoutData(gridData);

	}

	// ---------------------------------------------------------------

	/**
	 * Creates the info section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createInfoSection(ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Torrent info", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 3, 1);

		toolkit.createLabel(client, "Created by", SWT.NORMAL);

		createdByText = toolkit.createText(client, "", SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		createdByText.setLayoutData(gridData);
		createdByText.setEnabled(false);

		String product = Platform.getProduct().getName();
		Version version = Version.parseVersion(Platform.getProduct().getDefiningBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION));
		createdByText.setText(product + " v" + version);

		extTorrent.setCreationBy(product + " v" + version);

		// ---

		toolkit.createLabel(client, "Creation date", SWT.NORMAL);

		creationDateText = toolkit.createText(client, "", SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		creationDateText.setLayoutData(gridData);

		String creationDate = DateUtils.decodeTime(System.currentTimeMillis(), EDateFormat.NICE_1);
		creationDateText.setText(creationDate);

		calendarBtn = factory.createButton(client, "Calendar", SWT.PUSH, this);

		extTorrent.setCreationDate(System.currentTimeMillis());

		// ---

		label = toolkit.createLabel(client, "Add comment", SWT.SHADOW_IN);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		label.setLayoutData(gridData);

		commentText = toolkit.createText(client, "", SWT.MULTI | SWT.H_SCROLL | SWT.VERTICAL | SWT.BORDER);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 50;
		gridData.horizontalSpan = 2;
		commentText.setLayoutData(gridData);

		extTorrent.setComment("");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.view.interfaces.IFormInput#setInput()
	 */
	@Override
	public boolean setInput() {
		// you should remove all trackers ?
		if (trackersTable == null) {
			if (btStructure.getTrackersMap().isEmpty()) {
				ValidationUtils.INSTANCE.showDecoration(trackersTable, IStatus.ERROR, "At least one tracker required");
				return false;
			}
		} else if (trackersTable.getItemCount() == 0) {
			ValidationUtils.INSTANCE.showDecoration(trackersTable, IStatus.ERROR, "At least one tracker required");
			return false;
		}
		// optional dirty/setup
		if ((trackersTable != null) && (!commentText.getText().isEmpty())) {
			extTorrent.setComment(commentText.getText());
		}
		return true;
	}
}