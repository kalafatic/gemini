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

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.BROWSE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.NEW;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BTN_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.LABEL_WIDTH;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.osgi.service.prefs.Preferences;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.tm.controller.actions.ModelActions;
import eu.kalafatic.gemini.bt.tm.controller.lib.ETMPreferences;
import eu.kalafatic.gemini.bt.tm.controller.model.BTStructureModelManager;
import eu.kalafatic.gemini.bt.tm.model.btStructure.BTStructure;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;
import eu.kalafatic.gemini.bt.tm.view.Activator;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.bt.utils.decoders.TDecoder;
import eu.kalafatic.gemini.core.factories.CommandFactory;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ATreeViewer;
import eu.kalafatic.gemini.core.interfaces.IFormInput;
import eu.kalafatic.gemini.core.interfaces.IViewer;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.lib.EHandler;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.core.utils.ValidationUtils;
import eu.kalafatic.gemini.core.widgets.SearchBox;

/**
 * The Class class FileManagerPage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class FileManagerPage extends FormPage implements Listener, IFormInput {

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The Constant ID. */
	public static final String ID = "FileManagerPage";

	/** The target viewer. */
	private TargetViewer targetViewer;

	/** The bt structure. */
	private BTStructure btStructure;

	/** The preferences. */
	private Preferences preferences;

	/** The grid data. */
	private GridData gridData;

	/** The grid layout. */
	private GridLayout gridLayout;

	/** The del btn. */
	private Button folderBtn, fileBtn, delBtn;

	/** The web sync btn. */
	private Button clientSyncBtn, trackerSyncBtn, webSyncBtn;

	/** The dest text. */
	private Text destText;

	/** The generate torrent btn. */
	private Button browseDestBtn, newBtn, generateTorrentBtn;

	/** The saved torrents combo. */
	private Combo savedTorrentsCombo;

	/** The factory. */
	private GUIFactory factory;

	/**
	 * Instantiates a new file manager page.
	 * @param torrentEditor the torrent editor
	 */
	public FileManagerPage(TorrentEditor torrentEditor) {
		super(torrentEditor, ID, "Files");
		this.torrentEditor = torrentEditor;
		init();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the.
	 */
	private void init() {
		factory = new GUIFactory();
		preferences = eu.kalafatic.gemini.bt.tm.controller.Activator.getPreferences();
		btStructure = BTStructureModelManager.getInstance().getBtStructure();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget == folderBtn) {
			String path = FileUtils.getInstance().openFile(false);
			if (path != null) {
				ModelActions.getInstance().addFileToModel(torrentEditor.getExtTorrent(), targetViewer.getViewer(), path);
			}
		} else if (event.widget == fileBtn) {
			String path = FileUtils.getInstance().openFile(true);
			if (path != null) {
				ModelActions.getInstance().addFileToModel(torrentEditor.getExtTorrent(), targetViewer.getViewer(), path);
			}
		} else if (event.widget == savedTorrentsCombo) {
			String selection = savedTorrentsCombo.getItem(savedTorrentsCombo.getSelectionIndex());
			setNewInput(selection);

		} else if (event.widget == delBtn) {
			removeFileTarget(targetViewer.getTree().getSelection());
		} else if (event.widget == newBtn) {
			createNewTorrent();
		} else if (event.widget == browseDestBtn) {
			openDestinationDialog();
		} else if (event.widget == generateTorrentBtn) {
			generateTorrent();
		} else if (event.widget == clientSyncBtn) {
			preferences.putBoolean(ETMPreferences.SYNC_CLIENT.getName(), clientSyncBtn.getSelection());
		} else if (event.widget == trackerSyncBtn) {
			preferences.putBoolean(ETMPreferences.SYNC_TRACKER.getName(), trackerSyncBtn.getSelection());
		} else if (event.widget == webSyncBtn) {
			preferences.putBoolean(ETMPreferences.SYNC_WEB.getName(), webSyncBtn.getSelection());
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui .forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();

		form.setText("File Manager");
		form.setBackgroundImage(Activator.getDefault().getImage(Activator.IMG_FORM_BG));
		form.getBody().setLayout(new GridLayout(2, true));

		createSourceSection(form, toolkit, "Source Files");
		createTargetSection(form, toolkit, "Target Files");
		createActiontSection(form, toolkit, "Actions");
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the source section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createSourceSection(final ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Use Drag and Drop or Add dialog", true);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 2;
		section.setLayoutData(gridData);

		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 2);

		Composite composite = toolkit.createComposite(client);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		SearchBox searchBox = new SearchBox(composite, SWT.BORDER);
		IViewer viewer = new SourceViewer(torrentEditor);
		((ATreeViewer) viewer).createPartControl(composite);
		searchBox.createControl(viewer, File.listRoots());

		composite = toolkit.createComposite(client);
		composite.setLayout(new GridLayout());

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.verticalAlignment = SWT.BEGINNING;
		composite.setLayoutData(gridData);

		folderBtn = factory.createButton(composite, "Add folder", SWT.PUSH, this);
		fileBtn = factory.createButton(composite, "Add file", SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the target section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createTargetSection(final ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Torrent content files", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 1);

		createSavedTorrentsCombo(toolkit, client);

		Composite composite = toolkit.createComposite(client);
		gridLayout = new GridLayout();
		composite.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);

		targetViewer = new TargetViewer(torrentEditor).createPartControl(composite);

		composite = toolkit.createComposite(client);
		gridLayout = new GridLayout();
		composite.setLayout(gridLayout);

		gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.verticalAlignment = SWT.BEGINNING;
		composite.setLayoutData(gridData);

		delBtn = factory.createButton(composite, REMOVE, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the saved torrents combo.
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	private void createSavedTorrentsCombo(FormToolkit toolkit, Composite client) {
		Composite composite = toolkit.createComposite(client);
		gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 15;
		composite.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);

		savedTorrentsCombo = new Combo(composite, SWT.READ_ONLY);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		savedTorrentsCombo.setText("Saved torrents");
		savedTorrentsCombo.setLayoutData(gridData);

		Set<String> keySet = BTStructureModelManager.getInstance().getBtStructure().getTreeObjects().keySet();
		savedTorrentsCombo.setItems(keySet.toArray(new String[keySet.size()]));
		savedTorrentsCombo.addListener(SWT.Selection, this);

		newBtn = factory.createButton(composite, NEW, SWT.PUSH, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the actiont section.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @param title the title
	 */
	private void createActiontSection(ScrolledForm form, FormToolkit toolkit, String title) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, title, "Torrent creation options", true);

		gridData = new GridData(GridData.FILL_BOTH | GridData.END);
		gridData.heightHint = 100;
		section.setLayoutData(gridData);

		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 2, 1);

		gridData = new GridData(GridData.FILL_BOTH | GridData.END);
		gridData.heightHint = 100;
		client.setLayoutData(gridData);

		Composite composite = toolkit.createComposite(client);
		gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);

		destText = factory.createText(composite, "Select torrent destination", false);
		ValidationUtils.INSTANCE.createReqControlDecorator(destText).show();

		browseDestBtn = factory.createButton(composite, BROWSE, SWT.PUSH, this);

		createActionButtons(toolkit, client);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the action buttons.
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	private void createActionButtons(FormToolkit toolkit, Composite client) {
		Composite btnComposite = toolkit.createComposite(client, SWT.NONE);
		btnComposite.setLayout(new GridLayout(1, false));
		btnComposite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		boolean selection = preferences.getBoolean(ETMPreferences.SYNC_CLIENT.getName(), (Boolean) ETMPreferences.SYNC_CLIENT.getValue());

		clientSyncBtn = factory.createButton(btnComposite, ETMPreferences.SYNC_CLIENT.getName(), SWT.CHECK, LABEL_WIDTH, selection, this);

		selection = preferences.getBoolean(ETMPreferences.SYNC_TRACKER.getName(), (Boolean) ETMPreferences.SYNC_TRACKER.getValue());
		trackerSyncBtn = factory.createButton(btnComposite, ETMPreferences.SYNC_TRACKER.getName(), SWT.CHECK, LABEL_WIDTH, selection, this);

		selection = preferences.getBoolean(ETMPreferences.SYNC_WEB.getName(), (Boolean) ETMPreferences.SYNC_WEB.getValue());
		webSyncBtn = factory.createButton(btnComposite, ETMPreferences.SYNC_WEB.getName(), SWT.CHECK, LABEL_WIDTH, selection, this);

		generateTorrentBtn = factory.createButton(client, "Generate", SWT.PUSH);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_END);
		gridData.widthHint = BTN_WIDTH;
		gridData.heightHint = BTN_HEIGHT;
		generateTorrentBtn.setLayoutData(gridData);
		generateTorrentBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the file target.
	 * @param treeItems the tree items
	 */
	private void removeFileTarget(TreeItem[] treeItems) {
		if (treeItems == null || treeItems.length == 0) {
			DialogUtils.INSTANCE.error("Nothing selected");
		} else {
			for (int i = 0; i < treeItems.length; i++) {
				if (treeItems[i].getData() instanceof FileTreeObject) {
					FileTreeObject fileTreeObject = (FileTreeObject) treeItems[i].getData();
					ModelActions.getInstance().removeFileFromModel(torrentEditor.getExtTorrent(), targetViewer.getViewer(), fileTreeObject);
					treeItems[i].dispose();
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the new torrent.
	 */
	private void createNewTorrent() {
		try {
			FileDialog fileDialog = new FileDialog(getSite().getShell());
			String torrentPath = fileDialog.open();
			if (torrentPath != null) {
				ExtTorrent extTorrent = BTStructureModelManager.getInstance().createNewTorrent(torrentPath, false);
				setNewInput(extTorrent.getName());
			}
		} catch (IOException e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the new input.
	 * @param selection the new new input
	 */
	private void setNewInput(String selection) {
		FileTreeObject fileTreeObject = btStructure.getTreeObjects().get(selection);
		ExtTorrent extTorrent = btStructure.getNewTorrents().get(selection);

		torrentEditor.init(extTorrent);
		targetViewer.setInput(fileTreeObject);

		Set<String> keySet = BTStructureModelManager.getInstance().getBtStructure().getTreeObjects().keySet();
		if (!keySet.contains(selection)) {
			savedTorrentsCombo.add(selection);
		}
		for (int i = 0; i < savedTorrentsCombo.getItems().length; i++) {
			if (savedTorrentsCombo.getItems()[i].equals(selection)) {
				savedTorrentsCombo.select(i);
			}
		}
		destText.setText(extTorrent.getPath());
	}

	// ---------------------------------------------------------------

	/**
	 * Open destination dialog.
	 */
	private void openDestinationDialog() {
		try {
			FileDialog fileDialog = new FileDialog(getSite().getShell());
			File destFile = new File(torrentEditor.getExtTorrent().getPath());
			fileDialog.setFilterPath(destFile.getParent());
			fileDialog.setFilterExtensions(new String[] { "*" + EExt.TORRENT.ext, "*.*" });
			String torrentPath = fileDialog.open();

			if (torrentPath != null && torrentPath.endsWith(EExt.TORRENT.ext)) {
				destText.setText(torrentPath);
				torrentEditor.getExtTorrent().setPath(torrentPath);
				String name = new File(torrentPath).getName();
				name = name.substring(0, name.lastIndexOf('.'));
				torrentEditor.getExtTorrent().setName(name);

				if (btStructure.getNewTorrents().containsKey(name)) {
					setNewInput(name);
				} else {
					ExtTorrent extTorrent = TDecoder.INSTANCE.decode(new File(torrentPath), true);
					btStructure.getNewTorrents().put(name, extTorrent);
					BTStructureModelManager.getInstance().setTreeObject(extTorrent);

					setNewInput(extTorrent.getName());
				}
			}
		} catch (Exception e) {
			Log.log(ETMPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Generate torrent.
	 */
	private void generateTorrent() {
		ExtTorrent extTorrent = torrentEditor.getExtTorrent();

		if (torrentEditor.setInput()) {
			CommandFactory.INSTANCE.executeCommand(EHandler.CREATE_TORRENT.ID, extTorrent.getName());
			torrentEditor.setDirty(false);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.view.interfaces.IFormInput#setInput()
	 */
	@Override
	public boolean setInput() {
		if (destText.getText().isEmpty()) {
			return false;
		}
		torrentEditor.getExtTorrent().setPath(destText.getText());
		return true;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the target viewer.
	 * @return the target viewer
	 */
	public TargetViewer getTargetViewer() {
		return targetViewer;
	}
}
