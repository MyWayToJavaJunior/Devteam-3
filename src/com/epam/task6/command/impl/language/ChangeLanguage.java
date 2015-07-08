package com.epam.task6.command.impl.language;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command for change language.
 *
 * Created by olga on 28.04.15.
 */
public class ChangeLanguage extends Command {

    private static ChangeLanguage instance = new ChangeLanguage();

    /** Initializes activity logger */
    private static Logger logger = Logger.getLogger(ChangeLanguage.class);

    /** Logger messages */
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String PARAM_LANGUAGE = "language";
    private static final String SESSION_LOCALE_ATTRIBUTE = "localeValue";
    private static final String SESSION_PAGE_ATTRIBUTE = "page";

    public static ChangeLanguage getInstance() {
        return instance;
    }

    /**
     *   Implementation of command that change language.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(ATTRIBUTE_USER);
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter(PARAM_LANGUAGE);
        session.setAttribute(SESSION_LOCALE_ATTRIBUTE, currentLocale);
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return ((String) session.getAttribute(SESSION_PAGE_ATTRIBUTE));
    }
}