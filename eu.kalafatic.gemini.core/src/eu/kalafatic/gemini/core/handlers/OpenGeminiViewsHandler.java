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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;

import eu.kalafatic.gemini.core.dialogs.ShowViewsDialog;
import eu.kalafatic.gemini.core.hack.Category;
import eu.kalafatic.gemini.core.lib.constants.FTextConstants;

/**
 * The Class class OpenGeminiViewsHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class OpenGeminiViewsHandler extends AbstractHandler {

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		final IWorkbenchPage page = window.getActivePage();
		if (page == null) {
			return null;
		}
		ShowViewsDialog showViewDialog = new ShowViewsDialog(window, getCategories());
		showViewDialog.setBlockOnOpen(true);
		showViewDialog.open();

		final IViewDescriptor[] descriptors = showViewDialog.getSelection();
		for (int i = 0; i < descriptors.length; ++i) {
			try {
				page.showView(descriptors[i].getId());
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
		if (showViewDialog.getReturnCode() == Window.CANCEL) {
			return null;
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the categories.
	 * @return the categories
	 */
	public List<Category> getCategories() {
		IViewDescriptor[] views = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench().getViewRegistry().getViews();

		Map<Object, Category> categories = Category.INSTANCE.categories;
		categories.clear();
		Map<String, Category> result = new HashMap<String, Category>();
		Category parentCategory, category;

		for (IViewDescriptor iViewDescriptor : views) {
			if (!iViewDescriptor.getId().startsWith(FTextConstants.DOMAIN_FILTER)) {
				continue;
			}
			String[] categoryPath = iViewDescriptor.getCategoryPath();

			if (categoryPath != null) {
				String sb = categoryPath[0];

				if (categories.containsKey(sb)) {
					parentCategory = categories.get(sb);
				} else {
					parentCategory = new Category(null, sb, sb, sb);
					categories.put(sb, parentCategory);
					result.put(sb, parentCategory);
				}
				for (int i = 1; i < categoryPath.length; i++) {
					sb += File.separator.concat(categoryPath[i]);

					if (!categories.containsKey(sb)) {
						category = new Category(parentCategory, categoryPath[i], categoryPath[i], sb);
						categories.put(sb, category);
						parentCategory.addElement(category);
					} else {
						category = categories.get(sb);
					}
					parentCategory = category;
				}
				if (parentCategory != null) {
					parentCategory.addElement(iViewDescriptor);
					categories.put(iViewDescriptor, parentCategory);
				}
			}
		}
		return new ArrayList<Category>(result.values());
	}
}
