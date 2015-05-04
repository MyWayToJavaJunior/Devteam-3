package com.epam.task6.command.impl.language;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 28.04.15.
 */
public class ChangeLanguage extends Command {
    /* Initialize activity logger */
    private static Logger logger = Logger.getLogger(ChangeLanguage.class);

    /* Logger messages */
    private static final String MSG_ACTIVITY = "logger.activity.change.language";

    /* Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String PARAM_LANGUAGE_CHOICE = "choice";

    /* Forward page */
    private static final String REDIRECT_TO_DEFAULT_PAGE = "Controller?executionCommand=SHOW_SPECIFICATIONS";

    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws com.epam.task6.dao.DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter("language");
        session.setAttribute("localeValue", currentLocale);
        String page = (String) session.getAttribute("page");
        setForward(page);
    }
}