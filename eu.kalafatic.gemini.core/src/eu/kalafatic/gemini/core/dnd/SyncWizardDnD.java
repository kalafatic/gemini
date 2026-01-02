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
package eu.kalafatic.gemini.core.dnd;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import eu.kalafatic.gemini.core.models.SyncObject;
import eu.kalafatic.gemini.core.wizards.SyncWizard;
import eu.kalafatic.gemini.core.wizards.pages.AWizardPage;

/**
 * The Class class SyncWizardDnD.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SyncWizardDnD {

	/** The instance. */
	private static SyncWizardDnD INSTANCE;

	/** The wizard. */
	private SyncWizard wizard;

	/** The sync select page. */
	private AWizardPage syncSelectPage;

	/** The style. */
	private final int style = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;

	/** The types. */
	private Transfer[] types;

	/**
	 * Gets the single instance of SyncWizardDnD.
	 * @return single instance of SyncWizardDnD
	 */
	public static SyncWizardDnD getInstance() {
		if (INSTANCE == null) {
			synchronized (SyncWizardDnD.class) {
				INSTANCE = new SyncWizardDnD();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Inits the dn d.
	 * @param wizard the wizard
	 * @param control the control
	 */
	public void initDnD(SyncWizard wizard, Control control) {
		this.wizard = wizard;
		this.syncSelectPage = (AWizardPage) wizard.pages[2];
		types = new Transfer[] { SyncObjectTransfer.getInstance() };

		initDropSource(control);
		initDropTarget(control);
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the drop source.
	 * @param control the control
	 */
	private void initDropSource(Control control) {
		DragSource dragSource = new DragSource(control, style);
		dragSource.setTransfer(types);

		dragSource.addDragListener(new DragSourceAdapter() {

			@Override
			public void dragSetData(DragSourceEvent event) {
				DragSource ds = (DragSource) event.widget;
				Table table = (Table) ds.getControl();

				TableItem[] selection = table.getSelection();
				SyncObject[] syncObjects = new SyncObject[selection.length];

				for (int i = 0; i < syncObjects.length; i++) {
					syncObjects[i] = (SyncObject) selection[i].getData();
				}
				event.data = syncObjects;
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the drop target.
	 * @param control the control
	 */
	private void initDropTarget(Control control) {
		DropTarget dropTarget = new DropTarget(control, style);
		dropTarget.setTransfer(types);

		dropTarget.addDropListener(new DropTargetAdapter() {

			@Override
			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				SyncObject[] syncObjects = (SyncObject[]) SyncObjectTransfer.getInstance().nativeToJava(event.dataTypes[0]);

				// no data to copy, indicate failure
				if (syncObjects == null) {
					event.detail = DND.DROP_NONE;
					return;
				}

				DropTarget target = (DropTarget) event.widget;

				if (target.getControl() instanceof Table) {
					Table table = (Table) target.getControl();
					moveSyncObjects(syncObjects, table);
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Move sync objects.
	 * @param syncObjects the sync objects
	 * @param table the table
	 */
	private void moveSyncObjects(SyncObject[] syncObjects, Table table) {
		if (wizard.export) {
			for (int i = 0; i < syncObjects.length; i++) {
				// import and target(commit or rollback)
				syncSelectPage.moveObject(table, syncObjects[i], table.equals(wizard.dest.values().iterator().next()));
			}

		} else {
			for (int i = 0; i < syncObjects.length; i++) {
				// import and target(commit or rollback)
				syncSelectPage.moveObject(table, syncObjects[i], table.equals(wizard.dest.values().iterator().next()));
			}
		}
	}
}
