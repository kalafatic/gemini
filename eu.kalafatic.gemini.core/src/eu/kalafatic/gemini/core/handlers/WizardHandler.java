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
package eu.kalafatic.gemini.core.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.LegacyResourceSupport;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.dialogs.ImportExportWizard;
import org.eclipse.ui.internal.dialogs.NewWizard;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

import eu.kalafatic.gemini.core.interfaces.IWizardHandler;

/**
 * The Class class WizardHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "restriction", "rawtypes" })
public class WizardHandler extends AbstractHandler implements IElementUpdater, IWizardHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String wizardId = event.getParameter("WIZARD_ID");

		IWorkbenchWindow activeWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		if (wizardId == null) {
			executeHandler(event);
		} else {
			IWizardRegistry wizardRegistry = getWizardRegistry();
			IWizardDescriptor wizardDescriptor = wizardRegistry.findWizard(wizardId);
			if (wizardDescriptor == null) {
				throw new ExecutionException("unknown wizard: " + wizardId); //$NON-NLS-1$
			}
			try {
				IWorkbenchWizard wizard = wizardDescriptor.createWizard();
				wizard.init(PlatformUI.getWorkbench(), getSelectionToUse(event));

				if (wizardDescriptor.canFinishEarly() && !wizardDescriptor.hasPages()) {
					wizard.performFinish();
					return null;
				}
				Shell parent = activeWindow.getShell();
				WizardDialog dialog = new WizardDialog(parent, wizard);
				dialog.create();
				dialog.open();

			} catch (CoreException ex) {
				throw new ExecutionException("error creating wizard", ex);
			}
		}
		return null;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class Export.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public static final class Export extends WizardHandler {

		/** The Constant SIZING_WIZARD_WIDTH. */
		private static final int SIZING_WIZARD_WIDTH = 470;

		/** The Constant SIZING_WIZARD_HEIGHT. */
		private static final int SIZING_WIZARD_HEIGHT = 550;

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardIdParameterId()
		 */
		@Override
		public String getWizardIdParameterId() {
			return IWorkbenchCommandConstants.FILE_EXPORT_PARM_WIZARDID;
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardRegistry()
		 */
		@Override
		public IWizardRegistry getWizardRegistry() {
			return PlatformUI.getWorkbench().getExportWizardRegistry();
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#executeHandler(org.eclipse.core.commands.ExecutionEvent)
		 */
		@Override
		public void executeHandler(ExecutionEvent event) {
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			if (activeWorkbenchWindow == null) {
				// action has been disposed
				return;
			}
			ImportExportWizard wizard = new ImportExportWizard(ImportExportWizard.EXPORT);
			IStructuredSelection selectionToPass = getSelectionToUse(event);

			wizard.init(activeWorkbenchWindow.getWorkbench(), selectionToPass);
			IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
			IDialogSettings wizardSettings = workbenchSettings.getSection("ImportExportAction"); //$NON-NLS-1$
			if (wizardSettings == null) {
				wizardSettings = workbenchSettings.addNewSection("ImportExportAction"); //$NON-NLS-1$
			}
			wizard.setDialogSettings(wizardSettings);
			wizard.setForcePreviousAndNextButtons(true);

			Shell parent = activeWorkbenchWindow.getShell();
			WizardDialog dialog = new WizardDialog(parent, wizard);
			dialog.create();
			dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
			activeWorkbenchWindow.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), IWorkbenchHelpContextIds.EXPORT_WIZARD);
			dialog.open();
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class Import.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public static final class Import extends WizardHandler {

		/** The Constant SIZING_WIZARD_WIDTH. */
		private static final int SIZING_WIZARD_WIDTH = 470;

		/** The Constant SIZING_WIZARD_HEIGHT. */
		private static final int SIZING_WIZARD_HEIGHT = 550;

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardIdParameterId()
		 */
		@Override
		public String getWizardIdParameterId() {
			return IWorkbenchCommandConstants.FILE_IMPORT_PARM_WIZARDID;
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardRegistry()
		 */
		@Override
		public IWizardRegistry getWizardRegistry() {
			return PlatformUI.getWorkbench().getImportWizardRegistry();
		}

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#executeHandler(org.eclipse.core.commands.ExecutionEvent)
		 */
		@Override
		public void executeHandler(ExecutionEvent event) {
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			if (activeWorkbenchWindow == null) {
				// action has been disposed
				return;
			}
			ImportExportWizard wizard = new ImportExportWizard(ImportExportWizard.IMPORT);
			IStructuredSelection selectionToPass = getSelectionToUse(event);

			wizard.init(activeWorkbenchWindow.getWorkbench(), selectionToPass);
			IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
			IDialogSettings wizardSettings = workbenchSettings.getSection("ImportExportAction"); //$NON-NLS-1$
			if (wizardSettings == null) {
				wizardSettings = workbenchSettings.addNewSection("ImportExportAction"); //$NON-NLS-1$
			}
			wizard.setDialogSettings(wizardSettings);
			wizard.setForcePreviousAndNextButtons(true);

			Shell parent = activeWorkbenchWindow.getShell();
			WizardDialog dialog = new WizardDialog(parent, wizard);
			dialog.create();
			dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
			activeWorkbenchWindow.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), IWorkbenchHelpContextIds.IMPORT_WIZARD);
			dialog.open();
		}
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * The Class class New.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public static final class New extends WizardHandler {

		/** The Constant SIZING_WIZARD_WIDTH. */
		private static final int SIZING_WIZARD_WIDTH = 500;

		/** The Constant SIZING_WIZARD_HEIGHT. */
		private static final int SIZING_WIZARD_HEIGHT = 500;

		/** The category id. */
		private String categoryId = null;

		// ---------------------------------------------------------------
		// ---------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardIdParameterId()
		 */
		@Override
		public String getWizardIdParameterId() {
			return IWorkbenchCommandConstants.FILE_NEW_PARM_WIZARDID;
		}

		// ---------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getWizardRegistry()
		 */
		@Override
		public IWizardRegistry getWizardRegistry() {
			return PlatformUI.getWorkbench().getNewWizardRegistry();
		}

		// ---------------------------------------------------------------

		/**
		 * Gets the category id.
		 * @return the category id
		 */
		public String getCategoryId() {
			return categoryId;
		}

		// ---------------------------------------------------------------

		/**
		 * Sets the category id.
		 * @param id the new category id
		 */
		public void setCategoryId(String id) {
			categoryId = id;
		}

		// ---------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#getSelectionToUse(org.eclipse.core.commands.ExecutionEvent)
		 */
		@Override
		protected IStructuredSelection getSelectionToUse(ExecutionEvent event) {
			ISelection selection = HandlerUtil.getCurrentSelection(event);
			IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
			if (selection instanceof IStructuredSelection) {
				selectionToPass = (IStructuredSelection) selection;
			} else {
				// @issue the following is resource-specific legacy code
				// Build the selection from the IFile of the editor
				Class resourceClass = LegacyResourceSupport.getResourceClass();
				if (resourceClass != null) {
					IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
					IWorkbenchPart part = activeWorkbenchWindow.getPartService().getActivePart();
					if (part instanceof IEditorPart) {
						IEditorInput input = ((IEditorPart) part).getEditorInput();
//						Object resource = Util.getAdapter(input, resourceClass);
//						if (resource != null) {
//							selectionToPass = new StructuredSelection(resource);
//						}
					}
				}
			}
			return selectionToPass;
		}

		// ---------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * @see eu.kalafatic.gemini.core.handlers.WizardHandler#executeHandler(org.eclipse.core.commands.ExecutionEvent)
		 */
		@Override
		public void executeHandler(ExecutionEvent event) {
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			if (activeWorkbenchWindow == null) {
				// action has been disposed
				return;
			}
			NewWizard wizard = new NewWizard();
			wizard.setCategoryId(categoryId);

			IStructuredSelection selectionToPass = getSelectionToUse(event);
			wizard.init(activeWorkbenchWindow.getWorkbench(), selectionToPass);

			IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
			IDialogSettings wizardSettings = workbenchSettings.getSection("NewWizardAction"); //$NON-NLS-1$
			if (wizardSettings == null) {
				wizardSettings = workbenchSettings.addNewSection("NewWizardAction"); //$NON-NLS-1$
			}
			wizard.setDialogSettings(wizardSettings);
			wizard.setForcePreviousAndNextButtons(true);

			Shell parent = activeWorkbenchWindow.getShell();
			WizardDialog dialog = new WizardDialog(parent, wizard);
			dialog.create();
			dialog.getShell().setSize(Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT);
			activeWorkbenchWindow.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), IWorkbenchHelpContextIds.NEW_WIZARD);
			dialog.open();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the selection to use.
	 * @param event the event
	 * @return the selection to use
	 */
	protected IStructuredSelection getSelectionToUse(ExecutionEvent event) {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			return (IStructuredSelection) selection;
		}
		return StructuredSelection.EMPTY;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.menus.UIElement, java.util.Map)
	 */
	@Override
	public void updateElement(UIElement element, Map parameters) {

		String wizardId = (String) parameters.get(getWizardIdParameterId());
		if (wizardId == null) {
			return;
		}
		IWizardDescriptor wizard = getWizardRegistry().findWizard(wizardId);
		if (wizard != null) {
			element.setText(wizard.getLabel());
			element.setTooltip(wizard.getDescription());
			element.setIcon(wizard.getImageDescriptor());
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IWizardHandler#getWizardIdParameterId()
	 */
	@Override
	public String getWizardIdParameterId() {
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IWizardHandler#getWizardRegistry()
	 */
	@Override
	public IWizardRegistry getWizardRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.IWizardHandler#executeHandler(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public void executeHandler(ExecutionEvent event) {
		// TODO Auto-generated method stub
	}
}