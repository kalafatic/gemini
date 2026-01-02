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
package eu.kalafatic.gemini.bt.client.net.controller.decoders;

import eu.kalafatic.gemini.bt.client.net.model.clientNetwork.networkConstants.EClients;

/**
 * The Class class ClientDecoder.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class ClientDecoder {

	/** The instance. */
	private static ClientDecoder INSTANCE;

	/**
	 * Gets the single instance of ClientDecoder.
	 * @return single instance of ClientDecoder
	 */
	public static ClientDecoder getInstance() {
		if (INSTANCE == null) {
			synchronized (ClientDecoder.class) {
				INSTANCE = new ClientDecoder();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Decode client name.
	 * @param peerID the peer id
	 * @return the string
	 */
	public String decodeClientName(String peerID) {

		String clientName;

		switch (peerID.charAt(0)) {
		case '-':
			clientName = getAzureusClientStyle(peerID);
			break;
		case 'A':
			clientName = "ABC " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		case 'M':
			clientName = "Mainline " + peerID.charAt(1) + "." + peerID.charAt(3) + "." + peerID.charAt(5);
			break;
		case 'O':
			clientName = "Osprey Permaseed " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		case 'R':
			clientName = "Tribler " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		case 'S':
			clientName = "Shadow's client " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		case 'T':
			clientName = "BitTornado " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		case 'U':
			clientName = "UPnP NAT Bit Torrent " + peerID.charAt(1) + "." + peerID.charAt(2) + "." + peerID.charAt(3);
			break;
		default:
			clientName = "Unknown";
			break;
		}
		return clientName;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the azureus client style.
	 * @param peerID the peer id
	 * @return the azureus client style
	 */
	private String getAzureusClientStyle(String peerID) {
		String clientName;
		String client = peerID.substring(1, 3);
		String version = peerID.charAt(3) + "." + peerID.charAt(4) + "." + peerID.charAt(5) + "." + peerID.charAt(6);

		if (client.equalsIgnoreCase(EClients.AR.name())) {
			clientName = EClients.AR.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.AX.name())) {
			clientName = EClients.AX.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.AZ.name())) {
			clientName = EClients.AZ.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.BB.name())) {
			clientName = EClients.BB.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.BC.name())) {
			clientName = EClients.BC.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.BS.name())) {
			clientName = EClients.BS.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.BX.name())) {
			clientName = EClients.BX.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.CD.name())) {
			clientName = EClients.CD.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.CT.name())) {
			clientName = EClients.CT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.LP.name())) {
			clientName = EClients.LP.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.LT.name())) {
			clientName = EClients.LT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.MP.name())) {
			clientName = EClients.MP.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.MT.name())) {
			clientName = EClients.MT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.QT.name())) {
			clientName = EClients.QT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.RT.name())) {
			clientName = EClients.RT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.SB.name())) {
			clientName = EClients.SB.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.SS.name())) {
			clientName = EClients.SS.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.SZ.name())) {
			clientName = EClients.SZ.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.TN.name())) {
			clientName = EClients.TN.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.TR.name())) {
			clientName = EClients.TR.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.TS.name())) {
			clientName = EClients.TS.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.UT.name())) {
			clientName = EClients.UT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.XT.name())) {
			clientName = EClients.XT.getLiteral() + version;
		} else if (client.equalsIgnoreCase(EClients.ZT.name())) {
			clientName = EClients.ZT.getLiteral() + version;
		} else {
			clientName = "Unknown";
		}
		return clientName;
	}
}
