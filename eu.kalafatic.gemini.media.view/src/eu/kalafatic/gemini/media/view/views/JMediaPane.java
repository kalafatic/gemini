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

import java.awt.Dimension;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;

import javax.swing.JComponent;

import com.sun.media.jmc.MediaCorruptedException;
import com.sun.media.jmc.MediaException;
import com.sun.media.jmc.MediaInaccessibleException;
import com.sun.media.jmc.MediaProvider;
import com.sun.media.jmc.MediaUnavailableException;
import com.sun.media.jmc.MediaUnsupportedException;
import com.sun.media.jmc.OperationUnsupportedException;
import com.sun.media.jmc.control.AudioControl;
import com.sun.media.jmc.control.VideoControl;
import com.sun.media.jmc.event.MediaTimeListener;

// Referenced classes of package com.sun.media.jmc: 
//            MediaProvider, MediaException, MediaUnavailableException, MediaUnsupportedException,  
//            MediaCorruptedException, MediaInaccessibleException, OperationUnsupportedException 

/**
 * The Class class JMediaPane.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class JMediaPane extends JComponent {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The media provider. */
	private MediaProvider mediaProvider;

	/** The video control. */
	private VideoControl videoControl;

	/** The audio control. */
	private AudioControl audioControl;

	/** The volume level. */
	private float volumeLevel;

	/** The media pane. */
	private JComponent mediaPane;

	/** The auto play. */
	private boolean autoPlay;

	/** The preserve aspect. */
	private boolean preserveAspect;

	/** The play requested. */
	private boolean playRequested;

	/** The is muted. */
	private boolean isMuted;

	/** The Constant TIME_UNKNOWN. */
	public static final double TIME_UNKNOWN = (-1.0D / 0.0D);

	/** The MEDI a_ sourc e_ changed. */
	final String MEDIA_SOURCE_CHANGED = "SOURCE_CHANGED";

	/** The Constant VOLUME_CHANGED. */
	static final String VOLUME_CHANGED = "VOLUME_CHANGED";

	/** The Constant MUTE_CHANGED. */
	static final String MUTE_CHANGED = "MUTE_CHANGED";

	/** The Constant REPEATING_CHANGED. */
	static final String REPEATING_CHANGED = "REPEATING_CHANGED";

	/** The Constant MEDIA_TIME_CHANGED. */
	static final String MEDIA_TIME_CHANGED = "MEDIA_TIME_CHANGED";

	/** The Constant SEEKABLE_CHANGED. */
	static final String SEEKABLE_CHANGED = "SEEKABLE_CHANGED";

	/** The Constant PAUSED_CHANGED. */
	static final String PAUSED_CHANGED = "PAUSED_CHANGED";

	/**
	 * The Class class PropertyChangeForwarder.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	private class PropertyChangeForwarder implements PropertyChangeListener {

		/*
		 * (non-Javadoc)
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void propertyChange(PropertyChangeEvent propertychangeevent) {
			firePropertyChange(propertychangeevent.getPropertyName(), propertychangeevent.getOldValue(), propertychangeevent.getNewValue());
		}

		/** The source. */
		Object source;

		/** The this$0. */
		final JMediaPane this$0;

		/**
		 * Instantiates a new property change forwarder.
		 * @param obj the obj
		 */
		PropertyChangeForwarder(Object obj) {
			this$0 = JMediaPane.this;
			// super();
			source = obj;
		}
	}

	/**
	 * Instantiates a new j media pane.
	 */
	public JMediaPane() {
		autoPlay = false;
		preserveAspect = true;
		playRequested = false;
		isMuted = false;
		volumeLevel = 1.0F;
		mediaProvider = new MediaProvider();
		setOpaque(false);
		setBackground(null);
		mediaProvider.addPropertyChangeListener(new PropertyChangeForwarder(this));
	}

	/**
	 * Instantiates a new j media pane.
	 * @param uri the uri
	 * @throws MediaUnavailableException the media unavailable exception
	 * @throws MediaUnsupportedException the media unsupported exception
	 * @throws MediaCorruptedException the media corrupted exception
	 * @throws MediaInaccessibleException the media inaccessible exception
	 */
	public JMediaPane(URI uri) throws MediaUnavailableException, MediaUnsupportedException, MediaCorruptedException, MediaInaccessibleException {
		this();
		setSource(uri);
	}

	/**
	 * Sets the source.
	 * @param uri the new source
	 * @throws MediaUnsupportedException the media unsupported exception
	 * @throws MediaUnavailableException the media unavailable exception
	 * @throws MediaCorruptedException the media corrupted exception
	 * @throws MediaInaccessibleException the media inaccessible exception
	 */
	public void setSource(URI uri) throws MediaUnsupportedException, MediaUnavailableException, MediaCorruptedException, MediaInaccessibleException {
		if (mediaProvider != null) {
			try {
				mediaProvider.setSource(uri);
			} catch (MediaException mediaexception) {
				throw mediaexception;
			}
		} else {
			mediaProvider = new MediaProvider(uri);
		}
		videoControl = mediaProvider.getControl(VideoControl.class);
		if (videoControl == null) {
			System.out.println("Null VideoControl");
			return;
		}
		audioControl = mediaProvider.getControl(AudioControl.class);
		removeAll();
		mediaPane = videoControl.getVideoPane();
		add(mediaPane);
		Rectangle rectangle = getBounds();
		mediaPane.setBounds(0, 0, rectangle.width, rectangle.height);
		validate();
		if (autoPlay) {
			play();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		if (mediaPane != null) {
			return mediaPane.getPreferredSize();
		} else {
			return super.getPreferredSize();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.Container#doLayout()
	 */
	@Override
	public void doLayout() {
		Dimension dimension = getSize();
		Rectangle rectangle = getBounds();
		if (mediaPane != null) {
			mediaPane.setBounds(0, 0, rectangle.width, rectangle.height);
		} else {
			System.out.println("null mediaPane");
		}
		invalidate();
	}

	/**
	 * Gets the source.
	 * @return the source
	 */
	public URI getSource() {
		return mediaProvider.getSource();
	}

	/**
	 * Play.
	 */
	public void play() {
		if (mediaProvider != null) {
			mediaProvider.play();
		}
		playRequested = true;
	}

	/**
	 * Stop.
	 */
	public void stop() {
		mediaProvider.pause();
		mediaProvider.setMediaTime(0.0D);
	}

	/**
	 * Pause.
	 */
	public void pause() {
		mediaProvider.pause();
		playRequested = false;
	}

	/**
	 * Sets the volume.
	 * @param f the f
	 * @return the float
	 */
	public float setVolume(float f) {
		if (audioControl != null) {
			float f1 = audioControl.setVolume(f);
			if (f1 != -1F) {
				volumeLevel = f1;
			}
		}
		return volumeLevel;
	}

	/**
	 * Gets the volume.
	 * @return the volume
	 */
	public float getVolume() {
		if (audioControl != null) {
			float f = audioControl.getVolume();
			if (f != -1F) {
				volumeLevel = f;
			}
		}
		return volumeLevel;
	}

	/**
	 * Sets the mute.
	 * @param flag the new mute
	 */
	public void setMute(boolean flag) {
		isMuted = flag;
		if (audioControl != null) {
			audioControl.setMute(isMuted);
		}
	}

	/**
	 * Checks if is mute.
	 * @return true, if is mute
	 */
	public boolean isMute() {
		if (audioControl != null) {
			isMuted = audioControl.isMuted();
		}
		return isMuted;
	}

	/**
	 * Sets the repeating.
	 * @param flag the new repeating
	 */
	public void setRepeating(boolean flag) {
		if (mediaProvider != null) {
			mediaProvider.setRepeating(flag);
		}
	}

	/**
	 * Checks if is repeating.
	 * @return true, if is repeating
	 */
	public boolean isRepeating() {
		if (mediaProvider != null) {
			return mediaProvider.isRepeating();
		} else {
			return false;
		}
	}

	/**
	 * Sets the auto play.
	 * @param flag the new auto play
	 */
	public void setAutoPlay(boolean flag) {
		autoPlay = flag;
	}

	/**
	 * Checks if is auto play.
	 * @return true, if is auto play
	 */
	public boolean isAutoPlay() {
		return autoPlay;
	}

	/**
	 * Checks if is seekable.
	 * @return true, if is seekable
	 */
	public boolean isSeekable() {
		Boolean boolean1 = mediaProvider.getCapability(MediaProvider.CapabilityKey.SUPPORTS_SEEKING, Boolean.class);
		return boolean1.booleanValue();
	}

	/**
	 * Seek.
	 * @param d the d
	 * @return the double
	 * @throws OperationUnsupportedException the operation unsupported exception
	 */
	public double seek(double d) throws OperationUnsupportedException {
		return mediaProvider.setMediaTime(d);
	}

	/**
	 * Sets the media time.
	 * @param d the d
	 * @return the double
	 */
	public double setMediaTime(double d) {
		return mediaProvider.setMediaTime(d);
	}

	/**
	 * Gets the media time.
	 * @return the media time
	 */
	public double getMediaTime() {
		return mediaProvider.getMediaTime();
	}

	/**
	 * Gets the duration.
	 * @return the duration
	 */
	public double getDuration() {
		return mediaProvider.getDuration();
	}

	/**
	 * Sets the start time.
	 * @param d the d
	 * @return the double
	 */
	public double setStartTime(double d) {
		return mediaProvider.setStartTime(d);
	}

	/**
	 * Sets the stop time.
	 * @param d the d
	 * @return the double
	 */
	public double setStopTime(double d) {
		return mediaProvider.setStopTime(d);
	}

	/**
	 * Sets the rate.
	 * @param d the d
	 * @return the double
	 */
	public double setRate(double d) {
		return mediaProvider.setRate(d);
	}

	/**
	 * Gets the rate.
	 * @return the rate
	 */
	public double getRate() {
		return mediaProvider.getRate();
	}

	/**
	 * Checks if is playing.
	 * @return true, if is playing
	 */
	public boolean isPlaying() {
		return mediaProvider.isPlaying();
	}

	/**
	 * Sets the preserve aspect ratio.
	 * @param flag the new preserve aspect ratio
	 */
	public void setPreserveAspectRatio(boolean flag) {
		preserveAspect = flag;
		if (videoControl != null) {
			if (flag) {
				videoControl.setResizeBehavior(com.sun.media.jmc.control.VideoControl.ResizeBehavior.Preserve);
			} else {
				videoControl.setResizeBehavior(com.sun.media.jmc.control.VideoControl.ResizeBehavior.Stretch);
			}
		}
	}

	/**
	 * Checks if is preserve aspect ratio.
	 * @return true, if is preserve aspect ratio
	 */
	public boolean isPreserveAspectRatio() {
		return preserveAspect;
	}

	/**
	 * Adds the notification time.
	 * @param d the d
	 * @param mediatimelistener the mediatimelistener
	 * @throws OperationUnsupportedException the operation unsupported exception
	 */
	public void addNotificationTime(double d, MediaTimeListener mediatimelistener) throws OperationUnsupportedException {
		mediaProvider.addNotificationTime(d, mediatimelistener);
	}

	/**
	 * Checks if is notification time supported.
	 * @return true, if is notification time supported
	 */
	public boolean isNotificationTimeSupported() {
		return true;
	}

	/**
	 * Removes the notification time.
	 * @param d the d
	 * @param mediatimelistener the mediatimelistener
	 */
	public void removeNotificationTime(double d, MediaTimeListener mediatimelistener) {
		mediaProvider.removeNotificationTime(d, mediatimelistener);
	}

}
