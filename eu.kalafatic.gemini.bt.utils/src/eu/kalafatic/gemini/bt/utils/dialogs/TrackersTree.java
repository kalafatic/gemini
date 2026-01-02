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
package eu.kalafatic.gemini.bt.utils.dialogs;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.ADD;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.EDIT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.REMOVE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.DIALOG_HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.DIALOG_WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TABLE_STYLE_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.utils.decoders.TEditor;
import eu.kalafatic.gemini.bt.utils.utils.BTUtils;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class TrackersTree.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackersTree extends TitleAreaDialog implements Listener {

	/** The tree. */
	private Tree tree;

	/** The remove btn. */
	private Button addBtn, editBtn, removeBtn;

	/** The selection. */
	private List<ExtTorrent> selection;

	/** The factory. */
	private GUIFactory factory;

	/** The announces. */
	private Set<String> announces;

	/**
	 * Instantiates a new trackers tree.
	 */
	public TrackersTree() {
		super(new Shell(Display.getDefault()));
		factory = new GUIFactory();
	}

	/**
	 * Instantiates a new trackers tree.
	 * @param selectedTorrents the selected torrents
	 */
	public TrackersTree(ExtTorrent... selectedTorrents) {
		this(Arrays.asList(selectedTorrents));
	}

	/**
	 * Instantiates a new trackers tree.
	 * @param selectedTorrents the selected torrents
	 */
	public TrackersTree(List<ExtTorrent> selectedTorrents) {
		this();
		this.selection = selectedTorrents;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget == addBtn) {
			addTracker(new String[] { "", "" });
		} else if (event.widget == editBtn) {
			editTracker(tree.getSelection()[0]);
		} else if (event.widget == removeBtn) {
			removeTrackers();

		} else if (event.item instanceof TreeItem) {
			TreeItem treeItem = (TreeItem) event.item;

			if (event.detail == SWT.CHECK) {
				TreeItem[] items = treeItem.getItems();

				for (TreeItem item : items) {
					item.setChecked(treeItem.getChecked());
				}
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse. swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		parent.getShell().setText("Select trackers");
		setMessage("You can add, edit or remove trackers", IMessageProvider.INFORMATION);
		return contents;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialog = (Composite) super.createDialogArea(parent);
		dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		Point cursorLocation = Display.getCurrent().getCursorLocation();
		dialog.getShell().setLocation(new Point(cursorLocation.x - DIALOG_WIDTH, cursorLocation.y));

		Composite composite = factory.createComposite(dialog, 2);

		createTrackersTree(composite);
		createButtons(composite);

		initListeners();

		return dialog;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the trackers tree.
	 * @param parent the parent
	 */
	private void createTrackersTree(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NATIVE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		tree = factory.createTree(composite, TABLE_STYLE_1, "Tracker table", true, true);

		factory.createTreeColumn(tree, SWT.CENTER, "Torrent", 70);
		factory.createTreeColumn(tree, SWT.LEFT, "Address", 350);

		announces = new HashSet<String>();

		for (ExtTorrent extTorrent : selection) {
			TreeItem torrentItem = factory.createTreeItem(tree, SWT.CHECK, extTorrent, new String[] { extTorrent.getName(), "" });

			for (String announce : extTorrent.getAnnounceList()) {
				announces.add(announce);

				factory.createTreeItem(torrentItem, SWT.CHECK, announce, new String[] { "", announce });
			}
			torrentItem.setExpanded(true);
		}
		tree.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the buttons.
	 * @param parent the parent
	 */
	private void createButtons(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NATIVE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		addBtn = factory.createButton(composite, ADD, SWT.BORDER | SWT.PUSH);
		editBtn = factory.createButton(composite, EDIT, SWT.BORDER | SWT.PUSH);
		removeBtn = factory.createButton(composite, REMOVE, SWT.BORDER | SWT.PUSH);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		addBtn.addListener(SWT.Selection, this);
		editBtn.addListener(SWT.Selection, this);
		removeBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the tracker.
	 * @param request the request
	 */

	private void addTracker(String[] request) {
		EditTrackerDialog editTrackerDialog = new EditTrackerDialog(super.getParentShell(), request);

		if (editTrackerDialog.open() == Window.OK) {
			String address = request[0];
			String port = request[1];

			String announce = BTUtils.INSTANCE.parse(address, port);

			if (announce == null) {
				DialogUtils.INSTANCE.warn("Can not read announce");
				return;
			}
			addTracker(announce);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Adds the tracker.
	 * @param announce the announce
	 */
	private void addTracker(String announce) {
		announces.add(announce);

		TreeItem[] items = tree.getSelection();

		for (TreeItem treeItem : items) {
			ExtTorrent extTorrent = (ExtTorrent) treeItem.getData();

			if (!extTorrent.getAnnounceList().contains(announce)) {
				extTorrent.getAnnounceList().add(announce);
				TEditor.INSTANCE.editTrackers(extTorrent);

				factory.createTreeItem(treeItem, SWT.CHECK, announce, new String[] { "", announce });
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Edits the tracker.
	 * @param treeItem the tree item
	 */

	private void editTracker(TreeItem treeItem) {
		if (treeItem.getData() instanceof String) {
			String announce = (String) treeItem.getData();
			ExtTorrent extTorrent = (ExtTorrent) treeItem.getParentItem().getData();

			String[] request = BTUtils.INSTANCE.parse(announce);

			EditTrackerDialog editTrackerDialog = new EditTrackerDialog(super.getParentShell(), request);

			if (editTrackerDialog.open() == Window.OK) {
				String address = request[0];
				String port = request[1];

				String editedAnnounce = BTUtils.INSTANCE.parse(address, port);

				if (editedAnnounce == null) {
					DialogUtils.INSTANCE.warn("Can not read announce");
					return;
				}
				treeItem.setText(1, editedAnnounce);
				treeItem.setData(editedAnnounce);

				extTorrent.getAnnounceList().remove(announce);
				extTorrent.getAnnounceList().add(editedAnnounce);

				TEditor.INSTANCE.editTrackers(extTorrent);
			}
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Removes the trackers.
	 */
	private void removeTrackers() {
		TreeItem[] torrentItems = tree.getItems();

		for (TreeItem torrentItem : torrentItems) {
			ExtTorrent extTorrent = (ExtTorrent) torrentItem.getData();

			TreeItem[] trackerItems = torrentItem.getItems();

			for (TreeItem trackerItem : trackerItems) {
				if (trackerItem.getChecked()) {
					extTorrent.getAnnounceList().remove(trackerItem.getData());
					trackerItem.dispose();
				}
			}
			TEditor.INSTANCE.editTrackers(extTorrent);
		}
	}
}
