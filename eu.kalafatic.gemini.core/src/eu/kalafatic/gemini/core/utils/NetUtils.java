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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class class NetUtils.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class NetUtils {

	/** The instance. */
	private volatile static NetUtils INSTANCE;

	/**
	 * Gets the single instance of NetUtils.
	 * @return single instance of NetUtils
	 */
	public static NetUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (NetUtils.class) {
				INSTANCE = new NetUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/**
	 * Checks if is i pv4.
	 * @param host the host
	 * @return true, if is i pv4
	 */
	public boolean isIPv4(String host) {
		boolean result = false;
		try {
			result = InetAddress.getByName(host) instanceof Inet4Address;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the local host.
	 * @param size the size
	 * @param iPv4 the i pv4
	 * @return the local host
	 */
	public String[] getLocalHost(int size, boolean iPv4) {
		List<String> ip = new ArrayList<String>();
		try {
			InetAddress address = InetAddress.getLocalHost();
			InetAddress[] all = InetAddress.getAllByName(address.getHostName());
			size = (size > all.length) ? all.length : size;

			for (int i = 0; i < size; i++) {
				if (iPv4) {
					if (all[i] instanceof Inet4Address) {
						ip.add(all[i].getHostName());
					}
				} else {
					if (all[i] instanceof Inet6Address) {
						ip.add(all[i].getHostName());
					}
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip.toArray(new String[ip.size()]);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the local host ip.
	 * @param size the size
	 * @param iPv4 the i pv4
	 * @return the local host ip
	 */
	public String[][] getLocalHostIP(int size, boolean iPv4) {
		List<String[]> ip = new ArrayList<String[]>();
		try {
			InetAddress address = InetAddress.getLocalHost();
			InetAddress[] all = InetAddress.getAllByName(address.getHostName());
			size = (size > all.length) ? all.length : size;

			String[] hostIP = new String[2];

			for (int i = 0; i < size; i++) {
				if (iPv4) {
					if (all[i] instanceof Inet4Address) {
						hostIP[0] = all[i].getHostName();
						hostIP[1] = all[i].getHostAddress();
						ip.add(hostIP);
					}
				} else {
					if (all[i] instanceof Inet6Address) {
						hostIP[0] = all[i].getHostName();
						hostIP[1] = all[i].getHostAddress();
						ip.add(hostIP);
					}
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip.toArray(new String[ip.size()][2]);
	}

}
