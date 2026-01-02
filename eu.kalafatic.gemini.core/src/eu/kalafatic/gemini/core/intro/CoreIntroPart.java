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
package eu.kalafatic.gemini.core.intro;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

/**
 * The Class class CoreIntroPart.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CoreIntroPart implements IIntroPart {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#addPropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	@Override
	public void addPropertyListener(IPropertyListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#dispose()
	 */
	@Override
	public void dispose() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#getIntroSite()
	 */
	@Override
	public IIntroSite getIntroSite() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#getTitle()
	 */
	@Override
	public String getTitle() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#getTitleImage()
	 */
	@Override
	public Image getTitleImage() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#init(org.eclipse.ui.intro.IIntroSite, org.eclipse.ui.IMemento)
	 */
	@Override
	public void init(IIntroSite site, IMemento memento) throws PartInitException {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#removePropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	@Override
	public void removePropertyListener(IPropertyListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#saveState(org.eclipse.ui.IMemento)
	 */
	@Override
	public void saveState(IMemento memento) {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#standbyStateChanged(boolean)
	 */
	@Override
	public void standbyStateChanged(boolean standby) {

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
