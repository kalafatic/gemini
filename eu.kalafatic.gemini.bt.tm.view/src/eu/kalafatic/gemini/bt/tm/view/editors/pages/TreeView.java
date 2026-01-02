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

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.COMMENT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CREATOR_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.DATE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ENCODING_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FOLDER_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.INFO_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.LOCK_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.PIECES_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.SIZE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TORRENT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.URL_IMG;

import java.io.File;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.model.torrents.Info;
import eu.kalafatic.gemini.bt.tm.view.editors.TorrentEditor;
import eu.kalafatic.gemini.bt.tm.view.interfaces.IView;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.utils.DialogUtils;

/**
 * The Class class TreeView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TreeView implements IView {

	/** The Constant ID_WIDTH. */
	protected static final int ID_WIDTH = 150;

	/** The Constant VALUE_WIDTH. */
	protected static final int VALUE_WIDTH = 100;

	/** The torrent editor. */
	private TorrentEditor torrentEditor;

	/** The tree. */
	private Tree tree;

	/** The id col. */
	private TreeColumn valCol, idCol;

	/** The creation date item. */
	private TreeItem rootItem, fileItem, announceItem, privateItem, piecesItem, infoItem, pieceLengthItem, commentItem, encodingItem, createdByItem, creationDateItem;

	/** The tree editor. */
	private TreeEditor treeEditor;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/**
	 * Instantiates a new tree view.
	 * @param torrentEditor the torrent editor
	 */
	public TreeView(TorrentEditor torrentEditor) {
		this.torrentEditor = torrentEditor;

		extTorrent = torrentEditor.getExtTorrent();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Creates the content.
	 * @param form the form
	 * @param toolkit the toolkit
	 * @return the tree view
	 */
	public TreeView createContent(ScrolledForm form, FormToolkit toolkit) {
		Section section = torrentEditor.getSectionFactory().createSection(form, toolkit, "Torrent content tree", "Torrent content files", true);
		Composite client = torrentEditor.getSectionFactory().createClient(toolkit, section, 1, 1);

		createTree(client);

		return this;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tree.
	 * @param composite the composite
	 */
	private void createTree(Composite composite) {
		tree = new Tree(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		initColumns();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		setTree();
		setInput(torrentEditor.getExtTorrent());
		rootItem.setExpanded(true);
		infoItem.setExpanded(true);
		tree.update();

		initListeners();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent event) {
				Control old = treeEditor.getEditor();
				if (old != null) {
					old.dispose();
				}

				Point pt = new Point(event.x, event.y);

				final TreeItem item = tree.getItem(pt);
				if (item == null) {
					return;
				}
				int column = -1;
				for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
					Rectangle rect = item.getBounds(i);
					if (rect.contains(pt)) {
						column = i;
						break;
					}
				}

				if (column != 1) {
					return;
				}
				if (item.equals(creationDateItem)) {
					GUIFactory.createCalendar(creationDateItem);
					extTorrent.setCreationDate((Long) creationDateItem.getData("time"));

				} else if (item.equals(encodingItem)) {
					createCombo(item, EEncoding.getValues());
				} else if (item.equals(commentItem)) {
					createText(item);
				}

			}

		});

		tree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.keyCode == SWT.F2 && tree.getSelectionCount() == 1) {
					final TreeItem item = tree.getSelection()[0];

					int rc = DialogUtils.INSTANCE.question("Message");
					item.setText(1, rc + "");
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.view.interfaces.IView#setInput(eu.kalafatic .gemini.bt.client.model.torrents.ExtTorrent)
	 */
	@Override
	public void setInput(ExtTorrent extTorrent) {
		long creationDate = extTorrent.getCreationDate();
		String date = creationDate > 0 ? new SimpleDateFormat().format(extTorrent.getCreationDate()) : "";

		creationDateItem.setText(1, date);
		createdByItem.setText(1, extTorrent.getCreationBy() == null ? "" : extTorrent.getCreationBy());
		encodingItem.setText(1, extTorrent.getEncoding() == null ? "" : extTorrent.getEncoding());
		commentItem.setText(1, extTorrent.getComment() == null ? "" : extTorrent.getComment());

		Info info = extTorrent.getInfo();
		pieceLengthItem.setText(1, info.getPieceLength() > 0 ? Integer.toString(info.getPieceLength()) : "");
		piecesItem.setText(1, info.getPieces() != null ? Integer.toString(info.getPieces().length) : "");
		privateItem.setText(1, info.isPrivate() ? "Yes" : "No");

		setDynamicItems(extTorrent);

		rootItem.setExpanded(true);
		infoItem.setExpanded(true);
		fileItem.setExpanded(true);
		announceItem.setExpanded(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the dynamic items.
	 * @param extTorrent the new dynamic items
	 */
	private void setDynamicItems(ExtTorrent extTorrent) {
		try {
			setAnnounce(extTorrent);
			setFiles(extTorrent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the files.
	 * @param extTorrent the new files
	 * @throws Exception the exception
	 */
	private void setFiles(ExtTorrent extTorrent) throws Exception {
		fileItem = new TreeItem(infoItem, SWT.NONE);
		fileItem.setImage(FOLDER_IMG);

		if (extTorrent.getInfo().getFiles().size() > 1) {
			fileItem.setText(0, "Multiple File Mode");

			TreeItem item1 = new TreeItem(fileItem, SWT.NONE);
			item1.setImage(FILE_IMG);
			item1.setText(0, "Name");
			item1.setText(1, new File(extTorrent.getPath()).getParent());

			for (DataFile dataFile : extTorrent.getInfo().getFiles()) {
				TreeItem item2 = new TreeItem(fileItem, SWT.NONE);
				item2.setImage(FOLDER_IMG);
				item2.setText(0, "File");

				TreeItem item = new TreeItem(item2, SWT.NONE);
				item.setImage(SIZE_IMG);
				item.setText(0, "Length");
				item.setText(1, Long.toString(dataFile.getLength()));

				item = new TreeItem(item2, SWT.NONE);
				item.setImage(FILE_IMG);
				item.setText(0, "MD5SUM");
				item.setText(1, dataFile.getMd5sum() == null ? "" : dataFile.getMd5sum());

				item = new TreeItem(item2, SWT.NONE);
				item.setImage(FILE_IMG);
				item.setText(0, "Path");
				item.setText(1, dataFile.getPath());
			}
		} else if (extTorrent.getInfo().getFiles().size() == 1) {
			DataFile dataFile = extTorrent.getInfo().getFiles().get(0);

			fileItem.setText(0, "Single File Mode");

			TreeItem item = new TreeItem(fileItem, SWT.NONE);
			item.setImage(FILE_IMG);
			item.setText(0, "Name");
			item.setText(1, dataFile.getName());

			item = new TreeItem(fileItem, SWT.NONE);
			item.setImage(SIZE_IMG);
			item.setText(0, "Length");
			item.setText(1, Long.toString(dataFile.getLength()));

			item = new TreeItem(fileItem, SWT.NONE);
			item.setImage(FILE_IMG);
			item.setText(0, "MD5SUM");
			item.setText(1, dataFile.getMd5sum() == null ? "" : dataFile.getMd5sum());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the announce.
	 * @param extTorrent the new announce
	 * @throws Exception the exception
	 */
	private void setAnnounce(ExtTorrent extTorrent) throws Exception {
		announceItem = new TreeItem(rootItem, SWT.NONE);

		if (extTorrent.getAnnounceList().size() > 1) {
			announceItem.setText(0, "Announce-list");
			announceItem.setImage(FOLDER_IMG);

			for (String announce : extTorrent.getAnnounceList()) {
				TreeItem item = new TreeItem(announceItem, SWT.NONE);
				item.setImage(URL_IMG);
				item.setText(0, "Announce");
				item.setText(1, announce);
			}
		} else if (extTorrent.getAnnounceList().size() == 1) {
			announceItem.setImage(URL_IMG);
			announceItem.setText(0, "Announce");
			announceItem.setText(1, extTorrent.getAnnounceList().get(0));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the tree.
	 */
	public void setTree() {
		rootItem = new TreeItem(tree, SWT.NONE);
		rootItem.setImage(TORRENT_IMG);
		rootItem.setText(0, "Torrent");

		creationDateItem = new TreeItem(rootItem, SWT.NONE);
		creationDateItem.setImage(DATE_IMG);
		creationDateItem.setText(0, "Creation Date");

		createdByItem = new TreeItem(rootItem, SWT.NONE);
		createdByItem.setImage(CREATOR_IMG);
		createdByItem.setText(0, "Created by");

		encodingItem = new TreeItem(rootItem, SWT.NONE);
		encodingItem.setImage(ENCODING_IMG);
		encodingItem.setText(0, "Encoding");

		commentItem = new TreeItem(rootItem, SWT.NONE);
		commentItem.setImage(COMMENT_IMG);
		commentItem.setText(0, "Comment");

		infoItem = new TreeItem(rootItem, SWT.NONE);
		infoItem.setImage(INFO_IMG);
		infoItem.setText(0, "Info");

		pieceLengthItem = new TreeItem(infoItem, SWT.NONE);
		pieceLengthItem.setImage(PIECES_IMG);
		pieceLengthItem.setText(0, "Piece Length");

		piecesItem = new TreeItem(infoItem, SWT.NONE);
		piecesItem.setImage(SIZE_IMG);
		piecesItem.setText(0, "Pieces");

		privateItem = new TreeItem(infoItem, SWT.NONE);
		privateItem.setImage(LOCK_IMG);
		privateItem.setText(0, "Private");

		treeEditor = new TreeEditor(tree);
		treeEditor.horizontalAlignment = SWT.LEFT;
		treeEditor.grabHorizontal = true;
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the columns.
	 */
	private void initColumns() {
		idCol = new TreeColumn(tree, SWT.LEFT);
		idCol.setText("Protocol ID");
		idCol.setWidth(ID_WIDTH);
		valCol = new TreeColumn(tree, SWT.LEFT);
		valCol.setText("Value");
		valCol.setWidth(VALUE_WIDTH);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the text.
	 * @param item the item
	 */
	private void createText(final TreeItem item) {
		final Text text = new Text(tree, SWT.SIMPLE);

		tree.getColumn(1).setWidth(VALUE_WIDTH);

		text.setFocus();
		treeEditor.setEditor(text, item, 1);

		text.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				item.setText(1, text.getText());
				text.dispose();
			}

			@Override
			public void focusGained(FocusEvent e) {}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the combo.
	 * @param item the item
	 * @param values the values
	 */
	private void createCombo(final TreeItem item, String... values) {
		final CCombo combo = new CCombo(tree, SWT.READ_ONLY);

		for (int i = 0; i < values.length; i++) {
			combo.add(values[i]);
		}

		combo.select(combo.indexOf(item.getText(1)));
		tree.getColumn(1).setWidth(VALUE_WIDTH);

		combo.setFocus();
		treeEditor.setEditor(combo, item, 1);

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				item.setText(1, combo.getText());
				combo.dispose();
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the tree.
	 * @return the tree
	 */
	public Tree getTree() {
		return tree;
	}
}
