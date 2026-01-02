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
package eu.kalafatic.gemini.bt.client.net.rc.model.rc.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.kalafatic.gemini.bt.client.net.rc.model.rc.Folder;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RCSession;
import eu.kalafatic.gemini.bt.client.net.rc.model.rc.RcPackage;

/**
 * The Class class RCImpl.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public class RCImpl extends EObjectImpl implements RC {

	/** The session map. */
	protected EMap<String, RCSession> sessionMap;

	/** The tree. */
	protected EMap<String, Folder> tree;

	/**
	 * Instantiates a new rC impl.
	 */
	protected RCImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EObjectImpl#eStaticClass()
	 */
	@Override
	protected EClass eStaticClass() {
		return RcPackage.Literals.RC;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC#getSessionMap()
	 */
	public EMap<String, RCSession> getSessionMap() {
		if (sessionMap == null) {
			sessionMap = new EcoreEMap<String, RCSession>(RcPackage.Literals.STRING_TO_SESSION_MAP_ENTRY, StringToSessionMapEntryImpl.class, this, RcPackage.RC__SESSION_MAP);
		}
		return sessionMap;
	}

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.bt.client.net.rc.model.rc.RC#getTree()
	 */
	public EMap<String, Folder> getTree() {
		if (tree == null) {
			tree = new EcoreEMap<String, Folder>(RcPackage.Literals.STRING_TO_FOLDER_MAP_ENTRY, StringToFolderMapEntryImpl.class, this, RcPackage.RC__TREE);
		}
		return tree;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int,
	 * org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case RcPackage.RC__SESSION_MAP:
			return ((InternalEList<?>) getSessionMap()).basicRemove(otherEnd, msgs);
		case RcPackage.RC__TREE:
			return ((InternalEList<?>) getTree()).basicRemove(otherEnd, msgs);
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
		case RcPackage.RC__SESSION_MAP:
			if (coreType)
				return getSessionMap();
			else
				return getSessionMap().map();
		case RcPackage.RC__TREE:
			if (coreType)
				return getTree();
			else
				return getTree().map();
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
		case RcPackage.RC__SESSION_MAP:
			((EStructuralFeature.Setting) getSessionMap()).set(newValue);
			return;
		case RcPackage.RC__TREE:
			((EStructuralFeature.Setting) getTree()).set(newValue);
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
		case RcPackage.RC__SESSION_MAP:
			getSessionMap().clear();
			return;
		case RcPackage.RC__TREE:
			getTree().clear();
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
		case RcPackage.RC__SESSION_MAP:
			return sessionMap != null && !sessionMap.isEmpty();
		case RcPackage.RC__TREE:
			return tree != null && !tree.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // RCImpl
