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
package eu.kalafatic.gemini.bt.tracker.controller.providers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import eu.kalafatic.gemini.bt.tracker.model.tracker.Communication;

/**
 * The Class class TrackerTableLabelProvider.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TrackerTableLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

	/** The formatter. */
	private Format formatter = new SimpleDateFormat("HH.mm.ss/dd.MM.yyyy");

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof Entry<?, ?>) {

			switch (columnIndex) {
			case 0:

				break;
			case 1:

				break;

			case 2:
				break;
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang .Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {

		if (element instanceof Communication) {
			Communication communication = (Communication) element;

			switch (columnIndex) {

			case 0:
				return formatter.format(communication.getDate());
			case 1:
				return communication.getRequest() + "\n" + communication.getResponse();
			}
		}
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang .Object, int)
	 */
	@Override
	public Color getBackground(Object element, int columnIndex) {
		return null;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.lang .Object, int)
	 */
	@Override
	public Color getForeground(Object element, int columnIndex) {
		return null;
	}

}
