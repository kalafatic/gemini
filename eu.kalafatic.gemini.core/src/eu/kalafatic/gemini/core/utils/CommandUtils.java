package eu.kalafatic.gemini.core.utils;

import java.util.Collections;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class CommandUtils {

	/** The instance. */
	private volatile static CommandUtils INSTANCE;

	/**
	 * Gets the single instance of CMDUtils.
	 * @return single instance of CMDUtils
	 */
	public static CommandUtils getInstance() {
		if (INSTANCE == null) {
			synchronized (CMDUtils.class) {
				INSTANCE = new CommandUtils();
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	public Object execute(String commandID) {
		try {
			ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

			Command command = commandService.getCommand(commandID);
			ParameterizedCommand parameterizedCommand = ParameterizedCommand.generateCommand(command, Collections.EMPTY_MAP);

			ExecutionEvent executionEvent = new ExecutionEvent();
			return command.executeWithChecks(executionEvent);

		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (NotDefinedException e) {
			e.printStackTrace();
		} catch (NotEnabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotHandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
