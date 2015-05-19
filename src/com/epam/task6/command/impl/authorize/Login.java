package com.epam.task6.command.impl.authorize;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.User;
import com.epam.task6.domain.verifable.SignInForm;
import com.epam.task6.domain.verifable.builder.VerifiableBuilder;
import com.epam.task6.resource.ResourceManager;
import com.epam.task6.service.ServiceException;
import com.epam.task6.service.builder.UserBuilder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Implementing command pattern.
 *
 * Created by olga on 22.04.15.
 */

public class Login extends Command {

    private static Login instance = new Login();
    //private static Lock lock = new ReentrantLock();
    private static Logger logger = Logger.getLogger(Login.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.login";
    private static final String MSG_SIGNED_IN = "logger.activity.user.signed.in";
    private static final String MSG_SIGN_FAILED = "logger.activity.user.sign.failed";

    /* Keeps session lifecycle */
    private static final int SESSION_LIFECYCLE = 600;

    /* Attributes and parameters */
    private static final String PARAM_USER = "user";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_REDIRECT_COMMAND = "Controller?executionCommand=SHOW_SPECIFICATIONS";
    private static final String PARAM_REDIRECT_COMMAND1 = "Controller?executionCommand=SHOW_PROJECTS";
    private static final String PARAM_REDIRECT_COMMAND2 = "Controller?executionCommand=DEV";

    private static final String PARAM_INCORRECT_MSG = "Incorrect login or password";
    private static final String ATTRIBUTE_INCORRECT_MSG = "errorLoginPasswordMessage";

    public static Login getInstance(){
        return instance;
    }

    /**
     * Implementation of login command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If errors occurred when executing command
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        SignInForm form = VerifiableBuilder.buildSignInForm(request);
        User user = null;
        try {
            user = UserBuilder.buildUser(form);
        } catch (ServiceException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
        catch (NullPointerException e)
        {
            return(JspPageName.ERROR_PAGE);
        }

        if (null != user) {
            HttpSession session = request.getSession();
            session.setAttribute(PARAM_USER, user);
            session.setAttribute(PARAM_ROLE, user.getRole());
            setSessionLifecycle(request, user);
            logger.info(ResourceManager.getProperty(MSG_SIGNED_IN) + user.getId());
            if (user.getRole() == Role.CUSTOMER) {
                return  (PARAM_REDIRECT_COMMAND);
            } else if (user.getRole() == Role.MANAGER) {
                return(PARAM_REDIRECT_COMMAND1);
            } else {
                return(PARAM_REDIRECT_COMMAND2);
            }
        } else {
            request.setAttribute(ATTRIBUTE_INCORRECT_MSG, PARAM_INCORRECT_MSG);
            return(JspPageName.ERROR_PAGE);
        }
    }

    private void setSessionLifecycle(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_LIFECYCLE);
        session.setAttribute(PARAM_USER, user);
    }
}
