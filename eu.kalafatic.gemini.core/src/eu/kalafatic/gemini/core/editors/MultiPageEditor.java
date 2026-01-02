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
package eu.kalafatic.gemini.core.editors;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import eu.kalafatic.gemini.core.editors.compare.CompareEditorAction;
import eu.kalafatic.gemini.core.lib.EExt;
import eu.kalafatic.gemini.core.lib.EMimeType;
import eu.kalafatic.gemini.core.utils.FileUtils;

/**
 * The Class class MultiPageEditor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings({ "rawtypes", "unused" })
public class MultiPageEditor extends MultiPageEditorPart implements IResourceChangeListener, IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider, IGotoMarker {

	/** The Constant ID. */
	public static final String ID = "eu.kalafatic.gemini.core.editors.MultiPageEditor";

	/** The editor. */
	private Editor editor;

	/** The editor input. */
	private IEditorInput editorInput;

	/** The font. */
	private Font font;

	/** The text. */
	private StyledText text;

	/**
	 * Instantiates a new multi page editor.
	 */
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	@Override
	protected void createPages() {
		try {
			URI uri = null;
			if (editorInput instanceof IFileEditorInput) {
				IFileEditorInput iFileEditorInput = (IFileEditorInput) editorInput;
				uri = URI.create(iFileEditorInput.toString());

			} else if (editorInput instanceof IStorageEditorInput) {
				IStorageEditorInput iStorageEditorInput = (IStorageEditorInput) editorInput;
				uri = URI.create(iStorageEditorInput.toString());

			} else if (editorInput instanceof FileStoreEditorInput) {
				FileStoreEditorInput fileStoreEditorInput = (FileStoreEditorInput) editorInput;
				uri = fileStoreEditorInput.getURI();

			} else if (editorInput instanceof IURIEditorInput) {
				IURIEditorInput iURIEditorInput = (IURIEditorInput) editorInput;
				uri = iURIEditorInput.getURI();
			}
			EExt eExt = FileUtils.getInstance().getExtension(editorInput.getName());

			createEditorPage(editorInput, eExt.protocol);

			Resource resource = loadXML("c:/x.ecore");

			// open editor for formatting (htm=xml)
			switch (eExt) {
			case XML:
			case ECORE:
				IDE.openEditor(getSite().getPage(), uri, EcoreEditor.ID, true);
				IDE.openEditor(getSite().getPage(), URI.create(resource.getURI().toString()), EcoreEditor.ID, true);
				new CompareEditorAction().run1();

				break;

			default:

				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the editor page.
	 * @param editorInput the editor input
	 * @param protocol the protocol
	 * @throws Exception the exception
	 */
	private void createEditorPage(IEditorInput editorInput, EMimeType protocol) throws Exception {

		editor = new Editor(protocol);
		int index = addPage(editor, editorInput);
		setPageText(index, editor.getTitle());
	}

	// ---------------------------------------------------------------

	/**
	 * Load xml.
	 * @param targetName the target name
	 * @return the resource
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	Resource loadXML(String targetName) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		org.eclipse.emf.common.util.URI targetURI = org.eclipse.emf.common.util.URI.createFileURI(targetName);
		Resource resource = resourceSet.createResource(targetURI);

		resource.save(null);
		return resource;
	}

	// ---------------------------------------------------------------

	/**
	 * Xsd to ecore.
	 * @param uri the uri
	 * @param nameSpace the name space
	 * @return the e package
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	EPackage xsdToEcore(org.eclipse.emf.common.util.URI uri, String nameSpace) throws IOException {
		XSDSchema xSDSchema = XSDFactory.eINSTANCE.createXSDSchema();
		xSDSchema.setTargetNamespace(nameSpace);

		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();

		File file = new File(uri + ".xsd");
		file.createNewFile();
		Collection<EObject> collection = xsdEcoreBuilder.generate(uri);

		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Xsd to xmi.
	 * @param sourceName the source name
	 * @param targetName the target name
	 */
	public void xsdToXMI(String sourceName, String targetName) {
		System.out.println("Starting");

		org.eclipse.emf.common.util.URI sourceURI = org.eclipse.emf.common.util.URI.createFileURI(sourceName);
		org.eclipse.emf.common.util.URI targetURI = org.eclipse.emf.common.util.URI.createFileURI(targetName);

		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		ResourceSet resourceSet = new ResourceSetImpl();
		Collection eCorePackages = xsdEcoreBuilder.generate(sourceURI);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(targetURI);

		for (Iterator iter = eCorePackages.iterator(); iter.hasNext();) {
			EPackage element = (EPackage) iter.next();
			resource.getContents().add(element);
		}

		try {
			resource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Finished");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime. IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	/**
	 * Goto marker.
	 * @param marker the marker
	 */
	@Override
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		// IDE.gotoMarker(getEditor(0), marker);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		// if (!(editorInput instanceof IFileEditorInput))
		// throw new
		// PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
		this.editorInput = editorInput;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 1) {
			sortWords();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org .eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(final IResourceChangeEvent event) {
		System.err.println();
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the font.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null) {
				font.dispose();
			}
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Sort words.
	 */

	void sortWords() {
		// String editorText = editor.getDocumentProvider()
		// .getDocument(editor.getEditorInput()).get();
		//
		// StringTokenizer tokenizer = new StringTokenizer(editorText,
		// " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		// ArrayList editorWords = new ArrayList();
		// while (tokenizer.hasMoreTokens()) {
		// editorWords.add(tokenizer.nextToken());
		// }
		//
		// Collections.sort(editorWords, Collator.getInstance());
		// StringWriter displayText = new StringWriter();
		// for (int i = 0; i < editorWords.size(); i++) {
		// displayText.write(((String) editorWords.get(i)));
		// displayText.write(System.getProperty("line.separator"));
		// }
		// text.setText(displayText.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.ui.viewer.IViewerProvider#getViewer()
	 */
	@Override
	public Viewer getViewer() {
		return null;
		// return currentViewer;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	@Override
	public ISelection getSelection() {
		return null;
		// return editorSelection;
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setSelection(ISelection selection) {
		// editorSelection = selection;

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
	 */
	@Override
	public EditingDomain getEditingDomain() {
		return null;
	}
}
