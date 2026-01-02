/**
 * 
 */
package eu.kalafatic.gemini.bt.client.themes.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.themes.IThemeManager;

/**
 * @author  Petr Kalafatic
 * @project Gemini 2.0
 */
public class DefaultThemeAction extends Action implements
		IWorkbenchWindowActionDelegate {

	IThemeManager themeManager;
	IWorkbenchWindow window;
	public final static String ID = "gemini.core.client.themes.default"; // theme id

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Gaudy theme");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		Display display = Display.getDefault();
	    Shell shell = new Shell(display);	
	    
	    int choose=0;
	    
	    ThemeSelect themeSelect = new ThemeSelect(
				shell,choose );
	    themeSelect.open();		
		
		themeManager = PlatformUI.getWorkbench().getThemeManager();
		themeManager.setCurrentTheme("gemini.core.client.themes.default"); // theme id
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				.setBackgroundMode(SWT.INHERIT_FORCE);

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				.setBackground(
						themeManager.getCurrentTheme().getColorRegistry().get(
								"gemini.core.client.themes.backgroundColor"));// color id
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
