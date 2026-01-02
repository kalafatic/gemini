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
package eu.kalafatic.gemini.media.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * The Class class AudioSystemPlayer.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class AudioSystemPlayer {

	// The shared instance
	/** The INSTANCE. */
	private static AudioSystemPlayer INSTANCE;

	/** The sounds enabled. */
	private boolean soundsEnabled;

	/** The ais. */
	private AudioInputStream ais;

	/** The clip. */
	private Clip clip;

	/**
	 * Instantiates a new audio system player.
	 */
	public AudioSystemPlayer() {}

	/**
	 * Gets the single instance of AudioSystemPlayer.
	 * @return single instance of AudioSystemPlayer
	 */
	public static AudioSystemPlayer getInstance() {
		if (INSTANCE == null) {
			synchronized (AudioSystemPlayer.class) {
				INSTANCE = new AudioSystemPlayer();
			}
		}
		return INSTANCE;
	}

	/**
	 * Play.
	 * @param f the f
	 */
	public void play(File f) {

		if (soundsEnabled) {

			try {
				ais = AudioSystem.getAudioInputStream(f);

				DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(ais);

				clip.start();

			} catch (UnsupportedAudioFileException uax) {
				JOptionPane.showMessageDialog(null, "Wrong format !", "Hey!", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} catch (LineUnavailableException eux) {
				JOptionPane.showMessageDialog(null, "Wrong URL !", "Hey!", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Wrong IO !", "Hey!", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
	}

	/**
	 * The main method.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// AudioSystemPlayer.getInstance().play(f)
	}

}
