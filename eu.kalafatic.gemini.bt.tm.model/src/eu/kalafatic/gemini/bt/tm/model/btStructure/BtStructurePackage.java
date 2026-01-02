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
package eu.kalafatic.gemini.bt.tm.model.btStructure;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * The Interface interface BtStructurePackage.
 * @author Petr Kalafatic
 * @project Gemini
 * @version 3.0.0
 */
public interface BtStructurePackage extends EPackage {

	/** The e name. */
	String eNAME = "btStructure";

	/** The e n s_ uri. */
	String eNS_URI = "http:///btStructure.ecore";

	/** The e n s_ prefix. */
	String eNS_PREFIX = "btStructure";

	/** The e instance. */
	BtStructurePackage eINSTANCE = eu.kalafatic.gemini.bt.tm.model.btStructure.impl.BtStructurePackageImpl.init();

	/** The B t_ structure. */
	int BT_STRUCTURE = 0;

	/** The B t_ structur e__ ne w_ torrents. */
	int BT_STRUCTURE__NEW_TORRENTS = 0;

	/** The B t_ structur e__ tracker s_ map. */
	int BT_STRUCTURE__TRACKERS_MAP = 1;

	/** The B t_ structur e__ tre e_ objects. */
	int BT_STRUCTURE__TREE_OBJECTS = 2;

	/** The B t_ structur e_ featur e_ count. */
	int BT_STRUCTURE_FEATURE_COUNT = 3;

	/** The TRACKER. */
	int TRACKER = 1;

	/** The TRACKE r__ checked. */
	int TRACKER__CHECKED = 0;

	/** The TRACKE r__ announce. */
	int TRACKER__ANNOUNCE = 1;

	/** The TRACKE r__ comment. */
	int TRACKER__COMMENT = 2;

	/** The TRACKE r_ featur e_ count. */
	int TRACKER_FEATURE_COUNT = 3;

	/** The FIL e_ tre e_ object. */
	int FILE_TREE_OBJECT = 2;

	/** The FIL e_ tre e_ objec t__ name. */
	int FILE_TREE_OBJECT__NAME = 0;

	/** The FIL e_ tre e_ objec t__ parent. */
	int FILE_TREE_OBJECT__PARENT = 1;

	/** The FIL e_ tre e_ objec t__ file. */
	int FILE_TREE_OBJECT__FILE = 2;

	/** The FIL e_ tre e_ objec t__ path. */
	int FILE_TREE_OBJECT__PATH = 3;

	/** The FIL e_ tre e_ objec t__ m d5_ sum. */
	int FILE_TREE_OBJECT__MD5_SUM = 4;

	/** The FIL e_ tre e_ objec t__ length. */
	int FILE_TREE_OBJECT__LENGTH = 5;

	/** The FIL e_ tre e_ objec t__ chil d_ map. */
	int FILE_TREE_OBJECT__CHILD_MAP = 6;

	/** The FIL e_ tre e_ objec t_ featur e_ count. */
	int FILE_TREE_OBJECT_FEATURE_COUNT = 7;

	/** The STRIN g_ t o_ tracke r_ ma p_ entry. */
	int STRING_TO_TRACKER_MAP_ENTRY = 3;

	/** The STRIN g_ t o_ tracke r_ ma p_ entr y__ key. */
	int STRING_TO_TRACKER_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ tracke r_ ma p_ entr y__ value. */
	int STRING_TO_TRACKER_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ tracke r_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_TRACKER_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entry. */
	int STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY = 4;

	/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entr y__ key. */
	int STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__KEY = 0;

	/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entr y__ value. */
	int STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__VALUE = 1;

	/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entr y_ featur e_ count. */
	int STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY_FEATURE_COUNT = 2;

	/** The EINTEGE r_ array. */
	int EINTEGER_ARRAY = 5;

	/**
	 * Gets the bT structure.
	 * @return the bT structure
	 */
	EClass getBTStructure();

	/**
	 * Gets the bT structure_ new torrents.
	 * @return the bT structure_ new torrents
	 */
	EReference getBTStructure_NewTorrents();

	/**
	 * Gets the bT structure_ trackers map.
	 * @return the bT structure_ trackers map
	 */
	EReference getBTStructure_TrackersMap();

	/**
	 * Gets the bT structure_ tree objects.
	 * @return the bT structure_ tree objects
	 */
	EReference getBTStructure_TreeObjects();

	/**
	 * Gets the tracker.
	 * @return the tracker
	 */
	EClass getTracker();

	/**
	 * Gets the tracker_ checked.
	 * @return the tracker_ checked
	 */
	EAttribute getTracker_Checked();

	/**
	 * Gets the tracker_ announce.
	 * @return the tracker_ announce
	 */
	EAttribute getTracker_Announce();

	/**
	 * Gets the tracker_ comment.
	 * @return the tracker_ comment
	 */
	EAttribute getTracker_Comment();

	/**
	 * Gets the file tree object.
	 * @return the file tree object
	 */
	EClass getFileTreeObject();

	/**
	 * Gets the file tree object_ name.
	 * @return the file tree object_ name
	 */
	EAttribute getFileTreeObject_Name();

	/**
	 * Gets the file tree object_ parent.
	 * @return the file tree object_ parent
	 */
	EReference getFileTreeObject_Parent();

	/**
	 * Gets the file tree object_ file.
	 * @return the file tree object_ file
	 */
	EAttribute getFileTreeObject_File();

	/**
	 * Gets the file tree object_ path.
	 * @return the file tree object_ path
	 */
	EAttribute getFileTreeObject_Path();

	/**
	 * Gets the file tree object_ md5 sum.
	 * @return the file tree object_ md5 sum
	 */
	EAttribute getFileTreeObject_Md5Sum();

	/**
	 * Gets the file tree object_ length.
	 * @return the file tree object_ length
	 */
	EAttribute getFileTreeObject_Length();

	/**
	 * Gets the file tree object_ child map.
	 * @return the file tree object_ child map
	 */
	EReference getFileTreeObject_ChildMap();

	/**
	 * Gets the string to tracker map entry.
	 * @return the string to tracker map entry
	 */
	EClass getStringToTrackerMapEntry();

	/**
	 * Gets the string to tracker map entry_ key.
	 * @return the string to tracker map entry_ key
	 */
	EAttribute getStringToTrackerMapEntry_Key();

	/**
	 * Gets the string to tracker map entry_ value.
	 * @return the string to tracker map entry_ value
	 */
	EReference getStringToTrackerMapEntry_Value();

	/**
	 * Gets the string to file tree object map entry.
	 * @return the string to file tree object map entry
	 */
	EClass getStringToFileTreeObjectMapEntry();

	/**
	 * Gets the string to file tree object map entry_ key.
	 * @return the string to file tree object map entry_ key
	 */
	EAttribute getStringToFileTreeObjectMapEntry_Key();

	/**
	 * Gets the string to file tree object map entry_ value.
	 * @return the string to file tree object map entry_ value
	 */
	EReference getStringToFileTreeObjectMapEntry_Value();

	/**
	 * Gets the e integer array.
	 * @return the e integer array
	 */
	EDataType getEIntegerArray();

	/**
	 * Gets the bt structure factory.
	 * @return the bt structure factory
	 */
	BtStructureFactory getBtStructureFactory();

	/**
	 * The Interface interface Literals.
	 * @author Petr Kalafatic
	 * @project Gemini
	 * @version 3.0.0
	 */
	interface Literals {

		/** The B t_ structure. */
		EClass BT_STRUCTURE = eINSTANCE.getBTStructure();

		/** The B t_ structur e__ ne w_ torrents. */
		EReference BT_STRUCTURE__NEW_TORRENTS = eINSTANCE.getBTStructure_NewTorrents();

		/** The B t_ structur e__ tracker s_ map. */
		EReference BT_STRUCTURE__TRACKERS_MAP = eINSTANCE.getBTStructure_TrackersMap();

		/** The B t_ structur e__ tre e_ objects. */
		EReference BT_STRUCTURE__TREE_OBJECTS = eINSTANCE.getBTStructure_TreeObjects();

		/** The TRACKER. */
		EClass TRACKER = eINSTANCE.getTracker();

		/** The TRACKE r__ checked. */
		EAttribute TRACKER__CHECKED = eINSTANCE.getTracker_Checked();

		/** The TRACKE r__ announce. */
		EAttribute TRACKER__ANNOUNCE = eINSTANCE.getTracker_Announce();

		/** The TRACKE r__ comment. */
		EAttribute TRACKER__COMMENT = eINSTANCE.getTracker_Comment();

		/** The FIL e_ tre e_ object. */
		EClass FILE_TREE_OBJECT = eINSTANCE.getFileTreeObject();

		/** The FIL e_ tre e_ objec t__ name. */
		EAttribute FILE_TREE_OBJECT__NAME = eINSTANCE.getFileTreeObject_Name();

		/** The FIL e_ tre e_ objec t__ parent. */
		EReference FILE_TREE_OBJECT__PARENT = eINSTANCE.getFileTreeObject_Parent();

		/** The FIL e_ tre e_ objec t__ file. */
		EAttribute FILE_TREE_OBJECT__FILE = eINSTANCE.getFileTreeObject_File();

		/** The FIL e_ tre e_ objec t__ path. */
		EAttribute FILE_TREE_OBJECT__PATH = eINSTANCE.getFileTreeObject_Path();

		/** The FIL e_ tre e_ objec t__ m d5_ sum. */
		EAttribute FILE_TREE_OBJECT__MD5_SUM = eINSTANCE.getFileTreeObject_Md5Sum();

		/** The FIL e_ tre e_ objec t__ length. */
		EAttribute FILE_TREE_OBJECT__LENGTH = eINSTANCE.getFileTreeObject_Length();

		/** The FIL e_ tre e_ objec t__ chil d_ map. */
		EReference FILE_TREE_OBJECT__CHILD_MAP = eINSTANCE.getFileTreeObject_ChildMap();

		/** The STRIN g_ t o_ tracke r_ ma p_ entry. */
		EClass STRING_TO_TRACKER_MAP_ENTRY = eINSTANCE.getStringToTrackerMapEntry();

		/** The STRIN g_ t o_ tracke r_ ma p_ entr y__ key. */
		EAttribute STRING_TO_TRACKER_MAP_ENTRY__KEY = eINSTANCE.getStringToTrackerMapEntry_Key();

		/** The STRIN g_ t o_ tracke r_ ma p_ entr y__ value. */
		EReference STRING_TO_TRACKER_MAP_ENTRY__VALUE = eINSTANCE.getStringToTrackerMapEntry_Value();

		/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entry. */
		EClass STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY = eINSTANCE.getStringToFileTreeObjectMapEntry();

		/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entr y__ key. */
		EAttribute STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__KEY = eINSTANCE.getStringToFileTreeObjectMapEntry_Key();

		/** The STRIN g_ t o_ fil e_ tre e_ objec t_ ma p_ entr y__ value. */
		EReference STRING_TO_FILE_TREE_OBJECT_MAP_ENTRY__VALUE = eINSTANCE.getStringToFileTreeObjectMapEntry_Value();

		/** The EINTEGE r_ array. */
		EDataType EINTEGER_ARRAY = eINSTANCE.getEIntegerArray();

	}

} // BtStructurePackage
