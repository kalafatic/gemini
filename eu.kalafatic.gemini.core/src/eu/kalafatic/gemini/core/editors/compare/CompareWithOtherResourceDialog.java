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
package eu.kalafatic.gemini.core.editors.compare;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.part.ResourceTransfer;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.editors.providers.Utilities;

/**
 * The Class class CompareWithOtherResourceDialog.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CompareWithOtherResourceDialog extends TitleAreaDialog {

	/** The min width. */
	private int MIN_WIDTH = 320;

	/** The min height with ancestor. */
	private int MIN_HEIGHT_WITH_ANCESTOR = 320;

	/** The min height without ancestor. */
	private int MIN_HEIGHT_WITHOUT_ANCESTOR = 238;

	/**
	 * The listener interface for receiving fileTextDrag events. The class that is interested in processing a fileTextDrag event implements this
	 * interface, and the object created with that class is registered with a component using the component's
	 * <code>addFileTextDragListener<code> method. When
	 * the fileTextDrag event occurs, that object's appropriate
	 * method is invoked.
	 * @see FileTextDragEvent
	 */
	private class FileTextDragListener implements DragSourceListener {

		/** The element. */
		private ContentTypeElement element;

		/**
		 * Instantiates a new file text drag listener.
		 * @param element the element
		 */
		public FileTextDragListener(ContentTypeElement element) {
			this.element = element;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DragSourceListener#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
		 */
		@Override
		public void dragFinished(DragSourceEvent event) {
			element.setText(""); //$NON-NLS-1$
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DragSourceListener#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
		 */
		@Override
		public void dragSetData(DragSourceEvent event) {
			event.data = element.getText();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DragSourceListener#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
		 */
		@Override
		public void dragStart(DragSourceEvent event) {
			if (element.getText() == null) {
				event.doit = false;
			}
		}
	}

	/**
	 * The listener interface for receiving fileTextDrop events. The class that is interested in processing a fileTextDrop event implements this
	 * interface, and the object created with that class is registered with a component using the component's
	 * <code>addFileTextDropListener<code> method. When
	 * the fileTextDrop event occurs, that object's appropriate
	 * method is invoked.
	 * @see FileTextDropEvent
	 */
	private class FileTextDropListener implements DropTargetListener {

		/** The element. */
		private ContentTypeElement element;

		/** The resource transfer. */
		private ResourceTransfer resourceTransfer;

		/** The text transfer. */
		private TextTransfer textTransfer;

		/**
		 * Instantiates a new file text drop listener.
		 * @param element the element
		 */
		public FileTextDropListener(ContentTypeElement element) {
			this.element = element;
			resourceTransfer = ResourceTransfer.getInstance();
			textTransfer = TextTransfer.getInstance();
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void dragEnter(DropTargetEvent event) {

			if (event.detail == DND.DROP_DEFAULT) {
				if ((event.operations & DND.DROP_COPY) != 0) {
					event.detail = DND.DROP_COPY;
				} else {
					event.detail = DND.DROP_NONE;
				}
			}

			for (int i = 0; i < event.dataTypes.length; i++) {
				if (resourceTransfer.isSupportedType(event.dataTypes[i]) || textTransfer.isSupportedType(event.dataTypes[i])) {
					event.currentDataType = event.dataTypes[i];
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
					break;
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#dragLeave(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void dragLeave(DropTargetEvent event) {
			// intentionally empty
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#dragOperationChanged(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void dragOperationChanged(DropTargetEvent event) {

			if (event.detail == DND.DROP_DEFAULT) {
				if ((event.operations & DND.DROP_COPY) != 0) {
					event.detail = DND.DROP_COPY;
				} else {
					event.detail = DND.DROP_NONE;
				}
			} else if (resourceTransfer.isSupportedType(event.currentDataType)) {
				if (event.detail != DND.DROP_COPY) {
					event.detail = DND.DROP_NONE;
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#dragOver(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void dragOver(DropTargetEvent event) {
			// intentionally empty
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void drop(DropTargetEvent event) {

			if (textTransfer.isSupportedType(event.currentDataType)) {
				String txt = (String) event.data;
				IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(txt);
				if (r != null) {
					element.setResource(r);
				}
			} else if (resourceTransfer.isSupportedType(event.currentDataType)) {
				IResource[] files = (IResource[]) event.data;
				if (files.length > 0) {
					element.setResource(files[0]);
				}
			}

		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.dnd.DropTargetListener#dropAccept(org.eclipse.swt.dnd.DropTargetEvent)
		 */
		@Override
		public void dropAccept(DropTargetEvent event) {
			// intentionally empty
		}

	}

	/**
	 * The Class class ContentTypeElement.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private abstract class ContentTypeElement {

		/** The radio button. */
		private Button radioButton;

		/** The main button. */
		protected Button mainButton;

		/** The text. */
		protected Text text;

		/** The type. */
		private String type;

		/** The section. */
		protected InternalSection section;

		/** The resource. */
		private IResource resource;

		/**
		 * Instantiates a new content type element.
		 * @param parent the parent
		 * @param type the type
		 * @param section the section
		 */
		public ContentTypeElement(Composite parent, String type, InternalSection section) {
			this.type = type;
			this.section = section;
			createContents(parent);
		}

		/**
		 * Creates the contents.
		 * @param parent the parent
		 */
		private void createContents(Composite parent) {
			createRadioButton(parent);
			createText(parent);
			createMainButton(parent);
		}

		/**
		 * Creates the radio button.
		 * @param parent the parent
		 */
		private void createRadioButton(Composite parent) {
			radioButton = new Button(parent, SWT.RADIO);
			radioButton.setText(type);
		}

		/**
		 * Creates the text.
		 * @param parent the parent
		 */
		protected void createText(Composite parent) {
			text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			text.setEditable(false);
		}

		/**
		 * Creates the main button.
		 * @param parent the parent
		 */
		protected void createMainButton(Composite parent) {
			mainButton = new Button(parent, SWT.PUSH);
			mainButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		}

		/**
		 * Gets the radio button.
		 * @return the radio button
		 */
		protected Button getRadioButton() {
			return radioButton;
		}

		/**
		 * Gets the text.
		 * @return the text
		 */
		protected String getText() {
			return text.getText();
		}

		/**
		 * Sets the text.
		 * @param string the new text
		 */
		protected void setText(String string) {
			text.setText(string);
		}

		/**
		 * Sets the enabled.
		 * @param enabled the new enabled
		 */
		protected void setEnabled(boolean enabled) {
			radioButton.setSelection(enabled);
			mainButton.setEnabled(enabled);
			text.setEnabled(enabled);
		}

		/**
		 * Sets the resource.
		 * @param resource the new resource
		 */
		protected void setResource(IResource resource) {
			this.resource = resource;
			section.setResource(resource);
		}

		/**
		 * Gets the resource.
		 * @return the resource
		 */
		public IResource getResource() {
			return resource;
		}

		/**
		 * Clear resource.
		 */
		void clearResource() {
			resource = null;
			text.setText(""); //$NON-NLS-1$
		}

	}

	/**
	 * The Class class WorkspaceContent.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class WorkspaceContent extends ContentTypeElement {

		/**
		 * Instantiates a new workspace content.
		 * @param parent the parent
		 * @param section the section
		 */
		public WorkspaceContent(Composite parent, InternalSection section) {
			super(parent, "Compare", section);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#createMainButton(org.eclipse.swt.widgets.Composite
		 * )
		 */
		@Override
		protected void createMainButton(Composite parent) {
			super.createMainButton(parent);
			mainButton.setText("Compare");
			// temporarily hide this button. For more information about
			// supporting for browsing workspace see bug 243744.
			mainButton.setVisible(false);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#createText(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected void createText(Composite parent) {

			super.createText(parent);
			text.setEditable(true);

			text.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					section.setResource(text.getText());
					updateErrorInfo();
				}
			});

			text.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					section.setResource(text.getText());
					updateErrorInfo();
				}
			});

			initDrag();
			initDrop();
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#setResource(org.eclipse.core.resources.IResource
		 * )
		 */
		@Override
		protected void setResource(IResource resource) {
			super.setResource(resource);
			text.setText(resource.getFullPath().toOSString());
		}

		/**
		 * Inits the drag.
		 */
		protected void initDrag() {
			DragSource source = new DragSource(text, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
			Transfer[] types = new Transfer[] { TextTransfer.getInstance(), ResourceTransfer.getInstance() };
			source.setTransfer(types);
			source.addDragListener(new FileTextDragListener(this));
		}

		/**
		 * Inits the drop.
		 */
		protected void initDrop() {
			DropTarget target = new DropTarget(text, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
			Transfer[] types = new Transfer[] { TextTransfer.getInstance(), ResourceTransfer.getInstance() };
			target.setTransfer(types);
			target.addDropListener(new FileTextDropListener(this));
		}

	}

	/**
	 * The Class class ExternalFileContent.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class ExternalFileContent extends ContentTypeElement {

		/**
		 * Instantiates a new external file content.
		 * @param parent the parent
		 * @param section the section
		 */
		public ExternalFileContent(Composite parent, InternalSection section) {
			super(parent, "Compare", section);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#createMainButton(org.eclipse.swt.widgets.Composite
		 * )
		 */
		@Override
		protected void createMainButton(Composite parent) {
			super.createMainButton(parent);
			mainButton.setText("Compare");
			mainButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					IResource r = tmpProject.getExternalFile();
					if (r == null) {
						return;
					}
					setResource(r);
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#setResource(org.eclipse.core.resources.IResource
		 * )
		 */
		@Override
		protected void setResource(IResource resource) {
			super.setResource(resource);
			text.setText(resource.getLocation().toOSString());
		}

	}

	/**
	 * The Class class ExternalFolderContent.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class ExternalFolderContent extends ContentTypeElement {

		/**
		 * Instantiates a new external folder content.
		 * @param parent the parent
		 * @param section the section
		 */
		public ExternalFolderContent(Composite parent, InternalSection section) {
			super(parent, "Compare", section);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#createMainButton(org.eclipse.swt.widgets.Composite
		 * )
		 */
		@Override
		protected void createMainButton(Composite parent) {
			super.createMainButton(parent);
			mainButton.setText("Compare");
			mainButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					IResource r = tmpProject.getExternalFolder();
					if (r == null) {
						return;
					}
					setResource(r);
				}
			});
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.ContentTypeElement#setResource(org.eclipse.core.resources.IResource
		 * )
		 */
		@Override
		protected void setResource(IResource resource) {
			super.setResource(resource);
			text.setText(resource.getLocation().toOSString());
		}

	}

	/**
	 * The Class class InternalSection.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private abstract class InternalSection {

		// there is no "enum" support in Java 1.4. Sigh...
		/** The Constant WORKSPACE. */
		public static final int WORKSPACE = 0;

		/** The Constant EXTERNAL_FILE. */
		public static final int EXTERNAL_FILE = 1;

		/** The Constant EXTERNAL_FOLDER. */
		public static final int EXTERNAL_FOLDER = 2;

		/** The group. */
		protected Group group;

		/** The resource. */
		private IResource resource;

		/** The external file content. */
		ExternalFileContent externalFileContent;

		/** The external folder content. */
		ExternalFolderContent externalFolderContent;

		/** The workspace content. */
		WorkspaceContent workspaceContent;

		/**
		 * Instantiates a new internal section.
		 */
		private InternalSection() {
			// not to instantiate
		}

		/**
		 * Creates the contents.
		 * @param parent the parent
		 */
		protected void createContents(Composite parent) {

			group = new Group(parent, SWT.NONE);
			group.setLayout(new GridLayout(3, false));
			group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			workspaceContent = new WorkspaceContent(group, this);
			externalFileContent = new ExternalFileContent(group, this);
			externalFolderContent = new ExternalFolderContent(group, this);

			addListenersToRadioButtons();
		}

		/**
		 * Adds the listeners to radio buttons.
		 */
		private void addListenersToRadioButtons() {
			final ContentTypeElement[] elements = new ContentTypeElement[] { workspaceContent, externalFileContent, externalFolderContent };
			for (int i = 0; i < elements.length; i++) {
				elements[i].getRadioButton().addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event event) {
						for (int j = 0; j < elements.length; j++) {
							if (event.widget != elements[j].getRadioButton()) {
								elements[j].setEnabled(false);
							} else {
								elements[j].setEnabled(true);
								setResource(elements[j].getResource());
							}
						}
					}
				});
			}
		}

		/**
		 * Gets the resource.
		 * @return the resource
		 */
		protected IResource getResource() {
			return resource;
		}

		/**
		 * Sets the resource.
		 * @param resource the new resource
		 */
		protected void setResource(IResource resource) {
			this.resource = resource;
			updateErrorInfo();
		}

		/**
		 * Sets the resource.
		 * @param s the new resource
		 */
		protected void setResource(String s) {
			IResource tmp = ResourcesPlugin.getWorkspace().getRoot().findMember(s);
			if (tmp instanceof IWorkspaceRoot) {
				resource = null;
			} else {
				resource = tmp;
			}
			updateErrorInfo();
		}

		/**
		 * Clear resource.
		 */
		protected void clearResource() {
			resource = null;
			workspaceContent.clearResource();
			externalFileContent.clearResource();
			externalFolderContent.clearResource();
			updateErrorInfo();
		}

		/**
		 * Sets the content type.
		 * @param type the new content type
		 */
		protected void setContentType(int type) {
			switch (type) {
			case WORKSPACE:
				workspaceContent.setEnabled(true);
				externalFileContent.setEnabled(false);
				externalFolderContent.setEnabled(false);
				break;
			case EXTERNAL_FILE:
				workspaceContent.setEnabled(false);
				externalFileContent.setEnabled(true);
				externalFolderContent.setEnabled(false);
				break;
			case EXTERNAL_FOLDER:
				workspaceContent.setEnabled(false);
				externalFileContent.setEnabled(false);
				externalFolderContent.setEnabled(true);
			}
		}
	}

	/**
	 * The Class class InternalGroup.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class InternalGroup extends InternalSection {

		/**
		 * Instantiates a new internal group.
		 * @param parent the parent
		 */
		public InternalGroup(Composite parent) {
			createContents(parent);
		}

		/**
		 * Sets the text.
		 * @param text the new text
		 */
		public void setText(String text) {
			group.setText(text);
		}

		/**
		 * Sets the layout data.
		 * @param layoutData the new layout data
		 */
		public void setLayoutData(GridData layoutData) {
			group.setLayoutData(layoutData);
		}
	}

	/**
	 * The Class class InternalExpandable.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class InternalExpandable extends InternalSection {

		/** The expandable. */
		private ExpandableComposite expandable;

		/** The clear button. */
		private Button clearButton;

		/**
		 * Instantiates a new internal expandable.
		 * @param parent the parent
		 */
		public InternalExpandable(Composite parent) {
			createContents(parent);
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * eu.kalafatic.gemini.core.editors.compare.CompareWithOtherResourceDialog.InternalSection#createContents(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected void createContents(Composite parent) {
			final Composite p = parent;
			expandable = new ExpandableComposite(parent, SWT.NONE, ExpandableComposite.TREE_NODE | ExpandableComposite.TWISTIE);
			super.createContents(expandable);
			createClearButton(group);
			expandable.setClient(group);
			expandable.addExpansionListener(new ExpansionAdapter() {
				@Override
				public void expansionStateChanged(ExpansionEvent e) {
					p.layout();
					adjustSize(e.getState());
				}
			});
		}

		/**
		 * Creates the clear button.
		 * @param parent the parent
		 */
		private void createClearButton(Composite parent) {
			clearButton = new Button(parent, SWT.PUSH);
			clearButton.setText("Compare");
			clearButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					clearResource();
				}
			});
		}

		/**
		 * Sets the text.
		 * @param text the new text
		 */
		public void setText(String text) {
			expandable.setText(text);
			group.setText(text);
		}

		/**
		 * Sets the layout data.
		 * @param layoutData the new layout data
		 */
		public void setLayoutData(GridData layoutData) {
			expandable.setLayoutData(layoutData);
		}
	}

	/**
	 * The Class class ExternalResourcesProject.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private class ExternalResourcesProject {

		// Implementation based on
		// org.eclipse.jdt.internal.core.ExternalFoldersManager

		/** The counter. */
		private int counter = 0;

		/** The Constant TMP_PROJECT_NAME. */
		private static final String TMP_PROJECT_NAME = ".org.eclipse.compare.tmp"; //$NON-NLS-1$

		/** The Constant TMP_PROJECT_FILE. */
		private final static String TMP_PROJECT_FILE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //$NON-NLS-1$
				+ "<projectDescription>\n" //$NON-NLS-1$
				+ "\t<name>" + TMP_PROJECT_NAME + "\t</name>\n" //$NON-NLS-1$ //$NON-NLS-2$
				+ "\t<comment></comment>\n" //$NON-NLS-1$
				+ "\t<projects>\n" //$NON-NLS-1$
				+ "\t</projects>\n" //$NON-NLS-1$
				+ "\t<buildSpec>\n" //$NON-NLS-1$
				+ "\t</buildSpec>\n" //$NON-NLS-1$
				+ "\t<natures>\n" + "\t</natures>\n" //$NON-NLS-1$//$NON-NLS-2$
				+ "</projectDescription>"; //$NON-NLS-1$

		/** The Constant TMP_FOLDER_NAME. */
		private final static String TMP_FOLDER_NAME = "tmpFolder"; //$NON-NLS-1$

		/**
		 * Instantiates a new external resources project.
		 */
		private ExternalResourcesProject() {
			// nothing to do here
		}

		/**
		 * Creates the tmp project.
		 * @return the i project
		 * @throws CoreException the core exception
		 */
		private IProject createTmpProject() throws CoreException {
			IProject project = getTmpProject();
			if (!project.isAccessible()) {
				try {
					IPath stateLocation = CompareUI.getPlugin().getStateLocation();
					if (!project.exists()) {
						IProjectDescription desc = project.getWorkspace().newProjectDescription(project.getName());
						desc.setLocation(stateLocation.append(TMP_PROJECT_NAME));
						project.create(desc, null);
					}
					try {
						project.open(null);
					} catch (CoreException e) { // in case .project file or
												// folder has been deleted
						IPath projectPath = stateLocation.append(TMP_PROJECT_NAME);
						projectPath.toFile().mkdirs();
						FileOutputStream output = new FileOutputStream(projectPath.append(".project").toOSString()); //$NON-NLS-1$
						try {
							output.write(TMP_PROJECT_FILE.getBytes());
						} finally {
							output.close();
						}
						project.open(null);
					}
					getTmpFolder(project);
				} catch (IOException ioe) {
					return project;
				} catch (CoreException ce) {
					throw new CoreException(ce.getStatus());
				}
			}
			project.setHidden(true);
			return project;
		}

		/**
		 * Gets the tmp folder.
		 * @param project the project
		 * @return the tmp folder
		 * @throws CoreException the core exception
		 */
		private IFolder getTmpFolder(IProject project) throws CoreException {
			IFolder folder = project.getFolder(TMP_FOLDER_NAME);
			if (!folder.exists()) {
				folder.create(IResource.NONE, true, null);
			}
			return folder;
		}

		/**
		 * Gets the external file.
		 * @return the external file
		 */
		private IFile getExternalFile() {
			FileDialog dialog = new FileDialog(getShell());
			String path = dialog.open();
			if (path != null) {
				return (IFile) linkResource(new Path(path));
			}
			return null;
		}

		/**
		 * Gets the external folder.
		 * @return the external folder
		 */
		private IFolder getExternalFolder() {
			DirectoryDialog dialog = new DirectoryDialog(getShell());
			String path = dialog.open();
			if (path != null) {
				return (IFolder) linkResource(new Path(path));
			}
			return null;
		}

		/**
		 * Link resource.
		 * @param path the path
		 * @return the i resource
		 */
		private IResource linkResource(IPath path) {
			IResource r = null;
			String resourceName = path.lastSegment();
			try {
				IProject project = createTmpProject();
				if (!project.isOpen()) {
					project.open(null);
				}
				if (path.toFile().isFile()) {
					r = getTmpFolder(project).getFile(resourceName);
					if (r.exists()) { // add a number to file's name when there
										// already is a file with that name in a
										// folder
						String extension = path.getFileExtension();
						String fileName = path.removeFileExtension().lastSegment();
						r = getTmpFolder(project).getFile(getName(fileName, extension));
					}
					((IFile) r).createLink(path, IResource.REPLACE, null);
				} else { // isDirectory
					r = getTmpFolder(project).getFolder(resourceName);
					if (r.exists()) {
						r = getTmpFolder(project).getFolder(getName(resourceName, null));
					}
					((IFolder) r).createLink(path, IResource.REPLACE, null);
				}
			} catch (CoreException e) {
				e.printStackTrace();

				// CompareUIPlugin.log(e);
				// MessageDialog.openError(getShell(),
				// CompareMessages.CompareWithOtherResourceDialog_externalFile_errorTitle,
				// CompareMessages.CompareWithOtherResourceDialog_externalFile_errorMessage);
			}
			return r;
		}

		/**
		 * Gets the name.
		 * @param name the name
		 * @param extension the extension
		 * @return the name
		 */
		private String getName(String name, String extension) {
			if (counter != 0) {
				name = name + "-" + counter; //$NON-NLS-1$
			}
			// at most 3 resources at the same time with the same name:
			// left, right, ancestor
			counter = (counter + 1) % 3;
			if (extension != null) {
				name += "." + extension; //$NON-NLS-1$
			}
			// don't change the name if counter equals 0
			return name;
		}

		/**
		 * Gets the tmp project.
		 * @return the tmp project
		 */
		private IProject getTmpProject() {
			return ResourcesPlugin.getWorkspace().getRoot().getProject(TMP_PROJECT_NAME);
		}
	}

	/** The ok button. */
	private Button okButton;

	/** The left panel. */
	private InternalGroup rightPanel, leftPanel;

	/** The ancestor panel. */
	private InternalExpandable ancestorPanel;

	/** The selection. */
	private ISelection selection;

	/** The tmp project. */
	private ExternalResourcesProject tmpProject = new ExternalResourcesProject();

	/**
	 * Instantiates a new compare with other resource dialog.
	 * @param shell the shell
	 * @param selection the selection
	 */
	protected CompareWithOtherResourceDialog(Shell shell, ISelection selection) {
		super(shell);
		setShellStyle(SWT.MODELESS | SWT.RESIZE | SWT.MAX);
		this.selection = selection;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite mainPanel = new Composite(parent, SWT.NULL);
		mainPanel.setLayout(new GridLayout(1, true));
		mainPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		ancestorPanel = new InternalExpandable(mainPanel);
		ancestorPanel.setText("Compare");
		GridData ancestorGD = new GridData(SWT.FILL, SWT.FILL, true, false);
		ancestorPanel.setLayoutData(ancestorGD);

		leftPanel = new InternalGroup(mainPanel);
		leftPanel.setText("Compare");
		leftPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		rightPanel = new InternalGroup(mainPanel);
		rightPanel.setText("Compare");
		rightPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		setSelection(selection);
		getShell().setText("Compare");
		setTitle("Compare");
		adjustSize(ancestorPanel.expandable.isExpanded());

		return mainPanel;
	}

	/**
	 * Adjust size.
	 * @param expanded the expanded
	 */
	private void adjustSize(boolean expanded) {
		int minWidth = convertHorizontalDLUsToPixels(MIN_WIDTH);
		int minHeight = convertVerticalDLUsToPixels(expanded ? MIN_HEIGHT_WITH_ANCESTOR : MIN_HEIGHT_WITHOUT_ANCESTOR);
		getShell().setMinimumSize(minWidth, minHeight);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		okButton = getButton(IDialogConstants.OK_ID);
		updateErrorInfo();
		setMessage("Compare");
	}

	/**
	 * Sets the selection.
	 * @param selection the new selection
	 */
	private void setSelection(ISelection selection) {
		IResource[] selectedResources = Utilities.getResources(selection);
		switch (selectedResources.length) {
		case 1:
			leftPanel.workspaceContent.setResource(selectedResources[0]);
			break;
		case 2:
			leftPanel.workspaceContent.setResource(selectedResources[0]);
			rightPanel.workspaceContent.setResource(selectedResources[1]);
			break;
		case 3:
			ancestorPanel.workspaceContent.setResource(selectedResources[0]);
			ancestorPanel.expandable.setExpanded(true);
			leftPanel.workspaceContent.setResource(selectedResources[1]);
			rightPanel.workspaceContent.setResource(selectedResources[2]);
			break;
		}
		setInitialContentTypes();
	}

	/**
	 * Sets the initial content types.
	 */
	private void setInitialContentTypes() {
		ancestorPanel.setContentType(InternalSection.WORKSPACE);
		leftPanel.setContentType(InternalSection.WORKSPACE);
		rightPanel.setContentType(InternalSection.WORKSPACE);
	}

	/**
	 * Checks if is compare possible.
	 * @return true, if is compare possible
	 */
	private boolean isComparePossible() {
		IResource[] resources;
		if (ancestorPanel.getResource() == null) {
			resources = new IResource[] { leftPanel.getResource(), rightPanel.getResource() };
		} else {
			resources = new IResource[] { ancestorPanel.getResource(), leftPanel.getResource(), rightPanel.getResource() };
		}

		ResourceCompareInput r = new ResourceCompareInput(new CompareConfiguration());
		return r.isEnabled(new StructuredSelection(resources));
	}

	/**
	 * Update error info.
	 */
	private void updateErrorInfo() {
		if (okButton != null) {
			if (leftPanel.getResource() == null || rightPanel.getResource() == null) {
				setMessage("Compare", IMessageProvider.ERROR);
				okButton.setEnabled(false);
			} else if (!isComparePossible()) {
				setMessage("Compare", IMessageProvider.ERROR);
				okButton.setEnabled(false);
			} else {
				setMessage("Compare");
				okButton.setEnabled(true);
			}
		}
	}

	/**
	 * Gets the result.
	 * @return the result
	 */
	public IResource[] getResult() {
		IResource[] resources;
		IResource rightResource = rightPanel.getResource();
		IResource leftResource = leftPanel.getResource();
		IResource ancestorResource = ancestorPanel.getResource();
		if (ancestorResource == null) {
			resources = new IResource[] { leftResource, rightResource };
		} else {
			resources = new IResource[] { ancestorResource, leftResource, rightResource };
		}
		return resources;
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsSettings()
	 */
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsSettings()
	 */
	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		String sectionName = getClass().getName() + "_dialogBounds"; //$NON-NLS-1$
		// IDialogSettings settings = CompareUIPlugin.getDefault()
		// .getDialogSettings();
		IDialogSettings settings = Activator.getDefault().getDialogSettings();
		IDialogSettings section = settings.getSection(sectionName);
		if (section == null) {
			section = settings.addNewSection(sectionName);
		}
		return section;
	}
}