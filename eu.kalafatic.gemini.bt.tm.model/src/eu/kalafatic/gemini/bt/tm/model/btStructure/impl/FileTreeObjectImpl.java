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
package eu.kalafatic.gemini.bt.tm.model.btStructure.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.tm.model.btStructure.BtStructurePackage;
import eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject;

/**
 * The Class class FileTreeObjectImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class FileTreeObjectImpl extends EObjectImpl implements FileTreeObject {

	/** The Constant NAME_EDEFAULT. */
	protected static final String NAME_EDEFAULT = null;

	/** The name. */
	protected String name = NAME_EDEFAULT;

	/** The parent. */
	protected FileTreeObject parent;

	/** The Constant FILE_EDEFAULT. */
	protected static final boolean FILE_EDEFAULT = false;

	/** The file. */
	protected boolean file = FILE_EDEFAULT;

	/** The Constant PATH_EDEFAULT. */
	protected static final String PATH_EDEFAULT = null;

	/** The path. */
	protected String path = PATH_EDEFAULT;

	/** The Constant MD5_SUM_EDEFAULT. */
	protected static final String MD5_SUM_EDEFAULT = null;

	/** The md5 sum. */
	protected String md5Sum = MD5_SUM_EDEFAULT;

	/** The Constant LENGTH_EDEFAULT. */
	protected static final long LENGTH_EDEFAULT = 0L;

	/** The length. */
	protected long length = LENGTH_EDEFAULT;

	/** The child map. */
	protected EMap<String, FileTreeObject> childMap;

	/**
	 * Instantiates a new file tree object impl.
	 */
	protected FileTreeObjectImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return BtStructurePackage.Literals.FILE_TREE_OBJECT;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setName(java.lang.String)
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__NAME, oldName, name));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getParent()
	 */
	public FileTreeObject getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject) parent;
			parent = (FileTreeObject) eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BtStructurePackage.FILE_TREE_OBJECT__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * Basic get parent.
	 * @return the file tree object
	 */
	public FileTreeObject basicGetParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setParent(eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject)
	 */
	public void setParent(FileTreeObject newParent) {
		FileTreeObject oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__PARENT, oldParent, parent));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#isFile()
	 */
	public boolean isFile() {
		return file;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setFile(boolean)
	 */
	public void setFile(boolean newFile) {
		boolean oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__FILE, oldFile, file));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getPath()
	 */
	public String getPath() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setPath(java.lang.String)
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__PATH, oldPath, path));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getMd5Sum()
	 */
	public String getMd5Sum() {
		return md5Sum;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setMd5Sum(java.lang.String)
	 */
	public void setMd5Sum(String newMd5Sum) {
		String oldMd5Sum = md5Sum;
		md5Sum = newMd5Sum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__MD5_SUM, oldMd5Sum, md5Sum));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getLength()
	 */
	public long getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#setLength(long)
	 */
	public void setLength(long newLength) {
		long oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BtStructurePackage.FILE_TREE_OBJECT__LENGTH, oldLength, length));
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.tm.model.btStructure.FileTreeObject#getChildMap()
	 */
	public EMap<String, FileTreeObject> getChildMap() {
		if (childMap == null) {
			childMap = new EcoreEMap<String, FileTreeObject>(BtStructurePackage.Literals.STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY, StringToFileTreeObjectMapEntryImpl.class, this,
					BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP);
		}
		return childMap;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP:
			return ((InternalEList<?>) getChildMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BtStructurePackage.FILE_TREE_OBJECT__NAME:
			return getName();
		case BtStructurePackage.FILE_TREE_OBJECT__PARENT:
			if (resolve)
				return getParent();
			return basicGetParent();
		case BtStructurePackage.FILE_TREE_OBJECT__FILE:
			return isFile();
		case BtStructurePackage.FILE_TREE_OBJECT__PATH:
			return getPath();
		case BtStructurePackage.FILE_TREE_OBJECT__MD5_SUM:
			return getMd5Sum();
		case BtStructurePackage.FILE_TREE_OBJECT__LENGTH:
			return getLength();
		case BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP:
			if (coreType)
				return getChildMap();
			else
				return getChildMap().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(int, java.lang.Object)
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BtStructurePackage.FILE_TREE_OBJECT__NAME:
			setName((String) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__PARENT:
			setParent((FileTreeObject) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__FILE:
			setFile((Boolean) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__PATH:
			setPath((String) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__MD5_SUM:
			setMd5Sum((String) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__LENGTH:
			setLength((Long) newValue);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP:
			((EStructuralFeature.Setting) getChildMap()).set(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eUnset(int)
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case BtStructurePackage.FILE_TREE_OBJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__PARENT:
			setParent((FileTreeObject) null);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__FILE:
			setFile(FILE_EDEFAULT);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__MD5_SUM:
			setMd5Sum(MD5_SUM_EDEFAULT);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__LENGTH:
			setLength(LENGTH_EDEFAULT);
			return;
		case BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP:
			getChildMap().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eIsSet(int)
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case BtStructurePackage.FILE_TREE_OBJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BtStructurePackage.FILE_TREE_OBJECT__PARENT:
			return parent != null;
		case BtStructurePackage.FILE_TREE_OBJECT__FILE:
			return file != FILE_EDEFAULT;
		case BtStructurePackage.FILE_TREE_OBJECT__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
		case BtStructurePackage.FILE_TREE_OBJECT__MD5_SUM:
			return MD5_SUM_EDEFAULT == null ? md5Sum != null : !MD5_SUM_EDEFAULT.equals(md5Sum);
		case BtStructurePackage.FILE_TREE_OBJECT__LENGTH:
			return length != LENGTH_EDEFAULT;
		case BtStructurePackage.FILE_TREE_OBJECT__CHILD_MAP:
			return childMap != null && !childMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#toString()
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", file: ");
		result.append(file);
		result.append(", path: ");
		result.append(path);
		result.append(", md5Sum: ");
		result.append(md5Sum);
		result.append(", length: ");
		result.append(length);
		result.append(')');
		return result.toString();
	}

} // FileTreeObjectImpl
