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

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.about.AboutBundleGroupData;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

import eu.kalafatic.gemini.core.Activator;

/**
 * The Class class AboutHandler.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class AboutHandler extends AbstractHandler implements IHandler {

	/** The Constant DIALOG_IMAGE. */
	private static final Image DIALOG_IMAGE = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/branding/zodiac-102-77.jpg").createImage();

	/** The Constant DIALOG_TITLE. */
	private static final String DIALOG_TITLE = "About Gemini Project";

	/** The Constant NONE. */
	private static final int NONE = 0;

	/** The Constant DIALOG_BUTTONS_LABELS. */
	private static final String[] DIALOG_BUTTONS_LABELS = new String[] { IDialogConstants.OK_LABEL };

	/** The Constant DEFAULT_INDEX. */
	private static final int DEFAULT_INDEX = 0;

	/** The Constant DIALOG_MESSAGE_TITLE. */
	private static final String DIALOG_MESSAGE_TITLE = "\nWelcome to my Product based on Eclipse Product Configuration.\nProject Gemini is implementation of BitTorrent protocol.\n";

	/** The Constant COPYRIGHT_LINE. */
	private static final String COPYRIGHT_LINE = "Copyright (c) 2010, Petr Kalafatic";

	/**
	 * The Class class AboutDialog.
	 * @author Petr Kalafatic
	 * @version 3.0.0
	 * @project Gemini
	 */
	class AboutDialog extends MessageDialog {

		/**
		 * Instantiates a new about dialog.
		 * @param parentShell the parent shell
		 * @param dialogTitle the dialog title
		 * @param dialogTitleImage the dialog title image
		 * @param dialogMessage the dialog message
		 * @param dialogImageType the dialog image type
		 * @param dialogButtonLabels the dialog button labels
		 * @param defaultIndex the default index
		 */
		public AboutDialog(Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType, String[] dialogButtonLabels, int defaultIndex) {
			super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);

			// create a descriptive object for each BundleGroup
			IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
			List<AboutBundleGroupData> groups = new ArrayList<AboutBundleGroupData>();
			if (providers != null) {
				for (int i = 0; i < providers.length; ++i) {
					IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
					for (int j = 0; j < bundleGroups.length; ++j) {
						groups.add(new AboutBundleGroupData(bundleGroups[j]));
					}
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.MessageDialog#getImage()
		 */
		@Override
		public Image getImage() {
			return DIALOG_IMAGE;
		}

	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands .ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Shell parentShell = HandlerUtil.getActiveShell(event);

		String dialogMessage = getDialogMessage();
		AboutDialog dialog = new AboutDialog(parentShell, DIALOG_TITLE, DIALOG_IMAGE, dialogMessage, NONE, DIALOG_BUTTONS_LABELS, DEFAULT_INDEX);

		dialog.open();
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the dialog message.
	 * @return the dialog message
	 */
	private String getDialogMessage() {

		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		Dictionary<?, ?> dictionary = bundle.getHeaders();

		String versionLine = getVersionLine((String) dictionary.get("Bundle-version"));
		String buildLine = getBuildLine((String) dictionary.get("Bundle-version"));
		return DIALOG_MESSAGE_TITLE + "\n\n\n" + versionLine + "\n" + buildLine + "\n\n" + COPYRIGHT_LINE;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the version line.
	 * @param bundleVersion the bundle version
	 * @return the version line
	 */
	private String getVersionLine(String bundleVersion) {
		int index = bundleVersion.lastIndexOf('.');

		return "Version: " + bundleVersion.substring(0, index);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the builds the line.
	 * @param bundleVersion the bundle version
	 * @return the builds the line
	 */
	private String getBuildLine(String bundleVersion) {
		int index = bundleVersion.lastIndexOf('.');

		return "Build: " + bundleVersion.substring(index + 1);
	}
}
