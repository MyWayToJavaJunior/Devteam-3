package com.epam.task6.service.checker;

import com.epam.task6.command.CommandName;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 *  This class for check user permissions
 *
 * Created by olga on 30.04.15.
 */
public class AccessChecker {
    /* Keeps name of user attribute in session */
    private static final String USER = "user";

    /**
     * This method check permissions of all application users
     *
     * @param request HttpServletRequest object
     * @param command Requested command
     * @return True if access is allowed, otherwise false
     */
    public static boolean checkPermission(HttpServletRequest request, CommandName command) {
        User user = (User) request.getSession().getAttribute(USER);
        if (user == null) {
            return false;
        } else if (command.getUserType() == Role.ALL) {
            return true;
        }
        return command.getUserType() == user.getRole();
    }

}