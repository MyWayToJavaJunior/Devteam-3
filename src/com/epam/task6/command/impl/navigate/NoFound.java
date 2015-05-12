package com.epam.task6.command.impl.navigate;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoFound extends Command {

    private static NoFound instance = new NoFound();
    /* Initialize activity logger */
    private static Logger logger = Logger.getLogger("activity");

    /* Logger messages */
    private static final String MSG_ACTIVITY = "logger.activity.requested.not.found";

    /* Forward page */
    private static final String FORWARD_NOT_FOUND = "forward.error.404";

    /* Parameters */
    private static final String PARAM_EXECUTION_COMMAND = "executionCommand";

    public static NoFound getInstance() {
        return instance;
    }

    /**
     * Implementation of command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If an error has occurred on runtime
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String command = request.getParameter(PARAM_EXECUTION_COMMAND);
        logger.info(ResourceManager.getProperty(MSG_ACTIVITY) + command);
        return  (ResourceManager.getProperty(FORWARD_NOT_FOUND));
    }

}
