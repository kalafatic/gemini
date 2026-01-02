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
package eu.kalafatic.gemini.core.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;

import eu.kalafatic.gemini.core.models.SyncObject;

/**
 * The Class class SyncObjectTransfer.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
class SyncObjectTransfer extends ByteArrayTransfer {

	/** The Constant TYPE_NAME. */
	private static final String TYPE_NAME = "SyncObject";

	/** The Constant TYPE_ID. */
	private static final int TYPE_ID = registerType(TYPE_NAME);

	/** The Constant INSTANCE. */
	private static final SyncObjectTransfer INSTANCE = new SyncObjectTransfer();

	/**
	 * Gets the single instance of SyncObjectTransfer.
	 * @return single instance of SyncObjectTransfer
	 */
	static SyncObjectTransfer getInstance() {
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.ByteArrayTransfer#javaToNative(java.lang.Object, org.eclipse.swt.dnd.TransferData)
	 */
	@Override
	public void javaToNative(Object object, TransferData transferData) {
		if (!checkMyType(object) || !isSupportedType(transferData)) {
			DND.error(DND.ERROR_INVALID_DATA);
		}
		SyncObject[] syncObjects = (SyncObject[]) object;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			for (int i = 0; i < syncObjects.length; i++) {
				oos.writeObject(syncObjects[i]);
			}
			oos.writeObject(null);

			byte[] buffer = baos.toByteArray();

			oos.close();
			super.javaToNative(buffer, transferData);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.ByteArrayTransfer#nativeToJava(org.eclipse.swt.dnd .TransferData)
	 */
	@Override
	public Object nativeToJava(TransferData transferData) {
		if (isSupportedType(transferData)) {
			byte[] buffer = (byte[]) super.nativeToJava(transferData);

			if (buffer == null) {
				return null;
			}
			List<SyncObject> syncObjects = new ArrayList<SyncObject>();

			try {
				ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
				ObjectInputStream ois = new ObjectInputStream(bais);

				Object object = null;
				while ((object = ois.readObject()) != null) {

					if (object instanceof SyncObject) {
						SyncObject syncObject = (SyncObject) object;
						syncObjects.add(syncObject);
					}
				}

				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return syncObjects.toArray(new SyncObject[syncObjects.size()]);
		}
		return null;
	}

	// ---------------------------------------------------------------

	/**
	 * Check my type.
	 * @param object the object
	 * @return true, if successful
	 */
	private boolean checkMyType(Object object) {
		if (object == null || !(object instanceof SyncObject[]) || ((SyncObject[]) object).length == 0) {
			return false;
		}
		return true;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#validate(java.lang.Object)
	 */
	@Override
	protected boolean validate(Object object) {
		return checkMyType(object);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
	 */
	@Override
	protected int[] getTypeIds() {
		return new int[] { TYPE_ID };
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
	 */
	@Override
	protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

}
