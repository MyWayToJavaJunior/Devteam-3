package com.epam.task6.command.impl.authorize;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command {

//      private static Logger logger = Logger.getLogger(Login.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.login";
    private static final String MSG_SIGNED_IN = "logger.activity.user.signed.in";
    private static final String MSG_SIGN_FAILED = "logger.activity.user.sign.failed";

    /* Keeps session lifecycle */
    private static final int SESSION_LIFECYCLE = 600;

    /* Attributes and parameters */
    private static final String PARAM_USER = "user";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_FORWARD_LOGIN = "login.jsp";
    private static final String PARAM_REDIRECT_COMMAND = "Controller?executionCommand=SHOW_SPECIFICATIONS";
    private static final String PARAM_REDIRECT_COMMAND1 = "Controller?executionCommand=SHOW_PROJECTS";
    private static final String PARAM_REDIRECT_COMMAND2 = "Controller?executionCommand=DEV";

    private static final String PARAM_INCORRECT_MSG = "Incorrect login or password";
    private static final String ATTRIBUTE_INCORRECT_MSG = "errorLoginPasswordMessage";


    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        String email = request.getParameter(RequestParameterName.EMAIL);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        UserDAO loginDAO = UserDAO.getInstance();
        User user = loginDAO.checkUserMailAndPassword(email, password);

        if (null != user) {
            HttpSession session = request.getSession();
            session.setAttribute(PARAM_USER, user);
            session.setAttribute(PARAM_ROLE, user.getRole());
            setSessionLifecycle(request, user);
            if (user.getRole() == Role.CUSTOMER) {
                setForward(PARAM_REDIRECT_COMMAND);
            } else if (user.getRole() == Role.MANAGER) {
                setForward(PARAM_REDIRECT_COMMAND1);
            } else {
                setForward(PARAM_REDIRECT_COMMAND2);
            }
        } else {
            request.setAttribute(ATTRIBUTE_INCORRECT_MSG, PARAM_INCORRECT_MSG);
            setForward(JspPageName.ERROR_PAGE);
        }
    }



    private void setSessionLifecycle(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_LIFECYCLE);
        session.setAttribute(PARAM_USER, user);
    }
}
