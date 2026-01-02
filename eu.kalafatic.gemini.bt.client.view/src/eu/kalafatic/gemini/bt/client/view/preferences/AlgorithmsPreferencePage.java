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

import static eu.kalafatic.gemini.core.lib.constants.FConstants.PREFERENCES;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.NO_LIMIT;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.RATIO_DEF;
import static eu.kalafatic.gemini.core.lib.constants.FTextConstants.RATIO_MAN;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.COMBO_WIDTH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;

import eu.kalafatic.gemini.bt.client.net.controller.Activator;
import eu.kalafatic.gemini.bt.client.net.controller.lib.EBTClientPreferences;
import eu.kalafatic.gemini.bt.client.net.controller.model.NetworkModelManager;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EAlgorithm;
import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EStrategy;
import eu.kalafatic.gemini.core.factories.GUIFactory;
import eu.kalafatic.gemini.core.lib.constants.APreferencePage;
import eu.kalafatic.gemini.core.utils.DialogUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class AlgorithmsPreferencePage.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class AlgorithmsPreferencePage extends APreferencePage {

	/** The algorithms. */
	private final String ALGORITHMS = "Algorithms";

	/** The strategy. */
	private final String STRATEGY = "Strategy";

	/** The factory. */
	private GUIFactory factory;

	/** The ratio combo. */
	private Combo algCombo, strategyCombo, ratioCombo;

	/** The ratio text. */
	private Text algText, strategyText, ratioText;

	/** The upld slider. */
	private Slider dwnldSlider, upldSlider;

	/** The dwnld lbl. */
	private Label upldLbl, dwnldLbl;

	/**
	 * Instantiates a new algorithms preference page.
	 */
	public AlgorithmsPreferencePage() {
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
			Group group = factory.createGroup(container, ALGORITHMS, 3);
			createAlgorithmBox(group);

			group = factory.createGroup(container, STRATEGY, 3);
			createStrategyBox(group);

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
		if (event.widget.equals(algCombo)) {
			String value = algCombo.getItem(algCombo.getSelectionIndex());
			algText.setText(value);
			Activator.getPreferences().put(EBTClientPreferences.ALGORITHM.getName(), value);

		} else if (event.widget.equals(strategyCombo)) {
			String value = strategyCombo.getItem(strategyCombo.getSelectionIndex());
			strategyText.setText(value);
			setUpStrategy(value);
			Activator.getPreferences().put(EBTClientPreferences.STRATEGY.getName(), value);

		} else if (event.widget.equals(ratioCombo)) {
			String value = ratioCombo.getItem(ratioCombo.getSelectionIndex());
			ratioText.setText(value);
			if (value.equals(RATIO_DEF)) {
				Activator.getPreferences().putFloat(EBTClientPreferences.RATIO.getName(), 0);
			} else {
				// Activator.getPreferences().putFloat(
				// EBTClientPreferences.RATIO.getName(),
				// Float.parseFloat(value));
			}

		} else if (event.widget.equals(dwnldSlider)) {
			int value = dwnldSlider.getSelection();
			dwnldLbl.setText(Integer.toString(value));
			Activator.getPreferences().putInt(EBTClientPreferences.MAX_DWNLD_SPEED.getName(), value);

		} else if (event.widget.equals(upldSlider)) {
			int value = upldSlider.getSelection();
			upldLbl.setText(Integer.toString(value));
			Activator.getPreferences().putInt(EBTClientPreferences.MAX_UPLD_SPEED.getName(), value);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the algorithm box.
	 * @param group the group
	 */
	private void createAlgorithmBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.ALGORITHM.getName());

		algText = factory.createText(group, EBTClientPreferences.ALGORITHM.getName(), false);

		String value = Activator.getPreferences().get(((EAlgorithm) EBTClientPreferences.ALGORITHM.getValue()).getLiteral(), ((EAlgorithm) EBTClientPreferences.ALGORITHM.getValue()).getLiteral());
		algText.setText(value);

		algCombo = factory.createCombo(group, EBTClientPreferences.ALGORITHM.getName(), SWT.READ_ONLY);
		EAlgorithm[] algorithms = EAlgorithm.VALUES.toArray(new EAlgorithm[EAlgorithm.VALUES.size()]);
		for (int i = 0; i < algorithms.length; i++) {
			algCombo.add(algorithms[i].getLiteral());
		}
		algCombo.addListener(SWT.Selection, this);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the strategy box.
	 * @param group the group
	 */
	private void createStrategyBox(final Group group) {
		factory.createLabel(group, EBTClientPreferences.STRATEGY.getName());

		strategyText = factory.createText(group, EBTClientPreferences.STRATEGY.getName(), false);

		String value = Activator.getPreferences().get(((EStrategy) EBTClientPreferences.STRATEGY.getValue()).getLiteral(), ((EStrategy) EBTClientPreferences.STRATEGY.getValue()).getLiteral());
		strategyText.setText(value);

		strategyCombo = factory.createCombo(group, EBTClientPreferences.STRATEGY.getName(), SWT.READ_ONLY);

		final EStrategy[] strategies = EStrategy.VALUES.toArray(new EStrategy[EStrategy.VALUES.size()]);

		for (int i = 0; i < strategies.length; i++) {
			strategyCombo.add(strategies[i].getLiteral());
		}
		strategyCombo.addListener(SWT.Selection, this);

		factory.createLabel(group, EBTClientPreferences.RATIO.getName());
		ratioText = factory.createText(group, EBTClientPreferences.RATIO.getName(), true);

		float ratioValue = Activator.getPreferences().getFloat(EBTClientPreferences.RATIO.getName(), (Float) EBTClientPreferences.RATIO.getDef());

		ratioText.setText(ratioValue == 0 ? RATIO_DEF : Float.toString(ratioValue));

		ratioCombo = factory.createCombo(group, EBTClientPreferences.RATIO.getName(), RATIO_DEF, RATIO_MAN);
		ratioCombo.addListener(SWT.Selection, this);

		createMaxSpeedContent(group);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the max speed content.
	 * @param group the group
	 */
	private void createMaxSpeedContent(Group group) {
		Rectangle rectangle = new Rectangle(0, 0, 200, 5);

		factory.createLabel(group, EBTClientPreferences.MAX_DWNLD_SPEED.getName(), SWT.NONE);
		dwnldSlider = factory.createSlider(group, rectangle, (byte) 1);
		dwnldSlider.addListener(SWT.Selection, this);
		dwnldLbl = factory.createLabel(group, NO_LIMIT, SWT.BORDER, COMBO_WIDTH);
		// ---
		factory.createLabel(group, EBTClientPreferences.MAX_UPLD_SPEED.getName(), SWT.NONE);
		upldSlider = factory.createSlider(group, rectangle, (byte) 1);
		upldSlider.addListener(SWT.Selection, this);
		upldLbl = factory.createLabel(group, NO_LIMIT, SWT.BORDER, COMBO_WIDTH);
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up strategy.
	 * @param value the new up strategy
	 */
	private void setUpStrategy(String value) {
		if (value.equals(EStrategy.RANDOM.getLiteral())) {
			NetworkModelManager.getInstance().getClientNetwork().setAlgorithm(EStrategy.RANDOM);
		} else if (value.equals(EStrategy.SEQUENCE.getLiteral())) {
			NetworkModelManager.getInstance().getClientNetwork().setAlgorithm(EStrategy.SEQUENCE);
		}
		NetworkModelManager.getInstance().doSave();
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		// // algText.setText(value);
		// Activator.getPreferences().put(
		// EBTClientPreferences.ALGORITHM.getName(),
		// EBTClientPreferences.ALGORITHM.getDef());
		//
		// // } else if (event.widget.equals(strategyCombo)) {
		// String value = strategyCombo.getItem(strategyCombo
		// .getSelectionIndex());
		// strategyText.setText(value);
		// setUpStrategy(value);
		// Activator.getPreferences().put(
		// EBTClientPreferences.STRATEGY.getName(), value);
		//
		// // } else if (event.widget.equals(ratioCombo)) {
		// String value = ratioCombo.getItem(ratioCombo.getSelectionIndex());
		// ratioText.setText(value);
		// if (value.equals(RATIO_DEF)) {
		// Activator.getPreferences().putFloat(
		// EBTClientPreferences.RATIO.getName(), 0);
		// } else {
		// // Activator.getPreferences().putFloat(
		// // EBTClientPreferences.RATIO.getName(),
		// // Float.parseFloat(value));
		// }
		//
		// // } else if (event.widget.equals(dwnldSlider)) {
		// int value = dwnldSlider.getSelection();
		// dwnldLbl.setText(Integer.toString(value));
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.MAX_DWNLD_SPEED.getName(), value);
		//
		// // } else if (event.widget.equals(upldSlider)) {
		// int value = upldSlider.getSelection();
		// upldLbl.setText(Integer.toString(value));
		// Activator.getPreferences().putInt(
		// EBTClientPreferences.MAX_UPLD_SPEED.getName(), value);

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
			PREFERENCES.flush();
		} catch (Exception e) {
			DialogUtils.INSTANCE.showException(e);
		}
		return super.performOk();
	}
}
