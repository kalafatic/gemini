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
package eu.kalafatic.gemini.bt.tracker.view.properties.tabbed;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import eu.kalafatic.gemini.bt.tracker.model.tracker.ClientSession;
import eu.kalafatic.gemini.bt.tracker.model.tracker.Session;
import eu.kalafatic.gemini.bt.tracker.model.tracker.TorrentSession;
import eu.kalafatic.gemini.core.factories.GUIFactory;

/**
 * The Class class SessionSection.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class SessionSection extends AbstractPropertySection {

	/** The note text. */
	private Text sessionText, addressText, peerIdText, infoHashTexr, portText, noteText;;

	/** The session. */
	private Session session;

	/** The factory. */
	private GUIFactory factory = new GUIFactory();

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/** The listener. */
	private ModifyListener listener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent arg0) {
			System.err.println();
			// TorrentSessionProperties properties = (ButtonElementProperties)
			// buttonElement
			// .getAdapter(IPropertySource.class);
			// properties.setPropertyValue(ButtonElementProperties.PROPERTY_TEXT,
			// labelText.getText());
		}
	};

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));

		TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

		sessionText = factory.createPropertyText(widgetFactory, parent, "Session Type");

		addressText = factory.createPropertyText(widgetFactory, parent, "Address");

		peerIdText = factory.createPropertyText(widgetFactory, parent, "peerIdText");

		infoHashTexr = factory.createPropertyText(widgetFactory, parent, "infoHashTexr");
		portText = factory.createPropertyText(widgetFactory, parent, "portText");
		noteText = factory.createPropertyText(widgetFactory, parent, "noteText");
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		Object input = ((IStructuredSelection) selection).getFirstElement();
		if (input instanceof Entry) {
			Entry<?, ?> entry = (Entry<?, ?>) input;

			if (entry.getValue() instanceof TorrentSession) {
				TorrentSession torrentSession = (TorrentSession) entry.getValue();
				this.session = torrentSession;
			} else if (entry.getValue() instanceof ClientSession) {
				ClientSession clientSession = (ClientSession) entry.getValue();
				this.session = clientSession;
			}
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		addressText.removeModifyListener(listener);

		if (session instanceof TorrentSession) {
			refreshTorrentSession();
		} else if (session instanceof ClientSession) {
			refreshClientSession();
		}
		peerIdText.setText(session.getPeerId() == null ? "" : session.getPeerId());
		infoHashTexr.setText(session.getInfoHash() == null ? "" : session.getInfoHash());
		portText.setText(Integer.toString(session.getListenPort()));
		noteText.setText(session.getNote() == null ? "" : session.getNote());

		// addressText.addModifyListener(listener);
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh torrent session.
	 */
	private void refreshTorrentSession() {
		sessionText.setText("Torrent session");
		addressText.setText("");
	}

	// ---------------------------------------------------------------

	/**
	 * Refresh client session.
	 */
	private void refreshClientSession() {
		sessionText.setText("Client session");
		addressText.setText(session.getAddress() == null ? "" : session.getAddress());
	}

}
