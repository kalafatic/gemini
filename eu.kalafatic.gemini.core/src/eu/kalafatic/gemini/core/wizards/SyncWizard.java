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
package eu.kalafatic.gemini.core.wizards;

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.HEIGHT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.TITLE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.WIDTH;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.TABLE_STYLE_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;

import eu.kalafatic.gemini.core.dialogs.SetupDialog;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.interfaces.ESyncType;
import eu.kalafatic.gemini.core.interfaces.ISWizard;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.utils.DBUtils.DB;
import eu.kalafatic.gemini.core.wizards.pages.ISWizardPage;

/**
 * The Class class SyncWizard.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "unchecked", /* "rawtypes", */"restriction" })
public abstract class SyncWizard extends Wizard implements IWorkbenchWizard, ISWizard {

	/** The export. */
	public boolean export;

	/** The synchronizer. */
	public String SYNCHRONIZER = "SYNCHRONIZER";

	/** The src dest. */
	public int SRC_DEST = 1;

	/** The pages. */
	public IWizardPage[] pages = new IWizardPage[4];

	/** The executors. */
	public ThreadPoolExecutor[] executors = null;

	/** The tasks. */
	public List<Runnable> tasks = new ArrayList<Runnable>();

	/** The work to do. */
	public int total, workToDo;

	/** The done. */
	public AtomicInteger worked, done;

	/** The factory. */
	public GUIFactory factory;

	/** The sync sash form. */
	public SashForm syncSashForm;

	/** The wizard steps group. */
	public Group wizardStepsGroup;

	/** The src. */
	public Map<String, Table> src = new HashMap<String, Table>();

	/** The dest. */
	public Map<String, Table> dest = new HashMap<String, Table>();

	/** The dest clones. */
	public Map<String, Table> destClones = new HashMap<String, Table>();

	/** The window title. */
	private String windowTitle;

	/** The wizard banner image. */
	private ImageDescriptor wizardBannerImage;

	/** The progress. */
	public int progress = 0;

	/**
	 * Instantiates a new sync wizard.
	 * @param export the export
	 */
	public SyncWizard(boolean export) {
		this.export = export;

		setNeedsProgressMonitor(true);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class Progress.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public class Progress {

		/** The done. */
		public AtomicInteger worked, done;

		/**
		 * Instantiates a new progress.
		 * @param worked the worked
		 * @param done the done
		 */
		public Progress(AtomicInteger worked, AtomicInteger done) {
			this.worked = worked;
			this.done = done;
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setTitleAndImage();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		Shell shell = getContainer().getShell();
		Rectangle bounds = Display.getDefault().getBounds();
		shell.setBounds(bounds.width / 2 - 400, bounds.height / 2 - 300, 800, 600);

		factory = new GUIFactory(shell);

		createPages();

		for (int i = 0; i < pages.length; i++) {
			addPage(pages[i]);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard. IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		// IWizardPage nextPage = super.getNextPage(page);

		if (page.getControl() instanceof SashForm) {
			SashForm sashForm = (SashForm) page.getControl();
			sashForm.setWeights(new int[] { 1, 5 });
		}
		((ISWizardPage) page).redraw();

		if (page.equals(pages[0])) {
			progress = 0;
			return pages[1];
		} else if (page.equals(pages[1])) {
			return pages[2];
		} else if (page.equals(pages[2])) {
			return pages[3];
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISWizard#addTable(boolean, org.eclipse.swt.widgets.Table)
	 */
	@Override
	public void addTable(boolean source, Table table) {
		if (source) {
			src.put((String) table.getData(GUIFactory.NAME), table);
		} else {
			dest.put((String) table.getData(GUIFactory.NAME), table);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the wizard steps composite.
	 * @param iWizardPage the i wizard page
	 * @param parent the parent
	 * @param progress the progress
	 */
	public void createWizardStepsComposite(final IWizardPage iWizardPage, Composite parent, Button[] progress) {
		syncSashForm = factory.createSashForm(parent, 2, SWT.HORIZONTAL);

		wizardStepsGroup = new Group(syncSashForm, SWT.SHADOW_IN);
		wizardStepsGroup.setText("Progress");
		wizardStepsGroup.setLayout(new RowLayout(SWT.VERTICAL));

		for (int i = 0; i < progress.length; i++) {
			progress[i] = new Button(wizardStepsGroup, SWT.RADIO);
			progress[i].setData(i);
			// progress[i].addListener(SWT.Selection, listener);
			progress[i].setEnabled(false);
		}
		progress[0].setText("Sources");
		progress[1].setText("Specify");
		progress[2].setText("Select");
		progress[3].setText("Summary");
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the table.
	 * @param parent the parent
	 * @param name the name
	 * @param cols the cols
	 * @return the table
	 */
	public Table createTable(Composite parent, String name, String... cols) {
		Table table = factory.createTable(parent, TABLE_STYLE_2, name, true, true);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		table.setLayoutData(gridData);

		if (cols.length == 0) {
			TableColumn tc1 = new TableColumn(table, SWT.LEFT);
			TableColumn tc2 = new TableColumn(table, SWT.LEFT);
			tc1.setText(name);
			tc2.setText("Note");
			tc1.setWidth(200);
			tc2.setWidth(200);
		} else {
			for (int i = 0; i < cols.length; i++) {
				TableColumn tc = new TableColumn(table, SWT.LEFT);
				tc.setText(cols[i]);
				tc.setWidth(200);
			}
		}
		return table;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the title and image.
	 */
	protected void setTitleAndImage() {
		setNeedsProgressMonitor(true);
		if (export) {
			windowTitle = WorkbenchMessages.ExportWizard_title;
			wizardBannerImage = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ);
		} else {
			windowTitle = WorkbenchMessages.ImportWizard_title;
			wizardBannerImage = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_WIZ);
		}
		if (windowTitle != null) {
			setWindowTitle(windowTitle);
		}
		if (wizardBannerImage != null) {
			setDefaultPageImageDescriptor(wizardBannerImage);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Item exists.
	 * @param targetTable the target table
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean itemExists(Table targetTable, String name) {
		TableItem[] items = targetTable.getItems();

		for (int i = 0; i < items.length; i++) {
			if (items[i].getData(GUIFactory.NAME).equals(name)) {
				return true;
			}
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selected items.
	 * @param from the from
	 * @return the selected items
	 */
	public List<TableItem> getSelectedItems(boolean from) {
		List<TableItem> selected = new ArrayList<TableItem>();
		Collection<Table> values = from ? src.values() : dest.values();

		for (Table table : values) {
			TableItem[] selection = table.getSelection();

			for (int i = 0; i < selection.length; i++) {
				selected.add(selection[i]);
			}
		}
		return selected;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the executors.
	 * @param number the number
	 */
	protected void createExecutors(int number) {
		executors = new ThreadPoolExecutor[number];
		for (int i = 0; i < executors.length; i++) {
			executors[i] = new ThreadPoolExecutor(number, number, number, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the progress.
	 * @param sum the sum
	 * @return the i runnable with progress
	 */
	protected IRunnableWithProgress createProgress(final int sum) {

		return new IRunnableWithProgress() {
			@Override
			public void run(final IProgressMonitor monitor) {
				try {
					monitor.beginTask("Synchronizing ", sum);

					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {

							while (!tasks.isEmpty()) {
								for (int i = 0; (i < executors.length) && (!tasks.isEmpty()); i++) {

									executors[i].execute(tasks.remove(0));
								}
							}
						}
					});
					progressTask(monitor, tasks.size());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	// ---------------------------------------------------------------

	/**
	 * Progress task.
	 * @param monitor the monitor
	 * @param taskLen the task len
	 */
	private void progressTask(IProgressMonitor monitor, int taskLen) {
		try {
			long start = System.currentTimeMillis();

			while (!monitor.isCanceled() && (taskLen > done.get())) {

				monitor.subTask(" task " + done.get() + " out of " + taskLen + "  time: " + (System.currentTimeMillis() - start) + " ms");
				monitor.worked(1);
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Edits the db.
	 * @param db the db
	 */
	public void editDB(DB db) {
		try {
			if (db == null) {
				return;
			}
			SetupDialog.setup.put(TITLE, "Edit Database");
			SetupDialog.setup.put(WIDTH, 300);
			SetupDialog.setup.put(HEIGHT, 350);

			SetupDialog editDBDialog = new SetupDialog(factory, db.settings);

			if (editDBDialog.open() == Window.OK) {
				db.parseURL();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the sync type.
	 * @param table the table
	 * @return the sync type
	 */
	public ESyncType getSyncType(Table table) {
		String tableName = (String) table.getData(GUIFactory.NAME);
		ESyncType eSyncType = null;

		if (tableName.equals(ECorePreferences.SYNC_FILES.getName())) {
			eSyncType = ESyncType.FILE;

		} else if (tableName.equals(ECorePreferences.SYNC_CLIENT.getName())) {

			eSyncType = ESyncType.DEF;
		} else if (tableName.equals(ECorePreferences.SYNC_DB.getName())) {
			eSyncType = ESyncType.DB;
		}
		return eSyncType;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the wizard steps group.
	 * @return the wizard steps group
	 */
	public Group getWizardStepsGroup() {
		return wizardStepsGroup;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the sync sash form.
	 * @return the sync sash form
	 */
	public SashForm getSyncSashForm() {
		return syncSashForm;
	}

}
