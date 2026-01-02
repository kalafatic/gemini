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
package eu.kalafatic.gemini.bt.client.view.views;

import static eu.kalafatic.gemini.core.lib.constants.FTableConstants.VIEWER_DATA_TYPE;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.BLACK;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.RED;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

import eu.kalafatic.gemini.bt.client.controller.adapters.TorrentDetailContentAdapter;
import eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable;
import eu.kalafatic.gemini.bt.client.controller.providers.FilesTableContentProvider;
import eu.kalafatic.gemini.bt.client.controller.providers.FilesTableLabelProvider;
import eu.kalafatic.gemini.bt.client.model.torrents.DataFile;
import eu.kalafatic.gemini.bt.client.model.torrents.ExtTorrent;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.SwarmSession;
import eu.kalafatic.gemini.core.interfaces.AViewer;
import eu.kalafatic.gemini.core.utils.ModelUtils;

/**
 * The Class class TorrentDetailFilesView.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TorrentDetailFilesView extends AViewer implements ITable {

	/** The site. */
	IWorkbenchPartSite site;

	/** The viewer. */
	private TableViewer viewer;

	/** The table. */
	private Table table;

	/** The ext torrent. */
	private ExtTorrent extTorrent;

	/** The swarm session. */
	private SwarmSession swarmSession;

	/** The files table label provider. */
	private FilesTableLabelProvider filesTableLabelProvider;

	/**
	 * Instantiates a new torrent detail files view.
	 * @param site the site
	 * @param extTorrent the ext torrent
	 * @param swarmSession the swarm session
	 */
	public TorrentDetailFilesView(IWorkbenchPartSite site, ExtTorrent extTorrent, SwarmSession swarmSession) {
		this.site = site;
		this.extTorrent = extTorrent;
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		this.viewer = new TableViewer(parent, SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		viewer.setData(VIEWER_DATA_TYPE, "TorrentDetailFilesView");

		filesTableLabelProvider = new FilesTableLabelProvider(extTorrent, swarmSession);
		viewer.setContentProvider(new FilesTableContentProvider());
		viewer.setLabelProvider(filesTableLabelProvider);
		viewer.setUseHashlookup(true);

		table = viewer.getTable();

		initColumns();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setInput(extTorrent.getInfo().getFiles());

		initListeners();
		initAdapters();
		hookContextMenu();

		site.setSelectionProvider(viewer);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initListeners()
	 */
	@Override
	public void initListeners() {
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.CHECK:
					if (event.item instanceof TableItem) {
						TableItem item = (TableItem) event.item;
						DataFile dataFile = (DataFile) item.getData();

						dataFile.setEnabled(item.getChecked());
						ModelUtils.doSave(dataFile);

						item.setForeground(item.getChecked() ? BLACK : RED);
					}
					break;
				default:
					break;
				}
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.stateMask == SWT.CTRL && e.keyCode == 97) {
					table.selectAll();
					return;
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the adapters.
	 */

	private void initAdapters() {
		NetworkModelManager.getInstance().getClientNetwork().eAdapters().add(new TorrentDetailContentAdapter(viewer));
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#initColumns()
	 */
	@Override
	public void initColumns() {
		TableColumn tc0 = new TableColumn(table, SWT.LEFT);
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table, SWT.LEFT);
		TableColumn tc3 = new TableColumn(table, SWT.LEFT);
		TableColumn tc4 = new TableColumn(table, SWT.LEFT);
		TableColumn tc5 = new TableColumn(table, SWT.LEFT);

		tc0.setText("Enabled");
		tc1.setText("File Name");
		tc2.setText("Path");
		tc3.setText("Length");
		tc4.setText("MD5Sum");
		tc5.setText("Pieces");

		tc0.setWidth(50);
		// tc0.pack();
		tc1.setWidth(100);
		tc2.setWidth(100);
		tc3.setWidth(50);
		tc4.setWidth(58);
		tc5.setWidth(50);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IViewer#hookContextMenu()
	 */
	@Override
	public void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {}
		});
		Menu menu = menuMgr.createContextMenu(table);
		table.setMenu(menu);
		site.registerContextMenu(menuMgr, viewer);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.controller.interfaces.IViewer#getViewer ()
	 */
	@Override
	public Viewer getViewer() {
		return viewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable#getExtTorrent ()
	 */
	@Override
	public ExtTorrent getExtTorrent() {
		return extTorrent;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the ext torrent.
	 * @param extTorrent the new ext torrent
	 */
	public void setExtTorrent(ExtTorrent extTorrent) {
		this.extTorrent = extTorrent;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.controller.lib.interfaces.ITable#getTable()
	 */
	@Override
	public Table getTable() {
		return table;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the files table label provider.
	 * @return the files table label provider
	 */
	public FilesTableLabelProvider getFilesTableLabelProvider() {
		return filesTableLabelProvider;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the swarm session.
	 * @param swarmSession the new swarm session
	 */
	public void setSwarmSession(SwarmSession swarmSession) {
		this.swarmSession = swarmSession;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui. IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	protected void fillLocalToolBar(IToolBarManager toolBarManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillLocalPullDown(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillLocalPullDown(IMenuManager menuManager) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.AViewer#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		// TODO Auto-generated method stub

	}
}
