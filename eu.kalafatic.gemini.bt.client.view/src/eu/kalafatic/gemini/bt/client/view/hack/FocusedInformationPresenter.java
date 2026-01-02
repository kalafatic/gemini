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
package eu.kalafatic.gemini.bt.client.view.hack;

/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHoverExtension;
import org.eclipse.jface.text.source.ILineRange;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class class FocusedInformationPresenter.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class FocusedInformationPresenter extends InformationPresenter {

	/**
	 * The Class class InformationProvider.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public final static class InformationProvider implements IInformationProvider, IInformationProviderExtension, IInformationProviderExtension2 {

		/** The hover region. */
		private IRegion fHoverRegion;

		/** The hover info. */
		private Object fHoverInfo;

		/** The control creator. */
		private IInformationControlCreator fControlCreator;

		/**
		 * Instantiates a new information provider.
		 * @param hoverRegion the hover region
		 * @param hoverInfo the hover info
		 * @param controlCreator the control creator
		 */
		public InformationProvider(IRegion hoverRegion, Object hoverInfo, IInformationControlCreator controlCreator) {
			fHoverRegion = hoverRegion;
			fHoverInfo = hoverInfo;
			fControlCreator = controlCreator;
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProvider#getSubject (org.eclipse.jface.text.ITextViewer, int)
		 */
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.information.IInformationProvider#getSubject(org.eclipse.jface.text.ITextViewer, int)
		 */
		@Override
		public IRegion getSubject(ITextViewer textViewer, int invocationOffset) {
			return fHoverRegion;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.information.IInformationProvider#getInformation(org.eclipse.jface.text.ITextViewer,
		 * org.eclipse.jface.text.IRegion)
		 */
		@SuppressWarnings("deprecation")
		@Deprecated
		@Override
		public String getInformation(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo == null ? null : fHoverInfo.toString();
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension# getInformation2(org.eclipse.jface.text.ITextViewer,
		 * org.eclipse.jface.text.IRegion)
		 * @since 3.2
		 */
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension#getInformation2(org.eclipse.jface.text.ITextViewer,
		 * org.eclipse.jface.text.IRegion)
		 */
		@Override
		public Object getInformation2(ITextViewer textViewer, IRegion subject) {
			return fHoverInfo;
		}

		/*
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension2 #getInformationPresenterControlCreator()
		 */
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
		 */
		@Override
		public IInformationControlCreator getInformationPresenterControlCreator() {
			return fControlCreator;
		}
	}

	/**
	 * The Class class DefaultInformationControlCreator.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	private static class DefaultInformationControlCreator implements IInformationControlCreator {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.IInformationControlCreator#createInformationControl(org.eclipse.swt.widgets.Shell)
		 */
		@Override
		public IInformationControl createInformationControl(Shell shell) {
			return new DefaultInformationControl(shell, true);
		}
	}

	/** The source viewer. */
	private final ISourceViewer fSourceViewer;

	/** The source viewer configuration. */
	private final SourceViewerConfiguration fSourceViewerConfiguration;

	/**
	 * Instantiates a new focused information presenter.
	 * @param sourceViewer the source viewer
	 * @param sourceViewerConfiguration the source viewer configuration
	 */
	public FocusedInformationPresenter(ISourceViewer sourceViewer, SourceViewerConfiguration sourceViewerConfiguration) {
		super(new DefaultInformationControlCreator());
		fSourceViewer = sourceViewer;
		fSourceViewerConfiguration = sourceViewerConfiguration;

		// sizes: see org.eclipse.jface.text.TextViewer.TEXT_HOVER_*_CHARS
		setSizeConstraints(100, 12, true, true);
		install(sourceViewer);
		setDocumentPartitioning(sourceViewerConfiguration.getConfiguredDocumentPartitioning(sourceViewer));
	}

	/**
	 * Open focused annotation hover.
	 * @param annotationHover the annotation hover
	 * @param line the line
	 * @return true, if successful
	 */
	public boolean openFocusedAnnotationHover(IAnnotationHover annotationHover, int line) {

		try {
			// compute the hover information
			Object hoverInfo;
			if (annotationHover instanceof IAnnotationHoverExtension) {
				IAnnotationHoverExtension extension = (IAnnotationHoverExtension) annotationHover;
				ILineRange hoverLineRange = extension.getHoverLineRange(fSourceViewer, line);
				if (hoverLineRange == null) {
					return false;
				}
				final int maxVisibleLines = Integer.MAX_VALUE; // allow any
																// number of
																// lines being
																// displayed, as
																// we support
																// scrolling
				hoverInfo = extension.getHoverInfo(fSourceViewer, hoverLineRange, maxVisibleLines);
			} else {
				hoverInfo = annotationHover.getHoverInfo(fSourceViewer, line);
			}

			// hover region: the beginning of the concerned line to place the
			// control right over the line
			IDocument document = fSourceViewer.getDocument();
			int offset = document.getLineOffset(line);
			String contentType = TextUtilities.getContentType(document, fSourceViewerConfiguration.getConfiguredDocumentPartitioning(fSourceViewer), offset, true);

			IInformationControlCreator controlCreator = null;
			if (annotationHover instanceof IInformationProviderExtension2) {
				controlCreator = ((IInformationProviderExtension2) annotationHover).getInformationPresenterControlCreator();
			} else if (annotationHover instanceof IAnnotationHoverExtension) {
				controlCreator = ((IAnnotationHoverExtension) annotationHover).getHoverControlCreator();
			}

			IInformationProvider informationProvider = new InformationProvider(new Region(offset, 0), hoverInfo, controlCreator);

			setOffset(offset);
			setAnchor(AbstractInformationControlManager.ANCHOR_RIGHT);
			setMargins(4, 0); // AnnotationBarHoverManager sets (5,0), minus
								// SourceViewer.GAP_SIZE_1
			setInformationProvider(informationProvider, contentType);
			showInformation();

			return true;

		} catch (BadLocationException e) {
			return false;
		}
	}
}
