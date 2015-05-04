package com.epam.task6.command.impl;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
import com.epam.task6.dao.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class realizes command which save customer specifications.
 *
 * Created by olga on 21.04.15.
 */
public class ShowDeveloperPage extends Command {
    private static Logger logger = Logger.getLogger("activity");

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_FORM_CORRECT = "isFormCorrect";
    private static final String PARAM_FORWARD_PAGE = "redirect.customer.show.order.form";


    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        setForward(JspPageName.DEVELOPER_PAGE);
    }

}
