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
package eu.kalafatic.gemini.bt.client.view.preferences;

import static eu.kalafatic.gemini.core.lib.constants.FConstants.MEMORY_SIZES;
import static eu.kalafatic.gemini.core.lib.constants.FConstants.NUMBERS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class TransportPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class TransportPreferencePage extends APreferencePage {

	/** The factory. */
	private GUIFactory factory;

	/** The transport buffer text. */
	private Text blockSizeText, reqPiecesText, blocksInTransportText, trackerNumwantText, transportBufferText;

	/** The transport buffer combo. */
	private Combo blockSizeCombo, reqPiecesCombo, blocksInTransportCombo, trackerNumwantCombo, transportBufferCombo;

	/**
	 * Instantiates a new transport preference page.
	 */
	public TransportPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		factory = new GUIFactory(parent);
		Composite container = factory.createComposite(parent, 1);
		try {
			Group group = factory.createGroup(container, "Sizes", 3);

			createBlockSizeBox(group);
			createPiecesCountBox(group);
			createBlocksCountBox(group);
			createTrackerNumwantBox(group);
			createTransportBufferBox(group);

		} catch (Exception e) {
			Log.log(EBTClientPreferences.MODULE, e);
		}
		return container;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets. Event)
	 */
	@Override
	public void handleEvent(Event event) {
		if (event.widget.equals(blockSizeCombo)) {
			String item = blockSizeCombo.getItem(blockSizeCombo.getSelectionIndex());
			blockSizeText.setText(item);
			Activator.getPreferences().putInt(EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), Integer.parseInt(item));

		} else if (event.widget.equals(reqPiecesCombo)) {
			String item = reqPiecesCombo.getItem(reqPiecesCombo.getSelectionIndex());
			reqPiecesText.setText(item);
			Activator.getPreferences().putInt(EBTClientPreferences.REQUESTED_PIECES.getName(), Integer.parseInt(item));

		} else if (event.widget.equals(blocksInTransportCombo)) {
			String item = blocksInTransportCombo.getItem(blocksInTransportCombo.getSelectionIndex());
			blocksInTransportText.setText(item);
			Activator.getPreferences().putInt(EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), Integer.parseInt(item));

		} else if (event.widget.equals(trackerNumwantCombo)) {
			String item = trackerNumwantCombo.getItem(trackerNumwantCombo.getSelectionIndex());
			trackerNumwantText.setText(item);
			Activator.getPreferences().putInt(EBTClientPreferences.TRACKER_NUMWANT.getName(), Integer.parseInt(item));
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the block size box.
	 * @param group the group
	 */
	private void createBlockSizeBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName());

		blockSizeText = factory.createText(group, EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), false);
		blockSizeCombo = factory.createCombo(group, EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), MEMORY_SIZES);

		int value = Activator.getPreferences().getInt(EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), (Integer) EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getDef());
		blockSizeText.setText(Integer.toString(value));
		blockSizeCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the pieces count box.
	 * @param group the group
	 */
	private void createPiecesCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.REQUESTED_PIECES.getName());

		reqPiecesText = factory.createText(group, EBTClientPreferences.REQUESTED_PIECES.getName(), false);
		reqPiecesCombo = factory.createCombo(group, EBTClientPreferences.REQUESTED_PIECES.getName(), NUMBERS);

		int value = Activator.getPreferences().getInt(EBTClientPreferences.REQUESTED_PIECES.getName(), (Integer) EBTClientPreferences.REQUESTED_PIECES.getDef());
		reqPiecesText.setText(Integer.toString(value));
		reqPiecesCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the blocks count box.
	 * @param group the group
	 */
	private void createBlocksCountBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.BLOCKS_IN_REQUEST.getName());

		blocksInTransportText = factory.createText(group, EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), false);
		blocksInTransportCombo = factory.createCombo(group, EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), NUMBERS);

		int value = Activator.getPreferences().getInt(EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), (Integer) EBTClientPreferences.BLOCKS_IN_REQUEST.getDef());
		blocksInTransportText.setText(Integer.toString(value));
		blocksInTransportCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tracker numwant box.
	 * @param group the group
	 */
	private void createTrackerNumwantBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.TRACKER_NUMWANT.getName());

		trackerNumwantText = factory.createText(group, EBTClientPreferences.TRACKER_NUMWANT.getName(), false);
		trackerNumwantCombo = factory.createCombo(group, EBTClientPreferences.TRACKER_NUMWANT.getName(), NUMBERS);

		int value = Activator.getPreferences().getInt(EBTClientPreferences.TRACKER_NUMWANT.getName(), (Integer) EBTClientPreferences.TRACKER_NUMWANT.getDef());
		trackerNumwantText.setText(Integer.toString(value));
		trackerNumwantCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the transport buffer box.
	 * @param group the group
	 */
	private void createTransportBufferBox(Group group) {
		factory.createLabel(group, EBTClientPreferences.TRANSPORT_BUFFER.getName());

		transportBufferText = factory.createText(group, EBTClientPreferences.TRANSPORT_BUFFER.getName(), false);
		transportBufferCombo = factory.createCombo(group, EBTClientPreferences.TRANSPORT_BUFFER.getName(), NUMBERS);

		int value = Activator.getPreferences().getInt(EBTClientPreferences.TRANSPORT_BUFFER.getName(), (Integer) EBTClientPreferences.TRANSPORT_BUFFER.getDef());
		transportBufferText.setText(Integer.toString(value));
		transportBufferCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		blockSizeText.setText((String) EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getDef());
		Activator.getPreferences().putInt(EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getName(), (Integer) EBTClientPreferences.TRANSPORT_BLOCK_SIZE.getDef());

		reqPiecesText.setText((String) EBTClientPreferences.REQUESTED_PIECES.getDef());
		Activator.getPreferences().putInt(EBTClientPreferences.REQUESTED_PIECES.getName(), (Integer) EBTClientPreferences.REQUESTED_PIECES.getDef());

		blocksInTransportText.setText((String) EBTClientPreferences.BLOCKS_IN_REQUEST.getDef());
		Activator.getPreferences().putInt(EBTClientPreferences.BLOCKS_IN_REQUEST.getName(), (Integer) EBTClientPreferences.BLOCKS_IN_REQUEST.getDef());

		trackerNumwantText.setText((String) EBTClientPreferences.TRACKER_NUMWANT.getDef());
		Activator.getPreferences().putInt(EBTClientPreferences.TRACKER_NUMWANT.getName(), (Integer) EBTClientPreferences.TRACKER_NUMWANT.getDef());

		transportBufferText.setText((String) EBTClientPreferences.TRANSPORT_BUFFER.getDef());
		Activator.getPreferences().putInt(EBTClientPreferences.TRANSPORT_BUFFER.getName(), (Integer) EBTClientPreferences.TRANSPORT_BUFFER.getDef());

		super.performDefaults();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		try {

			// Activator.getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		try {
			// Forces the application to save the CORE preferences
			Activator.getPreferences().flush();
			// PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
