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
package eu.kalafatic.gemini.media.view.views;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * The Class class VideoPlayer.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class VideoPlayer {

	/** The parent. */
	private Composite parent;

	/** The video. */
	private VideoControl video;

	/** The play button. */
	private Button playButton;

	/** The open button. */
	private Button openButton;

	/**
	 * Instantiates a new video player.
	 * @param parent the parent
	 */
	public VideoPlayer(Composite parent) {
		this.parent = parent;
		Composite playerComposite = new Composite(parent, SWT.NONE);
		playerComposite.setLayout(new GridLayout(1, false));

		Composite videoComposite = new Composite(playerComposite, SWT.NONE);
		videoComposite.setLayout(new FillLayout());
		videoComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.video = new VideoControl(videoComposite);

		Composite buttonComposite = new Composite(playerComposite, SWT.NONE);
		buttonComposite.setLayout(new RowLayout());

		this.openButton = new Button(buttonComposite, SWT.PUSH);
		openButton.setText("Open");
		openButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				video.stop();
				// File file = new File("c:/lib/1.avi");
				File file = new File("c:/lib/1.avi");

				URI uri = file.toURI();

				// try {
				// uri = new URL("file:///" + file.getAbsolutePath()).toURI();
				// } catch (MalformedURLException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// } catch (URISyntaxException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

				// URI uri = file.toURI();

				// URI uri = getVideoUri();

				if (uri != null) {

					video.setSource(uri);
				}
				updateButtons();
			}
		});

		this.playButton = new Button(buttonComposite, SWT.PUSH);
		playButton.setText("Play");
		playButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (video.isPlaying()) {
					video.pause();
				} else {
					video.play();
				}
				updateButtons();
			}
		});

		RowData buttonData = new RowData();
		buttonData.width = 100;

		openButton.setLayoutData(buttonData);
		playButton.setLayoutData(buttonData);
	}

	/**
	 * Gets the video uri.
	 * @return the video uri
	 */
	protected URI getVideoUri() {
		InputDialog dialog = new InputDialog(parent.getShell(), "Video URI", "Enter a valid URI for the video file you wish to play.", "", new IInputValidator() {

			@Override
			public String isValid(String newText) {
				try {
					new URI(newText);
				} catch (Exception e) {
					return "Invalid URI";
				}
				return null;
			}

		});
		if (dialog.open() == Dialog.OK) {
			;
			try {
				return new URI(dialog.getValue());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Update buttons.
	 */
	protected void updateButtons() {
		if (this.video.isPlaying()) {
			this.playButton.setText("Pause");
		} else {
			this.playButton.setText("Play");
		}
	}
}
