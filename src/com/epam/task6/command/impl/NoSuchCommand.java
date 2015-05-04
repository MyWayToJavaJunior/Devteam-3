package com.epam.task6.command.impl;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoSuchCommand extends Command {
    /* Initialize activity logger */
//    private static Logger logger = Logger.getLogger("activity");

    /* Logger messages */
    private static final String MSG_ACTIVITY = "logger.activity.requested.not.found";

    /* Forward page */
    private static final String FORWARD_NOT_FOUND = "forward.error.404";

    /* Parameters */
    private static final String PARAM_EXECUTION_COMMAND = "executionCommand";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

    }


}