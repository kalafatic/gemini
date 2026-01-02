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
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.PROGRESS_FONT;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class class DefaultInformationControl.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class DefaultInformationControl extends AbstractInformationControl implements DisposeListener {

	/**
	 * The Interface interface IInformationPresenter.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public interface IInformationPresenter {

		/**
		 * Update presentation.
		 * @param display the display
		 * @param hoverInfo the hover info
		 * @param presentation the presentation
		 * @param maxWidth the max width
		 * @param maxHeight the max height
		 * @return the string
		 */
		@Deprecated
		String updatePresentation(Display display, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight);
	}

	/**
	 * The Interface interface IInformationPresenterExtension.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	public interface IInformationPresenterExtension {

		/**
		 * Update presentation.
		 * @param drawable the drawable
		 * @param hoverInfo the hover info
		 * @param presentation the presentation
		 * @param maxWidth the max width
		 * @param maxHeight the max height
		 * @return the string
		 */
		String updatePresentation(Drawable drawable, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight);
	}

	/** The Constant INNER_BORDER. */
	private static final int INNER_BORDER = 1;

	/** The text. */
	public StyledText fText;

	/** The presenter. */
	public final IInformationPresenter fPresenter;

	/** The presentation. */
	public final TextPresentation fPresentation = new TextPresentation();

	/** The Constant shellStyle. */
	private final static int shellStyle = SWT.RESIZE | SWT.TOOL | SWT.ON_TOP;
	// private final int fAdditionalTextStyles = SWT.V_SCROLL | SWT.H_SCROLL;
	/** The additional text styles. */
	private final int fAdditionalTextStyles = SWT.NONE;

	/**
	 * Instantiates a new default information control.
	 * @param parent the parent
	 * @param statusFieldText the status field text
	 * @param presenter the presenter
	 */
	public DefaultInformationControl(Shell parent, String statusFieldText, IInformationPresenter presenter) {
		super(parent, shellStyle, statusFieldText, null);
		fPresenter = presenter;
		create();
	}

	// ---------------------------------------------------------------

	/**
	 * Instantiates a new default information control.
	 * @param parent the parent
	 * @param toolBarManager the tool bar manager
	 * @param presenter the presenter
	 */
	public DefaultInformationControl(Shell parent, ToolBarManager toolBarManager, IInformationPresenter presenter) {
		super(parent, toolBarManager);

		fPresenter = presenter;
		create();
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#createContent(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createContent(Composite parent) {
		fText = new StyledText(parent, SWT.MULTI | SWT.READ_ONLY | fAdditionalTextStyles);
		fText.setForeground(parent.getForeground());
		fText.setBackground(parent.getBackground());
		fText.setFont(PROGRESS_FONT);
		// fText.setFont(JFaceResources.getTextFont());
		FillLayout layout = (FillLayout) parent.getLayout();

		if (fText.getWordWrap()) {
			layout.marginHeight = INNER_BORDER;
			layout.marginWidth = INNER_BORDER;
		} else {
			fText.setIndent(INNER_BORDER);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#setInformation(java.lang.String)
	 */
	@Override
	public void setInformation(String content) {
		if (fPresenter == null) {
			fText.setText(content);
		} else {
			fPresentation.clear();
			int maxWidth = -1;
			int maxHeight = -1;
			Point constraints = getSizeConstraints();
			if (constraints != null) {
				maxWidth = constraints.x;
				maxHeight = constraints.y;
				if (fText.getWordWrap()) {
					maxWidth -= INNER_BORDER * 2;
					maxHeight -= INNER_BORDER * 2;
				} else {
					maxWidth -= INNER_BORDER; // indent
				}
				Rectangle trim = computeTrim();
				maxWidth -= trim.width;
				maxHeight -= trim.height;
				maxWidth -= fText.getCaret().getSize().x;
			}
			maxHeight = Integer.MAX_VALUE;

			if (fPresenter instanceof IInformationPresenterExtension) {
				content = ((IInformationPresenterExtension) fPresenter).updatePresentation(fText, content, fPresentation, maxWidth, maxHeight);
			} else {
				content = fPresenter.updatePresentation(getShell().getDisplay(), content, fPresentation, maxWidth, maxHeight);
			}
			if (content != null) {
				fText.setText(content);
				TextPresentation.applyTextPresentation(fPresentation, fText);
			} else {
				fText.setText("");
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension2#setInput(java.lang.Object)
	 */
	@Override
	public void setInput(Object content) {
		setInformation((String) content);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			if (fText.getWordWrap()) {
				layout();
			}
		}
		super.setVisible(visible);
	}

	// ---------------------------------------------------------------

	/**
	 * Layout.
	 */
	public void layout() {
		Point currentSize = getShell().getSize();
		getShell().pack(true);
		Point newSize = getShell().getSize();
		if (newSize.x > currentSize.x || newSize.y > currentSize.y) {
			setSize(currentSize.x, currentSize.y);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#computeSizeHint()
	 */
	@Override
	public Point computeSizeHint() {
		int widthHint = SWT.DEFAULT;
		Point constraints = getSizeConstraints();
		if (constraints != null && fText.getWordWrap()) {
			widthHint = constraints.x;
		}
		return getShell().computeSize(widthHint, SWT.DEFAULT, true);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#computeTrim()
	 */
	@Override
	public Rectangle computeTrim() {
		return Geometry.add(super.computeTrim(), fText.computeTrim(0, 0, 0, 0));
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#setForegroundColor(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setForegroundColor(Color foreground) {
		super.setForegroundColor(foreground);
		fText.setForeground(foreground);
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.view.hack.AbstractInformationControl#setBackgroundColor(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setBackgroundColor(Color background) {
		super.setBackgroundColor(background);
		fText.setBackground(background);
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IInformationControlExtension#hasContents()
	 */
	@Override
	public boolean hasContents() {
		return fText.getCharCount() > 0;
	}

	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
	 */
	@Deprecated
	@Override
	public void widgetDisposed(DisposeEvent event) {}

}
