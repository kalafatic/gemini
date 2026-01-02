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
package eu.kalafatic.gemini.core.utils;

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.AUDIO_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.EXE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.FILE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.MSI_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.PDF_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.RAR_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TORRENT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.TXT_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.VIDEO_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.WORD_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ZIP_IMG;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.misc.StatusUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.lib.EExt;

/**
 * The Class class ImageUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
public class ImageUtils {

	/** The image loader. */
	private static ImageLoader imageLoader = new ImageLoader();;

	/** The image data array. */
	private static ImageData[] imageDataArray;

	/** The animated. */
	private static Image[] animated;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Gets the file extension image.
	 * @param fileName the file name
	 * @return the file extension image
	 */
	public static Image getFileExtensionImage(String fileName) {
		if (fileName.endsWith(".txt")) {
			return TXT_IMG;
		} else if (fileName.endsWith(".pdf")) {
			return PDF_IMG;
		} else if (fileName.endsWith(".doc")) {
			return WORD_IMG;
		} else if (fileName.endsWith(".avi")) {
			return VIDEO_IMG;
		} else if (fileName.endsWith(".txt")) {
			return TXT_IMG;
		} else if (fileName.endsWith(".mp3")) {
			return AUDIO_IMG;
		} else if (fileName.endsWith(".rar")) {
			return RAR_IMG;
		} else if (fileName.endsWith(".zip")) {
			return ZIP_IMG;
		} else if (fileName.endsWith(".exe")) {
			return EXE_IMG;
		} else if (fileName.endsWith(".msi")) {
			return MSI_IMG;
		} else if (fileName.endsWith(EExt.TORRENT.ext)) {
			return TORRENT_IMG;
		}

		return FILE_IMG;
	}

	// ---------------------------------------------------------------

	/**
	 * Load gif.
	 * @param path the path
	 * @return the image[]
	 */
	public static Image[] loadGif(String path) {
		File imageFile = Activator.getImageFile(path);
		imageDataArray = imageLoader.load(imageFile.getAbsolutePath());
		animated = new Image[imageDataArray.length];

		for (int i = 0; i < imageDataArray.length; i++) {
			animated[i] = new Image(Display.getDefault(), imageDataArray[i]);
		}
		return animated;
	}

	// ---------------------------------------------------------------

	/**
	 * Load image.
	 * @param path the path
	 * @return the image
	 */
	public static Image loadImage(String path) {
		Image image = null;
		if (path != null) {
			InputStream input = null;
			try {
				input = new BufferedInputStream(new FileInputStream(path));
				image = new Image(Display.getDefault(), input);
			} catch (SWTException e) {
				StatusManager.getManager().handle(StatusUtil.newStatus(WorkbenchPlugin.PI_WORKBENCH, e));
			} catch (IOException e) {
				StatusManager.getManager().handle(StatusUtil.newStatus(WorkbenchPlugin.PI_WORKBENCH, e));
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// he's done for
					}
				}
			}
		}
		return image;
	}

	// ---------------------------------------------------------------

	/**
	 * Resize.
	 * @param image the image
	 * @param width the width
	 * @param height the height
	 * @return the image
	 */
	@SuppressWarnings("unused")
	private static Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
	}
}
