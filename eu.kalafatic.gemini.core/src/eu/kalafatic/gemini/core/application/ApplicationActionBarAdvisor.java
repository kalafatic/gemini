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
package eu.kalafatic.gemini.core.application;

import static eu.kalafatic.gemini.core.lib.constants.FCoreConstants.FILTERED_ACTIONSETS;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ARROW_DOWN_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ARROW_UP_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.CS_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.DE_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.EN_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ES_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.RU_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.ZH_IMG;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

import eu.kalafatic.gemini.core.hack.StatusLineContributionItem;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ELang;

/**
 * The Class class ApplicationActionBarAdvisor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	/** The width. */
	private final int width = 70;

	/** The cpu item. */
	private final StatusLineContributionItem cpuItem = new StatusLineContributionItem("CPU", width);

	/** The lang item. */
	private final StatusLineContributionItem langItem = new StatusLineContributionItem("NL", width);

	/** The speed up item. */
	private final StatusLineContributionItem speedUpItem = new StatusLineContributionItem("SpeedUp", width);

	/** The speed down item. */
	private final StatusLineContributionItem speedDownItem = new StatusLineContributionItem("SpeedDown", width);

	/** The configurer. */
	private final IActionBarConfigurer configurer;

	private Map<ActionFactory, IWorkbenchAction> actions = new HashMap<ActionFactory, IWorkbenchAction>();

	/**
	 * Instantiates a new application action bar advisor.
	 * @param configurer the configurer
	 */
	ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		this.configurer = configurer;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.ActionBarAdvisor#fillStatusLine(org.eclipse .jface.action.IStatusLineManager)
	 */
	@Override
	protected void fillStatusLine(IStatusLineManager statusLineManager) {
		super.fillStatusLine(statusLineManager);

		statusLineManager.add(langItem);
		statusLineManager.add(speedDownItem);
		statusLineManager.add(speedUpItem);
		statusLineManager.add(cpuItem);

		cpuItem.setText("CPU: 100 %");

		String locale = System.getProperty("osgi.nl");
		langItem.setImage(getFlag(locale));
		langItem.setText("NL: " + locale);

		speedDownItem.setImage(ARROW_DOWN_IMG);
		speedDownItem.setText(" ");

		speedUpItem.setImage(ARROW_UP_IMG);
		speedUpItem.setText(" ");

		langItem.setVisible(true);
		speedDownItem.setVisible(true);
		speedUpItem.setVisible(true);
		cpuItem.setVisible(true);

		AppData.getInstance().setStatusLineManager(statusLineManager);

		AppData.getInstance().setCpuItem(cpuItem);
		AppData.getInstance().setSpeedDownItem(speedDownItem);
		AppData.getInstance().setSpeedUpItem(speedUpItem);

		// statusLineManager.setErrorMessage("fhn");
		statusLineManager.update(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the flag.
	 * @param locale the locale
	 * @return the flag
	 */
	private Image getFlag(String locale) {
		if (locale.toUpperCase().startsWith(ELang.CS.literal)) {
			return CS_IMG;
		} else if (locale.toUpperCase().startsWith(ELang.EN.literal)) {
			return EN_IMG;
		} else if (locale.toUpperCase().startsWith(ELang.DE.literal)) {
			return DE_IMG;
		} else if (locale.toUpperCase().startsWith(ELang.ZH.literal)) {
			return ZH_IMG;
		} else if (locale.toUpperCase().startsWith(ELang.RU.literal)) {
			return RU_IMG;
		} else if (locale.toUpperCase().startsWith(ELang.ES.literal)) {
			return ES_IMG;
		}
		return EN_IMG;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui .IWorkbenchWindow)
	 */
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		ActionFactory actionsToCreate[] = new ActionFactory[] {
				// file
				ActionFactory.NEW_WIZARD_DROP_DOWN,
				ActionFactory.NEW,
				ActionFactory.PRINT,
				ActionFactory.IMPORT,
				ActionFactory.EXPORT,
				ActionFactory.SAVE,
				ActionFactory.SAVE_AS,
				ActionFactory.SAVE_ALL,
				ActionFactory.CLOSE,
				ActionFactory.CLOSE_ALL,
				ActionFactory.QUIT,
				// edit
				ActionFactory.UNDO,
				ActionFactory.REDO,
				ActionFactory.CUT,
				ActionFactory.COPY,
				ActionFactory.PASTE,
				ActionFactory.DELETE,
				ActionFactory.SELECT_ALL,

				ActionFactory.REVERT,
				ActionFactory.PROPERTIES,
				ActionFactory.FORWARD_HISTORY,
				ActionFactory.BACKWARD_HISTORY,
				ActionFactory.LOCK_TOOL_BAR,

				ActionFactory.PREFERENCES,


				ActionFactory.EDIT_ACTION_SETS,

				ActionFactory.FIND,

				ActionFactory.MAXIMIZE,
				ActionFactory.MINIMIZE,
				ActionFactory.ACTIVATE_EDITOR,
				ActionFactory.NEXT_EDITOR,
				ActionFactory.PREVIOUS_EDITOR,
				ActionFactory.SHOW_OPEN_EDITORS,
				ActionFactory.SHOW_WORKBOOK_EDITORS,
				ActionFactory.NEXT_PART,
				ActionFactory.PREVIOUS_PART,
				ActionFactory.SHOW_PART_PANE_MENU,
				ActionFactory.RENAME,
				ActionFactory.REFRESH,

				ActionFactory.PROPERTIES,
				ActionFactory.PREFERENCES,
				ActionFactory.INTRO,
				// window
				ActionFactory.OPEN_NEW_WINDOW,
				ActionFactory.NEW_EDITOR,
				ActionFactory.OPEN_PERSPECTIVE_DIALOG,
				ActionFactory.SAVE_PERSPECTIVE,
				ActionFactory.RESET_PERSPECTIVE,
				ActionFactory.CLOSE_PERSPECTIVE,
				//help
				ActionFactory.HELP_CONTENTS,
				ActionFactory.HELP_SEARCH,
				ActionFactory.DYNAMIC_HELP,
				ActionFactory.ABOUT,

				ActionFactory.SHOW_VIEW_MENU };

		for (ActionFactory factory : actionsToCreate) {
			IWorkbenchAction action = factory.create(window);
			actions.put(factory, action);
			register(action);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface .action.IMenuManager)
	 */
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		try {
			IWorkbenchWindow window = getActionBarConfigurer().getWindowConfigurer().getWindow();
			menuBar.add(createFileMenu(window));
			createCoolBarMenu(window);
			menuBar.add(createEditMenu(window));
			menuBar.add(createProjectMenu(window));
			menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			menuBar.add(createToolsMenu(window));
			menuBar.add(createWindowMenu(window));
			menuBar.add(createHelpMenu(window));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the cool bar menu.
	 * @param window the window
	 */
	private void createCoolBarMenu(IWorkbenchWindow window) {

		// Control control = getActionBarConfigurer()
		// .getWindowConfigurer().createCoolBarControl(Display.getDefault().
		// getActiveShell());
		//
		// control.setBackground(Display.getDefault().getSystemColor(SWT.
		// COLOR_BLUE));
		//
		// control.getShell().setAlpha(50);
		// control.getParent().layout(true, true);
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
		// .getActivePart().getSite().getShell().getMenuBar().getShell()
		// .setAlpha(50);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.ActionBarAdvisor#fillCoolBar(org.eclipse.jface .action.ICoolBarManager)
	 */
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager tb = new ToolBarManager(coolBar.getStyle());
		tb.removeAll();

		// remove native buttons from toolbar
		ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();

		IActionSetDescriptor[] actionSets = reg.getActionSets();

		for (int i = 0; i < actionSets.length; i++) {
			for (int j = 0; j < FILTERED_ACTIONSETS.length; j++) {
				if (!actionSets[i].getId().equals(FILTERED_ACTIONSETS[j])) {
					continue;
				}
				IExtension ext = actionSets[i].getConfigurationElement().getDeclaringExtension();
				reg.removeExtension(ext, new Object[] { actionSets[i] });
			}
		}

		tb.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
		tb.add(new Separator());
		tb.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		coolBar.add(new ToolBarContributionItem(tb, "coolBar"));
		tb.update(true);
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the file menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	private IMenuManager createFileMenu(IWorkbenchWindow window) {
		MenuManager menuManager = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
		menuManager.add(actions.get(ActionFactory.NEW));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.CLOSE));
		menuManager.add(actions.get(ActionFactory.CLOSE_ALL));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.SAVE));
		menuManager.add(actions.get(ActionFactory.SAVE_AS));
		menuManager.add(actions.get(ActionFactory.SAVE_ALL));
		menuManager.add(actions.get(ActionFactory.REVERT));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.PRINT));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.IMPORT));
		menuManager.add(actions.get(ActionFactory.EXPORT));
		menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menuManager.add(actions.get(ActionFactory.PROPERTIES));
		menuManager.add(ContributionItemFactory.REOPEN_EDITORS.create(window));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.MRU));
		menuManager.add(new GroupMarker("syncMarker"));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.QUIT));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the edit menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	protected IMenuManager createEditMenu(IWorkbenchWindow window) {
		MenuManager menuManager = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));
		menuManager.add(actions.get(ActionFactory.REDO));
		menuManager.add(actions.get(ActionFactory.UNDO));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
		menuManager.add(actions.get(ActionFactory.COPY));
		menuManager.add(actions.get(ActionFactory.PASTE));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.DELETE));
		menuManager.add(actions.get(ActionFactory.SELECT_ALL));
		menuManager.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the project menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	protected IMenuManager createProjectMenu(IWorkbenchWindow window) {
		MenuManager menuManager = new MenuManager("&Project", IWorkbenchActionConstants.M_PROJECT);
		menuManager.add(actions.get(ActionFactory.PROPERTIES));
		menuManager.add(new Separator());

		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the window menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	protected IMenuManager createWindowMenu(IWorkbenchWindow window) {
		MenuManager menuManager = new MenuManager("Window", IWorkbenchActionConstants.M_WINDOW);
		menuManager.add(actions.get(ActionFactory.OPEN_NEW_WINDOW));
		menuManager.add(actions.get(ActionFactory.NEW_EDITOR));
		menuManager.add(new Separator());
		// menuManager.add(actions.get(ActionFactory.EDIT_ACTION_SETS));
		menuManager.add(actions.get(ActionFactory.OPEN_PERSPECTIVE_DIALOG));
		menuManager.add(actions.get(ActionFactory.SAVE_PERSPECTIVE));
		menuManager.add(actions.get(ActionFactory.RESET_PERSPECTIVE));
		menuManager.add(new Separator());

		MenuManager subMenu = new MenuManager("Navigate");
		subMenu.add(actions.get(ActionFactory.SHOW_PART_PANE_MENU));
		subMenu.add(actions.get(ActionFactory.SHOW_VIEW_MENU));
		subMenu.add(new Separator());
		subMenu.add(actions.get(ActionFactory.MAXIMIZE));
		subMenu.add(actions.get(ActionFactory.MINIMIZE));
		subMenu.add(new Separator());
		subMenu.add(actions.get(ActionFactory.ACTIVATE_EDITOR));
		subMenu.add(actions.get(ActionFactory.NEXT_EDITOR));
		subMenu.add(actions.get(ActionFactory.PREVIOUS_EDITOR));
		subMenu.add(actions.get(ActionFactory.SHOW_OPEN_EDITORS));
		subMenu.add(actions.get(ActionFactory.SHOW_WORKBOOK_EDITORS));
		subMenu.add(new Separator());
		subMenu.add(actions.get(ActionFactory.NEXT_PART));
		subMenu.add(actions.get(ActionFactory.PREVIOUS_PART));
		menuManager.add(subMenu);

		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.PREFERENCES));
		menuManager.add(ContributionItemFactory.OPEN_WINDOWS.create(window));
		menuManager.add(new Separator());

		// ---
		MenuManager perspectiveMenu = new MenuManager("&Open Perspective", "Open Perspective"); //$NON-NLS-1$
		IContributionItem perspectiveList = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);

		perspectiveMenu.add(new GroupMarker("perspMarker"));
		perspectiveMenu.add(new Separator());

		perspectiveMenu.add(perspectiveList);
		menuManager.add(perspectiveMenu);

		// ---
		MenuManager viewMenu = new MenuManager("&Show View", "Show View");
		IContributionItem viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
		viewMenu.add(new GroupMarker("viewMarker"));
		viewMenu.add(new Separator());
		viewMenu.add(viewList);
		menuManager.add(viewMenu);
		menuManager.add(new Separator());
		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the tools menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	private IMenuManager createToolsMenu(final IWorkbenchWindow window) {
		MenuManager menuManager = new MenuManager("&Tools", "Tools");
		menuManager.add(new GroupMarker("languageMarker"));
		menuManager.add(new GroupMarker("toolsMarker"));
		menuManager.add(new GroupMarker("actionMarker"));
		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the help menu.
	 * @param window the window
	 * @return the i menu manager
	 */
	protected IMenuManager createHelpMenu(IWorkbenchWindow window) {
		IMenuManager menuManager = new MenuManager("&Help", "Help");

		menuManager.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
		menuManager.add(actions.get(ActionFactory.INTRO));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.HELP_SEARCH));
		menuManager.add(actions.get(ActionFactory.HELP_CONTENTS));
		menuManager.add(new GroupMarker("assistMarker"));
		menuManager.add(new Separator());

		menuManager.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));
		menuManager.add(new GroupMarker("preferencesMarker"));
		menuManager.add(actions.get(ActionFactory.PREFERENCES));
		menuManager.add(new GroupMarker("updateMarker"));
		menuManager.add(new Separator());
		menuManager.add(new GroupMarker("helpMarker"));
		menuManager.add(new Separator());
		menuManager.add(new GroupMarker("aboutMarker"));
		menuManager.add(new Separator());
		menuManager.add(actions.get(ActionFactory.ABOUT));

		menuManager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuManager.add(new Separator());
		return menuManager;
	}

	// ---------------------------------------------------------------

	/**
	 * Fill tray item.
	 * @param trayMenu the tray menu
	 */
	public void fillTrayItem(MenuManager trayMenu) {
		trayMenu.add(actions.get(ActionFactory.ABOUT));
		trayMenu.add(actions.get(ActionFactory.QUIT));
	}
}
