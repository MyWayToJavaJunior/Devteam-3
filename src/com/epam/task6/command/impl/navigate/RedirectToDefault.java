package com.epam.task6.command.impl.navigate;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 12.05.15.
 */
public class RedirectToDefault extends Command{

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "";

    /* Attributes and parameters */
    private static final String EXECUTION_COMMAND = "executionCommand";
    private static final String PARAM_USER = "user";

    /* Forward pages */
    private static final String FORWARD_DEFAULT_CUSTOMER_PAGE = "redirect.customer.default.page";
    private static final String FORWARD_DEFAULT_EMPLOYEE_PAGE = "redirect.employee.default.page";
    private static final String FORWARD_DEFAULT_MANAGER_PAGE = "redirect.manager.default.page";
    private static final String NOT_FOUND = "forward.error.404";

    /**
     * Implementation of command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If an error has occurred on runtime
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(PARAM_USER);
        switch (user.getRole()) {
            case CUSTOMER:
                return (ResourceManager.getProperty(FORWARD_DEFAULT_CUSTOMER_PAGE));
            case DEVELOPER:
                return(ResourceManager.getProperty(FORWARD_DEFAULT_EMPLOYEE_PAGE));
            case MANAGER:
                return(ResourceManager.getProperty(FORWARD_DEFAULT_MANAGER_PAGE));
            default:
                return(ResourceManager.getProperty(NOT_FOUND));
        }
    }
}
