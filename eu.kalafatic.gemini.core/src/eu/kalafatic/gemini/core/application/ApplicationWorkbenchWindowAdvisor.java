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

import static eu.kalafatic.gemini.core.lib.constants.FCoreImageConstants.GEMINI_IMG;
import static eu.kalafatic.gemini.core.lib.constants.FUIConstants.APP_SIZE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.splash.SplashHandlerFactory;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.MarkerTransfer;
import org.eclipse.ui.part.ResourceTransfer;
import org.osgi.framework.Version;
import org.osgi.service.prefs.Preferences;

import eu.kalafatic.gemini.core.Activator;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler;
import eu.kalafatic.gemini.core.dialogs.GeminiSplashHandler.GSHf;
import eu.kalafatic.gemini.core.dnd.EditorAreaDropAdapter;
import eu.kalafatic.gemini.core.hack.EclipseSplashHandler;
import eu.kalafatic.gemini.core.interfaces.ISplashUser;
import eu.kalafatic.gemini.core.lib.AppData;
import eu.kalafatic.gemini.core.lib.ECorePreferences;
import eu.kalafatic.gemini.core.lib.EEncoding;
import eu.kalafatic.gemini.core.lib.EFolder;
import eu.kalafatic.gemini.core.utils.AppUtils;
import eu.kalafatic.gemini.core.utils.CMDUtils;
import eu.kalafatic.gemini.core.utils.Log;

/**
 * The Class class ApplicationWorkbenchWindowAdvisor.
 * @author Petr Kalafatic
 * @version 3.0.0
 * @project Gemini
 */
@SuppressWarnings("restriction")
class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor implements ISplashUser {

	/** The tray item. */
	private TrayItem trayItem;

	/** The tray image. */
	private Image trayImage;

	/** The tip. */
	private ToolTip tip;

	/** The window. */
	private IWorkbenchWindow window;

	/** The action bar advisor. */
	private ApplicationActionBarAdvisor actionBarAdvisor;

	/** The splash. */
	private static GeminiSplashHandler splash;

	/** The api store. */
	private IPreferenceStore apiStore;

	/** The display. */
	private Display display;

	/**
	 * Instantiates a new application workbench window advisor.
	 * @param configurer the configurer
	 */
	ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createActionBarAdvisor (org.eclipse.ui.application.IActionBarConfigurer)
	 */
	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		actionBarAdvisor = new ApplicationActionBarAdvisor(configurer);
		return actionBarAdvisor;
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()
	 */
	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(APP_SIZE);
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setShowFastViewBars(true);
		configurer.setShowMenuBar(true);
		configurer.setShowProgressIndicator(true);

		// add the drag and drop support for the editor area
		configurer.addEditorAreaTransfer(ResourceTransfer.getInstance());
		configurer.addEditorAreaTransfer(FileTransfer.getInstance());
		configurer.addEditorAreaTransfer(MarkerTransfer.getInstance());
		configurer.addEditorAreaTransfer(LocalSelectionTransfer.getTransfer());
		configurer.addEditorAreaTransfer(EditorInputTransfer.getInstance());
		configurer.configureEditorAreaDropListener(new EditorAreaDropAdapter(configurer.getWindow()));

		final String product = Platform.getProduct().getName();
		Version version = Version.parseVersion(Platform.getProduct().getDefiningBundle().getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION));
		configurer.setTitle(product + " v" + version.getMajor() + "." + version.getMinor() + "." + version.getMicro());

		apiStore = PlatformUI.getPreferenceStore();
		apiStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, EFolder.TOP_LEFT.ID);

		AppData.getInstance().getSplashUsersUsers().add(this);
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowOpen()
	 */
	@Override
	public void postWindowOpen() {
		super.postWindowOpen();

		window = getWindowConfigurer().getWindow();

		// test
		// openUpdatePopup();

		trayItem = initTrayItem(window);

		if (trayItem != null) {
			createMinimize();
			hookPopupMenu();

			window.getShell().setImage(GEMINI_IMG);
		}
		// Activator.processMessages();
		CMDUtils.getInstance().runAfterStart();
		AppUtils.getInstance().createProject(Platform.getProduct().getName());
		initListeners();
		setUpPreferences();

	}

	// ---------------------------------------------------------------

	// void openUpdatePopup() {
	// if (popup == null) {
	// popup = new AutomaticUpdatesPopup(window.getShell(),
	// alreadyDownloaded, getPreferenceStore());
	// // popup.getShell().setBackgroundImage(UPDATE_IMG);
	// }
	// popup.open();
	//
	// // initUpdate();
	// }

	// @SuppressWarnings("restriction")
	// private void initUpdate() {
	// final IProvisioningAgent agent = (IProvisioningAgent) ServiceHelper
	// .getService(Activator.bundleContext,
	// IProvisioningAgent.SERVICE_NAME);
	// if (agent == null) {
	// LogHelper
	// .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
	// "No provisioning agent found.  This application is not set up for updates."));
	// }
	// // XXX if we're restarting after updating, don't check again.
	// final IPreferenceStore prefStore = Activator.getDefault()
	// .getPreferenceStore();
	// if (prefStore.getBoolean(JUSTUPDATED)) {
	// prefStore.setValue(JUSTUPDATED, false);
	// return;
	// }
	//
	// // XXX check for updates before starting up.
	// // If an update is performed, restart. Otherwise log
	// // the status.
	// IRunnableWithProgress runnable = new IRunnableWithProgress() {
	// @Override
	// public void run(IProgressMonitor monitor)
	// throws InvocationTargetException, InterruptedException {
	// IStatus updateStatus = P2Util.checkForUpdates(agent, monitor);
	// if (updateStatus.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE) {
	// PlatformUI.getWorkbench().getDisplay()
	// .asyncExec(new Runnable() {
	// @Override
	// public void run() {
	// MessageDialog.openInformation(null,
	// "Updates", "No updates were found");
	// }
	// });
	// } else if (updateStatus.getSeverity() != IStatus.ERROR) {
	// prefStore.setValue(JUSTUPDATED, true);
	// PlatformUI.getWorkbench().restart();
	// } else {
	// LogHelper.log(updateStatus);
	// }
	// }
	// };
	// try {
	// new ProgressMonitorDialog(null).run(true, true, runnable);
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// }
	//
	// }

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createWindowContents (org.eclipse.swt.widgets.Shell)
	 */
	@Override
	public void createWindowContents(Shell shell) {
		super.createWindowContents(shell);

		// StatusLineContributionItem cpuItem = new StatusLineContributionItem(
		// "CPU", 70);
		//
		// StatusLineContributionItem cpuItem1 = new StatusLineContributionItem(
		// "CPU", 70);
		//
		// StatusLineContributionItem cpuItem2 = new StatusLineContributionItem(
		// "CPU", 70);
		//
		// RCPUtil.addContributionItemTrim(shell, cpuItem,
		// "CPU");
		//
		// RCPUtil.addContributionItemTrim(shell, cpuItem1,
		// "CPU1");
		//
		// RCPUtil.addContributionItemTrim(shell, cpuItem2,
		// "CPU2");
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the splash.
	 * @return the splash
	 */
	@SuppressWarnings({ "unused" })
	private EclipseSplashHandler getSplash() {
		if (splash == null) {

			IProduct product = Platform.getProduct();
			if (product != null) {
				splash = (GeminiSplashHandler) SplashHandlerFactory.findSplashHandlerFor(product);
			}
			if (splash == null) {
				splash = (GeminiSplashHandler) new EclipseSplashHandler();
			}
		}
		return splash;
	}

	// ---------------------------------------------------------------

	/**
	 * Hook popup menu.
	 */
	private void hookPopupMenu() {
		trayItem.addListener(SWT.MenuDetect, new Listener() {
			@Override
			public void handleEvent(Event event) {
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());
				actionBarAdvisor.fillTrayItem(trayMenu);
				menu.setVisible(true);
			}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the tray item.
	 * @param window the window
	 * @return the tray item
	 */
	private TrayItem initTrayItem(IWorkbenchWindow window) {
		try {
			final Tray tray = window.getShell().getDisplay().getSystemTray();
			trayItem = new TrayItem(tray, SWT.NONE);
			trayItem.setImage(GEMINI_IMG);
			trayItem.setToolTipText("Gemini");

			tip = new ToolTip(window.getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
			tip.setAutoHide(true);
			trayItem.setToolTip(tip);

			AppData.getInstance().setTrayItem(trayItem);

			trayItem.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (e.getSource() instanceof TrayItem) {
						TrayItem item = (TrayItem) e.getSource();
						item.dispose();
						item = null;
					}
				}
			});
		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		}
		return trayItem;
	}

	// ---------------------------------------------------------------

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		PlatformUI.getWorkbench().addWindowListener(new IWindowListener() {
			@Override
			public void windowOpened(IWorkbenchWindow window) {}

			@Override
			public void windowDeactivated(IWorkbenchWindow window) {}

			@Override
			public void windowClosed(IWorkbenchWindow window) {
				endPlugin();
			}

			@Override
			public void windowActivated(IWorkbenchWindow window) {}
		});

		window.addPerspectiveListener(new IPerspectiveListener() {

			@Override
			public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
				// if (page.isEditorAreaVisible()) {
				// IEditorReference[] editores = page.getEditorReferences();
				// if (editores.length <= 0) {
				// page.setEditorAreaVisible(false);
				// }
				// }
			}

			@Override
			public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String changeId) {}
		});
	}

	// ---------------------------------------------------------------

	/**
	 * Sets the up preferences.
	 */
	private void setUpPreferences() {
		try {
			String path = ApplicationWorkbenchWindowAdvisor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, EEncoding.UTF_8.getLiteral());

			Preferences preferences = Platform.getPreferencesService().getRootNode().node(Plugin.PLUGIN_PREFERENCE_SCOPE).node(Activator.PLUGIN_ID);

			preferences.put("programDirectory", decodedPath);

		} catch (UnsupportedEncodingException e) {
			Log.log(ECorePreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Gets the current display.
	 * @param currentThread the current thread
	 * @return the current display
	 */
	private Display getCurrentDisplay(Thread currentThread) {
		Display display = null;
		if ((display = Display.getDefault()) != null) {
			return display;
		} else if ((display = Display.getCurrent()) != null) {
			return display;
		} else if ((display = Display.findDisplay(currentThread)) != null) {
			return display;
		}
		return display;
	}

	// ---------------------------------------------------------------

	/**
	 * End plugin.
	 */
	private void endPlugin() {
		display = getCurrentDisplay(Display.getCurrent().getThread());

		display.asyncExec(new Runnable() {

			Thread finishThread = new Thread() {
				@Override
				public void run() {
					finishApplications(splash);
				};
			};

			Thread pendingThread = new Thread() {
				@Override
				public void run() {
					splash.runPending();
				};
			};

			@Override
			public void run() {
				try {
					splash = AppData.getInstance().getSplashHandler();
					splash.setEndSplash(true);
					Shell shell = splash.createUI(display);
					splash.init(shell);
					splash.setMonitor();

					splash.getBundleProgressMonitor();

					shell.open();

					pendingThread.start();
					finishThread.start();

					while (((GSHf.FLAG & GSHf.DONE) == 0)) {
						if (display.getThread() == Thread.currentThread()) {
							splash.update();
							Thread.sleep(250);
						}
					}
				} catch (Exception e) {
					Log.log(ECorePreferences.MODULE, e);
				} finally {
					if (splash != null) {
						splash.done();
					}
				}
			}

			// --------------------------------------------------------------

			private void finishApplications(GeminiSplashHandler splashHandler) {
				try {
					final List<ISplashUser> splashUsers = AppData.getInstance().getSplashUsersUsers();

					final int users = splashUsers.size() + 1;

					int oneAlpha = (users > 0) ? (200 / (users * 4)) : 0;

					int alpha = splashHandler.getAlpha();

					splashHandler.startTask("Closing Gemini ", users * 100);

					for (ISplashUser iSplashUser : splashUsers) {
						splashHandler.setAlpha(alpha -= oneAlpha / 2);

						iSplashUser.endWithProgress(splashHandler);
						Thread.sleep(500);

						splashHandler.setAlpha(alpha -= oneAlpha / 2);
						Thread.sleep(500);

						GSHf.FLAG |= GSHf.TASK_END;
					}
					GSHf.FLAG |= GSHf.TASK_END;
					Thread.sleep(500);

				} catch (Exception e) {
					Log.log(ECorePreferences.MODULE, e);
				} finally {
					GSHf.FLAG = GSHf.DONE;
				}
			}
		});
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see eu.kalafatic.gemini.core.interfaces.ISplashUser#endWithProgress()
	 */
	@Override
	public void endWithProgress(final GeminiSplashHandler splashHandler) {
		try {
			splashHandler.startSubTask("Stopping modules ...", (GSHf.FLAG & ~GSHf.VISIBLE));
			Thread.sleep(500);

		} catch (Exception e) {
			Log.log(ECorePreferences.MODULE, e);
		}
	}

	// ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#dispose()
	 */
	@Override
	public void dispose() {
		if (trayImage != null) {
			trayImage.dispose();
			trayItem.dispose();
		}
	}

	// ---------------------------------------------------------------

	/**
	 * Creates the minimize.
	 */
	private void createMinimize() {
		window.getShell().addShellListener(new ShellAdapter() {
			@Override
			public void shellIconified(ShellEvent e) {
				window.getShell().setVisible(false);
			}
		});

		trayItem.addListener(SWT.DefaultSelection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Shell shell = window.getShell();
				if (!shell.isVisible()) {
					shell.setVisible(true);
					window.getShell().setMinimized(false);
				}
			}
		});
	}
}
