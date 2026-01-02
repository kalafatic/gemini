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

import java.awt.BorderLayout;
import java.awt.Frame;
import java.net.URI;

import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;

import com.sun.media.jmc.JMediaPane;

/**
 * The Class class VideoControl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class VideoControl {

	/** The player. */
	private JMediaPane player;

	/** The pane. */
	private JRootPane pane;

	/**
	 * Instantiates a new video control.
	 * @param parent the parent
	 */
	public VideoControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);

		/* This AWT construction is meant to reduce flicker as much as possible. */
		Frame frame = SWT_AWT.new_Frame(composite);
		JPanel panel = new JPanel(new BorderLayout());
		frame.add(panel);
		this.pane = new JRootPane();
		panel.add(this.pane);
	}

	/**
	 * Play.
	 */
	public void play() {
		if (this.player != null) {
			this.player.play();
		}
	}

	/**
	 * Stop.
	 */
	public void stop() {
		if (this.player != null) {
			this.player.stop();
		}
	}

	/**
	 * Pause.
	 */
	public void pause() {
		if (this.player != null) {
			this.player.pause();
		}
	}

	/**
	 * Sets the source.
	 * @param uri the new source
	 */
	public void setSource(URI uri) {

		if (this.player != null) {
			this.pane.remove(this.player);
		}
		try {
			this.player = new JMediaPane(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pane.setContentPane(player);
		this.pane.revalidate();
	}

	/**
	 * Checks if is playing.
	 * @return true, if is playing
	 */
	public boolean isPlaying() {
		if (this.player != null) {
			return this.player.isPlaying();
		}
		return false;
	}
}
