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

import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.OPEN;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PAUSE;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.PLAY;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.STOP;

import java.awt.Canvas;
import java.awt.Frame;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.DefaultFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsCanvas;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.utils.FileUtils;
import eu.kalafatic.gemini.core.utils.Log;
import eu.kalafatic.gemini.media.controller.lib.EMediaPreferences;

/**
 * The Class class VideoPlayerView.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class VideoPlayerView extends ViewPart implements Listener {

	/** The factory. */
	private GUIFactory factory;

	/** The player. */
	private JMediaPane player;

	/** The pane. */
	private JRootPane pane;

	/** The Constant FILTER_NAMES. */
	private static final String[] FILTER_NAMES = { "OpenOffice.org Spreadsheet Files (*.sxc)", "Microsoft Excel Spreadsheet Files (*.xls)", "Comma Separated Values Files (*.csv)", "All Files (*.*)" };

	/** The Constant FILTER_EXTS. */
	private static final String[] FILTER_EXTS = { "*.avi", "*.flv", "*.csv", "*.*" };

	/** The paths. */
	List<String> paths = new ArrayList<String>();

	/** The media path. */
	private String mediaPath;

	/** The action group. */
	private Group actionGroup;

	/** The stop btn. */
	private Button openBtn, playBtn, stopBtn;

	/** The address combo. */
	private Combo addressCombo;

	/** The panel. */
	private JPanel panel;

	/** The frame. */
	private Frame frame;

	/** The player composite. */
	private Composite playerComposite;

	/** The media player factory. */
	private MediaPlayerFactory mediaPlayerFactory;

	/** The media player. */
	private EmbeddedMediaPlayer mediaPlayer;

	/** The video surface. */
	private Canvas videoSurface;

	/** The media player component. */
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets .Composite)
	 */
	@Override
	public void createPartControl(final Composite parent) {
		// new VideoPlayer(parent);

		factory = new GUIFactory(parent);
		Composite container = factory.createComposite(parent, 2);

		addressCombo = new Combo(container, SWT.SIMPLE | SWT.DROP_DOWN);
		addressCombo.setToolTipText("Path");
		addressCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		openBtn = factory.createButton(container, OPEN, SWT.PUSH);

		Composite composite = factory.createComposite(container, 1, SWT.BORDER);
		composite.setLayout(new FillLayout());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);

		playerComposite = new Composite(composite, SWT.EMBEDDED | SWT.NO_BACKGROUND);

		frame = SWT_AWT.new_Frame(playerComposite);

		System.setProperty("jna.library.path", "c:/Program Files/VideoLAN/VLC/");

		if (RuntimeUtil.isWindows()) {
			videoSurface = new WindowsCanvas();
		} else {
			videoSurface = new Canvas();
		}
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);

		videoSurface.setBackground(java.awt.Color.black);
		frame.add(videoSurface);
		playerComposite.setBounds(100, 100, 450, 200);
		playerComposite.setVisible(true);

		actionGroup = factory.createGroup(container, "Controls", 3);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_CENTER;
		actionGroup.setLayoutData(gridData);

		createPlayerActionsBox(actionGroup);

		initPlayer();
		initButtons();
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the player.
	 */
	private void initPlayer() {

		List<String> vlcArgs = new ArrayList<String>();

		// vlcArgs.add("--ffmpeg-hw"); // <--- if your system supports it, this
		// might be useful
		vlcArgs.add("--no-plugins-cache");
		vlcArgs.add("--no-video-title-show");
		vlcArgs.add("--no-snapshot-preview");
		vlcArgs.add("--quiet");
		vlcArgs.add("--quiet-synchro");
		vlcArgs.add("--intf");
		vlcArgs.add("dummy");

		FullScreenStrategy fullScreenStrategy = new DefaultFullScreenStrategy(frame);

		mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
		mediaPlayerFactory.setUserAgent("vlcj test player");

		mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(fullScreenStrategy);
		// mediaPlayer.setVideoSurface(videoSurface);

		mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));

		mediaPlayer.setPlaySubItems(true);

		mediaPlayer.setEnableKeyInputHandling(false);
		mediaPlayer.setEnableMouseInputHandling(false);

		frame.pack();
		frame.setVisible(true);
		videoSurface.setVisible(true);

	}

	// ---------------------------------------------------------------

	/**
	 * Inits the buttons.
	 */
	private void initButtons() {
		openBtn.addListener(SWT.Selection, this);
		playBtn.addListener(SWT.Selection, this);
		stopBtn.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget == openBtn) {
			openFile(actionGroup.getShell());
		} else if (event.widget == playBtn) {
			if (isPlaying()) {
				pause();
			} else {
				play();
			}
		} else if (event.widget == stopBtn) {
			stop();
		}
		updateButtons();
	}

	// ---------------------------------------------------------------

	/**
	 * Update buttons.
	 */
	private void updateButtons() {
		if (isPlaying()) {
			playBtn.setText(PAUSE);
		} else {
			playBtn.setText(PLAY);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Checks if is playing.
	 * @return true, if is playing
	 */
	private boolean isPlaying() {
		if (player != null) {
			return player.isPlaying();
		}
		return false;
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the source.
	 * @param path the new source
	 */
	public void setSource(final String path) {
		try {
			if (player != null) {
				pane.remove(player);
			}

			File file = new File(path);
			URI uri = file.toURI();

			mediaPlayer.setRepeat(true);
			mediaPlayer.playMedia(path);

		} catch (Exception t) {
			// Log the exception and continue
			System.err.println(t);

			// } catch (Exception e) {
			// Log.log(EModule.MEDIA, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Open file.
	 * @param shell the shell
	 * @return the file
	 */
	private File openFile(Shell shell) {
		try {
			mediaPath = FileUtils.getInstance().openFile(true, "c:/lib/");

			if (!mediaPath.isEmpty()) {
				if (paths.contains(mediaPath)) {
					addressCombo.setText(mediaPath);
				} else {
					paths.add(0, mediaPath);
					addressCombo.add(mediaPath);
					addressCombo.setItems(paths.toArray(new String[paths.size()]));
					addressCombo.select(0);
				}
				stop();
				setSource(mediaPath);
				// play();
			}
		} catch (Exception e) {
			Log.log(EMediaPreferences.MODULE, e);
		}
		return new File(mediaPath);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the player actions box.
	 * @param group the group
	 */
	private void createPlayerActionsBox(Group group) {
		playBtn = factory.createButton(group, PLAY, SWT.PUSH);
		stopBtn = factory.createButton(group, STOP, SWT.PUSH);
	}

	// ---------------------------------------------------------------

	/**
	 * Play.
	 */
	public void play() {
		try {
			if (player != null) {
				player.play();
			}
		} catch (Exception e) {
			Log.log(EMediaPreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Stop.
	 */
	public void stop() {
		if (player != null) {
			player.stop();
			// player.updateUI();
			// player.validate();
			// pane.remove(player);
			// player = null;
			// // pane.revalidate();
			// frame.pack();
			// frame.validate();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Pause.
	 */
	public void pause() {
		if (player != null) {
			player.pause();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {}
}
