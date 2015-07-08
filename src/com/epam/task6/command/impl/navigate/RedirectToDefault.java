package com.epam.task6.command.impl.navigate;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 12.05.15.
 */
public class RedirectToDefault extends Command{

    private static  RedirectToDefault instance = new RedirectToDefault();

    private static final Logger logger = Logger.getLogger(RedirectToDefault.class);

    /** Logger messages */
    private static final String MSG_ACTIVITY = "logger.activity.requested.not.found";

    /** Attributes and parameters */
    private static final String PARAM_USER = "user";

    /** Forward pages */
    private static final String FORWARD_DEFAULT_CUSTOMER_PAGE = "redirect.customer.default.page";
    private static final String FORWARD_DEFAULT_EMPLOYEE_PAGE = "redirect.employee.default.page";
    private static final String FORWARD_DEFAULT_MANAGER_PAGE = "redirect.manager.default.page";
    private static final String NOT_FOUND = "forward.error.404";

    public static RedirectToDefault getInstance() {
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
        User user = (User)request.getSession().getAttribute(PARAM_USER);
        logger.info(ResourceManager.getProperty(MSG_ACTIVITY) + user.getId());

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
