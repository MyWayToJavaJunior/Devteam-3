package com.epam.task6.command.impl.authorize;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Implementing command pattern.
 *
 * Created by olga on 22.04.15.
 */
public class Logout extends Command{
    private static Logout instance = new Logout();
    /* Initializes activity logger */
    private static Logger logger = Logger.getLogger(Logout.class);

    /* Logger messages */
    private static final String MSG_SIGN_OUT = "logger.activity.user.sign.out";

    /* Forward pages */
    private static final String LOGIN_PAGE = "login.jsp";

    /* Attributes */
    private static final String USER_ATTRIBUTE = "user";

    public static Logout getInstance() {
        return instance;
    }

    /**
     * This method invalidates user session
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        HttpSession session=request.getSession();
        session.invalidate();
        //logger.info(ResourceManager.getProperty(MSG_SIGN_OUT) + user.getId());
        return LOGIN_PAGE;
    }

}
