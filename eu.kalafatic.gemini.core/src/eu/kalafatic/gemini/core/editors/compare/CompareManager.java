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
package eu.kalafatic.gemini.core.editors.compare;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

/**
 * The Class class CompareManager.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
public class CompareManager {

	/** The log. */
	private static Log log = LogFactory.getLog(CompareManager.class);

	/** The instance. */
	static CompareManager instance = new CompareManager();

	/**
	 * Gets the single instance of CompareManager.
	 * @return single instance of CompareManager
	 */
	public static CompareManager getInstance() {
		return instance;
	}

	/**
	 * Creates the project.
	 * @param label the label
	 * @return the i project
	 */
	public IProject createProject(String label) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IProject prj = root.getProject(label);
		if (prj.exists()) {
			return prj;
		}
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription desc = workspace.newProjectDescription(label);
		// desc.setNatureIds(new String[] { MdmNature.ID });
		desc.setComment("");//$NON-NLS-1$
		try {
			prj.create(desc, null);
			prj.open(IResource.BACKGROUND_REFRESH, null);
		} catch (CoreException e) {
			log.error(e.getMessage(), e);
		}
		return prj;
	}

	// public void compareTwoStream(String left, String right, boolean format,
	// CompareHeadInfo compareHeadInfo, String leftLabel,
	// String rightLabel, boolean leftEditable, boolean rightEditable) throws
	// Exception {
	// if (left == null || right == null)
	// return;
	//
	// if (format) {
	//            left = XmlUtil.formatCompletely(left, "UTF-8");//$NON-NLS-1$
	//            right = XmlUtil.formatCompletely(right, "UTF-8");//$NON-NLS-1$
	// }
	//
	// // prepare the two resources
	//        IProject prj = createProject("comparewithsvn");//$NON-NLS-1$
	//        IFile leftF = prj.getFile("left");//$NON-NLS-1$
	//        IFile rightF = prj.getFile("right");//$NON-NLS-1$
	// if (!leftF.exists())
	// leftF.create(new ByteArrayInputStream(left.getBytes()), IFile.FORCE,
	// null);
	// if (!rightF.exists())
	// rightF.create(new ByteArrayInputStream(right.getBytes()), IFile.FORCE,
	// null);
	// leftF.setContents(new ByteArrayInputStream(left.getBytes()), IFile.FORCE,
	// null);
	// rightF.setContents(new ByteArrayInputStream(right.getBytes()),
	// IFile.FORCE, null);
	//
	// CompareConfiguration cc = new CompareConfiguration();
	// cc.setLeftEditable(leftEditable);
	// cc.setRightEditable(rightEditable);
	// if (leftLabel == null)
	//            leftLabel = "";//$NON-NLS-1$
	// cc.setLeftLabel(leftLabel);
	// if (rightLabel == null)
	//            rightLabel = "";//$NON-NLS-1$
	// cc.setRightLabel(rightLabel);
	// ResourceCompareInput input = new ResourceCompareInput(cc);
	// StructuredSelection sel = new StructuredSelection(new IFile[] { leftF,
	// rightF });
	// input.setSelection(sel, null);
	// input.setCompareHeadInfo(compareHeadInfo);
	// CompareUI.openCompareEditor(input);
	//
	// }

	// public String getLeftContent() throws Exception {
	//        IProject prj = createProject("comparewithsvn");//$NON-NLS-1$
	//        IFile leftF = prj.getFile("left");//$NON-NLS-1$
	// if (!leftF.exists())
	// return null;
	// byte[] buf = new byte[1024];
	// StringBuffer sb = new StringBuffer();
	// BufferedInputStream in = new BufferedInputStream(leftF.getContents());
	// while (in.read(buf) > 0) {
	// sb.append(new String(buf));
	// in.read(buf);
	// }
	// return sb.toString().trim();
	// }
}
